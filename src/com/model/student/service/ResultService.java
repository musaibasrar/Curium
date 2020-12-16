package com.model.student.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.model.branch.dao.BranchDAO;
import com.model.branch.dto.Branch;
import com.model.branch.service.BranchService;
import com.model.examlevels.dao.ExamLevelDetailsDAO;
import com.model.examlevels.dto.Examleveldetails;
import com.model.examlevels.dto.Subexamlevel;
import com.model.examlevels.service.ExamLevelService;
import com.model.language.service.LanguageService;
import com.model.marksdetails.dao.MarksDetailsDAO;
import com.model.marksdetails.dto.Marks;
import com.model.parents.dto.Parents;
import com.model.qualification.service.QualificationService;
import com.model.referencebooks.dao.ReferenceBooksDAO;
import com.model.referencebooks.dto.Referencebooks;
import com.model.std.dto.Classhierarchy;
import com.model.student.dao.studentDetailsDAO;
import com.model.student.dto.Student;
import com.model.subjectdetails.dao.SubjectDetailsDAO;
import com.model.subjectdetails.dto.Subject;
import com.util.DataUtil;
import com.util.MarksSheet;
import com.util.Result;
import com.util.ResultAnalysis;

public class ResultService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;
	private String BRANCHID = "branchid";
	private String CURRENTACADEMICYEAR = "currentAcademicYear";
	private static final Logger logger = LogManager.getLogger(ResultService.class);
	/**
    * Size of a byte buffer to read/write file
    */
   private static final int BUFFER_SIZE = 4096;
	
	public ResultService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

	    public void marksSheet() {
	        new ExamLevelService(request, response).examLevels();
	        new LanguageService(request, response).viewLanguage();
	        new BranchService(request, response).viewBranches();
	        new QualificationService(request, response).viewQualification();
	                
        	     httpSession.setAttribute("markssheetlist", "");
                 httpSession.setAttribute("markssheetsubexamlevel", "");
	             httpSession.setAttribute("markssheetyear", "");
	             httpSession.setAttribute("totalstudentresult", "");
	             httpSession.setAttribute("failcount", "");
	             httpSession.setAttribute("passcount", "");
	             httpSession.setAttribute("secondcount", "");
	             httpSession.setAttribute("firstcount", "");
	             httpSession.setAttribute("distinction", "");
	    }
	
	
    public void resultReport() {
        new ExamLevelService(request, response).examLevels();
        new LanguageService(request, response).viewLanguage();
        new BranchService(request, response).viewBranches();
        new QualificationService(request, response).viewQualification();
        httpSession.setAttribute("resultlist", "");
        httpSession.setAttribute("ranklistreport", "");
        httpSession.setAttribute("resultsubexamlevel", "");
        httpSession.setAttribute("resultcentername", "");
        httpSession.setAttribute("resultcentercode", "");
        httpSession.setAttribute("resultexamlevel", "");
        httpSession.setAttribute("resultlanguage", "");
        httpSession.setAttribute("resultqualification", "");
        httpSession.setAttribute("totalstudentresult", "");
        httpSession.setAttribute("failcount", "");
        httpSession.setAttribute("passcount", "");
        httpSession.setAttribute("secondcount", "");
        httpSession.setAttribute("firstcount", "");
        httpSession.setAttribute("distinction", "");
        httpSession.setAttribute("resultlistadmno", "");
    }

    public void searchResultReport() {
       String examLevel = request.getParameter("examlevelcode");
       String[] examLevelCode = examLevel.split(":");
       String examYear = request.getParameter("examyear");
       //String ForExamLevel = request.getParameter("forexamlevel");
       
       String language = null;
       String searchQuery = "From Parents as parent where ";
       String subQuery =null;
       
            if(!request.getParameter("centercode").equalsIgnoreCase("")) {
                String[] centerCode = request.getParameter("centercode").split(":");
                subQuery = "parent.Student.centercode = '"+centerCode[0]+"'"; 
                httpSession.setAttribute("resultservicecentersearch", centerCode[0]+":"+centerCode[1]);
            }else {
                httpSession.setAttribute("resultservicecentersearch", "");
            }
            
            if(!request.getParameter("examlevelcode").equalsIgnoreCase("")) {
                
                if(subQuery!=null) {
                    subQuery = subQuery+" AND parent.Student.examlevel = '"+examLevelCode[0]+"'";
                }else {
                    subQuery = "parent.Student.examlevel = '"+examLevelCode[0]+"'";
                }
                httpSession.setAttribute("resultserviceexamlevelsearch", request.getParameter("examlevelcode").toString());
                //httpSession.setAttribute("forresultserviceexamlevelsearch", request.getParameter("forexamlevel").toString());
            }else {
                httpSession.setAttribute("resultserviceexamlevelsearch", "");
               // httpSession.setAttribute("forresultserviceexamlevelsearch", "");
            }
            
            if(!request.getParameter("languageopted").equalsIgnoreCase("")) {
                language = request.getParameter("languageopted");
                if(subQuery!=null) {
                    subQuery = subQuery+" AND parent.Student.languageopted = '"+request.getParameter("languageopted")+"'";
                }else {
                    subQuery = "parent.Student.languageopted = '"+request.getParameter("languageopted")+"'";
                }
                httpSession.setAttribute("resultservicelanguagesearch",request.getParameter("languageopted").toString());
            }else {
                httpSession.setAttribute("resultservicelanguagesearch","");
            }
            
            if(!DataUtil.emptyString(request.getParameter("examyear")).equalsIgnoreCase("")) {
            	httpSession.setAttribute("resultexamyear",examYear);
            }else {
            	httpSession.setAttribute("resultexamyear","");
            }
            
            /*if(!DataUtil.emptyString(request.getParameter("academicyear")).equalsIgnoreCase("")) {
                if(subQuery!=null) {
                    String subAcademicYear = request.getParameter("academicyear").toString().substring(2, 4);
                    
                    subQuery = subQuery+" AND parent.Student.admissionnumber like '"+subAcademicYear+"%'";
                }else {
                    subQuery = "parent.Student.admissionnumber like '\"+subAcademicYear+\"%'";
                }
                httpSession.setAttribute("resultserviceacademicsearch",request.getParameter("academicyear").toString());
            }else {
                httpSession.setAttribute("resultserviceacademicsearch","");
            }*/
            
            searchQuery = searchQuery+subQuery+ " AND parent.Student.archive = 0 AND parent.Student.passedout = 0 AND parent.Student.droppedout = 0 AND (parent.Student.remarks = 'approved' OR parent.Student.remarks = 'admin') Order By parent.Student.admissionnumber ASC";
            List<Parents> parentsList = new studentDetailsDAO().getStudentsList(searchQuery);
            
            //Query Class Hierarchy to get the exam level (levelcode)
            String subExamYear = examYear.substring(0, 4);
            String currentAcademicYear = httpSession.getAttribute("currentAcademicYear").toString().substring(0, 4);
            int differenceYear = Integer.parseInt(currentAcademicYear)-Integer.parseInt(subExamYear);
            List<String> classHierarchy = new ExamLevelDetailsDAO().getClassHierarchyLowerClass(examLevelCode[0]);
            int listItem = differenceYear - 1;
           
            if(listItem<=classHierarchy.size() || differenceYear == 0) {
           	 String forExamLevelCode = null;
           	 if(differenceYear == 0) {
           		 forExamLevelCode = examLevelCode[0];
           	 }else {
           		 forExamLevelCode = classHierarchy.get(listItem);
           	 }
	                        
	            //END Query Class Hierarchy to get the exam level
	            
	            //Query examleveldetails where levelcode is 
	            List<Examleveldetails> examLevelDetails = new ExamLevelDetailsDAO().getExamLevelDetails(forExamLevelCode);
	            //END Query examleveldetails where levelcode is
	            
	            //String[] examDet = ForExamLevel.split(":");
	            List<Subexamlevel> subList = new ExamLevelDetailsDAO().getSubExamLevelSubject(examLevelDetails.get(0).getLevelcode());
	            List<Result> resultList = new ArrayList<Result>();
	            int failCounter=0,passCounter=0,secondCounter=0,firstCounter=0,distinctionCounter=0,absentCounter=0;
	            
	            for (Parents studentDetails : parentsList) {
	                Result result = new Result();
	                List<Integer> marksList = new ArrayList<Integer>();
	                List<String> subjectList = new ArrayList<String>();
	                int marksObtained = 0;
	                int totalMarks = 0;
	                String finalResult = null;
	                
	                    for (Subexamlevel subexamlevel : subList) {
	                        Subject subjectIds = new SubjectDetailsDAO().getSubjectDetails(subexamlevel.getSubjectname());
	                        Marks marks = new MarksDetailsDAO().readMarksPerStudent(studentDetails.getStudent().getSid(), subjectIds.getSubid(), examLevelDetails.get(0).getIdexamlevel(), examYear);
	                        subjectList.add(subjectIds.getSubjectname());
	                        
	                        if(marks!=null) {
	                            marksList.add(marks.getMarksobtained());
	                            marksObtained = marksObtained+marks.getMarksobtained();
	                        }else {
	                            marksList.add(0);
	                            finalResult = "ABSENT";
	                        }
	                        
	                        totalMarks = totalMarks+subjectIds.getMaxmarks();
	                        if(marks!=null && marks.getMarksobtained() < subjectIds.getMinmarks()) {
	                            finalResult = "FAIL";
	                        }
	                    }
	                    
	                    String res = null;
	                    double percentage = 0;
	                    		
	                    if(totalMarks>0) {
	                    	percentage = (marksObtained*100)/totalMarks;
	                    	percentage = Math.round(percentage);
	                    	int percent = (int) percentage;
	                        res = getResultClass(percent);
	                    }else {
	                    	res = "No Result";
	                    }
	                    
	                    
	                    if(finalResult==null) {
	                        finalResult = res;
	                    }
	                    
	                    if("FAIL".equalsIgnoreCase(finalResult)) {
	                        failCounter++;
	                    }else if("PASS".equalsIgnoreCase(finalResult)) {
	                        passCounter++;
	                    }else if("SECOND CLASS".equalsIgnoreCase(finalResult)) {
	                        secondCounter++;
	                    }else if("FIRST CLASS".equalsIgnoreCase(finalResult)) {
	                        firstCounter++;
	                    }else if("DISTINCTION".equalsIgnoreCase(finalResult)) {
	                        distinctionCounter++;
	                    }else if("ABSENT".equalsIgnoreCase(finalResult)) {
	                    	absentCounter++;
	                    }
	                    
	                    result.setStudent(studentDetails.getStudent());
	                    result.setSubjectList(subjectList);
	                    result.setMarksList(marksList);
	                    result.setPercentage(percentage);
	                    result.setResultclass(finalResult);
	                    resultList.add(result);
	            }
	            httpSession.setAttribute("totalstudentresult", parentsList.size());
	            httpSession.setAttribute("failcount", failCounter);
	            httpSession.setAttribute("passcount", passCounter);
	            httpSession.setAttribute("secondcount", secondCounter);
	            httpSession.setAttribute("firstcount", firstCounter);
	            httpSession.setAttribute("distinctioncount", distinctionCounter);
	            httpSession.setAttribute("absentcount", absentCounter);
	            httpSession.setAttribute("resultlist", resultList);
	            httpSession.setAttribute("resultsubexamlevel", subList);
	            
	            if(language==null) {
	                language = "ALL";
	            }
            }else {
            	httpSession.setAttribute("resultlist", "");
                httpSession.setAttribute("ranklistreport", "");
                httpSession.setAttribute("resultsubexamlevel", "");
                httpSession.setAttribute("resultcentername", "");
                httpSession.setAttribute("resultcentercode", "");
                httpSession.setAttribute("resultexamlevel", "");
                httpSession.setAttribute("resultlanguage", "");
                httpSession.setAttribute("resultqualification", "");
                httpSession.setAttribute("totalstudentresult", "");
                httpSession.setAttribute("failcount", "");
                httpSession.setAttribute("passcount", "");
                httpSession.setAttribute("secondcount", "");
                httpSession.setAttribute("firstcount", "");
                httpSession.setAttribute("distinction", "");
                httpSession.setAttribute("resultlistadmno", "");
            }
            String[] centerCodeName = DataUtil.emptyString(request.getParameter("centercode")).split(":");
            String[] examLevelCodeName = DataUtil.emptyString(request.getParameter("examlevelcode")).split(":");
            httpSession.setAttribute("resultcentername",  "Center Code/Name:  "+centerCodeName[0]+"/"+centerCodeName[1]);
            httpSession.setAttribute("resultcentercode",  "Center Code:  "+centerCodeName[0]);
            httpSession.setAttribute("resultexamlevel", "Examination Code:  "+examLevelCodeName[0]);
            
            httpSession.setAttribute("resultlanguage", "Language: "+language);
            new ExamLevelService(request, response).examLevels();
            new LanguageService(request, response).viewLanguage();
            new BranchService(request, response).viewBranches();
            new QualificationService(request, response).viewQualification();
    }

    private String getResultClass(double percentage) {
        
        if(percentage < 35) {
            return "FAIL";
        }else if(percentage <= 49) {
            return "PASS";
        }else if(percentage <= 59) {
            return "SECOND CLASS";
        }else if(percentage <= 74) {
            return "FIRST CLASS";
        }else if(percentage <= 100) {
            return "Distinction";
        }else {
            return "";
        }
        
    }

    public void searchRankListReport() {
        
        String examLevel = request.getParameter("examlevelcode");
        String examYear = request.getParameter("examyear");
        String centerValue = request.getParameter("centervalue");
        String centerCodeMain = request.getParameter("centercode");
        String languageOptedMain = request.getParameter("languageopted");
        String qualificationMain = request.getParameter("qualification");
        String examYearMain = request.getParameter("examyear");
        List<Examleveldetails> examLevellist = new ArrayList<Examleveldetails>();
        List<String> examList = new ArrayList<String>();
        int parentsListMain = 0;
        int failCounterMain=0,passCounterMain=0,secondCounterMain=0,firstCounterMain=0,distinctionCounterMain=0;
        List<Result> resultListMain = new ArrayList<Result>();
        List<Result> rankListReportListMain = new ArrayList<Result>();
        List<Result> resultListRLMain = new LinkedList<Result>();
        List<List<Result>> resultListFailMain = new ArrayList<List<Result>>();
        List<Subexamlevel> subListMain = new ArrayList<Subexamlevel>();
        String language = null;
        
        
        if(examLevel.equalsIgnoreCase("ALL")) {
        	
        	 examLevellist = new ExamLevelDetailsDAO().readListOfObjects();
        	 
        	 for (Examleveldetails examleveldetails : examLevellist) {
        		 examList.add(examleveldetails.getLevelcode()+":"+examleveldetails.getIdexamlevel());
			}
        	 
        }else {
        	examList.add(examLevel);
        }
        
        	for (String examListIteration : examList) {
        		
        		String searchQuery = null;
                String subQuery =null;
                searchQuery = "From Parents as parent where ";
        		
        		String[] examLevelCode = examListIteration.split(":");
        	
             if(!centerCodeMain.isEmpty()) {
                 String[] centerCode = centerCodeMain.split(":");
                 subQuery = "parent.Student.centercode = '"+centerCode[0]+"'";
                 httpSession.setAttribute("ranklistcentersearch", centerCode[0]+":"+centerCode[1]);
             }else if("true".equalsIgnoreCase(centerValue)){
            	 Branch branch = new BranchDAO().getBranch(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
                 if(branch!=null) {
                     subQuery = "parent.Student.centercode = '"+branch.getCentercode()+"'";
                     httpSession.setAttribute("ranklistcentersearch", branch.getCentercode()+":"+branch.getCentername());
                 }
             }else {
                 httpSession.setAttribute("ranklistcentersearch", "");
             }
             
             
             if(subQuery!=null) {
                 subQuery = subQuery+" AND parent.Student.examlevel = '"+examLevelCode[0]+"'";
             }else {
                 subQuery = "parent.Student.examlevel = '"+examLevelCode[0]+"'";
             }
             httpSession.setAttribute("ranklistexamlevelsearch", request.getParameter("examlevelcode").toString());
             
             /*
             
             if(!request.getParameter("examlevelcode").equalsIgnoreCase("")) {
                 
                 if(subQuery!=null) {
                     subQuery = subQuery+" AND parent.Student.examlevel = '"+examLevelCode[0]+"'";
                 }else {
                     subQuery = "parent.Student.examlevel = '"+examLevelCode[0]+"'";
                 }
                 httpSession.setAttribute("ranklistexamlevelsearch", request.getParameter("examlevelcode").toString());
                 //httpSession.setAttribute("forranklistexamlevelsearch", request.getParameter("forexamlevel").toString());
             }else {
                 httpSession.setAttribute("ranklistexamlevelsearch", "");
                 //httpSession.setAttribute("forranklistexamlevelsearch", "");
             }*/
             
             if(!languageOptedMain.isEmpty()) {
                 language = request.getParameter("languageopted");
                 if(subQuery!=null) {
                     subQuery = subQuery+" AND parent.Student.languageopted = '"+request.getParameter("languageopted")+"'";
                 }else {
                     subQuery = "parent.Student.languageopted = '"+request.getParameter("languageopted")+"'";
                 }
                 httpSession.setAttribute("ranklistlanguagesearch",request.getParameter("languageopted").toString());
             }else {
                 httpSession.setAttribute("ranklistlanguagesearch","");
             }
             

             if(!qualificationMain.isEmpty()) {
                 if(subQuery!=null) {
                     subQuery = subQuery+" AND parent.Student.qualification = '"+request.getParameter("qualification")+"'";
                 }else {
                     subQuery = "parent.Student.qualification = '"+request.getParameter("qualification")+"'";
                 }
                 httpSession.setAttribute("resultqualification", "Qualification: "+request.getParameter("qualification").toString());
                 httpSession.setAttribute("ranklistqualificationsearch", request.getParameter("qualification").toString());
             }else {
                 httpSession.setAttribute("ranklistqualificationsearch", "");
             }
             
             if(!examYearMain.isEmpty()) {
            	 httpSession.setAttribute("ranklistexamyear",examYear);
             }else {
            	 httpSession.setAttribute("ranklistexamyear","");
             }
             
             /*if(!DataUtil.emptyString(request.getParameter("academicyear")).equalsIgnoreCase("")) {
                 if(subQuery!=null) {
                     String subAcademicYear = request.getParameter("academicyear").toString().substring(2, 4);
                     
                     subQuery = subQuery+"AND parent.Student.admissionnumber like '"+subAcademicYear+"%'";
                 }else {
                     subQuery = "parent.Student.admissionnumber like '\"+subAcademicYear+\"%'";
                 }
                 httpSession.setAttribute("ranklistacademicsearch",request.getParameter("academicyear").toString());
             }else {
                 httpSession.setAttribute("ranklistacademicsearch","");
             }*/
             
             searchQuery = searchQuery+subQuery+ " AND parent.Student.archive = 0 AND parent.Student.passedout = 0 AND parent.Student.droppedout = 0 AND (parent.Student.remarks = 'approved' OR parent.Student.remarks = 'admin') Order By parent.Student.admissionnumber ASC";
             List<Parents> parentsList = new studentDetailsDAO().getStudentsList(searchQuery);
             
             //Query Class Hierarchy to get the exam level (levelcode)
             String subExamYear = examYear.substring(0, 4);
             String currentAcademicYear = httpSession.getAttribute("currentAcademicYear").toString().substring(0, 4);
             int differenceYear = Integer.parseInt(currentAcademicYear)-Integer.parseInt(subExamYear);
             List<String> classHierarchy = new ExamLevelDetailsDAO().getClassHierarchyLowerClass(examLevelCode[0]);
             int listItem = differenceYear - 1;
            
             if(listItem<=classHierarchy.size() || differenceYear == 0) {
            	 String forExamLevelCode = null;
            	 if(differenceYear == 0) {
            		 forExamLevelCode = examLevelCode[0];
            	 }else {
            		 forExamLevelCode = classHierarchy.get(listItem);
            	 }
 	                        
 	            //END Query Class Hierarchy to get the exam level
 	            
 	            //Query examleveldetails where levelcode is 
 	            List<Examleveldetails> examLevelDetails = new ExamLevelDetailsDAO().getExamLevelDetails(forExamLevelCode);
 	            //END Query examleveldetails where levelcode is
 	            
             //String[] examDet = forExamLevel.split(":");
             List<Subexamlevel> subList = new ExamLevelDetailsDAO().getSubExamLevelSubject(examLevelDetails.get(0).getLevelcode());
             List<Result> resultList = new ArrayList<Result>();
             List<Result> rankListReportList = new ArrayList<Result>();
             List<Result> resultListRL = new LinkedList<Result>();
             List<Result> resultListFail = new ArrayList<Result>();
             int failCounter=0,passCounter=0,secondCounter=0,firstCounter=0,distinctionCounter=0;
             
             for (Parents studentDetails : parentsList) {
                 Result result = new Result();
                 List<Integer> marksList = new ArrayList<Integer>();
                 List<String> subjectList = new ArrayList<String>();
                 double marksObtained = 0;
                 double totalMarks = 0;
                 String finalResult = null;
                 
                     for (Subexamlevel subexamlevel : subList) {
                         Subject subjectIds = new SubjectDetailsDAO().getSubjectDetails(subexamlevel.getSubjectname());
                         Marks marks = new MarksDetailsDAO().readMarksPerStudent(studentDetails.getStudent().getSid(), subjectIds.getSubid(), examLevelDetails.get(0).getIdexamlevel(), examYear);
                         subjectList.add(subjectIds.getSubjectname());
                         
                         if(marks!=null) {
                             marksList.add(marks.getMarksobtained());
                             marksObtained = marksObtained+marks.getMarksobtained();
                         }else {
                             marksList.add(0);
                             finalResult = "ABSENT";
                         }
                         
                         totalMarks = totalMarks+subjectIds.getMaxmarks();
                         if(marks!=null && marks.getMarksobtained() < subjectIds.getMinmarks()) {
                             finalResult = "FAIL";
                         }
                     }
                     
                     if (subList.size()==1) {
                             marksList.add(999);
                     }
                     
                     double percentage = (marksObtained*100)/totalMarks;
                     percentage = Math.round(percentage);
                     int percent = (int) percentage;
                     String res = getResultClass(percent);
                     
                     if(finalResult==null) {
                         finalResult = res;
                     }
                     
                     if("FAIL".equalsIgnoreCase(finalResult)) {
                         failCounter++;
                     }else if("PASS".equalsIgnoreCase(finalResult)) {
                         passCounter++;
                     }else if("SECOND CLASS".equalsIgnoreCase(finalResult)) {
                         secondCounter++;
                     }else if("FIRST CLASS".equalsIgnoreCase(finalResult)) {
                         firstCounter++;
                     }else if("DISTINCTION".equalsIgnoreCase(finalResult)) {
                         distinctionCounter++;
                     }
                     
                     result.setStudent(studentDetails.getStudent());
                     result.setSubjectList(subjectList);
                     result.setMarksList(marksList);
                     result.setPercentage(percentage);
                     result.setResultclass(finalResult);
                     
                     
                     if("DISTINCTION".equalsIgnoreCase(result.getResultclass())) {
                    	 rankListReportList.add(result);
                     }if("FIRST CLASS".equalsIgnoreCase(result.getResultclass())) {
                    	 rankListReportList.add(result);
                     }if("SECOND CLASS".equalsIgnoreCase(result.getResultclass())) {
                    	 rankListReportList.add(result);
                     }if("PASS".equalsIgnoreCase(result.getResultclass())) {
                    	 rankListReportList.add(result);
                     }
                     
                    if(!"FAIL".equalsIgnoreCase(result.getResultclass())  && !"ABSENT".equalsIgnoreCase(result.getResultclass())) {
                         resultList.add(result);
                         resultListRL.add(result);
                     }else if("FAIL".equalsIgnoreCase(result.getResultclass())){
                         resultListFail.add(result);
                         resultListRL.add(result);
                     }
                     
             }
             Collections.sort(resultList);
             Collections.sort(rankListReportList);
             //Collections.sort(resultListFail);

             int i=1;
             for (Result result : resultList) {
                 result.setRank(i);
                i++;
            }
             
             int j=1;
             for (Result result : rankListReportList) {
                 result.setRank(j);
                j++;
            }
             //resultList.addAll(resultListFail);
             //parentsListMain = parentsListMain+parentsList.size();
             failCounterMain = failCounterMain + failCounter;
             passCounterMain = passCounterMain + passCounter;
             secondCounterMain = secondCounterMain + secondCounter;
             firstCounterMain = firstCounterMain + firstCounter;
             distinctionCounterMain = distinctionCounterMain + distinctionCounter;
             
             //httpSession.setAttribute("totalstudentresult", parentsList.size());
             //httpSession.setAttribute("failcount", failCounter);
             //httpSession.setAttribute("passcount", passCounter);
             //httpSession.setAttribute("secondcount", secondCounter);
             //httpSession.setAttribute("firstcount", firstCounter);
             //httpSession.setAttribute("distinctioncount", distinctionCounter);
             
             for (Result reslist : resultList) {
            	 resultListMain.add(reslist);
			}
             
             for (Result rankList : rankListReportList) {
            	 rankListReportListMain.add(rankList);
			}
             
             for (Result rlList : resultListRL) {
            	 resultListRLMain.add(rlList);
			}
             
             subListMain = subList;
             //resultListMain.add(resultList);
             //rankListReportListMain.add(rankListReportList);
             //subListMain.add(subList);
             //resultListRLMain.add(resultListRL);
             
             //httpSession.setAttribute("resultlist", resultList);
             //httpSession.setAttribute("ranklistreport", rankListReportList);
             //httpSession.setAttribute("resultsubexamlevel", subList);
             //httpSession.setAttribute("resultlistadmno", resultListRL);
             
             if(language==null) {
                 language = "ALL";
             }
             
             }else {
            	 httpSession.setAttribute("resultlist", "");
                 httpSession.setAttribute("ranklistreport", "");
                 httpSession.setAttribute("resultsubexamlevel", "");
                 httpSession.setAttribute("resultcentername", "");
                 httpSession.setAttribute("resultcentercode", "");
                 httpSession.setAttribute("resultexamlevel", "");
                 httpSession.setAttribute("resultlanguage", "");
                 httpSession.setAttribute("resultqualification", "");
                 httpSession.setAttribute("totalstudentresult", "");
                 httpSession.setAttribute("failcount", "");
                 httpSession.setAttribute("passcount", "");
                 httpSession.setAttribute("secondcount", "");
                 httpSession.setAttribute("firstcount", "");
                 httpSession.setAttribute("distinction", "");
                 httpSession.setAttribute("resultlistadmno", "");
             }
             
             String[] centerCodeName = centerCodeMain.split(":");
             String[] examLevelCodeName = examListIteration.split(":");
             if(!"".equalsIgnoreCase(centerCodeName[0])) {
                 httpSession.setAttribute("resultcentername",  "Center Code/Name:  "+centerCodeName[0]+"/"+centerCodeName[1]);
                 httpSession.setAttribute("resultcentercode",  "Center Code:  "+centerCodeName[0]);
             }else {
                 httpSession.setAttribute("resultcentername",  "Center Code/Name:  ALL");
             }
             
             if(examLevel.equalsIgnoreCase("ALL")) {
            	 httpSession.setAttribute("resultexamlevel", "Examination Code:  ALL");
             }else {
            	 httpSession.setAttribute("resultexamlevel", "Examination Code:  "+examLevelCodeName[0]);
             }
             
             httpSession.setAttribute("resultlanguage", "Language: "+language);
             
        		}
                             
        	
        	 parentsListMain = parentsListMain + failCounterMain + passCounterMain + secondCounterMain + firstCounterMain + distinctionCounterMain;
        	  httpSession.setAttribute("totalstudentresult", parentsListMain);
              httpSession.setAttribute("failcount", failCounterMain);
              httpSession.setAttribute("passcount", passCounterMain);
              httpSession.setAttribute("secondcount", secondCounterMain);
              httpSession.setAttribute("firstcount", firstCounterMain);
              httpSession.setAttribute("distinctioncount", distinctionCounterMain);
              
              httpSession.setAttribute("resultlist", resultListMain);
              httpSession.setAttribute("ranklistreport", rankListReportListMain);
              httpSession.setAttribute("resultsubexamlevel", subListMain);
              httpSession.setAttribute("resultlistadmno", resultListRLMain);
              
              
             new ExamLevelService(request, response).examLevels();
             new LanguageService(request, response).viewLanguage();
             new BranchService(request, response).viewBranches();
             new QualificationService(request, response).viewQualification();
     }

    public void searchMarksSheet() {
        String examLevel = request.getParameter("examlevelcode");
        String[] examLevelCode = examLevel.split(":");
        String examYear = request.getParameter("examyear");
        
        String language = null;
        String searchQuery = "From Parents as parent where ";
        String subQuery =null;
        
             if(!request.getParameter("centercode").equalsIgnoreCase("")) {
                 String[] centerCode = request.getParameter("centercode").split(":");
                 subQuery = "parent.Student.centercode = '"+centerCode[0]+"'";
                 httpSession.setAttribute("markssheetcentersearch", centerCode[0]+":"+centerCode[1]);
             }else {
                 httpSession.setAttribute("markssheetcentersearch", "");
             }
             
             if(!request.getParameter("examlevelcode").equalsIgnoreCase("")) {
                 
                 if(subQuery!=null) {
                     subQuery = subQuery+" AND parent.Student.examlevel = '"+examLevelCode[0]+"'";
                 }else {
                     subQuery = "parent.Student.examlevel = '"+examLevelCode[0]+"'";
                 }
                 httpSession.setAttribute("markssheetexamlevelsearch", request.getParameter("examlevelcode").toString());
                // httpSession.setAttribute("formarkssheetexamlevelsearch", request.getParameter("forexamlevel").toString());
             }else {
                 httpSession.setAttribute("markssheetexamlevelsearch", "");
               //  httpSession.setAttribute("formarkssheetexamlevelsearch", "");
             }
             
             if(!request.getParameter("languageopted").equalsIgnoreCase("")) {
                 language = request.getParameter("languageopted");
                 if(subQuery!=null) {
                     subQuery = subQuery+" AND parent.Student.languageopted = '"+request.getParameter("languageopted")+"'";
                 }else {
                     subQuery = "parent.Student.languageopted = '"+request.getParameter("languageopted")+"'";
                 }
                 httpSession.setAttribute("markssheetlanguagesearch",request.getParameter("languageopted").toString());
             }else {
                 httpSession.setAttribute("markssheetlanguagesearch","");
             }
             

             if(!request.getParameter("qualification").equalsIgnoreCase("")) {
                 if(subQuery!=null) {
                     subQuery = subQuery+" AND parent.Student.qualification = '"+request.getParameter("qualification")+"'";
                 }else {
                     subQuery = "parent.Student.qualification = '"+request.getParameter("qualification")+"'";
                 }
                 httpSession.setAttribute("resultqualification", "Qualification: "+request.getParameter("qualification").toString());
                 httpSession.setAttribute("markssheetqualificationsearch", request.getParameter("qualification").toString());
             }else {
                 httpSession.setAttribute("markssheetqualificationsearch", "");
             }
          
             
             if(!DataUtil.emptyString(request.getParameter("examyear")).equalsIgnoreCase("")) {
	                if(subQuery!=null) {
	                    String subAcademicYear = request.getParameter("examyear").toString().substring(2, 4);
	                    String subCurrentAcademicYear = httpSession.getAttribute(CURRENTACADEMICYEAR).toString().substring(2, 4);
	                    
	                    subQuery = subQuery+"AND (parent.Student.admissionnumber like '"+subCurrentAcademicYear+"%'";
	                    subQuery = subQuery+" OR parent.Student.admissionnumber like '"+subAcademicYear+"%')";
	                }
	                httpSession.setAttribute("studentsreportacademicsearch", request.getParameter("examyear").toString());
	            }else {
	                httpSession.setAttribute("studentsreportacademicsearch", "");
	            }
             
             
             /*if(!DataUtil.emptyString(request.getParameter("academicyear")).equalsIgnoreCase("")) {
                 if(subQuery!=null) {
                     String subAcademicYear = request.getParameter("academicyear").toString().substring(2, 4);
                     
                     subQuery = subQuery+"AND parent.Student.admissionnumber like '"+subAcademicYear+"%'";
                 }else {
                     subQuery = "parent.Student.admissionnumber like '\"+subAcademicYear+\"%'";
                 }
                 httpSession.setAttribute("markssheetacademicsearch",request.getParameter("academicyear").toString());
             }else {
                 httpSession.setAttribute("markssheetacademicsearch","");
             }*/
             
             searchQuery = searchQuery+subQuery+" AND parent.Student.archive = 0 AND parent.Student.passedout = 0 AND parent.Student.droppedout = 0 AND (parent.Student.remarks = 'approved' OR parent.Student.remarks = 'admin') Order By parent.Student.admissionnumber ASC";
             List<Parents> parentsList = new studentDetailsDAO().getStudentsList(searchQuery);
             
             
             //Query Class Hierarchy to get the exam level (levelcode)
             String subExamYear = examYear.substring(0, 4);
             String currentAcademicYear = httpSession.getAttribute("currentAcademicYear").toString().substring(0, 4);
             int differenceYear = Integer.parseInt(currentAcademicYear)-Integer.parseInt(subExamYear);
             List<String> classHierarchy = new ExamLevelDetailsDAO().getClassHierarchyLowerClass(examLevelCode[0]);
             int listItem = differenceYear - 1;
            
             if(listItem<=classHierarchy.size() || differenceYear == 0) {
            	 String forExamLevelCode = null;
            	 if(differenceYear == 0) {
            		 forExamLevelCode = examLevelCode[0];
            	 }else {
            		 forExamLevelCode = classHierarchy.get(listItem);
            	 }
 	            
 	                        
 	            //END Query Class Hierarchy to get the exam level
 	            
 	            //Query examleveldetails where levelcode is 
 	            List<Examleveldetails> examLevelDetails = new ExamLevelDetailsDAO().getExamLevelDetails(forExamLevelCode);
 	            //END Query examleveldetails where levelcode is
 	            
             //String[] examDet = forExamLevel.split(":");
             List<Subexamlevel> subList = new ExamLevelDetailsDAO().getSubExamLevelSubject(examLevelDetails.get(0).getLevelcode());
             
             List<MarksSheet> resultList = new ArrayList<MarksSheet>();
             List<MarksSheet> resultListFail = new ArrayList<MarksSheet>();
             int failCounter=0,passCounter=0,secondCounter=0,firstCounter=0,distinctionCounter=0;
             
             for (Parents studentDetails : parentsList) {
                 MarksSheet result = new MarksSheet();
                 List<Integer> marksList = new ArrayList<Integer>();
                 List<String> subjectList = new ArrayList<String>();
                 double marksObtained = 0;
                 double totalMarks = 0;
                 String finalResult = null;
                 
                     for (Subexamlevel subexamlevel : subList) {
                         Subject subjectIds = new SubjectDetailsDAO().getSubjectDetails(subexamlevel.getSubjectname());
                         Marks marks = new MarksDetailsDAO().readMarksPerStudent(studentDetails.getStudent().getSid(), subjectIds.getSubid(), examLevelDetails.get(0).getIdexamlevel(), examYear);
                         
                         if(subList.size()==1) {
                        	 subjectList.add(subjectIds.getSubjectname().replaceAll("1",""));
                         }else {
                        	 subjectList.add(subjectIds.getSubjectname());
                         }
                         
                         
                         if(marks!=null) {
                             marksList.add(marks.getMarksobtained());
                             marksObtained = marksObtained+marks.getMarksobtained();
                         }else {
                             marksList.add(0);
                             finalResult = "ABSENT";
                         }
                         
                         totalMarks = totalMarks+subjectIds.getMaxmarks();
                         if(marks!=null && marks.getMarksobtained() < subjectIds.getMinmarks()) {
                             finalResult = "FAIL";
                         }
                     }
                     
                     double percentage = (marksObtained*100)/totalMarks;
                     percentage = Math.round(percentage);
                     int percent = (int) percentage;
                     String res = getResultClass(percent);
                     
                     
                     if(finalResult==null) {
                         finalResult = res;
                     }
                     
                     if("FAIL".equalsIgnoreCase(finalResult)) {
                         failCounter++;
                     }else if("PASS".equalsIgnoreCase(finalResult)) {
                         passCounter++;
                     }else if("SECOND CLASS".equalsIgnoreCase(finalResult)) {
                         secondCounter++;
                     }else if("FIRST CLASS".equalsIgnoreCase(finalResult)) {
                         firstCounter++;
                     }else if("DISTINCTION".equalsIgnoreCase(finalResult)) {
                         distinctionCounter++;
                     }
                     // get the reference books
                     List<Referencebooks> refBooksList = new ReferenceBooksDAO().getReferenceBooks(examLevelDetails.get(0).getLevelcode(),studentDetails.getStudent().getLanguageopted());
                     List<String> referenceBooksList = new ArrayList<String>();
                     
                     for (Referencebooks refBook : refBooksList) {
                    	 String[] referenceBooks = refBook.getReferencebooks().split(",");
                    	 
                    	 for (String refBooks : referenceBooks) {
                    		 referenceBooksList.add(refBooks.trim());
						}
                         
                    }
                    //End querying reference books
                     
                     result.setParents(studentDetails);
                     result.setSubjectList(subjectList);
                     result.setMarksList(marksList);
                     result.setPercentage(percentage);
                     result.setResultclass(finalResult);
                     result.setTotalMarksObtained((int)marksObtained);
                     result.setReferenceBooksList(referenceBooksList);
                     result.setTotalMarks((int)totalMarks);
                     
                     if(!"FAIL".equalsIgnoreCase(result.getResultclass()) && !"ABSENT".equalsIgnoreCase(result.getResultclass())) {
                         resultList.add(result);
                     }else if("FAIL".equalsIgnoreCase(result.getResultclass())){
                         resultListFail.add(result);
                     }
                     
             }
             Collections.sort(resultList);
             Collections.sort(resultListFail);
            
             int i=1;
             for (MarksSheet result : resultList) {
                 result.setRank(i);
                i++;
            }
            resultList.addAll(resultListFail);
             
             httpSession.setAttribute("totalstudentresult", parentsList.size());
             httpSession.setAttribute("failcount", failCounter);
             httpSession.setAttribute("passcount", passCounter);
             httpSession.setAttribute("secondcount", secondCounter);
             httpSession.setAttribute("firstcount", firstCounter);
             httpSession.setAttribute("distinction", distinctionCounter);
             httpSession.setAttribute("forexamlevel", forExamLevelCode);
             
             httpSession.setAttribute("markssheetlist", resultList);
             httpSession.setAttribute("markssheetsubexamlevel", subList);
             
             }else {
            	 httpSession.setAttribute("markssheetlist", "");
                 httpSession.setAttribute("markssheetsubexamlevel", "");
	             httpSession.setAttribute("markssheetyear", "");
	             httpSession.setAttribute("totalstudentresult", "");
	             httpSession.setAttribute("failcount", "");
	             httpSession.setAttribute("passcount", "");
	             httpSession.setAttribute("secondcount", "");
	             httpSession.setAttribute("firstcount", "");
	             httpSession.setAttribute("distinction", "");
	             httpSession.setAttribute("forexamlevel", "");
             }
             String[] centerCodeName = DataUtil.emptyString(request.getParameter("centercode")).split(":");
             String[] examLevelCodeName = DataUtil.emptyString(request.getParameter("examlevelcode")).split(":");
             httpSession.setAttribute("markssheetyear", examYear);
             httpSession.setAttribute("markssheetcentercode", centerCodeName[0]);
             httpSession.setAttribute("markssheetcentername", centerCodeName[1]);
             httpSession.setAttribute("markssheetexamlevel", examLevelCodeName[2]);
             httpSession.setAttribute("markssheetlanguage", language);
             httpSession.setAttribute("markssheetacademicsearch",examYear);
             
             new ExamLevelService(request, response).examLevels();
             new LanguageService(request, response).viewLanguage();
             new BranchService(request, response).viewBranches();
             new QualificationService(request, response).viewQualification();
     }

    public void resultReportCenter() {
        
        if(httpSession.getAttribute(BRANCHID)!=null) {
        new ExamLevelService(request, response).examLevels();
        new LanguageService(request, response).viewLanguage();
        new BranchService(request, response).viewBranchesCenter(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
        new QualificationService(request, response).viewQualification();
        httpSession.setAttribute("resultlist", "");
        httpSession.setAttribute("resultsubexamlevel", "");
        httpSession.setAttribute("resultcentername", "");
        httpSession.setAttribute("resultcentercode", "");
        httpSession.setAttribute("resultexamlevel", "");
        httpSession.setAttribute("resultlanguage", "");
        httpSession.setAttribute("resultqualification", "");
        }
    }

    public void searchResultReportCenter() {
        
        String examLevel = request.getParameter("examlevelcode");
        String academicYear = request.getParameter("academicyear");
        String language = null;
        String searchQuery = "From Parents as parent where ";
        String subQuery =null;
        
        
        if(httpSession.getAttribute(BRANCHID)!=null) {
            
            Branch branch = new BranchDAO().getBranch(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
            
                if(branch!=null) {
                    subQuery = "parent.Student.centercode = '"+branch.getCentercode()+"'";
                
                    
             if(!request.getParameter("examlevelcode").equalsIgnoreCase("")) {
                 String[] examLevelCode = examLevel.split(":");
                     subQuery = subQuery+"AND parent.Student.examlevel = '"+examLevelCode[0]+"'";
             }
             
             if(!request.getParameter("languageopted").equalsIgnoreCase("")) {
                 language = request.getParameter("languageopted");
                     subQuery = subQuery+"AND parent.Student.languageopted = '"+request.getParameter("languageopted")+"'";
             }
             searchQuery = searchQuery+subQuery+" AND parent.Student.archive = 0 AND parent.Student.passedout = 0 AND parent.Student.droppedout = 0 AND (parent.Student.remarks = 'approved' OR parent.Student.remarks = 'admin') Order By parent.Student.admissionnumber ASC";
             List<Parents> parentsList = new studentDetailsDAO().getStudentsList(searchQuery);
             String[] examDet = examLevel.split(":");
             List<Subexamlevel> subList = new ExamLevelDetailsDAO().getSubExamLevelSubject(examDet[0]);
             List<Result> resultList = new ArrayList<Result>();
             
             for (Parents studentDetails : parentsList) {
                 Result result = new Result();
                 List<Integer> marksList = new ArrayList<Integer>();
                 List<String> subjectList = new ArrayList<String>();
                 double marksObtained = 0;
                 double totalMarks = 0;
                 String finalResult = null;
                 
                     for (Subexamlevel subexamlevel : subList) {
                         Subject subjectIds = new SubjectDetailsDAO().getSubjectDetails(subexamlevel.getSubjectname());
                         Marks marks = new MarksDetailsDAO().readMarksPerStudent(studentDetails.getStudent().getSid(), subjectIds.getSubid(), Integer.parseInt(examDet[1]), academicYear);
                         subjectList.add(subjectIds.getSubjectname());
                         
                         if(marks!=null) {
                             marksList.add(marks.getMarksobtained());
                             marksObtained = marksObtained+marks.getMarksobtained();
                         }else {
                             marksList.add(0);
                             finalResult = "FAIL";
                         }
                         
                         totalMarks = totalMarks+subjectIds.getMaxmarks();
                         if(marks!=null && marks.getMarksobtained() < subjectIds.getMinmarks()) {
                             finalResult = "FAIL";
                         }
                     }
                     
                     double percentage = (marksObtained*100)/totalMarks;
                     percentage = Math.round(percentage);
                     int percent = (int) percentage;
                     String res = getResultClass(percent);
                     
                     if(finalResult==null) {
                         finalResult = res;
                     }
                     result.setStudent(studentDetails.getStudent());
                     result.setSubjectList(subjectList);
                     result.setMarksList(marksList);
                     result.setPercentage(percentage);
                     result.setResultclass(finalResult);
                     resultList.add(result);
             }
             httpSession.setAttribute("resultlist", resultList);
             httpSession.setAttribute("resultsubexamlevel", subList);
             String[] examLevelCodeName = DataUtil.emptyString(request.getParameter("examlevelcode")).split(":");
             httpSession.setAttribute("resultcentername",  "Center Code/Name:  "+branch.getCentercode()+"/"+branch.getCentername());
             httpSession.setAttribute("resultcentercode",  "Center Code:  "+branch.getCentercode());
             httpSession.setAttribute("resultexamlevel", "Examination Code:  "+examLevelCodeName[0]);
             
             if(language==null) {
                 language = "ALL";
             }
             httpSession.setAttribute("resultlanguage", "Language: "+language);
             new ExamLevelService(request, response).examLevels();
             new LanguageService(request, response).viewLanguage();
             new BranchService(request, response).viewBranches();
             new QualificationService(request, response).viewQualification();
     }
        }
    }

	public void resultAnalysis() {
		
			int grandTotalDistinction = 0;
			int grandTotalFirstClass = 0;
			int grandTotalSecondClass = 0;
			int grandTotalPass = 0;
			int grandTotalFail = 0;
			int grandTotalAbsent = 0;
			int grandTotalPresent = 0;
			
	       String subQuery =null;
	       
	    // Get other Religion Student Details
	       //String currentAcademicYear = httpSession.getAttribute("currentAcademicYear").toString();
	       String currentAcademicYear = request.getParameter("examyear");
	       BigInteger studentListPaper1 = new studentDetailsDAO().getStudentsListSQL("select count(*) from student s, att_studentdailyattendance sa WHERE religion='OTHERS' AND sa.attendancestatus = 'Present'  AND sa.subject='PAPER 1' AND s.studentexternalid = sa.attendeeid AND sa.academicyear='"+currentAcademicYear+"'");
	       httpSession.setAttribute("resultanalysispaper1nonm", studentListPaper1);
	       System.out.println("resultanalysispaper1nonm ********** "+studentListPaper1);
	       
	       BigInteger studentListPaper2 = new studentDetailsDAO().getStudentsListSQL("select count(*) from student s, att_studentdailyattendance sa WHERE religion='OTHERS' AND sa.attendancestatus = 'Present'  AND sa.subject='PAPER 2' AND s.studentexternalid = sa.attendeeid AND sa.academicyear='"+currentAcademicYear+"'");
	       httpSession.setAttribute("resultanalysispaper2nonm", studentListPaper2);
	       System.out.println("resultanalysispaper2nonm ********** "+studentListPaper2);
	       
	       if(httpSession.getAttribute("currentAcademicYear")!=null) {
	    	   String academicYear = httpSession.getAttribute("currentAcademicYear").toString();
	         List<ResultAnalysis> resultAnalysisList = new LinkedList<ResultAnalysis>();
	       
	       
	       			List<Branch> branchList = new BranchDAO().readListOfObjects();
	       			branchList.remove(0);
	       
	       			for (Branch branch : branchList) {
	       				
	       				List<Examleveldetails> examLevelList = new ExamLevelDetailsDAO().readListOfObjects();
	       				
	       				for (Examleveldetails examLevels : examLevelList) {
	       					
	       					String searchQuery = "From Parents as parent where ";
	       					subQuery = "parent.Student.centercode = '"+branch.getCentercode()+"'"
	       							+ " AND parent.Student.examlevel = '"+examLevels.getLevelcode()+"' "
	       									+ " AND parent.Student.archive = 0 AND parent.Student.droppedout = 0 AND (parent.Student.remarks = 'approved' OR parent.Student.remarks = 'admin')"; 
	       					
	       		            searchQuery = searchQuery+subQuery;
	       		            List<Parents> parentsList = new studentDetailsDAO().getStudentsList(searchQuery);
	       		            List<Subexamlevel> subList = new ExamLevelDetailsDAO().getSubExamLevelSubject(examLevels.getLevelcode());
	       		            List<Result> resultList = new ArrayList<Result>();
	       		            int failCounter=0,passCounter=0,secondCounter=0,firstCounter=0,distinctionCounter=0,absentCounter=0;
	       		            
	       		            for (Parents studentDetails : parentsList) {
	       		                Result result = new Result();
	       		                List<Integer> marksList = new ArrayList<Integer>();
	       		                List<String> subjectList = new ArrayList<String>();
	       		                int marksObtained = 0;
	       		                int totalMarks = 0;
	       		                String finalResult = null;
	       		                
	       		                    for (Subexamlevel subexamlevel : subList) {
	       		                        Subject subjectIds = new SubjectDetailsDAO().getSubjectDetails(subexamlevel.getSubjectname());
	       		                        Marks marks = new MarksDetailsDAO().readMarksPerStudent(studentDetails.getStudent().getSid(), subjectIds.getSubid(), examLevels.getIdexamlevel(), academicYear);
	       		                        subjectList.add(subjectIds.getSubjectname());
	       		                        
	       		                        if(marks!=null) {
	       		                            marksList.add(marks.getMarksobtained());
	       		                            marksObtained = marksObtained+marks.getMarksobtained();
	       		                        }else {
	       		                            marksList.add(0);
	       		                            finalResult = "ABSENT";
	       		                        }
	       		                        
	       		                        totalMarks = totalMarks+subjectIds.getMaxmarks();
	       		                        if(marks!=null && marks.getMarksobtained() < subjectIds.getMinmarks()) {
	       		                            finalResult = "FAIL";
	       		                        }
	       		                    }
	       		                    
	       		                    String res = null;
	       		                    double percentage = 0;
	       		                    		
	       		                    if(totalMarks>0) {
	       		                    	percentage = (marksObtained*100)/totalMarks;
	       		                    	percentage = Math.round(percentage);
	       		                    	int percent = (int) percentage;
	       		                        res = getResultClass(percent);
	       		                    }else {
	       		                    	res = "No Result";
	       		                    }
	       		                    
	       		                    
	       		                    if(finalResult==null) {
	       		                        finalResult = res;
	       		                    }
	       		                    
	       		                    if("FAIL".equalsIgnoreCase(finalResult)) {
	       		                        failCounter++;
	       		                    }else if("PASS".equalsIgnoreCase(finalResult)) {
	       		                        passCounter++;
	       		                    }else if("SECOND CLASS".equalsIgnoreCase(finalResult)) {
	       		                        secondCounter++;
	       		                    }else if("FIRST CLASS".equalsIgnoreCase(finalResult)) {
	       		                        firstCounter++;
	       		                    }else if("DISTINCTION".equalsIgnoreCase(finalResult)) {
	       		                        distinctionCounter++;
	       		                    }else if("ABSENT".equalsIgnoreCase(finalResult)) {
	       		                    	absentCounter++;
	       		                    }
	       		                    
	       		                    result.setStudent(studentDetails.getStudent());
	       		                    result.setSubjectList(subjectList);
	       		                    result.setMarksList(marksList);
	       		                    result.setPercentage(percentage);
	       		                    result.setResultclass(finalResult);
	       		                    resultList.add(result);
	       		            }
	       		            
	       		            logger.info("RESULT FOR center: "+branch.getCentername()+" Exam: "+examLevels.getLevelcode()+" totalStudent: "+parentsList.size()+" Fail:"+failCounter+" Pass:"+passCounter+" Second:"+secondCounter+
	       		            		" First:"+firstCounter+" Distinction:"+distinctionCounter+ "Absent: "+absentCounter);
	       		            ResultAnalysis resultAnalysis = new ResultAnalysis();
	       		            resultAnalysis.setCenterCode(branch.getCentercode());
	       		            resultAnalysis.setCenterName(branch.getCentername());
	       		            resultAnalysis.setExamLevelCode(examLevels.getLevelcode());
	       		            resultAnalysis.setTotalStudent(parentsList.size());
	       		            resultAnalysis.setDistinction(distinctionCounter);
	       		            resultAnalysis.setFirstClass(firstCounter);
	       		            resultAnalysis.setSecondClass(secondCounter);
	       		            resultAnalysis.setPass(passCounter);
	       		            resultAnalysis.setFail(failCounter);
	       		            resultAnalysis.setAbsent(absentCounter);
	       		            if(parentsList.size()>0) {
	       		            	resultAnalysis.setPresent(parentsList.size()-absentCounter);
	       		            }else {
	       		            	resultAnalysis.setPresent(0);
	       		            }
	       		            resultAnalysisList.add(resultAnalysis);
	       		            
	       		            grandTotalDistinction = grandTotalDistinction+distinctionCounter;
	       		            grandTotalFirstClass = grandTotalFirstClass+firstCounter;
	       		            grandTotalSecondClass = grandTotalSecondClass+secondCounter;
	       		            grandTotalPass = grandTotalPass+passCounter;
	       		            grandTotalFail = grandTotalFail+failCounter;
	       		            grandTotalPresent = grandTotalPresent+resultAnalysis.getPresent();
	       		            grandTotalAbsent = grandTotalAbsent+absentCounter;
	       		            
	       		            subQuery=null;
	       		            searchQuery=null;
						}
					}
	       			httpSession.setAttribute("resultanalysis", resultAnalysisList);
	       			httpSession.setAttribute("resultanalysisdistinction", grandTotalDistinction);
	       			httpSession.setAttribute("resultanalysisfirstclass", grandTotalFirstClass);
	       			httpSession.setAttribute("resultanalysissecondclass", grandTotalSecondClass);
	       			httpSession.setAttribute("resultanalysispass", grandTotalPass);
	       			httpSession.setAttribute("resultanalysisfail", grandTotalFail);
	       			httpSession.setAttribute("resultanalysispresent", grandTotalPresent);
	       			httpSession.setAttribute("resultanalysisabsent", grandTotalAbsent);
	       			httpSession.setAttribute("resultanalysistotalpromotion", grandTotalDistinction+grandTotalFirstClass+grandTotalSecondClass+grandTotalPass);
	       }
	    }
}
