/**
 * 
 */
package com.model.std.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.std.service.StandardService;

/**
 * @author Musaib_2
 * 
 */
public class StandardAction {

	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession httpSession;
	String url;

	public StandardAction(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

	public String execute(String action) {
		if (action.equalsIgnoreCase("viewClasses")) {
			url = viewClasses();
		}else if (action.equalsIgnoreCase("createClass")) {
                        url = createClass();
                 }else if (action.equalsIgnoreCase("deleteClass")) {
                        url = deleteClass();
               }
		
		return url;
	}
	
	
	  private String deleteClass() {
	      
	      if( new StandardService(request, response).deleteClasses()) {
	          return "addclass.jsp";
	      }
	      return "error.jsp";
    }

    private String viewClasses() {
	        if(new StandardService(request, response).viewClasses()) {
	            return "addclass.jsp";
	        }
	        return "error.jsp";
	    }

    private String createClass() {
        if(new StandardService(request, response).createClass()) {
            return "addclass.jsp";
        }
        return "error.jsp";
    }

}
