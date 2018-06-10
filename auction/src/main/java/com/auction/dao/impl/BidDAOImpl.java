package com.auction.dao.impl;

import com.auction.dao.BidDAO;
import com.auction.entity.Bid;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by
 * Petro Karabyn,
 * Stepan Makara
 * on 05-Dec-17
 */

@Repository("bidDAO")
public class BidDAOImpl extends HibernateDAO<Bid, Integer> implements BidDAO {

    public List<Bid> findByLot(String lotId)
    {
        Query query = currentSession().createQuery("from " + Bid.class.getName() + " where lot_id = " + lotId);
        List<Bid> bids = query.getResultList();
        return bids;
    }

}