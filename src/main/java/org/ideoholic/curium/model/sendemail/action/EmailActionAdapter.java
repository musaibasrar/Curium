package org.ideoholic.curium.model.sendemail.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.sendemail.dto.SendAllEmailDto;
import org.ideoholic.curium.model.sendemail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
public class EmailActionAdapter {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private HttpSession httpSession;
    
    @Autowired
    private EmailService emailService;

    private String BRANCHID = "branchid";

    public boolean sendAllEmail() {

        SendAllEmailDto dto = new SendAllEmailDto();
        dto.setAddClass(request.getParameter("addclass"));
        dto.setAddSec(request.getParameter("addsec"));
        dto.setSubject(request.getParameter("subject"));
        dto.setMessageBody(request.getParameter("messagebody"));

        ResultResponse resultResponse = emailService.sendAllEmail(dto, httpSession.getAttribute(BRANCHID).toString());

        return resultResponse.isSuccess();
    }
}
