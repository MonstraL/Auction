package com.auction.dao;

import com.auction.entity.Bid;
import com.auction.entity.Lot;
import com.auction.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Petro Karabyn
 * on 05-Dec-17
 */

@ContextConfiguration(locations = "/spring/root-context.xml")
public class BidDAOTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private BidDAO bidDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private LotDAO lotDAO;

    @Test
    public void testCreate() {
        int size = bidDAO.findAll().size();
        User user = prepopulateWithUser();
        Lot lot = prepopulateWithLot();
        Bid bid = new Bid(user, lot, 1);
        bidDAO.create(bid);
        assertTrue (size < bidDAO.findAll().size());
    }

    @Test
    public void testUpdate() {
        User user = prepopulateWithUser();
        Lot lot = prepopulateWithLot();
        Bid bid = new Bid(user, lot, 1);
        bidDAO.create(bid);
        bid.setValue(2);
        bidDAO.update(bid);
        Bid found = bidDAO.findById(bid.getId());
        assertEquals(2, found.getValue());
    }

    @Test
    public void testFindById() {
        User user = prepopulateWithUser();
        Lot lot = prepopulateWithLot();
        Bid bid = new Bid(user, lot, 1);
        bidDAO.create(bid);
        Bid found = bidDAO.findById(bid.getId());
        assertEquals(found, bid);
    }

    @Test
    public void testFindAll() {
        int size = bidDAO.findAll().size();
        User user = prepopulateWithUser();
        Lot lot = prepopulateWithLot();
        Bid bid = new Bid(user, lot, 1);
        bidDAO.create(bid);
        List<Bid> bids = bidDAO.findAll();
        assertEquals(size + 1, bids.size());
        assertTrue(bids.contains(bid));
    }



    @Test
    public void testDelete() {
        User user = prepopulateWithUser();
        Lot lot = prepopulateWithLot();
        Bid bid = new Bid(user, lot, 1);
        bidDAO.create(bid);
        // successfully added
        assertEquals(bid, bidDAO.findById(bid.getId()));
        // try to delete
        bidDAO.delete(bid);
        assertNull(bidDAO.findById(bid.getId()));
    }

    private User prepopulateWithUser() {
        User user = new User("bidder@gmail.com", "testBidder");
        userDAO.create(user);
        return userDAO.findById(user.getId());
    }

    private Lot prepopulateWithLot() {
        Lot lot = new Lot();
        lot.setName("lotTestBidding");
        lotDAO.create(lot);
        return lotDAO.findById(lot.getId());
    }

}
