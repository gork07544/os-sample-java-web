package com.tvs.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tvs.model.*;

@Repository("AuthoritiesDAO")
public class AuthoritiesDAO {

	@Autowired
	public SessionFactory sessionFactory;
	
	@Autowired
	private UsersDAO userDAO;
	
	public Authorities insertAuthority(Authorities authorities )  {
		String res = "";
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			session.save(authorities);
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
		return authorities;
	}
	
	public Authorities deleteAuthority(String username, String authority)  {
		String res = "";
		Session session = null;
		Transaction tx = null;
		Authorities authorities = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Users user = userDAO.getUserByUserName(username);
			Authorities aouth = null;
			List<Authorities> authList = user.getAuthorities();
			for (int i = 0; i < authList.size(); i++) {
				if (user.getAuthorities().get(i).getAuthority().equals(authority)) {
					aouth = user.getAuthorities().get(i);
				}
			}
			session.delete(aouth);
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
		return authorities;
	}
	
	public Authorities updateAuthority(String username, String oldAuthority, String newAuthority)  {
		String res = "";
		Session session = null;
		Transaction tx = null;
		Authorities authorities = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Users user = userDAO.getUserByUserName(username);
			Authorities aouth = null;
			List<Authorities> authList = user.getAuthorities();
			for (int i = 0; i < authList.size(); i++) {
				if (user.getAuthorities().get(i).getAuthority().equals(oldAuthority)) {
					aouth = user.getAuthorities().get(i);
					aouth.setAuthority(newAuthority);
				}
			}
			session.saveOrUpdate(aouth);
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
		return authorities;
	}

}
