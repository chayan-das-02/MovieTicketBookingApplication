package com.movieticket.dto;

import com.movieticket.entity.Booking;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BookingDTO {
    private Long bookingId;
    private String bookingRef;
    private Long userId;
    private String userName;
    private Long showId;
    private String movieTitle;
    private LocalDateTime showStartTime;
    private String screenName;
    private Integer numberOfSeats;
    private BigDecimal totalPrice;
    private Booking.BookingStatus status;
    private LocalDateTime bookedAt;
    private LocalDateTime cancelledAt;

    public BookingDTO() {
    }

    public BookingDTO(Long bookingId, String bookingRef, Long userId, Long showId, Integer numberOfSeats, BigDecimal totalPrice, Booking.BookingStatus status, LocalDateTime bookedAt) {
        this.bookingId = bookingId;
        this.bookingRef = bookingRef;
        this.userId = userId;
        this.showId = showId;
        this.numberOfSeats = numberOfSeats;
        this.totalPrice = totalPrice;
        this.status = status;
        this.bookedAt = bookedAt;
    }

    public BookingDTO(Long bookingId, String bookingRef, Long userId, String userName, Long showId,
                     String movieTitle, LocalDateTime showStartTime, String screenName,
                     Integer numberOfSeats, BigDecimal totalPrice, String statusStr,
                     LocalDateTime bookedAt, LocalDateTime cancelledAt) {
        this.bookingId = bookingId;
        this.bookingRef = bookingRef;
        this.userId = userId;
        this.userName = userName;
        this.showId = showId;
        this.movieTitle = movieTitle;
        this.showStartTime = showStartTime;
        this.screenName = screenName;
        this.numberOfSeats = numberOfSeats;
        this.totalPrice = totalPrice;
        this.status = Booking.BookingStatus.valueOf(statusStr);
        this.bookedAt = bookedAt;
        this.cancelledAt = cancelledAt;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public String getBookingRef() {
        return bookingRef;
    }

    public void setBookingRef(String bookingRef) {
        this.bookingRef = bookingRef;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public LocalDateTime getShowStartTime() {
        return showStartTime;
    }

    public void setShowStartTime(LocalDateTime showStartTime) {
        this.showStartTime = showStartTime;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Booking.BookingStatus getStatus() {
        return status;
    }

    public void setStatus(Booking.BookingStatus status) {
        this.status = status;
    }

    public LocalDateTime getBookedAt() {
        return bookedAt;
    }

    public void setBookedAt(LocalDateTime bookedAt) {
        this.bookedAt = bookedAt;
    }

    public LocalDateTime getCancelledAt() {
        return cancelledAt;
    }

    public void setCancelledAt(LocalDateTime cancelledAt) {
        this.cancelledAt = cancelledAt;
    }
}
