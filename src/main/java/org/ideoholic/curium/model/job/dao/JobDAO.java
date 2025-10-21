package org.ideoholic.curium.model.job.dao;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.transaction.Transactional;

import org.ideoholic.curium.model.job.dto.JobQuery;
import org.ideoholic.curium.model.task.dto.Task;
import org.ideoholic.curium.repositories.JobQueryRepository;
import org.ideoholic.curium.repositories.TaskRepository;
import org.ideoholic.curium.util.DateUtil;
import org.ideoholic.curium.util.QueryUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.ObjectUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JobDAO {

	private final JobQueryRepository jobQueryRepository;
	private final TaskRepository taskRepository;
	private final QueryUtil queryUtil;

	@Transactional
	public String addQuery(JobQuery query) {
		String queryNo = null;
		try {
			// session.save(query);
			query = jobQueryRepository.save(query);
			queryNo = query.getExternalid() + ":" + query.getId();
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return queryNo;
	}

	@Transactional
	public List<JobQuery> readListOfObjectsPagination(int offset, int noOfRecords, int branchId) {

		List<JobQuery> results = new ArrayList<JobQuery>();

		try {
			// Query query = session.createQuery("From JobQuery as query where query.branchid = "+branchId+" order by query.id desc");
			// query.setFirstResult(offset);   
			// query.setMaxResults(noOfRecords);
			Pageable pageable = PageRequest.of(offset, noOfRecords);
			Page<JobQuery> page = jobQueryRepository.findByBranchidOrderByIdDesc(branchId, pageable);
			results = page.getContent();
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return results;
	}

	@Transactional
	public int getNoOfRecords(int branchId) {
		int noOfRecords = 0;
		try {
			// results = (List<JobQuery>) session.createQuery("From JobQuery where branchid="+branchId).list();
			noOfRecords = jobQueryRepository.countByBranchid(branchId);
			log.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: " + noOfRecords);
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return noOfRecords;
	}

	@Transactional
	public int getNoOfRecords() {
		int noOfRecords = 0;
		try {
			// results = (List<JobQuery>) session.createQuery("From JobQuery where status !='Cancelled'")
			noOfRecords = jobQueryRepository.countByStatusNot("Cancelled");
			log.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: " + noOfRecords);

		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return noOfRecords;
	}

	@Transactional
	public List<JobQuery> completeQueries(List<Integer> queryIdsList, int userId) {
		List<JobQuery> result = new ArrayList<JobQuery>();
		try {
			// Query query = session.createQuery("update JobQuery set status = 'Completed', updateddate = CURDATE(), updateduserid= "+userId+" where id="+appId+"");
			jobQueryRepository.updateJobStatus(queryIdsList, "Completed", userId, Date.from(Instant.now()));
			result = jobQueryRepository.findAllById(queryIdsList);
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return result;
	}

	@Transactional
	public boolean cancelQueries(List<Integer> queryIdsList, int userId) {

		boolean result = false;
		try {
			// Query query = session.createQuery("update JobQuery set status = 'Cancelled',  updateduserid= "+userId+", updateddate=CURDATE()  where id="+appId+"");
			jobQueryRepository.updateJobStatus(queryIdsList, "Cancelled", userId, Date.from(Instant.now()));
			result = true;
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return result;
	}

	@Transactional
	public boolean inProgressQueries(List<Integer> queryIdsList, int userId) {

		boolean result = false;
		try {
			// Query query = session.createQuery("update JobQuery set status = 'In Progress', updateduserid= "+userId+", updateddate=CURDATE()   where id="+appId+"");
			jobQueryRepository.updateJobStatus(queryIdsList, "In Progress", userId, Date.from(Instant.now()));
			result = true;
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}

		return result;
	}

	@Transactional
	public JobQuery viewQueryDetails(int queryId) {
		JobQuery parentQuery = null;
		try {
			// Query query = session.createQuery("from JobQuery where id="+queryId+"");	
			parentQuery = jobQueryRepository.findById(queryId).orElse(new JobQuery());
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return parentQuery;
	}

	@Transactional
	public boolean updateQueries(String queryId, String parentQuery, String response, int userId) {
		int qid = Integer.parseInt(queryId);
		boolean result = false;
		try {
			// Query query = session.createQuery("update JobQuery set query = '"+parentQuery+"', response='"+response+"', updateduserid= "+userId+", updateddate = CURDATE() where id="+queryId+"");
			jobQueryRepository.updateJobQuery(qid, parentQuery, response, userId, Date.from(Instant.now()));
			result = true;
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return result;
	}

	@Transactional
	public List<JobQuery> readListOfObjectsPaginationDepartmentWise(int offset, int noOfRecords, int branchId,
			int tid) {

		List<JobQuery> results = new ArrayList<JobQuery>();

		try {
			// Query query = session.createQuery("From JobQuery as query where query.branchid = "+branchId+" and query.teacher.tid='"+tid+"' order by query.id desc").setCacheable(true).setCacheRegion("commonregion");
			// query.setFirstResult(offset);   
			// query.setMaxResults(noOfRecords);
			PageRequest pageRequest = PageRequest.of(offset / noOfRecords, noOfRecords);
			results = jobQueryRepository.findByBranchIdAndTeacherTid(branchId, tid, pageRequest);
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return results;
	}

	@Transactional
	public int getNoOfRecordsDepartmentWise(int branchId, int tid) {
		int noOfRecords = 0;
		try {
			// results = (List<JobQuery>) session.createQuery("From JobQuery where teacher.tid='"+tid+"' and branchid="+branchId).setCacheable(true).setCacheRegion("commonregion")
			noOfRecords = jobQueryRepository.countByTeacherIdAndBranchId(tid, branchId);
			log.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: " + noOfRecords);

		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return noOfRecords;
	}

	@Transactional
	public int getNoOfRecordsMonthly(String fromDate, String toDate) {
		Date fromdate = DateUtil.dateParserdd(fromDate);
		Date todate = DateUtil.dateParserdd(toDate);
		List<JobQuery> results = new ArrayList<JobQuery>();
		int noOfRecords = 0;
		try {
			// results = (List<JobQuery>) session.createQuery("From JobQuery where (createddate between '"+fromDate+"' and '"+toDate+"')  and status !='Cancelled'")
			results = jobQueryRepository.findByCreateddateBetweenAndStatusNot(fromdate, todate, "Cancelled");
			noOfRecords = results.size();
			log.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: " + noOfRecords);

		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return noOfRecords;
	}

	@Transactional
	public int getNoOfRecordsResolvedQueries() {

		int noOfRecords = 0;
		try {
			// (List<JobQuery>) session.createQuery("From JobQuery where status = 'Completed'").setCacheable(true).setCacheRegion("commonregion")
			noOfRecords = jobQueryRepository.countByStatus("Completed");

			log.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: " + noOfRecords);

		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return noOfRecords;
	}

	@Transactional
	public int getNoOfRecordsUnResolvedQueries() {

		int noOfRecords = 0;
		try {
			// (List<JobQuery>) session.createQuery("From JobQuery where status = 'To Do'").setCacheable(true).setCacheRegion("commonregion")
			noOfRecords = jobQueryRepository.countByStatus("To Do");
			log.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: " + noOfRecords);

		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return noOfRecords;
	}

	@Transactional
	public int getNoOfRecordsTodayResolvedQueries() {

		int noOfRecords = 0;
		try {
			// (List<JobQuery>) session.createQuery("From JobQuery where status = 'Completed' and createddate = CURDATE()").setCacheable(true).setCacheRegion("commonregion")
			noOfRecords = jobQueryRepository.countByStatusAndCreateddate("Completed", Date.from(Instant.now()));
			log.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: " + noOfRecords);

		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return noOfRecords;

	}

	@Transactional
	public int getNoOfRecordsTodayUnResolvedQueries() {

		List<JobQuery> results = new ArrayList<JobQuery>();
		int noOfRecords = 0;
		try {
			// (List<JobQuery>) session.createQuery("From JobQuery where (status = 'Assigned' or status = 'In Progress') and createddate = CURDATE()").setCacheable(true).setCacheRegion("commonregion")
			results = jobQueryRepository.findByStatusInAndCreateddate(Arrays.asList("Assigned", "In Progress"),
					Date.from(Instant.now()));
			noOfRecords = results.size();
			log.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: " + noOfRecords);

		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return noOfRecords;
	}

	@Transactional
	public List<JobQuery> generateQueriesReport(String parentQuery) {

		List<JobQuery> results = new ArrayList<JobQuery>();

		try {
			// (List<JobQuery>) session.createQuery(parentQuery).setCacheable(true).setCacheRegion("commonregion").list();
			results = queryUtil.runGivenQuery(parentQuery, JobQuery.class);
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return results;
	}

	@Transactional
	public boolean feedback(int queryId, String pid, String feedbackpoints) {
		int ipid = Integer.parseInt(pid);
		boolean result = false;
		try {
			// Query query = session.createQuery("update JobQuery set feedback = '"+feedbackpoints+"' where id="+queryId+" and stdid="+pid+"");
			JobQuery jobQuery = jobQueryRepository.findByQueryIdAndStaffId(queryId, ipid);
			jobQuery.setFeedback(feedbackpoints);
			jobQueryRepository.save(jobQuery);
			result = true;
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return result;
	}

	@Transactional
	public boolean toDoQueries(List<Integer> queryIdsList, int userId) {

		boolean result = false;
		try {
			// Query query = session.createQuery("update JobQuery set status = 'To Do', updateduserid= "+userId+", updateddate=CURDATE()   where id="+appId+"");
			jobQueryRepository.updateJobStatus(queryIdsList, "To Do", userId, Date.from(Instant.now()));
			result = true;
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return result;
	}

	@Transactional
	public boolean updateQueryRemarks(String queryId, String remarks, int userId) {

		int queryid = Integer.parseInt(queryId);
		AtomicBoolean result = new AtomicBoolean(false);
		try {
			// Query query = session.createQuery("update JobQuery set feedback = IFNULL (CONCAT( feedback , '"+remarks+"' ), '"+remarks+"'), updateduserid= "+userId+", updateddate = CURDATE() where id="+queryId+"");
			jobQueryRepository.findById(queryid).ifPresent(jobQuery -> {
				String feedBack = jobQuery.getFeedback();
				if (ObjectUtils.isEmpty(feedBack)) {
					jobQuery.setFeedback(remarks);
				} else {
					jobQuery.setFeedback(feedBack + " " + remarks);
				}
				jobQueryRepository.save(jobQuery);
				result.set(true);
			});
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return result.get();
	}

	public int getNoOfRecordsInProgressQueries() {

		int noOfRecords = 0;
		try {
			// results = (List<JobQuery>) session.createQuery("From JobQuery where status = 'In Progress'").setCacheable(true).setCacheRegion("commonregion")
			noOfRecords = jobQueryRepository.countByStatus("In Progress");

			log.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: " + noOfRecords);

		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();

		}
		return noOfRecords;
	}

	public String addTask(List<Task> taskList, int jobId) {

		String result = "false";
		try {

			JobQuery jobQuery = jobQueryRepository.findById(jobId).orElseGet(() -> {
				JobQuery jobQ = new JobQuery();
				jobQ.setId(jobId);
				return jobQ;
			});

			for (Task task : taskList) {
				task.setJobquery(jobQuery);
				taskRepository.save(task);
			}

			result = "true";

		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}

		return result;
	}

	public List<Task> viewTaksDetails(int jobId) {

		List<Task> results = new ArrayList<Task>();

		try {
			// results = (List<Task>) session.createQuery("from Task where jobid="+jobId+"").list();
			results = taskRepository.findByJobquery_Id(jobId);
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return results;
	}

	@Transactional
	public List<Task> readListOfObjectsPaginationTask(int offset, int noOfRecords, int branchId) {

		List<Task> results = new ArrayList<Task>();

		try {
			// Query query = session.createQuery("From Task as query where query.branchid = "+branchId+" order by query.id desc").setCacheable(true).setCacheRegion("commonregion");
			Pageable pageable = PageRequest.of(offset, noOfRecords);
			results = taskRepository.findByBranchidOrderByIdDesc(branchId, pageable).getContent();

		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return results;
	}

	@Transactional
	public int getNoOfRecordsTask(int branchId) {
		int noOfRecords = 0;
		try {
			// results = (List<Task>) session.createQuery("From Task where branchid="+branchId).setCacheable(true).setCacheRegion("commonregion")
			noOfRecords = taskRepository.countByBranchid(branchId);
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}

		return noOfRecords;
	}

	@Transactional
	public List<Task> readListOfObjectsPaginationDepartmentWiseTask(int offset, int noOfRecords, int branchId, int tid) {

		List<Task> results = new ArrayList<Task>();

		try {
			// Query query = session.createQuery("From Task as query where query.branchid = "+branchId+" and query.teacher.tid='"+tid+"' order by query.id desc");
			PageRequest pageable = PageRequest.of(offset / noOfRecords, noOfRecords);
			results = taskRepository.findByBranchIdAndTeacherTid(branchId, tid, pageable);

		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return results;
	}

	@Transactional
	public int getNoOfRecordsDepartmentWiseTask(int branchId, int tid) {
		int noOfRecords = 0;
		try {
			// session.createQuery("From Task as task where task.teacher.tid='"+tid+"' and task.branchid="+branchId).setCacheable(true).setCacheRegion("commonregion")
			noOfRecords = taskRepository.countByBranchIdAndTeacherTid(branchId, tid);

		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return noOfRecords;
	}

	@Transactional
	public List<Task> completeTasks(List<Integer> taskIdsList, int userId, String jobStatus, int jobId) {

		List<Task> result = new ArrayList<Task>();
		try {
			// Query query = session.createQuery("update JobQuery set status = '"+jobStatus+"', updateddate = CURDATE(), updateduserid= "+userId+" where id="+jobId+"");
			jobQueryRepository.updateJobStatus(jobStatus, Date.from(Instant.now()), userId, jobId);
			for (Integer taskId : taskIdsList) {
				// Query task = session.createQuery("update Task set status = 'Completed', updateddate = CURDATE(), updateduserid= "+userId+" where id="+appId+"");
				taskRepository.updateTaskToCompleted("Completed", Date.from(Instant.now()), userId, taskId);
			}
			// Query queryGet = session.createQuery("From Task as query where query.id = "+appId+"");
			result = taskRepository.findByIdIn(taskIdsList);
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return result;
	}

	@Transactional
	public List<Task> cancelTasks(List<Integer> taskIdsList, int userId, String jobStatus, int jobId) {

		List<Task> result = new ArrayList<Task>();
		try {

			if (jobStatus != null) {
				// Query query = session.createQuery("update JobQuery set status = '"+jobStatus+"', updateddate = CURDATE(), updateduserid= "+userId+" where id="+jobId+"");
				jobQueryRepository.updateJobStatus(jobStatus, Date.from(Instant.now()), userId, jobId);
			}

			for (Integer appId : taskIdsList) {
				// Query task = session.createQuery("update Task set status = 'Cancelled', updateddate = CURDATE(), updateduserid= "+userId+" where id="+appId+"");
				taskRepository.updateTaskToCompleted("Cancelled", Date.from(Instant.now()), userId, appId);
			}
			// Query queryGet = session.createQuery("From Task as query where query.id = "+appId+"");
			result = taskRepository.findByIdIn(taskIdsList);
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return result;
	}

	@Transactional
	public List<Task> toDoTasks(List<Integer> taskIdsList, int userId, String jobStatus, int jobId) {

		List<Task> result = new ArrayList<Task>();
		try {

			// Query query = session.createQuery("update JobQuery set status = '"+jobStatus+"', updateddate = CURDATE(), updateduserid= "+userId+" where id="+jobId+"");
			jobQueryRepository.updateJobStatus(jobStatus, Date.from(Instant.now()), userId, jobId);
			for (Integer appId : taskIdsList) {
				// Query task = session.createQuery("update Task set status = 'To Do', updateddate = CURDATE(), updateduserid= "+userId+" where id="+appId+"");
				taskRepository.updateTaskToCompleted("To Do", Date.from(Instant.now()), userId, appId);
			}
			// Query queryGet = session.createQuery("From Task as query where query.id = "+appId+"");
			result = taskRepository.findByIdIn(taskIdsList);
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return result;
	}

	@Transactional
	public List<Task> inProgressTasks(List<Integer> taskIdsList, int userId, String jobStatus, int jobId) {

		List<Task> result = new ArrayList<Task>();
		try {

			// Query query = session.createQuery("update JobQuery set status = 'In Progress', updateddate = CURDATE(), updateduserid= "+userId+" where id="+jobId+"");
			jobQueryRepository.updateJobStatus(jobStatus, Date.from(Instant.now()), userId, jobId);

			for (Integer appId : taskIdsList) {
				// Query task = session.createQuery("update Task set status = 'In Progress', updateddate = CURDATE(), updateduserid= "+userId+" where id="+appId+"");
				taskRepository.updateTaskToCompleted("In Progress", Date.from(Instant.now()), userId, appId);
			}
			// Query queryGet = session.createQuery("From Task as query where query.id = "+appId+"");
			result = taskRepository.findByIdIn(taskIdsList);
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}

		return result;
	}

	@Transactional
	public List<Task> generateTasksReport(String parentQuery) {

		List<Task> results = new ArrayList<Task>();
		try {
			// results = (List<Task>) session.createQuery(parentQuery).setCacheable(true).setCacheRegion("commonregion").list();
			results = queryUtil.runGivenQuery(parentQuery, Task.class);
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return results;
	}

	@Transactional
	public JobQuery viewOneJobDetails(int jobId) {

		JobQuery results = null;

		try {
			// results = (List<JobQuery>) session.createQuery("from JobQuery where id="+jobId+"").list();
			results = jobQueryRepository.findById(jobId).orElse(new JobQuery());
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();

		}
		return results;
	}

}
