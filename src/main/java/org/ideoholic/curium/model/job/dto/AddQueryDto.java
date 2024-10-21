package org.ideoholic.curium.model.job.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddQueryDto {

	    private String[] employeeIDs;
	    private String[] assignto;
	    private String[] task;
	    private String[] description;
	    private String[] expecteddeliverydatetask;
	    private String jobquery;		
	    private String jobtitle;
	    private String expecteddeliverydate;
	    private String[] expecteddd; 
}
