package org.ideoholic.curium.model.sponsor.dto;

import java.util.List;

import lombok.Data;

@Data
public class SponsorResponseDto {
	
    private List<Sponsor> list;
    
    private Sponsor sponsor;
    
    private boolean success;

}
