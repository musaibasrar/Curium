package com.model.documents.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
		session = HibernateUtil.openSession();
	}
	
	

	public String generateTransferCertificate(Transfercertificate tc) {
		
		try {
			Transfercertificate transferCertificate = getTransferCertificateDetails(tc.getSid()); 
			if(transferCertificate != null){
				return "studentexists";
			}
			transaction = session.beginTransaction();
			session.save(tc);
			transaction.commit();
			return "true";
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}
		return "false";
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
		}
		return tc;
	}
	
}
