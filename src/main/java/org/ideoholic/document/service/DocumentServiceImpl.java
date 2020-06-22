package org.ideoholic.document.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.model.documents.dao.DocumentDAO;
import com.model.documents.dto.Transfercertificate;
import com.model.parents.dto.Parents;
import com.model.std.service.StandardService;
import com.model.student.dao.studentDetailsDAO;
import com.model.student.dto.Student;
import com.util.DataUtil;
import com.util.DateUtil;

public class DocumentServiceImpl implements DocumentService {
	
public String transferCertificate(String branchId){
	StringBuffer sb = new StringBuffer();
	sb.append("{");
		
		if(branchId!=null){
			try {
				
				List<Parents> list = new studentDetailsDAO().getStudentsList("from Parents where branchid = "+Integer.parseInt(branchId.toString()));
				sb.append("studentListtc").append(list);
				sb.append("result:").append(true);
				return sb.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		sb.append("result:").append(false);
		sb.append("}");
		return sb.toString();
		
	}


public String generateTransferCertificate(int studentId,String leavingReason,Date dateOfTc) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	Student student = new Student();
	Parents parents = new Parents();
	Transfercertificate tc = new Transfercertificate();
	String transferCertificateString = null;
	
	student.setReasonleaving(leavingReason);
	student.setSid(studentId);
	 boolean updateStudent = new studentDetailsDAO().updateStudent(student);
	 
	 if(updateStudent){
		 tc.setSid(studentId);
		 tc.setApplicationstatus("applied");
		 tc.setDateofissues(dateOfTc);
		 tc.setNoofissues(1);
		 
		 Transfercertificate transferCertificate = new DocumentDAO().getTransferCertificateDetails(tc.getSid()); 
		 if(transferCertificate != null){
			 return "studentexists";
		 }else {
				transferCertificateString = new DocumentDAO().generateTransferCertificate(tc);
		}
	 }
	 
	 if("true".equalsIgnoreCase(transferCertificateString)){
		 String getStudentInfo  = "from Parents as parents where parents.Student.sid="+studentId;
		 parents = new studentDetailsDAO().getStudentRecords(getStudentInfo);
		 sb.append("studentdetails").append(parents);
		 sb.append("parents").append(tc);
		 return "true";
	 }
	 sb.append("}");
	 return "false";
}

public String printTransferCertificate(int studentId) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	
	Parents parents = new Parents();
	Transfercertificate tc = new Transfercertificate();
		 
		tc = new DocumentDAO().getTransferCertificateDetails(studentId);
	 
		 String getStudentInfo  = "from Parents as parents where parents.Student.sid="+studentId;
		 parents = new studentDetailsDAO().getStudentRecords(getStudentInfo);
		 sb.append("studentdetails").append(parents);
		 sb.append("tcdetails").append(tc);
		 
		 if(tc.getTcid() != null){
			 sb.append("result:").append(true);
			 return sb.toString();
		 }else{
			 sb.append("result:").append(false);
			 sb.append("}");
			 return sb.toString();
		 }
		 
}

public String admissionAbstract(String branchId) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	
	if(branchId!=null){
		try {
			List<Parents> list = new studentDetailsDAO().getStudentsList("from Parents where branchid = "+Integer.parseInt(branchId.toString()));
			sb.append("studentListaa").append(list);
			new StandardService(request, response).viewClasses();
			sb.append("result:").append(true);
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	sb.append("}");
	sb.append("result:").append(false);
	return sb.toString();
	
}

public String searchForStudents(String branchId,String studentname,String admissionNumber,String addClass,String addSec) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	List<Parents> searchStudentList = new ArrayList<Parents>();
	boolean result = false;
	if(branchId!=null){
	
	String queryMain = "From Parents as parents where";
		String conClassStudying = "";
	
	if (!addClass.equalsIgnoreCase("")) {

		conClassStudying = addClass+"--" +"%";

	}
	if (!addSec.equalsIgnoreCase("")) {
		conClassStudying = addClass;
		conClassStudying = conClassStudying+"--"+addSec+"%";
	}

	String classStudying = DataUtil.emptyString(conClassStudying);
	String querySub = "";

	if (!studentname.equalsIgnoreCase("")) {
		querySub = " parents.Student.name like '%" + studentname + "%'";
	}

	if (!classStudying.equalsIgnoreCase("")	&& !querySub.equalsIgnoreCase("")) {
		querySub = querySub + " AND parents.Student.classstudying like '"
				+ classStudying + "'";
	} else if (!classStudying.equalsIgnoreCase("")) {
		querySub = querySub + " parents.Student.classstudying like '"
				+ classStudying + "'";
	}

	if (!admissionNumber.equalsIgnoreCase("") && !querySub.equalsIgnoreCase("")) {
		querySub = querySub + " AND parents.Student.admissionnumber = '"+admissionNumber+"'";
	}else if(!admissionNumber.equalsIgnoreCase("")) {
		querySub = querySub + " parents.Student.admissionnumber = '"+admissionNumber+"'";
	}
	
	if(!"".equalsIgnoreCase(querySub)) {
		queryMain = queryMain + querySub + " AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.branchid="+Integer.parseInt(branchId.toString())+" order by parents.Student.admissionnumber ASC";
		System.out.println("QUERY*********** "+queryMain);
		searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
	}
	result = true;
}
	sb.append("searchStudentList").append(searchStudentList);
	sb.append("}");
	return sb.toString();

}

public String downlaodFile(byte[] buffer) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	boolean result = false;
	try {

		File downloadFile = new File(System.getProperty("java.io.tmpdir")+"/admissionabstract.xlsx");
        FileInputStream inStream = new FileInputStream(downloadFile);

        // get MIME type of the file
		String mimeType = "application/vnd.ms-excel";

		// set content attributes for the response
		response.setContentType(mimeType);
		// response.setContentLength((int) bis.length());

		// set headers for the response
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"",
				"admissionabstract.xlsx");
		response.setHeader(headerKey, headerValue);

		// get output stream of the response
		OutputStream outStream = response.getOutputStream();

		int bytesRead = -1;

		// write bytes read from the input stream into the output stream
		while ((bytesRead = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}

		inStream.close();
		outStream.close();
		result = true;
	} catch (Exception e) {
		System.out.println("" + e);
	}
	sb.append("}");
	return sb.toString();
}

}
