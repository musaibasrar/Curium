package org.ideoholic.curium.model.documents.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.ideoholic.curium.model.examdetails.dto.Exams;
import org.ideoholic.curium.model.parents.dto.Parents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ideoholic.curium.model.subjectdetails.dto.Subject;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class SearchStudentResponseDto {
		@Builder.Default
		private boolean success = false;
		private List<Parents> searchStudentList;
		private List<Exams> examsList;
		private List<Subject> subjectList;
		private String classSearch;
}
