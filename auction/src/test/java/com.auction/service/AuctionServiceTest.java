package com.auction.service;

import com.auction.entity.Auction;
import com.auction.entity.Lot;
import com.auction.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import static org.junit.Assert.*;

/**
 * Created by Petro Karabyn
 * on 14-Dec-17
 */

@ContextConfiguration(locations = "/spring/root-context.xml")
public class AuctionServiceTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private AuctionService auctionService;

    @Autowired
    private UserService userService;

    @Autowired
    private LotService lotService;

    @Test
    public void testAddLotToAuction() {
        // create auction
        Auction auction = prepopulateWithAuction();
        // create user
        User seller = prepopulateWithUser();
        // create a lot for sale
        Lot lot = prepopulateWithLot(seller);
        // add lot to auction
        auctionService.addLotToAuction(auction, lot);
        // retrieve lot from db
        lot = lotService.findById(lot.getId());
        // check if lot is assigned to an auction
        assertNotNull(lot.getAuction());
        // retrieve auction from db
        auction = auctionService.findByIdInitialized(auction.getId());
        // check that an auction has one lot
        assertTrue(auction.getLots().size() == 1);
        // check that a lot is assigned to the correct auction
        assertEquals(lot.getAuction().getId(), auction.getId());
    }

    @Test
    public void testRemoveLotFromAuction() {
        // create auction
        Auction auction = prepopulateWithAuction();
        // create user
        User seller = prepopulateWithUser();
        // create a lot for sale
        Lot lot = prepopulateWithLot(seller);
        // add lot to auction
        auctionService.addLotToAuction(auction, lot);
        auction = auctionService.findByIdInitialized(auction.getId());
        int size = auction.getLots().size();
        assertEquals(lot.getAuction().getId(), auction.getId());
        // remove lot from auction
        auctionService.removeLotFromAuction(auction, lot);
        auction = auctionService.findByIdInitialized(auction.getId());
        // check that a lot is not assigned to any auction
        assertNull(lot.getAuction());
        // check that a number of lots decreased by 1
        assertEquals(size - 1, auction.getLots().size());
    }

    private Auction prepopulateWithAuction() {
        Auction auction = new Auction();
        auction.setName("auctionWithLot");
        auctionService.create(auction);
        return auction;
    }

    private User prepopulateWithUser() {
        User seller = new User("lotseller@gmail.com", "testAddLotToAuction");
        userService.create(seller);
        return seller;
    }

    private Lot prepopulateWithLot(User seller) {
        Lot lot = new Lot();
        lot.setName("lotForAuction");
        lotService.addLotForSale(lot, seller);
        return lot;
    }

}
