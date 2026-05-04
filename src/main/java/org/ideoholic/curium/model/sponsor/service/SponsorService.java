package org.ideoholic.curium.model.sponsor.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.sponsor.dao.SponsorDao;
import org.ideoholic.curium.model.sponsor.dto.Sponsor;
import org.ideoholic.curium.model.sponsor.dto.SponsorDto;
import org.ideoholic.curium.model.sponsor.dto.SponsorResponseDto;
import org.ideoholic.curium.model.student.dao.studentDetailsDAO;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.model.student.dto.Studentfeesstructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SponsorService {
	
	 @Autowired
	    private SponsorDao sponsorDao;
	    
	    @Autowired
	    private studentDetailsDAO studentDetailsDAO;
	
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
		result = sponsorDao.addSponsor(sponsor);
		if(result) {
			sponsorResponseDto.setSuccess(true);	
		}
		return sponsorResponseDto;
		
	}

	public SponsorResponseDto viewAllSponsor(String branchid) {
		int branchId = Integer.parseInt(branchid);
		SponsorResponseDto sponsorResponseDto = new SponsorResponseDto();
		List<Sponsor> list = sponsorDao.viewSponsor(branchId);
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
	        sponsorDao.deleteMultiple(ids);
		 }
		
	}

	public SponsorResponseDto viewDetailsSponsor(String spId) {
		SponsorResponseDto sponsorResponseDto = new SponsorResponseDto();

		 boolean result = false;
	        try {
	            int id = Integer.parseInt(spId);
	            Sponsor sponsor = sponsorDao.readUniqueObject(id);
	           
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
		int userId = sponsorDto.getUserId();
		int branchId = sponsorDto.getBranchId();
		
		sponsor.setId(Integer.parseInt(id));
		sponsor.setName(name);
		sponsor.setContact(contact);
		sponsor.setAddress(address);
		sponsor.setNotes(notes);
		sponsor.setUserid(userId);
		sponsor.setBranchid(branchId);
		result = sponsorDao.updateSponsor(sponsor);	
	    sponsorResponseDto.setSuccess(result);	
		return sponsorResponseDto;
	}

	public ResultResponse viewSponsor(String branchId) {
		if (branchId != null) {
			List<Sponsor> sponsorList = sponsorDao.viewSponsor(Integer.parseInt(branchId));
			return ResultResponse.builder().resultList(sponsorList).success(true).build();
		}

		return ResultResponse.builder().success(false).build();
	}

	public SponsorResponseDto getFeesStructuredBySponsor(String branchid, SponsorDto sponsorDto) {
		SponsorResponseDto sponsorResponseDto = new SponsorResponseDto();
		Map<Student, Studentfeesstructure> mapOfSponsors = new HashMap<Student, Studentfeesstructure>();
		String sponsorName =  sponsorDto.getName();
		int branchId = Integer.parseInt(branchid);
		List<Studentfeesstructure> list = sponsorDao.getFeesStructuredBySponsor(branchId,sponsorName);
		List<Integer> sIds = new ArrayList<Integer>(); 
		for (Studentfeesstructure studentfeesstructure : list) {
			sIds.add(studentfeesstructure.getSid());
		}
		List<Student> listStudent = studentDetailsDAO.getStudentsListByIds(sIds);
		for (Student student : listStudent) {
			for (Studentfeesstructure studentfeesstructure : list) {
				int sids = student.getSid();
				int sidf = studentfeesstructure.getSid();
				if(sids==sidf) {
			mapOfSponsors.put(student, studentfeesstructure);
		}
		
	    }
		}	
		sponsorResponseDto.setMapOfSponsors(mapOfSponsors);
		return sponsorResponseDto;
	}	

}