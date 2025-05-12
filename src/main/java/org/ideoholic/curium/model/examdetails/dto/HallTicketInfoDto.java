package org.ideoholic.curium.model.examdetails.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.ideoholic.curium.model.parents.dto.Parents;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class HallTicketInfoDto {
    private String[] studentIds;
    private String[] blockNos;
    private String[] seatNos;
}
