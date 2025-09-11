/**
 * 
 */
package org.ideoholic.curium.model.printids.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.employee.service.EmployeeService;
import org.ideoholic.curium.model.printids.service.PrintIdsService;
import org.ideoholic.curium.model.std.service.StandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Musaib_2
 * 
 */
@Controller
@RequestMapping("/Printids")
public class PrintIdsAction {

	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	@Autowired
	HttpSession httpSession;

	@PostMapping("/updateCardValidity")
	public String updateCardValidity() {

		new PrintIdsService(request, response).updateCardValidity();
		return "cardvalidity";

	}

	@PostMapping("/searchDetailsCardValidity")
	public String searchDetailsCardValidity() {

		new PrintIdsService(request, response).searchDetailsCardValidity();
		return "cardvalidity";
	}

	@GetMapping("/cardValidity")
	public String cardValidity() {
		new StandardService(request, response).viewClasses();
		return "cardvalidity";
	}

	@GetMapping("/generateIds")
	public String generateIds() {
		new StandardService(request, response).viewClasses();
		return "generateids";
	}
	
	@PostMapping("/generateIdBackPart")
	public String printBackPreview() {
		new PrintIdsService(request, response).printMultiple();
		return "printbackpreview";
	}   
	
	@PostMapping("/generateIdBackPartstaff")
	public String generateIdBackPartstaff() {
		new EmployeeService(request, response).printMultipleEmployees();
		return "printbackpreviewstaff";
	} 

	@PostMapping("/searchDetails")
	public String searchDetails() {

		new PrintIdsService(request, response).searchDetails();
		return "generateids";
	}

	@PostMapping("/printPreview")
	public String printPreview() {
		new PrintIdsService(request, response).printMultiple();
		System.out.println(httpSession.getAttribute("branchid").toString());
		if (httpSession.getAttribute("branchid").toString().equalsIgnoreCase("2")) {
			return "printpreview";
		} else if (httpSession.getAttribute("branchid").toString().equalsIgnoreCase("3")) {
			return "printpreviewpuc";
		} 
		return "printpreview";
	}
	
	@GetMapping("/generateIdsEmployees")
	public String generateIdsEmployees() {
		 new EmployeeService(request, response).ViewAllEmployee();
		return "generateidsemployee";
	}
	
	@PostMapping("/printpreviewemployee")
	public String printpreviewemployee() {
		 new EmployeeService(request, response).printMultipleEmployees();
		 return "printpreviewemployee";
	}
	
}
