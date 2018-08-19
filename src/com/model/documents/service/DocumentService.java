package com.model.documents.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.documents.dao.DocumentDAO;
import com.model.documents.dto.Transfercertificate;
import com.model.parents.dto.Parents;
import com.model.student.dao.studentDetailsDAO;
import com.model.student.dto.Student;
import com.util.DataUtil;
import com.util.DateUtil;

public class DocumentService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;
	private String BRANCHID = "branchid";
	/**
    * Size of a byte buffer to read/write file
    */
   private static final int BUFFER_SIZE = 4096;
	
	public DocumentService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}
	
	
	public boolean transferCertificate(){
		
		if(httpSession.getAttribute(BRANCHID)!=null){
			try {
				List<Parents> list = new studentDetailsDAO().getStudentsList("from Parents where branchid"+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
				request.setAttribute("studentListtc", list);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return false;
		
	}

	public String generateTransferCertificate() {
		Student student = new Student();
		Parents parents = new Parents();
		Transfercertificate tc = new Transfercertificate();
		String transferCertificate = null;
		
		int studentId = DataUtil.parseInt(request.getParameter("studentId"));
		String leavingReason = DataUtil.emptyString(request.getParameter("reason"));
		Date dateOfTc = DateUtil.dateParserUpdateStd(request.getParameter("dateoftc"));
		
		//student.setReasonleaving(leavingReason);
		student.setSid(studentId);
		 boolean updateStudent = true;
		 
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
