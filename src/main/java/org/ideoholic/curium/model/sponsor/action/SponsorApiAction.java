package org.ideoholic.curium.model.sponsor.action;

import org.ideoholic.curium.model.sponsor.dto.SponsorDto;
import org.ideoholic.curium.model.sponsor.dto.SponsorResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

public interface SponsorApiAction {
	
	
	 ResponseEntity<String> addSponsorPage();
	
	 ResponseEntity<SponsorResponseDto> addSponsor(@RequestBody SponsorDto sponsorDto, @RequestHeader(value = "branchid") String branchid, @RequestHeader(value = "userid") String userid) ;
	
     ResponseEntity<SponsorResponseDto> ViewAllSponsor(@RequestHeader(value = "branchid") String branchid);
	
	 ResponseEntity<SponsorResponseDto> deleteMultiple(@RequestBody SponsorDto sponsorDto, @RequestHeader(value = "branchid") String branchId);
	
	 ResponseEntity<SponsorResponseDto> viewDetailsSponsor(@RequestParam(value = "id") String empId);
	
	 ResponseEntity<SponsorResponseDto> updateSponsorDetails(@RequestParam(value = "id") String empId);
	
	 ResponseEntity<SponsorResponseDto> updateSponsor(@RequestBody SponsorDto sponsorDto);

}
