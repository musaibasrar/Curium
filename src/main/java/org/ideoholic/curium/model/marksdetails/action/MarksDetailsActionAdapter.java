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

    private String CURRENTACADEMICYEAR = "currentAcademicYear";
    private String BRANCHID = "branchid";
    private String USERID = "userloginid";

    public void getStudentGraph() {
        MarksDetailsService marksDetailsService = new MarksDetailsService(request, response);

        StudentIdsDto dto = new StudentIdsDto();
        dto.setStudentIds(request.getParameterValues("studentIDs"));
        dto.setExamClass(request.getParameterValues("examclass"));

        StudentGraphResponseDto responseDto = marksDetailsService.getStudentGraph(dto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
        request.setAttribute("examDetailsGraph", responseDto.getExamDetailsGraph());
        request.setAttribute("examDetailsGraphSize", responseDto.getExamDetailsGraphSize());
        request.setAttribute("studentName", responseDto.getSearchStudent());
    }

    public void getStudentList() {
        MarksDetailsService marksDetailsService = new MarksDetailsService(request, response);

        ResultResponse resultResponse = marksDetailsService.getStudentList(httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("studentList", resultResponse.getResultList());
    }

    public void Search() {
        MarksDetailsService marksDetailsService = new MarksDetailsService(request, response);

        SearchStudentExamDto dto = new SearchStudentExamDto();
        dto.setStudentName(request.getParameter("namesearch"));
        dto.setAddClass(request.getParameter("classsearch"));
        dto.setAddSec(request.getParameter("secsearch"));

        SearchStudentResponseDto responseDto = marksDetailsService.Search(dto, httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("searchStudentList", responseDto.getSearchStudentList());
        request.setAttribute("listExam", responseDto.getExamsList());
    }

    public boolean generateReport() {
        MarksDetailsService marksDetailsService = new MarksDetailsService(request, response);

        GenerateReportDto dto = new GenerateReportDto();
        dto.setStudentIds(request.getParameterValues("studentIDs"));
        dto.setExamClass(request.getParameter("examclass"));

        GenerateReportResponseDto responseDto = marksDetailsService.generateReport(dto, httpSession.getAttribute(CURRENTACADEMICYEAR).toString(), httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("endloop", responseDto.getEndLoop());
        request.setAttribute("markssheetlist", responseDto.getMarksSheetList());

        return responseDto.isSuccess();
    }

    public boolean generateReportParent() {
        MarksDetailsService marksDetailsService = new MarksDetailsService(request, response);

        GenerateReportDto dto = new GenerateReportDto();
        dto.setStudentUID(request.getParameter("id"));
        dto.setStudentIds(request.getParameterValues("studentIDs"));

        GenerateReportResponseDto responseDto = marksDetailsService.generateReportParent(dto, httpSession.getAttribute(CURRENTACADEMICYEAR).toString(), httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("endloop", responseDto.getEndLoop());
        request.setAttribute("markssheetlist", responseDto.getMarksSheetList());

        return responseDto.isSuccess();
    }

    public boolean deleteMultiple() {
        MarksDetailsService marksDetailsService = new MarksDetailsService(request, response);

        GenerateReportDto dto = new GenerateReportDto();
        dto.setStudentIds(request.getParameterValues("studentIDs"));
        dto.setMarksIds(request.getParameterValues("marksid"));

        ResultResponse resultResponse = marksDetailsService.deleteMultiple(dto);

        return resultResponse.isSuccess();
    }

    public boolean updateMarks() {
        MarksDetailsService marksDetailsService = new MarksDetailsService(request, response);

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
        MarksDetailsService marksDetailsService = new MarksDetailsService(request, response);

        SearchStudentResponseDto responseDto = marksDetailsService.getSubjectExams(httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("listSubject", responseDto.getSubjectList());
        request.setAttribute("listExam", responseDto.getExamsList());
    }

    public boolean viewMarks() {
        MarksDetailsService marksDetailsService = new MarksDetailsService(request, response);

        MarksViewDto dto = new MarksViewDto();
        dto.setStudentName(request.getParameter("namesearch"));
        dto.setAddClass(request.getParameter("classsearch"));
        dto.setAddSec(request.getParameter("secsearch"));
        dto.setExam(request.getParameter("exam"));
        dto.setSubject(request.getParameter("subject"));
        dto.setSubjectSelected(request.getParameter("subjectselected"));
        dto.setExamSelected(request.getParameter("examselected"));

        MarksViewResponseDto responseDto = marksDetailsService.viewMarks(dto, httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("newStudentList", responseDto.getNewStudentList());
        request.setAttribute("newMarksDetails", responseDto.getNewMarksDetails());
        request.setAttribute("subjectselected", responseDto.getSubjectSelected());
        request.setAttribute("examselected", responseDto.getExamSelected());
        request.setAttribute("subjectid", responseDto.getSubject());
        request.setAttribute("examid", responseDto.getExam());

        return responseDto.isSuccess();

    }
}
