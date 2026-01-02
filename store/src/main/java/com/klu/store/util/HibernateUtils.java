package com.klu.store.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

    private static SessionFactory sf = null;

    static {
        try {
            sf = new Configuration().configure().buildSessionFactory();
        } catch (Throwable e) {
            System.err.println(e);
            throw new ExceptionInInitializerError();
        }
    }

    public static SessionFactory getSessionFactory() {
        return sf;
    }

    public void shutdown() {
        if (sf != null)
            sf.close();
    }
}
