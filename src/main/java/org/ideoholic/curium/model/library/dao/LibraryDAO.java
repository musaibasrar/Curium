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
import org.ideoholic.curium.model.library.dto.BooksRequestDto;
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
					.createQuery("delete from Book as book where issuedqty=0 and book.bid IN (:ids)");
			query.setParameterList("ids", ids);
			
			query.executeUpdate();
			
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}	
		
	}

	public List<Book> readListOfBook(String branchId) {
		List<Book> results = new ArrayList<Book>();
        try {
            
            transaction = session.beginTransaction();
            results = (List<Book>) session.createQuery("From Book where branchid="+branchId+"").list();
            transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
    			HibernateUtil.closeSession();
            return results;
        }

	}

	public void updatebookAfterIssue(List<Integer> ids, List<BookHistory> bookHistoryList, List<BookIssue> bookIssueList) {
		try {
			transaction = session.beginTransaction();
			Query query= session.createSQLQuery("update book set issuedqty = issuedqty + 1  where bid IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			
			for (BookIssue bookIssue : bookIssueList) {
				session.save(bookIssue);
			}
			
			for (BookHistory bookHistory : bookHistoryList) {
				session.save(bookHistory);
			}
			
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		
	}

	public List<BookIssue> readListOfBooksIssued(String sid) {
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
			String shelf, int availableQty, int issuedQty) {
		try {
			transaction = session.beginTransaction();
			Query query= session.createSQLQuery("update book set bookname = '"+bookname+"' , subject = '"+subject+"' , author = '"+author+"' , publisher = '"+publisher+"' , isbn = '"+isbn+"' , shelf = '"+shelf+"', availableqty='"+availableQty+"', issuedqty='"+issuedQty+"' where bid ='"+bid+"'");
			query.executeUpdate();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}

		
	}

	public List<BookHistory> readListOfBookHistory(String fromDate, String toDate) {
		List<BookHistory> results = new ArrayList<BookHistory>();
        try {
            
            transaction = session.beginTransaction();
            results = (List<BookHistory>) session.createQuery("From BookHistory where issueDate between '"+fromDate+"' and '"+toDate+"'").list();
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
	

	public void updateBookOnReturn(List<Integer> bookIds,List<Integer> bookIssueIds,List<Integer> noOfDays,String returnDate) {
		try {
			transaction = session.beginTransaction();
			Query query= session.createSQLQuery("update book set issuedQty = issuedQty-1  where bid IN (:ids)");
			query.setParameterList("ids", bookIds);
			query.executeUpdate();
			
			Query queryBookIssue = session.createSQLQuery("UPDATE bookissue SET noofdays = :totalDays, returned = 'Yes',actualreturndate='"+returnDate+"' WHERE id = :bookId");
		    queryBookIssue.setParameter("totalDays", noOfDays);
		    queryBookIssue.setParameter("bookId", bookIssueIds);
			queryBookIssue.executeUpdate();
				
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		
	}

}
