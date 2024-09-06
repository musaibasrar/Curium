package org.ideoholic.curium.model.student.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.attendance.dto.StudentAttendanceDetailsResponseDto;
import org.ideoholic.curium.model.documents.dto.StudentNameSearchDto;
import org.ideoholic.curium.model.feescollection.dto.FeesDetailsResponseDto;
import org.ideoholic.curium.model.parents.dto.ParentListResponseDto;
import org.ideoholic.curium.model.std.action.StandardActionAdapter;
import org.ideoholic.curium.model.student.dto.*;
import org.ideoholic.curium.model.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Service
public class StudentActionAdapter {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private StandardActionAdapter standardActionAdapter;

    private String CURRENTACADEMICYEAR = "currentAcademicYear";
    private String BRANCHID = "branchid";
    private String USERID = "userloginid";


    public void viewAllStudentsSuperAdmin() {
        StudentService studentService = new StudentService(request, response, standardActionAdapter);

        StudentsSuperAdminResponseDto responseDto = studentService.viewAllStudentsSuperAdmin();
        request.setAttribute("studentList", responseDto.getStudentList());
        request.setAttribute("noOfPages", responseDto.getNoOfPages());
        request.setAttribute("currentPage", responseDto.getPage());
    }

    public ResultResponse addNew() {
        StudentService studentService = new StudentService(request, response, standardActionAdapter);

        ResultResponse resultResponse = studentService.addNew(httpSession.getAttribute(BRANCHID).toString());

        return resultResponse;
    }

    public boolean downlaodFile() {
        StudentService studentService = new StudentService(request, response, standardActionAdapter);

        ResultResponse resultResponse = studentService.downlaodFile();

        return resultResponse.isSuccess();
    }

    public BonafideGenerationResponseDto generateBonafide() {
        StudentService studentService = new StudentService(request, response, standardActionAdapter);

        StudentIdsDto dto = new StudentIdsDto();
        dto.setStudentIds(request.getParameterValues("studentIDs"));

        BonafideGenerationResponseDto responseDto = studentService.generateBonafide(dto);
        httpSession.setAttribute("studentdetailsbonafide", responseDto.getParents());

        return responseDto;
    }

    public void viewfeesStructurePerYear() {
        StudentService studentService = new StudentService(request, response, standardActionAdapter);

        StudentIdDto dto = new StudentIdDto();
        dto.setStudentId(request.getParameter("id"));
        dto.setAcademicYear(request.getParameter("academicyear"));

        FeesDetailsResponseDto responseDto = studentService.viewfeesStructurePerYear(dto);
        request.setAttribute("receiptinfo", responseDto.getReceiptInfo());
        httpSession.setAttribute("feesstructure", responseDto.getFeesStructure());
        httpSession.setAttribute("sumoffees", responseDto.getTotalSum());
        httpSession.setAttribute("dueamount", responseDto.getDueAmount());
        httpSession.setAttribute("totalfees", responseDto.getTotalFeesAmount());
        httpSession.setAttribute("academicPerYear", responseDto.getAcademicPerYear());
        httpSession.setAttribute("totalfeesconcession", responseDto.getTotalFeesConcession());
    }

    public void viewAllStudentsParents() {
        StudentService studentService = new StudentService(request, response, standardActionAdapter);

        String page = request.getParameter("page");

        ParentListResponseDto responseDto = studentService.viewAllStudentsParents(page, httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("studentList", responseDto.getList());
        request.setAttribute("noOfPages", responseDto.getNoOfPages());
        request.setAttribute("currentPage", responseDto.getPage());
    }

    public boolean promoteMultiple() {
        StudentService studentService = new StudentService(request, response, standardActionAdapter);

        PromoteMultipleDto dto = new PromoteMultipleDto();
        dto.setStudentIds(request.getParameterValues("studentIDs"));
        dto.setClassStudying(request.getParameter("classstudying"));

        Map<String, String> allRequestParameters = new HashMap<>();
        Enumeration<String> enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String fieldName = enumeration.nextElement();
            String fieldValue = request.getParameter(fieldName);
            allRequestParameters.put(fieldName, fieldValue);
        }
        dto.setRequestParams(allRequestParameters);

        ResultResponse resultResponse = studentService.promoteMultiple(dto, httpSession.getAttribute(CURRENTACADEMICYEAR).toString(), httpSession.getAttribute(BRANCHID).toString());

        return resultResponse.isSuccess();
    }

    public void restoreMultiple() {
        StudentService studentService = new StudentService(request, response, standardActionAdapter);

        StudentIdsDto dto = new StudentIdsDto();
        dto.setStudentIds(request.getParameterValues("studentIDs"));

        studentService.restoreMultiple(dto);
    }

    public void deleteMultiple() {

        StudentService studentService = new StudentService(request, response, standardActionAdapter);

        StudentIdsDto dto = new StudentIdsDto();
        dto.setStudentIds(request.getParameterValues("studentIDs"));

        studentService.deleteMultiple(dto);
    }

    public void viewAllStudentsArchive() {
        StudentService studentService = new StudentService(request, response, standardActionAdapter);

        StudentAttendanceDetailsResponseDto responseDto = studentService.viewAllStudentsArchive();
        request.setAttribute("studentListArchive", responseDto.getStudentList());
    }

    public void archiveMultiple() {
        StudentService studentService = new StudentService(request, response, standardActionAdapter);

        StudentIdsDto dto = new StudentIdsDto();
        dto.setStudentIds(request.getParameterValues("studentIDs"));

        studentService.archiveMultiple(dto);
    }

    public boolean addStudent(StudentDto student, MultipartFile[] uploadedFiles) {
        StudentService studentService = new StudentService(request, response, standardActionAdapter);

        student.setYearofadmission(request.getParameter("yearofadmission"));

        ResultResponse resultResponse = studentService.addStudent(student, uploadedFiles, httpSession.getAttribute("branchcode").toString(), httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(USERID).toString(), httpSession.getAttribute(CURRENTACADEMICYEAR).toString());

        return resultResponse.isSuccess();
    }

    public boolean exportDataForStudents() {
        StudentService studentService = new StudentService(request, response, standardActionAdapter);

        StudentIdsDto dto = new StudentIdsDto();
        dto.setStudentIds(request.getParameterValues("studentIDs"));

        ResultResponse resultResponse = studentService.exportDataForStudents(dto, httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("searchStudentList", resultResponse.getResultList());

        return resultResponse.isSuccess();
    }
}
