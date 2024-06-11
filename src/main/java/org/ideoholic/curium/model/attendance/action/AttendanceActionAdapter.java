package org.ideoholic.curium.model.attendance.action;

import org.ideoholic.curium.model.attendance.dto.AddHolidaysDto;
import org.ideoholic.curium.model.attendance.service.AttendanceService;
import org.ideoholic.curium.util.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
public class AttendanceActionAdapter {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private HttpSession httpSession;
    private String CURRENTACADEMICYEAR = "currentAcademicYear";
    private String BRANCHID = "branchid";
    public boolean addHolidays() {
        AttendanceService attendanceService = new AttendanceService(request,response);
        AddHolidaysDto addHolidaysDto = new AddHolidaysDto();
        addHolidaysDto.setFromDate(request.getParameterValues("fromdate"));
        addHolidaysDto.setToDate(request.getParameterValues("todate"));
        addHolidaysDto.setHolidayName(request.getParameterValues("holidayname"));

        addHolidaysDto.setCurrentAcademicYear(httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
        addHolidaysDto.setBranchId(httpSession.getAttribute(BRANCHID).toString());
        return true;
    }

}
