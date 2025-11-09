package org.ideoholic.curium.repositories;

import org.ideoholic.curium.model.degreedetails.dto.Degreedetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DegreeDetailsRepository extends JpaRepository<Degreedetails, Integer> {

}