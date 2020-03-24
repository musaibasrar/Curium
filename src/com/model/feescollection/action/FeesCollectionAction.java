/**
 * 
 */
package com.model.feescollection.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.feescategory.service.FeesService;
import com.model.feescollection.dto.Receiptinfo;
import com.model.feescollection.service.FeesCollectionService;

/**
 * @author Musaib_2
 *
 */
public class FeesCollectionAction {
        HttpServletRequest request;
        HttpServletResponse response;
        HttpSession httpSession;
        String url;

        public FeesCollectionAction(HttpServletRequest request,
                        HttpServletResponse response) {
                        this.request = request;
                        this.response = response;
                        this.httpSession = request.getSession();}

        public String execute(String action, String page) {
                 if (action.equalsIgnoreCase("feesAdd")) {
                                url = feesAdd();
                        }else if (action.equalsIgnoreCase("printReceipt")) {
                                url = printReceipt();
                        }else if (action.equalsIgnoreCase("ViewDetails")) {
                                url = ViewDetails();
                        }else if (action.equalsIgnoreCase("StampFees")) {
                                url = StampFees();
                        }else if (action.equalsIgnoreCase("CancelFeesReceipt")) {
                            url = cancelFeesReceipt();
                        }else if (action.equalsIgnoreCase("viewCancelledReceipts")) {
                            url = viewCancelledReceipts();
                        }else if (action.equalsIgnoreCase("UndoFeesReceipt")) {
                            url = undoFeesReceipt();
                        }else if (action.equalsIgnoreCase("searchFeesReport")) {
                            url = searchFeesReport();
                    }
                return url;
        }

        private String searchFeesReport() {
            new FeesCollectionService(request, response).getFeesReport();
            return "feesreport.jsp";
    }

		private String undoFeesReceipt() {
        	new FeesCollectionService(request, response).undoFeesReceipt();
			return "Controller?process=FeesCollection&action=viewCancelledReceipts";
		}

		private String viewCancelledReceipts() {
        	new FeesCollectionService(request, response).viewCancelledReceipts();
			return "feescancelledreceipts.jsp";
		}

		private String cancelFeesReceipt() {
			new FeesCollectionService(request, response).cancelFeesReceipt();
			return "Controller?process=UserProcess&action=searchByDate";
		}

		private String StampFees() {
                new FeesCollectionService(request, response).getStampFees();
                new FeesService(request, response).viewAllBranchStudents();
                return "feesCollection.jsp";
        }

        private String ViewDetails() {
                //new FeesCollectionService(request, response).preview();
                new FeesCollectionService(request, response).previewFeesDetails();
                return "previewFeesDetail.jsp";
        }

        private String printReceipt() {
                new FeesCollectionService(request, response).previewDetails();
                
                if(httpSession.getAttribute("branchid")!=null){
                    String branchId = httpSession.getAttribute("branchid").toString();
                    if("1".equalsIgnoreCase(branchId) || "2".equalsIgnoreCase(branchId) || "3".equalsIgnoreCase(branchId)) {
                        return "printFeesDetail.jsp";
                    }else if("4".equalsIgnoreCase(branchId)) {
                        return "printFeesDetail_pu.jsp";
                    }else if("5".equalsIgnoreCase(branchId)) {
                        return "printFeesDetail_dc.jsp";
                    }
                }
                
                return "printFeesDetail.jsp";
        }

        private String feesAdd() {
                Receiptinfo receiptInfo = new FeesCollectionService(request, response).add();
                if(receiptInfo.getReceiptnumber()!=null){
                        //under implementation
                        /*SmsService smsSerivce = new SmsService(request, response);
                        smsSerivce.sendSMS(DataUtil.emptyString(request.getParameter("contactnumber")),"We have received Rs."+DataUtil.emptyString(request.getParameter("grandTotalAmount"))+" towards fees collection.");*/
                        new FeesCollectionService(request, response).preview(receiptInfo);
                        return "previewFeesDetail.jsp";
                }else{
                        return "error.jsp";
                }
                
        }

}
