package org.ideoholic.curium.model.account.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateAccountResponseDto {
	private List<Accountdetailsbalance> accountDetailsBalance;
	private List<Accountgroupmaster> accountGroupMaster;
	private boolean success;
	private String message;
}
