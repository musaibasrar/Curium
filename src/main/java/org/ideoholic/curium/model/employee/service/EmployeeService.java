package org.ideoholic.curium.model.employee.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.ideoholic.curium.model.department.dao.departmentDAO;
import org.ideoholic.curium.model.department.dto.Department;
import org.ideoholic.curium.model.employee.dao.EmployeeDAO;
import org.ideoholic.curium.model.employee.dto.*;
import org.ideoholic.curium.model.hr.dto.Paybasic;
import org.ideoholic.curium.model.position.dao.positionDAO;
import org.ideoholic.curium.model.position.dto.Position;
import org.ideoholic.curium.model.printids.dao.PrintIdsDAO;
import org.ideoholic.curium.model.user.dao.UserDAO;
import org.ideoholic.curium.model.user.dto.Login;
import org.ideoholic.curium.model.user.service.UserService;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.DateUtil;
import org.ideoholic.curium.util.ResultResponse;
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

	public ResultResponse addEmployee(MultipartFile[] listOfFiles, EmployeeDto employeeDto, String branchId) {
		Teacher employee = new Teacher();
		
		try {

		                	employee.setTeachername(DataUtil.emptyString(employeeDto.getName()));
		                	employee.setGender(DataUtil.emptyString(employeeDto.getGender()));
							employee.setAddress(DataUtil.emptyString(employeeDto.getAddress()));
		            		employee.setContactnumber(DataUtil.emptyString(employeeDto.getContactNumber()));
		                	employee.setEmail(DataUtil.emptyString(employeeDto.getEmail()));
		                	employee.setDateofjoining(DateUtil.indiandateParser(employeeDto.getDateOfJoining()));
		                	employee.setTotalexperience(DataUtil.emptyString(employeeDto.getTotalExperience()));
		                	employee.setQualification(DataUtil.emptyString(employeeDto.getQualification()));
		                	employee.setDepartment(DataUtil.emptyString(employeeDto.getDepartment()));
		                	employee.setDesignation(DataUtil.emptyString(employeeDto.getDesignation()));
		                	employee.setSalary(DataUtil.emptyString(employeeDto.getSalary()));
		                	employee.setRemarks(DataUtil.emptyString(employeeDto.getRemarks()));
		                	employee.setCurrentemployee(DataUtil.emptyString(employeeDto.getCurrentEmployee()));
		                	employee.setJoiningdate(DateUtil.indiandateParser(employeeDto.getJoiningDate()));
		                	employee.setBankname(DataUtil.emptyString(employeeDto.getBankName()));
		                	employee.setBankifsc(DataUtil.emptyString(employeeDto.getBankIFSC()));
							employee.setAccno(DataUtil.emptyString(employeeDto.getAccNo()));
		                //End Bank Details

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
		
		employee.setTeacherexternalid(employeeDto.getBranchCode());
		employee.setBranchid(Integer.parseInt(branchId));
		
		if(new EmployeeDAO().create(employee)){
			if(new UserService(request, response,null).addUser(employee)){
				return ResultResponse.builder().success(true).build();
			}else{
				new EmployeeDAO().delete(employee);
			}
		}
		
		return ResultResponse.builder().build();
	}
//TODO: Naming Convention error in this method, method name should start from small alphabet.
	public ViewAllEmployeeResponseDto ViewAllEmployee(String branchId) {
		ViewAllEmployeeResponseDto viewAllEmployeeResponseDto = new ViewAllEmployeeResponseDto();
		
		boolean result = false;
    try {
    	List<Teacher> list = new EmployeeDAO().readListOfObjects(Integer.parseInt(branchId));
        viewAllEmployeeResponseDto.setEmployeeList(list);
        viewAllEmployeeResponseDto.setEmployeeListProcessSalary(list);
        viewAllEmployeeResponseDto.setSuccess(true);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return viewAllEmployeeResponseDto;
}

	public ViewDetailsEmployeeResponseDto viewDetailsEmployee() {
		ViewDetailsEmployeeResponseDto viewDetailsEmployeeResponseDto = new ViewDetailsEmployeeResponseDto();
		 boolean result = false;
	        try {
	            long id = Long.parseLong(request.getParameter("id"));
	            Teacher employee = new EmployeeDAO().readUniqueObject(id);
	            Login employeeLogin = new UserDAO().getUserDetails(employee.getTeacherexternalid());
	           
	            if (employee.getTid() != null) {
	            	viewDetailsEmployeeResponseDto.setEmployee(employee);
	                viewDetailsEmployeeResponseDto.setEmployeeLogin(employeeLogin);

	                viewDetailsEmployeeResponseDto.setSuccess(true);
	            } 
	        } catch (Exception e) {
	            e.printStackTrace();
	            viewDetailsEmployeeResponseDto.setSuccess(false);
	        }
	        return viewDetailsEmployeeResponseDto;
	}

	public Teacher updateEmployee(MultipartFile[] listOfFiles, EmployeeDto employeeDto) {
		Teacher employee = new Teacher();
		String id = "";
		int employeeId = 0;
		String employeePhotoUpdate = null;
		String employeePhotodelete = null;
		String employeedoc1Update = null;
		String employeedoc2Update = null;
		String employeedoc3Update = null;
		String employeedoc4Update = null;
		String employeedoc5Update = null;
		String employeedoc1delete = null;
		String employeedoc2delete = null;
		String employeedoc3delete = null;
		String employeedoc4delete = null;
		String employeedoc5delete = null;

		try {

			employee.setTeachername(DataUtil.emptyString(employeeDto.getName()));
			employee.setGender(DataUtil.emptyString(employeeDto.getGender()));
			employee.setAddress(DataUtil.emptyString(employeeDto.getAddress()));
			employee.setContactnumber(DataUtil.emptyString(employeeDto.getContactNumber()));
			employee.setEmail(DataUtil.emptyString(employeeDto.getEmail()));
			employee.setDateofjoining(DateUtil.indiandateParser(employeeDto.getDateOfJoining()));
			employee.setTotalexperience(DataUtil.emptyString(employeeDto.getTotalExperience()));
			employee.setQualification(DataUtil.emptyString(employeeDto.getQualification()));
			employee.setDepartment(DataUtil.emptyString(employeeDto.getDepartment()));
			employee.setDesignation(DataUtil.emptyString(employeeDto.getDesignation()));
			employee.setSalary(DataUtil.emptyString(employeeDto.getSalary()));
			employee.setRemarks(DataUtil.emptyString(employeeDto.getRemarks()));
			employee.setCurrentemployee(DataUtil.emptyString(employeeDto.getCurrentEmployee()));
			employee.setTeacherexternalid(DataUtil.emptyString(employeeDto.getTeacherExternalId()));
			employee.setBranchid(Integer.parseInt(DataUtil.emptyString(employeeDto.getBranchId())));
			employee.setLeavingdate(DateUtil.indiandateParser(employeeDto.getLeavingdate()));
			employee.setJoiningdate(DateUtil.indiandateParser(employeeDto.getJoiningDate()));
			//Bank Details

			employee.setBankname(DataUtil.emptyString(employeeDto.getBankName()));
			employee.setBankifsc(DataUtil.emptyString(employeeDto.getBankIFSC()));
			employee.setAccno(DataUtil.emptyString(employeeDto.getAccNo()));
			employeePhotoUpdate = DataUtil.emptyString(employeeDto.getEmployeephotoupdate());
			employeedoc1Update = DataUtil.emptyString(employeeDto.getEmployeedoc1update());
			employeedoc2Update = DataUtil.emptyString(employeeDto.getEmployeedoc2update());
			employeedoc3Update = DataUtil.emptyString(employeeDto.getEmployeedoc3update());
			employeedoc4Update = DataUtil.emptyString(employeeDto.getEmployeedoc4update());
			employeedoc5Update = DataUtil.emptyString(employeeDto.getEmployeedoc5update());
			employeedoc1delete = DataUtil.emptyString(employeeDto.getEmployeedoc1delete());
			employeedoc2delete = DataUtil.emptyString(employeeDto.getEmployeedoc2delete());
			employeedoc3delete = DataUtil.emptyString(employeeDto.getEmployeedoc3delete());
			employeedoc4delete = DataUtil.emptyString(employeeDto.getEmployeedoc4delete());
			employeedoc5delete = DataUtil.emptyString(employeeDto.getEmployeedoc5delete());

		// Process form file field (input type="file")
		if (listOfFiles != null && listOfFiles.length != 0) {

			MultipartFile fileItem1 = listOfFiles[0];
			String fileName1 = (DataUtil.emptyString(fileItem1.getOriginalFilename()));

			if (!fileName1.equalsIgnoreCase("")) {
				byte[] bytesEncoded = Base64.encodeBase64(fileItem1.getBytes());
				String saveFile = new String(bytesEncoded);
				employee.setEmployeephoto(saveFile);
			} else {
				if (employeePhotodelete != null) {
					employee.setEmployeephoto(null);
				} else {
					employee.setEmployeephoto(employeePhotoUpdate);
				}
			}

			//Employee Docs
			MultipartFile fileItem2 = listOfFiles[1];
			String fileName2 = (DataUtil.emptyString(fileItem2.getOriginalFilename()));

			if (!fileName2.equalsIgnoreCase("")) {
				// encode data on your side using BASE64
				byte[] bytesEncoded = Base64.encodeBase64(fileItem2.getBytes());
				String saveFile = new String(bytesEncoded);
				employee.setEmployeedoc1(saveFile);

			} else {
				if (employeedoc1delete != null) {
					employee.setEmployeedoc1(null);
				} else {
					employee.setEmployeedoc1(employeedoc1Update);
				}
			}

			MultipartFile fileItem3 = listOfFiles[2];
			String fileName3 = (DataUtil.emptyString(fileItem3.getOriginalFilename()));

			if (!fileName3.equalsIgnoreCase("")) {
				// encode data on your side using BASE64
				byte[] bytesEncoded = Base64.encodeBase64(fileItem3.getBytes());
				String saveFile = new String(bytesEncoded);
				employee.setEmployeedoc2(saveFile);

			} else {
				if (employeedoc2delete != null) {
					employee.setEmployeedoc2(null);
				} else {
					employee.setEmployeedoc2(employeedoc2Update);
				}
			}


			MultipartFile fileItem4 = listOfFiles[3];
			String fileName4 = (DataUtil.emptyString(fileItem4.getOriginalFilename()));

			if (!fileName4.equalsIgnoreCase("")) {
				// encode data on your side using BASE64
				byte[] bytesEncoded = Base64.encodeBase64(fileItem4.getBytes());
				String saveFile = new String(bytesEncoded);
				employee.setEmployeedoc3(saveFile);

			} else {
				if (employeedoc3delete != null) {
					employee.setEmployeedoc3(null);
				} else {
					employee.setEmployeedoc3(employeedoc3Update);
				}
			}

			MultipartFile fileItem5 = listOfFiles[4];
			String fileName5 = (DataUtil.emptyString(fileItem5.getOriginalFilename()));
			if (!fileName5.equalsIgnoreCase("")) {
				// encode data on your side using BASE64
				byte[] bytesEncoded = Base64.encodeBase64(fileItem5.getBytes());
				String saveFile = new String(bytesEncoded);
				employee.setEmployeedoc4(saveFile);

			} else {
				if (employeedoc4delete != null) {
					employee.setEmployeedoc4(null);
				} else {
					employee.setEmployeedoc4(employeedoc4Update);
				}
			}

			MultipartFile fileItem6 = listOfFiles[5];

			String fileName6 = (DataUtil.emptyString(fileItem6.getOriginalFilename()));
			if (!fileName6.equalsIgnoreCase("")) {
				// encode data on your side using BASE64
				byte[] bytesEncoded = Base64.encodeBase64(fileItem6.getBytes());
				String saveFile = new String(bytesEncoded);
				employee.setEmployeedoc5(saveFile);
			} else {
				if (employeedoc5delete != null) {
					employee.setEmployeedoc5(null);
				} else {
					employee.setEmployeedoc5(employeedoc5Update);
				}
			}

			//End Employee Docs
		}


		} catch (IOException e) {
			e.printStackTrace();
		}
		
		employee = new EmployeeDAO().update(employee);

        return employee;
	}

	public void deleteMultiple(EmployeeIdsDto employeeIdsDto) {
		 String[] employeeIds = employeeIdsDto.getEmployeeIds();
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

	public ViewAllRelationsResponseDto viewAllRelations(String branchId) {
		ViewAllRelationsResponseDto viewAllRelationsResponseDto = new ViewAllRelationsResponseDto();
		if(branchId!=null) {
			List<Department> listDepartment = new departmentDAO().readListOfObjects(Integer.parseInt(branchId));
	        viewAllRelationsResponseDto.setListDepartment(listDepartment);
	        List<Position> listPosition = new positionDAO().readListOfObjects(Integer.parseInt(branchId));
	        viewAllRelationsResponseDto.setListPosition(listPosition);
		}
		return viewAllRelationsResponseDto;
	}

	public EmployeeListDto searchEmployee(SearchEmployeeDto searchEmployeeDto, String branchId) {
		EmployeeListDto employeeListDto = new EmployeeListDto();
		String staffName = searchEmployeeDto.getStaffName();
		String staffDepartment = searchEmployeeDto.getStaffDepartment();
		List<Teacher> employeeList = new ArrayList<Teacher>();
		
		if(branchId!=null){
			if(staffName!=""){
				employeeList = new EmployeeDAO().readListOfEmployeesByName(staffName, Integer.parseInt(branchId));
			}else if(staffDepartment!=""){
				employeeList = new EmployeeDAO().readListOfEmployeesByDepartment(staffDepartment, Integer.parseInt(branchId));
			}else {
				employeeList = new EmployeeDAO().readListOfEmployeesBasicPay(Integer.parseInt(branchId));
			}
		}
		
		employeeListDto.setEmployeeList(employeeList);
		
		ViewAllEmployee(branchId);

		return employeeListDto;
	}

	public BasicPayEmployeesDto basicpayEmployees(String branchId) {
		BasicPayEmployeesDto basicPayEmployeesDto = new BasicPayEmployeesDto();
		List<Paybasic> employeeList = new ArrayList<Paybasic>();
		
		if(branchId!=null){
				employeeList = new EmployeeDAO().readListOfEmployeesBasicPayDetails(Integer.parseInt(branchId));
		}
		basicPayEmployeesDto.setVieweditbasicpay(employeeList);
		return basicPayEmployeesDto;
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
