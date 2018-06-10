package com.auction.dao.impl;

import com.auction.dao.LotDAO;
import com.auction.entity.Lot;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by
 * Petro Karabyn,
 * Stepan Makara
 * on 05-Dec-17
 */

@Repository("lotDAO")
public class LotDAOImpl extends HibernateDAO<Lot, Integer> implements LotDAO {

    @Override
    public Lot findByIdInitialized(int id) {
        Lot lot = currentSession().get(Lot.class, id);
        // Initialize only Lazy loaded fields.
        // Eager loaded ones are already initialized by default.
        Hibernate.initialize(lot.getBids());
        Hibernate.initialize(lot.getSeller());
        return lot;
    }

    @Override
    public List<Lot> findBySellerIdInitialized(int sellerId) {
        Query query = currentSession().createQuery("from " + Lot.class.getName()+ " where seller_id = " + sellerId);
        List<Lot> lots = query.getResultList();
        for(Lot lot: lots) {
            Hibernate.initialize(lot.getBids());
        }
        return lots;
    }

    @Override
    public List<Lot> findAllInitialized() {
        Query query = currentSession().createQuery("from " + Lot.class.getName());
        List<Lot> lots = query.getResultList();
        for(Lot lot: lots) {
            Hibernate.initialize(lot.getBids());
        }
        return lots;
    }
}