package com.auction.service;

import com.auction.entity.Auction;
import com.auction.entity.Lot;
import com.auction.entity.User;

import java.util.List;

/**
 * Created by
 * Petro Karabyn,
 * Stepan Makara
 * on 05-Dec-17
 */

public interface UserService {

    User findById(int id);

    User findByIdInitialized(int id);

    List<User> findAll();

    List<User> findAllInitialized();

    void create(User user);

    void update(User user);

    void unRegister(User user);

    User logIn(String email, String password);

    void delete(User user);

    void registerForAnAuction(User user, Auction auction);

    void unregisterFromAnAuction(User user, Auction auction);

    List<Auction> getPlannedOpen();

}
