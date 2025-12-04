package com.chrisking.golfclub.service;

import com.chrisking.golfclub.model.Member;
import com.chrisking.golfclub.model.Tournament;
import com.chrisking.golfclub.repository.MemberRepository;
import com.chrisking.golfclub.repository.TournamentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * Service class for tournament-related business logic.
 * Handles operations such as creating, retrieving, searching tournaments, and managing tournament membership.
 * Delegates database operations to TournamentRepository and MemberRepository.
 */
@Service
public class TournamentService {

    // Repository dependency for tournament data access
    private final TournamentRepository tournamentRepository;
    
    // Repository dependency for member data access
    private final MemberRepository memberRepository;

    // Constructor injection for both repositories
    public TournamentService(TournamentRepository tournamentRepository,
                             MemberRepository memberRepository) {
        this.tournamentRepository = tournamentRepository;
        this.memberRepository = memberRepository;
    }

    /**
     * Creates and saves a new tournament to the database.
     * @param tournament the tournament to create
     * @return the created tournament with assigned ID
     */
    public Tournament createTournament(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    /**
     * Retrieves all tournaments from the database.
     * @return list of all tournaments
     */
    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    /**
     * Retrieves a tournament by its ID.
     * @param id the tournament ID
     * @return the tournament with the specified ID
     * @throws RuntimeException if tournament not found
     */
    public Tournament getTournamentById(Long id) {
        return tournamentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tournament not found with id: " + id));
    }

    /**
     * Searches for tournaments by their start date.
     * @param startDate the start date to filter by
     * @return list of tournaments starting on the specified date
     */
    public List<Tournament> searchByStartDate(LocalDate startDate) {
        return tournamentRepository.findByStartDate(startDate);
    }

    /**
     * Searches for tournaments by their location (case-insensitive, partial match).
     * @param location the location or partial location to search for
     * @return list of tournaments matching the location criteria
     */
    public List<Tournament> searchByLocation(String location) {
        return tournamentRepository.findByLocationContainingIgnoreCase(location);
    }

    /**
     * Adds a member to a tournament. Maintains bidirectional relationship.
     * Transaction ensures atomicity of the operation.
     * @param tournamentId the ID of the tournament
     * @param memberId the ID of the member to add
     * @return the updated tournament
     * @throws RuntimeException if tournament or member not found
     */
    @Transactional
    public Tournament addMemberToTournament(Long tournamentId, Long memberId) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new RuntimeException("Tournament not found with id: " + tournamentId));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + memberId));

        tournament.getMembers().add(member);
        member.getTournaments().add(tournament);

        return tournamentRepository.save(tournament);
    }

    /**
     * Retrieves all members participating in a specific tournament.
     * @param tournamentId the ID of the tournament
     * @return list of members in the tournament (immutable copy)
     */
    public List<Member> getMembersInTournament(Long tournamentId) {
        Tournament tournament = getTournamentById(tournamentId);
        return List.copyOf(tournament.getMembers());
    }
}
