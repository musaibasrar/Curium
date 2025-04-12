package org.ideoholic.curium.model.enquiry.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.enquiry.service.EnquiryService;
import org.ideoholic.curium.model.std.action.StandardAction;
import org.ideoholic.curium.model.std.service.StandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/EnquiryProcess")
public class EnquiryAction {
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	@Autowired
	HttpSession httpSession;
	
	@GetMapping("/newCertificate")
	public String NewCertificateDetail() {
		new StandardService(request, response).viewClasses(); 
		return "newcertificate";
	}

	@PostMapping("/genarateNewCertificate")
	public String genarateNewCertificate() {
		new EnquiryService(request, response).getCertificate(); 
		return "newcertificatepreview";
	}
	
	@GetMapping("/enquiry")
	public String enquiry() {
		new StandardService(request, response).viewClasses();
		return "enquiryform";
	}
	
	@PostMapping("/saveEnquiryForm")
	public String saveEnquiryForm() {
		if(new EnquiryService(request, response).saveEnquiryForm()) {
			new EnquiryService(request, response).getStudentLastEnquiry();
			return "studentenquiryformprint";
		}else {
			return "error";
		}
		
	}
	
	@RequestMapping(value = "/viewEnquiry", method = { RequestMethod.GET, RequestMethod.POST })
	public String viewEnquiry() {
		new EnquiryService(request, response).viewEnquiry(); 
		return "viewenquirylist";
	}
	
	@GetMapping("/getStudentEnquiryform")
	public String getStudentEnquiryform() {
		new EnquiryService(request, response).getStudentEnquiry(); 
		return "studentenquiryformprint";
	}
	
	@PostMapping("/updateEnquiryDetails")
	public String updateEmployeeDetails() {
		new EnquiryService(request, response).getStudentEnquiry(); 
			return "enquiry_update";
	}
	
	@PostMapping("/updateEnquiryFormDetails")
	public String updateEnquiryFormDetails() {
		if(new EnquiryService(request, response).updateEnquiry()) {
			new StandardService(request, response).viewClasses();
			return "updatesuccessful";	
		}
		return "error";
	}
	
	@PostMapping("/deleteEnquiry")
	public String deleteEnquiry() {
		new EnquiryService(request, response).deleteEnquiry(); 
			return viewEnquiry();
	}
	

	}
