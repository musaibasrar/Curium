/**
 *
 */
package org.ideoholic.curium.model.sendsms.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.exceptions.CustomErrorMessage;
import org.ideoholic.curium.exceptions.CustomResponseException;
import org.ideoholic.curium.model.academicyear.action.YearActionAdapter;
import org.ideoholic.curium.model.academicyear.dto.CurrentAcademicYearResponseDto;
import org.ideoholic.curium.model.academicyear.service.YearService;
import org.ideoholic.curium.model.department.dto.DepartmentResponseDto;
import org.ideoholic.curium.model.employee.action.EmployeeActionAdapter;
import org.ideoholic.curium.model.employee.service.EmployeeService;
import org.ideoholic.curium.model.sendsms.dto.SMSResponseDto;
import org.ideoholic.curium.model.sendsms.dto.SendSMSDto;
import org.ideoholic.curium.model.sendsms.dto.SendSMSResponseDto;
import org.ideoholic.curium.model.sendsms.service.SmsService;
import org.ideoholic.curium.model.std.action.StandardActionAdapter;
import org.ideoholic.curium.model.std.service.StandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Musaib_2
 *
 */


@RequestMapping("/api/v1//SMSProcess")
public interface SmsApiAction {

    @GetMapping("/sendSMS")
    public ResponseEntity<SendSMSResponseDto> sendSMS(@RequestHeader(value = "branchid") String branchId);
    @PostMapping("/sendStaffSMS")
    public ResponseEntity<ResultResponse> sendStaffSMS(@RequestBody SendSMSDto dto, @RequestHeader(value = "branchid") String branchId);
    @PostMapping("/sendAllSMS")
    public ResponseEntity<ResultResponse> sendAllSMS(@RequestBody SendSMSDto dto, @RequestHeader(value = "branchid")  String branchId);
    @PostMapping("/sendNumbersSMS")
    public ResponseEntity<ResultResponse> sendNumbersSMS(@RequestBody  SendSMSDto dto);
    @GetMapping("/updateYear")
    public ResponseEntity<CurrentAcademicYearResponseDto> updateYear();
    @PostMapping("/sendSMSFeesDueReminder")
    public ResponseEntity<ResultResponse> sendSMSFeesDueReminder(@RequestBody SendSMSDto dto);
    @GetMapping("/SMSDeliveryReport")
    public ResponseEntity<SMSResponseDto> SMSDeliveryReport();


}
