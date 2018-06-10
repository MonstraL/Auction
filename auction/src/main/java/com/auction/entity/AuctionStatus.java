package com.auction.entity;

/**
 * Created by Stepan
 * on 24-Nov-17
 * The status of an Auction
 */

public enum AuctionStatus {
    /**
     *  The auction is in planned state
     */
    PLANNED,
    /**
     *  The auction is in open state
     */
    OPEN,
    /**
     *  The auction is in closed state
     */
    CLOSED
}