package org.ideoholic.student;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ideoholic.student.dto.StudentProfileDto;
import org.ideoholic.student.service.StudentService;
import org.ideoholic.user.dto.UserProfileDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;



@Path("StudentProcess")
@Component
@Scope("singleton")

public class StudentRestApiResource {
	private final static Logger logger = LoggerFactory.getLogger(StudentRestApiResource.class);

	@Inject
	private StudentService studentService;
	
	@POST
	@Path("viewAllStudents")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response viewAllStudents(final StudentProfileDto studentDto) {
		String output = "";
		output = studentService.viewAllStudents(studentDto.getBranchId());
		return Response.status(200).entity(output).build();
	}
	
	
	@POST
	@Path("viewAllStudentsList")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response viewAllStudentsList(final StudentProfileDto studentDto) {
		String output = "";
		output = studentService.viewAllStudentsList(studentDto.getBranchId());
		return Response.status(200).entity(output).build();
	}

	
	@POST
	@Path("viewAllStudentsParents")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response viewAllStudentsParents(final StudentProfileDto studentDto) {
		String output = "";
		output = studentService.viewAllStudentsParents(studentDto.getBranchId(), studentDto.getPageN());
		return Response.status(200).entity(output).build();
	}
	
	@POST
	@Path("addStudent")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addStudent(final StudentProfileDto studentDto) {
		String output = "";
		output = studentService.addStudent(studentDto.getBranchId());
		return Response.status(200).entity(output).build();
	}
	
	@POST
	@Path("viewDetailsOfStudent")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response viewDetailsOfStudent(final StudentProfileDto studentDto) {
		String output = "";
		output = studentService.viewDetailsOfStudent(studentDto.getId());
		return Response.status(200).entity(output).build();
	}
	
	@POST
	@Path("updateStudent")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateStudent(final StudentProfileDto studentDto) {
		String output = "";
		output = studentService.updateStudent(studentDto.getBranchId());
		return Response.status(200).entity(output).build();
	}
	
	@POST
	@Path("archiveMultiple")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response archiveMultiple(final StudentProfileDto studentDto) {
		String output = "";
		output = studentService.archiveMultiple(studentDto.getStudentIds());
		return Response.status(200).entity(output).build();
	}
	
	@GET
	@Path("viewAllStudentsArchive")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response viewAllStudentsArchive(final StudentProfileDto studentDto) {
		String output = "";
		output = studentService.viewAllStudentsArchive();
		return Response.status(200).entity(output).build();
	}
	
	@DELETE
	@Path("deleteMultiple")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteMultiple(final StudentProfileDto studentDto) {
		String output = "";
		output = studentService.deleteMultiple(studentDto.getStudentIds());
		return Response.status(200).entity(output).build();
	}
	
	@POST
	@Path("restoreMultiple")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response restoreMultiple(final StudentProfileDto studentDto) {
		String output = "";
		output = studentService.restoreMultiple(studentDto.getStudentIds());
		return Response.status(200).entity(output).build();
	}
	
	@POST
	@Path("promoteMultiple")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response promoteMultiple(final StudentProfileDto studentDto) {
		String output = "";
		output = studentService.promoteMultiple(studentDto.getStudentIds(), studentDto.getClassStudying());
		return Response.status(200).entity(output).build();
	}
	
	@POST
	@Path("viewfeesStructurePerYear")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response viewfeesStructurePerYear(final StudentProfileDto studentDto) {
		String output = "";
		output = studentService.viewfeesStructurePerYear(studentDto.getId(), studentDto.getAcademicYear());
		return Response.status(200).entity(output).build();
	}
	
	@POST
	@Path("exportDataForStudents")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportDataForStudents(final StudentProfileDto studentDto) {
		String output = "";
		output = studentService.exportDataForStudents(studentDto.getStudentIds(), studentDto.getBranchId());
		return Response.status(200).entity(output).build();
	}
	
	@POST
	@Path("GenerateBonafide")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response generateBonafide(final StudentProfileDto studentDto) {
		String output = "";
		output = studentService.generateBonafide(studentDto.getStudentIds());
		return Response.status(200).entity(output).build();
	}
	
	@POST
	@Path("downlaodFile")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response downlaodFile(final StudentProfileDto studentDto) {
		String output = "";
		output = studentService.downlaodFile();
		return Response.status(200).entity(output).build();
	}
	
	@POST
	@Path("addNew")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addNew(final StudentProfileDto studentDto) {
		String output = "";
		output = studentService.addNew(studentDto.getBranchId());
		return Response.status(200).entity(output).build();
	}
	
	@POST
	@Path("viewAllStudentsSuperAdmin")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response viewAllStudentsSuperAdmin(final StudentProfileDto studentDto) {
		String output = "";
		output = studentService.viewAllStudentsSuperAdmin();
		return Response.status(200).entity(output).build();
	}
}
