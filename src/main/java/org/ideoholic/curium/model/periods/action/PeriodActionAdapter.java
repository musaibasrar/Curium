package org.ideoholic.curium.model.periods.action;

import org.ideoholic.curium.model.documents.action.DocumentActionAdapter;
import org.ideoholic.curium.model.employee.action.EmployeeActionAdapter;
import org.ideoholic.curium.model.periods.dto.TeacherTimeTableResponseDto;
import org.ideoholic.curium.model.periods.dto.TimeTableGenerateResponseDto;
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

        TimeTableGenerateResponseDto responseDto = periodService.generateTimeTable(httpSession.getAttribute(BRANCHID).toString());
        httpSession.setAttribute("currentYear", responseDto.getCurrentYear());
        httpSession.setAttribute("periodmasterlist", responseDto.getPeriodMaster());

        return responseDto.isSuccess();
    }
}
