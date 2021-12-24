package com.model.appointment.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.appointment.dao.AppointmentDAO;
import com.model.appointment.dto.Appointment;
import com.model.parents.dto.Parents;
import com.model.query.dao.QueryDAO;
import com.util.DataUtil;
import com.util.DateUtil;

public class AppointmentService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;

	public AppointmentService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

	public boolean addAppointment() {
		
		boolean result = false;
		
		String[] studentId = request.getParameterValues("studentIDs");
		String appointmentDate = request.getParameter("appointmentdate");
		String appointmentTime = request.getParameter("appointmenttime");
		
		if(httpSession.getAttribute("branchid")!=null){
	
					Appointment appointment = new Appointment();
					appointment.setAcademicyear(DataUtil.emptyString(httpSession.getAttribute("currentAcademicYear").toString()));
					appointment.setAppointmentdate(DateUtil.dateParserdd(appointmentDate));
					appointment.setBranchid(Integer.parseInt(httpSession.getAttribute("branchid").toString()));
					appointment.setCreateddate(new Date());
					appointment.setCreateduserid(Integer.parseInt(httpSession.getAttribute("userloginid").toString()));
					appointment.setStatus("Scheduled");
					//appointment.setStdid(1);
					Parents parent = new Parents();
					parent.setPid(Integer.parseInt(studentId[0]));
					appointment.setParent(parent);
					
					String[] starttimeSplit = appointmentTime.split(":");
					String hours = starttimeSplit[0];
					String meridian = null;
					String outputStartTime = null;
						  if (Integer.parseInt(hours) < 12) {
							  outputStartTime = appointmentTime;
						    meridian = "AM";
						  } else if (Integer.parseInt(hours) >= 12) {
							  
							  DateFormat df = new SimpleDateFormat("HH:mm");
						       //Date/time pattern of desired output date
						       DateFormat outputformat = new SimpleDateFormat("hh:mm");
						       Date date1 = null;
						       try{
						          //Conversion of input String to date
						    	  date1= df.parse(appointmentTime);
						          //old date format to new date format
						    	  outputStartTime = outputformat.format(date1);
						    	}catch(ParseException pe){
						    	    pe.printStackTrace();
						    	 }
						    meridian = "PM";
						  }
				appointment.setAppointmenttime(outputStartTime+" "+meridian);		  
				result = new AppointmentDAO().addAppointment(appointment);
				}
		
		return result;
	}

	
	public boolean viewAllAppointments() {

		boolean result = false;
		//String pages = "1";
		if(httpSession.getAttribute("branchid")!=null){
			try {
				int page = 1;
				int recordsPerPage = 500;
					if (!"".equalsIgnoreCase(DataUtil.emptyString(request.getParameter("page")))) {
						page = Integer.parseInt(request.getParameter("page"));
					}

				List<Appointment> list = new AppointmentDAO().readListOfObjectsPagination((page - 1) * recordsPerPage,
						recordsPerPage, Integer.parseInt(httpSession.getAttribute("branchid").toString()));
				request.setAttribute("studentList", list);
				int noOfRecords = new AppointmentDAO().getNoOfRecords(Integer.parseInt(httpSession.getAttribute("branchid").toString()));
				int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
				request.setAttribute("appointmentList", list);
				request.setAttribute("noOfPages", noOfPages);
				request.setAttribute("currentPage", page);
				result = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public void completeAppointments() {
		
		String[] appointmentIds = request.getParameterValues("appointmentids");
		List<Integer> appointmentIdsList = new ArrayList<Integer>();
		boolean result = false;
		
		if(appointmentIds!=null) {
			for (String ids : appointmentIds) {
				appointmentIdsList.add(Integer.parseInt(ids));
			}
			
			result = new AppointmentDAO().completeAppointments(appointmentIdsList);
			request.setAttribute("appointmentstatus",result);
		}
	}

	public void cancelAppointments() {
		
		String[] appointmentIds = request.getParameterValues("appointmentids");
		List<Integer> appointmentIdsList = new ArrayList<Integer>();
		boolean result = false;
		
		if(appointmentIds!=null) {
			for (String ids : appointmentIds) {
				appointmentIdsList.add(Integer.parseInt(ids));
			}
			
			result = new AppointmentDAO().cancelAppointments(appointmentIdsList);
			request.setAttribute("appointmentstatus",result);
		}
		
	}

	public int getNoOfRecordsMonthly() {
		
		Date monthOf = new Date();
		String Currentmonth = null;   
		 
		Calendar cStart = Calendar.getInstance();
		cStart.setTime(monthOf);
		cStart.set(Calendar.DAY_OF_MONTH, cStart.getActualMinimum(Calendar.DAY_OF_MONTH));
		monthOf = cStart.getTime();
		Timestamp TimestampFrom = new Timestamp(monthOf.getTime());
		
		cStart.set(Calendar.DAY_OF_MONTH, cStart.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date lastDayOfMonth = cStart.getTime();
		Timestamp Timestampto = new Timestamp(lastDayOfMonth.getTime());
		
		Currentmonth = cStart.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
		
		Date dateFrom = new Date(TimestampFrom.getTime());
		Date dateTo = new Date(Timestampto.getTime());
	    String fromDate = new SimpleDateFormat("yyyy-MM-dd").format(dateFrom);
	    String toDate = new SimpleDateFormat("yyyy-MM-dd").format(dateTo);
	    
	    
	    return new AppointmentDAO().getNoOfRecordsMonthly(fromDate, toDate);
}

	public void generateAppointmentsReport() {
		
		String fromDate = DateUtil.dateFromatConversionSlash(request.getParameter("transactiondatefrom"));
		String toDate = DateUtil.dateFromatConversionSlash(request.getParameter("transactiondateto"));
		String status = request.getParameter("status");
		String studentId = request.getParameter("studentId");
		String admnno = request.getParameter("admnno");
		String studentName = request.getParameter("studentName");
		String queryMain = "from Appointment ap where ap.appointmentdate between '"+fromDate+"' and '"+toDate+"' ";
		String subQuery = "";
		List<Appointment> appointmentList = new ArrayList<Appointment>();
				
		if(!status.isEmpty()) {
			subQuery = subQuery + " and status = '"+status+"'";
			httpSession.setAttribute("statusselected", "Status:&nbsp;"+status);
		}else {
		httpSession.setAttribute("statusselected", "");
		}
		
		if(!studentId.isEmpty()) {
			subQuery = subQuery + "and ap.parent.Student.sid = '"+studentId+"'";
			httpSession.setAttribute("studentselected", "Student Name:&nbsp;"+studentName);
		}else {
			httpSession.setAttribute("studentselected", "");
		}
		
		appointmentList = new AppointmentDAO().generateAppointmentsReport(queryMain+subQuery);
		
		httpSession.setAttribute("appointmentList", appointmentList);
		httpSession.setAttribute("transactionfromdateselected", "From:"+request.getParameter("transactiondatefrom"));
		httpSession.setAttribute("transactiontodateselected", "To:"+request.getParameter("transactiondateto"));
	}

	public void getMonthlyAppointments() {
		
		List<String> monthList = new LinkedList<String>();
		List<String> totalAppointments = new LinkedList<String>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date newdate = new Date();
		String todaysDate = df.format(newdate);
		List<Appointment> AppointmentList = new ArrayList<Appointment>();
		Date dateBefore = null;
		Date dateAfter = null;
		String queryMain = "from Appointment ap where ap.status!='Cancelled' and ";
		String toDate = null;
		String fromDate = null;
		
		try {
			dateBefore = df.parse(todaysDate);
			dateAfter = df.parse(todaysDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Calendar start1 = Calendar.getInstance();
		start1.setTime(dateBefore);
		Calendar end1 = Calendar.getInstance();
		end1.setTime(dateAfter);
		start1.set(Calendar.MONTH, start1.getActualMinimum(Calendar.MONTH));
		start1.set(Calendar.DAY_OF_MONTH, start1.getActualMinimum(Calendar.DAY_OF_MONTH));
		end1.set(Calendar.MONTH, end1.getActualMaximum(Calendar.MONTH));
		end1.add(Calendar.DAY_OF_MONTH, 1);
		
		for (Date date = start1.getTime(); start1.before(end1); start1.add(Calendar.MONTH,+1), date = start1.getTime()) {
			fromDate = new SimpleDateFormat("YYYY-MM-dd").format(date);
			Calendar endday = Calendar.getInstance();
			endday.setTime(date);
			endday.set(Calendar.DAY_OF_MONTH, start1.getActualMaximum(Calendar.DAY_OF_MONTH));
			Date enddayofmonth = endday.getTime();
			toDate = new SimpleDateFormat("YYYY-MM-dd").format(enddayofmonth);
			String querySub = "";
			querySub = " ap.createddate between '"+fromDate+"' and '"+toDate+"'";
			AppointmentList = new AppointmentDAO().generateAppointmentsReport(queryMain+querySub);
			
			totalAppointments.add("\"" + AppointmentList.size() + "\"");
			//Date Format
			SimpleDateFormat month_date = new SimpleDateFormat("MMM yyyy", Locale.ENGLISH);
			String monthYear = month_date.format(date);
			
			monthList.add("\"" + monthYear + "\"");
		}
		
		request.setAttribute("monthlytotalappointments", totalAppointments);
		request.setAttribute("monthlistappointment", monthList);
	}
	
}
