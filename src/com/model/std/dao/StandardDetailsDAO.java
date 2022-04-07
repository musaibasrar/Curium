package com.model.std.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import com.util.Session;
import org.hibernate.SessionFactory;
import com.util.Session.Transaction;
import org.hibernate.query.Query;

import com.model.std.dto.Classsec;
import com.util.HibernateUtil;

public class StandardDetailsDAO {

	Session session = null;
    /**
     * * Hibernate Session Variable
     */
    Transaction transaction = null;
    /**
     * * Hibernate Transaction Variable
     */
    Transaction transaction1;
    SessionFactory sessionFactory;
	
	public StandardDetailsDAO() {
		session = HibernateUtil.openCurrentSession();
	}

	@SuppressWarnings("finally")
	public Classsec create(Classsec classsec) {
		 try {
	            transaction = session.beginTransaction();
	            session.save(classsec);
	            transaction.commit();
	        } catch (HibernateException hibernateException) {transaction.rollback();
	            hibernateException.printStackTrace();
	        } finally {
	            HibernateUtil.closeSession();
	            return classsec;
	        }
	}

    public List<Classsec> viewClasses(int branchId) {
        
        List<Classsec> classsecList = new ArrayList<Classsec>();
        try {
            transaction = session.beginTransaction();

            classsecList = session.createQuery("From Classsec where classdetails <> '' and branchid="+branchId).setCacheable(true).setCacheRegion("commonregion").list();

            transaction.commit();
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
            return classsecList;
        }
    }

    public void deleteMultiple(List ids) {

        try {
                transaction = session.beginTransaction();
                Query query = session.createQuery("delete from Classsec where stdrdid IN (:ids)");
                query.setParameterList("ids", ids);
                query.executeUpdate();
                transaction.commit();
        } catch (HibernateException hibernateException) {transaction.rollback();
                hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
		}


        
    }
}