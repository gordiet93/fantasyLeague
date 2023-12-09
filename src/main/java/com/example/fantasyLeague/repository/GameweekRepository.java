package com.example.fantasyLeague.repository;

import com.example.fantasyLeague.model.Gameweek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface GameweekRepository extends JpaRepository<Gameweek, Long> {
    List<Gameweek> findAll();

    Gameweek findByStartDateEquals(Date date);

    Gameweek findFirstByStartDateAfterOrderByStartDateAsc(Date date);

    Gameweek findFirstByEndDateAfterOrderByEndDateAsc(Date date);
}
