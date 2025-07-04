package org.ideoholic.curium.repositories;

import org.ideoholic.curium.model.account.dto.Accountgroupmaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountGroupMasterRepository extends JpaRepository<Accountgroupmaster, Integer> {
}