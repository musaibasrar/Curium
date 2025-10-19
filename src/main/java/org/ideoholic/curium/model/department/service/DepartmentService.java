package org.ideoholic.curium.model.department.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.department.dao.departmentDAO;
import org.ideoholic.curium.model.department.dto.AddDepartmentDto;
import org.ideoholic.curium.model.department.dto.DeleteMultipleDto;
import org.ideoholic.curium.model.department.dto.Department;
import org.ideoholic.curium.model.department.dto.DepartmentResponseDto;
import org.ideoholic.curium.util.DataUtil;
import org.springframework.stereotype.Service;

@Service

public class DepartmentService {

    public ResultResponse addDepartment(AddDepartmentDto addDepartmentDto, String branchId) {

        Department department = new Department();
        if (branchId != null) {

            department.setDepartmentname(DataUtil.emptyString(addDepartmentDto.getDepartment()));
            department.setBranchid(Integer.parseInt(branchId));

            if (!department.getDepartmentname().equalsIgnoreCase("")) {
                department = new departmentDAO().create(department);
               return  ResultResponse.builder().success(true).build();


            }

        }
        return ResultResponse.builder().build();
    }

    public DepartmentResponseDto viewDepartment(String branchId) {
        DepartmentResponseDto departmentResponseDto = new DepartmentResponseDto();
        try {
            List<Department> list = new departmentDAO().readListOfObjects(Integer.parseInt(branchId));
            departmentResponseDto.setDepartmentList(list);

            departmentResponseDto.setSuccess(true);

        } catch (Exception e) {
            e.printStackTrace();
            departmentResponseDto.setSuccess(false);
        }
        return departmentResponseDto;
    }

    public ResultResponse deleteMultiple(DeleteMultipleDto deleteMultipleDto) {
        String[] departmentIds = deleteMultipleDto.getDepartmentIds();
        if (departmentIds != null) {


            List<Integer> ids = new ArrayList();
            for (String id : departmentIds) {
                System.out.println("id" + id);
                ids.add(Integer.valueOf(id));

            }
            System.out.println("id length" + departmentIds.length);
          return ResultResponse.builder().success(new departmentDAO().deleteMultiple(ids)).build();
        }
        return ResultResponse.builder().build();
    }

}
