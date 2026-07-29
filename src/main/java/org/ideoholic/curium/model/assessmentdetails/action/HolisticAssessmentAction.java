package org.ideoholic.curium.model.assessmentdetails.action;

import org.ideoholic.curium.model.academicyear.action.YearActionAdapter;
import org.ideoholic.curium.model.assessmentsubjectdetails.action.AssessmentSubjectDetailsActionAdapter;
import org.ideoholic.curium.model.std.action.StandardActionAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller for Holistic Development Assessment
 * Handles HTTP requests for assessment management
 * URL Pattern: /HolisticAssessmentProcess/*
 */
@Controller
@RequestMapping({ "/HolisticAssessmentProcess", "/HolisticAssessmentDetailsProcess" })
public class HolisticAssessmentAction {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private YearActionAdapter yearActionAdapter;

    @Autowired
    private StandardActionAdapter standardActionAdapter;

    @Autowired
    private HolisticAssessmentActionAdapter holisticAssessmentActionAdapter;
    
    @Autowired
    private AssessmentSubjectDetailsActionAdapter assessmentSubjectDetailsActionAdapter;
    
    private static final String ERROR = "error";

    /**
     * Display assessment schedule page
     * URL: /HolisticAssessmentProcess/assessmentSchedule
     */
    @GetMapping("/assessmentSchedule")
    public String assessmentSchedule() {

        boolean result;

        result = holisticAssessmentActionAdapter.readListOfAssessments();
        if (!result)
            return ERROR;
        
        result = standardActionAdapter.viewClasses();
        if (!result)
            return ERROR;
        
        assessmentSubjectDetailsActionAdapter.readListOfSubjectNames();
        if (!result)
            return ERROR;
        
        result = yearActionAdapter.getYear();
        if (!result)
            return ERROR;
        
        result = holisticAssessmentActionAdapter.getAssessmentSchedule();
        if (!result)
            return ERROR;

        return "assessmentschedule";
    }

    /**
     * Add new assessment schedule
     * URL: /HolisticAssessmentProcess/addSchedule
     */
    @PostMapping({ "/addSchedule", "/addAssessmentSchedule" })
    public String addSchedule() {

        if (holisticAssessmentActionAdapter.addSchedule()) {
            return assessmentSchedule();
        } else {
            return ERROR;
        }
    }

    /**
     * Delete assessment schedule
     * URL: /HolisticAssessmentProcess/deleteAssessmentSchedule
     */
    @PostMapping({ "/deleteAssessmentSchedule", "/removeAssessmentSchedule" })
    public String deleteAssessmentSchedule() {

        if (holisticAssessmentActionAdapter.deleteAssessmentSchedule()) {
            return assessmentSchedule();
        } else {
            return ERROR;
        }
    }

    /**
     * Display list of assessments
     * URL: /HolisticAssessmentProcess/readListOfAssessments
     */
    @GetMapping("/readListOfAssessments")
    public String readListOfAssessments() {
        if (holisticAssessmentActionAdapter.readListOfAssessments()) {
            return "HolisticAssessmentDetails";
        } else {
            return ERROR;
        }
    }

    /**
     * Add new assessment
     * URL: /HolisticAssessmentProcess/addAssessment
     */
    @PostMapping("/addAssessment")
    public String addAssessment() {

        if (holisticAssessmentActionAdapter.addAssessment()) {
            return readListOfAssessments();
        } else {
            return ERROR;
        }
    }

    /**
     * Delete multiple assessments
     * URL: /HolisticAssessmentProcess/deleteMultiple
     */
    @PostMapping("/deleteMultiple")
    public String deleteMultiple() {
        if (holisticAssessmentActionAdapter.deleteMultiple()) {
            return readListOfAssessments();
        } else {
            return ERROR;
        }
    }
}
