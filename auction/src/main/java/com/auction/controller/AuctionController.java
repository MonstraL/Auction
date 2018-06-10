package com.auction.controller;

import com.auction.entity.Auction;
import com.auction.entity.AuctionStatus;
import com.auction.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Petro Karabyn
 * on 15-Dec-17
 */

@Controller
@RequestMapping("/auctions")
public class AuctionController {

    private AuctionService auctionService;

    @Autowired
    public void setAuctionService(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public String displayInfo(){
        return "Auctions controller";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public Auction getAuctionById(@PathVariable("id") int id) {
        return auctionService.findByIdInitialized(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Auction> getAllAuctions() {
        return auctionService.findAllInitialized();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public void createAuction(@RequestBody Auction auction) {
        auctionService.create(auction);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public void update(@RequestBody Auction auction) {
        auctionService.update(auction);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteAuctionById(@PathVariable("id") int id) {
        Auction auction = auctionService.findByIdInitialized(id);
        if(auction.getStatus()!= AuctionStatus.OPEN)
            auctionService.delete(auction);
    }


}
