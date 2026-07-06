package org.ideoholic.curium.model.academicyear.dto;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrentAcademicYearDto {

	private Integer currentayid;
	private String currentacademicyear;
	private Date academicyearstartdate;
    private Date academicyearenddate;
    private Boolean active;
    private Integer branchid;
    private Integer userid;
}
