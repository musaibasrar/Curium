/**
 * 
 */
package org.ideoholic.curium.model.mess.stockmove.action;

import org.ideoholic.curium.model.mess.item.action.MessItemActionAdapter;
import org.ideoholic.curium.model.mess.stockmove.service.MessStockMoveService;
import org.ideoholic.curium.model.std.action.StandardActionAdapter;
import org.ideoholic.curium.model.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Musaib_2
 * 
 */
@Controller
@RequestMapping("/MessItemsMoveProcess")
public class MessStockMoveAction {

	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	@Autowired
	private StandardActionAdapter standardActionAdapter;
	@Autowired
	private MessStockMoveActionAdapter messStockMoveActionAdapter;
	@Autowired
	private MessItemActionAdapter messItemActionAdapter;

	@PostMapping("/cancelStockMove")
	public String cancelStockMove() {

		new MessStockMoveService(request, response).cancelStockMove();
		messItemActionAdapter.getCurrentStockToIssue();
		/*
		 * Batch stock issue new MessStockMoveService(request,
		 * response).viewStockEntryDetails();
		 */
		messStockMoveActionAdapter.viewStockMoveDetails();
		return "billsreport";
	}

	@RequestMapping(value = "/issueItems", method = { RequestMethod.GET, RequestMethod.POST })
	public String issueItems() {
		messItemActionAdapter.getCurrentStockToIssue();
		/*
		 * Batch stock issue new MessStockMoveService(request,
		 * response).viewStockEntryDetails();
		 */
		
		//Batch stock issue 
    	messStockMoveActionAdapter.viewStockEntryDetails();
    	
    	//Get Customers
    	new StudentService(request, response, standardActionAdapter).viewStudentsParentsPerBranch();
		
		
		
		messStockMoveActionAdapter.viewStockMoveDetails();
		return "issuestock";
	}

	@PostMapping("/saveStockMove")
	public String saveStockMove() {

		new MessStockMoveService(request, response).saveStockMove();
		messItemActionAdapter.getCurrentStockToIssue();
		/*
		 * Batch stock issue new MessStockMoveService(request,
		 * response).viewStockEntryDetails();
		 */
		//Batch stock issue 
    	messStockMoveActionAdapter.viewStockEntryDetails();
    	
    	messStockMoveActionAdapter.viewStockMoveDetails();
    	//Get Student
    	new StudentService(request, response, standardActionAdapter).viewAllStudentsParents();
    	return "bill";
    	//return "issuestock";

	}
	
	@GetMapping("/billsReport")
	public String billsReport() {
		    	
    	messStockMoveActionAdapter.viewStockMoveDetails();
    	
    	return "billsreport";
    }
	
	@PostMapping("/dueReport")
	public String dueReport() {
		    	
    	new MessStockMoveService(request, response).viewStockDueDetails();
    	
    	return "duereport";
    }
	
	@GetMapping("/getDueReport")
	public String getDueReport() {
		    	
		standardActionAdapter.viewClasses();
    	
    	return "duereport";
    }
	
	
	@GetMapping("/getCustomerLastPrice")
	public void getCustomerLastPrice() {
		
    	try {
			new MessStockMoveService(request, response).getCustomerLastPrice();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
