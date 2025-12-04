package com.chrisking.golfclub.controller;

import com.chrisking.golfclub.model.Member;
import com.chrisking.golfclub.model.Tournament;
import com.chrisking.golfclub.service.TournamentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

/**
 * REST controller for managing golf club tournaments.
 * Provides endpoints for creating, retrieving, searching tournaments, and managing tournament membership.
 */
@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    // Service dependency for tournament operations
    private final TournamentService tournamentService;

    // Constructor injection for TournamentService
    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    /**
     * Creates a new tournament in the system.
     * @param tournament the tournament to create
     * @return the created tournament with assigned ID
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tournament createTournament(@RequestBody Tournament tournament) {
        return tournamentService.createTournament(tournament);
    }

    /**
     * Retrieves all tournaments from the system.
     * @return list of all tournaments
     */
    @GetMapping
    public List<Tournament> getAllTournaments() {
        return tournamentService.getAllTournaments();
    }

    /**
     * Retrieves a specific tournament by its ID.
     * @param id the tournament ID
     * @return the tournament with the specified ID
     * @throws ResponseStatusException if tournament not found
     */
    @GetMapping("/{id}")
    public Tournament getTournamentById(@PathVariable Long id) {
        try {
            return tournamentService.getTournamentById(id);
        } catch (RuntimeException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    /**
     * Searches for tournaments by their start date.
     * @param startDate the start date to filter by
     * @return list of tournaments starting on the specified date
     */
    @GetMapping("/search/by-start-date")
    public List<Tournament> searchByStartDate(
            @RequestParam("startDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate) {

        return tournamentService.searchByStartDate(startDate);
    }

    /**
     * Searches for tournaments by their location.
     * @param location the location to filter by
     * @return list of tournaments at the specified location
     */
    @GetMapping("/search/by-location")
    public List<Tournament> searchByLocation(@RequestParam String location) {
        return tournamentService.searchByLocation(location);
    }

    /**
     * Adds a member to a tournament.
     * @param tournamentId the ID of the tournament
     * @param memberId the ID of the member to add
     * @return the updated tournament
     * @throws ResponseStatusException if tournament or member not found
     */
    @PostMapping("/{tournamentId}/members/{memberId}")
    public Tournament addMemberToTournament(@PathVariable Long tournamentId,
                                            @PathVariable Long memberId) {
        try {
            return tournamentService.addMemberToTournament(tournamentId, memberId);
        } catch (RuntimeException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    /**
     * Retrieves all members participating in a specific tournament.
     * @param tournamentId the ID of the tournament
     * @return list of members in the tournament
     */
    @GetMapping("/{tournamentId}/members")
    public List<Member> getMembersInTournament(@PathVariable Long tournamentId) {
        return tournamentService.getMembersInTournament(tournamentId);
    }
}
