package org.ideoholic.curium.model.examdetails.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.ideoholic.curium.model.examdetails.dto.Exams;
import org.ideoholic.curium.model.examdetails.dto.Examschedule;
import org.ideoholic.curium.repositories.ExamScheduleRepository;
import org.ideoholic.curium.repositories.ExamsRepository;
import org.ideoholic.curium.util.HibernateUtil;
import org.ideoholic.curium.util.Session;
import org.ideoholic.curium.util.Session.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ExamDetailsDAO {
	
	@Autowired
	private ExamsRepository examsRepo;
	
	@Autowired
	private ExamScheduleRepository examScheduleRepo;

	@Transactional
	public Exams addExams(Exams exams) {
		try {
			examsRepo.save(exams);
			log.debug("in add3:{}", exams);
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();

			throw hibernateException;
		}
		return exams;
	}


	public List<Exams> readListOfExams(int branchId) {
		List<Exams> results = new ArrayList<Exams>();
		try {
			// results = (List<Exams>) session.createQuery("From Exams where branchid="+branchId).list();
			results = examsRepo.findByBranchid(branchId);
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();

			throw hibernateException;
		}
		return results;
	}


	public void deleteMultiple(List<Integer> ids) {
		try {
			examsRepo.deleteAllById(ids);
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
	}


	public boolean addExamSchedule(List<Examschedule> examScheduleList) {
		try {
			if (examScheduleList != null) {
				examScheduleRepo.saveAll(examScheduleList);
				return true;
			}
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return false;
	}



	public List<Examschedule> readListOfExamSchedule(int branchId) {
		Session session = HibernateUtil.openCurrentSession();
		Transaction transaction = null;
		List<Examschedule> results = new ArrayList<Examschedule>();
		try {
			// this.session =
			// HibernateUtil.getSessionFactory().openCurrentSession();
			transaction = session.beginTransaction();

			results = (List<Examschedule>) session.createQuery("From Examschedule where branchid = "+branchId)
					.list();
			transaction.commit();

		} catch (Exception hibernateException) {
			transaction.rollback(); 
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();

		} finally {
				HibernateUtil.closeSession();
			return results;
		}
	}



	public void deleteExamSchedule(List<Integer> ids) {
		Session session = HibernateUtil.openCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("delete from Examschedule where idexamschedule IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
	}

	public List<Examschedule> getExamScheduleDetails(String academicYear,
			String classH, String exam, int branchId) {
		Session session = HibernateUtil.openCurrentSession();
		Transaction transaction = null;
		List<Examschedule> listExamSchedule = new ArrayList<Examschedule>();
		try {
			transaction = session.beginTransaction();
			listExamSchedule = session.createQuery("from Examschedule where classes = '"+classH+"' and academicyear = '"+academicYear+"' and examname = '"+exam+"' and branchid="+branchId+" ORDER BY date ASC").list();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); log.error(e.getMessage(), e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		
		return listExamSchedule;
	}



	public Exams getExamDetails(Integer examid) {
		Session session = HibernateUtil.openCurrentSession();
		Transaction transaction = null;
		Exams exam = null;
		try {

			transaction = session.beginTransaction();
			Query query =  session.createQuery("From Exams where id="+examid);
			exam = (Exams) query.uniqueResult();
			transaction.commit();
		} catch (Exception hibernateException) {
			transaction.rollback(); 
			log.error(hibernateException.getMessage(), hibernateException);
		} finally {
				HibernateUtil.closeSession();
			return exam;
		}
	}
	
	public List<Exams> readListOfExams(List<Integer> deeniyatExamIds, int branchId) {
		Session session = HibernateUtil.openCurrentSession();
		Transaction transaction = null;
		List<Exams> results = new ArrayList<Exams>();
		
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("From Exams where exid IN (:ids) and branchid="+branchId);
			query.setParameterList("ids", deeniyatExamIds);
			results = (List<Exams>) query.getResultList();
			transaction.commit();
			
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
			return results;
		}

	}
}
