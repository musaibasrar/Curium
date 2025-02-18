package org.ideoholic.curium.model.feescollection.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.std.dto.Classsec;
import org.ideoholic.curium.model.student.dto.Studentotherfeesstructure;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class OtherStampFeesResponseDto {
    private String studentNameDetails;
    private String admNoDetails;
    private String classAndSecDetails;
    private String studentIdDetails;
    private String dateOfFeesDetails;
    private long totalSum;
    private long totalFeesAmount;
    private long dueAmount;
    private String academicPerYear;
    private String currentAcademicYear;
    private List<Studentotherfeesstructure> otherFeesStructure;
    private Map<Studentotherfeesstructure, Long> otherFeesMap;
    private List<Classsec> classSecList;
    private List<Parents> parentsList;

    @Builder.Default
    private boolean success = false;
}