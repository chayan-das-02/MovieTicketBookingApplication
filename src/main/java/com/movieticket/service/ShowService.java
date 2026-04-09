package com.movieticket.service;

import com.movieticket.dto.ShowDTO;
import com.movieticket.entity.Show;
import com.movieticket.entity.Movie;
import com.movieticket.entity.Screen;
import com.movieticket.repository.ShowRepository;
import com.movieticket.repository.MovieRepository;
import com.movieticket.repository.ScreenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ShowService {

    private final ShowRepository showRepository;
    private final MovieRepository movieRepository;
    private final ScreenRepository screenRepository;

    @Autowired
    public ShowService(ShowRepository showRepository, MovieRepository movieRepository, ScreenRepository screenRepository) {
        this.showRepository = showRepository;
        this.movieRepository = movieRepository;
        this.screenRepository = screenRepository;
    }

    public ShowDTO createShow(ShowDTO showDTO) {
        Movie movie = movieRepository.findById(showDTO.getMovieId())
            .orElseThrow(() -> new IllegalArgumentException("Movie not found"));
        Screen screen = screenRepository.findById(showDTO.getScreenId())
            .orElseThrow(() -> new IllegalArgumentException("Screen not found"));

        Show show = new Show();
        show.setMovie(movie);
        show.setScreen(screen);
        show.setStartTime(showDTO.getStartTime());
        show.setEndTime(showDTO.getEndTime());
        show.setTicketPrice(showDTO.getTicketPrice());
        show.setTotalSeatsAvailable(screen.getTotalSeats());
        show.setTotalSeatsBooked(0);
        show.setStatus(Show.ShowStatus.UPCOMING);
        show.setCreatedAt(LocalDateTime.now());
        show.setUpdatedAt(LocalDateTime.now());

        Show savedShow = showRepository.save(show);
        return convertToDTO(savedShow);
    }

    public ShowDTO getShowById(Long showId) {
        Show show = showRepository.findById(showId)
            .orElseThrow(() -> new IllegalArgumentException("Show not found"));
        return convertToDTO(show);
    }

    public List<ShowDTO> getAllShows() {
        return showRepository.findAll()
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public List<ShowDTO> getShowsByMovie(Long movieId) {
        Movie movie = movieRepository.findById(movieId)
            .orElseThrow(() -> new IllegalArgumentException("Movie not found"));
        return showRepository.findByMovie(movie)
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public List<ShowDTO> getUpcomingShowsForMovie(Long movieId) {
        return showRepository.findUpcomingShowsForMovie(movieId, LocalDateTime.now())
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public List<ShowDTO> getShowsByStatus(Show.ShowStatus status) {
        return showRepository.findByStatus(status)
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public List<ShowDTO> getShowsByTheater(Long theaterId) {
        return showRepository.findByTheater(theaterId)
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public ShowDTO updateShowStatus(Long showId, Show.ShowStatus status) {
        Show show = showRepository.findById(showId)
            .orElseThrow(() -> new IllegalArgumentException("Show not found"));
        show.setStatus(status);
        show.setUpdatedAt(LocalDateTime.now());
        Show updatedShow = showRepository.save(show);
        return convertToDTO(updatedShow);
    }

    public void deleteShow(Long showId) {
        if (!showRepository.existsById(showId)) {
            throw new IllegalArgumentException("Show not found");
        }
        showRepository.deleteById(showId);
    }

    private ShowDTO convertToDTO(Show show) {
        return new ShowDTO(
            show.getShowId(),
            show.getMovie().getMovieId(),
            show.getScreen().getScreenId(),
            show.getStartTime(),
            show.getEndTime(),
            show.getTicketPrice(),
            show.getTotalSeatsAvailable(),
            show.getTotalSeatsBooked(),
            show.getStatus()
        );
    }
}
