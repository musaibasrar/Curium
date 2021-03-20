package com.model.marksdetails.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
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

import com.model.examdetails.dao.ExamDetailsDAO;
import com.model.examdetails.dto.Exams;
import com.model.marksdetails.dao.MarksDetailsDAO;
import com.model.marksdetails.dto.Marks;
import com.model.parents.dto.Parents;
import com.model.student.dao.studentDetailsDAO;
import com.model.student.dto.Student;
import com.model.subjectdetails.dao.SubjectDetailsDAO;
import com.model.subjectdetails.dto.Subject;
import com.util.DataUtil;
import com.util.ExamsDetails;

public class MarksDetailsService {

	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession httpSession;
	private String CURRENTACADEMICYEAR = "currentAcademicYear";
	private String BRANCHID = "branchid";
	
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
		String exam = request.getParameter("exam");
		String subject = request.getParameter("subject");
		System.out.println("the subject id is " + subject + ", and exam id is " + exam);
		int sizeOfArray = 0;
		Map<Integer, String> mapOfMarks = new HashMap<Integer, String>();
		List<Integer> ids = new ArrayList<Integer>();
		List<String> studentsMarksList = new ArrayList<String>();

		if (studentsMarks != null) {

			for (String marksList : studentsMarks) {
				System.out.println("student Marks" + marksList);
				studentsMarksList.add(marksList);

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
				marks.setSid((int) mapEntry.getKey());
				marks.setMarksobtained(Integer.parseInt(test));
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
					+ "' OR parents.Student.classstudying = '" + conClassStudyingEquals
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
		}

	}

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
					+ "' OR parents.Student.classstudying = '" + conClassStudyingEquals
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
		System.out.println("the subject id is " + subject + ", and exam id is " + exam);
		int sizeOfArray = 0;
		Map<Integer, Map<Integer, String>> mapOfMarksid = new HashMap<Integer, Map<Integer, String>>();
		List<Integer> ids = new ArrayList<Integer>();
		List<String> studentsMarksList = new ArrayList<String>();
		List<Integer> marksids = new ArrayList<Integer>();

		if (studentsMarks != null) {

			for (String marksList : studentsMarks) {
				System.out.println("student Marks" + marksList);
				studentsMarksList.add(marksList);

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
				mapOfMarksOne.put(Integer.parseInt(studentIds[i]), studentsMarks[i]);

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
			List marksListids = new ArrayList();
			List studentListids = new ArrayList();

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
			String totalColumnNumber = new DataUtil().getPropertiesValue("totalColumnNumber");
			String[][] marksList = new String[studentIds.length][Integer.parseInt(totalColumnNumber)+1];

			for (int i = 0; i < studentIds.length; i++) {
				Student studentDetails = new studentDetailsDAO().readUniqueObject(Integer.parseInt(studentIds[i]));
				
				List<Marks> marksDetailsList = new MarksDetailsDAO().readMarksforStudent(Integer.parseInt(studentIds[i]),httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
				marksList[i][0] = studentDetails.getAdmissionnumber();
				marksList[i][1] = studentDetails.getName();
				int k = 2;
				
				for (int m=0; m<marksDetailsList.size(); m++) {
							marksList[i][k] = marksDetailsList.get(m).getMarksobtained().toString();
							k++;
					}
			}

			try {
				if (writeToReportCard(marksList)) {
					result = true;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}

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



}
