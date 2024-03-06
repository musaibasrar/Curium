package org.ideoholic.curium.model.enquiry.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.enquiry.service.EnquiryService;
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
	
	@GetMapping("/newEnquiry")
	public String NewEnquiryDetail() {
		new StandardService(request, response).viewClasses(); 
		return "newenquiry";
	}

	@PostMapping("/genarateNewCertificate")
	public String genarateNewCertificate() {
		new EnquiryService(request, response).getCertificate(); 
		return "newcertificatepreview";
	}
	
	@PostMapping("/saveEnquiry")
	public String saveEnquiry() {
		new EnquiryService(request, response).saveEnquiry();
		return "savedenquiry";
	}
	
	@RequestMapping(value = "/viewAllEnquiries", method = { RequestMethod.GET, RequestMethod.POST })
	public String viewAllEnquiries() {
		new EnquiryService(request, response).getEnquiries(); 
		return "viewallenquiries";
	}
	
	@PostMapping("/approved")
	private String approved() {
		new EnquiryService(request, response).approved();
		String displayType = request.getParameter("display").toString();
		
		if(displayType.equalsIgnoreCase("viewall")) {
			return viewAllEnquiries();
		}else {
			return viewAllEnquiries();
		}
	}
	
	@PostMapping("/rejected")
	private String rejected() {
		new EnquiryService(request, response).rejected();
		String displayType = request.getParameter("display").toString();
		
		if(displayType.equalsIgnoreCase("viewall")) {
			return viewAllEnquiries();
		}else {
			return viewAllEnquiries();
		}
	}
	
	@PostMapping("/processing")
	private String processing() {
		new EnquiryService(request, response).processing();
		String displayType = request.getParameter("display").toString();
		
		if(displayType.equalsIgnoreCase("viewall")) {
			return viewAllEnquiries();
		}else {
			return viewAllEnquiries();
		}
	}
		
	@PostMapping("/onhold")
	private String onHold() {
		new EnquiryService(request, response).onHold();
		String displayType = request.getParameter("display").toString();
		
		if(displayType.equalsIgnoreCase("viewall")) {
			return viewAllEnquiries();
		}else {
			return viewAllEnquiries();
		}
	}
	
	}
