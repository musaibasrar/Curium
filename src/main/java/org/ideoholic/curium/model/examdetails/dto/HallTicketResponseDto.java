package org.ideoholic.curium.model.examdetails.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.ideoholic.curium.model.parents.dto.Parents;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class HallTicketResponseDto {
    private List<Parents> studentList ;
    private String examname;
    private List<Examschedule> examscheduleList;
    private String urlbranchid;
}
