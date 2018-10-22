package com.controller;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.model.attendance.service.AttendanceService;

 public class StaffsAttendanceJob implements Job {

  public void execute(JobExecutionContext context) throws JobExecutionException {
    try {
         new AttendanceService().markDailyAttendanceJobStaff();

    } catch (Exception ex) {

    }
  }
}