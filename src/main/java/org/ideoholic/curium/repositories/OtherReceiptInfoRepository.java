package org.ideoholic.curium.repositories;

import org.ideoholic.curium.model.feescollection.dto.Otherreceiptinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtherReceiptInfoRepository extends JpaRepository<Otherreceiptinfo, Integer> {

}
