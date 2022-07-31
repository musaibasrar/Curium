package org.ideoholic.curium.model.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	@GetMapping("/sessionTimeOut")
	public String sessionTimeOut() {
		return "sessiontimeout";
	}

	@PostMapping("/searchByDate")
	public String searchByDate() {
		new UserService(request, response).searchByDate();
		return "feesCollectionDetails";
	}

	@PostMapping("/advanceSearchByParents")
	public String advanceSearchByParents() {
		new UserService(request, response).advanceSearchByParents();
		return "viewAllWithParents";
	}

	@PostMapping("/backup")
	public String backup() {
		String fileName = request.getParameter("filename");
		if (new UserService(request, response).backupData(fileName)) {
			return "BackupSuccess";
		} else {
			return "BackupFailed";
		}
	}

	@PostMapping("/advanceSearch")
	public String advanceSearch() {
		new UserService(request, response).advanceSearch();
		return "advanceSearchResult";
	}

	@GetMapping("/dashBoard")
	public String dashBoard() {
		new UserService(request, response).dashBoard();
		return "jspbarchart";
	}

	@PostMapping("/authenticateUser")
	public String authenticateUser(Model model) {
		// ModelAndView model = new ModelAndView("/");
		if (new UserService(request, response).authenticateUser()) {
			model.addAttribute("login_success", true);
		} else {
			model.addAttribute("login_success", false);
		}
		return "login";
	}
	
	@GetMapping("/multiUser")
	public String authenticateMultiUser(Model model) {
		// ModelAndView model = new ModelAndView("/");
		if (new UserService(request, response).authenticateMultiUser()) {
			model.addAttribute("login_success", true);
		} else {
			model.addAttribute("login_success", false);
		}
		return "login";
	}

	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logOutUser(Model model) {
		new UserService(request, response).logOutUser();
		model.addAttribute("logout", true);
		return "login";
	}

	@PostMapping("/changePassword")
	public String changePassword() {
		if (new UserService(request, response).ChangePassword()) {
			return "passwordSuccess";
		} else {
			return "passwordFail";
		}
	}
	
	public void setHttpobjects(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

}