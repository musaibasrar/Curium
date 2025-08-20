package org.ideoholic.curium.repositories;

import org.ideoholic.curium.model.hr.dto.Payhead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayHeadRepository extends JpaRepository<Payhead, Long> {
}
