package org.ideoholic.curium.model.academicyear.dto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface Yearmapper {

	Yearmapper INSTANCE = Mappers.getMapper(Yearmapper.class);
	

	Currentacademicyear mapCurrentacademicyear(CurrentAcademicYearDto CurrentAcademicYearDto);
	
	CurrentAcademicYearDto mapCurrentAcademicYearDto(Currentacademicyear Currentacademicyear);}
