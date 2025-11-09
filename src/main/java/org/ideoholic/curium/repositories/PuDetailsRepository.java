package org.ideoholic.curium.repositories;

import org.ideoholic.curium.model.pudetails.dto.Pudetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PuDetailsRepository extends JpaRepository<Pudetails, Integer> {

    @Modifying
    @Query("UPDATE Pudetails SET exampassedappearance = :examPassed WHERE idpudetails = :puDetails")
    void updateExamPassed(@Param("examPassed") Integer examPassed, @Param("puDetails") Integer puDetails);
}