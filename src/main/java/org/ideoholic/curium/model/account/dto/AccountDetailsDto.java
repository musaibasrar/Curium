package org.ideoholic.curium.model.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDetailsDto {
    private Integer accountDetailsId;
    private int accountSubgroupMasterId;
    private int ssGroupMasterId;
    private int accountGroupId;
    private String accountName;
    private String accountCode;
    private Accountssgroupmaster accountSSGroupMaster;
    private Accountsubgroupmaster accountSubGroupMaster;
    private Accountgroupmaster accountGroupMaster;
    private int branchId;
    private int userId;
}
