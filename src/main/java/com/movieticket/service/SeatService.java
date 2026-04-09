package com.movieticket.service;

import com.movieticket.dto.SeatDTO;
import com.movieticket.entity.Screen;
import com.movieticket.entity.Seat;
import com.movieticket.entity.Show;
import com.movieticket.repository.SeatRepository;
import com.movieticket.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SeatService {

    private final SeatRepository seatRepository;
    private final ShowRepository showRepository;

    @Autowired
    public SeatService(SeatRepository seatRepository, ShowRepository showRepository) {
        this.seatRepository = seatRepository;
        this.showRepository = showRepository;
    }

    public void initializeSeatsForShow(Long showId) {
        Show show = showRepository.findById(showId)
            .orElseThrow(() -> new IllegalArgumentException("Show not found"));

        Screen screen = show.getScreen();
        List<Seat> seats = new ArrayList<>();

        // Create seats based on screen layout
        String[] rows = {"A", "B", "C", "D", "E", "F", "G", "H"};

        for (int row = 0; row < screen.getTotalRows() && row < rows.length; row++) {
            for (int col = 1; col <= screen.getSeatsPerRow(); col++) {
                Seat seat = new Seat();
                seat.setShow(show);
                seat.setRowNumber(rows[row]);
                seat.setColumnNumber(col);
                seat.setSeatType(Seat.SeatType.STANDARD);
                seat.setStatus(Seat.SeatStatus.AVAILABLE);
                seat.setCreatedAt(LocalDateTime.now());
                seat.setUpdatedAt(LocalDateTime.now());
                seats.add(seat);
            }
        }

        seatRepository.saveAll(seats);
    }

    public List<SeatDTO> getAvailableSeatsForShow(Long showId) {
        return seatRepository.findAvailableSeatsForShow(showId)
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public List<SeatDTO> getAllSeatsForShow(Long showId) {
        Show show = showRepository.findById(showId)
            .orElseThrow(() -> new IllegalArgumentException("Show not found"));
        return seatRepository.findByShow(show)
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public SeatDTO getSeatById(Long seatId) {
        Seat seat = seatRepository.findById(seatId)
            .orElseThrow(() -> new IllegalArgumentException("Seat not found"));
        return convertToDTO(seat);
    }

    public List<SeatDTO> getSeatsByStatus(Long showId, Seat.SeatStatus status) {
        Show show = showRepository.findById(showId)
            .orElseThrow(() -> new IllegalArgumentException("Show not found"));
        return seatRepository.findByShowAndStatus(show, status)
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public Integer getBookedSeatsCount(Long showId) {
        return seatRepository.countBookedSeatsForShow(showId);
    }

    private SeatDTO convertToDTO(Seat seat) {
        return new SeatDTO(
            seat.getSeatId(),
            seat.getShow().getShowId(),
            seat.getRowNumber(),
            seat.getColumnNumber(),
            seat.getSeatType(),
            seat.getStatus()
        );
    }
}
