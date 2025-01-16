package org.ideoholic.curium.model.teachersperformance.dto;

import java.util.List;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.examdetails.dto.Exams;
import org.ideoholic.curium.model.examdetails.dto.ExamsListResponseDto;
import org.ideoholic.curium.model.std.dto.Classsec;
import org.ideoholic.curium.model.subjectdetails.dto.Subject;
import org.ideoholic.curium.model.subjectdetails.dto.SubjectsResponseDto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class ExamSubectResponseDto {
    private boolean success;
    private List<Exams> exams;
    private List<Subject> subjects;
	private List<Classsec> classSecs;

	public  void copySubjectsResponseDto(SubjectsResponseDto subjectsResponseDto) {
		subjects = subjectsResponseDto.getSubjects();
	}

	public  void copyExamsListResponseDto(ExamsListResponseDto examsListResponseDto) {
		exams = examsListResponseDto.getExams();
	}

	public void copyClassSec(ResultResponse result) {
		classSecs = result.getResultList();
		
	}

}
