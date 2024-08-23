package org.ideoholic.curium.model.printids.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrintIdsDto {
	
	private String[] studentIDs;
	Map<String, String> requestParams;

}
