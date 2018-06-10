package com.auction.dao.extensions.excluded;

import com.auction.dao.HibernateDAO;
import com.auction.entity.extensions.Notification;

import java.util.List;

public class NotificationDAO extends HibernateDAO<Notification, Integer> {

    public NotificationDAO() {
        super();
    }

    public void create(Notification notification){
        super.create(notification);
    }

    @Override
    public void delete(Notification notification) {
        super.delete(notification);
    }

    public Notification findById(Integer id) {
        return super.findById(id);
    }

    public List findAll() {
        return super.findAll();
    }
}
