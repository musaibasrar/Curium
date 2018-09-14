package com.model.documents.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.model.documents.dto.Transfercertificate;
import com.model.parents.dto.Parents;
import com.model.stampfees.dto.Academicfeesstructure;
import com.model.student.dto.Student;
import com.model.student.dto.Studentfeesstructure;
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
