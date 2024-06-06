package org.ideoholic.curium.model.account.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CreateAccountResponseDto {
    private List accountDetailsBalance;
    private List accountGroupMaster;
    private boolean success;
}
