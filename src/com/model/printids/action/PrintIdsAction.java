/**
 * 
 */
package com.model.printids.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.feescategory.service.FeesService;
import com.model.printids.service.PrintIdsService;
import com.model.stampfees.service.StampFeesService;
import com.model.student.service.StudentService;

/**
 * @author Musaib_2
 * 
 */
public class PrintIdsAction {

	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession httpSession;
	String url;

	public PrintIdsAction(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

	public String execute(String action, String page) {
		// TODO Auto-generated method stub
		if (action.equalsIgnoreCase("printpreview")) {
			System.out.println("Action is printpreview");
			url = printPreview();
		}else if (action.equalsIgnoreCase("searchDetails")) {
			System.out.println("Action is viewAllStudents");
			url = searchDetails();
		}else if (action.equalsIgnoreCase("viewAllStudentsWithParents")) {
			System.out.println("Action is viewAllStudentsWithParents");
			url = viewAllStudentsWithParents();
		}else if (action.equalsIgnoreCase("AddStudent")) {
			System.out.println("Action is AddStudent");
			url = addStudent();
		}else if (action.equalsIgnoreCase("ViewDetails")) {
			System.out.println("Action is ViewDetails");
			url = viewStudent();
		}else if (action.equalsIgnoreCase("updateStudentDetails")) {
			System.out.println("Action is updateStudentDetails");
			url = updateStudentDetails();
		}else if (action.equalsIgnoreCase("updateStudent")) {
			System.out.println("Action is updateStudent");
			url = updateStudent();
		}else if (action.equalsIgnoreCase("archiveMultiple")) {
			System.out.println("Action is archiveMultiple");
			url = archiveMultiple();
		}else if (action.equalsIgnoreCase("archiveViewAll")) {
			System.out.println("Action is archiveViewAll");
			url = archiveViewAll();
		}else if (action.equalsIgnoreCase("deleteMultiple")) {
			System.out.println("Action is deleteMultiple");
			url = deleteMultiple();
		}else if (action.equalsIgnoreCase("restoreMultiple")) {
			System.out.println("Action is restoreMultiple");
			url = restoreMultiple();
		}else if (action.equalsIgnoreCase("searchByClass")) {
			System.out.println("Action is searchByClass");
			url = searchClass();
		}else if (action.equalsIgnoreCase("promoteClass")) {
			System.out.println("Action is promoteClass");
			url = promoteClass();
		}else if (action.equalsIgnoreCase("ViewFeesStructure")) {
			System.out.println("Action is ViewFeesStructure");
			url = ViewFeesStructure();
		}else if (action.equalsIgnoreCase("feesStructurePerYear")) {
			System.out.println("Action is feesStructurePerYear");
			url = feesStructurePerYear();
		}else if (action.equalsIgnoreCase("exportDataForStudents")) {
			System.out.println("Action is exportDataForStudents");
			url = exportDataForStudents();
		}else if (action.equalsIgnoreCase("searchForStudents")) {
			System.out.println("Action is searchForStudents");
			url = searchForStudents();
		}else if (action.equalsIgnoreCase("searchStudentsForBonafide")) {
			System.out.println("Action is searchStudentsForBonafide");
			url = searchStudentsForBonafide();
		}else if (action.equalsIgnoreCase("GenerateBonafide")) {
			System.out.println("Action is GenerateBonafide");
			url = generateBonafide();
		}
		return url;
	}
	
	

	private String generateBonafide() {
		
            return new StudentService(request, response).generateBonafide();
        
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
            //return "patientDetails_1.jsp";
            return "student_details_feesstructure.jsp";
        } else {
            return "viewAll.jsp";
        }
	}

	private String viewAllStudentsWithParents() {
		new StudentService(request, response).viewAllStudentsParents();
        System.out.println("IN action's view all Parents");
        return "viewAllWithParents.jsp";
	}

	private String searchDetails() {
		new StampFeesService(request, response).advanceSearch();
        
		new PrintIdsService(request, response).searchDetails();
        System.out.println("IN action's search details");
        return "generateids.jsp";
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
		if (new StudentService(request, response).viewDetailsOfStudent()) {
            //return "patientDetails_1.jsp";
            return "student_details.jsp";
        } else {
            return "viewAll.jsp";
        }
	}

	private String addStudent() {
		 if (new StudentService(request, response).addStudent()) {
	            return "saved.jsp";
	        } else {
	            return "notSaved.jsp";
	        }
		
	}

	private String printPreview() {

		new PrintIdsService(request, response).printMultiple();
        System.out.println("IN action's printPreview");
        return "printpreview.jsp";
       
		
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
