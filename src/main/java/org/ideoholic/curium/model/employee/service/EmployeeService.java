package org.ideoholic.curium.model.employee.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.ideoholic.curium.model.department.dao.departmentDAO;
import org.ideoholic.curium.model.department.dto.Department;
import org.ideoholic.curium.model.employee.dao.EmployeeDAO;
import org.ideoholic.curium.model.employee.dto.Teacher;
import org.ideoholic.curium.model.hr.dto.Paybasic;
import org.ideoholic.curium.model.position.dao.positionDAO;
import org.ideoholic.curium.model.position.dto.Position;
import org.ideoholic.curium.model.printids.dao.PrintIdsDAO;
import org.ideoholic.curium.model.user.dao.UserDAO;
import org.ideoholic.curium.model.user.dto.Login;
import org.ideoholic.curium.model.user.service.UserService;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.DateUtil;
import org.springframework.web.multipart.MultipartFile;

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

	public boolean addEmployee(MultipartFile[] listOfFiles) {
		Teacher employee = new Teacher();
		
		try {
			Enumeration<String> enumeration = request.getParameterNames();
		
			 while (enumeration.hasMoreElements()) {
		                // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
		                String fieldName = enumeration.nextElement();

		                if (fieldName.equalsIgnoreCase("name")) {
		                	employee.setTeachername(DataUtil.emptyString(request.getParameter(fieldName)));
		                }

		                
		                if (fieldName.equalsIgnoreCase("gender")) {
		                	employee.setGender(DataUtil.emptyString(request.getParameter(fieldName)));

		                }

		                if (fieldName.equalsIgnoreCase("address")) {
		            		employee.setAddress(DataUtil.emptyString(request.getParameter(fieldName)));

		                }

		                if (fieldName.equalsIgnoreCase("contactnumber")) {
		            		employee.setContactnumber(DataUtil.emptyString(request.getParameter(fieldName)));
		                }
		                
		                if (fieldName.equalsIgnoreCase("email")) {
		                	employee.setEmail(DataUtil.emptyString(request.getParameter(fieldName)));
		                }
		                
		                if (fieldName.equalsIgnoreCase("dateofjoining")) {
		                	employee.setDateofjoining(DateUtil.indiandateParser(request.getParameter(fieldName)));
		                }
		                
		                if (fieldName.equalsIgnoreCase("totalexperience")) {
		                	employee.setTotalexperience(DataUtil.emptyString(request.getParameter(fieldName)));
		                }
		                
		                if (fieldName.equalsIgnoreCase("qualification")) {
		                	employee.setQualification(DataUtil.emptyString(request.getParameter(fieldName)));
		                }
		                
		                if (fieldName.equalsIgnoreCase("department")) {
		                	employee.setDepartment(DataUtil.emptyString(request.getParameter(fieldName)));
		                }
		                
		                if (fieldName.equalsIgnoreCase("designation")) {
		                	employee.setDesignation(DataUtil.emptyString(request.getParameter(fieldName)));
		                }
		                
		                if (fieldName.equalsIgnoreCase("salary")) {
		                	employee.setSalary(DataUtil.emptyString(request.getParameter(fieldName)));
		                }
		                
		                if (fieldName.equalsIgnoreCase("remarks")) {
		                	employee.setRemarks(DataUtil.emptyString(request.getParameter(fieldName)));
		                }
		                
		                if (fieldName.equalsIgnoreCase("currentemployee")) {
		                	employee.setCurrentemployee(DataUtil.emptyString(request.getParameter(fieldName)));
		                }
		                
		                if (fieldName.equalsIgnoreCase("joiningdate")) {
		                	employee.setJoiningdate(DateUtil.indiandateParser(request.getParameter(fieldName)));
		                }
		                
		                //Bank Details
		                if (fieldName.equalsIgnoreCase("bankname")) {
		                	employee.setBankname(DataUtil.emptyString(request.getParameter(fieldName)));
		                }
		                if (fieldName.equalsIgnoreCase("bankifsc")) {
		                	employee.setBankifsc(DataUtil.emptyString(request.getParameter(fieldName)));
		                }
		                if (fieldName.equalsIgnoreCase("accno")) {
		                	employee.setAccno(DataUtil.emptyString(request.getParameter(fieldName)));
		                }
		                //End Bank Details
		            }
			// Process form file field (input type="file")
			 if(listOfFiles != null && listOfFiles.length != 0) 
				 {
						 
						 MultipartFile fileItem1 = listOfFiles[0];
						  String fileName1 = (DataUtil.emptyString(fileItem1.getOriginalFilename()));
		                    
		                    if (!fileName1.equalsIgnoreCase("")) {
		                    	byte[]   bytesEncoded = Base64.encodeBase64(fileItem1.getBytes());
		                    	String saveFile = new String(bytesEncoded);
		                    	employee.setEmployeephoto(saveFile);
		                    }
		                    
		                    //Employee Docs
		                    MultipartFile fileItem2 = listOfFiles[1];
							String fileName2 = (DataUtil.emptyString(fileItem2.getOriginalFilename()));
			                
			                    if (!fileName2.equalsIgnoreCase("")) {
			                    	// encode data on your side using BASE64
			                    	byte[]   bytesEncoded = Base64.encodeBase64(fileItem2.getBytes());
			                    	String saveFile = new String(bytesEncoded);
			                    	employee.setEmployeedoc1(saveFile);

			                    } 
			                
			                MultipartFile fileItem3 = listOfFiles[2];
			                String fileName3 = (DataUtil.emptyString(fileItem3.getOriginalFilename()));
			                
			                    if (!fileName3.equalsIgnoreCase("")) {
			                    	// encode data on your side using BASE64
			                    	byte[]   bytesEncoded = Base64.encodeBase64(fileItem3.getBytes());
			                    	String saveFile = new String(bytesEncoded);
			                    	employee.setEmployeedoc2(saveFile);

			                    } 
			                
			                
			                MultipartFile fileItem4 = listOfFiles[3];
							String fileName4 = (DataUtil.emptyString(fileItem4.getOriginalFilename()));
			                
			                    if (!fileName4.equalsIgnoreCase("")) {
			                    	// encode data on your side using BASE64
			                    	byte[]   bytesEncoded = Base64.encodeBase64(fileItem4.getBytes());
			                    	String saveFile = new String(bytesEncoded);
			                    	employee.setEmployeedoc3(saveFile);

			                    } 
			                
			                MultipartFile fileItem5 = listOfFiles[4];
			                	String fileName5 = (DataUtil.emptyString(fileItem5.getOriginalFilename()));
			                    if (!fileName5.equalsIgnoreCase("")) {
			                    	// encode data on your side using BASE64
			                    	byte[]   bytesEncoded = Base64.encodeBase64(fileItem5.getBytes());
			                    	String saveFile = new String(bytesEncoded);
			                    	employee.setEmployeedoc4(saveFile);

			                    } 
			                
			                MultipartFile fileItem6 = listOfFiles[5];

			                	String fileName6 = (DataUtil.emptyString(fileItem6.getOriginalFilename()));
			                    if (!fileName6.equalsIgnoreCase("")) {
			                    	// encode data on your side using BASE64
			                    	byte[]   bytesEncoded = Base64.encodeBase64(fileItem6.getBytes());
			                    	String saveFile = new String(bytesEncoded);
			                    	employee.setEmployeedoc5(saveFile);
			                    } 
			                
			                //End Employee Docs
				 }

		} catch (IOException e) {
			e.printStackTrace();
		}
		int branchid = Integer.parseInt(httpSession.getAttribute(BRANCHID).toString());
		if(branchid==2) {
			employee.setTeacherexternalid(httpSession.getAttribute("branchcode").toString());
		}else if(branchid==3) {
			employee.setTeacherexternalid("3"+httpSession.getAttribute("branchcode").toString());
		}
		
		employee.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		
		if(new EmployeeDAO().create(employee)){
			if(new UserService(request, response).addUser(employee)){
				return true;
			}else{
				new EmployeeDAO().delete(employee);
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

	public String updateEmployee(MultipartFile[] listOfFiles) {
		Teacher employee = new Teacher();
		String id = "";
		int employeeId = 0;
		String employeePhotoUpdate=null;
		String employeePhotodelete=null;
		String employeedoc1Update=null;
		String employeedoc2Update=null;
		String employeedoc3Update=null;
		String employeedoc4Update=null;
		String employeedoc5Update=null;
		String employeedoc1delete=null;
		String employeedoc2delete=null;
		String employeedoc3delete=null;
		String employeedoc4delete=null;
		String employeedoc5delete=null;
		
		try {
			Enumeration<String> enumeration = request.getParameterNames();
			

			 while (enumeration.hasMoreElements()) {
		                // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
		                String fieldName = enumeration.nextElement();

		                if (fieldName.equalsIgnoreCase("id")) {	                
	    	                id = DataUtil.emptyString(DataUtil.emptyString(request.getParameter(fieldName)));
	    	                
	    	                }
	    	                         		
	    	        		if(id!=""){
	    	        			employeeId = Integer.parseInt(id);
	    	        			employee.setTid(employeeId);
	    	        		}
	    	        		
	    	        		
		                if (fieldName.equalsIgnoreCase("name")) {
		                	employee.setTeachername(DataUtil.emptyString(request.getParameter(fieldName)));
		                }

		                
		                if (fieldName.equalsIgnoreCase("gender")) {
		                	employee.setGender(DataUtil.emptyString(request.getParameter(fieldName)));

		                }

		                if (fieldName.equalsIgnoreCase("address")) {
		            		employee.setAddress(DataUtil.emptyString(request.getParameter(fieldName)));

		                }

		                if (fieldName.equalsIgnoreCase("contactnumber")) {
		            		employee.setContactnumber(DataUtil.emptyString(request.getParameter(fieldName)));
		                }
		                
		                if (fieldName.equalsIgnoreCase("email")) {
		                	employee.setEmail(DataUtil.emptyString(request.getParameter(fieldName)));
		                }
		                
		                if (fieldName.equalsIgnoreCase("dateofjoining")) {
		                	employee.setDateofjoining(DateUtil.indiandateParser(request.getParameter(fieldName)));
		                }
		                
		                if (fieldName.equalsIgnoreCase("totalexperience")) {
		                	employee.setTotalexperience(DataUtil.emptyString(request.getParameter(fieldName)));
		                }
		                
		                if (fieldName.equalsIgnoreCase("qualification")) {
		                	employee.setQualification(DataUtil.emptyString(request.getParameter(fieldName)));
		                }
		                
		                if (fieldName.equalsIgnoreCase("department")) {
		                	employee.setDepartment(DataUtil.emptyString(request.getParameter(fieldName)));
		                }
		                
		                if (fieldName.equalsIgnoreCase("designation")) {
		                	employee.setDesignation(DataUtil.emptyString(request.getParameter(fieldName)));
		                }
		                
		                if (fieldName.equalsIgnoreCase("salary")) {
		                	employee.setSalary(DataUtil.emptyString(request.getParameter(fieldName)));
		                }
		                
		                if (fieldName.equalsIgnoreCase("remarks")) {
		                	employee.setRemarks(DataUtil.emptyString(request.getParameter(fieldName)));
		                }
		                
		                if (fieldName.equalsIgnoreCase("currentemployee")) {
		                	employee.setCurrentemployee(DataUtil.emptyString(request.getParameter(fieldName)));
		                }
		                
		                if (fieldName.equalsIgnoreCase("teacherexternalid")) {
    	                	employee.setTeacherexternalid(DataUtil.emptyString(request.getParameter(fieldName)));
    	                }
    	                
    	                if (fieldName.equalsIgnoreCase("branchid")) {
    	                	employee.setBranchid(Integer.parseInt(DataUtil.emptyString(request.getParameter(fieldName))));
    	                }
    	                
    	                if (fieldName.equalsIgnoreCase("leavingdate")) {

    	                	employee.setLeavingdate(DateUtil.indiandateParser(request.getParameter(fieldName)));

    	                }
    	                
    	                if (fieldName.equalsIgnoreCase("joiningdate")) {

    	                	employee.setJoiningdate(DateUtil.indiandateParser(request.getParameter(fieldName)));

    	                }
		                
		                //Bank Details
		                if (fieldName.equalsIgnoreCase("bankname")) {
		                	employee.setBankname(DataUtil.emptyString(request.getParameter(fieldName)));
		                }
		                if (fieldName.equalsIgnoreCase("bankifsc")) {
		                	employee.setBankifsc(DataUtil.emptyString(request.getParameter(fieldName)));
		                }
		                if (fieldName.equalsIgnoreCase("accno")) {
		                	employee.setAccno(DataUtil.emptyString(request.getParameter(fieldName)));
		                }
		                //End Bank Details
		                
		                if(fieldName.equalsIgnoreCase("employeephotoupdate")){
    	                	employeePhotoUpdate=DataUtil.emptyString(request.getParameter(fieldName));
    	                }
    	                
    	                if(fieldName.equalsIgnoreCase("employeedoc1update")){
    	                	employeedoc1Update=DataUtil.emptyString(request.getParameter(fieldName));
    	                }
    	                
    	                if(fieldName.equalsIgnoreCase("employeedoc2update")){
    	                	employeedoc2Update=DataUtil.emptyString(request.getParameter(fieldName));
    	                }
    	                
    	                if(fieldName.equalsIgnoreCase("employeedoc3update")){
    	                	employeedoc3Update=DataUtil.emptyString(request.getParameter(fieldName));
    	                }
    	                
    	                if(fieldName.equalsIgnoreCase("employeedoc4update")){
    	                	employeedoc4Update=DataUtil.emptyString(request.getParameter(fieldName));
    	                }
    	                
    	                if(fieldName.equalsIgnoreCase("employeedoc5update")){
    	                	employeedoc5Update=DataUtil.emptyString(request.getParameter(fieldName));
    	                }
    	                
    	                if (fieldName.equalsIgnoreCase("employeedoc1delete")) {
		                	employeedoc1delete=DataUtil.emptyString(request.getParameter(fieldName));
		                }
		                
		                if (fieldName.equalsIgnoreCase("employeedoc2delete")) {
		                	employeedoc2delete=DataUtil.emptyString(request.getParameter(fieldName));
		                }
		                
		                if (fieldName.equalsIgnoreCase("employeedoc3delete")) {
		                	employeedoc3delete=DataUtil.emptyString(request.getParameter(fieldName));
		                }
		                
		                if (fieldName.equalsIgnoreCase("employeedoc4delete")) {
		                	employeedoc4delete=DataUtil.emptyString(request.getParameter(fieldName));
		                }
		                
		                if (fieldName.equalsIgnoreCase("employeedoc5delete")) {
		                	employeedoc5delete=DataUtil.emptyString(request.getParameter(fieldName));
		                }
		                
		            }
			// Process form file field (input type="file")
			 if(listOfFiles != null && listOfFiles.length != 0) 
				 {
						 
						 MultipartFile fileItem1 = listOfFiles[0];
						  String fileName1 = (DataUtil.emptyString(fileItem1.getOriginalFilename()));
		                    
		                    if (!fileName1.equalsIgnoreCase("")) {
		                    	byte[]   bytesEncoded = Base64.encodeBase64(fileItem1.getBytes());
		                    	String saveFile = new String(bytesEncoded);
		                    	employee.setEmployeephoto(saveFile);
		                    }else{
    	                    	if(employeePhotodelete!=null) {
    	                    		employee.setEmployeephoto(null);
    	                    	}else {
    	                    		employee.setEmployeephoto(employeePhotoUpdate);
    	                    	}
    	                    }
		                    
		                    //Employee Docs
		                    MultipartFile fileItem2 = listOfFiles[1];
							String fileName2 = (DataUtil.emptyString(fileItem2.getOriginalFilename()));
			                
			                    if (!fileName2.equalsIgnoreCase("")) {
			                    	// encode data on your side using BASE64
			                    	byte[]   bytesEncoded = Base64.encodeBase64(fileItem2.getBytes());
			                    	String saveFile = new String(bytesEncoded);
			                    	employee.setEmployeedoc1(saveFile);

			                    } else{
	    	                    	if(employeedoc1delete!=null) {
	    	                    		employee.setEmployeedoc1(null);
	    	                    	}else {
	    	                    		employee.setEmployeedoc1(employeedoc1Update);
	    	                    	}
	    	                    } 
			                
			                MultipartFile fileItem3 = listOfFiles[2];
			                String fileName3 = (DataUtil.emptyString(fileItem3.getOriginalFilename()));
			                
			                    if (!fileName3.equalsIgnoreCase("")) {
			                    	// encode data on your side using BASE64
			                    	byte[]   bytesEncoded = Base64.encodeBase64(fileItem3.getBytes());
			                    	String saveFile = new String(bytesEncoded);
			                    	employee.setEmployeedoc2(saveFile);

			                    }else{
	    	                    	if(employeedoc2delete!=null) {
	    	                    		employee.setEmployeedoc2(null);
	    	                    	}else {
	    	                    		employee.setEmployeedoc2(employeedoc2Update);
	    	                    	}
	    	                    } 
			                
			                
			                MultipartFile fileItem4 = listOfFiles[3];
							String fileName4 = (DataUtil.emptyString(fileItem4.getOriginalFilename()));
			                
			                    if (!fileName4.equalsIgnoreCase("")) {
			                    	// encode data on your side using BASE64
			                    	byte[]   bytesEncoded = Base64.encodeBase64(fileItem4.getBytes());
			                    	String saveFile = new String(bytesEncoded);
			                    	employee.setEmployeedoc3(saveFile);

			                    } else{
	    	                    	if(employeedoc3delete!=null) {
	    	                    		employee.setEmployeedoc3(null);
	    	                    	}else {
	    	                    		employee.setEmployeedoc3(employeedoc3Update);
	    	                    	}
	    	                    }
			                
			                MultipartFile fileItem5 = listOfFiles[4];
			                	String fileName5 = (DataUtil.emptyString(fileItem5.getOriginalFilename()));
			                    if (!fileName5.equalsIgnoreCase("")) {
			                    	// encode data on your side using BASE64
			                    	byte[]   bytesEncoded = Base64.encodeBase64(fileItem5.getBytes());
			                    	String saveFile = new String(bytesEncoded);
			                    	employee.setEmployeedoc4(saveFile);

			                    }else{
	    	                    	if(employeedoc4delete!=null) {
	    	                    		employee.setEmployeedoc4(null);
	    	                    	}else {
	    	                    		employee.setEmployeedoc4(employeedoc4Update);
	    	                    	}
	    	                    } 
			                
			                MultipartFile fileItem6 = listOfFiles[5];

			                	String fileName6 = (DataUtil.emptyString(fileItem6.getOriginalFilename()));
			                    if (!fileName6.equalsIgnoreCase("")) {
			                    	// encode data on your side using BASE64
			                    	byte[]   bytesEncoded = Base64.encodeBase64(fileItem6.getBytes());
			                    	String saveFile = new String(bytesEncoded);
			                    	employee.setEmployeedoc5(saveFile);
			                    }  else{
	    	                    	if(employeedoc5delete!=null) {
	    	                    		employee.setEmployeedoc5(null);
	    	                    	}else {
	    	                    		employee.setEmployeedoc5(employeedoc5Update);
	    	                    	}
	    	                    }
			                
			                //End Employee Docs
				 }

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		employee = new EmployeeDAO().update(employee);
				
        String empId = employee.getTid().toString();
        return empId;
	}

	public void deleteMultiple() {
		 String[] employeeIds = request.getParameterValues("employeeIDs");
		 if(employeeIds!=null){
	        List<Integer> ids = new ArrayList();
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

	public boolean printMultipleEmployees() {
		boolean result = false;
	    String[] studentIDs = request.getParameterValues("employeeIDs");
	    List<Long> ids = new ArrayList<Long>();
	    Teacher teacherDetails = new Teacher();
	 
	    	
	      int i = 1;
	   
	      for (String id : studentIDs) {

	          
	           System.out.println("Value of i is " + i);
	           int sid = Integer.valueOf(id);
	           teacherDetails = new PrintIdsDAO().printMultipleIdsEmployee(id);
	           
	           //PersonalDetails personal = new PersonalDetailsDAO().printMultiple(pid);

	           if (teacherDetails != null) {
	        	   httpSession.setAttribute("staffid" + i + "", teacherDetails.getTeacherexternalid());
	        	   httpSession.setAttribute("teachername" + i + "", teacherDetails.getTeachername());
	        	   httpSession.setAttribute("guardian" + i + "", teacherDetails.getRemarks());
	               httpSession.setAttribute("contactnumber" + i + "", teacherDetails.getContactnumber());
	               httpSession.setAttribute("designation" + i + "", teacherDetails.getDesignation());
	               httpSession.setAttribute("Address" + i + "", teacherDetails.getAddress());
	               httpSession.setAttribute("employeephoto" + i + "",teacherDetails.getEmployeephoto());
	               httpSession.setAttribute("dateofjoining" + i + "", DateUtil.dateParserddMMYYYY(teacherDetails.getDateofjoining()));
	               request.setAttribute("currentacadmicyear", httpSession.getAttribute("currentAcademicYear"));
	               //result = true;
	           } else {

	              
	               //result = false;
	           }

	           i++;
	       }
	   
	   httpSession.setAttribute("iInitial", i);
	   i = (int) (Math.ceil((float) (i) / 3));
	   httpSession.setAttribute("endValue", i);
	   
	   
	    if (teacherDetails == null) {
	        result = false;
	    } else {
	        result = true;
	    }
	    return result;

}

	public boolean viewDetailsEmployeeStaffLogin() {
		 boolean result = false;
	        try {
	            Teacher employee = new EmployeeDAO().getEmployeeDetails(httpSession.getAttribute("username").toString());
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
}
