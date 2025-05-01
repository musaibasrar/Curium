package org.ideoholic.curium.model.sponsor.service;

import java.util.ArrayList;
import java.util.List;

import org.ideoholic.curium.model.employee.dao.EmployeeDAO;
import org.ideoholic.curium.model.employee.dto.EmployeeDetailsResponseDto;
import org.ideoholic.curium.model.employee.dto.Teacher;
import org.ideoholic.curium.model.sponsor.dao.SponsorDao;
import org.ideoholic.curium.model.sponsor.dto.Sponsor;
import org.ideoholic.curium.model.sponsor.dto.SponsorDto;
import org.ideoholic.curium.model.sponsor.dto.SponsorResponseDto;
import org.ideoholic.curium.model.user.dao.UserDAO;
import org.ideoholic.curium.model.user.dto.Login;
import org.springframework.stereotype.Service;

@Service
public class SponsorService {

	public SponsorResponseDto addSponsor(SponsorDto sponsorDto, String branchid, String userid) {
		SponsorResponseDto sponsorResponseDto = new SponsorResponseDto();
		boolean result = false;
		Sponsor sponsor = new Sponsor();
		String name = sponsorDto.getName();
		String contact = sponsorDto.getContact();
		String address = sponsorDto.getAddress();
		String notes = sponsorDto.getNotes();
		sponsor.setName(name);
		sponsor.setContact(contact);
		sponsor.setAddress(address);
		sponsor.setNotes(notes);
		sponsor.setBranchid(Integer.parseInt(branchid));
		sponsor.setUserid(Integer.parseInt(userid));
		result = new SponsorDao().addSponsor(sponsor);
		if(result) {
			sponsorResponseDto.setSuccess(true);	
		}
		return sponsorResponseDto;
		
	}

	public SponsorResponseDto viewAllSponsor(String branchid) {
		int branchId = Integer.parseInt(branchid);
		SponsorResponseDto sponsorResponseDto = new SponsorResponseDto();
		List<Sponsor> list = new SponsorDao().viewSponsor(branchId);
		sponsorResponseDto.setList(list);
		return sponsorResponseDto;
	}

	public void deleteMultiple(SponsorDto sponsorDto) {
		 String[] sponsorIds = sponsorDto.getSponsorIds();
		 if(sponsorIds!=null){
	        List<Integer> ids = new ArrayList();
	        for (String id : sponsorIds) {
	            ids.add(Integer.valueOf(id));

	        }
	        new SponsorDao().deleteMultiple(ids);
		 }
		
	}

	public SponsorResponseDto viewDetailsSponsor(String spId) {
		SponsorResponseDto sponsorResponseDto = new SponsorResponseDto();

		 boolean result = false;
	        try {
	            int id = Integer.parseInt(spId);
	            Sponsor sponsor = new SponsorDao().readUniqueObject(id);
	           
	            if (sponsor != null) {
	            	sponsorResponseDto.setSponsor(sponsor);
	            	sponsorResponseDto.setSuccess(true);
	            } 
	        } catch (Exception e) {
	            e.printStackTrace();
	            sponsorResponseDto.setSuccess(false);
	        }
	        return sponsorResponseDto;
	}

	public SponsorResponseDto updateSponsor(SponsorDto sponsorDto) {
		
		SponsorResponseDto sponsorResponseDto = new SponsorResponseDto();
		boolean result = false;
		Sponsor sponsor = new Sponsor();
		String id = sponsorDto.getId();
		String name = sponsorDto.getName();
		String contact = sponsorDto.getContact();
		String address = sponsorDto.getAddress();
		String notes = sponsorDto.getNotes();
		sponsor.setId(Integer.parseInt(id));
		sponsor.setName(name);
		sponsor.setContact(contact);
		sponsor.setAddress(address);
		sponsor.setNotes(notes);
		result = new SponsorDao().updateSponsor(sponsor);	
	    sponsorResponseDto.setSuccess(result);	
		return sponsorResponseDto;
	}

}
