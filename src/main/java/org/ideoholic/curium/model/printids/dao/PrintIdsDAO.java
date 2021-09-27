package org.ideoholic.curium.model.printids.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import org.ideoholic.curium.model.mess.card.dto.Card;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.util.DateUtil;
import org.ideoholic.curium.util.HibernateUtil;
import org.ideoholic.curium.util.Session;
import org.ideoholic.curium.util.Session.Transaction;

public class PrintIdsDAO {
	Session session = null;
	/**
	 * * Hibernate Session Variable
	 */
	Transaction transaction = null;
	/**
	 * * Hibernate Transaction Variable
	 */
	SessionFactory sessionFactory;
	
	private static final Logger logger = LogManager.getLogger(PrintIdsDAO.class);

	public PrintIdsDAO() {
		session = HibernateUtil.openCurrentSession();
	}


	public Parents printMultipleIds(String id) {
		 Parents parentsDetails = new Parents();
	        
	       try {
	            //this.session = HibernateUtil.getSessionFactory().openCurrentSession();
	            //System.out.println("In DAO with id " + personalIds);
	            transaction = session.beginTransaction();
	                    int sid = Integer.valueOf(id);
	                    Query query = session.createQuery("From Parents as parents where parents.Student.sid=" + sid);
	                    parentsDetails = (Parents) query.uniqueResult();
	            transaction.commit();
	        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
	            
	            hibernateException.printStackTrace();
	        } finally {
				HibernateUtil.closeSession();
			}	      
	        return parentsDetails;
	}
	
public boolean updateCardValidity(List<Card> cardList) {
		
		boolean result = false;
		try {
			transaction = session.beginTransaction();
			
			for (Card card : cardList) {
				
				Query query = session.createQuery("update Card set validfrom = '"+DateUtil.dateParseryyyymmdd(card.getValidfrom())+"', validto = '"+DateUtil.dateParseryyyymmdd(card.getValidto())+"' where sid="+card.getSid()+"");
				query.executeUpdate();
				
			}
			
			transaction.commit();
			result = true;
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		 }
		return result;
	}

}
