package org.ideoholic.curium.model.sendsms.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.feescollection.dto.StudentFeesReport;
import org.ideoholic.curium.model.sendsms.dto.SMSResponseDto;
import org.ideoholic.curium.model.sendsms.dto.SendSMSDto;
import org.ideoholic.curium.model.sendsms.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

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
        SmsService smsService = new SmsService(request, response);

        SendSMSDto dto = new SendSMSDto();
        dto.setNumbers(request.getParameter("numbers"));
        dto.setMessageBodyNumbers(request.getParameter("messagebodynumbers"));

        ResultResponse resultResponse = smsService.sendNumbersSMS(dto);

        return resultResponse.isSuccess();
    }

    public boolean sendStaffSMS() {
        SmsService smsService = new SmsService(request, response);

        SendSMSDto dto = new SendSMSDto();
        dto.setDepartment(request.getParameter("department"));
        dto.setMessageBodyStaff(request.getParameter("messagebodystaff"));

        ResultResponse resultResponse = smsService.sendStaffSMS(dto, httpSession.getAttribute(BRANCHID).toString());

        return resultResponse.isSuccess();
    }
    public boolean sendSMSFeesDueReminder() {
        SmsService smsService = new SmsService(request,response);
        SendSMSDto dto = new SendSMSDto();
        String[] studentIds = request.getParameterValues("studentIDs");
        dto.setStudentIds(studentIds);
        dto.setMessage(request.getParameter("deadline"));
        dto.setStudentFeesReportList((List<StudentFeesReport>) httpSession.getAttribute("studentfeesreportlist"));
        ResultResponse result = smsService.sendSMSFeesDueReminder(dto);

       return  result.isSuccess();
    }
    public boolean SMSDeliveryReport() {

        SmsService smsService = new SmsService(request,response);

        SMSResponseDto result= smsService.SMSDeliveryReport();
        request.setAttribute("smsdeliveryreport", result.getSmsDeliveryReport().get(0).getRecords());

        return result.isSuccess();
    }
    public boolean sendAllSMS() {

        SmsService smsService = new SmsService(request,response);

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
