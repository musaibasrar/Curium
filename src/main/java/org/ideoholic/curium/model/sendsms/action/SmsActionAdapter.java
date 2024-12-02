package org.ideoholic.curium.model.sendsms.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.sendsms.dto.SendNumberSMSDto;
import org.ideoholic.curium.model.sendsms.dto.SendStaffSMSDto;
import org.ideoholic.curium.model.sendsms.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.BaseRowSet;
import java.awt.geom.RectangularShape;

@Service
public class SmsActionAdapter {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private HttpSession httpSession;

    private String BRANCHID = "branchid";
    private String USERID = "userloginid";


    public boolean sendNumbersSMS() {
        SmsService smsService = new SmsService(request,response);

        SendNumberSMSDto dto = new SendNumberSMSDto();
        dto.setNumbers(request.getParameter("numbers"));
        dto.setMessageBodyNumbers(request.getParameter("messagebodynumbers"));

        ResultResponse resultResponse = smsService.sendNumbersSMS(dto);

        return resultResponse.isSuccess();
    }

    public boolean sendStaffSMS() {
        SmsService smsService = new SmsService(request,response);

        SendStaffSMSDto dto = new SendStaffSMSDto();
        dto.setDepartment(request.getParameter("department"));
        dto.setMessageBodyStaff(request.getParameter("messagebodystaff"));

        ResultResponse resultResponse = smsService.sendStaffSMS(dto, httpSession.getAttribute(BRANCHID).toString());

        return resultResponse.isSuccess();
    }
}
