package org.ideoholic.curium.model.marksdetails.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.examdetails.action.ExamDetailsActionAdapter;
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
    HttpServletRequest request;
    
	@Autowired
	HttpServletResponse response;
    
	@Autowired
	HttpSession httpSession;

	
	@Autowired
	private StandardActionAdapter standardActionAdapter;
	@Autowired
	private MarksDetailsActionAdapter marksDetailsActionAdapter;
	@Autowired
	ExamDetailsActionAdapter examDetailsActionAdapter;

	@GetMapping("/marksEntry")
	public String marksEntry() {
		

		if (httpSession.getAttribute("userType").toString().equalsIgnoreCase("superadmin")) {
			standardActionAdapter.viewClasses();
		} else if (httpSession.getAttribute("userType").toString().equalsIgnoreCase("admin")) {
			standardActionAdapter.viewClasses();
		} else if (httpSession.getAttribute("userType").toString().equalsIgnoreCase("officeadmin")) {
			standardActionAdapter.viewClasses();
		} else if (httpSession.getAttribute("userType").toString().equalsIgnoreCase("principal")) {
			standardActionAdapter.viewClasses();
		}  else if (httpSession.getAttribute("userType").toString().equalsIgnoreCase("teacher")) {
			standardActionAdapter.viewClassesForTeacherMarksEntry();
		} else if (!httpSession.getAttribute("userType").toString().equalsIgnoreCase("admin")) {
			standardActionAdapter.viewClasses();
		} else {
			standardActionAdapter.viewClasses();
		}
		
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
		
		if (httpSession.getAttribute("userType").toString().equalsIgnoreCase("superadmin")) {
			marksDetailsActionAdapter.Search();
		} else if (httpSession.getAttribute("userType").toString().equalsIgnoreCase("admin")) {
			marksDetailsActionAdapter.Search();
		} else if (httpSession.getAttribute("userType").toString().equalsIgnoreCase("officeadmin")) {
			marksDetailsActionAdapter.Search();
		} else if (httpSession.getAttribute("userType").toString().equalsIgnoreCase("principal")) {
			marksDetailsActionAdapter.Search();
		}  else if (httpSession.getAttribute("userType").toString().equalsIgnoreCase("teacher")) {
			marksDetailsActionAdapter.SearchForTeacher();
		} else if (!httpSession.getAttribute("userType").toString().equalsIgnoreCase("admin")) {
			marksDetailsActionAdapter.Search();
		} else {
			marksDetailsActionAdapter.Search();
		}
		
		
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
		examDetailsActionAdapter.readListOfExams();
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
	
	@PostMapping("/generateReportSingleExamsVertical")
	public String generateReportSingleExamsVertical() {
		if (marksDetailsActionAdapter.generateReportSingleExams()) {
			return "markssheetsingleexamsvertical";
		} else {
			return "error";
		}
	}
	
	@PostMapping("/addMarksSubSubject")
	public String addMarksSubSubject() {
		String result = marksDetailsActionAdapter.addMarksSubSubject();
		if (result == "true") {
			return "markssaved";
		} else if (result == "Duplicate") {
			return "erroraddingmarks";
		} else {
			return "error";
		}

	}
	
	@GetMapping("/marksEntrySub")
	public String marksEntrySub() {
		

		if (httpSession.getAttribute("userType").toString().equalsIgnoreCase("superadmin")) {
			standardActionAdapter.viewClasses();
		} else if (httpSession.getAttribute("userType").toString().equalsIgnoreCase("admin")) {
			standardActionAdapter.viewClasses();
		} else if (httpSession.getAttribute("userType").toString().equalsIgnoreCase("officeadmin")) {
			standardActionAdapter.viewClasses();
		} else if (httpSession.getAttribute("userType").toString().equalsIgnoreCase("principal")) {
			standardActionAdapter.viewClasses();
		}  else if (httpSession.getAttribute("userType").toString().equalsIgnoreCase("teacher")) {
			standardActionAdapter.viewClassesForTeacherMarksEntry();
		} else if (!httpSession.getAttribute("userType").toString().equalsIgnoreCase("admin")) {
			standardActionAdapter.viewClasses();
		} else {
			standardActionAdapter.viewClasses();
		}
		
		return "marksentrysub";
	}
	
	@PostMapping("/searchForMarksSub")
	public String searchForMarksSub() {
		
		if (httpSession.getAttribute("userType").toString().equalsIgnoreCase("superadmin")) {
			marksDetailsActionAdapter.Search();
		} else if (httpSession.getAttribute("userType").toString().equalsIgnoreCase("admin")) {
			marksDetailsActionAdapter.Search();
		} else if (httpSession.getAttribute("userType").toString().equalsIgnoreCase("officeadmin")) {
			marksDetailsActionAdapter.Search();
		} else if (httpSession.getAttribute("userType").toString().equalsIgnoreCase("principal")) {
			marksDetailsActionAdapter.Search();
		}  else if (httpSession.getAttribute("userType").toString().equalsIgnoreCase("teacher")) {
			marksDetailsActionAdapter.SearchForTeacher();
		} else if (!httpSession.getAttribute("userType").toString().equalsIgnoreCase("admin")) {
			marksDetailsActionAdapter.Search();
		} else {
			marksDetailsActionAdapter.Search();
		}
		
		
		return "marksentrysub";
	}
	
	
	@GetMapping("/getSubjectsExamsSub")
	public String getSubjectsExamsSub() {
		marksDetailsActionAdapter.getSubjectExams();
		standardActionAdapter.viewClasses();
		return "markssearchsub";
	}
	
	@PostMapping("/viewMarksSub")
	public String viewMarksSub() {
		if (marksDetailsActionAdapter.viewMarksSub()) {
			marksDetailsActionAdapter.getSubjectExams();
			return "markssearchsub";
		} else {
			return "error";
		}
	}
	
	@PostMapping("/updateMarksSub")
	public String updateMarksSub() {
		if (marksDetailsActionAdapter.updateMarksSub()) {
			return "markssaved";
		} else {
			return "error";
		}
	}
	
}
