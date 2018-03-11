/**
 * 
 */
package com.model.student.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.feescategory.service.FeesService;
import com.model.stampfees.service.StampFeesService;
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

	public StudentAction(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

	public String execute(String action, String page) {
		// TODO Auto-generated method stub
		if (action.equalsIgnoreCase("viewAll")) {
			url = viewAll();
		}else if (action.equalsIgnoreCase("viewAllStudents")) {
			url = viewAllStudents();
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
		}else if (action.equalsIgnoreCase("searchByClass")) {
			url = searchClass();
		}else if (action.equalsIgnoreCase("promoteClass")) {
			url = promoteClass();
		}else if (action.equalsIgnoreCase("ViewFeesStructure")) {
			url = ViewFeesStructure();
		}else if (action.equalsIgnoreCase("feesStructurePerYear")) {
			url = feesStructurePerYear();
		}else if (action.equalsIgnoreCase("exportDataForStudents")) {
			url = exportDataForStudents();
		}else if (action.equalsIgnoreCase("searchForStudents")) {
			url = searchForStudents();
		}else if (action.equalsIgnoreCase("searchStudentsForBonafide")) {
			url = searchStudentsForBonafide();
		}else if (action.equalsIgnoreCase("GenerateBonafide")) {
			url = generateBonafide();
		}else if (action.equalsIgnoreCase("download")) {
			url = downlaodFile();
		}
		return url;
	}
	
	

	private String downlaodFile() {
		if(new StudentService(request, response).downlaodFile()){
			return "exportsuccess.jsp";
		}
        return "exportfailure.jsp";
	}

	private String generateBonafide() {
		if (new StudentService(request, response).generateBonafide()) {
            //return "patientDetails_1.jsp";
            return "bonafidesuccess.jsp";
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
		
		
		if (new StudentService(request, response).viewfeesStructurePerYear()) {
            //return "patientDetails_1.jsp";
            return "student_details_feesstructure.jsp";
        } else{
        	return "student_details_feesstructure.jsp";
        }
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

	private String searchClass() {
		new StudentService(request, response).searchClass();
	    return "Promotion.jsp";
	}

	private String restoreMultiple() {
		new StudentService(request, response).restoreMultiple();
	    return "Controller?process=StudentProcess&action=viewAll";// TODO Auto-generated method stub
		
	}

	private String deleteMultiple() {
		 new StudentService(request, response).deleteMultiple();
	        return "Controller?process=StudentProcess&action=archiveViewAll";
	}

	private String archiveViewAll() {
		new StudentService(request, response).viewAllStudentsArchive();
        System.out.println("IN action's view all Archive");
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
            //return "patientDetails_1.jsp";
            return "student_update.jsp";
        } else {
            return "viewAll.jsp";
        }
	}

	private String viewStudent() {
		String studentaction=null;
		if (new StudentService(request, response).viewDetailsOfStudent()) {
            //return "patientDetails_1.jsp";
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
		 if (new StudentService(request, response).addStudent()) {
	            return "saved.jsp";
	        } else {
	            return "notSaved.jsp";
	        }
		
	}

	private String viewAll() {

		new StudentService(request, response).viewAllStudentsParents();
        System.out.println("IN action's view all Parents");
        return "viewAllWithParents.jsp";
        /*new StudentService(request, response).viewAllStudents();
        System.out.println("IN action's view all");
        return "viewAll.jsp";*/
		
	}

	private String studentsDetailsReport() {

		new StudentService(request, response).studentsDetailsSearch();
        return "studentsdetailsreports.jsp";
		
	}
	

	private String exportDataForStudents() {
		if(new StudentService(request, response).exportDataForStudents()){
			return "exportsuccess.jsp";
		}else{
			return "exportfailure.jsp";
		}
		
	}


}
