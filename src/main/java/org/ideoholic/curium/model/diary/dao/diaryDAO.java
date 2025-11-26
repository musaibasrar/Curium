package org.ideoholic.curium.model.diary.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.ideoholic.curium.model.diary.dto.Diary;
import org.ideoholic.curium.repositories.DiaryRepository;
import org.ideoholic.curium.util.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class diaryDAO {
	@Autowired
    private DiaryRepository diaryRepo;

    @Transactional
	public Diary create(Diary diary) {
		try {
			diaryRepo.save(diary);

		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return diary;
	}
    @Transactional
	public  List<Diary>  readListOfObjects(int offset, int noOfRecords, int branchId) {
		List<Diary> results = new ArrayList<>();
		
        try {
			
        	Pageable pageable = PageRequest.of(offset, noOfRecords);
        	 results = diaryRepo.findByBranchid(branchId, pageable).toList();
        } catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();       
            } 
        return results;
	}
    @Transactional
	public  List<Diary>  readListOfParentObjects(int offset, int noOfRecords, int branchId, String classsec) {
		List<Diary> results = new ArrayList<>();
        try {
			Pageable pageable = PageRequest.of(offset, noOfRecords);
			results = diaryRepo.findByBranchidAndClasssec(branchId, classsec, pageable).toList();
        } catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();       
        }
        return results;
	}
	
	@Transactional
	public int getNoOfRecords(int branchId) {
		Long noOfRecords = 0L;
		try {
			noOfRecords = diaryRepo.countByBranchid(branchId);
		} catch (Exception hibernateException) {
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return noOfRecords.intValue();
	}

	@Transactional
	public void deleteRecord(List<Integer> ids) {
		
		try {
			diaryRepo.deleteAllById(ids);
			
		}catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	
	}

	@Transactional
	public Diary getMessage(long id) {
		Diary diary = new Diary();
		Long did = new Long(id);
		try {
			Optional<Diary> diarymsg = diaryRepo.findById(did.intValue());
			diary = diarymsg.orElse(null);
		} catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}finally {
			HibernateUtil.closeSession();
		 }
		return diary;
	}
	
}
