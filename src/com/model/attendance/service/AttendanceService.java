package com.model.attendance.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.model.academicyear.dao.YearDAO;
import com.model.academicyear.dto.Currentacademicyear;
import com.model.attendance.dao.AttendanceDAO;
import com.model.attendance.dto.Attendancemaster;
import com.model.attendance.dto.Holidaysmaster;
import com.model.attendance.dto.Staffdailyattendance;
import com.model.attendance.dto.Studentdailyattendance;
import com.model.attendance.dto.Weeklyoff;
import com.model.employee.dao.EmployeeDAO;
import com.model.employee.dto.Teacher;
import com.model.marksdetails.dao.MarksDetailsDAO;
import com.model.marksdetails.dto.Marks;
import com.model.parents.dto.Parents;
import com.model.sendsms.service.SmsService;
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
	    private String BRANCHID = "branchid";
	    private String USERID = "userloginid";
	    
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
		List<Holidaysmaster> list = new AttendanceDAO().readListOfHolidays(httpSession.getAttribute(CURRENTACADEMICYEAR).toString(), Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
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
			holidayMaster.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			holidayMaster.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
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
			weeklyOff.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			weeklyOff.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
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
			List<Weeklyoff> weekOffList = new AttendanceDAO().readListOfWeekOff(httpSession.getAttribute(CURRENTACADEMICYEAR).toString(), Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
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
		attendanceMaster.setIntime(request.getParameter("cutoff"));
		attendanceMaster.setWeeklyoff(sbWeek.toString());
		attendanceMaster.setHolidayname(sbHoliday.toString());
		attendanceMaster.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		attendanceMaster.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
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
			attendanceMaster.setIntime(request.getParameter("intime"));
			attendanceMaster.setOuttime(request.getParameter("outtime"));
			attendanceMaster.setWeeklyoff(sbWeek.toString());
			attendanceMaster.setHolidayname(sbHoliday.toString());
			attendanceMaster.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			attendanceMaster.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
			attendanceMasterList.add(attendanceMaster);
		}
		return new AttendanceDAO().addAttendanceMaster(attendanceMasterList);
	}

	public boolean uploadAttendanceFile() throws IOException {
			
		boolean result = false;
		if(httpSession.getAttribute(CURRENTACADEMICYEAR)!=null){
		
		Date todaysDate = new Date();
		List<String> staffExternalId = new EmployeeDAO().getEmployeeExternalId();
		List<Attendancemaster> studentAttendanceMaster = new AttendanceDAO().getAttendanceMasterDetails("00011", Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		String[] weeklyOffString = studentAttendanceMaster.get(0).getWeeklyoff().split(",");
		List<Integer> studentWeeklyOffList = new ArrayList<Integer>();
		boolean studentWeeklyOff = false;
		boolean studentHoliday = false;
		for (String weekOffS : weeklyOffString) {
			studentWeeklyOffList.add(Integer.parseInt(weekOffS));
		}
		List<Weeklyoff> studentWeekOff = new AttendanceDAO().readListOfWeeklyOff(studentWeeklyOffList, httpSession.getAttribute(CURRENTACADEMICYEAR).toString(), Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		
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
		List<Holidaysmaster> studentHolidays = new AttendanceDAO().readListOfholidays(studentHolidayList, httpSession.getAttribute(CURRENTACADEMICYEAR).toString(), Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
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
		List<Staffdailyattendance> listStaffAttendance = new ArrayList<Staffdailyattendance>();
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
			       		              	 studentAttendance.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			       		              	 studentAttendance.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
			       		              	 listStudentAttendance.add(studentAttendance);
			       		              	 
		       		                }else if(staffExternalId.contains(formatter.formatCellValue(row.getCell(0)))){
		       		                	
		       		                	Staffdailyattendance staffAttendance = new Staffdailyattendance();
		       		                	staffAttendance.setAttendeeid(formatter.formatCellValue(row.getCell(0)));
		       		                	staffAttendance.setIntime(formatter.formatCellValue(row.getCell(1)));
		       		                	staffAttendance.setOuttime(formatter.formatCellValue(row.getCell(2)));
			       		              	 
		       		                	if(formatter.formatCellValue(row.getCell(1))!=""){
			       		              		 staffAttendance.setAttendancestatus("P");
			       		              	 }else{
			       		              		 staffAttendance.setAttendancestatus("A");	 
			       		              	 }

			       		              	 if(studentWeeklyOff || studentHoliday){
			       		              		staffAttendance.setAttendancestatus("H");
			       		              	 }
			       		              	 	staffAttendance.setAcademicyear(httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
			       		              		staffAttendance.setDate(new Date());
			       		              		staffAttendance.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			       		           			staffAttendance.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
			       		              		listStaffAttendance.add(staffAttendance);
		       		                }
		       		                	
		       		                    }
		       		                }
		       		            }
		                }
		        } catch (FileUploadException e) {
		        	logger.error(e);
		        }
		
			if(!listStudentAttendance.isEmpty()){
				result = new AttendanceDAO().saveStudentAttendance(listStudentAttendance);
			}
			
			if(!listStaffAttendance.isEmpty()){
				result = new AttendanceDAO().saveStaffAttendance(listStaffAttendance);
			}
		}
		return result;
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
			
			if (!addClass.equalsIgnoreCase("")) {

				conClassStudying = addClass+"--" +"%";

			}
			if (!addSec.equalsIgnoreCase("")) {
				conClassStudying = addClass;
				conClassStudying = conClassStudying+"--"+addSec+"%";
			}

			String classStudying = DataUtil.emptyString(conClassStudying);
			String querySub = "";

			if (!studentname.equalsIgnoreCase("")) {
				querySub = " student.name like '%" + studentname + "%'";
			}

			if (!classStudying.equalsIgnoreCase("")) {
				querySub = " student.classstudying like '" + classStudying
						+ "' OR student.classstudying = '" + conClassStudyingEquals
						+ "'  AND student.archive=0 and student.passedout=0 AND student.droppedout=0 and student.leftout=0  AND student.branchid="+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString());
			} else if (classStudying.equalsIgnoreCase("") && !querySub.equalsIgnoreCase("")) {
				querySub = querySub + " AND student.archive=0 and student.passedout=0 AND student.droppedout=0 and student.leftout=0 AND student.branchid="+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString());
			}

			queryMain = queryMain + querySub;
			List<Student> searchStudentList = new studentDetailsDAO().getListStudents(queryMain);
			
			List<Student> newStudentList = new ArrayList<Student>();
			List<Studentdailyattendance> newStudentDailyAttendance = new ArrayList<Studentdailyattendance>();
			
			Date searchdate = DateUtil.dateParserUpdateStd(request.getParameter("dateofattendance"));
			Timestamp timestamp = new Timestamp(searchdate.getTime());
			for (Student student : searchStudentList) {

				List<Studentdailyattendance> studentsAttendance = new AttendanceDAO().readListOfStudentAttendance(httpSession.getAttribute(CURRENTACADEMICYEAR).toString(), timestamp,student.getStudentexternalid(), Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
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
		List<Student> studentList = new studentDetailsDAO().readListOfObjectsForIcon(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		request.setAttribute("studentList", studentList);
		return result;
	}

	public boolean viewAttendance() {
		List<Student> studentList = new studentDetailsDAO().readListOfObjectsForIcon(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		request.setAttribute("studentList", studentList);
			if(!studentList.isEmpty()){
				return true;
			}
			return false;
	}

	public boolean viewStudentAttendanceDetailsMonthly() {
		
		boolean result = false;
		if(httpSession.getAttribute(CURRENTACADEMICYEAR).toString()!=null){
			
			String studentExternalId = DataUtil.emptyString(request.getParameter("studentexternalid"));
			Date fromDate = DateUtil.dateParserUpdateStd(request.getParameter("fromdateofattendance"));
			Date toDate = DateUtil.dateParserUpdateStd(request.getParameter("todateofattendance"));
			Timestamp fromTimestamp = new Timestamp(fromDate.getTime());
			Timestamp toTimestamp = new Timestamp(toDate.getTime());
			
			List<Studentdailyattendance> studentDailyAttendance = new ArrayList<Studentdailyattendance>();
			studentDailyAttendance = new AttendanceDAO().getStudentDailyAttendance(studentExternalId, fromTimestamp, toTimestamp, httpSession.getAttribute(CURRENTACADEMICYEAR).toString(), Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			request.setAttribute("studentDailyAttendance", studentDailyAttendance);
			request.setAttribute("studentname", request.getParameter("studentname"));
			request.setAttribute("admno", request.getParameter("admno"));
			Calendar start = Calendar.getInstance();
			start.setTime(fromDate);
			Calendar end = Calendar.getInstance();
			end.setTime(toDate);
			end.add(Calendar.DATE, 1);
			
			int absentDays = 0;
			int totalDays = 0;
			int totalPresent = 0;
			
			for (Studentdailyattendance dailyattendance : studentDailyAttendance) {
				
				totalDays++;
				if(("A").equalsIgnoreCase(dailyattendance.getAttendancestatus())){
					absentDays++;
				}
				
			}
			
			if(!studentDailyAttendance.isEmpty()){
				totalPresent = totalDays - absentDays;
			}
			
			request.setAttribute("totalpresent", totalPresent);
			request.setAttribute("totalabsent", absentDays);
			result = true;
		}
		List<Student> studentList = new studentDetailsDAO().readListOfObjectsForIcon(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		request.setAttribute("studentList", studentList);
		
		return result;
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
				studentDailyAttendance = new AttendanceDAO().getStudentDailyAttendanceGraph(studentExternalIdGraph, timestampFrom, timestampTo, httpSession.getAttribute(CURRENTACADEMICYEAR).toString(), Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
				
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
		List<Student> studentList = new studentDetailsDAO().readListOfObjectsForIcon(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
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
				studentDailyAttendance = new AttendanceDAO().getStudentDailyAttendance(studentExternalIdGraph, TimestampFrom, Timestampto, httpSession.getAttribute(CURRENTACADEMICYEAR).toString(), Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
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
		List<Student> studentList = new studentDetailsDAO().readListOfObjectsForIcon(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
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

			if (!addClass.equalsIgnoreCase("")) {

				conClassStudying = addClass+"--" +"%";

			}
			if (!addSec.equalsIgnoreCase("")) {
				conClassStudying = addClass;
				conClassStudying = conClassStudying+"--"+addSec+"%";
			}

			String classStudying = DataUtil.emptyString(conClassStudying);
			String querySub = "";

			if (!studentname.equalsIgnoreCase("")) {
				querySub = " student.name like '%" + studentname + "%'";
			}

			if (!classStudying.equalsIgnoreCase("")) {
				querySub = " student.classstudying like '" + classStudying
						+ "'  AND student.archive=0 and student.passedout=0 AND student.droppedout=0 and student.leftout=0 AND student.branchid="+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString());
			} else if (classStudying.equalsIgnoreCase("") && !querySub.equalsIgnoreCase("")) {
				querySub = querySub + " AND student.archive=0 and student.passedout=0 AND student.droppedout=0 and student.leftout=0 AND student.branchid="+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString());
			}

			queryMain = queryMain + querySub;
			List<Student> searchStudentList = new studentDetailsDAO().getListStudents(queryMain);

			request.setAttribute("StudentListAttendance", searchStudentList);
			request.setAttribute("attendanceclass", conClassStudying.replace("--"," ").replace("%", ""));
			request.setAttribute("attendanceclasssearch", addClass);
			
		        result = true;
			}
		return result;
	}

	public boolean markStudentsAttendance() {
		boolean result = false;
		
		if(httpSession.getAttribute(CURRENTACADEMICYEAR).toString()!=null){
			
			String[] attendanceIds = request.getParameterValues("externalIDs");
			String[] studentAttendanceStatus = request.getParameterValues("studentAttendanceStatus");
						
			if(attendanceIds!=null) {
			
			List<Studentdailyattendance> studentDailyAttendanceList = new ArrayList<Studentdailyattendance>();
			
			for(int i=0;i<attendanceIds.length;i++) {
				Studentdailyattendance studentDailyAttendance = new Studentdailyattendance();
				
				String[] attidString = attendanceIds[i].split(",");
				studentDailyAttendance.setAttendeeid(attidString[0]);
				studentDailyAttendance.setAttendancestatus(studentAttendanceStatus[i]);
				studentDailyAttendance.setIntime("00:00");
				studentDailyAttendance.setDate(new Date());
				studentDailyAttendance.setAcademicyear(httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
				studentDailyAttendance.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
				studentDailyAttendance.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
				studentDailyAttendanceList.add(studentDailyAttendance);
			}
					
			String res = new AttendanceDAO().checkAndMarkStudentAttendance(studentDailyAttendanceList); 
			request.setAttribute("attedanceresult", res);
			
				if(res!=null) {
					result = true;
				}
					if(res!=null && res.contains("success")) {
						sendSMSAbsentees(studentDailyAttendanceList);
					}
			}
		}
		return result;
	}
	
	public void sendSMSAbsentees(List<Studentdailyattendance> studentDailyAttendanceList) {
		
		Properties properties = new Properties();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("Util.properties");
        
			        try {
						properties.load(inputStream);
					} catch (IOException e) {
						logger.info("send SMS "+e);
					}
        
        String sendSMS = properties.getProperty("sendsms");
        String classesPrePrimary = properties.getProperty("classespreprimary");
        String classesPrimary = properties.getProperty("classesprimary");
        String classesHigh = properties.getProperty("classeshigh");
        
        
        if("yes".equalsIgnoreCase(sendSMS)) {
        	
        	String attendanceClass = request.getParameter("attendanceclass");
        	String absentMessage = null;
        	StringBuilder sbN = new StringBuilder();
        	String numbers = null;
        	List<String> classesPrePrimaryArray = Arrays.asList(classesPrePrimary.split(","));
        	List<String> classesPrimaryArray = Arrays.asList(classesPrimary.split(","));
        	List<String> classesHighArray = Arrays.asList(classesHigh.split(","));
        	
        	if(classesPrePrimaryArray.contains(attendanceClass)) {
        		absentMessage = properties.getProperty("absentmessagepreprimary");
        	}else if(classesPrimaryArray.contains(attendanceClass)) {
        		absentMessage = properties.getProperty("absentmessageprimary");
        	}else if(classesHighArray.contains(attendanceClass)) {
        		absentMessage = properties.getProperty("absentmessagehigh");
        	}
        	
        	for (Studentdailyattendance studentDailyAttendance : studentDailyAttendanceList) {
				  
        		if("A".equalsIgnoreCase(studentDailyAttendance.getAttendancestatus())) {
        				List<Parents> parentDetails = new studentDetailsDAO().getStudentsList("from Parents as parents where parents.Student.studentexternalid='"+studentDailyAttendance.getAttendeeid()+"'");
        				if(parentDetails.size()>0) {
        					sbN.append(parentDetails.get(0).getContactnumber());
        					sbN.append(",");
        				}
				  }
			}
        	
        	if(sbN.length()>1) {
        		numbers = sbN.toString();
    			numbers = numbers.substring(0, numbers.length()-1);
    			String todaysDate = new DateUtil().dateParserddMMYYYY(new Date());
    			logger.info("Absentees Numbers "+numbers+" Absentees Message "+absentMessage.replace("%todaysdate%", todaysDate));
    			new SmsService(request, response).sendSMS(numbers, absentMessage.replace("%todaysdate%", todaysDate));
        	}
        }
        
		
	}

	public void markDailyAttendanceJob(){
		
			Date todaysDate = new Date();
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				todaysDate = sdf.parse(sdf.format(todaysDate)) ;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List<Student> studentList = new studentDetailsDAO().getListStudents("from Student where archive=0 and passedout=0 AND droppedout=0 and leftout=0");
			Currentacademicyear currentAcademicYear = new YearDAO().showYear();
			List<Attendancemaster> studentAttendanceMaster = new AttendanceDAO().getAttendanceMasterDetails("00011");
			String[] weeklyOffString = studentAttendanceMaster.get(0).getWeeklyoff().split(",");
			List<Integer> studentWeeklyOffList = new ArrayList<Integer>();
			boolean studentWeeklyOff = false;
			boolean studentHoliday = false;
			for (String weekOffS : weeklyOffString) {
				studentWeeklyOffList.add(Integer.parseInt(weekOffS));
			}
			List<Weeklyoff> studentWeekOff = new AttendanceDAO().readListOfWeeklyOff(studentWeeklyOffList, currentAcademicYear.getCurrentacademicyear());
			
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
			List<Holidaysmaster> studentHolidays = new AttendanceDAO().readListOfholidays(studentHolidayList, currentAcademicYear.getCurrentacademicyear());
			for (Holidaysmaster holidaysmaster : studentHolidays) {
				Date fromDate = holidaysmaster.getFromdate();
				Date toDate = holidaysmaster.getTodate();
				if(fromDate.compareTo(todaysDate) * todaysDate.compareTo(toDate) >= 0){
					studentHoliday = true;
				}
				
			}
			}
			
			List<Studentdailyattendance> listStudentAttendance = new ArrayList<Studentdailyattendance>();
			
			for (Student studentAttendance : studentList) {
				
				if(studentWeeklyOff || studentHoliday){
						Studentdailyattendance studentDailyAttendance = new Studentdailyattendance();
						studentDailyAttendance.setAttendeeid(studentAttendance.getStudentexternalid());
						studentDailyAttendance.setDate(new Date());
						studentDailyAttendance.setAttendancestatus("H");
						studentDailyAttendance.setAcademicyear(currentAcademicYear.getCurrentacademicyear());
						studentDailyAttendance.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
						listStudentAttendance.add(studentDailyAttendance);
				}
			}
			new AttendanceDAO().markDailyAttendanceJob(listStudentAttendance);
	}

	public boolean exportMonthlyData() {
		
		boolean result = false;
		
		if(httpSession.getAttribute(CURRENTACADEMICYEAR).toString()!=null){
		
		String queryMain = "From Student as student where";

		String addClass = request.getParameter("classsearch");
		String addSec = request.getParameter("secsearch");
		String conClassStudying = "";
		String conClassStudyingEquals = "";

		if (!addClass.equalsIgnoreCase("")) {

			conClassStudying = addClass+"--" +"%";

		}
		if (!addSec.equalsIgnoreCase("")) {
			conClassStudying = addClass;
			conClassStudying = conClassStudying+"--"+addSec+"%";
		}
		
		String classStudying = DataUtil.emptyString(conClassStudying);
		String querySub = "";

		if (!classStudying.equalsIgnoreCase("")) {
			querySub = " student.classstudying like '" + classStudying
					+ "' OR student.classstudying = '" + conClassStudyingEquals
					+ "'  AND student.archive=0 and student.passedout=0 AND student.droppedout=0 and student.leftout=0  AND student.branchid="+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString());
		} else if (classStudying.equalsIgnoreCase("") && !querySub.equalsIgnoreCase("")) {
			querySub = querySub + " AND student.archive=0 and student.passedout=0 AND student.droppedout=0 and student.leftout=0 AND student.branchid="+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString());
		}
		queryMain = queryMain + querySub;
		List<Student> searchStudentList = new studentDetailsDAO().getListStudents(queryMain);
		
		
		Date monthOf = DateUtil.dateParserddmmyyyy(request.getParameter("monthof"));
		 
		Calendar cStart = Calendar.getInstance();
		cStart.setTime(monthOf);
		cStart.set(Calendar.DAY_OF_MONTH, cStart.getActualMinimum(Calendar.DAY_OF_MONTH));
		monthOf = cStart.getTime();
		Timestamp TimestampFrom = new Timestamp(monthOf.getTime());
		
		cStart.set(Calendar.DAY_OF_MONTH, cStart.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date lastDayOfMonth = cStart.getTime();
		Timestamp Timestampto = new Timestamp(lastDayOfMonth.getTime());
		
		Map<String,List<Studentdailyattendance>> studentsAttendance = new AttendanceDAO().readListOfStudentAttendanceExport(httpSession.getAttribute(CURRENTACADEMICYEAR).toString(), TimestampFrom, Timestampto,searchStudentList, Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		
		try {
			result = exportDataToExcel(studentsAttendance,monthOf);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return result;
	}
	
	
	public boolean exportDataToExcel(Map<String, List<Studentdailyattendance>> studentDailyAttendance,Date monthOf)
			throws Exception {

		boolean writeSucees = false;
		Calendar cStart = Calendar.getInstance();
		cStart.setTime(monthOf);
		int numberOfDays = cStart.getActualMaximum(Calendar.DAY_OF_MONTH);
		String monthName = new SimpleDateFormat("MMMM").format(monthOf);
		
		try {

			// Creating an excel file
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet(monthName);
			Map<String, Object[]> data = new HashMap<String, Object[]>();
			
			Row headerRow = sheet.createRow(0);
			headerRow.createCell(0).setCellValue("Student Name");
			sheet.autoSizeColumn(0);
			for(int j=1;j<=numberOfDays;j++){
				headerRow.createCell(j).setCellValue(j);
			}
			
			headerRow.createCell(numberOfDays+2).setCellValue("Total Days Present");
			sheet.autoSizeColumn(numberOfDays+2);
			headerRow.createCell(numberOfDays+3).setCellValue("Total Days Absent");
			sheet.autoSizeColumn(numberOfDays+3);
			int rownum = 1;
			
			for (Entry<String, List<Studentdailyattendance>> entry : studentDailyAttendance.entrySet())
			{
			    Row row = sheet.createRow(rownum++);
			    row.createCell(0).setCellValue(entry.getKey());
			    	int i=1;
			    	int totalDaysPresent = 0;
			    	int totalDaysAbsent = 0;
			    for (Studentdailyattendance studentdailyattendance : entry.getValue()) {
			    			
			    		if(studentdailyattendance.getDate().compareTo(monthOf) > 0) {
			    			
			    			long difference = studentdailyattendance.getDate().getTime() - monthOf.getTime();
			 		        float daysBetween = (difference / (1000*60*60*24));
			 		        
			 		        	for(int j=0; j<daysBetween; j++) {
			 		        		row.createCell(i).setCellValue("NA");
			 				    	sheet.autoSizeColumn(i);
			 				    	i++;
			 		        	}
			 		       
			    		}
			    	
			    	if("P".equalsIgnoreCase(studentdailyattendance.getAttendancestatus()) || "H".equalsIgnoreCase(studentdailyattendance.getAttendancestatus())){
			    		totalDaysPresent++;
			    	}
			    	if("A".equalsIgnoreCase(studentdailyattendance.getAttendancestatus())){
			    		totalDaysAbsent++;
			    	}
			    	row.createCell(i).setCellValue(studentdailyattendance.getAttendancestatus());
			    	sheet.autoSizeColumn(i);
			    	i++;
				}
			    
			    if(i<numberOfDays) {
			    	
	 		        	for(int j=i; j<=numberOfDays; j++) {
	 		        		row.createCell(i).setCellValue("NA");
	 				    	sheet.autoSizeColumn(i);
	 				    	i++;
	 		        	}
			    }
			    
			    
			    row.createCell(++i).setCellValue(totalDaysPresent);
			    row.createCell(++i).setCellValue(totalDaysAbsent);
			}
				
				FileOutputStream out = new FileOutputStream(new File(System.getProperty("java.io.tmpdir")+"/studentsmonthlyattendance.xlsx"));
				workbook.write(out);
				out.close();
				writeSucees = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return writeSucees;
		// getFile(name, path);
	}

	public boolean downloadFile() {
		

		boolean result = false;
		try {

			File downloadFile = new File(System.getProperty("java.io.tmpdir")+"/studentsmonthlyattendance.xlsx");
	        FileInputStream inStream = new FileInputStream(downloadFile);

	        // get MIME type of the file
			String mimeType = "application/vnd.ms-excel";

			// set content attributes for the response
			response.setContentType(mimeType);
			// response.setContentLength((int) bis.length());

			// set headers for the response
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"",
					"studentsmonthlyattendance.xlsx");
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

	public boolean viewAttendanceStaff() {
		
		if(httpSession.getAttribute(BRANCHID)!=null){
			List<Teacher> staffList = new EmployeeDAO().readListOfObjects(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			request.setAttribute("staffList", staffList);
				if(!staffList.isEmpty()){
					return true;
				}
		}
		
			return false;
	}

	public boolean searchStaffAttendanceDetails() {
		
		boolean result = false;
		
		if(httpSession.getAttribute(CURRENTACADEMICYEAR)!=null){
		List<Teacher> searchStaffList = new EmployeeDAO().readListOfObjects(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		
		List<Teacher> newStaffList = new ArrayList<Teacher>();
		List<Staffdailyattendance> newStaffDailyAttendance = new ArrayList<Staffdailyattendance>();
		
		Date searchdate = DateUtil.dateParserUpdateStd(request.getParameter("dateofattendance"));
		Timestamp timestamp = new Timestamp(searchdate.getTime());
		for (Teacher teacher : searchStaffList) {

			List<Staffdailyattendance> staffAttendance = new AttendanceDAO().readListOfStaffAttendance(httpSession.getAttribute(CURRENTACADEMICYEAR).toString(), timestamp,teacher.getTeacherexternalid(), Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			for (Staffdailyattendance staffDailyAttendance : staffAttendance) {
					newStaffList.add(teacher);
					newStaffDailyAttendance.add(staffDailyAttendance);
				
			}
		}

		request.setAttribute("StaffListAttendance", newStaffList);
		request.setAttribute("StaffDailyAttendanceDate", newStaffDailyAttendance);
		request.setAttribute("searchedDate", DateUtil.dateParserUpdateStd(request.getParameter("dateofattendance")));
		
			List<Teacher> staffDetailsList = new EmployeeDAO().readListOfObjects(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			request.setAttribute("staffList", staffDetailsList);
			result = true;
		}
		
		return result;
	}

	public boolean updateStaffAttendanceDetails() {
		
		if(httpSession.getAttribute(CURRENTACADEMICYEAR).toString()!=null){
			String[] attendanceIds = request.getParameterValues("attandanceIDs");
			String[] studentAttendanceStatus = request.getParameterValues("staffAttendanceStatus");
			List<Integer> attendanceIdsList = new ArrayList<Integer>();
			List<String> staffAttendanceStatusList = new ArrayList<String>();
			for (String attid : attendanceIds) {
				String[] attidString = attid.split(",");
				if(attidString[0]!=null && attidString[1]!=null){
					attendanceIdsList.add(Integer.parseInt(attidString[0]));
					staffAttendanceStatusList.add(studentAttendanceStatus[Integer.parseInt(attidString[1])]);	
				}
				
			}
			return new AttendanceDAO().updateStaffAttendanceDetails(attendanceIdsList,staffAttendanceStatusList);
		}
		return false;
	}

	public boolean viewStaffAttendanceDetailsMonthly() {
		
		boolean result = false;
		if(httpSession.getAttribute(CURRENTACADEMICYEAR).toString()!=null){
			
			String staffExternalId = DataUtil.emptyString(request.getParameter("staffexternalid"));
			Date fromDate = DateUtil.dateParserUpdateStd(request.getParameter("fromdateofattendance"));
			Date toDate = DateUtil.dateParserUpdateStd(request.getParameter("todateofattendance"));
			Timestamp fromTimestamp = new Timestamp(fromDate.getTime());
			Timestamp toTimestamp = new Timestamp(toDate.getTime());
			
			List<Staffdailyattendance> staffDailyAttendance = new ArrayList<Staffdailyattendance>();
			staffDailyAttendance = new AttendanceDAO().getStaffDailyAttendance(staffExternalId, fromTimestamp, toTimestamp, httpSession.getAttribute(CURRENTACADEMICYEAR).toString(), Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			request.setAttribute("staffDailyAttendance", staffDailyAttendance);
			request.setAttribute("staffname", request.getParameter("nameofstaff"));
			Calendar start = Calendar.getInstance();
			start.setTime(fromDate);
			Calendar end = Calendar.getInstance();
			end.setTime(toDate);
			end.add(Calendar.DATE, 1);
			
			int absentDays = 0;
			int totalDays = 0;
			int totalPresent = 0;
			
			for (Staffdailyattendance dailyattendance : staffDailyAttendance) {
				
				totalDays++;
				if(("A").equalsIgnoreCase(dailyattendance.getAttendancestatus())){
					absentDays++;
				}
				
			}
			
			if(!staffDailyAttendance.isEmpty()){
				totalPresent = totalDays - absentDays;
			}
			
			request.setAttribute("totalpresent", totalPresent);
			request.setAttribute("totalabsent", absentDays);
			result = true;
		}
		
		List<Teacher> staffList = new EmployeeDAO().readListOfObjects(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		request.setAttribute("staffList", staffList);
		
		return result;
	}

	public boolean markStaffAttendance() {
		boolean result = false;
		if(httpSession.getAttribute(CURRENTACADEMICYEAR).toString()!=null){
			String[] attendanceIds = request.getParameterValues("externalIDs");
			String[] staffAttendanceStatus = request.getParameterValues("staffAttendanceStatus");
			String[] inTime = request.getParameterValues("intime");
			String[] outTime = request.getParameterValues("outtime");
			List<String> attendanceIdsList = new ArrayList<String>();
			List<String> staffAttendanceStatusList = new ArrayList<String>();
			List<String> inTimeList = new ArrayList<String>();
			List<String> outTimeList = new ArrayList<String>();
			for (String attid : attendanceIds) {
				String[] attidString = attid.split(",");
				if(attidString[0]!=null && attidString[1]!=null){
					attendanceIdsList.add(attidString[0]);
					staffAttendanceStatusList.add(staffAttendanceStatus[Integer.parseInt(attidString[1])]);	
					inTimeList.add(inTime[Integer.parseInt(attidString[1])]);
					outTimeList.add(outTime[Integer.parseInt(attidString[1])]);
				}
				
			}
			List<Staffdailyattendance> staffdailyattendanceList = new ArrayList<Staffdailyattendance>();
			for (int i=0; i<attendanceIdsList.size();i++) {
				Staffdailyattendance staffDailyAttendance = new Staffdailyattendance();
				staffDailyAttendance.setAttendeeid(attendanceIdsList.get(i));
				staffDailyAttendance.setAttendancestatus(staffAttendanceStatusList.get(i));
				staffDailyAttendance.setIntime(inTimeList.get(i));
				staffDailyAttendance.setOuttime(outTimeList.get(i));
				staffDailyAttendance.setDate(new Date());
				staffDailyAttendance.setAcademicyear(httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
				staffDailyAttendance.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
				staffDailyAttendance.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
				staffdailyattendanceList.add(staffDailyAttendance);
			}
			result = new AttendanceDAO().checkStaffAttendance(staffdailyattendanceList);
		}
		return result;
	}

	public boolean exportMonthlyDataStaff() {
		boolean result = false;
		
		if(httpSession.getAttribute(CURRENTACADEMICYEAR).toString()!=null){
			
		Date monthOf = DateUtil.dateParserUpdateStd(request.getParameter("monthof"));
		
		Calendar cStart = Calendar.getInstance();
		cStart.setTime(monthOf);
		cStart.set(Calendar.DAY_OF_MONTH, cStart.getActualMinimum(Calendar.DAY_OF_MONTH));
		monthOf = cStart.getTime();
		Timestamp TimestampFrom = new Timestamp(monthOf.getTime());
		
		cStart.set(Calendar.DAY_OF_MONTH, cStart.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date lastDayOfMonth = cStart.getTime();
		Timestamp Timestampto = new Timestamp(lastDayOfMonth.getTime());
		
		List<Teacher> staffList = new EmployeeDAO().readListOfObjects(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		
		Map<String,List<Staffdailyattendance>> staffsAttendance = new AttendanceDAO().readListOfStaffAttendanceExport(httpSession.getAttribute(CURRENTACADEMICYEAR).toString(), TimestampFrom, Timestampto,staffList, Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		
		try {
			result = exportDataToExcelStaff(staffsAttendance,monthOf);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return result;
	}

	private boolean exportDataToExcelStaff(Map<String, List<Staffdailyattendance>> staffsAttendance,Date monthOf) {

		boolean writeSucees = false;
		Calendar cStart = Calendar.getInstance();
		cStart.setTime(monthOf);
		int numberOfDays = cStart.getActualMaximum(Calendar.DAY_OF_MONTH);
		String monthName = new SimpleDateFormat("MMMM").format(monthOf);
		
		try {

			// Creating an excel file
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet(monthName);
			Map<String, Object[]> data = new HashMap<String, Object[]>();
			
			Row headerRow = sheet.createRow(0);
			headerRow.createCell(0).setCellValue("Staff Name");
			sheet.autoSizeColumn(0);
			for(int j=1;j<=numberOfDays;j++){
				headerRow.createCell(j).setCellValue(j);
			}
			
			headerRow.createCell(numberOfDays+2).setCellValue("Total Days Present");
			sheet.autoSizeColumn(numberOfDays+2);
			headerRow.createCell(numberOfDays+3).setCellValue("Total Days Absent");
			sheet.autoSizeColumn(numberOfDays+3);
			headerRow.createCell(numberOfDays+4).setCellValue("Total Days Leave");
			sheet.autoSizeColumn(numberOfDays+4);
			int rownum = 1;
			
			for (Entry<String, List<Staffdailyattendance>> entry : staffsAttendance.entrySet())
			{
			    Row row = sheet.createRow(rownum++);
			    row.createCell(0).setCellValue(entry.getKey());
			    	int i=1;
			    	int totalDaysPresent = 0;
			    	int totalDaysAbsent = 0;
			    	int totalLeaves = 0;
			    for (Staffdailyattendance staffdailyattendance : entry.getValue()) {
			    	if("P".equalsIgnoreCase(staffdailyattendance.getAttendancestatus()) || "L".equalsIgnoreCase(staffdailyattendance.getAttendancestatus())
			    			|| "H".equalsIgnoreCase(staffdailyattendance.getAttendancestatus())){
			    		totalDaysPresent++;
			    	}
			    	if("A".equalsIgnoreCase(staffdailyattendance.getAttendancestatus())){
			    		totalDaysAbsent++;
			    	}
			    	if("L".equalsIgnoreCase(staffdailyattendance.getAttendancestatus())){
			    		totalLeaves++;
			    	}
			    	row.createCell(i).setCellValue(staffdailyattendance.getAttendancestatus());
			    	sheet.autoSizeColumn(i);
			    	i++;
			    	
				}
			    
			    row.createCell(++i).setCellValue(totalDaysPresent);
			    row.createCell(++i).setCellValue(totalDaysAbsent);
			    row.createCell(++i).setCellValue(totalLeaves);
			}
			
			int rownumTwo = 2;
			
			for (Entry<String, List<Staffdailyattendance>> entry : staffsAttendance.entrySet())
			{
			    Row row = sheet.createRow(rownumTwo++);
			    row.createCell(0).setCellValue("");
			    	int i=1;
			    for (Staffdailyattendance staffdailyattendance : entry.getValue()) {
			    	row.createCell(i).setCellValue(staffdailyattendance.getIntime()+"/"+staffdailyattendance.getOuttime());
			    	sheet.autoSizeColumn(i);
			    	i++;
			    	
				}
			}
				
				FileOutputStream out = new FileOutputStream(new File(System.getProperty("java.io.tmpdir")+"staffsmonthlyattendance.xlsx"));
				workbook.write(out);
				out.close();
				writeSucees = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return writeSucees;
		// getFile(name, path);
	}

	public boolean downloadFileStaff() {
		

		boolean result = false;
		try {

			File downloadFile = new File(System.getProperty("java.io.tmpdir")+"staffsmonthlyattendance.xlsx");
	        FileInputStream inStream = new FileInputStream(downloadFile);

	        // get MIME type of the file
			String mimeType = "application/vnd.ms-excel";

			// set content attributes for the response
			response.setContentType(mimeType);
			// response.setContentLength((int) bis.length());

			// set headers for the response
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"",
					"staffsmonthlyattendance.xlsx");
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

	public void markDailyAttendanceJobStaff() {
		
		Date todaysDate = new Date();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			todaysDate = sdf.parse(sdf.format(todaysDate)) ;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Teacher> staffList = new EmployeeDAO().readListOfObjects();
		Currentacademicyear currentAcademicYear = new YearDAO().showYear();
		List<Staffdailyattendance> listStaffAttendance = new ArrayList<Staffdailyattendance>();
		
		for (Teacher teacher : staffList) {
			List<Attendancemaster> staffAttendanceMaster = new AttendanceDAO().getAttendanceMasterDetails(Integer.toString((teacher.getTid())));
			String[] weeklyOffString = staffAttendanceMaster.get(0).getWeeklyoff().split(",");
			List<Integer> staffWeeklyOffList = new ArrayList<Integer>();
			boolean staffWeeklyOff = false;
			boolean staffHoliday = false;
			for (String weekOffS : weeklyOffString) {
				staffWeeklyOffList.add(Integer.parseInt(weekOffS));
			}
			List<Weeklyoff> staffWeekOff = new AttendanceDAO().readListOfWeeklyOff(staffWeeklyOffList, currentAcademicYear.getCurrentacademicyear());
			String today = new SimpleDateFormat("EEEE").format(todaysDate);
			for (Weeklyoff weeklyoff : staffWeekOff) {
				if(weeklyoff.getWeeklyoffday().equalsIgnoreCase(new SimpleDateFormat("EEEE").format(todaysDate))){
					staffWeeklyOff = true;
				}
			}
			
			if(!staffWeeklyOff){
				String[] holidayString = staffAttendanceMaster.get(0).getHolidayname().split(",");
				List<Integer> staffHolidayList = new ArrayList<Integer>();
				for (String singleHoliday : holidayString) {
					staffHolidayList.add(Integer.parseInt(singleHoliday));
				}
				List<Holidaysmaster> staffHolidays = new AttendanceDAO().readListOfholidays(staffHolidayList, currentAcademicYear.getCurrentacademicyear());
				for (Holidaysmaster holidaysmaster : staffHolidays) {
					Date fromDate = holidaysmaster.getFromdate();
					Date toDate = holidaysmaster.getTodate();
					if(fromDate.compareTo(todaysDate) * todaysDate.compareTo(toDate) >= 0){
						staffHoliday = true;
					}
					
				}
				}
			
			if(staffWeeklyOff || staffHoliday){
				Staffdailyattendance staffDailyAttendance = new Staffdailyattendance();
				staffDailyAttendance.setAttendeeid(teacher.getTeacherexternalid());
				staffDailyAttendance.setDate(new Date());
				staffDailyAttendance.setAttendancestatus("H");
				staffDailyAttendance.setAcademicyear(currentAcademicYear.getCurrentacademicyear());
				staffDailyAttendance.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
				listStaffAttendance.add(staffDailyAttendance);
		}
		
		}
		if(!listStaffAttendance.isEmpty()){
			new AttendanceDAO().markDailyAttendanceJobStaff(listStaffAttendance);
		}
		
}

}
