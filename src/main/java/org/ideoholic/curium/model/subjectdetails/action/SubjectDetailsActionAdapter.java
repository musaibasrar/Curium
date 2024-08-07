package org.ideoholic.curium.model.subjectdetails.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.examdetails.dto.ExamIdsDto;
import org.ideoholic.curium.model.subjectdetails.dto.SubjectDto;
import org.ideoholic.curium.model.subjectdetails.dto.SubjectIdsDto;
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

        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setSubjectname(request.getParameter("subjectname"));
        subjectDto.setMinMarks(request.getParameter("minmarks"));
        subjectDto.setMaxMarks(request.getParameter("maxmarks"));
        subjectDto.setExamName(request.getParameter("examname"));
        subjectDto.setExamClass(request.getParameter("examclass"));
        ResultResponse result = subjectDetailsService.addSubject(subjectDto, httpSession.getAttribute("branchid").toString(),
                                                                   httpSession.getAttribute("userloginid").toString());
        return result.isSuccess();
    }
    public boolean deleteMultiple() {
        SubjectDetailsService subjectDetailsService = new SubjectDetailsService(request,response);

        SubjectIdsDto subjectIdsDto = new SubjectIdsDto();
        subjectIdsDto.setSubjectIds(request.getParameterValues("subjectIDs"));

        ResultResponse resultResponse = subjectDetailsService.deleteMultiple(subjectIdsDto);

        return resultResponse.isSuccess();
    }

}
