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
import org.ideoholic.curium.model.library.dto.BookHistory;
import org.ideoholic.curium.model.library.dto.BookIssue;
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

	public void updatebookAfterIssue(List<Integer> ids) {
		try {
			transaction = session.beginTransaction();
			Query query= session.createSQLQuery("update Book set issuedQty = issuedQty + 1  where bid IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		
	}

	public List<BookIssue> readListOfBook(String sid) {
		List<BookIssue> results = new ArrayList<BookIssue>();
        try {
            
            transaction = session.beginTransaction();
            results = (List<BookIssue>) session.createQuery("From BookIssue where bookHolder='"+sid+"'").list();
            transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
    			HibernateUtil.closeSession();
            return results;
        }
	}

	public void updatebookAfterReturn( List<Integer> ids) {
		try {
			transaction = session.beginTransaction();
			Query query= session.createSQLQuery("update book set issuedQty = issuedQty-1  where bid IN (:ids)");
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
			Query query= session.createSQLQuery("update Book set bookname = '"+bookname+"' , subject = '"+subject+"' , author = '"+author+"' , publisher = '"+publisher+"' , isbn = '"+isbn+"' , shelf = '"+shelf+"' where bid ='"+bid+"'");
			query.executeUpdate();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}

		
	}

	public List<BookHistory> readListOfBookHistory() {
		List<BookHistory> results = new ArrayList<BookHistory>();
        try {
            
            transaction = session.beginTransaction();
            results = (List<BookHistory>) session.createQuery("From BookHistory").list();
            transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
    			HibernateUtil.closeSession();
            return results;
        }
	}

	public BookHistory add(BookHistory bookHistory) {
		try {
			// this.session = sessionFactory.openCurrentSession();
			transaction = session.beginTransaction();
			session.save(bookHistory);
			transaction.commit();

		} catch (Exception hibernateException) {
			transaction.rollback();
			logger.error(hibernateException);

			hibernateException.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
			return bookHistory;
		}
	}

	public void deleteBookHistoryRecord(List<Integer> ids) {
		try {
			transaction = session.beginTransaction();
			
			
			Query query = session
					.createQuery("delete from BookHistory as book where book.id IN (:ids)");
			query.setParameterList("ids", ids);
			
			query.executeUpdate();
			
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}	
		
	}

	public BookIssue add(BookIssue bookIssue) {
		try {
			// this.session = sessionFactory.openCurrentSession();
			transaction = session.beginTransaction();
			session.save(bookIssue);
			transaction.commit();

		} catch (Exception hibernateException) {
			transaction.rollback();
			logger.error(hibernateException);

			hibernateException.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
			return bookIssue;
		}
	}

	public void updatebookissueAfterReturn(List<Integer> ids) {
		try {
			transaction = session.beginTransaction();
			
			
			Query query = session
					.createQuery("delete from BookIssue as book where book.id IN (:ids)");
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
