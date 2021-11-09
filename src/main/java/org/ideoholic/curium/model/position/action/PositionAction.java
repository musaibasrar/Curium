package org.ideoholic.curium.model.position.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.department.service.DepartmentService;
import org.ideoholic.curium.model.position.service.PositionService;
import org.ideoholic.curium.model.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/PositionProcess")
public class PositionAction {

	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;

	@PostMapping("/deleteMultiple")
	public String deleteMultiple() {
		new PositionService(request, response).deleteMultiple();
        return viewPosition();
	}

	@GetMapping("/viewPosition")
	private String viewPosition() {
		new PositionService(request, response).viewPosition();
        System.out.println("IN action's position view");
        return "position";
	}

	
	@PostMapping("/addPosition")
	public String addPosition() {
		
		new PositionService(request, response).addPosition();
		System.out.println("IN action's add position");
		return viewPosition();
	}

}
