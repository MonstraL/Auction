package com.auction.controller;

import com.auction.entity.Auction;
import com.auction.entity.AuctionStatus;
import com.auction.entity.Lot;
import com.auction.service.AuctionService;
import com.auction.service.LotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Petro Karabyn
 * on 14-Dec-17
 *
 * Tested in postman, works correctly as of 14-Dec-17
 */

@Controller
@RequestMapping("/lots")
public class LotController {

    private LotService lotService;
    private AuctionService auctionService;

    @Autowired
    public void setLotService(LotService lotService) {
        this.lotService = lotService;
    }

    @Autowired
    public void setAuctionService(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public String displayInfo(){
        return "Lots controller";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public Lot getLotById(@PathVariable("id") int id) {
        return lotService.findByIdInitialized(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Lot> getAllLots() {
        return lotService.findAllInitialized();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public void createPlainLot(@RequestBody Lot lot) {
        lotService.create(lot);
    }

    @RequestMapping(value = "userId={id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public String placeLotForSale(@RequestBody Lot lot, @PathVariable("id") int id) {
        lotService.addLotForSale(lot, id);
        return "Lot was placed for sale by user "  + id;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String update(@RequestBody Lot lot, @PathVariable("id") int id) {
        lot.setId(id);
        lotService.update(lot);
        return "Lot with id: " + lot.getId() + " was updated";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteAuctionById(@PathVariable("id") int id) {
        Lot lot = lotService.findByIdInitialized(id);
        Auction auction = auctionService.findByIdInitialized(lot.getAuction().getId());
        if(auction.getStatus()!= AuctionStatus.OPEN)
            lotService.delete(lot);
    }

}
