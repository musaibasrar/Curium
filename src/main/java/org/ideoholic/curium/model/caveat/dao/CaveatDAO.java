package org.ideoholic.curium.model.caveat.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.ideoholic.curium.model.caveat.dto.Caveat;
import org.ideoholic.curium.util.HibernateUtil;
import org.ideoholic.curium.util.Session;
import org.ideoholic.curium.util.Session.Transaction;

public class CaveatDAO {
	Session session = null;
	/**
	 * * Hibernate Session Variable
	 */
	Transaction transaction = null;
	/**
	 * * Hibernate Transaction Variable
	 */
	SessionFactory sessionFactory;
	
	private static final Logger logger = LogManager.getLogger(CaveatDAO.class);

	public CaveatDAO() {
		session = HibernateUtil.openCurrentSession();
	}
	
	
	public List<Caveat> readListOfObjectsPagination(int offset,
			int noOfRecords, int branchId) {
		
		List<Caveat> results = new ArrayList<Caveat>();

		try {
			
			transaction = session.beginTransaction();
			Query query = session.createQuery("From Caveat as caveat where caveat.branchid = "+branchId+" order by caveat.id desc").setCacheable(true).setCacheRegion("commonregion");
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
		List<Caveat> results = new ArrayList<Caveat>();
		int noOfRecords = 0;
		try {
			// this.session =
			// HibernateUtil.getSessionFactory().openCurrentSession();
			transaction = session.beginTransaction();

			results = (List<Caveat>) session.createQuery("From Caveat where branchid="+branchId).setCacheable(true).setCacheRegion("commonregion")
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


	public String addCaveat(Caveat Caveat) {
		
		String CaveatNo = null;
	
		try {
				transaction = session.beginTransaction();
				String caseId = "";
				 Query<Caveat> queryParentCaveat = session.createQuery("from Caveat where branchid = "+Caveat.getBranchid()+" and fileno like 'CAV%' order by id DESC");
				 	List<Caveat> queryList = queryParentCaveat.list();
				 	
				 	if(queryList.size()>0) {
				 		caseId = queryList.get(0).getFileno();
				 	    String numberOnly= caseId.replaceAll("[^0-9]", "");
				 	   	int fileNumber = Integer.parseInt(numberOnly)+1;
				 	    Caveat.setFileno("CAV"+fileNumber);
				 	}else {
				 		Caveat.setFileno("CAV"+"1");
				 	}
				session.save(Caveat);
				
				transaction.commit();
				CaveatNo=Caveat.getFileno();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return CaveatNo;
	}
	
	public boolean pendingCaveat(List<Integer> CaveatIdsList, int userId) {
		
		boolean result = false;
		try {
			transaction = session.beginTransaction();
			
			for (Integer appId : CaveatIdsList) {
				Query query = session.createQuery("update Caveat set status = 'Pending', updateduserid= "+userId+" where id="+appId+"");
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


	public boolean disposeCaveat(List<Integer> CaveatIdsList, int userId, Caveat Caveat, String disDate) {
		
		boolean result = false;
		try {
			transaction = session.beginTransaction();
			
			for (Integer appId : CaveatIdsList) {
				Query query = session.createQuery("update Caveat set status = 'Disposed', dateofdispose='"+disDate+"', filetaken='"+Caveat.getFiletaken()+"', filetakenby='"+Caveat.getFiletakenby()+"', filetakenbyname='"+Caveat.getFiletakenbyname()+"', filetakenbynumber='"+Caveat.getFiletakenbynumber()+"', updateduserid= "+userId+", updateddate=CURDATE()   where id="+appId+"");
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


	public boolean nocCaveat(List<Integer> CaveatIdsList, int userId, Caveat Caveat, String disDate) {
		
		boolean result = false;
		try {
			transaction = session.beginTransaction();
			
			for (Integer appId : CaveatIdsList) {
				Query query = session.createQuery("update Caveat set status = 'NOC', dateofdispose='"+disDate+"', filetaken='"+Caveat.getFiletaken()+"', filetakenby='"+Caveat.getFiletakenby()+"', filetakenbyname='"+Caveat.getFiletakenbyname()+"', filetakenbynumber='"+Caveat.getFiletakenbynumber()+"', updateduserid= "+userId+", updateddate=CURDATE()   where id="+appId+"");
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


	public boolean cancelCaveat(List<Integer> CaveatIdsList, int userId) {
		
		boolean result = false;
		try {
			transaction = session.beginTransaction();
			
			for (Integer appId : CaveatIdsList) {
				Query query = session.createQuery("update Caveat set status = 'Cancelled', updateduserid= "+userId+"  where id="+appId+"");
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


	public List<Caveat> generateCaveatReport(String parentQuery) {
		
        List<Caveat> results = new ArrayList<Caveat>();
        
        try {
                transaction = session.beginTransaction();
                results = (List<Caveat>) session.createQuery(parentQuery).setCacheable(true).setCacheRegion("commonregion").list();
                transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
                
                hibernateException.printStackTrace();

        } finally {
    			HibernateUtil.closeSession();
        }
        return results;
}


	public boolean expiredCaveat(List<Integer> caveatsIdsList, int userId) {
		
		boolean result = false;
		try {
			transaction = session.beginTransaction();
			
			for (Integer appId : caveatsIdsList) {
				Query query = session.createQuery("update Caveat set status = 'Expired', updateduserid= "+userId+", updateddate=CURDATE()   where id="+appId+"");
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

}
