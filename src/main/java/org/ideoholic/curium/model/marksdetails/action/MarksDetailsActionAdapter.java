package org.ideoholic.curium.model.marksdetails.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.documents.dto.SearchStudentResponseDto;
import org.ideoholic.curium.model.marksdetails.dto.*;
import org.ideoholic.curium.model.marksdetails.service.MarksDetailsService;
import org.ideoholic.curium.model.student.dto.StudentIdsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
public class MarksDetailsActionAdapter {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private MarksDetailsService marksDetailsService;

    private String CURRENTACADEMICYEAR = "currentAcademicYear";
    private String BRANCHID = "branchid";
    private String USERID = "userloginid";

    public void getStudentGraph() {

        StudentGraphDto dto = new StudentGraphDto();
        dto.setStudentIds(request.getParameterValues("studentIDs"));
        dto.setExamClass(request.getParameterValues("examclass"));

        StudentGraphResponseDto responseDto = marksDetailsService.getStudentGraph(dto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
        request.setAttribute("examDetailsGraph", responseDto.getExamDetailsGraph());
        request.setAttribute("examDetailsGraphSize", responseDto.getExamDetailsGraphSize());
        request.setAttribute("studentName", responseDto.getSearchStudent());
    }

    public void getStudentList() {

        ResultResponse resultResponse = marksDetailsService.getStudentList(httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("studentList", resultResponse.getResultList());
    }

    public void Search() {

        SearchStudentExamDto dto = new SearchStudentExamDto();
        dto.setStudentName(request.getParameter("namesearch"));
        dto.setAddClass(request.getParameter("classsearch"));
        dto.setAddSec(request.getParameter("secsearch"));

        SearchStudentResponseDto responseDto = marksDetailsService.Search(dto, httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("searchStudentList", responseDto.getSearchStudentList());
        request.setAttribute("listExam", responseDto.getExamsList());
    }

    public boolean generateReport() {

        GenerateReportDto dto = new GenerateReportDto();
        dto.setStudentIds(request.getParameterValues("studentIDs"));
        dto.setExamClass(request.getParameter("examclass"));

        GenerateReportResponseDto responseDto = marksDetailsService.generateReport(dto, httpSession.getAttribute(CURRENTACADEMICYEAR).toString(), httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("endloop", responseDto.getEndLoop());
        request.setAttribute("markssheetlist", responseDto.getMarksSheetList());

        return responseDto.isSuccess();
    }

    public boolean generateReportParent() {

        GenerateReportDto dto = new GenerateReportDto();
        dto.setStudentUID(request.getParameter("id"));
        dto.setStudentIds(request.getParameterValues("studentIDs"));

        GenerateReportResponseDto responseDto = marksDetailsService.generateReportParent(dto, httpSession.getAttribute(CURRENTACADEMICYEAR).toString(), httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("endloop", responseDto.getEndLoop());
        request.setAttribute("markssheetlist", responseDto.getMarksSheetList());

        return responseDto.isSuccess();
    }

    public boolean deleteMultiple() {

        GenerateReportDto dto = new GenerateReportDto();
        dto.setStudentIds(request.getParameterValues("studentIDs"));
        dto.setMarksIds(request.getParameterValues("marksid"));

        ResultResponse resultResponse = marksDetailsService.deleteMultiple(dto);

        return resultResponse.isSuccess();
    }

    public boolean updateMarks() {

        MarksUpdateDto dto = new MarksUpdateDto();
        dto.setStudentIds(request.getParameterValues("studentIDs"));
        dto.setStudentsMarks(request.getParameterValues("studentMarks"));
        dto.setMarksId(request.getParameterValues("marksid"));
        dto.setExam(request.getParameter("examidselected"));
        dto.setSubject(request.getParameter("subjectidselected"));

        ResultResponse resultResponse = marksDetailsService.updateMarks(dto, httpSession.getAttribute(CURRENTACADEMICYEAR).toString(), httpSession.getAttribute(BRANCHID).toString());

        return resultResponse.isSuccess();
    }

    public void getSubjectExams() {

        SearchStudentResponseDto responseDto = marksDetailsService.getSubjectExams(httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("listSubject", responseDto.getSubjectList());
        request.setAttribute("listExam", responseDto.getExamsList());
    }

    public boolean viewMarks() {

        MarksViewDto dto = new MarksViewDto();
        dto.setStudentName(request.getParameter("namesearch"));
        dto.setAddClass(request.getParameter("classsearch"));
        dto.setAddSec(request.getParameter("secsearch"));
        dto.setExam(request.getParameter("exam"));
        dto.setSubject(request.getParameter("subject"));
        dto.setSubjectSelected(request.getParameter("subjectselected"));
        dto.setExamSelected(request.getParameter("examselected"));

        MarksResponseDto responseDto = marksDetailsService.viewMarks(dto, httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("newStudentList", responseDto.getNewStudentList());
        request.setAttribute("newMarksDetails", responseDto.getNewMarksDetails());
        request.setAttribute("subjectselected", responseDto.getSubjectSelected());
        request.setAttribute("examselected", responseDto.getExamSelected());
        request.setAttribute("subjectid", responseDto.getSubject());
        request.setAttribute("examid", responseDto.getExam());

        return responseDto.isSuccess();

    }

    public void downloadReportCard() {

        ResultResponse resultResponse = marksDetailsService.downloadReportCard();
    }

    public String addMarks() {

        MarksUpdateDto dto = new MarksUpdateDto();
        dto.setStudentIds(request.getParameterValues("studentIDs"));
        dto.setStudentsMarks(request.getParameterValues("studentMarks"));
        dto.setExam(request.getParameter("exam"));
        dto.setSubject(request.getParameter("subject"));

        ResultResponse resultResponse = marksDetailsService.addMarks(dto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(CURRENTACADEMICYEAR).toString(), httpSession.getAttribute(USERID).toString());

        return resultResponse.getMessage();
    }

    public void rankSearch() {

        SearchStudentExamDto dto = new SearchStudentExamDto();
        dto.setAddClass(request.getParameter("classsearch"));
        dto.setAddSec(request.getParameter("secsearch"));

        SearchStudentResponseDto responseDto = marksDetailsService.rankSearch(dto, httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("searchStudentList", responseDto.getSearchStudentList());
        request.setAttribute("listExam", responseDto.getExamsList());
    }

    public boolean generateRankReport() {

        GenerateReportDto dto = new GenerateReportDto();
        dto.setStudentIds(request.getParameterValues("studentIDs"));
        dto.setExamClass(request.getParameter("examclass"));

        GenerateReportResponseDto responseDto = marksDetailsService.generateRankReport(dto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(CURRENTACADEMICYEAR).toString(), httpSession.getAttribute(USERID).toString());
        request.setAttribute("endloop", responseDto.getEndLoop());
        request.setAttribute("markssheetlist", responseDto.getMarksSheetList());

        return responseDto.isSuccess();
    }
    
    public boolean generateReportSingleExams() {

        GenerateReportDto dto = new GenerateReportDto();
        dto.setStudentIds(request.getParameterValues("studentIDs"));
        dto.setExamClass(request.getParameter("examclass"));

        GenerateReportResponseDto responseDto = marksDetailsService.generateReportSingleExams(dto, httpSession.getAttribute(CURRENTACADEMICYEAR).toString(), httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("endloop", responseDto.getEndLoop());
        request.setAttribute("markssheetlist", responseDto.getMarksSheetList());

        return responseDto.isSuccess();
    }
}
