package org.ideoholic.curium.model.feescategory.dto;


import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConcessionDto {
	
	private String[] sfsid;
	private String id;
	Map<String, String> requestParams;
}
