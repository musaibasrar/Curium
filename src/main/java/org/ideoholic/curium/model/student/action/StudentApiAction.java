package org.ideoholic.curium.model.student.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.attendance.dto.StudentAttendanceDetailsResponseDto;
import org.ideoholic.curium.model.documents.dto.SearchStudentDto;
import org.ideoholic.curium.model.documents.dto.SearchStudentResponseDto;
import org.ideoholic.curium.model.feescollection.dto.FeesDetailsResponseDto;
import org.ideoholic.curium.model.parents.dto.ParentListResponseDto;
import org.ideoholic.curium.model.student.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

public interface StudentApiAction {

    ResponseEntity<SearchStudentResponseDto> multiClassSearch(@RequestBody SearchStudentDto dto, @RequestHeader(value = "branchid") String branchId);

    ResponseEntity<ResultResponse> advanceSearchStudents(@RequestHeader(value = "branchid") String branchId);

    ResponseEntity<StudentsSuperAdminResponseDto> viewAllSuperAdmin();

    ResponseEntity<ResultResponse> addNew(@RequestHeader(value = "branchid") String branchId);

    ResponseEntity<ResultResponse> downlaodFile();

    ResponseEntity<BonafideGenerationResponseDto> generateBonafide(@RequestBody StudentIdsDto dto);

    ResponseEntity<SearchStudentResponseDto> searchStudentsForBonafide(@RequestBody SearchStudentDto dto, @RequestHeader(value = "branchid") String branchId);

    ResponseEntity<SearchStudentResponseDto> searchForStudents(@RequestBody SearchStudentDto dto, @RequestHeader(value = "branchid") String branchId);

    ResponseEntity<FeesDetailsResponseDto> feesStructurePerYear(@RequestBody StudentIdDto dto);

    ResponseEntity<StudentDetailsResponseDto> ViewFeesStructure(@RequestParam(value = "id") String studentId, @RequestHeader(value = "branchid") String branchId);

    ResponseEntity<ParentListResponseDto> viewAllStudentsWithParents(@RequestParam(value = "page") String page, @RequestHeader(value = "branchid") String branchId);

    ResponseEntity<ParentListResponseDto> viewAllStudents(@RequestParam(value = "page") String page, @RequestHeader(value = "branchid") String branchId);
    //student detail
    String studentdetail();

    ResponseEntity<ResultResponse> promoteClass(@RequestBody PromoteMultipleDto dto, @RequestHeader(value = "currentAcademicYear") String currentAcademicYear, @RequestHeader(value = "branchid") String branchId);

    ResponseEntity<ParentListResponseDto> restoreMultiple(@RequestBody StudentIdsDto dto, @RequestParam(value = "page") String page, @RequestHeader(value = "branchid") String branchId);

    ResponseEntity<StudentAttendanceDetailsResponseDto> deleteMultiple(@RequestBody StudentIdsDto dto);

    ResponseEntity<StudentAttendanceDetailsResponseDto> archiveViewAll();

    ResponseEntity<ParentListResponseDto> archiveMultiple(@RequestBody StudentIdsDto dto, @RequestParam(value = "page") String page, @RequestHeader(value = "branchid") String branchId);

    ResponseEntity<StudentDetailsResponseDto> updateStudent(@RequestPart("fileToUpload") MultipartFile[] uploadedFiles, @RequestPart("studentDto") StudentDto student, @RequestParam(value = "id") String studentId, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "userloginid") String userId);

    ResponseEntity<StudentDetailsResponseDto> updateStudentDetails(@RequestParam(value = "id") String studentId, @RequestHeader(value = "branchid") String branchId);

    ResponseEntity<StudentDetailsResponseDto> viewStudent(@RequestParam(value = "id") String studentId, @RequestHeader(value = "branchid") String branchId);
    //view detail with external id ViewDetailsbyexternalid
    ResponseEntity<StudentDetailsResponseDto> ViewDetailsbyexternalid(@RequestParam(value = "id") String studentId, @RequestHeader(value = "branchid") String branchId);
    //end view detail
    ResponseEntity<StudentDetailsResponseDto> ViewFeesDetailsbyexternalid(@RequestParam(value = "id") String studentId, @RequestHeader(value = "branchid") String branchId);

    ResponseEntity<ResultResponse> addStudent(@RequestPart("student") CreateStudentDto student,
                                                     @RequestPart("fileToUpload") MultipartFile[] uploadedFiles, @RequestHeader(value = "branchcode") String branchCode, @RequestHeader(value = "branchid") String branchId,  @RequestHeader(value = "userloginid") String userId, @RequestHeader(value = "currentAcademicYear") String currentAcademicYear);

    ResponseEntity<ParentListResponseDto> viewAll(@RequestParam(value = "page") String page, @RequestHeader(value = "branchid") String branchId);

    ResponseEntity<ResultResponse> exportDataForStudents(@RequestBody StudentIdsDto dto, @RequestHeader(value = "branchid") String branchId);

    String printAdmissionForm();

    ResponseEntity<StudentDetailsResponseDto> ViewotherFeesStructure(@RequestParam(value = "id") String studentId, @RequestHeader(value = "branchid") String branchId);

}
