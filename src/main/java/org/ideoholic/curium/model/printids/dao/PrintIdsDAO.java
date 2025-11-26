package org.ideoholic.curium.model.printids.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.ideoholic.curium.model.employee.dto.Teacher;
import org.ideoholic.curium.model.mess.card.dto.Card;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.repositories.CardRepository;
import org.ideoholic.curium.repositories.ParentsRepository;
import org.ideoholic.curium.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PrintIdsDAO {
	

	@Autowired
	private ParentsRepository parentsRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private CardRepository cardRepo;

	@Transactional
	public Parents printMultipleIds(String id) {
		Parents parentsDetails = null;
		try {
			int sid = Integer.valueOf(id);
			parentsDetails = parentsRepository.findByStudentSid(sid).orElse(new Parents());
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return parentsDetails;
	}
	
	@Transactional	
    public boolean updateCardValidity(List<Card> cardList) {
		
		boolean result = false;
		 try {
	            for (Card card : cardList) {
	            	Optional<Card> cardDetail = cardRepo.findById(card.getSid());
	                if (cardDetail.isPresent()) {
	                    Card existingCard = cardDetail.get();
	                    existingCard.setValidfrom(card.getValidfrom());
	                    existingCard.setValidto(card.getValidto());
	                    cardRepo.save(existingCard);
	                }
	            }
	            result = true;
	        }
			
		catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return result;
	}

@Transactional
public Teacher printMultipleIdsEmployee(String id) {
	Teacher teacherDetails = new Teacher();
    try {
                 int sid = Integer.valueOf(id);
                 teacherDetails = teacherRepository.findById(sid).orElse(null);
     } catch (Exception hibernateException) { 
     	log.error(hibernateException.getMessage(), hibernateException);
        hibernateException.printStackTrace();
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
     } 	      
     return teacherDetails;
}

}
