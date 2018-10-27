package com.model.student.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.branch.dao.BranchDAO;
import com.model.branch.dto.Branch;
import com.model.branch.service.BranchService;
import com.model.examlevels.dao.ExamLevelDetailsDAO;
import com.model.examlevels.dto.Subexamlevel;
import com.model.examlevels.service.ExamLevelService;
import com.model.language.service.LanguageService;
import com.model.marksdetails.dao.MarksDetailsDAO;
import com.model.marksdetails.dto.Marks;
import com.model.parents.dto.Parents;
import com.model.qualification.service.QualificationService;
import com.model.referencebooks.dao.ReferenceBooksDAO;
import com.model.referencebooks.dto.Referencebooks;
import com.model.student.dao.studentDetailsDAO;
import com.model.subjectdetails.dao.SubjectDetailsDAO;
import com.model.subjectdetails.dto.Subject;
import com.util.DataUtil;
import com.util.MarksSheet;
import com.util.Result;

public class ResultService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;
	private String BRANCHID = "branchid";
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
	             httpSession.setAttribute("markssheetcentercode", "");
	             httpSession.setAttribute("markssheetcentername", "");
	             httpSession.setAttribute("markssheetexamlevel", "");
	             httpSession.setAttribute("markssheetlanguage", "");
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
        httpSession.setAttribute("resultsubexamlevel", "");
        httpSession.setAttribute("resultcentername", "");
        httpSession.setAttribute("resultexamlevel", "");
        httpSession.setAttribute("resultlanguage", "");
        httpSession.setAttribute("resultqualification", "");
        httpSession.setAttribute("totalstudentresult", "");
        httpSession.setAttribute("failcount", "");
        httpSession.setAttribute("passcount", "");
        httpSession.setAttribute("secondcount", "");
        httpSession.setAttribute("firstcount", "");
        httpSession.setAttribute("distinction", "");
    }

    public void searchResultReport() {
       String examLevel = request.getParameter("examlevelcode");
       String academicYear = request.getParameter("academicyear");
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
                String[] examLevelCode = examLevel.split(":");
                if(subQuery!=null) {
                    subQuery = subQuery+"AND parent.Student.examlevel = '"+examLevelCode[0]+"'";
                }else {
                    subQuery = "parent.Student.examlevel = '"+examLevelCode[0]+"'";
                }
                httpSession.setAttribute("resultserviceexamlevelsearch", request.getParameter("examlevelcode").toString());
            }else {
                httpSession.setAttribute("resultserviceexamlevelsearch", "");
            }
            
            if(!request.getParameter("languageopted").equalsIgnoreCase("")) {
                language = request.getParameter("languageopted");
                if(subQuery!=null) {
                    subQuery = subQuery+"AND parent.Student.languageopted = '"+request.getParameter("languageopted")+"'";
                }else {
                    subQuery = "parent.Student.languageopted = '"+request.getParameter("languageopted")+"'";
                }
                httpSession.setAttribute("resultservicelanguagesearch",request.getParameter("languageopted").toString());
            }else {
                httpSession.setAttribute("resultservicelanguagesearch","");
            }
            
            if(!DataUtil.emptyString(request.getParameter("academicyear")).equalsIgnoreCase("")) {
                if(subQuery!=null) {
                    String subAcademicYear = request.getParameter("academicyear").toString().substring(2, 4);
                    
                    subQuery = subQuery+"AND parent.Student.admissionnumber like '"+subAcademicYear+"%'";
                }else {
                    subQuery = "parent.Student.admissionnumber like '\"+subAcademicYear+\"%'";
                }
                httpSession.setAttribute("resultserviceacademicsearch",request.getParameter("academicyear").toString());
            }else {
                httpSession.setAttribute("resultserviceacademicsearch","");
            }
            
            searchQuery = searchQuery+subQuery;
            List<Parents> parentsList = new studentDetailsDAO().getStudentsList(searchQuery);
            String[] examDet = examLevel.split(":");
            List<Subexamlevel> subList = new ExamLevelDetailsDAO().getSubExamLevelSubject(examDet[0]);
            List<Result> resultList = new ArrayList<Result>();
            int failCounter=0,passCounter=0,secondCounter=0,firstCounter=0,distinctionCounter=0;
            
            for (Parents studentDetails : parentsList) {
                Result result = new Result();
                List<Integer> marksList = new ArrayList<Integer>();
                List<String> subjectList = new ArrayList<String>();
                int marksObtained = 0;
                int totalMarks = 0;
                String finalResult = null;
                
                    for (Subexamlevel subexamlevel : subList) {
                        Subject subjectIds = new SubjectDetailsDAO().getSubjectDetails(subexamlevel.getSubjectname());
                        Marks marks = new MarksDetailsDAO().readMarks(studentDetails.getStudent().getSid(), subjectIds.getSubid(), Integer.parseInt(examDet[1]), academicYear);
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
                    String res = getResultClass(percentage);
                    
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
                    resultList.add(result);
            }
            httpSession.setAttribute("totalstudentresult", parentsList.size());
            httpSession.setAttribute("failcount", failCounter);
            httpSession.setAttribute("passcount", passCounter);
            httpSession.setAttribute("secondcount", secondCounter);
            httpSession.setAttribute("firstcount", firstCounter);
            httpSession.setAttribute("distinction", distinctionCounter);
            httpSession.setAttribute("resultlist", resultList);
            httpSession.setAttribute("resultsubexamlevel", subList);
            String[] centerCodeName = DataUtil.emptyString(request.getParameter("centercode")).split(":");
            String[] examLevelCodeName = DataUtil.emptyString(request.getParameter("examlevelcode")).split(":");
            httpSession.setAttribute("resultcentername",  "Center Code/Name:  "+centerCodeName[0]+"/"+centerCodeName[1]);
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

    private String getResultClass(double percentage) {
        
        if(percentage < 35) {
            return "FAIL";
        }else if(percentage < 49) {
            return "PASS";
        }else if(percentage < 59) {
            return "SECOND CLASS";
        }else if(percentage < 79) {
            return "FIRST CLASS";
        }else if(percentage < 100) {
            return "Distinction";
        }else {
            return "";
        }
        
    }

    public void searchRankListReport() {
        
        String examLevel = request.getParameter("examlevelcode");
        String academicYear = request.getParameter("academicyear");
        String language = null;
        String searchQuery = "From Parents as parent where ";
        String subQuery =null;
        
             if(!request.getParameter("centercode").equalsIgnoreCase("")) {
                 String[] centerCode = request.getParameter("centercode").split(":");
                 subQuery = "parent.Student.centercode = '"+centerCode[0]+"'";
                 httpSession.setAttribute("ranklistcentersearch", centerCode[0]+":"+centerCode[1]);
             }else {
                 httpSession.setAttribute("ranklistcentersearch", "");
             }
             
             if(!request.getParameter("examlevelcode").equalsIgnoreCase("")) {
                 String[] examLevelCode = examLevel.split(":");
                 if(subQuery!=null) {
                     subQuery = subQuery+" AND parent.Student.examlevel = '"+examLevelCode[0]+"'";
                 }else {
                     subQuery = "parent.Student.examlevel = '"+examLevelCode[0]+"'";
                 }
                 httpSession.setAttribute("ranklistexamlevelsearch", request.getParameter("examlevelcode").toString());
             }else {
                 httpSession.setAttribute("ranklistexamlevelsearch", "");
             }
             
             if(!request.getParameter("languageopted").equalsIgnoreCase("")) {
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
             

             if(!request.getParameter("qualification").equalsIgnoreCase("")) {
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
             
             if(!DataUtil.emptyString(request.getParameter("academicyear")).equalsIgnoreCase("")) {
                 if(subQuery!=null) {
                     String subAcademicYear = request.getParameter("academicyear").toString().substring(2, 4);
                     
                     subQuery = subQuery+"AND parent.Student.admissionnumber like '"+subAcademicYear+"%'";
                 }else {
                     subQuery = "parent.Student.admissionnumber like '\"+subAcademicYear+\"%'";
                 }
                 httpSession.setAttribute("ranklistacademicsearch",request.getParameter("academicyear").toString());
             }else {
                 httpSession.setAttribute("ranklistacademicsearch","");
             }
             
             searchQuery = searchQuery+subQuery;
             List<Parents> parentsList = new studentDetailsDAO().getStudentsList(searchQuery);
             String[] examDet = examLevel.split(":");
             List<Subexamlevel> subList = new ExamLevelDetailsDAO().getSubExamLevelSubject(examDet[0]);
             List<Result> resultList = new ArrayList<Result>();
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
                         Marks marks = new MarksDetailsDAO().readMarks(studentDetails.getStudent().getSid(), subjectIds.getSubid(), Integer.parseInt(examDet[1]), academicYear);
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
                     String numberAsString = String.format ("%.2f", percentage);
                     percentage = Double.valueOf(numberAsString);
                     String res = getResultClass(percentage);
                     
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
                     
                     if(!"FAIL".equalsIgnoreCase(result.getResultclass())) {
                         resultList.add(result);
                     }else {
                         resultListFail.add(result);
                     }
                     
             }
             Collections.sort(resultList);
             Collections.sort(resultListFail);

             int i=1;
             for (Result result : resultList) {
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
             
             httpSession.setAttribute("resultlist", resultList);
             httpSession.setAttribute("resultsubexamlevel", subList);
             String[] centerCodeName = DataUtil.emptyString(request.getParameter("centercode")).split(":");
             String[] examLevelCodeName = DataUtil.emptyString(request.getParameter("examlevelcode")).split(":");
             if(!"".equalsIgnoreCase(centerCodeName[0])) {
                 httpSession.setAttribute("resultcentername",  "Center Code/Name:  "+centerCodeName[0]+"/"+centerCodeName[1]);
             }else {
                 httpSession.setAttribute("resultcentername",  "Center Code/Name:  ALL");
             }
             
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

    public void searchMarksSheet() {
        String examLevel = request.getParameter("examlevelcode");
        String academicYear = request.getParameter("academicyear");
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
                 String[] examLevelCode = examLevel.split(":");
                 if(subQuery!=null) {
                     subQuery = subQuery+"AND parent.Student.examlevel = '"+examLevelCode[0]+"'";
                 }else {
                     subQuery = "parent.Student.examlevel = '"+examLevelCode[0]+"'";
                 }
                 httpSession.setAttribute("markssheetexamlevelsearch", request.getParameter("examlevelcode").toString());
             }else {
                 httpSession.setAttribute("markssheetexamlevelsearch", "");
             }
             
             if(!request.getParameter("languageopted").equalsIgnoreCase("")) {
                 language = request.getParameter("languageopted");
                 if(subQuery!=null) {
                     subQuery = subQuery+"AND parent.Student.languageopted = '"+request.getParameter("languageopted")+"'";
                 }else {
                     subQuery = "parent.Student.languageopted = '"+request.getParameter("languageopted")+"'";
                 }
                 httpSession.setAttribute("markssheetlanguagesearch",request.getParameter("languageopted").toString());
             }else {
                 httpSession.setAttribute("markssheetlanguagesearch","");
             }
             

             if(!request.getParameter("qualification").equalsIgnoreCase("")) {
                 if(subQuery!=null) {
                     subQuery = subQuery+"AND parent.Student.qualification = '"+request.getParameter("qualification")+"'";
                 }else {
                     subQuery = "parent.Student.qualification = '"+request.getParameter("qualification")+"'";
                 }
                 httpSession.setAttribute("resultqualification", "Qualification: "+request.getParameter("qualification").toString());
                 httpSession.setAttribute("markssheetqualificationsearch", request.getParameter("qualification").toString());
             }else {
                 httpSession.setAttribute("markssheetqualificationsearch", "");
             }
             
             
             if(!DataUtil.emptyString(request.getParameter("academicyear")).equalsIgnoreCase("")) {
                 if(subQuery!=null) {
                     String subAcademicYear = request.getParameter("academicyear").toString().substring(2, 4);
                     
                     subQuery = subQuery+"AND parent.Student.admissionnumber like '"+subAcademicYear+"%'";
                 }else {
                     subQuery = "parent.Student.admissionnumber like '\"+subAcademicYear+\"%'";
                 }
                 httpSession.setAttribute("markssheetacademicsearch",request.getParameter("academicyear").toString());
             }else {
                 httpSession.setAttribute("markssheetacademicsearch","");
             }
             
             searchQuery = searchQuery+subQuery;
             List<Parents> parentsList = new studentDetailsDAO().getStudentsList(searchQuery);
             String[] examDet = examLevel.split(":");
             List<Subexamlevel> subList = new ExamLevelDetailsDAO().getSubExamLevelSubject(examDet[0]);
             
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
                         Marks marks = new MarksDetailsDAO().readMarks(studentDetails.getStudent().getSid(), subjectIds.getSubid(), Integer.parseInt(examDet[1]), academicYear);
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
                     String numberAsString = String.format ("%.2f", percentage);
                     percentage = Double.valueOf(numberAsString);
                     String res = getResultClass(percentage);
                     
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
                     List<Referencebooks> refBooksList = new ReferenceBooksDAO().getReferenceBooks(examDet[0],studentDetails.getStudent().getLanguageopted());
                     List<String> referenceBooksList = new ArrayList<String>();
                     for (Referencebooks refBook : refBooksList) {
                         referenceBooksList.add(refBook.getReferencebooks());
                    }
                    //End querying reference books
                     
                     result.setParents(studentDetails);
                     result.setSubjectList(subjectList);
                     result.setMarksList(marksList);
                     result.setPercentage(percentage);
                     result.setResultclass(finalResult);
                     result.setTotalMarksObtained(marksObtained);
                     result.setReferenceBooksList(referenceBooksList);
                     
                     if(!"FAIL".equalsIgnoreCase(result.getResultclass())) {
                         resultList.add(result);
                     }else {
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
             
             httpSession.setAttribute("markssheetlist", resultList);
             httpSession.setAttribute("markssheetsubexamlevel", subList);
             String[] centerCodeName = DataUtil.emptyString(request.getParameter("centercode")).split(":");
             String[] examLevelCodeName = DataUtil.emptyString(request.getParameter("examlevelcode")).split(":");
             httpSession.setAttribute("markssheetyear", academicYear);
             httpSession.setAttribute("markssheetcentercode", centerCodeName[0]);
             httpSession.setAttribute("markssheetcentername", centerCodeName[1]);
             httpSession.setAttribute("markssheetexamlevel", examLevelCodeName[2]);
             httpSession.setAttribute("markssheetlanguage", language);
             
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
             searchQuery = searchQuery+subQuery;
             List<Parents> parentsList = new studentDetailsDAO().getStudentsList(searchQuery);
             String[] examDet = examLevel.split(":");
             List<Subexamlevel> subList = new ExamLevelDetailsDAO().getSubExamLevelSubject(examDet[0]);
             List<Result> resultList = new ArrayList<Result>();
             
             for (Parents studentDetails : parentsList) {
                 Result result = new Result();
                 List<Integer> marksList = new ArrayList<Integer>();
                 List<String> subjectList = new ArrayList<String>();
                 int marksObtained = 0;
                 int totalMarks = 0;
                 String finalResult = null;
                 
                     for (Subexamlevel subexamlevel : subList) {
                         Subject subjectIds = new SubjectDetailsDAO().getSubjectDetails(subexamlevel.getSubjectname());
                         Marks marks = new MarksDetailsDAO().readMarks(studentDetails.getStudent().getSid(), subjectIds.getSubid(), Integer.parseInt(examDet[1]), academicYear);
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
                     String res = getResultClass(percentage);
                     
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
}
