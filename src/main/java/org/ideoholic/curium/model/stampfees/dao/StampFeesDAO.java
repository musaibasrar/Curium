package org.ideoholic.curium.model.stampfees.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.ideoholic.curium.util.Session;
import org.hibernate.SessionFactory;
import org.ideoholic.curium.util.Session.Transaction;
import org.hibernate.query.Query;

import org.ideoholic.curium.model.account.dto.VoucherEntrytransactions;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.stampfees.dto.Academicfeesstructure;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.model.student.dto.Studentfeesstructure;
import org.ideoholic.curium.model.user.dto.Login;
import org.ideoholic.curium.util.HibernateUtil;

public class StampFeesDAO {
	 Session session = null;
	    /** * Hibernate Session Variable */
	    Transaction transaction = null;
	    /** * Hibernate Transaction Variable */
	    SessionFactory sessionFactory;
	    
	    private static final Logger logger = LogManager.getLogger(StampFeesDAO.class);
	    
	    public StampFeesDAO() {
	        sessionFactory = HibernateUtil.getSessionFactory();
	        session=HibernateUtil.openCurrentSession();
	}

	@SuppressWarnings("finally")
	public Login readUniqueObject(String userName, String password) {
        Login login = null;
       try{
           transaction = session.beginTransaction();
           
           /*String sql = "SELECT * FROM User where username='"+userName+"' and password='"+password+"'";
           SQLQuery query = session.createSQLQuery(sql);
           query.addEntity(User.class);
           List login1 = (List) query.list();
           System.out.println("THE  login result from the readunique object is "+login.getUsertype());
           System.out.println("THE  login result from the readunique object is "+((java.util.List) login1).get(1));*/
           Query query = session.createQuery("FROM Login as login where login.username1= :loginName and login.password1= :password");
           query.setParameter("loginName", userName);
           query.setParameter("password", password);
           login = (Login) query.uniqueResult();
           transaction.commit();
           
       }catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
           System.out.println(hibernateException);
       }finally{
   			HibernateUtil.closeSession();
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
	            logger.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: " + noOfRecords);
	            transaction.commit();



	        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
	            
	            hibernateException.printStackTrace();

	        } finally {
	    			HibernateUtil.closeSession();
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

	            results = (java.util.List<Student>) session.createQuery("From Student s where s.classstudying LIKE '"+classStudying+" %' AND s.archive = 0").list();
	            noOfRecords = results.size();
	            System.out.println("Total Number of students:::::::::::::::::::::::::::::::::::::::::: " + noOfRecords);
	            transaction.commit();



	        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
	            
	            hibernateException.printStackTrace();

	        } finally {
	    			HibernateUtil.closeSession();
	            return noOfRecords;
	        }
	}

	public java.util.List<Parents> getListOfStudents(String query) {
		java.util.List<Parents> parents = new ArrayList<Parents>();
        try {
            //this.session = HibernateUtil.getSessionFactory().openCurrentSession();

            transaction = session.beginTransaction();
            Query HQLquery = session.createQuery(query);
            parents = (java.util.List<Parents>) HQLquery.list();
            transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
		}
        return parents;
	}

	public void addStampFees(
			java.util.List<Academicfeesstructure> listOfacademicfessstructure, String currentYear, List<Studentfeesstructure> listOfstudentfeesstructure, VoucherEntrytransactions transactions, String updateDrAccount, String updateCrAccount) {
		try {
			// this.session = sessionFactory.openCurrentSession();
			transaction = session.beginTransaction();
			
			for (Academicfeesstructure academicfeesStructure : listOfacademicfessstructure) {
				Query query = session.createQuery("from Academicfeesstructure afs where afs.sid = '"+academicfeesStructure.getSid()+"' and afs.academicyear = '"+currentYear+"'");
				Academicfeesstructure feesStructure = (Academicfeesstructure) query.uniqueResult();
				if(feesStructure != null){
					
					Query queryUpdate = session
							.createQuery("update Academicfeesstructure set totalfees = '"+academicfeesStructure.getTotalfees()+"'  where sid = '"+academicfeesStructure.getSid()+"' and academicyear = '"+currentYear+"'");
					
					
					queryUpdate.executeUpdate();
				}else if(feesStructure == null){
					session.save(academicfeesStructure);
				}
				
			
			
			}
			
			for (Studentfeesstructure studentfeesstructure : listOfstudentfeesstructure) {
				
				Query query = session.createQuery("from Studentfeesstructure as sfs where sfs.sid = '"+studentfeesstructure.getSid()+"' and sfs.Feescategory.idfeescategory = '"+studentfeesstructure.getIdfeescategory()+"' and sfs.academicyear = '"+currentYear+"'");
				Studentfeesstructure feesStructure = (Studentfeesstructure) query.uniqueResult();
				if(feesStructure != null){
					
					Query queryUpdate = session
							.createQuery("update Studentfeesstructure set idfeescategory = '"+studentfeesstructure.getIdfeescategory()+"',feesamount = '"+studentfeesstructure.getFeesamount()+"'  where sid = '"+studentfeesstructure.getSid()+"' and academicyear = '"+currentYear+"'");
					
					
					queryUpdate.executeUpdate();
				}else if(feesStructure == null){
					session.save(studentfeesstructure);
				}
		}
			
			//accounts
			
			session.save(transactions);
			Query queryAccounts = session.createQuery(updateDrAccount);
			queryAccounts.executeUpdate();
			Query queryqueryAccounts1 = session.createQuery(updateCrAccount);
			queryqueryAccounts1.executeUpdate();
			

			transaction.commit();
			System.out.println("in add3");
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public void deleteMultiple(java.util.List ids, String currentYear) {

		try {
			transaction = session.beginTransaction();
			
			
			Query query = session
					.createQuery("delete from Studentfeesstructure as sfs where sfs.sid IN (:ids) and sfs.academicyear='"+currentYear+"'");
			query.setParameterList("ids", ids);
			Query query2 = session
					.createQuery("delete from Academicfeesstructure where sid IN (:ids) and academicyear='"+currentYear+"'");
			query2.setParameterList("ids", ids);
			query.executeUpdate();
			query2.executeUpdate();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}

	
		
	}

	
	

}
