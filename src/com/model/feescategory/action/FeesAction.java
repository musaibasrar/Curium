package com.model.feescategory.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.feescategory.service.FeesService;
import com.model.std.service.StandardService;

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
                }else if ("feesStructure".equalsIgnoreCase(action)) {
                    url = feesStructure();
                }
                return url;
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
