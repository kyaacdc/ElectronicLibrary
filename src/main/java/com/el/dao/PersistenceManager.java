package com.el.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public enum PersistenceManager {
    ENT;

    private EntityManagerFactory emFactory;

    PersistenceManager() {
        emFactory =
                Persistence.createEntityManagerFactory("electroniclibrary");
    }

    public EntityManager getEntityManager() {
        return emFactory.createEntityManager();
    }

    public void close() {
        emFactory.close();
    }
}
