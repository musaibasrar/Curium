package org.ideoholic.curium.model.feescategory.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ideoholic.curium.model.feescategory.service.FeesService;
import org.ideoholic.curium.model.std.service.StandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/FeesProcess")
public class FeesAction {
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;

	@PostMapping("/printFeesWaiveoffReport")
	public String printFeesWaiveoffReport() {
		return "printfeeswaiveoffreport";
	}

	@PostMapping("/searchFeesWaiveoffReport")
	public String searchFeesWaiveoffReport() {
		new FeesService(request, response).searchFeesWaiveofforConcessionReport("waiveoff");
		return "feeswaiveoffreport";
	}

	@GetMapping("/feesWaiveoffReport")
	public String feesWaiveoffReport() {
		new StandardService(request, response).viewClasses();
		return "feeswaiveoffreport";
	}

	@PostMapping("/printFeesConcessionReport")
	public String printFeesConcessionReport() {
		return "printfeesconcessionreport";
	}

	@PostMapping("/searchFeesConcessionReport")
	public String searchFeesConcessionReport() {
		new FeesService(request, response).searchFeesWaiveofforConcessionReport("concession");
		return "feesconcessionreport";
	}

	@GetMapping("/feesConcessionReport")
	public String feesConcessionReport() {
		new StandardService(request, response).viewClasses();
		return "feesconcessionreport";
	}

	@PostMapping("/waiveOffFees")
	public String waiveOffFees() {
		return new FeesService(request, response).waiveOffFees();
	}

	@GetMapping("/feesReport")
	public String feesReport() {
		new StandardService(request, response).viewClasses();
		return "feesreport";
	}

	@GetMapping("/feesStructure")
	public String feesStructure() {
		new StandardService(request, response).viewClasses();
		return "feesstructure";
	}

	@PostMapping("/deleteFeesCategory")
	public String deleteFeesCategory() {
		return new FeesService(request, response).deleteFeesCategory();
	}

	@PostMapping("/deleteMultiple")
	public String deleteMultiple() {
		new FeesService(request, response).deleteMultiple();
		return viewFees();
	}

	@GetMapping("/feesCollect")
	public String feesCollect() {
		new FeesService(request, response).viewFees();
		new FeesService(request, response).viewAllStudentsList();
		return "feesCollection";

	}

	@GetMapping("/feesCollectAllBranches")
	public String feesCollectAllBranches() {
		new FeesService(request, response).viewFees();
		new FeesService(request, response).viewAllBranchStudents();
		return "feesCollection";
	}

	@PostMapping("/addFeesParticular")
	public String addFeesParticular() {
		new FeesService(request, response).addFeesParticular();
		return viewFees();
	}

	@GetMapping("/feesView")
	public String viewFees() {
		new FeesService(request, response).viewFees();
		new StandardService(request, response).viewClasses();
		return "feesCategory";
	}

}
