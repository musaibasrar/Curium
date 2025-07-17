package org.ideoholic.curium.model.sendsms.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.feescollection.dto.StudentFeesReport;
import org.ideoholic.curium.model.sendsms.dto.SMSResponseDto;
import org.ideoholic.curium.model.sendsms.dto.SendSMSDto;
import org.ideoholic.curium.model.sendsms.service.SmsService;
import org.ideoholic.curium.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsActionAdapter {

    @Autowired
    private HttpServletRequest request;
    
    @Autowired
    private HttpSession httpSession;
    
    @Autowired
    private SmsService smsService;


    public boolean sendNumbersSMS() {

        SendSMSDto dto = new SendSMSDto();
        dto.setNumbers(request.getParameter("numbers"));
        dto.setMessageBodyNumbers(request.getParameter("messagebodynumbers"));

        ResultResponse resultResponse = smsService.sendNumbersSMS(dto);

        return resultResponse.isSuccess();
    }

    public boolean sendStaffSMS() {

        SendSMSDto dto = new SendSMSDto();
        dto.setDepartment(request.getParameter("department"));
        dto.setMessageBodyStaff(request.getParameter("messagebodystaff"));

        ResultResponse resultResponse = smsService.sendStaffSMS(dto, httpSession.getAttribute(Constants.BRANCHID).toString());

        return resultResponse.isSuccess();
    }

    public boolean sendSMSFeesDueReminder() {

        SendSMSDto dto = new SendSMSDto();
        String[] studentIds = request.getParameterValues("studentIDs");
        dto.setStudentIds(studentIds);
        dto.setMessage(request.getParameter("deadline"));
        dto.setStudentFeesReportList((List<StudentFeesReport>) httpSession.getAttribute("studentfeesreportlist"));
        ResultResponse result = smsService.sendSMSFeesDueReminder(dto);

       return  result.isSuccess();
    }

    public boolean SMSDeliveryReport() {

        SMSResponseDto result= smsService.SMSDeliveryReport();
        request.setAttribute("smsdeliveryreport", result.getSmsDeliveryReport().get(0).getRecords());

        return result.isSuccess();
    }

    public boolean sendAllSMS() {

        SendSMSDto dto = new SendSMSDto();
        dto.setAddClass(request.getParameter("addclass"));
        dto.setAddSec(request.getParameter("addsec"));
        String SMSTempType = request.getParameter("messagebody");
        dto.setSmsTempType(SMSTempType );
        dto.setMessage(request.getParameter(SMSTempType+"var1")+":"+request.getParameter(SMSTempType+"var2")+":"+request.getParameter(SMSTempType+"var3")+":"+request.getParameter(SMSTempType+"var4"));

        ResultResponse result = smsService.sendAllSMS(dto, httpSession.getAttribute("branchid").toString());

        return result.isSuccess();
    }
}
