package org.ideoholic.curium.model.feescategory.action;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.feescategory.dto.ConcessionDto;
import org.ideoholic.curium.model.student.dto.StudentIdDto;
import org.ideoholic.curium.model.feescategory.service.FeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeesActionAdapter {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	@Autowired
	private HttpSession httpSession;

	public String applyConcession() {
		FeesService feesService = new FeesService(request, response);
		ConcessionDto concessionDto = new ConcessionDto();
		concessionDto.setSfsid(request.getParameterValues("sfsid"));
		concessionDto.setId(request.getParameter("id"));
		Map<String, String> allRequestParameters = new HashMap<>();
			Enumeration<String> enumeration = request.getParameterNames();
			while (enumeration.hasMoreElements()) {
				String fieldName = enumeration.nextElement();
				String fieldValue = request.getParameter(fieldName);
				allRequestParameters.put(fieldName, fieldValue);
			}
			concessionDto.setRequestParams(allRequestParameters);

		
			StudentIdDto studentIdDto = feesService.applyConcession(concessionDto);
			 String studentId = studentIdDto.getStudentId();
			 return studentId;
			 
	}
	
}
