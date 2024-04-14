package org.ideoholic.curium.util;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultResponse {

    @Builder.Default
    private boolean success = false;
    private String message;

}
