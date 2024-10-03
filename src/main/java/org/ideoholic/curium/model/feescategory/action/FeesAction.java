package org.ideoholic.curium.model.feescategory.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.feescategory.service.FeesService;
import org.ideoholic.curium.model.std.action.StandardActionAdapter;
import org.ideoholic.curium.model.student.action.StudentActionAdapter;
import org.ideoholic.curium.model.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/FeesProcess")
public class FeesAction {
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private HttpSession httpSession;
	@Autowired
	private StandardActionAdapter standardActionAdapter;
	@Autowired
	private FeesActionAdapter feesActionAdapter;
	@Autowired
	private StudentActionAdapter studentActionAdapter;

	@PostMapping("/applyConcession")
	public String applyConcession() {
		feesActionAdapter.applyConcession();
		return studentFeePage();
	}

	@PostMapping("/printFeesWaiveoffReport")
	public String printFeesWaiveoffReport() {
		return "printfeeswaiveoffreport";
	}

	@PostMapping("/searchFeesWaiveoffReport")
	public String searchFeesWaiveoffReport() {
		feesActionAdapter.searchFeesWaiveofforConcessionReport("waiveoff");
		return "feeswaiveoffreport";
	}

	@GetMapping("/feesWaiveoffReport")
	public String feesWaiveoffReport() {
		standardActionAdapter.viewClasses();
		return "feeswaiveoffreport";
	}

	@PostMapping("/printFeesConcessionReport")
	public String printFeesConcessionReport() {
		return "printfeesconcessionreport";
	}

	@PostMapping("/searchFeesConcessionReport")
	public String searchFeesConcessionReport() {
		feesActionAdapter.searchFeesWaiveofforConcessionReport("concession");
		return "feesconcessionreport";
	}

	@GetMapping("/feesConcessionReport")
	public String feesConcessionReport() {
		standardActionAdapter.viewClasses();
		return "feesconcessionreport";
	}

	@PostMapping("/waiveOffFees")
	public String waiveOffFees() {
		feesActionAdapter.waiveOffFees();
		return studentFeePage();
	}

	@GetMapping("/feesReport")
	public String feesReport() {
		standardActionAdapter.viewClasses();
		feesActionAdapter.viewFees();
		return "feesreport";
	}

	@GetMapping("/feesStructure")
	public String feesStructure() {
		standardActionAdapter.viewClasses();
		return "feesstructure";
	}

	@PostMapping("/deleteFeesCategory")
	public String deleteFeesCategory() {
		feesActionAdapter.deleteFeesCategory();
		return studentFeePage();
	}

	@PostMapping("/deleteMultiple")
	public String deleteMultiple() {
		 feesActionAdapter.deleteMultiple();
		return viewFees();
	}

	@GetMapping("/feesCollect")
	public String feesCollect() {
		feesActionAdapter.viewFees();
		feesActionAdapter.viewAllStudentsList();
		return "feesCollection";
	}

	@GetMapping("/feesCollectAllBranches")
	public String feesCollectAllBranches() {
		feesActionAdapter.viewFees();
		feesActionAdapter.viewAllBranchStudents();
		return "feesCollection";
	}

	@PostMapping("/addFeesParticular")
	public String addFeesParticular() {
		feesActionAdapter.addFeesParticular();
		return viewFees();
	}

	@GetMapping("/feesView")
	public String viewFees() {
		feesActionAdapter.viewFees();
		standardActionAdapter.viewClasses();
		return "feesCategory";
	}

	private String studentFeePage() {
		if (studentActionAdapter.viewDetailsOfStudent()) {
			if (httpSession.getAttribute("userType").toString().equalsIgnoreCase("admin")) {
				return "student_details_feesstructure_admin";
			} else if (!httpSession.getAttribute("userType").toString().equalsIgnoreCase("admin")) {
				return "student_details_feesstructure";
			} else {
				return "student_details_feesstructure";
			}
		} else {
			return "viewAll";
		}
	}
	
	
	@GetMapping("/feesDueStampFees")
	public String feesDueStampFees() {
		standardActionAdapter.viewClasses();
		return "feesstampdue";
	}
	
	@PostMapping("/odeleteMultiple")
	public String odeleteMultiple() {
		feesActionAdapter.odeleteMultiple();
		return otherviewFees();
	}
	
	@PostMapping("/addotherFeesParticular")
	public String addotherFeesParticular() {
		feesActionAdapter.addOtherFeesParticular();
		return otherviewFees();
	}
	
	@GetMapping("/otherFeesView")
	public String otherviewFees() {
		feesActionAdapter.viewOtherFees();
		standardActionAdapter.viewClasses();
		return "otherfeecategory";
	}
	
	@GetMapping("/otherfeesCollect")
	public String otherfeesCollect() {
		feesActionAdapter.viewOtherFees();
		feesActionAdapter.viewAllStudentsList();
		return "otherfeesCollection";
	}
	
	@GetMapping("/otherfeesReport")
	public String otherfeesReport() {
		standardActionAdapter.viewClasses();
		feesActionAdapter.viewOtherFees();
		return "otherfeesreport";
	}
	
	@GetMapping("/searchfeecategory")
	public void searchFeeCategory() {
			try {
				feesActionAdapter.getFeeCategory();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public void setHttpobjects(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
	
	@PostMapping("/applyotherConcession")
	public String applyotherConcession() {
		feesActionAdapter.applyotherConcession();
		return studentotherFeePage();
	}
	
	private String studentotherFeePage() {
		if (studentActionAdapter.viewOtherFeesDetailsOfStudent()) {
			if (httpSession.getAttribute("userType").toString().equalsIgnoreCase("superadmin")) {
				return "student_details_other_feesstructure_admin";
			}else if (httpSession.getAttribute("userType").toString().equalsIgnoreCase("admin")) {
				return "student_details_other_feesstructure_admin";
			} else if (!httpSession.getAttribute("userType").toString().equalsIgnoreCase("admin")) {
				return "student_details_other_feesstructure";
			} else {
				return "student_details_other_feesstructure";
			}
		} else {
			return "viewAll";
		}
	}
	
	@GetMapping("/feesDueReportHeadWise")
	public String feesDueReportHeadWise() {
		standardActionAdapter.viewClasses();
		feesActionAdapter.viewFees();
		return "feesdueheadwisereport";
	}
	
	@GetMapping("/searchfeecategoryheadwise")
	public void searchFeeCategoryHeadWise() {
			try {
				feesActionAdapter.getFeeCategoryHeadWise();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	@GetMapping("/defaulterReport")
	public String defaulterReport() {
		standardActionAdapter.viewClasses();
		feesActionAdapter.viewFees();
		return "defaultersreport";
	}

	@GetMapping("/dndReport")
	public String dndReport() {
		feesActionAdapter.getDndReport();
		return "dndreport";
	}
	
	@PostMapping("/deleteOtherFeesCategory")
	public String deleteOtherFeesCategory() {
		feesActionAdapter.deleteOtherFeesCategory();
		return studentotherFeePage();
	}
	
	@GetMapping("/feesReportDue")
	public String feesReportDue() {
		standardActionAdapter.viewClasses();
		feesActionAdapter.viewFees();
		return "feesreportdue";
	}
}
