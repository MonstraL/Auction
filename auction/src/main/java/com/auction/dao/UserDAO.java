package com.auction.dao;

import com.auction.entity.User;

import java.util.List;

/**
 * Created by Petro Karabyn
 * on 04-Dec-17
 */

public interface UserDAO extends GenericDAO<User, Integer> {

    User findByIdInitialized(int id);

    List<User> findAllInitialized();

    User findByEmailInitialized(String email);

}
