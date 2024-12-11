package org.ideoholic.curium.model.enquiry.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.enquiry.dto.CertificateDto;
import org.ideoholic.curium.model.enquiry.dto.CertificateResponseDto;
import org.ideoholic.curium.model.enquiry.service.EnquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnquiryActionAdapter {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private EnquiryService enquiryService;


    public void getCertificate() {

        CertificateDto dto = new CertificateDto();
        dto.setName(request.getParameter("subject"));
        dto.setPlace(request.getParameter("place"));
        dto.setMobile(request.getParameter("mobile"));
        dto.setDate(request.getParameter("date"));

        CertificateResponseDto responseDto = enquiryService.getCertificate(dto);
        request.setAttribute("name", responseDto.getName());
        request.setAttribute("place", responseDto.getPlace());
        request.setAttribute("mobile", responseDto.getMobile());
        request.setAttribute("date", responseDto.getDate());
    }
}
