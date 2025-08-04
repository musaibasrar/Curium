package org.ideoholic.curium.model.periods.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.exceptions.CustomErrorMessage;
import org.ideoholic.curium.exceptions.CustomResponseException;
import org.ideoholic.curium.model.documents.service.DocumentService;
import org.ideoholic.curium.model.employee.dto.EmployeesWithSalaryResponseDto;
import org.ideoholic.curium.model.employee.service.EmployeeService;
import org.ideoholic.curium.model.periods.dto.*;
import org.ideoholic.curium.model.periods.service.PeriodService;
import org.ideoholic.curium.model.std.service.StandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PeriodApiActionImpl implements PeriodApiAction {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private StandardService standardService;

    @Autowired
    private EmployeeService employeeService;

    // TODO Uncomment after PeriodService is made @Service
    // @Autowired
    private PeriodService periodService;

    public ResponseEntity<TeacherTimeTableResponseDto> viewTeacherTimeTable(String teacherName, String branchId) {
        TeacherTimeTableResponseDto result = periodService.viewTeacherTimeTable(teacherName, branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<EmployeesWithSalaryResponseDto> generateTeacherTimeTable(String branchId) {
        EmployeesWithSalaryResponseDto result = employeeService.viewAllEmployee(branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<TimeTableResponseDto> generateTimeTable(String branchId) {//Error
        TimeTableResponseDto result = periodService.generateTimeTable(branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<TimeTableResponseDto> deletePeriods(PeriodMasterIdDto dto, String branchId) {
    	ResultResponse result = periodService.deletePeriods(dto);
        if (result.isSuccess()) {
            return periodConfiguration(branchId);
        }

        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<TimeTableViewResponseDto> viewTimeTable(String periodMasterId) {
        TimeTableViewResponseDto result = periodService.viewTimeTable(periodMasterId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<TimeTableResponseDto> savePeriods(PeriodsSaveDto dto, String branchId, String userId) {
    	ResultResponse result = periodService.savePeriods(dto, branchId, userId);
        if (result.isSuccess()) {
            return periodConfiguration(branchId);
        }

        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<TimeTableResponseDto> periodConfiguration(String branchId) {
        TimeTableResponseDto result = periodService.periodConfiguration(branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<ResultResponse> transferCertificate(String branchId) {
        ResultResponse result = documentService.transferCertificate(branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<UpdatePeriodDetailsResponseDto> updatePeriodDetails(String periodMasterId, String branchId) {//Error
        UpdatePeriodDetailsResponseDto result = new UpdatePeriodDetailsResponseDto();

        TimeTableViewResponseDto periodDetailsResult = periodService.updatePeriodDetails(periodMasterId);
        result.setPeriodMasterId(periodDetailsResult.getPeriodMasterId());
        result.setPeriodMaster(periodDetailsResult.getPeriodMaster());
        result.setPeriodDetails(periodDetailsResult.getPeriodDetails());
        result.setPeriodMap(periodDetailsResult.getPeriodMap());

        periodService.getPeriodDetail();

        ResultResponse resultResponse = standardService.viewClasses(branchId);
        result.setClasssecList(resultResponse.getResultList());

        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<ResultResponse> updatenewPeriodDetails(PeriodsSaveDto dto, String branchId, String userId) {//Error
        ResultResponse result = periodService.updatenewPeriodDetails(dto, branchId, userId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

}
