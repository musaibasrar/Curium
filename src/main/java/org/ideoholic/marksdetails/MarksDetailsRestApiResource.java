package org.ideoholic.marksdetails;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ideoholic.marksdetails.dto.MarksDetailsProfileDto;
import org.ideoholic.marksdetails.service.MarksDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("MarksDetailsProcess")
@Component
@Scope("singleton")

public class MarksDetailsRestApiResource {
	
	private final static Logger logger = LoggerFactory.getLogger(MarksDetailsRestApiResource.class);
	
	@Inject
	private MarksDetailsService marksDeailsService;
	
	@POST
	@Path("addMarks")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addMarks(final MarksDetailsProfileDto marksDto){
		String output = "";
		output =marksDeailsService.addMarks(marksDto.getBranchId(),marksDto.getCurrentAcademicYear(),marksDto.getStudentIds(),
				marksDto.getStudentsMarks(),marksDto.getExam(),marksDto.getSubject());
		return Response.status(200).entity(output).build();

	}

	@POST
	@Path("Search")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response Search(final MarksDetailsProfileDto marksDto){
		String output = "";
		output =marksDeailsService.Search(marksDto.getBranchId(),marksDto.getStudentname(),marksDto.getAddClass(),marksDto.getAddSec());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("getSubjectExams")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getSubjectExams(final MarksDetailsProfileDto marksDto){
		String output = "";
		output =marksDeailsService.getSubjectExams(marksDto.getBranchId());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("updateMarks")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateMarks(final MarksDetailsProfileDto marksDto){
		String output = "";
		output =marksDeailsService.updateMarks(marksDto.getBranchId(),marksDto.getCurrentAcademicYear(),marksDto.getStudentIds(),
				marksDto.getStudentsMarks(), marksDto.getMarksid(), marksDto.getExam(),marksDto.getSubject());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("deleteMultiple")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteMultiple(final MarksDetailsProfileDto marksDto){
		String output = "";
		output =marksDeailsService.deleteMultiple(marksDto.getStudentIds(), marksDto.getMarksid());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("generateReport")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response generateReport(final MarksDetailsProfileDto marksDto){
		String output = "";
		output =marksDeailsService.generateReport(marksDto.getCurrentAcademicYear(), marksDto.getStudentIds());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("getStudentGraph")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getStudentGraph(final MarksDetailsProfileDto marksDto){
		String output = "";
		output =marksDeailsService.getStudentGraph(marksDto.getBranchId(), marksDto.getCurrentAcademicYear(), 
				marksDto.getStudentIds(), marksDto.getExamClass());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("downloadReportCard")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response downloadReportCard(final MarksDetailsProfileDto marksDto){
		String output = "";
		output =marksDeailsService.downloadReportCard(marksDto.getBuffer());
		return Response.status(200).entity(output).build();

	}
}
