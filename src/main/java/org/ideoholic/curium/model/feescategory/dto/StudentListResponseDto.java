package org.ideoholic.curium.model.feescategory.dto;

import java.util.List;

import org.ideoholic.curium.model.student.dto.Student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentListResponseDto {
		private List<Student> studentList;
		@Builder.Default
		private boolean success = false;
		private String message;
}
