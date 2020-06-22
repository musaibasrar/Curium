package org.ideoholic.document;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ideoholic.document.dto.DocumentProfileDto;
import org.ideoholic.document.service.DocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("DocumentProcess")
@Component
@Scope("singleton")

public class DocumentRestApiResource {

	private final static Logger logger = LoggerFactory.getLogger(DocumentRestApiResource.class);
	
	@Inject
	private DocumentService documentService;
	
	@POST
	@Path("transferCertificate")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response transferCertificate(final DocumentProfileDto documentDto){
		String output = "";
		output =documentService.transferCertificate(documentDto.getBranchId());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("generateTransferCertificate")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response generateTransferCertificate(final DocumentProfileDto documentDto){
		String output = "";
		output =documentService.generateTransferCertificate(documentDto.getStudentId(),documentDto.getLeavingReason(),
				documentDto.getDateOfTc());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("printTransferCertificate")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response printTransferCertificate(final DocumentProfileDto documentDto){
		String output = "";
		output =documentService.printTransferCertificate(documentDto.getStudentId());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("admissionAbstract")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response admissionAbstract(final DocumentProfileDto documentDto){
		String output = "";
		output =documentService.admissionAbstract(documentDto.getBranchId());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("searchForStudents")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response searchForStudents(final DocumentProfileDto documentDto){
		String output = "";
		output =documentService.searchForStudents(documentDto.getBranchId(),documentDto.getStudentname(),documentDto.getAdmissionNumber(),
				documentDto.getAddClass(),documentDto.getAddSec());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("downlaodFile")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response downlaodFile(final DocumentProfileDto documentDto){
		String output = "";
		output =documentService.downlaodFile(documentDto.getBuffer());
		return Response.status(200).entity(output).build();

	}
}
