package org.ideoholic.curium.model.hr.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.hr.dto.LeaveDetailsDto;
import org.ideoholic.curium.model.hr.dto.LeaveTypeDto;
import org.ideoholic.curium.model.hr.dto.LeaveTypeResponseDto;
import org.ideoholic.curium.model.hr.dto.LeavesDetailsResponseDto;
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


}
