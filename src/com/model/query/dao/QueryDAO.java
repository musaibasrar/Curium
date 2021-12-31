package com.model.query.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.model.department.dto.Department;
import com.model.mess.stockmove.dto.MessStockMove;
import com.model.query.dto.ParentQuery;
import com.model.user.dao.UserDAO;
import com.util.HibernateUtil;
import com.util.Session;
import com.util.Session.Transaction;

public class QueryDAO {
	Session session = null;
	/**
	 * * Hibernate Session Variable
	 */
	Transaction transaction = null;
	/**
	 * * Hibernate Transaction Variable
	 */
	SessionFactory sessionFactory;
	
	private static final Logger logger = LogManager.getLogger(QueryDAO.class);

	public QueryDAO() {
		session = HibernateUtil.openCurrentSession();
	}


		public String addQuery(ParentQuery query) {
			
			String queryNo = null;
		
			try {
					transaction = session.beginTransaction();
					
		
					 Query<ParentQuery> queryParentQuery = session.createQuery("from ParentQuery where branchid = "+query.getBranchid()+" order by id DESC");
					 	List<ParentQuery> queryList = queryParentQuery.list();
					 	
					 	if(queryList.size() > 0) {
					 		String qNo = queryList.get(0).getExternalid();
					 		String splitqNo = qNo.substring(2);
					 		
					 		if(Integer.parseInt(splitqNo) < 1000) {
					 			query.setExternalid("EN"+String.format("%03d", Integer.parseInt(splitqNo)+1));
					 		}else {
					 			query.setExternalid("EN"+String.format("%03d", 1));
					 		}
					 	}else {
					 		query.setExternalid("EN"+String.format("%03d", 1));
					 	}
					 	
					 	
					session.save(query);
					transaction.commit();
					queryNo=query.getExternalid()+":"+query.getId();
			} catch (Exception e) { transaction.rollback(); logger.error(e);
				e.printStackTrace();
			}finally {
				HibernateUtil.closeSession();
			}
			return queryNo;
		}


		public List<ParentQuery> readListOfObjectsPagination(int offset,
				int noOfRecords, int branchId) {
			
			List<ParentQuery> results = new ArrayList<ParentQuery>();

			try {
				
				transaction = session.beginTransaction();
				Query query = session.createQuery("From ParentQuery as query where query.branchid = "+branchId+" order by query.id desc").setCacheable(true).setCacheRegion("commonregion");
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
			List<ParentQuery> results = new ArrayList<ParentQuery>();
			int noOfRecords = 0;
			try {
				// this.session =
				// HibernateUtil.getSessionFactory().openCurrentSession();
				transaction = session.beginTransaction();

				results = (List<ParentQuery>) session.createQuery("From ParentQuery where branchid="+branchId).setCacheable(true).setCacheRegion("commonregion")
						.list();
				noOfRecords = results.size();
				logger.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: "
								+ noOfRecords);
				transaction.commit();

			} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
				
				hibernateException.printStackTrace();

			} finally {
					HibernateUtil.closeSession();
				return noOfRecords;
			}
		}
		
		
		public int getNoOfRecords() {
			List<ParentQuery> results = new ArrayList<ParentQuery>();
			int noOfRecords = 0;
			try {
				// this.session =
				// HibernateUtil.getSessionFactory().openCurrentSession();
				transaction = session.beginTransaction();

				results = (List<ParentQuery>) session.createQuery("From ParentQuery where status !='Cancelled'").setCacheable(true).setCacheRegion("commonregion")
						.list();
				noOfRecords = results.size();
				logger.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: "
								+ noOfRecords);
				transaction.commit();

			} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
				
				hibernateException.printStackTrace();

			} finally {
					HibernateUtil.closeSession();
				return noOfRecords;
			}
		}


		public List<ParentQuery> completeQueries(List<Integer> queryIdsList, int userId) {
			
			List<ParentQuery> result = new ArrayList<ParentQuery>();
			try {
				transaction = session.beginTransaction();
				
				for (Integer appId : queryIdsList) {
					Query query = session.createQuery("update ParentQuery set status = 'Completed', updateddate = CURDATE(), updateduserid= "+userId+" where id="+appId+"");
					query.executeUpdate();
					ParentQuery pq = new ParentQuery();
					Query queryGet = session.createQuery("From ParentQuery as query where query.id = "+appId+"");
					pq = (ParentQuery) queryGet.uniqueResult();
					result.add(pq);
				}
				
				transaction.commit();
			} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
				hibernateException.printStackTrace();
			}finally {
				HibernateUtil.closeSession();
			 }
			return result;
		}


		public boolean cancelQueries(List<Integer> queryIdsList, int userId) {
			
			boolean result = false;
			try {
				transaction = session.beginTransaction();
				
				for (Integer appId : queryIdsList) {
					Query query = session.createQuery("update ParentQuery set status = 'Cancelled',  updateduserid= "+userId+", updateddate=CURDATE()  where id="+appId+"");
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
		
		public boolean inProgressQueries(List<Integer> queryIdsList, int userId) {
			
			boolean result = false;
			try {
				transaction = session.beginTransaction();
				
				for (Integer appId : queryIdsList) {
					Query query = session.createQuery("update ParentQuery set status = 'In Progress', updateduserid= "+userId+", updateddate=CURDATE()   where id="+appId+"");
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


		public ParentQuery viewQueryDetails(int queryId) {
			
			ParentQuery parentQuery = new ParentQuery();
			try {
				transaction = session.beginTransaction();
				
					Query query = session.createQuery("from ParentQuery where id="+queryId+"");
					parentQuery = (ParentQuery) query.uniqueResult();
				
				transaction.commit();
			} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
				hibernateException.printStackTrace();
			}finally {
				HibernateUtil.closeSession();
			 }
			return parentQuery;
		}


		public boolean updateQueries(String queryId, String parentQuery, String response, int userId) {
			
			boolean result = false;
			try {
				transaction = session.beginTransaction();
				
					Query query = session.createQuery("update ParentQuery set query = '"+parentQuery+"', response='"+response+"', updateduserid= "+userId+", updateddate = CURDATE() where id="+queryId+"");
					query.executeUpdate();
				
				transaction.commit();
				result = true;
			} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
				hibernateException.printStackTrace();
			}finally {
				HibernateUtil.closeSession();
			 }
			return result;
		}


		public List<ParentQuery> readListOfObjectsPaginationDepartmentWise(int offset,
				int noOfRecords, int branchId, String department) {
			
			List<ParentQuery> results = new ArrayList<ParentQuery>();

			try {
				
				transaction = session.beginTransaction();
				Query queryDepartment = session.createQuery("From Department as dep where dep.departmentname = '"+department+"'").setCacheable(true).setCacheRegion("commonregion");
				Department dep = (Department) queryDepartment.uniqueResult();
				Query query = session.createQuery("From ParentQuery as query where query.branchid = "+branchId+" and query.department.id='"+dep.getDepid()+"' order by query.id desc").setCacheable(true).setCacheRegion("commonregion");
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


		public int getNoOfRecordsDepartmentWise(int branchId, String department) {
			List<ParentQuery> results = new ArrayList<ParentQuery>();
			int noOfRecords = 0;
			try {
				// this.session =
				// HibernateUtil.getSessionFactory().openCurrentSession();
				transaction = session.beginTransaction();
				Query queryDepartment = session.createQuery("From Department as dep where dep.departmentname = '"+department+"'").setCacheable(true).setCacheRegion("commonregion");
				Department dep = (Department) queryDepartment.uniqueResult();
				results = (List<ParentQuery>) session.createQuery("From ParentQuery where department.depid='"+dep.getDepid()+"' and branchid="+branchId).setCacheable(true).setCacheRegion("commonregion")
						.list();
				noOfRecords = results.size();
				logger.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: "
								+ noOfRecords);
				transaction.commit();

			} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
				
				hibernateException.printStackTrace();

			} finally {
					HibernateUtil.closeSession();
				return noOfRecords;
			}
		}


		public int getNoOfRecordsMonthly(String fromDate, String toDate) {

			List<ParentQuery> results = new ArrayList<ParentQuery>();
			int noOfRecords = 0;
			try {
				// this.session =
				// HibernateUtil.getSessionFactory().openCurrentSession();
				transaction = session.beginTransaction();

				results = (List<ParentQuery>) session.createQuery("From ParentQuery where (createddate between '"+fromDate+"' and '"+toDate+"')  and status !='Cancelled'").setCacheable(true).setCacheRegion("commonregion")
						.list();
				noOfRecords = results.size();
				logger.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: "
								+ noOfRecords);
				transaction.commit();

			} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
				
				hibernateException.printStackTrace();

			} finally {
					HibernateUtil.closeSession();
				return noOfRecords;
			}
		
					
		}


		public int getNoOfRecordsResolvedQueries() {
			
			List<ParentQuery> results = new ArrayList<ParentQuery>();
			int noOfRecords = 0;
			try {
				// this.session =
				// HibernateUtil.getSessionFactory().openCurrentSession();
				transaction = session.beginTransaction();

				results = (List<ParentQuery>) session.createQuery("From ParentQuery where status = 'Completed'").setCacheable(true).setCacheRegion("commonregion")
						.list();
				noOfRecords = results.size();
				logger.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: "
								+ noOfRecords);
				transaction.commit();

			} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
				
				hibernateException.printStackTrace();

			} finally {
					HibernateUtil.closeSession();
				return noOfRecords;
			}
		}


		public int getNoOfRecordsUnResolvedQueries() {
			
			List<ParentQuery> results = new ArrayList<ParentQuery>();
			int noOfRecords = 0;
			try {
				// this.session =
				// HibernateUtil.getSessionFactory().openCurrentSession();
				transaction = session.beginTransaction();

				results = (List<ParentQuery>) session.createQuery("From ParentQuery where status = 'Assigned' or status = 'In Progress'").setCacheable(true).setCacheRegion("commonregion")
						.list();
				noOfRecords = results.size();
				logger.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: "
								+ noOfRecords);
				transaction.commit();

			} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
				
				hibernateException.printStackTrace();

			} finally {
					HibernateUtil.closeSession();
				return noOfRecords;
			}
		}


		public int getNoOfRecordsTodayResolvedQueries() {
			
			List<ParentQuery> results = new ArrayList<ParentQuery>();
			int noOfRecords = 0;
			try {
				// this.session =
				// HibernateUtil.getSessionFactory().openCurrentSession();
				transaction = session.beginTransaction();

				results = (List<ParentQuery>) session.createQuery("From ParentQuery where status = 'Completed' and createddate = CURDATE()").setCacheable(true).setCacheRegion("commonregion")
						.list();
				noOfRecords = results.size();
				logger.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: "
								+ noOfRecords);
				transaction.commit();

			} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
				
				hibernateException.printStackTrace();

			} finally {
					HibernateUtil.closeSession();
				return noOfRecords;
			}
		}


		public int getNoOfRecordsTodayUnResolvedQueries() {
			
			List<ParentQuery> results = new ArrayList<ParentQuery>();
			int noOfRecords = 0;
			try {
				// this.session =
				// HibernateUtil.getSessionFactory().openCurrentSession();
				transaction = session.beginTransaction();

				results = (List<ParentQuery>) session.createQuery("From ParentQuery where (status = 'Assigned' or status = 'In Progress') and createddate = CURDATE()").setCacheable(true).setCacheRegion("commonregion")
						.list();
				noOfRecords = results.size();
				logger.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: "
								+ noOfRecords);
				transaction.commit();

			} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
				
				hibernateException.printStackTrace();

			} finally {
					HibernateUtil.closeSession();
				return noOfRecords;
			}
		}


		public List<ParentQuery> generateQueriesReport(String parentQuery) {
			
	        List<ParentQuery> results = new ArrayList<ParentQuery>();
	        
	        try {
	                transaction = session.beginTransaction();
	                results = (List<ParentQuery>) session.createQuery(parentQuery).setCacheable(true).setCacheRegion("commonregion").list();
	                transaction.commit();
	        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
	                
	                hibernateException.printStackTrace();

	        } finally {
	    			HibernateUtil.closeSession();
	        }
	        return results;
}


		public boolean feedback(int queryId, String pid, String feedbackpoints) {
			
			boolean result = false;
			try {
				transaction = session.beginTransaction();
				
					Query query = session.createQuery("update ParentQuery set feedback = '"+feedbackpoints+"' where id="+queryId+" and stdid="+pid+"");
					query.executeUpdate();
				
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
