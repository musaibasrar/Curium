package org.ideoholic.curium.model.ratingdetails.action;

import org.ideoholic.curium.dto.ResultResponse;
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
import org.springframework.http.ResponseEntity;

public interface RatingDetailsApiAction {

    ResponseEntity<ResultResponse> ratingEntry(String branchId);

    ResponseEntity<ResultResponse> progressreport(String branchId);

    ResponseEntity<StudentRatingGraphResponseDto> getStudentGraph(StudentRatingGraphDto dto, String branchId,
            String currentAcademicYear);

    ResponseEntity<GraphicalAssessmentReportDto> getGraphicalReportData(String branchId);

    ResponseEntity<SearchStudentResponseDto> searchForReport(SearchStudentAssessmentDto dto, String branchId);

    ResponseEntity<GenerateAssessmentReportResponseDto> generateReport(GenerateAssessmentReportDto dto,
            String currentAcademicYear, String branchId);

    ResponseEntity<ResultResponse> deleteMultiple(RatingUpdateDto dto);

    ResponseEntity<ResultResponse> updateRatings(RatingUpdateDto dto, String currentAcademicYear, String branchId);

    ResponseEntity<AssessmentSubjectsDto> getSubjectsAssessments(String branchId);

    ResponseEntity<RatingDto> viewRatings(RatingViewDto dto, String branchId);

    ResponseEntity<ResultResponse> addRatings(RatingUpdateDto dto, String branchId, String currentAcademicYear,
            String userLoginId);

    ResponseEntity<SearchStudentResponseDto> search(SearchStudentAssessmentDto dto, String branchId);

    ResponseEntity<AssessmentReportDto> searchForGraphicalReport(SearchStudentAssessmentDto dto, String branchId);

    ResponseEntity<ResultResponse> rankreport(String branchId);

    ResponseEntity<SearchStudentResponseDto> searchForRank(SearchStudentAssessmentDto dto, String branchId);

    ResponseEntity<GenerateAssessmentReportResponseDto> generateRankReport(GenerateAssessmentReportDto dto,
            String branchId, String currentAcademicYear, String userLoginId);

    ResponseEntity<ResultResponse> finalAssessmentReport(String branchId);

    ResponseEntity<SearchStudentResponseDto> searchForFinalReport(SearchStudentAssessmentDto dto, String branchId);

    ResponseEntity<GenerateAssessmentReportResponseDto> generateFinalAssessmentReport(GenerateAssessmentReportDto dto,
            String currentAcademicYear, String branchId);
}
