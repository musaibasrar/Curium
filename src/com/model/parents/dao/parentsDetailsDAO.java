package com.model.parents.dao;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.model.parents.dto.Parents;
import com.util.HibernateUtil;
import com.util.Session;
import com.util.Session.Transaction;

public class parentsDetailsDAO {
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
	    
	    public parentsDetailsDAO() {
	    	   session = HibernateUtil.openCurrentSession();
		}

	@SuppressWarnings("finally")
	public Parents create(Parents parents) {
		 try {
	            //this.session = sessionFactory.openCurrentSession();
	            transaction = session.beginTransaction();
	            session.save(parents);
	            transaction.commit();
	           
	        } catch (HibernateException hibernateException) {transaction.rollback();
	            hibernateException.printStackTrace();
	        } finally {
	            HibernateUtil.closeSession();
	            return parents;
	        }
	}

	public Parents readUniqueObject(long id) {
		Parents parents = new Parents();
        try {
            //this.session = HibernateUtil.getSessionFactory().openCurrentSession();

            transaction = session.beginTransaction();
            Query query = session.createQuery("from Parents as parents where parents.Student.sid=" + id);
            parents = (Parents) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        }
        finally {
			HibernateUtil.closeSession();
		}
        return parents;
	}

	@SuppressWarnings("finally")
	public Parents update(Parents parents) {
		
		try {
            //this.session = sessionFactory.openCurrentSession();
            transaction = session.beginTransaction();
            session.update(parents);
            transaction.commit();
            System.out.println("in update parents");
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
            return parents;
        }
	}

}
