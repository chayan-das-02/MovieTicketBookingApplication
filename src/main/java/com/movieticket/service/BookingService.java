package com.movieticket.service;

import com.movieticket.dto.BookingDTO;
import com.movieticket.dto.BookingRequest;
import com.movieticket.dto.BookingStatisticsDTO;
import com.movieticket.entity.*;
import com.movieticket.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingDetailRepository bookingDetailRepository;
    private final UserRepository userRepository;
    private final ShowRepository showRepository;
    private final SeatRepository seatRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, BookingDetailRepository bookingDetailRepository,
                          UserRepository userRepository, ShowRepository showRepository,
                          SeatRepository seatRepository) {
        this.bookingRepository = bookingRepository;
        this.bookingDetailRepository = bookingDetailRepository;
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.seatRepository = seatRepository;
    }

    public BookingDTO createBooking(Long userId, BookingRequest bookingRequest) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Show show = showRepository.findById(bookingRequest.getShowId())
            .orElseThrow(() -> new IllegalArgumentException("Show not found"));

        // Check if show is currently screening (RUNNING status)
        if (!show.getStatus().equals(Show.ShowStatus.RUNNING)) {
            throw new IllegalArgumentException("This show is not currently available for booking. Status: " + show.getStatus());
        }

        // Validate seats are available
        List<Seat> selectedSeats = seatRepository.findAllById(bookingRequest.getSeatIds());
        
        if (selectedSeats.isEmpty()) {
            throw new IllegalArgumentException("No valid seats selected");
        }

        if (selectedSeats.size() != bookingRequest.getSeatIds().size()) {
            throw new IllegalArgumentException("Some seats not found");
        }

        // Check if all selected seats are available
        for (Seat seat : selectedSeats) {
            if (!seat.getStatus().equals(Seat.SeatStatus.AVAILABLE)) {
                throw new IllegalArgumentException("Seat " + seat.getRowNumber() + seat.getColumnNumber() + " is not available");
            }
        }

        // Create booking
        Booking booking = new Booking();
        booking.setBookingRef(generateBookingReference());
        booking.setUser(user);
        booking.setShow(show);
        booking.setNumberOfSeats(selectedSeats.size());
        booking.setTotalPrice(show.getTicketPrice().multiply(BigDecimal.valueOf(selectedSeats.size())));
        booking.setStatus(Booking.BookingStatus.PENDING);
        booking.setBookedAt(LocalDateTime.now());
        booking.setCreatedAt(LocalDateTime.now());
        booking.setUpdatedAt(LocalDateTime.now());

        Booking savedBooking = bookingRepository.save(booking);

        // Create booking details and update seat status
        for (Seat seat : selectedSeats) {
            BookingDetail bookingDetail = new BookingDetail();
            bookingDetail.setBooking(savedBooking);
            bookingDetail.setSeat(seat);
            bookingDetailRepository.save(bookingDetail);

            seat.setStatus(Seat.SeatStatus.BOOKED);
            seat.setUpdatedAt(LocalDateTime.now());
            seatRepository.save(seat);
        }

        // Update show seat counts
        show.setTotalSeatsBooked(show.getTotalSeatsBooked() + selectedSeats.size());
        showRepository.save(show);

        return convertToDTO(savedBooking);
    }

    public BookingDTO getBookingById(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
            .orElseThrow(() -> new IllegalArgumentException("Booking not found"));
        return convertToDTO(booking);
    }

    public BookingDTO getBookingByRef(String bookingRef) {
        Booking booking = bookingRepository.findByBookingRef(bookingRef)
            .orElseThrow(() -> new IllegalArgumentException("Booking not found"));
        return convertToDTO(booking);
    }

    public List<BookingDTO> getUserBookings(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return bookingRepository.findByUser(user)
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public List<BookingDTO> getBookingsByStatus(Booking.BookingStatus status) {
        return bookingRepository.findByStatus(status)
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public BookingDTO confirmBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
            .orElseThrow(() -> new IllegalArgumentException("Booking not found"));

        if (!booking.getStatus().equals(Booking.BookingStatus.PENDING)) {
            throw new IllegalArgumentException("Only pending bookings can be confirmed");
        }

        booking.setStatus(Booking.BookingStatus.CONFIRMED);
        booking.setUpdatedAt(LocalDateTime.now());
        Booking updatedBooking = bookingRepository.save(booking);
        return convertToDTO(updatedBooking);
    }

    public BookingDTO cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
            .orElseThrow(() -> new IllegalArgumentException("Booking not found"));

        if (booking.getStatus().equals(Booking.BookingStatus.CANCELLED)) {
            throw new IllegalArgumentException("Booking is already cancelled");
        }

        // Release seats
        List<BookingDetail> bookingDetails = bookingDetailRepository.findByBooking(booking);
        Show show = booking.getShow();

        for (BookingDetail detail : bookingDetails) {
            Seat seat = detail.getSeat();
            seat.setStatus(Seat.SeatStatus.AVAILABLE);
            seat.setUpdatedAt(LocalDateTime.now());
            seatRepository.save(seat);
        }

        // Update show seat count
        show.setTotalSeatsBooked(show.getTotalSeatsBooked() - bookingDetails.size());
        showRepository.save(show);

        booking.setStatus(Booking.BookingStatus.CANCELLED);
        booking.setCancelledAt(LocalDateTime.now());
        booking.setUpdatedAt(LocalDateTime.now());
        Booking updatedBooking = bookingRepository.save(booking);

        return convertToDTO(updatedBooking);
    }

    public List<BookingStatisticsDTO> getBookingStatisticsByUser() {
        List<User> users = userRepository.findAll();
        return users.stream()
            .map(user -> {
                List<Booking> userBookings = bookingRepository.findByUser(user);
                long totalBookings = userBookings.size();
                long confirmed = userBookings.stream()
                    .filter(b -> b.getStatus() == Booking.BookingStatus.CONFIRMED).count();
                long pending = userBookings.stream()
                    .filter(b -> b.getStatus() == Booking.BookingStatus.PENDING).count();
                long cancelled = userBookings.stream()
                    .filter(b -> b.getStatus() == Booking.BookingStatus.CANCELLED).count();

                return new BookingStatisticsDTO(
                    user.getUserId(),
                    user.getFirstName() + " " + user.getLastName(),
                    user.getEmail(),
                    (int) totalBookings,
                    (int) confirmed,
                    (int) pending,
                    (int) cancelled
                );
            })
            .collect(Collectors.toList());
    }

    private String generateBookingReference() {
        return "BK" + System.currentTimeMillis() + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    private BookingDTO convertToDTO(Booking booking) {
        return new BookingDTO(
            booking.getBookingId(),
            booking.getBookingRef(),
            booking.getUser().getUserId(),
            booking.getUser().getFirstName() + " " + booking.getUser().getLastName(),
            booking.getShow().getShowId(),
            booking.getShow().getMovie().getTitle(),
            booking.getShow().getStartTime(),
            booking.getShow().getScreen().getScreenName(),
            booking.getNumberOfSeats(),
            booking.getTotalPrice(),
            booking.getStatus().toString(),
            booking.getBookedAt(),
            booking.getCancelledAt()
        );
    }
}
