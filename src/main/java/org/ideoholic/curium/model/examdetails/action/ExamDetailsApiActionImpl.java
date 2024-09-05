package org.ideoholic.curium.model.examdetails.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.exceptions.CustomErrorMessage;
import org.ideoholic.curium.exceptions.CustomResponseException;
import org.ideoholic.curium.model.academicyear.dto.Currentacademicyear;
import org.ideoholic.curium.model.academicyear.service.YearService;
import org.ideoholic.curium.model.examdetails.dto.*;
import org.ideoholic.curium.model.examdetails.service.ExamDetailsService;
import org.ideoholic.curium.model.std.service.StandardService;
import org.ideoholic.curium.model.subjectdetails.dto.SubjectsResponseDto;
import org.ideoholic.curium.model.subjectdetails.service.SubjectDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/examDetailsProcess")
public class ExamDetailsApiActionImpl implements ExamDetailsApiAction {
    @Autowired
    private StandardService standardService;
    @Autowired
    private YearService yearService;
    @Autowired
    private ExamDetailsService examDetailsService;
    @Autowired
    private SubjectDetailsService subjectDetailsService;

    private String error = "error";


    @PostMapping("/printPreviewHallTicket")
    public ResponseEntity<HallTicketResponseDto> printPreviewHallTicket(@RequestBody PrintPreviewHallTicketDto dto, @RequestHeader(value = "branchId") String branchId) {
        HallTicketResponseDto result = examDetailsService.printPreviewHallTicket(dto, branchId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/searchHallTicketDetails")
    public ResponseEntity<ExamScheduleResponseDto> searchHallTicketDetails(@RequestBody ExamScheduleDto examScheduleDto, @RequestHeader(value = "branchId") String branchId) {

        ExamScheduleResponseDto examScheduleResponseDto = examDetailsService.getExamScheduleDetails(examScheduleDto, branchId);
        ExamsListResponseDto examsListResponseDto = examDetailsService.readListOfExams(branchId);
        subjectDetailsService.readListOfSubjects(branchId);
        return ResponseEntity.ok(examScheduleResponseDto);
    }

    @GetMapping("/generateHallTicket")
    public ResponseEntity<GenerateHallTicketResponseDto> generateHallTicket(@RequestHeader(value = "branchid") String branchId) {
        GenerateHallTicketResponseDto result = new GenerateHallTicketResponseDto();

        ExamsListResponseDto examsListResponseDto = examDetailsService.readListOfExams(branchId);
        if (!examsListResponseDto.isSuccess()) {
            throw new CustomResponseException(CustomErrorMessage.ERROR);
        }
        result.setExams(examsListResponseDto.getExams());
        ResultResponse classResponse = standardService.viewClasses(branchId);
        if (!classResponse.isSuccess()) {
            throw new CustomResponseException(CustomErrorMessage.ERROR);
        }
        result.setMessage(classResponse.getMessage());
        result.setResultMap(classResponse.getResultMap());
        result.setResultValue(classResponse.getResultValue());
        result.setResultList(classResponse.getResultList());

        SubjectsResponseDto subjectsResponseDto = subjectDetailsService.readListOfSubjectNames(branchId);
        if (!subjectsResponseDto.isSuccess()) {
            throw new CustomResponseException(CustomErrorMessage.ERROR);
        }
        result.setResultList(subjectsResponseDto.getList());
        Currentacademicyear currentacademicyear = yearService.getYear();

        if (currentacademicyear == null) {
            throw new CustomResponseException(CustomErrorMessage.ERROR);
        }
        result.setCurrentacademicyear(currentacademicyear.getCurrentacademicyear());
        ExamScheduleResponseDto examScheduleResponseDto = examDetailsService.getExamSchedule(branchId);
        if (!examScheduleResponseDto.isSuccess()) {
            throw new CustomResponseException(CustomErrorMessage.ERROR);
        }
        result.setExams(examScheduleResponseDto.getExams());
        result.setList(examScheduleResponseDto.getList());
        result.setSelectedclass(examScheduleResponseDto.getSelectedclass());
        result.setSelectedexam(examScheduleResponseDto.getSelectedexam());
        result.setSelectedstudentname(examScheduleResponseDto.getSelectedstudentname());
        result.setSelectedclassandsec(examScheduleResponseDto.getSelectedclassandsec());
        result.setSelectedadmissionno(examScheduleResponseDto.getSelectedadmissionno());
        result.setExamschedules(examScheduleResponseDto.getExamschedules());
        result.setMessage(examScheduleResponseDto.getMessage());
        result.setResultMap(examScheduleResponseDto.getResultMap());
        result.setResultValue(examScheduleResponseDto.getResultValue());
        result.setResultList(examScheduleResponseDto.getResultList());

        return ResponseEntity.ok(result);
    }

    @PostMapping("/deleteExamSchedule")
    public ResponseEntity<ExamScheduleResponseDto> deleteExamSchedule(@RequestBody ExamIdsDto dto, @RequestHeader(value = "branchid") String branchId) {
        ExamScheduleResponseDto examScheduleResponseDto = new ExamScheduleResponseDto();

        ResultResponse result = examDetailsService.deleteExamSchedule(dto);
        if (result.isSuccess()) {
            return examSchedule(branchId);
        } else {
            throw new CustomResponseException(CustomErrorMessage.ERROR);
        }
    }

    @PostMapping("addSchedule")
    public ResponseEntity<ExamScheduleResponseDto> addSchedule(@RequestBody AddScheduleDto dto, @RequestHeader(value = "branchid") String branchId) {
        ExamScheduleResponseDto examScheduleResponseDto = new ExamScheduleResponseDto();

        ResultResponse result = examDetailsService.addSchedule(dto, branchId);
        if (result.isSuccess()) {
            return examSchedule(branchId);
        } else {
            throw new CustomResponseException(CustomErrorMessage.ERROR);
        }


    }


    @GetMapping("/examSchedule")
    public ResponseEntity<ExamScheduleResponseDto> examSchedule(@RequestHeader(value = "branchid") String branchId) {
        ExamScheduleResponseDto result = new ExamScheduleResponseDto();


        ExamsListResponseDto examsListResponseDto = examDetailsService.readListOfExams(branchId);

        if (!examsListResponseDto.isSuccess()) {

            throw new CustomResponseException(CustomErrorMessage.ERROR);
        }
        result.setExams(examsListResponseDto.getExams());
        ResultResponse classResponse = standardService.viewClasses(branchId);

        if (!classResponse.isSuccess()) {
            throw new CustomResponseException(CustomErrorMessage.ERROR);
        }
        result.setMessage(classResponse.getMessage());
        result.setResultMap(classResponse.getResultMap());
        result.setResultValue(classResponse.getResultValue());
        result.setResultList(classResponse.getResultList());
        SubjectsResponseDto subjectsResponseDto = subjectDetailsService.readListOfSubjectNames(branchId);

        if (!subjectsResponseDto.isSuccess()) {
            throw new CustomResponseException(CustomErrorMessage.ERROR);
        }
        result.setList(subjectsResponseDto.getList());
        Currentacademicyear currentacademicyear = yearService.getYear();

        if (currentacademicyear == null) {
            throw new CustomResponseException(CustomErrorMessage.ERROR);
        }
        result.setCurrentacademicyear(currentacademicyear.getCurrentacademicyear());
        ExamScheduleResponseDto examScheduleResponseDto = examDetailsService.getExamSchedule(branchId);

        if (!examScheduleResponseDto.isSuccess()) {
            throw new CustomResponseException(CustomErrorMessage.ERROR);
        }
        result.setExams(examScheduleResponseDto.getExams());
        result.setList(examScheduleResponseDto.getList());
        result.setSelectedclass(examScheduleResponseDto.getSelectedclass());
        result.setSelectedexam(examScheduleResponseDto.getSelectedexam());
        result.setSelectedstudentname(examScheduleResponseDto.getSelectedstudentname());
        result.setSelectedclassandsec(examScheduleResponseDto.getSelectedclassandsec());
        result.setSelectedadmissionno(examScheduleResponseDto.getSelectedadmissionno());
        result.setExamschedules(examScheduleResponseDto.getExamschedules());
        result.setMessage(examScheduleResponseDto.getMessage());
        result.setResultMap(examScheduleResponseDto.getResultMap());
        result.setResultValue(examScheduleResponseDto.getResultValue());
        result.setResultList(examScheduleResponseDto.getResultList());


        return ResponseEntity.ok(result);
    }


    @PostMapping("/deleteMultiple")
    public ResponseEntity<ExamsListResponseDto> deleteMultiple(@RequestBody ExamIdsDto dto, @RequestHeader(value = "branchid") String branchId) {
        ResultResponse result = examDetailsService.deleteMultiple(dto);
        if (result.isSuccess()) {
            return readListOfExams(branchId);
        } else {
            throw new CustomResponseException(CustomErrorMessage.ERROR);
        }
    }

    @GetMapping("/readListOfExams")
    public ResponseEntity<ExamsListResponseDto> readListOfExams(@RequestHeader(value = "branchid") String branchId) {
        ExamsListResponseDto result = examDetailsService.readListOfExams(branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        } else {
            throw new CustomResponseException(CustomErrorMessage.ERROR);
        }
    }

    @PostMapping("/addExam")
    public ResponseEntity<ExamsListResponseDto> addExam(@RequestBody AddExamDto dto, @RequestHeader(value = "branchid") String branchId) {
        ResultResponse result = examDetailsService.addExam(dto, branchId);
        if (result.isSuccess()) {
            return readListOfExams(branchId);
        } else {
            throw new CustomResponseException(CustomErrorMessage.ERROR);
        }

    }
}
