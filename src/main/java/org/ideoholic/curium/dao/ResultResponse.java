package org.ideoholic.curium.dao;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class ResultResponse {

    @Builder.Default
    private boolean success = false;
    private String message;
    private Map resultMap;
    private Integer resultValue;
    private List resultList;
}
