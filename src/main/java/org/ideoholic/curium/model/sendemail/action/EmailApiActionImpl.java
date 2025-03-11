/**
 * 
 */
package org.ideoholic.curium.model.sendemail.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.exceptions.CustomErrorMessage;
import org.ideoholic.curium.exceptions.CustomMessageResponseException;
import org.ideoholic.curium.exceptions.CustomResponseException;
import org.ideoholic.curium.model.academicyear.dto.CurrentAcademicYearResponseDto;
import org.ideoholic.curium.model.academicyear.service.YearService;
import org.ideoholic.curium.model.sendemail.dto.SendAllEmailDto;
import org.ideoholic.curium.model.sendemail.service.EmailService;
import org.ideoholic.curium.model.sendsms.dto.SendSMSDto;
import org.ideoholic.curium.model.sendsms.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Musaib_2
 * 
 */
@RestController
public class EmailApiActionImpl implements EmailApiAction {

	@Autowired
	private YearService yearService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private SmsService smsService;

	public ResponseEntity<ResultResponse> sendStaffSMS(SendSMSDto dto, String branchId) {
		ResultResponse result = smsService.sendStaffSMS(dto,branchId);
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		}
		throw new CustomResponseException(CustomErrorMessage.ERRORSMS);
	}

	
	public ResponseEntity<ResultResponse> sendAllEmail(SendAllEmailDto dto, String branchId) {
		ResultResponse result = emailService.sendAllEmail(dto,branchId);
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		}
		throw new CustomMessageResponseException(result.getMessage());
	}

	public ResponseEntity<ResultResponse> sendNumbersSMS(SendSMSDto dto) {
		ResultResponse result = smsService.sendNumbersSMS(dto); 
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);	
			}
		throw new CustomResponseException(CustomErrorMessage.ERRORSMS);
	}

	public ResponseEntity<CurrentAcademicYearResponseDto> updateYear() {
		CurrentAcademicYearResponseDto result = yearService.updateYear();
		return ResponseEntity.ok(result);

	}

}
