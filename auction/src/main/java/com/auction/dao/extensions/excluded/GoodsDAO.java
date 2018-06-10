package com.auction.dao.extensions.excluded;

import com.auction.dao.HibernateDAO;
import com.auction.entity.extensions.Goods;

public class GoodsDAO extends HibernateDAO<Goods, Integer> {

    public GoodsDAO() {
        super();
    }

    public void create(Goods goods) {
        super.saveOrUpdate(goods);
    }

    @Override
    public void delete(Goods goods) {
        super.delete(goods);
    }

    public Goods findById(Integer id) {
        return super.findById(Goods.class, id);
    }
    public void update(Goods goods) {
        super.saveOrUpdate(goods);
    }

    public List findAll() {
        return super.findAll(Goods.class);
    }
}
