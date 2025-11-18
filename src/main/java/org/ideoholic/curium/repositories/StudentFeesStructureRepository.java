package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.student.dto.Studentfeesstructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentFeesStructureRepository extends JpaRepository<Studentfeesstructure, Integer> {

	// Find fee structures for a student and a set of feescategory ids
	List<Studentfeesstructure> findByStudentSidAndFeescategoryIdfeescategoryIn(Integer sid, List<Integer> feescat);
	
	// Find fee structures for a student and academic year
	List<Studentfeesstructure> findByStudentSidAndAcademicyear(Integer sid, String academicYear);

	List<Studentfeesstructure> findByStudent_SidInAndAcademicyear(List<Integer> studentids, String academicYear);

	Studentfeesstructure findByStudent_SidAndFeescategory_IdfeescategoryAndAcademicyear(Integer sid, Integer idFeesCategory, String academicYear);

    List<Studentfeesstructure> findByStudent_SidInAndFeesamountGreaterThanAndAcademicyear(List<Integer> studentIds, Long amount, String academicYear);

    @Modifying
    @Query("delete from Studentfeesstructure s where s.student.sid = :sid and s.sfsid in :ids")
    void deleteBySidAndSfsidIn(@Param("sid") int sid, @Param("ids") List<Integer> ids);
    
    // Find by sfsid
    List<Studentfeesstructure> findBySfsid(Integer sfsid);

    // Used in addStudentfeesstructure to find existing record for combination of student, feescategory and year
    List<Studentfeesstructure> findByStudentSidAndFeescategoryIdfeescategoryAndAcademicyear(Integer sid, Integer feescategoryId, String academicYear);
    
    void deleteBySidInAndAcademicyear(List<Integer> sids, String academicyear);

    // sid = student.getSid(); idfeescategory = student.getFeescategory().getIdfeescategory();
    @Query("SELECT s FROM Studentfeesstructure s WHERE s.student.sid = :sid AND s.feescategory.idfeescategory = :idfeescategory AND s.academicyear = :academicyear")
	Studentfeesstructure findBySidAndIdfeescategoryAndAcademicyear(@Param("sid") int sid, @Param("idfeescategory") Integer idFeesCategory, @Param("academicyear") String academicYear);
}