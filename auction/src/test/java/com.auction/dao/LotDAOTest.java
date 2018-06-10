package com.auction.dao;

import com.auction.entity.Lot;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Petro Karabyn
 * on 05-Dec-17
 */

@ContextConfiguration(locations = "/spring/root-context.xml")
public class LotDAOTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private LotDAO lotDAO;

    @Test
    public void testCreate() {
        int size = lotDAO.findAll().size();
        Lot lot = new Lot();
        lot.setName("test");
        lotDAO.create(lot);
        assertTrue (size < lotDAO.findAll().size());
    }

    @Test
    public void testUpdate() {
        Lot lot = new Lot();
        lot.setName("test");
        lotDAO.create(lot);
        lot.setName("updated");
        lotDAO.update(lot);
        Lot found = lotDAO.findById(lot.getId());
        assertEquals("updated", found.getName());
    }

    @Test
    public void testFindById() {
        Lot lot = new Lot();
        lot.setName("test");
        lotDAO.create(lot);
        Lot found = lotDAO.findById(lot.getId());
        assertEquals(found, lot);
    }

    @Test
    public void testFindAll() {
        int size = lotDAO.findAll().size();
        Lot lot = new Lot();
        lot.setName("test");
        lotDAO.create(lot);
        List<Lot> lots = lotDAO.findAll();
        assertEquals(size + 1, lots.size());
        assertTrue(lots.contains(lot));
    }

    @Test
    public void testDelete() {
        Lot lot = new Lot();
        lot.setName("test");
        lotDAO.create(lot);
        // successfully added
        assertEquals(lot, lotDAO.findById(lot.getId()));
        // try to delete
        lotDAO.delete(lot);
        assertNull(lotDAO.findById(lot.getId()));
    }

    @Test
    public void testFindByIdInitialized() {
        Lot lot = new Lot();
        lot.setName("initialized");
        lotDAO.create(lot);
        lot = lotDAO.findByIdInitialized(lot.getId());
        List emptyList = new ArrayList();
        // try to retrieve the lazy loaded fields of user and check if they are initialized
        assertEquals(emptyList, lot.getBids());
    }

}
