/**
 *
 */
package org.ideoholic.curium.model.sendsms.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.exceptions.CustomErrorMessage;
import org.ideoholic.curium.exceptions.CustomResponseException;
import org.ideoholic.curium.model.academicyear.dto.CurrentAcademicYearResponseDto;
import org.ideoholic.curium.model.academicyear.service.YearService;
import org.ideoholic.curium.model.department.dto.DepartmentResponseDto;
import org.ideoholic.curium.model.employee.service.EmployeeService;
import org.ideoholic.curium.model.sendsms.dto.SMSResponseDto;
import org.ideoholic.curium.model.sendsms.dto.SendSMSDto;
import org.ideoholic.curium.model.sendsms.dto.SendSMSResponseDto;
import org.ideoholic.curium.model.sendsms.service.SmsService;
import org.ideoholic.curium.model.std.service.StandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Musaib_2
 *
 */

@RestController
public class SmsApiActionImpl implements SmsApiAction {

    @Autowired
    private StandardService standardService;
    
    @Autowired
    private YearService yearService;
    
    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private SmsService smsService;

    public ResponseEntity<SendSMSResponseDto> sendSMS(String branchId) {
       SendSMSResponseDto result = new SendSMSResponseDto();

        ResultResponse resultResponse = standardService.viewClasses(branchId);
        result.setSuccess(resultResponse.isSuccess());
        DepartmentResponseDto departmentResponseDto = employeeService.viewDepartments(branchId);
        result.setDepartmentList(departmentResponseDto.getDepartmentList());

        return ResponseEntity.ok(result);
    }

    public ResponseEntity<ResultResponse> sendStaffSMS(SendSMSDto dto,String branchId) {
       ResultResponse result = smsService.sendStaffSMS(dto,branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.SENDSMSMERROR);
    }

    public ResponseEntity<ResultResponse> sendAllSMS(SendSMSDto dto,String branchId) {
       ResultResponse result = smsService.sendAllSMS(dto,branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<ResultResponse> sendNumbersSMS(SendSMSDto dto) {
        ResultResponse result = smsService.sendNumbersSMS(dto);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<CurrentAcademicYearResponseDto> updateYear() {
        CurrentAcademicYearResponseDto result = yearService.updateYear();
        return ResponseEntity.ok(result);

    }

    public ResponseEntity<ResultResponse> sendSMSFeesDueReminder(SendSMSDto dto,String branchId) {
        ResultResponse result = smsService.sendSMSFeesDueReminder(dto,branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<SMSResponseDto> smsDeliveryReport() {
        SMSResponseDto result = smsService.SMSDeliveryReport();
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

}
