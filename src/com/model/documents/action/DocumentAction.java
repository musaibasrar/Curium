/**
 * 
 */
package com.model.documents.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.documents.service.DocumentService;
import com.model.feescategory.service.FeesService;
import com.model.stampfees.service.StampFeesService;
import com.model.student.service.StudentService;

/**
 * @author Musaib_2
 * 
 */
public class DocumentAction {

	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession httpSession;
	String url;
	private String error ="error.jsp";

	public DocumentAction(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

	public String execute(String action) {

		if ("transferCertificate".equalsIgnoreCase(action)) {
			url = transferCertificate();
		}else if ("generateTransferCertificate".equalsIgnoreCase(action)) {
			url = generateTransferCertificate();
		}else if ("PrintTransferCertificate".equalsIgnoreCase(action)) {
			url = printTransferCertificate();
		}
		return url;
	}
	

	private String printTransferCertificate() {
		
		if(new DocumentService(request, response).printTransferCertificate()){
			return "transfercertificateprint.jsp";
		}
        return error;
	}

	private String generateTransferCertificate() {
		
		String result = new DocumentService(request, response).generateTransferCertificate();
		
		if("true".equalsIgnoreCase(result)){
			return "transfercertificatepreview.jsp";
		}else if("studentexists".equalsIgnoreCase(result)){
        return "transfercertificatefail.jsp";
		}
		return error;
	}

	private String transferCertificate() {
		if(new DocumentService(request, response).transferCertificate()){
			return "transfercertificate.jsp";
		}
        return error;
	}
}
