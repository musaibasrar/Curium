package org.ideoholic.curium.model.teachersperformance.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ideoholic.curium.model.examdetails.dao.ExamDetailsDAO;
import org.ideoholic.curium.model.examdetails.dto.Exams;
import org.ideoholic.curium.model.studentdiary.dao.StudentDiaryDAO;
import org.ideoholic.curium.repositories.ExamsRepository;
import org.ideoholic.curium.util.HibernateUtil;
import org.ideoholic.curium.util.Session;
import org.ideoholic.curium.util.Session.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TeacherPerformanceDAO {
	
	
	@Autowired 
	private ExamsRepository examsRepo;

	
	@Transactional
	public List<Exams> getExamsList(int branchid) {
		List<Exams> results = new ArrayList<Exams>();
		try {
			
			results = examsRepo.findByBranchid(branchid);


		} catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
        } 
		return results;
	}

	

}
