package com.auction.service;

import com.auction.entity.Auction;
import com.auction.entity.Lot;

import java.util.List;

/**
 * Created by Petro Karabyn
 * on 05-Dec-17
 */
public interface AuctionService {

    Auction findById(int id);

    Auction findByIdInitialized(int id);

    List<Auction> findAll();

    List<Auction> findAllInitialized();

    void create(Auction auction);

    void update(Auction auction);

    void addLotToAuction(Auction auction, Lot lot);

    void removeLotFromAuction(Auction auction, Lot lot);

    void delete(Auction auction);
}