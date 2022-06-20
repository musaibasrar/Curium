package org.ideoholic.curium.model.job.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.ideoholic.curium.model.department.dto.Department;
import org.ideoholic.curium.model.job.dto.JobQuery;
import org.ideoholic.curium.util.HibernateUtil;
import org.ideoholic.curium.util.Session;
import org.ideoholic.curium.util.Session.Transaction;

public class JobDAO {
	Session session = null;
	/**
	 * * Hibernate Session Variable
	 */
	Transaction transaction = null;
	/**
	 * * Hibernate Transaction Variable
	 */
	SessionFactory sessionFactory;
	
	private static final Logger logger = LogManager.getLogger(JobDAO.class);

	public JobDAO() {
		session = HibernateUtil.openCurrentSession();
	}


		public String addQuery(JobQuery query) {
			
			String queryNo = null;
		
			try {
					transaction = session.beginTransaction();
					String[] extId = query.getExternalid().split("_");	
		
					 Query<JobQuery> queryParentQuery = session.createQuery("from JobQuery where branchid = "+query.getBranchid()+" order by id DESC");
					 	List<JobQuery> queryList = queryParentQuery.list();
					 	
					 	Date date = queryList.get(0).getCreateddate();
					 	Date todaysDate = new Date();
					 	
				 		Calendar calendar = Calendar.getInstance();
				 		calendar.setTime(date);
				 		int month = calendar.get(Calendar.MONTH);
				 		
				 		Calendar todayscalendar = Calendar.getInstance();
				 		todayscalendar.setTime(todaysDate);
				 		int todaysmonth = todayscalendar.get(Calendar.MONTH);
					 	
					 	if(todaysmonth == month) {
					 		String qNo = queryList.get(0).getExternalid();
					 		String[] splitqNo = qNo.split("_");
					 		int sum = Integer.parseInt(splitqNo[2])+1;
					 		query.setExternalid(extId[0]+"_"+extId[1]+"_"+sum+"_"+extId[2]+"_"+extId[3]);
					 	}else {
					 		query.setExternalid(extId[0]+"_"+extId[1]+"_"+"1"+"_"+extId[2]+"_"+extId[3]);
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


		public List<JobQuery> readListOfObjectsPagination(int offset,
				int noOfRecords, int branchId) {
			
			List<JobQuery> results = new ArrayList<JobQuery>();

			try {
				
				transaction = session.beginTransaction();
				Query query = session.createQuery("From JobQuery as query where query.branchid = "+branchId+" order by query.id desc").setCacheable(true).setCacheRegion("commonregion");
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
			List<JobQuery> results = new ArrayList<JobQuery>();
			int noOfRecords = 0;
			try {
				// this.session =
				// HibernateUtil.getSessionFactory().openCurrentSession();
				transaction = session.beginTransaction();

				results = (List<JobQuery>) session.createQuery("From JobQuery where branchid="+branchId).setCacheable(true).setCacheRegion("commonregion")
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
			List<JobQuery> results = new ArrayList<JobQuery>();
			int noOfRecords = 0;
			try {
				// this.session =
				// HibernateUtil.getSessionFactory().openCurrentSession();
				transaction = session.beginTransaction();

				results = (List<JobQuery>) session.createQuery("From JobQuery where status !='Cancelled'").setCacheable(true).setCacheRegion("commonregion")
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


		public List<JobQuery> completeQueries(List<Integer> queryIdsList, int userId) {
			
			List<JobQuery> result = new ArrayList<JobQuery>();
			try {
				transaction = session.beginTransaction();
				
				for (Integer appId : queryIdsList) {
					Query query = session.createQuery("update JobQuery set status = 'Completed', updateddate = CURDATE(), updateduserid= "+userId+" where id="+appId+"");
					query.executeUpdate();
					JobQuery pq = new JobQuery();
					Query queryGet = session.createQuery("From JobQuery as query where query.id = "+appId+"");
					pq = (JobQuery) queryGet.uniqueResult();
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
					Query query = session.createQuery("update JobQuery set status = 'Cancelled',  updateduserid= "+userId+", updateddate=CURDATE()  where id="+appId+"");
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
					Query query = session.createQuery("update JobQuery set status = 'In Progress', updateduserid= "+userId+", updateddate=CURDATE()   where id="+appId+"");
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


		public JobQuery viewQueryDetails(int queryId) {
			
			JobQuery parentQuery = new JobQuery();
			try {
				transaction = session.beginTransaction();
				
					Query query = session.createQuery("from JobQuery where id="+queryId+"");
					parentQuery = (JobQuery) query.uniqueResult();
				
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
				
					Query query = session.createQuery("update JobQuery set query = '"+parentQuery+"', response='"+response+"', updateduserid= "+userId+", updateddate = CURDATE() where id="+queryId+"");
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


		public List<JobQuery> readListOfObjectsPaginationDepartmentWise(int offset,
				int noOfRecords, int branchId, int tid) {
			
			List<JobQuery> results = new ArrayList<JobQuery>();

			try {
				
				transaction = session.beginTransaction();
				/*
				 * Query queryDepartment =
				 * session.createQuery("From Teacher as t where t.teachername = '"+department+
				 * "'").setCacheable(true).setCacheRegion("commonregion"); Department dep =
				 * (Department) queryDepartment.uniqueResult();
				 */
				Query query = session.createQuery("From JobQuery as query where query.branchid = "+branchId+" and query.teacher.tid='"+tid+"' order by query.id desc").setCacheable(true).setCacheRegion("commonregion");
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


		public int getNoOfRecordsDepartmentWise(int branchId, int tid) {
			List<JobQuery> results = new ArrayList<JobQuery>();
			int noOfRecords = 0;
			try {
				// this.session =
				// HibernateUtil.getSessionFactory().openCurrentSession();
				transaction = session.beginTransaction();
				/*
				 * Query queryDepartment =
				 * session.createQuery("From Department as dep where dep.departmentname = '"
				 * +department+"'").setCacheable(true).setCacheRegion("commonregion");
				 * Department dep = (Department) queryDepartment.uniqueResult();
				 */
				results = (List<JobQuery>) session.createQuery("From JobQuery where teacher.tid='"+tid+"' and branchid="+branchId).setCacheable(true).setCacheRegion("commonregion")
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

			List<JobQuery> results = new ArrayList<JobQuery>();
			int noOfRecords = 0;
			try {
				// this.session =
				// HibernateUtil.getSessionFactory().openCurrentSession();
				transaction = session.beginTransaction();

				results = (List<JobQuery>) session.createQuery("From JobQuery where (createddate between '"+fromDate+"' and '"+toDate+"')  and status !='Cancelled'").setCacheable(true).setCacheRegion("commonregion")
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
			
			List<JobQuery> results = new ArrayList<JobQuery>();
			int noOfRecords = 0;
			try {
				// this.session =
				// HibernateUtil.getSessionFactory().openCurrentSession();
				transaction = session.beginTransaction();

				results = (List<JobQuery>) session.createQuery("From JobQuery where status = 'Completed'").setCacheable(true).setCacheRegion("commonregion")
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
			
			List<JobQuery> results = new ArrayList<JobQuery>();
			int noOfRecords = 0;
			try {
				// this.session =
				// HibernateUtil.getSessionFactory().openCurrentSession();
				transaction = session.beginTransaction();

				results = (List<JobQuery>) session.createQuery("From JobQuery where status = 'To Do'").setCacheable(true).setCacheRegion("commonregion")
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
			
			List<JobQuery> results = new ArrayList<JobQuery>();
			int noOfRecords = 0;
			try {
				// this.session =
				// HibernateUtil.getSessionFactory().openCurrentSession();
				transaction = session.beginTransaction();

				results = (List<JobQuery>) session.createQuery("From JobQuery where status = 'Completed' and createddate = CURDATE()").setCacheable(true).setCacheRegion("commonregion")
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
			
			List<JobQuery> results = new ArrayList<JobQuery>();
			int noOfRecords = 0;
			try {
				// this.session =
				// HibernateUtil.getSessionFactory().openCurrentSession();
				transaction = session.beginTransaction();

				results = (List<JobQuery>) session.createQuery("From JobQuery where (status = 'Assigned' or status = 'In Progress') and createddate = CURDATE()").setCacheable(true).setCacheRegion("commonregion")
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


		public List<JobQuery> generateQueriesReport(String parentQuery) {
			
	        List<JobQuery> results = new ArrayList<JobQuery>();
	        
	        try {
	                transaction = session.beginTransaction();
	                results = (List<JobQuery>) session.createQuery(parentQuery).setCacheable(true).setCacheRegion("commonregion").list();
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
				
					Query query = session.createQuery("update JobQuery set feedback = '"+feedbackpoints+"' where id="+queryId+" and stdid="+pid+"");
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


		public boolean toDoQueries(List<Integer> queryIdsList, int userId) {
			
			boolean result = false;
			try {
				transaction = session.beginTransaction();
				
				for (Integer appId : queryIdsList) {
					Query query = session.createQuery("update JobQuery set status = 'To Do', updateduserid= "+userId+", updateddate=CURDATE()   where id="+appId+"");
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


		public boolean updateQueryRemarks(String queryId, String remarks, int userId) {
			
			boolean result = false;
			try {
				transaction = session.beginTransaction();
				
					Query query = session.createQuery("update JobQuery set feedback = IFNULL (CONCAT( feedback , '"+remarks+"' ), '"+remarks+"'), updateduserid= "+userId+", updateddate = CURDATE() where id="+queryId+"");
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


		public int getNoOfRecordsInProgressQueries() {
			
			List<JobQuery> results = new ArrayList<JobQuery>();
			int noOfRecords = 0;
			try {
				// this.session =
				// HibernateUtil.getSessionFactory().openCurrentSession();
				transaction = session.beginTransaction();

				results = (List<JobQuery>) session.createQuery("From JobQuery where status = 'In Progress'").setCacheable(true).setCacheRegion("commonregion")
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

}
