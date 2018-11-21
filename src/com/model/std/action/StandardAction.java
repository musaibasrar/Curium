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
        }else if (action.equalsIgnoreCase("promoteClass")) {
                   url = promoteClass();
        }else if (action.equalsIgnoreCase("classHierarchy")) {
            url = classHierarchy();
        }else if (action.equalsIgnoreCase("addClassHierarchy")) {
            url = addClassHierarchy();
        }else if (action.equalsIgnoreCase("deleteClassHierarchy")) {
        	url = deleteClassHierarchy();
        }else if (action.equalsIgnoreCase("graduateMultiple")) {
        	url = graduateMultiple();
        }else if (action.equalsIgnoreCase("dropoutMultiple")) {
        	url = droppedoutMultiple();
        }else if (action.equalsIgnoreCase("leftoutMultiple")) {
            url = leftoutMultiple();
        }
		return url;
	}

	  private String leftoutMultiple() {
		  if(new StandardService(request, response).leftoutMultiple()) {  
			  return "successleftout.jsp";
		  }
		  return "failureleftout.jsp"; 
		  }

	private String droppedoutMultiple() {
		  if(new StandardService(request, response).droppedoutMultiple()) { 
			  return "successdroppedout.jsp";
		  }
		  return "failuredroppedout.jsp"; 
		  }

	private String graduateMultiple() {
		  if(new StandardService(request, response).graduateMultiple()) { 
			     return "successgraduate.jsp";
		  }
		  return "failuregraduate.jsp"; 
	}

	
	
	private String deleteClassHierarchy() {
		  new StandardService(request, response).deleteClassHierarchy();
	      return "Controller?process=ClassProcess&action=classHierarchy";
	}

	private String addClassHierarchy() {
		  	new StandardService(request, response).addClassHierarchy();
	        return "Controller?process=ClassProcess&action=classHierarchy";
	}

	private String classHierarchy() {
		  new StandardService(request, response).viewClasses();
		  new StandardService(request, response).viewClassHierarchy();
			return "classhierarchy.jsp";
	}

	private String promoteClass() {
		  new StandardService(request, response).viewClasses();
		return "Promotion.jsp";
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
