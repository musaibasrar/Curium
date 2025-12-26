package org.ideoholic.curium.model.login.dao;

import java.util.ArrayList;
import java.util.List;

import org.ideoholic.curium.model.branch.dto.Branch;
import org.ideoholic.curium.model.user.dto.Login;
import org.ideoholic.curium.repositories.BranchRepository;
import org.ideoholic.curium.repositories.LoginRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginDao {
	
    private final LoginRepository loginRepository;
    private final BranchRepository branchRepository;
    

	public List<Login> readListOfLoginDetail(String branchId) {
		List<Login> results = new ArrayList<>();
		try {
			// results = (List<Login>) session.createQuery("From Login where branchid="+branchId+"").list();
			Integer bid = null;
			if (branchId != null && branchId.trim().length() > 0) {
				bid = Integer.parseInt(branchId);
				if (bid != null) {
					results = loginRepository.findByBranchId(bid);
				}
			}
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return results;
	}


	public void deleteRecord(List<Integer> ids) {
		try {
			// Original HQL (preserved for reference):
			// Query query = session.createQuery("delete from Login as login where login.lid IN (:ids)");
			// query.setParameterList("ids", ids);
			// query.executeUpdate();
			loginRepository.deleteAllById(ids);
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(),hibernateException);
			hibernateException.printStackTrace();
		}
	}


	public Login readDetailsOfLogin(int lid) {
		Login login = new Login();
		try {
			// Original HQL (preserved for reference):
			// Query query = session.createQuery("from Login as login where login.lid=" + lid);
			// login = (Login) query.uniqueResult();
			login = loginRepository.findById(lid).orElse(new Login());
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(),hibernateException);
			hibernateException.printStackTrace();
		}
		return login;
	}


	public boolean updateDetailsOfLogin(Login login) {
		boolean result = false;
		try {
            // Original SQL/HQL (preserved for reference):
            // Query queryUpdate = session.createSQLQuery("update Login set username='"+login.getUsername()+"',password='"+login.getPassword()+"',usertype='"+login.getUsertype()+"' where lid="+login.getLid());
            // queryUpdate.executeUpdate();
            int updated = loginRepository.updateLoginDetails(login.getUsername(), login.getPassword(), login.getUsertype(), login.getLid());
            result = (updated > 0);
        } catch (Exception hibernateException) { 
        	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        	log.error(hibernateException.getMessage(),hibernateException);
            hibernateException.printStackTrace();
        } 
		return result;
	}


	public List<Branch> readListOfBranchId() {
		List<Branch> results = new ArrayList<Branch>();
		try {
			// Original HQL (preserved for reference):
			// results = (List<Branch>) session.createQuery("From Branch").list();
			results = branchRepository.findAll();
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(),hibernateException);
			hibernateException.printStackTrace();
		} 
			return results;
	}


	public boolean saveLoginDetail(Login login) {
		boolean result = false;
		try {
			// Original HQL (preserved for reference):
			// Query<Login> queryLogin = session.createQuery("from Login order by lid DESC");
			// List<Login> queryList = queryLogin.list();
			// int userId = queryList.get(0).getUserid();
			// userId = userId + 1;
			// login.setUserid(userId);
			Login last = loginRepository.findTopByOrderByLidDesc();
			Branch branch = branchRepository.findById(login.getBranch().getIdbranch()).orElse(null);
			if (branch != null) {
				int userId = 1;
				if (last != null && last.getUserid() != null) {
					userId = last.getUserid() + 1;
				}
				login.setBranch(branch);
				login.setUserid(userId);
				loginRepository.save(login);
				result = true;
			}
        } catch (Exception hibernateException) { 
        	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        	log.error(hibernateException.getMessage(),hibernateException);
            hibernateException.printStackTrace();
        } 
		return result;
	}

}