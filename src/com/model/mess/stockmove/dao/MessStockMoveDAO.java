package com.model.mess.stockmove.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import com.model.account.dto.VoucherEntrytransactions;
import com.model.degreedetails.dto.Degreedetails;
import com.model.mess.item.dto.MessItems;
import com.model.mess.stockentry.dto.MessStockEntry;
import com.model.mess.stockmove.dto.MessStockMove;
import com.model.parents.dto.Parents;
import com.model.pudetails.dto.Pudetails;
import com.model.std.dto.Classhierarchy;
import com.model.student.dto.Student;
import com.model.student.dto.Studentfeesstructure;
import com.util.HibernateUtil;
import com.util.Session;
import com.util.Session.Transaction;

public class MessStockMoveDAO {
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
	
	private static final Logger logger = LogManager.getLogger(MessStockMoveDAO.class);
	
	public MessStockMoveDAO() {
		session = HibernateUtil.openCurrentSession();
	}

	

	public List<MessItems> getItemsDetails() {
		
        List<MessItems> results = new ArrayList<MessItems>();
        try {
                transaction = session.beginTransaction();
                results = (List<MessItems>) session.createQuery("From MessItems mi order by mi.id ASC").setCacheable(true).setCacheRegion("commonregion").list();
                transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
                
                hibernateException.printStackTrace();

        } finally {
    			HibernateUtil.closeSession();
        }
        return results;
}



	public MessItems addNewItem(MessItems messItems) {
		 
		try {
	            //this.session = sessionFactory.openCurrentSession();
	            transaction = session.beginTransaction();
	            session.save(messItems);
	            Query query = session.createQuery("update MessItems set externalid= concat(externalid,'_"+messItems.getId()+"') where id="+messItems.getId());
				query.executeUpdate();
	            transaction.commit();
	        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
	            
	            hibernateException.printStackTrace();
	        } finally {
	    			HibernateUtil.closeSession();
	        }
		 return messItems;
	}



	public boolean deleteItems(List<Integer> ids) {
        
        boolean result = false;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from MessItems where id IN (:ids)");
            query.setParameterList("ids", ids);
            query.executeUpdate();
            transaction.commit();
            result = true;
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
		}
        return result;
    }



	public boolean updateMultipleItems(List<MessItems> messList) {

        boolean result = false;
        try {
            transaction = session.beginTransaction();
            for (MessItems items : messList) {
                Query query = session.createQuery("update MessItems set name = '"+items.getName()+"', unitofmeasure = '"+items.getUnitofmeasure()+"' where id="+items.getId());
				query.executeUpdate();
            }
            transaction.commit();
            result = true;
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
		}
        return result;
    
	}

	public List<MessStockEntry> getMRVDetails(int invoiceDetailsId) {
		
        List<MessStockEntry> results = new ArrayList<MessStockEntry>();
        try {
                transaction = session.beginTransaction();
                results = (List<MessStockEntry>) session.createQuery("From MessStockEntry mse where mse.status != 'CANCELLED' and mse.messinvoicedetails.status !='CANCELLED' and mse.messinvoicedetails.id = '"+invoiceDetailsId+"'  order by mse.id ASC").setCacheable(true).setCacheRegion("commonregion").list();
                transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
                
                hibernateException.printStackTrace();

        } finally {
    			HibernateUtil.closeSession();
        }
        return results;
}



	public boolean moveStockSave(List<MessStockMove> messStockMovesList, VoucherEntrytransactions transactions,
			String updateDrAccount, String updateCrAccount) {
		 
		boolean result = false; 
		try {
	        //this.session = sessionFactory.openCurrentSession();
	        transaction = session.beginTransaction();
			session.save(transactions);
			Query query = session.createQuery(updateDrAccount);
			query.executeUpdate();
			Query query1 = session.createQuery(updateCrAccount);
			query1.executeUpdate();

			for (MessStockMove messStockMove : messStockMovesList) {
	        	
				session.save(messStockMove);
	        	Query queryUpdateMessStock = session.createQuery("update MessStockMove set externalid= concat(externalid,'_"+messStockMove.getId()+"'), voucherid = '"+transactions.getTransactionsid()+"' where id="+messStockMove.getId());
	        	queryUpdateMessStock.executeUpdate();
				Query queryStockAvailability = session.createQuery("update MessStockAvailability set availablestock= availablestock-'"+messStockMove.getQuantity()+"' where itemid="+messStockMove.getItemid());
				queryStockAvailability.executeUpdate();
				Query queryStockEntry = session.createQuery("update MessStockEntry set availablequantity= availablequantity-'"+messStockMove.getQuantity()+"' where id="+messStockMove.getStockentryid());
				queryStockEntry.executeUpdate();
				Query invoiceQuery = session.createQuery("from MessStockEntry where id = '"+messStockMove.getStockentryid()+"'");
				MessStockEntry messStockEntry = (MessStockEntry) invoiceQuery.uniqueResult();
				Query queryInvoiceId = session.createQuery("update MessStockEntry set status = 'MOVED' where invoicedetailsid = '"+messStockEntry.getMessinvoicedetails().getId()+"'");
				queryInvoiceId.executeUpdate();
				Query queryInvoiceDetails = session.createQuery("update MessInvoiceDetails set status = 'MOVED' where id = '"+messStockEntry.getMessinvoicedetails().getId()+"'");
				queryInvoiceDetails.executeUpdate();
			}
	        
	        transaction.commit();
	        result = true;
	        
    } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
        
        hibernateException.printStackTrace();
    } finally {
			HibernateUtil.closeSession();
    }

 return result;
}



	public List<MessStockMove> getStockMoveDetails(int offset,
			int noOfRecords, int branchId) {
		
        List<MessStockMove> results = new ArrayList<MessStockMove>();
        
        try {
                transaction = session.beginTransaction();
                
                Query query = session.createQuery("From MessStockMove msm where msm.status != 'CANCELLED' order by msm.id DESC").setCacheable(true).setCacheRegion("commonregion");
    			query.setFirstResult(offset);   
    			query.setMaxResults(noOfRecords);
    			results = query.getResultList();
    			
                transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
                
                hibernateException.printStackTrace();

        } finally {
    			HibernateUtil.closeSession();
        }
        return results;
}



	public MessStockMove getStockMoveDetails(int stockid) {
		
        MessStockMove results = new MessStockMove();
        
        try {
                transaction = session.beginTransaction();
                Query query = session.createQuery("From MessStockMove msm where id = '"+stockid+"'");
                results = (MessStockMove) query.uniqueResult();
                transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
                
                hibernateException.printStackTrace();

        } finally {
    			HibernateUtil.closeSession();
        }
        return results;
}



	public boolean cancelStockMove(MessStockMove messStockMove, VoucherEntrytransactions transactions, String updateDrAccount, String updateCrAccount) {
		
		boolean result = false;
		
		try {
            
			transaction = session.beginTransaction();
			
			//
            Query queryInvoiceDetails = session.createQuery("update MessStockMove set status='CANCELLED' where id = '"+messStockMove.getId()+"'");
            queryInvoiceDetails.executeUpdate();
            
            Query queryStock = session.createQuery("update MessStockEntry set availablequantity = availablequantity+'"+messStockMove.getQuantity()+"' where id = '"+messStockMove.getStockentryid()+"'");
            queryStock.executeUpdate();
            
            Query queryStockAvailable = session.createQuery("update MessStockAvailability set availablequantity = availablequantity+'"+messStockMove.getQuantity()+"' where itemid = '"+messStockMove.getItemid()+"'");
            queryStock.executeUpdate();
            
            //Accounts
			session.save(transactions);
			Query query = session.createQuery(updateDrAccount);
			query.executeUpdate();
			Query query1 = session.createQuery(updateCrAccount);
			query1.executeUpdate();
            
            
            transaction.commit();
            
            result = true;
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
		}
		
		return result;
	}



	public List<MessStockMove> getStockMoveDetailsReport(String stockMoveQuery) {
		
        List<MessStockMove> results = new ArrayList<MessStockMove>();
        
        try {
                transaction = session.beginTransaction();
                results = (List<MessStockMove>) session.createQuery(stockMoveQuery).setCacheable(true).setCacheRegion("commonregion").list();
                transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
                
                hibernateException.printStackTrace();

        } finally {
    			HibernateUtil.closeSession();
        }
        return results;
}



	public int getNoOfRecordsStockMove(int branchId) {
		List<Student> results = new ArrayList<Student>();
		int noOfRecords = 0;
		try {
			// this.session =
			// HibernateUtil.getSessionFactory().openCurrentSession();
			transaction = session.beginTransaction();

			results = (List<Student>) session.createQuery("From MessStockMove msm where msm.status != 'CANCELLED' AND msm.branchid="+branchId).setCacheable(true).setCacheRegion("commonregion")
					.list();
			noOfRecords = results.size();
			logger.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: "+ noOfRecords);
			
			transaction.commit();

		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();

		} finally {
				HibernateUtil.closeSession();
			return noOfRecords;
		}
	}
	
}
