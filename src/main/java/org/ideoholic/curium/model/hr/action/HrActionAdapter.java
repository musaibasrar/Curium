package org.ideoholic.curium.model.hr.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.hr.dto.AdvanceSalaryApprovalDto;
import org.ideoholic.curium.model.hr.dto.ApplyLeaveDto;
import org.ideoholic.curium.model.hr.dto.BasicPayDto;
import org.ideoholic.curium.model.hr.dto.DeleteAdvaceSalaryApprovalDto;
import org.ideoholic.curium.model.hr.dto.LeaveApprovalsResponseDto;
import org.ideoholic.curium.model.hr.dto.LeaveDetailsDto;
import org.ideoholic.curium.model.hr.dto.LeaveIdsDto;
import org.ideoholic.curium.model.hr.dto.LeaveTypeDto;
import org.ideoholic.curium.model.hr.dto.LeaveTypeResponseDto;
import org.ideoholic.curium.model.hr.dto.LeavesDetailsResponseDto;
import org.ideoholic.curium.model.hr.dto.PayHeadDto;
import org.ideoholic.curium.model.hr.dto.PayHeadResponseDto;
import org.ideoholic.curium.model.hr.dto.PayHeadStaffDetailsDto;
import org.ideoholic.curium.model.hr.dto.PfDto;
import org.ideoholic.curium.model.hr.dto.PfSettingsResponseDto;
import org.ideoholic.curium.model.hr.dto.SalaryDto;
import org.ideoholic.curium.model.hr.dto.SalaryResponseDto;
import org.ideoholic.curium.model.hr.dto.SalarySlipResponseDto;
import org.ideoholic.curium.model.hr.dto.SaveAdvanceSalaryDto;
import org.ideoholic.curium.model.hr.dto.StaffDetailsDto;
import org.ideoholic.curium.model.hr.dto.StaffDetailsResponseDto;
import org.ideoholic.curium.model.hr.service.HrService;
import org.ideoholic.curium.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HrActionAdapter {
    @Autowired
    private HttpServletRequest request;
    
    @Autowired
    private HttpSession httpSession;
    
    @Autowired
    private HrService hrService;

    public boolean leaveType() {

        LeaveTypeResponseDto leaveTypeResponseDto = hrService.leaveType(httpSession.getAttribute(Constants.BRANCHID).toString());
        httpSession.setAttribute("leavetypemaster", leaveTypeResponseDto.getLeavetypemaster());

        return leaveTypeResponseDto.isSuccess();

    }
    public boolean saveLeaveType() {

        LeaveTypeDto dto = new LeaveTypeDto();
        dto.setLeaveTypeName(request.getParameter("leavetypename"));
        ResultResponse resultResponse =  hrService.saveLeaveType(dto,httpSession.getAttribute(Constants.BRANCHID).toString(),httpSession.getAttribute(Constants.USERID).toString());

        return resultResponse.isSuccess();

    }
    public boolean deleteLeaveType() {

        LeaveTypeDto dto = new LeaveTypeDto();
        dto.setIdLeave(request.getParameter("idleave"));
        ResultResponse resultResponse = hrService.deleteLeaveType(dto);
        return resultResponse.isSuccess();
    }
    public boolean addLeaves() {

        LeaveTypeDto dto = new LeaveTypeDto();
        dto.setLeaveTypeNames(request.getParameterValues("leavetypename"));
        dto.setTotalLeaves(request.getParameterValues("totalleaves"));
        dto.setStaff(request.getParameterValues("employeeIDs"));

        ResultResponse resultResponse = hrService.addLeaves(dto, httpSession.getAttribute(Constants.CURRENTACADEMICYEAR).toString() ,httpSession.getAttribute(Constants.BRANCHID).toString(),
                httpSession.getAttribute(Constants.USERID).toString());

        return resultResponse.isSuccess();

    }
    public boolean viewLeavesDetails() {

        LeavesDetailsResponseDto result = hrService.viewLeavesDetails(request.getParameter("id"));

        request.setAttribute("leavedetailslist", result.getLeaveDetailsList());
        request.setAttribute("teachername",result.getTeacherName());

        httpSession.setAttribute("leavedetailsteachersid", result.getLeaveDetailsTeachersId());
        httpSession.setAttribute("academicPerYear", result.getAcademicPerYear());
        httpSession.setAttribute(Constants.CURRENTACADEMICYEAR,result.getCurrentAcademicYear());

        return result.isSuccess();

    }
    public boolean leaveDetailsPerYear() {

        LeaveDetailsDto leaveDetailsDto = new LeaveDetailsDto();

        leaveDetailsDto.setLeaveDetailsTeachersId(request.getParameter("leavedetailsteachersid"));
        leaveDetailsDto.setAcademicYear(request.getParameter("academicyear"));

        LeavesDetailsResponseDto result = hrService.leaveDetailsPerYear(leaveDetailsDto);

        request.setAttribute("leavedetailslist", result.getLeaveDetailsList());

        httpSession.setAttribute("academicPerYear",result.getAcademicPerYear());

        return result.isSuccess();
    }

    public void payHead() {

        PayHeadResponseDto result = hrService.payHead(httpSession.getAttribute(Constants.CURRENTACADEMICYEAR).toString(),httpSession.getAttribute(Constants.BRANCHID).toString());

        request.setAttribute("payheadlist", result.getPayHeadList());
    }
    public boolean savePayHead() {

        PayHeadDto dto = new PayHeadDto();
        dto.setPayHeadName(request.getParameter("payheadname"));
        dto.setType(request.getParameter("type"));
        dto.setValidatory(request.getParameter("validatory"));
        dto.setDescription(request.getParameter("description"));

        ResultResponse result = hrService.savePayHead(dto,
        httpSession.getAttribute(Constants.CURRENTACADEMICYEAR).toString(),
        httpSession.getAttribute(Constants.BRANCHID).toString(),
        httpSession.getAttribute(Constants.USERID).toString());
        return result.isSuccess();
    }
    public boolean addPayHeadStaffDetails() {

        PayHeadStaffDetailsDto dto = new PayHeadStaffDetailsDto();
         dto.setStaffIds(request.getParameterValues("employeeIDs"));
         dto.setValues(request.getParameterValues("values"));
         dto.setPayHeadId(request.getParameter("payhead"));
         dto.setAmountPer(request.getParameter("amtper"));

        ResultResponse result = hrService.addPayHeadStaffDetails(dto,
                    httpSession.getAttribute(Constants.CURRENTACADEMICYEAR).toString(),
                    httpSession.getAttribute(Constants.BRANCHID).toString(),
                    httpSession.getAttribute(Constants.USERID).toString());

        return result.isSuccess();

    }
    public boolean addBasicPay() {

        BasicPayDto dto = new BasicPayDto();
        dto.setStaffIds(request.getParameterValues("employeeIDs"));
        dto.setBasicPay(request.getParameterValues("basicpay"));
        dto.setPaymentType(request.getParameterValues("paymenttype"));
        dto.setAccountNo(request.getParameterValues("accountno"));
        dto.setOverTime(request.getParameterValues("ot"));


        ResultResponse result = hrService.addBasicPay(dto,
                httpSession.getAttribute(Constants.CURRENTACADEMICYEAR).toString(),
                httpSession.getAttribute(Constants.BRANCHID).toString(),
                httpSession.getAttribute(Constants.USERID).toString());

        return result.isSuccess();
    }
    public void addPf() {

            PfDto dto = new PfDto();
            dto.setPaidByManagement(request.getParameter("paidbymanagement"));
            dto.setPaidByStaff(request.getParameter("paidbystaff"));
            dto.setDate(request.getParameter("datepf"));

            hrService.addPf(dto,
                    httpSession.getAttribute(Constants.BRANCHID).toString(),
                    httpSession.getAttribute(Constants.USERID).toString());

    }
    public void pfSettings() {

        PfSettingsResponseDto result = hrService.pfSettings(httpSession.getAttribute(Constants.BRANCHID).toString());

        request.setAttribute("pflist", result.getPf());
    }
    public void deletePf() {

        PfDto dto = new PfDto();
        dto.setPfids(request.getParameterValues("pfids"));

        hrService.deletePf(dto);
    }
    public boolean saveAdvanceSalary() {

        SaveAdvanceSalaryDto dto = new SaveAdvanceSalaryDto();
        dto.setAmount(request.getParameter("amount"));
        dto.setDeductionPerMonth(request.getParameter("deductionpermonth"));
        dto.setDeductionMonth(request.getParameter("deductionmonth"));
        dto.setDeductionYear(request.getParameter("deductionyear"));
        dto.setStaffId(request.getParameter("staffid"));
        dto.setYear(request.getParameter("year"));
        dto.setMonth(request.getParameter("month"));
        dto.setSalaryForDay(request.getParameter("salaryforday"));
        dto.setDateAdvance(request.getParameter("dateadvance"));

        ResultResponse result = hrService.saveAdvanceSalary(dto,
                httpSession.getAttribute(Constants.BRANCHID).toString(),
                httpSession.getAttribute(Constants.USERID).toString());




        return result.isSuccess();
    }
    public void salaryApprovalDispaly() {

        SalaryResponseDto result = hrService.salaryApprovalDispaly(httpSession.getAttribute(Constants.BRANCHID).toString());

        request.setAttribute("payadvancesalarylist", result.getPayAdvanceSalary());
    }

    public boolean deleteAdvaceSalaryApproval() {

        DeleteAdvaceSalaryApprovalDto dto = new DeleteAdvaceSalaryApprovalDto();
        dto.setIdPayAdvanceSalary(request.getParameter("payadvance"));

        ResultResponse result = hrService.deleteAdvaceSalaryApproval(dto);

        return result.isSuccess();
    }
    public boolean saveAdvanceSalaryApproval() {

        AdvanceSalaryApprovalDto dto = new AdvanceSalaryApprovalDto();
        dto.setPaymentAdvance(request.getParameter("payadvance"));
        dto.setReason(request.getParameter("reason_"+dto.getPaymentAdvance()));
        dto.setStatus(request.getParameter("status_"+dto.getPaymentAdvance()));

        ResultResponse result = hrService.saveAdvanceSalaryApproval(dto, httpSession.getAttribute(Constants.BRANCHID).toString());

        return result.isSuccess();
    }
    public boolean salaryIssue() {

        SalaryResponseDto result = hrService.salaryIssue(httpSession.getAttribute(Constants.BRANCHID).toString());
        request.setAttribute("salaryissue",result.getPayAdvanceSalary() );


        return result.isSuccess();

    }
     public boolean applyLeave() {

        ApplyLeaveDto dto = new ApplyLeaveDto();
        dto.setLeaveTypeName(request.getParameter("leavetypename"));
        dto.setReason(request.getParameter("reason"));
        dto.setFromDate(request.getParameter("fromdate"));
        dto.setToDate(request.getParameter("todate"));

        ResultResponse result = hrService.applyLeave(dto,
        httpSession.getAttribute(Constants.CURRENTACADEMICYEAR).toString(),
        httpSession.getAttribute(Constants.USERAUTH).toString(),
        httpSession.getAttribute(Constants.USERNAME).toString(),
        httpSession.getAttribute(Constants.BRANCHID).toString(),
        httpSession.getAttribute(Constants.USERID).toString());

        return result.isSuccess();
    }
    public boolean leaveApprovals() {

        LeaveApprovalsResponseDto result = hrService.leaveApprovals(
                httpSession.getAttribute(Constants.CURRENTACADEMICYEAR).toString(),
                httpSession.getAttribute(Constants.BRANCHID).toString());

        request.setAttribute("leaveapplicationlist", result.getListLeaveApplication());

    return result.isSuccess();
    }
    public boolean rejectLeave() {

        LeaveIdsDto dto = new LeaveIdsDto();
        dto.setIdLeaveApplication(request.getParameterValues("idleaveapplication"));

        ResultResponse result = hrService.rejectLeave(dto);

        return result.isSuccess();
    }
    public boolean approveLeave() {
        LeaveIdsDto dto = new LeaveIdsDto();
        dto.setIdLeaveApplication(request.getParameterValues("idleaveapplication"));

        ResultResponse result = hrService.approveLeave(dto);
        return result.isSuccess();

    }
    public boolean processStaffSalary() {

        SalaryDto dto = new SalaryDto();
        dto.setStaffids(request.getParameterValues("employeeIDs"));
        dto.setMonth(request.getParameter("month"));
        dto.setYear(request.getParameter("year"));
        dto.setDateProcess(request.getParameter("dateprocess"));

        ResultResponse result = hrService.processStaffSalary(dto,httpSession.getAttribute(Constants.CURRENTACADEMICYEAR).toString(),httpSession.getAttribute(Constants.BRANCHID).toString(),httpSession.getAttribute(Constants.USERID).toString());

        return result.isSuccess();
    }
    public void getPayHead() throws IOException {


        PayHeadResponseDto result = hrService.getPayHead(request.getParameter("payHeadType"),httpSession.getAttribute(Constants.CURRENTACADEMICYEAR).toString(),httpSession.getAttribute(Constants.BRANCHID).toString());
        request.setAttribute("payheadlistdynamic", result.getPayHeadList());

    }
    public boolean issueStaffSalary() {

        SalaryResponseDto result = hrService.issueStaffSalary(httpSession.getAttribute(Constants.CURRENTACADEMICYEAR).toString(),httpSession.getAttribute(Constants.BRANCHID).toString());
        request.setAttribute("processsalarydetailslist", result.getProcessSalaryDetailsList());

        return result.isSuccess();
    }
    public void printSalarySlip() {

        SalarySlipResponseDto result = hrService.printSalarySlip(request.getParameter("salaryid"));
        request.setAttribute("processsalarydetails", result.getProcessSalaryDetails());
        request.setAttribute("earningmap", result.getEarningsMap());
        request.setAttribute("deductionmap", result.getDeductionsMap());
        request.setAttribute("totalearning", result.getTotalEarnings());
        request.setAttribute("totaldeduction", result.getTotalDeductions());
        request.setAttribute("netpay", result.getNetPay());
    }
    public void getStaffDetails() {

        StaffDetailsDto dto = new StaffDetailsDto();
        dto.setStaffId(request.getParameter("staffid"));

        StaffDetailsResponseDto result = hrService.getStaffDetails(dto,httpSession.getAttribute(Constants.CURRENTACADEMICYEAR).toString());

        request.setAttribute("payheaddetailslist", result.getPayHeadDetailsList());
    }
    public boolean deletePayHeadStaff() {

        SalaryDto dto = new SalaryDto();
        dto.setStaffids(request.getParameterValues("teacherid"));
        dto.setIdPayHeadStaffDetails(request.getParameterValues("idpayheadstaffdetails"));
        StaffDetailsResponseDto result = hrService.deletePayHeadStaff(dto,httpSession.getAttribute(Constants.CURRENTACADEMICYEAR).toString());
        request.setAttribute("payheaddetailslist", result.getPayHeadDetailsList());
        return result.isSuccess();
    }
    public boolean issueProcessedSalary() {

        SalaryDto dto = new SalaryDto();
        dto.setIdProcessSalaryDetails(request.getParameterValues("idprocesssalarydetails"));

        ResultResponse result = hrService.issueProcessedSalary(dto,httpSession.getAttribute(Constants.CURRENTACADEMICYEAR).toString(),httpSession.getAttribute(Constants.BRANCHID).toString());

        return result.isSuccess();
    }
    public boolean cancelProcessedSalary() {

        SalaryDto dto = new SalaryDto();
        dto.setIdProcessSalaryDetails(request.getParameterValues("idprocesssalarydetails"));

        ResultResponse result = hrService.cancelProcessedSalary(dto,httpSession.getAttribute(Constants.CURRENTACADEMICYEAR).toString(), httpSession.getAttribute(Constants.BRANCHID).toString());
        return result.isSuccess();
    }
    public void updateBasicPayEmployees() {

        BasicPayDto dto = new BasicPayDto();
        dto.setStaffIds(request.getParameterValues("employeeIDs"));
        dto.setBasicPay(request.getParameterValues("basicpay"));
        dto.setPaymentType(request.getParameterValues("paymenttype"));
        dto.setAccountNo(request.getParameterValues("accountno"));
        dto.setOverTime(request.getParameterValues("overtime"));
        dto.setAcademicYear(request.getParameterValues("academicyear"));

        ResultResponse result = hrService.updateBasicPayEmployees(dto, httpSession.getAttribute(Constants.BRANCHID).toString());
        request.setAttribute("basicpayupdate", result.isSuccess());
    }


}
