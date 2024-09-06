package org.ideoholic.curium.model.feescategory.dto;

import java.util.List;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.parents.dto.ParentListResponseDto;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.student.dto.Student;

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
public class FeescategoryDetailDto {
	
	private List<Feescategory> feescategory;
	private List<Parents> searchStudentList;
	private List<Student> studentListFeesCollection;
	private boolean success;

	public void copyFeescategoryResponseDto(FeescategoryResponseDto feescategoryResponseDto) {
		feescategory=feescategoryResponseDto.getFeescategory();
		searchStudentList= feescategoryResponseDto.getSearchStudentList();
		success= feescategoryResponseDto.isSuccess();
		
	}

	public void copyResultResponse(ResultResponse result) {
		success= result.isSuccess();
		
	}

	public void copyParentListResponseDto(ParentListResponseDto parentResponseDto) {
		searchStudentList=parentResponseDto.getList();
		
	}

	public void copyStudentListResponseDto(StudentListResponseDto studentListResponseDto) {
		studentListFeesCollection= studentListResponseDto.getStudentListFeesCollection();
		
	}

}
