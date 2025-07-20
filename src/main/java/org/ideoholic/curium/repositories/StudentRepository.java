package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.student.dto.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

	@Query("select s.sid, s.studentexternalid, s.admissionnumber, s.name, s.classstudying, f.fathersname, f.mothersname from "
			+ "Student s JOIN Parents f ON s.sid=f.student.sid where s.archive = 0 AND s.branchid=:branchId order by s.sid DESC")
	List<Student> findAllValidStudents(@Param("branchId")String branchId);
	
	List<Student> findByClassstudying(String classStudying);
}