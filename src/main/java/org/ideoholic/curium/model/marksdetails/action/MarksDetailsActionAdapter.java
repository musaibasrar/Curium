package org.ideoholic.curium.model.marksdetails.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.attendance.dto.ExportMonthlyDataDto;
import org.ideoholic.curium.model.documents.dto.SearchStudentResponseDto;
import org.ideoholic.curium.model.marksdetails.dto.SearchStudentExamDto;
import org.ideoholic.curium.model.marksdetails.dto.StudentGraphResponseDto;
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
}
