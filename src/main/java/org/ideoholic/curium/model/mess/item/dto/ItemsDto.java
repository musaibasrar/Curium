package org.ideoholic.curium.model.mess.item.dto;

import lombok.Data;

import java.util.Map;

@Data
public class ItemsDto {
    private String[] messIds;
    private Map<String, String> requestParams;
}