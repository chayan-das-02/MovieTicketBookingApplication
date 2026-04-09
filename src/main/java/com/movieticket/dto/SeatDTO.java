package com.movieticket.dto;

import com.movieticket.entity.Seat;

public class SeatDTO {
    private Long seatId;
    private Long showId;
    private String rowNumber;
    private Integer columnNumber;
    private Seat.SeatType seatType;
    private Seat.SeatStatus status;

    public SeatDTO() {
    }

    public SeatDTO(Long seatId, Long showId, String rowNumber, Integer columnNumber, Seat.SeatType seatType, Seat.SeatStatus status) {
        this.seatId = seatId;
        this.showId = showId;
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
        this.seatType = seatType;
        this.status = status;
    }

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public String getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(String rowNumber) {
        this.rowNumber = rowNumber;
    }

    public Integer getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(Integer columnNumber) {
        this.columnNumber = columnNumber;
    }

    public Seat.SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(Seat.SeatType seatType) {
        this.seatType = seatType;
    }

    public Seat.SeatStatus getStatus() {
        return status;
    }

    public void setStatus(Seat.SeatStatus status) {
        this.status = status;
    }
}
