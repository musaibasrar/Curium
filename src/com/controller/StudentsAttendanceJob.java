package com.controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.model.attendance.dao.AttendanceDAO;
import com.model.attendance.service.AttendanceService;

 public class StudentsAttendanceJob implements Job {
     
     private static final Logger logger = LogManager.getLogger(StudentsAttendanceJob.class);

  public void execute(JobExecutionContext context) throws JobExecutionException {
    try {
    	logger.info("In job");
         new AttendanceService().markDailyAttendanceJob();

    } catch (Exception ex) {
        logger.error(ex);  

    }
  }
}