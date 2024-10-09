package org.ideoholic.curium.model.job.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.job.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;

public class JobActionAdapter {
	
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	@Autowired
	private HttpSession httpSession;

	public boolean download() {
		JobService jobService = new JobService(request, response);
		ResultResponse result = jobService.download();
		return result.isSuccess();
	}

	public boolean exportQueriesReport() {
		JobService jobService = new JobService(request, response);
		ResultResponse result = jobService.exportQueriesReport(httpSession.getAttribute("parentquerylist"));
		return result.isSuccess();
		
	}

}
