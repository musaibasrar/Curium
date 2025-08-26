package org.ideoholic.curium.repositories;

import org.ideoholic.curium.model.feescollection.dto.Feescollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeescollectionRepository extends JpaRepository<Feescollection, Integer> {

	List<Feescollection> findByStudentSidAndStudentFeeStructureSfsidIn(Integer sid, List<Integer> ids);

	List<Feescollection> findByReceiptInfo_Receiptnumber(Integer receiptnumber);

	List<Feescollection> findByStudent_SidAndAcademicyear(Long sid, String academicyear);

	List<Feescollection> findByStudentFeeStructure_Sfsid(Integer sfsid);

	// List<Feescollection> findByFeesdetailsid(Integer feeid);
}