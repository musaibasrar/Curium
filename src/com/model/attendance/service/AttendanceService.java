package com.model.attendance.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.attendance.dao.AttendanceDAO;
import com.model.attendance.dto.Attendancemaster;
import com.model.attendance.dto.Holidaysmaster;
import com.model.attendance.dto.Weeklyoff;
import com.model.employee.dto.Teacher;
import com.util.DateUtil;

public class AttendanceService {
	
	 private HttpServletRequest request;
	    private HttpServletResponse response;
	    private HttpSession httpSession;
	    
	
	public AttendanceService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
        this.response = response;
        this.httpSession = request.getSession();
	}

	public void viewAllHolidays() {
		//remove it after testing
		httpSession.setAttribute("currentAcademicYear", "2017/18");
		
		if(httpSession.getAttribute("currentAcademicYear")!=null){
		List<Holidaysmaster> list = new AttendanceDAO().readListOfHolidays(httpSession.getAttribute("currentAcademicYear").toString());
	        httpSession.setAttribute("holidaysList", list);
		}
	}

	public boolean addHolidays() {

		Holidaysmaster holidayMaster = new Holidaysmaster();
		String[] fromDate = request.getParameterValues("fromdate");
		String[] toDate = request.getParameterValues("todate");
		String[] holidayName = request.getParameterValues("holidayname");
		boolean result = false;

		if(fromDate!=null && toDate!=null && holidayName!=null && httpSession.getAttribute("currentAcademicYear")!=null){
			try{
		for (int i = 0; i < fromDate.length; i++) {
			
			holidayMaster.setFromdate(DateUtil.datePars(fromDate[i]));
			holidayMaster.setTodate(DateUtil.datePars(toDate[i]));
			holidayMaster.setHolidayname(holidayName[i]);
			holidayMaster.setAcademicyear(httpSession.getAttribute("currentAcademicYear").toString());	
			result = new AttendanceDAO().saveHolidays(holidayMaster);
		}
			}catch(Exception e){
			e.printStackTrace();	
			}
		}
		return result;
	}

	public boolean addWeekOff() {
		Weeklyoff weeklyOff = new Weeklyoff();
		String[] weekOff = request.getParameterValues("weekoff");
		if(weekOff!=null){
		for(int i=0; i<weekOff.length;i++){
			weeklyOff.setWeeklyoffday(weekOff[i]);
			weeklyOff.setAcademicyear(httpSession.getAttribute("currentAcademicYear").toString());
			return new AttendanceDAO().saveWeeklyOff(weeklyOff);
		}
		}
		return false;
	}

	public boolean deleteMultiple() {
		String ids[] = request.getParameterValues("holidayid");
		if(ids!=null){
		List<Integer> holidayIds = new ArrayList<Integer>();
		for (String id : ids) {
			holidayIds.add(Integer.valueOf(id));
		}
		return new AttendanceDAO().deleteMultiple(holidayIds);	
		}
		return false;
	}

	public void viewAllWeekOffs() {
		if(httpSession.getAttribute("currentAcademicYear")!=null){
			List<Weeklyoff> weekOffList = new AttendanceDAO().readListOfWeekOff(httpSession.getAttribute("currentAcademicYear").toString());
		        httpSession.setAttribute("weekOffList", weekOffList);
			}
		
	}

	@SuppressWarnings("null")
	public boolean addStudentAttendanceMaster() {

		Attendancemaster attendanceMaster = new Attendancemaster();
		
		String[] weeklyOff = request.getParameterValues("weekoff");
		String[] holidays = request.getParameterValues("holidays");
		StringBuilder sbWeek = new StringBuilder();
		StringBuilder sbHoliday = new StringBuilder();
		for (String weekoff : weeklyOff) {
			if (sbWeek.length() > 0) 
			sbWeek.append(",");
			
			sbWeek.append(weekoff);
			
		}
		for (String holidaysString : holidays) {
			
			if (sbHoliday.length() > 0) 
				sbHoliday.append(",");
			
			sbHoliday.append(holidaysString);
		}
	
		attendanceMaster.setAttendeeid("00011");
		attendanceMaster.setIntime(request.getParameter("cutoff") +":"+ request.getParameter("min") +":"+ request.getParameter("ampm"));
		attendanceMaster.setWeeklyoff(sbWeek.toString());
		attendanceMaster.setHolidayname(sbHoliday.toString());
		
		return new AttendanceDAO().addAttendanceMaster(attendanceMaster);
		
		/*List<Integer> weeklyOffList = new ArrayList<Integer>();
		for (String weekoff : weeklyOff) {
			weeklyOffList.add(Integer.parseInt(weekoff));
		}
		List<Weeklyoff> weekOffList = new AttendanceDAO().readListOfWeeklyOff(weeklyOffList,httpSession.getAttribute("currentAcademicYear").toString());
		List<Integer> holidaysIntList = new ArrayList<Integer>();
		for (String holidaysString : holidays) {
			holidaysIntList.add(Integer.parseInt(holidaysString));
		}
		List<Holidaysmaster> holidaysList = new AttendanceDAO().readListOfholidays(holidaysIntList,httpSession.getAttribute("currentAcademicYear").toString());
		 */
	
	}

	public boolean addStaffAttendanceMaster() {
		
		String[] staffId = request.getParameterValues("employeeIDs");
		String[] weeklyOff = request.getParameterValues("weekoffstaff");
		String[] holidays = request.getParameterValues("holidaysstaff");
		StringBuilder sbWeek = new StringBuilder();
		StringBuilder sbHoliday = new StringBuilder();
		for (String weekoff : weeklyOff) {
			if (sbWeek.length() > 0) 
			sbWeek.append(",");
			
			sbWeek.append(weekoff);
		}
		for (String holidaysString : holidays) {
			
			if (sbHoliday.length() > 0) 
				sbHoliday.append(",");
			
			sbHoliday.append(holidaysString);
		}
		
		List<Attendancemaster> attendanceMasterList = new ArrayList<Attendancemaster>();
		for (String idStaff : staffId) {
			Attendancemaster attendanceMaster = new Attendancemaster();
			attendanceMaster.setAttendeeid(idStaff);
			attendanceMaster.setIntime(request.getParameter("intime") +":"+ request.getParameter("mininstaff") +":"+ request.getParameter("ampminstaff"));
			attendanceMaster.setOuttime(request.getParameter("outtime") +":"+ request.getParameter("minoutstaff") +":"+ request.getParameter("ampmoutstaff"));
			attendanceMaster.setWeeklyoff(sbWeek.toString());
			attendanceMaster.setHolidayname(sbHoliday.toString());
			attendanceMasterList.add(attendanceMaster);
		}
		return new AttendanceDAO().addAttendanceMaster(attendanceMasterList);
	}
	
}
