package com.auction.dao.extensions.excluded;

import com.auction.dao.HibernateDAO;
import com.auction.entity.extensions.Comment;

/*
* by DA
* 11/22/17
* */

public class CommentDAO extends HibernateDAO<Comment, Integer> {

    public CommentDAO() {
        super();
    }

    public void create(Comment comment) {
        super.saveOrUpdate(comment);
    }

    @Override
    public void delete(Comment comment) {
        super.delete(comment);
    }

    public Comment findById(Integer id) {
        return super.findById(Comment.class, id);
    }
    public void update(Comment comment) {
        super.saveOrUpdate(comment);
    }

    public List findAll() {
        return super.findAll(Comment.class);
    }
}
