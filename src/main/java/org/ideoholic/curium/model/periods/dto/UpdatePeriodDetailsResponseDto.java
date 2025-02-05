package org.ideoholic.curium.model.periods.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.ideoholic.curium.model.std.dto.Classsec;

import java.util.List;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class UpdatePeriodDetailsResponseDto {
    private Periodmaster periodMaster;
    private List<Perioddetails> periodDetails;
    private Map<String,List<Perioddetails>> periodMap;
    private String periodMasterId;
    private List<Classsec> classsecList;
}
