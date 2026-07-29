package org.ideoholic.curium.model.assessmentsubjectdetails.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ideoholic.curium.model.assessmentdetails.action.HolisticAssessmentActionAdapter;
import org.ideoholic.curium.model.std.action.StandardActionAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for Assessment Subject Details
 * Handles HTTP requests for assessment subject management
 * URL Pattern: /AssessmentSubjectDetailsProcess/*
 */
@Controller
@RequestMapping("/AssessmentSubjectDetailsProcess")
public class AssessmentSubjectDetailsAction {

	@Autowired
	private StandardActionAdapter standardActionAdapter;
	
	@Autowired
	private HolisticAssessmentActionAdapter holisticAssessmentActionAdapter;
	
    @Autowired
	private AssessmentSubjectDetailsActionAdapter assessmentSubjectDetailsActionAdapter;
	
	private static final String ERROR = "error";
	
	/**
	 * Delete multiple subject masters
	 * URL: /AssessmentSubjectDetailsProcess/deleteMultipleSubjects
	 */
	@PostMapping("/deleteMultipleAssessmentSubjects")
	public String deleteMultipleSubjects() {
		if(assessmentSubjectDetailsActionAdapter.deleteMultipleSubjectMaster()){
			return readListOfSubjectNames();
		}else{
			return ERROR;
		}
	}

	/**
	 * Display list of subject masters
	 * URL: /AssessmentSubjectDetailsProcess/readListOfSubjectNames
	 */
	@GetMapping("/readListOfSubjectNames")
	public String readListOfSubjectNames() {
		assessmentSubjectDetailsActionAdapter.readListOfSubjectNames();
        return "AssessmentSubjectMaster";
	}

	/**
	 * Add new subject master
	 * URL: /AssessmentSubjectDetailsProcess
	 */
	@PostMapping({ "/addAssessmentSubjectMaster"})
	public String addSubjectMaster() {
		if(assessmentSubjectDetailsActionAdapter.addAssessmentSubjectMaster()){
			return readListOfSubjectNames();
		}else{
			return ERROR;
		}
	}

	/**
	 * Delete multiple assessment subjects
	 * URL: /AssessmentSubjectDetailsProcess/deleteMultiple
	 */
	@PostMapping({ "/deleteMultipleAssessmentSubjectDetails" })
	public String deleteMultiple() {
		if(assessmentSubjectDetailsActionAdapter.deleteMultiple()){
			return readListOfAssessmentSubjects();
		}else{
			return ERROR;
		}
	}

	/**
	 * Add new assessment subject
	 * URL: /AssessmentSubjectDetailsProcess/addSubject
	 */
	@PostMapping({ "/addAssessmentSubject" })
	public String addSubject() {
		if(assessmentSubjectDetailsActionAdapter.addAssessmentSubject()){
			return readListOfAssessmentSubjects();
		}else{
			return ERROR;
		}
	}

	/**
	 * Display list of assessment subjects
	 * URL: /AssessmentSubjectDetailsProcess/readListOfSubjects
	 */
	@GetMapping("/readListOfSubjects")
	public String readListOfAssessmentSubjects() {
		assessmentSubjectDetailsActionAdapter.readListOfAssessmentSubjects();
		assessmentSubjectDetailsActionAdapter.readListOfSubjectNames();
		holisticAssessmentActionAdapter.readListOfAssessments();
		standardActionAdapter.viewClasses();
        return "AssessmentSubjectDetails";
	}
}
