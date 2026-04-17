package org.ideoholic.curium.controller;

import org.ideoholic.curium.model.attendance.service.AttendanceService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class StaffsAttendanceJob implements Job {

	private final AttendanceService attendanceService;

	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			log.debug("In staff attendance job");
			attendanceService.markDailyAttendanceJobStaff();

		} catch (Exception ex) {
			log.debug("entering the quartz config");

		}
	}
}