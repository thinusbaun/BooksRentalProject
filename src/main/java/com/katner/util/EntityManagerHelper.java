package com.katner.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by michal on 03.11.15.
 */
public class EntityManagerHelper {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("NewPersistenceUnit");

    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

}
