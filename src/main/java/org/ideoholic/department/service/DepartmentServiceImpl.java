package org.ideoholic.department.service;

import java.util.ArrayList;
import java.util.List;

import com.model.department.dao.departmentDAO;
import com.model.department.dto.Department;
import com.util.DataUtil;

public class DepartmentServiceImpl implements DepartmentService {
	
public String addDepartment(String branchId, String department1) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
		
		
		Department department = new Department();
		if(branchId!=null){
			
			department.setBranchid(Integer.parseInt(branchId));
			
			if(!department.getDepartmentname().equalsIgnoreCase("")){
				department =  new departmentDAO().create(department);
			}
			
		}
		sb.append("}");
		return sb.toString();
	}


public String viewDepartment(String branchId) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	boolean result = false;
    try {
    	List<Department> list = new departmentDAO().readListOfObjects(Integer.parseInt(branchId.toString()));
    	sb.append("departmentList").append(list);
        result = true;
    } catch (Exception e) {
        e.printStackTrace();
        result = false;
    }
    sb.append("}");
    return sb.toString();
}

public String deleteMultiple(String[] departmentIds) {
	
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	 if(departmentIds!=null){
		 
	 
       List ids = new ArrayList();
       for (String id : departmentIds) {
           System.out.println("id" + id);
           ids.add(Integer.valueOf(id));

       }
       System.out.println("id length" + departmentIds.length);
       new departmentDAO().deleteMultiple(ids);
	 }
	 sb.append("}");
	return sb.toString();
}

}
