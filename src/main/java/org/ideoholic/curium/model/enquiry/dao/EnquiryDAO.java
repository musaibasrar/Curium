package org.ideoholic.curium.model.enquiry.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.ideoholic.curium.model.enquiry.dto.AdmissionEnquiry;
import org.ideoholic.curium.model.enquiry.dto.Enquiry;
import org.ideoholic.curium.repositories.AdmissionEnquiryRepository;
import org.ideoholic.curium.repositories.EnquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EnquiryDAO {

	@Autowired
	private EnquiryRepository enquiryRepo;

	@Autowired
	private AdmissionEnquiryRepository admissionEnquiryRepo;

	@Transactional
	public void create(Enquiry enquiry) {
		try {
			enquiryRepo.save(enquiry);
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();

			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}
	
	@Transactional
	public boolean add(AdmissionEnquiry admissionEnquiry) {
		boolean result = false;
		try {
			// original: Session session = HibernateUtil.openCurrentSession();
            // transaction = session.beginTransaction();
            // session.save(admissionEnquiry);
            // transaction.commit();
			admissionEnquiryRepo.save(admissionEnquiry);
            result = true;
        } catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
		return result;
		
	}
	@Transactional
	public AdmissionEnquiry getStudentLastEnquiry(int branchId) {
		AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry();
		try {
			// original:
			// Session session = HibernateUtil.openCurrentSession();
			// transaction = session.beginTransaction();
			// Query query = session.createQuery("from AdmissionEnquiry where branchid="+branchId+" order by id desc");
			// query.setMaxResults(1);
			// admissionEnquiry = (AdmissionEnquiry) query.uniqueResult();
			// transaction.commit();
			admissionEnquiry = admissionEnquiryRepo.findTopByBranchIdOrderByIdDesc(branchId);
		} catch (Exception e) { 
			log.error(e.getMessage(), e);
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return admissionEnquiry;
	}
	
	@Transactional
	public List<AdmissionEnquiry> viewEnquiryList(int branchId) {
		List<AdmissionEnquiry> results = new ArrayList<AdmissionEnquiry>();
		try {
			// original:
			// Session session = HibernateUtil.openCurrentSession();
            // transaction = session.beginTransaction();
            // results = (List<AdmissionEnquiry>) session.createQuery("From AdmissionEnquiry where branchid="+branchId).list();
            // transaction.commit();
            results = admissionEnquiryRepo.findByBranchId(branchId);
        } catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
		return results;
	}
	@Transactional
	public AdmissionEnquiry getStudentEnquiry(int id) {
	    AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry();
		try {
			// original:
			// Session session = HibernateUtil.openCurrentSession();
			// transaction = session.beginTransaction();
			// Query query = session.createQuery("from AdmissionEnquiry where id="+id);
			// admissionEnquiry = (AdmissionEnquiry) query.uniqueResult(); 
			// transaction.commit();
			admissionEnquiry = admissionEnquiryRepo.findById(id).orElse(admissionEnquiry);
		} catch (Exception e) { 
			log.error(e.getMessage(), e);
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return admissionEnquiry;
	}

	@Transactional
	public void update(AdmissionEnquiry admissionEnquiry) {
		try {
			// original:
            // Session session = HibernateUtil.openCurrentSession();
            // transaction = session.beginTransaction();
            // session.update(admissionEnquiry);
            // transaction.commit();
            admissionEnquiryRepo.save(admissionEnquiry);
        } catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
		
	}
	@Transactional
	public boolean deleteEnquiry(List<Integer> ids) {
		boolean result =false;
		try {
			// original:
			// Session session = HibernateUtil.openCurrentSession();
			// transaction = session.beginTransaction();
			// Query query = session.createQuery("delete from AdmissionEnquiry where id IN (:ids)");
			// query.setParameterList("ids", ids);
			// query.executeUpdate();
			// transaction.commit();
			admissionEnquiryRepo.deleteAllById(ids);
			result = true;
		} catch (Exception hibernateException) { 
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return result;
		
	}


}