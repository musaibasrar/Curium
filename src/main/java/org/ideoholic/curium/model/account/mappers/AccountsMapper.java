package org.ideoholic.curium.model.account.mappers;

import org.ideoholic.curium.model.account.dto.AccountDetailsDto;
import org.ideoholic.curium.model.account.dto.Accountdetails;
import org.ideoholic.curium.util.DataUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValueCheckStrategy= NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, imports = DataUtil.class)
public interface AccountsMapper {

    @Mapping(target = "accountName", expression = "java(DataUtil.emptyString(accountDetailsDto.getAccountName()))")
    @Mapping(target = "accountCode", expression = "java(DataUtil.emptyString(accountDetailsDto.getAccountCode()))")
    @Mapping(target = "branchid", source = "branchId")
    @Mapping(target = "userid", source = "userId")
    Accountdetails mapAccountdetails(AccountDetailsDto accountDetailsDto);
    
    AccountDetailsDto mapAccountDetailsDto(Accountdetails accountdetails);

}