package org.ideoholic.curium.repositories;

import org.ideoholic.curium.model.feescollection.dto.Receiptinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptinfoRepository extends JpaRepository<Receiptinfo, Integer> {

}
