package org.ideoholic.curium.model.student.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.std.action.StandardActionAdapter;
import org.ideoholic.curium.model.student.dto.StudentsSuperAdminResponseDto;
import org.ideoholic.curium.model.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
public class StudentActionAdapter {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private StandardActionAdapter standardActionAdapter;

    private String CURRENTACADEMICYEAR = "currentAcademicYear";
    private String BRANCHID = "branchid";
    private String USERID = "userloginid";


    public void viewAllStudentsSuperAdmin() {
        StudentService studentService = new StudentService(request, response, standardActionAdapter);

        StudentsSuperAdminResponseDto responseDto = studentService.viewAllStudentsSuperAdmin();
        request.setAttribute("studentList", responseDto.getStudentList());
        request.setAttribute("noOfPages", responseDto.getNoOfPages());
        request.setAttribute("currentPage", responseDto.getPage());
    }

    public String addNew() {
        StudentService studentService = new StudentService(request, response, standardActionAdapter);

        ResultResponse resultResponse = studentService.addNew(httpSession.getAttribute(BRANCHID).toString());
        return "addStudent";
    }

    public boolean downlaodFile() {
        StudentService studentService = new StudentService(request, response, standardActionAdapter);

        ResultResponse resultResponse = studentService.downlaodFile();

        return resultResponse.isSuccess();
    }
}
