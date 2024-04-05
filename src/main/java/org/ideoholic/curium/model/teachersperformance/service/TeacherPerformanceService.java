package org.ideoholic.curium.model.teachersperformance.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.examdetails.dao.ExamDetailsDAO;
import org.ideoholic.curium.model.examdetails.dto.Exams;
import org.ideoholic.curium.model.marksdetails.dao.MarksDetailsDAO;
import org.ideoholic.curium.model.marksdetails.dto.Marks;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.student.dao.studentDetailsDAO;
import org.ideoholic.curium.model.subjectdetails.dao.SubjectDetailsDAO;
import org.ideoholic.curium.model.subjectdetails.dto.Subject;
import org.ideoholic.curium.util.SubjectAverage;

public class TeacherPerformanceService {
	 private HttpServletRequest request;
     private HttpServletResponse response;
     private HttpSession httpSession;
     private String BRANCHID = "branchid";
     private String academicyear = "academicyear";
     
	public TeacherPerformanceService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
	       this.response = response;
	       this.httpSession = request.getSession();
	}

	public boolean readListOfSubjects() {
		boolean result = false;
	    try {
	    	List<Subject> list = new SubjectDetailsDAO().readAllSubjects(Integer.parseInt(httpSession.getAttribute("branchid").toString()));
	        httpSession.setAttribute("listSubject", list);

	        result = true;
	    } catch (Exception e) {
	        e.printStackTrace();
	        result = false;
	    }
		return result;
	}

	
	public void getDetailofteacher() {
		
		String[] classsec = request.getParameterValues("classesselected");
		String subjectDetails = request.getParameter("subject");
		String[] subject = subjectDetails.split("--");
		String AcademicYear = request.getParameter("academicyear");
		List<Parents> searchStudentList = new ArrayList<Parents>();
		List<Exams> examsList = new ExamDetailsDAO().readListOfExams(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		List<SubjectAverage> subjectaverageList = new ArrayList<SubjectAverage>();
		
		
		for (Exams exams : examsList) {
			
			SubjectAverage subjectAverage = new SubjectAverage();
			List<String> classsection = new LinkedList<String>();
			List<Integer> averageMarksScored = new LinkedList<Integer>();
			subjectAverage.setExamName("\""+exams.getExamname()+"\"");
			
			for (String classOne : classsec) {
				String queryMain = "From Parents as parents where";
				String querySub = "";

					querySub = querySub + " parents.Student.classstudying = '"
							+ classOne + "' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.branchid="+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())+" order by parents.Student.admissionnumber ASC";

				if(!"".equalsIgnoreCase(querySub)) {
					queryMain = queryMain + querySub;
					searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
				}
				
				//get Student Marks of classone
				List<Integer> studentIds = new ArrayList<Integer>();
				for (Parents student : searchStudentList) {
					studentIds.add(student.getStudent().getSid());
				}
				List<Marks> marksList = new  MarksDetailsDAO().readListOfMarksPerSubject(studentIds,Integer.parseInt(subject[0]),exams.getExid());
				int averageMarks = 0;
				int sum = 0;
				int i = marksList.size();
				for (Marks marks : marksList) {
					
				sum= sum + marks.getMarksobtained();
				}
				if(i>0) {
				averageMarks =(sum/i);	
				classsection.add("\""+classOne+"\"");
				averageMarksScored.add(averageMarks);
				}
				//End Student Marks
				
			}
			subjectAverage.setClasssec(classsection);
			subjectAverage.setAverageMarks(averageMarksScored);
			if(!averageMarksScored.isEmpty()) {
				subjectaverageList.add(subjectAverage);
	
			}
					}
		
		request.setAttribute("subjectaveragelist", subjectaverageList);
		request.setAttribute("subjectaveragelistsize", subjectaverageList.size());
		request.setAttribute("subjectName", subject[1]);
	
	}

	/*public void getDetailofteacher() {
		//academicyear
		Subject subject;
		Classsec[] classsec;
		Map<classsec, listofsids> subjectaverage=new HashMap<String, List<Integers>>();
		subjectaverage = exam,map<classsec,averagemarks>
		
		List<subjectaverage> subjectaverageList ;
		
				foreach(list of classes) {
			//1. list of sid based on classsec
			//2. insert into MapClasssecSid
		}
		
		
		foreach(list of exams)
		{
			new subjectaverage;
			insideloopmapclassaverage = new map<classsec,averagemarks>;
			for(MapClasssecSid
					{
						classsec
						listofstudentssid
						//query in marks table where sid = listofstudentssid and academicyear = and sub
						
						calculate average
						
						insideloopmapclassaverage.put(classsec, average);
					}
			subjectaverage.set(examname);
			subjectaverage.set(insideloopmapclassaverage);
			
			subjectaverageList.add(new subjectaverage);

		
	}
	}*/
}
