package org.ideoholic.curium.model.feescategory.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.feescategory.service.FeesService;
import org.ideoholic.curium.model.std.service.StandardService;
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
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	@Autowired
	HttpSession httpSession;

	@PostMapping("/applyConcession")
	public String applyConcession() {
		String studentId = new FeesService(request, response).applyConcession();
		return studentFeePage(studentId);
	}

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
		String studentId = new FeesService(request, response).waiveOffFees();
		return studentFeePage(studentId);
	}

	@GetMapping("/feesReport")
	public String feesReport() {
		new StandardService(request, response).viewClasses();
		new FeesService(request, response).viewFees();
		return "feesreport";
	}

	@GetMapping("/feesStructure")
	public String feesStructure() {
		new StandardService(request, response).viewClasses();
		return "feesstructure";
	}

	@PostMapping("/deleteFeesCategory")
	public String deleteFeesCategory() {
		String studentId = new FeesService(request, response).deleteFeesCategory();
		return studentFeePage(studentId);
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

	private String studentFeePage(String studentId) {
		if (new StudentService(request, response).viewDetailsOfStudent(studentId)) {
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
		new StandardService(request, response).viewClasses();
		return "feesstampdue";
	}
	
	@PostMapping("/odeleteMultiple")
	public String odeleteMultiple() {
		new FeesService(request, response).odeleteMultiple();
		return otherviewFees();
	}
	
	@PostMapping("/addotherFeesParticular")
	public String addotherFeesParticular() {
		new FeesService(request, response).addOtherFeesParticular();
		return otherviewFees();
	}
	
	@GetMapping("/otherFeesView")
	public String otherviewFees() {
		new FeesService(request, response).viewOtherFees();
		new StandardService(request, response).viewClasses();
		return "otherfeecategory";
	}
	
	@GetMapping("/otherfeesCollect")
	public String otherfeesCollect() {
		new FeesService(request, response).viewOtherFees();
		new FeesService(request, response).viewAllStudentsList();
		return "otherfeesCollection";
	}
	
	@GetMapping("/otherfeesReport")
	public String otherfeesReport() {
		new StandardService(request, response).viewClasses();
		new FeesService(request, response).viewOtherFees();
		return "otherfeesreport";
	}
	
	@GetMapping("/searchfeecategory")
	public void searchFeeCategory() {
			try {
				new FeesService(request, response).getFeeCategory();
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
		String studentId = new FeesService(request, response).applyotherConcession();
		return studentotherFeePage(studentId);
	}
	
	private String studentotherFeePage(String studentId) {
		if (new StudentService(request, response).viewOtherFeesDetailsOfStudent(studentId)) {
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
		new StandardService(request, response).viewClasses();
		new FeesService(request, response).viewFees();
		return "feesdueheadwisereport";
	}
	
	@GetMapping("/searchfeecategoryheadwise")
	public void searchFeeCategoryHeadWise() {
			try {
				new FeesService(request, response).getFeeCategoryHeadWise();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	@GetMapping("/defaulterReport")
	public String defaulterReport() {
		new StandardService(request, response).viewClasses();
		new FeesService(request, response).viewFees();
		return "defaultersreport";
	}

	@GetMapping("/dndReport")
	public String dndReport() {
		new FeesService(request, response).getDndReport();
		return "dndreport";
	}
}
