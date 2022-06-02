package com.model.employee.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.model.department.dao.departmentDAO;
import com.model.department.dto.Department;
import com.model.employee.dao.EmployeeDAO;
import com.model.employee.dto.Teacher;
import com.model.hr.dao.HrDAO;
import com.model.hr.dto.Paybasic;
import com.model.position.dao.positionDAO;
import com.model.position.dto.Position;
import com.model.student.dao.studentDetailsDAO;
import com.model.student.dto.Student;
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
		
		
		
		try {
			
		List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
	
		 for (FileItem item : items) {
	            if (item.isFormField()) {
	                // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
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
	                
	                if (fieldName.equalsIgnoreCase("email")) {
	                	employee.setEmail(DataUtil.emptyString(item.getString()));
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
	                
	                if (fieldName.equalsIgnoreCase("bankname")) {
	                	employee.setBankname(DataUtil.emptyString(item.getString()));
	                }
	                
	                if (fieldName.equalsIgnoreCase("bankifsc")) {
	                	employee.setBankifsc(DataUtil.emptyString(item.getString()));
	                }
	                
	                if (fieldName.equalsIgnoreCase("accno")) {
	                	employee.setAccno(DataUtil.emptyString(item.getString()));
	                }
	                
	                if (fieldName.equalsIgnoreCase("teacherexternalid")) {
	                	employee.setTeacherexternalid(DataUtil.emptyString(item.getString()));
	                }
	                
	            } else {
	                // Process form file field (input type="file").
	                String fieldName = item.getFieldName();

	                if (fieldName.equalsIgnoreCase("fileToUpload")) {

	                    String fileName = (DataUtil.emptyString(item.getName()));
	                    String fileValue = (DataUtil.emptyString(item.getString()));
	                    if (!fileName.equalsIgnoreCase("")) {
	                    	// encode data on your side using BASE64
	                    	byte[]   bytesEncoded = Base64.encodeBase64(item.get());
	                    	String saveFile = new String(bytesEncoded);
	                    	employee.setEmployeephoto(saveFile);

	                    } 
	                }
	                
	              //Employee Docs
	                if (fieldName.equalsIgnoreCase("employeedoc1")) {

	                    String fileName = (DataUtil.emptyString(item.getName()));
	                    String fileValue = (DataUtil.emptyString(item.getString()));
	                    if (!fileName.equalsIgnoreCase("")) {
	                    	// encode data on your side using BASE64
	                    	byte[]   bytesEncoded = Base64.encodeBase64(item.get());
	                    	String saveFile = new String(bytesEncoded);
	                    	employee.setEmployeedoc1(saveFile);

	                    } 
	                }
	                
	                if (fieldName.equalsIgnoreCase("employeedoc2")) {

	                    String fileName = (DataUtil.emptyString(item.getName()));
	                    String fileValue = (DataUtil.emptyString(item.getString()));
	                    if (!fileName.equalsIgnoreCase("")) {
	                    	// encode data on your side using BASE64
	                    	byte[]   bytesEncoded = Base64.encodeBase64(item.get());
	                    	String saveFile = new String(bytesEncoded);
	                    	employee.setEmployeedoc2(saveFile);

	                    } 
	                }
	                
	                if (fieldName.equalsIgnoreCase("employeedoc3")) {

	                    String fileName = (DataUtil.emptyString(item.getName()));
	                    String fileValue = (DataUtil.emptyString(item.getString()));
	                    if (!fileName.equalsIgnoreCase("")) {
	                    	// encode data on your side using BASE64
	                    	byte[]   bytesEncoded = Base64.encodeBase64(item.get());
	                    	String saveFile = new String(bytesEncoded);
	                    	employee.setEmployeedoc3(saveFile);

	                    } 
	                }
	                
	                if (fieldName.equalsIgnoreCase("employeedoc4")) {

	                    String fileName = (DataUtil.emptyString(item.getName()));
	                    String fileValue = (DataUtil.emptyString(item.getString()));
	                    if (!fileName.equalsIgnoreCase("")) {
	                    	// encode data on your side using BASE64
	                    	byte[]   bytesEncoded = Base64.encodeBase64(item.get());
	                    	String saveFile = new String(bytesEncoded);
	                    	employee.setEmployeedoc4(saveFile);

	                    } 
	                }
	                
	                if (fieldName.equalsIgnoreCase("employeedoc5")) {

	                    String fileName = (DataUtil.emptyString(item.getName()));
	                    String fileValue = (DataUtil.emptyString(item.getString()));
	                    if (!fileName.equalsIgnoreCase("")) {
	                    	// encode data on your side using BASE64
	                    	byte[]   bytesEncoded = Base64.encodeBase64(item.get());
	                    	String saveFile = new String(bytesEncoded);
	                    	employee.setEmployeedoc5(saveFile);
	                    } 
	                }
	                
	                //End employee Docs

	            }
	        }
	} catch (FileUploadException e) {
		e.printStackTrace();
	}
		
		
		if(httpSession.getAttribute(BRANCHID)!=null){
		
		final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		int count =4;
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
		int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
		builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		//employee.setTeacherexternalid(builder.toString());
		employee.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		employee.setUserid(Integer.parseInt(httpSession.getAttribute("userloginid").toString()));
		
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
        
        try{
			
    		List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
    	
    		 for (FileItem item : items) {
    	            if (item.isFormField()) {
    	                // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
    	                String fieldName = item.getFieldName();
    	               
    	                if (fieldName.equalsIgnoreCase("id")) {	                
    	                id = DataUtil.emptyString(item.getString());
    	                
    	                }
    	                         		
    	        		if(id!=""){
    	        			employeeId = Integer.parseInt(id);
    	        			employee.setTid(employeeId);
    	        		}
    	        		
    	        		
    	        		  
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
    	                
    	                if (fieldName.equalsIgnoreCase("email")) {
    	                	employee.setEmail(DataUtil.emptyString(item.getString()));
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
    	                
    	                if (fieldName.equalsIgnoreCase("bankname")) {
    	                	employee.setBankname(DataUtil.emptyString(item.getString()));
    	                }
    	                
    	                if (fieldName.equalsIgnoreCase("bankifsc")) {
    	                	employee.setBankifsc(DataUtil.emptyString(item.getString()));
    	                }
    	                
    	                if (fieldName.equalsIgnoreCase("accno")) {
    	                	employee.setAccno(DataUtil.emptyString(item.getString()));
    	                }
    	                
    	                if (fieldName.equalsIgnoreCase("teacherexternalid")) {
    	                	employee.setTeacherexternalid(DataUtil.emptyString(item.getString()));
    	                }
    	                
    	                if (fieldName.equalsIgnoreCase("branchid")) {
    	                	employee.setBranchid(Integer.parseInt(DataUtil.emptyString(item.getString())));
    	                }
    	                
    	                if (fieldName.equalsIgnoreCase("leavingdate")) {

    	                	employee.setLeavingdate(DateUtil.indiandateParser(item.getString()));

    	                }
    	                
    	                if(fieldName.equalsIgnoreCase("employeephotoupdate")){
    	                	employeePhotoUpdate=DataUtil.emptyString(item.getString());
    	                }
    	                
    	                if(fieldName.equalsIgnoreCase("employeedoc1update")){
    	                	employeedoc1Update=DataUtil.emptyString(item.getString());
    	                }
    	                
    	                if(fieldName.equalsIgnoreCase("employeedoc2update")){
    	                	employeedoc2Update=DataUtil.emptyString(item.getString());
    	                }
    	                
    	                if(fieldName.equalsIgnoreCase("employeedoc3update")){
    	                	employeedoc3Update=DataUtil.emptyString(item.getString());
    	                }
    	                
    	                if(fieldName.equalsIgnoreCase("employeedoc4update")){
    	                	employeedoc4Update=DataUtil.emptyString(item.getString());
    	                }
    	                
    	                if(fieldName.equalsIgnoreCase("employeedoc5update")){
    	                	employeedoc5Update=DataUtil.emptyString(item.getString());
    	                }
    	                
    	                if (fieldName.equalsIgnoreCase("employeedoc1delete")) {
		                	employeedoc1delete=DataUtil.emptyString(item.getString());
		                }
		                
		                if (fieldName.equalsIgnoreCase("employeedoc2delete")) {
		                	employeedoc2delete=DataUtil.emptyString(item.getString());
		                }
		                
		                if (fieldName.equalsIgnoreCase("employeedoc3delete")) {
		                	employeedoc3delete=DataUtil.emptyString(item.getString());
		                }
		                
		                if (fieldName.equalsIgnoreCase("employeedoc4delete")) {
		                	employeedoc4delete=DataUtil.emptyString(item.getString());
		                }
		                
		                if (fieldName.equalsIgnoreCase("employeedoc5delete")) {
		                	employeedoc5delete=DataUtil.emptyString(item.getString());
		                }
    	              
    		                
    	            } else {
    	                String fieldName = item.getFieldName();

    	                if (fieldName.equalsIgnoreCase("fileToUpload")) {


    	                    String fileName = (DataUtil.emptyString(item.getName()));
    	                    String fileValue = (DataUtil.emptyString(item.getString()));
    	                    if (!fileName.equalsIgnoreCase("")) {
    	                       
    	                    	                    	
    	                    	// Resize the image
    	                    	byte[]   bytesEncoded = Base64.encodeBase64(item.get());
    	                    	System.out.println("ecncoded value is " + new String(bytesEncoded ));
    	                    	String saveFile = new String(bytesEncoded);

    	                    	employee.setEmployeephoto(saveFile);

    	                    } else{
    	                    	if(employeePhotodelete!=null) {
    	                    		employee.setEmployeephoto(null);
    	                    	}else {
    	                    		employee.setEmployeephoto(employeePhotoUpdate);
    	                    	}
    	                    }
    	                }
    	                
    	                
    	                if (fieldName.equalsIgnoreCase("employeedoc1")) {


    	                    String fileName = (DataUtil.emptyString(item.getName()));
    	                    String fileValue = (DataUtil.emptyString(item.getString()));
    	                    if (!fileName.equalsIgnoreCase("")) {
    	                       
    	                    	                    	
    	                    	// Resize the image
    	                    	byte[]   bytesEncoded = Base64.encodeBase64(item.get());
    	                    	System.out.println("ecncoded value is " + new String(bytesEncoded ));
    	                    	String saveFile = new String(bytesEncoded);

    	                    	employee.setEmployeedoc1(saveFile);

    	                    } else{
    	                    	if(employeedoc1delete!=null) {
    	                    		employee.setEmployeedoc1(null);
    	                    	}else {
    	                    		employee.setEmployeedoc1(employeedoc1Update);
    	                    	}
    	                    }
    	                }
    	                
    	                if (fieldName.equalsIgnoreCase("employeedoc2")) {


    	                    String fileName = (DataUtil.emptyString(item.getName()));
    	                    String fileValue = (DataUtil.emptyString(item.getString()));
    	                    if (!fileName.equalsIgnoreCase("")) {
    	                       
    	                    	                    	
    	                    	// Resize the image
    	                    	byte[]   bytesEncoded = Base64.encodeBase64(item.get());
    	                    	System.out.println("ecncoded value is " + new String(bytesEncoded ));
    	                    	String saveFile = new String(bytesEncoded);

    	                    	employee.setEmployeedoc2(saveFile);

    	                    } else{
    	                    	if(employeedoc2delete!=null) {
    	                    		employee.setEmployeedoc2(null);
    	                    	}else {
    	                    		employee.setEmployeedoc2(employeedoc2Update);
    	                    	}
    	                    }
    	                }
    	                
    	                if (fieldName.equalsIgnoreCase("employeedoc3")) {


    	                    String fileName = (DataUtil.emptyString(item.getName()));
    	                    String fileValue = (DataUtil.emptyString(item.getString()));
    	                    if (!fileName.equalsIgnoreCase("")) {
    	                       
    	                    	                    	
    	                    	// Resize the image
    	                    	byte[]   bytesEncoded = Base64.encodeBase64(item.get());
    	                    	System.out.println("ecncoded value is " + new String(bytesEncoded ));
    	                    	String saveFile = new String(bytesEncoded);

    	                    	employee.setEmployeedoc3(saveFile);

    	                    } else{
    	                    	if(employeedoc3delete!=null) {
    	                    		employee.setEmployeedoc3(null);
    	                    	}else {
    	                    		employee.setEmployeedoc3(employeedoc3Update);
    	                    	}
    	                    }
    	                }
    	                
    	                if (fieldName.equalsIgnoreCase("employeedoc4")) {


    	                    String fileName = (DataUtil.emptyString(item.getName()));
    	                    String fileValue = (DataUtil.emptyString(item.getString()));
    	                    if (!fileName.equalsIgnoreCase("")) {
    	                       
    	                    	                    	
    	                    	// Resize the image
    	                    	byte[]   bytesEncoded = Base64.encodeBase64(item.get());
    	                    	System.out.println("ecncoded value is " + new String(bytesEncoded ));
    	                    	String saveFile = new String(bytesEncoded);

    	                    	employee.setEmployeedoc4(saveFile);

    	                    } else{
    	                    	if(employeedoc4delete!=null) {
    	                    		employee.setEmployeedoc4(null);
    	                    	}else {
    	                    		employee.setEmployeedoc4(employeedoc4Update);
    	                    	}
    	                    }
    	                }
    	                
    	                if (fieldName.equalsIgnoreCase("employeedoc5")) {


    	                    String fileName = (DataUtil.emptyString(item.getName()));
    	                    String fileValue = (DataUtil.emptyString(item.getString()));
    	                    if (!fileName.equalsIgnoreCase("")) {
    	                       
    	                    	                    	
    	                    	// Resize the image
    	                    	byte[]   bytesEncoded = Base64.encodeBase64(item.get());
    	                    	System.out.println("ecncoded value is " + new String(bytesEncoded ));
    	                    	String saveFile = new String(bytesEncoded);

    	                    	employee.setEmployeedoc5(saveFile);

    	                    } else{
    	                    	if(employeedoc5delete!=null) {
    	                    		employee.setEmployeedoc5(null);
    	                    	}else {
    	                    		employee.setEmployeedoc5(employeedoc5Update);
    	                    	}
    	                    }
    	                }
    	            }
    	        }
    	
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
        
		employee.setUserid(Integer.parseInt(httpSession.getAttribute("userloginid").toString()));
				
				
		
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

	public void addNew() {
        
        if(httpSession.getAttribute(BRANCHID)!=null){
            String branchId = httpSession.getAttribute(BRANCHID).toString();
            String branchCode = httpSession.getAttribute("branchcode").toString();
            String registrationNo = null;
            List<Teacher> teacherList = new EmployeeDAO().getListEmployees("from Teacher as teacher where teacher.branchid="+branchId+" order by teacher.tid DESC");
            
            if(teacherList.size()>0) {
            	teacherList.get(0).getTeacherexternalid();
            	int regNo = Integer.parseInt(teacherList.get(0).getTeacherexternalid().substring(teacherList.get(0).getTeacherexternalid().length()-4));
            	registrationNo = branchCode+String.format("%03d", regNo+1);
            }else {
            	registrationNo = branchCode+String.format("%03d", 1);
            }
            
            request.setAttribute("teacherexternalid", registrationNo);
            
            
          }
    }
}
