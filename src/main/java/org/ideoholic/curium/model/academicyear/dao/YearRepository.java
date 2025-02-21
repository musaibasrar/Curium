package org.ideoholic.curium.model.academicyear.dao;

import java.util.List;

import org.ideoholic.curium.model.academicyear.dto.Currentacademicyear;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YearRepository extends JpaRepository<Currentacademicyear, Integer>{
	List<Currentacademicyear> findByCurrentacademicyear(String currentacademicyear);

}
