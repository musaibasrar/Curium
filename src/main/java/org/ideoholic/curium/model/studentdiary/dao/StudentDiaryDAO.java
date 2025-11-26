package org.ideoholic.curium.model.studentdiary.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.ideoholic.curium.model.studentdiary.dto.StudentDiary;
import org.ideoholic.curium.model.studentdiary.dto.StudentDiaryProjection;
import org.ideoholic.curium.repositories.StudentDiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class StudentDiaryDAO {
	
	
	@Autowired
	private StudentDiaryRepository studentDiaryRepo;

	@Transactional
	public StudentDiary create(StudentDiary diary) {
		try {
			studentDiaryRepo.save(diary);
            
        }catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        } 
		 return diary;
	}
	
	@Transactional
	public  List<StudentDiaryProjection>  readListOfObjects(int offset, int noOfRecords, int branchId) {
	    List<StudentDiaryProjection> results = new ArrayList<>();
        try {
        	
        	Pageable pageable = PageRequest.of(offset / noOfRecords, noOfRecords); // Page index is zero-based
        	results = studentDiaryRepo.findDiaryByBranchId(branchId, pageable);
        }catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();       
        }
        return results;
	}
 
	@Transactional
	public  List<StudentDiaryProjection>  readListOfParentObjects(int offset, int noOfRecords, int branchId, int sid) {
	    List<StudentDiaryProjection> results = new ArrayList<>();
        try {
        	
        	Pageable pageable = PageRequest.of(offset, noOfRecords);
			results = studentDiaryRepo.findByBranchIdAndSid(branchId, sid, pageable);
        } catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();       
        }
        return results;
	}
	
	@Transactional
	public int getNoOfRecords(int branchId, int sid) {
		List<StudentDiary> results = new ArrayList<StudentDiary>();
		int noOfRecords = 0;
		try {
			noOfRecords = studentDiaryRepo.countByBranchidAndSid(branchId, sid);

		} catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();   

		} 
		return noOfRecords;
	}
	
	 @Transactional
	public int getNoOfRecords(int branchId) {
		int noOfRecords = 0;
		try {
			noOfRecords = studentDiaryRepo.countByBranchid(branchId);

		}catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return noOfRecords;
	}

	 @Transactional
	public void deleteRecord(List<Integer> ids) {
		try {
			studentDiaryRepo.deleteAllById(ids);
			
		}catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

	 @Transactional
	public StudentDiary getMessage(long id) {
		StudentDiary diary = new StudentDiary();
		Long did = new Long(id);
		try {
			Optional<StudentDiary> diarymsg = studentDiaryRepo.findById(did.intValue());
			diary = diarymsg.orElse(null);
		}  catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return diary;
	}

}
