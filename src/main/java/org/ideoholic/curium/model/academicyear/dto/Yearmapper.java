package org.ideoholic.curium.model.academicyear.dto;
import org.ideoholic.curium.model.student.dto.StudentDto;
import org.ideoholic.curium.model.student.dto.StudentMapper;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.DateUtil;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface Yearmapper {

	Yearmapper INSTANCE = Mappers.getMapper(Yearmapper.class);
	

	Currentacademicyear mapCurrentacademicyear(CurrentAcademicYearDto CurrentAcademicYearDto);
	
	CurrentAcademicYearDto mapCurrentAcademicYearDto(Currentacademicyear Currentacademicyear);}
