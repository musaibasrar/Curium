package org.ideoholic.curium.model.studentdiary.dto;

import java.util.List;

import org.ideoholic.curium.util.SubjectAverage;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class TeacherDetailResponseDto {
	
	private List<SubjectAverage> subjectAverageList;
	private int subjectAverageListSize;
	private String subjectName;

}
