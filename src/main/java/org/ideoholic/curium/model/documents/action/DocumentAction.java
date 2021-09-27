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
import com.model.std.service.StandardService;
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
		}else if ("studentsDetailsReports".equalsIgnoreCase(action)) {
			url = studentsDetailsReports();
		}else if ("studentsDetailsBonafide".equalsIgnoreCase(action)) {
			url = studentsDetailsBonafide();
		}else if ("printBonafide".equalsIgnoreCase(action)) {
			url = printBonafide();
		}else if ("admissionAbstract".equalsIgnoreCase(action)) {
			url = admissionAbstract();
		}else if ("searchForStudents".equalsIgnoreCase(action)) {
			url = searchForStudents();
		}else if ("generateAdmissionAbstract".equalsIgnoreCase(action)) {
			url = generateAdmissionAbstract();
		}else if ("download".equalsIgnoreCase(action)) {
			url = downloadAdmissionAbstract();
		}
		return url; 
	} 
	

	private String downloadAdmissionAbstract() {
		if(new DocumentService(request, response).downlaodFile()){
            return "exportsuccessaa.jsp";
    }
		return "exportfailure.jsp";
	}

	private String generateAdmissionAbstract() {
		
        if(new DocumentService(request, response).exportAdmissionAbstract()){
                return "exportsuccessaa.jsp";
        }else{
                return "exportfailure.jsp";
        }
        
	}

	private String searchForStudents() {
		if(new DocumentService(request, response).searchForStudents()){
			new DocumentService(request, response).admissionAbstract();
			return "admissionabstract.jsp";
		}
        return error;
	}

	private String admissionAbstract() {
		if(new DocumentService(request, response).admissionAbstract()){
			return "admissionabstract.jsp";
		}
        return error;
	}

	private String printBonafide() {
		return "bonafideprint.jsp";
	}

	private String studentsDetailsBonafide() {
		new StandardService(request, response).viewClasses(); 
		return "studentsdetailsbonafide.jsp";
	}

	private String studentsDetailsReports() {
		new StandardService(request, response).viewClasses(); 
		return "studentsdetailsreports.jsp";
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
