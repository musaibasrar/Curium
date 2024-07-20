package org.ideoholic.curium.model.examdetails.action;

import org.ideoholic.curium.model.examdetails.dto.Exams;
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

        Exams result = examDetailsService.addExam(httpSession.getAttribute(BRANCHID).toString());
        result.setExamname(request.getParameter("examname"));

        return result.isSuccess();
    }


}
