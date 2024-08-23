package org.ideoholic.curium.model.marksdetails.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.ideoholic.curium.util.MarksSheet;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class GenerateReportResponseDto {
    private int endLoop;
    private List<MarksSheet> marksSheetList;
    @Builder.Default
    private boolean success = false;
}
