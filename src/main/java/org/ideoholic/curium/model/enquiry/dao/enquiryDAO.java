package org.ideoholic.curium.model.enquiry.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.ideoholic.curium.model.diary.dto.Diary;
import org.ideoholic.curium.model.enquiry.dto.Enquiry;
import org.ideoholic.curium.util.HibernateUtil;
import org.ideoholic.curium.util.Session;
import org.ideoholic.curium.util.Session.Transaction;

public class enquiryDAO {
	Session session = null;
    /**
     * * Hibernate Session Variable
     */
    Transaction transaction = null;
    /**
     * * Hibernate Transaction Variable
     */
  
    SessionFactory sessionFactory;
    private static final Logger logger = LogManager.getLogger(enquiryDAO.class);
    
    public enquiryDAO() {
		session = HibernateUtil.openCurrentSession();
	}
    @SuppressWarnings("finally")
	public Enquiry create(Enquiry enquiry ) {
		// TODO Auto-generated method stub
		try {
            //this.session = sessionFactory.openCurrentSession();
            transaction = session.beginTransaction();
            session.save(enquiry);
            transaction.commit();
            
        } catch (Exception hibernateException) { transaction.rollback();
        logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
    			HibernateUtil.closeSession();
            return enquiry;
        }
	}

}
