package org.ideoholic.examdetails.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ideoholic.examdetails.dto.ExamDetailsParamDto;

import com.model.examdetails.dao.ExamDetailsDAO;
import com.model.examdetails.dto.Exams;
import com.model.examdetails.dto.Examschedule;
import com.model.parents.dto.Parents;
import com.model.student.dao.studentDetailsDAO;
import com.model.student.dto.Student;
import com.util.DataUtil;
import com.util.DateUtil;

public class ExamDetailsServiceImpl implements ExamDetailsService {
	
	private String classStudying;

	public String addExam(String branchId, String examName) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		// TODO Auto-generated method stub
		Exams exams = new Exams();
		boolean result = true;
		
		if(branchId!=null){

		exams.setExamname(DataUtil.emptyString(examName));
		exams.setBranchid(Integer.parseInt(branchId.toString()));
		exams = new ExamDetailsDAO().addExams(exams);

		}
		if(exams == null){
			result=false;
		}
		sb.append("}");
		return sb.toString();
	}
	
public String readListOfExams(String branchId) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
		
		boolean result = true;
		
		if(branchId!=null){
			
			List<Exams> exams = new ExamDetailsDAO().readListOfExams(Integer.parseInt(branchId.toString()));
			sb.append("examdetails").append(exams);
			 if(exams == null){
					result=false;
				}
		}
		
		sb.append("}");
		return sb.toString();
	}

public String deleteMultiple(String[] examIds) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	boolean result = false;
	 if(examIds!=null){
        List ids = new ArrayList();
        for (String id : examIds) {
            System.out.println("id" + id);
            ids.add(Integer.valueOf(id));

        }
        new ExamDetailsDAO().deleteMultiple(ids);
        result = true;
}else{
	result = false;
}
	 sb.append("}");
	 return sb.toString();
}

public String addSchedule(String branchId, String[] subject, String[] date, String[] startTime, String[] endTime,
		String[] classesSelected, String academicYear, String exam) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	
	boolean result = false;
	List<Examschedule> examScheduleList = new ArrayList<Examschedule>();
	
	if(branchId!=null){
		
		for (String selectedClass : classesSelected) {
			
			for (int i=0; i<subject.length;i++) {
				Examschedule examschedule = new Examschedule();
				examschedule.setClasses(selectedClass);
				examschedule.setDate(DateUtil.dateParserUpdateStd(date[i]));
				String[] starttimeSplit = startTime[i].split(":");
				String hours = starttimeSplit[0];
				String meridian = null;
				String outputStartTime = null;
					  if (Integer.parseInt(hours) < 12) {
						  outputStartTime = startTime[i];
					    meridian = "AM";
					  } else if (Integer.parseInt(hours) >= 12) {
						  
						  DateFormat df = new SimpleDateFormat("HH:mm");
					       //Date/time pattern of desired output date
					       DateFormat outputformat = new SimpleDateFormat("hh:mm");
					       Date date1 = null;
					       try{
					          //Conversion of input String to date
					    	  date1= df.parse(startTime[i]);
					          //old date format to new date format
					    	  outputStartTime = outputformat.format(date1);
					    	}catch(ParseException pe){
					    	    pe.printStackTrace();
					    	 }
					    meridian = "PM";
					  }
					  
				examschedule.setStarttime(outputStartTime+" "+meridian);
				String[] endtimeSplit = endTime[i].split(":");
				String endhours = endtimeSplit[0];
				String endmeridian = null;
				String outputEndTime = null;
					  if (Integer.parseInt(endhours) < 12) {
						  endmeridian = "AM";
					  } else if (Integer.parseInt(endhours) >= 12) {

						  DateFormat df = new SimpleDateFormat("HH:mm");
					       //Date/time pattern of desired output date
					       DateFormat outputformat = new SimpleDateFormat("hh:mm");
					       Date date1 = null;
					       
					       try{
					          //Conversion of input String to date
					    	  date1= df.parse(endTime[i]);
					          //old date format to new date format
					    	  outputEndTime = outputformat.format(date1);
					    	}catch(ParseException pe){
					    	    pe.printStackTrace();
					    	 }
					       
						  endmeridian = "PM";
					  }
					  
				examschedule.setEndtime(outputEndTime+" "+endmeridian);
				examschedule.setSubject(DataUtil.emptyString(subject[i]));
				examschedule.setBranchid(Integer.parseInt(branchId.toString()));
				examScheduleList.add(examschedule);
			}
		}
		result = new ExamDetailsDAO().addExamSchedule(examScheduleList);
	}
	sb.append("}");
	return sb.toString();
}


public String deleteExamSchedule(String[] examIds) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");

	boolean result = false;
	 if(examIds!=null){
        List ids = new ArrayList();
        for (String id : examIds) {
            ids.add(Integer.valueOf(id));

        }
        new ExamDetailsDAO().deleteExamSchedule(ids);
        result = true;
}else{
	result = false;
}
	 sb.append("}");
	 return sb.toString();

}

public String printPreviewHallTicket(ExamDetailsParamDto examParamDto) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	
	if(examParamDto.getExamName()!=null){
	
	List<Parents> studentList = new ArrayList<Parents>();
	List<Examschedule> examscheduleList = new ArrayList<Examschedule>();
	classStudying = examParamDto.getClassStudying()+"--" +"%";
	
	if(examParamDto.getAdmNo()==""){
		studentList = new studentDetailsDAO().getStudentsList("from Parents as parents where parents.Student.classstudying LIKE '"+classStudying+"'");
	}else{
		Parents parent = new Parents();
		Student student = new Student();
		student.setAdmissionnumber(examParamDto.getAdmNo());
		student.setName(examParamDto.getStudentName());
		student.setClassstudying(examParamDto.getClassAndSec());
		parent.setStudent(student);
		studentList.add(parent);
	}
	
	sb.append("studentList").append(studentList);
	
	for (int i = 0; i < examParamDto.getEndTime().length; i++) {
		Examschedule exams = new Examschedule();
		exams.setClasses(examParamDto.getClasses()[i]);
		exams.setDate(DateUtil.dateParserUpdateStd(examParamDto.getDateOfExam()[i]));
		exams.setExamname(examParamDto.getExamName()[i]);
		exams.setSubject(examParamDto.getSubject()[i]);
		exams.setStarttime(examParamDto.getStartTime()[i]);
		exams.setEndtime(examParamDto.getEndTime()[i]);
		examscheduleList.add(exams);
	}
	sb.append("examname").append(examParamDto.getExamName()[0]);
	sb.append(",examschedulelist").append(examscheduleList);
	
}
	sb.append("}");
	return sb.toString();
	
}
}
