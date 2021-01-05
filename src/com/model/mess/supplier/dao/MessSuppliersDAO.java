package com.model.mess.supplier.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import com.model.account.dto.Accountdetails;
import com.model.account.dto.Accountdetailsbalance;
import com.model.mess.stockentry.dto.MessInvoiceDetails;
import com.model.mess.supplier.dto.MessSuppliers;
import com.util.HibernateUtil;
import com.util.Session;
import com.util.Session.Transaction;

public class MessSuppliersDAO {
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
	
	private static final Logger logger = LogManager.getLogger(MessSuppliersDAO.class);
	
	public MessSuppliersDAO() {
		session = HibernateUtil.openCurrentSession();
	}

	

	public List<MessSuppliers> getSupplierDetails() {
		
        List<MessSuppliers> results = new ArrayList<MessSuppliers>();
        try {
                transaction = session.beginTransaction();
                results = (List<MessSuppliers>) session.createQuery("From MessSuppliers ms order by ms.id DESC").setCacheable(true).setCacheRegion("commonregion").list();
                transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
                
                hibernateException.printStackTrace();

        } finally {
    			HibernateUtil.closeSession();
        }
        return results;
}



	public MessSuppliers addNewSupplier(Accountdetails accountDetails, Accountdetailsbalance accountDetailsBalance, MessSuppliers messSuppliers) {
		 
		try {
	            //this.session = sessionFactory.openCurrentSession();
	            transaction = session.beginTransaction();
	            session.save(messSuppliers);
	            Query query = session.createQuery("update MessSuppliers set externalid= concat(externalid,'_"+messSuppliers.getId()+"') where id="+messSuppliers.getId());
				query.executeUpdate();
				session.save(accountDetails);
				session.save(accountDetailsBalance);
				Query queryLedgerLink = session.createQuery("update MessSuppliers set linkedledgerid= '"+accountDetails.getAccountdetailsid()+"' where id="+messSuppliers.getId());
				queryLedgerLink.executeUpdate();
				transaction.commit();
	        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
	            hibernateException.printStackTrace();
	        } finally {
	    			HibernateUtil.closeSession();
	        }
		 return messSuppliers;
	}


	public boolean deleteSuppliers(List<MessSuppliers> messList) {
        
        boolean result = false;
        List<Integer> accountIdsList = new ArrayList<Integer>();
        List<Integer> idsList = new ArrayList<Integer>();
        
        try {
            	transaction = session.beginTransaction();
            	
            	
            	
            	for (MessSuppliers messSuppliers : messList) {
            		
            		accountIdsList.add(messSuppliers.getLinkedledgerid());
            		idsList.add(messSuppliers.getId());
    	            
				}
            
            	//check invoice details 
            	
            	List<MessInvoiceDetails> messInvoiceList = new ArrayList<MessInvoiceDetails>();
               Query queryID = session.createQuery("From MessInvoiceDetails where suppliersid IN (:ids)");
               queryID.setParameterList("ids", idsList);
               messInvoiceList = queryID.list();
            	
               	
            	if(messInvoiceList.isEmpty()) {
            		
            		//Delete linked Account Details balance
                	Query queryAB = session.createQuery("delete from Accountdetailsbalance where accountdetailsid IN (:ids)");
    	            queryAB.setParameterList("ids", accountIdsList);
    	            queryAB.executeUpdate();
    	            
                //Delete linked Account Details
    	            Query queryAD = session.createQuery("delete from Accountdetails where accountdetailsid IN (:ids)");
    	            queryAD.setParameterList("ids", accountIdsList);
    	            queryAD.executeUpdate();
    	            
                //Delete Supplier
    	            Query queryS = session.createQuery("delete from MessSuppliers where id IN (:ids)");
    	            queryS.setParameterList("ids", idsList);
    	            queryS.executeUpdate();
    	            
    	            transaction.commit();
    	            
    	            result = true;
            	}
            
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
		}
        return result;
    }



	public boolean updateMultipleSuppliers(List<MessSuppliers> messList) {

        boolean result = false;
        try {
            transaction = session.beginTransaction();
            for (MessSuppliers suppliers : messList) {
                Query query = session.createQuery("update MessSuppliers set name = '"+suppliers.getName()+"', contactnumber = '"+suppliers.getContactnumber()+"', payto='"+suppliers.getPayto()+"', bankaccountno = '"+suppliers.getBankaccountno()+"', ifsccode = '"+suppliers.getIfsccode()+"', address = '"+suppliers.getAddress()+"' where id="+suppliers.getId());
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



	public MessSuppliers getMessSupplierById(Integer supplierid) {
		
        MessSuppliers result = new MessSuppliers();
        try {
                transaction = session.beginTransaction();
                Query query = session.createQuery("From MessSuppliers ms where ms.id = '"+supplierid+"'");
                result = (MessSuppliers) query.uniqueResult();
                transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
                
                hibernateException.printStackTrace();

        } finally {
    			HibernateUtil.closeSession();
        }
        return result;
}
	
}
