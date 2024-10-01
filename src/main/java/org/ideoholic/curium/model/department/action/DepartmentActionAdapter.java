package org.ideoholic.curium.model.department.action;

import org.ideoholic.curium.model.department.dto.AddDepartmentDto;
import org.ideoholic.curium.model.department.dto.DeleteMultipleDto;
import org.ideoholic.curium.model.department.dto.DepartmentResponseDto;
import org.ideoholic.curium.model.department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
public class DepartmentActionAdapter {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private HttpSession httpSession;
    private String BRANCHID = "branchid";

    public void addDepartment() {
        DepartmentService departmentService = new DepartmentService(request, response);
        AddDepartmentDto addDepartmentDto = new AddDepartmentDto();

        addDepartmentDto.setDepartment(request.getParameter("department"));
        departmentService.addDepartment(addDepartmentDto, httpSession.getAttribute(BRANCHID).toString());
    }
    public boolean viewDepartment(){
        DepartmentService departmentService = new DepartmentService(request, response);
        DepartmentResponseDto departmentResponseDto = new DepartmentResponseDto();
        departmentResponseDto = departmentService.viewDepartment(httpSession.getAttribute(BRANCHID).toString());

        httpSession.setAttribute("departmentList", departmentResponseDto.getDepartmentList());

        return departmentResponseDto.isSuccess();
    }
    public void deleteMultiple() {
        DepartmentService departmentService = new DepartmentService(request,response);
        DeleteMultipleDto deleteMultipleDto = new DeleteMultipleDto();
        deleteMultipleDto.setDepartmentIds(request.getParameterValues("departmentIDs"));
        departmentService.deleteMultiple(deleteMultipleDto);

    }

}
