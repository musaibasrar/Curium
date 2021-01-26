/**
 * 
 */
package com.model.mess.supplier.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.account.service.AccountService;
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
            }else if (action.equalsIgnoreCase("paymentSuppliers")) {
        			url = paymentSuppliers();
            }else if (action.equalsIgnoreCase("getSupplierBalance")) {
        		 	getSupplierBalance();
            }else if (action.equalsIgnoreCase("issueCheque")) {
            		url = issueCheque();
            }else if (action.equalsIgnoreCase("deliveredCheque")) {
        		url = deliveredCheque();
            }else if (action.equalsIgnoreCase("clearedCheque")) {
        		url = clearedCheque();
            }else if (action.equalsIgnoreCase("cancelCheque")) {
        		url = cancelCheque();
            }else if (action.equalsIgnoreCase("printSupplierPayment")) {
        		url = printSupplierPayment();
            }else if (action.equalsIgnoreCase("balanceSuppliers")) {
        		url = balanceSuppliers();
            }else if (action.equalsIgnoreCase("printSuppliersBalance")) {
        		url = printSuppliersBalance(); 
            }else if (action.equalsIgnoreCase("supplierPaymentReport")) {
        		url = supplierPaymentReport();
            }else if (action.equalsIgnoreCase("searchSupplierPaymentDetails")) {
        		url = searchSupplierPaymentDetails();
            }else if (action.equalsIgnoreCase("printSearchSupplierPaymentDetails")) {
        		url = printSearchSupplierPaymentDetails();
            }                
                return url;
        }
        
        

        private String printSearchSupplierPaymentDetails() {
    		new AccountService(request, response).printSearchJournalEntries();
    		return "printsupplierpaymentdetails.jsp";
    	}

		private String searchSupplierPaymentDetails() {
    		new AccountService(request, response).searchJournalEntries();
    		new MessSuppliersService(request, response).viewSuppliersDetails();
    		return "supplierpaymentdetails.jsp";
    	}

		private String supplierPaymentReport() {
        	new MessSuppliersService(request, response).viewSuppliersDetails();
    		return "supplierpaymentdetails.jsp";
		}

		private String printSuppliersBalance() {
        	new MessSuppliersService(request, response).viewBalanceSuppliers();
			return "printsuppliersbalance.jsp";
		}

		private String balanceSuppliers() {
        	new MessSuppliersService(request, response).viewBalanceSuppliers();
			return "suppliersbalance.jsp";
		}

		private String printSupplierPayment() {
        	new MessSuppliersService(request, response).viewSuppliersDetails();
        	new MessSuppliersService(request, response).viewSuppliersPaymentDetails();
        	return "printsupplierpayment.jsp";
		}

		private String cancelCheque() {
        	
        	new MessSuppliersService(request, response).cancelCheque();
        	new MessSuppliersService(request, response).viewSuppliersDetails();
        	new MessSuppliersService(request, response).viewSuppliersPaymentDetails();
			return "supplierpayment.jsp";
			
		}

		private String clearedCheque() {
        	
        	new MessSuppliersService(request, response).clearedCheque();
        	new MessSuppliersService(request, response).viewSuppliersDetails();
        	new MessSuppliersService(request, response).viewSuppliersPaymentDetails();
			return "supplierpayment.jsp";
			
		}

		private String deliveredCheque() {
        	new MessSuppliersService(request, response).deliveredCheque();
        	new MessSuppliersService(request, response).viewSuppliersDetails();
        	new MessSuppliersService(request, response).viewSuppliersPaymentDetails();
			return "supplierpayment.jsp";
		}

		private String issueCheque() {
        	new MessSuppliersService(request, response).issueCheque();
        	new MessSuppliersService(request, response).viewSuppliersDetails();
        	new MessSuppliersService(request, response).viewSuppliersPaymentDetails();
			return "supplierpayment.jsp";
		}

		private void getSupplierBalance() {
			try {
				new MessSuppliersService(request, response).getSupplierBalance();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		private String paymentSuppliers() {
        	new MessSuppliersService(request, response).viewSuppliersDetails();
        	new MessSuppliersService(request, response).viewSuppliersPaymentDetails();
        	return "supplierpayment.jsp";
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
