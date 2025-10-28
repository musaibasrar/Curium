package org.ideoholic.curium.model.sponsor.dto;

import java.util.List;
import java.util.Map;

import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.model.student.dto.Studentfeesstructure;

import lombok.Data;

@Data
public class SponsorResponseDto {
	
    private List<Sponsor> list;
    
    private Sponsor sponsor;
    
    private boolean success;
    
    private Map<Student, Studentfeesstructure> mapOfSponsors;

}
