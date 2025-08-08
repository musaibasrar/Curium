/**
 *
 */
package org.ideoholic.curium.model.sendsms.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.academicyear.dto.CurrentAcademicYearResponseDto;
import org.ideoholic.curium.model.sendsms.dto.SMSResponseDto;
import org.ideoholic.curium.model.sendsms.dto.SendSMSDto;
import org.ideoholic.curium.model.sendsms.dto.SendSMSResponseDto;
import org.ideoholic.curium.util.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Musaib_2
 *
 */


@RequestMapping("/api/v1/smsProcess")
public interface SmsApiAction {

    @GetMapping("/sendSMS")
    public ResponseEntity<SendSMSResponseDto> sendSMS(@RequestHeader(value = Constants.BRANCHID) String branchId);
    
    @PostMapping("/sendStaffSMS")
    public ResponseEntity<ResultResponse> sendStaffSMS(@RequestBody SendSMSDto dto, @RequestHeader(value = Constants.BRANCHID) String branchId);
    
    @PostMapping("/sendAllSMS")
    public ResponseEntity<ResultResponse> sendAllSMS(@RequestBody SendSMSDto dto, @RequestHeader(value = Constants.BRANCHID)  String branchId);
    
    @PostMapping("/sendNumbersSMS")
    public ResponseEntity<ResultResponse> sendNumbersSMS(@RequestBody  SendSMSDto dto);
    
    @GetMapping("/updateYear")
    public ResponseEntity<CurrentAcademicYearResponseDto> updateYear();
    
    @PostMapping("/sendSMSFeesDueReminder")
    public ResponseEntity<ResultResponse> sendSMSFeesDueReminder(@RequestBody SendSMSDto dto, @RequestHeader(value = Constants.BRANCHID)  String branchId);
    
    @GetMapping("/smsDeliveryReport")
    public ResponseEntity<SMSResponseDto> smsDeliveryReport();
}
