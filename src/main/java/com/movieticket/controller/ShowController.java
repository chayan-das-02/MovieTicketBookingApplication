package com.movieticket.controller;

import com.movieticket.dto.ShowDTO;
import com.movieticket.entity.Show;
import com.movieticket.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shows")
@CrossOrigin(origins = "*")
public class ShowController {

    private final ShowService showService;

    @Autowired
    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    @PostMapping
    public ResponseEntity<ShowDTO> createShow(@RequestBody ShowDTO showDTO) {
        ShowDTO savedShow = showService.createShow(showDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedShow);
    }

    @GetMapping("/{showId}")
    public ResponseEntity<ShowDTO> getShowById(@PathVariable Long showId) {
        ShowDTO showDTO = showService.getShowById(showId);
        return ResponseEntity.ok(showDTO);
    }

    @GetMapping
    public ResponseEntity<List<ShowDTO>> getAllShows() {
        List<ShowDTO> shows = showService.getAllShows();
        return ResponseEntity.ok(shows);
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<ShowDTO>> getShowsByMovie(@PathVariable Long movieId) {
        List<ShowDTO> shows = showService.getShowsByMovie(movieId);
        return ResponseEntity.ok(shows);
    }

    @GetMapping("/movie/{movieId}/upcoming")
    public ResponseEntity<List<ShowDTO>> getUpcomingShowsForMovie(@PathVariable Long movieId) {
        List<ShowDTO> shows = showService.getUpcomingShowsForMovie(movieId);
        return ResponseEntity.ok(shows);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ShowDTO>> getShowsByStatus(@PathVariable Show.ShowStatus status) {
        List<ShowDTO> shows = showService.getShowsByStatus(status);
        return ResponseEntity.ok(shows);
    }

    @GetMapping("/theater/{theaterId}")
    public ResponseEntity<List<ShowDTO>> getShowsByTheater(@PathVariable Long theaterId) {
        List<ShowDTO> shows = showService.getShowsByTheater(theaterId);
        return ResponseEntity.ok(shows);
    }

    @PatchMapping("/{showId}/status")
    public ResponseEntity<ShowDTO> updateShowStatus(@PathVariable Long showId, 
                                                   @RequestParam Show.ShowStatus status) {
        ShowDTO updatedShow = showService.updateShowStatus(showId, status);
        return ResponseEntity.ok(updatedShow);
    }

    @DeleteMapping("/{showId}")
    public ResponseEntity<Void> deleteShow(@PathVariable Long showId) {
        showService.deleteShow(showId);
        return ResponseEntity.noContent().build();
    }
}
