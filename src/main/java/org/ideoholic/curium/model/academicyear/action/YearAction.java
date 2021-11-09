/**
 * 
 */
package org.ideoholic.curium.model.academicyear.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.academicyear.service.YearService;
import org.ideoholic.curium.model.adminexpenses.service.AdminService;
import org.ideoholic.curium.model.feescategory.service.FeesService;
import org.ideoholic.curium.model.student.service.StudentService;
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
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	
	
	@PostMapping("/saveYear")
	public String saveYear() { 
		
		if(new YearService(request, response).saveYear()){
			return "yearsaved";
		}else{
		return "error";
		}
		
    }

	
	@GetMapping("/updateYear")
	private String updateYear() {
		 new YearService(request, response).updateYear();
	            return "academicyear";
	       
		
	}

}
