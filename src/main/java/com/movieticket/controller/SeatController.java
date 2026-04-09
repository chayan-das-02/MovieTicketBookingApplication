package com.movieticket.controller;

import com.movieticket.dto.SeatDTO;
import com.movieticket.entity.Seat;
import com.movieticket.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/seats")
@CrossOrigin(origins = "*")
public class SeatController {

    private final SeatService seatService;

    @Autowired
    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @PostMapping("/show/{showId}/initialize")
    public ResponseEntity<Void> initializeSeatsForShow(@PathVariable Long showId) {
        seatService.initializeSeatsForShow(showId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{seatId}")
    public ResponseEntity<SeatDTO> getSeatById(@PathVariable Long seatId) {
        SeatDTO seatDTO = seatService.getSeatById(seatId);
        return ResponseEntity.ok(seatDTO);
    }

    @GetMapping("/show/{showId}")
    public ResponseEntity<List<SeatDTO>> getAllSeatsForShow(@PathVariable Long showId) {
        List<SeatDTO> seats = seatService.getAllSeatsForShow(showId);
        return ResponseEntity.ok(seats);
    }

    @GetMapping("/show/{showId}/available")
    public ResponseEntity<List<SeatDTO>> getAvailableSeatsForShow(@PathVariable Long showId) {
        List<SeatDTO> seats = seatService.getAvailableSeatsForShow(showId);
        return ResponseEntity.ok(seats);
    }

    @GetMapping("/show/{showId}/status/{status}")
    public ResponseEntity<List<SeatDTO>> getSeatsByStatus(@PathVariable Long showId, 
                                                          @PathVariable Seat.SeatStatus status) {
        List<SeatDTO> seats = seatService.getSeatsByStatus(showId, status);
        return ResponseEntity.ok(seats);
    }

    @GetMapping("/show/{showId}/booked-count")
    public ResponseEntity<Map<String, Integer>> getBookedSeatsCount(@PathVariable Long showId) {
        Integer count = seatService.getBookedSeatsCount(showId);
        return ResponseEntity.ok(Map.of("bookedSeats", count));
    }
}
