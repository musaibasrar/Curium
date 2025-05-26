package org.ideoholic.curium.model.login.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/LoginProcess")
public class LoginAction {
	
	@Autowired
    private LoginActionAdapter loginActionAdapter;
	
	 @RequestMapping(value = "/viewLoginDetail", method = { RequestMethod.GET, RequestMethod.POST })
	    public String viewLoginDetail() {
	        loginActionAdapter.viewLogin();
	        return "viewlogindetail";

	    }
	 
	 @PostMapping("/deleteRecord")
	    public String deleteRecord() {
		 loginActionAdapter.deleteRecord();
		 loginActionAdapter.viewLogin();
	        return "viewlogindetail";
	    }
	 
	 @GetMapping("/logindetail")
	    public String logindetail() {
		 loginActionAdapter.viewLoginDetail();
	        return "viewdetailslogin";
	    }
	 
	 @PostMapping("/updateLoginDetails")
	    public String updateLoginDetails() {
		 loginActionAdapter.viewLoginDetail();
	        return "updatelogindetail";
	    }

	 @PostMapping("/updateDetailsOfLogin")
	    public String updateDetailsOfLogin() {
   		 if(loginActionAdapter.updateDetailsOfLogin())
   		 {
		 loginActionAdapter.viewLoginDetail();
	        return "viewdetailslogin";
   		 }
   		 return "error";
	    }

	 @GetMapping("/addLoginStaff")
	    public String addLoginStaff() {
		 loginActionAdapter.readListOfBranchId();
	        return "addlogindetail";
	    }
	 
	 @PostMapping("/addLoginStaffDetail")
	    public String addLoginStaffDetail() {
		 if(loginActionAdapter.addLoginStaffDetail())
		 {
	        return "addedsuccesfully";
		 }
		 return "error";
	    }

}
