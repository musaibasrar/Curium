package org.ideoholic.curium.model.ratingdetails.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.assessmentdetails.dao.HolisticAssessmentDAO;
import org.ideoholic.curium.model.assessmentdetails.dto.HolisticAssessment;
import org.ideoholic.curium.model.assessmentsubjectdetails.dao.AssessmentSubjectDetailsDAO;
import org.ideoholic.curium.model.assessmentsubjectdetails.dto.AssessmentSubjectMaster;
import org.ideoholic.curium.model.documents.dto.SearchStudentResponseDto;
import org.ideoholic.curium.model.examdetails.dao.ExamDetailsDAO;
import org.ideoholic.curium.model.examdetails.dto.Exams;
import org.ideoholic.curium.model.ratingdetails.dto.RatingDto;
import org.ideoholic.curium.model.ratingdetails.dto.StudentReportCardDto;
import org.ideoholic.curium.model.ratingdetails.dto.RatingUpdateDto;
import org.ideoholic.curium.model.ratingdetails.dto.RatingViewDto;
import org.ideoholic.curium.model.ratingdetails.dto.SearchStudentAssessmentDto;
import org.ideoholic.curium.model.ratingdetails.service.RatingDetailsService;
import org.ideoholic.curium.model.subjectdetails.dao.SubjectDetailsDAO;
import org.ideoholic.curium.model.subjectdetails.dto.Subjectmaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Action Adapter for Holistic Rating Details
 * Handles request/response mapping for grade-based rating entry
 * CRITICAL: Processes grade codes (A+, B+, etc.) from UI
 */
@Service
public class RatingDetailsActionAdapter {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private RatingDetailsService ratingDetailsService;

    private static final String BRANCHID = "branchid";
    private static final String CURRENTACADEMICYEAR = "currentAcademicYear";
    private static final String USERLOGINID = "userloginid";

    private String getFirstNonEmptyParameter(String... names) {
        for (String name : names) {
            String value = request.getParameter(name);
            if (value != null && !value.trim().isEmpty()) {
                return value;
            }
        }
        return null;
    }

    private String[] getFirstAvailableParameterValues(String... names) {
        for (String name : names) {
            String[] values = request.getParameterValues(name);
            if (values != null) {
                return values;
            }
        }
        return null;
    }

    /**
     * Add ratings from grade-based entry
     * Receives grade codes (A+, A, B+, etc.) from UI
     * Service layer converts to numeric values
     */
    public String addRatings() {
        
        RatingUpdateDto dto = new RatingUpdateDto();
        dto.setStudentIds(request.getParameterValues("studentIDs"));
        dto.setStudentsRatings(getFirstAvailableParameterValues("studentsRatings", "studentRatings")); // Grade codes: A+, B+, etc.
        dto.setAssessment(request.getParameter("assessment"));
        dto.setSubject(request.getParameter("subject"));
        dto.setClassSearch(request.getParameter("classsearch"));
        dto.setAcademicYear(request.getParameter("academicyear"));

        ResultResponse result = ratingDetailsService.addRatings(
            dto, 
            httpSession.getAttribute(BRANCHID).toString(),
            httpSession.getAttribute(CURRENTACADEMICYEAR).toString(),
            httpSession.getAttribute(USERLOGINID).toString()
        );

        if (result.isSuccess()) {
            return "true";
        } else if ("Duplicate".equals(result.getMessage())) {
            return "Duplicate";
        }
        return "false";
    }

    /**
     * Search for students to enter ratings
     */
    public void Search() {

        SearchStudentAssessmentDto dto = new SearchStudentAssessmentDto();
        dto.setStudentName(getFirstNonEmptyParameter("namesearch", "studentName"));
        dto.setAddClass(request.getParameter("classsearch"));
        dto.setAddSec(request.getParameter("secsearch"));

        SearchStudentResponseDto result = ratingDetailsService.Search(dto, httpSession.getAttribute(BRANCHID).toString());
        // get all the subjects
     	List<AssessmentSubjectMaster> subjectList = new AssessmentSubjectDetailsDAO().readListOfSubjectNames(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
     	request.setAttribute("listAssessmentSubjectNames", subjectList);

     	// get the list for all the assessments
     	List<HolisticAssessment> assessments = new HolisticAssessmentDAO().readListOfAssessments(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
     	request.setAttribute("listAssessment", assessments);
     	
     	request.setAttribute("classselected", dto.getAddClass());
        httpSession.setAttribute("searchStudentList", result.getSearchStudentList());
        httpSession.setAttribute("classSearch", result.getClassSearch());
    }

    /**
     * View ratings for students
     */
    public boolean viewRatings() {

        RatingViewDto dto = new RatingViewDto();
        dto.setStudentName(getFirstNonEmptyParameter("namesearch", "studentName"));
        dto.setAddClass(request.getParameter("classsearch"));
        dto.setAddSec(request.getParameter("secsearch"));
        dto.setAssessment(request.getParameter("assessment"));
        dto.setSubject(request.getParameter("subject"));
        dto.setSubjectSelected(request.getParameter("subjectselected"));
        dto.setAssessmentSelected(request.getParameter("assessmentselected"));
        dto.setAcademicYear(request.getParameter("academicyear"));

        RatingDto result = ratingDetailsService.viewRatings(dto, httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("newStudentList", result.getNewStudentList());
        request.setAttribute("newRatingsDetails", result.getNewRatingsList());
        request.setAttribute("subjectselected", result.getSubjectSelected());
        request.setAttribute("assessmentselected", result.getAssessmentSelected());
        request.setAttribute("subjectid", result.getSubject());
        request.setAttribute("examidname", result.getAssessment());
        request.setAttribute("classselected", dto.getAddClass());
        request.setAttribute("sectionselected", dto.getAddSec());
        request.setAttribute("studentname", dto.getStudentName());
        httpSession.setAttribute("ratingList", result.getRatingList());
        httpSession.setAttribute("studentsIds", result.getStudentsIds());
        
        return result.isSuccess();
    }

    /**
     * Update ratings with grade-based entry
     * Receives updated grade codes from UI
     */
    public boolean updateRatings() {

        RatingUpdateDto dto = new RatingUpdateDto();
        dto.setRatingId(getFirstAvailableParameterValues("ratingid", "ratingsid"));
        dto.setStudentsRatings(getFirstAvailableParameterValues("studentsRatings", "studentRatings")); // Updated grade codes

        ResultResponse result = ratingDetailsService.updateRatings(
            dto,
            httpSession.getAttribute(CURRENTACADEMICYEAR).toString(),
            httpSession.getAttribute(BRANCHID).toString()
        );

        return result.isSuccess();
    }

    /**
     * Delete multiple ratings
     */
    public boolean deleteMultiple() {

        RatingUpdateDto dto = new RatingUpdateDto();
        dto.setRatingId(request.getParameterValues("ratingIDs"));

        ResultResponse result = ratingDetailsService.deleteMultiple(dto);

        return result.isSuccess();
    }

    /**
     * Generate assessment report for student
     */
    public void getStartDate() {
        String startDate = request.getParameter("startdate");
        httpSession.setAttribute("startdate", startDate);
    }

    /**
     * Generate server-rendered assessment progress report data for selected students.
     */
    public boolean generateProgressReport() {
        try {
            String[] studentIds = request.getParameterValues("studentIDs");
            if (studentIds == null || studentIds.length == 0) {
                request.setAttribute("progressReportError", "Please select at least one student");
                return false;
            }

            String assessmentName = getFirstNonEmptyParameter("assessmentName", "assessmentname");
            if (assessmentName == null) {
                assessmentName = "Assessment Progress Report";
            }

            List<StudentReportCardDto> reportCards = ratingDetailsService.buildAssessmentProgressReports(
                    studentIds,
                    assessmentName,
                    httpSession.getAttribute(CURRENTACADEMICYEAR).toString(),
                    httpSession.getAttribute(BRANCHID).toString());

            request.setAttribute("studentReportCards", reportCards);
            request.setAttribute("assessmentReportTitle", assessmentName);
            request.setAttribute("reportStartDate", request.getParameter("startdate"));
            request.setAttribute("reportEndDate", request.getParameter("enddate"));
            return !reportCards.isEmpty();
        } catch (Exception e) {
            request.setAttribute("progressReportError", "Unable to generate progress report");
            return false;
        }
    }
}
