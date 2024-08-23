package org.ideoholic.curium.model.marksdetails.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.documents.dto.SearchStudentResponseDto;
import org.ideoholic.curium.model.marksdetails.dto.GenerateReportDto;
import org.ideoholic.curium.model.marksdetails.dto.GenerateReportResponseDto;
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

        String studentUID = request.getParameter("id");
        String[] studentIds = request.getParameterValues("studentIDs");

        GenerateReportResponseDto responseDto = marksDetailsService.generateReportParent(studentUID, studentIds, httpSession.getAttribute(CURRENTACADEMICYEAR).toString(), httpSession.getAttribute(BRANCHID).toString());
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
}
