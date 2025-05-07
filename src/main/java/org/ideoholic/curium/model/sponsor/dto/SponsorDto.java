package org.ideoholic.curium.model.sponsor.dto;

import lombok.Data;

@Data
public class SponsorDto {
	
	private String id;	
	private String name;	
	private String contact;	
	private String address;	
	private String notes;	
	private String[] sponsorIds;
	private int userId;
	private int branchId;

}
