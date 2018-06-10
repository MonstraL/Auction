package com.auction.controller;

import com.auction.entity.User;
import com.auction.service.AuctionService;
import com.auction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    // UserService is an interface. One of the most important purposes of Spring is used here.
    // Depend on the interfaces and have implementations injected instead of creating them by hand.
    private UserService userService;
    private AuctionService auctionService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setAuctionService(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    public UserService getUserService() {
        return userService;
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public String displayInfo(){
        return "Users controller";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public User getUserById(@PathVariable("id") int id) {
        return userService.findByIdInitialized(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<User> getAllUsers() {
            return userService.findAllInitialized();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public void createUser(@RequestBody User user) {
        userService.create(user);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public void update(@RequestBody User user) {
        userService.update(user);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteUserById(@PathVariable("id") int id) {
        User user = userService.findByIdInitialized(id);
        userService.unRegister(user);
        userService.delete(user);
    }

    @RequestMapping(value = "/login/{email}+{password}", method = RequestMethod.GET)
    @ResponseBody
    public User getUserById(@PathVariable("email") String email, @PathVariable("password") String password) {
        return userService.logIn(email, password);
    }

    @RequestMapping(value = "/aregister/{auctionId}+{userId}", method = RequestMethod.GET)
    @ResponseBody
    public void registerInAuction(@PathVariable("auctionId") String auctionId, @PathVariable("userId") String userId) {
        userService.registerForAnAuction(userService.findByIdInitialized(Integer.parseInt(userId)), auctionService.findByIdInitialized(Integer.parseInt(auctionId)));
    }

}
