package com.model.mess.stockentry.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import com.model.degreedetails.dto.Degreedetails;
import com.model.mess.item.dto.MessItems;
import com.model.mess.stockentry.dto.MessStockEntry;
import com.model.parents.dto.Parents;
import com.model.pudetails.dto.Pudetails;
import com.model.std.dto.Classhierarchy;
import com.model.student.dto.Student;
import com.model.student.dto.Studentfeesstructure;
import com.util.HibernateUtil;
import com.util.Session;
import com.util.Session.Transaction;

public class MessStockEntryDAO {
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
	
	private static final Logger logger = LogManager.getLogger(MessStockEntryDAO.class);
	
	public MessStockEntryDAO() {
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
                results = (List<MessStockEntry>) session.createQuery("From MessStockEntry mse where mse.status != 'CANCELLED' and mse.messinvoicedetails.id = '"+invoiceDetailsId+"' and mse.messinvoicedetails.status !='CANCELLED' order by mse.id ASC").setCacheable(true).setCacheRegion("commonregion").list();
                transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
                
                hibernateException.printStackTrace();

        } finally {
    			HibernateUtil.closeSession();
        }
        return results;
}



	public List<MessStockEntry> getItemsStockEntry(int itemId) {
		
        List<MessStockEntry> results = new ArrayList<MessStockEntry>();
        try {
                transaction = session.beginTransaction();
                results = (List<MessStockEntry>) session.createQuery("From MessStockEntry mse where mse.status != 'CANCELLED' and mse.itemid = '"+itemId+"' and mse.messinvoicedetails.status !='CANCELLED' and mse.availablequantity > 0  order by mse.id ASC").setCacheable(true).setCacheRegion("commonregion").list();
                transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
                
                hibernateException.printStackTrace();

        } finally {
    			HibernateUtil.closeSession();
        }
        return results;
}
	
}
