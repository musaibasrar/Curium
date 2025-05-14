package org.ideoholic.curium.model.printids.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.ideoholic.curium.model.employee.dto.Teacher;
import org.ideoholic.curium.model.mess.card.dto.Card;
import org.ideoholic.curium.model.parents.dao.ParentsRepository;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.util.DateUtil;
import org.ideoholic.curium.util.HibernateUtil;
import org.ideoholic.curium.util.Session;
import org.ideoholic.curium.util.Session.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PrintIdsDAO {
	

	@Autowired
	private ParentsRepository parentsRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;

	@Transactional
	public Parents printMultipleIds(String id) {
		 Parents parentsDetails = new Parents();
			       try {
	                    int sid = Integer.valueOf(id);
	                    parentsDetails = parentsRepository.findByStudentSid(sid);
	        } catch (Exception hibernateException) { 
	        	log.error(hibernateException.getMessage(), hibernateException);
	            hibernateException.printStackTrace();
	            throw hibernateException;
	        }       
	        return parentsDetails;
	}
	
public boolean updateCardValidity(List<Card> cardList) {
	 Session session = null;
	 Transaction transaction = null;

		
		boolean result = false;
		try {
			transaction = session.beginTransaction();
			
			for (Card card : cardList) {
				
				Query query = session.createQuery("update Card set validfrom = '"+DateUtil.dateParseryyyymmdd(card.getValidfrom())+"', validto = '"+DateUtil.dateParseryyyymmdd(card.getValidto())+"' where sid="+card.getSid()+"");
				query.executeUpdate();
				
			}
			
			transaction.commit();
			result = true;
		} catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
		}finally {
			HibernateUtil.closeSession();
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
        throw hibernateException;
     } 	      
     return teacherDetails;
}

}
