package com.movieticket.service;

import com.movieticket.dto.MovieDTO;
import com.movieticket.entity.Movie;
import com.movieticket.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public MovieDTO createMovie(MovieDTO movieDTO) {
        Movie movie = new Movie();
        movie.setTitle(movieDTO.getTitle());
        movie.setDescription(movieDTO.getDescription());
        movie.setGenre(movieDTO.getGenre());
        movie.setDurationMinutes(movieDTO.getDurationMinutes());
        movie.setLanguage(movieDTO.getLanguage());
        movie.setRating(movieDTO.getRating());
        movie.setDirector(movieDTO.getDirector());
        // Set release date - use provided date or default to today
        movie.setReleaseDate(movieDTO.getReleaseDate() != null ? movieDTO.getReleaseDate() : LocalDateTime.now());
        movie.setCreatedAt(LocalDateTime.now());
        movie.setUpdatedAt(LocalDateTime.now());

        Movie savedMovie = movieRepository.save(movie);
        return convertToDTO(savedMovie);
    }

    public MovieDTO getMovieById(Long movieId) {
        Movie movie = movieRepository.findById(movieId)
            .orElseThrow(() -> new IllegalArgumentException("Movie not found"));
        return convertToDTO(movie);
    }

    public List<MovieDTO> getAllMovies() {
        return movieRepository.findAll()
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public List<MovieDTO> getMoviesByGenre(String genre) {
        return movieRepository.findByGenre(genre)
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public List<MovieDTO> getMoviesByLanguage(String language) {
        return movieRepository.findByLanguage(language)
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public List<MovieDTO> getMoviesByReleaseDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return movieRepository.findMoviesByReleaseDateRange(startDate, endDate)
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public MovieDTO updateMovie(Long movieId, MovieDTO movieDTO) {
        Movie movie = movieRepository.findById(movieId)
            .orElseThrow(() -> new IllegalArgumentException("Movie not found"));

        if (movieDTO.getTitle() != null) movie.setTitle(movieDTO.getTitle());
        if (movieDTO.getDescription() != null) movie.setDescription(movieDTO.getDescription());
        if (movieDTO.getGenre() != null) movie.setGenre(movieDTO.getGenre());
        if (movieDTO.getDurationMinutes() != null) movie.setDurationMinutes(movieDTO.getDurationMinutes());
        if (movieDTO.getLanguage() != null) movie.setLanguage(movieDTO.getLanguage());
        if (movieDTO.getRating() != null) movie.setRating(movieDTO.getRating());
        if (movieDTO.getDirector() != null) movie.setDirector(movieDTO.getDirector());

        movie.setUpdatedAt(LocalDateTime.now());
        Movie updatedMovie = movieRepository.save(movie);
        return convertToDTO(updatedMovie);
    }

    public void deleteMovie(Long movieId) {
        if (!movieRepository.existsById(movieId)) {
            throw new IllegalArgumentException("Movie not found");
        }
        movieRepository.deleteById(movieId);
    }

    private MovieDTO convertToDTO(Movie movie) {
        return new MovieDTO(
            movie.getMovieId(),
            movie.getTitle(),
            movie.getDescription(),
            movie.getGenre(),
            movie.getDurationMinutes(),
            movie.getLanguage(),
            movie.getRating(),
            movie.getDirector(),
            movie.getReleaseDate()
        );
    }
}
