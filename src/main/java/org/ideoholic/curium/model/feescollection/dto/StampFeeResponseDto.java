package org.ideoholic.curium.model.feescollection.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ideoholic.curium.model.student.dto.Studentfeesstructure;
import org.ideoholic.curium.model.student.dto.Studentotherfeesstructure;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class StampFeeResponseDto {
    private Map<Studentfeesstructure,Long> feesMapPreviousYear;
    private String previousYear;
    private Map<Studentfeesstructure,Long> feesMap;
    private String studentNameDetails;
    private String admNoDetails;
    private String classAndSecDetails;
    private String studentIdDetails;
    private String dateOfFeesDetails;
    private Map<Studentotherfeesstructure,Long> otherFeesMap;
    @Builder.Default
    private boolean success = false;
}
