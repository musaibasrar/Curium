package org.ideoholic.curium.model.studentdiary.dto;

import org.ideoholic.curium.model.std.dto.ClassDto;

import lombok.Data;

@Data
public class AddStudentDiaryDto {
	
	private String studentId;
	private String classAndSec;
	private String messageBody;
	private String subject;
	private String createdDate;

}
