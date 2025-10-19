package org.ideoholic.curium.model.feesdetails.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.feesdetails.dto.DataForFeesResponseDto;
import org.ideoholic.curium.model.feesdetails.dto.FeesIdDetailsDto;
import org.ideoholic.curium.model.feesdetails.service.FeesDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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

		public boolean printDataForFees() {
			FeesDetailsService feesDetailsService = new FeesDetailsService(request, response);
			FeesIdDetailsDto feesIdDetailsDto = new FeesIdDetailsDto();
			feesIdDetailsDto.setFeesIds(request.getParameterValues("feesIDs"));
			feesIdDetailsDto.setFromDate(request.getParameter("fromdate"));
			feesIdDetailsDto.setOneDay(request.getParameter("oneday"));
			feesIdDetailsDto.setToDate(request.getParameter("todate"));
			DataForFeesResponseDto dataForFeesResponseDto = feesDetailsService.printDataForFees(feesIdDetailsDto);
			httpSession.setAttribute("sumofdetailsfees", dataForFeesResponseDto.getSumOfDetailsFees());
			httpSession.setAttribute("sumofonlyfee", dataForFeesResponseDto.getSumOfOnlyFee());
			httpSession.setAttribute("sumoffine", dataForFeesResponseDto.getSumOfFine());
			httpSession.setAttribute("sumofmisc", dataForFeesResponseDto.getSumOfMisc());
			httpSession.setAttribute("daterangefeescollection", dataForFeesResponseDto.getDateRangeFeesCollection());
			request.setAttribute("feesmap", dataForFeesResponseDto.getFeesMap());
			return dataForFeesResponseDto.isSuccess();
		}

}
