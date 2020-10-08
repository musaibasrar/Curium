package com.model.employee.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.model.department.dao.departmentDAO;
import com.model.department.dto.Department;
import com.model.employee.dao.EmployeeDAO;
import com.model.employee.dto.Teacher;
import com.model.hr.dto.Paybasic;
import com.model.position.dao.positionDAO;
import com.model.position.dto.Position;
import com.model.user.dao.UserDAO;
import com.model.user.dto.Login;
import com.model.user.service.UserService;
import com.util.DataUtil;
import com.util.DateUtil;

public class EmployeeService {

	private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession httpSession;
    private String BRANCHID = "branchid";
    
	public EmployeeService(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
        this.response = response;
        this.httpSession = request.getSession();
	}

	public boolean addEmployee() {
		Teacher employee = new Teacher();
		
		if(httpSession.getAttribute(BRANCHID)!=null){
			
			try {
				List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				
				for (FileItem item : items) {
					

					if (item.isFormField()) {
						String fieldName = item.getFieldName();
						
						  if (fieldName.equalsIgnoreCase("name")) {
							  employee.setTeachername(DataUtil.emptyString(item.getString()));
			                }
						  
						  if (fieldName.equalsIgnoreCase("gender")) {
							  employee.setGender(DataUtil.emptyString(item.getString()));
			                }
						  
						  if (fieldName.equalsIgnoreCase("address")) {
							  employee.setAddress(DataUtil.emptyString(item.getString()));
			                }
						  
						  if (fieldName.equalsIgnoreCase("contactnumber")) {
							  employee.setContactnumber(DataUtil.emptyString(item.getString()));
			                }
						  
						  if (fieldName.equalsIgnoreCase("aadharnumber")) {
							  employee.setAadharnumber(DataUtil.emptyString(item.getString()));
			                }
						  
						  if (fieldName.equalsIgnoreCase("pan")) {
							  employee.setPan(DataUtil.emptyString(item.getString()));
			                }
						  
						  if (fieldName.equalsIgnoreCase("dateofjoining")) {
							  employee.setDateofjoining(DateUtil.indiandateParser(item.getString()));
			                }
						  
						  if (fieldName.equalsIgnoreCase("totalexperience")) {
							  employee.setTotalexperience(DataUtil.emptyString(item.getString()));
			                }
						  
						  if (fieldName.equalsIgnoreCase("qualification")) {
							  employee.setQualification(DataUtil.emptyString(item.getString()));
			                }
						  
						  if (fieldName.equalsIgnoreCase("department")) {
							  employee.setDepartment(DataUtil.emptyString(item.getString()));
			                }
						  
						  if (fieldName.equalsIgnoreCase("designation")) {
							  employee.setDesignation(DataUtil.emptyString(item.getString()));
			                }
						  
						  if (fieldName.equalsIgnoreCase("salary")) {
							  employee.setSalary(DataUtil.emptyString(item.getString()));
			                }
						  
						  if (fieldName.equalsIgnoreCase("remarks")) {
							  employee.setRemarks(DataUtil.emptyString(item.getString()));
			                }
						  
						  if (fieldName.equalsIgnoreCase("currentemployee")) {
							  employee.setCurrentemployee(DataUtil.emptyString(item.getString()));
			                }
						  
						  
						  //Bank Details
						  
						  if (fieldName.equalsIgnoreCase("bankname")) {
							  employee.setBankname(DataUtil.emptyString(item.getString()));
			                }
						  
						  if (fieldName.equalsIgnoreCase("bankifsc")) {
							  employee.setBankifsc(DataUtil.emptyString(item.getString()));
			                }
						  
						  if (fieldName.equalsIgnoreCase("accno")) {
							  employee.setAccno(DataUtil.emptyString(item.getString()));
			                }
						  
						  //End
						  
						 
					}else {

		                // Process form file field (input type="file").
		                String fieldName = item.getFieldName();

		                //Student Docs
		                if (fieldName.equalsIgnoreCase("employeedoc1")) {
		                    String fileName = (DataUtil.emptyString(item.getName()));
		                    String fileValue = (DataUtil.emptyString(item.getString()));
		                    if (!fileName.equalsIgnoreCase("")) {
		                    	// encode data on your side using BASE64
		                    	byte[]   bytesEncoded = Base64.encodeBase64(item.get());
		                    	String saveFile = new String(bytesEncoded);
		                    	employee.setDoc1(saveFile);

		                    } 
		                }else if (fieldName.equalsIgnoreCase("employeedoc2")) {
		                    String fileName = (DataUtil.emptyString(item.getName()));
		                    String fileValue = (DataUtil.emptyString(item.getString()));
		                    if (!fileName.equalsIgnoreCase("")) {
		                    	// encode data on your side using BASE64
		                    	byte[]   bytesEncoded = Base64.encodeBase64(item.get());
		                    	String saveFile = new String(bytesEncoded);
		                    	employee.setDoc2(saveFile);

		                    } 
		                }else if (fieldName.equalsIgnoreCase("employeedoc3")) {
		                    String fileName = (DataUtil.emptyString(item.getName()));
		                    String fileValue = (DataUtil.emptyString(item.getString()));
		                    if (!fileName.equalsIgnoreCase("")) {
		                    	// encode data on your side using BASE64
		                    	byte[]   bytesEncoded = Base64.encodeBase64(item.get());
		                    	String saveFile = new String(bytesEncoded);
		                    	employee.setDoc3(saveFile);

		                    } 
		                }else if (fieldName.equalsIgnoreCase("employeedoc4")) {
		                    String fileName = (DataUtil.emptyString(item.getName()));
		                    String fileValue = (DataUtil.emptyString(item.getString()));
		                    if (!fileName.equalsIgnoreCase("")) {
		                    	// encode data on your side using BASE64
		                    	byte[]   bytesEncoded = Base64.encodeBase64(item.get());
		                    	String saveFile = new String(bytesEncoded);
		                    	employee.setDoc4(saveFile);

		                    } 
		                }else if (fieldName.equalsIgnoreCase("employeedoc5")) {
		                    String fileName = (DataUtil.emptyString(item.getName()));
		                    String fileValue = (DataUtil.emptyString(item.getString()));
		                    if (!fileName.equalsIgnoreCase("")) {
		                    	// encode data on your side using BASE64
		                    	byte[]   bytesEncoded = Base64.encodeBase64(item.get());
		                    	String saveFile = new String(bytesEncoded);
		                    	employee.setDoc5(saveFile);

		                    } 
		                }else if (fieldName.equalsIgnoreCase("employeedoc6")) {
		                    String fileName = (DataUtil.emptyString(item.getName()));
		                    String fileValue = (DataUtil.emptyString(item.getString()));
		                    if (!fileName.equalsIgnoreCase("")) {
		                    	// encode data on your side using BASE64
		                    	byte[]   bytesEncoded = Base64.encodeBase64(item.get());
		                    	String saveFile = new String(bytesEncoded);
		                    	employee.setDoc6(saveFile);

		                    } 
		                }else if (fieldName.equalsIgnoreCase("employeedoc7")) {
		                    String fileName = (DataUtil.emptyString(item.getName()));
		                    String fileValue = (DataUtil.emptyString(item.getString()));
		                    if (!fileName.equalsIgnoreCase("")) {
		                    	// encode data on your side using BASE64
		                    	byte[]   bytesEncoded = Base64.encodeBase64(item.get());
		                    	String saveFile = new String(bytesEncoded);
		                    	employee.setDoc7(saveFile);

		                    } 
		                }else if (fieldName.equalsIgnoreCase("employeedoc8")) {
		                    String fileName = (DataUtil.emptyString(item.getName()));
		                    String fileValue = (DataUtil.emptyString(item.getString()));
		                    if (!fileName.equalsIgnoreCase("")) {
		                    	// encode data on your side using BASE64
		                    	byte[]   bytesEncoded = Base64.encodeBase64(item.get());
		                    	String saveFile = new String(bytesEncoded);
		                    	employee.setDoc8(saveFile);

		                    } 
		                }else if (fieldName.equalsIgnoreCase("employeedoc9")) {
		                    String fileName = (DataUtil.emptyString(item.getName()));
		                    String fileValue = (DataUtil.emptyString(item.getString()));
		                    if (!fileName.equalsIgnoreCase("")) {
		                    	// encode data on your side using BASE64
		                    	byte[]   bytesEncoded = Base64.encodeBase64(item.get());
		                    	String saveFile = new String(bytesEncoded);
		                    	employee.setDoc9(saveFile);

		                    } 
		                }else if (fieldName.equalsIgnoreCase("employeedoc10")) {
		                    String fileName = (DataUtil.emptyString(item.getName()));
		                    String fileValue = (DataUtil.emptyString(item.getString()));
		                    if (!fileName.equalsIgnoreCase("")) {
		                    	// encode data on your side using BASE64
		                    	byte[]   bytesEncoded = Base64.encodeBase64(item.get());
		                    	String saveFile = new String(bytesEncoded);
		                    	employee.setDoc10(saveFile);

		                    } 
		                }
		                
		                //End Student Docs
		            }
					
				}
				
			}catch (Exception e) {
				// TODO: handle exception
			}
		
		
		//Attach Documents 
		
		final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		int count =4;
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
		int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
		builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		employee.setTeacherexternalid(builder.toString());
		employee.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		
		if(new EmployeeDAO().create(employee)){
			if(new UserService(request, response).addUser(employee)){
				return true;
			}else{
				new EmployeeDAO().delete(employee);
			}
		}
		}
		return false;
	}

	public boolean ViewAllEmployee() {
		
		boolean result = false;
    try {
    	List<Teacher> list = new EmployeeDAO().readListOfObjects(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
        httpSession.setAttribute("employeeList", list);
        httpSession.setAttribute("employeeListProcessSalary", list);
        result = true;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return result;
}

	public boolean viewDetailsEmployee() {
		 boolean result = false;
	        try {
	            long id = Long.parseLong(request.getParameter("id"));
	            Teacher employee = new EmployeeDAO().readUniqueObject(id);
	            Login employeeLogin = new UserDAO().getUserDetails(employee.getTeacherexternalid());
	           
	            if (employee.getTid() != null) {
	            	httpSession.setAttribute("employee", employee);
	                request.setAttribute("stafflogin", employeeLogin);
	                return true;
	            } 
	        } catch (Exception e) {
	            e.printStackTrace();
	            result = false;
	        }
	        return result;
	}

	public String updateEmployee() {
		Teacher employee = new Teacher();
		
		
        String id = "";
        String employeedoc1update=null,employeedoc2update=null,employeedoc3update=null,employeedoc4update=null,
				employeedoc5update=null,employeedoc6update=null,employeedoc7update=null,employeedoc8update=null,employeedoc9update=null,employeedoc10update=null;
        
        int employeeId = 0;
        int parentsId = 0;
        
    	try {
			List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			
			for (FileItem item : items) {
				

				if (item.isFormField()) {
					String fieldName = item.getFieldName();
					
					 if (fieldName.equalsIgnoreCase("id")) {	                
			                id = DataUtil.emptyString(item.getString());
			                
			                }
					 
					 	else if (fieldName.equalsIgnoreCase("name")) {
					 		employee.setTeachername(DataUtil.emptyString(item.getString()));

		                }

		        		else if (fieldName.equalsIgnoreCase("gender")) {
		        			employee.setGender(DataUtil.emptyString(item.getString()));

		                }
					 
		        		else if (fieldName.equalsIgnoreCase("address")) {
		        			employee.setAddress(DataUtil.emptyString(item.getString()));

		                }

					 
		        		else if (fieldName.equalsIgnoreCase("contactnumber")) {
		        			employee.setContactnumber(DataUtil.emptyString(item.getString()));

		                }

					 
		        		else if (fieldName.equalsIgnoreCase("email")) {
		        			employee.setEmail(DataUtil.emptyString(item.getString()));

		                }

					 
		        		else if (fieldName.equalsIgnoreCase("aadharnumber")) {
		        			employee.setAadharnumber(DataUtil.emptyString(item.getString()));

		                }

					 
		        		else if (fieldName.equalsIgnoreCase("pan")) {
		        			employee.setPan(DataUtil.emptyString(item.getString()));

		                }

		        		else if (fieldName.equalsIgnoreCase("dateofjoining")) {
		        			employee.setDateofjoining(DateUtil.indiandateParser(item.getString()));

		                }
					 
		        		else if (fieldName.equalsIgnoreCase("totalexperience")) {
		        			employee.setTotalexperience(DataUtil.emptyString(item.getString()));

		                }
					 
		        		else if (fieldName.equalsIgnoreCase("qualification")) {
		        			employee.setQualification(DataUtil.emptyString(item.getString()));

		                }
					 
		        		else if (fieldName.equalsIgnoreCase("department")) {
		        			employee.setDepartment(DataUtil.emptyString(item.getString()));

		                }
					 
		        		else if (fieldName.equalsIgnoreCase("designation")) {
		        			employee.setDesignation(DataUtil.emptyString(item.getString()));

		                }
					 
		        		else if (fieldName.equalsIgnoreCase("salary")) {
		        			employee.setSalary(DataUtil.emptyString(item.getString()));

		                }
					 
		        		else if (fieldName.equalsIgnoreCase("remarks")) {
		        			employee.setRemarks(DataUtil.emptyString(item.getString()));

		                }
					 
		        		else if (fieldName.equalsIgnoreCase("teacherexternalid")) {
		        			employee.setTeacherexternalid(DataUtil.emptyString(item.getString()));

		                }
					 
		        		else if (fieldName.equalsIgnoreCase("branchid")) {
		        			employee.setBranchid(DataUtil.parseInt(item.getString()));

		                }
					 
		        		else if (fieldName.equalsIgnoreCase("currentemployee")) {
		        			employee.setCurrentemployee(DataUtil.emptyString(item.getString()));

		                }
					 
		        		else if (fieldName.equalsIgnoreCase("bankname")) {
		        			employee.setBankname(DataUtil.emptyString(item.getString()));

		                }
					 
		        		else if (fieldName.equalsIgnoreCase("bankifsc")) {
		        			employee.setBankifsc(DataUtil.emptyString(item.getString()));

		                }
					 
		        		else if (fieldName.equalsIgnoreCase("accno")) {
		        			employee.setAccno(DataUtil.emptyString(item.getString()));

		                }
					 
		        		else if (fieldName.equalsIgnoreCase("leavingdate")) {
		        			employee.setDateofjoining(DateUtil.indiandateParser(item.getString()));

		                }
					 
					 // Student Documents
		                
		                else if(fieldName.equalsIgnoreCase("employeedoc1update")){
			                	employeedoc1update=DataUtil.emptyString(item.getString());
			                }
			                
		                else if(fieldName.equalsIgnoreCase("employeedoc2update")){
			                	employeedoc2update=DataUtil.emptyString(item.getString());
			                }
			                
		                else if(fieldName.equalsIgnoreCase("employeedoc3update")){
			                	employeedoc3update=DataUtil.emptyString(item.getString());
			                }
			                
		                else if(fieldName.equalsIgnoreCase("employeedoc4update")){
			                	employeedoc4update=DataUtil.emptyString(item.getString());
			                }
			                
		                else if(fieldName.equalsIgnoreCase("employeedoc5update")){
			                	employeedoc5update=DataUtil.emptyString(item.getString());
			                }
			                
		                else if(fieldName.equalsIgnoreCase("employeedoc6update")){
			                	employeedoc6update=DataUtil.emptyString(item.getString());
			                }
			                
		                else if(fieldName.equalsIgnoreCase("employeedoc7update")){
			                	employeedoc7update=DataUtil.emptyString(item.getString());
			                }
			                
		                else if(fieldName.equalsIgnoreCase("employeedoc8update")){
			                	employeedoc8update=DataUtil.emptyString(item.getString());
			                }
			                
		                else if(fieldName.equalsIgnoreCase("employeedoc9update")){
			                	employeedoc9update=DataUtil.emptyString(item.getString());
			                }
			                
		                else if(fieldName.equalsIgnoreCase("employeedoc10update")){
			                	employeedoc10update=DataUtil.emptyString(item.getString());
			                }
			                
					 
				} else {
	                String fieldName = item.getFieldName();

	                //employee Docs
	                
	                if (fieldName.equalsIgnoreCase("employeedoc1")) {

	                    String fileName = (DataUtil.emptyString(item.getName()));
	                    if (!fileName.equalsIgnoreCase("")) {
	                    	// encode data on your side using BASE64
	                    	byte[]   bytesEncoded = Base64.encodeBase64(item.get());
	                    	String saveFile = new String(bytesEncoded);
	                    	employee.setDoc1(saveFile);
	                    }else{
	                    	employee.setDoc1(employeedoc1update);
	                    } 
	                }else if (fieldName.equalsIgnoreCase("employeedoc2")) {

	                    String fileName = (DataUtil.emptyString(item.getName()));
	                    if (!fileName.equalsIgnoreCase("")) {
	                    	// encode data on your side using BASE64
	                    	byte[]   bytesEncoded = Base64.encodeBase64(item.get());
	                    	String saveFile = new String(bytesEncoded);
	                    	employee.setDoc2(saveFile);

	                    }else{
	                    	
	                    	employee.setDoc2(employeedoc2update);
	                    } 
	                }else if (fieldName.equalsIgnoreCase("employeedoc3")) {

	                    String fileName = (DataUtil.emptyString(item.getName()));
	                    if (!fileName.equalsIgnoreCase("")) {
	                    	// encode data on your side using BASE64
	                    	byte[]   bytesEncoded = Base64.encodeBase64(item.get());
	                    	String saveFile = new String(bytesEncoded);
	                    	employee.setDoc3(saveFile);

	                    }else{
	                    	employee.setDoc3(employeedoc3update);
	                    }  
	                }else if (fieldName.equalsIgnoreCase("employeedoc4")) {

	                    String fileName = (DataUtil.emptyString(item.getName()));
	                    if (!fileName.equalsIgnoreCase("")) {
	                    	// encode data on your side using BASE64
	                    	byte[]   bytesEncoded = Base64.encodeBase64(item.get());
	                    	String saveFile = new String(bytesEncoded);
	                    	employee.setDoc4(saveFile);

	                    }else{
	                    	employee.setDoc4(employeedoc4update);
	                    }  
	                }else if (fieldName.equalsIgnoreCase("employeedoc5")) {

	                    String fileName = (DataUtil.emptyString(item.getName()));
	                    if (!fileName.equalsIgnoreCase("")) {
	                    	// encode data on your side using BASE64
	                    	byte[]   bytesEncoded = Base64.encodeBase64(item.get());
	                    	String saveFile = new String(bytesEncoded);
	                    	employee.setDoc5(saveFile);

	                    } else{
	                    	employee.setDoc5(employeedoc5update);
	                    } 
	                }else if (fieldName.equalsIgnoreCase("employeedoc6")) {

	                    String fileName = (DataUtil.emptyString(item.getName()));
	                    if (!fileName.equalsIgnoreCase("")) {
	                    	// encode data on your side using BASE64
	                    	byte[]   bytesEncoded = Base64.encodeBase64(item.get());
	                    	String saveFile = new String(bytesEncoded);
	                    	employee.setDoc6(saveFile);

	                    } else{
	                    	employee.setDoc6(employeedoc6update);
	                    } 
	                }else if (fieldName.equalsIgnoreCase("employeedoc7")) {

	                    String fileName = (DataUtil.emptyString(item.getName()));
	                    if (!fileName.equalsIgnoreCase("")) {
	                    	// encode data on your side using BASE64
	                    	byte[]   bytesEncoded = Base64.encodeBase64(item.get());
	                    	String saveFile = new String(bytesEncoded);
	                    	employee.setDoc7(saveFile);

	                    } else{
	                    	employee.setDoc7(employeedoc7update);
	                    } 
	                }else if (fieldName.equalsIgnoreCase("employeedoc8")) {

	                    String fileName = (DataUtil.emptyString(item.getName()));
	                    if (!fileName.equalsIgnoreCase("")) {
	                    	// encode data on your side using BASE64
	                    	byte[]   bytesEncoded = Base64.encodeBase64(item.get());
	                    	String saveFile = new String(bytesEncoded);
	                    	employee.setDoc8(saveFile);

	                    } else{
	                    	employee.setDoc8(employeedoc8update);
	                    } 
	                }else if (fieldName.equalsIgnoreCase("employeedoc9")) {

	                    String fileName = (DataUtil.emptyString(item.getName()));
	                    if (!fileName.equalsIgnoreCase("")) {
	                    	// encode data on your side using BASE64
	                    	byte[]   bytesEncoded = Base64.encodeBase64(item.get());
	                    	String saveFile = new String(bytesEncoded);
	                    	employee.setDoc9(saveFile);

	                    } else{
	                    	employee.setDoc9(employeedoc9update);
	                    } 
	                }else if (fieldName.equalsIgnoreCase("employeedoc10")) {

	                    String fileName = (DataUtil.emptyString(item.getName()));
	                    if (!fileName.equalsIgnoreCase("")) {
	                    	// encode data on your side using BASE64
	                    	byte[]   bytesEncoded = Base64.encodeBase64(item.get());
	                    	String saveFile = new String(bytesEncoded);
	                    	employee.setDoc10(saveFile);

	                    } else{
	                    	employee.setDoc10(employeedoc10update);
	                    } 
	                }
	                
	                //End Student Docs
	            }
			}}catch (Exception e) {
				e.getStackTrace();
			}
        
    	employeeId = Integer.parseInt(id);
        employee.setTid(employeeId);
		employee = new EmployeeDAO().update(employee);
				
        String empId = employee.getTid().toString();
        return empId;
	}

	public void deleteMultiple() {
		 String[] employeeIds = request.getParameterValues("employeeIDs");
		 if(employeeIds!=null){
	        List ids = new ArrayList();
	        for (String id : employeeIds) {
	            System.out.println("id" + id);
	            ids.add(Integer.valueOf(id));

	        }
	        System.out.println("id length" + employeeIds.length);
	        new EmployeeDAO().deleteMultiple(ids);
		 }
	}

	public void viewAllRelations() {
		
		if(httpSession.getAttribute(BRANCHID)!=null) {
			List<Department> listDepartment = new departmentDAO().readListOfObjects(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
	        httpSession.setAttribute("listDepartment", listDepartment);
	        List<Position> listPosition = new positionDAO().readListOfObjects(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
	        httpSession.setAttribute("listPosition", listPosition);
		}
	}

	public void searchEmployee() {
		String staffName = request.getParameter("staffName");
		String staffDepartment = request.getParameter("staffDepartment");
		List<Teacher> employeeList = new ArrayList<Teacher>();
		
		if(httpSession.getAttribute(BRANCHID)!=null){
			if(staffName!=""){
				employeeList = new EmployeeDAO().readListOfEmployeesByName(staffName, Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			}else if(staffDepartment!=""){
				employeeList = new EmployeeDAO().readListOfEmployeesByDepartment(staffDepartment, Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			}else {
				employeeList = new EmployeeDAO().readListOfEmployeesBasicPay(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			}
		}
		
		request.setAttribute("searchedemployeeList", employeeList);
		
		new EmployeeService(request, response).ViewAllEmployee();
	}

	public void basicpayEmployees() {
		List<Paybasic> employeeList = new ArrayList<Paybasic>();
		
		if(httpSession.getAttribute(BRANCHID)!=null){
				employeeList = new EmployeeDAO().readListOfEmployeesBasicPayDetails(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		}
		request.setAttribute("vieweditbasicpay", employeeList);
	}

	public void viewDepartments() {
		
		if(httpSession.getAttribute(BRANCHID)!=null) {
			List<Department> listDepartment = new departmentDAO().readListOfObjects(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
	        httpSession.setAttribute("listDepartment", listDepartment);
		}
	}
}
