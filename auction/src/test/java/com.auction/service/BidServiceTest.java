package com.auction.service;

import com.auction.entity.Lot;
import com.auction.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Petro Karabyn
 * on 12-Dec-17
 */

@ContextConfiguration(locations = "/spring/root-context.xml")
public class BidServiceTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private BidService bidService;

    @Autowired
    private UserService userService;

    @Autowired
    private LotService lotService;

    @Test
    public void testPlaceBid() {
        // create bidder
        User bidder = new User("bidder@gmail.com", "testBidder");
        userService.create(bidder);
        // create seller
        User seller = new User("seller@gmail.com", "testSeller");
        userService.create(seller);
        // Create lot for sale
        Lot lot = new Lot();
        lot.setName("lotBidTest");
        lotService.addLotForSale(lot, seller);
        // place a first bid on a lot
        bidService.placeBid(bidder, lot, 10);
        lot = lotService.findByIdInitialized(lot.getId());
        assertTrue(lot.getBids().size() == 1);
        // try to place a bid lower than a current max bid
        bidService.placeBid(bidder, lot, 5);
        lot = lotService.findByIdInitialized(lot.getId());
        // make sure that the bid wasn't placed
        assertTrue(lot.getBids().size() == 1);
        // place a bid higher than a current max bid
        bidService.placeBid(bidder, lot, 20);
        lot = lotService.findByIdInitialized(lot.getId());
        // check if the bid was placed
        assertTrue(lot.getBids().size() == 2);
        // check if the current max bid is correct
        assertEquals(20, Collections.max(lot.getBids()).getValue());
        // place one more bid higher than a current one
        bidService.placeBid(bidder, lot, 50);
        lot = lotService.findByIdInitialized(lot.getId());
        assertTrue(lot.getBids().size() == 3);
        assertEquals(50, Collections.max(lot.getBids()).getValue());
    }
}
