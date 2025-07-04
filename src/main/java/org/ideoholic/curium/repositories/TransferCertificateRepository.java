package org.ideoholic.curium.repositories;

import org.ideoholic.curium.model.documents.dto.Transfercertificate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferCertificateRepository extends JpaRepository<Transfercertificate, Integer> {
	Transfercertificate findBySid(int sid);
}
