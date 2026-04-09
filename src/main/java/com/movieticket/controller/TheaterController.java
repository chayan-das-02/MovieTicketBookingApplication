package com.movieticket.controller;

import com.movieticket.dto.TheaterDTO;
import com.movieticket.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/theaters")
@CrossOrigin(origins = "*")
public class TheaterController {

    private final TheaterService theaterService;

    @Autowired
    public TheaterController(TheaterService theaterService) {
        this.theaterService = theaterService;
    }

    @PostMapping
    public ResponseEntity<TheaterDTO> createTheater(@RequestBody TheaterDTO theaterDTO) {
        TheaterDTO savedTheater = theaterService.createTheater(theaterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTheater);
    }

    @GetMapping("/{theaterId}")
    public ResponseEntity<TheaterDTO> getTheaterById(@PathVariable Long theaterId) {
        TheaterDTO theaterDTO = theaterService.getTheaterById(theaterId);
        return ResponseEntity.ok(theaterDTO);
    }

    @GetMapping
    public ResponseEntity<List<TheaterDTO>> getAllTheaters() {
        List<TheaterDTO> theaters = theaterService.getAllTheaters();
        return ResponseEntity.ok(theaters);
    }

    @GetMapping("/by-city/{city}")
    public ResponseEntity<List<TheaterDTO>> getTheatersByCity(@PathVariable String city) {
        List<TheaterDTO> theaters = theaterService.getTheatersByCity(city);
        return ResponseEntity.ok(theaters);
    }

    @PutMapping("/{theaterId}")
    public ResponseEntity<TheaterDTO> updateTheater(@PathVariable Long theaterId, 
                                                     @RequestBody TheaterDTO theaterDTO) {
        TheaterDTO updatedTheater = theaterService.updateTheater(theaterId, theaterDTO);
        return ResponseEntity.ok(updatedTheater);
    }

    @DeleteMapping("/{theaterId}")
    public ResponseEntity<Void> deleteTheater(@PathVariable Long theaterId) {
        theaterService.deleteTheater(theaterId);
        return ResponseEntity.noContent().build();
    }
}
