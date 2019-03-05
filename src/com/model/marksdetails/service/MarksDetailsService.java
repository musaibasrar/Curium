package com.model.marksdetails.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.model.attendance.dao.AttendanceDAO;
import com.model.attendance.dto.Studentdailyattendance;
import com.model.attendance.service.AttendanceService;
import com.model.branch.dao.BranchDAO;
import com.model.branch.dto.Branch;
import com.model.branch.service.BranchService;
import com.model.examdetails.dao.ExamDetailsDAO;
import com.model.examdetails.dto.Exams;
import com.model.examlevels.dao.ExamLevelDetailsDAO;
import com.model.examlevels.dto.Examleveldetails;
import com.model.examlevels.dto.Subexamlevel;
import com.model.examlevels.service.ExamLevelService;
import com.model.language.service.LanguageService;
import com.model.marksdetails.dao.MarksDetailsDAO;
import com.model.marksdetails.dto.Marks;
import com.model.parents.dto.Parents;
import com.model.student.dao.studentDetailsDAO;
import com.model.student.dto.Student;
import com.model.subjectdetails.dao.SubjectDetailsDAO;
import com.model.subjectdetails.dto.Subject;
import com.model.subjectdetails.service.SubjectDetailsService;
import com.util.DataUtil;

public class MarksDetailsService {

	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession httpSession;
	private String CURRENTACADEMICYEAR = "currentAcademicYear";
	private String BRANCHID = "branchid";
	
	private static final Logger logger = LogManager.getLogger(MarksDetailsService.class);
	
	public MarksDetailsService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

	public String addMarks() {

		String result = "false";

		String[] studentIds = request.getParameterValues("studentIDs");
		String[] studentsMarks = request.getParameterValues("studentMarks");
		String exam = request.getParameter("hiddensearchedexamlevel");
		String subject = request.getParameter("hiddensearchedsubject");
		logger.info("the subject id is " + subject + ", and exam level is " + exam);
		int sizeOfArray = 0;
		Map<Integer, String> mapOfMarks = new HashMap<Integer, String>();
		List<Integer> ids = new ArrayList<Integer>();
		List<String> studentsMarksList = new ArrayList<String>();

		if (studentIds != null && subject != null) {
		for (String sid : studentIds) {
                        String[] stdId = sid.split(":");
                        studentsMarksList.add(studentsMarks[Integer.parseInt(stdId[1])]);
                        ids.add(Integer.valueOf(stdId[0]));
                }
		
		if (studentsMarks != null) {

			for (String marksList : studentsMarks) {
				studentsMarksList.add(marksList);

			}
		}

			/*for (String id : studentIds) {
				System.out.println("id" + id);
				ids.add(Integer.valueOf(id));

			}*/

			sizeOfArray = ids.size();

			for (int i = 0; i < sizeOfArray; i++) {
				mapOfMarks.put(ids.get(i), studentsMarksList.get(i));
			}

			Set mapSet = mapOfMarks.entrySet();
			Iterator mapIterator = mapSet.iterator();

			
			
			List<Examleveldetails> examLevelDetails = new ExamLevelDetailsDAO().getExamLevelDetails(exam);
			
			int examid = examLevelDetails.get(0).getIdexamlevel();
			
			
			Subject subjectDetails = new SubjectDetailsService(request, response).getSubjectDetails(subject);
			int subid = subjectDetails.getSubid();
			List<Marks> marksList = new ArrayList<Marks>();

			while (mapIterator.hasNext()) {
				Map.Entry mapEntry = (Entry) mapIterator.next();
				    logger.info("The id is " + mapEntry.getKey() + "and marks is " + mapEntry.getValue());

				String test = (String) mapEntry.getValue();
				Marks marks = new Marks();
				marks.setExamid(examid);
				marks.setSubid(subid);
				marks.setSid((int) mapEntry.getKey());
				marks.setMarksobtained(Integer.parseInt(test));
				String currentYear = (String) httpSession.getAttribute(CURRENTACADEMICYEAR);
				marks.setAcademicyear(currentYear);
				marks.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
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
		    
		    
		    String[] examLevel = DataUtil.emptyString(request.getParameter("examlevel")).split(":");
		       String searchQuery = "From Parents as parent where ";
		       String subQuery =null;
		       
		            if(!request.getParameter("centercode").equalsIgnoreCase("")) {
		                String[] centerCode = request.getParameter("centercode").split(":");
		                subQuery = "parent.Student.centercode = '"+centerCode[0]+"'";
		                httpSession.setAttribute("printcentername", "Center Name: "+centerCode[1]);
		                httpSession.setAttribute("printcentercode", "Center Code: "+centerCode[0]);
		                httpSession.setAttribute("evaluationsheetcentersearch", centerCode[0]+":"+centerCode[1]);
		            }else {
		                httpSession.setAttribute("evaluationsheetcentersearch", "");
		            }
		            
		            if(!request.getParameter("examlevel").equalsIgnoreCase("")) {
		                if(subQuery!=null) {
		                    subQuery = subQuery+" AND parent.Student.examlevel = '"+examLevel[1]+"'";
		                }else {
		                    subQuery = "parent.Student.examlevel = '"+examLevel[1]+"'";
		                }
		                httpSession.setAttribute("printexamlevel", "Examination Level: "+examLevel[1]);
		                httpSession.setAttribute("evaluationsheetexamlevelsearch", request.getParameter("examlevel").toString());
		            }else {
		                httpSession.setAttribute("evaluationsheetexamlevelsearch", "");
		            }
		            
		            if(!request.getParameter("languageopted").equalsIgnoreCase("")) {
		                if(subQuery!=null) {
		                    subQuery = subQuery+" AND parent.Student.languageopted = '"+request.getParameter("languageopted")+"'";
		                }else {
		                    subQuery = "parent.Student.languageopted = '"+request.getParameter("languageopted")+"'";
		                }
		                httpSession.setAttribute("printlanguage", "Language: "+request.getParameter("languageopted").toString());
		                httpSession.setAttribute("evaluationsheetlanguagesearch",request.getParameter("languageopted").toString());
		            }else {
		                httpSession.setAttribute("evaluationsheetlanguagesearch","");
		            }
		            
		            searchQuery = searchQuery+subQuery+ " Order By parent.Student.admissionnumber ASC";
		            List<Parents> parentsList = new studentDetailsDAO().getStudentsList(searchQuery);
		            Map<Parents,String> mapStudentReports = new LinkedHashMap<Parents,String>();
		            
		            for (Parents parents : parentsList) {
		                Branch centerName = new BranchDAO().getBranch(parents.getStudent().getCentercode());
		                mapStudentReports.put(parents, centerName.getCentername());
		            }
		            httpSession.setAttribute("mapstudentreports", mapStudentReports);
		            httpSession.setAttribute("totalstudentevaluation", parentsList.size());
		            new ExamLevelService(request, response).examLevels();
		            new LanguageService(request, response).viewLanguage();
		            new BranchService(request, response).viewBranches();

		            List<Subexamlevel>  subExamList =  new ExamLevelService(request, response).getSubExamLevelSubject(examLevel[1]);
		            request.setAttribute("subjectlist", subExamList);
		            httpSession.setAttribute("subjectlistevaluation", subExamList);
		            request.setAttribute("searchedexamlevel", DataUtil.emptyString(request.getParameter("examlevel")));
		            request.setAttribute("subjectentermarks", DataUtil.emptyString(request.getParameter("subject")));
		}
	}

	public String viewMarks() {
		
		if(httpSession.getAttribute(BRANCHID)!=null){
		    
	                    String examLevel = DataUtil.emptyString(request.getParameter("examlevel"));
	                    String subjectName = DataUtil.emptyString(request.getParameter("subjectnameAjax"));
	                    if(subjectName==null || subjectName.isEmpty()) {
	                    	subjectName = DataUtil.emptyString(request.getParameter("subjectselected"));
	                    }
	                    String academicYear = DataUtil.emptyString(request.getParameter("academicyear"));
	                    
	                       String searchQuery = "From Student where ";
	                       String subQuery =null;
	                       
	                            if(!request.getParameter("centercode").equalsIgnoreCase("")) {
	                                String[] centerCode = request.getParameter("centercode").split(":");
	                                subQuery = "centercode = '"+centerCode[0]+"'";
	                                httpSession.setAttribute("printcentername", "Center Name: "+centerCode[1]);
	                                httpSession.setAttribute("evaluationsheetcentersearch", centerCode[0]+":"+centerCode[1]);
	        		            }else {
	        		                httpSession.setAttribute("evaluationsheetcentersearch", "");
	        		            }
	                            
	                            if(!request.getParameter("examlevel").equalsIgnoreCase("")) {
	                                if(subQuery!=null) {
	                                    subQuery = subQuery+" AND examlevel = '"+examLevel+"'";
	                                }else {
	                                    subQuery = "examlevel = '"+examLevel+"'";
	                                }
	                                httpSession.setAttribute("printexamlevel", "Examination Level: "+examLevel);
	                                httpSession.setAttribute("evaluationsheetexamlevelsearch", request.getParameter("examlevel").toString());
	                                httpSession.setAttribute("examselected", examLevel);
	        		            }else {
	        		                httpSession.setAttribute("evaluationsheetexamlevelsearch", "");
	        		                httpSession.setAttribute("examselected", "");
	        		            }
	                            
	                            if(!request.getParameter("languageopted").equalsIgnoreCase("")) {
	                                if(subQuery!=null) {
	                                    subQuery = subQuery+" AND languageopted = '"+request.getParameter("languageopted")+"'";
	                                }else {
	                                    subQuery = "languageopted = '"+request.getParameter("languageopted")+"'";
	                                }
	                                httpSession.setAttribute("printlanguage", "Language: "+request.getParameter("languageopted").toString());
	                                httpSession.setAttribute("languagesearch", request.getParameter("languageopted").toString());
	        		            }else {
	        		                httpSession.setAttribute("languagesearch", "");
	        		            }
	                            
	                            httpSession.setAttribute("subjectselected", subjectName);
	                            
	                            searchQuery = searchQuery+subQuery+" Order By admissionnumber ASC";
	                            List<Student> studentList = new studentDetailsDAO().getListStudents(searchQuery);
	                            //Map<Parents,String> mapStudentReports = new HashMap<Parents,String>();
	                            
		
		List<Examleveldetails> examLevelDetails = new ExamLevelService(request, response).getExamLevelDetails(examLevel);
		Subject subjectDetails = new SubjectDetailsService(request, response).getSubjectDetails(subjectName);
		
		Integer examId = examLevelDetails.get(0).getIdexamlevel();
                Integer subjectId = subjectDetails.getSubid();
                
		Map<Student,Marks> marksStudentMap = new LinkedHashMap<Student,Marks>();
		List<Integer> studentIds = new LinkedList<Integer>();
		
			for (Student student : studentList) {
				studentIds.add(student.getSid());
			}
		
			List<Marks> singleMarksDetails = new MarksDetailsDAO().readMarks(studentIds, subjectId,	examId, academicYear);
			
			if(singleMarksDetails.size() > 0) {
				
				//sort student and marks
				Collections.sort(studentList);
				Collections.sort(singleMarksDetails);

				for (Student student : studentList) {
					
					boolean marksYesNo = false;
					
					for (Marks marks : singleMarksDetails) {
						
						if(student.getSid().intValue() == marks.getSid().intValue()) {
							marksYesNo = true;
							marksStudentMap.put(student, marks);
							break;
						}
					}
					
					if(!marksYesNo) {
						
						Studentdailyattendance studentAttendance = new Studentdailyattendance();
						
						//search in attendance 
						studentAttendance = new AttendanceService().getStudentAttendance(student,subjectName,examLevel);
							
						if(studentAttendance!=null && "Present".equalsIgnoreCase(studentAttendance.getAttendancestatus())) {
							
							Marks marksObtained = new Marks();
							marksObtained.setExamid(examId);
							marksObtained.setSubid(subjectId);
							marksObtained.setSid(student.getSid());
							marksObtained.setMarksobtained(0);
							String currentYear = (String) httpSession.getAttribute(CURRENTACADEMICYEAR);
							marksObtained.setAcademicyear(currentYear);
							marksObtained.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
							String output = new MarksDetailsDAO().addMarks(marksObtained);

							if(output=="success"){
								marksStudentMap.put(student, marksObtained);
							}else if (output.contains("Duplicate")){
								logger.info("Duplicate marks found");
							}
						}
					}
					
				}
			}else {
				return "MARKSENTRY";
			}
			
			
		/*for (Parents parents : parentsList) {
					studentIds.add(parents.getStudent().getSid());
			
			
			Marks singleMarksDetails = new MarksDetailsDAO().readMarks(parents.getStudent().getSid(),subjectId,examId,academicYear);
			if(singleMarksDetails!=null) {
			    marksStudentMap.put(parents, singleMarksDetails);  
			}
		}*/

				request.setAttribute("marksstudentmap", marksStudentMap);
				
                new ExamLevelService(request, response).examLevels();
                new LanguageService(request, response).viewLanguage();
                new BranchService(request, response).viewBranches();
    
		}
		
		return "VIEWMARKS";
	}

	public void getSubjectExams() {
		// get all the subjects
		List<Subject> subjectList = new SubjectDetailsDAO().readListOfSubjects(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		request.setAttribute("listSubject", subjectList);
/*
		// get the list for all the midterms
		List<Exams> examList = new ExamDetailsDAO().readListOfExams(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		request.setAttribute("listExam", examList);
		//
*/		new ExamLevelService(request, response).examLevels();
	        new LanguageService(request, response).viewLanguage();
	        new BranchService(request, response).viewBranches();

	}

	public boolean updateMarks() {
		boolean result = false;
		String[] marksIds = request.getParameterValues("marksIDs");
		String[] studentsMarks = request.getParameterValues("studentMarks");
		Map<Integer, Integer> idsMarks = new HashMap<Integer, Integer>();
		
		if(marksIds!=null) {
		    
		for (String marksId : marksIds) {
                    String[] stdId = marksId.split(":");
                    idsMarks.put(Integer.parseInt(stdId[0]), Integer.parseInt(studentsMarks[Integer.parseInt(stdId[1])]));
                }
		
		if (new MarksDetailsDAO().updateMarks(idsMarks)) {
                    result = true;
		    }
		}
		return result;
	}

	public boolean deleteMultiple() {
		boolean result = true;
		String[] marksIds = request.getParameterValues("marksIDs");
		if (marksIds != null) {
			List marksListids = new ArrayList();
			
			for (String id : marksIds) {
				String[] marksId = id.split(":");
				marksListids.add(Integer.valueOf(marksId[0]));

			}

			if (new MarksDetailsDAO().deleteMultiple(marksListids)) {
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

			List<Exams> exams = new ExamDetailsDAO().readListOfExams(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			List<Subject> subjects = new SubjectDetailsDAO().readListOfSubjects(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			Integer[][] examsubjectCombo = new Integer[exams.size() * subjects.size()][2];
			int r = 0, c = 0;
			for (Exams examsList : exams) {

				for (Subject subject : subjects) {
					c = 0;
					examsubjectCombo[r][c] = subject.getSubid();
					c++;
					examsubjectCombo[r][c] = examsList.getExid();
					r++;
				}

			}

			for (int i = 0; i < studentIds.length; i++) {
				Student studentDetails = new studentDetailsDAO().readUniqueObject(Integer.parseInt(studentIds[i]));
				List<Marks> marksDetails = new MarksDetailsDAO().readMarksforStudent(Integer.parseInt(studentIds[i]),httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
				marksList[i][0] = studentDetails.getAdmissionnumber();
				marksList[i][1] = studentDetails.getName();
				int k = 2;
				int a=0;
				int b=0;
				
				for (int m=0; m<marksDetails.size(); m++) {
					b=0;
					if(examsubjectCombo[a][b].compareTo(marksDetails.get(m).getSubid())==0 ){
						b++;
						if(examsubjectCombo[a][b].compareTo(marksDetails.get(m).getExamid())==0){
							try{
							marksList[i][k] = marksDetails.get(m).getMarksobtained().toString();
							k++;
							}catch(Exception e){
								e.printStackTrace();
							}
						}
					}else{
						marksList[i][k] = "0";
						k++;
						m--;
					}
					a++;
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
		if (copyReportCard()) {

			try {
				FileInputStream inputStream = new FileInputStream(
						new File(new DataUtil().getPropertiesValue("reportcardpathdirectory")));
				String pathOfReportCard = new DataUtil().getPropertiesValue("reportcardpathdirectory");
				httpSession.setAttribute("reportcardpath", pathOfReportCard);
				Workbook workbook = WorkbookFactory.create(inputStream);

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

				inputStream.close();

				FileOutputStream outputStream = new FileOutputStream(
						new DataUtil().getPropertiesValue("reportcardpathdirectory"));
				workbook.write(outputStream);
				// workbook.close();
				outputStream.close();
				result = true;
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return result;

	}

	@SuppressWarnings("finally")
	private boolean copyReportCard() {
		boolean result = false;
		InputStream is = null;
		OutputStream os = null;
		String destFile = new DataUtil().getPropertiesValue("reportcardpathdirectory");

		try {
			is = this.getClass().getClassLoader().getResourceAsStream("reportCardTemplate.xlsx");
			;
			os = new FileOutputStream(destFile);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				is.close();
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}

	}

    public void enterMarks() {
        new ExamLevelService(request, response).examLevels();
        new LanguageService(request, response).viewLanguage();
        new BranchService(request, response).viewBranches();
        httpSession.setAttribute("studentslist", "");
        httpSession.setAttribute("subjectlistevaluation", "");
        httpSession.setAttribute("totalstudentevaluation", "");
    }

	public void SearchMarksEntry() {

		if(httpSession.getAttribute(BRANCHID)!=null){
		    
		    String examLevel = DataUtil.emptyString(request.getParameter("examlevel"));
		    String subjectName = DataUtil.emptyString(request.getParameter("subjectnameAjax"));
		    
            if(subjectName==null || subjectName.isEmpty()) {
            	subjectName = DataUtil.emptyString(request.getParameter("subjectselected"));
            }
            
		       String searchQuery = "From Parents as parent where ";
		       String subQuery =null;
		       
		            if(!request.getParameter("centercode").equalsIgnoreCase("")) {
		                String[] centerCode = request.getParameter("centercode").split(":");
		                subQuery = "parent.Student.centercode = '"+centerCode[0]+"'";
		                httpSession.setAttribute("printcentername", "Center Name: "+centerCode[1]);
		                httpSession.setAttribute("printcentercode", "Center Code: "+centerCode[0]);
		                httpSession.setAttribute("evaluationsheetcentersearch", centerCode[0]+":"+centerCode[1]);
		            }else {
		                httpSession.setAttribute("evaluationsheetcentersearch", "");
		            }
		            
		            if(!request.getParameter("examlevel").equalsIgnoreCase("")) {
		                if(subQuery!=null) {
		                    subQuery = subQuery+" AND parent.Student.examlevel = '"+examLevel+"'";
		                }else {
		                    subQuery = "parent.Student.examlevel = '"+examLevel+"'";
		                }
		                httpSession.setAttribute("printexamlevel", "Examination Level: "+examLevel);
		                httpSession.setAttribute("evaluationsheetexamlevelsearch", request.getParameter("examlevel").toString());
		                httpSession.setAttribute("examselected", examLevel);
		                
		            }else {
		                httpSession.setAttribute("evaluationsheetexamlevelsearch", "");
		                httpSession.setAttribute("examselected", "");
		            }
		            
		            if(!request.getParameter("languageopted").equalsIgnoreCase("")) {
                        if(subQuery!=null) {
                            subQuery = subQuery+" AND languageopted = '"+request.getParameter("languageopted")+"'";
                        }else {
                            subQuery = "languageopted = '"+request.getParameter("languageopted")+"'";
                        }
                        httpSession.setAttribute("printlanguage", "Language: "+request.getParameter("languageopted").toString());
                        httpSession.setAttribute("languagesearch", request.getParameter("languageopted").toString());
		            }else {
		                httpSession.setAttribute("languagesearch", "");
		            }
		            
		            
		            List<Studentdailyattendance> studentDailyAttendanceOne = new ArrayList<Studentdailyattendance>();
		           
	                            studentDailyAttendanceOne = new AttendanceDAO().getStudentsAttendance(""
	                                    + "from Studentdailyattendance where  examlevelcode='"+examLevel+"'"
	                                    		+ " and subject='"+subjectName+"' and attendancestatus='Present'");
		            
	                            searchQuery = searchQuery+subQuery;
	                            
	                            List<String> studentExternalId = new ArrayList<String>();
	                            for (Studentdailyattendance studentdailyattendance : studentDailyAttendanceOne) {
	                            	studentExternalId.add(studentdailyattendance.getAttendeeid());
								}
	                            
	                            List<Parents> parentsList = new studentDetailsDAO().getStudentsListAttendance(searchQuery, studentExternalId);
		            
		            httpSession.setAttribute("studentslist", parentsList);
		            
		            new ExamLevelService(request, response).examLevels();
		            new BranchService(request, response).viewBranches();
		            new LanguageService(request, response).viewLanguage();
		            
		            request.setAttribute("subjectselected", subjectName);
		            httpSession.setAttribute("searchedexamlevel", DataUtil.emptyString(request.getParameter("examlevel")));
		            httpSession.setAttribute("searchedsubject", subjectName);
		}
	}
	
	
	public String addMarksNew() {


		String result = "false";

		String[] studentIds = request.getParameterValues("studentIDs");
		String[] studentsMarks = request.getParameterValues("studentMarks");
		String exam = request.getParameter("examselected");
		String subject = request.getParameter("subjectselected");
		logger.info("the subject id is " + subject + ", and exam level is " + exam);
		int sizeOfArray = 0;
		Map<Integer, String> mapOfMarks = new HashMap<Integer, String>();
		List<Integer> ids = new ArrayList<Integer>();
		List<String> studentsMarksList = new ArrayList<String>();

		if (studentIds != null && subject != null) {
		for (String sid : studentIds) {
                        String[] stdId = sid.split(":");
                        studentsMarksList.add(studentsMarks[Integer.parseInt(stdId[1])]);
                        ids.add(Integer.valueOf(stdId[0]));
                }
		
		if (studentsMarks != null) {

			for (String marksList : studentsMarks) {
				studentsMarksList.add(marksList);

			}
		}

			/*for (String id : studentIds) {
				System.out.println("id" + id);
				ids.add(Integer.valueOf(id));

			}*/

			sizeOfArray = ids.size();

			for (int i = 0; i < sizeOfArray; i++) {
				mapOfMarks.put(ids.get(i), studentsMarksList.get(i));
			}

			Set mapSet = mapOfMarks.entrySet();
			Iterator mapIterator = mapSet.iterator();

			
			
			List<Examleveldetails> examLevelDetails = new ExamLevelDetailsDAO().getExamLevelDetails(exam);
			
			int examid = examLevelDetails.get(0).getIdexamlevel();
			
			
			Subject subjectDetails = new SubjectDetailsService(request, response).getSubjectDetails(subject);
			int subid = subjectDetails.getSubid();
			List<Marks> marksList = new ArrayList<Marks>();

			while (mapIterator.hasNext()) {
				Map.Entry mapEntry = (Entry) mapIterator.next();
				    logger.info("The id is " + mapEntry.getKey() + "and marks is " + mapEntry.getValue());

				String test = (String) mapEntry.getValue();
				Marks marks = new Marks();
				marks.setExamid(examid);
				marks.setSubid(subid);
				marks.setSid((int) mapEntry.getKey());
				marks.setMarksobtained(Integer.parseInt(test));
				String currentYear = (String) httpSession.getAttribute(CURRENTACADEMICYEAR);
				marks.setAcademicyear(currentYear);
				marks.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
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

	public void SearchForEvaluationSheet() {

		if(httpSession.getAttribute(BRANCHID)!=null){
		    
		    
		    String[] examLevel = DataUtil.emptyString(request.getParameter("examlevel")).split(":");
		       String searchQuery = "From Parents as parent where ";
		       String subQuery =null;
		       
		            if(!request.getParameter("centercode").equalsIgnoreCase("")) {
		                String[] centerCode = request.getParameter("centercode").split(":");
		                subQuery = "parent.Student.centercode = '"+centerCode[0]+"'";
		                httpSession.setAttribute("printcentername", "Center Name: "+centerCode[1]);
		                httpSession.setAttribute("printcentercode", "Center Code: "+centerCode[0]);
		                httpSession.setAttribute("evaluationsheetcentersearch", centerCode[0]+":"+centerCode[1]);
		            }else {
		                httpSession.setAttribute("evaluationsheetcentersearch", "");
		            }
		            
		            if(!request.getParameter("examlevel").equalsIgnoreCase("")) {
		                if(subQuery!=null) {
		                    subQuery = subQuery+" AND parent.Student.examlevel = '"+examLevel[1]+"'";
		                }else {
		                    subQuery = "parent.Student.examlevel = '"+examLevel[1]+"'";
		                }
		                httpSession.setAttribute("printexamlevel", "Examination Level: "+examLevel[1]);
		                httpSession.setAttribute("evaluationsheetexamlevelsearch", request.getParameter("examlevel").toString());
		            }else {
		                httpSession.setAttribute("evaluationsheetexamlevelsearch", "");
		            }
		            
		            if(!request.getParameter("languageopted").equalsIgnoreCase("")) {
		                if(subQuery!=null) {
		                    subQuery = subQuery+" AND parent.Student.languageopted = '"+request.getParameter("languageopted")+"'";
		                }else {
		                    subQuery = "parent.Student.languageopted = '"+request.getParameter("languageopted")+"'";
		                }
		                httpSession.setAttribute("printlanguage", "Language: "+request.getParameter("languageopted").toString());
		                httpSession.setAttribute("evaluationsheetlanguagesearch",request.getParameter("languageopted").toString());
		            }else {
		                httpSession.setAttribute("evaluationsheetlanguagesearch","");
		            }
		            
		            searchQuery = searchQuery+subQuery+ " Order By parent.Student.admissionnumber ASC";
		            List<Parents> parentsList = new studentDetailsDAO().getStudentsList(searchQuery);
		            
		            //New Code
		            List<Subexamlevel>  subExamList =  new ExamLevelService(request, response).getSubExamLevelSubject(examLevel[1]);
		            Map<Subexamlevel,List<Parents>> parentsExam = new HashMap<Subexamlevel,List<Parents>>();
		            
		            for (Subexamlevel subexamlevel : subExamList) {
		            	 
		            	List<Parents> listParents = new ArrayList<Parents>();
		            	 
		            
		            List<String> studentExternalId = new ArrayList<String>();
		            List<Studentdailyattendance> studentDailyAttendance =  new AttendanceDAO().getStudentsAttendance("from Studentdailyattendance where examlevelcode='"+examLevel[1]+"' and attendancestatus='Present' and subject='"+subexamlevel.getSubjectname()+"'");
                            
                            for (Studentdailyattendance studentdailyattendance2 : studentDailyAttendance) {
                            	studentExternalId.add(studentdailyattendance2.getAttendeeid());
							}
                            
                            for (Parents parent : parentsList) {
                            
								if(studentExternalId.contains(parent.getStudent().getStudentexternalid())) {
										System.out.println("************ True external id "+parent.getStudent().getStudentexternalid());
										listParents.add(parent);
										
							}
								
                            }
                            
                            if(listParents.size()>0) {
                            	parentsExam.put(subexamlevel, listParents);
                            }
                            
		            }   
		            httpSession.setAttribute("mapstudentsexam", parentsExam);
		            //End New Code
		            
		           /* 
		            Map<Parents,String> mapStudentReports = new LinkedHashMap<Parents,String>();
		            
		            for (Parents parents : parentsList) {
		                Branch centerName = new BranchDAO().getBranch(parents.getStudent().getCentercode());
		                mapStudentReports.put(parents, centerName.getCentername());
		            }
		            //Musaib httpSession.setAttribute("mapstudentreports", mapStudentReports);
		            */		            
		            
		            httpSession.setAttribute("totalstudentevaluation", parentsList.size());
		            new ExamLevelService(request, response).examLevels();
		            new LanguageService(request, response).viewLanguage();
		            new BranchService(request, response).viewBranches();

		            //List<Subexamlevel>  subExamList =  new ExamLevelService(request, response).getSubExamLevelSubject(examLevel[1]);
		            request.setAttribute("subjectlist", subExamList);
		            httpSession.setAttribute("subjectlistevaluation", subExamList);
		            request.setAttribute("searchedexamlevel", DataUtil.emptyString(request.getParameter("examlevel")));
		            request.setAttribute("subjectentermarks", DataUtil.emptyString(request.getParameter("subject")));
		}
	}

	public boolean checkMarks(String sid, Integer subid, Integer idexamlevel, String academicYear) {
			List<Integer> sID = new ArrayList<Integer>();
			sID.add(Integer.parseInt(sid));
		
			List<Marks> marksList = new MarksDetailsDAO().readMarks(sID, subid, idexamlevel, academicYear);
		
		if(marksList.size()>0) {
			return true;
		}
			return false;
	}
	

}
