package org.ideoholic.curium.model.enquiry.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.enquiry.dao.enquiryDAO;
import org.ideoholic.curium.model.enquiry.dto.Enquiry;

public class EnquiryService {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;

	

	public EnquiryService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}



	public void getCertificate() {
		
		
	    String name= request.getParameter("name");		
	    String place= request.getParameter("place");	
	    String mobile= request.getParameter("mobile");	
	    String date= request.getParameter("date");
	    httpSession.setAttribute("name", name);
	    httpSession.setAttribute("place", place);
	    httpSession.setAttribute("mobile", mobile);
	    httpSession.setAttribute("date", date);

	    Enquiry enquiry = new Enquiry();
        
       	 
                
	    enquiry.setName(name);
	    enquiry.setAddress(place);
	    enquiry.setMobileno(mobile);
	          
                enquiry =  new enquiryDAO().create(enquiry);
                }
	   
		}

