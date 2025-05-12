package org.ideoholic.curium.model.printids.dao;

import java.util.List;

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


public Teacher printMultipleIdsEmployee(String id) {
	Teacher teacherDetails = new Teacher();
	 Session session = null;
	 Transaction transaction = null;

    
    try {
         transaction = session.beginTransaction();
                 int sid = Integer.valueOf(id);
                 Query query = session.createQuery("From Teacher as teacher where teacher.tid=" + sid);
                 teacherDetails = (Teacher) query.uniqueResult();
         transaction.commit();
     } catch (Exception hibernateException) { 
     	log.error(hibernateException.getMessage(), hibernateException);
        hibernateException.printStackTrace();
        throw hibernateException;
     } finally {
			HibernateUtil.closeSession();
		}	      
     return teacherDetails;
}

}
