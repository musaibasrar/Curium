package org.ideoholic.curium.model.student.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.ideoholic.curium.model.parents.dto.Parents;

import java.util.List;
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class StudentsSuperAdminResponseDto {
    private List<Parents> studentList;
    private int noOfPages;
    private int page;
    @Builder.Default
    private boolean success = false;
}
