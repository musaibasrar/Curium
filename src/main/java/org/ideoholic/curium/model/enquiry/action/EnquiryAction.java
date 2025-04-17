package org.ideoholic.curium.model.enquiry.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.documents.service.DocumentService;
import org.ideoholic.curium.model.enquiry.service.EnquiryService;
import org.ideoholic.curium.model.std.action.StandardActionAdapter;
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
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private HttpSession httpSession;
	@Autowired
	private StandardActionAdapter standardActionAdapter;
	@Autowired
	private EnquiryActionAdapter enquiryActionAdapter;
	
	/*
	 * @GetMapping("/newEnquiry") public String NewEnquiryDetail() {
	 * standardActionAdapter.viewClasses(); return "newenquiry"; }
	 * 
	 * @PostMapping("/genarateNewCertificate") public String
	 * genarateNewCertificate() { enquiryActionAdapter.getCertificate(); return
	 * "newcertificatepreview"; }
	 */

		
		
	@GetMapping("/enquiry")
	public String enquiry() {
		standardActionAdapter.viewClasses();
		return "enquiryform";
	}
	
	@PostMapping("/saveEnquiryForm")
	public String saveEnquiryForm() {
		if(enquiryActionAdapter.saveEnquiryForm()) {
			enquiryActionAdapter.getStudentLastEnquiry();
			return "studentenquiryformprint";
		}else {
			return "error";
		}
		
	}
	
	@RequestMapping(value = "/viewEnquiry", method = { RequestMethod.GET, RequestMethod.POST })
	public String viewEnquiry() {
		enquiryActionAdapter.viewEnquiry(); 
		return "viewenquirylist";
	}
	
	@GetMapping("/getStudentEnquiryform")
	public String getStudentEnquiryform() {
		enquiryActionAdapter.getStudentEnquiry(); 
		return "studentenquiryformprint";
	}
	
	@PostMapping("/updateEnquiryDetails")
	public String updateEmployeeDetails() {
		enquiryActionAdapter.getStudentEnquiry(); 
			return "enquiry_update";
	}
	
	@PostMapping("/updateEnquiryFormDetails")
	public String updateEnquiryFormDetails() {
		if(enquiryActionAdapter.updateEnquiry()) {
			standardActionAdapter.viewClasses();
			return "updatesuccessful";	
		}
		return "error";
	}
	
	@PostMapping("/deleteEnquiry")
	public String deleteEnquiry() {
		enquiryActionAdapter.deleteEnquiry(); 
			return viewEnquiry();
	}
	

}
