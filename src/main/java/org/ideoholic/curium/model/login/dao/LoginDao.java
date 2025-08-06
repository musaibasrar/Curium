package org.ideoholic.curium.model.login.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.ideoholic.curium.model.branch.dto.Branch;
import org.ideoholic.curium.model.user.dto.Login;
import org.ideoholic.curium.util.HibernateUtil;
import org.ideoholic.curium.util.Session;
import org.ideoholic.curium.util.Session.Transaction;

public class LoginDao {
	
	Session session = null;
    /**
     * * Hibernate Session Variable
     */
    Transaction transaction = null;
    /**
     * * Hibernate Transaction Variable
     */
  
    SessionFactory sessionFactory;
    private static final Logger logger = LogManager.getLogger(LoginDao.class);
    
    public LoginDao() {
		session = HibernateUtil.openCurrentSession();
	}


	public List<Login> readListOfLoginDetail(String branchId) {
		List<Login> results = new ArrayList<Login>();
        try {
            
            transaction = session.beginTransaction();
            results = (List<Login>) session.createQuery("From Login where branchid="+branchId+"").list();
            transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
    			HibernateUtil.closeSession();
            return results;
        }
	}


	public void deleteRecord(List<Integer> ids) {
		try {
			transaction = session.beginTransaction();
			
			
			Query query = session
					.createQuery("delete from Login as login where login.lid IN (:ids)");
			query.setParameterList("ids", ids);
			
			query.executeUpdate();
			
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}	
		
	}


	public Login readDetailsOfLogin(int lid) {
		Login login = new Login();
		try {

			transaction = session.beginTransaction();
			Query query = session
					.createQuery("from Login as login where login.lid="
							+ lid);
			login = (Login) query.uniqueResult();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		 }
		return login;
	}


	public boolean updateDetailsOfLogin(Login login) {
		boolean result = false;
		try {
            transaction = session.beginTransaction();
            Query queryUpdate = session.createSQLQuery("update Login set username='"+login.getUsername()+"',password='"+login.getPassword()+"',usertype='"+login.getUsertype()+"' where lid="+login.getLid()+" ");
            queryUpdate.executeUpdate();
            transaction.commit();
            result = true;
        } catch (Exception hibernateException) { 
        	transaction.rollback(); logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
    			HibernateUtil.closeSession();
        }
		
		return result;
	}


	public List<Branch> readListOfBranchId() {
		List<Branch> results = new ArrayList<Branch>();
		try {

			transaction = session.beginTransaction();
			results = (List<Branch>) session.createQuery("From Branch")
					.list();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();
		} finally {
				HibernateUtil.closeSession();
			return results;
		}
	}


	public boolean saveLoginDetail(Login login) {
		boolean result = false;
				try {
		            transaction = session.beginTransaction();
		            Query<Login> queryLogin = session.createQuery("from Login order by lid DESC");
				 	List<Login> queryList = queryLogin.list();
				 	int userId = queryList.get(0).getUserid();
				 	userId = userId + 1;
				 	login.setUserid(userId);
		            session.save(login);
		            transaction.commit();
		            result = true;
		        } catch (Exception hibernateException) { transaction.rollback();
		        logger.error(hibernateException);
		            hibernateException.printStackTrace();
		        } finally {
		    			HibernateUtil.closeSession();
		        }
				return result;
	}

}
