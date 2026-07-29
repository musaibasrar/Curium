package org.ideoholic.curium.model.ratingdetails.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.assessmentdetails.action.HolisticAssessmentActionAdapter;
import org.ideoholic.curium.model.assessmentsubjectdetails.action.AssessmentSubjectDetailsActionAdapter;
import org.ideoholic.curium.model.std.action.StandardActionAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for Holistic Rating Details
 * Handles HTTP requests for grade-based rating entry and management
 * URL Pattern: /RatingDetailsProcess/*
 * 
 * CRITICAL: This controller handles grade-based rating entry (A+, B+, etc.)
 */
@Controller
@RequestMapping("/RatingDetailsProcess")
public class RatingDetailsAction {

	@Autowired
    HttpServletRequest request;
    
	@Autowired
	HttpServletResponse response;
    
	@Autowired
	HttpSession httpSession;

	@Autowired
	private StandardActionAdapter standardActionAdapter;
	
	@Autowired
	private RatingDetailsActionAdapter ratingDetailsActionAdapter;
	
	@Autowired
	HolisticAssessmentActionAdapter holisticAssessmentActionAdapter;
	
	@Autowired
	AssessmentSubjectDetailsActionAdapter assessmentSubjectDetailsActionAdapter;

	private static final String ERROR = "error";

	/**
	 * Display rating entry page with grade selection
	 * URL: /RatingDetailsProcess/ratingEntry
	 * 
	 * Determines which classes to show based on user role
	 */
	@GetMapping("/ratingEntry")
	public String ratingEntry() {
		
		String userType = httpSession.getAttribute("userType").toString();

		if ("superadmin".equalsIgnoreCase(userType) || 
		    "admin".equalsIgnoreCase(userType) || 
		    "officeadmin".equalsIgnoreCase(userType) || 
		    "principal".equalsIgnoreCase(userType)) {
			standardActionAdapter.viewClasses();
		} else if ("teacher".equalsIgnoreCase(userType)) {
			standardActionAdapter.viewClassesForTeacherMarksEntry();
		} else {
			standardActionAdapter.viewClasses();
		}
		
		return "ratingentry";
	}

	/**
	 * Add ratings from grade-based entry
	 * URL: /RatingDetailsProcess/addRatings
	 * 
	 * Receives grade codes (A+, A, B+, B, C, D, F) from UI
	 * Service layer converts grades to numeric values
	 */
	@PostMapping("/addRatings")
	public String addRatings() {
		String result = ratingDetailsActionAdapter.addRatings();
		if ("true".equals(result)) {
			return "ratingsaved";
		} else if ("Duplicate".equals(result)) {
			return "erroraddingratings";
		} else {
			return ERROR;
		}
	}

	/**
	 * Search for students to enter ratings
	 * URL: /RatingDetailsProcess/search
	 * 
	 * Filters based on user role (teacher vs admin)
	 */
	@PostMapping("/search")
	public String search() {
		
		String userType = httpSession.getAttribute("userType").toString();
		
		if ("superadmin".equalsIgnoreCase(userType) || 
		    "admin".equalsIgnoreCase(userType) || 
		    "officeadmin".equalsIgnoreCase(userType) || 
		    "principal".equalsIgnoreCase(userType)) {
			ratingDetailsActionAdapter.Search();
		} else if ("teacher".equalsIgnoreCase(userType)) {
			// Teachers see only their assigned classes
			ratingDetailsActionAdapter.Search();
		} else {
			ratingDetailsActionAdapter.Search();
		}
		
		return "ratingentry";
	}

	/**
	 * View existing ratings
	 * URL: /RatingDetailsProcess/viewRatings
	 */
	@PostMapping("/viewRatings")
	public String viewRatings() {
		if (ratingDetailsActionAdapter.viewRatings()) {
			return "ratingsearch";
		} else {
			return ERROR;
		}
	}

	
	@GetMapping("/getSubjectsAssessments")
	public String getSubjectsAssessments() {
		assessmentSubjectDetailsActionAdapter.readListOfSubjectNames();
		holisticAssessmentActionAdapter.readListOfAssessments();
		standardActionAdapter.viewClasses();
		return "ratingsearch";
	}
	/**
	 * Update ratings with new grades
	 * URL: /RatingDetailsProcess/updateRatings
	 * 
	 * Receives updated grade codes from UI
	 */
	@PostMapping("/updateRatings")
	public String updateRatings() {
		if (ratingDetailsActionAdapter.updateRatings()) {
			return "ratingsaved";
		} else {
			return ERROR;
		}
	}

	/**
	 * Delete multiple ratings
	 * URL: /RatingDetailsProcess/deleteMultiple
	 */
	@PostMapping("/deleteMultiple")
	public String deleteMultiple() {
		if (ratingDetailsActionAdapter.deleteMultiple()) {
			return "ratingsaved";
		} else {
			return "notSaved";
		}
	}

	/**
	 * Display assessment progress report page
	 * URL: /RatingDetailsProcess/progressReport
	 */
	@RequestMapping(value = "/progressReport", method = { RequestMethod.GET, RequestMethod.POST })
	public String progressReport() {
		standardActionAdapter.viewClasses();
		return "assessmentprogressreport";
	}

	/**
	 * Search for progress report
	 * URL: /RatingDetailsProcess/searchForReport
	 */
	@PostMapping("/searchForReport")
	public String searchForReport() {
		ratingDetailsActionAdapter.Search();
		ratingDetailsActionAdapter.getStartDate();
		return "assessmentprogressreport";
	}

	/**
	 * Generate full assessment progress report for selected students.
	 * URL: /RatingDetailsProcess/generateProgressReport
	 */
	@PostMapping("/generateProgressReport")
	public String generateProgressReport() {
		ratingDetailsActionAdapter.generateProgressReport();
		return "assessmentprogressreportprint";
	}

	/**
	 * Display rank report page
	 * URL: /RatingDetailsProcess/rankReport
	 */
	@GetMapping("/rankReport")
	public String rankReport() {
		standardActionAdapter.viewClasses();
		holisticAssessmentActionAdapter.readListOfAssessments();
		return "assessmentrankreport";
	}

	/**
	 * Search for rank report
	 * URL: /RatingDetailsProcess/searchForRank
	 */
	@PostMapping("/searchForRank")
	public String searchForRank() {
		ratingDetailsActionAdapter.Search();
		return "assessmentrankreport";
	}
}
