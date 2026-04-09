package com.movieticket.repository;

import com.movieticket.entity.Seat;
import com.movieticket.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByShow(Show show);
    List<Seat> findByShowAndStatus(Show show, Seat.SeatStatus status);
    Optional<Seat> findByShowAndRowNumberAndColumnNumber(Show show, String rowNumber, Integer columnNumber);
    
    @Query("SELECT s FROM Seat s WHERE s.show.showId = :showId AND s.status = 'AVAILABLE'")
    List<Seat> findAvailableSeatsForShow(@Param("showId") Long showId);
    
    @Query("SELECT COUNT(s) FROM Seat s WHERE s.show.showId = :showId AND s.status = 'BOOKED'")
    Integer countBookedSeatsForShow(@Param("showId") Long showId);
}
