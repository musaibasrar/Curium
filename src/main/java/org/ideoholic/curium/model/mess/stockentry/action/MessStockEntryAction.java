/**
 * 
 */
package org.ideoholic.curium.model.mess.stockentry.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.account.service.AccountService;
import org.ideoholic.curium.model.mess.item.service.MessItemsService;
import org.ideoholic.curium.model.mess.stockentry.service.MessStockEntryService;
import org.ideoholic.curium.model.mess.supplier.service.MessSuppliersService;

/**
 * @author Musaib_2
 * 
 */
public class MessStockEntryAction {

        HttpServletRequest request;
        HttpServletResponse response;
        HttpSession httpSession;
        String url = null;

        public MessStockEntryAction(HttpServletRequest request,
                        HttpServletResponse response) {
                this.request = request;
                this.response = response;
                this.httpSession = request.getSession();
        }

        public String execute(String action) {
        	
               if (action.equalsIgnoreCase("mrvdetails")) {
                    	mrvDetails();
                }                
                return url;
        }
        


        private void mrvDetails() {

			try {
				new MessStockEntryService(request, response).getMRVDetails();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}

		private String savePurchase() {
			
        	return "purchase";
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

        private String purchaseItems() {
        	new MessSuppliersService(request, response).viewSuppliersDetails();
        	new MessItemsService(request, response).viewItemDetails();
                return "purchase";
        }
        
        private String addSuppliers() {
            return "addsuppliers";
    }


}
