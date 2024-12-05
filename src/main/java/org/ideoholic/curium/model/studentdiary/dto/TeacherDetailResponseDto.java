package org.ideoholic.curium.model.studentdiary.dto;

import java.util.List;

import org.ideoholic.curium.util.SubjectAverage;

import lombok.Data;
@Data
public class TeacherDetailResponseDto {
	
	private List<SubjectAverage> subjectaverageList;
	private int subjectaveragelistsize;
	private String subjectName;

}
