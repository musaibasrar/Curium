package org.ideoholic.curium.model.account.mappers;

import org.ideoholic.curium.model.account.dto.*;
import org.ideoholic.curium.util.DataUtil;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValueCheckStrategy= NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, imports = DataUtil.class)
public interface AccountsMapper {
    AccountsMapper INSTANCE = Mappers.getMapper(AccountsMapper.class);

    @Mapping(target = "accountname", expression = "java(DataUtil.emptyString(accountDetailsDto.getAccountName()))")
    @Mapping(target = "accountcode", expression = "java(DataUtil.emptyString(accountDetailsDto.getAccountCode()))")
    Accountdetails mapAccountdetails(AccountDetailsDto accountDetailsDto);
    AccountDetailsDto mapAccountDetailsDto(Accountdetails accountdetails);


}
