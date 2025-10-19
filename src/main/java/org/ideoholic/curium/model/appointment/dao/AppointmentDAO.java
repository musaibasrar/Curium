package org.ideoholic.curium.model.appointment.dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.ideoholic.curium.model.adminexpenses.dto.Adminexpenses;
import org.ideoholic.curium.model.appointment.dto.Appointment;
import org.ideoholic.curium.repositories.AppointmentRepository;
import org.ideoholic.curium.util.DateUtil;
import org.ideoholic.curium.util.HibernateUtil;
import org.ideoholic.curium.util.QueryUtil;
import org.ideoholic.curium.util.Session;
import org.ideoholic.curium.util.Session.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AppointmentDAO {

	@Autowired
	private AppointmentRepository appointmentRepo;

	@Autowired
	private QueryUtil queryUtil;

	@Transactional
	public String addAppointment(Appointment appointment) {
		String resultString = null;
		try {

			List<Appointment> appointmentList = appointmentRepo.findByBranchidOrderByIdDesc(appointment.getBranchid());

			if (appointmentList.size() > 0) {
				String appNo = appointmentList.get(0).getExternalid();
				String splitAppNo = appNo.substring(2);

				if (Integer.parseInt(splitAppNo) < 1000) {
					appointment.setExternalid("AP" + String.format("%03d", Integer.parseInt(splitAppNo) + 1));
				} else {
					appointment.setExternalid("AP" + String.format("%03d", 1));
				}
			} else {
				appointment.setExternalid("AP" + String.format("%03d", 1));
			}
			appointment = appointmentRepo.save(appointment);
			resultString = appointment.getExternalid();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return resultString;
	}

	@Transactional
	public List<Appointment> readListOfObjectsPagination(int offset,
														 int noOfRecords, int branchId) {

		List<Appointment> results = new ArrayList<Appointment>();

		try {

			int pageNumber = (noOfRecords > 0) ? offset / noOfRecords : 0;
			Pageable pageable = PageRequest.of(pageNumber, noOfRecords, Sort.by(Sort.Direction.DESC, "id"));
			Page<Appointment> page = appointmentRepo.findByBranchidOrderByIdDesc(branchId, pageable);
			return page.getContent();

		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			throw hibernateException;
		}

	}

	@Transactional
	public int getNoOfRecords(int branchId) {
		int noOfRecords = 0;
		try {

			noOfRecords = appointmentRepo.countByBranchid(branchId);

		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);

			hibernateException.printStackTrace();
			throw hibernateException;

		}
		return noOfRecords;

	}

	@Transactional
	public int getNoOfRecords() {

		int noOfRecords = 0;
		try {
			Long activeAppointments = appointmentRepo.countByStatusNot("Cancelled");
			noOfRecords = activeAppointments.intValue();

		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			throw hibernateException;

		}
		return noOfRecords;

	}

	@Transactional
	public boolean completeAppointments(List<Integer> appointmentIdsList) {

		boolean result;

		try {

			List<Appointment> appointments = appointmentRepo.findAllById(appointmentIdsList);
			for (Appointment appointment : appointments) {

				appointment.setStatus("Completed");
				appointmentRepo.save(appointment);
			}


			result = true;
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();

			throw hibernateException;
		}
		return result;
	}

	@Transactional
	public List<Appointment> cancelAppointments(List<Integer> appointmentIdsList) {

		List<Appointment> appointments = appointmentRepo.findAllById(appointmentIdsList);
		try {

			for (Appointment appointment : appointments) {
				appointment.setStatus("Cancelled");
				appointmentRepo.save(appointment);

			}

		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			throw hibernateException;
		}
		return appointments;
	}

	@Transactional
	public int getNoOfRecordsMonthly(String fromDate, String toDate) {

		int noOfRecords = 0;
		try {

			Date start = DateUtil.indiandateParser(fromDate);
			Date end = DateUtil.indiandateParser(toDate);
			noOfRecords = (int) appointmentRepo.countByDate(start, end);
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);

			hibernateException.printStackTrace();
			throw hibernateException;

		}
		return noOfRecords;

	}

	@Transactional
	public int getNoOfRecordsCompletedAppointments() {

		int noOfRecords = 0;
		try {

			Long result = appointmentRepo.countByStatus("Completed");
			noOfRecords = result.intValue();
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);

			hibernateException.printStackTrace();
			throw hibernateException;

		}
		return noOfRecords;

	}

	@Transactional
	public int getNoOfRecordsIncompleteAppointments() {

		int noOfRecords = 0;
		try {

			Long result = appointmentRepo.countByStatus("Scheduled");
			noOfRecords = result.intValue();
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			throw hibernateException;

		}
		return noOfRecords;

	}

	@Transactional
	public int getNoOfRecordsTodayCompletedAppointments() {

		int noOfRecords = 0;
		try {
			Long result = appointmentRepo.countByStatusAndCreateddate("Completed", new Date());
			noOfRecords = result.intValue();
		} catch (Exception hibernateException) {

			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			throw hibernateException;
		}
		return noOfRecords;
	}

	@Transactional
	public int getNoOfRecordsTodayIncompleteAppointments() {

		int noOfRecords = 0;
		Transaction transaction = null;
		try {

			Long result = appointmentRepo.countByStatusAndCreateddate("Scheduled", new Date());
			noOfRecords = result.intValue();

		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			throw hibernateException;

		}
		return noOfRecords;

	}

	@Transactional
	public List<Appointment> generateAppointmentsReport(String query) {

		List<Appointment> results = new ArrayList<Appointment>();

		try {

			results = queryUtil.runGivenQuery(query, Appointment.class);

		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);

			hibernateException.printStackTrace();
			throw hibernateException;

		}
		return results;
	}

	@Transactional
	public boolean updateAppointments(List<Appointment> appointmentList) {

		boolean result = false;

		try {

			appointmentList.forEach(appointment -> {

				appointmentRepo.findById(appointment.getId()).ifPresent(existingAppointment -> {

					existingAppointment.setAppointmentstarttime(appointment.getAppointmentstarttime());
					existingAppointment.setAppointmentendtime(appointment.getAppointmentendtime());
					existingAppointment.setTotaltime(appointment.getTotaltime());

					appointmentRepo.save(existingAppointment);

				});
			});
			result = true;

		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);

			hibernateException.printStackTrace();
			throw hibernateException;

		}
		return result;
	}

}



