/**
 * 
 */
package org.ideoholic.curium.model.documents.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.exceptions.CustomErrorMessage;
import org.ideoholic.curium.exceptions.CustomResponseException;
import org.ideoholic.curium.model.documents.dto.CharacterDto;
import org.ideoholic.curium.model.documents.dto.CharacterResponseDto;
import org.ideoholic.curium.model.documents.dto.ParentDto;
import org.ideoholic.curium.model.documents.dto.SearchStudentDto;
import org.ideoholic.curium.model.documents.dto.SearchStudentResponseDto;
import org.ideoholic.curium.model.documents.dto.StudentDetailsDto;
import org.ideoholic.curium.model.documents.dto.StudentNameSearchDto;
import org.ideoholic.curium.model.documents.dto.TcResponseDto;
import org.ideoholic.curium.model.documents.dto.TransferCertificateDto;
import org.ideoholic.curium.model.documents.dto.TransferCertificateResponseDto;
import org.ideoholic.curium.model.documents.service.DocumentService;
import org.ideoholic.curium.model.parents.dto.ParentListResponseDto;
import org.ideoholic.curium.model.stampfees.service.StampFeesService;
import org.ideoholic.curium.model.std.service.StandardService;
import org.ideoholic.curium.model.student.dto.StudentIdsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Musaib_2
 * 
 */
@Controller
@RequestMapping("/api/v1/DocumentsProcess")
public class DocumentApiActionImpl implements DocumentApiAction {
    
	@Autowired
	private DocumentService documentService;
	
	@Autowired
    private StandardService standardService;
	
	@Autowired
    private StampFeesService stampFeesService;
	
	
	public String error ="error";

	
	@PostMapping("/download")
	public ResponseEntity<ResultResponse> downloadAdmissionAbstract() {
		ResultResponse result = documentService.downlaodFile();
		if(result.isSuccess()){
            return ResponseEntity.ok(result);
    }
		throw new CustomResponseException(CustomErrorMessage.EXPORTFAILURE);
	}

	@PostMapping("/generateAdmissionAbstract")
	public ResponseEntity<ResultResponse> generateAdmissionAbstract(@RequestBody StudentIdsDto studentIdsDto,@RequestHeader(value = "branchid") String branchid) {
		
		ResultResponse result = documentService.exportAdmissionAbstract(studentIdsDto,branchid);
        if(result.isSuccess()){
                return ResponseEntity.ok(result);
        }else{
        	throw new CustomResponseException(CustomErrorMessage.EXPORTFAILURE);
        }
        
	}
	
	
	@PostMapping("/searchForStudents")
	public ResponseEntity<StudentDetailsDto> searchForStudents(@RequestBody SearchStudentDto searchStudentDto,@RequestHeader(value = "branchid") String branchId ) {
		StudentDetailsDto studentDetailsDto = new StudentDetailsDto();
		SearchStudentResponseDto searchStudentResponseDto = documentService.searchForStudents(searchStudentDto,branchId);
		studentDetailsDto.copySearchStudentResponseDto(searchStudentResponseDto);
		if(searchStudentResponseDto.isSuccess()){
			ParentListResponseDto parentListResponseDto = documentService.admissionAbstract(branchId);
			studentDetailsDto.copyParentListResponseDto(parentListResponseDto);
			return ResponseEntity.ok(studentDetailsDto);
		}
		throw new CustomResponseException(CustomErrorMessage.ERROR);
	}
	
	@GetMapping("/admissionAbstract")
	public ResponseEntity<ParentListResponseDto> admissionAbstract(@RequestHeader(value = "branchid") String branchId) {
		ParentListResponseDto result = documentService.admissionAbstract(branchId);
		if(result.isSuccess()){
			return ResponseEntity.ok(result);
		}
         throw new CustomResponseException(CustomErrorMessage.ERROR);
        }

	@GetMapping("/printBonafide")
	public ResponseEntity<String> printBonafide() {
		return ResponseEntity.ok("bonafideprint");
	}

	@GetMapping("/studentsDetailsBonafide")
	public ResponseEntity<ResultResponse> studentsDetailsBonafide(@RequestHeader(value = "branchid") String branchId) {
		ResultResponse result = standardService.viewClasses(branchId);
		return ResponseEntity.ok(result);
	}
	@GetMapping("/characterCertificate")
	public ResponseEntity<ResultResponse> characterCertificate(@RequestHeader(value = "branchid") String branchId) {
		ResultResponse result = standardService.viewClasses(branchId);
		return ResponseEntity.ok(result);
	}
	@GetMapping("/studentsDetailsReports")
	public ResponseEntity<ResultResponse> studentsDetailsReports(@RequestHeader(value = "branchid") String branchId) {
		ResultResponse result = standardService.viewClasses(branchId);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/PrintTransferCertificate")
	public ResponseEntity<TcResponseDto> printTransferCertificate(@RequestParam(value="studentId") int studentId) {
		TcResponseDto result = documentService.printTransferCertificate(studentId);
		if(result.isSuccess()){
			return ResponseEntity.ok(result);
		}
		throw new CustomResponseException(CustomErrorMessage.ERROR);
	}

	@PostMapping("/generateTransferCertificate")
	public ResponseEntity<TransferCertificateResponseDto> generateTransferCertificate(@RequestBody TransferCertificateDto transferCertificateDto) {
		
		TransferCertificateResponseDto transferCertificateResponseDto = documentService.generateTransferCertificate(transferCertificateDto);
		
		 switch(transferCertificateResponseDto.getStatus())
		    {
		    case TCEXISTS:
		    	return ResponseEntity.ok(transferCertificateResponseDto);
		    case TCNEW:
		    	return ResponseEntity.ok(transferCertificateResponseDto);
		    case TCFAILED:
		    	default:
		    		throw new CustomResponseException(CustomErrorMessage.ERROR);
		    }	}
	
	

	@GetMapping("/transferCertificate")
	public ResponseEntity<ResultResponse> transferCertificate(@RequestHeader(value = "branchid") String branchId) {
		ResultResponse result = documentService.transferCertificate(branchId);
		if(result.isSuccess()){
			return ResponseEntity.ok(result);
		}
		throw new CustomResponseException(CustomErrorMessage.ERROR);
	}
	
	@GetMapping("/studentsAdmissionReports")
	public ResponseEntity<ResultResponse> studentsAdmissionReports(@RequestHeader(value = "branchid") String branchId) {
		ResultResponse result = standardService.viewClasses(branchId);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/studentsPendingAdmissionReports")
	public ResponseEntity<ResultResponse> studentsPendingAdmissionReports(@RequestHeader(value = "branchid") String branchId) {
		ResultResponse result = standardService.viewClasses(branchId);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/multiClassSearchAdmissoinReport")
	public ResponseEntity<SearchStudentResponseDto> multiClassSearchAdmissoinReport(@RequestBody StudentNameSearchDto studentNameSearchDto,@RequestHeader(value = "branchid") String branchId) {
		SearchStudentResponseDto result = documentService.multiClassSearchAdmissoinReport(studentNameSearchDto,branchId); 
		return ResponseEntity.ok(result);
	}
	
		
	@PostMapping("/multiClassSearchPendingAdmissoinReport")
	public ResponseEntity<SearchStudentResponseDto> multiClassSearchPendingAdmissoinReport(@RequestBody StudentNameSearchDto studentNameSearchDto,@RequestHeader(value = "branchid") String branchId,@RequestHeader(value = "currentAcademicYear") String currentAcademicYear) {
		SearchStudentResponseDto result = documentService.multiClassSearchPendingAdmissoinReport(studentNameSearchDto,branchId,currentAcademicYear); 
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/GenerateCharacterCertificate")
	public ResponseEntity<ParentDto> generateCharacterCertificate(@RequestBody StudentIdsDto studentIdsDto) {
		ParentDto result = documentService.GenerateCharacterCertificate(studentIdsDto);
		if (result != null) {
			return ResponseEntity.ok(result);
		} else {
			throw new CustomResponseException(CustomErrorMessage.BONAFIDEFAILURE);
		}
	}

	@GetMapping("/printStudyCertificate")
	public ResponseEntity<String> printStudyCertificate() {
		return ResponseEntity.ok("studycertprint");
	}
	
	@GetMapping("/studentsDetailsStudyCertificate")
	public ResponseEntity<ResultResponse> studentsDetailsStudyCertificate(@RequestHeader(value = "branchid") String branchId) {
		ResultResponse result = standardService.viewClasses(branchId);
		return ResponseEntity.ok(result);
		}
	
	@PostMapping("/searchStudentsForStudyCertificate")
	public ResponseEntity<SearchStudentResponseDto> searchStudentsForStudyCertificate(@RequestBody SearchStudentDto searchStudentDto, @RequestHeader(value = "branchid") String branchId) {
		SearchStudentResponseDto result = stampFeesService.advanceSearch(searchStudentDto,branchId);
		return  ResponseEntity.ok(result);
	}

	@PostMapping("/GenerateStudyCertificate")
	public ResponseEntity<ParentDto> generateStudyCertificate(@RequestBody StudentIdsDto studentIdsDto) {
		ParentDto result = documentService.generateStudyCertificate(studentIdsDto);
		if (result != null) {
			return ResponseEntity.ok(result);
			}
		else {
			throw new CustomResponseException(CustomErrorMessage.BONAFIDEFAILURE);
		}
	}
	
	@PostMapping("/searchStudentsForCharacter")
	public ResponseEntity<SearchStudentResponseDto> searchStudentsForCharacter(@RequestBody SearchStudentDto searchStudentDto, @RequestHeader(value = "branchid") String branchId) {
		SearchStudentResponseDto result = stampFeesService.advanceSearch(searchStudentDto,branchId);
		return  ResponseEntity.ok(result);
	}
	
	@PostMapping("/printCharacterCertificate")
	public ResponseEntity<CharacterResponseDto> printCharacterCertificate(@RequestBody CharacterDto characterDto) {
		CharacterResponseDto result = documentService.printCharacterCertificate(characterDto);
		return ResponseEntity.ok(result);
	}
}
