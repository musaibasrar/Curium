package org.ideoholic.curium.model.enquiry.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.ideoholic.curium.model.enquiry.dto.Enquiry;
import org.ideoholic.curium.model.task.dto.Task;
import org.ideoholic.curium.util.HibernateUtil;
import org.ideoholic.curium.util.Session;
import org.ideoholic.curium.util.Session.Transaction;

public class EnquiryDAO {
	Session session = null;
    /**
     * * Hibernate Session Variable
     */
    Transaction transaction = null;
    /**
     * * Hibernate Transaction Variable
     */
  
    SessionFactory sessionFactory;
    private static final Logger logger = LogManager.getLogger(EnquiryDAO.class);
    
    public EnquiryDAO() {
		session = HibernateUtil.openCurrentSession();
	}
    @SuppressWarnings("finally")
	public Enquiry create(Enquiry enquiry ) {
		// TODO Auto-generated method stub
		try {
            //this.session = sessionFactory.openCurrentSession();
            transaction = session.beginTransaction();
            session.save(enquiry);
            transaction.commit();
            
        } catch (Exception hibernateException) { transaction.rollback();
        logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
    			HibernateUtil.closeSession();
            return enquiry;
        }
	}
    
    
    public List<Enquiry> readListOfObjectsPaginationEnquiry(int offset, int noOfRecords, int branchId) {
		
		List<Enquiry> results = new ArrayList<Enquiry>();

		try {
			
			transaction = session.beginTransaction();
			Query query = session.createQuery("From Enquiry as query where query.branchid = "+branchId+" order by query.id desc").setCacheable(true).setCacheRegion("commonregion");
			query.setFirstResult(offset);   
			query.setMaxResults(noOfRecords);
			results = query.getResultList();
			transaction.commit();
			

		} catch (Exception hibernateException) {  transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();

		} finally {
				HibernateUtil.closeSession();
			return results;
		}
	}
    
    public int getNoOfRecordsEnquiry(int branchId) {
		List<Enquiry> results = new ArrayList<Enquiry>();
		int noOfRecords = 0;
		try {
			// this.session =
			// HibernateUtil.getSessionFactory().openCurrentSession();
			transaction = session.beginTransaction();

			results = (List<Enquiry>) session.createQuery("From Enquiry where branchid="+branchId).setCacheable(true).setCacheRegion("commonregion")
					.list();
			noOfRecords = results.size();
			transaction.commit();

		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();

		} finally {
				HibernateUtil.closeSession();
			return noOfRecords;
		}
	}
    
	public boolean updateStatus(List<Integer> enquiryIdsList, int userId, String status) {
		
		boolean result = false;
		
		try {
			transaction = session.beginTransaction();
			
			for (Integer eId : enquiryIdsList) {
				Query enquiry = session.createQuery("update Enquiry set status = '"+status+"',userid= "+userId+" where id="+eId+"");
				enquiry.executeUpdate();
			}
			
			transaction.commit();
			result=true;
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		 }
		return result;
	}

}
