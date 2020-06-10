package org.ideoholic.subjectdetails;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ideoholic.subjectdetails.dto.SubjectDetailsProfileDto;
import org.ideoholic.subjectdetails.service.SubjectDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("SubjectDetailsProcess")
@Component
@Scope("singleton")

public class SubjectDetailsRestApiResource {
	
	private final static Logger logger = LoggerFactory.getLogger(SubjectDetailsRestApiResource.class);
	
	@Inject
	private SubjectDetailsService subjectDetailsService;
	
	@POST
	@Path("readListOfSubjects")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response readListOfSubjects(final SubjectDetailsProfileDto subjectDto){
		String output = "";
		output =subjectDetailsService.readListOfSubjects(subjectDto.getBranchId());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("addSubject")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addSubject(final SubjectDetailsProfileDto subjectDto){
		String output = "";
		output =subjectDetailsService.addSubject(subjectDto.getBranchId(),subjectDto.getSubjectName(),subjectDto.getMinMarks(),
				subjectDto.getMaxMarks(),subjectDto.getExamName(),subjectDto.getExamclass());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("deleteMultiple")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteMultiple(final SubjectDetailsProfileDto subjectDto){
		String output = "";
		output =subjectDetailsService.deleteMultiple(subjectDto.getExamIds());
		return Response.status(200).entity(output).build();

	}

	
	@POST
	@Path("readListOfSubjectNames")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response readListOfSubjectNames(final SubjectDetailsProfileDto subjectDto){
		String output = "";
		output =subjectDetailsService.readListOfSubjectNames(subjectDto.getBranchId());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("deleteMultipleSubjects")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteMultipleSubjects(final SubjectDetailsProfileDto subjectDto){
		String output = "";
		output =subjectDetailsService.deleteMultipleSubjects(subjectDto.getExamIds());
		return Response.status(200).entity(output).build();

	}
}
