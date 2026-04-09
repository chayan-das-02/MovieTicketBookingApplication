package com.movieticket.controller;

import com.movieticket.dto.BookingDTO;
import com.movieticket.dto.BookingRequest;
import com.movieticket.dto.BookingStatisticsDTO;
import com.movieticket.entity.Booking;
import com.movieticket.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
@CrossOrigin(origins = "*")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/users/{userId}")
    public ResponseEntity<BookingDTO> createBooking(@PathVariable Long userId, 
                                                    @Valid @RequestBody BookingRequest bookingRequest) {
        BookingDTO bookingDTO = bookingService.createBooking(userId, bookingRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingDTO);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long bookingId) {
        BookingDTO bookingDTO = bookingService.getBookingById(bookingId);
        return ResponseEntity.ok(bookingDTO);
    }

    @GetMapping("/ref/{bookingRef}")
    public ResponseEntity<BookingDTO> getBookingByRef(@PathVariable String bookingRef) {
        BookingDTO bookingDTO = bookingService.getBookingByRef(bookingRef);
        return ResponseEntity.ok(bookingDTO);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingDTO>> getUserBookings(@PathVariable Long userId) {
        List<BookingDTO> bookings = bookingService.getUserBookings(userId);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<BookingDTO>> getBookingsByStatus(@PathVariable Booking.BookingStatus status) {
        List<BookingDTO> bookings = bookingService.getBookingsByStatus(status);
        return ResponseEntity.ok(bookings);
    }

    @PatchMapping("/{bookingId}/confirm")
    public ResponseEntity<BookingDTO> confirmBooking(@PathVariable Long bookingId) {
        BookingDTO bookingDTO = bookingService.confirmBooking(bookingId);
        return ResponseEntity.ok(bookingDTO);
    }

    @PatchMapping("/{bookingId}/cancel")
    public ResponseEntity<BookingDTO> cancelBooking(@PathVariable Long bookingId) {
        BookingDTO bookingDTO = bookingService.cancelBooking(bookingId);
        return ResponseEntity.ok(bookingDTO);
    }

    @GetMapping("/stats/by-user")
    public ResponseEntity<List<BookingStatisticsDTO>> getBookingStatisticsByUser() {
        List<BookingStatisticsDTO> stats = bookingService.getBookingStatisticsByUser();
        return ResponseEntity.ok(stats);
    }
}
