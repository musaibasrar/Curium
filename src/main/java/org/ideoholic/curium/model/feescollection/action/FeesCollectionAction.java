/**
 * 
 */
package org.ideoholic.curium.model.feescollection.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.feescategory.action.FeesActionAdapter;
import org.ideoholic.curium.model.feescategory.service.FeesService;
import org.ideoholic.curium.model.feescollection.dto.Otherreceiptinfo;
import org.ideoholic.curium.model.feescollection.dto.Receiptinfo;
import org.ideoholic.curium.model.feescollection.service.FeesCollectionService;
import org.ideoholic.curium.model.std.action.StandardActionAdapter;
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

		@Autowired
		StandardActionAdapter standardActionAdapter;
		
		@Autowired
		private FeesActionAdapter feesActionAdapter;

		@Autowired
		private FeeCollectionActionAdapter feeCollectionActionAdapter;

        @PostMapping("/searchFeesReport")
        public String searchFeesReport() {
            feeCollectionActionAdapter.getFeesReport();
            return "feesreport";
        }

        @GetMapping("/UndoFeesReceipt")
		public String undoFeesReceipt() {
        	feeCollectionActionAdapter.undoFeesReceipt();
			return viewCancelledReceipts();
		}

		@PostMapping("/viewCancelledReceipts")
		public String viewCancelledReceipts() {
        	feeCollectionActionAdapter.viewCancelledReceipts();
			return "feescancelledreceipts";
		}

		@GetMapping("/CancelFeesReceipt")
		public String cancelFeesReceipt() {
			new FeesCollectionService(request, response, standardActionAdapter).cancelFeesReceipt();
			UserAction userAction = new UserAction();
			userAction.setHttpobjects(request, response, httpSession);
			return userAction.searchByDate();
		}

		@PostMapping("/StampFees")
		public String StampFees() {
                new FeesCollectionService(request, response, standardActionAdapter).getStampFees();
                new FeesCollectionService(request, response, standardActionAdapter).getFeesDetails();
                standardActionAdapter.viewClasses();
                feesActionAdapter.viewAllStudentsList();
                return "feesCollection";
        }

		@GetMapping("/ViewDetails")
        public String ViewDetails() {
                //new FeesCollectionService(request, response).preview();
                new FeesCollectionService(request, response, standardActionAdapter).previewFeesDetails();
                //return "previewFeesDetail";
                return "previewFeesReceiptDetail";
        }

        @GetMapping("/printReceipt")
        public String printReceipt() {
                new FeesCollectionService(request, response, standardActionAdapter).previewDetails();
                
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
                Receiptinfo receiptInfo = new FeesCollectionService(request, response, standardActionAdapter).add();
                if(receiptInfo.getReceiptnumber()!=null){
                        //under implementation
                        /*SmsService smsSerivce = new SmsService(request, response);
                        smsSerivce.sendSMS(DataUtil.emptyString(request.getParameter("contactnumber")),"We have received Rs."+DataUtil.emptyString(request.getParameter("grandTotalAmount"))+" towards fees collection.");*/
                        new FeesCollectionService(request, response, standardActionAdapter).preview(receiptInfo);
                        return "previewFeesDetail";
                }else{
                        return "error";
                }
                
        }
        
        @PostMapping("/exportDataForStudentsFeesReport")
        private String exportDataForStudentsFeesReport() {
        	new FeesCollectionService(request, response, standardActionAdapter).exportDataForStudentsFeesReport();
            return "feesreportexportsuccess";
		}
        
        
        @PostMapping("/download")
        private String download() {
        	if (new FeesCollectionService(request, response, standardActionAdapter).downlaod()) {
    			return "feesreportexportsuccess";
    		}
    		return "exportfailure";
		}
        
        @PostMapping("/searchFeesStampDueReport")
        public String searchFeesStampDueReport() {
            new FeesCollectionService(request, response, standardActionAdapter).getFeesStampDueReport();
            return "feesstampdue";
        }
        
        @GetMapping("/printFeesReceipt")
        private String printFeesReceipt() {
            new FeesCollectionService(request, response, standardActionAdapter).previewDetails();
            return "printReceiptFeesDetail";
    }
        
		@PostMapping("/otherStampFees")
		public String otherStampFees() {
                new FeesCollectionService(request, response, standardActionAdapter).getotherStampFees();
                new FeesCollectionService(request, response, standardActionAdapter).getotherFeesDetails();
                standardActionAdapter.viewClasses();
                feesActionAdapter.viewAllStudentsList();
                return "otherfeesCollection";
        }
		
		  @PostMapping("/othersearchFeesReport")
	        public String othersearchFeesReport() {
	            new FeesCollectionService(request, response, standardActionAdapter).getotherFeesReport();
	            return "otherfeesreport";
	        }
		  
	  @GetMapping("/otherprintReceipt")
        public String otherprintReceipt() {
                new FeesCollectionService(request, response, standardActionAdapter).otherpreviewDetails();

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
	  
	  @PostMapping("/feesAddother")	
      public String feesAddother() {
      	Otherreceiptinfo receiptInfo = new FeesCollectionService(request, response, standardActionAdapter).addother();
              if(receiptInfo.getReceiptnumber()!=null){
                      //under implementation
                      /*SmsService smsSerivce = new SmsService(request, response);
                      smsSerivce.sendSMS(DataUtil.emptyString(request.getParameter("contactnumber")),"We have received Rs."+DataUtil.emptyString(request.getParameter("grandTotalAmount"))+" towards fees collection.");*/
                      new FeesCollectionService(request, response, standardActionAdapter).otherpreview(receiptInfo);
                      return "otherpreviewfeesdetail";
              }else{
                      return "error";
              }

      }
	  
		@PostMapping("/searchOtherFeesCollection")
		public String searchOtherFeesCollection() {
			new FeesCollectionService(request, response, standardActionAdapter).searchOtherFeesCollection();
			return "otherfeesCollectionDetails";
		}
		
		@GetMapping("/viewOtherFeesDetails")
        public String viewOtherFeesDetails() {
                //new FeesCollectionService(request, response).preview();
                new FeesCollectionService(request, response, standardActionAdapter).previewOtherFeesDetails();
                //return "previewFeesDetail";
                return "otherpreviewfeesdetail";
        }
		
		@GetMapping("/CancelOtherFeesReceipt")
		public String cancelOtherFeesReceipt() {
			new FeesCollectionService(request, response, standardActionAdapter).cancelOtherFeesReceipt();
			return searchOtherFeesCollection();
		}
		
		 @PostMapping("/exportDataForStudentsOtherFeesReport")
	        private String exportDataForStudentsOtherFeesReport() {
	        	new FeesCollectionService(request, response, standardActionAdapter).exportDataForStudentsOtherFeesReport();
	            return "feesreportexportsuccess";
			}
		 
		 @PostMapping("/feesSummaryReport")
	        private String feesSummaryReport() {
	        	new FeesCollectionService(request, response, standardActionAdapter).getFeesDetailsDashBoard();
	            return "feessummaryreport";
			}	 
		 
		 @PostMapping("/searchByDateFeesCollectionCategory")
	        private String searchByDateFeesCollectionCategory() {
	        	new FeesCollectionService(request, response, standardActionAdapter).getFeesCollectionCategory();
	            return "feescollectiondetailscategory";
			}
		 
		 @PostMapping("/searchByDateFeesCollectionCategoryPrint")
	        private String searchByDateFeesCollectionCategoryPrint() {
	        	new FeesCollectionService(request, response, standardActionAdapter).getFeesCollectionCategory();
	            return "printfeescollectiondetailscategory";
			}
		 
		 @PostMapping("/searchFeesDueHeadWiseReport")
	        public String searchFeesDueHeadWiseReport() {
	            feeCollectionActionAdapter.getFeesReport();
	            return "feesdueheadwisereport";
	        }
		 
		 @PostMapping("/printFeesDueHeadWiseReport")
	        private String printFeesDueHeadWiseReport() {
	        	new FeesCollectionService(request, response, standardActionAdapter).printFeesDueHeadWiseReport();
	            return "printfeesdueheadwisereport";
			}
		 
		 @PostMapping("/printOtherDataForFees")
			public String printOtherFeesData() {
				
				if(new FeesCollectionService(request, response, standardActionAdapter).printOtherDataForFees()){
					return "printotherfeescollectiondetails";
				}else{
					return "error";
				}
				
			}
		 
		 @PostMapping("/searchDefaultersReport")
	        public String searchDefaultersReport() {
	            new FeesCollectionService(request, response, standardActionAdapter).getDefaultersReport();
	            return "defaultersreport";
	        }
		 
		 @PostMapping("/viewCancelledOtherFeesReceipts")
			public String viewCancelledOtherFeesReceipts() {
	        	new FeesCollectionService(request, response, standardActionAdapter).viewCancelledOtherFeesReceipts();
				return "otherfeescancelledreceipts";
			}
		 
		 @PostMapping("/searchFeesReportDue")
	        public String searchFeesReportDue() {
	            new FeesCollectionService(request, response,standardActionAdapter).getFeesReportDue();
	            return "feesreportdue";
	        }
}
