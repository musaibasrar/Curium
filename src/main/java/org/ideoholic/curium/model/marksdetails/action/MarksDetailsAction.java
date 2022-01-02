package org.ideoholic.curium.model.marksdetails.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ideoholic.curium.model.marksdetails.service.MarksDetailsService;
import org.ideoholic.curium.model.std.service.StandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/MarksDetailsProcess")
public class MarksDetailsAction {

	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;

	@GetMapping("/marksEntry")
	public String marksEntry() {
		new StandardService(request, response).viewClasses();
		return "marksentry";
	}

	@PostMapping("/downloadReportCard")
	public String downloadReportCard() {
		new MarksDetailsService(request, response).downloadReportCard();
		return "reportcardsaved";

	}

	@GetMapping("/progressReport")
	public String progressreport() {
		new StandardService(request, response).viewClasses();
		return "progressreport";
	}

	@PostMapping("/getStudentGraph")
	public String getStudentGraph() {
		new MarksDetailsService(request, response).getStudentGraph();
		return "studentgraph";
	}

	@GetMapping("/getGraphicalReportData")
	public String getGraphicalReportData() {
		new MarksDetailsService(request, response).getStudentList();
		new StandardService(request, response).viewClasses();
		return "graphicalreport";
	}

	@PostMapping("/searchForReport")
	public String searchForReport() {
		new MarksDetailsService(request, response).Search();
		return "progressreport";
	}

	@PostMapping("/generateReport")
	public String generateReport() {
		if (new MarksDetailsService(request, response).generateReport()) {
			return "markssheet";
			// return "reportcardsaved";
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
		new StandardService(request, response).viewClasses();
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
		new MarksDetailsService(request, response).Search();
		return "marksentry";
	}

	@PostMapping("/searchForGraphicalReport")
	public String searchForGraphicalReport() {
		new MarksDetailsService(request, response).Search();
		new MarksDetailsService(request, response).getStudentList();
		return "graphicalreport";
	}

}
