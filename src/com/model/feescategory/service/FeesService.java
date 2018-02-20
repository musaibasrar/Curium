package com.model.feescategory.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.feescategory.dao.feesCategoryDAO;
import com.model.feescategory.dto.Feescategory;
import com.model.feesdetails.dao.feesDetailsDAO;
import com.model.parents.dto.Parents;
import com.model.student.dao.studentDetailsDAO;
import com.model.student.dto.Student;
import com.model.user.dao.UserDAO;
import com.util.DataUtil;

public class FeesService {
	
	 private HttpServletRequest request;
	    private HttpServletResponse response;
	    private HttpSession httpSession;
	    
	    /**
	     * Size of a byte buffer to read/write file
	     */
	    private static final int BUFFER_SIZE = 4096;
	
	public FeesService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
       this.response = response;
       this.httpSession = request.getSession();
	}


	public boolean viewFees() {
		
		
		 boolean result = false;
	        try {
	        	List<Feescategory> list = new feesCategoryDAO().readListOfObjects();
	            httpSession.setAttribute("feescategory", list);

	            result = true;
	        } catch (Exception e) {
	            e.printStackTrace();
	            result = false;
	        }
	        return result;
		
	}


	public void addFeesParticular() {
		
		Feescategory feescategory = new Feescategory();
		
		feescategory.setFeescategoryname(DataUtil.emptyString(request.getParameter("feescategory")));
		if(!DataUtil.emptyString(request.getParameter("fromclass")).equalsIgnoreCase("ALL") && !DataUtil.emptyString(request.getParameter("toclass")).equalsIgnoreCase("ALL")){
			feescategory.setParticularname(DataUtil.emptyString(request.getParameter("fromclass"))+"-"+DataUtil.emptyString(request.getParameter("toclass")));
		}else{
			feescategory.setParticularname(DataUtil.emptyString(request.getParameter("fromclass")));
		}
		
		feescategory.setAmount(DataUtil.parseInt(request.getParameter("amount")));
		
		if(!feescategory.getFeescategoryname().equalsIgnoreCase("") && !feescategory.getParticularname().equalsIgnoreCase("") && feescategory.getAmount() != 0 ){
			feescategory =  new feesCategoryDAO().create(feescategory);
		}
		
		
	}


	public void deleteMultiple() {
		 String[] idfeescategory = request.getParameterValues("idfeescategory");
		 if(idfeescategory!=null){
	        List ids = new ArrayList();
	        for (String id : idfeescategory) {
	            System.out.println("id" + id);
	            ids.add(Integer.valueOf(id));

	        }
	        System.out.println("id length" + idfeescategory.length);
	        new feesCategoryDAO().deleteMultiple(ids);
		 }
	}


	public boolean viewAllStudentsList() {

		boolean result = false;
		try {
			List<Student> list = new feesDetailsDAO().readListOfStudents();
			request.setAttribute("studentListFeesCollection", list);
			result = true;
		} catch (Exception e) {
			result = false;
		}
		return result;
	}


	public boolean downlaodFile() {
		boolean result = false;
		try {

			File downloadFile = new File(System.getProperty("java.io.tmpdir")+"feesdetails.xlsx");
	        FileInputStream inStream = new FileInputStream(downloadFile);

	        // get MIME type of the file
			String mimeType = "application/vnd.ms-excel";

			// set content attributes for the response
			response.setContentType(mimeType);
			// response.setContentLength((int) bis.length());

			// set headers for the response
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"",
					"feesdetails.xlsx");
			response.setHeader(headerKey, headerValue);

			// get output stream of the response
			OutputStream outStream = response.getOutputStream();

			byte[] buffer = new byte[BUFFER_SIZE];
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
		return result;
	}


	
	

}
