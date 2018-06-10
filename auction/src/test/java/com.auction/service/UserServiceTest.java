package com.auction.service;

import com.auction.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import static org.junit.Assert.*;

/**
 * Created by Petro Karabyn
 * on 05-Dec-17
 */

@ContextConfiguration(locations = "/spring/root-context.xml")
public class UserServiceTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private UserService userService;

    @Test
    public void testFindByIdInitialized() {
        User user = new User("initialized@yahoo.com", "initialized");
        userService.create(user);
        user = userService.findByIdInitialized(user.getId());
        // Check that a user was retrieved
        assertNotNull(user);
        // Check that a lazy loaded fields get initialized
        assertNotNull(user.getAuctions());
        assertNotNull(user.getBids());
        assertNotNull(user.getLotsForSale());
    }
}
