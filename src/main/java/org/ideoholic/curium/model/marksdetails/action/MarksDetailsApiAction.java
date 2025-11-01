package org.ideoholic.curium.model.marksdetails.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.documents.dto.SearchStudentResponseDto;
import org.ideoholic.curium.model.marksdetails.dto.GenerateReportDto;
import org.ideoholic.curium.model.marksdetails.dto.GenerateReportResponseDto;
import org.ideoholic.curium.model.marksdetails.dto.GraphicalReportDto;
import org.ideoholic.curium.model.marksdetails.dto.MarksDto;
import org.ideoholic.curium.model.marksdetails.dto.MarksUpdateDto;
import org.ideoholic.curium.model.marksdetails.dto.MarksViewDto;
import org.ideoholic.curium.model.marksdetails.dto.ReportDto;
import org.ideoholic.curium.model.marksdetails.dto.SearchStudentExamDto;
import org.ideoholic.curium.model.marksdetails.dto.StudentGraphDto;
import org.ideoholic.curium.model.marksdetails.dto.StudentGraphResponseDto;
import org.ideoholic.curium.model.marksdetails.dto.SubjectsExamsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

public interface MarksDetailsApiAction {
    ResponseEntity<ResultResponse> marksEntry(@RequestHeader(value = "branchid") String branchId);

    ResponseEntity<ResultResponse> downloadReportCard();

    ResponseEntity<ResultResponse> progressreport(@RequestHeader(value = "branchid") String branchId);

    ResponseEntity<StudentGraphResponseDto> getStudentGraph(@RequestBody StudentGraphDto dto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "currentAcademicYear") String currentAcademicYear);

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

   	ResponseEntity<ResultResponse> finalExamReport(@RequestHeader(value = "branchid") String branchId);
   	
   	ResponseEntity<SearchStudentResponseDto> searchForFinalReport(@RequestBody SearchStudentExamDto dto, @RequestHeader(value = "branchid") String branchId);
   	
   	ResponseEntity<GenerateReportResponseDto> generatefinalexamReport(@RequestBody GenerateReportDto dto, @RequestHeader(value = "currentAcademicYear") String currentAcademicYear,@RequestHeader(value = "branchid") String branchId);
   	
}
