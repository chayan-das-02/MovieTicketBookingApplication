package com.movieticket.controller;

import com.movieticket.dto.ScreenDTO;
import com.movieticket.service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/screens")
@CrossOrigin(origins = "*")
public class ScreenController {

    private final ScreenService screenService;

    @Autowired
    public ScreenController(ScreenService screenService) {
        this.screenService = screenService;
    }

    @PostMapping
    public ResponseEntity<ScreenDTO> createScreen(@RequestBody ScreenDTO screenDTO) {
        ScreenDTO savedScreen = screenService.createScreen(screenDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedScreen);
    }

    @GetMapping("/{screenId}")
    public ResponseEntity<ScreenDTO> getScreenById(@PathVariable Long screenId) {
        ScreenDTO screenDTO = screenService.getScreenById(screenId);
        return ResponseEntity.ok(screenDTO);
    }

    @GetMapping
    public ResponseEntity<List<ScreenDTO>> getAllScreens() {
        List<ScreenDTO> screens = screenService.getAllScreens();
        return ResponseEntity.ok(screens);
    }

    @GetMapping("/theater/{theaterId}")
    public ResponseEntity<List<ScreenDTO>> getScreensByTheater(@PathVariable Long theaterId) {
        List<ScreenDTO> screens = screenService.getScreensByTheater(theaterId);
        return ResponseEntity.ok(screens);
    }

    @PutMapping("/{screenId}")
    public ResponseEntity<ScreenDTO> updateScreen(@PathVariable Long screenId, 
                                                   @RequestBody ScreenDTO screenDTO) {
        ScreenDTO updatedScreen = screenService.updateScreen(screenId, screenDTO);
        return ResponseEntity.ok(updatedScreen);
    }

    @DeleteMapping("/{screenId}")
    public ResponseEntity<Void> deleteScreen(@PathVariable Long screenId) {
        screenService.deleteScreen(screenId);
        return ResponseEntity.noContent().build();
    }
}
