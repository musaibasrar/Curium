package org.ideoholic.curium.model.marksdetails.action;

import org.ideoholic.curium.model.marksdetails.service.MarksDetailsService;
import org.ideoholic.curium.model.std.action.StandardActionAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/MarksDetailsProcess")
public class MarksDetailsAction {

	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private StandardActionAdapter standardActionAdapter;
	@Autowired
	private MarksDetailsActionAdapter marksDetailsActionAdapter;

	@GetMapping("/marksEntry")
	public String marksEntry() {
		standardActionAdapter.viewClasses();
		return "marksentry";
	}

	@PostMapping("/downloadReportCard")
	public String downloadReportCard() {
		new MarksDetailsService(request, response).downloadReportCard();
		return "reportcardsaved";

	}

	//@GetMapping("/progressReport")
	@RequestMapping(value = "/progressReport", method = { RequestMethod.GET, RequestMethod.POST })
	public String progressreport() {
		standardActionAdapter.viewClasses();
		return "progressreport";
	}

	@PostMapping("/getStudentGraph")
	public String getStudentGraph() {
		marksDetailsActionAdapter.getStudentGraph();
		return "studentgraph";
	}

	@GetMapping("/getGraphicalReportData")
	public String getGraphicalReportData() {
		marksDetailsActionAdapter.getStudentList();
		standardActionAdapter.viewClasses();
		return "graphicalreport";
	}

	@PostMapping("/searchForReport")
	public String searchForReport() {
		marksDetailsActionAdapter.Search();
		return "progressreport";
	}
	
	@PostMapping("/generateReport")
	public String generateReport() {
		if (new MarksDetailsService(request, response).generateReport()) {
			return "markssheet";
		} else {
			return "error";
		}
	}
	
	@GetMapping("/generateReportParent")
	public String generateReportParent() {
		if (new MarksDetailsService(request, response).generateReportParent()) {
			return "markssheetparent";
		} else {
			return "error";
		}
	}
	
	@PostMapping("/deleteMultiple")
	public String deleteMultiple() {
		if (new MarksDetailsService(request, response).deleteMultiple()) {
			return "markssaved";
		} else {
			return "notSaved";
		}
	}

	@PostMapping("/updateMarks")
	public String updateMarks() {
		if (new MarksDetailsService(request, response).updateMarks()) {
			return "markssaved";
		} else {
			return "error";
		}
	}

	@GetMapping("/getSubjectsExams")
	public String getSubjectsExams() {
		new MarksDetailsService(request, response).getSubjectExams();
		standardActionAdapter.viewClasses();
		return "markssearch";
	}

	@PostMapping("/viewMarks")
	public String viewMarks() {
		if (new MarksDetailsService(request, response).viewMarks()) {
			new MarksDetailsService(request, response).getSubjectExams();
			return "markssearch";
		} else {
			return "error";
		}
	}

	@PostMapping("/addMarks")
	public String addMarks() {
		String result = new MarksDetailsService(request, response).addMarks();
		if (result == "true") {
			return "markssaved";
		} else if (result == "Duplicate") {
			return "erroraddingmarks";
		} else {
			return "error";
		}

	}

	@PostMapping("/search")
	public String search() {
		marksDetailsActionAdapter.Search();
		return "marksentry";
	}

	@PostMapping("/searchForGraphicalReport")
	public String searchForGraphicalReport() {
		marksDetailsActionAdapter.Search();
		marksDetailsActionAdapter.getStudentList();
		return "graphicalreport";
	}
	
	@GetMapping("/rankReport")
	public String rankreport() {
		standardActionAdapter.viewClasses();
		return "rankreport";
	}
	
	@PostMapping("/searchForRank")
	public String searchForRank() {
		new MarksDetailsService(request, response).rankSearch();
		return "rankreport";
	}

	@PostMapping("/generateRankReport")
	public String generateRankReport() {
		if (new MarksDetailsService(request, response).generateRankReport()) {
			return "studentRankReport";
		} else {
			return "error";
		}
	}
}
