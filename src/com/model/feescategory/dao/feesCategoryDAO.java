package com.model.feescategory.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.util.Session;
import org.hibernate.SessionFactory;
import com.util.Session.Transaction;
import org.hibernate.query.Query;

import com.model.feescategory.dto.Feescategory;
import com.model.feescollection.dto.Feescollection;
import com.util.HibernateUtil;

public class feesCategoryDAO {
	Session session = null;
    /**
     * * Hibernate Session Variable
     */
    Transaction transaction = null;
    /**
     * * Hibernate Transaction Variable
     */
  
    SessionFactory sessionFactory;
    
    private static final Logger logger = LogManager.getLogger(feesCategoryDAO.class);
    

	public feesCategoryDAO() {
		session = HibernateUtil.openCurrentSession();
	}

	@SuppressWarnings({ "finally", "unchecked" })
	public List<Feescategory> readListOfObjects(int branchId) {
		
		List<Feescategory> results = new ArrayList<Feescategory>();
        try {
            
            transaction = session.beginTransaction();
            results = (List<Feescategory>) session.createQuery("From Feescategory where branchid="+branchId).list();
            transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
    			HibernateUtil.closeSession();
            return results;
        }
	}

	@SuppressWarnings("finally")
	public Feescategory create(Feescategory feescategory) {
		try {
            //this.session = sessionFactory.openCurrentSession();
            transaction = session.beginTransaction();
            session.save(feescategory);


            transaction.commit();
            
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
    			HibernateUtil.closeSession();
            return feescategory;
        }
	}

	public void deleteMultiple(List ids) {
		try {
			transaction = session.beginTransaction();
			
			
			Query query = session
					.createQuery("delete from Feescategory as fess where fess.idfeescategory IN (:ids)");
			query.setParameterList("ids", ids);
			
			query.executeUpdate();
			
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}

	}

	public void deleteFeesCategory(List ids, List feesCatId, String sid) {
		
		List<Feescollection> feesCollection = new ArrayList<Feescollection>();
		try {
			transaction = session.beginTransaction();
			Query queryOne = session.createQuery("from Feescollection as feescollection where feescollection.sid = '"+sid+"' and feescollection.sfsid IN (:ids)");
			queryOne.setParameterList("ids", ids);
			feesCollection = queryOne.list();
			
			if(feesCollection.isEmpty()){
				Query query = session.createQuery("delete from Studentfeesstructure as fees where fees.sid = "+sid+" and fees.Feescategory.idfeescategory IN (:feescat)");
				query.setParameterList("feescat", feesCatId);
				query.executeUpdate();
			}
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
	}

}
