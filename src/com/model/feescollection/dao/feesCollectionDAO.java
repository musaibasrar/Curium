/**
 * 
 */
package com.model.feescollection.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.model.employee.dto.Teacher;
import com.model.feescollection.dto.Feescollection;
import com.util.HibernateUtil;

/**
 * @author Musaib_2
 *
 */
public class feesCollectionDAO {
	Session session = null;
    Transaction transaction = null;
    Transaction transaction1;
    SessionFactory sessionFactory;

	public feesCollectionDAO() {
		session = HibernateUtil.openSession();
	}

	@SuppressWarnings("finally")
	public Feescollection create(Feescollection feescollection) {
		 try {
	            //this.session = sessionFactory.openCurrentSession();
	            transaction = session.beginTransaction();
	            session.save(feescollection);


	            transaction.commit();
	            System.out.println("in add3");
	        } catch (HibernateException hibernateException) {
	            transaction.rollback();
	            hibernateException.printStackTrace();
	        } finally {
	            session.close();
	            return feescollection;
	        }
	}

	@SuppressWarnings({ "unchecked", "finally" })
	public List<Feescollection> readListOfObject(Integer feeid) {

		List<Feescollection> results = new ArrayList<Feescollection>();
		try {

			transaction = session.beginTransaction();
			results = (List<Feescollection>) session.createQuery("From Feescollection where feesdetailsid="+feeid).list();
			
			transaction.commit();
		} catch (HibernateException hibernateException) {
			transaction.rollback();
			hibernateException.printStackTrace();
		} finally {
			// session.close();
			return results;
		}
	}

}
