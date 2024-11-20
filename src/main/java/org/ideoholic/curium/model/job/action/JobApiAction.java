/**
 *
 */
package org.ideoholic.curium.model.job.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.documents.dto.SearchStudentResponseDto;
import org.ideoholic.curium.model.employee.dto.ViewAllEmployeeResponseDto;
import org.ideoholic.curium.model.job.dto.AddQueryDto;
import org.ideoholic.curium.model.job.dto.EmployeeResponseDto;
import org.ideoholic.curium.model.job.dto.FeedbackDto;
import org.ideoholic.curium.model.job.dto.JobQueryDto;
import org.ideoholic.curium.model.job.dto.QueriesDto;
import org.ideoholic.curium.model.job.dto.CombinedQueryDto;
import org.ideoholic.curium.model.job.dto.ReportDto;
import org.ideoholic.curium.model.job.dto.ReportResponseDto;
import org.ideoholic.curium.model.job.dto.UpdateQueriesDto;
import org.ideoholic.curium.model.job.dto.ViewStudentResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;


public interface JobApiAction {

	
	  ResponseEntity<ResultResponse> download();

	 ResponseEntity<ResultResponse> exportQueriesReport(@RequestBody JobQueryDto jobQueryDto);
	
	 ResponseEntity<SearchStudentResponseDto> feedback(@RequestBody FeedbackDto feedbackDto);

	ResponseEntity<String> printQueriesReport();

	 ResponseEntity<ReportResponseDto> generateQueriesReport(@RequestBody ReportDto reportDto);

	 ResponseEntity<ViewAllEmployeeResponseDto> queryReport(@RequestHeader(value = "branchid") String branchId);

	 ResponseEntity<JobQueryDto> viewAllQueriesDepartmentWise(@RequestParam(value = "page") String page,
			@RequestHeader(value = "branchid") String branchId,
			@RequestHeader(value = "userloginid") String userLoginId);

	 ResponseEntity<SearchStudentResponseDto> updateQueries(@RequestBody UpdateQueriesDto updateQueriesDto,
			@RequestHeader(value = "userloginid") String userLoginId);

	 ResponseEntity<SearchStudentResponseDto> updateQueryRemarks(@RequestBody CombinedQueryDto combinedQueryDto,
			@RequestHeader(value = "userloginid") String userLoginId);

	 ResponseEntity<ResultResponse>viewQueryDetails(@RequestBody UpdateQueriesDto updateQueriesDto,
			@RequestHeader(value = "branchid") String branchId);

	 ResponseEntity<SearchStudentResponseDto> inProgressQueries(@RequestBody QueriesDto queriesDto,
			@RequestHeader(value = "userloginid") String userLoginId);

	 ResponseEntity<SearchStudentResponseDto> toDoQueries(@RequestBody QueriesDto queriesDto,
			@RequestHeader(value = "userloginid") String userLoginId);

	 ResponseEntity<SearchStudentResponseDto> cancelQueries(@RequestBody QueriesDto queriesDto,
			@RequestHeader(value = "userloginid") String userLoginId);
	
	 ResponseEntity<ReportResponseDto> completeQueries(@RequestBody QueriesDto queriesDto,
			@RequestHeader(value = "userloginid") String userLoginId);
	
	 ResponseEntity<EmployeeResponseDto> createQuery(@RequestHeader(value = "userType") String userType,
			@RequestParam(value = "empId") String empId, @RequestHeader(value = "branchid") String branchId,
			@RequestHeader(value = "username") String userName);

	 ResponseEntity<JobQueryDto> viewAllQueries(@RequestHeader(value = "userType") String userType,
			@RequestParam(value = "page") String page, @RequestHeader(value = "branchid") String branchId,
			@RequestHeader(value = "username") String userName);

	 ResponseEntity<ResultResponse> addQuery(@RequestBody AddQueryDto addQueryDto,
			@RequestHeader(value = "branchid") String branchId,
			@RequestHeader(value = "currentAcademicYear") String currentAcademicYear,
			@RequestHeader(value = "userloginid") String userLoginId);

	 ResponseEntity<JobQueryDto> viewAllTasks(@RequestHeader(value = "userType") String userType,
			@RequestParam(value = "page") String page, @RequestHeader(value = "branchid") String branchId,
			@RequestHeader(value = "username") String userName);
	
	 ResponseEntity<JobQueryDto> viewTaskDetails(@RequestBody CombinedQueryDto combinedQueryDto,
			@RequestHeader(value = "branchid") String branchId);

	 ResponseEntity<JobQueryDto> viewOneJobDetails(@RequestBody QueriesDto queriesDto,
			@RequestHeader(value = "branchid") String branchId);

	 ResponseEntity<JobQueryDto> inProgressTasks(@RequestBody CombinedQueryDto queriesDto,
			@RequestHeader(value = "userType") String userType, @RequestParam(value = "page") String page,
			@RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "username") String userName,
			@RequestHeader(value = "userloginid") String userLoginId);

	 ResponseEntity<JobQueryDto> toDoTasks(@RequestBody CombinedQueryDto queriesDto,
			@RequestHeader(value = "userType") String userType, @RequestParam(value = "page") String page,
			@RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "username") String userName,
			@RequestHeader(value = "userloginid") String userLoginId);

	 ResponseEntity<JobQueryDto> cancelTasks(@RequestBody CombinedQueryDto queriesDto,
			@RequestHeader(value = "userType") String userType, @RequestParam(value = "page") String page,
			@RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "username") String userName,
			@RequestHeader(value = "userloginid") String userLoginId);

	 ResponseEntity<JobQueryDto> completeTasks(@RequestBody CombinedQueryDto queriesDto,
			@RequestHeader(value = "userType") String userType, @RequestParam(value = "page") String page,
			@RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "username") String userName,
			@RequestHeader(value = "userloginid") String userLoginId);
	
	 ResponseEntity<JobQueryDto> updateTaskRemarks(@RequestBody CombinedQueryDto combinedQueryDto,
		    @RequestHeader(value = "userType") String userType,
			@RequestParam(value = "page") String page, @RequestHeader(value = "branchid") String branchId,
			@RequestHeader(value = "username") String userName,
			@RequestHeader(value = "userloginid") String userLoginId);

	 ResponseEntity<EmployeeResponseDto> createTask(@RequestBody QueriesDto queriesDto,
			@RequestHeader(value = "branchid") String branchId,
			@RequestHeader(value = "userloginid") String userLoginId,@RequestHeader(value = "userType")String userType);

	 ResponseEntity<ResultResponse> addTask(@RequestBody QueriesDto queriesDto,
			@RequestHeader(value = "branchid") String branchId);

	 ResponseEntity<ViewStudentResponseDto> taskReport(@RequestHeader(value = "branchid") String branchId);

	 ResponseEntity<ReportResponseDto> generateTasksReport(@RequestBody ReportDto reportDto);

	 ResponseEntity<String> printTasksReport();
	
	 ResponseEntity mrvDetails(@RequestBody ReportDto reportDto,
			@RequestHeader(value = "branchid") String branchId);
}
