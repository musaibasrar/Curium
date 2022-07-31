/**
 * 
 */
package org.ideoholic.curium.model.documents.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.documents.service.DocumentService;
import org.ideoholic.curium.model.std.service.StandardService;
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
@RequestMapping("/DocumentsProcess")
public class DocumentAction {

	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpServletResponse response;
	
	@Autowired
	HttpSession httpSession;
	
	public String error ="error";

	
	@PostMapping("/download")
	public String downloadAdmissionAbstract() {
		if(new DocumentService(request, response).downlaodFile()){
            return "exportsuccessaa";
    }
		return "exportfailure";
	}

	@PostMapping("/generateAdmissionAbstract")
	public String generateAdmissionAbstract() {
		
        if(new DocumentService(request, response).exportAdmissionAbstract()){
                return "exportsuccessaa";
        }else{
                return "exportfailure";
        }
        
	}

	@PostMapping("/searchForStudents")
	public String searchForStudents() {
		if(new DocumentService(request, response).searchForStudents()){
			new DocumentService(request, response).admissionAbstract();
			return "admissionabstract";
		}
        return error;
	}

	@GetMapping("/admissionAbstract")
	public String admissionAbstract() {
		if(new DocumentService(request, response).admissionAbstract()){
			return "admissionabstract";
		}
        return error;
	}

	@GetMapping("/printBonafide")
	public String printBonafide() {
		return "bonafideprint";
	}

	@GetMapping("/studentsDetailsBonafide")
	public String studentsDetailsBonafide() {
		new StandardService(request, response).viewClasses(); 
		return "studentsdetailsbonafide";
	}

	@GetMapping("/studentsDetailsReports")
	public String studentsDetailsReports() {
		new StandardService(request, response).viewClasses(); 
		return "studentsdetailsreports";
	}

	@GetMapping("/PrintTransferCertificate")
	public String printTransferCertificate() {
		
		if(new DocumentService(request, response).printTransferCertificate()){
			return "transfercertificateprint";
		}
        return error;
	}

	@PostMapping("/generateTransferCertificate")
	public String generateTransferCertificate() {
		
		String result = new DocumentService(request, response).generateTransferCertificate();
		
		if("true".equalsIgnoreCase(result)){
			return "transfercertificatepreview";
		}else if("studentexists".equalsIgnoreCase(result)){
        return "transfercertificatefail";
		}
		return error;
	}

	@GetMapping("/transferCertificate")
	public String transferCertificate() {
		if(new DocumentService(request, response).transferCertificate()){
			return "transfercertificate";
		}
        return error;
	}
	
	@GetMapping("/studentsAdmissionReports")
	public String studentsAdmissionReports() {
		new StandardService(request, response).viewClasses(); 
		return "studentsadmissionreports";
	}
	
	@GetMapping("/studentsPendingAdmissionReports")
	public String studentsPendingAdmissionReports() {
		new StandardService(request, response).viewClasses(); 
		return "studentspendingadmissionreports";
	}
	
	@PostMapping("/multiClassSearchAdmissoinReport")
	public String multiClassSearchAdmissoinReport() {
		new DocumentService(request, response).multiClassSearchAdmissoinReport(); 
		return "studentsadmissionreports";
	}
	
	@PostMapping("/multiClassSearchPendingAdmissoinReport")
	public String multiClassSearchPendingAdmissoinReport() {
		new DocumentService(request, response).multiClassSearchPendingAdmissoinReport(); 
		return "studentspendingadmissionreports";
	}
}
