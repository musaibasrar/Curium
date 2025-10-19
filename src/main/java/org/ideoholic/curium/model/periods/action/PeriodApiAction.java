package org.ideoholic.curium.model.periods.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.employee.dto.EmployeesWithSalaryResponseDto;
import org.ideoholic.curium.model.periods.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/periodProcess")
public interface PeriodApiAction {

    @GetMapping("/viewTeacherTimeTable")
    ResponseEntity<TeacherTimeTableResponseDto> viewTeacherTimeTable(@RequestParam(value = "teachername") String teacherName, @RequestHeader(value = "branchid") String branchId);

    @GetMapping("/generateTeacherTimeTable")
    ResponseEntity<EmployeesWithSalaryResponseDto> generateTeacherTimeTable(@RequestHeader(value = "branchid") String branchId);


    @RequestMapping(value = "/generateTimeTable", method = {RequestMethod.GET, RequestMethod.POST})
    ResponseEntity<TimeTableResponseDto> generateTimeTable(@RequestHeader(value = "branchid") String branchId);

    @PostMapping("/deletePeriods")
    ResponseEntity<TimeTableResponseDto> deletePeriods(@RequestBody PeriodMasterIdDto dto, @RequestHeader(value = "branchid") String branchId);

    @GetMapping("/viewTimeTable")
    ResponseEntity<TimeTableViewResponseDto> viewTimeTable(@RequestParam(value = "id") String periodMasterId);

    @PostMapping("/savePeriods")
    ResponseEntity<TimeTableResponseDto> savePeriods(@RequestBody PeriodsSaveDto dto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "userloginid") String userId);

    @GetMapping("/periodConfiguration")
    ResponseEntity<TimeTableResponseDto> periodConfiguration(@RequestHeader(value = "branchid") String branchId);


    @GetMapping("/transferCertificate")
    ResponseEntity<ResultResponse> transferCertificate(@RequestHeader(value = "branchid") String branchId);

    @GetMapping("/updatePeriodDetails")
    ResponseEntity<UpdatePeriodDetailsResponseDto> updatePeriodDetails(@RequestParam(value = "id") String periodMasterId, @RequestHeader(value = "branchid") String branchId);

    @PostMapping("/updatenewPeriodDetails")
    public ResponseEntity<ResultResponse> updatenewPeriodDetails(@RequestBody PeriodsSaveDto dto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "userloginid") String userId);

}
