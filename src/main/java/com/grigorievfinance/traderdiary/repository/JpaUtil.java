package com.grigorievfinance.traderdiary.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JpaUtil {
    @PersistenceContext
    private EntityManager entityManager;

    public void clear2ndLevelHibernateCache() {
        Session s = (Session) entityManager.getDelegate();
        SessionFactory sf = s.getSessionFactory();
//        sf.getCache().evictEntityData(User.class, AbstractBaseEntity.START_SEQ);
//        sf.getCache().evictEntityData(User.class);
        sf.getCache().evictAllRegions();
    }
}
