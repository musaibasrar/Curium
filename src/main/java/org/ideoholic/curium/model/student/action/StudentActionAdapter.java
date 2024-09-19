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

        studentDto.setSid(DataUtil.parseInt(request.getParameter("id")));
        studentDto.setPid(DataUtil.parseInt(request.getParameter("idparents")));
        studentDto.setName(request.getParameter("name"));
        studentDto.setGender(request.getParameter("gender"));
        studentDto.setDateofbirth(request.getParameter("dateofbirth"));
        studentDto.setAge(DataUtil.parseInt(request.getParameter("age")));
        studentDto.setClassSec(request.getParameter("classsec"));
        studentDto.setSecstudying(request.getParameter("secstudying"));
        studentDto.setAdmclassE(request.getParameter("admclass"));
        studentDto.setAdmsecE(request.getParameter("admsec"));
        studentDto.setLastclass(request.getParameter("lastclass"));
        studentDto.setLastschool(request.getParameter("lastschool"));
        studentDto.setAdmissionnumber(request.getParameter("admnno"));
        studentDto.setAdmissiondate(DateUtil.indiandateParser(request.getParameter("dateofadmission")));
        studentDto.setBloodgroup(request.getParameter("bloodgroup"));
        studentDto.setNationality(request.getParameter("nationality"));
        studentDto.setReligion(request.getParameter("religion"));
        studentDto.setCaste(request.getParameter("caste"));
        studentDto.setMotherT(request.getParameter("motherT"));
        studentDto.setCreateddate(request.getParameter("createddate"));
        studentDto.setRemarks(request.getParameter("remarks"));
        studentDto.setStudentPicUpdate(request.getParameter("studentpicupdate"));
        studentDto.setStudentDoc1Update(request.getParameter("studentdoc1update"));
        studentDto.setStudentDoc2Update(request.getParameter("studentdoc2update"));
        studentDto.setStudentDoc3Update(request.getParameter("studentdoc3update"));
        studentDto.setStudentDoc4Update(request.getParameter("studentdoc4update"));
        studentDto.setStudentDoc5Update(request.getParameter("studentdoc5update"));
        studentDto.setStudentexternalid(request.getParameter("studentexternalid"));
        studentDto.setCrecord(request.getParameter("crecord"));
        studentDto.setPlaceofbirth(request.getParameter("place"));
        studentDto.setTcno(DataUtil.parseInt(request.getParameter("tcno")));
        studentDto.setDateoftc(DataUtil.emptyString(request.getParameter("dateoftc")));
        studentDto.setClassonleaving(request.getParameter("classonleaving"));
        studentDto.setProgress(request.getParameter("progress"));
        studentDto.setDateleaving(request.getParameter("dateofleaving"));
        studentDto.setReasonforleaving(request.getParameter("reasonforleaving"));
        studentDto.setNotcissued(DataUtil.parseInt(request.getParameter("notcissued")));
        studentDto.setDateoftcissued(request.getParameter("dateoftcissued"));
        studentDto.setGuardian(request.getParameter("guardian"));
        studentDto.setSemester(DataUtil.parseInt(request.getParameter("semester")));
        studentDto.setStream(request.getParameter("stream"));
        studentDto.setMediumofinstruction(request.getParameter("mediumofinstruction"));
        studentDto.setPreviousschooltype(request.getParameter("previousschooltype"));
        studentDto.setPreviouschooladdress(request.getParameter("previouschooladdress"));
        studentDto.setUrbanrural(request.getParameter("urbanrural"));
        studentDto.setStudentscastecertno(request.getParameter("studentscastecertno"));
        studentDto.setStudentscaste(request.getParameter("studentscaste"));
        studentDto.setSocialcategory(request.getParameter("socialcategory"));
        studentDto.setBelongtobpl(DataUtil.parseInt(request.getParameter("belongtobpl")));
        studentDto.setBplcardno(request.getParameter("bplcardno"));
        studentDto.setBhagyalakshmibondnumber(request.getParameter("bhagyalakshmibondnumber"));
        studentDto.setDisabilitychild(request.getParameter("disabilitychild"));
        studentDto.setSpecialcategory(request.getParameter("specialcategory"));
        studentDto.setNewcategory(request.getParameter("newcategory"));
        studentDto.setSts((request.getParameter("sts")));
        studentDto.setRte(DataUtil.parseInt(request.getParameter("rte")));
        studentDto.setPassedout(DataUtil.parseInt(request.getParameter("passedout")));
        studentDto.setLeftout(DataUtil.parseInt(request.getParameter("leftout")));
        studentDto.setDroppedout(DataUtil.parseInt(request.getParameter("droppedout")));
        studentDto.setYearofadmission(request.getParameter("yearofadmission"));
        studentDto.setArchive(request.getParameter("archive"));
        studentDto.setPromotedyear(request.getParameter("promotedyear"));


        studentDto.setFathersname(request.getParameter("fathersname"));
        studentDto.setMothersname(request.getParameter("mothersname"));
        studentDto.setProfession(request.getParameter("profession"));
        studentDto.setAnnualincome(request.getParameter("annualincome"));
        studentDto.setAddresspermanent(request.getParameter("permanentaddress"));
        studentDto.setAddresstemporary(request.getParameter("temporaryaddress"));
        studentDto.setNoofdependents(DataUtil.parseInt(request.getParameter("noofdependents")));
        studentDto.setRemarksadditional(request.getParameter("remarksadditional"));
        studentDto.setContactnumber(request.getParameter("contactnumber"));
        studentDto.setCocontactnumber(request.getParameter("cocontactnumber"));
        studentDto.setEmail(request.getParameter("email"));
        studentDto.setPep(DataUtil.parseInt(request.getParameter("pep")));
        studentDto.setPassedyear(request.getParameter("passedyear"));
        studentDto.setRegno(request.getParameter("regno"));
        studentDto.setResultclass(request.getParameter("resultclass"));
        studentDto.setXsecondlanguage(request.getParameter("Xsecondlanguage"));
        studentDto.setAggmarks(request.getParameter("aggmarks"));
        studentDto.setSubjectspart1(request.getParameter("subjectspart1"));
        studentDto.setSubjectspart2(request.getParameter("subjectspart2"));
        studentDto.setXmediuminstruction(request.getParameter("Xmediuminstruction"));
        studentDto.setPUmediuminstruction(request.getParameter("PUmediuminstruction"));
        studentDto.setPudetailsid(DataUtil.parseInt(request.getParameter("pudetailsid")));
        studentDto.setIddegreedetails(DataUtil.parseInt(request.getParameter("degreedetailsid")));
        studentDto.setFathersqualification(request.getParameter("fathersqualification"));
        studentDto.setMothersqualification(request.getParameter("mothersqualification"));
        studentDto.setFatherscastecertno(request.getParameter("fatherscastecertno"));
        studentDto.setMotherscastecertno(request.getParameter("motherscastecertno"));
        studentDto.setFatherscaste(request.getParameter("fatherscaste"));
        studentDto.setMotherscaste(request.getParameter("motherscaste"));
        studentDto.setLanguagesstudied(request.getParameter("languagesstudied"));
        studentDto.setMediumofinstructionlastschool(request.getParameter("mediumofinstructionlastschool"));
        studentDto.setPepdc(DataUtil.parseInt(request.getParameter("pepdc")));
        studentDto.setPassedyeardc(request.getParameter("passedyeardc"));
        studentDto.setRegnodc(request.getParameter("regnodc"));
        studentDto.setResultclassdc(request.getParameter("resultclassdc"));
        studentDto.setMediumofinstructiondc(request.getParameter("mediumofinstructiondc"));
        studentDto.setQpartone(request.getParameter("qpartone"));
        studentDto.setQparttwo(request.getParameter("qparttwo"));
        studentDto.setPpartone(request.getParameter("ppartone"));
        studentDto.setPparttwo(request.getParameter("pparttwo"));
        studentDto.setPumarkscard(DataUtil.parseInt(request.getParameter("pumarkscard")));
        studentDto.setMedicalreport(DataUtil.parseInt(request.getParameter("medicalreport")));
        studentDto.setIncomecertificate(DataUtil.parseInt(request.getParameter("incomecertificate")));
        studentDto.setMigrationcertificate(DataUtil.parseInt(request.getParameter("migrationcertificate")));
        studentDto.setOriginaltc(DataUtil.parseInt(request.getParameter("originaltc")));
        studentDto.setCastecertificate(DataUtil.parseInt(request.getParameter("castecertificate")));
        studentDto.setGames(request.getParameter("games"));
        studentDto.setExtracurricular(request.getParameter("extracurricular"));
        studentDto.setEmployer(request.getParameter("employer"));
        studentDto.setKarnataka(DataUtil.parseInt(request.getParameter("karnataka")));

        studentDto.setBankname(request.getParameter("bankname"));
        studentDto.setBankifsc(request.getParameter("bankifsc"));
        studentDto.setAccno(request.getParameter("accno"));
        studentDto.setStudentPicDelete(request.getParameter("studentpicdelete"));
        studentDto.setStudentDoc1Delete(request.getParameter("studentdoc1delete"));
        studentDto.setStudentDoc2Delete(request.getParameter("studentdoc2delete"));
        studentDto.setStudentDoc3Delete(request.getParameter("studentdoc3delete"));
        studentDto.setStudentDoc4Delete(request.getParameter("studentdoc4delete"));
        studentDto.setStudentDoc5Delete(request.getParameter("studentdoc5delete"));

        Student student = studentService.updateStudent(uploadedFiles, studentDto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(USERID).toString());

        String stId = student.getSid().toString();
		int branchId = student.getBranchid();
		return stId+"_"+branchId;
    }
}
