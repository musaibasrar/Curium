package org.ideoholic.curium.model.feesdetails.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.exceptions.CustomErrorMessage;
import org.ideoholic.curium.exceptions.CustomResponseException;
import org.ideoholic.curium.model.feescategory.service.FeesService;
import org.ideoholic.curium.model.feesdetails.dto.DataForFeesResponseDto;
import org.ideoholic.curium.model.feesdetails.dto.FeesIdDetailsDto;
import org.ideoholic.curium.model.feesdetails.service.FeesDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FeesDetailsApiActionImpl implements FeesDetailsApiAction {
	
	@Autowired
	private FeesService feesService;
	
	@Autowired
	private FeesDetailsService feesDetailsService;

	public ResponseEntity<ResultResponse> downloadFile() {
		
		ResultResponse result = feesService.downlaodFile();
		if(result.isSuccess()){
			return ResponseEntity.ok(result);
		}
		throw new CustomResponseException(CustomErrorMessage.EXPORTFAILURE);
	}

	public ResponseEntity<ResultResponse> exportFeesData(FeesIdDetailsDto feesIdDetailsDto) {
		
		ResultResponse result = feesDetailsService.exportDataForFees(feesIdDetailsDto);
		if(result.isSuccess()){
			return ResponseEntity.ok(result);
		}else{
			throw new CustomResponseException(CustomErrorMessage.EXPORTFAILURE);
		}
		
	}
	
	public ResponseEntity<ResultResponse> exportOtherFeesData(FeesIdDetailsDto feesIdDetailsDto) {
		
		ResultResponse result = feesDetailsService.exportDataForOtherFees(feesIdDetailsDto);
		if(result.isSuccess()){
			return ResponseEntity.ok(result);
		}else{
			throw new CustomResponseException(CustomErrorMessage.EXPORTFAILURE);
		}
		
	}
	
	public ResponseEntity<DataForFeesResponseDto> printFeesData(FeesIdDetailsDto feesIdDetailsDto) {
		
		DataForFeesResponseDto result = feesDetailsService.printDataForFees(feesIdDetailsDto);
		if(result.isSuccess()){
			return ResponseEntity.ok(result);
		}else{
			throw new CustomResponseException(CustomErrorMessage.ERROR);
		}
		
	}
	
}
