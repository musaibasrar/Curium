package org.ideoholic.curium.model.subjectdetails.action;

import org.ideoholic.curium.model.subjectdetails.dto.ListOfSubjectsResponseDto;
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

        ListOfSubjectsResponseDto result = subjectDetailsService.readListOfSubjects(httpSession.getAttribute("branchid").toString());
        httpSession.setAttribute("listSubject", result.getList());
        return result.isSuccess();
    }

}
