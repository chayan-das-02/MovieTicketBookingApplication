package com.movieticket.repository;

import com.movieticket.entity.Movie;
import com.movieticket.entity.Screen;
import com.movieticket.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
    List<Show> findByMovie(Movie movie);
    List<Show> findByScreen(Screen screen);
    List<Show> findByStatus(Show.ShowStatus status);
    
    @Query("SELECT s FROM Show s WHERE s.startTime >= :startTime AND s.startTime <= :endTime")
    List<Show> findShowsByTimeRange(@Param("startTime") LocalDateTime startTime, 
                                    @Param("endTime") LocalDateTime endTime);
    
    @Query("SELECT s FROM Show s WHERE s.movie.movieId = :movieId AND s.startTime >= :startTime")
    List<Show> findUpcomingShowsForMovie(@Param("movieId") Long movieId, 
                                        @Param("startTime") LocalDateTime startTime);
    
    @Query("SELECT s FROM Show s WHERE s.screen.theater.theaterId = :theaterId ORDER BY s.startTime")
    List<Show> findByTheater(@Param("theaterId") Long theaterId);
}
