package org.ideoholic.curium.model.enquiry.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.enquiry.dao.enquiryDAO;
import org.ideoholic.curium.model.enquiry.dto.AdmissionEnquiry;
import org.ideoholic.curium.model.enquiry.dto.AdmissionEnquiryDto;
import org.ideoholic.curium.model.enquiry.dto.AdmissionEnquiryResponseDto;
import org.ideoholic.curium.model.enquiry.dto.CertificateDto;
import org.ideoholic.curium.model.enquiry.dto.CertificateResponseDto;
import org.ideoholic.curium.model.enquiry.dto.Enquiry;
import org.ideoholic.curium.util.DateUtil;
import org.springframework.stereotype.Service;

@Service
public class EnquiryService {
	

	public CertificateResponseDto getCertificate(CertificateDto dto) {
		CertificateResponseDto certificateResponseDto = CertificateResponseDto.builder().success(false).build();;
		
	    String name= dto.getName();
	    String place= dto.getPlace();
	    String mobile= dto.getMobile();
	    String date= dto.getDate();;
	    certificateResponseDto.setName(name);
	    certificateResponseDto.setPlace(place);
	    certificateResponseDto.setMobile(mobile);
	    certificateResponseDto.setDate(date);

	    Enquiry enquiry = new Enquiry();
        
       	 
                
	    enquiry.setName(name);
	    enquiry.setAddress(place);
	    enquiry.setMobileno(mobile);
	          
                enquiry =  new enquiryDAO().create(enquiry);
                certificateResponseDto.setSuccess(true);
				return certificateResponseDto;
                }


	public AdmissionEnquiryResponseDto saveEnquiryForm(AdmissionEnquiryDto admissionEnquiryDto) {
		
		AdmissionEnquiryResponseDto admissionEnquiryResponseDto = new AdmissionEnquiryResponseDto();
		AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry();
		admissionEnquiry.setName(admissionEnquiryDto.getName());
	    admissionEnquiry.setGender(admissionEnquiryDto.getGender());
	    admissionEnquiry.setCaste(admissionEnquiryDto.getCaste());
	    admissionEnquiry.setPlaceOfBirth(admissionEnquiryDto.getPlaceOfBirth());
	    admissionEnquiry.setSurName(admissionEnquiryDto.getSurName());
	    admissionEnquiry.setPreviousClassPassed(admissionEnquiryDto.getPreviousClassPassed());
	    admissionEnquiry.setPreviousSchoolName(admissionEnquiryDto.getPreviousSchoolName());
	    admissionEnquiry.setReligion(admissionEnquiryDto.getReligion());
	    admissionEnquiry.setFathername(admissionEnquiryDto.getFathername());
	    admissionEnquiry.setFatherQualification(admissionEnquiryDto.getFatherQualification());
	    admissionEnquiry.setMothername(admissionEnquiryDto.getMothername());
	    admissionEnquiry.setMotherQualification(admissionEnquiryDto.getMotherQualification());
	    admissionEnquiry.setAdmissionclass(admissionEnquiryDto.getAdmissionclass());
	    admissionEnquiry.setBrothereducation(admissionEnquiryDto.getBrothereducation());
	    admissionEnquiry.setSistereducation(admissionEnquiryDto.getSistereducation());
	    admissionEnquiry.setOccupation(admissionEnquiryDto.getOccupation());
	    admissionEnquiry.setDateofbirth(DateUtil.indiandateParser(admissionEnquiryDto.getDateofbirth()));
	    admissionEnquiry.setAddress(admissionEnquiryDto.getAddress());
	    admissionEnquiry.setMobileno(admissionEnquiryDto.getMobileno());
	    admissionEnquiry.setAcademicYear(admissionEnquiryDto.getAcademicYear());
	    admissionEnquiry.setNotes(admissionEnquiryDto.getNotes());
	    
	    boolean result = new enquiryDAO().add(admissionEnquiry);
	    
	    admissionEnquiryResponseDto.setName(admissionEnquiryDto.getName());
	    admissionEnquiryResponseDto.setGender(admissionEnquiryDto.getGender());
	    admissionEnquiryResponseDto.setCaste(admissionEnquiryDto.getCaste());
	    admissionEnquiryResponseDto.setPlaceOfBirth(admissionEnquiryDto.getPlaceOfBirth());
	    admissionEnquiryResponseDto.setSurName(admissionEnquiryDto.getSurName());
	    admissionEnquiryResponseDto.setPreviousClassPassed(admissionEnquiryDto.getPreviousClassPassed());
	    admissionEnquiryResponseDto.setPreviousSchoolName(admissionEnquiryDto.getPreviousSchoolName());
	    admissionEnquiryResponseDto.setReligion(admissionEnquiryDto.getReligion());
	    admissionEnquiryResponseDto.setFathername(admissionEnquiryDto.getFathername());
	    admissionEnquiryResponseDto.setFatherQualification(admissionEnquiryDto.getFatherQualification());
	    admissionEnquiryResponseDto.setMothername(admissionEnquiryDto.getMothername());
	    admissionEnquiryResponseDto.setMotherQualification(admissionEnquiryDto.getMotherQualification());
	    admissionEnquiryResponseDto.setAdmissionclass(admissionEnquiryDto.getAdmissionclass());
	    admissionEnquiryResponseDto.setBrothereducation(admissionEnquiryDto.getBrothereducation());
	    admissionEnquiryResponseDto.setSistereducation(admissionEnquiryDto.getSistereducation());
	    admissionEnquiryResponseDto.setOccupation(admissionEnquiryDto.getOccupation());
	    admissionEnquiryResponseDto.setDateofbirth(admissionEnquiryDto.getDateofbirth());
	    admissionEnquiryResponseDto.setAddress(admissionEnquiryDto.getAddress());
	    admissionEnquiryResponseDto.setMobileno(admissionEnquiryDto.getMobileno());
	    admissionEnquiryResponseDto.setAcademicYear(admissionEnquiryDto.getAcademicYear());
	    admissionEnquiryResponseDto.setNotes(admissionEnquiryDto.getNotes());
	    
	    admissionEnquiryResponseDto.setSuccess(true);
	    
		return admissionEnquiryResponseDto;
	}


	public AdmissionEnquiryResponseDto getStudentLastEnquiry(String branchId) {
        
		AdmissionEnquiryResponseDto admissionEnquiryResponseDto = new AdmissionEnquiryResponseDto();
		AdmissionEnquiry admissionEnquiry = new enquiryDAO().getStudentLastEnquiry(Integer.parseInt(branchId.toString()));
		admissionEnquiryResponseDto.setAdmissionEnquiry(admissionEnquiry);
		return admissionEnquiryResponseDto;
	}


	public AdmissionEnquiryResponseDto viewEnquiry(String branchId) {
  
		AdmissionEnquiryResponseDto admissionEnquiryResponseDto = new AdmissionEnquiryResponseDto();
		List<AdmissionEnquiry> admissionEnquiryList = new enquiryDAO().viewEnquiryList(Integer.parseInt(branchId.toString()));
		admissionEnquiryResponseDto.setAdmissionEnquiryList(admissionEnquiryList);
		return admissionEnquiryResponseDto;
	}


	public AdmissionEnquiryResponseDto getStudentEnquiry(AdmissionEnquiryDto admissionEnquiryDto) {

		AdmissionEnquiryResponseDto admissionEnquiryResponseDto = new AdmissionEnquiryResponseDto();
		int id= admissionEnquiryDto.getId();
		AdmissionEnquiry admissionEnquiry = new enquiryDAO().getStudentEnquiry(id);
		admissionEnquiryResponseDto.setAdmissionEnquiry(admissionEnquiry);
		return admissionEnquiryResponseDto;
	}


	public AdmissionEnquiryResponseDto updateEnquiry(AdmissionEnquiryDto admissionEnquiryDto) {
		
		AdmissionEnquiryResponseDto admissionEnquiryResponseDto = new AdmissionEnquiryResponseDto();
		AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry();
		admissionEnquiry.setId(admissionEnquiryDto.getId());
		admissionEnquiry.setName(admissionEnquiryDto.getName());
	    admissionEnquiry.setGender(admissionEnquiryDto.getGender());
	    admissionEnquiry.setCaste(admissionEnquiryDto.getCaste());
	    admissionEnquiry.setPlaceOfBirth(admissionEnquiryDto.getPlaceOfBirth());
	    admissionEnquiry.setSurName(admissionEnquiryDto.getSurName());
	    admissionEnquiry.setPreviousClassPassed(admissionEnquiryDto.getPreviousClassPassed());
	    admissionEnquiry.setPreviousSchoolName(admissionEnquiryDto.getPreviousSchoolName());
	    admissionEnquiry.setReligion(admissionEnquiryDto.getReligion());
	    admissionEnquiry.setFathername(admissionEnquiryDto.getFathername());
	    admissionEnquiry.setFatherQualification(admissionEnquiryDto.getFatherQualification());
	    admissionEnquiry.setMothername(admissionEnquiryDto.getMothername());
	    admissionEnquiry.setMotherQualification(admissionEnquiryDto.getMotherQualification());
	    admissionEnquiry.setAdmissionclass(admissionEnquiryDto.getAdmissionclass());
	    admissionEnquiry.setBrothereducation(admissionEnquiryDto.getBrothereducation());
	    admissionEnquiry.setSistereducation(admissionEnquiryDto.getSistereducation());
	    admissionEnquiry.setOccupation(admissionEnquiryDto.getOccupation());
	    admissionEnquiry.setDateofbirth(DateUtil.indiandateParser(admissionEnquiryDto.getDateofbirth()));
	    admissionEnquiry.setAddress(admissionEnquiryDto.getAddress());
	    admissionEnquiry.setMobileno(admissionEnquiryDto.getMobileno());
	    admissionEnquiry.setAcademicYear(admissionEnquiryDto.getAcademicYear());
	    admissionEnquiry.setNotes(admissionEnquiryDto.getNotes());
	    new enquiryDAO().update(admissionEnquiry);
	    admissionEnquiryResponseDto.setSuccess(true);
	    return admissionEnquiryResponseDto;
		
	}


	public AdmissionEnquiryResponseDto deleteEnquiry(AdmissionEnquiryDto admissionEnquiryDto) {

		AdmissionEnquiryResponseDto admissionEnquiryResponseDto = new AdmissionEnquiryResponseDto();
		String[] enquiryIds = admissionEnquiryDto.getEnquiryIds();
		if (enquiryIds != null) {
			List<Integer> ids = new ArrayList();
			for (String id : enquiryIds) {
				ids.add(Integer.parseInt(id));
			}
			boolean result = new enquiryDAO().deleteEnquiry(ids);
			admissionEnquiryResponseDto.setSuccess(result);
			
		}
		
		return admissionEnquiryResponseDto;
	    }

	}

