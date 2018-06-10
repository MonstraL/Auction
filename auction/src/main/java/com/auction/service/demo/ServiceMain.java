package com.auction.service.demo;

import com.auction.service.UserService;
import com.auction.service.impl.UserServiceImpl;
import org.springframework.stereotype.Component;


/**
 * Created by Petro Karabyn
 * on 25-Nov-17
 * Temporary class for services demonstration and
 * checking the correctness of behavior.
 */
@Component
class ServiceMain {
    public static void main(String[] args) {
     UserService userService = new UserServiceImpl();
     userService.findByIdInitialized(257);
   }
}
