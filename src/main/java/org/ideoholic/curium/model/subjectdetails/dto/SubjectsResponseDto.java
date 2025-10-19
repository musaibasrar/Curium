package org.ideoholic.curium.model.subjectdetails.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

import org.ideoholic.curium.model.std.dto.Classsec;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class SubjectsResponseDto {
    private List<Subject> subjects;
    private List<Subjectmaster> listSubjectNames;
    private boolean success;
    private List<Classsec> classSecs;
}
