package org.ideoholic.curium.model.appointment.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ideoholic.curium.model.appointment.dao.AppointmentDAO;
import org.ideoholic.curium.model.appointment.dto.*;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.DateUtil;
import org.ideoholic.curium.util.ResultResponse;

public class AppointmentService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;
	private static final int BUFFER_SIZE = 4096;

	public AppointmentService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

	public ResultResponse addAppointment(AddAppointmentDto addAppointmentDto) {


		
		String[] studentId = addAppointmentDto.getStudentId();
		String appointmentDate = addAppointmentDto.getAppointmentDate();
		String appointmentTime = addAppointmentDto.getAppointmentTime();
		String[] pidContact = studentId[0].split(":");
		String[] apptDate = appointmentDate.split("-");
		String appointmentDateParent = apptDate[2]+"/"+apptDate[1]+"/"+apptDate[0];
		System.out.println("Date "+appointmentDateParent);
		

		if(addAppointmentDto.getBranchId()!=null){
					Appointment appointment = new Appointment();
					appointment.setAcademicyear(DataUtil.emptyString(addAppointmentDto.getCurrentAcademicYear().toString()));
					appointment.setAppointmentdate(DateUtil.dateParserdd(appointmentDate));
			        appointment.setBranchid(Integer.parseInt(addAppointmentDto.getBranchId().toString()));
					appointment.setCreateddate(new Date());
					appointment.setCreateduserid(Integer.parseInt(addAppointmentDto.getUserloginid().toString()));
					appointment.setStatus("Scheduled");
					//appointment.setStdid(1);
					Parents parent = new Parents();
					parent.setPid(Integer.parseInt(pidContact[0]));
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
				String resultQuery = new AppointmentDAO().addAppointment(appointment);
				String sendAppointmentSMS = new DataUtil().getPropertiesValue("sendappointmentsms");
				
				if(resultQuery!=null && "yes".equalsIgnoreCase(sendAppointmentSMS)) {

					 String message = "Your appt. with appt. no "+resultQuery+" has been scheduled on "+appointmentDateParent+" at "+appointment.getAppointmenttime()+".";

					 //new SmsService(request, response).sendSMS("91"+pidContact[1], message);
					return ResultResponse.builder().success(true).build();
				}else if(resultQuery!=null && "no".equalsIgnoreCase(sendAppointmentSMS)) {
					return ResultResponse.builder().success(true).build();
				}
				
				}

		return ResultResponse.builder().build();
	}

	public ViewAllAppoinmentsResponseDto viewAllAppointments(ViewAllAppointmentsDto viewAllAppointmentsDto) {
           ViewAllAppoinmentsResponseDto viewAllAppoinmentsResponseDto = new ViewAllAppoinmentsResponseDto();
		//String pages = "1";
		if(viewAllAppointmentsDto.getBranchId()!=null){
			try {
				int page = 1;
				int recordsPerPage = 500;
					if (!"".equalsIgnoreCase(DataUtil.emptyString(String.valueOf(viewAllAppointmentsDto.getPage())))) {
						page = viewAllAppointmentsDto.getPage();
					}
				List<Appointment> list = new AppointmentDAO().readListOfObjectsPagination((page - 1) * recordsPerPage,
						recordsPerPage, viewAllAppointmentsDto.getBranchId());
					viewAllAppoinmentsResponseDto.setStudentList(list);
				int noOfRecords = new AppointmentDAO().getNoOfRecords(viewAllAppointmentsDto.getBranchId());
				int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
				    viewAllAppoinmentsResponseDto.setAppointmentList(list);
				    viewAllAppoinmentsResponseDto.setNoOfPages(noOfPages);
				    viewAllAppoinmentsResponseDto.setCurrentPage(page);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return viewAllAppoinmentsResponseDto;
	}

	public ResultResponse completeAppointments(CompleteAppointmentsDto completeAppointmentsDto) {
		String[] appointmentIds = completeAppointmentsDto.getAppointmentIds();
		List<Integer> appointmentIdsList = new ArrayList<Integer>();
		boolean result = false;
		
		if(appointmentIds!=null) {
			for (String ids : appointmentIds) {
				appointmentIdsList.add(Integer.parseInt(ids));
			}
			
			result = new AppointmentDAO().completeAppointments(appointmentIdsList);
			 return ResultResponse.builder().success(result).build();

		}
        return ResultResponse.builder().build();
	}

	public ResultResponse cancelAppointments(CancelAppointmentsDto cancelAppointmentsDto) {
		
		String[] appointmentIds =cancelAppointmentsDto.getAppointmentIds();
		List<Integer> appointmentIdsList = new ArrayList<Integer>();
		List<Appointment> result = new ArrayList<Appointment>();
		
		if(appointmentIds!=null) {
			for (String ids : appointmentIds) {
				appointmentIdsList.add(Integer.parseInt(ids));
			}
			
			result = new AppointmentDAO().cancelAppointments(appointmentIdsList);
			String sendCancelAppointmentSMS = new DataUtil().getPropertiesValue("sendcancelappointmentsms");
			if(!result.isEmpty() && "yes".equalsIgnoreCase(sendCancelAppointmentSMS)) {
				for (Appointment appointment : result) {
					 String message = "Your appointment with appt. no. "+appointment.getExternalid()+" has been cancelled.";
					 //new SmsService(request, response).sendSMS("91"+appointment.getParent().getContactnumber(), message);
				}
				return ResultResponse.builder().success(true).build();
			}
		}
	return ResultResponse.builder().build();
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

	public AppointmentResponseDto generateAppointmentsReport(GenerateAppointmentsReportDto generateAppointmentsReportDto) {
		AppointmentResponseDto appointmentResponseDto =new AppointmentResponseDto();
		String fromDate = DateUtil.dateFromatConversionSlash(generateAppointmentsReportDto.getFromDate());
		String toDate = DateUtil.dateFromatConversionSlash(generateAppointmentsReportDto.getToDate());
		String status = generateAppointmentsReportDto.getStatus();
		String studentId = generateAppointmentsReportDto.getStudentId();
		String admnno = generateAppointmentsReportDto.getAdmnno();
		String studentName = generateAppointmentsReportDto.getStudentName();
		String queryMain = "from Appointment ap where ap.appointmentdate between '"+fromDate+"' and '"+toDate+"' ";
		String subQuery = "";
		List<Appointment> appointmentList = new ArrayList<Appointment>();

		if(!status.isEmpty()) {
			subQuery = subQuery + " and status = '"+status+"'";
			appointmentResponseDto.setStatusselected(status);
		}else {
			appointmentResponseDto.setStatusselected("");

		}

		if(!studentId.isEmpty()) {
			subQuery = subQuery + "and ap.parent.Student.sid = '"+studentId+"'";
			appointmentResponseDto.setStudentselected(studentName);
		}else {
			appointmentResponseDto.setStudentselected("");
		}
		appointmentList = new AppointmentDAO().generateAppointmentsReport(queryMain+subQuery);
		appointmentResponseDto.setAppointmentList(appointmentList);

		appointmentResponseDto.setTransactionfromdateselected(generateAppointmentsReportDto.getFromDate());

		appointmentResponseDto.setTransactiontodateselected(generateAppointmentsReportDto.getToDate());

		return appointmentResponseDto;
	}
	public GetMonthlyAppointmentsResponseDto getMonthlyAppointments() {
		GetMonthlyAppointmentsResponseDto getMonthlyAppointmentsResponseDto = new GetMonthlyAppointmentsResponseDto();
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
		getMonthlyAppointmentsResponseDto.setMonthlistappointment(totalAppointments);
		getMonthlyAppointmentsResponseDto.setMonthlytotalappointments(monthList);
		return getMonthlyAppointmentsResponseDto;

	}

	public boolean exportAppointmentsReport() {
		
		
		List<Appointment> apptList = (List<Appointment>) httpSession.getAttribute("appointmentList");
	
			boolean writeSucees = false;
			
			try {

				// Creating an excel file
				XSSFWorkbook workbook = new XSSFWorkbook();
				XSSFSheet sheet = workbook.createSheet("ListOfAppointments");
				Map<String, Object[]> data = new HashMap<String, Object[]>();
				Map<String, Object[]> headerData = new HashMap<String, Object[]>();
				headerData.put("Header",
						new Object[] { "Appt. UID", "Appt. No.", "Appointment Date", "Client Name",
								"Contact Number", "Status"});
				int i = 1;
				for (Appointment appointmentDetails : apptList) {
					data.put(Integer.toString(i),
							new Object[] { DataUtil.emptyString(Integer.toString(appointmentDetails.getId())),  DataUtil.emptyString(appointmentDetails.getExternalid()),
									 DataUtil.emptyString(DateUtil.getStringDate(appointmentDetails.getAppointmentdate())),
									/*
									 * DataUtil.emptyString(appointmentDetails.getParent().getStudent().
									 * getAdmissionnumber()),
									 */
									 DataUtil.emptyString(appointmentDetails.getParent().getStudent().getName()),
									/*
									 * DataUtil.emptyString(appointmentDetails.getParent().getStudent().
									 * getClassstudying().replace("--", " ")),
									 */
									 DataUtil.emptyString(appointmentDetails.getParent().getContactnumber()),
									 DataUtil.emptyString(appointmentDetails.getStatus()) });
					i++;
				}
				Row headerRow = sheet.createRow(0);
				Object[] objArrHeader = headerData.get("Header");
				int cellnum1 = 0;
				for (Object obj : objArrHeader) {
					Cell cell = headerRow.createCell(cellnum1++);
					if (obj instanceof String)
						cell.setCellValue((String) obj);
				}
				Set<String> keyset = data.keySet();
				int rownum = 1;
				for (String key : keyset) {
					Row row = sheet.createRow(rownum++);
					Object[] objArr = data.get(key);
					int cellnum = 0;
					for (Object obj : objArr) {
						Cell cell = row.createCell(cellnum++);
						if (obj instanceof Date)
							cell.setCellValue((Date) obj);
						else if (obj instanceof Boolean)
							cell.setCellValue((Boolean) obj);
						else if (obj instanceof String)
							cell.setCellValue((String) obj);
						else if (obj instanceof Double)
							cell.setCellValue((Double) obj);
					}
				}
					FileOutputStream out = new FileOutputStream(new File(System.getProperty("java.io.tmpdir")+"/appointmentreport.xlsx"));
					workbook.write(out);
					out.close();
					writeSucees = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return writeSucees;
			// getFile(name, path);
		
	}

	public boolean download() {
		boolean result = false;
		try {

			File downloadFile = new File(System.getProperty("java.io.tmpdir")+"/appointmentreport.xlsx");
	        FileInputStream inStream = new FileInputStream(downloadFile);

	        // get MIME type of the file
			String mimeType = "application/vnd.ms-excel";

			// set content attributes for the response
			response.setContentType(mimeType);
			// response.setContentLength((int) bis.length());

			// set headers for the response
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"",
					"appointmentreport.xlsx");
			response.setHeader(headerKey, headerValue);

			// get output stream of the response
			OutputStream outStream = response.getOutputStream();

			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;

			// write bytes read from the input stream into the output stream
			while ((bytesRead = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}

			inStream.close();
			outStream.close();
			result = true;
		} catch (Exception e) {
			System.out.println("" + e);
		}
		return result;
	}

	public void generateAppointmentsReportForClient() {
		
		String studentId = request.getParameter("id");
		String queryMain = "from Appointment ap where ap.parent.Student.sid = '"+studentId+"' ";
		List<Appointment> appointmentList = new ArrayList<Appointment>();
				
		appointmentList = new AppointmentDAO().generateAppointmentsReport(queryMain);
		
		httpSession.setAttribute("appointmentList", appointmentList);
	}

	public boolean updateAppointment() {
		
		String[] appointmentIds = request.getParameterValues("appointmentids");
		List<Appointment> appointmentList = new ArrayList<Appointment>();
		boolean result = false;
		
		if(appointmentIds.length > 0 ) {
			
			for (String ids : appointmentIds) {
				Appointment appt = new Appointment();
				appt.setId(Integer.parseInt(ids));
				appt.setAppointmentstarttime(request.getParameter("starttime_"+ids));
				appt.setAppointmentendtime(request.getParameter("endtime_"+ids));
				
				 SimpleDateFormat format = new SimpleDateFormat("HH:mm"); // 12 hour format
				 java.util.Date d1 = null;
				 java.util.Date d2 = null;
				 
				 if(appt.getAppointmentstarttime() != "" && appt.getAppointmentendtime() !="" ) {
					 
				 
				try {
					d1 = (java.util.Date)format.parse(request.getParameter("starttime_"+ids).toString());
					d2 = (java.util.Date)format.parse(request.getParameter("endtime_"+ids).toString());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				long difference_In_Time = d2.getTime()-d1.getTime();
				 //long difference_In_Hours = (difference_In_Time / (1000 * 60 * 60)) % 24;
				 
				 long diffSeconds = difference_In_Time / 1000;         
				 long diffMinutes = difference_In_Time / (60 * 1000) % 60;         
				 long diffHours = difference_In_Time / (60 * 60 * 1000);  
				 
				 appt.setTotaltime(Long.toString(diffHours)+":"+Long.toString(diffMinutes));
				
				 }else {
					 appt.setTotaltime("");
				 }
				 appointmentList.add(appt);
			}
			result = new AppointmentDAO().updateAppointments(appointmentList);
		}
		request.setAttribute("appointmentstatus",result);
		return result;
	}
	
}
