package com.chrisking.golfclub.repository;

import com.chrisking.golfclub.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository interface for Tournament entity.
 * Provides database access operations and custom query methods for tournaments.
 * Extends JpaRepository to inherit standard CRUD operations.
 */
public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    /**
     * Finds all tournaments that start on the specified date.
     * @param startDate the start date to filter by
     * @return list of tournaments starting on the specified date
     */
    List<Tournament> findByStartDate(LocalDate startDate);

    /**
     * Finds all tournaments whose location contains the specified string (case-insensitive).
     * @param location the location or partial location to search for
     * @return list of tournaments matching the location criteria
     */
    List<Tournament> findByLocationContainingIgnoreCase(String location);
}
