package org.ideoholic.curium.model.examdetails.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.exceptions.CustomErrorMessage;
import org.ideoholic.curium.exceptions.CustomResponseException;
import org.ideoholic.curium.model.academicyear.service.YearService;
import org.ideoholic.curium.model.examdetails.dto.*;
import org.ideoholic.curium.model.examdetails.service.ExamDetailsService;
import org.ideoholic.curium.model.std.service.StandardService;
import org.ideoholic.curium.model.subjectdetails.service.SubjectDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/examDetailsProcess")
public class ExamDetailsApiActionImpl {
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
    public ResponseEntity<HallTicketResponseDto> printPreviewHallTicket(@RequestBody PrintPreviewHallTicketDto dto,@RequestHeader(value="branchId") String branchId) {
        HallTicketResponseDto result = examDetailsService.printPreviewHallTicket(dto,branchId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/searchHallTicketDetails")
    public ResponseEntity<ExamScheduleResponseDto> searchHallTicketDetails(@RequestBody ExamScheduleDto examScheduleDto,@RequestHeader(value="branchId") String branchId) {

        ExamScheduleResponseDto examScheduleResponseDto = examDetailsService.getExamScheduleDetails(examScheduleDto,branchId);
        ExamsListResponseDto examsListResponseDto = examDetailsService.readListOfExams(branchId);
        subjectDetailsService.readListOfSubjects(branchId);
        return ResponseEntity.ok(examScheduleResponseDto);
    }

    @GetMapping("/generateHallTicket")
    public ResponseEntity<GenerateHallTicketResponseDto> generateHallTicket(@RequestHeader(value="branchid") String branchId) {
            GenerateHallTicketResponseDto result = new GenerateHallTicketResponseDto();

       examDetailsService.readListOfExams(branchId);
        if (result == null)
            throw new CustomResponseException(CustomErrorMessage.ERROR);
        standardService.viewClasses(branchId);
        if (result == null)
            throw new CustomResponseException(CustomErrorMessage.ERROR);
       subjectDetailsService.readListOfSubjectNames(branchId);
        if (result == null)
            throw new CustomResponseException(CustomErrorMessage.ERROR);
        yearService.getYear();
        if (result == null)
            throw new CustomResponseException(CustomErrorMessage.ERROR);
        examDetailsService.getExamSchedule(branchId);
        if (result == null)
            throw new CustomResponseException(CustomErrorMessage.ERROR);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/deleteExamSchedule")
    public ResponseEntity<ExamSchedulesResponseDto> deleteExamSchedule(@RequestBody ExamIdsDto dto, @RequestHeader(value="branchid") String branchId) {
        ExamSchedulesResponseDto result = new ExamSchedulesResponseDto();
        examDetailsService.deleteExamSchedule(dto);
        if(result != null){
            return examSchedule(branchId);
        }else{
            throw new CustomResponseException(CustomErrorMessage.ERROR);
        }
    }

    @PostMapping("addSchedule")
    public ResponseEntity<ExamSchedulesResponseDto> addSchedule(@RequestBody AddScheduleDto dto,@RequestHeader(value="branchid") String branchId) {
         ExamSchedulesResponseDto result = new ExamSchedulesResponseDto();
        examDetailsService.addSchedule(dto,branchId);
        if(result != null){
           return examSchedule(branchId);
        }else{
            throw new CustomResponseException(CustomErrorMessage.ERROR);
        }
    }


    @GetMapping("/examSchedule")
    public ResponseEntity<ExamSchedulesResponseDto> examSchedule(@RequestHeader(value="branchid") String branchId) {
        ExamSchedulesResponseDto result = new ExamSchedulesResponseDto();

            examDetailsService.readListOfExams(branchId);
        if (result == null)
            throw new CustomResponseException(CustomErrorMessage.ERROR);
            standardService.viewClasses(branchId);
        if (result == null)
            throw new CustomResponseException(CustomErrorMessage.ERROR);
        subjectDetailsService.readListOfSubjectNames(branchId);
        if (result == null)
            throw new CustomResponseException(CustomErrorMessage.ERROR);
            yearService.getYear();
        if (result == null)
            throw new CustomResponseException(CustomErrorMessage.ERROR);
            examDetailsService.getExamSchedule(branchId);
        if (result == null)
            throw new CustomResponseException(CustomErrorMessage.ERROR);

        return ResponseEntity.ok(result);

    }

    @PostMapping("/deleteMultiple")
    public ResponseEntity<ExamsListResponseDto> deleteMultiple(@RequestBody ExamIdsDto dto, @RequestHeader(value="branchid") String branchId) {
        ResultResponse result = examDetailsService.deleteMultiple(dto);
        if(result.isSuccess()){
            return readListOfExams(branchId);
        }else{
            throw new CustomResponseException(CustomErrorMessage.ERROR);
        }
    }

    @GetMapping("/readListOfExams")
    public ResponseEntity<ExamsListResponseDto>  readListOfExams(@RequestHeader(value="branchid") String branchId) {
         ExamsListResponseDto result = examDetailsService.readListOfExams(branchId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }else{
            throw new CustomResponseException(CustomErrorMessage.ERROR);
        }
    }

    @PostMapping("/addExam")
    public ResponseEntity<ExamsListResponseDto> addExam(@RequestBody AddExamDto dto, @RequestHeader(value="branchid") String branchId) {
        ResultResponse result = examDetailsService.addExam(dto,branchId);
        if(result.isSuccess()){
            return readListOfExams(branchId);
        }else{
            throw new CustomResponseException(CustomErrorMessage.ERROR);
        }

    }
}
