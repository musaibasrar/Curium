package org.ideoholic.curium.model.student.dto;

import java.util.List;

import org.ideoholic.curium.model.parents.dto.Parents;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class BonafideGenerationResponseDto {

    @Builder.Default
    private boolean success = false;
    private List<Parents> parentsList;
    private String message;
    private String dateOfBirth;
}
