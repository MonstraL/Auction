package com.auction.dao;

import com.auction.entity.Auction;
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
public class AuctionDAOTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private AuctionDAO auctionDAO;

    @Test
    public void testCreate() {
        int size = auctionDAO.findAll().size();
        Auction auction = new Auction();
        auction.setName("test");
        auctionDAO.create(auction);
        assertTrue (size < auctionDAO.findAll().size());
    }

    @Test
    public void testUpdate() {
        Auction auction = new Auction();
        auction.setName("test");
        auctionDAO.create(auction);
        auction.setName("updated");
        auctionDAO.update(auction);
        Auction found = auctionDAO.findById(auction.getId());
        assertEquals("updated", found.getName());
    }

    @Test
    public void testFindById() {
        Auction auction = new Auction();
        auctionDAO.create(auction);
        Auction found = auctionDAO.findById(auction.getId());
        assertEquals(found, auction);
    }

    @Test
    public void testFindAll() {
        int size = auctionDAO.findAll().size();
        Auction auction = new Auction();
        auctionDAO.create(auction);
        List<Auction> auctions = auctionDAO.findAll();
        assertEquals(size + 1, auctions.size());
        assertTrue(auctions.contains(auction));
    }

    @Test
    public void testDelete() {
        Auction auction = new Auction();
        auctionDAO.create(auction);
        // successfully added
        assertEquals(auction, auctionDAO.findById(auction.getId()));
        // try to delete
        auctionDAO.delete(auction);
        assertNull(auctionDAO.findById(auction.getId()));
    }

    @Test
    public void testFindByIdInitialized() {
        Auction auction = new Auction();
        auction.setName("initialized");
        auctionDAO.create(auction);
        auction = auctionDAO.findByIdInitialized(auction.getId());
        List emptyList = new ArrayList();
        // try to retrieve the lazy loaded fields of user and check if they are initialized
        assertEquals(emptyList, auction.getLots());
        assertEquals(emptyList, auction.getParticipants());
    }

}
