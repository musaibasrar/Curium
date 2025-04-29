package org.ideoholic.curium.model.appointment.dao;

import java.time.LocalDate;
import java.util.List;

import org.ideoholic.curium.model.appointment.dto.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
	List<Appointment> findByBranchidOrderByIdDesc(int branchid);

	Page<Appointment> findByBranchidOrderByIdDesc(int branchId, Pageable pageable);

	int countByBranchid( int branchId);

	Long countByStatusNot(String status);

	@Query("SELECT COUNT(a) FROM Appointment a WHERE a.appointmentdate BETWEEN :fromDate AND :toDate AND a.status <> 'Cancelled'")
	long countByAppointmentdateBetweenAndStatusNotCancelled(
			@Param("fromDate") LocalDate fromDate,
			@Param("toDate") LocalDate toDate
	);
}
