package org.ideoholic.curium.repositories;

import org.ideoholic.curium.model.academicyear.dto.Currentacademicyear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YearRepository extends JpaRepository<Currentacademicyear, Integer>{

}
