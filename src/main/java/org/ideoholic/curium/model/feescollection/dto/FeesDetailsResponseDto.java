package org.ideoholic.curium.model.feescollection.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ideoholic.curium.model.student.dto.Studentfeesstructure;
import org.ideoholic.curium.model.student.dto.Studentotherfeesstructure;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class FeesDetailsResponseDto {
    private List<Receiptinfo> receiptInfo;
    private List<Studentfeesstructure> feesStructure;
    private long totalSum;
    private long totalFeesAmount;
    private long dueAmount;
    private long totalFeesConcession;
    private String academicPerYear;
    private String currentAcademicYear;
    private List<Studentotherfeesstructure> otherFeesStructure;
    @Builder.Default
    private boolean success = false;
}
