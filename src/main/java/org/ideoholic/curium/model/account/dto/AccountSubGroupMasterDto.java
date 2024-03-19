package org.ideoholic.curium.model.account.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountSubGroupMasterDto {
    private Integer accountSubGroupMasterId;
    private String accountSubGroupName;
    private Integer accountGroupId;
    private Accountgroupmaster accountGroupMaster;
    private int branchId;
    private int userId;
}
