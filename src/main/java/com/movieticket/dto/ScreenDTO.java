package com.movieticket.dto;

import com.movieticket.entity.Screen;

public class ScreenDTO {
    private Long screenId;
    private Long theaterId;
    private String screenName;
    private Integer totalSeats;
    private Integer totalRows;
    private Integer seatsPerRow;
    private Screen.ScreenType screenType;

    public ScreenDTO() {
    }

    public ScreenDTO(Long screenId, Long theaterId, String screenName, Integer totalSeats, Integer totalRows, Integer seatsPerRow, Screen.ScreenType screenType) {
        this.screenId = screenId;
        this.theaterId = theaterId;
        this.screenName = screenName;
        this.totalSeats = totalSeats;
        this.totalRows = totalRows;
        this.seatsPerRow = seatsPerRow;
        this.screenType = screenType;
    }

    public Long getScreenId() {
        return screenId;
    }

    public void setScreenId(Long screenId) {
        this.screenId = screenId;
    }

    public Long getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(Long theaterId) {
        this.theaterId = theaterId;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public Integer getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(Integer totalSeats) {
        this.totalSeats = totalSeats;
    }

    public Integer getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Integer totalRows) {
        this.totalRows = totalRows;
    }

    public Integer getSeatsPerRow() {
        return seatsPerRow;
    }

    public void setSeatsPerRow(Integer seatsPerRow) {
        this.seatsPerRow = seatsPerRow;
    }

    public Screen.ScreenType getScreenType() {
        return screenType;
    }

    public void setScreenType(Screen.ScreenType screenType) {
        this.screenType = screenType;
    }
}
