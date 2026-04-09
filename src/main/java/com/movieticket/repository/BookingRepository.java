package com.movieticket.repository;

import com.movieticket.entity.Booking;
import com.movieticket.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findByBookingRef(String bookingRef);
    List<Booking> findByUser(User user);
    List<Booking> findByStatus(Booking.BookingStatus status);
    
    @Query("SELECT b FROM Booking b WHERE b.user.userId = :userId AND b.createdAt >= :startDate")
    List<Booking> findUserBookingsAfterDate(@Param("userId") Long userId, 
                                           @Param("startDate") LocalDateTime startDate);
    
    @Query("SELECT COUNT(b) FROM Booking b WHERE b.status = 'CONFIRMED' AND b.show.showId = :showId")
    Integer countConfirmedBookingsForShow(@Param("showId") Long showId);
}
