package com.auction.dao.impl;

import com.auction.dao.GenericDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by Petro Karabyn
 * on 24-Nov-17
 */

@Transactional(propagation = Propagation.REQUIRED)
public class HibernateDAO<Item, Id extends Serializable> implements GenericDAO<Item, Id> {

    @Autowired
    private SessionFactory sessionFactory;
    protected Class<? extends Item> daoType;


    public HibernateDAO() {
        daoType = (Class<Item>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void create(Item entity) {
        currentSession().save(entity);
    }

    @Override
    public void update(Item entity) {
        currentSession().update(entity);
    }

    @Override
    public void delete(Item entity) {
        currentSession().delete(entity);
    }

    @Override
    public Item findById(Id id) {
        return (Item) currentSession().get(daoType, id);
    }

    @Override
    public List<Item> findAll() {
        return currentSession().createQuery("from " + daoType.getName()).getResultList();
    }

    public void deleteAll(List<Item> entities) {
        Session session = currentSession();
        for (Item entity : entities) {
            session.delete(entity);
        }
    }
}