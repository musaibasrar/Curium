package com.model.adminexpenses.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.util.Session;
import org.hibernate.SessionFactory;
import com.util.Session.Transaction;
import org.hibernate.query.Query;

import com.model.adminexpenses.dto.Adminexpenses;
import com.model.feescollection.dto.Receiptinfo;
import com.model.student.dto.Student;
import com.util.HibernateUtil;

public class AdminDetailsDAO {
	Session session = null;
	/**
	 * * Hibernate Session Variable
	 */
	Transaction transaction = null;
	/**
	 * * Hibernate Transaction Variable
	 */
	Transaction transaction1;
	SessionFactory sessionFactory;
	
	private static final Logger logger = LogManager.getLogger(AdminDetailsDAO.class);

	public AdminDetailsDAO() {
		session = HibernateUtil.openCurrentSession();
	}

	@SuppressWarnings("finally")
	public Adminexpenses create(Adminexpenses adminexpenses) {
		try {
			// this.session = sessionFactory.openCurrentSession();
			transaction = session.beginTransaction();
			session.save(adminexpenses);
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();
		} finally {
				HibernateUtil.closeSession();
			return adminexpenses;
		}
	}

	


	@SuppressWarnings({ "unchecked", "finally" })
	public List<Adminexpenses> readListOfExpenses(int branchId) {
		List<Adminexpenses> results = new ArrayList<Adminexpenses>();

		try {
			// this.session =
			// HibernateUtil.getSessionFactory().openCurrentSession();
			transaction = session.beginTransaction();

			results = (List<Adminexpenses>) session.createQuery("From Adminexpenses where branchid="+branchId)
					.list();
			System.out.println("Adminexpenses " + results.size());
			transaction.commit();

		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();

		} finally {
				HibernateUtil.closeSession();
			return results;
		}
	}

	


	public void deleteMultiple(List ids) {
		

		try {
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("update Adminexpenses set voucherstatus='CANCELLED' where idAdminExpenses IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}

	
		
	/*	try {
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("delete from Adminexpenses where idAdminExpenses IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}*/

	}

	public List<Adminexpenses> searchExpensesbydate(String queryMain) {
		
		java.util.List<Adminexpenses> adminExpenses = new ArrayList<Adminexpenses>();
        try {
            //this.session = HibernateUtil.getSessionFactory().openCurrentSession();

            transaction = session.beginTransaction();
            Query HQLquery = session.createQuery(queryMain);
            adminExpenses = (java.util.List<Adminexpenses>) HQLquery.list();
            transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        }
        finally {
			HibernateUtil.closeSession();
		 }
        return adminExpenses;
	}

	public Adminexpenses readExpenses(int expensesIds, int branchId) {
		

			Adminexpenses results = new Adminexpenses();

			try {
				// this.session =
				// HibernateUtil.getSessionFactory().openCurrentSession();
				transaction = session.beginTransaction();
				
				Query query = session
						.createQuery("From Adminexpenses where idAdminExpenses='"+expensesIds+"' and branchid="+branchId);
				results = (Adminexpenses) query.uniqueResult();

				transaction.commit();

			} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
				
				hibernateException.printStackTrace();

			} finally {
					HibernateUtil.closeSession();
				return results;
			}
			
	}

	public void rejectVoucher(List ids) {
		try {
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("update Adminexpenses set voucherstatus='rejected' where idAdminExpenses IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}

	}

	public void approveVoucher(List ids) {
		try {
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("update Adminexpenses set voucherstatus='approved' where idAdminExpenses IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}

	}

	
	

}
