package com.auction.entity.wrappers;

import com.auction.entity.Lot;
import com.auction.entity.User;

/**
 * Created by Petro Karabyn
 * on 15-Dec-17
 *
 * A controller method in Spring MVC cannot receive multiple @RequestBody params.
 * For bid placement with POST method we need to pass a user, a lot and a value of a bid.
 * The below code is not possible since there are multiple @RequestBody params.
 * placeBid(@RequestBody User user, @RequestBody Lot lot, @RequestBody int value)
 * The most reasonable solution to this is to have a wrapper class in order to have
 * one @RequestBody param that contains all the needed objects.
 * This is an implementation of such a wrapper for bid placement.
 *
 * The json will have the following structure:
 * { "user": {"id": 382}, "lot": {"id": 94}, "bidValue": 3, "timestamp": }
 */

public class BidContextWrapper {

    private User user;
    private Lot lot;
    private int bidValue;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Lot getLot() {
        return lot;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }

    public int getBidValue() {
        return bidValue;
    }

    public void setBidValue(int bidValue) {
        this.bidValue = bidValue;
    }
}
