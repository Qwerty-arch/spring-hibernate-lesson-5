package com.oshovskii.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ProductDao {

    private static SessionFactory factory;


    public static void init() {
        PrepareDataApp.forcePrepareData();
        factory = new Configuration()
                .configure("configs/hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public static void  findAll() {
        try (Session session = factory.getCurrentSession()) {
            System.out.println("==========\nFIND ALL PRODUCT\n==========");
            session.beginTransaction();
            List<Product> items = session.createQuery("from Product").getResultList();
            System.out.println(items + "\n");
            session.getTransaction().commit();
        }
    }

    public static void shutdown() {
        factory.close();
    }

    public static void saveOrUpdate(Product product) {
        try (Session session = factory.getCurrentSession()) {
            System.out.println("==========\nSAVE/UPDATE PRODUCT\n==========");
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
            System.out.println(product);
        }
    }

    public static void findById(Long id) {
        System.out.println("==========\nFIND PRODUCT BY ID " + id + "\n==========");
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            System.out.println(product);
            session.getTransaction().commit();
        }
    }

    public static void deleteById(Long id) {
        try (Session session = factory.getCurrentSession()) {
            System.out.println("==========\nDELETE PRODUCT BY ID " + id + "\n==========");
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            System.out.println(product + " - delete");
            session.delete(product);
            session.getTransaction().commit();
        }
    }

    public static void main(String[] args) {
        try {
            init();
            Product cat = new Product("Egyptian mau", 13000);
            saveOrUpdate(cat);
            findById(3L);
            deleteById(3L);
            findAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            shutdown();
        }
    }
}
