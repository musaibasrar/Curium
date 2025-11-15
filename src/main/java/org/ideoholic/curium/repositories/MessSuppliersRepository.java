package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.mess.supplier.dto.MessSuppliers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MessSuppliersRepository extends JpaRepository<MessSuppliers, Integer> {

    List<MessSuppliers> findAllByOrderByIdDesc();

    @Modifying
    @Query("update MessSuppliers m set m.externalid = CONCAT(m.externalid, :suffix) where m.id = :id")
    int updateExternalid(@Param("id") Integer id, @Param("suffix") String suffix);

    @Modifying
    @Query("update MessSuppliers m set m.linkedledgerid = :linkedLedgerId where m.id = :id")
    int updateLinkedLedgerId(@Param("id") Integer id, @Param("linkedLedgerId") Integer linkedLedgerId);

}