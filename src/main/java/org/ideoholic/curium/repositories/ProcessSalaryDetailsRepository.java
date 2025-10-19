package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.hr.dto.Processsalarydetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessSalaryDetailsRepository extends JpaRepository<Processsalarydetails, Integer> {

	List<Processsalarydetails> findByAcademicyearAndBranchid(String academicYear, Integer branchId);

	List<Processsalarydetails> findByTeacherTid(int teacherId);

	Processsalarydetails findByTeacherTidAndMonthAndYear(int teacherId, String month, Integer year);
}
