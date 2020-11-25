/**
 * 
 */
package com.model.mess.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.stampfees.service.StampFeesService;
import com.model.std.service.StandardService;
import com.model.student.service.StudentService;

/**
 * @author Musaib_2
 * 
 */
public class MessAction {

        HttpServletRequest request;
        HttpServletResponse response;
        HttpSession httpSession;
        String url;

        public MessAction(HttpServletRequest request,
                        HttpServletResponse response) {
                this.request = request;
                this.response = response;
                this.httpSession = request.getSession();
        }

        public String execute(String action) {
                // TODO Auto-generated method stub
                if (action.equalsIgnoreCase("issueItems")) {
                        url = issueItems();
                }else if (action.equalsIgnoreCase("purchaseItems")) {
                        url = purchaseItems();
                }else if (action.equalsIgnoreCase("addItems")) {
                        url = addItems();
                }else if (action.equalsIgnoreCase("addSuppliers")) {
                        url = addSuppliers();
                }                return url;
        }
        


        private String addItems() {
        	 	
        		return "additems.jsp";
                /* if (new StudentService(request, response).addStudent()) {
                    return "saved.jsp";
                } else {
                    return "notSaved.jsp";
                }
                */
        }

        private String issueItems() {
        	return "issuestock.jsp";
        }


        private String purchaseItems() {
                return "purchase.jsp";
        }
        
        private String addSuppliers() {
            return "addsuppliers.jsp";
    }


}
