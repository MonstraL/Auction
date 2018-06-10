package com.auction.service.extenstions.excluded;

import com.auction.dao.extensions.excluded.GoodsDAO;
import com.auction.dao.impl.UserDAOImpl;
import com.auction.entity.extensions.Goods;

import java.sql.Timestamp;
import java.util.List;

public class GoodsService {

    private GoodsDAO goodsDAO;
    private UserDAOImpl userDAOImpl;

    public boolean create(String currentUser, String title, String content, int price, Timestamp deadline, String picture){
        if (currentUser != null && !title.isEmpty() && !content.isEmpty() && !picture.isEmpty()){
            Goods goods = new Goods();
            goods.setTitle(title);
            goods.setContent(content);
            goods.setPrice(price);
            goods.setDeadline(deadline);
            goods.setPicture(picture);
            goods.setStatus("check");
            try {
               // TODO: setUserId
                // TODO: saveGoods
            }catch (Exception e){
                return false;
            }
        }
        return false;
    }

    public List<Goods> getAll(){
        return goodsDAO.findAll();
    }

    public boolean updateStatus(int id, String status){
        try {
            Goods goods = goodsDAO.findById(id);
            goods.setStatus(status);
            goodsDAO.update(goods);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean delete(int id){
        try {
            goodsDAO.delete(goodsDAO.findById(id));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    //TODO: add keyword search
}
