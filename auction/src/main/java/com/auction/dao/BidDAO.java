package com.auction.dao;

import com.auction.entity.Bid;

import java.util.List;

/**
 * Created by
 * Petro Karabyn,
 * Stepan Makara
 * on 05-Dec-17
 */

public interface BidDAO extends GenericDAO<Bid, Integer> {

    List<Bid> findByLot(String lotId);

}
