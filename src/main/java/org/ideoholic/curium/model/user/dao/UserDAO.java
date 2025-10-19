package org.ideoholic.curium.model.user.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.ideoholic.curium.exceptions.CustomErrorMessage;
import org.ideoholic.curium.exceptions.CustomResponseException;
import org.ideoholic.curium.model.feescollection.dto.Otherreceiptinfo;
import org.ideoholic.curium.model.feescollection.dto.Receiptinfo;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.model.user.dto.Login;
import org.ideoholic.curium.repositories.LoginRepository;
import org.ideoholic.curium.util.HibernateUtil;
import org.ideoholic.curium.util.QueryUtil;
import org.ideoholic.curium.util.Session;
import org.ideoholic.curium.util.Session.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserDAO {

    @Autowired
    private LoginRepository loginRepo;

    @Autowired
    private QueryUtil queryUtil;

    @Transactional
	public Login readUniqueObject(String userName, String password) {
		Login login = null;
		try {
			List<Login> loginList = loginRepo.findByUsernameAndPassword(userName, password);
			if (!CollectionUtils.isEmpty(loginList)) {
				login = loginList.get(0);
				// throw new CustomResponseException(CustomErrorMessage.INVALID_CREDENTIALS);
			}
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			log.debug("In user-dao null pointer exception {}", hibernateException.getMessage());
			hibernateException.printStackTrace();
			throw hibernateException;
		}
		return login;
	}

	@SuppressWarnings({ "unchecked", "finally" })
	public int getNoOfStudents(int branchId) {
		java.util.List<Student> results;
	    int noOfRecords = 0;
        Transaction transaction = null;
	        try {
                Session session = HibernateUtil.openCurrentSession();
	            transaction = session.beginTransaction();

	            results = (java.util.List<Student>) session.createQuery("FROM Student s where s.archive = 0 AND s.branchid="+branchId).list();
	            noOfRecords = results.size();
	            transaction.commit();

	        } catch (Exception hibernateException) {
                if(transaction != null) transaction.rollback();
                log.error(hibernateException.getMessage(), hibernateException);
	            hibernateException.printStackTrace();
	        } finally {
                HibernateUtil.closeSession();
	            return noOfRecords;
	        }
	}

	@SuppressWarnings("unchecked")
	public int getNoOfStudentsOne(String classStudying, int branchId) {
		java.util.List<Student> results;
        Transaction transaction = null;
	    int noOfRecords = 0;
	        try {
                Session session = HibernateUtil.openCurrentSession();
	            transaction = session.beginTransaction();

	            results = (java.util.List<Student>) session.createQuery("From Student s where s.classstudying LIKE '"+classStudying+" %' OR s.classstudying = '"+classStudying+"'  AND s.archive = 0 AND s.branchid="+branchId+"").list();
	            noOfRecords = results.size();
	            transaction.commit();

	        } catch (Exception hibernateException) {
                if(transaction != null) transaction.rollback();
                log.error(hibernateException.getMessage(), hibernateException);
	            hibernateException.printStackTrace();
	        } finally {
                HibernateUtil.closeSession();
	            return noOfRecords;
	        }
	}

	@Transactional
	public Login readPassword(String currentPassword) {
       Login login = null;
       try{
           List<Login> loginList = loginRepo.findByPassword(currentPassword);
           if(CollectionUtils.isEmpty(loginList)){
               throw new CustomResponseException(CustomErrorMessage.INVALID_CREDENTIALS);
           }
           login = loginList.get(0);
       }catch (Exception hibernateException) {
           log.error(hibernateException.getMessage(), hibernateException);
           hibernateException.printStackTrace();
           throw hibernateException;
       }
        return login;
   }

	@Transactional
	public Login update(Login login) {
        try {
            loginRepo.save(login);
        } catch (Exception hibernateException) {
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
        }
        return login;
   }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public java.util.List<Receiptinfo> getReceiptDetailsList(String queryMain) {
		java.util.List<Receiptinfo> feesDetails = new ArrayList<>();
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.openCurrentSession();

            transaction = session.beginTransaction();
            Query HQLquery = session.createQuery(queryMain);
            feesDetails = (java.util.List<Receiptinfo>) HQLquery.list();
            transaction.commit();
        } catch (Exception hibernateException) {
            if(transaction != null) transaction.rollback();
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
        }
        finally {
			HibernateUtil.closeSession();
		 }
        return feesDetails;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public boolean addUser(Login user) {
		try {
			int userid = 1;
			List<Login> loginList = queryUtil.findByClassLimitedTo("select l from Login as l order by l.userid", Login.class, 1);

			user = loginRepo.save(user);

           if(!CollectionUtils.isEmpty(loginList)){
        	   Login last = loginList.get(0);
               userid = last.getUserid()+1;
           }
           user.setUserid(userid);
           loginRepo.save(user);
        } catch (Exception hibernateException) {
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
		 }
		return true;
	}

	@Transactional
	public Login getUserDetails(String teacherexternalid) {
		Login user = new Login();
		try {
            List<Login> loginList = loginRepo.findByUsername(teacherexternalid);
            if(CollectionUtils.isEmpty(loginList)){
                throw new CustomResponseException(CustomErrorMessage.INVALID_CREDENTIALS);
            }
            user = loginList.get(0);
        } catch (Exception hibernateException) {
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
		}
		return user;
	}
	
	@Transactional
	public Login getLoginDetails(String userName, int branchId) {
        Login login = null;
       try{
           List<Login> loginList = loginRepo.findByBranchIdAndUserName(branchId, userName);
           if(CollectionUtils.isEmpty(loginList)){
               throw new CustomResponseException(CustomErrorMessage.INVALID_CREDENTIALS);
           }
           login = loginList.get(0);
       }catch (Exception hibernateException) {
           log.error(hibernateException.getMessage(), hibernateException);
           hibernateException.printStackTrace();
           throw hibernateException;
       }
        return login;
    }

	@Transactional
	public Login getUniqueObject(int userid) {
        Login login = null;
       try{
           List<Login> loginList = loginRepo.findByUserid(userid);
           if(CollectionUtils.isEmpty(loginList)){
               new CustomResponseException(CustomErrorMessage.INVALID_CREDENTIALS);
           }
           login = loginList.get(0);
       }catch (Exception hibernateException) {
           log.error(hibernateException.getMessage(), hibernateException);
           log.error("In userdao null pointer exception", hibernateException);
           hibernateException.printStackTrace();
           throw hibernateException;
       }
        return login;
    }
	
	@SuppressWarnings("unchecked")
	public java.util.List<Otherreceiptinfo> getOtherReceiptDetailsList(String queryMain) {
		java.util.List<Otherreceiptinfo> feesDetails = new ArrayList<>();
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.openCurrentSession();

            transaction = session.beginTransaction();
            Query HQLquery = session.createQuery(queryMain);
            feesDetails = (java.util.List<Otherreceiptinfo>) HQLquery.list();
            transaction.commit();
        } catch (Exception hibernateException) {
            if(transaction != null) transaction.rollback();
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
        }
        finally {
			HibernateUtil.closeSession();
		 }
        return feesDetails;
	}
	
	private void rollback() {
		// Roll back the transaction manually
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	}
}