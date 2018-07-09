package com.tvs.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tvs.model.*;

@Repository("UsersDAO")
public class UsersDAO {

	@Autowired
	public SessionFactory sessionFactory;

	public Users insertUser(Users user) {
		String res = "";
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.save(user);
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
		return user;
	}

	public Users deleteUser(String userName) {
		String res = "";
		Session session = null;
		Transaction tx = null;
		Users user = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			user = (Users) session.load(Users.class, userName);
			session.delete(user);
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
		return user;
	}

	public Users updateUser(Users user) {
		String res = "";
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.saveOrUpdate(user);
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
		return user;
	}

	public Users getUserByUserName(String userName) {
		String res = "";
		Session session = null;
		Transaction tx = null;
		Users user = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			user = (Users) session.get(Users.class, userName);
			if (user != null) {
				user.getAuthorities().size();
				if (user.getCompanys().size() > 0)
					for(int i = 0 ; i < user.getCompanys().size() ; i ++ ){
						user.getCompanys().get(i).getApplications().size();
					}

			}
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

		return user;
	}

	public String createUser(String username, String password) {
		String res = "";
		Session sess = null;
		Transaction tx = null;
		try {
			sess = sessionFactory.openSession();
			tx = sess.beginTransaction();

			Users user = new Users(username, password, Short.valueOf("1"));
			Authorities aouth = new Authorities(user, "ROLE_USER");
			user.getAuthorities().add(aouth);

			sess.save(user);

			sess.getTransaction().commit();
			res = "The registration process succeed. Now you can log in.";
		} catch (ConstraintViolationException ex) {
			if (tx != null) {
				try {
					tx.rollback();
					res = "ConstraintViolationException";
				} catch (Exception re) {
					res = "RollBackException";
				}
			} else {
				res = "Transaction is null!";
			}

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
			if (sess != null)
				sess.close();
		}
		return res;
	}

}
