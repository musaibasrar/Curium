package org.ideoholic.curium.model.student.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.ideoholic.curium.model.parents.dto.Parents;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class BonafideGenerationResponseDto {

    @Builder.Default
    private boolean success = false;
    private Parents parents;
    private String message;
    private String dateInWord;
}
