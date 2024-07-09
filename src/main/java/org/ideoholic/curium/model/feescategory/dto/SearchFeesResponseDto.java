package org.ideoholic.curium.model.feescategory.dto;

import java.util.List;
import java.util.Map;

import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.student.dto.Studentfeesstructure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchFeesResponseDto {
		private String currentYearFromService;
		private Map<Parents,List<Studentfeesstructure>> studentsFeesStructureDetailsWaiveoff;
		private Map<Parents,List<Studentfeesstructure>> studentsFeesStructureDetailsConcession;
}
