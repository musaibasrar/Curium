package org.ideoholic.curium.model.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountSsGroupMasterDto {
    private Integer ssGroupMasterId;
    private String ssGroupName;
    private Integer subGroupMasterId;
    private Accountsubgroupmaster accountSubGroupMaster;
    private int branchId;
    private int userId;
}
