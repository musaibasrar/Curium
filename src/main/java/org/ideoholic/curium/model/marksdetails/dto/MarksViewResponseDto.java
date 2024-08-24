package org.ideoholic.curium.model.marksdetails.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.ideoholic.curium.model.parents.dto.Parents;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class MarksViewResponseDto {
    private List<Parents> newStudentList;
    private List<Marks> newMarksDetails;
    private String subjectSelected;
    private String examSelected;
    private String subject;
    private String exam;
    @Builder.Default
    private boolean success = false;
}
