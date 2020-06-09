package org.ideoholic.examdetails;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ideoholic.examdetails.dto.ExamDetailsProfileDto;
import org.ideoholic.examdetails.service.ExamDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("ExamDetailsProcess")
@Component
@Scope("singleton")

public class ExamDetailsRestApiResource {
	
	private final static Logger logger = LoggerFactory.getLogger(ExamDetailsRestApiResource.class);
	
	@Inject
	private ExamDetailsService examDetailsService;
	
	@POST
	@Path("addExam")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addExam(final ExamDetailsProfileDto examDto){
		String output = "";
		output =examDetailsService.addExam(examDto.getBranchId(), examDto.getExamName());
		return Response.status(200).entity(output).build();

	}
	
	
	@POST
	@Path("readListOfExams")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response readListOfExams(final ExamDetailsProfileDto examDto){
		String output = "";
		output =examDetailsService.readListOfExams(examDto.getBranchId());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("deleteMultiple")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteMultiple(final ExamDetailsProfileDto examDto){
		String output = "";
		output =examDetailsService.deleteMultiple(examDto.getExamIds());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("addSchedule")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addSchedule(final ExamDetailsProfileDto examDto){
		String output = "";
		output =examDetailsService.addSchedule(examDto.getBranchId(),examDto.getSubject(), examDto.getDate(), examDto.getStartTime(),
				examDto.getEndTime(),examDto.getClassesSelected(),examDto.getAcademicYear(),examDto.getExam());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("deleteExamSchedule")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteExamSchedule(final ExamDetailsProfileDto examDto){
		String output = "";
		output =examDetailsService.deleteExamSchedule(examDto.getExamIds());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("printPreviewHallTicket")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response printPreviewHallTicket(final ExamDetailsProfileDto examDto){
		String output = "";
		output =examDetailsService.printPreviewHallTicket(null);
		return Response.status(200).entity(output).build();

	}

}
