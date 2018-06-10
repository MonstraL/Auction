package com.auction.service.impl;

import com.auction.dao.LotDAO;
import com.auction.dao.UserDAO;
import com.auction.entity.AuctionStatus;
import com.auction.entity.Lot;
import com.auction.entity.User;
import com.auction.service.BidService;
import com.auction.service.LotService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by
 * Petro Karabyn,
 * Stepan Makara
 * on 25-Nov-17
 */

@Service("LotServiceImpl")
@Transactional(propagation= Propagation.REQUIRED)
public class LotServiceImpl implements LotService {

    // dependencies
    private SessionFactory sessionFactory;
    private LotDAO lotDAO;
    private UserDAO userDAO;
    private BidService bidService;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Autowired
    public void setLotDAO(LotDAO lotDAO) {
        this.lotDAO = lotDAO;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setBidService(BidService bidService) {
        this.bidService = bidService;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Lot findById(int id) {
        return lotDAO.findById(id);
    }

    @Override
    public Lot findByIdInitialized(int id) {
        return lotDAO.findByIdInitialized(id);
    }

    @Override
    public List<Lot> findAll() {
        return lotDAO.findAll();
    }

    @Override
    public List<Lot> findAllInitialized() {
        return lotDAO.findAllInitialized();
    }

    @Override
    public void addLotForSale(Lot lot, User user) {
        List<Lot> lotsForSale = user.getLotsForSale();
        if (lotsForSale == null) {
            lotsForSale = new ArrayList<>();
        }
        lotsForSale.add(lot);
        lot.setSeller(user);
        currentSession().saveOrUpdate(lot);
    }

    @Override
    public void addLotForSale(Lot lot, int userId) {
        User user = userDAO.findByIdInitialized(userId);
        addLotForSale(lot, user);
    }

    @Override
    public void create(Lot lot) {
        lotDAO.create(lot);
    }

    @Override
    public void update(Lot lot) {
        lotDAO.update(lot);
    }

    @Override
    public void removeLotFromSale(Lot lot, User user) {
        if (lot.getBids() != null) {
            return;
        }
        user.getLotsForSale().remove(lot);
        lot.setSeller(null);
        lotDAO.update(lot);
    }

    @Override
    public void removeLotFromSale(Lot lot, int userId) {
        User user = userDAO.findByIdInitialized(userId);
        removeLotFromSale(lot, user);
    }

    @Override
    public void delete(Lot lot) {
        lotDAO.delete(lot);
    }

    @Override
    public void removeListOfLots(List<Lot> lots) {
        Iterator<Lot> iterator = lots.iterator();
        Lot lot;
        while (iterator.hasNext()) {
            lot = iterator.next();
            bidService.removeListOfBids(lot.getBids());
            lotDAO.delete(lot);
        }
    }

    @Override
    public boolean LotInOpenAuction(List<Lot> lots){
        Iterator<Lot> iterator = lots.iterator();
        while(iterator.hasNext())
            if(iterator.next().getAuction().getStatus()== AuctionStatus.OPEN)
                return true;
        return false;
    }




}
