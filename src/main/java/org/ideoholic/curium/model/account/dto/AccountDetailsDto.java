package org.ideoholic.curium.model.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AccountDetailsDto implements java.io.Serializable{
    private Integer AccountDetailsId;
    private int AccountSubgroupMasterId;
    private int ssGroupMasterId;
    private int AccountGroupId;
    private String AccountName;
    private String AccountCode;
    private Accountssgroupmaster accountSSGroupMaster;
    private Accountsubgroupmaster accountSubGroupMaster;
    private Accountgroupmaster accountGroupMaster;
    private int BranchId;
    private int UserId;
}
