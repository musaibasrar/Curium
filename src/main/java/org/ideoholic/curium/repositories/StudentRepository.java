package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.student.dto.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

	List<Student> findByClassstudying(String classStudying);

	@Query("SELECT s.sid, s.studentexternalid, s.admissionnumber, s.name, s.classstudying, "
			+ "p.fathersname, p.mothersname " + "FROM Student s JOIN Parents p ON s.sid = p.student.sid "
			+ "WHERE s.archive = 0 AND s.branchid = :branchId " + "ORDER BY s.sid DESC")
	List<Object[]> findStudentDetailsByBranchId(@Param("branchId") Integer branchId);

	@Query("SELECT s.sid, s.name, s.classstudying, s.studentexternalid, s.admissionnumber, p.fathersname "
			+ "FROM Student s JOIN s.parents p "
			+ "WHERE s.sid IN (SELECT f.student.sid FROM Studentfeesstructure f WHERE f.branchid = :branchId)")
	List<Object[]> findStudentsByBranchId(@Param("branchId") Integer branchId);

	@Query("SELECT s.sid, s.name, s.classstudying, s.studentexternalid, s.admissionnumber, p.fathersname "
			+ "FROM Student s JOIN s.parents p "
			+ "WHERE s.sid IN (SELECT f.student.sid FROM Studentotherfeesstructure f WHERE f.branchid = :branchId)")
	List<Object[]> findStudentByBranchId(@Param("branchId") Integer branchId);

	@Query("SELECT s FROM Student s WHERE s.archive = 0 AND s.sid IN (SELECT f.student.sid FROM Studentfeesstructure f)")
	List<Student> findAllActiveBranchStudents();

	@Query("select s.sid, s.studentexternalid, s.admissionnumber, s.name, s.classstudying, p.fathersname, p.mothersname "
			+ "from Parents p join p.student s "
			+ "where s.archive = 0 and s.passedout = 0 and s.droppedout = 0 and s.leftout = 0 and s.branchid = :branchId "
			+ "ORDER BY s.name ASC")
	List<Object[]> findExistingStudentDetailsByBranchId(@Param("branchId") Integer branchId);

	@Query("select s.sid, s.studentexternalid, s.admissionnumber, s.name, s.classstudying, p.fathersname, p.mothersname "
			+ "from Parents p join p.student s "
			+ "where s.archive = 0 and s.passedout = 0 and s.droppedout = 0 and s.leftout = 0 and s.branchid = :branchId "
			+ "ORDER BY s.name ASC")
	Page<Object[]> findExistingStudentDetailsByBranchIdPageable(@Param("branchId") Integer branchId, Pageable pageable);

	Long countByArchiveAndPassedoutAndDroppedoutAndLeftoutAndBranchid(Integer archive, Integer passedOut, Integer droppedOut, Integer leftOut, Integer branchId);

	// Used to read list of students filtered by flags + branch
	List<Student> findByArchiveAndPassedoutAndDroppedoutAndLeftoutAndBranchid(Integer archive, Integer passedOut, Integer droppedOut, Integer leftOut, Integer branchId);

	// Used to read archived students by branch
	List<Student> findByArchiveAndBranchid(Integer archive, Integer branchId);

	// Used for icon listing ordered by name
	List<Student> findByArchiveAndPassedoutAndDroppedoutAndLeftoutAndBranchidOrderByNameAsc(Integer archive, Integer passedOut, Integer droppedOut, Integer leftOut, Integer branchId);

	Student findByStudentexternalid(String studentExternalId);

	List<Student> findByArchiveAndPassedoutAndDroppedoutAndLeftout(Integer archive, Integer passedOut, Integer droppedOut, Integer leftOut);

	@Query("SELECT count(s) FROM Student s where s.classstudying LIKE :classStudying OR s.classstudying = classStudying "
			+ "AND s.archive = :archive AND s.branchid=:branchId")
	Integer countNumberOfStudentInClass(@Param("classStudying") String classStudying, @Param("archive") Integer archive, @Param("branchId") Integer branchId);
	
	List<Student> findByArchive(Integer archive);
	
	// For LIKE: use @Query for "LIKE 'classStudying %'"
    @Query("SELECT s FROM Student s WHERE s.classstudying LIKE :classStudying AND s.archive = :archive")
    List<Student> findByClassstudyingLikeAndArchive(@Param("classStudying") String classstudyingLike, @Param("archive") Integer archive);
}