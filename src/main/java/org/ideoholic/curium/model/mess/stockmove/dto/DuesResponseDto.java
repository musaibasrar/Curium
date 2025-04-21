package org.ideoholic.curium.model.mess.stockmove.dto;

import java.util.List;

import org.ideoholic.curium.model.mess.item.dto.MessStockMoveInfo;

import lombok.Data;

@Data
public class DuesResponseDto {
	
	private List<MessStockMoveInfo> messStockMoveInfoList;

}
