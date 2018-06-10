package com.auction.dao;

import com.auction.entity.Lot;

import java.util.List;

/**
 * Created by
 * Petro Karabyn,
 * Stepan Makara
 * on 05-Dec-17
 */

public interface LotDAO extends GenericDAO<Lot, Integer> {

    Lot findByIdInitialized(int id);

    List<Lot> findBySellerIdInitialized(int sellerId);

    List<Lot> findAllInitialized();
}
