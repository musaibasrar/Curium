package org.ideoholic.employee.service;

import java.util.ArrayList;
import java.util.List;

import org.ideoholic.employee.dto.AddEmplParameterDto;

import com.model.department.dao.departmentDAO;
import com.model.department.dto.Department;
import com.model.employee.dao.EmployeeDAO;
import com.model.employee.dto.Teacher;
import com.model.hr.dto.Paybasic;
import com.model.user.dao.UserDAO;
import com.model.user.dto.Login;
import com.model.user.service.UserService;
import com.util.DataUtil;
import com.util.DateUtil;

public class EmployeeServiceImpl implements EmployeeService {
	
	public String addEmployee(String branchId, AddEmplParameterDto addParam, UserService userService) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		Teacher employee = new Teacher();
		
		if(branchId!=null){
		
		//End Bank Details
		
		
		final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		int count =4;
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
		int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
		builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		employee.setTeacherexternalid(builder.toString());
		employee.setBranchid(Integer.parseInt(branchId.toString()));
		
		if(new EmployeeDAO().create(employee)){
			if(userService.addUser(employee)){
				sb.append("result:").append(true);
				return sb.toString();
			}else{
				new EmployeeDAO().delete(employee);
			}
		}
		}
		sb.append("result:").append(false);
		sb.append("}");
		return sb.toString();
	}
	
public String ViewAllEmployee(String branchId) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
		
		boolean result = false;
    try {
    	List<Teacher> list = new EmployeeDAO().readListOfObjects(Integer.parseInt(branchId.toString()));
    	sb.append("employeeList").append(list);
        sb.append(",employeeListProcessSalary").append(list);
        result = true;
    } catch (Exception e) {
        e.printStackTrace();
    }
    sb.append("}");
    return sb.toString();
}

public String viewDetailsEmployee(long id) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	 boolean result = false;
       try {
           Teacher employee = new EmployeeDAO().readUniqueObject(id);
           Login employeeLogin = new UserDAO().getUserDetails(employee.getTeacherexternalid());
          
           if (employee.getTid() != null) {
        	   sb.append("employee").append(employee);
           	sb.append(",stafflogin").append(employeeLogin);
               return sb.toString();
           } 
       } catch (Exception e) {
           e.printStackTrace();
           result = false;
       }
       sb.append("}");
       return sb.toString();
}

public String updateEmployee(String id1,String leavingdate, AddEmplParameterDto addParam) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	Teacher employee = new Teacher();
	
	
    
    System.out.println("THE ID IS: " + id1);

    int employeeId = 0;
    int parentsId = 0;
    employeeId = Integer.parseInt(id1);
    
    System.out.println("M in in personal service and ID is :::::::::::::::::::::::::::::: " + employeeId);

    employee.setTid(employeeId);
    
	//End Bank Details
						
	
	employee = new EmployeeDAO().update(employee);
			
    String empId = employee.getTid().toString();
    sb.append("}");
    return empId;
   
}

public String deleteMultiple(String[] employeeIds) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	 if(employeeIds!=null){
       List ids = new ArrayList();
       for (String id : employeeIds) {
           System.out.println("id" + id);
           ids.add(Integer.valueOf(id));

       }
       System.out.println("id length" + employeeIds.length);
       new EmployeeDAO().deleteMultiple(ids);
	 }
	 sb.append("}");
	return sb.toString();
}

public String searchEmployee(String staffName, String staffDepartment, String branchId, EmployeeService employeeService) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	List<Teacher> employeeList = new ArrayList<Teacher>();
	
	if(branchId!=null){
		if(staffName!=""){
			employeeList = new EmployeeDAO().readListOfEmployeesByName(staffName, Integer.parseInt(branchId.toString()));
		}else if(staffDepartment!=""){
			employeeList = new EmployeeDAO().readListOfEmployeesByDepartment(staffDepartment, Integer.parseInt(branchId.toString()));
		}else {
			employeeList = new EmployeeDAO().readListOfEmployeesBasicPay(Integer.parseInt(branchId.toString()));
		}
	}
	sb.append("searchedemployeeList").append(employeeList);
	
   employeeService.ViewAllEmployee(branchId);
	sb.append("}");
	return sb.toString();
}

@Override
public String addEmployee(String branchId, AddEmplParameterDto addParam,
		org.ideoholic.user.service.UserService userService) {
	// TODO Auto-generated method stub
	return null;
}


public String basicpayEmployees(String branchId) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	List<Paybasic> employeeList = new ArrayList<Paybasic>();
	
	if(branchId!=null){
			employeeList = new EmployeeDAO().readListOfEmployeesBasicPayDetails(Integer.parseInt(branchId.toString()));
	}
	sb.append("vieweditbasicpay").append(employeeList);
	sb.append("}");
	return sb.toString();
}

}
