/**
 * 
 */
package com.model.feescollection.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.academicyear.dto.Currentacademicyear;
import com.model.academicyear.service.YearService;
import com.model.feescategory.service.FeesService;
import com.model.feescollection.dto.Feescollection;
import com.model.feescollection.dto.Receiptinfo;
import com.model.feescollection.service.FeesCollectionService;
import com.model.feesdetails.dto.Feesdetails;
import com.model.feesdetails.service.FeesDetailsService;
import com.model.sendsms.service.SmsService;
import com.util.DataUtil;

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
			}
		return url;
	}

	private String StampFees() {
		new FeesCollectionService(request, response).getStampFees();
		new FeesService(request, response).viewFees();
		new FeesService(request, response).viewAllStudentsList();
		return "feesCollection.jsp";
	}

	private String ViewDetails() {
		//new FeesCollectionService(request, response).preview();
		new FeesCollectionService(request, response).previewFeesDetails();
		return "previewFeesDetail.jsp";
	}

	private String printReceipt() {
		new FeesCollectionService(request, response).previewDetails();
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
