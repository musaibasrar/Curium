package org.ideoholic.curium.model.mess.stockmove.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import org.ideoholic.curium.model.account.dto.VoucherEntrytransactions;
import org.ideoholic.curium.model.degreedetails.dto.Degreedetails;
import org.ideoholic.curium.model.mess.item.dto.MessItems;
import org.ideoholic.curium.model.mess.stockentry.dto.MessStockEntry;
import org.ideoholic.curium.model.mess.stockmove.dto.Bill;
import org.ideoholic.curium.model.mess.stockmove.dto.MessStockMove;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.pudetails.dto.Pudetails;
import org.ideoholic.curium.model.std.dto.Classhierarchy;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.model.student.dto.Studentfeesstructure;
import org.ideoholic.curium.util.HibernateUtil;
import org.ideoholic.curium.util.Session;
import org.ideoholic.curium.util.Session.Transaction;

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
			String updateDrAccount, String updateCrAccount, VoucherEntrytransactions transactionsIncomeCash,VoucherEntrytransactions transactionsIncomeBank,
			VoucherEntrytransactions transactionsIncomeCheque,
			String updateDrAccountIncomeCash, String updateCrAccountIncomeCash, String updateDrAccountIncomeBank, String updateCrAccountIncomeBank,
			String updateDrAccountIncomeCheque, String updateCrAccountIncomeCheque) {
		 
		boolean result = false;
		int billNo = 0;
		try {
	        //this.session = sessionFactory.openCurrentSession();
	        transaction = session.beginTransaction();
			session.save(transactions);
			Query query = session.createQuery(updateDrAccount);
			query.executeUpdate();
			Query query1 = session.createQuery(updateCrAccount);
			query1.executeUpdate();
			
			if(transactionsIncomeCash!=null) {
				session.save(transactionsIncomeCash);
			}
			
			if(transactionsIncomeBank!=null) {
				session.save(transactionsIncomeBank);
			}
			
			if(transactionsIncomeCheque!=null) {
				session.save(transactionsIncomeCheque);
			}
			
			
			if(updateDrAccountIncomeCash!=null) {
				Query queryIncome = session.createQuery(updateDrAccountIncomeCash);
				queryIncome.executeUpdate();
				Query queryIncomeCr = session.createQuery(updateCrAccountIncomeCash);
				queryIncomeCr.executeUpdate();
			}
			
			
			if(updateDrAccountIncomeBank!=null) {
				Query queryIncome = session.createQuery(updateDrAccountIncomeBank);
				queryIncome.executeUpdate();
				Query queryIncomeCr = session.createQuery(updateCrAccountIncomeBank);
				queryIncomeCr.executeUpdate();
			}
			
			
			if(updateDrAccountIncomeCheque!=null) {
				Query queryIncome = session.createQuery(updateDrAccountIncomeCheque);
				queryIncome.executeUpdate();
				Query queryIncomeCr = session.createQuery(updateCrAccountIncomeCheque);
				queryIncomeCr.executeUpdate();
			}
			
			Query queryMaxRow = session.createQuery("from MessStockMove ORDER BY id DESC");
			queryMaxRow.setMaxResults(1);
   			MessStockMove msm = (MessStockMove) queryMaxRow.uniqueResult();
			
			if(msm!=null) {
				String[] externalId = msm.getExternalid().split("_");
				billNo = Integer.parseInt(externalId[1]) + 1;
			}else {
				billNo = 1;
			}
			
			for (MessStockMove messStockMove : messStockMovesList) {
	        	
				session.save(messStockMove);
	        	Query queryUpdateMessStock = session.createQuery("update MessStockMove set externalid= concat(externalid,'_"+billNo+"'), voucherid = '"+transactions.getTransactionsid()+"' where id="+messStockMove.getId());
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



	public List<Bill> getStockMoveDetails(int offset,
			int noOfRecords, int branchId) {
		
        List<Bill> results = new ArrayList<Bill>();
        
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
            
            Query queryStockAvailable = session.createQuery("update MessStockAvailability set availablestock = availablestock+'"+messStockMove.getQuantity()+"' where itemid = '"+messStockMove.getItemid()+"'");
            queryStockAvailable.executeUpdate();
            
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
	
	public List<MessStockMove> getCustomerLastPrices(String customerName, String itemid, int branchId) {
		List<MessStockMove> results = new ArrayList<MessStockMove>();
		try {
			// this.session =
			// HibernateUtil.getSessionFactory().openCurrentSession();
			transaction = session.beginTransaction();

			Query query = session.createQuery("From MessStockMove msm where msm.status != 'CANCELLED' and issuedto='"+customerName+"' and itemid='"+itemid+"' AND msm.branchid="+branchId+" order by id DESC").setCacheable(true).setCacheRegion("commonregion");
			results = query.list();
			transaction.commit();

		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();

		} finally {
				HibernateUtil.closeSession();
			return results;
		}
	}



	public MessStockMove getMessStockMoveMaxRow() {
		
		MessStockMove msm = new MessStockMove();
		
				try {
					
		            transaction = session.beginTransaction();
		    		Query queryMaxRow = session.createQuery("from MessStockMove ORDER BY id DESC");
		    		queryMaxRow.setMaxResults(1);
		    		 msm = (MessStockMove) queryMaxRow.uniqueResult();
		            transaction.commit();
		            
		    } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
		            
		            hibernateException.printStackTrace();
		
		    } finally {
					HibernateUtil.closeSession();
		    }
				return msm;
		
	}
	
}
