package com.model.order.booksinfo.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import com.model.order.booksinfo.dto.BooksInfo;
import com.util.HibernateUtil;
import com.util.Session;
import com.util.Session.Transaction;

public class BooksInfoDAO {
	Session session = null;
	/**
	 * * Hibernate Session Variable
	 */
	Transaction transaction = null;
	/**
	 * * Hibernate Transaction Variable
	 */
	Transaction transaction1;
	//SessionFactory sessionFactory;
	
	private static final Logger logger = LogManager.getLogger(BooksInfoDAO.class);
	
	public BooksInfoDAO() {
		session = HibernateUtil.openCurrentSession();
	}

	
	public BooksInfo addNewBooksInfo(BooksInfo booksinfo) {
		 
		try {
	            //this.session = sessionFactory.openCurrentSession();
	            transaction = session.beginTransaction();
	            session.save(booksinfo);
	            transaction.commit();
	        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
	            
	            hibernateException.printStackTrace();
	        } finally {
	    			HibernateUtil.closeSession();
	        }
		 return booksinfo;
	}



	public boolean deleteBooksInfo(List<Integer> ids) {
        
        boolean result = false;
        try {
            transaction = session.beginTransaction();
            
            	
            	Query query = session.createQuery("delete from BooksInfo bi where bi.id IN (:ids)");
                query.setParameterList("ids", ids);
                query.executeUpdate();
                               
                result = true;
			
            transaction.commit();
            
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
		}
        return result;
    }


	public List<BooksInfo> getBooksInfo() {
		
        List<BooksInfo> results = new ArrayList<BooksInfo>();
        try {
                transaction = session.beginTransaction();
                results = (List<BooksInfo>) session.createQuery("From BooksInfo bi order by bi.id DESC").setCacheable(true).setCacheRegion("commonregion").list();
                transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
                
                hibernateException.printStackTrace();

        } finally {
    			HibernateUtil.closeSession();
        }
        return results;
}


	public List<BooksInfo> getAuthor(String title) {
		
        List<BooksInfo> results = new ArrayList<BooksInfo>();
        try {
                transaction = session.beginTransaction();
                results = (List<BooksInfo>) session.createQuery("From BooksInfo bi where bi.title = '"+title+"'").setCacheable(true).setCacheRegion("commonregion").list();
                transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
                
                hibernateException.printStackTrace();

        } finally {
    			HibernateUtil.closeSession();
        }
        return results;
}


}
