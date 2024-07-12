package org.ideoholic.curium.model.feescategory.dto;

import java.util.List;
import org.ideoholic.curium.model.parents.dto.Parents;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParentResponseDto {
		
	private List<Parents> studentListFeesCollection;
	private boolean success;
}
