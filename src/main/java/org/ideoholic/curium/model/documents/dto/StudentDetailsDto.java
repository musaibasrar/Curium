package org.ideoholic.curium.model.documents.dto;

import java.util.List;

import org.ideoholic.curium.model.examdetails.dto.Exams;
import org.ideoholic.curium.model.feescategory.dto.Feescategory;
import org.ideoholic.curium.model.parents.dto.ParentListResponseDto;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.stampfees.dto.FeesDetailsDto;
import org.ideoholic.curium.model.std.dto.Classsec;
import org.ideoholic.curium.model.subjectdetails.dto.Subject;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class StudentDetailsDto {

	private List<Parents> searchStudentList;
	private List<Exams> examsList;
	private List<Subject> subjectList;
	private List<Parents> list;
	private boolean success;
	private int page;
	private int noOfPages;
	public void copySearchStudentResponseDto(SearchStudentResponseDto searchStudentResponseDto) {
		searchStudentList = searchStudentResponseDto.getSearchStudentList();
		examsList = searchStudentResponseDto.getExamsList();
		subjectList = searchStudentResponseDto.getSubjectList();
		
	}
	public void copyParentListResponseDto(ParentListResponseDto parentListResponseDto) {
		list = parentListResponseDto.getParentsList();
		
	}
}
