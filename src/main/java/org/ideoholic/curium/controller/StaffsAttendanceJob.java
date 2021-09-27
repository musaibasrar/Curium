package org.ideoholic.curium.controller;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import org.ideoholic.curium.model.attendance.service.AttendanceService;

 public class StaffsAttendanceJob implements Job {

  public void execute(JobExecutionContext context) throws JobExecutionException {
    try {
    	System.out.println("In staff attendance job");
         new AttendanceService().markDailyAttendanceJobStaff();

    } catch (Exception ex) {
        System.out.println("entering the quartz config");  

    }
  }
}