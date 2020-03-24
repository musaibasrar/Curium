package com.model.feescategory.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.feescategory.dto.Feescategory;
import com.model.feescategory.service.FeesService;
import com.model.std.service.StandardService;
import com.model.student.service.StudentService;
import com.util.DataUtil;
import com.util.DateUtil;

public class FeesAction {
        HttpServletRequest request;
        HttpServletResponse response;
        HttpSession httpSession;
        String url;
        String error = "error.jsp";
        
        public FeesAction(HttpServletRequest request, HttpServletResponse response) {
                this.request = request;
                this.response = response;
                this.httpSession = request.getSession();
        }

        public String execute(String action, String page) {
                
                 if ("feesView".equalsIgnoreCase(action)) {
                        url = viewFees();
                }else if ("addFeesParticular".equalsIgnoreCase(action)) {
                        url = addFeesParticular();
                }else if ("feesCollect".equalsIgnoreCase(action)) {
                        url = feesCollect();
                }else if ("deleteMultiple".equalsIgnoreCase(action)) {
                        url = deleteMultiple();
                }else if ("deleteFeesCategory".equalsIgnoreCase(action)) {
                        url = deleteFeesCategory();
                }else if ("feesCollectAllBranches".equalsIgnoreCase(action)) {
                    url = feesCollectAllBranches();
                }else if ("feesStructure".equalsIgnoreCase(action)) {
                    url = feesStructure();
                }else if ("feesReport".equalsIgnoreCase(action)) {
                    url = feesReport();
                }
                return url;
        }

        private String feesReport() {
        	new StandardService(request, response).viewClasses();
			return "feesreport.jsp";
		}

		private String feesStructure() {
            new StandardService(request, response).viewClasses();
        return "feesstructure.jsp";
    }

    private String deleteFeesCategory() {
                        return new FeesService(request, response).deleteFeesCategory();
        }

        private String deleteMultiple() {
                new FeesService(request, response).deleteMultiple();
        return "Controller?process=FeesProcess&action=feesView";
        }

        private String feesCollect() {
                
        new FeesService(request, response).viewFees();
        new FeesService(request, response).viewAllStudentsList();
        return "feesCollection.jsp";
    
        }

        private String feesCollectAllBranches() {
            
                new FeesService(request, response).viewFees();
                new FeesService(request, response).viewAllBranchStudents();
                return "feesCollection.jsp";
            
                }
        
        private String addFeesParticular() {
                
                
                new FeesService(request, response).addFeesParticular();
        return "Controller?process=FeesProcess&action=feesView";
                
        }

        private String viewFees() {
                new FeesService(request, response).viewFees();
                new StandardService(request, response).viewClasses();
                return "feesCategory.jsp";
        }

}
