package org.ideoholic.curium.model.stampfees.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ideoholic.curium.model.academicyear.action.YearActionAdapter;
import org.ideoholic.curium.model.academicyear.service.YearService;
import org.ideoholic.curium.model.feescategory.action.FeesActionAdapter;
import org.ideoholic.curium.model.feescategory.service.FeesService;
import org.ideoholic.curium.model.stampfees.service.StampFeesService;
import org.ideoholic.curium.model.std.action.StandardActionAdapter;
import org.ideoholic.curium.model.std.service.StandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/StampFeesProcess")
public class StampFeesAction {

	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private YearActionAdapter yearActionAdapter;
	@Autowired
	private StandardActionAdapter standardActionAdapter;
	@Autowired
	private FeesActionAdapter feesActionAdapter;

	@PostMapping("/searchForFees")
	public String searchForFees() {
		new StampFeesService(request, response).advanceSearch();
		return "feesstructure";
	}

	@PostMapping("/delete")
	public String deleteFeesStructure() {
		new StampFeesService(request, response).deleteFeesStamp();
		return "feesstampsuccess";
	}

	@PostMapping("/applyFees")
	public String applyFees() {
		new StampFeesService(request, response).addFeesStamp();
		return "feesstampsuccess";
	}

	@GetMapping("/showFeesDetails")
	public String showFeesDetails() {
		feesActionAdapter.viewFees();
		yearActionAdapter.getYear();
		standardActionAdapter.viewClasses();
		return "stampfees";
	}

	@PostMapping("/search")
	public String search() {
		new StampFeesService(request, response).advanceSearch();
		return "stampfees";
	}
	
	@GetMapping("/showFeesDetailsYearly")
	public void showFeesDetailsYearly() {
		try {
			new FeesService(request, response).viewFeesYearly();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PostMapping("/applyotherFees")
	public String applyotherFees() {
		new StampFeesService(request, response).addotherFeesStamp();
		return "feesstampsuccess";
	}
	
	@GetMapping("/showOtherFeesDetails")
	public String showOtherFeesDetails() {
		feesActionAdapter.viewOtherFees();
		yearActionAdapter.getYear();
		standardActionAdapter.viewClasses();
		return "otherstampfees";
	}
	
	@PostMapping("/othersearch")
	public String othersearch() {
		new StampFeesService(request, response).otheradvanceSearch();
		return "otherstampfees";
	}
	
	@PostMapping("/advanceSearchForStampFees")
	public String advanceSearchForStampFees() {
		new StampFeesService(request, response).advanceSearchForStampFees();
		return "stampfees";
	}
	
}
