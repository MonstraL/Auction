package com.auction.util.excluded;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Created by Petro Karabyn
 * on 24-Nov-17
 */

public class HibernateFactory {

    private static SessionFactory sessionFactory;
    private static Logger logger = Logger.getLogger(HibernateFactory.class);

    /**
     * Constructs a new Singleton SessionFactory
     */
    public static SessionFactory buildSessionFactory() throws HibernateException {
        if (sessionFactory != null) {
            closeFactory();
        }
        return configureSessionFactory();
    }

    /**
     * Builds a SessionFactory, if it hasn't been already.
     */
    public static SessionFactory buildIfNeeded(){
        if (sessionFactory != null) {
            return sessionFactory;
        }
        return configureSessionFactory();
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    

    public static Session openSession() throws HibernateException {
        buildIfNeeded();
        return sessionFactory.openSession();
    }

    public static void closeFactory() {
        if (sessionFactory != null) {
            try {
                sessionFactory.close();
            } catch (HibernateException ignored) {
                logger.error("Couldn't close SessionFactory", ignored);
            }
        }
    }

    public static void close(Session session) {
        if (session != null) {
            try {
                session.close();
            } catch (HibernateException ignored) {
                logger.error("Couldn't close Session", ignored);
            }
        }
    }

    public static void rollback(Transaction tx) {
        try {
            if (tx != null) {
                tx.rollback();
            }
        } catch (HibernateException ignored) {
            logger.error("Couldn't rollback Transaction", ignored);
        }
    }


    private static SessionFactory configureSessionFactory() throws HibernateException {
        sessionFactory = new Configuration()
                .configure("hibernate/hibernate.cfg.xml")
                // fix for a slow processing with a remote db
                .setProperty("hibernate.extensions.use_jdbc_metadata_defaults", "false")
                .buildSessionFactory();
        return sessionFactory;
    }
}
