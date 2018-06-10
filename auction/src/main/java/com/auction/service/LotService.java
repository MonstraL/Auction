package com.auction.service;

import com.auction.entity.Lot;
import com.auction.entity.User;

import java.util.List;

/**
 * Created by
 * Stepan, Petro
 * on 07-Dec-17
 */

public interface LotService {

    Lot findById(int id);

    Lot findByIdInitialized(int id);

    List<Lot> findAll();

    List<Lot> findAllInitialized();

    void addLotForSale(Lot lot, User user);

    void addLotForSale(Lot lot, int userId);

    void create(Lot lot);

    void update(Lot lot);

    void removeLotFromSale(Lot lot, User user);

    void removeLotFromSale(Lot lot, int userId);

    void delete(Lot lot);

    void removeListOfLots(List<Lot> lots);

    boolean LotInOpenAuction(List<Lot> lots);

}
