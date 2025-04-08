package org.ideoholic.curium.model.enquiry.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.ideoholic.curium.model.documents.dto.Transfercertificate;
import org.ideoholic.curium.model.enquiry.dto.AdmissionEnquiry;
import org.ideoholic.curium.model.enquiry.dto.Enquiry;
import org.ideoholic.curium.model.feescategory.dto.Feescategory;
import org.ideoholic.curium.util.HibernateUtil;
import org.ideoholic.curium.util.Session;
import org.ideoholic.curium.util.Session.Transaction;

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
		try {
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
    
	public void add(AdmissionEnquiry admissionEnquiry) {
		try {
            transaction = session.beginTransaction();
            session.save(admissionEnquiry);
            transaction.commit();
            
        } catch (Exception hibernateException) { transaction.rollback();
        logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
    			HibernateUtil.closeSession();
        }
	}
	
	@SuppressWarnings({ "finally", "unchecked" })
	public List<AdmissionEnquiry> viewEnquiryList() {
		List<AdmissionEnquiry> results = new ArrayList<AdmissionEnquiry>();
        try {
            
            transaction = session.beginTransaction();
            results = (List<AdmissionEnquiry>) session.createQuery("From AdmissionEnquiry").list();
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

}
