package com.model.user.dao;

import java.awt.List;
import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.model.employee.dto.Teacher;
import com.model.feescollection.dto.Receiptinfo;
import com.model.feesdetails.dto.Feesdetails;
import com.model.parents.dto.Parents;
import com.model.student.dto.Student;
import com.model.user.dto.Login;
import com.util.HibernateUtil;

public class UserDAO {
	 Session session = null;
	    /** * Hibernate Session Variable */
	    Transaction transaction = null;
	    /** * Hibernate Transaction Variable */
	    SessionFactory sessionFactory;
	    
	    
	    public UserDAO() {
	        sessionFactory = HibernateUtil.getSessionFactory();
	        session=HibernateUtil.openSession();
	}

	@SuppressWarnings("finally")
	public Login readUniqueObject(String userName, String password) {
        Login login = null;
        System.out.println("in readuniqueobject method");
        System.out.println("The username in DAO is: "+userName);
        System.out.println("The password in DAO is: "+password);
       try{
           System.out.println("in USERDAO");
           this.session = sessionFactory.openSession();
           transaction = session.beginTransaction();
           
           /*String sql = "SELECT * FROM User where username='"+userName+"' and password='"+password+"'";
           SQLQuery query = session.createSQLQuery(sql);
           query.addEntity(User.class);
           List login1 = (List) query.list();
           System.out.println("THE  login result from the readunique object is "+login.getUsertype());
           System.out.println("THE  login result from the readunique object is "+((java.util.List) login1).get(1));*/
           Query query = session.createQuery("FROM Login as login where login.username= :loginName and login.password= :password");
           query.setParameter("loginName", userName);
           query.setParameter("password", password);
           login = (Login) query.uniqueResult();
           transaction.commit();
           
       }catch(HibernateException hibernateException){
           System.out.println("In userdao null pointer exception"+hibernateException);
           hibernateException.printStackTrace();
       }finally{
           return login;
       }}

	public void sessionClose() {
		HibernateUtil.closeSession();
		
	}

	@SuppressWarnings({ "unchecked", "finally" })
	public int getNoOfStudents() {
		 java.util.List<Student> results = new ArrayList<Student>();
	        int noOfRecords = 0;
	        try {
	            //this.session = HibernateUtil.getSessionFactory().openCurrentSession();
	            transaction = session.beginTransaction();

	            results = (java.util.List<Student>) session.createQuery("FROM Student s where s.archive = 0").list();
	            noOfRecords = results.size();
	            System.out.println("The size of list is:::::::::::::::::::::::::::::::::::::::::: " + noOfRecords);
	            transaction.commit();



	        } catch (HibernateException hibernateException) {
	            transaction.rollback();
	            hibernateException.printStackTrace();

	        } finally {
	            //session.close();
	            return noOfRecords;
	        }
	}

	@SuppressWarnings("unchecked")
	public int getNoOfStudentsOne(String classStudying) {
		 java.util.List<Student> results = new ArrayList<Student>();
		 
	        int noOfRecords = 0;
	        try {
	            //this.session = HibernateUtil.getSessionFactory().openCurrentSession();
	            transaction = session.beginTransaction();

	            results = (java.util.List<Student>) session.createQuery("From Student s where s.classstudying LIKE '"+classStudying+" %' OR s.classstudying = '"+classStudying+"'  AND s.archive = 0").list();
	            noOfRecords = results.size();
	            transaction.commit();



	        } catch (HibernateException hibernateException) {
	            transaction.rollback();
	            hibernateException.printStackTrace();

	        } finally {
	            //session.close();
	            return noOfRecords;
	        }
	}

	public Login readPassword(String currentPassword) {
        Login login = null;
        
       try{
           this.session = sessionFactory.openSession();
           transaction = session.beginTransaction();
           
           Query query = session.createQuery("from Login as user where user.password1= :password");
           query.setParameter("password", currentPassword);
           login = (Login) query.uniqueResult();
           transaction.commit();
           System.out.println("THE  login result from the readunique object is "+login);
       }catch(HibernateException hibernateException){
           System.out.println("In userdao null pointer exception"+hibernateException);
           hibernateException.printStackTrace();
       }finally{
           return login;
       }
   }

	public Login update(Login login) {
        try {
            //this.session = sessionFactory.openCurrentSession();
            transaction = session.beginTransaction();
            session.update(login);
            transaction.commit();
            System.out.println("in add2");
        } catch (HibernateException hibernateException) {
            transaction.rollback();
            hibernateException.printStackTrace();
        } finally {
            //session.close();
            return login;
        }
   }

	@SuppressWarnings("unchecked")
	public java.util.List<Feesdetails> getListOfFeesDetails(String queryMain) {
		java.util.List<Feesdetails> feesDetails = new ArrayList<Feesdetails>();
        try {
            //this.session = HibernateUtil.getSessionFactory().openCurrentSession();

            transaction = session.beginTransaction();
            Query HQLquery = session.createQuery(queryMain);
            feesDetails = (java.util.List<Feesdetails>) HQLquery.list();
            transaction.commit();
        } catch (HibernateException hibernateException) {
            transaction.rollback();
            hibernateException.printStackTrace();
        }
        //session.close();
        return feesDetails;
	}

	@SuppressWarnings("unchecked")
	public java.util.List<Receiptinfo> getReceiptDetailsList(String queryMain) {
		java.util.List<Receiptinfo> feesDetails = new ArrayList<Receiptinfo>();
        try {
            //this.session = HibernateUtil.getSessionFactory().openCurrentSession();

            transaction = session.beginTransaction();
            Query HQLquery = session.createQuery(queryMain);
            feesDetails = (java.util.List<Receiptinfo>) HQLquery.list();
            transaction.commit();
        } catch (HibernateException hibernateException) {
            transaction.rollback();
            hibernateException.printStackTrace();
        }
        //session.close();
        return feesDetails;
	}

	public boolean addUser(Login user) {
		
		try {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
           return true;
        } catch (HibernateException hibernateException) {
            transaction.rollback();
            hibernateException.printStackTrace();
        } finally {
            session.close();
        }
		return false;
		
	}

	public Login getUserDetails(String teacherexternalid) {
		Login user = new Login();
		try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Login as login where login.username= :loginName");
            query.setParameter("loginName", teacherexternalid);
            user = (Login) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException hibernateException) {
            transaction.rollback();
            hibernateException.printStackTrace();
        } finally {
            session.close();
        }
		return user;
	}
}
