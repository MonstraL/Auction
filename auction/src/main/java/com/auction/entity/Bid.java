package com.auction.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Timestamp;

/**
 * Created by
 * Stepan, Petro
 * on 24-Nov-17
 */

@Entity
@Table(name="bids")
public class Bid implements Comparable<Bid> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "bidder_id")
    @JsonIgnoreProperties(value = {"lotsForSale", "bids", "auctions"})
    private User bidder;

    @ManyToOne
    @JoinColumn(name = "lot_id")
    @JsonIgnoreProperties(value = {"seller", "auction", "bids"})
    private Lot lot;

    @Column(name = "value")
    private int value;

    @Column(name = "timestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    public Bid() {
    }

    public Bid(User bidder, Lot lot, int value) {
        this.bidder = bidder;
        this.lot = lot;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getBidder() {
        return bidder;
    }

    public void setBidder(User bidder) {
        this.bidder = bidder;
    }

    public Lot getLot() {
        return lot;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "id=" + id +
                ", bidder=" + bidder +
                ", lot=" + lot +
                ", value=" + value +
                ", timestamp=" + timestamp +
                '}';
    }

    @Override
    public int compareTo(Bid o) {
        return Integer.compare(this.getValue(), o.getValue());
    }
}