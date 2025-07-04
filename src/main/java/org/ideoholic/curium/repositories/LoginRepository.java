package org.ideoholic.curium.repositories;

import org.ideoholic.curium.model.user.dto.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LoginRepository extends JpaRepository<Login, Integer> {
    List<Login> findByUsernameAndPassword(String username, String password);

    List<Login> findByUsername(String username);

    List<Login> findByPassword(String password);

    List<Login> findByUserid(int userid);

    @Query("FROM Login as login where login.branch.idbranch=:branchId and login.username=:userName")
    List<Login> findByBranchIdAndUserName(@Param("branchId")Integer branchId, @Param("userName")String userName);
}