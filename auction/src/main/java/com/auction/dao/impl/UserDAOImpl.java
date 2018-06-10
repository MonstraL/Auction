package com.auction.dao.impl;

import com.auction.dao.UserDAO;
import com.auction.entity.User;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by Petro Karabyn
 * on 24-Nov-17
 */

@Repository("userDAO")
public class UserDAOImpl extends HibernateDAO<User, Integer> implements UserDAO {

    @Override
    public User findByIdInitialized(int id) {
        User user = currentSession().get(User.class, id);
        Hibernate.initialize(user.getLotsForSale());
        Hibernate.initialize(user.getBids());
        Hibernate.initialize(user.getAuctions());
        return user;
    }

    @Override
    public List<User> findAllInitialized() {
        Query query = currentSession().createQuery("from " + User.class.getName());
        List<User> users = query.getResultList();
        for(User user: users) {
            Hibernate.initialize(user.getLotsForSale());
            Hibernate.initialize(user.getBids());
            Hibernate.initialize(user.getAuctions());
        }
        return users;
    }

    @Override
    public User findByEmailInitialized(String email){
        Query query = currentSession().createQuery("from " + User.class.getName() + " where email =  :emailParam");
        query.setParameter("emailParam", email);
        @SuppressWarnings("unchecked")
        User user = (User) query.getSingleResult();
        Hibernate.initialize(user.getLotsForSale());
        Hibernate.initialize(user.getBids());
        Hibernate.initialize(user.getAuctions());
        return user;
    }
}
