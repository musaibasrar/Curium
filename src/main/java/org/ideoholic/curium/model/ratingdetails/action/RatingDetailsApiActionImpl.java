package org.ideoholic.curium.model.ratingdetails.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.exceptions.CustomErrorMessage;
import org.ideoholic.curium.exceptions.CustomResponseException;
import org.ideoholic.curium.model.documents.dto.SearchStudentResponseDto;
import org.ideoholic.curium.model.ratingdetails.dto.AssessmentReportDto;
import org.ideoholic.curium.model.ratingdetails.dto.AssessmentSubjectsDto;
import org.ideoholic.curium.model.ratingdetails.dto.GenerateAssessmentReportDto;
import org.ideoholic.curium.model.ratingdetails.dto.GenerateAssessmentReportResponseDto;
import org.ideoholic.curium.model.ratingdetails.dto.GraphicalAssessmentReportDto;
import org.ideoholic.curium.model.ratingdetails.dto.RatingDto;
import org.ideoholic.curium.model.ratingdetails.dto.RatingUpdateDto;
import org.ideoholic.curium.model.ratingdetails.dto.RatingViewDto;
import org.ideoholic.curium.model.ratingdetails.dto.SearchStudentAssessmentDto;
import org.ideoholic.curium.model.ratingdetails.dto.StudentRatingGraphDto;
import org.ideoholic.curium.model.ratingdetails.dto.StudentRatingGraphResponseDto;
import org.ideoholic.curium.model.ratingdetails.service.RatingDetailsService;
import org.ideoholic.curium.model.std.service.StandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ratingDetailsProcess")
public class RatingDetailsApiActionImpl implements RatingDetailsApiAction {

    @Autowired
    private RatingDetailsService ratingDetailsService;

    @Autowired
    private StandardService standardService;

    @GetMapping("/ratingEntry")
    public ResponseEntity<ResultResponse> ratingEntry(
            @RequestHeader(value = "branchId", required = false) String branchId) {
        ResultResponse result = standardService.viewClasses(branchId);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/progressReport", method = { RequestMethod.GET,
            RequestMethod.POST })
    public ResponseEntity<ResultResponse> progressreport(
            @RequestHeader(value = "branchId", required = false) String branchId) {
        ResultResponse result = standardService.viewClasses(branchId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/getStudentGraph")
    public ResponseEntity<StudentRatingGraphResponseDto> getStudentGraph(@RequestBody StudentRatingGraphDto dto,
            @RequestHeader(value = "branchId", required = false) String branchId,
            @RequestHeader(value = "currentAcademicYear", required = false) String currentAcademicYear) {
        StudentRatingGraphResponseDto result = ratingDetailsService.getStudentGraph(dto, branchId, currentAcademicYear);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getGraphicalReportData")
    public ResponseEntity<GraphicalAssessmentReportDto> getGraphicalReportData(
            @RequestHeader(value = "branchId", required = false) String branchId) {
        GraphicalAssessmentReportDto reportDto = new GraphicalAssessmentReportDto();
        reportDto.setStudentList(ratingDetailsService.getStudentList(branchId).getResultList());
        reportDto.setClasssecList(standardService.viewClasses(branchId).getResultList());
        return ResponseEntity.ok(reportDto);
    }

    @PostMapping("/searchForReport")
    public ResponseEntity<SearchStudentResponseDto> searchForReport(@RequestBody SearchStudentAssessmentDto dto,
            @RequestHeader(value = "branchId", required = false) String branchId) {
        SearchStudentResponseDto result = ratingDetailsService.Search(dto, branchId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/generateReport")
    public ResponseEntity<GenerateAssessmentReportResponseDto> generateReport(@RequestBody GenerateAssessmentReportDto dto,
            @RequestHeader(value = "currentAcademicYear", required = false) String currentAcademicYear,
            @RequestHeader(value = "branchId", required = false) String branchId) {
        GenerateAssessmentReportResponseDto result = ratingDetailsService.generateReport(dto, currentAcademicYear,
                branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    @PostMapping("/deleteMultiple")
    public ResponseEntity<ResultResponse> deleteMultiple(@RequestBody RatingUpdateDto dto) {
        ResultResponse result = ratingDetailsService.deleteMultiple(dto);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.NOTSAVED);
    }

    @PostMapping("/updateRatings")
    public ResponseEntity<ResultResponse> updateRatings(@RequestBody RatingUpdateDto dto,
            @RequestHeader(value = "currentAcademicYear", required = false) String currentAcademicYear,
            @RequestHeader(value = "branchId", required = false) String branchId) {
        ResultResponse result = ratingDetailsService.updateRatings(dto, currentAcademicYear, branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    @GetMapping("/getSubjectsAssessments")
    public ResponseEntity<AssessmentSubjectsDto> getSubjectsAssessments(
            @RequestHeader(value = "branchId", required = false) String branchId) {
        AssessmentSubjectsDto result = ratingDetailsService.getSubjectAssessments(branchId);
        result.setClasssecList(standardService.viewClasses(branchId).getResultList());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/viewRatings")
    public ResponseEntity<RatingDto> viewRatings(@RequestBody RatingViewDto dto,
            @RequestHeader(value = "branchId", required = false) String branchId) {
        RatingDto result = ratingDetailsService.viewRatings(dto, branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    @PostMapping("/addRatings")
    public ResponseEntity<ResultResponse> addRatings(@RequestBody RatingUpdateDto dto,
            @RequestHeader(value = "branchId", required = false) String branchId,
            @RequestHeader(value = "currentAcademicYear", required = false) String currentAcademicYear,
            @RequestHeader(value = "userLoginId", required = false) String userLoginId) {

        ResultResponse result = ratingDetailsService.addRatings(dto, branchId, currentAcademicYear, userLoginId);
        if ("true".equals(result.getMessage())) {
            result.setSuccess(true);
            return ResponseEntity.ok(result);
        } else if ("Duplicate".equals(result.getMessage())) {
            throw new CustomResponseException(CustomErrorMessage.ERRORADDINGMARKS);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    @PostMapping("/search")
    public ResponseEntity<SearchStudentResponseDto> search(@RequestBody SearchStudentAssessmentDto dto,
            @RequestHeader(value = "branchId", required = false) String branchId) {
        SearchStudentResponseDto result = ratingDetailsService.Search(dto, branchId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/searchForGraphicalReport")
    public ResponseEntity<AssessmentReportDto> searchForGraphicalReport(@RequestBody SearchStudentAssessmentDto dto,
            @RequestHeader(value = "branchId", required = false) String branchId) {
        AssessmentReportDto reportDto = new AssessmentReportDto();
        SearchStudentResponseDto searchResult = ratingDetailsService.Search(dto, branchId);
        reportDto.setSearchStudentList(searchResult.getSearchStudentList());
        AssessmentSubjectsDto subjectsDto = ratingDetailsService.getSubjectAssessments(branchId);
        reportDto.setAssessmentSubjectList(subjectsDto.getAssessmentSubjects());
        reportDto.setAssessmentList(subjectsDto.getAssessments());
        reportDto.setStudentList(ratingDetailsService.getStudentList(branchId).getResultList());
        return ResponseEntity.ok(reportDto);
    }

    @GetMapping("/rankReport")
    public ResponseEntity<ResultResponse> rankreport(
            @RequestHeader(value = "branchId", required = false) String branchId) {
        ResultResponse result = standardService.viewClasses(branchId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/searchForRank")
    public ResponseEntity<SearchStudentResponseDto> searchForRank(@RequestBody SearchStudentAssessmentDto dto,
            @RequestHeader(value = "branchId", required = false) String branchId) {
        SearchStudentResponseDto result = ratingDetailsService.rankSearch(dto, branchId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/generateRankReport")
    public ResponseEntity<GenerateAssessmentReportResponseDto> generateRankReport(@RequestBody GenerateAssessmentReportDto dto,
            @RequestHeader(value = "branchId", required = false) String branchId,
            @RequestHeader(value = "currentAcademicYear", required = false) String currentAcademicYear,
            @RequestHeader(value = "userLoginId", required = false) String userLoginId) {

        GenerateAssessmentReportResponseDto result = ratingDetailsService.generateRankReport(dto,
                branchId, currentAcademicYear, userLoginId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    @GetMapping("/finalAssessmentReport")
    public ResponseEntity<ResultResponse> finalAssessmentReport(
            @RequestHeader(value = "branchId", required = false) String branchId) {
        ResultResponse result = standardService.viewClasses(branchId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/searchForFinalReport")
    public ResponseEntity<SearchStudentResponseDto> searchForFinalReport(@RequestBody SearchStudentAssessmentDto dto,
            @RequestHeader(value = "branchId", required = false) String branchId) {
        SearchStudentResponseDto result = ratingDetailsService.Search(dto, branchId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/generateFinalAssessmentReport")
    public ResponseEntity<GenerateAssessmentReportResponseDto> generateFinalAssessmentReport(
            @RequestBody GenerateAssessmentReportDto dto,
            @RequestHeader(value = "currentAcademicYear", required = false) String currentAcademicYear,
            @RequestHeader(value = "branchId", required = false) String branchId) {
        GenerateAssessmentReportResponseDto result = ratingDetailsService.generateReport(dto, currentAcademicYear,
                branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }
}
