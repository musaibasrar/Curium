package org.ideoholic.curium.model.std.action;

import org.ideoholic.curium.model.documents.dto.StudentIdDto;
import org.ideoholic.curium.model.std.service.StandardService;
import org.ideoholic.curium.util.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
public class StandardActionAdapter {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private HttpSession httpSession;

    private String BRANCHID = "branchid";
    private String USERID = "userloginid";


    public boolean viewClasses() {
        StandardService standardService = new StandardService(request, response);

        ResultResponse resultResponse = standardService.viewClasses(httpSession.getAttribute(BRANCHID).toString());
        httpSession.setAttribute("classdetailslist", resultResponse.getResultList());
        return resultResponse.isSuccess();
    }

    public void restoreMultipleLeftout() {
        StandardService standardService = new StandardService(request, response);

        StudentIdDto dto = new StudentIdDto();
        dto.setStudentIds(request.getParameterValues("studentIDs"));
        standardService.restoreMultipleLeftout(dto);
    }

    public void restoreMultipleGraduate() {
        StandardService standardService = new StandardService(request, response);

        StudentIdDto dto = new StudentIdDto();
        dto.setStudentIds(request.getParameterValues("studentIDs"));
        standardService.restoreMultipleGraduate(dto);
    }

    public void restoreMultipleDroppedout() {
        StandardService standardService = new StandardService(request, response);

        StudentIdDto dto = new StudentIdDto();
        dto.setStudentIds(request.getParameterValues("studentIDs"));
        standardService.restoreMultipleDroppedout(dto);
    }

    public void viewGraduated() {
        StandardService standardService = new StandardService(request, response);

        ResultResponse resultResponse = standardService.viewGraduated();
        request.setAttribute("studentListGraduated", resultResponse.getResultList());
    }

    public void viewDropped() {
        StandardService standardService = new StandardService(request, response);

        ResultResponse resultResponse = standardService.viewDropped();
        request.setAttribute("studentListDropped", resultResponse.getResultList());
    }
}
