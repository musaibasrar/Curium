package org.ideoholic.curium.model.marksdetails.action;

import org.ideoholic.curium.model.std.action.StandardActionAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/MarksDetailsProcess")
public class MarksDetailsAction {

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
		marksDetailsActionAdapter.downloadReportCard();
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
		if (marksDetailsActionAdapter.generateReport()) {
			return "markssheet";
		} else {
			return "error";
		}
	}
	
	@GetMapping("/generateReportParent")
	public String generateReportParent() {
		if (marksDetailsActionAdapter.generateReportParent()) {
			return "markssheetparent";
		} else {
			return "error";
		}
	}
	
	@PostMapping("/deleteMultiple")
	public String deleteMultiple() {
		if (marksDetailsActionAdapter.deleteMultiple()) {
			return "markssaved";
		} else {
			return "notSaved";
		}
	}

	@PostMapping("/updateMarks")
	public String updateMarks() {
		if (marksDetailsActionAdapter.updateMarks()) {
			return "markssaved";
		} else {
			return "error";
		}
	}

	@GetMapping("/getSubjectsExams")
	public String getSubjectsExams() {
		marksDetailsActionAdapter.getSubjectExams();
		standardActionAdapter.viewClasses();
		return "markssearch";
	}

	@PostMapping("/viewMarks")
	public String viewMarks() {
		if (marksDetailsActionAdapter.viewMarks()) {
			marksDetailsActionAdapter.getSubjectExams();
			return "markssearch";
		} else {
			return "error";
		}
	}

	@PostMapping("/addMarks")
	public String addMarks() {
		String result = marksDetailsActionAdapter.addMarks();
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
		marksDetailsActionAdapter.rankSearch();
		return "rankreport";
	}

	@PostMapping("/generateRankReport")
	public String generateRankReport() {
		if (marksDetailsActionAdapter.generateRankReport()) {
			return "studentRankReport";
		} else {
			return "error";
		}
	}
	
	/*@GetMapping("/prePrimaryProgressReport")
	public String prePrimaryProgressReport() {
		new StandardService(request, response).viewClasses();
		return "preprimaryprogressreport";
	}
	
	@PostMapping("/searchForPreprimaryReport")
	public String searchForPreprimaryReport() {
		new MarksDetailsService(request, response).Search();
		return "preprimaryprogressreport";
	}
	
	@PostMapping("/generatePreprimaryReport")
	public String generatePreprimaryReport() {
		if (new MarksDetailsService(request, response).generatePreprimaryReport()) {
			return "preprimarymarkssheet";
			// return "reportcardsaved";
		} else {
			return "error";
		}
	}*/
	
	@RequestMapping(value = "/progressReportSingleExams", method = { RequestMethod.GET, RequestMethod.POST })
	public String progressReportSingleExams() {
		standardActionAdapter.viewClasses();
		return "progressreportsingleexams";
	}
	
	
	@PostMapping("/generateReportSingleExams")
	public String generateReportSingleExams() {
		if (marksDetailsActionAdapter.generateReportSingleExams()) {
			return "markssheetsingleexams";
		} else {
			return "error";
		}
	}
	
	@PostMapping("/searchForReportSingleExams")
	public String searchForReportSingleExams() {
		marksDetailsActionAdapter.Search();
		return "progressreportsingleexams";
	}
	
}
