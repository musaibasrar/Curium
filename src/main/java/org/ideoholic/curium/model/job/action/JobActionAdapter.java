package org.ideoholic.curium.model.job.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.documents.dto.SearchStudentResponseDto;
import org.ideoholic.curium.model.job.dto.AddQueryDto;
import org.ideoholic.curium.model.job.dto.FeedbackDto;
import org.ideoholic.curium.model.job.dto.JobQuery;
import org.ideoholic.curium.model.job.dto.JobQueryDto;
import org.ideoholic.curium.model.job.dto.QueriesDto;
import org.ideoholic.curium.model.job.dto.ReportDto;
import org.ideoholic.curium.model.job.dto.ReportResponseDto;
import org.ideoholic.curium.model.job.dto.UpdateQueriesDto;
import org.ideoholic.curium.model.job.service.JobService;
import org.ideoholic.curium.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobActionAdapter {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private JobService jobService;

	public boolean download() {
		ResultResponse result = jobService.download();
		return result.isSuccess();
	}

	public boolean exportQueriesReport() {
		JobQueryDto jobQueryDto = new JobQueryDto();
		jobQueryDto.setQueriesList((List<JobQuery>)httpSession.getAttribute("parentquerylist"));
		ResultResponse result = jobService.exportQueriesReport(jobQueryDto);
		return result.isSuccess();

	}

	public boolean feedback() {
		FeedbackDto feedbackDto = new FeedbackDto();
		feedbackDto.setId(request.getParameter("id"));
		feedbackDto.setNo(request.getParameter("no"));
		feedbackDto.setFeedback(request.getParameter("feedback"));
		SearchStudentResponseDto searchStudentResponseDto = jobService.feedback(feedbackDto);
		return searchStudentResponseDto.isSuccess();
	}

	public void generateQueriesReport() {
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
		JobQueryDto jobQueryDto = jobService.viewAllQueriesDepartmentWise(page,httpSession.getAttribute(Constants.BRANCHID).toString(),httpSession.getAttribute(Constants.USERNAME).toString());
		request.setAttribute("studentList", jobQueryDto.getQueriesList());
		request.setAttribute("queryList", jobQueryDto.getQueriesList());
		request.setAttribute("noOfPages", jobQueryDto.getNoOfPages());
		request.setAttribute("currentPage",jobQueryDto.getPage());
		return jobQueryDto.isSuccess();
	}

	public void updateQueries() {
		UpdateQueriesDto updateQueriesDto = new UpdateQueriesDto();
		updateQueriesDto.setQueryId(request.getParameter("queryid"));
        updateQueriesDto.setJobQuery(request.getParameter("JobQuery"));
        updateQueriesDto.setResponse(request.getParameter("response"));
        SearchStudentResponseDto searchStudentResponseDto = jobService.updateQueries(updateQueriesDto,httpSession.getAttribute(Constants.USERID).toString());
        request.setAttribute("querystatus",searchStudentResponseDto.isSuccess());
		
	}

	public void updateQueryRemarks() {
		UpdateQueriesDto updateQueriesDto = new UpdateQueriesDto();
		updateQueriesDto.setQueryId(request.getParameter("queryid"));
		updateQueriesDto.setQueryRemarks(request.getParameter("queryremarks"));
		SearchStudentResponseDto searchStudentResponseDto = jobService.updateQueryRemarks(updateQueriesDto,httpSession.getAttribute(Constants.USERID).toString());
		request.setAttribute("querystatus",searchStudentResponseDto.isSuccess());
		
	}

	
	public void inProgressQueries() {
		QueriesDto queriesDto = new QueriesDto();
		queriesDto.setQueryIds(request.getParameterValues("queryids"));
		SearchStudentResponseDto searchStudentResponseDto = jobService.inProgressQueries(queriesDto,httpSession.getAttribute(Constants.USERID).toString());
		request.setAttribute("querystatus",searchStudentResponseDto.isSuccess());
	}

	public void toDoQueries() {
		QueriesDto queriesDto = new QueriesDto();
		queriesDto.setQueryIds(request.getParameterValues("queryids"));
		SearchStudentResponseDto searchStudentResponseDto = jobService.toDoQueries(queriesDto,httpSession.getAttribute(Constants.USERID).toString());
		request.setAttribute("querystatus",searchStudentResponseDto.isSuccess());
		
	}

	public void cancelQueries() {
		QueriesDto queriesDto = new QueriesDto();
		queriesDto.setQueryIds(request.getParameterValues("queryids"));
		SearchStudentResponseDto searchStudentResponseDto = jobService.cancelQueries(queriesDto,httpSession.getAttribute(Constants.USERID).toString());
		request.setAttribute("querystatus",searchStudentResponseDto.isSuccess());
	}

	public void completeQueries() {
		QueriesDto queriesDto = new QueriesDto();
		queriesDto.setQueryIds(request.getParameterValues("queryids"));
		ReportResponseDto reportResponseDto = jobService.completeQueries(queriesDto,httpSession.getAttribute(Constants.USERID).toString());
		request.setAttribute("querycompleted",reportResponseDto.getQuerycompleted());
		request.setAttribute("querystatus",reportResponseDto.isSuccess());
	}

	public boolean viewAllQueries() {
		String page = request.getParameter("page");
		JobQueryDto jobQueryDto = jobService.viewAllQueries(page,httpSession.getAttribute(Constants.BRANCHID).toString());
		request.setAttribute("queryList", jobQueryDto.getQueriesList());
		request.setAttribute("noOfPages", jobQueryDto.getNoOfPages());
		request.setAttribute("currentPage", jobQueryDto.getCurrentPage());
		request.setAttribute("studentList",jobQueryDto.getStudentList());
		return jobQueryDto.isSuccess();
	}

	public boolean addQuery() {
		AddQueryDto addQueryDto = new AddQueryDto();
		addQueryDto.setEmployeeIDs(request.getParameterValues("employeeIDs"));
		addQueryDto.setJobquery(request.getParameter("jobquery"));
		addQueryDto.setJobtitle(request.getParameter("jobtitle"));
		addQueryDto.setExpecteddeliverydate(request.getParameter("expecteddeliverydate"));
		addQueryDto.setAssignto(request.getParameterValues("assignto"));
		addQueryDto.setTask(request.getParameterValues("task"));
		addQueryDto.setDescription(request.getParameterValues("description"));
		addQueryDto.setExpecteddeliverydatetask(request.getParameterValues("expecteddeliverydatetask"));
		ResultResponse response = jobService.addQuery(addQueryDto,httpSession.getAttribute(BRANCHID).toString(),httpSession.getAttribute(CURRENTACADEMICYEAR).toString(),httpSession.getAttribute(USERLOGINID).toString());
		return response.isSuccess();
	}

	public boolean viewAllTasks() {
		String page = request.getParameter("page");
		JobQueryDto jobQueryDto = jobService.viewAllTasks(page,httpSession.getAttribute(Constants.BRANCHID).toString());
		request.setAttribute("taskdetails", jobQueryDto.getTaskList());
		request.setAttribute("noOfPages", jobQueryDto.getNoOfPages());
		request.setAttribute("currentPage", jobQueryDto.getCurrentPage());
		request.setAttribute("studentList", jobQueryDto.getTaskList());
		return jobQueryDto.isSuccess();
	}

	public boolean viewAllTasksDepartmentWise() {
		String page = request.getParameter("page");
		JobQueryDto jobQueryDto = jobService.viewAllTasksDepartmentWise(page,httpSession.getAttribute(Constants.BRANCHID).toString(),httpSession.getAttribute(Constants.USERNAME).toString());
		request.setAttribute("taskdetails", jobQueryDto.getTaskList());
		request.setAttribute("noOfPages", jobQueryDto.getNoOfPages());
		request.setAttribute("currentPage", jobQueryDto.getPage());
		return jobQueryDto.isSuccess();
	}

	public boolean viewTaskDetails() {
		QueriesDto queriesDto = new QueriesDto();
		queriesDto.setJobId(request.getParameter("jobid"));
		JobQueryDto jobQueryDto = jobService.viewTaskDetails(queriesDto,httpSession.getAttribute(Constants.BRANCHID).toString());
		request.setAttribute("taskdetails",jobQueryDto.getTaskList());
		return jobQueryDto.isSuccess();
	}

	public boolean viewOneJobDetails() {
		QueriesDto queriesDto = new QueriesDto();
		queriesDto.setJobId(request.getParameter("jobid"));
		JobQueryDto jobQueryDto = jobService.viewOneJobDetails(queriesDto,httpSession.getAttribute(Constants.BRANCHID).toString());
		request.setAttribute("queryList",jobQueryDto.getQueriesList());
		return jobQueryDto.isSuccess();
	}

	public void inProgressTasks() {
		QueriesDto queriesDto = new QueriesDto();
		queriesDto.setJobId(request.getParameter("jobid"));
		queriesDto.setTaskIds(request.getParameterValues("taskids"));
		JobQueryDto jobQueryDto = jobService.inProgressTasks(queriesDto,httpSession.getAttribute(Constants.USERID).toString());
		request.setAttribute("querystatus",jobQueryDto.isSuccess());
	}

	public void toDoTasks() {
		QueriesDto queriesDto = new QueriesDto();
		queriesDto.setTaskIds(request.getParameterValues("taskids"));
		queriesDto.setJobId(request.getParameter("jobid"));
		JobQueryDto jobQueryDto = jobService.toDoTasks(queriesDto,httpSession.getAttribute(Constants.USERID).toString());
		request.setAttribute("querystatus",jobQueryDto.isSuccess());
	}

	public void cancelTasks() {
		QueriesDto queriesDto = new QueriesDto();
		queriesDto.setTaskIds(request.getParameterValues("taskids"));
		queriesDto.setJobId(request.getParameter("jobid"));
		JobQueryDto jobQueryDto = jobService.cancelTasks(queriesDto,httpSession.getAttribute(Constants.USERID).toString());
		request.setAttribute("querystatus",jobQueryDto.isSuccess());
		
	}

	public void completeTasks() {
		QueriesDto queriesDto = new QueriesDto();
		queriesDto.setTaskIds(request.getParameterValues("taskids"));
		queriesDto.setJobId(request.getParameter("jobid"));
		JobQueryDto jobQueryDto = jobService.completeTasks(queriesDto,httpSession.getAttribute(Constants.USERID).toString());
		request.setAttribute("querycompleted",jobQueryDto.getQuerycompleted());
		request.setAttribute("querystatus",jobQueryDto.isSuccess());
		
	}

	public void createTask() {
		QueriesDto queriesDto = new QueriesDto();
		queriesDto.setJobId(request.getParameter("jobid"));
		queriesDto.setJobno(request.getParameter("jobno"));
		JobQueryDto jobQueryDto = jobService.createTask(queriesDto);
		request.setAttribute("jobid",jobQueryDto.getJobId());
		request.setAttribute("jobno",jobQueryDto.getJobno());
	}

	public boolean addTask() {
		QueriesDto queriesDto = new QueriesDto();
		queriesDto.setJobId(request.getParameter("jobid"));
		queriesDto.setAssignto(request.getParameterValues("assignto"));
		queriesDto.setTask(request.getParameterValues("task"));
		queriesDto.setDescription(request.getParameterValues("description"));
		queriesDto.setExpecteddd(request.getParameterValues("expecteddeliverydatetask"));
		ResultResponse response = jobService.addTask(queriesDto,httpSession.getAttribute(Constants.BRANCHID).toString());
		return response.isSuccess();
	}

	public void generateTasksReport() {
		ReportDto reportDto = new ReportDto();
		reportDto.setTransactionDateFrom(request.getParameter("transactiondatefrom"));
		reportDto.setTransactionDateTo(request.getParameter("transactiondateto"));
		reportDto.setEmployee(request.getParameter("employee"));
		reportDto.setStatus(request.getParameter("status"));
		reportDto.setStaffName(request.getParameter("staffName"));
		ReportResponseDto reportResponseDto = jobService.generateTasksReport(reportDto);
		httpSession.setAttribute("teacherselected", reportResponseDto.getStaffSelected());
		httpSession.setAttribute("statusselected", reportResponseDto.getStatusSelected());
		httpSession.setAttribute("studentselected", reportResponseDto.getStudentselected());
		httpSession.setAttribute("parenttaskslist", reportResponseDto.getTaskList());
		httpSession.setAttribute("transactionfromdateselected", reportResponseDto.getTransactionFromDateSelected());
		httpSession.setAttribute("transactiontodateselected", reportResponseDto.getTransactionToDateSelected());
		
	}

	public void getReferredbyDetails() throws IOException {
		ReportDto reportDto = new ReportDto();
		reportDto.setReferredby(request.getParameter("referredby"));
		jobService.getReferredbyDetails(reportDto,httpSession.getAttribute(Constants.BRANCHID).toString());
		
	}
	
	public void viewQueryDetails() throws IOException {

		UpdateQueriesDto updateQueriesDto = new UpdateQueriesDto();
		updateQueriesDto.setQueryId(request.getParameter("id"));
		if (httpSession.getAttribute(Constants.BRANCHID) != null) {
			PrintWriter out = response.getWriter();

			try {
				int queryId = Integer.parseInt(updateQueriesDto.getQueryId());

				ResultResponse result = jobService.viewQueryDetails(updateQueriesDto,
						httpSession.getAttribute(Constants.BRANCHID).toString());

				if (!result.isSuccess()) {
					throw new IOException("Failed to retrive data");
				}
				response.setContentType("text/xml");
				response.setHeader("Cache-Control", "no-cache");

				StringBuilder tableBuilder = new StringBuilder("<table  style='margin-left: auto;margin-right: auto;'>"
						+ "						<tr>" + "							" + "						</tr>"
						+ "					</table>"

				);

				StringBuilder rowBuidler = new StringBuilder(
						"<table border='0' style='margin-left: auto;margin-right: auto;' style='border-color:#4b6a84' id='querydetailspopup'>");

				rowBuidler.append("<tr style='border-color:#000000' border='0' cellpadding='1' cellspacing='1'>"
						+ "<td class='alignLeft'>Query:</td>"
						+ "<td class='dataText'><textarea name='JobQuerypopup' id='JobQuerypopup' rows='5' cols='38'>"
						+ result.getMessage() + "</textarea>"
						+ "<input type='hidden' id='queryid' name='queryid' value='" + queryId + "'></td>" + "</tr>"
						+ "<tr>" + "<td><br><br></td>" + "</tr>"
						+ "<tr style='border-color:#000000' border='1' cellpadding='1' cellspacing='1' >"
						+ "<td class='alignLeft'>Response:</td>"
						+ "<td class='dataText'><textarea name='responsepopup' id='responsepopup' rows='5' cols='38'>"
						+ result.getMessage() + "</textarea></td>" + "</tr>");

				rowBuidler.append("</tbody>" + "		                </table>");

				tableBuilder.append(rowBuidler.toString());
				String outputTable = tableBuilder.toString();

				response.getWriter().println(outputTable);

			} catch (Exception e) {
				out.write("<table> <tr><td>Data Not Available</td></tr></table>");
			} finally {
				out.flush();
				out.close();
			}
		}


	}



}