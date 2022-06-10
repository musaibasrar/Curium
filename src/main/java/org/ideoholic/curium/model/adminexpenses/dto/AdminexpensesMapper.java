package org.ideoholic.curium.model.adminexpenses.dto;

import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.model.student.dto.StudentDto;
import org.ideoholic.curium.util.DateUtil;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.util.StringUtils;

@Mapper(imports = { StringUtils.class,
		DateUtil.class }, componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AdminexpensesMapper {
	AdminexpensesMapper INSTANCE = Mappers.getMapper(AdminexpensesMapper.class);
	
	Adminexpenses mapAdminexpenses(AdminexpensesDto adminexpensesDto);

}
