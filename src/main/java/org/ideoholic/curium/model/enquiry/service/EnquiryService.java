package org.ideoholic.curium.model.enquiry.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.enquiry.dao.enquiryDAO;
import org.ideoholic.curium.model.enquiry.dto.AdmissionEnquiry;
import org.ideoholic.curium.model.enquiry.dto.Enquiry;
import org.ideoholic.curium.model.parents.dao.parentsDetailsDAO;
import org.ideoholic.curium.model.sendsms.service.SmsService;
import org.ideoholic.curium.model.student.dao.studentDetailsDAO;
import org.ideoholic.curium.util.DateUtil;

public class EnquiryService {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;

	public EnquiryService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

	public void getCertificate() {
		
		
	    String name= request.getParameter("name");		
	    String place= request.getParameter("place");	
	    String mobile= request.getParameter("mobileno");	
	    String date= request.getParameter("date");
	    httpSession.setAttribute("name", name);
	    httpSession.setAttribute("place", place);
	    httpSession.setAttribute("mobile", mobile);
	    httpSession.setAttribute("date", date);

	    Enquiry enquiry = new Enquiry();
        
       	 
                
	    enquiry.setName(name);
	    enquiry.setAddress(place);
	    enquiry.setMobileno(mobile);
	    enquiry.setDateofbirth(new Date());
	          
        enquiry =  new enquiryDAO().create(enquiry);
        }

	public boolean saveEnquiryForm() {
       
		AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry();
		String name= request.getParameter("name");		
	    String gender= request.getParameter("gender");	
	    String caste= request.getParameter("caste");	
	    String placeOfBirth= request.getParameter("placeofbirth");
	    String surName= request.getParameter("surname");		
	    String previousClassPassed= request.getParameter("previousclasspass");	
	    String previousSchoolName= request.getParameter("previousschoolname");	
	    String religion= request.getParameter("religion");
	    String fathername= request.getParameter("fathername");		
	    String fatherQualification= request.getParameter("fatherqualification");	
	    String mothername= request.getParameter("mothername");	
	    String motherQualification= request.getParameter("motherqualification");
	    String admissionclass= request.getParameter("classadmittedin");		
	    String brothereducation= request.getParameter("brothereducation");	
	    String sistereducation= request.getParameter("sistereducation");	
	    String occupation= request.getParameter("occupation");
	    String academicyear= request.getParameter("academicyear");
	    Date dateofbirth= DateUtil.indiandateParser(request.getParameter("dateofbirth"));		
	    String address= request.getParameter("address");
	    String notes= request.getParameter("notes");
	    String mobileno= request.getParameter("contactno");
	    admissionEnquiry.setName(name);
	    admissionEnquiry.setGender(gender);
	    admissionEnquiry.setCaste(caste);
	    admissionEnquiry.setPlaceOfBirth(placeOfBirth);
	    admissionEnquiry.setSurName(surName);
	    admissionEnquiry.setPreviousClassPassed(previousClassPassed);
	    admissionEnquiry.setPreviousSchoolName(previousSchoolName);
	    admissionEnquiry.setReligion(religion);
	    admissionEnquiry.setFathername(fathername);
	    admissionEnquiry.setFatherQualification(fatherQualification);
	    admissionEnquiry.setMothername(mothername);
	    admissionEnquiry.setMotherQualification(motherQualification);
	    admissionEnquiry.setAdmissionclass(admissionclass);
	    admissionEnquiry.setBrothereducation(brothereducation);
	    admissionEnquiry.setSistereducation(sistereducation);
	    admissionEnquiry.setOccupation(occupation);
	    admissionEnquiry.setDateofbirth(dateofbirth);
	    admissionEnquiry.setAddress(address);
	    admissionEnquiry.setMobileno(mobileno);
	    admissionEnquiry.setAcademicYear(academicyear);
	    admissionEnquiry.setNotes(notes);
	    
	    request.setAttribute("name", name);
	    request.setAttribute("gender", gender);
	    request.setAttribute("caste", caste);
	    request.setAttribute("placeOfBirth", placeOfBirth);
	    request.setAttribute("surname", surName);
	    request.setAttribute("previousclasspassed", previousClassPassed);
	    request.setAttribute("previousschoolname", previousSchoolName);
	    request.setAttribute("religion", religion);
	    request.setAttribute("fathername", fathername);
	    request.setAttribute("fatherqualification", fatherQualification);
	    request.setAttribute("mothername", mothername);
	    request.setAttribute("motherqualification", motherQualification);
	    request.setAttribute("admissionclass", admissionclass);
	    request.setAttribute("brothereducation", brothereducation);
	    request.setAttribute("sistereducation", sistereducation);
	    request.setAttribute("occupation", occupation);
	    request.setAttribute("dateofbirth", request.getParameter("dateofbirth"));
	    request.setAttribute("address", address);
	    request.setAttribute("mobileno", mobileno);
	    request.setAttribute("academicyear", academicyear);
	    request.setAttribute("notes", notes);
	    
	    boolean result = new enquiryDAO().add(admissionEnquiry);
	    
	    if(result) {
	    	new SmsService(request, response).sendSMS(mobileno, " ", "enquiry");	
	    }
	    
	    return result;
	    
	}

	public void viewEnquiry() {
		List<AdmissionEnquiry> admissionEnquiryList = new enquiryDAO().viewEnquiryList();
		request.setAttribute("admissionEnquiryList", admissionEnquiryList);
		
	}

	public void getStudentEnquiry() {
		int id= Integer.parseInt(request.getParameter("id"));
		AdmissionEnquiry admissionEnquiry = new enquiryDAO().getStudentEnquiry(id);
		request.setAttribute("admissionEnquiry", admissionEnquiry);
		
	}

	public boolean updateEnquiry() {
		
		AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry();
		int id= Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String academicYear = request.getParameter("academicyear");
		String caste = request.getParameter("caste");
		String placeOfBirth = request.getParameter("placeofbirth");
		String surName = request.getParameter("surname");
		String previousClassPassed = request.getParameter("previousclasspass");
		String previousSchoolName = request.getParameter("previousschoolname");
		String religion = request.getParameter("religion");
		String fathername = request.getParameter("fathername");
		String fatherQualification = request.getParameter("fatherqualification");
		String mothername = request.getParameter("mothername");
		String motherQualification = request.getParameter("motherqualification");
		String admissionclass = request.getParameter("admissionclass");
		String brothereducation = request.getParameter("brothereducation");
	    String sistereducation = request.getParameter("sistereducation");
		String occupation = request.getParameter("occupation");
		Date dateofbirth = DateUtil.dateParserdd(request.getParameter("dateofbirth"));
		String address = request.getParameter("address");
		String mobileno = request.getParameter("mobileno");
		String notes = request.getParameter("notes");
		admissionEnquiry.setId(id);
		admissionEnquiry.setName(name);
	    admissionEnquiry.setGender(gender);
	    admissionEnquiry.setCaste(caste);
	    admissionEnquiry.setPlaceOfBirth(placeOfBirth);
	    admissionEnquiry.setSurName(surName);
	    admissionEnquiry.setPreviousClassPassed(previousClassPassed);
	    admissionEnquiry.setPreviousSchoolName(previousSchoolName);
	    admissionEnquiry.setReligion(religion);
	    admissionEnquiry.setFathername(fathername);
	    admissionEnquiry.setFatherQualification(fatherQualification);
	    admissionEnquiry.setMothername(mothername);
	    admissionEnquiry.setMotherQualification(motherQualification);
	    admissionEnquiry.setAdmissionclass(admissionclass);
	    admissionEnquiry.setBrothereducation(brothereducation);
	    admissionEnquiry.setSistereducation(sistereducation);
	    admissionEnquiry.setOccupation(occupation);
	    admissionEnquiry.setDateofbirth(dateofbirth);
	    admissionEnquiry.setAddress(address);
	    admissionEnquiry.setMobileno(mobileno);
	    admissionEnquiry.setAcademicYear(academicYear);
	    admissionEnquiry.setNotes(notes);
	    return new enquiryDAO().update(admissionEnquiry);
		
	}

	public void deleteEnquiry() {
		String[] enquiryIds = request.getParameterValues("id");
		if (enquiryIds != null) {
			List<Integer> ids = new ArrayList();
			for (String id : enquiryIds) {
				ids.add(Integer.parseInt(id));
			}
			boolean result = new enquiryDAO().deleteEnquiry(ids);
			request.setAttribute("deletesuccess", result);
	}
	}

	public void getStudentLastEnquiry() {
		AdmissionEnquiry admissionEnquiry = new enquiryDAO().getStudentLastEnquiry();
		request.setAttribute("admissionEnquiry", admissionEnquiry);
	}
	   
	}
