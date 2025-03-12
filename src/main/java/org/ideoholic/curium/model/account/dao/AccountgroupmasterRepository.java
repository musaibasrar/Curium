package org.ideoholic.curium.model.account.dao;

import org.ideoholic.curium.model.account.dto.Accountgroupmaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountgroupmasterRepository extends JpaRepository<Accountgroupmaster, Integer>{

}
