package org.ideoholic.curium.model.marksdetails.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.ideoholic.curium.model.examdetails.dao.ExamDetailsDAO;
import org.ideoholic.curium.model.examdetails.dto.Exams;
import org.ideoholic.curium.model.marksdetails.dao.MarksDetailsDAO;
import org.ideoholic.curium.model.marksdetails.dto.ExamRank;
import org.ideoholic.curium.model.marksdetails.dto.Marks;
import org.ideoholic.curium.model.marksdetails.dto.MarksGrade;
import org.ideoholic.curium.model.marksdetails.dto.SubjectGrade;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.student.dao.studentDetailsDAO;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.model.subjectdetails.dao.SubjectDetailsDAO;
import org.ideoholic.curium.model.subjectdetails.dto.Subject;
import org.ideoholic.curium.model.subjectdetails.dto.Subjectmaster;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.ExamsDetails;
import org.ideoholic.curium.util.ExamsMarks;
import org.ideoholic.curium.util.FinalTermMarks;
import org.ideoholic.curium.util.MarksSheet;

public class MarksDetailsService {

	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession httpSession;
	private String CURRENTACADEMICYEAR = "currentAcademicYear";
	private String BRANCHID = "branchid";
	private String USERID = "userloginid";
	private static final int BUFFER_SIZE = 4096;
	
	public MarksDetailsService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

	public String addMarks() {

		String result = "false";

		String[] studentIds = request.getParameterValues("studentIDs");
		String[] studentsMarks = request.getParameterValues("studentMarks");
		String[] examidName = request.getParameter("exam").split("__");
		String subject = request.getParameter("subject");
		String classSelected = request.getParameter("classsearch");
		System.out.println("the subject id is " + subject + ", and exam id is " + examidName[0]);
		int sizeOfArray = 0;
		Map<Integer, String> mapOfMarks = new HashMap<Integer, String>();
		List<Integer> ids = new ArrayList<Integer>();
		List<String> studentsMarksList = new ArrayList<String>();

		if (studentsMarks != null) {

			for (String marksList : studentsMarks) {
				
				if(!marksList.equalsIgnoreCase("A")) {
					studentsMarksList.add(marksList);
				}else {
					studentsMarksList.add("999");
				}
				

			}
		}

		if (studentIds != null && subject != null) {

			for (String id : studentIds) {
				System.out.println("id" + id);
				ids.add(Integer.valueOf(id));

			}

			sizeOfArray = ids.size();

			System.out.println("id length" + studentIds.length);

			for (int i = 0; i < sizeOfArray; i++) {
				mapOfMarks.put(ids.get(i), studentsMarksList.get(i));
			}

			Set mapSet = mapOfMarks.entrySet();
			Iterator mapIterator = mapSet.iterator();

			int examid = Integer.parseInt(examidName[0]);
			int subid = Integer.parseInt(subject);
			List<Marks> marksList = new ArrayList<Marks>();
			
			Subject subjectDetails =  new SubjectDetailsDAO().readSubjectByExam(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()),classSelected,examidName[1],subid);
			int minMarks = subjectDetails.getMinmarks();
			int maxMarks = subjectDetails.getMaxmarks();

			while (mapIterator.hasNext()) {
				Map.Entry mapEntry = (Entry) mapIterator.next();

				Marks marks = new Marks();
				marks.setExamid(examid);
				marks.setSubid(subid);
				
				int mymark= Integer.parseInt((String) mapEntry.getValue());
				float subjectPercentage = ((float)mymark / maxMarks) * 100;
				List<SubjectGrade> subjectGradeDetailsList = new MarksDetailsDAO().readSubjectGrade(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()),examid,classSelected);
				
				for (SubjectGrade subjectGrade : subjectGradeDetailsList) {
					if( subjectPercentage >= subjectGrade.getMinmarks() && subjectPercentage <= subjectGrade.getMaxmarks())	
					{
						marks.setSubgrade(subjectGrade.getStatus());
					}
					
				}
				
				marks.setSid((int) mapEntry.getKey());
				marks.setMarksobtained(mymark);
				String currentYear = (String) httpSession.getAttribute(CURRENTACADEMICYEAR);
				marks.setAcademicyear(currentYear);
				marks.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
				marks.setUserid(Integer.parseInt(httpSession.getAttribute("userloginid").toString()));
				marksList.add(marks);
			}

			String output = new MarksDetailsDAO().addMarks(marksList);
			
			if(output=="success"){
				result = "true";
			}else if (output.contains("Duplicate")){
				result = "Duplicate";
				
				
			}
				
			
			/*if (new MarksDetailsDAO().addMarks(marksList)) {
				result = true;
			}*/
		}

		return result;
	}

	public void Search() {

		if(httpSession.getAttribute(BRANCHID)!=null){
			
		String queryMain = "From Parents as parents where";
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
			querySub = " parents.Student.name like '%" + studentname + "%'";
		}

		if (!classStudying.equalsIgnoreCase("")) {
			querySub = " parents.Student.classstudying like '" + classStudying
					+ "' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0";
		} else if (classStudying.equalsIgnoreCase("") && !querySub.equalsIgnoreCase("")) {
			querySub = querySub + " AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.branchid="+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString());
		}

		queryMain = queryMain + querySub;
		/*
		 * queryMain =
		 * "FROM Parents as parents where  parents.Student.dateofbirth = '2006-04-06'"
		 * ;
		 */
		System.out.println("SEARCH QUERY ***** " + queryMain);
		List<Parents> searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
		request.setAttribute("searchStudentList", searchStudentList);

		// get all the subjects
		List<Subject> subjectList = new SubjectDetailsDAO().readListOfSubjectNames(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		request.setAttribute("listSubjectNames", subjectList);

		// get the list for all the midterms
		List<Exams> examList = new ExamDetailsDAO().readListOfExams(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		request.setAttribute("listExam", examList);
		
		//set class
		request.setAttribute("classselected", addClass);
		}

	}
//code for searchid
/*	public void Searchid() {

		if(httpSession.getAttribute(BRANCHID)!=null){
			
		String queryMain = "From Parents as parents where";
		String studentname = request.getParameter("username");

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
			querySub = " parents.Student.name  '%" + studentname + "%'";
		}

		if (!classStudying.equalsIgnoreCase("")) {
			querySub = " parents.Student.classstudying like '" + classStudying
					+ "' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0";
		} else if (classStudying.equalsIgnoreCase("") && !querySub.equalsIgnoreCase("")) {
			querySub = querySub + " AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.branchid="+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString());
		}*/

		//queryMain = queryMain + querySub;
		/*
		 * queryMain =
		 * "FROM Parents as parents where  parents.Student.dateofbirth = '2006-04-06'"
		 * ;
		 */
	/*	System.out.println("SEARCH QUERY ***** " + queryMain);
		List<Parents> searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
		request.setAttribute("searchStudentList", searchStudentList);

		// get all the subjects
		List<Subject> subjectList = new SubjectDetailsDAO().readListOfSubjectNames(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		request.setAttribute("listSubjectNames", subjectList);

		// get the list for all the midterms
		List<Exams> examList = new ExamDetailsDAO().readListOfExams(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		request.setAttribute("listExam", examList);
		}

	}*/
	
	//end of searchid
	public void getStudentGraph()
	{
		if(httpSession.getAttribute(BRANCHID)!=null){
			
			//String studentid = DataUtil.emptyString(request.getParameter("id"));
			String[] studentIds = request.getParameterValues("studentIDs");
			Student searchStudent = new studentDetailsDAO().readUniqueObject(Integer.parseInt(studentIds[0]));
			String[] examClass = request.getParameterValues("examclass");
			String[] exCl = examClass[0].split("--");
				List<Exams> examDetailsList = new ExamDetailsDAO().readListOfExams(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
				List<Subject> subjectDetailsList = new SubjectDetailsDAO().readListOfSubjects(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()),exCl[0]);
				List<ExamsDetails> examDetails = new ArrayList<ExamsDetails>();
				
				for (Exams exams : examDetailsList) {
					
					ExamsDetails examsD = new ExamsDetails();
							List<Marks> marksListPerSubject = new MarksDetailsDAO().readMarksPerExam(searchStudent.getSid(),exams.getExid(),
									httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
							List<String> subjectAppeared = new LinkedList<String>();
							List<Integer> marksScored = new LinkedList<Integer>();
							examsD.setExamName("\""+exams.getExamname()+"\"");
							
							for (Subject subject2 : subjectDetailsList) {
								
									for (Marks marks3 : marksListPerSubject) {

										if(subject2.getSubid() == marks3.getSubid()) {
											subjectAppeared.add("\""+subject2.getSubjectname()+"\"");
											marksScored.add(marks3.getMarksobtained());
										}
									}
							}
							examsD.setSubjects(subjectAppeared);
							examsD.setMarks(marksScored);
							
							if(!marksScored.isEmpty()) {
								examDetails.add(examsD);
							}
				}
				
				request.setAttribute("examDetailsGraph", examDetails);
				request.setAttribute("examDetailsGraphSize", examDetails.size());
				request.setAttribute("studentName", searchStudent.getName().toUpperCase());
		}
	}
	public boolean viewMarks() {
		
		if(httpSession.getAttribute(BRANCHID)!=null){
		String queryMain = "From Parents as parents where";
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
			querySub = " parents.Student.name like '%" + studentname + "%'";
		}

		if (!classStudying.equalsIgnoreCase("")) {
			querySub = " parents.Student.classstudying like '" + classStudying
					+ "'  AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0";
		} else if (classStudying.equalsIgnoreCase("") && !querySub.equalsIgnoreCase("")) {
			querySub = querySub + " AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.branchid="+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString());
		}

		queryMain = queryMain + querySub;
		/*
		 * queryMain =
		 * "FROM Parents as parents where  parents.Student.dateofbirth = '2006-04-06'"
		 * ;
		 */
		System.out.println("SEARCH QUERY ***** " + queryMain);
		List<Parents> searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
		// request.setAttribute("searchStudentList", searchStudentList);
		/*List<Integer> ids = new ArrayList();

		for (int i = 0; i < searchStudentList.size(); i++) {
			ids.add(searchStudentList.get(i).getStudent().getSid());
		}

		System.out.println("Total Number of Students" + searchStudentList.size());*/

		//
		String exam = request.getParameter("exam");
		String subject = request.getParameter("subject");
		System.out.println("the subject id is " + subject + ", and exam id is " + exam);

		List<Parents> newStudentList = new ArrayList<Parents>();
		List<Marks> newMarksDetails = new ArrayList<Marks>();
		for (Parents parents : searchStudentList) {

			List<Marks> singleMarksDetails = new MarksDetailsDAO().readListOfMarks(parents.getStudent().getSid());
			for (Marks marks : singleMarksDetails) {
				System.out.println("The student id is " + parents.getStudent().getSid());
				System.out.println("The marks sid is " + marks.getSid());
				if (marks.getSubid() == Integer.parseInt(subject) && marks.getExamid() == Integer.parseInt(exam)) {
					newStudentList.add(parents);
					newMarksDetails.add(marks);
					System.out.println("Marks Details " + marks.getMarksobtained());
				}
			}
		}

		request.setAttribute("newStudentList", newStudentList);
		request.setAttribute("newMarksDetails", newMarksDetails);
		request.setAttribute("subjectselected", request.getParameter("subjectselected"));
		request.setAttribute("examselected", request.getParameter("examselected"));
		request.setAttribute("subjectid", subject);
		request.setAttribute("examid", exam);
		/*
		 * for(int i=0; i<marksDetails.size(); i++){ System.out.println(
		 * "Marks details "+marksDetails.get(i).getMarksobtained()); }
		 */
		
		}
		return true;
	}

	public void getSubjectExams() {
		// get all the subjects
		List<Subject> subjectList = new SubjectDetailsDAO().readListOfSubjectNames(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		request.setAttribute("listSubject", subjectList);

		// get the list for all the midterms
		List<Exams> examList = new ExamDetailsDAO().readListOfExams(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		request.setAttribute("listExam", examList);

	}

	public boolean updateMarks() {
		boolean result = false;

		String[] studentIds = request.getParameterValues("studentIDs");
		String[] studentsMarks = request.getParameterValues("studentMarks");
		String[] marksid = request.getParameterValues("marksid");
		String exam = request.getParameter("examidselected");
		String subject = request.getParameter("subjectidselected");
		int sizeOfArray = 0;
		Map<Integer, Map<Integer, String>> mapOfMarksid = new HashMap<Integer, Map<Integer, String>>();
		List<Integer> ids = new ArrayList<Integer>();
		List<String> studentsMarksList = new ArrayList<String>();
		List<Integer> marksids = new ArrayList<Integer>();

		if (studentsMarks != null) {

			for (String marksList : studentsMarks) {
				
				if(!marksList.equalsIgnoreCase("A")) {
					studentsMarksList.add(marksList);
				}else {
					studentsMarksList.add("999");
				}

			}
		}

		if (studentIds != null && subject != null) {

			for (String id : studentIds) {
				System.out.println("id" + id);
				ids.add(Integer.valueOf(id));

			}

			sizeOfArray = ids.size();

			System.out.println("id length" + studentIds.length);

			for (int i = 0; i < marksid.length; i++) {

				Map<Integer, String> mapOfMarksOne = new HashMap<Integer, String>();
				mapOfMarksOne.put(Integer.parseInt(studentIds[i]), studentsMarksList.get(i));

				mapOfMarksid.put(Integer.parseInt(marksid[i]), mapOfMarksOne);

			}

			int examid = Integer.parseInt(exam);
			int subid = Integer.parseInt(subject);
			List<Marks> marksList = new ArrayList<Marks>();

			for (Entry<Integer, Map<Integer, String>> entry : mapOfMarksid.entrySet()) {
				Integer marksID = entry.getKey();
				Map<Integer, String> value = entry.getValue();

				System.out.println("Marks id is " + marksID);

				for (Entry<Integer, String> entryTwo : value.entrySet()) {
					Integer studentId = entryTwo.getKey();
					String marksObtained = entryTwo.getValue().toString();
					System.out.println("Student id is " + studentId + " Marks Obtained " + marksObtained);

					Marks marks = new Marks();
					marks.setMarksid(marksID);
					marks.setExamid(examid);
					marks.setSubid(subid);
					marks.setSid(studentId);
					marks.setMarksobtained(Integer.parseInt(marksObtained));
					String currentAcademicYear = (String) httpSession.getAttribute(CURRENTACADEMICYEAR);
					String currentYear = currentAcademicYear;
					marks.setAcademicyear(currentYear);
					marks.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
					marksList.add(marks);
				}

			}

			if (new MarksDetailsDAO().updateMarks(marksList)) {
				result = true;
			}
		}

		return result;
	}

	public boolean deleteMultiple() {
		boolean result = true;
		String[] studentIds = request.getParameterValues("studentIDs");
		String[] marksIds = request.getParameterValues("marksid");
		if (marksIds != null) {
			List<Integer> marksListids = new ArrayList();
			List<Integer> studentListids = new ArrayList();

			for (String studentsIdsdelete : studentIds) {
				studentListids.add(Integer.valueOf(studentsIdsdelete));
			}

			for (String id : marksIds) {
				System.out.println("id" + id);
				marksListids.add(Integer.valueOf(id));

			}
			System.out.println("id length" + marksIds.length);
			if (new MarksDetailsDAO().deleteMultiple(marksListids, studentListids)) {
				result = true;
			} else {
				result = false;
			}

		}
		return result;
	}

	public boolean generateReport() {
		
		boolean result = false;
		
		if(httpSession.getAttribute(CURRENTACADEMICYEAR)!=null){
			
			String[] studentIds = request.getParameterValues("studentIDs");
			String examC = request.getParameter("examclass");
			String[] examClass = examC.split("--");
			//String totalColumnNumber = new DataUtil().getPropertiesValue("totalColumnNumber");
			//String[][] marksList = new String[studentIds.length][Integer.parseInt(totalColumnNumber)+1];
			List<Exams> examsList = new ExamDetailsDAO().readListOfExams(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			List<MarksSheet> marksSheetList = new ArrayList<MarksSheet>();
			String[] subjectListOtherExamIds = new DataUtil().getPropertiesValue("OtherExamsSubjects"+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())).split(",");
			List<Integer> subjectListOtherExam = new ArrayList<Integer>();	
			Map<String,Double> subMarksTermOne = new HashMap<String, Double>();
			Map<String,Double> subMarksTermTwo = new HashMap<String, Double>();
			
			for (String id : subjectListOtherExamIds) {
				subjectListOtherExam.add(Integer.parseInt(id));
			}
			
			for (int i = 0; i < studentIds.length; i++) {
				MarksSheet markssheet = new MarksSheet();
				List<ExamsMarks> examMarksList = new ArrayList<ExamsMarks>();
				List<ExamsMarks> otherExamMarksList = new ArrayList<ExamsMarks>();
				Parents studentDetails = new studentDetailsDAO().readUniqueObjectParents(Integer.parseInt(studentIds[i]));
				markssheet.setParents(studentDetails);
				
				for (Exams exam : examsList) {
						
					ExamsMarks examMarks = new ExamsMarks();
					examMarks.setExamName(exam.getExamname());
					boolean present = false;
					Map<String,String> subMarks = new HashMap<String, String>();
					int totalObtainedMarks = 0;
					int totalMarks = 0;
					int marksObtainedSubjectAllExams = 0;
					int totalMarksObtainedSubjectAllExams = 0;
					
					List<Marks> marksDetailsList = new MarksDetailsDAO().readMarksforStudent(Integer.parseInt(studentIds[i]),httpSession.getAttribute(CURRENTACADEMICYEAR).toString(),exam.getExid());
					List<Subject> subjectList = new SubjectDetailsDAO().readAllSubjectsClassWise(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()),examClass[0],exam.getExamname());
					
					for (Marks marks : marksDetailsList) {
							
							int examId = exam.getExid();
							int marksExamId = marks.getExamid();
							
						if( examId == marksExamId) {
									
									
								for (Subject sub : subjectList) {
									
									int marksSubid = marks.getSubid();
									int subjectId = sub.getSubjectid();
									
									if(marksSubid == subjectId) {
										// &&  subjectId != subjectListOtherExam.get(0) && subjectId != subjectListOtherExam.get(1)
										if(!subjectListOtherExam.contains(subjectId)) {
											present = true;
										int marksObtained = marks.getMarksobtained();
										int minMarks = sub.getMinmarks();
										int maxMarks = sub.getMaxmarks();
										
										if( marksObtained < minMarks) {
											
											subMarks.put(sub.getSubjectname(), Integer.toString(marks.getMarksobtained())+"/"+sub.getMaxmarks()+""+"_F");
											totalObtainedMarks = totalObtainedMarks+marks.getMarksobtained();
										}else if ( marksObtained >= minMarks && marksObtained <= maxMarks) {
											
											subMarks.put(sub.getSubjectname(), Integer.toString(marks.getMarksobtained())+"/"+sub.getMaxmarks()+""+"_P");
											totalObtainedMarks = totalObtainedMarks+marks.getMarksobtained();
										}else if(marksObtained == 999) {
											subMarks.put(sub.getSubjectname(), " _AB");
										}
										
										totalMarks = totalMarks+sub.getMaxmarks();
										marksObtainedSubjectAllExams = marksObtainedSubjectAllExams + marksObtained;
										totalMarksObtainedSubjectAllExams = totalMarksObtainedSubjectAllExams + sub.getMaxmarks();
										}
									}
								}
								
						}
						
					}
					//subMarks.put("total", Integer.toString(00000000)+"/"+totalMarksObtainedSubjectAllExams+""+"_P");
										
					if(present) {
						examMarks.setTotalMarks(totalMarks);
						examMarks.setTotalMarksObtained(totalObtainedMarks);
						double d = (totalObtainedMarks*100.0)/totalMarks;
						examMarks.setPercentage(d);
						examMarks.setSubMarks(subMarks);
						examMarksList.add(examMarks);
					}
					
				}
				
				//Read all the  subjects of term one and query their marks subject wise
				String[] examTermOneIds = new DataUtil().getPropertiesValue("TermOneExams"+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())).split(",");
				List<Integer> examTermOneIdsList = new ArrayList<Integer>();	
				for (String id : examTermOneIds) {
					examTermOneIdsList.add(Integer.parseInt(id));
					}
					FinalTermMarks finalExamMarks = new FinalTermMarks();
					List<FinalTermMarks> finalExamMarksList = new ArrayList<FinalTermMarks>();
					finalExamMarks.setExamName("Term 1");
					boolean present = false;
					Map<String,String> subMarks = new HashMap<String, String>();
					int totalObtainedMarks = 0;
					int totalMarks = 0;
					int totalMarksObtainedSubjectAllExamsFinalTermOne = 0;
					String grade;
					List<Marks> marksDetailsList = new MarksDetailsDAO().readMarksPerExamPerSubject(Integer.parseInt(studentIds[i]),httpSession.getAttribute(CURRENTACADEMICYEAR).toString(),examTermOneIdsList);
					List<Subjectmaster> subjectList = new SubjectDetailsDAO().readListOfSubjectMasterNames(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
					
					for (Subjectmaster subFinal : subjectList) {
						
						int marksObtainedSubjectAllExams = 0;
						
						for (Marks marks : marksDetailsList) {
							
							//int examId = examTermOneIds;
							int marksExamId = marks.getExamid();
							
									int marksSubid = marks.getSubid();
									int subjectId = subFinal.getSubjectid();
									
									if(marksSubid == subjectId) {
										if(!subjectListOtherExam.contains(subjectId)) {
										present = true;
										
										int marksObtained = marks.getMarksobtained();
										
										marksObtainedSubjectAllExams = marksObtainedSubjectAllExams + marksObtained;
										totalMarksObtainedSubjectAllExamsFinalTermOne = totalMarksObtainedSubjectAllExamsFinalTermOne + marksObtained;
										}
									}
								}
						
						
						if(marksObtainedSubjectAllExams!=0) {
							
							switch(marksObtainedSubjectAllExams/10) {
							
							 case 10:
							    case 9:
							        grade = "A1";
							        break;
							    case 8:
							        grade = "A2";
							        break;
							    case 7:
							        grade = "B1";
							        break;
							    case 6:
							        grade = "B2";
							        break;
							    case 5:
							        grade = "C1";
							        break;
							    case 4:
							        grade = "C2";
							        break;
							    default:
							        grade = "F"; 
							
							}
							subMarksTermOne.put(subFinal.getSubjectname(), marksObtainedSubjectAllExams*0.4);
							subMarks.put(subFinal.getSubjectname(), Integer.toString(marksObtainedSubjectAllExams)+"_"+grade);
						}
						
						
					}
					
					//subMarks.put("total", Integer.toString(00000000)+"/"+totalMarksObtainedSubjectAllExams+""+"_P");
										
					if(present) {
						finalExamMarks.setTotalMarks(totalMarks);
						finalExamMarks.setTotalMarksObtained(totalMarksObtainedSubjectAllExamsFinalTermOne);
						String gradeTotal = null;
						switch(totalMarksObtainedSubjectAllExamsFinalTermOne/10) {
						
						 case 10:
						    case 9:
						    	gradeTotal = "A1";
						        break;
						    case 8:
						    	gradeTotal = "A2";
						        break;
						    case 7:
						    	gradeTotal = "B1";
						        break;
						    case 6:
						    	gradeTotal = "B2";
						        break;
						    case 5:
						    	gradeTotal = "C1";
						        break;
						    case 4:
						    	gradeTotal = "C2";
						        break;
						    default:
						    	gradeTotal = "F"; 
						
						}
						
						double d = (totalObtainedMarks*100.0)/totalMarks;
						finalExamMarks.setPercentage(d);
						finalExamMarks.setSubMarks(subMarks);
						finalExamMarks.setResultclass(gradeTotal);
						finalExamMarksList.add(finalExamMarks);
					}
					
					
					String[] examTermTwoIds = new DataUtil().getPropertiesValue("TermTwoExams"+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())).split(",");
					List<Integer> examTermTwoIdsList = new ArrayList<Integer>();	
					
						for (String id : examTermTwoIds) {
							examTermTwoIdsList.add(Integer.parseInt(id));
						}
						
						FinalTermMarks finalTermTwoExamMarks = new FinalTermMarks();
						finalTermTwoExamMarks.setExamName("Term 2");
						boolean presentTermTwo = false;
						Map<String,String> subMarksfinalTermTwo = new HashMap<String, String>();
						int totalMarksObtainedSubjectAllExamsFinalTermTwo = 0;
						
						List<Marks> marksDetailsListFinalTermTwo = new MarksDetailsDAO().readMarksPerExamPerSubject(Integer.parseInt(studentIds[i]),httpSession.getAttribute(CURRENTACADEMICYEAR).toString(),examTermTwoIdsList);
						
						for (Subjectmaster subFinal : subjectList) {
						
							int marksObtainedSubjectAllExamsfinalTermTwo = 0;
							
							for (Marks marks : marksDetailsListFinalTermTwo) {
								
								//int examId = examTermOneIds;
								int marksExamId = marks.getExamid();
								
										int marksSubid = marks.getSubid();
										int subjectId = subFinal.getSubjectid();
										
										if(marksSubid == subjectId) {
											
											if(!subjectListOtherExam.contains(subjectId)) {
											presentTermTwo = true;
											
											int marksObtained = marks.getMarksobtained();
											
											marksObtainedSubjectAllExamsfinalTermTwo = marksObtainedSubjectAllExamsfinalTermTwo + marksObtained;
											totalMarksObtainedSubjectAllExamsFinalTermTwo = totalMarksObtainedSubjectAllExamsFinalTermTwo + marksObtained;
											}
										}
									}
							
							if(marksObtainedSubjectAllExamsfinalTermTwo!=0) {
								
								switch(marksObtainedSubjectAllExamsfinalTermTwo/10) {
								
								 case 10:
								    case 9:
								        grade = "A1";
								        break;
								    case 8:
								        grade = "A2";
								        break;
								    case 7:
								        grade = "B1";
								        break;
								    case 6:
								        grade = "B2";
								        break;
								    case 5:
								        grade = "C1";
								        break;
								    case 4:
								        grade = "C2";
								        break;
								    default:
								        grade = "F"; 
								
								}
								subMarksTermTwo.put(subFinal.getSubjectname(), marksObtainedSubjectAllExamsfinalTermTwo*0.6);
								subMarksfinalTermTwo.put(subFinal.getSubjectname(), Integer.toString(marksObtainedSubjectAllExamsfinalTermTwo)+"_"+grade);
							}
							
							
						}
						
						//subMarks.put("total", Integer.toString(00000000)+"/"+totalMarksObtainedSubjectAllExams+""+"_P");
											
						if(presentTermTwo) {
							finalTermTwoExamMarks.setTotalMarks(totalMarks);
							finalTermTwoExamMarks.setTotalMarksObtained(totalMarksObtainedSubjectAllExamsFinalTermTwo);
							String gradeTotalTwo = null;
							switch(totalMarksObtainedSubjectAllExamsFinalTermTwo/10) {
							
							 case 10:
							    case 9:
							    	gradeTotalTwo = "A1";
							        break;
							    case 8:
							    	gradeTotalTwo = "A2";
							        break;
							    case 7:
							    	gradeTotalTwo = "B1";
							        break;
							    case 6:
							    	gradeTotalTwo = "B2";
							        break;
							    case 5:
							    	gradeTotalTwo = "C1";
							        break;
							    case 4:
							    	gradeTotalTwo = "C2";
							        break;
							    default:
							    	gradeTotalTwo = "F"; 
							
							}
							double d = (totalObtainedMarks*100.0)/totalMarks;
							finalTermTwoExamMarks.setPercentage(d);
							finalTermTwoExamMarks.setSubMarks(subMarksfinalTermTwo);
							finalTermTwoExamMarks.setResultclass(gradeTotalTwo);
							finalExamMarksList.add(finalTermTwoExamMarks);
						}
						//end
						
						// Other Subject Details
						
						for (Exams exam : examsList) {
							
							ExamsMarks otherExamMarks = new ExamsMarks();
							otherExamMarks.setExamName(exam.getExamname());
							boolean presentOtherExamMarks = false;
							Map<String,String> otherExamSubMarks = new HashMap<String, String>();
							int totalObtainedMarksOtherExam = 0;
							int totalOtherExamMarks = 0;
							int otherExamMarksObtainedSubjectAllExams = 0;
							int totalOtherExamMarksObtainedSubjectAllExams = 0;
							int totalMarksOtherExams = 0;
							
							List<Marks> marksDetailsListOtherExam = new MarksDetailsDAO().readMarksforStudent(Integer.parseInt(studentIds[i]),httpSession.getAttribute(CURRENTACADEMICYEAR).toString(),exam.getExid());
							//List<Subject> subjectListOtherExam = new SubjectDetailsDAO().readAllSubjectsClassWise(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()),examClass[0],exam.getExamname());
							
								for (String id : subjectListOtherExamIds) {
									subjectListOtherExam.add(Integer.parseInt(id));
								}
							
							
							for (Marks marks : marksDetailsListOtherExam) {
									
									int examId = exam.getExid();
									int marksExamId = marks.getExamid();
									
								if( examId == marksExamId) {
									
											
										for (String sub : subjectListOtherExamIds) {
											
											int marksSubid = marks.getSubid();
											int subjectId = Integer.parseInt(sub);
											if(marksSubid == subjectId) {
												presentOtherExamMarks = true;
												Subject subjectDetails =  new SubjectDetailsDAO().readSubjectByExam(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()),examClass[0],exam.getExamname(),subjectId);
												
												int marksObtained = marks.getMarksobtained();
												int minMarks = subjectDetails.getMinmarks();
												int maxMarks = subjectDetails.getMaxmarks();
												
												String gradeTotalOtherExam = null;
												switch(marksObtained/10) {
												
												 case 10:
												    case 9:
												    	gradeTotalOtherExam = "A1";
												        break;
												    case 8:
												    	gradeTotalOtherExam = "A1";
												        break;
												    case 7:
												    	gradeTotalOtherExam = "A2";
												        break;
												    case 6:
												    	gradeTotalOtherExam = "A2";
												        break;
												    case 5:
												    	gradeTotalOtherExam = "B1";
												        break;
												    case 4:
												    	gradeTotalOtherExam = "B2";
												        break;
												    case 3:
												    	gradeTotalOtherExam = "C1";
												        break;
												    case 2:
												    	gradeTotalOtherExam = "C2";
												        break;
												    default:
												    	gradeTotalOtherExam = "F"; 
												
												}
												
												if( marksObtained < minMarks) {
													
													otherExamSubMarks.put(subjectDetails.getSubjectname(), Integer.toString(marks.getMarksobtained())+"/"+subjectDetails.getMaxmarks()+""+"_"+gradeTotalOtherExam);
													totalObtainedMarksOtherExam = totalObtainedMarksOtherExam+marks.getMarksobtained();
												}else if ( marksObtained >= minMarks && marksObtained <= maxMarks) {
													
													otherExamSubMarks.put(subjectDetails.getSubjectname(), Integer.toString(marks.getMarksobtained())+"/"+subjectDetails.getMaxmarks()+""+"_"+gradeTotalOtherExam);
													totalObtainedMarksOtherExam = totalObtainedMarksOtherExam+marks.getMarksobtained();
												}else if(marksObtained == 999) {
													otherExamSubMarks.put(subjectDetails.getSubjectname(), " _AB");
												}
												
												totalMarksOtherExams = totalMarksOtherExams+subjectDetails.getMaxmarks();
												otherExamMarksObtainedSubjectAllExams = otherExamMarksObtainedSubjectAllExams + marksObtained;
												totalOtherExamMarksObtainedSubjectAllExams = totalOtherExamMarksObtainedSubjectAllExams + subjectDetails.getMaxmarks();
												
											}
										}
										
								}
								
							}
							//subMarks.put("total", Integer.toString(00000000)+"/"+totalMarksObtainedSubjectAllExams+""+"_P");
												
							if(presentOtherExamMarks) {
								otherExamMarks.setTotalMarks(totalMarksOtherExams);
								otherExamMarks.setTotalMarksObtained(otherExamMarksObtainedSubjectAllExams);
								String gradeTotalOtherExams = null;
								switch(otherExamMarksObtainedSubjectAllExams/10) {
								
								 case 10:
								    case 9:
								    	gradeTotalOtherExams = "A1";
								        break;
								    case 8:
								    	gradeTotalOtherExams = "A2";
								        break;
								    case 7:
								    	gradeTotalOtherExams = "B1";
								        break;
								    case 6:
								    	gradeTotalOtherExams = "B2";
								        break;
								    case 5:
								    	gradeTotalOtherExams = "C1";
								        break;
								    case 4:
								    	gradeTotalOtherExams = "C2";
								        break;
								    default:
								    	gradeTotalOtherExams = "F"; 
								
								}
								double d = (totalObtainedMarksOtherExam*100.0)/totalMarksOtherExams;
								otherExamMarks.setPercentage(d);
								otherExamMarks.setSubMarks(otherExamSubMarks);
								otherExamMarks.setResultclass(gradeTotalOtherExams);
								otherExamMarksList.add(otherExamMarks);
							}
							
						}
						
						
						//End Other Subject Details
						
						
						// Over All Subjects
						
							
							ExamsMarks examMarksOverAll = new ExamsMarks();
							examMarksOverAll.setExamName("Term Total/Grand Total");
							double overAllTotalMarks = 0;
							Map<String,String> subMarksoverAll = new HashMap<String, String>();
							
							for (String key : subMarksTermOne.keySet()) {
					            if (subMarksTermTwo.containsKey(key)) {
					                double value1 = subMarksTermOne.get(key);
					                double value2 = subMarksTermTwo.get(key);
					                
					                int overAll = (int) (value1+value2);
					                overAllTotalMarks = overAllTotalMarks + value1 + value2;
					                String overAllGrade = null;
									switch(overAll/10) {
									
									 case 10:
									    case 9:
									    	overAllGrade = "A1";
									        break;
									    case 8:
									    	overAllGrade = "A2";
									        break;
									    case 7:
									    	overAllGrade = "B1";
									        break;
									    case 6:
									    	overAllGrade = "B2";
									        break;
									    case 5:
									    	overAllGrade = "C1";
									        break;
									    case 4:
									    	overAllGrade = "C2";
									        break;
									    case 3:
									    	overAllGrade = "D";
									    default:
									    	overAllGrade = "E";
									
									}
					                
					                subMarksoverAll.put(key, Integer.toString((int)(value1+value2))+"_"+overAllGrade);
					            }
					        }
							
								int size = subMarksoverAll.size() * 100;
								double d = (overAllTotalMarks*100.0)/size;
								examMarksOverAll.setPercentage(d);
								int percentage = (int) d;
								examMarksOverAll.setSubMarks(subMarksoverAll);
								String overAllPercentageGrade = null;
								 String overAllResultClass = null;
								switch(percentage/10) {
								
								 case 10:
								    case 9:
								    	overAllPercentageGrade = "A1";
								    	overAllResultClass = "Outstanding";
								        break;
								    case 8:
								    	overAllPercentageGrade = "A2";
								    	overAllResultClass = "Excellent";
								        break;
								    case 7:
								    	overAllPercentageGrade = "B1";
								    	overAllResultClass = "Very Good";
								        break;
								    case 6:
								    	overAllPercentageGrade = "B2";
								    	overAllResultClass = "Good";
								        break;
								    case 5:
								    	overAllPercentageGrade = "C1";
								    	overAllResultClass = "Satisfactory, can do better";
								        break;
								    case 4:
								    	overAllPercentageGrade = "C2";
								    	overAllResultClass = "Average, can do better";
								        break;
								    case 3:
								    	overAllPercentageGrade = "D";
								    	overAllResultClass = "Work Hard";
								    default:
								    	overAllPercentageGrade = "E";
								    	overAllResultClass = "";
								
								}
								examMarksOverAll.setTotalMarksObtained((int) overAllTotalMarks);
								examMarksOverAll.setResultclass(overAllPercentageGrade);
								examMarksList.add(examMarksOverAll);
								//END Over All
								
			    markssheet.setFinaltermmarks(finalExamMarksList);
				markssheet.setExammarks(examMarksList);
				markssheet.setOtherexammarks(otherExamMarksList);
				markssheet.setOverallresult(overAllResultClass);
				marksSheetList.add(markssheet);
				result = true;
				/*
				 * marksList[i][0] = studentDetails.getStudent().getAdmissionnumber();
				 * marksList[i][1] = studentDetails.getStudent().getName(); int k = 2;
				 * 
				 * for (int m=0; m<marksDetailsList.size(); m++) { marksList[i][k] =
				 * marksDetailsList.get(m).getMarksobtained().toString(); k++; }
				 */
			}
			
			int size = examsList.size();
			int endLoop = size/5;
			
			request.setAttribute("endloop", endLoop+1);
			request.setAttribute("markssheetlist", marksSheetList);
			
			/*for (MarksSheet marksSheet2 : marksSheetList) {
				
				for (ExamsMarks marksSheet3 : marksSheet2.getExammarks()) {
					System.out.println("Exam Name "+marksSheet3.getExamName());
					System.out.println("Exam total "+marksSheet3.getTotalMarks());
					
					for (Map.Entry<String,String> entry : marksSheet3.getSubMarks().entrySet()) {
						System.out.println("Key = " + entry.getKey() +
	                             ", Value = " + entry.getValue());
					}
			            
					
				}
		}*/

			/*
			 * try { if (writeToReportCard(marksList)) { result = true; } } catch (Exception
			 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
			 */
		}

		return result;
	}
// code for generate parent report
public boolean generateReportParent() {
		
		boolean result = false;
		
		if(httpSession.getAttribute(CURRENTACADEMICYEAR)!=null){
			String studentUID = request.getParameter("id");
			Student student = new studentDetailsDAO().readploginUniqueObject(studentUID);
			String[] studentIds = request.getParameterValues("studentIDs");
			String examC = student.getClassstudying();
			//String examC = request.getParameter("examclass");
			String[] examClass = examC.split("--");
			//String totalColumnNumber = new DataUtil().getPropertiesValue("totalColumnNumber");
			//String[][] marksList = new String[studentIds.length][Integer.parseInt(totalColumnNumber)+1];
			List<Exams> examsList = new ExamDetailsDAO().readListOfExams(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			List<MarksSheet> marksSheetList = new ArrayList<MarksSheet>();

			//for (int i = 0; i < studentIds.length; i++) {
				MarksSheet markssheet = new MarksSheet();
				List<ExamsMarks> examMarksList = new ArrayList<ExamsMarks>();
				Parents studentDetails = new studentDetailsDAO().readUniqueObjectParents(student.getSid());
				markssheet.setParents(studentDetails);
				
				for (Exams exam : examsList) {
						
					ExamsMarks examMarks = new ExamsMarks();
					examMarks.setExamName(exam.getExamname());
					boolean present = false;
					Map<String,String> subMarks = new HashMap<String, String>();
					int totalObtainedMarks = 0;
					int totalMarks = 0;
					
					List<Marks> marksDetailsList = new MarksDetailsDAO().readMarksforStudent(student.getSid(),httpSession.getAttribute(CURRENTACADEMICYEAR).toString(),exam.getExid());
					List<Subject> subjectList = new SubjectDetailsDAO().readAllSubjectsClassWise(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()),examClass[0],exam.getExamname());
					
					
					for (Marks marks : marksDetailsList) {
							
							int examId = exam.getExid();
							int marksExamId = marks.getExamid();
							
						if( examId == marksExamId) {
									present = true;
									
								for (Subject sub : subjectList) {
									
									int marksSubid = marks.getSubid();
									int subjectId = sub.getSubjectid();
									
									if(marksSubid == subjectId) {
										
										if( marks.getMarksobtained() < sub.getMinmarks()) {
											subMarks.put(sub.getSubjectname(), Integer.toString(marks.getMarksobtained())+"/"+sub.getMaxmarks()+""+"_F");
										}else if ( marks.getMarksobtained() >= sub.getMinmarks()) {
											subMarks.put(sub.getSubjectname(), Integer.toString(marks.getMarksobtained())+"/"+sub.getMaxmarks()+""+"_P");
										}
										
										totalObtainedMarks = totalObtainedMarks+marks.getMarksobtained();
										totalMarks = totalMarks+sub.getMaxmarks();
									}
								}
						}
						
					}
					
					if(present) {
						examMarks.setTotalMarks(totalMarks);
						examMarks.setTotalMarksObtained(totalObtainedMarks);
						double d = (totalObtainedMarks*100.0)/totalMarks;
						examMarks.setPercentage(d);
						examMarks.setSubMarks(subMarks);
						examMarksList.add(examMarks);
					}
					
				}
				markssheet.setExammarks(examMarksList);
				marksSheetList.add(markssheet);
				result = true;
				/*
				 * marksList[i][0] = studentDetails.getStudent().getAdmissionnumber();
				 * marksList[i][1] = studentDetails.getStudent().getName(); int k = 2;
				 * 
				 * for (int m=0; m<marksDetailsList.size(); m++) { marksList[i][k] =
				 * marksDetailsList.get(m).getMarksobtained().toString(); k++; }
				 */
			//}
			
			int size = examsList.size();
			int endLoop = size/5;
			
			request.setAttribute("endloop", endLoop+1);
			request.setAttribute("markssheetlist", marksSheetList);
			
			/*for (MarksSheet marksSheet2 : marksSheetList) {
				
				for (ExamsMarks marksSheet3 : marksSheet2.getExammarks()) {
					System.out.println("Exam Name "+marksSheet3.getExamName());
					System.out.println("Exam total "+marksSheet3.getTotalMarks());
					
					for (Map.Entry<String,String> entry : marksSheet3.getSubMarks().entrySet()) {
						System.out.println("Key = " + entry.getKey() +
	                             ", Value = " + entry.getValue());
					}
			            
					
				}
		}*/

			/*
			 * try { if (writeToReportCard(marksList)) { result = true; } } catch (Exception
			 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
			 */
		}

		return result;
	}
	//end generate parent report
	private boolean writeToReportCard(String[][] marksList) throws Exception {
		boolean result = false;

			try {
								
				InputStream is = this.getClass().getClassLoader().getResourceAsStream("ReportCard.xlsx");
									
				Workbook workbook = WorkbookFactory.create(is);

				Sheet sheet = workbook.getSheetAt(1);

				// int rowCount = sheet.getLastRowNum();

				int rowCount = 6;
				for (Object[] aBook : marksList) {
					Row row = sheet.createRow(++rowCount);

					int columnCount = -1;

					/*
					 * Cell cell = row.createCell(columnCount);
					 * cell.setCellValue(rowCount);
					 */
					
					
					for (Object field : aBook) {
						Cell cell = row.createCell(++columnCount);
						if(columnCount>1 && field !=null){
							cell.setCellValue((Integer) Integer.parseInt(field.toString()));
						}else if(field !=null){
							cell.setCellValue((String) field);
						}
					}

					/*for (Object field : aBook) {
						Cell cell = row.createCell(++columnCount);
						if (field instanceof String) {
							cell.setCellValue((String) field);
						} else if (field instanceof Integer) {
							cell.setCellValue((Integer) field);
						}
					}*/

				}
				is.close();
				FileOutputStream out = new FileOutputStream(new File(System.getProperty("java.io.tmpdir")+"/reportcard.xlsx"));
				workbook.write(out);
				out.close();
				
				result = true;
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		

		return result;

	}

	public void getStudentList() {
		List<Student> studentList = new studentDetailsDAO().readListOfObjectsForIcon(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		request.setAttribute("studentList", studentList);
		
	}

	public boolean downloadReportCard() {
		boolean result = false;
		try {

			File downloadFile = new File(System.getProperty("java.io.tmpdir")+"/reportcard.xlsx");
	        FileInputStream inStream = new FileInputStream(downloadFile);

	        // get MIME type of the file
			String mimeType = "application/vnd.ms-excel";

			// set content attributes for the response
			response.setContentType(mimeType);
			// response.setContentLength((int) bis.length());

			// set headers for the response
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"",
					"reportcard.xlsx");
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

	public void rankSearch() {
		if(httpSession.getAttribute(BRANCHID)!=null){
			
			String queryMain = "From Parents as parents where";

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
				querySub = " parents.Student.classstudying like '" + classStudying
						+ "' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0";
			} else if (classStudying.equalsIgnoreCase("") && !querySub.equalsIgnoreCase("")) {
				querySub = querySub + " AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.branchid="+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString());
			}

			queryMain = queryMain + querySub;
			/*
			 * queryMain =
			 * "FROM Parents as parents where  parents.Student.dateofbirth = '2006-04-06'"
			 * ;
			 */
			System.out.println("SEARCH QUERY ***** " + queryMain);
			List<Parents> searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
			request.setAttribute("searchStudentList", searchStudentList);

			// get the list for all the midterms
			List<Exams> examList = new ExamDetailsDAO().readListOfExams(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			request.setAttribute("listExam", examList);
			}

		
	}
public boolean generateRankReport() {
		
		boolean result = false;
		
		if(httpSession.getAttribute(CURRENTACADEMICYEAR)!=null){
			
			String[] studentIds = request.getParameterValues("studentIDs");
			List<Integer> studentsIds = new ArrayList<Integer>();
			String examC = request.getParameter("examclass");
			String[] examClass = examC.split("--");
			//String totalColumnNumber = new DataUtil().getPropertiesValue("totalColumnNumber");
			//String[][] marksList = new String[studentIds.length][Integer.parseInt(totalColumnNumber)+1];
			List<Exams> examsList = new ExamDetailsDAO().readListOfExams(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			List<MarksSheet> marksSheetList = new ArrayList<MarksSheet>();
			
			int rank = 1;
			for (int i = 0; i < studentIds.length; i++) {
				MarksSheet markssheet = new MarksSheet();
				ExamRank examrank = new ExamRank();
				studentsIds.add(Integer.parseInt(studentIds[i]));
				List<ExamRank> examRankList = new ArrayList<ExamRank>();
				List<ExamsMarks> examMarksList = new ArrayList<ExamsMarks>();
				Parents studentDetails = new studentDetailsDAO().readUniqueObjectParents(Integer.parseInt(studentIds[i]));
				markssheet.setParents(studentDetails);
				
				for (Exams exam : examsList) {
						
					ExamsMarks examMarks = new ExamsMarks();
					examMarks.setExamName(exam.getExamname());
					boolean present = false;
					Map<String,String> subMarks = new HashMap<String, String>();
					int totalObtainedMarks = 0;
					int totalMarks = 0;
					
					List<Marks> marksDetailsList = new MarksDetailsDAO().readMarksforStudent(Integer.parseInt(studentIds[i]),httpSession.getAttribute(CURRENTACADEMICYEAR).toString(),exam.getExid());
					List<Subject> subjectList = new SubjectDetailsDAO().readAllSubjectsClassWise(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()),examClass[0],exam.getExamname());
					
					
					for (Marks marks : marksDetailsList) {
							
							int examId = exam.getExid();
							int marksExamId = marks.getExamid();
							
						if( examId == marksExamId) {
									present = true;
									
								for (Subject sub : subjectList) {
									
									int marksSubid = marks.getSubid();
									int subjectId = sub.getSubjectid();
									
									if(marksSubid == subjectId) {
										
										int marksObtained = marks.getMarksobtained();
										int minMarks = sub.getMinmarks();
										int maxMarks = sub.getMaxmarks();
										
										if( marksObtained < minMarks) {
											
											subMarks.put(sub.getSubjectname(), Integer.toString(marks.getMarksobtained())+"/"+sub.getMaxmarks()+""+"_F");
											totalObtainedMarks = totalObtainedMarks+marks.getMarksobtained();
										}else if ( marksObtained >= minMarks && marksObtained <= maxMarks) {
											
											subMarks.put(sub.getSubjectname(), Integer.toString(marks.getMarksobtained())+"/"+sub.getMaxmarks()+""+"_P");
											totalObtainedMarks = totalObtainedMarks+marks.getMarksobtained();
										}else if(marksObtained == 999) {
											subMarks.put(sub.getSubjectname(), " _AB");
										}
										
										totalMarks = totalMarks+sub.getMaxmarks();
										
										
									}
								}
						}
						
					}
					
					if(present) {
						examMarks.setTotalMarks(totalMarks);
						examMarks.setTotalMarksObtained(totalObtainedMarks);
						double d = (totalObtainedMarks*100.0)/totalMarks;
						examMarks.setPercentage(d);
						examMarks.setSubMarks(subMarks);
						//here
                        int mypercent= (int)Math.round(d);
						List<MarksGrade> marksGradeDetailsList = new MarksDetailsDAO().readMarksGrade(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
						for (MarksGrade marksGrade : marksGradeDetailsList) {
							if( mypercent >= marksGrade.getMinpercentage() && mypercent <= marksGrade.getMaxpercentage())	
							{
								examMarks.setResultclass(marksGrade.getStatus());
								examrank.setStatus(marksGrade.getStatus());
							}
							
						}
						examMarksList.add(examMarks);
						
						examrank.setSid(Integer.parseInt(studentIds[i]));
						examrank.setExamid(exam.getExid());
						examrank.setMarksobtained(totalObtainedMarks);
						examrank.setAcademicyear(httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
						examrank.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
						examrank.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
						examRankList.add(examrank);
					}
					
					/*
					 * ExamRank examRank = new
					 * MarksDetailsDAO().getExamRank(Integer.parseInt(studentIds[i]),exam.getExid(),
					 * httpSession.getAttribute(CURRENTACADEMICYEAR).toString(),Integer.parseInt(
					 * httpSession.getAttribute(BRANCHID).toString()));
					 * examMarks.setRank(examRank.getRank());
					 */
				}
				
				
				markssheet.setExammarks(examMarksList);
				marksSheetList.add(markssheet);
				
				
		        //ExamsMarks examMarks = new ExamsMarks();
		       // examMarks.setRank(rank);
				if(new MarksDetailsDAO().saveMarks(examRankList) )
					
				result = true;
				/*
				 * marksList[i][0] = studentDetails.getStudent().getAdmissionnumber();
				 * marksList[i][1] = studentDetails.getStudent().getName(); int k = 2;
				 * 
				 * for (int m=0; m<marksDetailsList.size(); m++) { marksList[i][k] =
				 * marksDetailsList.get(m).getMarksobtained().toString(); k++; }
				 */
			}
			
			int size = examsList.size();
			int endLoop = size/5;
			
			for (Exams exams : examsList) {
				
				List<ExamRank> listExamRank = new MarksDetailsDAO().getListExamRank(studentsIds,exams.getExid(),httpSession.getAttribute(CURRENTACADEMICYEAR).toString(),Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
				Collections.sort(listExamRank);
				
				// Assign ranks
		        
		        for (int i1 = 0; i1 < listExamRank.size(); i1++) {
		        	
		        	if(i1 > 0) {
		        		int currentMarks = listExamRank.get(i1).getMarksobtained();
			        	int previousMarks = listExamRank.get(i1 - 1).getMarksobtained();
			        	if (currentMarks != previousMarks) {
			                rank++;
			            }
		        	}
		        	
		        	listExamRank.get(i1).setRank(rank);
		        }
		        
		        if(new MarksDetailsDAO().updateExamRank(listExamRank))
		        	result = true;
					
			}
			
			request.setAttribute("endloop", endLoop+1);
			request.setAttribute("markssheetlist", marksSheetList);
			
			/*for (MarksSheet marksSheet2 : marksSheetList) {
				
				for (ExamsMarks marksSheet3 : marksSheet2.getExammarks()) {
					System.out.println("Exam Name "+marksSheet3.getExamName());
					System.out.println("Exam total "+marksSheet3.getTotalMarks());
					
					for (Map.Entry<String,String> entry : marksSheet3.getSubMarks().entrySet()) {
						System.out.println("Key = " + entry.getKey() +
	                             ", Value = " + entry.getValue());
					}
			            
					
				}
		}*/

			/*
			 * try { if (writeToReportCard(marksList)) { result = true; } } catch (Exception
			 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
			 */
		}

		return result;
	}


public boolean generatePreprimaryReport() {
	
	boolean result = false;
	
	if(httpSession.getAttribute(CURRENTACADEMICYEAR)!=null){
		
		String[] studentIds = request.getParameterValues("studentIDs");
		String examC = request.getParameter("examclass");
		String[] examClass = examC.split("--");
		//String totalColumnNumber = new DataUtil().getPropertiesValue("totalColumnNumber");
		//String[][] marksList = new String[studentIds.length][Integer.parseInt(totalColumnNumber)+1];
		String[] examIds = new DataUtil().getPropertiesValue("preprimaryexamids"+httpSession.getAttribute(BRANCHID).toString()).split(",");
		List<Integer> deeniyatExamIds = new ArrayList<Integer>();
		for(int i=0;i<examIds.length;i++) {
			deeniyatExamIds.add(Integer.parseInt(examIds[i]));
		}
		List<Exams> examsList = new ExamDetailsDAO().readListOfExams(deeniyatExamIds,Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		
		List<MarksSheet> marksSheetList = new ArrayList<MarksSheet>();
		//String[] subjectListOtherExamIds = new DataUtil().getPropertiesValue("OtherExamsSubjects"+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())).split(",");
		//List<Integer> subjectListOtherExam = new ArrayList<Integer>();
		//for (String id : subjectListOtherExamIds) {
		//	subjectListOtherExam.add(Integer.parseInt(id));
		//}
		Map<String,Double> subMarksTermOne = new HashMap<String, Double>();
		Map<String,Double> subMarksTermTwo = new HashMap<String, Double>();
		
		
		
		for (int i = 0; i < studentIds.length; i++) {
			MarksSheet markssheet = new MarksSheet();
			List<ExamsMarks> examMarksList = new ArrayList<ExamsMarks>();
			List<ExamsMarks> otherExamMarksList = new ArrayList<ExamsMarks>();
			Parents studentDetails = new studentDetailsDAO().readUniqueObjectParents(Integer.parseInt(studentIds[i]));
			markssheet.setParents(studentDetails);
			
			for (Exams exam : examsList) {
					
				ExamsMarks examMarks = new ExamsMarks();
				examMarks.setExamName(exam.getExamname());
				boolean present = false;
				Map<String,String> subMarks = new HashMap<String, String>();
				int totalObtainedMarks = 0;
				int totalMarks = 0;
				int marksObtainedSubjectAllExams = 0;
				int totalMarksObtainedSubjectAllExams = 0;
				
				List<Marks> marksDetailsList = new MarksDetailsDAO().readMarksforStudent(Integer.parseInt(studentIds[i]),httpSession.getAttribute(CURRENTACADEMICYEAR).toString(),exam.getExid());
				List<Subject> subjectList = new SubjectDetailsDAO().readAllSubjectsClassWise(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()),examClass[0],exam.getExamname());
				
				for (Marks marks : marksDetailsList) {
						
						int examId = exam.getExid();
						int marksExamId = marks.getExamid();
						
					if( examId == marksExamId) {
								
								
							for (Subject sub : subjectList) {
								
								int marksSubid = marks.getSubid();
								int subjectId = sub.getSubjectid();
								
								if(marksSubid == subjectId) {
									// &&  subjectId != subjectListOtherExam.get(0) && subjectId != subjectListOtherExam.get(1)
									//if(!subjectListOtherExam.contains(subjectId)) {
										present = true;
									int marksObtained = marks.getMarksobtained();
									int minMarks = sub.getMinmarks();
									int maxMarks = sub.getMaxmarks();
									
									if( marksObtained < minMarks) {
										
										subMarks.put(sub.getSubjectname(), Integer.toString(marks.getMarksobtained())+"/"+sub.getMaxmarks()+""+"_F");
										totalObtainedMarks = totalObtainedMarks+marks.getMarksobtained();
									}else if ( marksObtained >= minMarks && marksObtained <= maxMarks) {
										
										subMarks.put(sub.getSubjectname(), Integer.toString(marks.getMarksobtained())+"/"+sub.getMaxmarks()+""+"_P");
										totalObtainedMarks = totalObtainedMarks+marks.getMarksobtained();
									}else if(marksObtained == 999) {
										subMarks.put(sub.getSubjectname(), " _AB");
									}
									
									totalMarks = totalMarks+sub.getMaxmarks();
									marksObtainedSubjectAllExams = marksObtainedSubjectAllExams + marksObtained;
									totalMarksObtainedSubjectAllExams = totalMarksObtainedSubjectAllExams + sub.getMaxmarks();
									
								}
							}
							
					}
					
				}
				//subMarks.put("total", Integer.toString(00000000)+"/"+totalMarksObtainedSubjectAllExams+""+"_P");
									
				if(present) {
					examMarks.setTotalMarks(totalMarks);
					examMarks.setTotalMarksObtained(totalObtainedMarks);
					double d = (totalObtainedMarks*100.0)/totalMarks;
					examMarks.setPercentage(d);
					examMarks.setSubMarks(subMarks);
					examMarksList.add(examMarks);
				}
				
			}
			
			//Read all the  subjects of term one and query their marks subject wise
			String[] examTermOneIds = new DataUtil().getPropertiesValue("TermOneExamsPreprimary"+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())).split(",");
			List<Integer> examTermOneIdsList = new ArrayList<Integer>();	
			for (String id : examTermOneIds) {
				examTermOneIdsList.add(Integer.parseInt(id));
				}
				FinalTermMarks finalExamMarks = new FinalTermMarks();
				List<FinalTermMarks> finalExamMarksList = new ArrayList<FinalTermMarks>();
				finalExamMarks.setExamName("Term 1");
				boolean present = false;
				Map<String,String> subMarks = new HashMap<String, String>();
				int totalObtainedMarks = 0;
				int totalMarks = 0;
				int totalMarksObtainedSubjectAllExamsFinalTermOne = 0;
				String grade;
				List<Marks> marksDetailsList = new MarksDetailsDAO().readMarksPerExamPerSubject(Integer.parseInt(studentIds[i]),httpSession.getAttribute(CURRENTACADEMICYEAR).toString(),examTermOneIdsList);
				List<Subjectmaster> subjectList = new SubjectDetailsDAO().readListOfSubjectMasterNames(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
				
				for (Subjectmaster subFinal : subjectList) {
					
					int marksObtainedSubjectAllExams = 0;
					
					for (Marks marks : marksDetailsList) {
						
						//int examId = examTermOneIds;
						int marksExamId = marks.getExamid();
						
								int marksSubid = marks.getSubid();
								int subjectId = subFinal.getSubjectid();
								
								if(marksSubid == subjectId) {
									//if(!subjectListOtherExam.contains(subjectId)) {
									present = true;
									
									int marksObtained = marks.getMarksobtained();
									
									marksObtainedSubjectAllExams = marksObtainedSubjectAllExams + marksObtained;
									totalMarksObtainedSubjectAllExamsFinalTermOne = totalMarksObtainedSubjectAllExamsFinalTermOne + marksObtained;
								}
							}
					
					
					if(marksObtainedSubjectAllExams!=0) {
						
						switch(marksObtainedSubjectAllExams/10) {
						
						 case 10:
						    case 9:
						        grade = "A1";
						        break;
						    case 8:
						        grade = "A2";
						        break;
						    case 7:
						        grade = "B1";
						        break;
						    case 6:
						        grade = "B2";
						        break;
						    case 5:
						        grade = "C1";
						        break;
						    case 4:
						        grade = "C2";
						        break;
						    default:
						        grade = "F"; 
						
						}
						subMarksTermOne.put(subFinal.getSubjectname(), marksObtainedSubjectAllExams*0.5);
						subMarks.put(subFinal.getSubjectname(), Integer.toString(marksObtainedSubjectAllExams)+"_"+grade);
					}
					
					
				}
				
				//subMarks.put("total", Integer.toString(00000000)+"/"+totalMarksObtainedSubjectAllExams+""+"_P");
									
				if(present) {
					finalExamMarks.setTotalMarks(totalMarks);
					finalExamMarks.setTotalMarksObtained(totalMarksObtainedSubjectAllExamsFinalTermOne);
					String gradeTotal = null;
					switch(totalMarksObtainedSubjectAllExamsFinalTermOne/10) {
					
					 case 10:
					    case 9:
					    	gradeTotal = "A1";
					        break;
					    case 8:
					    	gradeTotal = "A2";
					        break;
					    case 7:
					    	gradeTotal = "B1";
					        break;
					    case 6:
					    	gradeTotal = "B2";
					        break;
					    case 5:
					    	gradeTotal = "C1";
					        break;
					    case 4:
					    	gradeTotal = "C2";
					        break;
					    default:
					    	gradeTotal = "F"; 
					
					}
					
					double d = (totalObtainedMarks*100.0)/totalMarks;
					finalExamMarks.setPercentage(d);
					finalExamMarks.setSubMarks(subMarks);
					finalExamMarks.setResultclass(gradeTotal);
					finalExamMarksList.add(finalExamMarks);
				}
				
				
				String[] examTermTwoIds = new DataUtil().getPropertiesValue("TermTwoExamsPreprimary"+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())).split(",");
				List<Integer> examTermTwoIdsList = new ArrayList<Integer>();	
				
					for (String id : examTermTwoIds) {
						examTermTwoIdsList.add(Integer.parseInt(id));
					}
					
					FinalTermMarks finalTermTwoExamMarks = new FinalTermMarks();
					finalTermTwoExamMarks.setExamName("Term 2");
					boolean presentTermTwo = false;
					Map<String,String> subMarksfinalTermTwo = new HashMap<String, String>();
					int totalMarksObtainedSubjectAllExamsFinalTermTwo = 0;
					
					List<Marks> marksDetailsListFinalTermTwo = new MarksDetailsDAO().readMarksPerExamPerSubject(Integer.parseInt(studentIds[i]),httpSession.getAttribute(CURRENTACADEMICYEAR).toString(),examTermTwoIdsList);
					
					for (Subjectmaster subFinal : subjectList) {
					
						int marksObtainedSubjectAllExamsfinalTermTwo = 0;
						
						for (Marks marks : marksDetailsListFinalTermTwo) {
							
							//int examId = examTermOneIds;
							int marksExamId = marks.getExamid();
							
									int marksSubid = marks.getSubid();
									int subjectId = subFinal.getSubjectid();
									
									if(marksSubid == subjectId) {
										
										//if(!subjectListOtherExam.contains(subjectId)) {
										presentTermTwo = true;
										
										int marksObtained = marks.getMarksobtained();
										
										marksObtainedSubjectAllExamsfinalTermTwo = marksObtainedSubjectAllExamsfinalTermTwo + marksObtained;
										totalMarksObtainedSubjectAllExamsFinalTermTwo = totalMarksObtainedSubjectAllExamsFinalTermTwo + marksObtained;
									}
								}
						
						if(marksObtainedSubjectAllExamsfinalTermTwo!=0) {
							
							switch(marksObtainedSubjectAllExamsfinalTermTwo/5) {
							
							 case 10:
							    case 9:
							        grade = "A1";
							        break;
							    case 8:
							        grade = "A2";
							        break;
							    case 7:
							        grade = "B1";
							        break;
							    case 6:
							        grade = "B2";
							        break;
							    case 5:
							        grade = "C1";
							        break;
							    case 4:
							        grade = "C2";
							        break;
							    default:
							        grade = "F"; 
							
							}
							subMarksTermTwo.put(subFinal.getSubjectname(), marksObtainedSubjectAllExamsfinalTermTwo*0.5);
							subMarksfinalTermTwo.put(subFinal.getSubjectname(), Integer.toString(marksObtainedSubjectAllExamsfinalTermTwo)+"_"+grade);
						}
						
						
					}
					
					//subMarks.put("total", Integer.toString(00000000)+"/"+totalMarksObtainedSubjectAllExams+""+"_P");
										
					if(presentTermTwo) {
						finalTermTwoExamMarks.setTotalMarks(totalMarks);
						finalTermTwoExamMarks.setTotalMarksObtained(totalMarksObtainedSubjectAllExamsFinalTermTwo);
						String gradeTotalTwo = null;
						switch(totalMarksObtainedSubjectAllExamsFinalTermTwo/10) {
						
						 case 10:
						    case 9:
						    	gradeTotalTwo = "A1";
						        break;
						    case 8:
						    	gradeTotalTwo = "A2";
						        break;
						    case 7:
						    	gradeTotalTwo = "B1";
						        break;
						    case 6:
						    	gradeTotalTwo = "B2";
						        break;
						    case 5:
						    	gradeTotalTwo = "C1";
						        break;
						    case 4:
						    	gradeTotalTwo = "C2";
						        break;
						    default:
						    	gradeTotalTwo = "F"; 
						
						}
						double d = (totalObtainedMarks*100.0)/totalMarks;
						finalTermTwoExamMarks.setPercentage(d);
						finalTermTwoExamMarks.setSubMarks(subMarksfinalTermTwo);
						finalTermTwoExamMarks.setResultclass(gradeTotalTwo);
						finalExamMarksList.add(finalTermTwoExamMarks);
					}
					//end
					
					// Over All Subjects
					
						
						ExamsMarks examMarksOverAll = new ExamsMarks();
						examMarksOverAll.setExamName("Term Total/Grand Total");
						double overAllTotalMarks = 0;
						Map<String,String> subMarksoverAll = new HashMap<String, String>();
						
						for (String key : subMarksTermOne.keySet()) {
				            if (subMarksTermTwo.containsKey(key)) {
				                double value1 = subMarksTermOne.get(key);
				                double value2 = subMarksTermTwo.get(key);
				                
				                int overAll = (int) (value1+value2);
				                overAllTotalMarks = overAllTotalMarks + value1 + value2;
				                String overAllGrade = null;
								switch(overAll/10) {
								
								 case 10:
								    case 9:
								    	overAllGrade = "A1";
								        break;
								    case 8:
								    	overAllGrade = "A2";
								        break;
								    case 7:
								    	overAllGrade = "B1";
								        break;
								    case 6:
								    	overAllGrade = "B2";
								        break;
								    case 5:
								    	overAllGrade = "C1";
								        break;
								    case 4:
								    	overAllGrade = "C2";
								        break;
								    case 3:
								    	overAllGrade = "D";
								    default:
								    	overAllGrade = "E";
								
								}
				                
				                subMarksoverAll.put(key, Integer.toString((int)(value1+value2))+"_"+overAllGrade);
				            }
				        }
						
							int size = subMarksoverAll.size() * 100;
							double d = (overAllTotalMarks*100.0)/size;
							examMarksOverAll.setPercentage(d);
							int percentage = (int) d;
							examMarksOverAll.setSubMarks(subMarksoverAll);
							String overAllPercentageGrade = null;
							 String overAllResultClass = null;
								switch(percentage/10) {
								
								 case 10:
								    case 9:
								    	overAllPercentageGrade = "A1";
								    	overAllResultClass = "Outstanding";
								        break;
								    case 8:
								    	overAllPercentageGrade = "A2";
								    	overAllResultClass = "Excellent";
								        break;
								    case 7:
								    	overAllPercentageGrade = "B1";
								    	overAllResultClass = "Very Good";
								        break;
								    case 6:
								    	overAllPercentageGrade = "B2";
								    	overAllResultClass = "Good";
								        break;
								    case 5:
								    	overAllPercentageGrade = "C1";
								    	overAllResultClass = "Satisfactory, can do better";
								        break;
								    case 4:
								    	overAllPercentageGrade = "C2";
								    	overAllResultClass = "Average, can do better";
								        break;
								    case 3:
								    	overAllPercentageGrade = "D";
								    	overAllResultClass = "Work Hard";
								    default:
								    	overAllPercentageGrade = "E";
								    	overAllResultClass = "";
								
								}
							examMarksOverAll.setTotalMarksObtained((int) overAllTotalMarks);
							examMarksOverAll.setResultclass(overAllPercentageGrade);
							examMarksList.add(examMarksOverAll);
							//END Over All
							
		    markssheet.setFinaltermmarks(finalExamMarksList);
			markssheet.setExammarks(examMarksList);
			markssheet.setOtherexammarks(otherExamMarksList);
			markssheet.setOverallresult(overAllResultClass);
			marksSheetList.add(markssheet);
			result = true;
			/*
			 * marksList[i][0] = studentDetails.getStudent().getAdmissionnumber();
			 * marksList[i][1] = studentDetails.getStudent().getName(); int k = 2;
			 * 
			 * for (int m=0; m<marksDetailsList.size(); m++) { marksList[i][k] =
			 * marksDetailsList.get(m).getMarksobtained().toString(); k++; }
			 */
		}
		
		int size = examsList.size();
		int endLoop = size/5;
		
		request.setAttribute("endloop", endLoop+1);
		request.setAttribute("markssheetlist", marksSheetList);
	}

	return result;
}

public boolean generateReportSingleExams() {
	
	boolean result = false;
	
	if(httpSession.getAttribute(CURRENTACADEMICYEAR)!=null){
		
		String[] studentIds = request.getParameterValues("studentIDs");
		String examC = request.getParameter("examclass");
		String[] examClass = examC.split("--");
		//String totalColumnNumber = new DataUtil().getPropertiesValue("totalColumnNumber");
		//String[][] marksList = new String[studentIds.length][Integer.parseInt(totalColumnNumber)+1];
		List<Exams> examsList = new ExamDetailsDAO().readListOfExams(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		List<MarksSheet> marksSheetList = new ArrayList<MarksSheet>();
		List<ExamRank> examRankList = new ArrayList<ExamRank>();

		for (int i = 0; i < studentIds.length; i++) {
			MarksSheet markssheet = new MarksSheet();
			ExamRank examrank = new ExamRank();
			List<ExamsMarks> examMarksList = new ArrayList<ExamsMarks>();
			Parents studentDetails = new studentDetailsDAO().readUniqueObjectParents(Integer.parseInt(studentIds[i]));
			markssheet.setParents(studentDetails);
			
			for (Exams exam : examsList) {
					
				ExamsMarks examMarks = new ExamsMarks();
				examMarks.setExamName(exam.getExamname());
				boolean present = false;
				Map<String,String> subMarks = new HashMap<String, String>();
				int totalObtainedMarks = 0;
				int totalMarks = 0;
				
				List<Marks> marksDetailsList = new MarksDetailsDAO().readMarksforStudent(Integer.parseInt(studentIds[i]),httpSession.getAttribute(CURRENTACADEMICYEAR).toString(),exam.getExid());
				List<Subject> subjectList = new SubjectDetailsDAO().readAllSubjectsClassWise(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()),examClass[0],exam.getExamname());
				
				
				for (Marks marks : marksDetailsList) {
						
						int examId = exam.getExid();
						int marksExamId = marks.getExamid();
						
					if( examId == marksExamId) {
								present = true;
								
							for (Subject sub : subjectList) {
								
								int marksSubid = marks.getSubid();
								int subjectId = sub.getSubjectid();
								
								if(marksSubid == subjectId) {
									
									int marksObtained = marks.getMarksobtained();
									int minMarks = sub.getMinmarks();
									int maxMarks = sub.getMaxmarks();
									
									if( marksObtained < minMarks) {
										
										subMarks.put(sub.getSubjectname(), Integer.toString(marks.getMarksobtained())+"/"+sub.getMaxmarks()+""+"_F");
										totalObtainedMarks = totalObtainedMarks+marks.getMarksobtained();
									}else if ( marksObtained >= minMarks && marksObtained <= maxMarks) {
										
										subMarks.put(sub.getSubjectname(), Integer.toString(marks.getMarksobtained())+"/"+sub.getMaxmarks()+""+"_P"+"_"+marks.getSubgrade());
										totalObtainedMarks = totalObtainedMarks+marks.getMarksobtained();
									}else if(marksObtained == 999) {
										subMarks.put(sub.getSubjectname(), " _AB");
									}
									
									totalMarks = totalMarks+sub.getMaxmarks();
									
									
								}
							}
					}
					
				}
				
				if(present) {
					examMarks.setTotalMarks(totalMarks);
					examMarks.setTotalMarksObtained(totalObtainedMarks);
					double d = (totalObtainedMarks*100.0)/totalMarks;
					examMarks.setPercentage(d);
					examMarks.setSubMarks(subMarks);
					//here
                    int mypercent= (int)Math.round(d);
					List<MarksGrade> marksGradeDetailsList = new MarksDetailsDAO().readMarksGrade(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
					for (MarksGrade marksGrade : marksGradeDetailsList) {
						if( mypercent >= marksGrade.getMinpercentage() && mypercent <= marksGrade.getMaxpercentage())	
						{
							examMarks.setResultclass(marksGrade.getStatus());
							examrank.setStatus(marksGrade.getStatus());
						}
						
					}
					ExamRank examRank = new MarksDetailsDAO().getExamRank(Integer.parseInt(studentIds[i]),exam.getExid(),httpSession.getAttribute(CURRENTACADEMICYEAR).toString(),Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
					if(examRank!=null) {
					examMarks.setRank(examRank.getRank());
					}
					examMarksList.add(examMarks);
					
					/*examrank.setSid(Integer.parseInt(studentIds[i]));
					examrank.setExamid(exam.getExid());
					examrank.setMarksobtained(totalObtainedMarks);
					examrank.setAcademicyear(httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
					examrank.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
					examrank.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
					examRankList.add(examrank);*/
					
				}
				
			}
			markssheet.setExammarks(examMarksList);
			marksSheetList.add(markssheet);
			//if(new MarksDetailsDAO().saveMarks(examRankList) )
			result = true;
			/*
			 * marksList[i][0] = studentDetails.getStudent().getAdmissionnumber();
			 * marksList[i][1] = studentDetails.getStudent().getName(); int k = 2;
			 * 
			 * for (int m=0; m<marksDetailsList.size(); m++) { marksList[i][k] =
			 * marksDetailsList.get(m).getMarksobtained().toString(); k++; }
			 */
		}
		
		int size = examsList.size();
		int endLoop = size/5;
		
		request.setAttribute("endloop", endLoop+1);
		request.setAttribute("markssheetlist", marksSheetList);
		
		/*for (MarksSheet marksSheet2 : marksSheetList) {
			
			for (ExamsMarks marksSheet3 : marksSheet2.getExammarks()) {
				System.out.println("Exam Name "+marksSheet3.getExamName());
				System.out.println("Exam total "+marksSheet3.getTotalMarks());
				
				for (Map.Entry<String,String> entry : marksSheet3.getSubMarks().entrySet()) {
					System.out.println("Key = " + entry.getKey() +
                             ", Value = " + entry.getValue());
				}
		            
				
			}
	}*/

		/*
		 * try { if (writeToReportCard(marksList)) { result = true; } } catch (Exception
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */
	}

	return result;
}


}
