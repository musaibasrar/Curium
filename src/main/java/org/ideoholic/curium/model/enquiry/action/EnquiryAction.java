package org.ideoholic.curium.model.enquiry.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.documents.service.DocumentService;
import org.ideoholic.curium.model.enquiry.service.EnquiryService;
import org.ideoholic.curium.model.std.service.StandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	}
