package com.chrisking.golfclub.service;

import com.chrisking.golfclub.model.Member;
import com.chrisking.golfclub.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Service class for member-related business logic.
 * Handles operations such as creating, retrieving, and searching members.
 * Delegates database operations to MemberRepository.
 */
@Service
public class MemberService {

    // Repository dependency for member data access
    private final MemberRepository memberRepository;

    // Constructor injection for MemberRepository
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * Creates and saves a new member to the database.
     * @param member the member to create
     * @return the created member with assigned ID
     */
    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    /**
     * Retrieves all members from the database.
     * @return list of all members
     */
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    /**
     * Retrieves a member by their ID.
     * @param id the member ID
     * @return an Optional containing the member if found, empty otherwise
     */
    public Optional<Member> getMemberById(Long id) {
        return memberRepository.findById(id);
    }

    /**
     * Searches for members by name (case-insensitive, partial match).
     * @param name the name or partial name to search for
     * @return list of members matching the name criteria
     */
    public List<Member> searchByName(String name) {
        return memberRepository.findByMemberNameContainingIgnoreCase(name);
    }

    /**
     * Searches for members by their membership type (case-insensitive).
     * @param membershipType the membership type to search for
     * @return list of members with the specified membership type
     */
    public List<Member> searchByMembershipType(String membershipType) {
        return memberRepository.findByMembershipTypeIgnoreCase(membershipType);
    }

    /**
     * Searches for a member by their phone number.
     * @param phoneNumber the phone number to search for
     * @return an Optional containing the member if found, empty otherwise
     */
    public Optional<Member> searchByPhoneNumber(String phoneNumber) {
        return memberRepository.findByPhoneNumber(phoneNumber);
    }

    /**
     * Searches for members participating in tournaments that start on a specified date.
     * @param startDate the tournament start date to filter by
     * @return list of members with tournaments starting on the specified date
     */
    public List<Member> searchByTournamentStartDate(LocalDate startDate) {
        return memberRepository.findByTournaments_StartDate(startDate);
    }
}
