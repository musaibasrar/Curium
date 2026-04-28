package org.ideoholic.curium.repositories;

import java.util.List;
import org.ideoholic.curium.model.sponsor.dto.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SponsorRepository extends JpaRepository<Sponsor, Integer> {

    List<Sponsor> findByBranchid(int branchId);

    void deleteByIdIn(List<Integer> ids);
}