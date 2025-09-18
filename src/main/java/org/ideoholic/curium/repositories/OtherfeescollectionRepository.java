package org.ideoholic.curium.repositories;

import org.ideoholic.curium.model.feescollection.dto.Feescollection;
import org.ideoholic.curium.model.feescollection.dto.Otherfeescollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OtherfeescollectionRepository extends JpaRepository<Otherfeescollection, Integer> {

	List<Otherfeescollection> findByReceiptInfo_Receiptnumber(Integer receiptnumber);

	List<Otherfeescollection> findByOtherFeesStructure_Sfsid(Integer sfsid);

	List<Feescollection> findByStudentSidAndOtherFeesStructureIn(Integer sid, List<Integer> ids);
}