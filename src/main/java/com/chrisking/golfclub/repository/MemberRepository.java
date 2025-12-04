package com.chrisking.golfclub.repository;

import com.chrisking.golfclub.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Member entity.
 * Provides database access operations and custom query methods for members.
 * Extends JpaRepository to inherit standard CRUD operations.
 */
public interface MemberRepository extends JpaRepository<Member, Long> {

    /**
     * Finds all members whose name contains the specified string (case-insensitive).
     * @param memberName the name or partial name to search for
     * @return list of members matching the name criteria
     */
    List<Member> findByMemberNameContainingIgnoreCase(String memberName);

    /**
     * Finds all members with the specified membership type (case-insensitive).
     * @param membershipType the membership type to search for
     * @return list of members with the specified membership type
     */
    List<Member> findByMembershipTypeIgnoreCase(String membershipType);

    /**
     * Finds a member by their phone number.
     * @param phoneNumber the phone number to search for
     * @return an Optional containing the member if found, empty otherwise
     */
    Optional<Member> findByPhoneNumber(String phoneNumber);

    /**
     * Finds all members who are participating in tournaments that start on a specified date.
     * @param startDate the tournament start date to filter by
     * @return list of members with tournaments starting on the specified date
     */
    List<Member> findByTournaments_StartDate(LocalDate startDate);
}
