/**
 * 
 */
package org.ideoholic.curium.model.printids.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ideoholic.curium.model.employee.action.EmployeeActionAdapter;
import org.ideoholic.curium.model.employee.service.EmployeeService;
import org.ideoholic.curium.model.printids.service.PrintIdsService;
import org.ideoholic.curium.model.std.action.StandardActionAdapter;
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
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private StandardActionAdapter standardActionAdapter;
	@Autowired
	private EmployeeActionAdapter employeeActionAdapter;
	@Autowired
	private PrintIdsActionAdapter printIdsActionAdapter;

	@PostMapping("/updateCardValidity")
	public String updateCardValidity() {

		printIdsActionAdapter.updateCardValidity();
		return "cardvalidity";

	}

	@PostMapping("/searchDetailsCardValidity")
	public String searchDetailsCardValidity() {

		printIdsActionAdapter.searchDetailsCardValidity();
		return "cardvalidity";
	}

	@GetMapping("/cardValidity")
	public String cardValidity() {
		standardActionAdapter.viewClasses();
		return "cardvalidity";
	}

	@GetMapping("/generateIds")
	public String generateIds() {
		standardActionAdapter.viewClasses();
		return "generateids";
	}

	@PostMapping("/searchDetails")
	public String searchDetails() {

		printIdsActionAdapter.searchDetails();
		return "generateids";
	}

	@PostMapping("/printPreview")
	public String printPreview() {

		new PrintIdsService(request, response).printMultiple();
		return "printpreview";
	}
	
	@GetMapping("/generateIdsEmployees")
	public String generateIdsEmployees() {
		 employeeActionAdapter.ViewAllEmployee();
		return "generateidsemployee";
	}
	
	@PostMapping("/printpreviewemployee")
	public String printpreviewemployee() {
		 employeeActionAdapter.printMultipleEmployees();
		 return "printpreviewemployee";
	}
	
}
