package org.ideoholic.curium.model.documents.dao;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.ideoholic.curium.model.documents.dto.Transfercertificate;
import org.ideoholic.curium.util.HibernateUtil;
import org.ideoholic.curium.util.QueryUtil;
import org.ideoholic.curium.util.Session;
import org.ideoholic.curium.util.Session.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DocumentDAO {
	
	@Autowired
    private DocumentRepository documentRepo;

    @Autowired
    private QueryUtil queryUtil;
	
	@Transactional 
	public String generateTransferCertificate(Transfercertificate tc) {
		String status = "false";
		try {
			documentRepo.save(tc);
			status = "true";
		} catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
		}
		return status;
	}



	public Transfercertificate getTransferCertificateDetails(int studentId) {
		Transfercertificate tc = new Transfercertificate();
		Session session = HibernateUtil.openCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Transfercertificate where sid="+studentId);
			tc = (Transfercertificate) query.uniqueResult(); 
			transaction.commit();
		}  catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
		}finally {
			HibernateUtil.closeSession();
		}
		return tc;
	}
	
}
