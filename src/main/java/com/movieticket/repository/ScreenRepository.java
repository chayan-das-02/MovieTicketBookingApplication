package com.movieticket.repository;

import com.movieticket.entity.Screen;
import com.movieticket.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Long> {
    List<Screen> findByTheater(Theater theater);
    Optional<Screen> findByTheaterAndScreenName(Theater theater, String screenName);
}
