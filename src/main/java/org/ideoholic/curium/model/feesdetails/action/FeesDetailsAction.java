package org.ideoholic.curium.model.feesdetails.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.feescategory.service.FeesService;
import org.ideoholic.curium.model.feesdetails.service.FeesDetailsService;
import org.ideoholic.curium.model.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/FeesDetails")
public class FeesDetailsAction {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpServletResponse response;
	
	@Autowired
	HttpSession httpSession;
	
	@Autowired
	private FeesService feesService;

	@PostMapping("/download")
	public String downloadFile() {
		if(feesService.downlaodFile()){
			return "feesexportsuccess";
		}
        return "exportfailure";
	}

	@PostMapping("/exportDataForFees")
	public String exportFeesData() {
		
		if(new FeesDetailsService(request, response).exportDataForFees()){
			return "feesexportsuccess";
		}else{
			return "exportfailure";
		}
		
	}
	
	@PostMapping("/exportDataForOtherFees")
	public String exportOtherFeesData() {
		
		if(new FeesDetailsService(request, response).exportDataForOtherFees()){
			return "feesexportsuccess";
		}else{
			return "exportfailure";
		}
		
	}
	
	@PostMapping("/printDataForFees")
	public String printFeesData() {
		
		if(new FeesDetailsService(request, response).printDataForFees()){
			return "printfeescollectiondetails";
		}else{
			return "error";
		}
		
	}
	
}
