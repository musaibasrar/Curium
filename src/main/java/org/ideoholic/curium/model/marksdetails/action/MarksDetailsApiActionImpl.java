package org.ideoholic.curium.model.marksdetails.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.exceptions.CustomErrorMessage;
import org.ideoholic.curium.exceptions.CustomResponseException;
import org.ideoholic.curium.model.documents.dto.SearchStudentResponseDto;
import org.ideoholic.curium.model.marksdetails.dto.*;
import org.ideoholic.curium.model.marksdetails.service.MarksDetailsService;
import org.ideoholic.curium.model.std.service.StandardService;
import org.ideoholic.curium.model.student.dto.StudentIdsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/marksDetailsProcess")
public class MarksDetailsApiActionImpl implements MarksDetailsApiAction{

    @Autowired
    private MarksDetailsService marksDetailsService;
    @Autowired
    private StandardService standardService;

    @GetMapping("/marksEntry")
    public ResponseEntity<ResultResponse> marksEntry(@RequestHeader(value = "branchid") String branchId) {
        ResultResponse result = standardService.viewClasses(branchId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/downloadReportCard")
    public ResponseEntity<ResultResponse> downloadReportCard() {
        ResultResponse result = marksDetailsService.downloadReportCard();
        return ResponseEntity.ok(result);

    }

    //@GetMapping("/progressReport")
    @RequestMapping(value = "/progressReport", method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseEntity<ResultResponse> progressreport(@RequestHeader(value = "branchid") String branchId) {
        ResultResponse result = standardService.viewClasses(branchId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/getStudentGraph")
    public ResponseEntity<StudentGraphResponseDto> getStudentGraph(@RequestBody StudentGraphDto dto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "currentAcademicYear") String currentAcademicYear) {
        StudentGraphResponseDto result = marksDetailsService.getStudentGraph(dto, branchId, currentAcademicYear);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getGraphicalReportData")
    public ResponseEntity<GraphicalReportDto> getGraphicalReportData(@RequestHeader(value = "branchid") String branchId) {
        GraphicalReportDto reportDto = new GraphicalReportDto();

        ResultResponse studentResult = marksDetailsService.getStudentList(branchId);
        reportDto.setStudentList(studentResult.getResultList());

        ResultResponse classesResult = standardService.viewClasses(branchId);
        reportDto.setClasssecList(classesResult.getResultList());

        return ResponseEntity.ok(reportDto);
    }

    @PostMapping("/searchForReport")
    public ResponseEntity<SearchStudentResponseDto> searchForReport(@RequestBody SearchStudentExamDto dto, @RequestHeader(value = "branchid") String branchId) {
        SearchStudentResponseDto result = marksDetailsService.Search(dto, branchId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/generateReport")
    public ResponseEntity<GenerateReportResponseDto> generateReport(@RequestBody GenerateReportDto dto, @RequestHeader(value = "currentAcademicYear") String currentAcademicYear, @RequestHeader(value = "branchid") String branchId) {
        GenerateReportResponseDto result = marksDetailsService.generateReport(dto, currentAcademicYear, branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        } else {
            throw new CustomResponseException(CustomErrorMessage.ERROR);
        }
    }

    @GetMapping("/generateReportParent")
    public ResponseEntity<GenerateReportResponseDto> generateReportParent(@RequestBody GenerateReportDto dto, @RequestHeader(value = "currentAcademicYear") String currentAcademicYear, @RequestHeader(value = "branchid") String branchId) {
        GenerateReportResponseDto result =marksDetailsService.generateReportParent(dto, currentAcademicYear, branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        } else {
            throw new CustomResponseException(CustomErrorMessage.ERROR);
        }
    }

    @PostMapping("/deleteMultiple")
    public ResponseEntity<ResultResponse> deleteMultiple(@RequestBody GenerateReportDto dto) {
        ResultResponse result = marksDetailsService.deleteMultiple(dto);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        } else {
            throw new CustomResponseException(CustomErrorMessage.NOTSAVED);
        }
    }

    @PostMapping("/updateMarks")
    public ResponseEntity<ResultResponse> updateMarks(@RequestBody MarksUpdateDto dto, @RequestHeader(value = "currentAcademicYear") String currentAcademicYear, @RequestHeader(value = "branchid") String branchId) {
        ResultResponse result = marksDetailsService.updateMarks(dto, currentAcademicYear, branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        } else {
            throw new CustomResponseException(CustomErrorMessage.ERROR);
        }
    }

    @GetMapping("/getSubjectsExams")
    public ResponseEntity<SubjectsExamsDto> getSubjectsExams(@RequestHeader(value = "branchid") String branchId) {
        SubjectsExamsDto subjectsExamsDto = new SubjectsExamsDto();

        SearchStudentResponseDto subjectExamsResult = marksDetailsService.getSubjectExams(branchId);
        subjectsExamsDto.setSubjectList(subjectExamsResult.getSubjectList());
        subjectsExamsDto.setExamList(subjectExamsResult.getExamsList());

        ResultResponse classesResult = standardService.viewClasses(branchId);
        subjectsExamsDto.setClasssecList(classesResult.getResultList());

        return ResponseEntity.ok(subjectsExamsDto);
    }

    @PostMapping("/viewMarks")
    public ResponseEntity<MarksDto> viewMarks(@RequestBody MarksViewDto dto, @RequestHeader(value = "branchid") String branchId) {
        MarksDto marksDto = new MarksDto();

        MarksResponseDto marksResult = marksDetailsService.viewMarks(dto, branchId);
        marksDto.setNewStudentList(marksResult.getNewStudentList());
        marksDto.setNewMarksDetails(marksResult.getNewMarksDetails());
        marksDto.setSubjectSelected(marksResult.getSubjectSelected());
        marksDto.setExamSelected(marksResult.getExamSelected());
        marksDto.setSubject(marksResult.getSubject());
        marksDto.setExam(marksResult.getExam());

        if (marksResult.isSuccess()) {
            SearchStudentResponseDto subjectExamsResult = marksDetailsService.getSubjectExams(branchId);
            marksDto.setSubjectList(subjectExamsResult.getSubjectList());
            marksDto.setExamList(subjectExamsResult.getExamsList());

            return ResponseEntity.ok(marksDto);
        } else {
            throw new CustomResponseException(CustomErrorMessage.ERROR);
        }
    }

    @PostMapping("/addMarks")
    public ResponseEntity<ResultResponse> addMarks(@RequestBody MarksUpdateDto dto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "currentAcademicYear") String currentAcademicYear, @RequestHeader(value = "userloginid") String userId) {
        ResultResponse result = marksDetailsService.addMarks(dto, branchId, currentAcademicYear, userId);
        if ("true".equals(result.getMessage())) {
            result.setSuccess(true);
            return ResponseEntity.ok(result);
        } else if (result.getMessage() != null && result.getMessage().equals("Duplicate") ) {
            throw new CustomResponseException(CustomErrorMessage.ERRORADDINGMARKS);
        } else {
            throw new CustomResponseException(CustomErrorMessage.ERROR);
        }

    }

    @PostMapping("/search")
    public ResponseEntity<SearchStudentResponseDto> search(@RequestBody SearchStudentExamDto dto, @RequestHeader(value = "branchid") String branchId) {
        SearchStudentResponseDto result = marksDetailsService.Search(dto, branchId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/searchForGraphicalReport")
    public ResponseEntity<ReportDto> searchForGraphicalReport(@RequestBody SearchStudentExamDto dto, @RequestHeader(value = "branchid") String branchId) {
        ReportDto reportDto = new ReportDto();

        SearchStudentResponseDto searchResult = marksDetailsService.Search(dto, branchId);
        reportDto.setSearchStudentList(searchResult.getSearchStudentList());
        reportDto.setSubjectList(searchResult.getSubjectList());
        reportDto.setExamList(searchResult.getExamsList());

        ResultResponse studentListResult = marksDetailsService.getStudentList(branchId);
        reportDto.setStudentList(studentListResult.getResultList());

        return ResponseEntity.ok(reportDto);
    }

    @GetMapping("/rankReport")
    public ResponseEntity<ResultResponse> rankreport(@RequestHeader(value = "branchid") String branchId) {
        ResultResponse result = standardService.viewClasses(branchId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/searchForRank")
    public ResponseEntity<SearchStudentResponseDto> searchForRank(@RequestBody SearchStudentExamDto dto, @RequestHeader(value = "branchid") String branchId) {
        SearchStudentResponseDto result = marksDetailsService.rankSearch(dto, branchId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/generateRankReport")
    public ResponseEntity<GenerateReportResponseDto> generateRankReport(@RequestBody GenerateReportDto dto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "currentAcademicYear") String currentAcademicYear, @RequestHeader(value = "userloginid") String userId) {
        GenerateReportResponseDto result = marksDetailsService.generateRankReport(dto, branchId, currentAcademicYear, userId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        } else {
            throw new CustomResponseException(CustomErrorMessage.ERROR);
        }
    }
}
