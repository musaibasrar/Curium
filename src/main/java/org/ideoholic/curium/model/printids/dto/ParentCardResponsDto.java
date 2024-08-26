package org.ideoholic.curium.model.printids.dto;

import java.util.Map;

import org.ideoholic.curium.model.mess.card.dto.Card;
import org.ideoholic.curium.model.parents.dto.Parents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParentCardResponsDto {
	
	private Map<Parents,Card> parentsCardList;

}
