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

	@Transactional
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

	@Transactional
	public void deleteMultiple(List<Integer> ids) {
		try {
			examsRepo.deleteAllById(ids);
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();

			throw hibernateException;
		}
	}

	@Transactional
	public boolean addExamSchedule(List<Examschedule> examScheduleList) {
		try {
			if (examScheduleList != null) {
				examScheduleRepo.saveAll(examScheduleList);
				return true;
			}
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();

			throw hibernateException;
		}
		return false;
	}


	@Transactional
	public List<Examschedule> readListOfExamSchedule(int branchId) {
		List<Examschedule> results = new ArrayList<Examschedule>();
		try {
			// results = (List<Examschedule>) session.createQuery("From Examschedule where branchid = "+branchId).list();
			results = examScheduleRepo.findByBranchid(branchId);
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();

			throw hibernateException;
		}
		return results;
	}


	@Transactional
	public void deleteExamSchedule(List<Integer> ids) {
		try {
			// Query query = session.createQuery("delete from Examschedule where idexamschedule IN (:ids)");
			examScheduleRepo.deleteAllById(ids);
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();

			throw hibernateException;
		}
	}

	@Transactional
	public List<Examschedule> getExamScheduleDetails(String academicYear, String classH, String exam, int branchId) {
		List<Examschedule> listExamSchedule = new ArrayList<Examschedule>();
		try {
			// listExamSchedule = session.createQuery("from Examschedule where classes = '"+classH+"' and academicyear = '"+academicYear+"' and examname = '"+exam+"' and branchid="+branchId+" ORDER BY date ASC").list();
		    listExamSchedule = examScheduleRepo.findByClassesAndAcademicyearAndExamnameAndBranchidOrderByDateAsc(classH, academicYear, exam, branchId);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
			
			throw e;
		}
		
		return listExamSchedule;
	}


	@Transactional
	public Exams getExamDetails(Integer examid) {
		Exams exam = null;
		try {
			// Query query =  session.createQuery("From Exams where id="+examid);
			exam = examsRepo.findById(examid).orElse(new Exams());
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();

			throw hibernateException;
		}
		return exam;
	}
	
	@Transactional
	public List<Exams> readListOfExams(List<Integer> examIds, int branchId) {
		List<Exams> results = new ArrayList<Exams>();

		try {
			// Query query = session.createQuery("From Exams where exid IN (:ids) and branchid="+branchId);
			results = examsRepo.findByExidInAndBranchid(examIds, branchId);
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();

			throw hibernateException;
		}
		return results;
	}
}
