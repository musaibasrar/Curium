package org.ideoholic.curium.model.library.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.ideoholic.curium.model.diary.dto.Diary;
import org.ideoholic.curium.model.feescategory.dto.Feescategory;
import org.ideoholic.curium.model.library.dto.Book;
import org.ideoholic.curium.util.HibernateUtil;
import org.ideoholic.curium.util.Session;
import org.ideoholic.curium.util.Session.Transaction;

public class LibraryDAO {

	Session session = null;
    /**
     * * Hibernate Session Variable
     */
    Transaction transaction = null;
    /**
     * * Hibernate Transaction Variable
     */
  
    SessionFactory sessionFactory;
    private static final Logger logger = LogManager.getLogger(LibraryDAO.class);
    
    public LibraryDAO() {
		session = HibernateUtil.openCurrentSession();
	}

    @SuppressWarnings("finally")
	public Book create(Book book) {
		try {
            //this.session = sessionFactory.openCurrentSession();
            transaction = session.beginTransaction();
            session.save(book);
            transaction.commit();
            
        } catch (Exception hibernateException) { transaction.rollback();
        logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
    			HibernateUtil.closeSession();
            return book;
        }	}

	public List<Book> readListOfAvailableBook() {
		List<Book> results = new ArrayList<Book>();
        try {
            
            transaction = session.beginTransaction();
            results = (List<Book>) session.createQuery("From Book").list();
            transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
    			HibernateUtil.closeSession();
            return results;
        }
	}

	public void deleteRecord(List<Integer> ids) {
		try {
			transaction = session.beginTransaction();
			
			
			Query query = session
					.createQuery("delete from Book as book where book.bid IN (:ids)");
			query.setParameterList("ids", ids);
			
			query.executeUpdate();
			
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}	
		
	}

	public List<Book> readListOfBook() {
		List<Book> results = new ArrayList<Book>();
        try {
            
            transaction = session.beginTransaction();
            results = (List<Book>) session.createQuery("From Book").list();
            transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
    			HibernateUtil.closeSession();
            return results;
        }

	}

	public void updatebook(String uid, List<Integer> ids, String date) {
		try {
			transaction = session.beginTransaction();
			Query query= session.createSQLQuery("update book set status = 'Issued' , bookHolder = '"+uid+"' , startdate = '"+date+"' where bid IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		
	}

	public List<Book> readListOfBook(String sid) {
		List<Book> results = new ArrayList<Book>();
        try {
            
            transaction = session.beginTransaction();
            results = (List<Book>) session.createQuery("From Book where bookHolder='"+sid+"'").list();
            transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
    			HibernateUtil.closeSession();
            return results;
        }
	}

	public void updatebook( List<Integer> ids) {
		try {
			transaction = session.beginTransaction();
			Query query= session.createSQLQuery("update book set status = 'Available' , bookHolder = ' ' where bid IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		
	}

	public Book readDetailsOfBook(int bid) {
		Book book = new Book();
		try {
			// this.session =
			// HibernateUtil.getSessionFactory().openCurrentSession();

			transaction = session.beginTransaction();
			Query query = session
					.createQuery("from Book as book where book.bid="
							+ bid);
			book = (Book) query.uniqueResult();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		 }
		return book;
	}

	public void updatebookdetail(int bid, String bookname, String subject, String author, String publisher, String isbn,
			String shelf) {
		try {
			transaction = session.beginTransaction();
			Query query= session.createSQLQuery("update book set bookname = '"+bookname+"' , subject = '"+subject+"' , author = '"+author+"' , publisher = '"+publisher+"' , isbn = '"+isbn+"' , shelf = '"+shelf+"' where bid ='"+bid+"'");
			query.executeUpdate();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}

		
	}

	
	

	

}
