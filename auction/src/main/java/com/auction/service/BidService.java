package com.auction.service;

import com.auction.entity.Bid;
import com.auction.entity.Lot;
import com.auction.entity.User;

import java.util.List;

/**
 * Created by
 * Stepan, Petro
 * on 07-Dec-17
 *
 * Bid should be placed on a lot and always contain information about
 * which lot it was placed on, the bidder, the value of a bid and
 * a timestamp.
 */

public interface BidService {

    Bid findById(int id);

    void placeBid(User user, Lot lot, int bidValue);

    void placeBid(int user_id, int lot_id, int bidValue);

    void removeListOfBids(List<Bid> bids);

}
