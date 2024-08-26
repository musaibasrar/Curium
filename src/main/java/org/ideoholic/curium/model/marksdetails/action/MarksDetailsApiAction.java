package org.ideoholic.curium.model.marksdetails.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.documents.dto.SearchStudentResponseDto;
import org.ideoholic.curium.model.marksdetails.dto.*;
import org.ideoholic.curium.model.student.dto.StudentIdsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

public interface MarksDetailsApiAction {
    ResponseEntity<ResultResponse> marksEntry(@RequestHeader(value = "branchid") String branchId);

    ResponseEntity<ResultResponse> downloadReportCard();

    ResponseEntity<ResultResponse> progressreport(@RequestHeader(value = "branchid") String branchId);

    ResponseEntity<StudentGraphResponseDto> getStudentGraph(@RequestBody StudentIdsDto dto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "currentAcademicYear") String currentAcademicYear);

    ResponseEntity<GraphicalReportDto> getGraphicalReportData(@RequestHeader(value = "branchid") String branchId);

    ResponseEntity<SearchStudentResponseDto> searchForReport(@RequestBody SearchStudentExamDto dto, @RequestHeader(value = "branchid") String branchId);

    ResponseEntity<GenerateReportResponseDto> generateReport(@RequestBody GenerateReportDto dto, @RequestHeader(value = "currentAcademicYear") String currentAcademicYear, @RequestHeader(value = "branchid") String branchId);

    ResponseEntity<GenerateReportResponseDto> generateReportParent(@RequestBody GenerateReportDto dto, @RequestHeader(value = "currentAcademicYear") String currentAcademicYear, @RequestHeader(value = "branchid") String branchId);

    ResponseEntity<ResultResponse> deleteMultiple(@RequestBody GenerateReportDto dto);

    ResponseEntity<ResultResponse> updateMarks(@RequestBody MarksUpdateDto dto, @RequestHeader(value = "currentAcademicYear") String currentAcademicYear, @RequestHeader(value = "branchid") String branchId);

    ResponseEntity<SubjectsExamsDto> getSubjectsExams(@RequestHeader(value = "branchid") String branchId);

    ResponseEntity<MarksDto> viewMarks(@RequestBody MarksViewDto dto, @RequestHeader(value = "branchid") String branchId);

    ResponseEntity<ResultResponse> addMarks(@RequestBody MarksUpdateDto dto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "currentAcademicYear") String currentAcademicYear, @RequestHeader(value = "userloginid") String userId);

    ResponseEntity<SearchStudentResponseDto> search(@RequestBody SearchStudentExamDto dto, @RequestHeader(value = "branchid") String branchId);

    ResponseEntity<ReportDto> searchForGraphicalReport(@RequestBody SearchStudentExamDto dto, @RequestHeader(value = "branchid") String branchId);

    ResponseEntity<ResultResponse> rankreport(@RequestHeader(value = "branchid") String branchId);

    ResponseEntity<SearchStudentResponseDto> searchForRank(@RequestBody SearchStudentExamDto dto, @RequestHeader(value = "branchid") String branchId);

    ResponseEntity<GenerateReportResponseDto> generateRankReport(@RequestBody GenerateReportDto dto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "currentAcademicYear") String currentAcademicYear, @RequestHeader(value = "userloginid") String userId);
}
