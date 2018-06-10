package com.auction.dao;

import com.auction.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Petro Karabyn
 * on 04-Dec-17
 */

@ContextConfiguration(locations = "/spring/root-context.xml")
public class UserDAOTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private UserDAO userDAO;

    @Test
    public void testCreate() {
        int size = userDAO.findAll().size();
        User user = new User("user@test.com", "test");
        userDAO.create(user);
        // list should have one more employee now
        assertTrue (size < userDAO.findAll().size());
    }

    @Test
    public void testUpdate() {
        User user = new User("user@test.com", "test");
        userDAO.create(user);
        user.setPassword("updated");
        userDAO.update(user);
        User found = userDAO.findById(user.getId());
        assertEquals("updated", found.getPassword());
    }


    @Test
    public void testFindById() {
        User user = new User("user@test.com", "test");
        userDAO.create(user);
        User found = userDAO.findById(user.getId());
        assertEquals(found, user);
    }

    @Test
    public void testFindAll() {
        int size = userDAO.findAll().size();
        User user = new User("user@test.com", "test");
        userDAO.create(user);
        List<User> users = userDAO.findAll();
        assertEquals(size + 1, users.size());
        assertTrue(users.contains(user));
    }

    @Test
    public void testDelete() {
        User user = new User("user@test.com", "test");
        userDAO.create(user);
        // successfully added
        assertEquals(user, userDAO.findById(user.getId()));
        // try to delete
        userDAO.delete(user);
        assertNull(userDAO.findById(user.getId()));
    }

    @Test
    public void testFindByIdInitialized() {
        User user = new User("user@test.com", "initialized");
        userDAO.create(user);
        user = userDAO.findByIdInitialized(user.getId());
        List emptyList = new ArrayList();
        // try to retrieve the lazy loaded fields of user and check if they are initialized
        assertEquals(emptyList, user.getLotsForSale());
        assertEquals(emptyList, user.getBids());
        assertEquals(emptyList, user.getAuctions());
    }



}
