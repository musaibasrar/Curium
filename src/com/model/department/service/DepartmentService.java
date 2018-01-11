package com.model.department.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.department.dao.departmentDAO;
import com.model.department.dto.Department;
import com.model.employee.dao.EmployeeDAO;
import com.util.DataUtil;

public class DepartmentService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;

	public DepartmentService(HttpServletRequest request,
			HttpServletResponse response) {

		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();

	}

	public void addDepartment() {
		
		
		Department department = new Department();
		
		department.setDepartmentname(DataUtil.emptyString(request.getParameter("department")));
		if(!department.getDepartmentname().equalsIgnoreCase("")){
			department =  new departmentDAO().create(department);
		}
		
		
	}

	public boolean viewDepartment() {
		boolean result = false;
        try {
        	List<Department> list = new departmentDAO().readListOfObjects();
            httpSession.setAttribute("departmentList", list);

            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
	}

	public void deleteMultiple() {
		 String[] departmentIds = request.getParameterValues("departmentIDs");
		 if(departmentIds!=null){
			 
		 
	        List ids = new ArrayList();
	        for (String id : departmentIds) {
	            System.out.println("id" + id);
	            ids.add(Integer.valueOf(id));

	        }
	        System.out.println("id length" + departmentIds.length);
	        new departmentDAO().deleteMultiple(ids);
		 }
	}

}
