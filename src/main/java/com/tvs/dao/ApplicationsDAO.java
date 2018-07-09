package com.tvs.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tvs.model.*;

@Repository("ApplicationsDAO")
public class ApplicationsDAO {


	@Autowired
	public SessionFactory sessionFactory;
	
	public Applications insertApplications(Applications app )  {
		String res = "";
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			session.save(app);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if (tx != null) {
				try {
					tx.rollback();
					res = "HibernateException!";
				} catch (Exception re) {
					res = "RollBackException!";
				}
			} else {
				res = "Transaction is null!";
			}
		} finally {
			if (session != null)
				session.close();
		}
		return app;
	}
	
	public Applications deleteApplications(int Id)  {
		String res = "";
		Session session = null;
		Transaction tx = null;
		Applications app = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			app = (Applications)session.get(Applications.class, Id);

			session.delete(app);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if (tx != null) {
				try {
					tx.rollback();
					res = e.getMessage();
				} catch (Exception re) {
					res = "RollBackException!";
				}
			} else {
				res = "Transaction is null!";
			}
		} finally {
			if (session != null)
				session.close();
		}
		//System.out.println(res);
		return app;
	}
	
	public Applications updateApplications(Applications app)  {
		String res = "";
		Session session = null;
		Transaction tx = null;
		Authorities authorities = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			session.saveOrUpdate(app);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if (tx != null) {
				try {
					tx.rollback();
					res = e.getMessage();
				} catch (Exception re) {
					res = "RollBackException!";
				}
			} else {
				res = "Transaction is null!";
			}
		} finally {
			if (session != null)
				session.close();
		}
		//System.out.println(res);
		return app;
	}
	
	public Applications getApplicationById(int applicationId){
		String res = "";
		Session session = null;
		Transaction tx = null;
		Applications applications = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			applications = (Applications)session.get(Applications.class, applicationId);
			applications.getUser();
			applications.getCompany().getUsers().size();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if (tx != null) {
				try {
					tx.rollback();
					res = "HibernateException!";
				} catch (Exception re) {
					res = "RollBackException!";
				}
			} else {
				res = "Transaction is null!";
			}
		} finally {
			if (session != null)
				session.close();
		}
		return applications;
	}

}
