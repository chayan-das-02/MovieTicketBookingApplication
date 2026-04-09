package com.movieticket.service;

import com.movieticket.dto.TheaterDTO;
import com.movieticket.entity.Theater;
import com.movieticket.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TheaterService {

    private final TheaterRepository theaterRepository;

    @Autowired
    public TheaterService(TheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }

    public TheaterDTO createTheater(TheaterDTO theaterDTO) {
        Theater theater = new Theater();
        theater.setName(theaterDTO.getName());
        theater.setCity(theaterDTO.getCity());
        theater.setAddress(theaterDTO.getAddress());
        theater.setPhoneNumber(theaterDTO.getPhoneNumber());
        theater.setEmail(theaterDTO.getEmail());
        theater.setTotalScreens(theaterDTO.getTotalScreens());
        theater.setCreatedAt(LocalDateTime.now());
        theater.setUpdatedAt(LocalDateTime.now());

        Theater savedTheater = theaterRepository.save(theater);
        return convertToDTO(savedTheater);
    }

    public TheaterDTO getTheaterById(Long theaterId) {
        Theater theater = theaterRepository.findById(theaterId)
            .orElseThrow(() -> new IllegalArgumentException("Theater not found"));
        return convertToDTO(theater);
    }

    public List<TheaterDTO> getAllTheaters() {
        return theaterRepository.findAll()
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public List<TheaterDTO> getTheatersByCity(String city) {
        return theaterRepository.findByCity(city)
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public TheaterDTO updateTheater(Long theaterId, TheaterDTO theaterDTO) {
        Theater theater = theaterRepository.findById(theaterId)
            .orElseThrow(() -> new IllegalArgumentException("Theater not found"));

        if (theaterDTO.getName() != null) theater.setName(theaterDTO.getName());
        if (theaterDTO.getCity() != null) theater.setCity(theaterDTO.getCity());
        if (theaterDTO.getAddress() != null) theater.setAddress(theaterDTO.getAddress());
        if (theaterDTO.getPhoneNumber() != null) theater.setPhoneNumber(theaterDTO.getPhoneNumber());
        if (theaterDTO.getEmail() != null) theater.setEmail(theaterDTO.getEmail());
        if (theaterDTO.getTotalScreens() != null) theater.setTotalScreens(theaterDTO.getTotalScreens());

        theater.setUpdatedAt(LocalDateTime.now());
        Theater updatedTheater = theaterRepository.save(theater);
        return convertToDTO(updatedTheater);
    }

    public void deleteTheater(Long theaterId) {
        if (!theaterRepository.existsById(theaterId)) {
            throw new IllegalArgumentException("Theater not found");
        }
        theaterRepository.deleteById(theaterId);
    }

    private TheaterDTO convertToDTO(Theater theater) {
        return new TheaterDTO(
            theater.getTheaterId(),
            theater.getName(),
            theater.getCity(),
            theater.getAddress(),
            theater.getPhoneNumber(),
            theater.getEmail(),
            theater.getTotalScreens()
        );
    }
}
