package com.model.importfile.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.model.importfile.dto.ImportFileDTO;
import com.model.parents.dto.Parents;
import com.util.HibernateUtil;
import com.util.Session;
import com.util.Session.Transaction;

public class ImportFileDAO {
	Session session = null;
	/**
	 * * Hibernate Session Variable
	 */
	Transaction transaction = null;
	
	private static final Logger logger = LogManager.getLogger(ImportFileDAO.class);
	
	public ImportFileDAO(){
		session = HibernateUtil.openCurrentSession();

		
	}

	@SuppressWarnings("finally")
	public ImportFileDTO create(ImportFileDTO importdto) {
		 try {
	            //this.session = sessionFactory.openCurrentSession();
	            transaction = session.beginTransaction();
	            session.save(importdto);


	            transaction.commit();
	           
	        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
	            
	            hibernateException.printStackTrace();
	        } finally {
	    			HibernateUtil.closeSession();
	            return importdto;
	        }
	}

}
