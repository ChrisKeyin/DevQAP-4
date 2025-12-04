package com.chrisking.golfclub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity class representing a golf tournament.
 * Persisted to the "tournaments" database table and maintains a many-to-many relationship with members.
 */
@Entity
@Table(name = "tournaments")
public class Tournament {

    // Primary key - auto-generated ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Tournament start date
    @Column(name = "start_date")
    private LocalDate startDate;

    // Tournament end date
    @Column(name = "end_date")
    private LocalDate endDate;

    // Tournament location/venue
    @Column(name = "location")
    private String location;

    // Entry fee for the tournament
    @Column(name = "entry_fee")
    private BigDecimal entryFee;

    // Total cash prize amount for the tournament
    @Column(name = "cash_prize_amount")
    private BigDecimal cashPrizeAmount;

    // Many-to-many relationship: a tournament can have multiple members
    // JsonIgnore prevents circular serialization when converting to JSON
    @ManyToMany
    @JoinTable(
            name = "tournament_members",
            joinColumns = @JoinColumn(name = "tournament_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id")
    )
    @JsonIgnore
    private Set<Member> members = new HashSet<>();

    // No-argument constructor for JPA
    public Tournament() {
    }

    // Constructor with all required fields
    public Tournament(LocalDate startDate,
                      LocalDate endDate,
                      String location,
                      BigDecimal entryFee,
                      BigDecimal cashPrizeAmount) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.entryFee = entryFee;
        this.cashPrizeAmount = cashPrizeAmount;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getEntryFee() {
        return entryFee;
    }

    public void setEntryFee(BigDecimal entryFee) {
        this.entryFee = entryFee;
    }

    public BigDecimal getCashPrizeAmount() {
        return cashPrizeAmount;
    }

    public void setCashPrizeAmount(BigDecimal cashPrizeAmount) {
        this.cashPrizeAmount = cashPrizeAmount;
    }

    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }
}
