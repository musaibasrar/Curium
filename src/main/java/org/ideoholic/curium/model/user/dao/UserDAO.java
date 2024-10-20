package org.ideoholic.curium.model.user.dao;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.ideoholic.curium.util.Session;
import org.hibernate.SessionFactory;
import org.ideoholic.curium.util.Session.Transaction;
import org.hibernate.query.Query;
import org.ideoholic.curium.model.feescollection.dto.Otherreceiptinfo;
import org.ideoholic.curium.model.feescollection.dto.Receiptinfo;
import org.ideoholic.curium.model.feesdetails.dto.Feesdetails;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.model.user.dto.Login;
import org.ideoholic.curium.util.HibernateUtil;

public class UserDAO {
	 Session session = null;
	    /** * Hibernate Session Variable */
	    Transaction transaction = null;
	    /** * Hibernate Transaction Variable */
	    SessionFactory sessionFacto;
	    
	    private static final Logger logger = LogManager.getLogger(UserDAO.class);
	    
	    public UserDAO() {
	    	//Musaib
	        //sessionFactory = HibernateUtil.getSessionFactory();
	        session=HibernateUtil.openCurrentSession();
	}

	@SuppressWarnings("finally")
	public Login readUniqueObject(String userName, String password) {
        Login login = null;
       try{
           transaction = session.beginTransaction();
           Query query = session.createQuery("FROM Login as login where login.username= :loginName and login.password= :password");
           query.setParameter("loginName", userName);
           query.setParameter("password", password);
           login = (Login) query.uniqueResult();
           transaction.commit();
           
       }catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
           System.out.println("In userdao null pointer exception"+hibernateException);
           hibernateException.printStackTrace();
       }finally{
   			HibernateUtil.closeSession();
           return login;
       }}

	public void sessionClose() {
		HibernateUtil.closeSession();
		
	}

	@SuppressWarnings({ "unchecked", "finally" })
	public int getNoOfStudents(int branchId) {
		 java.util.List<Student> results = new ArrayList<Student>();
	        int noOfRecords = 0;
	        try {
	            transaction = session.beginTransaction();

	            results = (java.util.List<Student>) session.createQuery("FROM Student s where s.archive = 0 AND s.branchid="+branchId).list();
	            noOfRecords = results.size();
	            transaction.commit();

	        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
	            
	            hibernateException.printStackTrace();

	        } finally {
	    			HibernateUtil.closeSession();
	            return noOfRecords;
	        }
	}

	@SuppressWarnings("unchecked")
	public int getNoOfStudentsOne(String classStudying, int branchId) {
		 java.util.List<Student> results = new ArrayList<Student>();
		 
	        int noOfRecords = 0;
	        try {
	            //this.session = HibernateUtil.getSessionFactory().openCurrentSession();
	            transaction = session.beginTransaction();

	            results = (java.util.List<Student>) session.createQuery("From Student s where s.classstudying LIKE '"+classStudying+" %' OR s.classstudying = '"+classStudying+"'  AND s.archive = 0 AND s.branchid="+branchId+"").list();
	            noOfRecords = results.size();
	            transaction.commit();



	        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
	            
	            hibernateException.printStackTrace();

	        } finally {
	    			HibernateUtil.closeSession();
	            return noOfRecords;
	        }
	}

	public Login readPassword(String currentPassword) {
        Login login = null;
        
       try{
          // this.session = sessionFactory.openSession();
           transaction = session.beginTransaction();
           
           Query query = session.createQuery("from Login as user where user.password= :password");
           query.setParameter("password", currentPassword);
           login = (Login) query.uniqueResult();
           transaction.commit();
       }catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
           hibernateException.printStackTrace();
       }finally{
   			HibernateUtil.closeSession();
           return login;
       }
   }

	public Login update(Login login) {
        try {
            transaction = session.beginTransaction();
            session.update(login);
            transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
    			HibernateUtil.closeSession();
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
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        }
        finally {
			HibernateUtil.closeSession();
		 }
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
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        }
        finally {
			HibernateUtil.closeSession();
		 }
        return feesDetails;
	}

	public boolean addUser(Login user) {
		
		try {
            transaction = session.beginTransaction();
            session.save(user);
            
            Query query = session.createQuery("from Login order by userid DESC");
            query.setMaxResults(1);
            Login last = (Login) query.uniqueResult();
            int userid = last.getUserid()+1;
            Query queryUpdate = session 
					.createSQLQuery("update login set userid = "+userid+" where username = '"+user.getUsername()+"'");
			queryUpdate.executeUpdate();
            transaction.commit();
           return true;
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
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
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
			HibernateUtil.closeSession();
		 }
		return user;
	}
	
	public Login getLoginDetails(String userName, int branchId) {
        Login login = null;
       try{
           transaction = session.beginTransaction();
           Query query = session.createQuery("FROM Login as login where login.branch='"+branchId+"' and login.username='"+userName+"'");
           login = (Login) query.uniqueResult();
           transaction.commit();
           
       }catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
           hibernateException.printStackTrace();
       }finally{
   			HibernateUtil.closeSession();
           return login;
       }}

	public Login getUniqueObject(int userid) {
        Login login = null;
       try{
           transaction = session.beginTransaction();
           Query query = session.createQuery("FROM Login as login where login.userid= :userId");
           query.setParameter("userId", userid);
           login = (Login) query.uniqueResult();
           transaction.commit();
           
       }catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
           System.out.println("In userdao null pointer exception"+hibernateException);
           hibernateException.printStackTrace();
       }finally{
   			HibernateUtil.closeSession();
           return login;
       }}
	
	@SuppressWarnings("unchecked")
	public java.util.List<Otherreceiptinfo> getOtherReceiptDetailsList(String queryMain) {
		java.util.List<Otherreceiptinfo> feesDetails = new ArrayList<Otherreceiptinfo>();
        try {
            //this.session = HibernateUtil.getSessionFactory().openCurrentSession();

            transaction = session.beginTransaction();
            Query HQLquery = session.createQuery(queryMain);
            feesDetails = (java.util.List<Otherreceiptinfo>) HQLquery.list();
            transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        }
        finally {
			HibernateUtil.closeSession();
		 }
        return feesDetails;
	}
}
