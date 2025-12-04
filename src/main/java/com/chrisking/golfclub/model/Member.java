package com.chrisking.golfclub.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity class representing a golf club member.
 * Persisted to the "members" database table and maintains a many-to-many relationship with tournaments.
 */
@Entity
@Table(name = "members")
public class Member {

    // Primary key - auto-generated ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Member's full name (required field)
    @Column(name = "member_name", nullable = false)
    private String memberName;

    // Member's street address
    @Column(name = "address")
    private String address;

    // Member's email address (required and unique)
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    // Member's phone number
    @Column(name = "phone_number")
    private String phoneNumber;

    // Date when the membership began
    @Column(name = "membership_start_date")
    private LocalDate membershipStartDate;

    // Duration of membership in months
    @Column(name = "membership_duration_months")
    private Integer membershipDurationMonths;

    // Type of membership (e.g., standard, premium, etc.)
    @Column(name = "membership_type")
    private String membershipType;

    // Many-to-many relationship: a member can participate in multiple tournaments
    @ManyToMany(mappedBy = "members")
    private Set<Tournament> tournaments = new HashSet<>();

    // No-argument constructor for JPA
    public Member() {
    }

    // Constructor with all required fields
    public Member(String memberName,
                  String address,
                  String email,
                  String phoneNumber,
                  LocalDate membershipStartDate,
                  Integer membershipDurationMonths,
                  String membershipType) {
        this.memberName = memberName;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.membershipStartDate = membershipStartDate;
        this.membershipDurationMonths = membershipDurationMonths;
        this.membershipType = membershipType;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getMembershipStartDate() {
        return membershipStartDate;
    }

    public void setMembershipStartDate(LocalDate membershipStartDate) {
        this.membershipStartDate = membershipStartDate;
    }

    public Integer getMembershipDurationMonths() {
        return membershipDurationMonths;
    }

    public void setMembershipDurationMonths(Integer membershipDurationMonths) {
        this.membershipDurationMonths = membershipDurationMonths;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public Set<Tournament> getTournaments() {
        return tournaments;
    }

    public void setTournaments(Set<Tournament> tournaments) {
        this.tournaments = tournaments;
    }
}
