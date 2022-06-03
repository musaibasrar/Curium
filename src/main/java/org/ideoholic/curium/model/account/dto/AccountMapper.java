package org.ideoholic.curium.model.account.dto;

import org.ideoholic.curium.util.DateUtil;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * https://mapstruct.org/
 * https://marketplace.eclipse.org/content/mapstruct-eclipse-plugin
 * https://mapstruct.org/documentation/ide-support/
 * https://stackoverflow.com/questions/45518161/how-to-get-eclipse-to-generate-mapstruct-mappers-using-gradle
 * 
 * @author Ideoholic
 *
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, imports = DateUtil.class)
public interface AccountMapper {
	AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

	Accountdetails mapAccoundetails(AccountDto accountDto);

	Accountdetailsbalance mapAccountdetailsbalance(AccountDto accountDto);

	Accountgroupmaster mapAccountgroupmaster(AccountDto accountDto);

	Accountssgroupmaster mapAccountssgroupmaster(AccountDto accountDto);

	Accountsubgroupmaster mapAccountsubgroupmaster(AccountDto accountDto);

	Financialaccountingyear mapFinancialaccountingyear(AccountDto accountDto);

	VoucherEntrytransactions mapVoucherEntrytransactions(AccountDto accountDto);
}
