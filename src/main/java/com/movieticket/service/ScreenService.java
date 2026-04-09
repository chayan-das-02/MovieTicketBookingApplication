package com.movieticket.service;

import com.movieticket.dto.ScreenDTO;
import com.movieticket.entity.Screen;
import com.movieticket.entity.Theater;
import com.movieticket.repository.ScreenRepository;
import com.movieticket.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ScreenService {

    private final ScreenRepository screenRepository;
    private final TheaterRepository theaterRepository;

    @Autowired
    public ScreenService(ScreenRepository screenRepository, TheaterRepository theaterRepository) {
        this.screenRepository = screenRepository;
        this.theaterRepository = theaterRepository;
    }

    public ScreenDTO createScreen(ScreenDTO screenDTO) {
        Theater theater = theaterRepository.findById(screenDTO.getTheaterId())
            .orElseThrow(() -> new IllegalArgumentException("Theater not found"));

        Screen screen = new Screen();
        screen.setTheater(theater);
        screen.setScreenName(screenDTO.getScreenName());
        screen.setTotalSeats(screenDTO.getTotalSeats());
        screen.setTotalRows(screenDTO.getTotalRows());
        screen.setSeatsPerRow(screenDTO.getSeatsPerRow());
        screen.setScreenType(screenDTO.getScreenType());
        screen.setCreatedAt(LocalDateTime.now());
        screen.setUpdatedAt(LocalDateTime.now());

        Screen savedScreen = screenRepository.save(screen);
        return convertToDTO(savedScreen);
    }

    public ScreenDTO getScreenById(Long screenId) {
        Screen screen = screenRepository.findById(screenId)
            .orElseThrow(() -> new IllegalArgumentException("Screen not found"));
        return convertToDTO(screen);
    }

    public List<ScreenDTO> getAllScreens() {
        return screenRepository.findAll()
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public List<ScreenDTO> getScreensByTheater(Long theaterId) {
        Theater theater = theaterRepository.findById(theaterId)
            .orElseThrow(() -> new IllegalArgumentException("Theater not found"));
        return screenRepository.findByTheater(theater)
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public ScreenDTO updateScreen(Long screenId, ScreenDTO screenDTO) {
        Screen screen = screenRepository.findById(screenId)
            .orElseThrow(() -> new IllegalArgumentException("Screen not found"));

        if (screenDTO.getScreenName() != null) screen.setScreenName(screenDTO.getScreenName());
        if (screenDTO.getTotalSeats() != null) screen.setTotalSeats(screenDTO.getTotalSeats());
        if (screenDTO.getTotalRows() != null) screen.setTotalRows(screenDTO.getTotalRows());
        if (screenDTO.getSeatsPerRow() != null) screen.setSeatsPerRow(screenDTO.getSeatsPerRow());
        if (screenDTO.getScreenType() != null) screen.setScreenType(screenDTO.getScreenType());

        screen.setUpdatedAt(LocalDateTime.now());
        Screen updatedScreen = screenRepository.save(screen);
        return convertToDTO(updatedScreen);
    }

    public void deleteScreen(Long screenId) {
        if (!screenRepository.existsById(screenId)) {
            throw new IllegalArgumentException("Screen not found");
        }
        screenRepository.deleteById(screenId);
    }

    private ScreenDTO convertToDTO(Screen screen) {
        return new ScreenDTO(
            screen.getScreenId(),
            screen.getTheater().getTheaterId(),
            screen.getScreenName(),
            screen.getTotalSeats(),
            screen.getTotalRows(),
            screen.getSeatsPerRow(),
            screen.getScreenType()
        );
    }
}
