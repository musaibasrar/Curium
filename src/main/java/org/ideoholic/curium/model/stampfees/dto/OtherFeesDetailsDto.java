package org.ideoholic.curium.model.stampfees.dto;
import java.util.List;

import org.ideoholic.curium.model.feescategory.dto.OtherFeecategory;
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
public class OtherFeesDetailsDto {
	
	private List<OtherFeecategory> otherFeecategory;
	private boolean success;
	private Integer cayid;
	private String currentacademicyear;
	private List<Classsec> classsecList;

}
