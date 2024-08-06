package org.ideoholic.curium.model.subjectdetails.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.subjectdetails.dto.AddSubjectDto;
import org.ideoholic.curium.model.subjectdetails.dto.SubjectsResponseDto;
import org.ideoholic.curium.model.subjectdetails.service.SubjectDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@Service
public class SubjectDetailsActionAdapter {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private HttpSession httpSession;


    public boolean readListOfSubjects() {
        SubjectDetailsService  subjectDetailsService = new SubjectDetailsService(request,response);

        SubjectsResponseDto result = subjectDetailsService.readListOfSubjects(httpSession.getAttribute("branchid").toString());
        httpSession.setAttribute("listSubject", result.getList());
        return result.isSuccess();
    }
    public boolean addSubject() {
        SubjectDetailsService subjectDetailsService = new SubjectDetailsService(request,response);

        AddSubjectDto addSubjectDto = new AddSubjectDto();
        addSubjectDto.setSubjectname(request.getParameter("subjectname"));
        addSubjectDto.setMinMarks(request.getParameter("minmarks"));
        addSubjectDto.setMaxMarks(request.getParameter("maxmarks"));
        addSubjectDto.setExamName(request.getParameter("examname"));
        addSubjectDto.setExamClass(request.getParameter("examclass"));
        ResultResponse result = subjectDetailsService.addSubject(addSubjectDto, httpSession.getAttribute("branchid").toString(),
                                                                   httpSession.getAttribute("userloginid").toString());
        return result.isSuccess();
    }

}
