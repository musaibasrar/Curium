package org.ideoholic.curium.model.assessmentsubjectdetails.action;

import org.ideoholic.curium.model.assessmentsubjectdetails.dto.AssessmentSubjectDto;
import org.ideoholic.curium.model.assessmentsubjectdetails.dto.AssessmentSubjectIdsDto;
import org.ideoholic.curium.model.assessmentsubjectdetails.dto.AssessmentSubjectsAssessmentsResponseDto;
import org.ideoholic.curium.model.assessmentsubjectdetails.dto.AssessmentSubjectsResponseDto;
import org.springframework.http.ResponseEntity;

public interface AssessmentSubjectDetailsApiAction {

    ResponseEntity<AssessmentSubjectsResponseDto> deleteMultipleSubjects(AssessmentSubjectIdsDto dto, String branchId);

    ResponseEntity<AssessmentSubjectsResponseDto> readListOfSubjectNames(String branchId);

    ResponseEntity<AssessmentSubjectsResponseDto> addAssessmentSubjectMaster(AssessmentSubjectDto dto, String branchId,
            String userLoginId);

    ResponseEntity<AssessmentSubjectsAssessmentsResponseDto> deleteMultiple(AssessmentSubjectIdsDto dto,
            String branchId);

    ResponseEntity<AssessmentSubjectsAssessmentsResponseDto> addAssessmentSubject(AssessmentSubjectDto dto,
            String branchId, String userLoginId);

    ResponseEntity<AssessmentSubjectsAssessmentsResponseDto> readListOfSubjects(String branchId);
}
