package org.ideoholic.curium.model.stampfees.dto;
import java.util.List;

import org.ideoholic.curium.model.academicyear.dto.Currentacademicyear;
import org.ideoholic.curium.model.feescategory.dto.Feescategory;
import org.ideoholic.curium.model.feescategory.dto.FeescategoryResponseDto;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.std.dto.Classsec;

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
public class FeesDetailsDto {
	private List<Feescategory> feescategory;
	private List<Parents> searchStudentList;
	private boolean success;
	private Integer cayid;
	private String currentacademicyear;
	private List<Classsec> classsecList;
	public void copyFeescategoryResponseDto(FeescategoryResponseDto feescategoryResponseDto) {
		feescategory=feescategoryResponseDto.getFeescategory();	
		searchStudentList= feescategoryResponseDto.getSearchStudentList();
	}
	public void copyCurrentacademicyear(Currentacademicyear currentAcademicYear) {
		currentacademicyear = currentAcademicYear.getCurrentacademicyear();
		cayid = currentAcademicYear.getCayid();

	}

}
