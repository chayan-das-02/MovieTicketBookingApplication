package com.movieticket.repository;

import com.movieticket.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findByTitle(String title);
    List<Movie> findByGenre(String genre);
    List<Movie> findByLanguage(String language);
    
    @Query("SELECT m FROM Movie m WHERE m.releaseDate >= :startDate AND m.releaseDate <= :endDate")
    List<Movie> findMoviesByReleaseDateRange(@Param("startDate") LocalDateTime startDate, 
                                             @Param("endDate") LocalDateTime endDate);
}
