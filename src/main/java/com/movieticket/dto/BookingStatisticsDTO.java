package com.movieticket.dto;

public class BookingStatisticsDTO {
    private Long userId;
    private String userName;
    private String userEmail;
    private Integer totalBookings;
    private Integer confirmedBookings;
    private Integer pendingBookings;
    private Integer cancelledBookings;

    public BookingStatisticsDTO() {
    }

    public BookingStatisticsDTO(Long userId, String userName, String userEmail, Integer totalBookings,
                               Integer confirmedBookings, Integer pendingBookings, Integer cancelledBookings) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.totalBookings = totalBookings;
        this.confirmedBookings = confirmedBookings;
        this.pendingBookings = pendingBookings;
        this.cancelledBookings = cancelledBookings;
    }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }

    public Integer getTotalBookings() { return totalBookings; }
    public void setTotalBookings(Integer totalBookings) { this.totalBookings = totalBookings; }

    public Integer getConfirmedBookings() { return confirmedBookings; }
    public void setConfirmedBookings(Integer confirmedBookings) { this.confirmedBookings = confirmedBookings; }

    public Integer getPendingBookings() { return pendingBookings; }
    public void setPendingBookings(Integer pendingBookings) { this.pendingBookings = pendingBookings; }

    public Integer getCancelledBookings() { return cancelledBookings; }
    public void setCancelledBookings(Integer cancelledBookings) { this.cancelledBookings = cancelledBookings; }
}
