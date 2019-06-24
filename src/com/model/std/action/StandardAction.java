/**
 * 
 */
package com.model.std.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.std.service.StandardService;
import com.model.student.service.StudentService;

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
        }else if (action.equalsIgnoreCase("viewGraduated")) {
            url = viewGraduated();
        }else if (action.equalsIgnoreCase("viewDropped")) {
            url = viewDropped();
        }else if (action.equalsIgnoreCase("viewLeftOut")) {
            url = viewLeftOut();
        }else if (action.equalsIgnoreCase("restoreMultipleGraduate")) {
            url = restoreMultipleGraduate();
        }else if (action.equalsIgnoreCase("restoreMultipleDroppedout")) {
            url = restoreMultipleDroppedout();
        }else if (action.equalsIgnoreCase("restoreMultipleLeftout")) {
            url = restoreMultipleLeftout();
        }else if (action.equalsIgnoreCase("searchByClass")) {
            url = searchByClass();
        }
		return url;
	}
	
	
		 private String restoreMultipleLeftout() { 
		        new StandardService(request, response).restoreMultipleLeftout();
		        return "Controller?process=ClassProcess&action=viewLeftOut";
		    }

		private String viewLeftOut() {
		        new StandardService(request, response).viewleft();
		        return "leftout.jsp";
		    }

		private String searchByClass() {
	         new StandardService(request, response).searchByClass();
	         return "Promotion.jsp";
		 	}

		private String restoreMultipleDroppedout() { 
	        new StandardService(request, response).restoreMultipleDroppedout();
	        return "Controller?process=ClassProcess&action=viewDropped";
	    }

	    private String restoreMultipleGraduate() {
	        new StandardService(request, response).restoreMultipleGraduate();
	        return "Controller?process=ClassProcess&action=viewGraduated";
	    }
	    
	    private String viewDropped() {
	        new StandardService(request, response).viewDropped();
	        return "droppedout.jsp";
	    }

		private String viewGraduated() {
	        new StandardService(request, response).viewGraduated();
	        return "graduated.jsp";
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
