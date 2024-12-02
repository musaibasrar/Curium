package org.ideoholic.curium.model.position.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ideoholic.curium.model.position.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/PositionProcess")
public class PositionAction {

	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private PositionActionAdapter positionActionAdapter;

	@PostMapping("/deleteMultiple")
	public String deleteMultiple() {
		positionActionAdapter.deleteMultiple();
		return viewPosition();
	}

	@GetMapping("/positionView")
	public String viewPosition() {
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
