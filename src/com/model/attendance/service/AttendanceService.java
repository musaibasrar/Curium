package com.model.attendance.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.model.academicyear.dao.YearDAO;
import com.model.academicyear.dto.Currentacademicyear;
import com.model.attendance.dao.AttendanceDAO;
import com.model.attendance.dto.Attendancemaster;
import com.model.attendance.dto.Holidaysmaster;
import com.model.attendance.dto.Studentdailyattendance;
import com.model.attendance.dto.Weeklyoff;
import com.model.employee.dao.EmployeeDAO;
import com.model.employee.dto.Teacher;
import com.model.marksdetails.dao.MarksDetailsDAO;
import com.model.marksdetails.dto.Marks;
import com.model.parents.dto.Parents;
import com.model.student.dao.studentDetailsDAO;
import com.model.student.dto.Student;
import com.model.user.dao.UserDAO;
import com.util.DataUtil;
import com.util.DateUtil;

public class AttendanceService {
	
	 	private HttpServletRequest request;
	    private HttpServletResponse response;
	    private HttpSession httpSession;
	    private static final int BUFFER_SIZE = 4096;
	    private String CURRENTACADEMICYEAR = "currentAcademicYear";
	
	    private static final Logger logger = LogManager.getLogger(AttendanceService.class);
	    
	    public AttendanceService(){
	    }
	    
	public AttendanceService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
        this.response = response;
        this.httpSession = request.getSession();
	}

	public void viewAllHolidays() {
		//remove it after testing
		//httpSession.setAttribute("currentAcademicYear", "2017/18");
		if(httpSession.getAttribute(CURRENTACADEMICYEAR)!=null){
		List<Holidaysmaster> list = new AttendanceDAO().readListOfHolidays(httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
	        request.setAttribute("holidaysList", list);
		}
	}

	public boolean addHolidays() {

		Holidaysmaster holidayMaster = new Holidaysmaster();
		String[] fromDate = request.getParameterValues("fromdate");
		String[] toDate = request.getParameterValues("todate");
		String[] holidayName = request.getParameterValues("holidayname");
		boolean result = false;

		if(fromDate!=null && toDate!=null && holidayName!=null && httpSession.getAttribute(CURRENTACADEMICYEAR)!=null){
			try{
		for (int i = 0; i < fromDate.length; i++) {
			
			holidayMaster.setFromdate(DateUtil.datePars(fromDate[i]));
			holidayMaster.setTodate(DateUtil.datePars(toDate[i]));
			holidayMaster.setHolidayname(holidayName[i]);
			holidayMaster.setAcademicyear(httpSession.getAttribute(CURRENTACADEMICYEAR).toString());	
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
			weeklyOff.setAcademicyear(httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
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
		if(httpSession.getAttribute(CURRENTACADEMICYEAR)!=null){
			List<Weeklyoff> weekOffList = new AttendanceDAO().readListOfWeekOff(httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
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
		attendanceMaster.setIntime(request.getParameter("cutoff") +":"+ request.getParameter("min") +" "+ request.getParameter("ampm"));
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

	public boolean uploadAttendanceFile() throws IOException {

		if(httpSession.getAttribute(CURRENTACADEMICYEAR)!=null){
		
		Date todaysDate = new Date();
		List<String> staffExternalId = new EmployeeDAO().getEmployeeExternalId();
		List<Attendancemaster> studentAttendanceMaster = new AttendanceDAO().getAttendanceMasterDetails("00011");
		String[] weeklyOffString = studentAttendanceMaster.get(0).getWeeklyoff().split(",");
		List<Integer> studentWeeklyOffList = new ArrayList<Integer>();
		boolean studentWeeklyOff = false;
		boolean studentHoliday = false;
		for (String weekOffS : weeklyOffString) {
			studentWeeklyOffList.add(Integer.parseInt(weekOffS));
		}
		List<Weeklyoff> studentWeekOff = new AttendanceDAO().readListOfWeeklyOff(studentWeeklyOffList, httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
		
		String today = new SimpleDateFormat("EEEE").format(todaysDate);
		for (Weeklyoff weeklyoff : studentWeekOff) {
			if(weeklyoff.getWeeklyoffday().equalsIgnoreCase(new SimpleDateFormat("EEEE").format(todaysDate))){
				studentWeeklyOff = true;
			}
		}
		if(!studentWeeklyOff){
		String[] holidayString = studentAttendanceMaster.get(0).getHolidayname().split(",");
		List<Integer> studentHolidayList = new ArrayList<Integer>();
		for (String singleHoliday : holidayString) {
			studentHolidayList.add(Integer.parseInt(singleHoliday));
		}
		List<Holidaysmaster> studentHolidays = new AttendanceDAO().readListOfholidays(studentHolidayList, httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
		for (Holidaysmaster holidaysmaster : studentHolidays) {
			Date fromDate = holidaysmaster.getFromdate();
			Date toDate = holidaysmaster.getTodate();
			if(fromDate.compareTo(todaysDate) * todaysDate.compareTo(toDate) >= 0){
				studentHoliday = true;
			}
			
		}
		}
		
		List<Teacher> staff = new ArrayList<Teacher>();
		List<Studentdailyattendance> listStudentAttendance = new ArrayList<Studentdailyattendance>();
		boolean employeeFlag = false;
		try {
			List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
		
			 for (FileItem item : items) {
		                // Process form file field (input type="file").
		                String fieldName = item.getFieldName();

		                if (fieldName.equalsIgnoreCase("file")) {
		                   

		       		            // Using XSSF for xlsx format, for xls use HSSF
		       		            Workbook workbook = new XSSFWorkbook(item.getInputStream());

		       		            int numberOfSheets = workbook.getNumberOfSheets();

		       		            //looping over each workbook sheet
		       		            for (int i = 0; i < numberOfSheets; i++) {
		       		                Sheet sheet = workbook.getSheetAt(i);
		       		                Iterator rowIterator = sheet.iterator();

		       		                //iterating over each row
		       		                while (rowIterator.hasNext()) {
		       		                 Row row = (Row) rowIterator.next();
		       		                 
		       		              if (row.getRowNum() == 0) {
		       		                continue;// skip first row, as it contains column names
		       		            }
		       		           DataFormatter formatter = new DataFormatter();
		       		           
		       		            //start time and end time
		       		      		boolean cutOffFlag = checkTimings(formatter.formatCellValue(row.getCell(1)),studentAttendanceMaster.get(0).getIntime());
		       		      		
		       		                if(!staffExternalId.contains(formatter.formatCellValue(row.getCell(0)))){
		       		                
		       		                	Studentdailyattendance studentAttendance = new Studentdailyattendance();
			       		                 studentAttendance.setAttendeeid(formatter.formatCellValue(row.getCell(0)));
			       		              	 studentAttendance.setIntime(formatter.formatCellValue(row.getCell(1)));
			       		              	 studentAttendance.setOuttime(formatter.formatCellValue(row.getCell(2)));
			       		              	 if(formatter.formatCellValue(row.getCell(1))!="" && !cutOffFlag){
			       		              		 studentAttendance.setAttendancestatus("P");
			       		              	 }else{
			       		              		 studentAttendance.setAttendancestatus("A");	 
			       		              	 }

			       		              	 if(studentWeeklyOff || studentHoliday){
			       		              		 studentAttendance.setAttendancestatus("H");
			       		              	 }
			       		              	 studentAttendance.setAcademicyear(httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
			       		              	 studentAttendance.setDate(new Date());
			       		              	 listStudentAttendance.add(studentAttendance);
			       		              	 
		       		                }else if(staffExternalId.contains(formatter.formatCellValue(row.getCell(0)))){
		       		                	
		       		                }
		       		                	
		       		                    }
		       		                }
		       		            }
		                }
		        } catch (FileUploadException e) {
		        	logger.error(e);
		        }
		
			if(!listStudentAttendance.isEmpty()){
				return new AttendanceDAO().saveStudentAttendance(listStudentAttendance);
			}
		}
		return false;
	}

	private boolean checkTimings(String intime, String cutOffTime) {
					try {

				        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa");
				        Date inTime = sdf.parse(intime);
				        Date cutOff = sdf.parse(cutOffTime);
				        
				        if(inTime.after(cutOff)){
				        	return true;
				        }
					} catch (Exception e) {
						logger.info("checktimings "+e);
					}
					
		return false;
	}

	public boolean searchStudentAttendanceDetails() {
		boolean result = false;
		if(httpSession.getAttribute(CURRENTACADEMICYEAR)!=null){
			
			String queryMain = "From Student as student where";
			String studentname = DataUtil.emptyString(request.getParameter("namesearch"));

			String addClass = request.getParameter("classsearch");
			String addSec = request.getParameter("secsearch");
			String conClassStudying = "";
			String conClassStudyingEquals = "";

			if (!addClass.equalsIgnoreCase("Class")) {

				conClassStudying = addClass + " " + "%";
				conClassStudyingEquals = addClass;
			}
			if (!addSec.equalsIgnoreCase("Sec")) {
				conClassStudying = addClass;
				conClassStudying = conClassStudying + " " + addSec;
				conClassStudyingEquals = conClassStudying;
			}

			String classStudying = DataUtil.emptyString(conClassStudying);
			String querySub = "";

			if (!studentname.equalsIgnoreCase("")) {
				querySub = " student.name like '%" + studentname + "%'";
			}

			if (!classStudying.equalsIgnoreCase("")) {
				querySub = " student.classstudying like '" + classStudying
						+ "' OR student.classstudying = '" + conClassStudyingEquals
						+ "'  AND student.archive=0";
			} else if (classStudying.equalsIgnoreCase("") && !querySub.equalsIgnoreCase("")) {
				querySub = querySub + " AND student.archive=0";
			}

			queryMain = queryMain + querySub;
			List<Student> searchStudentList = new studentDetailsDAO().getListStudents(queryMain);
			
			List<Student> newStudentList = new ArrayList<Student>();
			List<Studentdailyattendance> newStudentDailyAttendance = new ArrayList<Studentdailyattendance>();
			
			Date searchdate = DateUtil.dateParserUpdateStd(request.getParameter("dateofattendance"));
			Timestamp timestamp = new Timestamp(searchdate.getTime());
			for (Student student : searchStudentList) {

				List<Studentdailyattendance> studentsAttendance = new AttendanceDAO().readListOfStudentAttendance(httpSession.getAttribute(CURRENTACADEMICYEAR).toString(), timestamp,student.getStudentexternalid());
				for (Studentdailyattendance studentDailyAttendance : studentsAttendance) {
						newStudentList.add(student);
						newStudentDailyAttendance.add(studentDailyAttendance);
					
				}
			}

			request.setAttribute("StudentListAttendance", newStudentList);
			request.setAttribute("StudentDailyAttendanceDate", newStudentDailyAttendance);
			request.setAttribute("searchedDate", DateUtil.dateParserUpdateStd(request.getParameter("dateofattendance")));
			
		        result = true;
			}
		List<Student> studentList = new studentDetailsDAO().readListOfObjectsForIcon();
		request.setAttribute("studentList", studentList);
		return result;
	}

	public boolean viewAttendance() {
		List<Student> studentList = new studentDetailsDAO().readListOfObjectsForIcon();
		request.setAttribute("studentList", studentList);
			if(!studentList.isEmpty()){
				return true;
			}
			return false;
	}

	public boolean viewStudentAttendanceDetailsMonthly() {
		
		if(httpSession.getAttribute(CURRENTACADEMICYEAR).toString()!=null){
			
			String studentExternalId = DataUtil.emptyString(request.getParameter("studentexternalid"));
			Date fromDate = DateUtil.dateParserUpdateStd(request.getParameter("fromdateofattendance"));
			Date toDate = DateUtil.dateParserUpdateStd(request.getParameter("todateofattendance"));
			Timestamp fromTimestamp = new Timestamp(fromDate.getTime());
			Timestamp toTimestamp = new Timestamp(toDate.getTime());
			
			List<Studentdailyattendance> studentDailyAttendance = new ArrayList<Studentdailyattendance>();
			studentDailyAttendance = new AttendanceDAO().getStudentDailyAttendance(studentExternalId, fromTimestamp, toTimestamp, httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
			request.setAttribute("studentDailyAttendance", studentDailyAttendance);
			request.setAttribute("studentname", request.getParameter("studentname"));
			request.setAttribute("admno", request.getParameter("admno"));
			Calendar start = Calendar.getInstance();
			start.setTime(fromDate);
			Calendar end = Calendar.getInstance();
			end.setTime(toDate);
			end.add(Calendar.DATE, 1);
			
			for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
			    // Do your job here with `date`.
			    System.out.println(new SimpleDateFormat("dd-MM-YYYY").format(date) );
			    
			    
			}
			
		}
		List<Student> studentList = new studentDetailsDAO().readListOfObjectsForIcon();
		request.setAttribute("studentList", studentList);
		
		return true;
	}

	public boolean updateStudentAttendanceDetails() {
		
		if(httpSession.getAttribute(CURRENTACADEMICYEAR).toString()!=null){
			String[] attendanceIds = request.getParameterValues("attandanceIDs");
			String[] studentAttendanceStatus = request.getParameterValues("studentAttendanceStatus");
			List<Integer> attendanceIdsList = new ArrayList<Integer>();
			List<String> studentAttendanceStatusList = new ArrayList<String>();
			for (String attid : attendanceIds) {
				String[] attidString = attid.split(",");
				if(attidString[0]!=null && attidString[1]!=null){
					attendanceIdsList.add(Integer.parseInt(attidString[0]));
					studentAttendanceStatusList.add(studentAttendanceStatus[Integer.parseInt(attidString[1])]);	
				}
				
			}
			return new AttendanceDAO().updateStudentAttendanceDetails(attendanceIdsList,studentAttendanceStatusList,httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
		}
		return false;
	}

	
public boolean viewStudentAttendanceDetailsMonthlyGraph() {
				
		boolean result = false;
				
		if(httpSession.getAttribute(CURRENTACADEMICYEAR).toString()!=null){
			
			List<String> xAxis = new ArrayList<String>();
			List<Integer> yAxis = new ArrayList<Integer>();
			String studentExternalIdGraph = DataUtil.emptyString(request.getParameter("studentexternalidgraph"));
			Date fromDate = DateUtil.dateParserUpdateStd(request.getParameter("frommonthlyattendance"));
			Date toDate = DateUtil.dateParserUpdateStd(request.getParameter("tomonthlyattendance"));
			 
			Calendar start = Calendar.getInstance();
			start.setTime(fromDate);
			
			Calendar end = Calendar.getInstance();
			end.setTime(toDate);
			end.add(Calendar.DATE, 1);
			
			int totalMonths =0;
			for (Date date = start.getTime(); start.before(end); start.add(Calendar.MONTH, +1), date = start.getTime()) {
			    //System.out.println(new SimpleDateFormat("dd-MM-YYYY").format(date) );
			    totalMonths++;
			}
			
			start.setTime(fromDate);
			Date dateTemp = start.getTime();
			
			for(int i=0; i<totalMonths; i++){

				Timestamp timestampFrom = new Timestamp(dateTemp.getTime());
				start.set(Calendar.DAY_OF_MONTH, start.getActualMaximum(Calendar.DAY_OF_MONTH));
				
				int daysInMonth = start.getActualMaximum(Calendar.DAY_OF_MONTH);
				String monthName = new SimpleDateFormat("MMM").format(start.getTime());
				
				Date lastDayOfMonth = start.getTime();
				Timestamp timestampTo = new Timestamp(lastDayOfMonth.getTime());
				
				List<Studentdailyattendance> studentDailyAttendance = new ArrayList<Studentdailyattendance>();
				studentDailyAttendance = new AttendanceDAO().getStudentDailyAttendanceGraph(studentExternalIdGraph, timestampFrom, timestampTo, httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
				
				int absentDays = 0;
				
				for (Studentdailyattendance dailyattendance : studentDailyAttendance) {
					
					if(("A").equalsIgnoreCase(dailyattendance.getAttendancestatus())){
						absentDays++;
					}
					
				}
				
				if(!studentDailyAttendance.isEmpty()){
					int totalPresent = daysInMonth - absentDays;
					xAxis.add( monthName+" "+totalPresent+"/"+daysInMonth);
					yAxis.add(totalPresent);
				}
				
				start.add(Calendar.MONTH, +1);
				start.set(Calendar.DAY_OF_MONTH, start.getActualMinimum(Calendar.DAY_OF_MONTH));
				dateTemp = start.getTime();
			}
			
			request.setAttribute("xaxis", xAxis);
			request.setAttribute("yaxis", yAxis);
			request.setAttribute("studentnamegraph", request.getParameter("studentnamegraph"));
			request.setAttribute("admnograph", request.getParameter("admnograph"));
			result = true;
						
		}
		List<Student> studentList = new studentDetailsDAO().readListOfObjectsForIcon();
		request.setAttribute("studentList", studentList);
		
		return result;
	}

	public boolean viewStudentAttendanceDetailsMonthlyGraphOLD() {
		
		if(httpSession.getAttribute(CURRENTACADEMICYEAR).toString()!=null){
			
			String studentExternalIdGraph = DataUtil.emptyString(request.getParameter("studentexternalidGraph"));
			Date fromDate = DateUtil.dateParserUpdateStd(request.getParameter("frommonthlyattendance"));
			Date toDate = DateUtil.dateParserUpdateStd(request.getParameter("tomonthlyattendance"));
			 
			Calendar cStart = Calendar.getInstance();
			cStart.setTime(fromDate);
			cStart.set(Calendar.DAY_OF_MONTH, 1);
			Date dateTemp = cStart.getTime();
			//Timestamp fromTimestamp = new Timestamp(dateTemp.getTime());

			Calendar cEnd = Calendar.getInstance();
			cEnd.setTime(toDate);
			cEnd.set(Calendar.DAY_OF_MONTH, cEnd.getActualMaximum(Calendar.DAY_OF_MONTH));
			/*dateTemp = cEnd.getTime();
			Timestamp toTimestamp = new Timestamp(dateTemp.getTime());*/
			int diffYear = Math.abs(cStart.get(Calendar.YEAR) - cEnd.get(Calendar.YEAR));
			int diffMonth = diffYear * 12 + Math.abs(cStart.get(Calendar.MONTH) - cEnd.get(Calendar.MONTH));
			int monthsDiff = cStart.get(Calendar.MONTH) - cEnd.get(Calendar.MONTH);
			
			Map<Integer,List<Studentdailyattendance>> mapStudentDailyAttendance = new HashMap<Integer,List<Studentdailyattendance>>();
			for(int i=0; i<monthsDiff; i++){

				Timestamp TimestampFrom = new Timestamp(dateTemp.getTime());
				cStart.add(Calendar.MONTH, 1);
				cStart.set(Calendar.DAY_OF_MONTH, 1);
				cStart.add(Calendar.DATE, -1);
				
				Date lastDayOfMonth = cStart.getTime();
				Timestamp Timestampto = new Timestamp(lastDayOfMonth.getTime());
				
				List<Studentdailyattendance> studentDailyAttendance = new ArrayList<Studentdailyattendance>();
				studentDailyAttendance = new AttendanceDAO().getStudentDailyAttendance(studentExternalIdGraph, TimestampFrom, Timestampto, httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
				mapStudentDailyAttendance.put(i, studentDailyAttendance);
				
				cStart.add(Calendar.MONTH, +1);
				cStart.set(Calendar.DAY_OF_MONTH, cStart.getActualMinimum(Calendar.DAY_OF_MONTH));
				dateTemp = cStart.getTime();
			}
			
			request.setAttribute("studentDailyAttendanceGraph", "");
			request.setAttribute("studentname", request.getParameter("studentname"));
			request.setAttribute("admno", request.getParameter("admno"));
			Calendar start = Calendar.getInstance();
			start.setTime(fromDate);
			Calendar end = Calendar.getInstance();
			end.setTime(toDate);
			end.add(Calendar.DATE, 1);
			
			for (Date date = start.getTime(); start.before(end); start.add(Calendar.MONTH, +1), date = start.getTime()) {
			    // Do your job here with `date`.
			    System.out.println(new SimpleDateFormat("dd-MM-YYYY").format(date) );
			    
			    
			}
			
			
			for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
			    // Do your job here with `date`.
			    System.out.println(new SimpleDateFormat("dd-MM-YYYY").format(date) );
			    
			    
			}
			
		}
		List<Student> studentList = new studentDetailsDAO().readListOfObjectsForIcon();
		request.setAttribute("studentList", studentList);
		
		return true;
	}

	public boolean viewStudentAttendanceDetailsMark() {
		
		boolean result = false;
		if(httpSession.getAttribute(CURRENTACADEMICYEAR)!=null){
			
			String queryMain = "From Student as student where";
			String studentname = DataUtil.emptyString(request.getParameter("namesearch"));

			String addClass = request.getParameter("classsearch");
			String addSec = request.getParameter("secsearch");
			String conClassStudying = "";
			String conClassStudyingEquals = "";

			if (!addClass.equalsIgnoreCase("Class")) {

				conClassStudying = addClass + " " + "%";
				conClassStudyingEquals = addClass;
			}
			if (!addSec.equalsIgnoreCase("Sec")) {
				conClassStudying = addClass;
				conClassStudying = conClassStudying + " " + addSec;
				conClassStudyingEquals = conClassStudying;
			}

			String classStudying = DataUtil.emptyString(conClassStudying);
			String querySub = "";

			if (!studentname.equalsIgnoreCase("")) {
				querySub = " student.name like '%" + studentname + "%'";
			}

			if (!classStudying.equalsIgnoreCase("")) {
				querySub = " student.classstudying like '" + classStudying
						+ "' OR student.classstudying = '" + conClassStudyingEquals
						+ "'  AND student.archive=0";
			} else if (classStudying.equalsIgnoreCase("") && !querySub.equalsIgnoreCase("")) {
				querySub = querySub + " AND student.archive=0";
			}

			queryMain = queryMain + querySub;
			List<Student> searchStudentList = new studentDetailsDAO().getListStudents(queryMain);

			request.setAttribute("StudentListAttendance", searchStudentList);
			
		        result = true;
			}
		List<Student> studentList = new studentDetailsDAO().readListOfObjectsForIcon();
		request.setAttribute("studentList", studentList);
		return result;
	}

	public boolean markStudentsAttendance() {
		boolean result = false;
		if(httpSession.getAttribute(CURRENTACADEMICYEAR).toString()!=null){
			String[] attendanceIds = request.getParameterValues("externalIDs");
			String[] studentAttendanceStatus = request.getParameterValues("studentAttendanceStatus");
			List<String> attendanceIdsList = new ArrayList<String>();
			List<String> studentAttendanceStatusList = new ArrayList<String>();
			for (String attid : attendanceIds) {
				String[] attidString = attid.split(",");
				if(attidString[0]!=null && attidString[1]!=null){
					attendanceIdsList.add(attidString[0]);
					studentAttendanceStatusList.add(studentAttendanceStatus[Integer.parseInt(attidString[1])]);	
				}
				
			}
			
			for (int i=0; i<attendanceIdsList.size();i++) {
				Studentdailyattendance studentDailyAttendance = new Studentdailyattendance();
				studentDailyAttendance.setAttendeeid(attendanceIdsList.get(i));
				studentDailyAttendance.setAttendancestatus(studentAttendanceStatusList.get(i));
				studentDailyAttendance.setIntime("00:00");
				studentDailyAttendance.setDate(new Date());
				studentDailyAttendance.setAcademicyear(httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
				result = new AttendanceDAO().checkStudentAttendance(studentDailyAttendance);
			}
			
		}
		return result;
	}
	
	public void markDailyAttendanceJob(){
			List<Student> studentList = new studentDetailsDAO().getListStudents("from Student where archive=0");
			Currentacademicyear currentAcademicYear = new YearDAO().showYear();
			//get weekoff
			
			//get holiday
			
			//else{
			new AttendanceDAO().markDailyAttendanceJob(studentList,currentAcademicYear.getCurrentacademicyear());
	}
	
}
