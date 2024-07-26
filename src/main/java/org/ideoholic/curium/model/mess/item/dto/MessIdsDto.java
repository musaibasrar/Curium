package org.ideoholic.curium.model.mess.item.dto;

import lombok.Data;

import java.util.Map;

@Data
public class MessIdsDto {
    private String[] messIds;
    private Map<String, String> requestParams;
}
