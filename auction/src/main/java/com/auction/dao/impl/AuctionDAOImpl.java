package com.auction.dao.impl;

import com.auction.dao.AuctionDAO;
import com.auction.entity.Auction;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by Petro Karabyn
 * on 05-Dec-17
 */

@Repository("auctionDAO")
public class AuctionDAOImpl extends HibernateDAO<Auction, Integer> implements AuctionDAO {


    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Auction findByIdInitialized(int id) {
        Auction auction = currentSession().get(Auction.class, id);
        Hibernate.initialize(auction.getParticipants());
        Hibernate.initialize(auction.getLots());
        return auction;
    }

    @Override
    public List<Auction> findAllInitialized() {
        Query query = currentSession().createQuery("from " + Auction.class.getName());
        List<Auction>  auctions = query.getResultList();
        for (Auction auction : auctions) {
            Hibernate.initialize(auction.getParticipants());
            Hibernate.initialize(auction.getLots());
        }
        return auctions;
    }

    @Override
    public List<Auction> fintNotInOpen(){
        Query query = currentSession().createQuery("from " + Auction.class.getName()+ " where (start_date <= now() or end_date <= now()) and status <> 2");
        List<Auction> auctions = query.getResultList();
        for(Auction auction: auctions) {
            Hibernate.initialize(auction.getParticipants());
        }
        return auctions;
    }
}
