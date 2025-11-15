// MessSuppliersPaymentRepository.java
package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.mess.supplier.dto.MessSuppliersPayment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MessSuppliersPaymentRepository extends JpaRepository<MessSuppliersPayment, Integer> {

    @Query("FROM MessSuppliersPayment msp where msp.branchid = :branchid order by msp.issuedate DESC")
    List<MessSuppliersPayment> findByBranchidOrderByIssuedateDesc(@Param("branchid") int branchId, Pageable pageable);

    @Query("FROM MessSuppliersPayment msp where msp.branchid = :branchid")
    List<MessSuppliersPayment> findByBranchid(@Param("branchid") int branchId);

}