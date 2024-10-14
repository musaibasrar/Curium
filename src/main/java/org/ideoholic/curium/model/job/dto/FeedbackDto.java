package org.ideoholic.curium.model.job.dto;

import java.util.List;

import org.ideoholic.curium.model.documents.dto.SearchStudentResponseDto;
import org.ideoholic.curium.model.examdetails.dto.Exams;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.subjectdetails.dto.Subject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackDto {
	
	private String id;
	private String no;
	private String feedback;
	

}
