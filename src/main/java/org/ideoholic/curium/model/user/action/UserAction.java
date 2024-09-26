package org.ideoholic.curium.model.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.adminexpenses.service.AdminService;
import org.ideoholic.curium.model.feescollection.action.FeesCollectionActionAdapter;
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
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private HttpSession httpSession;
	@Autowired
	private StandardActionAdapter standardActionAdapter;
	@Autowired
	private AdminService adminService;
	@Autowired
	private FeesCollectionActionAdapter feesCollectionActionAdapter;

	@GetMapping("/sessionTimeOut")
	public String sessionTimeOut() {
		return "sessiontimeout";
	}

	@PostMapping("/searchByDate")
	public String searchByDate() {
		new UserService(request, response, standardActionAdapter,adminService, feesCollectionActionAdapter).searchByDate();
		if (httpSession.getAttribute("userType").toString().equalsIgnoreCase("superadmin")) {
			return "feesCollectionDetailsAdmin";
		} else if (httpSession.getAttribute("userType").toString().equalsIgnoreCase("admin")) {
			return "feesCollectionDetailsAdmin";
		} else if (httpSession.getAttribute("userType").toString().equalsIgnoreCase("officeadmin")) {
			return "feesCollectionDetailsAdmin";
		} else if (httpSession.getAttribute("userType").toString().equalsIgnoreCase("principal")) {
			return "feesCollectionDetailsAdmin";
		} else if (!httpSession.getAttribute("userType").toString().equalsIgnoreCase("admin")) {
			return "feesCollectionDetails";
		} else {
			return "feesCollectionDetails";
		}
	}

	@PostMapping("/advanceSearchByParents")
	public String advanceSearchByParents() {
		new UserService(request, response, standardActionAdapter,adminService, feesCollectionActionAdapter).advanceSearchByParents();
		return "viewAllWithParents";
	}

	@PostMapping("/backup")
	public String backup() {
		String fileName = request.getParameter("filename");
		if (new UserService(request, response, standardActionAdapter,adminService, feesCollectionActionAdapter).backupData(fileName)) {
			return "BackupSuccess";
		} else {
			return "BackupFailed";
		}
	}

	@PostMapping("/advanceSearch")
	public String advanceSearch() {
		new UserService(request, response, standardActionAdapter,adminService, feesCollectionActionAdapter).advanceSearch();
		return "advanceSearchResult";
	}

	@PostMapping("/dashBoard")
	public String dashBoard() {
		new UserService(request, response, standardActionAdapter,adminService, feesCollectionActionAdapter).dashBoard();
		return "jspbarchart";
	}

	@PostMapping("/authenticateUser")
	public String authenticateUser(Model model) {
		// ModelAndView model = new ModelAndView("/");
		if (new UserService(request, response, standardActionAdapter,adminService, feesCollectionActionAdapter).authenticateUser()) {
			model.addAttribute("login_success", true);
		} else {
			model.addAttribute("login_success", false);
		}
		return "login";
	}
	
	@GetMapping("/multiUser")
	public String authenticateMultiUser(Model model) {
		// ModelAndView model = new ModelAndView("/");
		if (new UserService(request, response, standardActionAdapter,adminService, feesCollectionActionAdapter).authenticateMultiUser()) {
			model.addAttribute("login_success", true);
		} else {
			model.addAttribute("login_success", false);
		}
		return "login";
	}

	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logOutUser(Model model) {
		new UserService(request, response, standardActionAdapter,adminService, feesCollectionActionAdapter).logOutUser();
		model.addAttribute("logout", true);
		return "login";
	}

	@PostMapping("/changePassword")
	public String changePassword() {
		if (new UserService(request, response, standardActionAdapter,adminService, feesCollectionActionAdapter).ChangePassword()) {
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