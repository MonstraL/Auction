package com.auction.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by
 * Petro Karabyn,
 * Stepan Makara
 * on 25-Nov-17
 * This class represents an Auction.
 *
 * According to the project specification:
 * Solution should allow at least two different trading schemes to choose.
 * EachAuction have a possibility to schedule a starting time and should follow trading scheme requirements for time.
 * Auction can be in next states: Planned, Open, Closed. Transition between states: Planned --> Open --> Closed.
 * When in Closed state, Auction cannot be transited into any other state.Each Auction can have list of Lots defined.
 * Solution should support next Client roles: ADMIN, TRADER, BUYER.
 *
 * This is not a final implementation yet. Functionality will be added.
 */

@Entity
@Table(name="auctions")
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="status")
    private  AuctionStatus status = AuctionStatus.PLANNED; //demo

    @OneToMany(mappedBy = "auction")
    @JsonIgnoreProperties(value = {"seller", "auction", "bids"})
    private List<Lot> lots;

    //TODO: Fix the hardcoded time value

    @Column(name="start_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Timestamp startDate = new Timestamp(System.currentTimeMillis()+(1000 * 60 * 60 * 1));//Default start_date: current + 1 hours

    @Column(name="end_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Timestamp endDate = new Timestamp(System.currentTimeMillis()+(1000 * 60 * 60 * 3)); //Default end_date: current + 3 hours

    @ManyToMany
    @JoinTable(
            name="auctions_users",
            joinColumns = @JoinColumn(name="auction_id"),
            inverseJoinColumns = @JoinColumn(name="user_id")
    )
    @JsonIgnoreProperties(value = {"lotsForSale", "bids", "auctions"})
    private List<User> participants;

    public Auction() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Lot> getLots() {
        return lots;
    }

    public void setLots(List<Lot> lots) {
        this.lots = lots;
    }

    public AuctionStatus getStatus() {
        return status;
    }

    public void setStatus(AuctionStatus status) {
        this.status = status;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Auction{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Auction)) return false;

        Auction auction = (Auction) o;

        if (id != auction.id) return false;
        if (status != auction.status) return false;
        if (startDate != null ? !startDate.equals(auction.startDate) : auction.startDate != null) return false;
        return endDate != null ? endDate.equals(auction.endDate) : auction.endDate == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + status.hashCode();
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }
}

