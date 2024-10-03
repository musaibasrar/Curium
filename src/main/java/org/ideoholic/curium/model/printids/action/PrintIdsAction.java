/**
 * 
 */
package org.ideoholic.curium.model.printids.action;

import org.ideoholic.curium.model.employee.action.EmployeeActionAdapter;
import org.ideoholic.curium.model.std.action.StandardActionAdapter;
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
@RequestMapping("/PrintIds")
public class PrintIdsAction {

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

		printIdsActionAdapter.printMultiple();
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
