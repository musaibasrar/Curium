/**
 * 
 */
package org.ideoholic.curium.model.academicyear.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ideoholic.curium.model.academicyear.service.YearService;
import org.ideoholic.curium.model.account.action.AccountActionAdapter;
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
@RequestMapping("/YearProcess")
public class YearAction {

	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	
	@Autowired
	private YearActionAdapter yearActionAdapter;

	
	
	@PostMapping("/saveYear")
	public String saveYear() {
		System.out.println("Action is viewAllExpenses");
		if (yearActionAdapter.saveYear()) {
			return "yearsaved";
		} else {
			return "error";
		}

	}

	@GetMapping("/updateYear")
	public String updateYear() {
		System.out.println("Action is addExpenses");
		yearActionAdapter.updateYear();
		return "academicyear";
	}

}
