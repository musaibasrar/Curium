package com.model.user.dao;

import java.awt.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
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
	    	//Musaib
	        //sessionFactory = HibernateUtil.getSessionFactory();
	        session=HibernateUtil.openSession();
	}

	@SuppressWarnings("finally")
	public Login readUniqueObject(String userName, String password) {
        Login login = null;
       try{
           System.out.println("in USERDAO");
           //Musaib
           //this.session = sessionFactory.openSession();
           transaction = session.beginTransaction();
           Query query = session.createQuery("FROM Login as login where login.username= :loginName and login.password= :password");
           query.setParameter("loginName", userName);
           query.setParameter("password", password);
           login = (Login) query.uniqueResult();
           transaction.commit();
           
       }catch(Exception hibernateException){
           System.out.println("In userdao null pointer exception"+hibernateException);
           hibernateException.printStackTrace();
       }finally{
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

	        } catch (HibernateException hibernateException) {
	            transaction.rollback();
	            hibernateException.printStackTrace();

	        } finally {
	            //session.close();
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
          // this.session = sessionFactory.openSession();
           transaction = session.beginTransaction();
           
           Query query = session.createQuery("from Login as user where user.password= :password");
           query.setParameter("password", currentPassword);
           login = (Login) query.uniqueResult();
           transaction.commit();
       }catch(HibernateException hibernateException){
           hibernateException.printStackTrace();
       }finally{
           return login;
       }
   }

	public Login update(Login login) {
        try {
            transaction = session.beginTransaction();
            session.update(login);
            transaction.commit();
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

    public java.util.List<Login> readListOfUsers() {
        
        java.util.List<Login> loginList = new ArrayList<Login>();
        try {
            transaction = session.beginTransaction();
            loginList = session.createQuery("FROM Login").list();
            transaction.commit();
        } catch (HibernateException hibernateException) {
            transaction.rollback();
            hibernateException.printStackTrace();
        } finally {
            session.close();
        }
        return loginList;
    }

    public boolean updateMultipleUsers(Map<Integer, java.util.List<String>> userMap) {
        boolean result = false;
        
        try{
            transaction = session.beginTransaction();
            
            for (Entry<Integer, java.util.List<String>> entry : userMap.entrySet()) {
                Query query = session.createQuery("update Login set username='"+entry.getValue().get(1)+"' ,password= '"+entry.getValue().get(2)+"', usertype='"+entry.getValue().get(3)+"' , branchid='"+Integer.parseInt(entry.getValue().get(0))+"' , addstudentflag='"+entry.getValue().get(4)+"', lastmodifiedby='"+entry.getValue().get(5)+"' where lid="+entry.getKey()+"");
                query.executeUpdate();
            }
            transaction.commit();
            result = true;
        }catch(HibernateException hibernateException){
            hibernateException.printStackTrace();
        }finally{
            session.close();
        }
        
        return result;
    }

    public boolean deleteMultipleUsers(java.util.List<Integer> ids) {
        boolean result = false;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from Login where lid IN (:ids)");
            query.setParameterList("ids", ids);
            query.executeUpdate();
            transaction.commit();
            result = true;
        } catch (HibernateException hibernateException) {
            hibernateException.printStackTrace();
        }
        return result;
    }

    public boolean pauseAllUsers() {
        boolean result = false;
        
        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("update Login set addstudentflag=0");
            query.executeUpdate();
            transaction.commit();
            result = true;
        }catch(HibernateException hibernateException){
            hibernateException.printStackTrace();
        }finally{
            session.close();
        }
        
        return result;
    }

    public boolean resumeAllUsers() {
        boolean result = false;
        
        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("update Login set addstudentflag=1");
            query.executeUpdate();
            transaction.commit();
            result = true;
        }catch(HibernateException hibernateException){
            hibernateException.printStackTrace();
        }finally{
            session.close();
        }
        
        return result;
    }
}
