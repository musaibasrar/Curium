package org.ideoholic.curium.model.department.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.department.dao.departmentDAO;
import org.ideoholic.curium.model.department.dto.AddDepartmentDto;
import org.ideoholic.curium.model.department.dto.Department;
import org.ideoholic.curium.model.department.dto.DepartmentResponseDto;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.ResultResponse;

public class DepartmentService {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession httpSession;
    private String BRANCHID = "branchid";

    public DepartmentService(HttpServletRequest request,
                             HttpServletResponse response) {

        this.request = request;
        this.response = response;
        this.httpSession = request.getSession();

    }

    public void addDepartment(AddDepartmentDto addDepartmentDto, String branchId) {

        Department department = new Department();
        if (branchId != null) {

            department.setDepartmentname(DataUtil.emptyString(addDepartmentDto.getDepartment()));
            department.setBranchid(Integer.parseInt(branchId));

            if (!department.getDepartmentname().equalsIgnoreCase("")) {
                department = new departmentDAO().create(department);
            }

        }
    }

    public DepartmentResponseDto viewDepartment(String branchId) {
        DepartmentResponseDto departmentResponseDto = new DepartmentResponseDto();
        boolean result = false;
        try {
            List<Department> list = new departmentDAO().readListOfObjects(Integer.parseInt(branchId));
            departmentResponseDto.setDepartmentList(list);

            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return departmentResponseDto;
    }

    public void deleteMultiple() {
        String[] departmentIds = request.getParameterValues("departmentIDs");
        if (departmentIds != null) {


            List<Integer> ids = new ArrayList();
            for (String id : departmentIds) {
                System.out.println("id" + id);
                ids.add(Integer.valueOf(id));

            }
            System.out.println("id length" + departmentIds.length);
            new departmentDAO().deleteMultiple(ids);
        }
    }

}
