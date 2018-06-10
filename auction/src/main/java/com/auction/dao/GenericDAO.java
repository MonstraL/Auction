package com.auction.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Petro Karabyn
 * on 24-Nov-17
 * GenericDAO interface supporting CRUD operations.
 */

public interface GenericDAO<Item, Id extends Serializable> {

    /**
     * Saves an entity
     * @param entity any type of entity
     */
    void create(Item entity);

    /**
     * Updates entity's contents.
     * @param entity any type of entity
     */
    void update(Item entity);

    /**
     * Deletes an entity.
     * @param entity any type of entity
     */
    void delete(Item entity);

    /**
     * Searches for an entity of a specified type with a specified id
     * and returns it if present.
     * @param id: id of an entity
     * @return Entity object
     */
    Item findById(Id id);

    /**
     * @return list of all existing entity objects of a specified type
     */
    List<Item> findAll();

}
