package org.ideoholic.curium.model.marksdetails.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.ideoholic.curium.model.parents.dto.Parents;

import java.util.List;
import java.util.Map;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class MarksResponseDto {
    private List<Parents> newStudentList;
    private List<Marks> newMarksDetails;
    private String subjectSelected;
    private String examSelected;
    private String subject;
    private String exam;
    Map<Parents,Map<Integer,Float>> studentsMarksMap;
    @Builder.Default
    private boolean success = false;
}
