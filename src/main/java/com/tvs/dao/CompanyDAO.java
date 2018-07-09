package com.tvs.dao;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tvs.model.*;

@Repository("CompanyDAO")
public class CompanyDAO {

	@Autowired
	public SessionFactory sessionFactory;
	
	public  Company insertCompany(Company company )  {
		String res = "";
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			session.save(company);
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
		return company;
	}
	
	public Company deleteCompanyReference(int companyId)  {

		String res = "";
		Session session = null;
		Transaction tx = null;
		Company company  = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			company = (Company)session.load(Company.class, companyId);
			session.delete(company);
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
		return company;
	}
	
	public Company deleteCompany(int companyId)  {

		String res = "";
		Session session = null;
		Transaction tx = null;
		Company company  = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			company = (Company)session.load(Company.class, companyId);
			company.setUsers(new ArrayList<Users>());
			company.getApplications().get(0).setUser(null);
			session.delete(company);
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
		return company;
	}
	public Company updateCompany(Company company)  {

		String res = "";
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			session.saveOrUpdate(company);
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
		return company;
	}
	
	public Company getCompanyById(int companyId){
		String res = "";
		Session session = null;
		Transaction tx = null;
		Company company = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			company = (Company)session.get(Company.class, companyId);
			company.getApplications().size();
			if(company.getUsers().size() > 0)
			company.getUsers().get(0).getAuthorities().size();
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
		return company;
	}
	
	public Company getCompanyByName(String companyName){
		String res = "";
		Session session = null;
		Transaction tx = null;
		Company company = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			Criteria cr1234 = session.createCriteria(Company.class);
			cr1234.add( Restrictions.eq("Name", companyName));
			
			company = (Company)cr1234.list().get(0);
			
			company.getApplications().size();
			if(company.getUsers().size() > 0)
			company.getUsers().get(0).getAuthorities().size();
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
		return company;
	}

}
