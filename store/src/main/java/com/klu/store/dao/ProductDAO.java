package com.klu.store.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.klu.store.entity.Product;
import com.klu.store.util.HibernateUtils;

public class ProductDAO {

	// Create
	public void saveProduct(Product p) {
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			Transaction tx = session.beginTransaction();
			session.save(p);
			tx.commit();
		}
	}

	// Read all
	public List<Product> getProducts() {
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			return session.createQuery("FROM Product", Product.class).list();
		}
	}

	// Read by ID
	public Product getByProductId(int pid) {
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			return session.get(Product.class, pid);
		}
	}

	// Update
	public boolean updateproduct(Product p) {
		Transaction tx = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			session.merge(p);
			tx.commit();
			return true;
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return false;
		}
	}

	// Delete
	public boolean deleteProduct(int pid) {
		Transaction tx = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			Product p = session.get(Product.class, pid);
			if (p != null) {
				session.delete(p);
			}
			tx.commit();
			return true;
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
			return false;
		}
	}
}
