/**
 * 
 */
package org.ideoholic.curium.model.std.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.std.service.StandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Musaib_2
 * 
 */


@Controller
@RequestMapping("/ClassProcess")
public class StandardAction {

	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpServletResponse response;
	
	@Autowired
	HttpSession httpSession;
		
	
		@PostMapping("/restoreMultipleLeftout")
		 public String restoreMultipleLeftout() { 
		        new StandardService(request, response).restoreMultipleLeftout();
		        return viewLeftOut();
		    }
		 
		 @GetMapping("/viewLeftOut")
		public String viewLeftOut() {
		        new StandardService(request, response).viewleft();
		        return "leftout";
		    }

		@PostMapping("/searchByClass")
		public String searchByClass() {
	         new StandardService(request, response).searchByClass();
	         return "Promotion";
		 	}

		@PostMapping("/restoreMultipleDroppedout")
		public String restoreMultipleDroppedout() { 
	        new StandardService(request, response).restoreMultipleDroppedout();
	        return viewDropped();
	    }

		@PostMapping("/restoreMultipleGraduate")
	    public String restoreMultipleGraduate() {
	        new StandardService(request, response).restoreMultipleGraduate();
	        return viewGraduated();
	    }
	    
	    @GetMapping("/viewDropped")
	    public String viewDropped() {
	        new StandardService(request, response).viewDropped();
	        return "droppedout";
	    }

	    @GetMapping("/viewGraduated")
		public String viewGraduated() {
	        new StandardService(request, response).viewGraduated();
	        return "graduated";
		}

		@PostMapping("/leftoutMultiple")
	  public String leftoutMultiple() {
		  if(new StandardService(request, response).leftoutMultiple()) {  
			  return "successleftout";
		  }
		  return "failureleftout"; 
		  }

	  @PostMapping("/dropoutMultiple")
	public String droppedoutMultiple() {
		  if(new StandardService(request, response).droppedoutMultiple()) { 
			  return "successdroppedout";
		  }
		  return "failuredroppedout"; 
		  }

	@PostMapping("/graduateMultiple")
	public String graduateMultiple() {
		  if(new StandardService(request, response).graduateMultiple()) { 
			     return "successgraduate";
		  }
		  return "failuregraduate"; 
	}

	
	@PostMapping("/deleteClassHierarchy")
	public String deleteClassHierarchy() {
		  new StandardService(request, response).deleteClassHierarchy();
		  return classHierarchy();
	}

	@PostMapping("/addClassHierarchy")
	public String addClassHierarchy() {
		  	new StandardService(request, response).addClassHierarchy();
		  	return classHierarchy();
	}

	@GetMapping("/classHierarchy")
	public String classHierarchy() {
		  new StandardService(request, response).viewClasses();
		  new StandardService(request, response).viewClassHierarchy();
			return "classhierarchy";
	}

	@GetMapping("/promoteClass")
	public String promoteClass() {
		  new StandardService(request, response).viewClasses();
		return "Promotion";
	}

	@PostMapping("/deleteClass")
	public String deleteClass() {
	      
	      if( new StandardService(request, response).deleteClasses()) {
	          return "addclass";
	      }
	      return "error";
    }

	@GetMapping("/viewClasses")
    public String viewClasses() {
	        if(new StandardService(request, response).viewClasses()) {
	            return "addclass";
	        }
	        return "error";
	    }

    @PostMapping("/createClass")
    public String createClass() {
        if(new StandardService(request, response).createClass()) {
            return "addclass";
        }
        return "error";
    }

}
