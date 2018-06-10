package com.auction.service.impl;

import com.auction.dao.AuctionDAO;
import com.auction.dao.BidDAO;
import com.auction.dao.LotDAO;
import com.auction.dao.UserDAO;
import com.auction.entity.*;
import com.auction.service.BidService;
import com.auction.service.LotService;
import com.auction.service.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 * Petro Karabyn,
 * Stepan Makara
 * on 24-Nov-17
 */

@Service("UserServiceImpl")
@Transactional(propagation= Propagation.REQUIRED)
public class UserServiceImpl implements UserService {

    // dependencies
    private SessionFactory sessionFactory;

    private UserDAO userDAO;
    private LotDAO lotDAO;
    private BidDAO bidDAO;
    private AuctionDAO auctionDAO;

    private BidService bidService;
    private LotService lotService;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setBidDAO(BidDAO bidDAO) {
        this.bidDAO = bidDAO;
    }

    @Autowired
    public void setAuctionDAO(AuctionDAO auctionDAO) {
        this.auctionDAO = auctionDAO;
    }

    @Autowired
    public void setLotDAO(LotDAO lotDAO) {
        this.lotDAO = lotDAO;
    }

    @Autowired
    public void setBidService(BidService bidService) {
        this.bidService = bidService;
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
    public User findById(int id) {
        return userDAO.findById(id);
    }

    @Override
    public User findByIdInitialized(int id) {
        return userDAO.findByIdInitialized(id);
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public List<User> findAllInitialized() {
        return userDAO.findAllInitialized();
    }

    public void create(User user) {
        userDAO.create(user);
    }

    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    public void unRegister(User user) {
        List<Lot> lots = lotDAO.findBySellerIdInitialized(user.getId());
        if(!lotService.LotInOpenAuction(lots)){
            lotService.removeListOfLots(lots);
            bidService.removeListOfBids(user.getBids());
        }

    }

    @Override
    public User logIn(String email, String password) {
        User user = userDAO.findByEmailInitialized(email);
        if(user.getPassword().equals(password))
            return user;
        else return null;
    }

    public void delete(User user) {
        userDAO.delete(user);
    }

    @Override
    public void registerForAnAuction(User user, Auction auction) {
        List<Auction> auctions = user.getAuctions();
        if (auctions == null) {
            auctions = new ArrayList<>();
        }
        auctions.add(auction);
        userDAO.update(user);
    }

    @Override
    public void unregisterFromAnAuction(User user, Auction auction) {
        List<Auction> auctions = user.getAuctions();
        auctions.remove(auction);
        userDAO.update(user);
    }

    @Override
    public List<Auction> getPlannedOpen() {
        return auctionDAO.fintNotInOpen();
    }

}
