package org.ideoholic.curium.model.feesdetails.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.feesdetails.dto.DataForFeesResponseDto;
import org.ideoholic.curium.model.feesdetails.dto.FeesIdDetailsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/api/v1/FeesDetails")
public interface FeesDetailsApiAction {
	

	@PostMapping("/download")
	public ResponseEntity<ResultResponse> downloadFile();

	@PostMapping("/exportDataForFees")
	public ResponseEntity<ResultResponse> exportFeesData(@RequestBody FeesIdDetailsDto feesIdDetailsDto);
		
	@PostMapping("/exportDataForOtherFees")
	public ResponseEntity<ResultResponse> exportOtherFeesData(@RequestBody FeesIdDetailsDto feesIdDetailsDto);
	
	@PostMapping("/printDataForFees")
	public ResponseEntity<DataForFeesResponseDto> printFeesData(@RequestBody FeesIdDetailsDto feesIdDetailsDto);
	
}
