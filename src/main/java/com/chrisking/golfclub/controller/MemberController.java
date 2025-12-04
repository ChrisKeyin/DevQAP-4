package com.chrisking.golfclub.controller;

import com.chrisking.golfclub.model.Member;
import com.chrisking.golfclub.service.MemberService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

/**
 * REST controller for managing golf club members.
 * Provides endpoints for creating, retrieving, and searching members.
 */
@RestController
@RequestMapping("/api/members")
public class MemberController {

    // Service dependency for member operations
    private final MemberService memberService;

    // Constructor injection for MemberService
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * Creates a new member in the system.
     * @param member the member to create
     * @return the created member with assigned ID
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Member createMember(@RequestBody Member member) {
        return memberService.createMember(member);
    }

    /**
     * Retrieves all members from the system.
     * @return list of all members
     */
    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    /**
     * Retrieves a specific member by their ID.
     * @param id the member ID
     * @return the member with the specified ID
     * @throws ResponseStatusException if member not found
     */
    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable Long id) {
        return memberService.getMemberById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found"));
    }

    /**
     * Searches for members by their name.
     * @param name the member name to search for
     * @return list of members matching the name
     */
    @GetMapping("/search/by-name")
    public List<Member> searchByName(@RequestParam String name) {
        return memberService.searchByName(name);
    }

    /**
     * Searches for members by their membership type.
     * @param membershipType the membership type to filter by
     * @return list of members with the specified membership type
     */
    @GetMapping("/search/by-membership-type")
    public List<Member> searchByMembershipType(@RequestParam String membershipType) {
        return memberService.searchByMembershipType(membershipType);
    }

    /**
     * Searches for a member by their phone number.
     * @param phoneNumber the phone number to search for
     * @return the member with the specified phone number
     * @throws ResponseStatusException if member not found
     */
    @GetMapping("/search/by-phone")
    public Member searchByPhoneNumber(@RequestParam String phoneNumber) {
        return memberService.searchByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found for phone"));
    }

    /**
     * Searches for members participating in tournaments starting on a specific date.
     * @param startDate the tournament start date to filter by
     * @return list of members with tournaments starting on the specified date
     */
    @GetMapping("/search/by-tournament-start-date")
    public List<Member> searchByTournamentStartDate(
            @RequestParam("startDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate) {

        return memberService.searchByTournamentStartDate(startDate);
    }
}
