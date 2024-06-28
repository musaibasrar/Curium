package org.ideoholic.curium.model.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.std.action.StandardActionAdapter;
import org.ideoholic.curium.model.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/UserProcess")
public class UserAction {

	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	@Autowired
	HttpSession httpSession;
	@Autowired
	StandardActionAdapter standardActionAdapter;

	@GetMapping("/sessionTimeOut")
	public String sessionTimeOut() {
		return "sessiontimeout";
	}

	@PostMapping("/searchByDate")
	public String searchByDate() {
		new UserService(request, response, standardActionAdapter).searchByDate();
		return "feesCollectionDetails";
	}

	@PostMapping("/advanceSearchByParents")
	public String advanceSearchByParents() {
		new UserService(request, response, standardActionAdapter).advanceSearchByParents();
		return "viewAllWithParents";
	}

	@PostMapping("/backup")
	public String backup() {
		String fileName = request.getParameter("filename");
		if (new UserService(request, response, standardActionAdapter).backupData(fileName)) {
			return "BackupSuccess";
		} else {
			return "BackupFailed";
		}
	}

	@PostMapping("/advanceSearch")
	public String advanceSearch() {
		new UserService(request, response, standardActionAdapter).advanceSearch();
		return "advanceSearchResult";
	}

	@PostMapping("/dashBoard")
	public String dashBoard() {
		new UserService(request, response, standardActionAdapter).dashBoard();
		return "jspbarchart";
	}

	@PostMapping("/authenticateUser")
	public String authenticateUser(Model model) {
		// ModelAndView model = new ModelAndView("/");
		if (new UserService(request, response, standardActionAdapter).authenticateUser()) {
			model.addAttribute("login_success", true);
		} else {
			model.addAttribute("login_success", false);
		}
		return "login";
	}
	
	@GetMapping("/multiUser")
	public String authenticateMultiUser(Model model) {
		// ModelAndView model = new ModelAndView("/");
		if (new UserService(request, response, standardActionAdapter).authenticateMultiUser()) {
			model.addAttribute("login_success", true);
		} else {
			model.addAttribute("login_success", false);
		}
		return "login";
	}

	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logOutUser(Model model) {
		new UserService(request, response, standardActionAdapter).logOutUser();
		model.addAttribute("logout", true);
		return "login";
	}

	@PostMapping("/changePassword")
	public String changePassword() {
		if (new UserService(request, response, standardActionAdapter).ChangePassword()) {
			return "passwordSuccess";
		} else {
			return "passwordFail";
		}
	}
	
	public void setHttpobjects(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) {
		this.request = request;
		this.response = response;
		this.httpSession = httpSession;
	}

}