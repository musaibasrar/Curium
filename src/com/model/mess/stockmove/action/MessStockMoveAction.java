/**
 * 
 */
package com.model.mess.stockmove.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.mess.item.dao.MessItemsDAO;
import com.model.mess.item.service.MessItemsService;
import com.model.mess.stockmove.service.MessStockMoveService;
import com.model.mess.supplier.service.MessSuppliersService;

/**
 * @author Musaib_2
 * 
 */
public class MessStockMoveAction {

        HttpServletRequest request;
        HttpServletResponse response;
        HttpSession httpSession;
        String url = null;

        public MessStockMoveAction(HttpServletRequest request,
                        HttpServletResponse response) {
                this.request = request;
                this.response = response;
                this.httpSession = request.getSession();
        }

        public String execute(String action) {
        	
               if (action.equalsIgnoreCase("saveStockMove")) {
            	   		url = saveStockMove();
                }else if(action.equalsIgnoreCase("issueItems")) {
                	   url = issueItems();
                }else if(action.equalsIgnoreCase("cancelStockMove")) {
             	   url = cancelStockMove();
                }
                return url;
        }
        


        private String cancelStockMove() {
        	
        	new MessStockMoveService(request, response).cancelStockMove();
        	new MessStockMoveService(request, response).viewStockEntryDetails();
        	new MessStockMoveService(request, response).viewStockMoveDetails();
        	return "issuestock.jsp";
		}

		private String issueItems() {
			new MessItemsService(request, response).getCurrentStockToIssue();
			/*Batch stock issue 
        	new MessStockMoveService(request, response).viewStockEntryDetails();*/
        	new MessStockMoveService(request, response).viewStockMoveDetails();
        	return "issuestock.jsp";
        }
        
        private String saveStockMove() {
        	
        	new MessStockMoveService(request, response).saveStockMove();
        	new MessItemsService(request, response).getCurrentStockToIssue();
        	/*Batch stock issue 
        	new MessStockMoveService(request, response).viewStockEntryDetails();*/
        	new MessStockMoveService(request, response).viewStockMoveDetails();
        	return "issuestock.jsp";
        	
		}

}
