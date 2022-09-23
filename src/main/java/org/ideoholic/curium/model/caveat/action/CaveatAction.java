/**
 * 
 */
package org.ideoholic.curium.model.caveat.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.caveat.service.CaveatService;
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
@RequestMapping("/CaveatProcess")
public class CaveatAction {

	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	@Autowired
	HttpSession httpSession;

	
	
	
	
	@RequestMapping(value = "/viewAllCaveats", method = { RequestMethod.GET, RequestMethod.POST })
	private String viewAllCaveats() {
		
		boolean result = false;
		
		 if(httpSession.getAttribute("userType").toString().equalsIgnoreCase("admin")) {
			 result = new CaveatService(request, response).viewAllCaveats(); 
         }else if(httpSession.getAttribute("userType").toString().equalsIgnoreCase("staff")) {
        	 result = new CaveatService(request, response).viewAllCaveats(); 
         }else if(httpSession.getAttribute("userType").toString().equalsIgnoreCase("reception")) {
        	 result = new CaveatService(request, response).viewAllCaveats();
        	 return "queriesreadonly";
         }else {
        	 result = new CaveatService(request, response).viewAllCaveats(); 
         }
		 
		 if(result){
  			return "caveat";
  		}else{
  			return "error";
  		}
	}
	
	
	@RequestMapping(value = "/createCaveats", method = { RequestMethod.GET, RequestMethod.POST })
	public String createCaveats() {
		new StudentService(request, response).viewAllStudentsParents();
		return "createcaveat";
	}
	
	@PostMapping("/createNewCaveats")
	public String createNewCaveats() {
		new StudentService(request, response).viewDetailsOfStudent();
		new StudentService(request, response).viewAllStudentsList();
		return "createnewcaveat";
	}
	
	@PostMapping("/addCaveats")
	private String addCaveats() {
		
		if(new CaveatService(request, response).addCaveats()){
			return "caveatsuccess";
		}else{
			return "error";
		}
	}
	
	@PostMapping("/pendingCaveats")
	private String pendingCaveats() {
		new CaveatService(request, response).pendingCaveats();
		return viewAllCaveats();
	}
	
	@PostMapping("/expiredCaveats")
	private String expiredCaveats() {
		new CaveatService(request, response).expiredCaveats();
		return viewAllCaveats();
	}
	
	
	 @PostMapping("/addDispose") 
	 private String disposeCaveats() { 
		  new CaveatService(request, response).disposeCaveats(); 
		  return viewAllCaveats(); 
	  }
	 
	 @PostMapping("/addNOC") 
	 private String nocCaveats() { 
		  new CaveatService(request, response).noCaveats(); 
		  return viewAllCaveats(); 
	  }
	 
	 @PostMapping("/cancelCaveats")
		private String cancelCaveats() {
			new CaveatService(request, response).cancelCaveats();
			return viewAllCaveats();
		}
	
	 @GetMapping("/caveatsReport")
		private String caveatsReport() {
			new StudentService(request, response).viewAllStudentsList();
			return "caveatreport";
		}
	 
	 @PostMapping("/generateCaveatsReport")
		private String generateCaveatsReport() {
			new CaveatService(request, response).generateCaveatsReport();
			return caveatsReport();
		}
	 
	 @PostMapping("/printCaveatsReport")
		private String printCaveatsReport() {
			return "printcaveatsreport";
		}
	 
	 @GetMapping("/viewReferredby")
		public void mrvDetails() {
			try {
				new CaveatService(request, response).getReferredbyDetails();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
}
