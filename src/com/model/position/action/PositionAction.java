package com.model.position.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.department.service.DepartmentService;
import com.model.position.service.PositionService;
import com.model.student.service.StudentService;

public class PositionAction {

	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession httpSession;
	String url;
	
	public PositionAction(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

	public String execute(String action, String page) {
		if (action.equalsIgnoreCase("addPosition")) {
			System.out.println("Action is add position");
			url = addPosition();
		}else if (action.equalsIgnoreCase("positionView")) {
			System.out.println("Action is positionView");
			url = viewPosition();
		}else if (action.equalsIgnoreCase("deleteMultiple")) {
			System.out.println("Action is deleteMultiple");
			url = deleteMultiple();
		}
		return url;
	}

	private String deleteMultiple() {
		new PositionService(request, response).deleteMultiple();
        return "Controller?process=PositionProcess&action=positionView";
	}

	private String viewPosition() {
		new PositionService(request, response).viewPosition();
        System.out.println("IN action's position view");
        return "position.jsp";
	}

	private String addPosition() {
		
		new PositionService(request, response).addPosition();
		System.out.println("IN action's add position");
		return "Controller?process=PositionProcess&action=positionView";
	}

}
