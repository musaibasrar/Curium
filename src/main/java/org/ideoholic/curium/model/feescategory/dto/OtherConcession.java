package org.ideoholic.curium.model.feescategory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OtherConcession {
	
	private int sfsid;
	private int feescatid;
	private String concession;
	private String concessionOld;

}
