package org.ideoholic.curium.model.periods.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.documents.action.DocumentActionAdapter;
import org.ideoholic.curium.model.employee.action.EmployeeActionAdapter;
import org.ideoholic.curium.model.periods.dto.PeriodMasterIdDto;
import org.ideoholic.curium.model.periods.dto.TeacherTimeTableResponseDto;
import org.ideoholic.curium.model.periods.dto.TimeTableResponseDto;
import org.ideoholic.curium.model.periods.dto.TimeTableViewResponseDto;
import org.ideoholic.curium.model.periods.service.PeriodService;
import org.ideoholic.curium.model.std.action.StandardActionAdapter;
import org.ideoholic.curium.model.subjectdetails.action.SubjectDetailsActionAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
public class PeriodActionAdapter {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private DocumentActionAdapter documentActionAdapter;
    @Autowired
    private StandardActionAdapter standardActionAdapter;
    @Autowired
    private EmployeeActionAdapter employeeActionAdapter;
    @Autowired
    private SubjectDetailsActionAdapter subjectDetailsActionAdapter;

    String BRANCHID = "branchid";

    public boolean viewTeacherTimeTable() {

        PeriodService periodService = new PeriodService(request, response, standardActionAdapter, employeeActionAdapter, subjectDetailsActionAdapter);

        String teacherName = request.getParameter("teachername");

        TeacherTimeTableResponseDto responseDto = periodService.viewTeacherTimeTable(teacherName, httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("teachername", responseDto.getTeacherName());
        request.setAttribute("teacherperiodmasterlist", responseDto.getPeriodMapList());

        return responseDto.isSuccess();
    }

    public boolean generateTimeTable() {
        PeriodService periodService = new PeriodService(request, response, standardActionAdapter, employeeActionAdapter, subjectDetailsActionAdapter);

        TimeTableResponseDto responseDto = periodService.generateTimeTable(httpSession.getAttribute(BRANCHID).toString());
        httpSession.setAttribute("currentYear", responseDto.getCurrentYear());
        httpSession.setAttribute("periodmasterlist", responseDto.getPeriodMaster());

        return responseDto.isSuccess();
    }

    public boolean deletePeriods() {
        PeriodService periodService = new PeriodService(request, response, standardActionAdapter, employeeActionAdapter, subjectDetailsActionAdapter);

        PeriodMasterIdDto dto = new PeriodMasterIdDto();
        dto.setPeriodMasterId(request.getParameterValues("idperiodmaster"));

        ResultResponse resultResponse = periodService.deletePeriods(dto);
        return resultResponse.isSuccess();
    }

    public boolean viewTimeTable() {
        PeriodService periodService = new PeriodService(request, response, standardActionAdapter, employeeActionAdapter, subjectDetailsActionAdapter);

        String periodMasterId = request.getParameter("id");

        TimeTableViewResponseDto responseDto = periodService.viewTimeTable(periodMasterId);
        request.setAttribute("timetable", responseDto.getPeriodMaster());
        request.setAttribute("timetableperioddetails", responseDto.getPeriodDetails());
        request.setAttribute("periodmap", responseDto.getPeriodMap());
        request.setAttribute("periodMasterid", responseDto.getPeriodMasterId());

        return responseDto.isSuccess();
    }
}
