package org.ideoholic.curium.model.user.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.ideoholic.curium.exceptions.CustomErrorMessage;
import org.ideoholic.curium.exceptions.CustomResponseException;
import org.ideoholic.curium.model.feescollection.dto.Otherreceiptinfo;
import org.ideoholic.curium.model.feescollection.dto.Receiptinfo;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.model.user.dto.Login;
import org.ideoholic.curium.repositories.LoginRepository;
import org.ideoholic.curium.repositories.StudentRepository;
import org.ideoholic.curium.util.QueryUtil;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserDAO {

	private final QueryUtil queryUtil;
    private final LoginRepository loginRepo;
    private final StudentRepository studentRepo;

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
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			log.debug("In user-dao null pointer exception {}", hibernateException.getMessage());
			hibernateException.printStackTrace();
		}
		return login;
	}

    @Transactional
	public int getNoOfStudents(Integer branchId) {
		List<Student> results;
		int noOfRecords = 0;
		try {
			// results = (java.util.List<Student>) session.createQuery("FROM Student s where s.archive = 0 AND s.branchid="+branchId).list();
			results = studentRepo.findByArchiveAndBranchid(0, branchId);
			noOfRecords = results.size();

		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return noOfRecords;
	}

	@Transactional
	public int getNoOfStudentsOne(String classStudying, int branchId) {
		int noOfRecords = 0;
		try {

			// results = (java.util.List<Student>) session.createQuery("From Student s where s.classstudying LIKE '"+classStudying+" %' OR s.classstudying = '"+classStudying+"' AND s.archive = 0 AND s.branchid="+branchId+"").list();
			noOfRecords = studentRepo.countNumberOfStudentInClass(classStudying, 0, branchId);

		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return noOfRecords;
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
    	   TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
           log.error(hibernateException.getMessage(), hibernateException);
           hibernateException.printStackTrace();
       }
        return login;
   }

	@Transactional
	public Login update(Login login) {
        try {
            loginRepo.save(login);
        } catch (Exception hibernateException) {
        	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
        }
        return login;
   }

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Receiptinfo> getReceiptDetailsList(String queryMain) {
		List<Receiptinfo> feesDetails = new ArrayList<>();
		try {
			feesDetails = queryUtil.runGivenQuery(queryMain, Receiptinfo.class);
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return feesDetails;
	}

	@Transactional
	public boolean addUser(Login user) {
		try {
			int userid = 1;
			Login lastAddedUser = loginRepo.findTopByOrderByUserid().orElse(null);

			user = loginRepo.save(user);

			if (lastAddedUser != null) {
				userid = lastAddedUser.getUserid() + 1;
			}
			user.setUserid(userid);
			loginRepo.save(user);
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
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
        	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
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
    	   TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
           log.error(hibernateException.getMessage(), hibernateException);
           hibernateException.printStackTrace();
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
    	   TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
           log.error(hibernateException.getMessage(), hibernateException);
           log.error("In userdao null pointer exception", hibernateException);
           hibernateException.printStackTrace();
       }
        return login;
    }
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Otherreceiptinfo> getOtherReceiptDetailsList(String queryMain) {
		List<Otherreceiptinfo> feesDetails = new ArrayList<>();
		try {
			feesDetails = queryUtil.runGivenQuery(queryMain, Otherreceiptinfo.class);
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return feesDetails;
	}

}