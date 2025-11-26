package org.ideoholic.curium.model.teachersperformance.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.ideoholic.curium.model.examdetails.dto.Exams;
import org.ideoholic.curium.repositories.ExamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TeacherPerformanceDAO {
	
	
	@Autowired 
	private ExamsRepository examsRepo;

	
	@Transactional
	public List<Exams> getExamsList(int branchid) {
		List<Exams> results = new ArrayList<>();
		try {
			
			results = examsRepo.findByBranchid(branchid);

		} catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        } 
		return results;
	}

	

}
