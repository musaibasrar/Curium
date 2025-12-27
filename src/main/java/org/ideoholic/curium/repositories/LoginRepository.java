package org.ideoholic.curium.repositories;

import java.util.List;
import java.util.Optional;

import org.ideoholic.curium.model.user.dto.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {

	Optional<Login> findByUsernameAndPassword(String username, String password);

	List<Login> findByUsername(String username);

	List<Login> findByPassword(String password);

	List<Login> findByUserid(int userid);

	@Query("FROM Login as login where login.branch.idbranch=:branchId and login.username=:userName")
	List<Login> findByBranchIdAndUserName(@Param("branchId") Integer branchId, @Param("userName") String userName);

	Optional<Login> findTopByOrderByUseridDesc();

	@Query("from Login l where l.branch.idbranch = :branchId")
	List<Login> findByBranchId(@Param("branchId") Integer branchId);

	@Modifying
	@Query("update Login set username = :username, password = :password, usertype = :usertype where lid = :lid")
	int updateLoginDetails(@Param("username") String username, @Param("password") String password,
			@Param("usertype") String usertype, @Param("lid") Integer lid);

	// For saveLoginDetail logic (find last record ordered by lid desc)
	Login findTopByOrderByLidDesc();
}