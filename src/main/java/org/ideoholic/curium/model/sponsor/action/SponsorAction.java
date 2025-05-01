package org.ideoholic.curium.model.sponsor.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/SponserProcess")
public class SponsorAction {
	
	@Autowired
	private SponsorActionAdapter sponsorActionAdapter;

	
	@GetMapping("/addSponsorPage")
	public String addSponsorPage() {
		return "addsponsor";
	}
	
	@PostMapping("/addSponsor")
	public String addSponsor() {
		if(sponsorActionAdapter.addSponsor())
		{
		return "sponsorsaved";
		}
		else
		{
			return "error";
		}
	}
	
	@RequestMapping(value = "/ViewAllSponsor", method = { RequestMethod.GET, RequestMethod.POST })
	public String ViewAllSponsor() {
		sponsorActionAdapter.viewAllSponsor();	
		return "viewallsponsor";
	}
	
	@PostMapping("/deleteMultiple")
	public String deleteMultiple() {
		sponsorActionAdapter.deleteMultiple();
		return ViewAllSponsor();
	}
	
	@GetMapping("/viewDetailsSponsor")
	public String viewDetailsSponsor() {
		sponsorActionAdapter.viewDetailsSponsor();
		return "sponsor_details";
	}
	
	@PostMapping("/updateSponsorDetails")
	public String updateSponsorDetails() {
		sponsorActionAdapter.viewDetailsSponsor();
			return "sponsor_update";
	}
	
	@PostMapping("/updateSponsor")
	public String updateSponsor() {
		if(sponsorActionAdapter.updateSponsor()) {
			return "sponsor_updated";
		}
		else {
			return "error";
		}
	}



}
