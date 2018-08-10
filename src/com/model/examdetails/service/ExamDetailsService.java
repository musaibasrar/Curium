/**
 * 
 */
package com.model.examdetails.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.adminexpenses.dao.AdminDetailsDAO;
import com.model.examdetails.action.ExamDetailsAction;
import com.model.examdetails.dao.ExamDetailsDAO;
import com.model.examdetails.dto.Exams;
import com.model.examdetails.dto.Examschedule;
import com.model.parents.dto.Parents;
import com.model.student.dao.studentDetailsDAO;
import com.model.student.dto.Student;
import com.util.DataUtil;
import com.util.DateUtil;

/**
 * @author Musaib_2
 *
 */
public class ExamDetailsService {

	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession httpSession;
	private String BRANCHID = "branchid";
	
	public ExamDetailsService(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}


	public Boolean addExam() {
		// TODO Auto-generated method stub
		Exams exams = new Exams();
		boolean result = true;
		
		if(httpSession.getAttribute(BRANCHID)!=null){

		exams.setExamname(DataUtil.emptyString(request.getParameter("examname")));
		exams.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		exams = new ExamDetailsDAO().addExams(exams);

		}
		if(exams == null){
			result=false;
		}
		return result;
	}


	public boolean readListOfExams() {
		
		boolean result = true;
		
		if(httpSession.getAttribute(BRANCHID)!=null){
			
			List<Exams> exams = new ExamDetailsDAO().readListOfExams(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			 httpSession.setAttribute("examdetails", exams);
			 if(exams == null){
					result=false;
				}
		}
		
		
		return result;
	}


	public boolean deleteMultiple() {
		String[] examIds = request.getParameterValues("examIDs");
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
		 return result;
	}


	public boolean addSchedule() {
		
		boolean result = false;
		List<Examschedule> examScheduleList = new ArrayList<Examschedule>();
		
		String[] subject = request.getParameterValues("subject");
		String[] date = request.getParameterValues("fromdate");
		String[] startTime = request.getParameterValues("starttime");
		String[] endTime = request.getParameterValues("endtime");
		String[] centerCodes = request.getParameterValues("centercodes");
		
		if(httpSession.getAttribute(BRANCHID)!=null){
			
			for (int i=0; i<subject.length;i++) {
				Examschedule examschedule = new Examschedule();
				examschedule.setAcademicyear(DataUtil.emptyString(request.getParameter("academicyear")));
				String centerCodeList = "";
				for (String string : centerCodes) {
                                    centerCodeList = centerCodeList+","+string;
                                }
				examschedule.setCentercode(centerCodeList);
				examschedule.setExamname(DataUtil.emptyString(request.getParameter("examlevel")));
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
				examschedule.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
				examScheduleList.add(examschedule);
			}
			result = new ExamDetailsDAO().addExamSchedule(examScheduleList);
		}
		
		return result;
	}


	public boolean getExamSchedule() {
		
		boolean result = true;
		
		if(httpSession.getAttribute(BRANCHID)!=null){
			
			List<Examschedule> exams = new ExamDetailsDAO().readListOfExamSchedule();
			 httpSession.setAttribute("examschedule", exams);
			if(exams == null){
				result=false;
			}
		}
		
		return result;
	}


	public boolean deleteExamSchedule() {

		String[] examIds = request.getParameterValues("idexamschedule");
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
		 return result;
	
	}


	public boolean getExamScheduleDetails() {
		
		String academicYear = DataUtil.emptyString(request.getParameter("searchacademicyear"));
		String[] searchExamLevel = DataUtil.emptyString(request.getParameter("searchexamlevel")).split(":");
                String[] searchCenter = DataUtil.emptyString(request.getParameter("searchcentercode")).split(":");
                
		
		request.setAttribute("selectedcentercode", DataUtil.emptyString(request.getParameter("searchcentercode")));
		request.setAttribute("selectedexamlevel", DataUtil.emptyString(request.getParameter("searchexamlevel")));
	
		if(httpSession.getAttribute(BRANCHID)!=null){
			List<Examschedule> examschedules = new ArrayList<Examschedule>();
			examschedules = new ExamDetailsDAO().getExamScheduleDetails(academicYear, searchCenter[0], searchExamLevel[0], Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			request.setAttribute("examschedules", examschedules);
			if(!examschedules.isEmpty()){
				return true;
			}
		}
		
		return false;
	}


	public void printPreviewHallTicket() {
		
		String[] examLevels = request.getParameterValues("examlevels");
		String[] centerCodes = request.getParameterValues("centercodes");
		String[] subject = request.getParameterValues("subject");
		String[] dateOfExam = request.getParameterValues("date");
		String[] startTime = request.getParameterValues("starttime");
		String[] endTime = request.getParameterValues("endtime");

		
		String[] selectedExamLevel = DataUtil.emptyString(request.getParameter("hiddenexamlevel")).toString().split(":");
		String[] selectedCenterCode = DataUtil.emptyString(request.getParameter("hiddencentercode")).toString().split(":");
		
		if(examLevels!=null){
		
		List<Parents> studentList = new ArrayList<Parents>();
		List<Examschedule> examscheduleList = new ArrayList<Examschedule>();
		
			studentList = new studentDetailsDAO().getStudentsList("from Parents as parents where parents.Student.examlevel = '" + 
			        selectedExamLevel[0]+"' and parents.Student.centercode='"+selectedCenterCode[0]+"'");
		             
	                request.setAttribute("studentList", studentList);
	                
	                for (int i = 0; i < endTime.length; i++) {
	                        Examschedule exams = new Examschedule();
	                        //exams.setClasses(classes[i]);
	                        exams.setDate(DateUtil.dateParserUpdateStd(dateOfExam[i]));
	                        exams.setExamname(examLevels[i]);
	                        exams.setSubject(subject[i]);
	                        exams.setStarttime(startTime[i]);
	                        exams.setEndtime(endTime[i]);
	                        examscheduleList.add(exams);
	                }
	                request.setAttribute("examcodename", selectedExamLevel[0]+" / "+selectedExamLevel[1]);
	                request.setAttribute("centercodename", selectedCenterCode[0]+" / "+selectedCenterCode[1]);
	                request.setAttribute("examschedulelist", examscheduleList);
		}
		

	
	}
		
	
}
