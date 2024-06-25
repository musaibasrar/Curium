/**
 * 
 */
package org.ideoholic.curium.model.documents.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.adminexpenses.action.AdminActionAdapter;
import org.ideoholic.curium.model.documents.service.DocumentService;
import org.ideoholic.curium.model.stampfees.service.StampFeesService;
import org.ideoholic.curium.model.std.action.StandardActionAdapter;
import org.ideoholic.curium.model.std.service.StandardService;
import org.ideoholic.curium.model.student.service.StudentService;
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
	private HttpServletRequest request;
	
	@Autowired
	private HttpServletResponse response;
	
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private DocumentActionAdapter documentActionAdapter;

	@Autowired
	private StandardActionAdapter standardActionAdapter;
	
	public String error ="error";

	
	@PostMapping("/download")
	public String downloadAdmissionAbstract() {
		if(new DocumentService(request, response, standardActionAdapter).downlaodFile()){
            return "exportsuccessaa";
    }
		return "exportfailure";
	}

	@PostMapping("/generateAdmissionAbstract")
	public String generateAdmissionAbstract() {
		
        if(documentActionAdapter.exportAdmissionAbstract()){
                return "exportsuccessaa";
        }else{
                return "exportfailure";
        }
        
	}
	
	
	@PostMapping("/searchForStudents")
	public String searchForStudents() {
		if(documentActionAdapter.searchForStudents()){
			documentActionAdapter.admissionAbstract();
			return "admissionabstract";
		}
        return error;
	}
	
	@GetMapping("/admissionAbstract")
	public String admissionAbstract() {
		if(documentActionAdapter.admissionAbstract()){
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
		standardActionAdapter.viewClasses();
		return "studentsdetailsbonafide";
	}
	@GetMapping("/characterCertificate")
	public String characterCertificate() {
		standardActionAdapter.viewClasses();
		return "studentcharactersdetails";
	}
	@GetMapping("/studentsDetailsReports")
	public String studentsDetailsReports() {
		standardActionAdapter.viewClasses();
		return "studentsdetailsreports";
	}

	@GetMapping("/PrintTransferCertificate")
	public String printTransferCertificate() {
		
		if(documentActionAdapter.printTransferCertificate()){
			return "transfercertificateprint";
		}
        return error;
	}

	@PostMapping("/generateTransferCertificate")
	public String generateTransferCertificate() {
		
		String result = new DocumentService(request, response, standardActionAdapter).generateTransferCertificate();
		
		if("true".equalsIgnoreCase(result)){
			return "transfercertificatepreview";
		}else if("studentexists".equalsIgnoreCase(result)){
        return "transfercertificatepreviewduplicate";
		}
		return error;
	}

	@GetMapping("/transferCertificate")
	public String transferCertificate() {
		if(documentActionAdapter.transferCertificate()){
			return "transfercertificate";
		}
        return error;
	}
	
	@GetMapping("/studentsAdmissionReports")
	public String studentsAdmissionReports() {
		standardActionAdapter.viewClasses();
		return "studentsadmissionreports";
	}
	
	@GetMapping("/studentsPendingAdmissionReports")
	public String studentsPendingAdmissionReports() {
		standardActionAdapter.viewClasses();
		return "studentspendingadmissionreports";
	}
	
	@PostMapping("/multiClassSearchAdmissoinReport")
	public String multiClassSearchAdmissoinReport() {
		documentActionAdapter.multiClassSearchAdmissoinReport(); 
		return "studentsadmissionreports";
	}
	
		
	@PostMapping("/multiClassSearchPendingAdmissoinReport")
	public String multiClassSearchPendingAdmissoinReport() {
		documentActionAdapter.multiClassSearchPendingAdmissoinReport(); 
		return "studentspendingadmissionreports";
	}
	
	@PostMapping("/GenerateCharacterCertificate")
	public String generateCharacterCertificate() {
		String result = documentActionAdapter.GenerateCharacterCertificate();
		if (result != null) {
			return result;
		} else {
			return "bonafidefailure";
		}
	}

	@GetMapping("/printStudyCertificate")
	public String printStudyCertificate() {
		return "studycertprint";
	}
	
	@GetMapping("/studentsDetailsStudyCertificate")
	public String studentsDetailsStudyCertificate() {
		standardActionAdapter.viewClasses();
		return "studentsdetailsstudycertificate";
	}
	
	@PostMapping("/searchStudentsForStudyCertificate")
	public String searchStudentsForStudyCertificate() {
		new StampFeesService(request, response).advanceSearch();
		return "studentsdetailsstudycertificate";
	}

	@PostMapping("/GenerateStudyCertificate")
	public String generateStudyCertificate() {
		String result = documentActionAdapter.generateStudyCertificate();
		if (result != null) {
			return result;
		} else {
			return "bonafidefailure";
		}
	}
	
	@PostMapping("/searchStudentsForCharacter")
	public String searchStudentsForCharacter() {
		new StampFeesService(request, response).advanceSearch();
		return "studentcharactersdetails";
	}
	
	@PostMapping("/printCharacterCertificate")
	public String printCharacterCertificate() {
		new DocumentService(request, response, standardActionAdapter).printCharacterCertificate();
		return "characterprint";
	}
}
