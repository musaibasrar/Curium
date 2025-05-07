package org.ideoholic.curium.model.sponsor.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.employee.dto.EmployeeDetailsResponseDto;
import org.ideoholic.curium.model.employee.dto.EmployeeIdsDto;
import org.ideoholic.curium.model.sponsor.dto.SponsorDto;
import org.ideoholic.curium.model.sponsor.dto.SponsorResponseDto;
import org.ideoholic.curium.model.sponsor.service.SponsorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SponsorActionAdapter {
	
	@Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private SponsorService sponsorService;
    
    private String BRANCHID = "branchid";
    
    private String USERID = "userloginid";
	
    public boolean addSponsor() {
    SponsorDto sponsorDto = new SponsorDto();
    sponsorDto.setName(request.getParameter("name"));
    sponsorDto.setContact(request.getParameter("contact"));
    sponsorDto.setNotes(request.getParameter("notes"));
    sponsorDto.setAddress( request.getParameter("address"));
    SponsorResponseDto sponsorResponseDto = sponsorService.addSponsor(sponsorDto,httpSession.getAttribute(BRANCHID).toString(),httpSession.getAttribute(USERID).toString());
    return sponsorResponseDto.isSuccess();
	}

	public void viewAllSponsor() {

		SponsorResponseDto sponsorResponseDto = sponsorService.viewAllSponsor(httpSession.getAttribute(BRANCHID).toString());
		request.setAttribute("sponsorlist", sponsorResponseDto.getList());
		
	}

	public void deleteMultiple() {
		SponsorDto sponsorDto = new SponsorDto();
		sponsorDto.setSponsorIds(request.getParameterValues("sponsorIDs"));
	        sponsorService.deleteMultiple(sponsorDto);
	}

	public boolean viewDetailsSponsor() {
		SponsorResponseDto sponsorResponseDto = sponsorService.viewDetailsSponsor(request.getParameter("id"));

        httpSession.setAttribute("sponsor", sponsorResponseDto.getSponsor());

        return sponsorResponseDto.isSuccess();
		
	}

	public boolean updateSponsor() {
		SponsorDto sponsorDto = new SponsorDto();
		sponsorDto.setId(request.getParameter("id"));
	    sponsorDto.setName(request.getParameter("name"));
	    sponsorDto.setContact(request.getParameter("contact"));
	    sponsorDto.setNotes(request.getParameter("notes"));
	    sponsorDto.setAddress( request.getParameter("address"));
	    sponsorDto.setBranchId(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
	    sponsorDto.setUserId(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
	    SponsorResponseDto sponsorResponseDto = sponsorService.updateSponsor(sponsorDto);
	    return sponsorResponseDto.isSuccess();
		
	}

	public boolean viewSponsor() {
		ResultResponse resultResponse = sponsorService.viewSponsor(httpSession.getAttribute(BRANCHID).toString());
        httpSession.setAttribute("sponsorlist", resultResponse.getResultList());
        return resultResponse.isSuccess();
		
	}

}
