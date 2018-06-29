package com.model.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.user.service.UserService;

public class UserAction {
	HttpServletRequest request;
    HttpServletResponse response;
    private String url;
	
	public UserAction(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response=response;
	}

	public String execute(String action) {
	       if (action.equalsIgnoreCase("authenticateUser")) {
	            url = authenticateUser();
	        }else if (action.equalsIgnoreCase("logout")) {
	            url = logOutUser();
	        }else if (action.equalsIgnoreCase("changePassword")) {
	            url = changePassword();
	        }else if (action.equalsIgnoreCase("dashBoard")) {
				url = dashBoard();
			}else if (action.equalsIgnoreCase("advanceSearch")) {
				url = advanceSearch();
			}else if (action.equalsIgnoreCase("advanceSearchByParents")) {
				url = advanceSearchByParents();
			}else if (action.equalsIgnoreCase("backup")) {
				url = backup();
			}else if (action.equalsIgnoreCase("searchByDate")) {
				url = searchByDate();
			} else if (action.equalsIgnoreCase("sessionTimeOut")) {
	            url = sessionTimeOut();
	        }
	       return url;
	       
	}

	private String sessionTimeOut() {
		return "sessiontimeout.jsp";
	}

	private String searchByDate() {
		new UserService(request, response).searchByDate();
        return "feesCollectionDetails.jsp";
	}

	private String advanceSearchByParents() {
		new UserService(request, response).advanceSearchByParents();
        return "viewAllWithParents.jsp";
	}

	private String backup() {
		 String fileName = request.getParameter("filename");
		if(new UserService(request, response).backupData(fileName)){
			
			return"BackupSuccess.jsp";
	       }else{
	           return"BackupFailed.jsp";
		}
	}

	private String advanceSearch() {
		new UserService(request, response).advanceSearch();
        return "advanceSearchResult.jsp";
	}

	private String dashBoard() {
		new UserService(request, response).dashBoard();
        //return "dashBoard.jsp";
		return "jspbarchart.jsp";
	}

	private String authenticateUser() {
		if (new UserService(request, response).authenticateUser()) {
			
        return "login.jsp?login_success=true";
    } else {
        return "login.jsp?login_success=false";
    }
	}

	private String logOutUser() {
		new UserService(request, response).logOutUser();
        return "login.jsp?logout=true";
	}
	
	private String changePassword() {
		
		if(new UserService(request, response).ChangePassword()){
	        return "passwordSuccess.jsp";
	    }else{
	        return "passwordFail.jsp";
	    }
	}

}
