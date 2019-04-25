package com.model.documents.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.util.Session;
import com.util.Session.Transaction;
import org.hibernate.query.Query;

import com.model.documents.dto.Transfercertificate;
import com.util.HibernateUtil;

public class DocumentDAO {
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
	
	private static final Logger logger = LogManager.getLogger(DocumentDAO.class);

	public DocumentDAO() {
		session = HibernateUtil.openCurrentSession();
	}
	
	

	public String generateTransferCertificate(Transfercertificate tc) {
		String status = "false";
		try {
			transaction = session.beginTransaction();
			session.save(tc);
			transaction.commit();
			status = "true";
		} catch (Exception e) { 
			transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return status;
	}



	public Transfercertificate getTransferCertificateDetails(int studentId) {
		Transfercertificate tc = new Transfercertificate();
		
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Transfercertificate where sid="+studentId);
			tc = (Transfercertificate) query.uniqueResult(); 
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return tc;
	}
	
}
