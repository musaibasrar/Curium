package org.ideoholic.curium.model.student.action;

import lombok.extern.slf4j.Slf4j;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
public class StudentApiActionImpl implements StudentApiAction{

    @Autowired
    private StudentService studentService;
    @Autowired
    private StampFeesService stampFeesService;
    @Autowired
    private StandardService standardService;

    public ResponseEntity<SearchStudentResponseDto> multiClassSearch(SearchStudentDto dto, String branchId) {
        SearchStudentResponseDto result = stampFeesService.multiClassSearch(dto, branchId);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<ResultResponse> advanceSearchStudents(String branchId) {
        ResultResponse result = standardService.viewClasses(branchId);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<StudentsSuperAdminResponseDto> viewAllSuperAdmin() {
        StudentsSuperAdminResponseDto result = studentService.viewAllStudentsSuperAdmin();
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<ResultResponse> addNew(String branchId) {
        standardService.viewClasses(branchId);
        ResultResponse result = studentService.addNew(branchId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity downlaodFile() {
        ResultResponse result = studentService.downlaodFile();
        if (result.isSuccess()) {
            return ResponseEntity.ok().build();
        }
        throw new CustomResponseException(CustomErrorMessage.EXPORTFAILURE);
    }

    public ResponseEntity<BonafideGenerationResponseDto> generateBonafide(StudentIdsDto dto) {
        BonafideGenerationResponseDto result = studentService.generateBonafide(dto);
        if ( result != null && result.isSuccess()) {
            return ResponseEntity.ok(result);
        } else {
            throw new CustomResponseException(CustomErrorMessage.BONAFIDEFAILURE);
        }
    }

    public ResponseEntity<SearchStudentResponseDto> searchStudentsForBonafide(SearchStudentDto dto, String branchId) {
        SearchStudentResponseDto result = stampFeesService.advanceSearch(dto, branchId);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<SearchStudentResponseDto> searchForStudents(SearchStudentDto dto, String branchId) {
        SearchStudentResponseDto result = stampFeesService.advanceSearch(dto, branchId);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<FeesDetailsResponseDto> feesStructurePerYear(StudentIdDto dto) {
        FeesDetailsResponseDto result = studentService.viewfeesStructurePerYear(dto);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<StudentDetailsResponseDto> ViewFeesStructure(String studentId, String branchId) {
        StudentDetailsResponseDto result = studentService.viewDetailsOfStudent(studentId, branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        } else {
            throw new CustomResponseException(CustomErrorMessage.ERROR);
        }
    }

    public ResponseEntity<ParentListResponseDto> viewAllStudentsWithParents(String page, String branchId) {
        ParentListResponseDto result = studentService.viewAllStudentsParents(page, branchId);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<ParentListResponseDto> viewAllStudents(String page, String branchId) {
        ParentListResponseDto result = studentService.viewAllStudentsParents(page, branchId);
        return ResponseEntity.ok(result);
    }
    //student detail
    public String studentdetail() {
        //new StudentService(request, response).viewAllStudentsParents();
        return "Views_student_detail";
    }
    //end

    public ResponseEntity<ResultResponse> promoteClass(PromoteMultipleDto dto, String currentAcademicYear, String branchId) {
        ResultResponse result = studentService.promoteMultiple(dto, currentAcademicYear, branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.FAILUREPROMOTE);
    }

    public ResponseEntity<ParentListResponseDto> restoreMultiple(StudentIdsDto dto, String page, String branchId) {
        studentService.restoreMultiple(dto);
        return viewAll(page, branchId);
    }

    public ResponseEntity<StudentAttendanceDetailsResponseDto> deleteMultiple(StudentIdsDto dto, String branchId) {
        studentService.deleteMultiple(dto);
        return archiveViewAll(branchId);
    }

    public ResponseEntity<StudentAttendanceDetailsResponseDto> archiveViewAll(String branchId) {
        StudentAttendanceDetailsResponseDto result = studentService.viewAllStudentsArchive(branchId);
        log.error("IN action's view all Archive");
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<ParentListResponseDto> archiveMultiple(StudentIdsDto dto, String page, String branchId) {
        studentService.archiveMultiple(dto);
        return viewAll(page, branchId);
    }

    public ResponseEntity<StudentDetailsResponseDto> updateStudent(MultipartFile[] uploadedFiles, StudentDto student, String studentId, String branchId, String userId, String branchCode) {
        studentService.updateStudent(uploadedFiles, student, branchId,studentId, userId, branchCode);
        return viewStudent(studentId, branchId);
    }

    public ResponseEntity<StudentDetailsResponseDto> updateStudentDetails(String studentId, String branchId) {
        StudentDetailsResponseDto result = studentService.viewDetailsOfStudent(studentId, branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<StudentDetailsResponseDto> viewStudent(String studentId, String branchId) {
        StudentDetailsResponseDto result = studentService.viewDetailsOfStudent(studentId, branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }
    //view detail with external id ViewDetailsbyexternalid
    public ResponseEntity<StudentDetailsResponseDto> ViewDetailsbyexternalid(String studentId, String branchId) {
        StudentDetailsResponseDto result = studentService.viewDetailsbySidStudent(studentId, branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }
    //end view detail
    public ResponseEntity<StudentDetailsResponseDto> ViewFeesDetailsbyexternalid(String studentId, String branchId) {
        StudentDetailsResponseDto result = studentService.viewDetailsbySidStudent(studentId, branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }
    public ResponseEntity<ResultResponse> addStudent(CreateStudentDto student,
                                                     MultipartFile[] uploadedFiles, String branchCode, String branchId, String userId, String currentAcademicYear) {
        ResultResponse result = studentService.addStudent(student, uploadedFiles, branchCode, branchId, userId, currentAcademicYear);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        } else {
            throw new CustomResponseException(CustomErrorMessage.NOTSAVED);
        }
    }

    public ResponseEntity<ParentListResponseDto> viewAll(String page, String branchId) {
        ParentListResponseDto result = studentService.viewAllStudentsParents(page, branchId);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<ResultResponse> exportDataForStudents(StudentIdsDto dto, String branchId) {
        ResultResponse result = studentService.exportDataForStudents(dto, branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        } else {
            throw new CustomResponseException(CustomErrorMessage.EXPORTFAILURE);
        }
    }

    public String printAdmissionForm() {
        return "printadmissionform";
    }

    public ResponseEntity<StudentDetailsResponseDto> ViewotherFeesStructure(String studentId, String branchId) {
        StudentDetailsResponseDto result = studentService.viewOtherFeesDetailsOfStudent(studentId, branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

}
