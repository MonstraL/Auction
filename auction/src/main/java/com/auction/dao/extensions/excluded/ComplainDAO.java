package com.auction.dao.extensions.excluded;

import com.auction.dao.HibernateDAO;
import com.auction.entity.extensions.Complain;

public class ComplainDAO extends HibernateDAO<Complain, Integer> {


    public ComplainDAO() {
        super();
    }

    public void create(Complain complain) {
        super.saveOrUpdate(complain);
    }

    @Override
    public void delete(Complain complain) {
        super.delete(complain);
    }

    public Complain findById(Integer id) {
        return super.findById(Complain.class, id);
    }

    public void update(Complain complain) {
        super.saveOrUpdate(complain);
    }

    public List returnAll() {
        return super.findAll(Complain.class);
    }
}
