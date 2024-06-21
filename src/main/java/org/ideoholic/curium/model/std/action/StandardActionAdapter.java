package org.ideoholic.curium.model.std.action;

import org.ideoholic.curium.model.documents.dto.StudentIdDto;
import org.ideoholic.curium.model.std.service.StandardService;
import org.ideoholic.curium.util.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class StandardActionAdapter {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private StandardService standardService;

    private String BRANCHID = "branchid";
    private String USERID = "userloginid";


    public boolean viewClasses() {
        ResultResponse resultResponse = standardService.viewClasses(httpSession.getAttribute(BRANCHID).toString());
        httpSession.setAttribute("classdetailslist", resultResponse.getResultList());
        return resultResponse.isSuccess();
    }

    public void restoreMultipleLeftout() {
        StudentIdDto dto = new StudentIdDto();
        dto.setStudentIds(request.getParameterValues("studentIDs"));
    }
}
