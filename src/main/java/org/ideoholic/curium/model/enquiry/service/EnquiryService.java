package org.ideoholic.curium.model.enquiry.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.diary.dao.diaryDAO;
import org.ideoholic.curium.model.diary.dto.Diary;
import org.ideoholic.curium.model.enquiry.dao.enquiryDAO;
import org.ideoholic.curium.model.enquiry.dto.CertificateDto;
import org.ideoholic.curium.model.enquiry.dto.CertificateResponseDto;
import org.ideoholic.curium.model.enquiry.dto.Enquiry;
import org.ideoholic.curium.util.DataUtil;
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



	public CertificateResponseDto getCertificate(CertificateDto dto) {
		CertificateResponseDto result = CertificateResponseDto.builder().build();
		
	    String name= dto.getName();
	    String place= dto.getPlace();
	    String mobile= dto.getMobile();
	    String date= dto.getDate();;
		result.setName(name);
		result.setPlace(place);
		result.setMobile(mobile);
		result.setDate(date);

	    Enquiry enquiry = new Enquiry();
        
       	 
                
	    enquiry.setName(name);
	    enquiry.setAddress(place);
	    enquiry.setMobileno(mobile);
	          
                enquiry =  new enquiryDAO().create(enquiry);
				result.setSuccess(true);
				return result;
                }

		}

