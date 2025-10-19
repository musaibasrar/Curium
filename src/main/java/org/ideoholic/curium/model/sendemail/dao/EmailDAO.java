package org.ideoholic.curium.model.sendemail.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.repositories.ParentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmailDAO {
	
	@Autowired
	private ParentsRepository parentsRepo;

	@Transactional
	public long countEmails(QUERY_TYPE queryType, String classStudying, String branchId) {
		long totalNumbers = 0;
		try {
			switch (queryType) {
			case ALL_PARENTS:
				totalNumbers = parentsRepo.countAllParentsWithEmail();
				break;
			case ALL_PARENTS_WITH_CLASS:
				totalNumbers = parentsRepo.countParentsWithEmailForGivenClass(classStudying, branchId);
			}
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			throw hibernateException;
		}
		return totalNumbers;
	}

	public List<Parents> getContactNumbers(int offset, int noOfRecords) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public List<Parents> readListOfObjectsPaginationALL(QUERY_TYPE queryType, String classStudying, String branchId, int offset, int noOfRecords) {
		List<Parents> results = new ArrayList<Parents>();

		try{
			switch (queryType) {
			case ALL_PARENTS:
				results = parentsRepo.getAllParentsWithEmail(PageRequest.of(offset, noOfRecords)).toList();
				break;
			case ALL_PARENTS_WITH_CLASS:
				results = parentsRepo.getParentsWithEmailForGivenClass(classStudying, branchId, PageRequest.of(offset, noOfRecords)).toList();
			}

		} catch (Exception hibernateException) { 
			log.error(hibernateException.getMessage(), hibernateException); 
			
			hibernateException.printStackTrace();
			throw hibernateException;
		}
		return results;
	}

	public static enum QUERY_TYPE {
		ALL_PARENTS,
		ALL_PARENTS_WITH_CLASS,
		NONE
	}
	

}
