package com.auction.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import javax.persistence.*;

/**
 * Created by
 * Stepan, Petro
 * on 24-Nov-17
 */

@Entity
@Table(name="lots")
public class Lot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="lot_name")
    private String name;

    @ManyToOne
    @JoinColumn(name="seller_id")
    @JsonIgnoreProperties(value = {"lotsForSale", "bids", "auctions"})
    // When the User entry that has lots for sale and tries to get deleted,
    // the deletion is prevented.
    private User seller;

    @ManyToOne
    @JoinColumn(name="auction_id")
    @JsonIgnoreProperties(value = {"lots", "participants"})
    // When the Auction is deleted, lot entry in db gets updated
    // and auction gets set to null for the corresponding lot.
    private Auction auction;

    @OneToMany(mappedBy = "lot")
    @JsonIgnoreProperties(value = {"bidder", "lot"})
    private List<Bid> bids;

    @Column(name="description")
    private String description;

    @Column(name="price")
    private int price;

    @Column(name="lot_location")
    private  String location;

    public Lot() {
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

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Lot{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", location='" + location + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }
}