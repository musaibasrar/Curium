package com.model.position.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.util.Session;
import org.hibernate.SessionFactory;
import com.util.Session.Transaction;
import org.hibernate.query.Query;

import com.model.position.dto.Position;
import com.util.HibernateUtil;

public class positionDAO {

	Session session = null;
	/**
	 * * Hibernate Session Variable
	 */
	Transaction transaction = null;
	/**
	 * * Hibernate Transaction Variable
	 */

	SessionFactory sessionFactory;
	
	private static final Logger logger = LogManager.getLogger(positionDAO.class);

	public positionDAO() {
		session = HibernateUtil.openCurrentSession();
	}

	@SuppressWarnings("finally")
	public Position create(Position position) {
		try {
            //this.session = sessionFactory.openCurrentSession();
            transaction = session.beginTransaction();
            session.save(position);


            transaction.commit();
            
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
    			HibernateUtil.closeSession();
            return position;
        }
	}

	@SuppressWarnings({ "finally", "unchecked" })
	public List<Position> readListOfObjects(int branchId) {
		
		List<Position> results = new ArrayList<Position>();
        try {
            
            transaction = session.beginTransaction();
            results = (List<Position>) session.createQuery("From Position where branchid = "+branchId).list();
            transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
    			HibernateUtil.closeSession();
            return results;
        }
	}

	public void deleteMultiple(List ids) {
		try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from Position where positionid IN (:ids)");
            query.setParameterList("ids", ids);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
            hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
		}
		
	}

}
