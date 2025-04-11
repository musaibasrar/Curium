package org.ideoholic.curium.model.appointment.dao;

import java.util.List;

import org.ideoholic.curium.model.appointment.dto.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
	List<Appointment> findByBranchidOrderByIdDesc(int branchid);

	Page<Appointment> findByBranchidOrderByIdDesc(int branchId, Pageable pageable);
}
