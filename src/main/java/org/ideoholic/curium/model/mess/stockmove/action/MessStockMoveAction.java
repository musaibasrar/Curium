/**
 * 
 */
package org.ideoholic.curium.model.mess.stockmove.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ideoholic.curium.model.mess.item.service.MessItemsService;
import org.ideoholic.curium.model.mess.stockmove.service.MessStockMoveService;
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
@RequestMapping("/MessItemsMoveProcess")
public class MessStockMoveAction {

	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;

	@PostMapping("/cancelStockMove")
	public String cancelStockMove() {

		new MessStockMoveService(request, response).cancelStockMove();
		new MessItemsService(request, response).getCurrentStockToIssue();
		/*
		 * Batch stock issue new MessStockMoveService(request,
		 * response).viewStockEntryDetails();
		 */
		new MessStockMoveService(request, response).viewStockMoveDetails();
		return "issuestock";
	}

	@RequestMapping(value = "/issueItems", method = { RequestMethod.GET, RequestMethod.POST })
	public String issueItems() {
		new MessItemsService(request, response).getCurrentStockToIssue();
		/*
		 * Batch stock issue new MessStockMoveService(request,
		 * response).viewStockEntryDetails();
		 */
		
		//Batch stock issue 
    	new MessStockMoveService(request, response).viewStockEntryDetails();
    	
    	//Get Customers
    	new StudentService(request, response).viewStudentsParentsPerBranch();
		
		
		
		new MessStockMoveService(request, response).viewStockMoveDetails();
		return "issuestock";
	}

	@PostMapping("/saveStockMove")
	public String saveStockMove() {

		new MessStockMoveService(request, response).saveStockMove();
		new MessItemsService(request, response).getCurrentStockToIssue();
		/*
		 * Batch stock issue new MessStockMoveService(request,
		 * response).viewStockEntryDetails();
		 */
		//Batch stock issue 
    	new MessStockMoveService(request, response).viewStockEntryDetails();
    	
    	new MessStockMoveService(request, response).viewStockMoveDetails();
    	//Get Student
    	new StudentService(request, response).viewAllStudentsParents();
    	return "bill";
    	//return "issuestock";

	}
	
	@GetMapping("/billsReport")
	public String billsReport() {
		    	
    	new MessStockMoveService(request, response).viewStockMoveDetails();
    	
    	return "billsreport";
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
