package com.model.documents.dao;

import org.hibernate.query.Query;

import com.model.documents.dto.Transfercertificate;
import com.util.HibernateUtil;
import com.util.Session;
import com.util.Session.Transaction;

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

	public DocumentDAO() {
		session = HibernateUtil.openCurrentSession();
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
		} catch (Exception e) {transaction.rollback();
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
		} catch (Exception e) {transaction.rollback();
			e.printStackTrace();
		}
		return tc;
	}
	
}
