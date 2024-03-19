package org.ideoholic.curium.model.account.mappers;

import org.ideoholic.curium.model.account.dto.*;
import org.ideoholic.curium.util.DataUtil;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValueCheckStrategy= NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, imports = DataUtil.class)
public interface AccountsMapper {
    AccountsMapper INSTANCE = Mappers.getMapper(AccountsMapper.class);

    @Mapping(target = "accountName", expression = "java(DataUtil.emptyString(accountDetailsDto.getAccountName()))")
    @Mapping(target = "accountCode", expression = "java(DataUtil.emptyString(accountDetailsDto.getAccountCode()))")
    @Mapping(target = "branchid", source = "branchId")
    @Mapping(target = "userid", source = "userId")
    Accountdetails mapAccountdetails(AccountDetailsDto accountDetailsDto);

    AccountDetailsDto mapAccountDetailsDto(Accountdetails accountdetails);


/*  @Mapping(target = "accountgroupid", expression = "java(DataUtil.emptyString(accountGroupMasterDto.getAccountGroupId()))")*/
    @Mapping(target = "accountgroupname", expression = "java(DataUtil.emptyString(accountGroupMasterDto.getAccountGroupName()))")
    Accountgroupmaster mapAccountgroupmaster(AccountGroupMasterDto  accountGroupMasterDto);

    AccountGroupMasterDto mapAccountGroupMasterDto(Accountgroupmaster accountgroupmaster);

    @Mapping(target = "accountsubgroupname", expression = "java(DataUtil.emptyString(accountSubGroupMasterDto.getAccountSubGroupName()))")
    Accountsubgroupmaster mapAccountsubgroupmaster(AccountSubGroupMasterDto accountSubGroupMasterDto);

    AccountSubGroupMasterDto mapAccountSubGroupMasterDto(Accountsubgroupmaster accountsubgroupmaster);

    @Mapping(target = "ssgroupname", expression = "java(DataUtil.emptyString(accountSsGroupMasterDto.getSsGroupName()))")
    Accountssgroupmaster mapAccountssgroupmaster(AccountSsGroupMasterDto accountSsGroupMasterDto);

    AccountSsGroupMasterDto mapAccountSsGroupMasterDto(Accountssgroupmaster accountssgroupmaster);
}
