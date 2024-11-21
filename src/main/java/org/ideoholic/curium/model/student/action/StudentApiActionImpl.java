package org.ideoholic.curium.model.student.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.exceptions.CustomErrorMessage;
import org.ideoholic.curium.exceptions.CustomResponseException;
import org.ideoholic.curium.model.attendance.dto.StudentAttendanceDetailsResponseDto;
import org.ideoholic.curium.model.documents.dto.SearchStudentDto;
import org.ideoholic.curium.model.documents.dto.SearchStudentResponseDto;
import org.ideoholic.curium.model.feescollection.dto.FeesDetailsResponseDto;
import org.ideoholic.curium.model.parents.dto.ParentListResponseDto;
import org.ideoholic.curium.model.stampfees.service.StampFeesService;
import org.ideoholic.curium.model.std.service.StandardService;
import org.ideoholic.curium.model.student.dto.*;
import org.ideoholic.curium.model.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/studentProcess")
public class StudentApiActionImpl implements StudentApiAction{
    @Autowired
    private StudentService studentService;
    @Autowired
    private StampFeesService stampFeesService;
    @Autowired
    private StandardService standardService;

    @PostMapping("/multiClassSearch")
    public ResponseEntity<SearchStudentResponseDto> multiClassSearch(@RequestBody SearchStudentDto dto, @RequestHeader(value = "branchid") String branchId) {
        SearchStudentResponseDto result = stampFeesService.multiClassSearch(dto, branchId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/advanceSearchStudents")
    public ResponseEntity<ResultResponse> advanceSearchStudents(@RequestHeader(value = "branchid") String branchId) {
        ResultResponse result = standardService.viewClasses(branchId);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/viewAllSuperAdmin", method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseEntity<StudentsSuperAdminResponseDto> viewAllSuperAdmin() {
        StudentsSuperAdminResponseDto result = studentService.viewAllStudentsSuperAdmin();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/addNew")
    public ResponseEntity<ResultResponse> addNew(@RequestHeader(value = "branchid") String branchId) {
        standardService.viewClasses(branchId);
        ResultResponse result = studentService.addNew(branchId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    @PostMapping("/download")
    public ResponseEntity<ResultResponse> downlaodFile() {
        ResultResponse result = studentService.downlaodFile();
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.EXPORTFAILURE);
    }

    @PostMapping("/GenerateBonafide")
    public ResponseEntity<BonafideGenerationResponseDto> generateBonafide(@RequestBody StudentIdsDto dto) {
        BonafideGenerationResponseDto result = studentService.generateBonafide(dto);
        if ( result != null && result.isSuccess()) {
            return ResponseEntity.ok(result);
        } else {
            throw new CustomResponseException(CustomErrorMessage.BONAFIDEFAILURE);
        }
    }

    @PostMapping("/searchStudentsForBonafide")
    public ResponseEntity<SearchStudentResponseDto> searchStudentsForBonafide(@RequestBody SearchStudentDto dto, @RequestHeader(value = "branchid") String branchId) {
        SearchStudentResponseDto result = stampFeesService.advanceSearch(dto, branchId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/searchForStudents")
    public ResponseEntity<SearchStudentResponseDto> searchForStudents(@RequestBody SearchStudentDto dto, @RequestHeader(value = "branchid") String branchId) {
        SearchStudentResponseDto result = stampFeesService.advanceSearch(dto, branchId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/feesStructurePerYear")
    public ResponseEntity<FeesDetailsResponseDto> feesStructurePerYear(@RequestBody StudentIdDto dto) {
        FeesDetailsResponseDto result = studentService.viewfeesStructurePerYear(dto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/ViewFeesStructure")
    public ResponseEntity<StudentDetailsResponseDto> ViewFeesStructure(@RequestParam(value = "id") String studentId, @RequestHeader(value = "branchid") String branchId) {
        StudentDetailsResponseDto result = studentService.viewDetailsOfStudent(studentId, branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        } else {
            throw new CustomResponseException(CustomErrorMessage.ERROR);
        }
    }

    @GetMapping("/viewAllStudentsWithParents")
    public ResponseEntity<ParentListResponseDto> viewAllStudentsWithParents(@RequestParam(value = "page") String page, @RequestHeader(value = "branchid") String branchId) {
        ParentListResponseDto result = studentService.viewAllStudentsParents(page, branchId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/viewAllStudents")
    public ResponseEntity<ParentListResponseDto> viewAllStudents(@RequestParam(value = "page") String page, @RequestHeader(value = "branchid") String branchId) {
        ParentListResponseDto result = studentService.viewAllStudentsParents(page, branchId);
        return ResponseEntity.ok(result);
    }
    //student detail
    @GetMapping("/studentdetail")
    public String studentdetail() {
        //new StudentService(request, response).viewAllStudentsParents();
        return "Views_student_detail";
    }
    //end

    @PostMapping("/promoteClass")
    public ResponseEntity<ResultResponse> promoteClass(@RequestBody PromoteMultipleDto dto, @RequestHeader(value = "currentAcademicYear") String currentAcademicYear, @RequestHeader(value = "branchid") String branchId) {
        ResultResponse result = studentService.promoteMultiple(dto, currentAcademicYear, branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.FAILUREPROMOTE);
    }

    @PostMapping("/restoreMultiple")
    public ResponseEntity<ParentListResponseDto> restoreMultiple(@RequestBody StudentIdsDto dto, @RequestParam(value = "page") String page, @RequestHeader(value = "branchid") String branchId) {
        studentService.restoreMultiple(dto);
        return viewAll(page, branchId);
    }

    @PostMapping("/deleteMultiple")
    public ResponseEntity<StudentAttendanceDetailsResponseDto> deleteMultiple(@RequestBody StudentIdsDto dto) {
        studentService.deleteMultiple(dto);
        return archiveViewAll();
    }

    @GetMapping("/archiveViewAll")
    public ResponseEntity<StudentAttendanceDetailsResponseDto> archiveViewAll() {
        StudentAttendanceDetailsResponseDto result = studentService.viewAllStudentsArchive();
        System.out.println("IN action's view all Archive");
        return ResponseEntity.ok(result);
    }

    @PostMapping("/archiveMultiple")
    public ResponseEntity<ParentListResponseDto> archiveMultiple(@RequestBody StudentIdsDto dto, @RequestParam(value = "page") String page, @RequestHeader(value = "branchid") String branchId) {
        studentService.archiveMultiple(dto);
        return viewAll(page, branchId);
    }

    @RequestMapping(value = "/updateStudent", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<StudentDetailsResponseDto> updateStudent(@RequestPart("fileToUpload") MultipartFile[] uploadedFiles, @RequestPart("studentDto") StudentDto student, @RequestParam(value = "id") String studentId, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "userloginid") String userId) {
        studentService.updateStudent(uploadedFiles, student, branchId, userId);
        return viewStudent(studentId, branchId);
    }

    @PostMapping("/updateStudentDetails")
    public ResponseEntity<StudentDetailsResponseDto> updateStudentDetails(@RequestParam(value = "id") String studentId, @RequestHeader(value = "branchid") String branchId) {
        StudentDetailsResponseDto result = studentService.viewDetailsOfStudent(studentId, branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    @RequestMapping(value = "/ViewDetails", method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseEntity<StudentDetailsResponseDto> viewStudent(@RequestParam(value = "id") String studentId, @RequestHeader(value = "branchid") String branchId) {
        StudentDetailsResponseDto result = studentService.viewDetailsOfStudent(studentId, branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }
    //view detail with external id ViewDetailsbyexternalid
    @RequestMapping(value = "/ViewDetailsbyexternalid", method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseEntity<StudentDetailsResponseDto> ViewDetailsbyexternalid(@RequestParam(value = "id") String studentId, @RequestHeader(value = "branchid") String branchId) {
        StudentDetailsResponseDto result = studentService.viewDetailsbySidStudent(studentId, branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }
    //end view detail
    @RequestMapping(value = "/ViewFeesDetailsbyexternalid", method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseEntity<StudentDetailsResponseDto> ViewFeesDetailsbyexternalid(@RequestParam(value = "id") String studentId, @RequestHeader(value = "branchid") String branchId) {
        StudentDetailsResponseDto result = studentService.viewDetailsbySidStudent(studentId, branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }
    @RequestMapping(value = "/AddStudent", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ResultResponse> addStudent(@RequestPart("student") CreateStudentDto student,
                                                     @RequestPart("fileToUpload") MultipartFile[] uploadedFiles, @RequestHeader(value = "branchcode") String branchCode, @RequestHeader(value = "branchid") String branchId,  @RequestHeader(value = "userloginid") String userId, @RequestHeader(value = "currentAcademicYear") String currentAcademicYear) {
        ResultResponse result = studentService.addStudent(student, uploadedFiles, branchCode, branchId, userId, currentAcademicYear);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        } else {
            throw new CustomResponseException(CustomErrorMessage.NOTSAVED);
        }
    }

    @RequestMapping(value = "/viewAll", method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseEntity<ParentListResponseDto> viewAll(@RequestParam(value = "page") String page, @RequestHeader(value = "branchid") String branchId) {
        ParentListResponseDto result = studentService.viewAllStudentsParents(page, branchId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/exportDataForStudents")
    public ResponseEntity<ResultResponse> exportDataForStudents(@RequestBody StudentIdsDto dto, @RequestHeader(value = "branchid") String branchId) {
        ResultResponse result = studentService.exportDataForStudents(dto, branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        } else {
            throw new CustomResponseException(CustomErrorMessage.EXPORTFAILURE);
        }
    }

    @PostMapping("/printAdmissionForm")
    public String printAdmissionForm() {
        return "printadmissionform";
    }

    @GetMapping("/ViewotherFeesStructure")
    public ResponseEntity<StudentDetailsResponseDto> ViewotherFeesStructure(@RequestParam(value = "id") String studentId, @RequestHeader(value = "branchid") String branchId) {
        StudentDetailsResponseDto result = studentService.viewOtherFeesDetailsOfStudent(studentId, branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

}
