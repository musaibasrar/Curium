package org.ideoholic.curium.model.sponsor.action;

import org.ideoholic.curium.exceptions.CustomErrorMessage;
import org.ideoholic.curium.exceptions.CustomResponseException;
import org.ideoholic.curium.model.diary.dto.DiaryResponseDto;
import org.ideoholic.curium.model.sponsor.dto.SponsorDto;
import org.ideoholic.curium.model.sponsor.dto.SponsorResponseDto;
import org.ideoholic.curium.model.sponsor.service.SponsorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/SponserProcess")
public class SponsorApiActionImpl implements SponsorApiAction {
	
	 @Autowired
	 private SponsorService sponsorService;
	
	@GetMapping("/addSponsorPage")
	public ResponseEntity<String> addSponsorPage() {
		return ResponseEntity.ok("addsponsor");
	}
	
	@PostMapping("/addSponsor")
	public ResponseEntity<SponsorResponseDto> addSponsor(@RequestBody SponsorDto sponsorDto, @RequestHeader(value = "branchid") String branchid, @RequestHeader(value = "userid") String userid) {
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
	
	@RequestMapping(value = "/ViewAllSponsor", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<SponsorResponseDto> ViewAllSponsor(@RequestHeader(value = "branchid") String branchid) {
		SponsorResponseDto result = sponsorService.viewAllSponsor(branchid);	
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/deleteMultiple")
	public ResponseEntity<SponsorResponseDto> deleteMultiple(@RequestBody SponsorDto sponsorDto, @RequestHeader(value = "branchid") String branchId) {
		sponsorService.deleteMultiple(sponsorDto);
		return ViewAllSponsor(branchId);
	}
	
	@GetMapping("/viewDetailsSponsor")
	public ResponseEntity<SponsorResponseDto> viewDetailsSponsor(@RequestParam(value = "id") String empId) {
		SponsorResponseDto result = sponsorService.viewDetailsSponsor(empId);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/updateSponsorDetails")
	public ResponseEntity<SponsorResponseDto> updateSponsorDetails(@RequestParam(value = "id") String empId) {
		SponsorResponseDto result = sponsorService.viewDetailsSponsor(empId);
			return ResponseEntity.ok(result);
	}
	
	@PostMapping("/updateSponsor")
	public ResponseEntity<SponsorResponseDto> updateSponsor(@RequestBody SponsorDto sponsorDto) {
		SponsorResponseDto result = sponsorService.updateSponsor(sponsorDto);
		if(result.isSuccess()) {
			return ResponseEntity.ok(result);
		}
		else {
			 throw new CustomResponseException(CustomErrorMessage.ERROR);
			}
	}



}
