package org.ideoholic.curium.model.teachersperformance.dto;

import java.util.List;

import org.ideoholic.curium.model.examdetails.dto.Exams;
import org.ideoholic.curium.model.examdetails.dto.ExamsListResponseDto;
import org.ideoholic.curium.model.subjectdetails.dto.Subject;
import org.ideoholic.curium.model.subjectdetails.dto.SubjectsResponseDto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class ExamSubectResponseDto {
	private List<Subject> list;
    private boolean success;
    private List<Exams> exams;

	public  void copySubjectsResponseDto(SubjectsResponseDto subjectsResponseDto) {
		list = subjectsResponseDto.getList();
	}

	public  void copyExamsListResponseDto(ExamsListResponseDto examsListResponseDto) {
		exams = examsListResponseDto.getExams();
	}

}
