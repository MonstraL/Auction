package com.auction.controller;

import com.auction.entity.Bid;
import com.auction.entity.wrappers.BidContextWrapper;
import com.auction.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Petro Karabyn
 * on 15-Dec-17
 */

@Controller
@RequestMapping("/bids")
public class BidController {

    private BidService bidService;

    @Autowired
    public void setBidService(BidService bidService) {
        this.bidService = bidService;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public Bid getBidById(@PathVariable("id") int id) {
        return bidService.findById(id);
    }

    /**
     * An implementation of placeBid() using POST method.
     * Navigate to the BidContextWrapper for additional documentation
     * and to see how the json should look like for this POST method.
     * @param bidContextWrapper: a wrapper for User, Lot and bidValue
     */
   /* @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public void placeBid(@RequestBody BidContextWrapper bidContextWrapper) {
        bidService.placeBid(bidContextWrapper.getUser(), bidContextWrapper.getLot(), bidContextWrapper.getBidValue());
    }*/

    /**
     * A handy implementation of placeBid() using the GET method
     * and RequestParams in the URI.
     * The URI should look like http://localhost:8080/bids?uid=1&lid=1&val=1
     *
     */
   /* @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public void placeBid(@RequestParam("uid") int user_id,
                           @RequestParam("lid") int lot_id,
                           @RequestParam("val") int value) {
        bidService.placeBid(user_id, lot_id, value);
    }*/

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public void createBid(@RequestBody Bid bid) {

        bidService.placeBid(bid.getBidder().getId(), bid.getLot().getId(), bid.getValue());
    }

}
