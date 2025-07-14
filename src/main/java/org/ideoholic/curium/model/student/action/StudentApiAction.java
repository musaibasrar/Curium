package org.ideoholic.curium.model.student.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.attendance.dto.StudentAttendanceDetailsResponseDto;
import org.ideoholic.curium.model.documents.dto.SearchStudentDto;
import org.ideoholic.curium.model.documents.dto.SearchStudentResponseDto;
import org.ideoholic.curium.model.feescollection.dto.FeesDetailsResponseDto;
import org.ideoholic.curium.model.parents.dto.ParentListResponseDto;
import org.ideoholic.curium.model.student.dto.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/api/v1/studentProcess")
public interface StudentApiAction {

    @PostMapping("/multiClassSearch")
    ResponseEntity<SearchStudentResponseDto> multiClassSearch(@RequestBody SearchStudentDto dto, @RequestHeader(value = "branchid") String branchId);

    @GetMapping("/advanceSearchStudents")
    ResponseEntity<ResultResponse> advanceSearchStudents(@RequestHeader(value = "branchid") String branchId);

    @RequestMapping(value = "/viewAllSuperAdmin", method = { RequestMethod.GET, RequestMethod.POST })
    ResponseEntity<StudentsSuperAdminResponseDto> viewAllSuperAdmin();

    @GetMapping("/addNew")
    ResponseEntity<ResultResponse> addNew(@RequestHeader(value = "branchid") String branchId);

    @PostMapping("/download")
    ResponseEntity<ResultResponse> downlaodFile();

    @PostMapping("/generateBonafide")
    ResponseEntity<BonafideGenerationResponseDto> generateBonafide(@RequestBody StudentIdsDto dto);

    @PostMapping("/searchStudentsForBonafide")
    ResponseEntity<SearchStudentResponseDto> searchStudentsForBonafide(@RequestBody SearchStudentDto dto, @RequestHeader(value = "branchid") String branchId);

    @GetMapping("/searchForStudents")
    ResponseEntity<SearchStudentResponseDto> searchForStudents(@RequestBody SearchStudentDto dto, @RequestHeader(value = "branchid") String branchId);

    @PostMapping("/feesStructurePerYear")
    ResponseEntity<FeesDetailsResponseDto> feesStructurePerYear(@RequestBody StudentIdDto dto);

    @GetMapping("/viewFeesStructure")
    ResponseEntity<StudentDetailsResponseDto> ViewFeesStructure(@RequestParam(value = "id") String studentId, @RequestHeader(value = "branchid") String branchId);

    @GetMapping("/viewAllStudentsWithParents")
    ResponseEntity<ParentListResponseDto> viewAllStudentsWithParents(@RequestParam(value = "page") String page, @RequestHeader(value = "branchid") String branchId);

    @GetMapping("/viewAllStudents")
    ResponseEntity<ParentListResponseDto> viewAllStudents(@RequestParam(value = "page") String page, @RequestHeader(value = "branchid") String branchId);

    @GetMapping("/studentdetail")
    String studentdetail();

    @PostMapping("/promoteClass")
    ResponseEntity<ResultResponse> promoteClass(@RequestBody PromoteMultipleDto dto, @RequestHeader(value = "currentAcademicYear") String currentAcademicYear, @RequestHeader(value = "branchid") String branchId);

    @PostMapping("/restoreMultiple")
    ResponseEntity<ParentListResponseDto> restoreMultiple(@RequestBody StudentIdsDto dto, @RequestParam(value = "page") String page, @RequestHeader(value = "branchid") String branchId);

    @PostMapping("/deleteMultiple")
    ResponseEntity<StudentAttendanceDetailsResponseDto> deleteMultiple(@RequestBody StudentIdsDto dto, @RequestHeader(value = "branchid") String branchId);

    @GetMapping("/archiveViewAll")
    ResponseEntity<StudentAttendanceDetailsResponseDto> archiveViewAll(@RequestHeader(value = "branchid") String branchId);

    @PostMapping("/archiveMultiple")
    ResponseEntity<ParentListResponseDto> archiveMultiple(@RequestBody StudentIdsDto dto, @RequestParam(value = "page") String page, @RequestHeader(value = "branchid") String branchId);

    @RequestMapping(value = "/updateStudent", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    ResponseEntity<StudentDetailsResponseDto> updateStudent(@RequestPart("fileToUpload") MultipartFile[] uploadedFiles, @RequestPart("studentDto") StudentDto student, @RequestParam(value = "id") String studentId, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "userloginid") String userId, @RequestHeader(value = "branchcode") String branchCode);

    @PostMapping("/updateStudentDetails")
    ResponseEntity<StudentDetailsResponseDto> updateStudentDetails(@RequestParam(value = "id") String studentId, @RequestHeader(value = "branchid") String branchId);

    @RequestMapping(value = "/viewDetails", method = { RequestMethod.GET, RequestMethod.POST })
    ResponseEntity<StudentDetailsResponseDto> viewStudent(@RequestParam(value = "id") String studentId, @RequestHeader(value = "branchid") String branchId);

    @RequestMapping(value = "/viewDetailsbyexternalid", method = { RequestMethod.GET, RequestMethod.POST })
    ResponseEntity<StudentDetailsResponseDto> ViewDetailsbyexternalid(@RequestParam(value = "id") String studentId, @RequestHeader(value = "branchid") String branchId);

    @RequestMapping(value = "/viewFeesDetailsbyexternalid", method = { RequestMethod.GET, RequestMethod.POST })
    ResponseEntity<StudentDetailsResponseDto> ViewFeesDetailsbyexternalid(@RequestParam(value = "id") String studentId, @RequestHeader(value = "branchid") String branchId);

    @RequestMapping(value = "/addStudent", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    ResponseEntity<ResultResponse> addStudent(@RequestPart("student") CreateStudentDto student,
                                                     @RequestPart("fileToUpload") MultipartFile[] uploadedFiles, @RequestHeader(value = "branchcode") String branchCode, @RequestHeader(value = "branchid") String branchId,  @RequestHeader(value = "userloginid") String userId, @RequestHeader(value = "currentAcademicYear") String currentAcademicYear);

    @RequestMapping(value = "/viewAll", method = { RequestMethod.GET, RequestMethod.POST })
    ResponseEntity<ParentListResponseDto> viewAll(@RequestParam(value = "page") String page, @RequestHeader(value = "branchid") String branchId);

    @PostMapping("/exportDataForStudents")
    ResponseEntity<ResultResponse> exportDataForStudents(@RequestBody StudentIdsDto dto, @RequestHeader(value = "branchid") String branchId);

    @PostMapping("/printAdmissionForm")
    String printAdmissionForm();

    @GetMapping("/viewOtherFeesStructure")
    ResponseEntity<StudentDetailsResponseDto> ViewotherFeesStructure(@RequestParam(value = "id") String studentId, @RequestHeader(value = "branchid") String branchId);

}
