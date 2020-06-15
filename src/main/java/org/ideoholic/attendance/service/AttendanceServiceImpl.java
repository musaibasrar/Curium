package org.ideoholic.attendance.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
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
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.model.attendance.dao.AttendanceDAO;
import com.model.attendance.dto.Attendancemaster;
import com.model.attendance.dto.Holidaysmaster;
import com.model.attendance.dto.Staffdailyattendance;
import com.model.attendance.dto.Studentdailyattendance;
import com.model.attendance.dto.Weeklyoff;
import com.model.employee.dao.EmployeeDAO;
import com.model.employee.dto.Teacher;
import com.model.parents.dto.Parents;
import com.model.sendsms.service.SmsService;
import com.model.student.dao.studentDetailsDAO;
import com.model.student.dto.Student;
import com.util.DataUtil;
import com.util.DateUtil;

public class AttendanceServiceImpl implements AttendanceService {
	
	 private static final Logger logger = LogManager.getLogger(AttendanceService.class);
	
	public String viewAllHolidays(String currentAcademicYear, String branchId) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		//remove it after testing
		//httpSession.setAttribute("currentAcademicYear", "2017/18");
		if(currentAcademicYear!=null){
		List<Holidaysmaster> list = new AttendanceDAO().readListOfHolidays(currentAcademicYear.toString(), Integer.parseInt(branchId.toString()));
		sb.append("holidaysList").append(list);   
		}
		sb.append("}");
		return sb.toString();
	}
	
	public String addHolidays(String[] fromDate,String[] toDate,String[] holidayName,String currentAcademicYear,String branchId) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");

		Holidaysmaster holidayMaster = new Holidaysmaster();
		boolean result = false;

		if(fromDate!=null && toDate!=null && holidayName!=null && currentAcademicYear!=null){
			try{
		for (int i = 0; i < fromDate.length; i++) {
			
			holidayMaster.setFromdate(DateUtil.datePars(fromDate[i]));
			holidayMaster.setTodate(DateUtil.datePars(toDate[i]));
			holidayMaster.setHolidayname(holidayName[i]);
			holidayMaster.setAcademicyear(currentAcademicYear.toString());
			holidayMaster.setBranchid(Integer.parseInt(branchId.toString()));
			result = new AttendanceDAO().saveHolidays(holidayMaster);
		}
			}catch(Exception e){
			e.printStackTrace();	
			}
		}
		sb.append("}");
		return sb.toString();
	}

	public String addWeekOff(String[] weekOff, String currentAcademicYear, String branchId) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		Weeklyoff weeklyOff = new Weeklyoff();
		if(weekOff!=null){
		for(int i=0; i<weekOff.length;i++){
			weeklyOff.setWeeklyoffday(weekOff[i]);
			weeklyOff.setAcademicyear(currentAcademicYear.toString());
			weeklyOff.setBranchid(Integer.parseInt(branchId.toString()));
			boolean saveWeeklyOff= new AttendanceDAO().saveWeeklyOff(weeklyOff);
		}
		}
		sb.append("}");
		return sb.toString();
	}
	
	public String deleteMultiple(String ids[]) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		if(ids!=null){
		List<Integer> holidayIds = new ArrayList<Integer>();
		for (String id : ids) {
			holidayIds.add(Integer.valueOf(id));
		}
		boolean deleteholidayIds= new AttendanceDAO().deleteMultiple(holidayIds);	
		}
		sb.append("}");
		return sb.toString();
	}
	
	@SuppressWarnings("null")
	public String addStudentAttendanceMaster(String[] weeklyOff,String[] holidays, String branchId,String cutOff) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");

		Attendancemaster attendanceMaster = new Attendancemaster();
		
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
		attendanceMaster.setWeeklyoff(sbWeek.toString());
		attendanceMaster.setHolidayname(sbHoliday.toString());
		attendanceMaster.setBranchid(Integer.parseInt(branchId.toString()));
		boolean addAttendanceMaster= new AttendanceDAO().addAttendanceMaster(attendanceMaster);
		sb.append("}");
		return sb.toString();
		
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
	
public String addStaffAttendanceMaster(String[] staffId,String[] weeklyOff,String[] holidays,String inTime, String outTime,
		String branchId) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
		
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
			attendanceMaster.setWeeklyoff(sbWeek.toString());
			attendanceMaster.setHolidayname(sbHoliday.toString());
			attendanceMaster.setBranchid(Integer.parseInt(branchId.toString()));
			attendanceMasterList.add(attendanceMaster);
		}
		boolean addAttendanceMasterList= new AttendanceDAO().addAttendanceMaster(attendanceMasterList);
		sb.append("}");
		return sb.toString();
	}

public String uploadAttendanceFile(String currentAcademicYear, String branchId) throws IOException {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	
	boolean result = false;
	if(currentAcademicYear!=null){
	
	Date todaysDate = new Date();
	List<String> staffExternalId = new EmployeeDAO().getEmployeeExternalId();
	List<Attendancemaster> studentAttendanceMaster = new AttendanceDAO().getAttendanceMasterDetails("00011", Integer.parseInt(branchId.toString()));
	String[] weeklyOffString = studentAttendanceMaster.get(0).getWeeklyoff().split(",");
	List<Integer> studentWeeklyOffList = new ArrayList<Integer>();
	boolean studentWeeklyOff = false;
	boolean studentHoliday = false;
	for (String weekOffS : weeklyOffString) {
		studentWeeklyOffList.add(Integer.parseInt(weekOffS));
	}
	List<Weeklyoff> studentWeekOff = new AttendanceDAO().readListOfWeeklyOff(studentWeeklyOffList, currentAcademicYear.toString(), Integer.parseInt(branchId.toString()));
	
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
	List<Holidaysmaster> studentHolidays = new AttendanceDAO().readListOfholidays(studentHolidayList, currentAcademicYear.toString(), Integer.parseInt(branchId.toString()));
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
		//TODO
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
	       		      		boolean cutOffFlag = checkTimings(formatter.formatCellValue(row.getCell(1)),studentAttendanceMaster.get(0).getIntime()) != null;
	       		      		
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
		       		              	 studentAttendance.setAcademicyear(currentAcademicYear.toString());
		       		              	 studentAttendance.setDate(new Date());
		       		              	 studentAttendance.setBranchid(Integer.parseInt(branchId.toString()));
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
		       		              	 	staffAttendance.setAcademicyear(currentAcademicYear.toString());
		       		              		staffAttendance.setDate(new Date());
		       		              		staffAttendance.setBranchid(Integer.parseInt(branchId.toString()));
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
	sb.append("}");
	return sb.toString();
}

public String searchStudentAttendanceDetails(String currentAcademicYear, String branchId,String studentName,
		String addClass,String addSec, String dateOfAttendance) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	boolean result = false;
	if(currentAcademicYear!=null){
		
		String queryMain = "From Student as student where";
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

		if (!studentName.equalsIgnoreCase("")) {
			querySub = " student.name like '%" + studentName + "%'";
		}

		if (!classStudying.equalsIgnoreCase("")) {
			querySub = " student.classstudying like '" + classStudying
					+ "' OR student.classstudying = '" + conClassStudyingEquals
					+ "'  AND student.archive=0 and student.passedout=0 AND student.droppedout=0 and student.leftout=0  AND student.branchid="+Integer.parseInt(branchId.toString());
		} else if (classStudying.equalsIgnoreCase("") && !querySub.equalsIgnoreCase("")) {
			querySub = querySub + " AND student.archive=0 and student.passedout=0 AND student.droppedout=0 and student.leftout=0 AND student.branchid="+Integer.parseInt(branchId.toString());
		}

		queryMain = queryMain + querySub;
		List<Student> searchStudentList = new studentDetailsDAO().getListStudents(queryMain);
		
		List<Student> newStudentList = new ArrayList<Student>();
		List<Studentdailyattendance> newStudentDailyAttendance = new ArrayList<Studentdailyattendance>();
		
		Date searchdate = DateUtil.dateParserUpdateStd(dateOfAttendance);
		Timestamp timestamp = new Timestamp(searchdate.getTime());
		for (Student student : searchStudentList) {

			List<Studentdailyattendance> studentsAttendance = new AttendanceDAO().readListOfStudentAttendance(currentAcademicYear.toString(), timestamp,student.getStudentexternalid(), Integer.parseInt(branchId.toString()));
			for (Studentdailyattendance studentDailyAttendance : studentsAttendance) {
					newStudentList.add(student);
					newStudentDailyAttendance.add(studentDailyAttendance);
				
			}
		}

		sb.append("StudentListAttendance").append(newStudentList);
		sb.append("StudentDailyAttendanceDate").append(newStudentDailyAttendance);
		sb.append("searchedDate").append(DateUtil.dateParserUpdateStd(dateOfAttendance));
		
	        result = true;
		}
	List<Student> studentList = new studentDetailsDAO().readListOfObjectsForIcon(Integer.parseInt(branchId.toString()));
	sb.append("studentList").append(studentList);
	sb.append("}");
	return sb.toString();
}

private String checkTimings(String inTime, String cutOffTime) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	try {

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa");
        Date inTime1 = sdf.parse(inTime);
        Date cutOff = sdf.parse(cutOffTime);
        
        if(inTime1.after(cutOff)){
        	return sb.toString();
        }
	} catch (Exception e) {
		logger.info("checktimings "+e);
	}
	sb.append("}");
return sb.toString();
}

public String viewAttendance(String branchId) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	List<Student> studentList = new studentDetailsDAO().readListOfObjectsForIcon(Integer.parseInt(branchId.toString()));
	sb.append("studentList").append(studentList);
		if(!studentList.isEmpty()){
			return sb.toString();
		}
		sb.append("}");
		return sb.toString();
}

public String updateStudentAttendanceDetails(String currentAcademicYear,String[] attendanceIds,String[] studentAttendanceStatus) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	
	if(currentAcademicYear.toString()!=null){
		List<Integer> attendanceIdsList = new ArrayList<Integer>();
		List<String> studentAttendanceStatusList = new ArrayList<String>();
		for (String attid : attendanceIds) {
			String[] attidString = attid.split(",");
			if(attidString[0]!=null && attidString[1]!=null){
				attendanceIdsList.add(Integer.parseInt(attidString[0]));
				studentAttendanceStatusList.add(studentAttendanceStatus[Integer.parseInt(attidString[1])]);	
			}
			
		}
		boolean updateAttendceDetail=new AttendanceDAO().updateStudentAttendanceDetails(attendanceIdsList,studentAttendanceStatusList,currentAcademicYear.toString());
	}
	sb.append("}");
	return sb.toString();
}

public String markStudentsAttendance(String currentAcademicYear, String branchId,String[] attendanceIds,
		String[] studentAttendanceStatus,String attendanceClass) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	boolean result = false;
	
	if(currentAcademicYear.toString()!=null){
					
		if(attendanceIds!=null) {
		
		List<Studentdailyattendance> studentDailyAttendanceList = new ArrayList<Studentdailyattendance>();
		
		for(int i=0;i<attendanceIds.length;i++) {
			Studentdailyattendance studentDailyAttendance = new Studentdailyattendance();
			
			String[] attidString = attendanceIds[i].split(",");
			studentDailyAttendance.setAttendeeid(attidString[0]);
			studentDailyAttendance.setAttendancestatus(studentAttendanceStatus[i]);
			studentDailyAttendance.setIntime("00:00");
			studentDailyAttendance.setDate(new Date());
			studentDailyAttendance.setAcademicyear(currentAcademicYear.toString());
			studentDailyAttendance.setBranchid(Integer.parseInt(branchId.toString()));
			studentDailyAttendanceList.add(studentDailyAttendance);
		}
				
		String res = new AttendanceDAO().checkAndMarkStudentAttendance(studentDailyAttendanceList); 
		sb.append("attedanceresult").append(res);		
			if(res!=null) {
				result = true;
			}
				if(res!=null && res.contains("success")) {
					sendSMSAbsentees(studentDailyAttendanceList, attendanceClass);
				}
		}
	}
	sb.append("}");
	return sb.toString();
}

public String sendSMSAbsentees(List<Studentdailyattendance> studentDailyAttendanceList, String attendanceClass) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	
	Properties properties = new Properties();
    InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("Backuplocation.properties");
    
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
    
    sb.append("}");
}

public String exportMonthlyData(String currentAcademicYear,String addClass,String addSec, String branchId, Date monthOf) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	
	boolean result = false;
	
	if(currentAcademicYear.toString()!=null){
	
	String queryMain = "From Student as student where";
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
				+ "'  AND student.archive=0 and student.passedout=0 AND student.droppedout=0 and student.leftout=0  AND student.branchid="+Integer.parseInt(branchId.toString());
	} else if (classStudying.equalsIgnoreCase("") && !querySub.equalsIgnoreCase("")) {
		querySub = querySub + " AND student.archive=0 and student.passedout=0 AND student.droppedout=0 and student.leftout=0 AND student.branchid="+Integer.parseInt(branchId.toString());
	}
	queryMain = queryMain + querySub;
	List<Student> searchStudentList = new studentDetailsDAO().getListStudents(queryMain);
		 
	Calendar cStart = Calendar.getInstance();
	cStart.setTime(monthOf);
	cStart.set(Calendar.DAY_OF_MONTH, cStart.getActualMinimum(Calendar.DAY_OF_MONTH));
	monthOf = cStart.getTime();
	Timestamp TimestampFrom = new Timestamp(monthOf.getTime());
	
	cStart.set(Calendar.DAY_OF_MONTH, cStart.getActualMaximum(Calendar.DAY_OF_MONTH));
	Date lastDayOfMonth = cStart.getTime();
	Timestamp Timestampto = new Timestamp(lastDayOfMonth.getTime());
	
	Map<String,List<Studentdailyattendance>> studentsAttendance = new AttendanceDAO().readListOfStudentAttendanceExport(currentAcademicYear.toString(), TimestampFrom, Timestampto,searchStudentList, Integer.parseInt(branchId.toString()));
	
	try {
		result = exportDataToExcel(studentsAttendance,monthOf) != null;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	sb.append("}");
	return sb.toString();
}

public String exportDataToExcel(Map<String, List<Studentdailyattendance>> studentDailyAttendance,Date monthOf)
		throws Exception {
	StringBuffer sb = new StringBuffer();
	sb.append("{");

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
	sb.append("}");
	return sb.toString();
	// getFile(name, path);
}
public String downloadFile(byte[] buffer) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	

	boolean result = false;
	try {

		File downloadFile = new File(System.getProperty("java.io.tmpdir")+"/studentsmonthlyattendance.xlsx");
        FileInputStream inStream = new FileInputStream(downloadFile);

        // get MIME type of the file
		String mimeType = "application/vnd.ms-excel";

		//TODO
		// set content attributes for the response
		response.setContentType(mimeType);
		// response.setContentLength((int) bis.length());

		// set headers for the response
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"",
				"studentsmonthlyattendance.xlsx");
		//TODO
		response.setHeader(headerKey, headerValue);

		// get output stream of the response
		//TODO
		OutputStream outStream = response.getOutputStream();

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
	sb.append("}");
	return sb.toString();
	
	}

public String viewAttendanceStaff(String branchId) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	if(branchId!=null){
		List<Teacher> staffList = new EmployeeDAO().readListOfObjects(Integer.parseInt(branchId.toString()));
		sb.append("staffList").append(staffList);
			if(!staffList.isEmpty()){
				return sb.toString();
			}
	}
	sb.append("}");
	
		return sb.toString();
}

public String searchStaffAttendanceDetails(String currentAcademicYear, String branchId, String dateOfAttendance) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	
	boolean result = false;
	
	if(currentAcademicYear!=null){
	List<Teacher> searchStaffList = new EmployeeDAO().readListOfObjects(Integer.parseInt(branchId.toString()));
	
	List<Teacher> newStaffList = new ArrayList<Teacher>();
	List<Staffdailyattendance> newStaffDailyAttendance = new ArrayList<Staffdailyattendance>();
	
	Date searchdate = DateUtil.dateParserUpdateStd(dateOfAttendance);
	Timestamp timestamp = new Timestamp(searchdate.getTime());
	for (Teacher teacher : searchStaffList) {

		List<Staffdailyattendance> staffAttendance = new AttendanceDAO().readListOfStaffAttendance(currentAcademicYear.toString(), timestamp,teacher.getTeacherexternalid(), Integer.parseInt(branchId.toString()));
		for (Staffdailyattendance staffDailyAttendance : staffAttendance) {
				newStaffList.add(teacher);
				newStaffDailyAttendance.add(staffDailyAttendance);
			
		}
	}

	sb.append("StaffListAttendance").append(newStaffList);
	sb.append("StaffDailyAttendanceDate").append(newStaffDailyAttendance);
	sb.append("searchedDate").append(DateUtil.dateParserUpdateStd(dateOfAttendance));
	
		List<Teacher> staffDetailsList = new EmployeeDAO().readListOfObjects(Integer.parseInt(branchId.toString()));
		sb.append("staffList").append(staffDetailsList);
		result = true;
	}
	sb.append("}");
	return sb.toString();
}

public String updateStaffAttendanceDetails(String currentAcademicYear,String[] attendanceIds,String[] studentAttendanceStatus) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	
	if(currentAcademicYear.toString()!=null){
		List<Integer> attendanceIdsList = new ArrayList<Integer>();
		List<String> staffAttendanceStatusList = new ArrayList<String>();
		for (String attid : attendanceIds) {
			String[] attidString = attid.split(",");
			if(attidString[0]!=null && attidString[1]!=null){
				attendanceIdsList.add(Integer.parseInt(attidString[0]));
				staffAttendanceStatusList.add(studentAttendanceStatus[Integer.parseInt(attidString[1])]);	
			}
			
		}
		boolean updateStaffDetails= new AttendanceDAO().updateStaffAttendanceDetails(attendanceIdsList,staffAttendanceStatusList);
	}
	sb.append("}");
	return sb.toString();
}

public String viewStaffAttendanceDetailsMonthly(String currentAcademicYear, String branchId,String staffExternalId,
		String fromdateofattendance,String todateofattendance,String nameofstaff) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	
	boolean result = false;
	if(currentAcademicYear.toString()!=null){
		String staffExternalId1 = DataUtil.emptyString(staffExternalId);
		Date fromDate = DateUtil.dateParserUpdateStd(fromdateofattendance);
		Date toDate = DateUtil.dateParserUpdateStd(todateofattendance);
		Timestamp fromTimestamp = new Timestamp(fromDate.getTime());
		Timestamp toTimestamp = new Timestamp(toDate.getTime());
		
		List<Staffdailyattendance> staffDailyAttendance = new ArrayList<Staffdailyattendance>();
		staffDailyAttendance = new AttendanceDAO().getStaffDailyAttendance(staffExternalId1, fromTimestamp, toTimestamp, currentAcademicYear.toString(), Integer.parseInt(branchId.toString()));
		sb.append("staffDailyAttendance").append(staffDailyAttendance);
		sb.append("staffname").append(nameofstaff);
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
		
		sb.append("totalpresent").append(totalPresent);
		sb.append("totalabsent").append(absentDays);
		result = true;
	}
	
	List<Teacher> staffList = new EmployeeDAO().readListOfObjects(Integer.parseInt(branchId.toString()));
	sb.append("staffList").append(staffList);
	
	sb.append("}");
	return sb.toString();

}

public String markStaffAttendance(String currentAcademicYear, String branchId,String[] attendanceIds,String[] staffAttendanceStatus,
		String[] inTime,String[] outTime) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	boolean result = false;
	if(currentAcademicYear.toString()!=null){
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
			staffDailyAttendance.setAcademicyear(currentAcademicYear.toString());
			staffDailyAttendance.setBranchid(Integer.parseInt(branchId.toString()));
			staffdailyattendanceList.add(staffDailyAttendance);
		}
		result = new AttendanceDAO().checkStaffAttendance(staffdailyattendanceList);
	}
	sb.append("}");
	return sb.toString();
}

public String exportMonthlyDataStaff(String currentAcademicYear, String branchId,Date monthOf) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	boolean result = false;
	
	if(currentAcademicYear.toString()!=null){
			
	Calendar cStart = Calendar.getInstance();
	cStart.setTime(monthOf);
	cStart.set(Calendar.DAY_OF_MONTH, cStart.getActualMinimum(Calendar.DAY_OF_MONTH));
	monthOf = cStart.getTime();
	Timestamp TimestampFrom = new Timestamp(monthOf.getTime());
	
	cStart.set(Calendar.DAY_OF_MONTH, cStart.getActualMaximum(Calendar.DAY_OF_MONTH));
	Date lastDayOfMonth = cStart.getTime();
	Timestamp Timestampto = new Timestamp(lastDayOfMonth.getTime());
	
	List<Teacher> staffList = new EmployeeDAO().readListOfObjects(Integer.parseInt(branchId.toString()));
	
	Map<String,List<Staffdailyattendance>> staffsAttendance = new AttendanceDAO().readListOfStaffAttendanceExport(currentAcademicYear.toString(), TimestampFrom, Timestampto,staffList, Integer.parseInt(branchId.toString()));
	
	try {
		result = exportDataToExcelStaff(staffsAttendance,monthOf);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	sb.append("}");
	return sb.toString();
}

private boolean exportDataToExcelStaff(Map<String, List<Staffdailyattendance>> staffsAttendance,Date monthOf) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");

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
	sb.append("}");
	return writeSucees;
	// getFile(name, path);
}
}
