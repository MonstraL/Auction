package com.auction.dao;

import com.auction.entity.Auction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Petro Karabyn
 * on 05-Dec-17
 */

public interface AuctionDAO extends GenericDAO<Auction, Integer> {

    Auction findByIdInitialized(int id);

    List<Auction> findAllInitialized();

    List<Auction> fintNotInOpen();

}
