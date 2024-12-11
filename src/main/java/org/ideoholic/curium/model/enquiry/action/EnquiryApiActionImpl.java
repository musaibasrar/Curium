package org.ideoholic.curium.model.enquiry.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.enquiry.dto.CertificateDto;
import org.ideoholic.curium.model.enquiry.dto.CertificateResponseDto;
import org.ideoholic.curium.model.enquiry.service.EnquiryService;
import org.ideoholic.curium.model.std.service.StandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/EnquiryProcess")
public class EnquiryApiActionImpl implements EnquiryApiAction {
	
	@Autowired
	private StandardService standardService;
	@Autowired
	private EnquiryService enquiryService;
	
	@GetMapping("/newEnquiry")
	public ResponseEntity<ResultResponse> NewEnquiryDetail(@RequestHeader(value = "branchid") String branchId) {
		ResultResponse result = standardService.viewClasses(branchId);
		return ResponseEntity.ok(result);
	}

	@PostMapping("/genarateNewCertificate")
	public ResponseEntity<CertificateResponseDto> genarateNewCertificate(@RequestBody CertificateDto dto) {
		CertificateResponseDto result = enquiryService.getCertificate(dto);
		return ResponseEntity.ok(result);
	}
	}
