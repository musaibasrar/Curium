package org.ideoholic.curium.model.account.dao;

import org.ideoholic.curium.model.account.dto.Accountdetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDetailsRepository extends JpaRepository<Accountdetails, Integer>{

}