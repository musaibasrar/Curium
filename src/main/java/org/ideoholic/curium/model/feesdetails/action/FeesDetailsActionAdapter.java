package org.ideoholic.curium.model.feesdetails.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.feesdetails.service.FeesDetailsService;
import org.springframework.beans.factory.annotation.Autowired;

public class FeesDetailsActionAdapter {
	
	    @Autowired
	    private HttpServletRequest request;

	    @Autowired
	    private HttpServletResponse response;

	    @Autowired
	    private HttpSession httpSession;

		public boolean exportDataForFees() {
			FeesDetailsService feesDetailsService = new FeesDetailsService(request, response);
			ResultResponse resultResponse = feesDetailsService.exportDataForFees();
			return resultResponse.isSuccess();		
			}

}
