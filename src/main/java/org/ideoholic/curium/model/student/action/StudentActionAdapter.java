package org.ideoholic.curium.model.student.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.attendance.dto.StudentAttendanceDetailsResponseDto;
import org.ideoholic.curium.model.feescollection.dto.FeesDetailsResponseDto;
import org.ideoholic.curium.model.parents.dto.ParentListResponseDto;
import org.ideoholic.curium.model.std.action.StandardActionAdapter;
import org.ideoholic.curium.model.student.dto.*;
import org.ideoholic.curium.model.student.service.StudentService;
import org.ideoholic.curium.util.DataUtil;
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


    public String updateStudent(StudentDto student, MultipartFile[] uploadedFiles) {
        StudentService studentService = new StudentService(request, response, standardActionAdapter);

        student.setSid(DataUtil.parseInt(request.getParameter("id")));
        student.setPid(DataUtil.parseInt(request.getParameter("idparents")));
        student.setName(request.getParameter("name"));
        student.setGender(request.getParameter("gender"));
        student.setDateofbirth(request.getParameter("dateofbirth"));
        student.setAge(DataUtil.parseInt(request.getParameter("age")));
        student.setClassSec(request.getParameter("classsec"));
        student.setSecStudying(request.getParameter("secstudying"));
        student.setAdmclassE(request.getParameter("admclass"));
        student.setAdmsecE(request.getParameter("admsec"));
        student.setLastclass(request.getParameter("lastclass"));
        student.setLastschool(request.getParameter("lastschool"));
        student.setAdmnno(request.getParameter("admnno"));
        student.setDateofadmission(request.getParameter("dateofadmission"));
        student.setBloodgroup(request.getParameter("bloodgroup"));
        student.setNationality(request.getParameter("nationality"));
        student.setReligion(request.getParameter("religion"));
        student.setCaste(request.getParameter("caste"));
        student.setMotherT(request.getParameter("motherT"));
        student.setCreateddate(request.getParameter("createddate"));
        student.setRemarks(request.getParameter("remarks"));
        student.setStudentPicUpdate(request.getParameter("studentpicupdate"));
        student.setStudentDoc1Update(request.getParameter("studentdoc1update"));
        student.setStudentDoc2Update(request.getParameter("studentdoc2update"));
        student.setStudentDoc3Update(request.getParameter("studentdoc3update"));
        student.setStudentDoc4Update(request.getParameter("studentdoc4update"));
        student.setStudentDoc5Update(request.getParameter("studentdoc5update"));
        student.setStudentexternalid(request.getParameter("studentexternalid"));
        student.setCrecord(request.getParameter("crecord"));
        student.setCreateddate(request.getParameter("crecorddate"));
        student.setPlace(request.getParameter("place"));
        student.setTcno(DataUtil.parseInt(request.getParameter("tcno")));
        student.setDateoftc(DataUtil.emptyString(request.getParameter("dateoftc")));
        student.setClassonleaving(request.getParameter("classonleaving"));
        student.setProgress(request.getParameter("progress"));
        student.setDateofleaving(request.getParameter("dateofleaving"));
        student.setReasonforleaving(request.getParameter("reasonforleaving"));
        student.setNotcissued(DataUtil.parseInt(request.getParameter("notcissued")));
        student.setDateoftcissued(request.getParameter("dateoftcissued"));
        student.setGuardian(request.getParameter("guardian"));
        student.setSemester(DataUtil.parseInt(request.getParameter("semester")));
        student.setStream(request.getParameter("stream"));
        student.setMediumofinstruction(request.getParameter("mediumofinstruction"));
        student.setPreviousschooltype(request.getParameter("previousschooltype"));
        student.setPreviouschooladdress(request.getParameter("previouschooladdress"));
        student.setUrbanrural(request.getParameter("urbanrural"));
        student.setStudentscastecertno(request.getParameter("studentscastecertno"));
        student.setStudentscaste(request.getParameter("studentscaste"));
        student.setSocialcategory(request.getParameter("socialcategory"));
        student.setBelongtobpl(DataUtil.parseInt(request.getParameter("belongtobpl")));
        student.setBplcardno(request.getParameter("bplcardno"));
        student.setBhagyalakshmibondnumber(request.getParameter("bhagyalakshmibondnumber"));
        student.setDisabilitychild(request.getParameter("disabilitychild"));
        student.setSpecialcategory(request.getParameter("specialcategory"));
        student.setNewcategory(request.getParameter("newcategory"));
        student.setSts(DataUtil.parseInt(request.getParameter("sts")));
        student.setRte(DataUtil.parseInt(request.getParameter("rte")));
        student.setPassedout(DataUtil.parseInt(request.getParameter("passedout")));
        student.setLeftout(DataUtil.parseInt(request.getParameter("leftout")));
        student.setDroppedout(DataUtil.parseInt(request.getParameter("droppedout")));
        student.setYearofadmission(request.getParameter("yearofadmission"));
        student.setArchive(request.getParameter("archive"));
        student.setPromotedyear(request.getParameter("promotedyear"));


        student.setFathersname(request.getParameter("fathersname"));
        student.setMothersname(request.getParameter("mothersname"));
        student.setProfession(request.getParameter("profession"));
        student.setAnnualincome(request.getParameter("annualincome"));
        student.setPermanentaddress(request.getParameter("permanentaddress"));
        student.setTemporaryaddress(request.getParameter("temporaryaddress"));
        student.setNoofdependents(DataUtil.parseInt(request.getParameter("noofdependents")));
        student.setRemarksadditional(request.getParameter("remarksadditional"));
        student.setContactnumber(request.getParameter("contactnumber"));
        student.setCocontactnumber(request.getParameter("cocontactnumber"));
        student.setEmail(request.getParameter("email"));
        student.setPep(DataUtil.parseInt(request.getParameter("pep")));
        student.setPassedyear(request.getParameter("passedyear"));
        student.setRegno(request.getParameter("regno"));
        student.setResultclass(request.getParameter("resultclass"));
        student.setXsecondlanguage(request.getParameter("Xsecondlanguage"));
        student.setAggmarks(request.getParameter("aggmarks"));
        student.setSubjectspart1(request.getParameter("subjectspart1"));
        student.setSubjectspart2(request.getParameter("subjectspart2"));
        student.setXmediuminstruction(request.getParameter("Xmediuminstruction"));
        student.setPUmediuminstruction(request.getParameter("PUmediuminstruction"));
        student.setPudetailsid(DataUtil.parseInt(request.getParameter("pudetailsid")));
        student.setIddegreedetails(DataUtil.parseInt(request.getParameter("degreedetailsid")));
        student.setFathersqualification(request.getParameter("fathersqualification"));
        student.setMothersqualification(request.getParameter("mothersqualification"));
        student.setFatherscastecertno(request.getParameter("fatherscastecertno"));
        student.setMotherscastecertno(request.getParameter("motherscastecertno"));
        student.setFatherscaste(request.getParameter("fatherscaste"));
        student.setMotherscaste(request.getParameter("motherscaste"));
        student.setLanguagesstudied(request.getParameter("languagesstudied"));
        student.setMediumofinstructionlastschool(request.getParameter("mediumofinstructionlastschool"));
        student.setPepdc(DataUtil.parseInt(request.getParameter("pepdc")));
        student.setPassedyeardc(request.getParameter("passedyeardc"));
        student.setRegnodc(request.getParameter("regnodc"));
        student.setResultclassdc(request.getParameter("resultclassdc"));
        student.setMediumofinstructiondc(request.getParameter("mediumofinstructiondc"));
        student.setQpartone(request.getParameter("qpartone"));
        student.setQparttwo(request.getParameter("qparttwo"));
        student.setPpartone(request.getParameter("ppartone"));
        student.setPparttwo(request.getParameter("pparttwo"));
        student.setPumarkscard(DataUtil.parseInt(request.getParameter("pumarkscard")));
        student.setMedicalreport(DataUtil.parseInt(request.getParameter("medicalreport")));
        student.setIncomecertificate(DataUtil.parseInt(request.getParameter("incomecertificate")));
        student.setMigrationcertificate(DataUtil.parseInt(request.getParameter("migrationcertificate")));
        student.setOriginaltc(DataUtil.parseInt(request.getParameter("originaltc")));
        student.setCastecertificate(DataUtil.parseInt(request.getParameter("castecertificate")));
        student.setGames(request.getParameter("games"));
        student.setExtracurricular(request.getParameter("extracurricular"));
        student.setEmployer(request.getParameter("employer"));
        student.setKarnataka(DataUtil.parseInt(request.getParameter("karnataka")));

        student.setBankname(request.getParameter("bankname"));
        student.setBankifsc(request.getParameter("bankifsc"));
        student.setAccno(request.getParameter("accno"));
        student.setStudentPicDelete(request.getParameter("studentpicdelete"));
        student.setStudentDoc1Delete(request.getParameter("studentdoc1delete"));
        student.setStudentDoc2Delete(request.getParameter("studentdoc2delete"));
        student.setStudentDoc3Delete(request.getParameter("studentdoc3delete"));
        student.setStudentDoc4Delete(request.getParameter("studentdoc4delete"));
        student.setStudentDoc5Delete(request.getParameter("studentdoc5delete"));

        ResultResponse resultResponse = studentService.updateStudent(uploadedFiles, student, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(USERID).toString());

        return resultResponse.getMessage();
    }
}
