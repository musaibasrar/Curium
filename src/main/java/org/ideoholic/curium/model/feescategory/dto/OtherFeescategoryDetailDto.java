package org.ideoholic.curium.model.feescategory.dto;

import java.util.List;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.parents.dto.ParentListResponseDto;
import org.ideoholic.curium.model.parents.dto.Parents;

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

public class OtherFeescategoryDetailDto {
	
	private List<OtherFeecategory> otherFeesCategory;
	private List<Parents> list;
	private boolean success;
	public void copyOtherFeesCategoryResponseDto(OtherFeesCategoryResponseDto otherFeesCategoryResponseDto) {
		otherFeesCategory=otherFeesCategoryResponseDto.getOtherFeesCategory();
		success= otherFeesCategoryResponseDto.isSuccess();
		
	}
	public void copyParentListResponseDto(ParentListResponseDto parentResponseDto) {
		list = parentResponseDto.getList();
		
	}
	public void copyResultResponse(ResultResponse result) {
		success= result.isSuccess();
		
	}

}
