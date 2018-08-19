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

import com.global.Global;
import com.model.branch.dao.BranchDAO;
import com.model.branch.dto.Branch;
import com.model.branch.service.BranchService;
import com.model.examdetails.dao.ExamDetailsDAO;
import com.model.examdetails.dto.Exams;
import com.model.examlevels.dto.Examleveldetails;
import com.model.examlevels.dto.Subexamlevel;
import com.model.examlevels.service.ExamLevelService;
import com.model.language.service.LanguageService;
import com.model.marksdetails.dao.MarksDetailsDAO;
import com.model.marksdetails.dto.Marks;
import com.model.parents.dto.Parents;
import com.model.qualification.service.QualificationService;
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
	
	public MarksDetailsService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

	public String addMarks() {

		String result = "false";

		String[] studentIds = request.getParameterValues("studentIDs");
		String[] studentsMarks = request.getParameterValues("studentMarks");
		String[] examList = request.getParameter("hiddensearchedexamlevel").split(":");
		String exam = examList[0];
		String subject = request.getParameter("subject");
		System.out.println("the subject id is " + subject + ", and exam level is " + exam);
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
				System.out.println("student Marks" + marksList);
				studentsMarksList.add(marksList);

			}
		}

			/*for (String id : studentIds) {
				System.out.println("id" + id);
				ids.add(Integer.valueOf(id));

			}*/

			sizeOfArray = ids.size();

			System.out.println("id length" + studentIds.length);

			for (int i = 0; i < sizeOfArray; i++) {
				mapOfMarks.put(ids.get(i), studentsMarksList.get(i));
			}

			Set mapSet = mapOfMarks.entrySet();
			Iterator mapIterator = mapSet.iterator();

			int examid = Integer.parseInt(exam);
			Subject subjectDetails = new SubjectDetailsService(request, response).getSubjectDetails(subject);
			int subid = subjectDetails.getSubid();
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
		            }
		            
		            if(!request.getParameter("examlevel").equalsIgnoreCase("")) {
		                if(subQuery!=null) {
		                    subQuery = subQuery+"AND parent.Student.examlevel = '"+examLevel[1]+"'";
		                }else {
		                    subQuery = "parent.Student.examlevel = '"+examLevel[1]+"'";
		                }
		                httpSession.setAttribute("printexamlevel", "Examination Level: "+examLevel[1]);
		            }
		            
		            if(!request.getParameter("languageopted").equalsIgnoreCase("")) {
		                if(subQuery!=null) {
		                    subQuery = subQuery+"AND parent.Student.languageopted = '"+request.getParameter("languageopted")+"'";
		                }else {
		                    subQuery = "parent.Student.languageopted = '"+request.getParameter("languageopted")+"'";
		                }
		                httpSession.setAttribute("printlanguage", "Language: "+request.getParameter("languageopted").toString());
		            }
		            
		            searchQuery = searchQuery+subQuery;
		            List<Parents> parentsList = new studentDetailsDAO().getStudentsList(searchQuery);
		            Map<Parents,String> mapStudentReports = new HashMap<Parents,String>();
		            
		            for (Parents parents : parentsList) {
		                Branch centerName = new BranchDAO().getBranch(parents.getStudent().getCentercode());
		                mapStudentReports.put(parents, centerName.getCentername());
		            }
		            httpSession.setAttribute("mapstudentreports", mapStudentReports);
		            new ExamLevelService(request, response).examLevels();
		            new LanguageService(request, response).viewLanguage();
		            new BranchService(request, response).viewBranches();

		            List<Subexamlevel>  subExamList =  new ExamLevelService(request, response).getSubExamLevelSubject(examLevel[1]);
		            request.setAttribute("subjectlist", subExamList);
		            httpSession.setAttribute("subjectlistevaluation", subExamList);
		            request.setAttribute("searchedexamlevel", DataUtil.emptyString(request.getParameter("examlevel")));
		}
	}

	public boolean viewMarks() {
		
		if(httpSession.getAttribute(BRANCHID)!=null){
		    
	                    String examLevel = DataUtil.emptyString(request.getParameter("examlevel"));
	                    String subjectName = DataUtil.emptyString(request.getParameter("subjectnameAjax"));
	                    String academicYear = DataUtil.emptyString(request.getParameter("academicyear"));
	                    
	                       String searchQuery = "From Parents as parent where ";
	                       String subQuery =null;
	                       
	                            if(!request.getParameter("centercode").equalsIgnoreCase("")) {
	                                String[] centerCode = request.getParameter("centercode").split(":");
	                                subQuery = "parent.Student.centercode = '"+centerCode[0]+"'";
	                                httpSession.setAttribute("printcentername", "Center Name: "+centerCode[1]);
	                            }
	                            
	                            if(!request.getParameter("examlevel").equalsIgnoreCase("")) {
	                                if(subQuery!=null) {
	                                    subQuery = subQuery+"AND parent.Student.examlevel = '"+examLevel+"'";
	                                }else {
	                                    subQuery = "parent.Student.examlevel = '"+examLevel+"'";
	                                }
	                                httpSession.setAttribute("printexamlevel", "Examination Level: "+examLevel);
	                            }
	                            
	                            if(!request.getParameter("languageopted").equalsIgnoreCase("")) {
	                                if(subQuery!=null) {
	                                    subQuery = subQuery+"AND parent.Student.languageopted = '"+request.getParameter("languageopted")+"'";
	                                }else {
	                                    subQuery = "parent.Student.languageopted = '"+request.getParameter("languageopted")+"'";
	                                }
	                                httpSession.setAttribute("printlanguage", "Language: "+request.getParameter("languageopted").toString());
	                            }
	                            
	                            searchQuery = searchQuery+subQuery;
	                            List<Parents> parentsList = new studentDetailsDAO().getStudentsList(searchQuery);
	                            Map<Parents,String> mapStudentReports = new HashMap<Parents,String>();
	                            
		
		List<Examleveldetails> examLevelDetails = new ExamLevelService(request, response).getExamLevelDetails(examLevel);
		Subject subjectDetails = new SubjectDetailsService(request, response).getSubjectDetails(subjectName);
		
		Integer examId = examLevelDetails.get(0).getIdexamlevel();
                Integer subjectId = subjectDetails.getSubid();
                
		List<Parents> newStudentList = new ArrayList<Parents>();
		List<Marks> newMarksDetails = new ArrayList<Marks>();
		Map<Parents,Marks> marksStudentMap = new HashMap<Parents,Marks>();
		
		for (Parents parents : parentsList) {
		        
			Marks singleMarksDetails = new MarksDetailsDAO().readMarks(parents.getStudent().getSid(),subjectId,examId,academicYear);
			if(singleMarksDetails!=null) {
			    marksStudentMap.put(parents, singleMarksDetails);  
			}
		}

		request.setAttribute("marksstudentmap", marksStudentMap);
		request.setAttribute("subjectselected", subjectName);
		request.setAttribute("examselected", examLevel);
		
                new ExamLevelService(request, response).examLevels();
                new LanguageService(request, response).viewLanguage();
                new BranchService(request, response).viewBranches();
    
		}
		
		return true;
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
        httpSession.setAttribute("mapstudentreports", "");
        httpSession.setAttribute("subjectlistevaluation", "");
        
    }

}
