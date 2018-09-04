/**
 * 
 */
package com.model.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.academicyear.service.YearService;
import com.model.branch.dao.BranchDAO;
import com.model.branch.service.BranchService;
import com.model.examdetails.service.ExamDetailsService;
import com.model.examlevels.service.ExamLevelService;
import com.model.language.service.LanguageService;
import com.model.order.service.OrderService;
import com.model.stampfees.service.StampFeesService;
import com.model.student.service.StudentService;
import com.model.subjectdetails.service.SubjectDetailsService;
import com.util.DataUtil;

/**
 * @author Musaib_2
 *
 */
public class OrderAction {

	HttpServletRequest request;
	HttpServletResponse response;
	String url;
	String error = "error.jsp";
	
	public OrderAction(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public String execute(String action) {
		if ("booksDetails".equalsIgnoreCase(action)) {
			url = booksDetails();
		}else if ("addBooks".equalsIgnoreCase(action)) {
			url = addBooks();
		}else if ("updateBooks".equalsIgnoreCase(action)) {
			url = updateBooks();
		}else if ("deleteMultipleBooks".equalsIgnoreCase(action)) {
			url = deleteMultipleBooks();
		}else if ("placeOrder".equalsIgnoreCase(action)) {
                    url = placeOrder();
                }else if ("confirmOrder".equalsIgnoreCase(action)) {
                    url = confirmOrder();
                }else if ("viewOrder".equalsIgnoreCase(action)) {
                    url = viewOrder();
                }else if ("rejectOrders".equalsIgnoreCase(action)) {
                    url = rejectOrders();
                }else if ("deliverOrders".equalsIgnoreCase(action)) {
                    url = deliverOrders();
                }else if ("updateOrders".equalsIgnoreCase(action)) {
                    url = updateOrders();
                }else if ("searchOrders".equalsIgnoreCase(action)) {
                    url = searchOrders();
                }else if ("viewOrderDetails".equalsIgnoreCase(action)) {
                    url = viewOrderDetails();
                }else if ("viewOrderCenter".equalsIgnoreCase(action)) {
                    url = viewOrderCenter();
                }else if ("searchOrdersCenter".equalsIgnoreCase(action)) {
                    url = searchOrdersCenter();
                }
		return url;
	}

		
	private String searchOrdersCenter() {
            new OrderService(request, response).searchOrdersCenter();
            return "viewordercenter.jsp";
    }

    private String viewOrderCenter() {
            new OrderService(request, response).viewOrderCenter();
            return "viewordercenter.jsp";
    }

    private String viewOrderDetails() {
	    new OrderService(request, response).viewOrderDetails();
        return "orderdetails.jsp";
    }

    private String searchOrders() {
	    new OrderService(request, response).searchOrders();
            return "vieworder.jsp";
    }

    private String updateOrders() {
	    new OrderService(request, response).updateOrders();
            return "Controller?process=OrderProcess&action=viewOrder";
    }

    private String deliverOrders() {
	    new OrderService(request, response).deliverOrders();
            return "Controller?process=OrderProcess&action=viewOrder";
    }

    private String rejectOrders() {
	    new OrderService(request, response).rejectOrders();
            return "Controller?process=OrderProcess&action=viewOrder";
    }

    private String viewOrder() {
	    new OrderService(request, response).viewOrder();
            return "vieworder.jsp";
    }

    private String confirmOrder() {
	    new OrderService(request, response).confirmOrder();
            return "Controller?process=OrderProcess&action=placeOrder";
    }

    private String placeOrder() {
	    new OrderService(request, response).bookDetails();
	        return "placeorder.jsp";
    }

    private String deleteMultipleBooks() {
	    new OrderService(request, response).deleteMultipleBooks();
            return "Controller?process=OrderProcess&action=booksDetails";
    }

    private String updateBooks() {
	    new OrderService(request, response).updateBooks();
            return "Controller?process=OrderProcess&action=booksDetails";
    }

    private String addBooks() {
	    new OrderService(request, response).addBooks();
	       return "Controller?process=OrderProcess&action=booksDetails";
    }

    private String booksDetails() {
         new OrderService(request, response).bookDetails();
        return "booksdetails.jsp";
    }

    private String printPreviewHallTicket() {
		
		new ExamDetailsService(request, response).printPreviewHallTicket();
		return "printpreviewhallticket.jsp";
	}

	private String searchHallTicketDetails() {
		
	        new ExamLevelService(request, response).examLevels();
                new BranchService(request, response).viewBranches();
            
		new ExamDetailsService(request, response).getExamScheduleDetails();
		new ExamDetailsService(request, response).readListOfExams();
		new SubjectDetailsService(request, response).readListOfSubjects();
		
		return "generatehallticket.jsp";
	}

	private String generateHallTicket() {
		
		boolean result;
		
		    new ExamLevelService(request, response).examLevels();
	            new BranchService(request, response).viewBranches();
	            
		result = new SubjectDetailsService(request, response).readListOfSubjects();
		if (!result) 
			return error;
		result = new YearService(request, response).getYear();
		if (!result) 
			return error;
		result = new StudentService(request, response).viewAllStudentsList();
		if (!result) 
			return error;
		
		return "generatehallticket.jsp";
	}

	private String deleteExamSchedule() {
		
		if(new ExamDetailsService(request, response).deleteExamSchedule()){
			return "Controller?process=ExamDetailsProcess&action=examSchedule";
		}else{
			return "error.jsp";
		}
	}

	private String addSchedule() {
		
		if(new ExamDetailsService(request, response).addSchedule()){
			return "Controller?process=ExamDetailsProcess&action=examSchedule";
		}else{
			return "error.jsp";
		}
	}

	private String examSchedule() {
		
		boolean result;
		
		new ExamLevelService(request, response).examLevels();
		new BranchService(request, response).viewBranches();
		result = new SubjectDetailsService(request, response).readListOfSubjects();
		if (!result) 
			return error;
		result = new YearService(request, response).getYear();
		if (!result) 
			return error;
		result = new ExamDetailsService(request, response).getExamSchedule();
		if (!result) 
			return error;
		
		return "examschedule.jsp";
		
	}

	private String deleteMultiple() {
		if(new ExamDetailsService(request, response).deleteMultiple()){
			return "Controller?process=ExamDetailsProcess&action=readListOfExams";
		}else{
			return "error.jsp";
		}
	}

	private String readListOfExams() {
		if(new ExamDetailsService(request, response).readListOfExams()){
			return "ExamDetails.jsp";
		}else{
			return "error.jsp";
		}
	}

	private String addExam() {
		
		if(new ExamDetailsService(request, response).addExam()){
			return "Controller?process=ExamDetailsProcess&action=readListOfExams";
		}else{
			return "error.jsp";
		}
        
	}

}
