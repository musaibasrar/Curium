package org.ideoholic.curium.model.assessmentsubjectdetails.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.exceptions.CustomErrorMessage;
import org.ideoholic.curium.exceptions.CustomResponseException;
import org.ideoholic.curium.model.assessmentdetails.dto.AssessmentListResponseDto;
import org.ideoholic.curium.model.assessmentdetails.service.HolisticAssessmentService;
import org.ideoholic.curium.model.assessmentsubjectdetails.dto.AssessmentSubjectDto;
import org.ideoholic.curium.model.assessmentsubjectdetails.dto.AssessmentSubjectIdsDto;
import org.ideoholic.curium.model.assessmentsubjectdetails.dto.AssessmentSubjectsAssessmentsResponseDto;
import org.ideoholic.curium.model.assessmentsubjectdetails.dto.AssessmentSubjectsResponseDto;
import org.ideoholic.curium.model.assessmentsubjectdetails.service.AssessmentSubjectDetailsService;
import org.ideoholic.curium.model.std.service.StandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/assessmentSubjectDetailsProcess")
public class AssessmentSubjectDetailsApiActionImpl implements AssessmentSubjectDetailsApiAction {

    @Autowired
    private StandardService standardService;

    @Autowired
    private HolisticAssessmentService holisticAssessmentService;

    @Autowired
    private AssessmentSubjectDetailsService assessmentSubjectDetailsService;

    @PostMapping("/deleteMultipleSubjects")
    public ResponseEntity<AssessmentSubjectsResponseDto> deleteMultipleSubjects(@RequestBody AssessmentSubjectIdsDto dto,
            @RequestHeader(value = "branchId", required = false) String branchId) {

        ResultResponse result = assessmentSubjectDetailsService.deleteMultipleSubjectMaster(dto);
        if (result.isSuccess()) {
            return readListOfSubjectNames(branchId);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    @GetMapping("/readListOfSubjectNames")
    public ResponseEntity<AssessmentSubjectsResponseDto> readListOfSubjectNames(
            @RequestHeader(value = "branchId", required = false) String branchId) {

        AssessmentSubjectsResponseDto result = assessmentSubjectDetailsService.readListOfSubjectNames(branchId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/addAssessmentSubjectMaster")
    public ResponseEntity<AssessmentSubjectsResponseDto> addAssessmentSubjectMaster(@RequestBody AssessmentSubjectDto dto,
            @RequestHeader(value = "branchId", required = false) String branchId,
            @RequestHeader(value = "userLoginId", required = false) String userLoginId) {

        ResultResponse result = assessmentSubjectDetailsService.addAssessmentSubjectMaster(dto, branchId, userLoginId);
        if (result.isSuccess()) {
            return readListOfSubjectNames(branchId);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    @PostMapping("/deleteMultiple")
    public ResponseEntity<AssessmentSubjectsAssessmentsResponseDto> deleteMultiple(@RequestBody AssessmentSubjectIdsDto dto,
            @RequestHeader(value = "branchId", required = false) String branchId) {

        ResultResponse result = assessmentSubjectDetailsService.deleteMultiple(dto);
        if (result.isSuccess()) {
            return readListOfSubjects(branchId);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    @PostMapping("/addAssessmentSubject")
    public ResponseEntity<AssessmentSubjectsAssessmentsResponseDto> addAssessmentSubject(
            @RequestBody AssessmentSubjectDto dto,
            @RequestHeader(value = "branchId", required = false) String branchId,
            @RequestHeader(value = "userLoginId", required = false) String userLoginId) {

        ResultResponse result = assessmentSubjectDetailsService.addAssessmentSubject(dto, branchId, userLoginId);
        if (result.isSuccess()) {
            return readListOfSubjects(branchId);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    @GetMapping("/readListOfSubjects")
    public ResponseEntity<AssessmentSubjectsAssessmentsResponseDto> readListOfSubjects(
            @RequestHeader(value = "branchId", required = false) String branchId) {

        AssessmentSubjectsAssessmentsResponseDto result = new AssessmentSubjectsAssessmentsResponseDto();

        AssessmentSubjectsResponseDto responseDto = assessmentSubjectDetailsService
                .readListOfAssessmentSubjects(branchId);
        result.setSubjects(responseDto.getList());
        result.setSuccess(responseDto.isSuccess());

        responseDto = assessmentSubjectDetailsService.readListOfSubjectNames(branchId);
        result.setListSubjectNames(responseDto.getListSubjectNames());
        result.setSuccess(result.isSuccess() & responseDto.isSuccess());

        AssessmentListResponseDto assessments = holisticAssessmentService.readListOfAssessments(branchId);
        result.setAssessments(assessments.getAssessments());
        result.setSuccess(result.isSuccess() & assessments.isSuccess());

        ResultResponse classResponse = standardService.viewClasses(branchId);
        result.setClasssecList(classResponse.getResultList());
        result.setSuccess(result.isSuccess() & classResponse.isSuccess());

        return ResponseEntity.ok(result);
    }
}
