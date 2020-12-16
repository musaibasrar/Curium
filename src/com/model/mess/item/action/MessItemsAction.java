/**
 * 
 */
package com.model.mess.item.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.mess.item.service.MessItemsService;

/**
 * @author Musaib_2
 * 
 */
public class MessItemsAction {

        HttpServletRequest request;
        HttpServletResponse response;
        HttpSession httpSession;
        String url;

        public MessItemsAction(HttpServletRequest request,
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
                }else if (action.equalsIgnoreCase("updateItems")) {
                    	url = updateItems();
                }else if (action.equalsIgnoreCase("deleteItems")) {
                		url = deleteItems();
                }else if (action.equalsIgnoreCase("addSuppliers")) {
                        url = addSuppliers();
                }else if (action.equalsIgnoreCase("viewItems")) {
                    url = viewItems();
                }                
                return url;
        }
        


        private String deleteItems() {
        	new MessItemsService(request, response).deleteMultipleItems();
            return viewItems();
		}

		private String updateItems() {
			
			new MessItemsService(request, response).updateItems();
            return viewItems();
		}

		private String viewItems() {
        	return new MessItemsService(request, response).viewItemDetails();
		}

		private String addItems() {
        		 new MessItemsService(request, response).addItemDetails();
        		 return viewItems();
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
