/**
 * 
 */
package org.ideoholic.curium.model.documents.action;

import org.ideoholic.curium.dto.ResultResponse;
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
import org.ideoholic.curium.model.parents.dto.ParentListResponseDto;
import org.ideoholic.curium.model.student.dto.StudentIdsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Musaib_2
 * 
 */
public interface DocumentApiAction {
    
	public ResponseEntity<ResultResponse> downloadAdmissionAbstract() ;

	public ResponseEntity<ResultResponse> generateAdmissionAbstract(@RequestBody StudentIdsDto studentIdsDto,@RequestHeader(value = "branchid") String branchid) ;
	
	public ResponseEntity<StudentDetailsDto> searchForStudents(@RequestBody SearchStudentDto searchStudentDto,@RequestHeader(value = "branchid") String branchId ); 
	
	public ResponseEntity<ParentListResponseDto> admissionAbstract(@RequestHeader(value = "branchid") String branchId); 

	public ResponseEntity<String> printBonafide();

	public ResponseEntity<ResultResponse> studentsDetailsBonafide(@RequestHeader(value = "branchid") String branchId);

	public ResponseEntity<ResultResponse> characterCertificate(@RequestHeader(value = "branchid") String branchId);

	public ResponseEntity<ResultResponse> studentsDetailsReports(@RequestHeader(value = "branchid") String branchId);

	public ResponseEntity<TcResponseDto> printTransferCertificate(@RequestParam(value="studentId") int studentId);

	public ResponseEntity<TransferCertificateResponseDto> generateTransferCertificate(@RequestBody TransferCertificateDto transferCertificateDto);
	

	public ResponseEntity<ResultResponse> transferCertificate(@RequestHeader(value = "branchid") String branchId);
	
	public ResponseEntity<ResultResponse> studentsAdmissionReports(@RequestHeader(value = "branchid") String branchId);
	
	public ResponseEntity<ResultResponse> studentsPendingAdmissionReports(@RequestHeader(value = "branchid") String branchId);
	
	public ResponseEntity<SearchStudentResponseDto> multiClassSearchAdmissoinReport(@RequestBody StudentNameSearchDto studentNameSearchDto,@RequestHeader(value = "branchid") String branchId);
		
	public ResponseEntity<SearchStudentResponseDto> multiClassSearchPendingAdmissoinReport(@RequestBody StudentNameSearchDto studentNameSearchDto,@RequestHeader(value = "branchid") String branchId,@RequestHeader(value = "currentAcademicYear") String currentAcademicYear);
	
	public ResponseEntity<ParentDto> generateCharacterCertificate(@RequestBody StudentIdsDto studentIdsDto);

	public ResponseEntity<String> printStudyCertificate();

	public ResponseEntity<ResultResponse> studentsDetailsStudyCertificate(@RequestHeader(value = "branchid") String branchId);
	
	public ResponseEntity<SearchStudentResponseDto> searchStudentsForStudyCertificate(@RequestBody SearchStudentDto searchStudentDto, @RequestHeader(value = "branchid") String branchId);

	public ResponseEntity<ParentDto> generateStudyCertificate(@RequestBody StudentIdsDto studentIdsDto);
	
	public ResponseEntity<SearchStudentResponseDto> searchStudentsForCharacter(@RequestBody SearchStudentDto searchStudentDto, @RequestHeader(value = "branchid") String branchId);

	public ResponseEntity<CharacterResponseDto> printCharacterCertificate(@RequestBody CharacterDto characterDto);
}
