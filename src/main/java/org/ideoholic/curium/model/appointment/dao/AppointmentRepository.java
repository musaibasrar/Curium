package org.ideoholic.curium.model.appointment.dao;

import java.util.List;

import org.ideoholic.curium.model.appointment.dto.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{
	
		List<Appointment> findByBranchidOrderByIdDesc(int branchid);
}
