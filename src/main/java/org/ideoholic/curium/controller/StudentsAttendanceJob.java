package org.ideoholic.curium.controller;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import org.ideoholic.curium.model.attendance.service.AttendanceService;

 public class StudentsAttendanceJob implements Job {

  public void execute(JobExecutionContext context) throws JobExecutionException {
    try {
    	System.out.println("In job");
         new AttendanceService().markDailyAttendanceJob();

    } catch (Exception ex) {
        System.out.println("entering the quartz config");  

    }
  }
}