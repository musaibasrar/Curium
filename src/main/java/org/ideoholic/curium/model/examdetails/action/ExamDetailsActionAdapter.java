package org.ideoholic.curium.model.examdetails.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.examdetails.dto.AddExamDto;
import org.ideoholic.curium.model.examdetails.dto.ExamsListResponseDto;
import org.ideoholic.curium.model.examdetails.service.ExamDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@Service
public class ExamDetailsActionAdapter {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;

    @Autowired
    private HttpSession httpSession;

    private String BRANCHID = "branchid";
    private String CURRENTACADEMICYEAR = "currentAcademicYear";

    public Boolean addExam() {
        ExamDetailsService examDetailsService = new ExamDetailsService(request,response);

        AddExamDto result = new AddExamDto();
        result.setExamName( request.getParameter("examname"));

        ResultResponse resultResponse = examDetailsService.addExam(result,httpSession.getAttribute(BRANCHID).toString());

        return resultResponse.isSuccess();
    }

    public boolean readListOfExams() {
        ExamDetailsService examDetailsService = new ExamDetailsService(request,response);

        ExamsListResponseDto result = examDetailsService.readListOfExams(httpSession.getAttribute(BRANCHID).toString());

        httpSession.setAttribute("examdetails", result.getExams());
        return result.isSuccess();
    }
    
}