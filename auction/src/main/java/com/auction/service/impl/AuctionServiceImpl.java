package com.auction.service.impl;

import com.auction.dao.AuctionDAO;
import com.auction.entity.Auction;
import com.auction.entity.Lot;
import com.auction.entity.User;
import com.auction.service.AuctionService;
import com.auction.service.LotService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Petro Karabyn
 * on 05-Dec-17
 */

@Service("AuctionServiceImpl")
@Transactional(propagation= Propagation.REQUIRED)
public class AuctionServiceImpl implements AuctionService {

    // dependencies
    private SessionFactory sessionFactory;
    private AuctionDAO auctionDAO;
    private LotService lotService;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Autowired
    public void setAuctionDAO(AuctionDAO auctionDAO) {
        this.auctionDAO = auctionDAO;
    }

    @Autowired
    public void setLotService(LotService lotService) {
        this.lotService = lotService;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Auction findById(int id) {
        return auctionDAO.findById(id);
    }

    @Override
    public Auction findByIdInitialized(int id) {
        return auctionDAO.findByIdInitialized(id);
    }

    @Override
    public List<Auction> findAll() {
        return auctionDAO.findAll();
    }

    @Override
    public List<Auction> findAllInitialized() {
        return auctionDAO.findAllInitialized();
    }

    @Override
    public void create(Auction auction) {
        auctionDAO.create(auction);
    }

    @Override
    public void update(Auction auction) {
        auctionDAO.update(auction);
    }

    @Override
    public void delete(Auction auction) {
        auctionDAO.delete(auction);
    }

    @Override
    public void addLotToAuction(Auction auction, Lot lot) {
        User seller = lot.getSeller();
        // lot must have a seller to be added to the auction
        if (seller == null) {
            return;
        }
        // make sure the lot exists. if not create the lot.
        if (seller.getLotsForSale() == null || !seller.getLotsForSale().contains(lot)) {
            lotService.addLotForSale(lot, seller);
        }
        lot.setAuction(auction);
        currentSession().update(lot);
    }

    @Override
    public void removeLotFromAuction(Auction auction, Lot lot) {
        if(auction == null || lot == null || lot.getAuction() == null || lot.getAuction().getId() != auction.getId()) {
            return;
        }
        lot.setAuction(null);
        currentSession().update(lot);
    }


}
