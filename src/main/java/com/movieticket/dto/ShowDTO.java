package com.movieticket.dto;

import com.movieticket.entity.Show;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ShowDTO {
    private Long showId;
    private Long movieId;
    private Long screenId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private BigDecimal ticketPrice;
    private Integer totalSeatsAvailable;
    private Integer totalSeatsBooked;
    private Show.ShowStatus status;

    public ShowDTO() {
    }

    public ShowDTO(Long showId, Long movieId, Long screenId, LocalDateTime startTime, LocalDateTime endTime, BigDecimal ticketPrice, Integer totalSeatsAvailable, Integer totalSeatsBooked, Show.ShowStatus status) {
        this.showId = showId;
        this.movieId = movieId;
        this.screenId = screenId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.ticketPrice = ticketPrice;
        this.totalSeatsAvailable = totalSeatsAvailable;
        this.totalSeatsBooked = totalSeatsBooked;
        this.status = status;
    }

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getScreenId() {
        return screenId;
    }

    public void setScreenId(Long screenId) {
        this.screenId = screenId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Integer getTotalSeatsAvailable() {
        return totalSeatsAvailable;
    }

    public void setTotalSeatsAvailable(Integer totalSeatsAvailable) {
        this.totalSeatsAvailable = totalSeatsAvailable;
    }

    public Integer getTotalSeatsBooked() {
        return totalSeatsBooked;
    }

    public void setTotalSeatsBooked(Integer totalSeatsBooked) {
        this.totalSeatsBooked = totalSeatsBooked;
    }

    public Show.ShowStatus getStatus() {
        return status;
    }

    public void setStatus(Show.ShowStatus status) {
        this.status = status;
    }
}
