package org.ideoholic.curium.model.enquiry.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.ideoholic.curium.model.diary.dto.Diary;
import org.ideoholic.curium.model.enquiry.dto.AdmissionEnquiry;
import org.ideoholic.curium.model.enquiry.dto.Enquiry;
import org.ideoholic.curium.util.HibernateUtil;
import org.ideoholic.curium.util.Session;
import org.ideoholic.curium.util.Session.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class enquiryDAO {
	Session session = null;
    /**
     * * Hibernate Session Variable
     */
    Transaction transaction = null;
    /**
     * * Hibernate Transaction Variable
     */
  
    SessionFactory sessionFactory;
    private static final Logger logger = LogManager.getLogger(enquiryDAO.class);
    
    public enquiryDAO() {
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
	public boolean add(AdmissionEnquiry admissionEnquiry) {
		boolean result = false;
		try {
            transaction = session.beginTransaction();
            session.save(admissionEnquiry);
            transaction.commit();
            result = true;
        } catch (Exception hibernateException) { transaction.rollback();
        logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
    			HibernateUtil.closeSession();
        }
		return result;
		
	}
	public AdmissionEnquiry getStudentLastEnquiry(int branchId) {
AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry();
		
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from AdmissionEnquiry where branchid="+branchId+" order by id desc");
			query.setMaxResults(1);
			admissionEnquiry = (AdmissionEnquiry) query.uniqueResult();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return admissionEnquiry;
	}
	
	@SuppressWarnings({ "finally", "unchecked" })
	public List<AdmissionEnquiry> viewEnquiryList(int branchId) {
		List<AdmissionEnquiry> results = new ArrayList<AdmissionEnquiry>();
        try {
            
            transaction = session.beginTransaction();
            results = (List<AdmissionEnquiry>) session.createQuery("From AdmissionEnquiry where branchid="+branchId).list();
            transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
    			HibernateUtil.closeSession();
            return results;
        }
	}
	public AdmissionEnquiry getStudentEnquiry(int id) {
    AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry();
		
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from AdmissionEnquiry where id="+id);
			admissionEnquiry = (AdmissionEnquiry) query.uniqueResult(); 
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return admissionEnquiry;
	}
	public void update(AdmissionEnquiry admissionEnquiry) {

		try {
            transaction = session.beginTransaction();
            session.update(admissionEnquiry);
            transaction.commit();
        } catch (Exception hibernateException) { 
        	transaction.rollback(); logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
    			HibernateUtil.closeSession();
        }
		
	}
	public boolean deleteEnquiry(List<Integer> ids) {
		boolean result =false;
		try {
			transaction = session.beginTransaction();
			
			
			Query query = session
					.createQuery("delete from AdmissionEnquiry where id IN (:ids)");
			query.setParameterList("ids", ids);
			
			query.executeUpdate();
			
			transaction.commit();
			result = true;
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		 }
		return result;
		
	}

}
