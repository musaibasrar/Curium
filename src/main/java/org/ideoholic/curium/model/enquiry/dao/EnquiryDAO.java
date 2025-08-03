package org.ideoholic.curium.model.enquiry.dao;

import javax.transaction.Transactional;

import org.ideoholic.curium.model.enquiry.dto.Enquiry;
import org.ideoholic.curium.repositories.EnquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EnquiryDAO {

	@Autowired
	private EnquiryRepository enquiryRepo;

	@Transactional
	public void create(Enquiry enquiry) {
		try {
			enquiryRepo.save(enquiry);
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();

			throw hibernateException;
		}
	}
	
	public boolean add(AdmissionEnquiry admissionEnquiry) {
		boolean result = false;
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openCurrentSession();
            transaction = session.beginTransaction();
            session.save(admissionEnquiry);
            transaction.commit();
            result = true;
        } catch (Exception hibernateException) { transaction.rollback();
        log.error(hibernateException.getMessage(), hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
    			HibernateUtil.closeSession();
        }
		return result;
		
	}
	public AdmissionEnquiry getStudentLastEnquiry(int branchId) {
		AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry();
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("from AdmissionEnquiry where branchid="+branchId+" order by id desc");
			query.setMaxResults(1);
			admissionEnquiry = (AdmissionEnquiry) query.uniqueResult();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); log.error(e.getMessage(), e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return admissionEnquiry;
	}
	
	@SuppressWarnings({ "finally", "unchecked" })
	public List<AdmissionEnquiry> viewEnquiryList(int branchId) {
		List<AdmissionEnquiry> results = new ArrayList<AdmissionEnquiry>();
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openCurrentSession();
            transaction = session.beginTransaction();
            results = (List<AdmissionEnquiry>) session.createQuery("From AdmissionEnquiry where branchid="+branchId).list();
            transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
    			HibernateUtil.closeSession();
            return results;
        }
	}
	public AdmissionEnquiry getStudentEnquiry(int id) {
    AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry();
	Transaction transaction = null;
		try {
			Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("from AdmissionEnquiry where id="+id);
			admissionEnquiry = (AdmissionEnquiry) query.uniqueResult(); 
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); log.error(e.getMessage(), e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return admissionEnquiry;
	}
	public void update(AdmissionEnquiry admissionEnquiry) {
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openCurrentSession();
            transaction = session.beginTransaction();
            session.update(admissionEnquiry);
            transaction.commit();
        } catch (Exception hibernateException) { 
        	transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
    			HibernateUtil.closeSession();
        }
		
	}
	public boolean deleteEnquiry(List<Integer> ids) {
		boolean result =false;
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openCurrentSession();
			transaction = session.beginTransaction();
			
			
			Query query = session
					.createQuery("delete from AdmissionEnquiry where id IN (:ids)");
			query.setParameterList("ids", ids);
			
			query.executeUpdate();
			
			transaction.commit();
			result = true;
		} catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		 }
		return result;
		
	}


}
