/**
 * 
 */
package com.model.student.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.model.branch.service.BranchService;
import com.model.examlevels.service.ExamLevelService;
import com.model.language.service.LanguageService;
import com.model.marksdetails.service.MarksDetailsService;
import com.model.stampfees.service.StampFeesService;
import com.model.student.service.ResultService;
import com.model.student.service.StudentService;

/**
 * @author Musaib_2
 * 
 */
public class StudentAction {

	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession httpSession;
	String url;

	private static final Logger logger = LogManager.getLogger(StudentAction.class);
	
	public StudentAction(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

	public String execute(String action, String page) {

	        if (action.equalsIgnoreCase("viewAll")) {
			url = viewAll();
		}else if (action.equalsIgnoreCase("viewAllStudents")) {
			url = viewAllStudents();
		}else if (action.equalsIgnoreCase("searchStudentsviewAll")) {
                    url = searchStudentsviewAll();
		}else if (action.equalsIgnoreCase("viewAllStudentsCenter")) {
                    url = viewAllStudentsCenter();
                }else if (action.equalsIgnoreCase("viewAllStudentsWithParents")) {
			url = viewAllStudentsWithParents();
		}else if (action.equalsIgnoreCase("AddStudent")) {
			url = addStudent();
		}else if (action.equalsIgnoreCase("ViewDetails")) {
			url = viewStudent();
		}else if (action.equalsIgnoreCase("updateStudentDetails")) {
			url = updateStudentDetails();
		}else if (action.equalsIgnoreCase("updateStudent")) {
			url = updateStudent();
		}else if (action.equalsIgnoreCase("archiveMultiple")) {
			url = archiveMultiple();
		}else if (action.equalsIgnoreCase("archiveViewAll")) {
			url = archiveViewAll();
		}else if (action.equalsIgnoreCase("deleteMultiple")) {
			url = deleteMultiple();
		}else if (action.equalsIgnoreCase("restoreMultiple")) {
			url = restoreMultiple();
		}else if (action.equalsIgnoreCase("searchForPromotion")) {
			url = searchForPromotion();
		}else if (action.equalsIgnoreCase("promoteClass")) {
			url = promoteClass();
		}else if (action.equalsIgnoreCase("demoteClass")) {
			url = demoteClass();
		}else if (action.equalsIgnoreCase("ViewFeesStructure")) {
			url = ViewFeesStructure();
		}else if (action.equalsIgnoreCase("feesStructurePerYear")) {
			url = feesStructurePerYear();
		}else if (action.equalsIgnoreCase("exportDataStudents")) {
			url = exportDataStudents();
		}else if (action.equalsIgnoreCase("searchForStudents")) {
			url = searchForStudents();
		}else if (action.equalsIgnoreCase("searchStudentsForBonafide")) {
			url = searchStudentsForBonafide();
		}else if (action.equalsIgnoreCase("GenerateBonafide")) {
			url = generateBonafide();
		}else if (action.equalsIgnoreCase("download")) {
			url = downlaodFile();
		}else if (action.equalsIgnoreCase("addNew")) {
                    url = addNew();
                }else if (action.equalsIgnoreCase("addNewStudentCenter")) {
                    url = addNewStudentCenter();
                }else if (action.equalsIgnoreCase("viewAllSuperAdmin")) {
                    url = viewAllSuperAdmin();
                }else if (action.equalsIgnoreCase("studentsListReport")) {
                    url = studentsListReport();
                }else if (action.equalsIgnoreCase("studentsListReportCenter")) {
                    url = studentsListReportCenter();
                }else if (action.equalsIgnoreCase("searchStudents")) {
                    url = searchStudents();
                }else if (action.equalsIgnoreCase("searchStudentsCenter")) {
                    url = searchStudentsCenter();
                }else if (action.equalsIgnoreCase("exportStudentsReport")) {
                    url = exportStudentsReport();
                }else if (action.equalsIgnoreCase("printStudentReport")) {
                    url = printStudentReport();
                }else if (action.equalsIgnoreCase("questionPaperSet")) {
                    url = questionPaperSet();
                }else if (action.equalsIgnoreCase("searchQuestionPaperDetails")) {
                    url = searchQuestionPaperDetails();
                }else if (action.equalsIgnoreCase("printQuestionSet")) {
                    url = printQuestionSet();
                }else if (action.equalsIgnoreCase("languageListReport")) {
                    url = languageListReport();
                }else if (action.equalsIgnoreCase("searchLanguagesReport")) {
                    url = searchLanguagesReport();
                }else if (action.equalsIgnoreCase("printLanguageReport")) {
                    url = printLanguageReport();
                }else if (action.equalsIgnoreCase("promotion")) {
                    url = promotion();
                }else if (action.equalsIgnoreCase("promotionCenter")) {
                    url = promotionCenter();
                }else if (action.equalsIgnoreCase("resultReport")) {
                    url = resultReport();
                }else if (action.equalsIgnoreCase("resultReportCenter")) {
                    url = resultReportCenter();
                }else if (action.equalsIgnoreCase("searchResultReport")) {
                    url = searchResultReport();
                }else if (action.equalsIgnoreCase("searchResultReportCenter")) {
                    url = searchResultReportCenter();
                }else if (action.equalsIgnoreCase("printResultReport")) {
                    url = printResultReport();
                }else if (action.equalsIgnoreCase("rankListReport")) {
                    url = rankListReport();
                }else if (action.equalsIgnoreCase("searchRankListReport")) {
                    url = searchRankListReport();
                }else if (action.equalsIgnoreCase("printRankListReport")) {
                    url = printRankListReport();
                }else if (action.equalsIgnoreCase("marksSheet")) {
                    url = marksSheet();
                }else if (action.equalsIgnoreCase("searchMarksSheet")) {
                    url = searchMarksSheet();
                }else if (action.equalsIgnoreCase("printMarksSheet")) {
                    url = printMarksSheet();
                }else if (action.equalsIgnoreCase("evaluationSheet")) {
                    url = evaluationSheet();
                }else if (action.equalsIgnoreCase("searchForEvaluationSheet")) {
                    url = searchForEvaluationSheet();
                }else if (action.equalsIgnoreCase("printEvaluationSheet")) {
                    url = printEvaluationSheet();
                }else if (action.equalsIgnoreCase("graduated")) {
                    url = graduated();
                }else if (action.equalsIgnoreCase("dropped")) {
                    url = dropped();
                }else if (action.equalsIgnoreCase("viewGraduated")) {
                    url = viewGraduated();
                }else if (action.equalsIgnoreCase("viewDropped")) {
                    url = viewDropped();
                }else if (action.equalsIgnoreCase("restoreMultipleGraduate")) {
                    url = restoreMultipleGraduate();
                }else if (action.equalsIgnoreCase("restoreMultipleDroppedout")) {
                    url = restoreMultipleDroppedout();
                }else if (action.equalsIgnoreCase("resultAnalysis")) {
                    url = resultAnalysis();
                }else if (action.equalsIgnoreCase("resultList")) {
                    url = resultList();
                }else if (action.equalsIgnoreCase("searchResultList")) {
                    url = searchResultList();
                }else if (action.equalsIgnoreCase("printResultList")) {
                    url = printResultList();
                }else if (action.equalsIgnoreCase("resultListCenter")) {
                    url = resultListCenter();
                }else if (action.equalsIgnoreCase("pendingapprovals")) {
                    url = pendingApprovals();
                }else if (action.equalsIgnoreCase("approveRecords")) {
                    url = approveRecords();
                }else if (action.equalsIgnoreCase("rejectRecords")) {
                    url = rejectRecords();
                }else if (action.equalsIgnoreCase("rejectedapprovals")) {
                    url = rejectedApprovals();
                }else if (action.equalsIgnoreCase("approvalshistory")) {
                    url = approvalsHistory();
                }
		return url;
	}
	
	

    private String approvalsHistory() {
    	new StudentService(request, response).approvalsHistory();
		return "approvalshistory.jsp";
	}

	private String rejectedApprovals() {
    	return new StudentService(request, response).rejectedApprovals();
	}

	private String promotionCenter() {
	    new ExamLevelService(request, response).examLevels();
	    new LanguageService(request, response).viewLanguage();
	    new BranchService(request, response).viewBranchesCenter();
        return "Promotion.jsp";
    }

	private String rejectRecords() {
		 new StudentService(request, response).rejectRecords();
		 return "rejectsuccess.jsp";
	}

	private String approveRecords() {
		 new StudentService(request, response).approveRecords();
		 return "approvesuccess.jsp";
	}

	private String pendingApprovals() {
		 return new StudentService(request, response).pendingApprovals();
	}

	private String demoteClass() {
		if( new StudentService(request, response).demoteMultiple()){
			return "successpromote.jsp";
		}
	       return "failurepromote.jsp"; 
	}

	private String resultListCenter() {
	    new ResultService(request, response).resultReport();
        return "resultlistcenter.jsp";
    }

	private String printResultList() {
        return "printresultlist.jsp";
        }

	private String searchResultList() {
	    new ResultService(request, response).searchRankListReportNew();
	    
	    if("true".equalsIgnoreCase(request.getParameter("centervalue"))) {
	    	return "resultlistcenter.jsp";
	    }
	    
        return "resultlist.jsp";
    }

	private String resultList() {
	    new ResultService(request, response).resultReport();
        return "resultlist.jsp";
}

	private String resultAnalysis() {
    	new ResultService(request, response).resultAnalysis();
        return "resultanalysis.jsp";
	}

	private String searchStudentsviewAll() {
        new StudentService(request, response).searchStudentsviewAll();
        return "viewAllWithParents.jsp";
}

    private String searchResultReportCenter() {
        new ResultService(request, response).searchResultReportCenter();
        return "resultreportcenter.jsp";
}

    private String searchStudentsCenter() {
        new StudentService(request, response).searchStudentsCenter();
        return "studentsreportscenter.jsp";
}

    private String addNewStudentCenter() {
        return new StudentService(request, response).addNewStdentCenter();
        }

    private String resultReportCenter() {
        new ResultService(request, response).resultReportCenter();
        return "resultreportcenter.jsp";
    }

    private String studentsListReportCenter() {
        new StudentService(request, response).studentsListReportCenter();
        return "studentsreportscenter.jsp";
}

    private String viewAllStudentsCenter() {
        new StudentService(request, response).viewAllStudentsParentsCenter();
            return "viewAllWithParents.jsp";
}

    private String restoreMultipleDroppedout() { 
        new StudentService(request, response).restoreMultipleDroppedout();
        return "Controller?process=StudentProcess&action=viewAll";
    }

    private String restoreMultipleGraduate() {
        new StudentService(request, response).restoreMultipleGraduate();
        return "Controller?process=StudentProcess&action=viewAll";
        
    }

    private String viewDropped() {
        new StudentService(request, response).viewDropped();
        return "droppedout.jsp";
    }

    private String viewGraduated() {
        new StudentService(request, response).viewGraduated();
        return "graduated.jsp";
}

    private String dropped() {
        if( new StudentService(request, response).droppedMultiple()){
            return "successpromote.jsp";
    }
            return "failurepromote.jsp"; 
    }

    private String graduated() {
        if( new StudentService(request, response).graduateMultiple()){
            return "successpromote.jsp";
    }
            return "failurepromote.jsp"; 
    }

    private String printEvaluationSheet() {
        return "printevaluationsheet.jsp";
    }
    
    private String searchForEvaluationSheet() {
        new MarksDetailsService(request, response).SearchForEvaluationSheet();
        return "evaluationsheet.jsp";
    }

    private String evaluationSheet() {
	    new MarksDetailsService(request, response).enterMarks();
            return "evaluationsheet.jsp";
    }

    private String printMarksSheet() {
	    return "printmarkssheet.jsp";
    }

    private String searchMarksSheet() {
	    new ResultService(request, response).searchMarksSheet();
            return "markssheet.jsp";
    }

    private String marksSheet() {
	    new ResultService(request, response).marksSheet();
            return "markssheet.jsp";
    }

    private String printRankListReport() {
        return "printranklistreport.jsp";
    }

    private String searchRankListReport() {
	    new ResultService(request, response).searchRankListReport();
            return "ranklistreport.jsp";
    }

    private String rankListReport() {
	    new ResultService(request, response).resultReport();
	        return "ranklistreport.jsp";
    }

    private String printResultReport() {
        return "printresultreport.jsp";
    }

    private String searchResultReport() {
          new ResultService(request, response).searchResultReport();
             return "resultreport.jsp";
    }

    private String resultReport() {
        new ResultService(request, response).resultReport();
        return "resultreport.jsp";
    }

    private String promotion() {
	    new ExamLevelService(request, response).examLevels();
	    new LanguageService(request, response).viewLanguage();
	    new BranchService(request, response).viewBranches();
        return "Promotion.jsp";
    }

    private String printLanguageReport() {
        return "printlanguagereport.jsp";
    }

    private String searchLanguagesReport() {
	    new StudentService(request, response).searchLanguagesReport();
            return "languagelistreport.jsp";
    }

    private String languageListReport() {
	    new StudentService(request, response).languageListReport();
            return "languagelistreport.jsp";
    }

    private String printQuestionSet() {
	    return "printquestionpaperset.jsp";
    }

    private String searchQuestionPaperDetails() {
	    new StudentService(request, response).searchStudents();
            return "questionpaperset.jsp";
    }

    private String questionPaperSet() {
	    new StudentService(request, response).studentsListReport();
            return "questionpaperset.jsp";
    }

    private String printStudentReport() {
	    //new StudentService(request, response).printStudentReport();
	    return "printstudentsreport.jsp";
    }

    private String exportStudentsReport() {
        new StudentService(request, response).exportStudentsReport();
        return "studentsreportsucess.jsp";
    }

    private String searchStudents() {
	    new StudentService(request, response).searchStudents();
            return "studentsreports.jsp";
    }

    private String studentsListReport() {
	    new StudentService(request, response).studentsListReport();
            return "studentsreports.jsp";
    }

    private String viewAllSuperAdmin() {
            new StudentService(request, response).viewAllStudentsSuperAdmin();
                return "viewAllWithParents.jsp";
        }

    private String addNew() {
            return new StudentService(request, response).addNew();
        }

	private String downlaodFile() {
		if(new StudentService(request, response).downlaodFile()){
			return "exportsuccess.jsp";
		}
        return "exportfailure.jsp";
	}

	private String generateBonafide() {
		
		String result = new StudentService(request, response).generateBonafide();
		if (result!=null) {
            return result;
        } else {
            return "bonafidefailure.jsp";
        }
	}

	private String searchStudentsForBonafide() {
		new StampFeesService(request, response).advanceSearch();
        return "studentsdetailsbonafide.jsp";
	}

	private String searchForStudents() {
		new StampFeesService(request, response).advanceSearch();
                return "studentsdetailsreports.jsp";
	}

	private String feesStructurePerYear() {
		
		
		new StudentService(request, response).viewfeesStructurePerYear();
        return "student_details_feesstructure.jsp";
        
	}

	private String ViewFeesStructure() {
		
		if (new StudentService(request, response).viewDetailsOfStudent()) {
            return "student_details_feesstructure.jsp";
        } else {
            return "viewAll.jsp";
        }
	}

	private String viewAllStudentsWithParents() {
		new StudentService(request, response).viewAllStudentsParents();
        return "viewAllWithParents.jsp";
	}

	private String viewAllStudents() {
		
		new StudentService(request, response).viewAllStudentsParents();
        return "viewAllWithParents.jsp";
	}

	private String promoteClass() {
		if( new StudentService(request, response).promoteMultiple()){
			return "successpromote.jsp";
		}
	       return "failurepromote.jsp"; 
	}

	private String searchForPromotion() {
		//new ResultService(request, response).searchResultReport();
		new ResultService(request, response).searchForPromotion();
	    return "Promotion.jsp";
	}

	private String restoreMultiple() {
		new StudentService(request, response).restoreMultiple();
	    return "Controller?process=StudentProcess&action=viewAll";// TODO Auto-generated method stub
		
	}

	private String deleteMultiple() {
		 if(new StudentService(request, response).deleteMultiple()) {
		     return "deletesuccess.jsp";
		 }
		 return "deletefail.jsp";
	        //return "Controller?process=StudentProcess&action=archiveViewAll";
	}

	private String archiveViewAll() {
		new StudentService(request, response).viewAllStudentsArchive();
        return "ArchiveviewAll.jsp";
	}

	private String archiveMultiple() {
	
    
    new StudentService(request, response).archiveMultiple();
    return "Controller?process=StudentProcess&action=viewAll";
    }

	private String updateStudent() {
		return "Controller?process=StudentProcess&action=viewDetails&id=" + new StudentService(request, response).updateStudent();
	}

	private String updateStudentDetails() {
		if (new StudentService(request, response).viewDetailsOfStudent()) {
            return "student_update.jsp";
        } else {
            return "viewAll.jsp";
        }
	}

	private String viewStudent() {
		String studentaction=null;
		if (new StudentService(request, response).viewDetailsOfStudent()) {
			studentaction = "success";
			httpSession.setAttribute("studentaction",studentaction);
            return "student_details.jsp";
        } else {
        	studentaction="fail";
        	httpSession.setAttribute("studentaction",studentaction);
            return "error.jsp";
        }
	}

	private String addStudent() {
		logger.info("******* Add Student ****** ");
		 if (new StudentService(request, response).addStudent()) {
			 logger.info("******* Saved Student ****** ");
	            return "saved.jsp";
	        } else {
	            return "notSaved.jsp";
	        }
	}

	private String viewAll() {
		new StudentService(request, response).viewAllStudentsParents();
		return "viewAllWithParents.jsp";
	}

	private String studentsDetailsReport() {
		new StudentService(request, response).studentsDetailsSearch();
		return "studentsdetailsreports.jsp";
	}

	private String exportDataStudents() {
		if(new StudentService(request, response).exportDataStudents()){
			return "exportsuccess.jsp";
		}else{
			return "exportfailure.jsp";
		}
	}
}
