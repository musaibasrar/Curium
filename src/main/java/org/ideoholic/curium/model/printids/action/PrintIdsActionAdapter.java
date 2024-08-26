package org.ideoholic.curium.model.printids.action;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.documents.dto.SearchStudentResponseDto;
import org.ideoholic.curium.model.printids.dto.PrintIdsDto;
import org.ideoholic.curium.model.printids.service.PrintIdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrintIdsActionAdapter {
	
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	@Autowired
	private HttpSession httpSession;

	public void updateCardValidity() {
		PrintIdsService printIdsService = new PrintIdsService(request, response);
		PrintIdsDto printIdsDto = new PrintIdsDto();
		printIdsDto.setStudentIDs(request.getParameterValues("studentIDs"));
		Map<String, String> allRequestParameters = new HashMap<>();
		Enumeration<String> enumeration = request.getParameterNames();
		while (enumeration.hasMoreElements()) {
			String fieldName = enumeration.nextElement();
			String fieldValue = request.getParameter(fieldName);
			allRequestParameters.put(fieldName, fieldValue);
		}
		printIdsDto.setRequestParams(allRequestParameters);
		ResultResponse result = printIdsService.updateCardValidity(printIdsDto);
		request.setAttribute("updatecard", result.isSuccess());
	}

}
