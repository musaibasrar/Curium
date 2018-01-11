/**
 * 
 */
package com.model.feescollection.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.academicyear.dto.Currentacademicyear;
import com.model.academicyear.service.YearService;
import com.model.feescollection.dto.Feescollection;
import com.model.feescollection.service.FeesCollectionService;
import com.model.feesdetails.dto.Feesdetails;
import com.model.feesdetails.service.FeesDetailsService;

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
				System.out.println("Action is feesAdd");
				url = feesAdd();
			}else if (action.equalsIgnoreCase("printReceipt")) {
				System.out.println("Action is printReceipt");
				url = printReceipt();
			}else if (action.equalsIgnoreCase("ViewDetails")) {
				System.out.println("Action is ViewDetails");
				url = ViewDetails();
			}
		return url;
	}

	private String ViewDetails() {
		String ssid = request.getParameter("sid");
		String id = request.getParameter("id");
		System.out.println("SID AND ID "+ssid+"ID "+id);
		new FeesCollectionService(request, response).preview();
		return "previewFeesDetail.jsp";
	}

	private String printReceipt() {
		new FeesCollectionService(request, response).preview();
		return "printFeesDetail.jsp";
	}

	private String feesAdd() {
		new YearService(request, response).getYear();
		Feesdetails feesdetails = new FeesDetailsService(request, response).addFeesDetails();
		Feescollection feescollection = new FeesCollectionService(request, response).add(feesdetails);
		new FeesCollectionService(request, response).preview(feescollection);
		return "previewFeesDetail.jsp";
	}

}
