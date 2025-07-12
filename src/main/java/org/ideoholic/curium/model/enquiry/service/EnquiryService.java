package org.ideoholic.curium.model.enquiry.service;

import org.ideoholic.curium.model.enquiry.dao.EnquiryDAO;
import org.ideoholic.curium.model.enquiry.dto.CertificateDto;
import org.ideoholic.curium.model.enquiry.dto.CertificateResponseDto;
import org.ideoholic.curium.model.enquiry.dto.Enquiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnquiryService {

	@Autowired
	private EnquiryDAO enquiryDao;

	public CertificateResponseDto getCertificate(CertificateDto dto) {
		CertificateResponseDto certificateResponseDto = CertificateResponseDto.builder().success(false).build();

		String name = dto.getName();
		String place = dto.getPlace();
		String mobile = dto.getMobile();
		String date = dto.getDate();

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
