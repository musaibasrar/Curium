package org.ideoholic.curium.model.enquiry.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.diary.dao.diaryDAO;
import org.ideoholic.curium.model.enquiry.dao.enquiryDAO;
import org.ideoholic.curium.model.enquiry.dto.CertificateDto;
import org.ideoholic.curium.model.enquiry.dto.CertificateResponseDto;
import org.ideoholic.curium.model.enquiry.dto.Enquiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnquiryService {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;
	
	@Autowired
	private enquiryDAO enquiryDao;


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
	          
	            enquiryDao.create(enquiry);
                certificateResponseDto.setSuccess(true);
				return certificateResponseDto;
                }

		}

