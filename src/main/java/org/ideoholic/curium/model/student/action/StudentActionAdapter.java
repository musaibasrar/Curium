package org.ideoholic.curium.model.student.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.attendance.dto.StudentAttendanceDetailsResponseDto;
import org.ideoholic.curium.model.feescollection.dto.FeesDetailsResponseDto;
import org.ideoholic.curium.model.parents.dto.ParentListResponseDto;
import org.ideoholic.curium.model.std.action.StandardActionAdapter;
import org.ideoholic.curium.model.student.dto.*;
import org.ideoholic.curium.model.student.service.StudentService;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.DateUtil;
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
        request.setAttribute("studentList", responseDto.getParentsList());
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

    public boolean addStudent(CreateStudentDto student, MultipartFile[] uploadedFiles) {
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


    public String updateStudent(StudentDto studentDto, MultipartFile[] uploadedFiles) {
        StudentService studentService = new StudentService(request, response, standardActionAdapter);

        studentDto.setAdmissionnumber(request.getParameter("admnno"));
        studentDto.setPlaceofbirth(request.getParameter("place"));
        studentDto.setClassSec(request.getParameter("classsec"));
        studentDto.setSecstudying(request.getParameter("secstudying"));
        studentDto.setAdmclassE(request.getParameter("classadm"));
        studentDto.setAdmsecE(request.getParameter("secadm"));
        studentDto.setDateleaving(request.getParameter("dateofleaving"));
        studentDto.setAddresspermanent(request.getParameter("permanentaddress"));
        studentDto.setAddresstemporary(request.getParameter("temporaryaddress"));
        studentDto.setSid(DataUtil.parseInt(request.getParameter("id")));
        studentDto.setPid(DataUtil.parseInt(request.getParameter("idparents")));
        studentDto.setAge(DataUtil.parseInt(request.getParameter("age")));
        studentDto.setAdmissiondate(DateUtil.indiandateParser(request.getParameter("dateofadmission")));
        studentDto.setTcno(DataUtil.parseInt(request.getParameter("tcno")));
        studentDto.setDateoftc(DataUtil.emptyString(request.getParameter("dateoftc")));
        studentDto.setNotcissued(DataUtil.parseInt(request.getParameter("notcissued")));
        studentDto.setSemester(DataUtil.parseInt(request.getParameter("semester")));
        studentDto.setBelongtobpl(DataUtil.parseInt(request.getParameter("belongtobpl")));
        studentDto.setRte(DataUtil.parseInt(request.getParameter("rte")));
        studentDto.setPassedout(DataUtil.parseInt(request.getParameter("passedout")));
        studentDto.setLeftout(DataUtil.parseInt(request.getParameter("leftout")));
        studentDto.setDroppedout(DataUtil.parseInt(request.getParameter("droppedout")));
        studentDto.setNoofdependents(DataUtil.parseInt(request.getParameter("noofdependents")));
        studentDto.setPep(DataUtil.parseInt(request.getParameter("pep")));
        studentDto.setPudetailsid(DataUtil.parseInt(request.getParameter("pudetailsid")));
        studentDto.setIddegreedetails(DataUtil.parseInt(request.getParameter("degreedetailsid")));
        studentDto.setPepdc(DataUtil.parseInt(request.getParameter("pepdc")));
        studentDto.setPumarkscard(DataUtil.parseInt(request.getParameter("pumarkscard")));
        studentDto.setMedicalreport(DataUtil.parseInt(request.getParameter("medicalreport")));
        studentDto.setIncomecertificate(DataUtil.parseInt(request.getParameter("incomecertificate")));
        studentDto.setMigrationcertificate(DataUtil.parseInt(request.getParameter("migrationcertificate")));
        studentDto.setOriginaltc(DataUtil.parseInt(request.getParameter("originaltc")));
        studentDto.setCastecertificate(DataUtil.parseInt(request.getParameter("castecertificate")));
        studentDto.setKarnataka(DataUtil.parseInt(request.getParameter("karnataka")));
        studentDto.setStudentPicDelete(request.getParameter("studentpicdelete"));
        studentDto.setStudentDoc1Delete(request.getParameter("studentdoc1delete"));
        studentDto.setStudentDoc2Delete(request.getParameter("studentdoc2delete"));
        studentDto.setStudentDoc3Delete(request.getParameter("studentdoc3delete"));
        studentDto.setStudentDoc4Delete(request.getParameter("studentdoc4delete"));
        studentDto.setStudentDoc5Delete(request.getParameter("studentdoc5delete"));
        studentDto.setStudentPicUpdate(request.getParameter("studentpicupdate"));
        studentDto.setStudentDoc1Update(request.getParameter("studentdoc1update"));
        studentDto.setStudentDoc2Update(request.getParameter("studentdoc2update"));
        studentDto.setStudentDoc3Update(request.getParameter("studentdoc3update"));
        studentDto.setStudentDoc4Update(request.getParameter("studentdoc4update"));
        studentDto.setStudentDoc5Update(request.getParameter("studentdoc5update"));
        studentDto.setStudentexternalid(request.getParameter("studentexternalid"));

        Student student = studentService.updateStudent(uploadedFiles, studentDto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(USERID).toString());

        String stId = student.getSid().toString();
		int branchId = student.getBranchid();
		return stId+"_"+branchId;
    }

    public boolean viewDetailsOfStudent() {
        StudentService studentService = new StudentService(request, response, standardActionAdapter);

        String studentId = request.getParameter("id");

        StudentDetailsResponseDto responseDto = studentService.viewDetailsOfStudent(studentId);
        httpSession.setAttribute("currentyearfromservice", responseDto.getCurrentYearFromService());
        request.setAttribute("receiptinfo", responseDto.getReceiptInfo());
        httpSession.setAttribute("student", responseDto.getStudent());
        httpSession.setAttribute("classstudying", responseDto.getClassStudying());
        httpSession.setAttribute("classstudy", responseDto.getClassParts());
        httpSession.setAttribute("secstudying", responseDto.getSecStudying());
        request.setAttribute("secadm", responseDto.getSecAdm());
        request.setAttribute("classadm", responseDto.getClassAdm());
        httpSession.setAttribute("parents", responseDto.getParents());
        httpSession.setAttribute("feesstructure", responseDto.getFeesStructure());
        httpSession.setAttribute("sumoffees", responseDto.getTotalSum());
        httpSession.setAttribute("dueamount", responseDto.getDueAmount());
        httpSession.setAttribute("totalfees", responseDto.getTotalFeesAmount());
        httpSession.setAttribute("academicPerYear", responseDto.getAcademicPerYear());
        httpSession.setAttribute("currentAcademicYear", responseDto.getCurrentAcademicYear());
        httpSession.setAttribute("totalfeesconcession", responseDto.getTotalFeesConcession());
        httpSession.setAttribute("totalfineamount", responseDto.getTotalFineAmount());
        httpSession.setAttribute("totalmiscamount", responseDto.getTotalMiscAmount());
        httpSession.setAttribute("resultfromservice", responseDto.isSuccess());

        return responseDto.isSuccess();
    }

    public boolean viewDetailsbySidStudent() {
        StudentService studentService = new StudentService(request, response, standardActionAdapter);

        String studentId = request.getParameter("id");

        StudentDetailsResponseDto responseDto = studentService.viewDetailsbySidStudent(studentId);
        httpSession.setAttribute("currentyearfromservice", responseDto.getCurrentYearFromService());
        request.setAttribute("receiptinfo", responseDto.getReceiptInfo());
        httpSession.setAttribute("student", responseDto.getStudent());
        httpSession.setAttribute("secstudying", responseDto.getSecStudying());
        httpSession.setAttribute("classstudying", responseDto.getClassStudying());
        request.setAttribute("classadm", responseDto.getClassAdm());
        request.setAttribute("secadm", responseDto.getSecAdm());
        httpSession.setAttribute("parents", responseDto.getParents());
        httpSession.setAttribute("feesstructure", responseDto.getFeesStructure());
        httpSession.setAttribute("sumoffees", responseDto.getTotalSum());
        httpSession.setAttribute("dueamount", responseDto.getDueAmount());
        httpSession.setAttribute("totalfees", responseDto.getTotalFeesAmount());
        httpSession.setAttribute("academicPerYear", responseDto.getAcademicPerYear());
        httpSession.setAttribute("currentAcademicYear", responseDto.getCurrentAcademicYear());
        httpSession.setAttribute("totalfeesconcession", responseDto.getTotalFeesConcession());
        httpSession.setAttribute("resultfromservice", responseDto.isSuccess());

        return responseDto.isSuccess();
    }

    public boolean viewOtherFeesDetailsOfStudent() {
        StudentService studentService = new StudentService(request, response, standardActionAdapter);

        String studentId = request.getParameter("id");

        StudentDetailsResponseDto responseDto = studentService.viewOtherFeesDetailsOfStudent(studentId);
        httpSession.setAttribute("currentyearfromservice", responseDto.getCurrentYearFromService());
        request.setAttribute("receiptinfo", responseDto.getReceiptInfo());
        httpSession.setAttribute("student", responseDto.getStudent());
        httpSession.setAttribute("secstudying", responseDto.getSecStudying());
        httpSession.setAttribute("classstudying", responseDto.getClassStudying());
        request.setAttribute("classadm", responseDto.getClassAdm());
        request.setAttribute("secadm", responseDto.getSecAdm());
        httpSession.setAttribute("parents", responseDto.getParents());
        httpSession.setAttribute("feesstructure", responseDto.getFeesStructure());
        httpSession.setAttribute("sumoffees", responseDto.getTotalSum());
        httpSession.setAttribute("dueamount", responseDto.getDueAmount());
        httpSession.setAttribute("totalfees", responseDto.getTotalFeesAmount());
        httpSession.setAttribute("academicPerYear", responseDto.getAcademicPerYear());
        httpSession.setAttribute("currentAcademicYear", responseDto.getCurrentAcademicYear());
        httpSession.setAttribute("totalfeesconcession", responseDto.getTotalFeesConcession());
        httpSession.setAttribute("resultfromservice", responseDto.isSuccess());

        return responseDto.isSuccess();
    }

    public String viewAllStudentsList(){
        StudentService studentService = new StudentService(request, response, standardActionAdapter);

        ResultResponse resultResponse = studentService.viewAllStudentsList(httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("studentList", resultResponse.getResultList());

        return resultResponse.getMessage();
    }

    public String viewStudentsParentsPerBranch(){
        StudentService studentService = new StudentService(request, response, standardActionAdapter);

        ResultResponse resultResponse = studentService.viewStudentsParentsPerBranch(httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("studentList", resultResponse.getResultList());

        return resultResponse.getMessage();
    }
}
