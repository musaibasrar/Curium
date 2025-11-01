package org.ideoholic.curium.repositories;

import org.ideoholic.curium.model.mess.stockmove.dto.MessTaxInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessTaxInvoiceRepository extends JpaRepository<MessTaxInvoice, Integer> {
}
