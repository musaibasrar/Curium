package org.ideoholic.curium.model.subjectdetails.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.subjectdetails.dto.SubjectDto;
import org.ideoholic.curium.model.subjectdetails.dto.SubjectIdsDto;
import org.ideoholic.curium.model.subjectdetails.dto.SubjectsResponseDto;
import org.ideoholic.curium.model.subjectdetails.service.SubjectDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
@Service
public class SubjectDetailsActionAdapter {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private SubjectDetailsService subjectDetailsService;


    public boolean readListOfSubjects() {

        SubjectsResponseDto result = subjectDetailsService.readListOfSubjects(httpSession.getAttribute("branchid").toString());
        httpSession.setAttribute("listSubject", result.getList());
        return result.isSuccess();
    }
    public boolean addSubject() {

        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setSubjectName(request.getParameter("subjectname"));
        subjectDto.setMinMarks(request.getParameter("minmarks"));
        subjectDto.setMaxMarks(request.getParameter("maxmarks"));
        subjectDto.setExamName(request.getParameter("examname"));
        subjectDto.setExamClass(request.getParameter("examclass"));
        ResultResponse result = subjectDetailsService.addSubject(subjectDto, httpSession.getAttribute("branchid").toString(),
                                                                   httpSession.getAttribute("userloginid").toString());
        return result.isSuccess();
    }
    public boolean deleteMultiple() {

        SubjectIdsDto subjectIdsDto = new SubjectIdsDto();
        subjectIdsDto.setSubjectIds(request.getParameterValues("subjectIDs"));

        ResultResponse resultResponse = subjectDetailsService.deleteMultiple(subjectIdsDto);

        return resultResponse.isSuccess();
    }
    public boolean addSubjectMaster() {

        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setSubjectName(request.getParameter("subjectname"));

        ResultResponse resultResponse =
                subjectDetailsService.addSubjectMaster(subjectDto, httpSession.getAttribute("branchid").toString(),
                        httpSession.getAttribute("userloginid").toString());

        return resultResponse.isSuccess();
    }
    public boolean deleteMultipleSubject() {

        SubjectIdsDto subjectIdsDto = new SubjectIdsDto();
        subjectIdsDto.setSubjectIds(request.getParameterValues("subjectIDs"));

        ResultResponse resultResponse = subjectDetailsService.deleteMultipleSubjects(subjectIdsDto);

        return resultResponse.isSuccess();
    }
    public void readListOfSubjectNames() {

        SubjectsResponseDto result = subjectDetailsService.readListOfSubjectNames(httpSession.getAttribute("branchid").toString());
        httpSession.setAttribute("listSubjectNames", result.getList());
    }

}
