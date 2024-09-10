package org.ideoholic.curium.model.hr.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.hr.dto.*;
import org.ideoholic.curium.model.hr.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
public class HrActionAdapter {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private HttpSession httpSession;
    private String BRANCHID = "branchid";
    private String USERID ="userid";
    private String CURRENTACADEMICYEAR ="currentAcademicYear";

    public boolean leaveType() {
        HrService hrService = new HrService(request,response);

        LeaveTypeResponseDto leaveTypeResponseDto = hrService.leaveType(httpSession.getAttribute(BRANCHID).toString());
        httpSession.setAttribute("leavetypemaster", leaveTypeResponseDto.getLeavetypemaster());

        return leaveTypeResponseDto.isSuccess();

    }
    public boolean saveLeaveType() {
        HrService hrService = new HrService(request,response);

        LeaveTypeDto dto = new LeaveTypeDto();
        dto.setLeaveTypeName(request.getParameter("leavetypename"));
        ResultResponse resultResponse =  hrService.saveLeaveType(dto,httpSession.getAttribute(BRANCHID).toString(),httpSession.getAttribute(USERID).toString());

        return resultResponse.isSuccess();

    }
    public boolean deleteLeaveType() {
        HrService hrService = new HrService(request,response);

        LeaveTypeDto dto = new LeaveTypeDto();
        dto.setIdLeave(request.getParameter("idleave"));
        ResultResponse resultResponse = hrService.deleteLeaveType(dto);
        return resultResponse.isSuccess();
    }
    public boolean addLeaves() {
        HrService hrService = new HrService(request,response);

        LeaveTypeDto dto = new LeaveTypeDto();
        dto.setLeaveTypeNames(request.getParameterValues("leavetypename"));
        dto.setTotalLeaves(request.getParameterValues("totalleaves"));
        dto.setStaff(request.getParameterValues("employeeIDs"));

        ResultResponse resultResponse = hrService.addLeaves(dto, httpSession.getAttribute("currentAcademicYear").toString() ,httpSession.getAttribute(BRANCHID).toString(),
                httpSession.getAttribute(USERID).toString());

        return resultResponse.isSuccess();

    }
    public boolean viewLeavesDetails() {
        HrService hrService = new HrService(request,response);

        LeavesDetailsResponseDto result = hrService.viewLeavesDetails();

        request.setAttribute("leavedetailslist", result.getLeaveDetailsList());
        request.setAttribute("teachername",result.getTeacherName());

        httpSession.setAttribute("leavedetailsteachersid", result.getLeaveDetailsTeachersId());
        httpSession.setAttribute("academicPerYear", result.getAcademicPerYear());
        httpSession.setAttribute("currentAcademicYear",result.getCurrentAcademicYear());

        return result.isSuccess();

    }
    public boolean leaveDetailsPerYear() {
        HrService hrService = new HrService(request,response);

        LeaveDetailsDto leaveDetailsDto = new LeaveDetailsDto();

        leaveDetailsDto.setLeaveDetailsTeachersId(request.getParameter("leavedetailsteachersid"));
        leaveDetailsDto.setAcademicYear(request.getParameter("academicyear"));

        LeavesDetailsResponseDto result = hrService.leaveDetailsPerYear(leaveDetailsDto);

        request.setAttribute("leavedetailslist", result.getLeaveDetailsList());

        httpSession.setAttribute("academicPerYear",result.getAcademicPerYear());

        return result.isSuccess();
    }

    public void payHead() {
        HrService hrService = new HrService(request,response);

        PayHeadResponseDto result = hrService.payHead(httpSession.getAttribute(CURRENTACADEMICYEAR).toString(),httpSession.getAttribute(BRANCHID).toString());

        request.setAttribute("payheadlist", result.getPayHeadList());
    }
    public boolean savePayHead() {
        HrService hrService = new HrService(request,response);

        PayHeadDto dto = new PayHeadDto();
        dto.setPayHeadName(request.getParameter("payheadname"));
        dto.setType(request.getParameter("type"));
        dto.setValidatory(request.getParameter("validatory"));
        dto.setDescription(request.getParameter("description"));

        ResultResponse result = hrService.savePayHead(dto,
        httpSession.getAttribute(CURRENTACADEMICYEAR).toString(),
        httpSession.getAttribute(BRANCHID).toString(),
        httpSession.getAttribute(USERID).toString());
        return result.isSuccess();
    }
    public boolean addPayHeadStaffDetails() {
        HrService hrService = new HrService(request,response);

        PayHeadStaffDetailsDto dto = new PayHeadStaffDetailsDto();
         dto.setStaffIds(request.getParameterValues("employeeIDs"));
         dto.setValues(request.getParameterValues("values"));
         dto.setPayHeadId(request.getParameter("payhead"));
         dto.setAmountPer(request.getParameter("amtper"));

        ResultResponse result = hrService.addPayHeadStaffDetails(dto,
                    httpSession.getAttribute(CURRENTACADEMICYEAR).toString(),
                    httpSession.getAttribute(BRANCHID).toString(),
                    httpSession.getAttribute(USERID).toString());

        return result.isSuccess();

    }
    public boolean addBasicPay() {
        HrService hrService = new HrService(request,response);

        BasicPayDto dto = new BasicPayDto();
        dto.setStaffIds(request.getParameterValues("employeeIDs"));
        dto.setBasicPay(request.getParameterValues("basicpay"));
        dto.setPaymentType(request.getParameterValues("paymenttype"));
        dto.setAccountNo(request.getParameterValues("accountno"));
        dto.setOverTime(request.getParameterValues("ot"));


        ResultResponse result = hrService.addBasicPay(dto,
                httpSession.getAttribute(CURRENTACADEMICYEAR).toString(),
                httpSession.getAttribute(BRANCHID).toString(),
                httpSession.getAttribute(USERID).toString());

        return result.isSuccess();
    }
    public void addPf() {
        HrService hrService = new HrService(request,response);

        PfDto dto = new PfDto();
        dto.setPaidByManagement(request.getParameter("paidbymanagement"));
        dto.setPaidByStaff(request.getParameter("paidbystaff"));
        dto.setDate(request.getParameter("datepf"));

        hrService.addPf(dto,
        httpSession.getAttribute(BRANCHID).toString(),
        httpSession.getAttribute(USERID).toString());


    }
    public void pfSettings() {
        HrService hrService = new HrService(request,response);

        PfSettingsResponseDto result = hrService.pfSettings(httpSession.getAttribute(BRANCHID).toString());

        request.setAttribute("pflist", result.getPf());
    }


}
