package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.documents.dto.StudyCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyCertificateRepository extends JpaRepository<StudyCertificate, Integer> {
	StudyCertificate findBySid(int sid);
	List<StudyCertificate> findByBranchId(Integer branchId);
	
	@Query("SELECT accdetails FROM Accountdetailsbalance accdetails " + "JOIN accdetails.accountDetails ad "
			+ "JOIN ad.accountGroupMaster agm "
			+ "WHERE agm.accountgroupid IN (:accountIds) AND accdetails.branchid = :branchId")
	List<StudyCertificate> findAllByStudentIdsIn(@Param("accountIds") List<Integer> accountIds, @Param("branchId") Integer branchId);
}
