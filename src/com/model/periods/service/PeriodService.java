package com.model.periods.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.academicyear.dao.YearDAO;
import com.model.academicyear.dto.Currentacademicyear;
import com.model.documents.dao.DocumentDAO;
import com.model.documents.dto.Transfercertificate;
import com.model.parents.dto.Parents;
import com.model.student.dao.studentDetailsDAO;
import com.model.student.dto.Student;
import com.util.DataUtil;
import com.util.DateUtil;

public class PeriodService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;
	/**
    * Size of a byte buffer to read/write file
    */
   private static final int BUFFER_SIZE = 4096;
	
	public PeriodService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}
	
	
	public boolean periodConfiguration(){
		
		Currentacademicyear currentYear = new YearDAO().showYear();
        httpSession.setAttribute("currentYear", currentYear.getCurrentacademicyear());
        
        if(currentYear.getCurrentacademicyear()==null){
        	return false;
        }
		return true;
		
	}

	public String generateTransferCertificate() {
		Student student = new Student();
		Parents parents = new Parents();
		Transfercertificate tc = new Transfercertificate();
		String transferCertificate = null;
		
		int studentId = DataUtil.parseInt(request.getParameter("studentId"));
		String leavingReason = DataUtil.emptyString(request.getParameter("reason"));
		Date dateOfTc = DateUtil.dateParserUpdateStd(request.getParameter("dateoftc"));
		
		student.setReasonleaving(leavingReason);
		student.setSid(studentId);
		 boolean updateStudent = new studentDetailsDAO().updateStudent(student);
		 
		 if(updateStudent){
			 tc.setSid(studentId);
			 tc.setApplicationstatus("applied");
			 tc.setDateofissues(dateOfTc);
			 tc.setNoofissues(1);
			 transferCertificate = new DocumentDAO().generateTransferCertificate(tc);
		 }
		 
		 if("true".equalsIgnoreCase(transferCertificate)){
			 String getStudentInfo  = "from Parents as parents where parents.Student.sid="+studentId;
			 parents = new studentDetailsDAO().getStudentRecords(getStudentInfo);
			 request.setAttribute("studentdetails", parents);
			 request.setAttribute("tcdetails", tc);
			 return "true";
		 }else if("studentexists".equalsIgnoreCase(transferCertificate)){
			 return "studentexists";
		 }
		 
		 
		 return "false";
	}


	public boolean printTransferCertificate() {
		
		Parents parents = new Parents();
		Transfercertificate tc = new Transfercertificate();
		
		int studentId = DataUtil.parseInt(request.getParameter("id"));
		 
			tc = new DocumentDAO().getTransferCertificateDetails(studentId);
		 
			 String getStudentInfo  = "from Parents as parents where parents.Student.sid="+studentId;
			 parents = new studentDetailsDAO().getStudentRecords(getStudentInfo);
			 request.setAttribute("studentdetails", parents);
			 request.setAttribute("tcdetails", tc);
			 
			 if(tc.getTcid() != null){
				 return true;
			 }else{
				 return false;
			 }
	}

	
	
}
