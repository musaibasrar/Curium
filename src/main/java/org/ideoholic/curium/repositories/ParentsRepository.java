package org.ideoholic.curium.repositories;

import java.util.List;
import java.util.Optional;

import org.ideoholic.curium.model.parents.dto.Parents;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentsRepository extends JpaRepository<Parents, Integer> {

	@Query("SELECT COUNT(*) FROM Parents AS parents WHERE parents.student.archive=0 AND parents.student.passedout=0 AND parents.student.droppedout=0 AND parents.student.leftout=0"
			+ "AND email IS NOT NULL AND email <> ''")
	long countAllParentsWithEmail();

	@Query("SELECT COUNT(*) FROM Parents AS parents WHERE parents.student.classstudying LIKE :classStudying AND parents.student.archive=0 AND parents.student.passedout=0 "
			+ "AND parents.student.droppedout=0 AND parents.student.leftout=0 AND parents.branchid=:branchId AND email IS NOT NULL AND email <> ''")
	long countParentsWithEmailForGivenClass(@Param("classStudying") String classStudying,
			@Param("branchId") String branchId);

	@Query("SELECT parents FROM Parents AS parents WHERE parents.student.archive=0 AND parents.student.passedout=0 AND parents.student.droppedout=0 AND parents.student.leftout=0"
			+ "AND email IS NOT NULL AND email <> ''")
	Page<Parents> getAllParentsWithEmail(Pageable pageable);

	@Query("SELECT parents FROM Parents AS parents WHERE parents.student.classstudying LIKE :classStudying AND parents.student.archive=0 AND parents.student.passedout=0 "
			+ "AND parents.student.droppedout=0 AND parents.student.leftout=0 AND parents.branchid=:branchId AND email IS NOT NULL AND email <> ''")
	Page<Parents> getParentsWithEmailForGivenClass(@Param("classStudying") String classStudying,
			@Param("branchId") String branchId, Pageable pageable);

	Parents findByStudentSid(@Param("sid") Integer sid);

	List<Parents> findByStudentSidIn(List<Integer> ids);
	
	
	@Query("SELECT parents FROM Parents AS parents WHERE parents.student.archive=0 AND parents.student.passedout=0 "
			+ "AND parents.student.droppedout=0 AND parents.student.leftout=0 AND parents.branchid=:branchId "
			+ "ORDER BY parents.student.sid desc")
	Page<Parents> findActiveParentsByBranchId(@Param("branchId") Integer branchId, Pageable pageable);

	@Query("SELECT parents FROM Parents AS parents WHERE parents.student.archive=0 and parents.student.passedout=0 AND parents.student.droppedout=0 "
			+ "AND parents.student.leftout=0 ORDER BY parents.student.name ASC")
	Page<Parents> findAllActiveParentsByBranchId(Pageable pageable);
	
    @Query("FROM Parents p WHERE p.student.sid = :sid")
    Optional<Parents> findByStudentSid(@Param("sid") long sid);

    // Fetch by Student External ID
    @Query("FROM Parents p WHERE p.student.studentexternalid = :externalId")
    Optional<Parents> findByStudentExternalId(@Param("externalId") String externalId);
}
