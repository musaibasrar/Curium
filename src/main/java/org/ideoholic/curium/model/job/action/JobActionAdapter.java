package org.ideoholic.curium.model.job.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.documents.dto.SearchStudentResponseDto;
import org.ideoholic.curium.model.job.dto.*;
import org.ideoholic.curium.model.job.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Service
public class JobActionAdapter {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	@Autowired
	private HttpSession httpSession;
	
	private String BRANCHID = "branchid";
	   
	private String CURRENTACADEMICYEAR = "currentAcademicYear";
	
	private String USERLOGINID = "userloginid";
	
	private String USERNAME = "username";

	public boolean download() {
		JobService jobService = new JobService(request, response);
		ResultResponse result = jobService.download();
		return result.isSuccess();
	}

	public boolean exportQueriesReport() {
		JobService jobService = new JobService(request, response);
		JobQueryDto jobQueryDto = new JobQueryDto();
		jobQueryDto.setQueriesList((List<JobQuery>)httpSession.getAttribute("parentquerylist"));
		ResultResponse result = jobService.exportQueriesReport(jobQueryDto);
		return result.isSuccess();

	}

	public boolean feedback() {
		JobService jobService = new JobService(request, response);
		FeedbackDto feedbackDto = new FeedbackDto();
		feedbackDto.setId(request.getParameter("id"));
		feedbackDto.setNo(request.getParameter("no"));
		feedbackDto.setFeedback(request.getParameter("feedback"));
		SearchStudentResponseDto searchStudentResponseDto = jobService.feedback(feedbackDto);
		return searchStudentResponseDto.isSuccess();
	}

	public void generateQueriesReport() {
		JobService jobService = new JobService(request, response);
		ReportDto reportDto = new ReportDto();
		reportDto.setTransactionDateFrom(request.getParameter("transactiondatefrom"));
		reportDto.setTransactionDateTo(request.getParameter("transactiondateto"));
		reportDto.setStatus(request.getParameter("status"));
		reportDto.setStaffId(request.getParameter("staffId"));
		reportDto.setStaffName(request.getParameter("staffName"));
		ReportResponseDto reportResponseDto = jobService.generateQueriesReport(reportDto);
		httpSession.setAttribute("statusselected",reportResponseDto.getStatusSelected());
		httpSession.setAttribute("staffselected",reportResponseDto.getStaffSelected());
		httpSession.setAttribute("parentquerylist",reportResponseDto.getJobQueryList());
		httpSession.setAttribute("transactionfromdateselected", reportResponseDto.getTransactionFromDateSelected());
		httpSession.setAttribute("transactiontodateselected", reportResponseDto.getTransactionToDateSelected());


	}

	public boolean viewAllQueriesDepartmentWise() {
		String page = request.getParameter("page");
		JobService jobService = new JobService(request, response);
		JobQueryDto jobQueryDto = jobService.viewAllQueriesDepartmentWise(page,httpSession.getAttribute(BRANCHID).toString(),httpSession.getAttribute(USERNAME).toString());
		request.setAttribute("studentList", jobQueryDto.getQueriesList());
		request.setAttribute("queryList", jobQueryDto.getQueriesList());
		request.setAttribute("noOfPages", jobQueryDto.getNoOfPages());
		request.setAttribute("currentPage",jobQueryDto.getPage());
		return jobQueryDto.isSuccess();
	}

	public void updateQueries() {
		JobService jobService = new JobService(request, response);
		UpdateQueriesDto updateQueriesDto = new UpdateQueriesDto();
		updateQueriesDto.setQueryId(request.getParameter("queryid"));
        updateQueriesDto.setJobQuery(request.getParameter("JobQuery"));
        updateQueriesDto.setResponse(request.getParameter("response"));
        SearchStudentResponseDto searchStudentResponseDto = jobService.updateQueries(updateQueriesDto,httpSession.getAttribute(USERLOGINID).toString());
        request.setAttribute("querystatus",searchStudentResponseDto.isSuccess());
		
	}

	public void updateQueryRemarks() {
		JobService jobService = new JobService(request, response);
		UpdateQueriesDto updateQueriesDto = new UpdateQueriesDto();
		updateQueriesDto.setQueryId(request.getParameter("queryid"));
		updateQueriesDto.setQueryRemarks(request.getParameter("queryremarks"));
		SearchStudentResponseDto searchStudentResponseDto = jobService.updateQueryRemarks(updateQueriesDto,httpSession.getAttribute(USERLOGINID).toString());
		request.setAttribute("querystatus",searchStudentResponseDto.isSuccess());
		
	}

	public void viewQueryDetails() throws IOException {
		JobService jobService = new JobService(request, response);
		UpdateQueriesDto updateQueriesDto = new UpdateQueriesDto();
		updateQueriesDto.setQueryId(request.getParameter("id"));
		jobService.viewQueryDetails(updateQueriesDto,httpSession.getAttribute(BRANCHID).toString());
		
	}

	public void inProgressQueries() {
		JobService jobService = new JobService(request, response);
		QueriesDto queriesDto = new QueriesDto();
		queriesDto.setQueryIds(request.getParameterValues("queryids"));
		SearchStudentResponseDto searchStudentResponseDto = jobService.inProgressQueries(queriesDto,httpSession.getAttribute(USERLOGINID).toString());
		request.setAttribute("querystatus",searchStudentResponseDto.isSuccess());
	}

	public void toDoQueries() {
		JobService jobService = new JobService(request, response);
		QueriesDto queriesDto = new QueriesDto();
		queriesDto.setQueryIds(request.getParameterValues("queryids"));
		SearchStudentResponseDto searchStudentResponseDto = jobService.toDoQueries(queriesDto,httpSession.getAttribute(USERLOGINID).toString());
		request.setAttribute("querystatus",searchStudentResponseDto.isSuccess());
		
	}

	public void cancelQueries() {
		JobService jobService = new JobService(request, response);
		QueriesDto queriesDto = new QueriesDto();
		queriesDto.setQueryIds(request.getParameterValues("queryids"));
		SearchStudentResponseDto searchStudentResponseDto = jobService.cancelQueries(queriesDto,httpSession.getAttribute(USERLOGINID).toString());
		request.setAttribute("querystatus",searchStudentResponseDto.isSuccess());
	}

	public void completeQueries() {
		JobService jobService = new JobService(request, response);
		QueriesDto queriesDto = new QueriesDto();
		queriesDto.setQueryIds(request.getParameterValues("queryids"));
		ReportResponseDto reportResponseDto = jobService.completeQueries(queriesDto,httpSession.getAttribute(USERLOGINID).toString());
		request.setAttribute("querycompleted",reportResponseDto.getQuerycompleted());
		request.setAttribute("querystatus",reportResponseDto.isSuccess());
	}

	public boolean viewAllQueries() {
		String page = request.getParameter("page");
		JobService jobService = new JobService(request, response);
		JobQueryDto jobQueryDto = jobService.viewAllQueries(page,httpSession.getAttribute(BRANCHID).toString());
		request.setAttribute("queryList", jobQueryDto.getQueriesList());
		request.setAttribute("noOfPages", jobQueryDto.getNoOfPages());
		request.setAttribute("currentPage", jobQueryDto.getCurrentPage());
		return jobQueryDto.isSuccess();
	}

	public boolean addQuery() {
		JobService jobService = new JobService(request, response);
		AddQueryDto addQueryDto = new AddQueryDto();
		addQueryDto.setEmployeeIDs(request.getParameterValues("employeeIDs"));
		addQueryDto.setJobquery(request.getParameter("jobquery"));
		addQueryDto.setJobtitle(request.getParameter("jobtitle"));
		addQueryDto.setExpecteddeliverydate(request.getParameter("expecteddeliverydate"));
		addQueryDto.setAssignto(request.getParameterValues("assignto"));
		addQueryDto.setTask(request.getParameterValues("task"));
		addQueryDto.setDescription(request.getParameterValues("description"));
		addQueryDto.setExpecteddd(request.getParameterValues("expecteddeliverydatetask"));
		ResultResponse response = jobService.addQuery(addQueryDto,httpSession.getAttribute(BRANCHID).toString(),httpSession.getAttribute(CURRENTACADEMICYEAR).toString(),httpSession.getAttribute(USERLOGINID).toString());
		return response.isSuccess();
	}

	public boolean viewAllTasks() {
		JobService jobService = new JobService(request, response);
		String page = request.getParameter("page");
		JobQueryDto jobQueryDto = jobService.viewAllTasks(page,httpSession.getAttribute(BRANCHID).toString());
		request.setAttribute("taskdetails", jobQueryDto.getTaskList());
		request.setAttribute("noOfPages", jobQueryDto.getNoOfPages());
		request.setAttribute("currentPage", jobQueryDto.getCurrentPage());
		return jobQueryDto.isSuccess();
	}

	public boolean viewAllTasksDepartmentWise() {
		JobService jobService = new JobService(request, response);
		String page = request.getParameter("page");
		JobQueryDto jobQueryDto = jobService.viewAllTasksDepartmentWise(page,httpSession.getAttribute(BRANCHID).toString(),httpSession.getAttribute(USERNAME).toString());
		request.setAttribute("taskdetails", jobQueryDto.getTaskList());
		request.setAttribute("noOfPages", jobQueryDto.getNoOfPages());
		request.setAttribute("currentPage", jobQueryDto.getPage());
		return jobQueryDto.isSuccess();
	}

	public boolean viewTaskDetails() {
		JobService jobService = new JobService(request, response);
		QueriesDto queriesDto = new QueriesDto();
		queriesDto.setJobId(request.getParameter("jobid"));
		JobQueryDto jobQueryDto = jobService.viewTaskDetails(queriesDto,httpSession.getAttribute(BRANCHID).toString());
		request.setAttribute("taskdetails",jobQueryDto.getTaskList());
		return jobQueryDto.isSuccess();
	}


}