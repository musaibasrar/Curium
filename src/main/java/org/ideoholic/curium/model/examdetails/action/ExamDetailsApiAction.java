package org.ideoholic.curium.model.examdetails.action;

import org.ideoholic.curium.model.examdetails.dto.*;
import org.springframework.http.ResponseEntity;

public interface ExamDetailsApiAction {
    public ResponseEntity<HallTicketResponseDto> printPreviewHallTicket(PrintPreviewHallTicketDto dto,String branchId);

    public ResponseEntity<ExamScheduleResponseDto> searchHallTicketDetails(ExamScheduleDto examScheduleDto,String branchId);

    public ResponseEntity<GenerateHallTicketResponseDto> generateHallTicket(String branchId);

    public ResponseEntity<ExamSchedulesResponseDto> deleteExamSchedule(ExamIdsDto dto,String branchId);

    public ResponseEntity<ExamSchedulesResponseDto> addSchedule(AddScheduleDto dto,String branchId);

    public ResponseEntity<ExamSchedulesResponseDto> examSchedule(String branchId);

    public ResponseEntity<ExamsListResponseDto> deleteMultiple(ExamIdsDto dto,String branchId);

    public ResponseEntity<ExamsListResponseDto>  readListOfExams(String branchId);

    public ResponseEntity<ExamsListResponseDto> addExam(AddExamDto dto,String branchId);





    }
