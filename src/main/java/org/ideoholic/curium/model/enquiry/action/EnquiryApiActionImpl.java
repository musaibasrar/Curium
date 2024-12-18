package org.ideoholic.curium.model.enquiry.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.enquiry.dto.CertificateDto;
import org.ideoholic.curium.model.enquiry.dto.CertificateResponseDto;
import org.ideoholic.curium.model.enquiry.service.EnquiryService;
import org.ideoholic.curium.model.std.service.StandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnquiryApiActionImpl implements EnquiryApiAction {
	
	@Autowired
	private StandardService standardService;
	@Autowired
	private EnquiryService enquiryService;
	
	
	public ResponseEntity<ResultResponse> newEnquiryDetail(String branchId) {
		ResultResponse result = standardService.viewClasses(branchId);
		return ResponseEntity.ok(result);
	}


	public ResponseEntity<CertificateResponseDto> genarateNewCertificate(CertificateDto dto) {
		CertificateResponseDto result = enquiryService.getCertificate(dto);
		return ResponseEntity.ok(result);
	}

	
	}
