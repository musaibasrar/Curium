package org.ideoholic.curium.model.marksdetails.service;

import org.apache.poi.ss.usermodel.*;
import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.documents.dto.SearchStudentResponseDto;
import org.ideoholic.curium.model.examdetails.dao.ExamDetailsDAO;
import org.ideoholic.curium.model.examdetails.dto.Exams;
import org.ideoholic.curium.model.marksdetails.dao.MarksDetailsDAO;
import org.ideoholic.curium.model.marksdetails.dto.*;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.student.dao.studentDetailsDAO;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.model.student.dto.StudentIdsDto;
import org.ideoholic.curium.model.subjectdetails.dao.SubjectDetailsDAO;
import org.ideoholic.curium.model.subjectdetails.dto.Subject;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.ExamsDetails;
import org.ideoholic.curium.util.ExamsMarks;
import org.ideoholic.curium.util.MarksSheet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

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

	public ResultResponse addMarks(MarksUpdateDto dto, String branchId, String currentAcademicYear, String userId) {

		ResultResponse result = ResultResponse.builder().build();

		String[] studentIds = dto.getStudentIds();
		String[] studentsMarks = dto.getStudentsMarks();
		String exam = dto.getExam();
		String subject = dto.getSubject();
		System.out.println("the subject id is " + subject + ", and exam id is " + exam);
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

			int examid = Integer.parseInt(exam);
			int subid = Integer.parseInt(subject);
			List<Marks> marksList = new ArrayList<Marks>();

			while (mapIterator.hasNext()) {
				Map.Entry mapEntry = (Entry) mapIterator.next();
				System.out.println("The id is " + mapEntry.getKey() + "and marks is " + mapEntry.getValue());

				String test = (String) mapEntry.getValue();
				Marks marks = new Marks();
				marks.setExamid(examid);
				marks.setSubid(subid);
				//here
                int mymark= Integer.parseInt(test);
				
				List<SubjectGrade> subjectGradeDetailsList = new MarksDetailsDAO().readSubjectGrade(Integer.parseInt(branchId));
				for (SubjectGrade subjectGrade : subjectGradeDetailsList) {
					if( mymark >= subjectGrade.getMinmarks() && mymark <= subjectGrade.getMaxmarks())	
					{
						marks.setSubgrade(subjectGrade.getStatus());
					}
					
				}
				marks.setSid((int) mapEntry.getKey());
				marks.setMarksobtained(Integer.parseInt(test));
				String currentYear = currentAcademicYear;
				marks.setAcademicyear(currentYear);
				marks.setBranchid(Integer.parseInt(branchId));
				marks.setUserid(Integer.parseInt(userId));
				marksList.add(marks);
			}

			String output = new MarksDetailsDAO().addMarks(marksList);
			
			if(output=="success"){
				result.setSuccess(true);
			}else if (output.contains("Duplicate")){
				result.setMessage("Duplicate");
			}
				
			
			/*if (new MarksDetailsDAO().addMarks(marksList)) {
				result = true;
			}*/
		}

		return result;
	}

	public SearchStudentResponseDto Search(SearchStudentExamDto dto, String branchId) {
		SearchStudentResponseDto result = SearchStudentResponseDto.builder().build();

		if(branchId!=null){
			
		String queryMain = "From Parents as parents where";
		String studentname = DataUtil.emptyString(dto.getStudentName());

		String addClass = dto.getAddClass();
		String addSec = dto.getAddSec();
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
			querySub = querySub + " AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.branchid="+Integer.parseInt(branchId);
		}

		queryMain = queryMain + querySub;
		/*
		 * queryMain =
		 * "FROM Parents as parents where  parents.Student.dateofbirth = '2006-04-06'"
		 * ;
		 */
		System.out.println("SEARCH QUERY ***** " + queryMain);
		List<Parents> searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
		result.setSearchStudentList(searchStudentList);

		// get all the subjects
		List<Subject> subjectList = new SubjectDetailsDAO().readListOfSubjectNames(Integer.parseInt(branchId));
		result.setSubjectList(subjectList);

		// get the list for all the midterms
		List<Exams> examList = new ExamDetailsDAO().readListOfExams(Integer.parseInt(branchId));
		result.setExamsList(examList);
		result.setSuccess(true);
		}
		return result;
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
	public StudentGraphResponseDto getStudentGraph(StudentIdsDto dto, String branchId, String currentAcademicYear)
	{
		StudentGraphResponseDto result = StudentGraphResponseDto.builder().build();

		if(branchId!=null){
			
			//String studentid = DataUtil.emptyString(request.getParameter("id"));
			String[] studentIds = dto.getStudentIds();
			Student searchStudent = new studentDetailsDAO().readUniqueObject(Integer.parseInt(studentIds[0]));
			String[] examClass = dto.getExamClass();
			String[] exCl = examClass[0].split("--");
				List<Exams> examDetailsList = new ExamDetailsDAO().readListOfExams(Integer.parseInt(branchId));
				List<Subject> subjectDetailsList = new SubjectDetailsDAO().readListOfSubjects(Integer.parseInt(branchId),exCl[0]);
				List<ExamsDetails> examDetails = new ArrayList<ExamsDetails>();
				
				for (Exams exams : examDetailsList) {
					
					ExamsDetails examsD = new ExamsDetails();
							List<Marks> marksListPerSubject = new MarksDetailsDAO().readMarksPerExam(searchStudent.getSid(),exams.getExid(),
									currentAcademicYear);
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

				result.setExamDetailsGraph(examDetails);
				result.setExamDetailsGraphSize(examDetails.size());
				result.setSearchStudent(searchStudent.getName().toUpperCase());
				result.setSuccess(true);
		}
		return result;
	}
	public MarksResponseDto viewMarks(MarksViewDto dto, String branchId) {
		MarksResponseDto result = MarksResponseDto.builder().build();
		
		if(branchId!=null){
		String queryMain = "From Parents as parents where";
		String studentname = DataUtil.emptyString(dto.getStudentName());

		String addClass = dto.getAddClass();
		String addSec = dto.getAddSec();
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
			querySub = querySub + " AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.branchid="+Integer.parseInt(branchId);
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
		String exam = dto.getExam();
		String subject = dto.getSubject();
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

		result.setNewStudentList(newStudentList);
		result.setNewMarksDetails(newMarksDetails);
		result.setSubjectSelected(dto.getSubjectSelected());
		result.setExamSelected(dto.getExamSelected());
		result.setSubject(subject);
		result.setExam(exam);
		result.setSuccess(true);
		/*
		 * for(int i=0; i<marksDetails.size(); i++){ System.out.println(
		 * "Marks details "+marksDetails.get(i).getMarksobtained()); }
		 */
		
		}
		return result;
	}

	public SearchStudentResponseDto getSubjectExams(String branchId) {
		SearchStudentResponseDto result = SearchStudentResponseDto.builder().build();
		// get all the subjects
		List<Subject> subjectList = new SubjectDetailsDAO().readListOfSubjectNames(Integer.parseInt(branchId));
		result.setSubjectList(subjectList);

		// get the list for all the midterms
		List<Exams> examList = new ExamDetailsDAO().readListOfExams(Integer.parseInt(branchId));
		result.setExamsList(examList);
		result.setSuccess(true);

		return result;
	}

	public ResultResponse updateMarks(MarksUpdateDto dto, String strCurrentAcademicYear, String branchId) {
		ResultResponse result = ResultResponse.builder().build();

		String[] studentIds = dto.getStudentIds();
		String[] studentsMarks = dto.getStudentsMarks();
		String[] marksid = dto.getMarksId();
		String exam = dto.getExam();
		String subject = dto.getSubject();
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
					String currentAcademicYear = strCurrentAcademicYear;
					String currentYear = currentAcademicYear;
					marks.setAcademicyear(currentYear);
					marks.setBranchid(Integer.parseInt(branchId));
					marksList.add(marks);
				}

			}

			if (new MarksDetailsDAO().updateMarks(marksList)) {
				result.setSuccess(true);
			}
		}

		return result;
	}

	public ResultResponse deleteMultiple(GenerateReportDto dto) {
		ResultResponse result = ResultResponse.builder().build();
		String[] studentIds = dto.getStudentIds();
		String[] marksIds = dto.getMarksIds();
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
				result.setSuccess(true);
			} else {
				result.setSuccess(false);
			}

		}
		return result;
	}

	public GenerateReportResponseDto generateReport(GenerateReportDto dto, String currentAcademicYear, String branchId) {

		GenerateReportResponseDto result = GenerateReportResponseDto.builder().build();
		
		if(currentAcademicYear!=null){
			
			String[] studentIds = dto.getStudentIds();
			String examC = dto.getExamClass();
			String[] examClass = examC.split("--");
			//String totalColumnNumber = new DataUtil().getPropertiesValue("totalColumnNumber");
			//String[][] marksList = new String[studentIds.length][Integer.parseInt(totalColumnNumber)+1];
			List<Exams> examsList = new ExamDetailsDAO().readListOfExams(Integer.parseInt(branchId));
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
					
					List<Marks> marksDetailsList = new MarksDetailsDAO().readMarksforStudent(Integer.parseInt(studentIds[i]),currentAcademicYear,exam.getExid());
					List<Subject> subjectList = new SubjectDetailsDAO().readAllSubjectsClassWise(Integer.parseInt(branchId),examClass[0],exam.getExamname());
					
					
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
						List<MarksGrade> marksGradeDetailsList = new MarksDetailsDAO().readMarksGrade(Integer.parseInt(branchId));
						for (MarksGrade marksGrade : marksGradeDetailsList) {
							if( mypercent >= marksGrade.getMinpercentage() && mypercent <= marksGrade.getMaxpercentage())	
							{
								examMarks.setResultclass(marksGrade.getStatus());
								examrank.setStatus(marksGrade.getStatus());
							}
							
						}
						ExamRank examRank = new MarksDetailsDAO().getExamRank(Integer.parseInt(studentIds[i]),exam.getExid(),currentAcademicYear,Integer.parseInt(branchId));
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
				result.setSuccess(true);
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

			result.setEndLoop(endLoop+1);
			result.setMarksSheetList(marksSheetList);
			result.setSuccess(true);
			
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
public GenerateReportResponseDto generateReportParent(GenerateReportDto dto, String currentAcademicYear, String branchId) {

		GenerateReportResponseDto result = GenerateReportResponseDto.builder().build();
		
		if(currentAcademicYear!=null){
			String studentUID = dto.getStudentUID();
            Student student = new studentDetailsDAO().readploginUniqueObject(studentUID);
			String[] studentIds = dto.getStudentIds();
            String examC = student.getClassstudying();
			//String examC = request.getParameter("examclass");
			String[] examClass = examC.split("--");
			//String totalColumnNumber = new DataUtil().getPropertiesValue("totalColumnNumber");
			//String[][] marksList = new String[studentIds.length][Integer.parseInt(totalColumnNumber)+1];
			List<Exams> examsList = new ExamDetailsDAO().readListOfExams(Integer.parseInt(branchId));
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
					
					List<Marks> marksDetailsList = new MarksDetailsDAO().readMarksforStudent(student.getSid(),currentAcademicYear,exam.getExid());
					List<Subject> subjectList = new SubjectDetailsDAO().readAllSubjectsClassWise(Integer.parseInt(branchId),examClass[0],exam.getExamname());
					
					
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
				result.setSuccess(true);
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

			result.setEndLoop(endLoop+1);
			result.setMarksSheetList(marksSheetList);
			
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

	public ResultResponse getStudentList(String branchId) {
		List<Student> studentList = new studentDetailsDAO().readListOfObjectsForIcon(Integer.parseInt(branchId));
		return ResultResponse
				.builder()
				.resultList(studentList)
				.success(true)
				.build();
	}

	public ResultResponse downloadReportCard() {
		ResultResponse result = ResultResponse.builder().build();
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
			result.setSuccess(true);
		} catch (Exception e) {
			System.out.println("" + e);
		}
		return result;
	}

	public SearchStudentResponseDto rankSearch(SearchStudentExamDto dto, String branchId) {
		SearchStudentResponseDto result = SearchStudentResponseDto.builder().build();

		if(branchId!=null){
			
			String queryMain = "From Parents as parents where";

			String addClass = dto.getAddClass();
			String addSec = dto.getAddSec();
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
			result.setSearchStudentList(searchStudentList);

			// get the list for all the midterms
			List<Exams> examList = new ExamDetailsDAO().readListOfExams(Integer.parseInt(branchId));
			result.setExamsList(examList);
			result.setSuccess(true);
			}

		return result;
	}
public GenerateReportResponseDto generateRankReport(GenerateReportDto dto, String branchId, String currentAcademicYear, String userId) {

		GenerateReportResponseDto result = GenerateReportResponseDto.builder().build();
		
		if(currentAcademicYear!=null){
			
			String[] studentIds = dto.getStudentIds();
			List<Integer> studentsIds = new ArrayList<Integer>();
			String examC = dto.getExamClass();
			String[] examClass = examC.split("--");
			//String totalColumnNumber = new DataUtil().getPropertiesValue("totalColumnNumber");
			//String[][] marksList = new String[studentIds.length][Integer.parseInt(totalColumnNumber)+1];
			List<Exams> examsList = new ExamDetailsDAO().readListOfExams(Integer.parseInt(branchId));
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
					
					List<Marks> marksDetailsList = new MarksDetailsDAO().readMarksforStudent(Integer.parseInt(studentIds[i]),branchId,exam.getExid());
					List<Subject> subjectList = new SubjectDetailsDAO().readAllSubjectsClassWise(Integer.parseInt(branchId),examClass[0],exam.getExamname());
					
					
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
						List<MarksGrade> marksGradeDetailsList = new MarksDetailsDAO().readMarksGrade(Integer.parseInt(branchId));
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
						examrank.setAcademicyear(currentAcademicYear);
						examrank.setBranchid(Integer.parseInt(branchId));
						examrank.setUserid(Integer.parseInt(userId));
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

				result.setSuccess(true);
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
				
				List<ExamRank> listExamRank = new MarksDetailsDAO().getListExamRank(studentsIds,exams.getExid(),currentAcademicYear,Integer.parseInt(branchId));
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
					result.setSuccess(true);
					
			}

			result.setEndLoop(endLoop+1);
			result.setMarksSheetList(marksSheetList);
			
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
