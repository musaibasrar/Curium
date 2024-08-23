package org.ideoholic.curium.model.marksdetails.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.ideoholic.curium.util.ExamsDetails;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class StudentGraphResponseDto {
    private List<ExamsDetails> examDetailsGraph;
    private int examDetailsGraphSize;
    private String searchStudent;
    @Builder.Default
    private boolean success = false;
}
