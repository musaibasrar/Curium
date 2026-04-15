package org.ideoholic.curium.model.sponsor.action;

import org.ideoholic.curium.model.sponsor.dto.SponsorDto;
import org.ideoholic.curium.model.sponsor.dto.SponsorResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/v1/SponserProcess")
public interface SponsorApiAction {
	 
	 @GetMapping("/addSponsorPage")
	 ResponseEntity<String> addSponsorPage();
		
	 @PostMapping("/addSponsor")
	 ResponseEntity<SponsorResponseDto> addSponsor(@RequestBody SponsorDto sponsorDto, @RequestHeader(value = "branchid") String branchid, @RequestHeader(value = "userid") String userid) ;
	
	 @RequestMapping(value = "/ViewAllSponsor", method = { RequestMethod.GET, RequestMethod.POST })
     ResponseEntity<SponsorResponseDto> viewAllSponsor(@RequestHeader(value = "branchid") String branchid);
	
	 @PostMapping("/deleteMultiple")
	 ResponseEntity<SponsorResponseDto> deleteMultiple(@RequestBody SponsorDto sponsorDto, @RequestHeader(value = "branchid") String branchId);
	
	 @GetMapping("/viewDetailsSponsor")
	 ResponseEntity<SponsorResponseDto> viewDetailsSponsor(@RequestParam(value = "id") String empId);
	
	 @PostMapping("/updateSponsorDetails")
	 ResponseEntity<SponsorResponseDto> updateSponsorDetails(@RequestParam(value = "id") String empId);
	
	 @PostMapping("/updateSponsor")
	 ResponseEntity<SponsorResponseDto> updateSponsor(@RequestBody SponsorDto sponsorDto);

}
