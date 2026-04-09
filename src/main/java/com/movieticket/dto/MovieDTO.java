package com.movieticket.dto;

import com.movieticket.entity.Movie;
import java.time.LocalDateTime;

public class MovieDTO {
    private Long movieId;
    private String title;
    private String description;
    private String genre;
    private Integer durationMinutes;
    private String language;
    private Movie.RatingType rating;
    private String director;
    private LocalDateTime releaseDate;

    public MovieDTO() {
    }

    public MovieDTO(Long movieId, String title, String description, String genre, Integer durationMinutes, String language, Movie.RatingType rating, String director, LocalDateTime releaseDate) {
        this.movieId = movieId;
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.durationMinutes = durationMinutes;
        this.language = language;
        this.rating = rating;
        this.director = director;
        this.releaseDate = releaseDate;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Movie.RatingType getRating() {
        return rating;
    }

    public void setRating(Movie.RatingType rating) {
        this.rating = rating;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }
}
