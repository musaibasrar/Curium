/**
 *
 */
package org.ideoholic.curium.model.job.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.exceptions.CustomErrorMessage;
import org.ideoholic.curium.exceptions.CustomResponseException;
import org.ideoholic.curium.model.documents.dto.SearchStudentResponseDto;
import org.ideoholic.curium.model.employee.dto.EmployeeDetailsResponseDto;
import org.ideoholic.curium.model.employee.dto.EmployeesWithSalaryResponseDto;
import org.ideoholic.curium.model.employee.dto.EmployeesWithSalaryResponseDto;
import org.ideoholic.curium.model.employee.service.EmployeeService;
import org.ideoholic.curium.model.feescategory.dto.StudentListResponseDto;
import org.ideoholic.curium.model.job.dto.AddQueryDto;
import org.ideoholic.curium.model.job.dto.TaskQueryDto;
import org.ideoholic.curium.model.job.dto.EmployeeResponseDto;
import org.ideoholic.curium.model.job.dto.FeedbackDto;
import org.ideoholic.curium.model.job.dto.JobQueryDto;
import org.ideoholic.curium.model.job.dto.QueriesDto;
import org.ideoholic.curium.model.job.dto.ReportDto;
import org.ideoholic.curium.model.job.dto.ReportResponseDto;
import org.ideoholic.curium.model.job.dto.UpdateQueriesDto;
import org.ideoholic.curium.model.job.dto.TaskReportResponseDto;
import org.ideoholic.curium.model.job.service.JobService;
import org.ideoholic.curium.model.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Musaib_2
 *
 */
@Controller
@RequestMapping("/api/v1/jobProcess")
public class JobApiActionImpl implements JobApiAction {

	 @Autowired
	 HttpServletRequest request;
	@Autowired
	private JobService jobService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private StudentService studentService;

	@PostMapping("/download")
	public ResponseEntity<ResultResponse> download() {
		ResultResponse result = jobService.download();
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		}
		throw new CustomResponseException(CustomErrorMessage.EXPORTFAILURE);
	}

	@PostMapping("/exportQueriesReport")
	public ResponseEntity<ResultResponse> exportQueriesReport(@RequestBody JobQueryDto jobQueryDto) {
		ResultResponse result = jobService.exportQueriesReport(jobQueryDto);
		return ResponseEntity.ok(result);
	}

	@PostMapping("/feedback")
	public ResponseEntity<SearchStudentResponseDto> feedback(@RequestBody FeedbackDto feedbackDto) {

		SearchStudentResponseDto result = jobService.feedback(feedbackDto);
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		} else {
			throw new CustomResponseException(CustomErrorMessage.FEEDBACKTHANKYOUFAIL);
		}
	}

	@PostMapping("/printQueriesReport")
	public ResponseEntity<String> printQueriesReport() {
		return ResponseEntity.ok("printqueriesreport");
	}

	@PostMapping("/generateQueriesReport")
	public ResponseEntity<ReportResponseDto> generateQueriesReport(@RequestBody ReportDto reportDto) {
		ReportResponseDto result = jobService.generateQueriesReport(reportDto);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/queryReport")
	public ResponseEntity<EmployeesWithSalaryResponseDto> queryReport(@RequestHeader(value = "branchid") String branchId) {
		EmployeesWithSalaryResponseDto result = employeeService.ViewAllEmployee(branchId);
		return ResponseEntity.ok(result);
	}

	@RequestMapping(value = "/viewAllQueriesDepartmentWise", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<JobQueryDto> viewAllQueriesDepartmentWise(@RequestParam(value = "page") String page,
			@RequestHeader(value = "branchid") String branchId,
			@RequestHeader(value = "userloginid") String userLoginId) {

		JobQueryDto result = jobService.viewAllQueriesDepartmentWise(page, branchId, userLoginId);
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		} else {
			throw new CustomResponseException(CustomErrorMessage.ERROR);
		}
	}

	@PostMapping("/updateQueries")
	public ResponseEntity<SearchStudentResponseDto> updateQueries(@RequestBody UpdateQueriesDto updateQueriesDto,
			@RequestHeader(value = "userloginid") String userLoginId) {
		SearchStudentResponseDto result = jobService.updateQueries(updateQueriesDto, userLoginId);
		return ResponseEntity.ok(result);
	}

	@PostMapping("/updateQueryRemarks")
	public ResponseEntity<SearchStudentResponseDto> updateQueryRemarks(@RequestBody UpdateQueriesDto updateQueriesDto,
			@RequestHeader(value = "userloginid") String userLoginId) {
		SearchStudentResponseDto result = jobService.updateQueryRemarks(updateQueriesDto, userLoginId);
		return ResponseEntity.ok(result);

	}

	@PostMapping("/viewQueryDetails")
	public ResponseEntity<ResultResponse> viewQueryDetails(@RequestBody UpdateQueriesDto updateQueriesDto,
			@RequestHeader(value = "branchid") String branchId) {
		ResultResponse result = null;

		try {
			 result = jobService.viewQueryDetails(updateQueriesDto, branchId);
		} catch (IOException e) {
			e.printStackTrace();
			throw new CustomResponseException(CustomErrorMessage.ERROR);
		}
		return ResponseEntity.ok(result);
	}

	@PostMapping("/inProgressQueries")
	public ResponseEntity<SearchStudentResponseDto> inProgressQueries(@RequestBody QueriesDto queriesDto,
			@RequestHeader(value = "userloginid") String userLoginId) {
		SearchStudentResponseDto result = jobService.inProgressQueries(queriesDto, userLoginId);
		return ResponseEntity.ok(result);

	}

	@PostMapping("/toDoQueries")
	public ResponseEntity<SearchStudentResponseDto> toDoQueries(@RequestBody QueriesDto queriesDto,
			@RequestHeader(value = "userloginid") String userLoginId) {
		SearchStudentResponseDto result = jobService.toDoQueries(queriesDto, userLoginId);
		return ResponseEntity.ok(result);

	}

	@PostMapping("/cancelQueries")
	public ResponseEntity<SearchStudentResponseDto> cancelQueries(@RequestBody QueriesDto queriesDto,
			@RequestHeader(value = "userloginid") String userLoginId) {
		SearchStudentResponseDto result = jobService.cancelQueries(queriesDto, userLoginId);
		return ResponseEntity.ok(result);
	}

	@PostMapping("/completeQueries")
	public ResponseEntity<ReportResponseDto> completeQueries(@RequestBody QueriesDto queriesDto,
			@RequestHeader(value = "userloginid") String userLoginId) {
		ReportResponseDto result = jobService.completeQueries(queriesDto, userLoginId);
		return ResponseEntity.ok(result);
	}

	@RequestMapping(value = "/createQuery", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<EmployeeResponseDto> createQuery(@RequestHeader(value = "userType") String userType,
			@RequestParam(value = "empId") String empId, @RequestHeader(value = "branchid") String branchId,
			@RequestHeader(value = "username") String userName) {

		EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
		if (userType.equalsIgnoreCase("admin")) {
			EmployeeDetailsResponseDto employeeDetailsResponseDto = employeeService.viewDetailsEmployee(empId);
			employeeResponseDto.copyEmployeeDetailsResponseDto(employeeDetailsResponseDto);
			EmployeesWithSalaryResponseDto EmployeesWithSalaryResponseDto = employeeService.ViewAllEmployee(branchId);
			employeeResponseDto.copyEmployeesWithSalaryResponseDto(EmployeesWithSalaryResponseDto);
		} else if (userType.equalsIgnoreCase("teacher")) {
			EmployeeDetailsResponseDto employeeDetailsResponseDto = employeeService
					.viewDetailsEmployeeStaffLogin(userName);
			employeeResponseDto.copyEmployeeDetailsResponseDto(employeeDetailsResponseDto);
		}

		return ResponseEntity.ok(employeeResponseDto);
	}

	@RequestMapping(value = "/viewAllQueries", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<JobQueryDto> viewAllQueries(@RequestHeader(value = "userType") String userType,
			@RequestParam(value = "page") String page, @RequestHeader(value = "branchid") String branchId,
			@RequestHeader(value = "username") String userName) {

		JobQueryDto result = null;

		if (userType.equalsIgnoreCase("admin")) {
			result = jobService.viewAllQueries(page, branchId);
		} else if (userType.equalsIgnoreCase("teacher")) {
			result = jobService.viewAllQueriesDepartmentWise(page, branchId, userName);
		} else if (userType.equalsIgnoreCase("reception")) {
			result = jobService.viewAllQueries(page, branchId);
		} else {
			result = jobService.viewAllQueries(page, branchId);
		}

		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		} else {
			throw new CustomResponseException(CustomErrorMessage.ERROR);
		}
	}

	@PostMapping("/addQuery")
	public ResponseEntity<ResultResponse> addQuery(@RequestBody AddQueryDto addQueryDto,
			@RequestHeader(value = "branchid") String branchId,
			@RequestHeader(value = "currentAcademicYear") String currentAcademicYear,
			@RequestHeader(value = "userloginid") String userLoginId) {

		ResultResponse result = jobService.addQuery(addQueryDto, branchId, currentAcademicYear, userLoginId);
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);

		} else {
			throw new CustomResponseException(CustomErrorMessage.ERROR);
		}
	}

	@RequestMapping(value = "/viewAllTasks", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<JobQueryDto> viewAllTasks(@RequestHeader(value = "userType") String userType,
			@RequestParam(value = "page") String page, @RequestHeader(value = "branchid") String branchId,
			@RequestHeader(value = "username") String userName) {

		JobQueryDto result = null;

		if (userType.equalsIgnoreCase("admin")) {
			result = jobService.viewAllTasks(page, branchId);
		} else if (userType.equalsIgnoreCase("teacher")) {
			result = jobService.viewAllTasksDepartmentWise(page, branchId, userName);
		} else if (userType.equalsIgnoreCase("reception")) {
			result = jobService.viewAllTasks(page, branchId);
		} else {
			result = jobService.viewAllTasks(page, branchId);
		}

		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		} else {
			throw new CustomResponseException(CustomErrorMessage.ERROR);
		}
	}

	@PostMapping("/ViewTaskDetails")
	public ResponseEntity<JobQueryDto> viewTaskDetails(@RequestBody QueriesDto queriesDto,
			@RequestHeader(value = "branchid") String branchId) {

		JobQueryDto result = jobService.viewTaskDetails(queriesDto, branchId);
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		} else {
			throw new CustomResponseException(CustomErrorMessage.ERROR);
		}
	}

	@PostMapping("/viewOneJobDetails")
	public ResponseEntity<JobQueryDto> viewOneJobDetails(@RequestBody QueriesDto queriesDto,
			@RequestHeader(value = "branchid") String branchId) {

		JobQueryDto result = jobService.viewOneJobDetails(queriesDto, branchId);
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		} else {
			throw new CustomResponseException(CustomErrorMessage.ERROR);
		}
	}

	@PostMapping("/inProgressTasks")
	public ResponseEntity<JobQueryDto> inProgressTasks(@RequestBody QueriesDto queriesDto,
			@RequestHeader(value = "userType") String userType, @RequestParam(value = "page") String page,
			@RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "username") String userName,
			@RequestHeader(value = "userloginid") String userLoginId) {
		jobService.inProgressTasks(queriesDto, userLoginId);

		if (queriesDto.getDisplayType().equalsIgnoreCase("viewall")) {
			return viewAllTasks(userType, page, branchId, userName);
		} else {
			return viewTaskDetails(queriesDto, branchId);
		}

	}

	@PostMapping("/toDoTasks")
	public ResponseEntity<JobQueryDto> toDoTasks(@RequestBody QueriesDto queriesDto,
			@RequestHeader(value = "userType") String userType, @RequestParam(value = "page") String page,
			@RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "username") String userName,
			@RequestHeader(value = "userloginid") String userLoginId) {
		jobService.toDoTasks(queriesDto, userLoginId);

		if (queriesDto.getDisplayType().equalsIgnoreCase("viewall")) {
			return viewAllTasks(userType, page, branchId, userName);
		} else {
			return viewTaskDetails(queriesDto, branchId);
		}
	}

	@PostMapping("/cancelTasks")
	public ResponseEntity<JobQueryDto> cancelTasks(@RequestBody QueriesDto queriesDto,
			@RequestHeader(value = "userType") String userType, @RequestParam(value = "page") String page,
			@RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "username") String userName,
			@RequestHeader(value = "userloginid") String userLoginId) {
		jobService.cancelTasks(queriesDto, userLoginId);

		if (queriesDto.getDisplayType().equalsIgnoreCase("viewall")) {
			return viewAllTasks(userType, page, branchId, userName);
		} else {
			return viewTaskDetails(queriesDto, branchId);
		}
	}

	@PostMapping("/completeTasks")
	public ResponseEntity<JobQueryDto> completeTasks(@RequestBody QueriesDto queriesDto,
			@RequestHeader(value = "userType") String userType, @RequestParam(value = "page") String page,
			@RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "username") String userName,
			@RequestHeader(value = "userloginid") String userLoginId) {
		jobService.completeTasks(queriesDto, userLoginId);

		if (queriesDto.getDisplayType().equalsIgnoreCase("viewall")) {
			return viewAllTasks(userType, page, branchId, userName);
		} else {
			return viewTaskDetails(queriesDto, branchId);
		}
	}

	@PostMapping("/updateTaskRemarks")
	public ResponseEntity<JobQueryDto> updateTaskRemarks(@RequestBody TaskQueryDto taskQueryDto,
			@RequestHeader(value = "userType") String userType,
			@RequestParam(value = "page") String page, @RequestHeader(value = "branchid") String branchId,
			@RequestHeader(value = "username") String userName,
			@RequestHeader(value = "userloginid") String userLoginId) {
		UpdateQueriesDto updateQueriesDto = new UpdateQueriesDto();
		updateQueriesDto.setJobQuery(taskQueryDto.getJobQuery());
		updateQueriesDto.setQueryId(taskQueryDto.getQueryId());
		updateQueriesDto.setResponse(taskQueryDto.getResponse());
		updateQueriesDto.setQueryRemarks(taskQueryDto.getQueryRemarks());
		jobService.updateQueryRemarks(updateQueriesDto, userLoginId);

		if (taskQueryDto.getDisplayType().equalsIgnoreCase("viewall")) {
			return viewAllTasks(userType, page, branchId, userName);
		} else {
			QueriesDto queriesDto = new QueriesDto();
			queriesDto.setAssignto(taskQueryDto.getAssignto());
			queriesDto.setDescription(taskQueryDto.getDescription());
			queriesDto.setDisplayType(taskQueryDto.getDisplayType());
			queriesDto.setExpecteddd(taskQueryDto.getExpecteddd());
			queriesDto.setJobId(taskQueryDto.getJobId());
			queriesDto.setJobno(taskQueryDto.getJobno());
			queriesDto.setQueryIds(taskQueryDto.getQueryIds());
			queriesDto.setTask(taskQueryDto.getTask());
			queriesDto.setTaskIds(taskQueryDto.getTaskIds());
			return viewTaskDetails(queriesDto, branchId);
		}
	}

	@PostMapping("/createTask")
	public ResponseEntity<EmployeeResponseDto> createTask(@RequestBody QueriesDto queriesDto,
			@RequestHeader(value = "branchid") String branchId,
			@RequestHeader(value = "userloginid") String userLoginId,@RequestHeader(value = "userType") String userType) {

		EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();

		if (userType.equalsIgnoreCase("admin")) {
			EmployeesWithSalaryResponseDto EmployeesWithSalaryResponseDto = employeeService.ViewAllEmployee(branchId);
			employeeResponseDto.copyEmployeesWithSalaryResponseDto(EmployeesWithSalaryResponseDto);
			JobQueryDto jobQueryDto = jobService.createTask(queriesDto);
			employeeResponseDto.copyJobQueryDto(jobQueryDto);
		} else if (userType.equalsIgnoreCase("teacher")) {
			EmployeeDetailsResponseDto employeeDetailsResponseDto = employeeService
					.viewDetailsEmployeeStaffLogin(userLoginId);
			employeeResponseDto.copyEmployeeDetailsResponseDto(employeeDetailsResponseDto);
		}

		return ResponseEntity.ok(employeeResponseDto);
	}

	@PostMapping("/addTask")
	public ResponseEntity<ResultResponse> addTask(@RequestBody QueriesDto queriesDto,
			@RequestHeader(value = "branchid") String branchId) {

		ResultResponse result = jobService.addTask(queriesDto, branchId);
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		} else {
			throw new CustomResponseException(CustomErrorMessage.ERROR);
		}
	}

	@GetMapping("/taskReport")
	public ResponseEntity<TaskReportResponseDto> taskReport(@RequestHeader(value = "branchid") String branchId) {
		TaskReportResponseDto viewStudentResponseDto = new TaskReportResponseDto();
		EmployeesWithSalaryResponseDto employeesWithSalaryResponseDto = employeeService.ViewAllEmployee(branchId);
		viewStudentResponseDto.copyEmployeesWithSalaryResponseDto(employeesWithSalaryResponseDto);

		StudentListResponseDto studentListResponseDto = studentService.viewAllStudentsList(branchId);
		viewStudentResponseDto.copyStudentListResponseDto(studentListResponseDto);
		return ResponseEntity.ok(viewStudentResponseDto);

	}

	@PostMapping("/generateTasksReport")
	public ResponseEntity<ReportResponseDto> generateTasksReport(@RequestBody ReportDto reportDto) {
		ReportResponseDto result = jobService.generateTasksReport(reportDto);
		return ResponseEntity.ok(result);
	}

	@PostMapping("/printTasksReport")
	public ResponseEntity<String> printTasksReport() {
		return ResponseEntity.ok("printtasksreport");
	}

	@GetMapping("/viewReferredby")
	public ResponseEntity mrvDetails(@RequestBody ReportDto reportDto,
			@RequestHeader(value = "branchid") String branchId) {
		try {
			jobService.getReferredbyDetails(reportDto, branchId);
		} catch (IOException e) {
			e.printStackTrace();
			throw new CustomResponseException(CustomErrorMessage.ERROR);
		}
		return ResponseEntity.ok().build();

	}

		
}
