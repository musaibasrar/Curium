/**
 * 
 */
package com.model.mess.supplier.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.mess.item.service.MessItemsService;
import com.model.mess.supplier.service.MessSuppliersService;

/**
 * @author Musaib_2
 * 
 */
public class MessSuppliersAction {

        HttpServletRequest request;
        HttpServletResponse response;
        HttpSession httpSession;
        String url;

        public MessSuppliersAction(HttpServletRequest request,
                        HttpServletResponse response) {
                this.request = request;
                this.response = response;
                this.httpSession = request.getSession();
        }

        public String execute(String action) {
                // TODO Auto-generated method stub
                if (action.equalsIgnoreCase("addSuppliers")) {
                        url = addSuppliers();
                }else if (action.equalsIgnoreCase("viewSuppliers")) {
                    url = viewSuppliers();
                }else if (action.equalsIgnoreCase("updateSuppliers")) {
                	url = updateSuppliers();
            }else if (action.equalsIgnoreCase("deleteSuppliers")) {
            		url = deleteSuppliers();
            }                
                return url;
        }
        


        private String deleteSuppliers() {
        	new MessSuppliersService(request, response).deleteMultipleSuppliers();
            return viewSuppliers();
		}

		private String updateSuppliers() {
			
			new MessSuppliersService(request, response).updateSuppliers();
            return viewSuppliers();
		}

		private String viewSuppliers() {
        	return new MessSuppliersService(request, response).viewSuppliersDetails();
		}

        private String addSuppliers() {
        	new MessSuppliersService(request, response).addSupplierDetails();
        	return viewSuppliers();
    }


}
