package com.model.parents.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.util.HibernateUtil;
import com.util.Session;
import org.hibernate.SessionFactory;
import com.util.Session.Transaction;
import org.hibernate.query.Query;

import com.model.parents.dto.Parents;

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
	    
	    private static final Logger logger = LogManager.getLogger(parentsDetailsDAO.class);
	    
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
	           
	        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
	            
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
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        }finally {
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
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
    			HibernateUtil.closeSession();
            return parents;
        }
	}

		@SuppressWarnings("finally")
	public boolean createMultiple(List<Parents> parents) {
		
			boolean result = false;
		 try {
	            //this.session = sessionFactory.openCurrentSession();
	            transaction = session.beginTransaction();
	            
	            for (Parents parent : parents) {
	            	session.save(parent);	
				}

	            transaction.commit();
	           result = true;
	        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
	            
	            hibernateException.printStackTrace();
	        } finally {
	    			HibernateUtil.closeSession();
	            return result;
	        }
	}

}
