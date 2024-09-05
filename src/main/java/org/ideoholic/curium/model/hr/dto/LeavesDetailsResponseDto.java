package org.ideoholic.curium.model.hr.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.ideoholic.curium.model.academicyear.dto.Currentacademicyear;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)

public class LeavesDetailsResponseDto {

    private Currentacademicyear currentAcademicYear;
    private List<Leavedetails> leaveDetailsList;
    private String teachername;
    private String leavedetailsteachersid;
    private String academicPerYear;
    private boolean success;

}
