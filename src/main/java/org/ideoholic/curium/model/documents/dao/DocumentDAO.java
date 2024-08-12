package org.ideoholic.curium.model.documents.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;
import org.ideoholic.curium.model.documents.dto.StudyCertificate;
import org.ideoholic.curium.model.documents.dto.Transfercertificate;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.util.HibernateUtil;
import org.ideoholic.curium.util.Session;
import org.ideoholic.curium.util.Session.Transaction;

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



	public List<Transfercertificate> getTCertificateDetails() {
		List<Transfercertificate> tc = new ArrayList<Transfercertificate>();
		try {
			transaction = session.beginTransaction();
			tc = session.createQuery("from Transfercertificate").list();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return tc;
	}



	public List<Parents> getListofStudentDetail(List<Integer> sid) {
		List<Parents> results = new ArrayList<Parents>();
		//Parents parents = new Parents();
		try {
			
			transaction = session.beginTransaction();
            Query<Parents> query = session.createQuery("from Parents as parents where parents.Student.sid IN (:ids)");
            query.setParameterList("ids", sid); 
            results = query.list();
            transaction.commit();	
			
			/*
			 * transaction = session.beginTransaction();
			 * Query query = session
			 * .createQuery("from Parents as parents where parents.Student.sid IN (:ids)");
			 * query.setParameterList("ids", sid); results = query.list();
			 * 
			 * transaction.commit();
			 */
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return results;
	}
	
	
	public Transfercertificate getTCLastRow() {
		Transfercertificate last = new Transfercertificate();
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Transfercertificate order by tcid DESC");
			query.setMaxResults(1);
			last = (Transfercertificate) query.uniqueResult();
			
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return last;
	}



	public StudyCertificate getSTLastRow() {
		StudyCertificate last = new StudyCertificate();
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from StudyCertificate order by stid DESC");
			query.setMaxResults(1);
			last = (StudyCertificate) query.uniqueResult();
			
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return last;
	}



	public String saveStudyCertificate(StudyCertificate studyCert) {
		String status = "false";
		try {
			transaction = session.beginTransaction();
			session.save(studyCert);
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



	public String updateTransferCertificate(Transfercertificate tc) {
		String result = "false";
		try {
			transaction = session.beginTransaction();
			Query queryUpdate = session 
					.createSQLQuery("update transfercertificate set noofissues = '"+tc.getNoofissues()+"' where sid = '"+tc.getSid()+"'");
			queryUpdate.executeUpdate();
			transaction.commit();
			result = "true";
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		 }
		return result;
	}
	
}
