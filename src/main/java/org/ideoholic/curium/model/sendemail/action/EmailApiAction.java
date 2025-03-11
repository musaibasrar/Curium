/**
 * 
 */
package org.ideoholic.curium.model.sendemail.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.academicyear.dto.CurrentAcademicYearResponseDto;
import org.ideoholic.curium.model.sendemail.dto.SendAllEmailDto;
import org.ideoholic.curium.model.sendsms.dto.SendSMSDto;
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

@RequestMapping("/api/v1/emailProcess")
public interface EmailApiAction {


	@PostMapping("/sendStaffSMS")
	public ResponseEntity<ResultResponse> sendStaffSMS(@RequestBody SendSMSDto dto, @RequestHeader(value = "branchid") String branchId);

	
	@PostMapping("/sendAllEmail")
	public ResponseEntity<ResultResponse> sendAllEmail(@RequestBody SendAllEmailDto dto, @RequestHeader(value = "branchid") String branchId);

	@PostMapping("/sendNumbersSMS")
	public ResponseEntity<ResultResponse> sendNumbersSMS(@RequestBody SendSMSDto dto);
	
	@GetMapping("/updateYear")
	public ResponseEntity<CurrentAcademicYearResponseDto> updateYear();

	

}
