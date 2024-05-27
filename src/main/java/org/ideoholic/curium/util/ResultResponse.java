package org.ideoholic.curium.util;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class ResultResponse {

    @Builder.Default
    private boolean success = false;
    private String message;
    private Map resultMap;
    private int resultValue;
    private List resultList;
}
