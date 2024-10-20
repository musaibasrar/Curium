/**
 * 
 */
package org.ideoholic.curium.model.std.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.attendance.action.AttendanceActionAdapter;
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
	private StandardActionAdapter standardActionAdapter;
		
	
		@PostMapping("/restoreMultipleLeftout")
		 public String restoreMultipleLeftout() { 
		        standardActionAdapter.restoreMultipleLeftout();
		        return viewLeftOut();
		    }
		 
		 @GetMapping("/viewLeftOut")
		public String viewLeftOut() {
		        standardActionAdapter.viewleft();
		        return "leftout";
		    }

		@PostMapping("/searchByClass")
		public String searchByClass() {
	         standardActionAdapter.searchByClass();
	         return "Promotion";
		 	}

		@PostMapping("/restoreMultipleDroppedout")
		public String restoreMultipleDroppedout() { 
	        standardActionAdapter.restoreMultipleDroppedout();
	        return viewDropped();
	    }

		@PostMapping("/restoreMultipleGraduate")
	    public String restoreMultipleGraduate() {
	        standardActionAdapter.restoreMultipleGraduate();
	        return viewGraduated();
	    }
	    
	    @GetMapping("/viewDropped")
	    public String viewDropped() {
	        standardActionAdapter.viewDropped();
	        return "droppedout";
	    }

	    @GetMapping("/viewGraduated")
		public String viewGraduated() {
	        standardActionAdapter.viewGraduated();
	        return "graduated";
		}

		@PostMapping("/leftoutMultiple")
	  public String leftoutMultiple() {
		  if(standardActionAdapter.leftoutMultiple()) {
			  return "successleftout";
		  }
		  return "failureleftout"; 
		  }

	  @PostMapping("/dropoutMultiple")
	public String droppedoutMultiple() {
		  if(standardActionAdapter.droppedoutMultiple()) {
			  return "successdroppedout";
		  }
		  return "failuredroppedout"; 
		  }

	@PostMapping("/graduateMultiple")
	public String graduateMultiple() {
		  if(standardActionAdapter.graduateMultiple()) {
			     return "successgraduate";
		  }
		  return "failuregraduate"; 
	}

	
	@PostMapping("/deleteClassHierarchy")
	public String deleteClassHierarchy() {
		  standardActionAdapter.deleteClassHierarchy();
		  return classHierarchy();
	}

	@PostMapping("/addClassHierarchy")
	public String addClassHierarchy() {
		  	standardActionAdapter.addClassHierarchy();
		  	return classHierarchy();
	}

	@GetMapping("/classHierarchy")
	public String classHierarchy() {
		  standardActionAdapter.viewClasses();
		  standardActionAdapter.viewClassHierarchy();
			return "classhierarchy";
	}

	@GetMapping("/promoteClass")
	public String promoteClass() {
		  standardActionAdapter.viewClasses();
		return "Promotion";
	}

	@PostMapping("/deleteClass")
	public String deleteClass() {
	      
	      if(standardActionAdapter.deleteClasses()) {
	          return "addclass";
	      }
	      return "error";
    }

	@GetMapping("/viewClasses")
    public String viewClasses() {
	        if(standardActionAdapter.viewClasses()) {
	            return "addclass";
	        }
	        return "error";
	    }

    @PostMapping("/createClass")
    public String createClass() {
        if(standardActionAdapter.createClass()) {
            return "addclass";
        }
        return "error";
    }

}
