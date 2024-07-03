package org.ideoholic.curium.model.mess.item.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemDetailsResponseDto {
    private boolean itemSave;
    private String itemName;
    private boolean success;
}
