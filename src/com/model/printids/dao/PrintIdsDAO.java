package com.model.printids.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.model.parents.dto.Parents;
import com.model.student.dto.Student;
import com.model.student.dto.Studentfeesstructure;
import com.util.HibernateUtil;

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

	public PrintIdsDAO() {
		session = HibernateUtil.openSession();
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
	        } catch (HibernateException hibernateException) {
	            transaction.rollback();
	            hibernateException.printStackTrace();
	        } 	      
	        return parentsDetails;
	}

}
