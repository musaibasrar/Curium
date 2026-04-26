package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.feescollection.dto.Otherreceiptinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtherReceiptInfoRepository extends JpaRepository<Otherreceiptinfo, Integer> {

	Otherreceiptinfo findTopByBranchidOrderByReceiptnumberDesc(Integer branchid);

	List<Otherreceiptinfo> findByStudent_SidAndAcademicyearAndCancelreceipt(Integer sid, String academicyear, Integer cancelreceipt);
}
