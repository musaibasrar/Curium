package org.ideoholic.curium.model.cases.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.ideoholic.curium.model.cases.dto.Cases;
import org.ideoholic.curium.model.department.dto.Department;
import org.ideoholic.curium.model.job.dto.JobQuery;
import org.ideoholic.curium.util.HibernateUtil;
import org.ideoholic.curium.util.Session;
import org.ideoholic.curium.util.Session.Transaction;

public class CasesDAO {
	Session session = null;
	/**
	 * * Hibernate Session Variable
	 */
	Transaction transaction = null;
	/**
	 * * Hibernate Transaction Variable
	 */
	SessionFactory sessionFactory;
	
	private static final Logger logger = LogManager.getLogger(CasesDAO.class);

	public CasesDAO() {
		session = HibernateUtil.openCurrentSession();
	}
	
	
	public List<Cases> readListOfObjectsPagination(int offset,
			int noOfRecords, int branchId) {
		
		List<Cases> results = new ArrayList<Cases>();

		try {
			
			transaction = session.beginTransaction();
			Query query = session.createQuery("From Cases as cases where cases.branchid = "+branchId+" order by cases.id desc").setCacheable(true).setCacheRegion("commonregion");
			query.setFirstResult(offset);   
			query.setMaxResults(noOfRecords);
			results = query.getResultList();
			transaction.commit();
			

		} catch (Exception hibernateException) {  transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();

		} finally {
				HibernateUtil.closeSession();
			return results;
		}
	}
	
	public int getNoOfRecords(int branchId) {
		List<Cases> results = new ArrayList<Cases>();
		int noOfRecords = 0;
		try {
			// this.session =
			// HibernateUtil.getSessionFactory().openCurrentSession();
			transaction = session.beginTransaction();

			results = (List<Cases>) session.createQuery("From Cases where branchid="+branchId).setCacheable(true).setCacheRegion("commonregion")
					.list();
			noOfRecords = results.size();
			logger.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: "+noOfRecords);
			transaction.commit();

		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();

		} finally {
				HibernateUtil.closeSession();
			return noOfRecords;
		}
	}


	public String addCases(Cases cases) {
		
		String casesNo = null;
	
		try {
				transaction = session.beginTransaction();
				String court = cases.getCourt();
				String caseId = "";
				 Query<Cases> queryParentCases = session.createQuery("from Cases where branchid = "+cases.getBranchid()+" and fileno like '"+court+"%' order by id DESC");
				 	List<Cases> queryList = queryParentCases.list();
				 	
				 	if(queryList.size()>0) {
				 		caseId = queryList.get(0).getFileno();
				 	    String numberOnly= caseId.replaceAll("[^0-9]", "");
				 	   	int fileNumber = Integer.parseInt(numberOnly)+1;
				 	    cases.setFileno(court+fileNumber);
				 	}else {
				 		cases.setFileno(court+"1");
				 	}
				session.save(cases);
				
				transaction.commit();
				casesNo=cases.getFileno();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return casesNo;
	}
	
	public boolean pendingCases(List<Integer> casesIdsList, int userId) {
		
		boolean result = false;
		try {
			transaction = session.beginTransaction();
			
			for (Integer appId : casesIdsList) {
				Query query = session.createQuery("update Cases set status = 'Pending', updateduserid= "+userId+", updateddate=CURDATE()   where id="+appId+"");
				query.executeUpdate();
			}
			
			transaction.commit();
			result = true;
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		 }
		return result;
	}


	public boolean disposeCases(List<Integer> casesIdsList, int userId, Cases cases, String disDate) {
		
		boolean result = false;
		try {
			transaction = session.beginTransaction();
			
			for (Integer appId : casesIdsList) {
				Query query = session.createQuery("update Cases set status = 'Disposed', dateofdispose='"+disDate+"', filetaken='"+cases.getFiletaken()+"', filetakenby='"+cases.getFiletakenby()+"', filetakenbyname='"+cases.getFiletakenbyname()+"', filetakenbynumber='"+cases.getFiletakenbynumber()+"', updateduserid= "+userId+", updateddate=CURDATE()   where id="+appId+"");
				query.executeUpdate();
			}
			
			transaction.commit();
			result = true;
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		 }
		return result;
	}


	public boolean nocCases(List<Integer> casesIdsList, int userId, Cases cases, String disDate) {
		
		boolean result = false;
		try {
			transaction = session.beginTransaction();
			
			for (Integer appId : casesIdsList) {
				Query query = session.createQuery("update Cases set status = 'NOC', dateofdispose='"+disDate+"', filetaken='"+cases.getFiletaken()+"', filetakenby='"+cases.getFiletakenby()+"', filetakenbyname='"+cases.getFiletakenbyname()+"', filetakenbynumber='"+cases.getFiletakenbynumber()+"', updateduserid= "+userId+", updateddate=CURDATE()   where id="+appId+"");
				query.executeUpdate();
			}
			
			transaction.commit();
			result = true;
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		 }
		return result;
	}


	public boolean cancelCases(List<Integer> casesIdsList, int userId) {
		
		boolean result = false;
		try {
			transaction = session.beginTransaction();
			
			for (Integer appId : casesIdsList) {
				Query query = session.createQuery("update Cases set status = 'Cancelled', updateduserid= "+userId+", updateddate=CURDATE()   where id="+appId+"");
				query.executeUpdate();
			}
			
			transaction.commit();
			result = true;
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		 }
		return result;
	}


	public List<Cases> generateCasesReport(String parentQuery) {
		
        List<Cases> results = new ArrayList<Cases>();
        
        try {
                transaction = session.beginTransaction();
                results = (List<Cases>) session.createQuery(parentQuery).setCacheable(true).setCacheRegion("commonregion").list();
                transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
                
                hibernateException.printStackTrace();

        } finally {
    			HibernateUtil.closeSession();
        }
        return results;
}

}
