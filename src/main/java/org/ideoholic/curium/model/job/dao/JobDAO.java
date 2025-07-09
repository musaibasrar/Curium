package org.ideoholic.curium.model.job.dao;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.ideoholic.curium.model.job.dto.JobQuery;
import org.ideoholic.curium.model.task.dto.Task;
import org.ideoholic.curium.repositories.JobQueryRepository;
import org.ideoholic.curium.repositories.TaskRepository;
import org.ideoholic.curium.util.DateUtil;
import org.ideoholic.curium.util.HibernateUtil;
import org.ideoholic.curium.util.QueryUtil;
import org.ideoholic.curium.util.Session;
import org.ideoholic.curium.util.Session.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class JobDAO {
	
	@Autowired
	private JobQueryRepository jobQueryRepository;
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private QueryUtil queryUtil;
	Session session = null;
	
	Transaction transaction = null;
	
	SessionFactory sessionFactory;
	

	public JobDAO() {
		session = HibernateUtil.openCurrentSession();
	}

	    @Transactional
		public String addQuery(JobQuery query) {
			
			String queryNo = null;
		
			try {
				query = jobQueryRepository.save(query);
				queryNo=query.getExternalid()+":"+query.getId();
			}catch (Exception hibernateException) { 
	        	log.error(hibernateException.getMessage(), hibernateException);
	            hibernateException.printStackTrace();
	            throw hibernateException;
			}
			return queryNo;
		}


	    @Transactional
		public List<JobQuery> readListOfObjectsPagination(int offset,
				int noOfRecords, int branchId) {
			
			List<JobQuery> results = new ArrayList<JobQuery>();

			try {
				 Pageable pageable = PageRequest.of(offset, noOfRecords); 
			     Page<JobQuery> page = jobQueryRepository.findByBranchidOrderByIdDesc(branchId, pageable);
			     results = page.getContent();
			}catch (Exception hibernateException) { 
	        	log.error(hibernateException.getMessage(), hibernateException);
	            hibernateException.printStackTrace();
	            throw hibernateException;
			}
			return results;
		}
		
	    @Transactional
		public int getNoOfRecords(int branchId) {
			List<JobQuery> results = new ArrayList<JobQuery>();
			int noOfRecords = 0;
			try {
				noOfRecords = jobQueryRepository.countByBranchid(branchId);
				log.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: "
								+ noOfRecords);
			} catch (Exception hibernateException) { 
	        	log.error(hibernateException.getMessage(), hibernateException);
	            hibernateException.printStackTrace();
	            throw hibernateException;
			} 
			return noOfRecords;
		}
		
	    @Transactional
		public int getNoOfRecords() {
			List<JobQuery> results = new ArrayList<JobQuery>();
			int noOfRecords = 0;
			try {

				noOfRecords = jobQueryRepository.countByStatusNot("Cancelled");
				log.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: "
								+ noOfRecords);

			}  catch (Exception hibernateException) { 
	        	log.error(hibernateException.getMessage(), hibernateException);
	            hibernateException.printStackTrace();
	            throw hibernateException;
			} 
			return noOfRecords;
		}


	    @Transactional
		public List<JobQuery> completeQueries(List<Integer> queryIdsList, int userId) {
			
			List<JobQuery> result = new ArrayList<JobQuery>();
			try {
				jobQueryRepository.updateJobStatus(queryIdsList, "Completed", userId, Date.from(Instant.now()));
				result = jobQueryRepository.findAllById(queryIdsList);
			} catch (Exception hibernateException) { 
	        	log.error(hibernateException.getMessage(), hibernateException);
	            hibernateException.printStackTrace();
	            throw hibernateException;
			} 
			return result;
		}


	    @Transactional
		public boolean cancelQueries(List<Integer> queryIdsList, int userId) {
			
			boolean result = false;
			try {
				jobQueryRepository.updateJobStatus(queryIdsList, "Cancelled", userId, Date.from(Instant.now()));
				result = true;
			} catch (Exception hibernateException) { 
	        	log.error(hibernateException.getMessage(), hibernateException);
	            hibernateException.printStackTrace();
	            throw hibernateException;
			}
			return result;
		}
		
	    @Transactional
		public boolean inProgressQueries(List<Integer> queryIdsList, int userId) {
			
			boolean result = false;
			try {
				jobQueryRepository.updateJobStatus(queryIdsList, "In Progress", userId, Date.from(Instant.now()));
				result = true;
			}  catch (Exception hibernateException) { 
	        	log.error(hibernateException.getMessage(), hibernateException);
	            hibernateException.printStackTrace();
	            throw hibernateException;
	            }

			return result;
		}


		@Transactional
		public JobQuery viewQueryDetails(int queryId) {
			JobQuery parentQuery = null;
			try {
				parentQuery = jobQueryRepository.findById(queryId).orElse(new JobQuery());
			} catch (Exception hibernateException) { 
	        	log.error(hibernateException.getMessage(), hibernateException);
	            hibernateException.printStackTrace();
	            throw hibernateException;
			}
			return parentQuery;
		}


		@Transactional
		public boolean updateQueries(String queryId, String parentQuery, String response, int userId) {
			int qid = Integer.parseInt(queryId); 
			boolean result = false;
			try {
				jobQueryRepository.updateJobQuery(qid, parentQuery, response, userId, Date.from(Instant.now()));
				result = true;
			} catch (Exception hibernateException) { 
	        	log.error(hibernateException.getMessage(), hibernateException);
	            hibernateException.printStackTrace();
	            throw hibernateException;
	            }
			return result;
		}


		@Transactional
		public List<JobQuery> readListOfObjectsPaginationDepartmentWise(int offset,
				int noOfRecords, int branchId, int tid) {
			
			List<JobQuery> results = new ArrayList<JobQuery>();

			try {
				 PageRequest pageRequest = PageRequest.of(offset / noOfRecords, noOfRecords);
				results = jobQueryRepository.findByBranchIdAndTeacherTid(branchId, tid, pageRequest);
			}  catch (Exception hibernateException) { 
	        	log.error(hibernateException.getMessage(), hibernateException);
	            hibernateException.printStackTrace();
	            throw hibernateException;
			} 
			return results;
		}

		@Transactional
		public int getNoOfRecordsDepartmentWise(int branchId, int tid) {
			List<JobQuery> results = new ArrayList<JobQuery>();
			int noOfRecords = 0;
			try {
				noOfRecords = jobQueryRepository.countByTeacherIdAndBranchId(tid, branchId);
				log.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: "
								+ noOfRecords);

			}  catch (Exception hibernateException) { 
	        	log.error(hibernateException.getMessage(), hibernateException);
	            hibernateException.printStackTrace();
	            throw hibernateException;
			} 
			return noOfRecords;
		}

		@Transactional
		public int getNoOfRecordsMonthly(String fromDate, String toDate) {
            Date fromdate =  DateUtil.dateParserdd(fromDate);
            Date todate = DateUtil.dateParserdd(toDate);
			List<JobQuery> results = new ArrayList<JobQuery>();
			int noOfRecords = 0;
			try {
				results = jobQueryRepository.findByCreateddateBetweenAndStatusNot(fromdate, todate, "Cancelled");
				noOfRecords = results.size();
				log.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: "
								+ noOfRecords);

			} catch (Exception hibernateException) { 
	        	log.error(hibernateException.getMessage(), hibernateException);
	            hibernateException.printStackTrace();
	            throw hibernateException;
			} 
			return noOfRecords;
		}


		@Transactional
		public int getNoOfRecordsResolvedQueries() {
			
			List<JobQuery> results = new ArrayList<JobQuery>();
			int noOfRecords = 0;
			try {
				noOfRecords = jobQueryRepository.countByStatus("Completed");

				log.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: "
								+ noOfRecords);

			}catch (Exception hibernateException) { 
	        	log.error(hibernateException.getMessage(), hibernateException);
	            hibernateException.printStackTrace();
	            throw hibernateException;
			} 
			return noOfRecords;
		}


		@Transactional
		public int getNoOfRecordsUnResolvedQueries() {
			
			List<JobQuery> results = new ArrayList<JobQuery>();
			int noOfRecords = 0;
			try {
				noOfRecords = jobQueryRepository.countByStatus("To Do");
				log.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: "
								+ noOfRecords);

			} catch (Exception hibernateException) { 
	        	log.error(hibernateException.getMessage(), hibernateException);
	            hibernateException.printStackTrace();
	            throw hibernateException;
			} 
			return noOfRecords;
		}


		@Transactional
		public int getNoOfRecordsTodayResolvedQueries() {
			
			List<JobQuery> results = new ArrayList<JobQuery>();
			int noOfRecords = 0;
			try {
				noOfRecords = jobQueryRepository.countByStatusAndCreateddate("Completed", Date.from(Instant.now()));
				log.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: "
								+ noOfRecords);

			}  catch (Exception hibernateException) { 
	        	log.error(hibernateException.getMessage(), hibernateException);
	            hibernateException.printStackTrace();
	            throw hibernateException;
			} 
				return noOfRecords;
			
		}


		@Transactional
		public int getNoOfRecordsTodayUnResolvedQueries() {
			
			List<JobQuery> results = new ArrayList<JobQuery>();
			int noOfRecords = 0;
			try {

				results = jobQueryRepository.findByStatusInAndCreateddate(Arrays.asList("Assigned", "In Progress"), Date.from(Instant.now()));
				noOfRecords = results.size();
				log.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: "
								+ noOfRecords);

			} catch (Exception hibernateException) { 
	        	log.error(hibernateException.getMessage(), hibernateException);
	            hibernateException.printStackTrace();
	            throw hibernateException;
			} 
			return noOfRecords;
		}

		@Transactional
		public List<JobQuery> generateQueriesReport(String parentQuery) {
			
	        List<JobQuery> results = new ArrayList<JobQuery>();
	        
	        try {
	        	    results = queryUtil.runGivenQuery(parentQuery, JobQuery.class);
	        }  catch (Exception hibernateException) { 
	        	log.error(hibernateException.getMessage(), hibernateException);
	            hibernateException.printStackTrace();
	            throw hibernateException;
	        } 
	        return results;
}


		@Transactional
		public boolean feedback(int queryId, String pid, String feedbackpoints) {
			int ipid = Integer.parseInt(pid);
			boolean result = false;
			try {
				JobQuery jobQuery = jobQueryRepository.findByQueryIdAndStaffId(queryId, ipid);
				jobQuery.setFeedback(feedbackpoints);
				jobQueryRepository.save(jobQuery);
				result = true;
			} catch (Exception hibernateException) { 
	        	log.error(hibernateException.getMessage(), hibernateException);
	            hibernateException.printStackTrace();
	            throw hibernateException;
			}
			return result;
		}

		@Transactional
		public boolean toDoQueries(List<Integer> queryIdsList, int userId) {
			
			boolean result = false;
			try {
				jobQueryRepository.updateJobStatus(queryIdsList,"To Do", userId, Date.from(Instant.now()));
				result = true;
			} catch (Exception hibernateException) { 
	        	log.error(hibernateException.getMessage(), hibernateException);
	            hibernateException.printStackTrace();
	            throw hibernateException;
			}
			return result;
		}


		@Transactional
		public boolean updateQueryRemarks(String queryId, String remarks, int userId) {
			
			int queryid = Integer.parseInt(queryId);
			AtomicBoolean result = new AtomicBoolean(false);
			try {
                jobQueryRepository.findById(queryid).ifPresent(jobQuery -> {
                	String feedBack = jobQuery.getFeedback();
                    if(ObjectUtils.isEmpty(feedBack)) {	
                    	jobQuery.setFeedback(remarks);
                    }
                    else {
                    	jobQuery.setFeedback(feedBack+" "+ remarks);
                    }
                    jobQueryRepository.save(jobQuery);
    				result.set(true);
                });
			}  catch (Exception hibernateException) { 
	        	log.error(hibernateException.getMessage(), hibernateException);
	            hibernateException.printStackTrace();
	            throw hibernateException;
			}
			return result.get();
		}


		public int getNoOfRecordsInProgressQueries() {
			
			List<JobQuery> results = new ArrayList<JobQuery>();
			int noOfRecords = 0;
			try {
				noOfRecords = jobQueryRepository.countByStatus("In Progress");

				log.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: "
								+ noOfRecords);

			}  catch (Exception hibernateException) { 
	        	log.error(hibernateException.getMessage(), hibernateException);
	            hibernateException.printStackTrace();
	            throw hibernateException;

			} 
			return noOfRecords;
		}


		public String addTask(List<Task> taskList,int jobId) {	
			
			String result = "false";
			try {
				
				JobQuery jobQuery = jobQueryRepository.findById(jobId).orElseGet(() ->{
					JobQuery jobQ = new JobQuery();
					  jobQ.setId(jobId);
					  return jobQ;
				});

	            for (Task task : taskList) {
	                task.setJobquery(jobQuery);
	                taskRepository.save(task); 
	            }

	            result =  "true";
	            
				} catch (Exception hibernateException) { 
		        	log.error(hibernateException.getMessage(), hibernateException);
		            hibernateException.printStackTrace();
		            throw hibernateException;

				}
			
			return result;
		}


		public List<Task> viewTaksDetails(int jobId) {
			
	        List<Task> results = new ArrayList<Task>();
	        
	        try {
	        	    results = taskRepository.findByJobquery_Id(jobId);
	        } catch (Exception hibernateException) { 
	        	log.error(hibernateException.getMessage(), hibernateException);
	            hibernateException.printStackTrace();
	            throw hibernateException;
			} 
	        return results;
		}


		@Transactional
		public List<Task> readListOfObjectsPaginationTask(int offset, int noOfRecords, int branchId) {

			List<Task> results = new ArrayList<Task>();

			try {

				Pageable pageable = PageRequest.of(offset, noOfRecords);
				results = taskRepository.findByBranchidOrderByIdDesc(branchId, pageable).getContent();

			} catch (Exception hibernateException) {
				log.error(hibernateException.getMessage(), hibernateException);
				hibernateException.printStackTrace();
				throw hibernateException;

			}
			return results;
		}


		@Transactional
		public int getNoOfRecordsTask(int branchId) {
			List<Task> results = new ArrayList<Task>();
			int noOfRecords = 0;
			try {
				noOfRecords = taskRepository.countByBranchid(branchId);
			}catch (Exception hibernateException) {
				log.error(hibernateException.getMessage(), hibernateException);
				hibernateException.printStackTrace();
				throw hibernateException;
			}
			
			return noOfRecords;
		}


		@Transactional
		public List<Task> readListOfObjectsPaginationDepartmentWiseTask(int offset, int noOfRecords, int branchId,
				int tid) {
			
			List<Task> results = new ArrayList<Task>();

			try {
				
				 PageRequest pageable = PageRequest.of(offset / noOfRecords, noOfRecords);
				 results =  taskRepository.findByBranchIdAndTeacherTid(branchId, tid, pageable);

			} catch (Exception hibernateException) {
				log.error(hibernateException.getMessage(), hibernateException);
				hibernateException.printStackTrace();
				throw hibernateException;
			} 
			return results;
		}


		@Transactional
		public int getNoOfRecordsDepartmentWiseTask(int branchId, int tid) {
			List<Task> results = new ArrayList<Task>();
			int noOfRecords = 0;
			try {
				noOfRecords = taskRepository.countByBranchIdAndTeacherTid(branchId, tid);

			} catch (Exception hibernateException) {
				log.error(hibernateException.getMessage(), hibernateException);
				hibernateException.printStackTrace();
				throw hibernateException;

			} 
			return noOfRecords;
		}


		@Transactional
		public List<Task> completeTasks(List<Integer> taskIdsList, int userId, String jobStatus, int jobId) {
			
			List<Task> result = new ArrayList<Task>();
			try {
				//Query query = session.createQuery("update JobQuery set status = '"+jobStatus+"', updateddate = CURDATE(), updateduserid= "+userId+" where id="+jobId+"");
				 jobQueryRepository.updateJobStatus(jobStatus,Date.from(Instant.now()), userId, jobId);
				 for (Integer taskId : taskIdsList) {
				//Query task = session.createQuery("update Task set status = 'Completed', updateddate = CURDATE(), updateduserid= "+userId+" where id="+appId+"");
			            taskRepository.updateTaskToCompleted("Completed",Date.from(Instant.now()),userId, taskId);
			        }
				//Query queryGet = session.createQuery("From Task as query where query.id = "+appId+"");
				 result = taskRepository.findByIdIn(taskIdsList);
			}  catch (Exception hibernateException) {
				log.error(hibernateException.getMessage(), hibernateException);
				hibernateException.printStackTrace();
				throw hibernateException;
			}
			return result;
		}


		@Transactional
		public List<Task> cancelTasks(List<Integer> taskIdsList, int userId, String jobStatus, int jobId) {
			
			List<Task> result = new ArrayList<Task>();
			try {
				
				if(jobStatus!=null) {
					//Query query = session.createQuery("update JobQuery set status = '"+jobStatus+"', updateddate = CURDATE(), updateduserid= "+userId+" where id="+jobId+"");
					 jobQueryRepository.updateJobStatus(jobStatus,Date.from(Instant.now()), userId, jobId);
				}
				
				for (Integer appId : taskIdsList) {
					//Query task = session.createQuery("update Task set status = 'Cancelled', updateddate = CURDATE(), updateduserid= "+userId+" where id="+appId+"");
					taskRepository.updateTaskToCompleted("Cancelled",Date.from(Instant.now()),userId, appId);
				}
				//Query queryGet = session.createQuery("From Task as query where query.id = "+appId+"");
				result = taskRepository.findByIdIn(taskIdsList);
			} catch (Exception hibernateException) {
				log.error(hibernateException.getMessage(), hibernateException);
				hibernateException.printStackTrace();
				throw hibernateException;
			}
			return result;
		}


		@Transactional
		public List<Task> toDoTasks(List<Integer> taskIdsList, int userId, String jobStatus, int jobId) {
			
			List<Task> result = new ArrayList<Task>();
			try {
				
				//Query query = session.createQuery("update JobQuery set status = '"+jobStatus+"', updateddate = CURDATE(), updateduserid= "+userId+" where id="+jobId+"");
				jobQueryRepository.updateJobStatus(jobStatus,Date.from(Instant.now()), userId, jobId);
				for (Integer appId : taskIdsList) {
					//Query task = session.createQuery("update Task set status = 'To Do', updateddate = CURDATE(), updateduserid= "+userId+" where id="+appId+"");
					taskRepository.updateTaskToCompleted("To Do",Date.from(Instant.now()),userId, appId);
				}
				//Query queryGet = session.createQuery("From Task as query where query.id = "+appId+"");
				result = taskRepository.findByIdIn(taskIdsList);
			} catch (Exception hibernateException) {
				log.error(hibernateException.getMessage(), hibernateException);
				hibernateException.printStackTrace();
				throw hibernateException;
			}
			return result;
		}


		@Transactional
		public List<Task> inProgressTasks(List<Integer> taskIdsList, int userId, String jobStatus, int jobId) {
			
			List<Task> result = new ArrayList<Task>();
			try {
				
				//Query query = session.createQuery("update JobQuery set status = 'In Progress', updateddate = CURDATE(), updateduserid= "+userId+" where id="+jobId+"");
				jobQueryRepository.updateJobStatus(jobStatus,Date.from(Instant.now()), userId, jobId);
				
				for (Integer appId : taskIdsList) {
					//Query task = session.createQuery("update Task set status = 'In Progress', updateddate = CURDATE(), updateduserid= "+userId+" where id="+appId+"");
					taskRepository.updateTaskToCompleted("In Progress",Date.from(Instant.now()),userId, appId);
				}
				//Query queryGet = session.createQuery("From Task as query where query.id = "+appId+"");
				result = taskRepository.findByIdIn(taskIdsList);
			} catch (Exception hibernateException) {
				log.error(hibernateException.getMessage(), hibernateException);
				hibernateException.printStackTrace();
				throw hibernateException;
			}
			
			return result;
		}


		public List<Task> generateTasksReport(String parentQuery) {
			
	        List<Task> results = new ArrayList<Task>();
	        
	        try {
	                transaction = session.beginTransaction();
	                results = (List<Task>) session.createQuery(parentQuery).setCacheable(true).setCacheRegion("commonregion").list();
	                transaction.commit();
	        } catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
	                
	                hibernateException.printStackTrace();

	        } finally {
	    			HibernateUtil.closeSession();
	        }
	        return results;
}


		public List<JobQuery> viewOneJobDetails(int jobId) {
			
	        List<JobQuery> results = new ArrayList<JobQuery>();
	        
	        try {
	                transaction = session.beginTransaction();
	                results = (List<JobQuery>) session.createQuery("from JobQuery where id="+jobId+"").list();
	                transaction.commit();
	        } catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(), hibernateException);
	                
	                hibernateException.printStackTrace();

	        } finally {
	    			HibernateUtil.closeSession();
	        }
	        return results;
		}

}
