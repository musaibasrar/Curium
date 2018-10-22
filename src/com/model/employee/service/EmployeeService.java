package com.model.employee.service;

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

import com.model.academicyear.dao.YearDAO;
import com.model.academicyear.dto.Currentacademicyear;
import com.model.branch.dao.BranchDAO;
import com.model.branch.service.BranchService;
import com.model.department.dao.departmentDAO;
import com.model.department.dto.Department;
import com.model.employee.dao.EmployeeDAO;
import com.model.employee.dto.Organizersdetails;
import com.model.employee.dto.Teacher;
import com.model.feescollection.dao.feesCollectionDAO;
import com.model.feescollection.dto.Receiptinfo;
import com.model.parents.dao.parentsDetailsDAO;
import com.model.parents.dto.Parents;
import com.model.position.dao.positionDAO;
import com.model.position.dto.Position;
import com.model.std.dto.Classsec;
import com.model.student.dao.studentDetailsDAO;
import com.model.student.dto.Student;
import com.model.student.dto.Studentfeesstructure;
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
		employee.setTeachername(DataUtil.emptyString(request.getParameter("name")));
		employee.setGender(DataUtil.emptyString(request.getParameter("gender")));
		employee.setAddress(DataUtil.emptyString(request.getParameter("address")));
		employee.setContactnumber(DataUtil.emptyString(request.getParameter("contactnumber")));
		employee.setEmail(DataUtil.emptyString(request.getParameter("email")));
		employee.setDateofjoining(DateUtil.simpleDateParser(request.getParameter("dateofjoining")));
		employee.setTotalexperience(DataUtil.emptyString(request.getParameter("totalexperience")));
		employee.setQualification(DataUtil.emptyString(request.getParameter("qualification")));
		employee.setDepartment(DataUtil.emptyString(request.getParameter("department")));
		employee.setDesignation(DataUtil.emptyString(request.getParameter("designation")));
		employee.setSalary(DataUtil.emptyString(request.getParameter("salary")));
		employee.setRemarks(DataUtil.emptyString(request.getParameter("remarks")));
		
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
        id = request.getParameter("id");
        

        int employeeId = 0;
        int parentsId = 0;
        employeeId = Integer.parseInt(id);
        

        employee.setTid(employeeId);
        employee.setTeachername(DataUtil.emptyString(request.getParameter("name")));
		employee.setGender(DataUtil.emptyString(request.getParameter("gender")));
		employee.setAddress(DataUtil.emptyString(request.getParameter("address")));
		employee.setContactnumber(DataUtil.emptyString(request.getParameter("contactnumber")));
		employee.setEmail(DataUtil.emptyString(request.getParameter("email")));
		employee.setDateofjoining(DateUtil.datePars(request.getParameter("dateofjoining")));
		employee.setTotalexperience(DataUtil.emptyString(request.getParameter("totalexperience")));
		employee.setQualification(DataUtil.emptyString(request.getParameter("qualification")));
		employee.setDepartment(DataUtil.emptyString(request.getParameter("department")));
		employee.setDesignation(DataUtil.emptyString(request.getParameter("designation")));
		employee.setSalary(DataUtil.emptyString(request.getParameter("salary")));
		employee.setRemarks(DataUtil.emptyString(request.getParameter("remarks")));
		employee.setTeacherexternalid(DataUtil.emptyString(request.getParameter("teacherexternalid")));
				
		
		employee = new EmployeeDAO().update(employee);
				
        String empId = employee.getTid().toString();
        return empId;
	}

	public void deleteMultiple() {
		 String[] employeeIds = request.getParameterValues("employeeIDs");
		 if(employeeIds!=null){
	        List ids = new ArrayList();
	        for (String id : employeeIds) {
	            ids.add(Integer.valueOf(id));

	        }
	        new EmployeeDAO().deleteMultiple(ids);
		 }
	}

	public void viewAllRelations() {
		List<Department> listDepartment = new departmentDAO().readListOfObjects(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
        httpSession.setAttribute("listDepartment", listDepartment);
        List<Position> listPosition = new positionDAO().readListOfObjects(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
        httpSession.setAttribute("listPosition", listPosition);
		
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
			}
		}
		
		request.setAttribute("employeeList", employeeList);
		
		new EmployeeService(request, response).ViewAllEmployee();
	}

    public boolean addOrganizer() {
        
        Organizersdetails orgDet = new Organizersdetails();
        boolean result = false;
        try {
                List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
        
                 for (FileItem item : items) {
                    if (item.isFormField()) {
                        String fieldName = item.getFieldName();

                        if (fieldName.equalsIgnoreCase("name")) {
                            orgDet.setName(DataUtil.emptyString(item.getString()).toUpperCase());
                        }

                        
                        if (fieldName.equalsIgnoreCase("contactnumber")) {
                            orgDet.setContactnumber(DataUtil.emptyString(item.getString()));
                        }

                        if (fieldName.equalsIgnoreCase("centercode")) {
                            orgDet.setCentercode(DataUtil.emptyString(item.getString()));
                        }

                        if (fieldName.equalsIgnoreCase("email")) {
                            orgDet.setEmailid(DataUtil.emptyString(item.getString()));
                        }
                        
                        if (fieldName.equalsIgnoreCase("Address")) {
                            orgDet.setAddress(DataUtil.emptyString(item.getString()));
                        }
                        
                        if (fieldName.equalsIgnoreCase("gender")) {
                            orgDet.setGender(DataUtil.emptyString(item.getString()));
                        }
                        
                        if (fieldName.equalsIgnoreCase("fathername")) {
                            orgDet.setFathername(DataUtil.emptyString(item.getString()).toUpperCase());
                        }
                        
                        if (fieldName.equalsIgnoreCase("husbandname")) {
                            orgDet.setHusbandname(DataUtil.emptyString(item.getString()).toUpperCase());
                        }
                        
                        if (fieldName.equalsIgnoreCase("education")) {
                            orgDet.setEducation(DataUtil.emptyString(item.getString()));
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
                                orgDet.setPhoto(saveFile);
                            } 
                        }
                    }
                }
        } catch (FileUploadException e) {
                e.printStackTrace();
        }
        
        
      
        result = new EmployeeDAO().create(orgDet);

        return result;

}

    public void viewAllOrganizers() {
        
            try {
            List<Organizersdetails> list = new EmployeeDAO().readListOfOrganizers();
            httpSession.setAttribute("organizersList", list);
            } catch (Exception e) {
            e.printStackTrace();
            }
    }

    public void viewOrganizerDetails() {
        boolean result = false;
       try {
           long id = Long.parseLong(request.getParameter("id"));
           Organizersdetails organizer = new EmployeeDAO().readOrganizersdetails(id);
          
           if (organizer.getIdorganizersdetails() != null) {
               httpSession.setAttribute("organizersdetails", organizer);
           } 
       } catch (Exception e) {
           e.printStackTrace();
       }
}

    public boolean viewDetailsOfOrganizer() {
        boolean result = false;
        try {
                long id = Long.parseLong(request.getParameter("id"));
                Organizersdetails organizersDetails = new EmployeeDAO().readOrganizersdetails(id);
                
                if (organizersDetails == null) {
                        result = false;
                } else {
                        new BranchService(request, response).viewBranches();
                        httpSession.setAttribute("organizer", organizersDetails);
                        httpSession.setAttribute("organizersphoto", organizersDetails.getPhoto());
                        httpSession.setAttribute("resultfromservice",result);
                        result = true;
                }
        } catch (Exception e) {
                e.printStackTrace();
                result = false;
        }
        return result;
}

    public String updateOrganizer() {
        Organizersdetails orgDet = new Organizersdetails();
        boolean result = false;
        String id = "";
        String pid = "";
        int organizerId = 0;
        String organizerPicUpdate=null;
        
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
                                organizerId = Integer.parseInt(id);
                                orgDet.setIdorganizersdetails(organizerId);
                        }
                        
                       
                
                         if (fieldName.equalsIgnoreCase("name")) {
                            
                            orgDet.setName(DataUtil.emptyString(item.getString()).toUpperCase());
                        }

                        
                        if (fieldName.equalsIgnoreCase("contactnumber")) {
                            orgDet.setContactnumber(DataUtil.emptyString(item.getString()));
                        }

                        if (fieldName.equalsIgnoreCase("centercode")) {
                            orgDet.setCentercode(DataUtil.emptyString(item.getString()));
                        }

                        if (fieldName.equalsIgnoreCase("email")) {
                            orgDet.setEmailid(DataUtil.emptyString(item.getString()));
                        }
                        
                        if (fieldName.equalsIgnoreCase("Address")) {
                            orgDet.setAddress(DataUtil.emptyString(item.getString()));
                        }
                                                                    
                        if(fieldName.equalsIgnoreCase("organizerpicupdate")){
                            organizerPicUpdate=DataUtil.emptyString(item.getString());
                        }
                        
                        if (fieldName.equalsIgnoreCase("gender")) {
                            orgDet.setGender(DataUtil.emptyString(item.getString()));
                        }
                        
                        if (fieldName.equalsIgnoreCase("fathername")) {
                            orgDet.setFathername(DataUtil.emptyString(item.getString()).toUpperCase());
                        }
                        
                        if (fieldName.equalsIgnoreCase("husbandname")) {
                            orgDet.setHusbandname(DataUtil.emptyString(item.getString()).toUpperCase());
                        }
                        
                        if (fieldName.equalsIgnoreCase("education")) {
                            orgDet.setEducation(DataUtil.emptyString(item.getString()));
                        }
                        
                    } else {
                String fieldName = item.getFieldName();

                if (fieldName.equalsIgnoreCase("fileToUpload")) {


                    String fileName = (DataUtil.emptyString(item.getName()));
                    String fileValue = (DataUtil.emptyString(item.getString()));
                    if (!fileName.equalsIgnoreCase("")) {
                       
                                                
                        // Resize the image
                        byte[]   bytesEncoded = Base64.encodeBase64(item.get());
                        String saveFile = new String(bytesEncoded);

                        orgDet.setPhoto(saveFile);

                    } else{
                        
                        orgDet.setPhoto(organizerPicUpdate);
                    }
                }
            }
        }

} catch (Exception e) {
        e.printStackTrace();
}
         orgDet = new EmployeeDAO().update(orgDet);
        return orgDet.getIdorganizersdetails().toString();
}

    public void deleteMultipleOrganizers() {
        String[] employeeIds = request.getParameterValues("organizersIDs");
        if(employeeIds!=null){
       List ids = new ArrayList();
       for (String id : employeeIds) {
           ids.add(Integer.valueOf(id));

       }
       new EmployeeDAO().deleteMultipleOrganizers(ids);
        }
}

}
