package org.ideoholic.curium.model.stampfees.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ideoholic.curium.model.academicyear.action.YearActionAdapter;
import org.ideoholic.curium.model.feescategory.action.FeesActionAdapter;
import org.ideoholic.curium.model.std.action.StandardActionAdapter;
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
	
	@Autowired
	private StampFeesActionAdapter stampFeesActionAdapter;

	@PostMapping("/searchForFees")
	public String searchForFees() {
		stampFeesActionAdapter.advanceSearch();
		return "feesstructure";
	}

	@PostMapping("/delete")
	public String deleteFeesStructure() {
		stampFeesActionAdapter.deleteFeesStamp();
		return "feesstampsuccess";
	}

	@PostMapping("/applyFees")
	public String applyFees() {
		stampFeesActionAdapter.addFeesStamp();
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
		stampFeesActionAdapter.advanceSearch();
		return "stampfees";
	}
	
	@GetMapping("/showFeesDetailsYearly")
	public void showFeesDetailsYearly() {
		try {
			feesActionAdapter.viewFeesYearly();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PostMapping("/applyotherFees")
	public String applyotherFees() {
		stampFeesActionAdapter.addotherFeesStamp();
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
		stampFeesActionAdapter.otheradvanceSearch();
		return "otherstampfees";
	}
	
	@PostMapping("/advanceSearchForStampFees")
	public String advanceSearchForStampFees() {
		stampFeesActionAdapter.advanceSearchForStampFees();
		return "stampfees";
	}
	
}
