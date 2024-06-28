package org.ideoholic.curium.model.enquiry.action;

import org.ideoholic.curium.model.enquiry.dto.CertificateDto;
import org.ideoholic.curium.model.enquiry.dto.CertificateResponseDto;
import org.ideoholic.curium.model.enquiry.service.EnquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
public class EnquiryActionAdapter {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private HttpSession httpSession;;


    public void getCertificate() {
        EnquiryService enquiryService = new EnquiryService(request, response);

        CertificateDto dto = new CertificateDto();
        dto.setName(request.getParameter("subject"));
        dto.setPlace(request.getParameter("place"));
        dto.setMobile(request.getParameter("mobile"));
        dto.setDate(request.getParameter("date"));

        CertificateResponseDto responseDto = enquiryService.getCertificate(dto);
        request.setAttribute("name", dto.getName());
        request.setAttribute("place", dto.getPlace());
        request.setAttribute("mobile", dto.getMobile());
        request.setAttribute("date", dto.getDate());
    }
}
