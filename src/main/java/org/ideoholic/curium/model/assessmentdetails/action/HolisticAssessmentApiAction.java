package org.ideoholic.curium.model.assessmentdetails.action;

import org.ideoholic.curium.model.assessmentdetails.dto.AddAssessmentDto;
import org.ideoholic.curium.model.assessmentdetails.dto.AddAssessmentScheduleDto;
import org.ideoholic.curium.model.assessmentdetails.dto.AssessmentIdsDto;
import org.ideoholic.curium.model.assessmentdetails.dto.AssessmentListResponseDto;
import org.ideoholic.curium.model.assessmentdetails.dto.AssessmentScheduleDto;
import org.ideoholic.curium.model.assessmentdetails.dto.AssessmentScheduleResponseDto;
import org.springframework.http.ResponseEntity;

public interface HolisticAssessmentApiAction {

    ResponseEntity<AssessmentScheduleResponseDto> searchAssessmentScheduleDetails(AssessmentScheduleDto assessmentScheduleDto,
            String branchId);

    ResponseEntity<AssessmentScheduleResponseDto> deleteAssessmentSchedule(AssessmentIdsDto dto, String branchId);

    ResponseEntity<AssessmentScheduleResponseDto> addSchedule(AddAssessmentScheduleDto dto, String branchId);

    ResponseEntity<AssessmentScheduleResponseDto> assessmentSchedule(String branchId);

    ResponseEntity<AssessmentListResponseDto> deleteMultiple(AssessmentIdsDto dto, String branchId);

    ResponseEntity<AssessmentListResponseDto> readListOfAssessments(String branchId);

    ResponseEntity<AssessmentListResponseDto> addAssessment(AddAssessmentDto dto, String branchId);
}
