/**
 * 
 */
package org.ideoholic.curium.model.cases.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.cases.service.CasesService;
import org.ideoholic.curium.model.employee.service.EmployeeService;
import org.ideoholic.curium.model.job.service.JobService;
import org.ideoholic.curium.model.mess.stockentry.service.MessStockEntryService;
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
@RequestMapping("/CasesProcess")
public class CasesAction {

	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	@Autowired
	HttpSession httpSession;

	
	
	
	
	@RequestMapping(value = "/viewAllCases", method = { RequestMethod.GET, RequestMethod.POST })
	private String viewAllCases() {
		
		boolean result = false;
		
		 if(httpSession.getAttribute("userType").toString().equalsIgnoreCase("admin")) {
			 result = new CasesService(request, response).viewAllCases(); 
         }else if(httpSession.getAttribute("userType").toString().equalsIgnoreCase("staff")) {
        	 result = new CasesService(request, response).viewAllCases(); 
         }else if(httpSession.getAttribute("userType").toString().equalsIgnoreCase("reception")) {
        	 result = new CasesService(request, response).viewAllCases();
        	 return "queriesreadonly";
         }else {
        	 result = new CasesService(request, response).viewAllCases(); 
         }
		 
		 if(result){
  			return "cases";
  		}else{
  			return "error";
  		}
	}
	
	
	@RequestMapping(value = "/createCase", method = { RequestMethod.GET, RequestMethod.POST })
	public String createCase() {
		new StudentService(request, response).viewAllStudentsParents();
		return "createcases";
	}
	
	@PostMapping("/createNewCases")
	public String createNewCases() {
		new StudentService(request, response).viewDetailsOfStudent();
		new StudentService(request, response).viewAllStudentsList();
		return "createnewcases";
	}
	
	@PostMapping("/addCases")
	private String addCases() {
		
		if(new CasesService(request, response).addCases()){
			return "casessuccess";
		}else{
			return "error";
		}
	}
	
	@PostMapping("/pendingCases")
	private String pendingCases() {
		new CasesService(request, response).pendingCases();
		return viewAllCases();
	}
	
	
	 @PostMapping("/addDispose") 
	 private String disposeCases() { 
		  new CasesService(request, response).disposeCases(); 
		  return viewAllCases(); 
	  }
	 
	 @PostMapping("/addNOC") 
	 private String nocCases() { 
		  new CasesService(request, response).nocCases(); 
		  return viewAllCases(); 
	  }
	 
	 @PostMapping("/cancelCases")
		private String cancelCases() {
			new CasesService(request, response).cancelCases();
			return viewAllCases();
		}
	
	 @GetMapping("/casesReport")
		private String casesReport() {
			new StudentService(request, response).viewAllStudentsList();
			return "casesreport";
		}
	 
	 @PostMapping("/generateCasesReport")
		private String generateCasesReport() {
			new CasesService(request, response).generateCasesReport();
			return casesReport();
		}
	 
	 @PostMapping("/printCasesReport")
		private String printCasesReport() {
			return "printcasesreport";
		}
	 
	 
	 @GetMapping("/viewReferredby")
		public void mrvDetails() {
			try {
				new CasesService(request, response).getReferredbyDetails();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
}
