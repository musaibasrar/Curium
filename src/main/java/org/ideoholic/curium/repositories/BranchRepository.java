package org.ideoholic.curium.repositories;

import org.ideoholic.curium.model.branch.dto.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {

}