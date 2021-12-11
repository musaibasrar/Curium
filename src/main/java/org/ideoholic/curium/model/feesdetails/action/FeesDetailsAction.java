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

	@PostMapping("/download")
	public String downloadFile() {
		if(new FeesService(request, response).downlaodFile()){
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
	
}
