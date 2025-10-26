package org.ideoholic.curium.model.documents.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;
import org.ideoholic.curium.model.documents.dto.StudyCertificate;
import org.ideoholic.curium.model.documents.dto.Transfercertificate;
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
	
	public boolean addHallTicketInfo(Map<Integer, String> mapOfHallTicket) {
 		boolean result = false;
 		try {
 			transaction = session.beginTransaction();
 			
 			for (Entry<Integer, String> entry : mapOfHallTicket.entrySet()) {
 	            String[] blockandSeat = entry.getValue().split(":");
 	            if(blockandSeat.length>0) {
 	            	Query queryBalanceBooksUpdate = session.createQuery("update Student set bankifsc='"+blockandSeat[0]+"',urbanrural='"+blockandSeat[1]+"' where sid="+entry.getKey());
 	 				queryBalanceBooksUpdate.executeUpdate();
 	            }
 	        }
 			transaction.commit();
 			result = true;
 		} catch (Exception e) { transaction.rollback(); logger.error(e);
 			e.printStackTrace();
 		}finally {
 			HibernateUtil.closeSession();
 		}
 		return result;
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

	public boolean add(StudyCertificate studyCertificate) {
		boolean status = false;
		try {
			transaction = session.beginTransaction();
			session.save(studyCertificate);
			transaction.commit();
			status = true;
		} catch (Exception e) { 
			transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return status;
	}



	public List<StudyCertificate> getStudentCertificateList(int branchId) {
		List<StudyCertificate> sc = new ArrayList<StudyCertificate>();
		try {
			transaction = session.beginTransaction();
			sc = session.createQuery("from StudyCertificate where branchid = "+branchId ).list();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return sc;
	}



	@SuppressWarnings("unchecked")
	public List<StudyCertificate> getListOfIssuedStudyCertificate(String[] sIds) {
		List<Integer> ids = Arrays.stream(sIds)
                .map(Integer::valueOf)
                .toList();
		List<StudyCertificate> sc = new ArrayList<StudyCertificate>();
		try {
			transaction = session.beginTransaction();
			 sc = session.createQuery(
		                "from StudyCertificate where  id in (:sIds)")
		                .setParameterList("sIds", ids)   
		                .list();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return sc;

	}

}
