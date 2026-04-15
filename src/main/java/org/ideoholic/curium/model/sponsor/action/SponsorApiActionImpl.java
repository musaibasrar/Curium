package org.ideoholic.curium.model.sponsor.action;

import org.ideoholic.curium.exceptions.CustomErrorMessage;
import org.ideoholic.curium.exceptions.CustomResponseException;
import org.ideoholic.curium.model.sponsor.dto.SponsorDto;
import org.ideoholic.curium.model.sponsor.dto.SponsorResponseDto;
import org.ideoholic.curium.model.sponsor.service.SponsorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SponsorApiActionImpl implements SponsorApiAction {
	
	 @Autowired
	 private SponsorService sponsorService;
	
	
	public ResponseEntity<String> addSponsorPage() {
		return ResponseEntity.ok("addsponsor");
	}
	
	
	public ResponseEntity<SponsorResponseDto> addSponsor( SponsorDto sponsorDto,  String branchid, String userid) {
		SponsorResponseDto result = sponsorService.addSponsor(sponsorDto,branchid,userid);
		if(result.isSuccess())
		{
			return ResponseEntity.ok(result);
		}
		else
		{
			throw new CustomResponseException(CustomErrorMessage.ERROR);
		}
	}
	
	
	public ResponseEntity<SponsorResponseDto> viewAllSponsor(String branchid) {
		SponsorResponseDto result = sponsorService.viewAllSponsor(branchid);	
		return ResponseEntity.ok(result);
	}
	
	
	public ResponseEntity<SponsorResponseDto> deleteMultiple( SponsorDto sponsorDto, String branchId) {
		sponsorService.deleteMultiple(sponsorDto);
		return viewAllSponsor(branchId);
	}
	
	
	public ResponseEntity<SponsorResponseDto> viewDetailsSponsor( String empId) {
		SponsorResponseDto result = sponsorService.viewDetailsSponsor(empId);
		return ResponseEntity.ok(result);
	}
	
	
	public ResponseEntity<SponsorResponseDto> updateSponsorDetails(String empId) {
		SponsorResponseDto result = sponsorService.viewDetailsSponsor(empId);
			return ResponseEntity.ok(result);
	}
	
	
	public ResponseEntity<SponsorResponseDto> updateSponsor( SponsorDto sponsorDto) {
		SponsorResponseDto result = sponsorService.updateSponsor(sponsorDto);
		if(result.isSuccess()) {
			return ResponseEntity.ok(result);
		}
		else {
			 throw new CustomResponseException(CustomErrorMessage.ERROR);
			}
	}

}
