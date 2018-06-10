package com.auction.service.impl;

import com.auction.dao.BidDAO;
import com.auction.dao.LotDAO;
import com.auction.dao.UserDAO;
import com.auction.entity.Bid;
import com.auction.entity.Lot;
import com.auction.entity.User;
import com.auction.service.BidService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by
 * Petro Karabyn
 */

@Service("BidServiceImpl")
@Transactional(propagation= Propagation.REQUIRED)
public class BidServiceImpl implements BidService {

    // dependencies
    private SessionFactory sessionFactory;
    private BidDAO bidDAO;
    private UserDAO userDAO;
    private LotDAO lotDAO;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Autowired
    public void setBidDAO(BidDAO bidDAO) {
        this.bidDAO = bidDAO;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setLotDAO(LotDAO lotDAO) {
        this.lotDAO = lotDAO;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Bid findById(int id) {
        return bidDAO.findById(id);
    }

    @Override
    public void removeListOfBids(List<Bid> bids)
    {
        Iterator<Bid> iterator = bids.iterator();
        while(iterator.hasNext())
            bidDAO.delete(iterator.next());
    }

    @Override
    public void placeBid(User user, Lot lot, int bidValue) {
        List<Bid> lotBids = lot.getBids();
        // 1) There are bids on a given lot. New bid is placed only if it's larger than the current max bid.
        if (lotBids != null && !lotBids.isEmpty()) {
            if (bidValue > Collections.max(lotBids).getValue()) {
                createBid(user, lot, bidValue);
            }
        }
        // 2) This is a first bid on this lot, simply place it.
        else {
            createBid(user, lot, bidValue);
        }
    }

    @Override
    public void placeBid(int user_id, int lot_id, int bidValue) {
        User user = userDAO.findById(user_id);
        Lot lot = lotDAO.findById(lot_id);
        placeBid(user, lot, bidValue);
    }

    private void createBid(User user, Lot lot, int bidValue) {
        Bid bid = new Bid(user, lot, bidValue);
        bidDAO.create(bid);
    }

}
