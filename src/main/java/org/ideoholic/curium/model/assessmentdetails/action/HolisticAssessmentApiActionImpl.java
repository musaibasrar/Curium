package org.ideoholic.curium.model.assessmentdetails.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.exceptions.CustomErrorMessage;
import org.ideoholic.curium.exceptions.CustomResponseException;
import org.ideoholic.curium.model.academicyear.dto.Currentacademicyear;
import org.ideoholic.curium.model.academicyear.service.YearService;
import org.ideoholic.curium.model.assessmentdetails.dto.AddAssessmentDto;
import org.ideoholic.curium.model.assessmentdetails.dto.AddAssessmentScheduleDto;
import org.ideoholic.curium.model.assessmentdetails.dto.AssessmentIdsDto;
import org.ideoholic.curium.model.assessmentdetails.dto.AssessmentListResponseDto;
import org.ideoholic.curium.model.assessmentdetails.dto.AssessmentScheduleDto;
import org.ideoholic.curium.model.assessmentdetails.dto.AssessmentScheduleResponseDto;
import org.ideoholic.curium.model.assessmentdetails.service.HolisticAssessmentService;
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
@RequestMapping("/api/v1/holisticAssessmentDetailsProcess")
public class HolisticAssessmentApiActionImpl implements HolisticAssessmentApiAction {

    @Autowired
    private StandardService standardService;

    @Autowired
    private YearService yearService;

    @Autowired
    private HolisticAssessmentService holisticAssessmentService;

    @Autowired
    private AssessmentSubjectDetailsService assessmentSubjectDetailsService;

    @PostMapping("/searchAssessmentScheduleDetails")
    public ResponseEntity<AssessmentScheduleResponseDto> searchAssessmentScheduleDetails(
            @RequestBody AssessmentScheduleDto assessmentScheduleDto,
            @RequestHeader(value = "branchId", required = false) String branchId) {

        AssessmentScheduleResponseDto result = holisticAssessmentService.getAssessmentScheduleDetails(assessmentScheduleDto,
                branchId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/deleteAssessmentSchedule")
    public ResponseEntity<AssessmentScheduleResponseDto> deleteAssessmentSchedule(@RequestBody AssessmentIdsDto dto,
            @RequestHeader(value = "branchId", required = false) String branchId) {

        ResultResponse result = holisticAssessmentService.deleteAssessmentSchedule(dto);
        if (result.isSuccess()) {
            return assessmentSchedule(branchId);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    @PostMapping("/addAssessmentSchedule")
    public ResponseEntity<AssessmentScheduleResponseDto> addSchedule(@RequestBody AddAssessmentScheduleDto dto,
            @RequestHeader(value = "branchId", required = false) String branchId) {

        ResultResponse result = holisticAssessmentService.addSchedule(dto, branchId);
        if (result.isSuccess()) {
            return assessmentSchedule(branchId);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    @GetMapping("/assessmentSchedule")
    public ResponseEntity<AssessmentScheduleResponseDto> assessmentSchedule(
            @RequestHeader(value = "branchId", required = false) String branchId) {
        AssessmentScheduleResponseDto result = new AssessmentScheduleResponseDto();

        AssessmentListResponseDto assessmentListResponseDto = holisticAssessmentService
                .readListOfAssessments(branchId);
        if (!assessmentListResponseDto.isSuccess()) {
            throw new CustomResponseException(CustomErrorMessage.ERROR);
        }
        result.setAssessments(assessmentListResponseDto.getAssessments());

        ResultResponse classResponse = standardService.viewClasses(branchId);
        if (!classResponse.isSuccess()) {
            throw new CustomResponseException(CustomErrorMessage.ERROR);
        }
        result.setMessage(classResponse.getMessage());
        result.setResultMap(classResponse.getResultMap());
        result.setResultValue(classResponse.getResultValue());
        result.setResultList(classResponse.getResultList());

        AssessmentSubjectsResponseDto subjectsResponseDto = assessmentSubjectDetailsService
                .readListOfSubjectNames(branchId);
        if (!subjectsResponseDto.isSuccess()) {
            throw new CustomResponseException(CustomErrorMessage.ERROR);
        }
        result.setListSubjectName(subjectsResponseDto.getListSubjectNames());

        Currentacademicyear currentacademicyear = yearService.getYear(Integer.parseInt(branchId));
        if (currentacademicyear == null) {
            throw new CustomResponseException(CustomErrorMessage.ERROR);
        }
        result.setCurrentacademicyear(currentacademicyear.getCurrentacademicyear());

        AssessmentScheduleResponseDto assessmentScheduleResponseDto = holisticAssessmentService
                .getAssessmentSchedule(branchId);
        if (!assessmentScheduleResponseDto.isSuccess()) {
            throw new CustomResponseException(CustomErrorMessage.ERROR);
        }

        result.setAssessments(assessmentScheduleResponseDto.getAssessments());
        result.setList(assessmentScheduleResponseDto.getList());
        result.setSelectedclass(assessmentScheduleResponseDto.getSelectedclass());
        result.setSelectedassessment(assessmentScheduleResponseDto.getSelectedassessment());
        result.setSelectedstudentname(assessmentScheduleResponseDto.getSelectedstudentname());
        result.setSelectedclassandsec(assessmentScheduleResponseDto.getSelectedclassandsec());
        result.setSelectedadmissionno(assessmentScheduleResponseDto.getSelectedadmissionno());
        result.setAssessmentschedules(assessmentScheduleResponseDto.getAssessmentschedules());

        return ResponseEntity.ok(result);
    }

    @PostMapping("/deleteMultiple")
    public ResponseEntity<AssessmentListResponseDto> deleteMultiple(@RequestBody AssessmentIdsDto dto,
            @RequestHeader(value = "branchId", required = false) String branchId) {

        ResultResponse result = holisticAssessmentService.deleteMultiple(dto);
        if (result.isSuccess()) {
            return readListOfAssessments(branchId);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    @GetMapping("/readListOfAssessments")
    public ResponseEntity<AssessmentListResponseDto> readListOfAssessments(
            @RequestHeader(value = "branchId", required = false) String branchId) {

        AssessmentListResponseDto result = holisticAssessmentService.readListOfAssessments(branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    @PostMapping("/addAssessment")
    public ResponseEntity<AssessmentListResponseDto> addAssessment(@RequestBody AddAssessmentDto dto,
            @RequestHeader(value = "branchId", required = false) String branchId) {

        ResultResponse result = holisticAssessmentService.addAssessment(dto, branchId);
        if (result.isSuccess()) {
            return readListOfAssessments(branchId);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }
}
