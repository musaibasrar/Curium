package org.ideoholic.curium.model.mess.supplier.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.mess.supplier.service.MessSuppliersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
public class MessSuppliersActionAdapter {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private HttpSession httpSession;

    private String BRANCHID = "branchid";
    private String USERID = "userloginid";

    public boolean viewSuppliersDetails() {
        MessSuppliersService messSuppliersService = new MessSuppliersService(request, response);

        ResultResponse resultResponse = messSuppliersService.viewSuppliersDetails(httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("messsupplierslist", resultResponse.getResultList());

        return resultResponse.isSuccess();
    }
}
