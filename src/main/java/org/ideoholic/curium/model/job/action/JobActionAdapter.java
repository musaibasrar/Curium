package org.ideoholic.curium.model.job.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.documents.dto.SearchStudentResponseDto;
import org.ideoholic.curium.model.job.dto.*;
import org.ideoholic.curium.model.job.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class JobActionAdapter {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	@Autowired
	private HttpSession httpSession;

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
		JobQueryDto jobQueryDto = jobService.viewAllQueriesDepartmentWise(page,httpSession.getAttribute("branchid").toString(),httpSession.getAttribute("username").toString());
		request.setAttribute("studentList", jobQueryDto.getQueriesList());
		request.setAttribute("queryList", jobQueryDto.getQueriesList());
		request.setAttribute("noOfPages", jobQueryDto.getNoOfPages());
		request.setAttribute("currentPage",jobQueryDto.getPage());
		return jobQueryDto.isSuccess();
	}


}