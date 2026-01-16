package org.ideoholic.curium.model.feesdetails.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.feescategory.action.FeesActionAdapter;
import org.ideoholic.curium.model.feescategory.service.FeesService;
import org.ideoholic.curium.model.feescollection.action.FeesCollectionActionAdapter;
import org.ideoholic.curium.model.feesdetails.service.FeesDetailsService;
import org.ideoholic.curium.model.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/FeesDetails")
public class FeesDetailsAction {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpServletResponse response;
	
	@Autowired
	HttpSession httpSession;
	
	@Autowired
	private FeesService feesService;
	
	@Autowired
	private FeesActionAdapter feesActionAdapter;
	
	@Autowired
	private FeesDetailsActionAdapter feesDetailsActionAdapter;
	
	@Autowired
	private FeesCollectionActionAdapter feesCollectionActionAdapter;


	@PostMapping("/download")
	public String downloadFile() {
		if(feesActionAdapter.downlaodFile()){
			return "feesexportsuccess";
		}
        return "exportfailure";
	}

	@PostMapping("/exportDataForFees")
	public String exportFeesData() {
		
		if(feesDetailsActionAdapter.exportDataForFees()){
			return "feesexportsuccess";
		}else{
			return "exportfailure";
		}
		
	}
	
	@PostMapping("/exportDataForOtherFees")
	public String exportOtherFeesData() {
		
		if(feesDetailsActionAdapter.exportDataForOtherFees()){
			return "feesexportsuccess";
		}else{
			return "exportfailure";
		}
		
	}
	
	@PostMapping("/printDataForFees")
	public String printFeesData() {
		
		if(feesDetailsActionAdapter.printDataForFees()){
			return "printfeescollectiondetails";
		}else{
			return "error";
		}
		
	}
	
	@PostMapping("/printDataForFeesReport")
	public String printFeesReportData() {
		
	         feesCollectionActionAdapter.getFeesReport();
			return "printfeesReportdetails";
		
	}
	
	@PostMapping("/printDataForFeesReportdue")
	public String printFeesReportDueData() {
		
		 feesCollectionActionAdapter.getFeesReportDue();
			return "printfeesDueReportdetails";
		
	}
	
}
