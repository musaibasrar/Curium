/**
 * 
 */
package org.ideoholic.curium.model.feescollection.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.feescategory.service.FeesService;
import org.ideoholic.curium.model.feescollection.dto.Receiptinfo;
import org.ideoholic.curium.model.feescollection.service.FeesCollectionService;
import org.ideoholic.curium.model.std.service.StandardService;
import org.ideoholic.curium.model.user.action.UserAction;
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
@RequestMapping("/FeesCollection")
public class FeesCollectionAction {
	
		@Autowired
        HttpServletRequest request;
        
		@Autowired
		HttpServletResponse response;
        
		@Autowired
		HttpSession httpSession;

        @PostMapping("/searchFeesReport")
        public String searchFeesReport() {
            new FeesCollectionService(request, response).getFeesReport();
            return "feesreport";
        }

        @GetMapping("/UndoFeesReceipt")
		public String undoFeesReceipt() {
        	new FeesCollectionService(request, response).undoFeesReceipt();
			return viewCancelledReceipts();
		}

		@PostMapping("/viewCancelledReceipts")
		public String viewCancelledReceipts() {
        	new FeesCollectionService(request, response).viewCancelledReceipts();
			return "feescancelledreceipts";
		}

		@GetMapping("/CancelFeesReceipt")
		public String cancelFeesReceipt() {
			new FeesCollectionService(request, response).cancelFeesReceipt();
			UserAction userAction = new UserAction();
			userAction.setHttpobjects(request, response);
			return userAction.searchByDate();
		}

		@PostMapping("/StampFees")
		public String StampFees() {
                new FeesCollectionService(request, response).getStampFees();
                new FeesCollectionService(request, response).getFeesDetails();
                new StandardService(request, response).viewClasses();
                new FeesService(request, response).viewAllStudentsList();
                return "feesCollection";
        }

		@GetMapping("/ViewDetails")
        public String ViewDetails() {
                //new FeesCollectionService(request, response).preview();
                new FeesCollectionService(request, response).previewFeesDetails();
                //return "previewFeesDetail";
                return "previewFeesReceiptDetail";
        }

        @GetMapping("/printReceipt")
        public String printReceipt() {
                new FeesCollectionService(request, response).previewDetails();
                
				/*
				 * if(httpSession.getAttribute("branchid")!=null){ String branchId =
				 * httpSession.getAttribute("branchid").toString();
				 * if("1".equalsIgnoreCase(branchId) || "2".equalsIgnoreCase(branchId) ||
				 * "3".equalsIgnoreCase(branchId)) { return "printFeesDetail"; }else
				 * if("4".equalsIgnoreCase(branchId)) { return "printFeesDetail"; }else
				 * if("5".equalsIgnoreCase(branchId)) { return "printFeesDetail"; } }
				 */
                
                return "printFeesDetail";
        }

        @PostMapping("/feesAdd")	
        public String feesAdd() {
                Receiptinfo receiptInfo = new FeesCollectionService(request, response).add();
                if(receiptInfo.getReceiptnumber()!=null){
                        //under implementation
                        /*SmsService smsSerivce = new SmsService(request, response);
                        smsSerivce.sendSMS(DataUtil.emptyString(request.getParameter("contactnumber")),"We have received Rs."+DataUtil.emptyString(request.getParameter("grandTotalAmount"))+" towards fees collection.");*/
                        new FeesCollectionService(request, response).preview(receiptInfo);
                        return "previewFeesDetail";
                }else{
                        return "error";
                }
                
        }
        
        @PostMapping("/exportDataForStudentsFeesReport")
        private String exportDataForStudentsFeesReport() {
        	new FeesCollectionService(request, response).exportDataForStudentsFeesReport();
            return "feesreportexportsuccess";
		}
        
        
        @PostMapping("/download")
        private String download() {
        	if (new FeesCollectionService(request, response).downlaod()) {
    			return "feesreportexportsuccess";
    		}
    		return "exportfailure";
		}
        
        @PostMapping("/searchFeesStampDueReport")
        public String searchFeesStampDueReport() {
            new FeesCollectionService(request, response).getFeesStampDueReport();
            return "feesstampdue";
        }
        
        @GetMapping("/printFeesReceipt")
        private String printFeesReceipt() {
            new FeesCollectionService(request, response).previewDetails();
            return "printReceiptFeesDetail";
    }
		
}
