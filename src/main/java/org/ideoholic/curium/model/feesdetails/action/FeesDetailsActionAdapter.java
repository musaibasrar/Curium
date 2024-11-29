package org.ideoholic.curium.model.feesdetails.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.feesdetails.dto.FeesIdDetailsDto;
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
			FeesIdDetailsDto feesIdDetailsDto = new FeesIdDetailsDto();
			feesIdDetailsDto.setFeesIds(request.getParameterValues("feesIDs"));
			ResultResponse resultResponse = feesDetailsService.exportDataForFees(feesIdDetailsDto);
			return resultResponse.isSuccess();		
			}

		public boolean exportDataForOtherFees() {
			FeesDetailsService feesDetailsService = new FeesDetailsService(request, response);
			FeesIdDetailsDto feesIdDetailsDto = new FeesIdDetailsDto();
			feesIdDetailsDto.setFeesIds(request.getParameterValues("feesIDs"));
			ResultResponse resultResponse = feesDetailsService.exportDataForOtherFees(feesIdDetailsDto);
			return resultResponse.isSuccess();
		}

}
