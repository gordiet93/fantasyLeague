package com.example.fantasyLeague.repository;

import com.example.fantasyLeague.model.Fixture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FixtureRepository extends JpaRepository<Fixture, Long> {
}
