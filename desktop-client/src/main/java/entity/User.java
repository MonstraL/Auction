package entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.List;

public class User {
    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Timestamp creationTimestamp = new Timestamp(System.currentTimeMillis());
    public String getFirstName() {
        return firstName;
    }

    public List<Lot> getLotsForSale() {
        return lotsForSale;
    }

    public void setLotsForSale(List<Lot> lotsForSale) {
        this.lotsForSale = lotsForSale;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    private List<Lot> lotsForSale;

    private List<Bid> bids;

    public List<Auction> getAuctions() {
        return auctions;
    }

    public void setAuctions(List<Auction> auctions) {
        this.auctions = auctions;
    }

    private List<Auction> auctions;

    public int getId() { return id; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(Timestamp creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }
}
