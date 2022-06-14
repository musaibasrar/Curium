/**
 * 
 */
package org.ideoholic.curium.model.job.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.employee.service.EmployeeService;
import org.ideoholic.curium.model.job.service.JobService;
import org.ideoholic.curium.model.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Musaib_2
 * 
 */
@Controller
@RequestMapping("/QueryProcess")
public class JobAction {

	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	@Autowired
	HttpSession httpSession;

	
	@PostMapping("/download")
	private String download() {
		if(new JobService(request, response).download()) {
			return "exportsuccessquery";
		}
		return "exportfailure";
	}

	@PostMapping("/exportQueriesReport")
	private String exportQueriesReport() {
		new JobService(request, response).exportQueriesReport();
		return "exportsuccessquery";
	}

	@PostMapping("/feedback")
	private String feedback() {
		
		if(new JobService(request, response).feedback()) {
			return "feedbackthankyou";	
		}else {
		return "feedbackthankyoufail";
		}
	}

	@PostMapping("/printQueriesReport")
	private String printQueriesReport() {
		return "printqueriesreport";
	}

	@PostMapping("/generateQueriesReport")
	private String generateQueriesReport() {
		new JobService(request, response).generateQueriesReport();
		return queryReport();
	}

	@GetMapping("/queryReport")
	private String queryReport() {
		new EmployeeService(request, response).ViewAllEmployee();
		new StudentService(request, response).viewAllStudentsList();
		return "queriesreport";
	}

	@RequestMapping(value = "/viewAllQueriesDepartmentWise", method = { RequestMethod.GET, RequestMethod.POST })
	private String viewAllQueriesDepartmentWise() {
		
		if(new JobService(request, response).viewAllQueriesDepartmentWise()){
			return "queries";
		}else{
			return "error";
		}
	}

	@PostMapping("/updateQueries")
	private String updateQueries() {
		new JobService(request, response).updateQueries();
		return viewAllQueries();
	}
	
	@PostMapping("/updateQueryRemarks")
	private String updateQueryRemarks() {
		new JobService(request, response).updateQueryRemarks();
		return viewAllQueries();
	}
	
	@PostMapping("/viewQueryDetails")
	private void viewQueryDetails() {
		
		try {
			new JobService(request, response).viewQueryDetails();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@PostMapping("/inProgressQueries")
	private String inProgressQueries() {
		new JobService(request, response).inProgressQueries();
		return viewAllQueries();
	}
	
	@PostMapping("/toDoQueries")
	private String toDoQueries() {
		new JobService(request, response).toDoQueries();
		return viewAllQueries();
	}

	@PostMapping("/cancelQueries")
	private String cancelQueries() {
		new JobService(request, response).cancelQueries();
		return viewAllQueries();
	}

	@PostMapping("/completeQueries")
	private String completeQueries() {
		new JobService(request, response).completeQueries();
		return viewAllQueries();
	}

	@RequestMapping(value = "/viewAllQueries", method = { RequestMethod.GET, RequestMethod.POST })
	private String viewAllQueries() {
		
		boolean result = false;
		
		 if(httpSession.getAttribute("userType").toString().equalsIgnoreCase("admin")) {
			 result = new JobService(request, response).viewAllQueries(); 
         }else if(httpSession.getAttribute("userType").toString().equalsIgnoreCase("staff")) {
        	 result = new JobService(request, response).viewAllQueriesDepartmentWise(); 
         }else {
        	 result = new JobService(request, response).viewAllQueries(); 
         }
		 
		 if(result){
  			return "queries";
  		}else{
  			return "error";
  		}
		 
		 
	}

	@PostMapping("/addQuery")
	private String addQuery() {
		
		if(new JobService(request, response).addQuery()){
			return "querysuccess";
		}else{
			return "error";
		}
	}
}
