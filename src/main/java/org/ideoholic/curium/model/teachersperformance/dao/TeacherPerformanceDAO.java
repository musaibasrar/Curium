package org.ideoholic.curium.model.teachersperformance.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ideoholic.curium.model.examdetails.dao.ExamDetailsDAO;
import org.ideoholic.curium.model.examdetails.dto.Exams;
import org.ideoholic.curium.util.HibernateUtil;
import org.ideoholic.curium.util.Session;
import org.ideoholic.curium.util.Session.Transaction;


public class TeacherPerformanceDAO {
	
	Session session;
	Transaction transaction;
	
	private static final Logger logger = LogManager.getLogger(TeacherPerformanceDAO.class);
	
	

	public TeacherPerformanceDAO() {
		session = HibernateUtil.openCurrentSession();
		
	}



	public List<Exams> getExamsList(int branchid) {
		List<Exams> results = new ArrayList<Exams>();
		try {
			// this.session =
			// HibernateUtil.getSessionFactory().openCurrentSession();
			transaction = session.beginTransaction();

			results = (List<Exams>) session.createQuery("From Exams where branchid="+branchid)
					.list();
			transaction.commit();

		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();

		} finally {
				HibernateUtil.closeSession();
			return results;
		}
	}

	

}
