package org.ideoholic.curium.model.student.service;

import org.apache.commons.codec.binary.Base64;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ideoholic.curium.model.academicyear.dao.YearDAO;
import org.ideoholic.curium.model.academicyear.dto.Currentacademicyear;
import org.ideoholic.curium.model.degreedetails.dto.Degreedetails;
import org.ideoholic.curium.model.feescollection.dao.feesCollectionDAO;
import org.ideoholic.curium.model.feescollection.dto.Receiptinfo;
import org.ideoholic.curium.model.parents.dao.parentsDetailsDAO;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.pudetails.dto.Pudetails;
import org.ideoholic.curium.model.std.dto.Classsec;
import org.ideoholic.curium.model.std.service.StandardService;
import org.ideoholic.curium.model.student.dao.studentDetailsDAO;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.model.student.dto.StudentDto;
import org.ideoholic.curium.model.student.dto.StudentMapper;
import org.ideoholic.curium.model.student.dto.Studentfeesstructure;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.DateUtil;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

public class StudentService {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession httpSession;
    private String BRANCHID = "branchid";
    private String USERID = "userloginid";
    private StringBuilder optional = new StringBuilder();
    private StringBuilder compulsory = new StringBuilder();

    /**
     * Size of a byte buffer to read/write file
     */
    private static final int BUFFER_SIZE = 4096;

    public StudentService(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.httpSession = request.getSession();
    }

    public boolean addStudent(StudentDto studentDto, MultipartFile[] listOfFiles) {

        Student student = StudentMapper.INSTANCE.mapStudent(studentDto);
        Parents parents = StudentMapper.INSTANCE.mapParent(studentDto);
        Pudetails puDetails = StudentMapper.INSTANCE.mapPudetails(studentDto);
        Degreedetails degreeDetails = StudentMapper.INSTANCE.mapDegreedetails(studentDto);

        try {
            // Process form file field (input type="file")
            if (listOfFiles != null && listOfFiles.length != 0) {
                for (MultipartFile fileItem : listOfFiles) {
                    String fileName = (DataUtil.emptyString(fileItem.getOriginalFilename()));
                    String fileValue = (DataUtil.emptyString(fileItem.getName()));
                    if (!fileName.equalsIgnoreCase("")) {
                        byte[] bytesEncoded = Base64.encodeBase64(fileItem.getBytes());
                        String saveFile = new String(bytesEncoded);
                        student.setStudentpic(saveFile);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        student.setArchive(0);
        student.setPassedout(0);
        student.setDroppedout(0);
        student.setLeftout(0);
        student.setStudentexternalid(DataUtil.generateString(5));
        student.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
        student.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
        puDetails.setOptionalsubjects(optional.toString());
        puDetails.setCompulsorysubjects(compulsory.toString());
        student.setPudetails(puDetails);
        student.setDegreedetails(degreeDetails);
        parents.setStudent(student);
        parents.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
        parents.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
        parents = new parentsDetailsDAO().create(parents);

        if (parents != null) {
            return true;
        }

        return false;
    }

    public boolean viewAllStudents() {

        boolean result = false;
        String pages = "1";
        try {
            int page = 1;
            int recordsPerPage = 2000;
            if (pages != null) {
                page = Integer.parseInt(pages);
            }

            List<Object[]> list = new studentDetailsDAO().readListOfObjectsPagination((page - 1) * recordsPerPage,
                recordsPerPage, Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
            int noOfRecords = new studentDetailsDAO()
                .getNoOfRecords(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            request.setAttribute("studentList", list);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public boolean viewDetailsOfStudent() {
        return viewDetailsOfStudent(request.getParameter("id"));
    }

    public boolean viewDetailsOfStudent(String studentId) {
        boolean result = false;
        try {
            long id = Long.parseLong(studentId);
            Student student = new studentDetailsDAO().readUniqueObject(id);
            Parents parents = new parentsDetailsDAO().readUniqueObject(id);

			/*httpSession.setAttribute("studentfromservice",student);
			httpSession.setAttribute("parentsfromservice",parents);
			httpSession.setAttribute("idofstudentfromservice",id);*/

            Currentacademicyear currentYear = new YearDAO().showYear();
            httpSession.setAttribute("currentyearfromservice", currentYear.getCurrentacademicyear());

            // List<Feesdetails> feesdetails = new feesDetailsDAO().readList(id,
            // currentYear.getCurrentacademicyear());
            // httpSession.setAttribute("feesdetailsfromservice",feesdetails);
            List<Receiptinfo> rinfo = new feesCollectionDAO().getReceiptDetailsPerStudent(id,
                currentYear.getCurrentacademicyear());
            request.setAttribute("receiptinfo", rinfo);
            List<Studentfeesstructure> feesstructure = new studentDetailsDAO().getStudentFeesStructure(id,
                currentYear.getCurrentacademicyear());

            long totalSum = 0l;
            for (Receiptinfo receiptInfoSingle : rinfo) {
                totalSum = totalSum + receiptInfoSingle.getTotalamount();
            }

            long totalFeesAmount = 0l;
            long grandtotalFeesAmount = 0l;
            long totalFeesConcession = 0l;
            for (Studentfeesstructure studentfeesstructureSingle : feesstructure) {
                totalFeesAmount = totalFeesAmount + studentfeesstructureSingle.getFeesamount() - studentfeesstructureSingle.getWaiveoff() - studentfeesstructureSingle.getConcession();
                grandtotalFeesAmount = grandtotalFeesAmount + studentfeesstructureSingle.getFeesamount();
                totalFeesConcession = totalFeesConcession + studentfeesstructureSingle.getConcession();
            }

            //String sumOfFees = new feesDetailsDAO().feesSum(id, currentYear.getCurrentacademicyear());
            //String totalFees = new feesDetailsDAO().feesTotal(id, currentYear.getCurrentacademicyear());
            //String dueAmount = new feesDetailsDAO().dueAmount(id, currentYear.getCurrentacademicyear());
            if (student == null) {
                result = false;
            } else {
                httpSession.setAttribute("student", student);
                String classStudying = student.getClassstudying();
                if (!classStudying.equalsIgnoreCase("")) {
                    String[] classParts = classStudying.split("--");
                    httpSession.setAttribute("classstudying", classParts[0]);
                    httpSession.setAttribute("secstudying", "");
                    if (classParts.length > 1) {
                        httpSession.setAttribute("secstudying", classParts[1]);
                    }

                } else {
                    httpSession.setAttribute("classstudying", classStudying);
                    httpSession.setAttribute("secstudying", "");
                }

                String classAdmitted = student.getClassadmittedin();

                if (!classAdmitted.equalsIgnoreCase("")) {

                    String[] classAdmittedParts = classAdmitted.split("--");
                    request.setAttribute("classadm", classAdmittedParts[0]);
                    request.setAttribute("secadm", "");
                    if (classAdmittedParts.length > 1) {
                        request.setAttribute("secadm", classAdmittedParts[1]);
                    }

                } else {
                    request.setAttribute("classadm", classAdmitted);
                    request.setAttribute("secadm", "");
                }

                httpSession.setAttribute("parents", parents);
                // httpSession.setAttribute("feesdetails", feesdetails);
                httpSession.setAttribute("feesstructure", feesstructure);
                httpSession.setAttribute("sumoffees", totalSum);
                httpSession.setAttribute("dueamount", totalFeesAmount - totalSum);
                httpSession.setAttribute("totalfees", totalFeesAmount);
                httpSession.setAttribute("academicPerYear", currentYear.getCurrentacademicyear());
                httpSession.setAttribute("currentAcademicYear", currentYear.getCurrentacademicyear());

                result = true;
                httpSession.setAttribute("resultfromservice", result);
            }
            new StandardService(request, response).viewClasses();
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public String updateStudent(StudentDto studentDto, MultipartFile[] listOfFiles) {
        Student student = StudentMapper.INSTANCE.mapStudent(studentDto);
        Classsec classsec = StudentMapper.INSTANCE.mapClassec(studentDto);
        Parents parents = StudentMapper.INSTANCE.mapParent(studentDto);
        Pudetails puDetails = StudentMapper.INSTANCE.mapPudetails(studentDto);
        Degreedetails degreeDetails = StudentMapper.INSTANCE.mapDegreedetails(studentDto);
        String studentPicUpdate = null;

        try {
            if (listOfFiles != null && listOfFiles.length != 0) {
                for (MultipartFile fileItem : listOfFiles) {
                    String fileName = (DataUtil.emptyString(fileItem.getOriginalFilename()));
                    String fileValue = (DataUtil.emptyString(fileItem.getName()));
                    if (!fileName.equalsIgnoreCase("")) {
                        // Resize the image
                        byte[] bytesEncoded = Base64.encodeBase64(fileItem.getBytes());
                        System.out.println("ecncoded value is " + new String(bytesEncoded));
                        String saveFile = new String(bytesEncoded);
                        student.setStudentpic(saveFile);
                    } else {
                        student.setStudentpic(studentPicUpdate);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        student.setArchive(0);
        student.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
        student.setUserid(Integer.parseInt(httpSession.getAttribute("userloginid").toString()));

        if (puDetails.getIdpudetails() != null) {
            new studentDetailsDAO().updatePuDetails(puDetails);
            student.setPudetails(puDetails);
        }

        if (degreeDetails.getIddegreedetails() != null) {
            new studentDetailsDAO().updateDegreeDetails(degreeDetails);
            student.setDegreedetails(degreeDetails);
        }
        student = new studentDetailsDAO().update(student);
        if (parents.getPid() != null) {
            parents.setStudent(student);
            parents.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
            parents.setUserid(Integer.parseInt(httpSession.getAttribute("userloginid").toString()));

            parents = new parentsDetailsDAO().update(parents);
        }
        String stId = student.getSid().toString();
        int branchId = student.getBranchid();
        return stId + "_" + branchId;

    }

    public String updateStudent(MultipartFile[] listOfFiles) {
        Student student = new Student();
        Classsec classsec = new Classsec();
        Parents parents = new Parents();
        Pudetails puDetails = new Pudetails();
        Degreedetails degreeDetails = new Degreedetails();
        String id = "";
        String pid = "";
        int studentId = 0;
        int parentsId = 0;
        String addClass = null, addSec = null, addClassE = null, addSecE = null, conClassStudying = null,
            conClassAdmittedIn = null;
        String studentPicUpdate = null;
        String dropdowncateg = null;
        String newcateg = null;

        try {

            Enumeration<String> enumeration = request.getParameterNames();

            while (enumeration.hasMoreElements()) {
                // Process regular form field (input type="text|radio|checkbox|etc", select,
                // etc).
                String fieldName = enumeration.nextElement();

                System.out.println("field name is " + fieldName);
                if (fieldName.equalsIgnoreCase("id")) {
                    id = DataUtil.emptyString(request.getParameter(fieldName));

                }

                if (fieldName.equalsIgnoreCase("idparents")) {
                    pid = DataUtil.emptyString(request.getParameter(fieldName));
                }

                System.out.println("THE ID IS: " + id + "," + pid);

                if (id != "") {
                    studentId = Integer.parseInt(id);
                    student.setSid(studentId);
                }

                if (pid != "") {

                    parentsId = Integer.parseInt(pid);
                }

                if (fieldName.equalsIgnoreCase("name")) {

                    student.setName(DataUtil.emptyString(request.getParameter(fieldName)));
                    System.out.println("name==" + request.getParameter(fieldName));
                }

                if (fieldName.equalsIgnoreCase("gender")) {

                    student.setGender(DataUtil.emptyString(request.getParameter(fieldName)));

                }

                if (fieldName.equalsIgnoreCase("dateofbirth")) {

                    student.setDateofbirth(DateUtil.indiandateParser(request.getParameter(fieldName)));

                }

                if (fieldName.equalsIgnoreCase("age")) {

                    student.setAge(DataUtil.parseInt(request.getParameter(fieldName)));

                }

                if (fieldName.equalsIgnoreCase("classsec")) {

                    addClass = DataUtil.emptyString(request.getParameter(fieldName));
                    if (!addClass.equalsIgnoreCase("")) {
                        conClassStudying = addClass + "--";

                    }
                }

                if (fieldName.equalsIgnoreCase("secstudying")) {

                    addSec = DataUtil.emptyString(request.getParameter(fieldName));
                    if (!addSec.equalsIgnoreCase("")) {
                        conClassStudying = conClassStudying + addSec;
                    }
                }

                student.setClassstudying(DataUtil.emptyString(conClassStudying));

                if (fieldName.equalsIgnoreCase("admclass")) {

                    addClassE = DataUtil.emptyString(request.getParameter(fieldName));

                    if (!addClassE.equalsIgnoreCase("")) {
                        conClassAdmittedIn = addClassE + "--";

                    }

                }

                if (fieldName.equalsIgnoreCase("admsec")) {

                    addSecE = DataUtil.emptyString(request.getParameter(fieldName));
                    if (!addSecE.equalsIgnoreCase("")) {
                        conClassAdmittedIn = conClassAdmittedIn + addSecE;
                    }

                }

                student.setClassadmittedin(DataUtil.emptyString(conClassAdmittedIn));

                if (fieldName.equalsIgnoreCase("lastclass")) {
                    student.setStdlaststudied(DataUtil.emptyString(request.getParameter(fieldName)));
                }

                if (fieldName.equalsIgnoreCase("lastschool")) {
                    student.setSchoollastattended(DataUtil.emptyString(request.getParameter(fieldName)));
                }

                if (fieldName.equalsIgnoreCase("admnno")) {
                    student.setAdmissionnumber(DataUtil.emptyString(request.getParameter(fieldName)));
                }

                if (fieldName.equalsIgnoreCase("dateofadmission")) {
                    student.setAdmissiondate(DateUtil.indiandateParser(request.getParameter(fieldName)));
                }

                if (fieldName.equalsIgnoreCase("bloodgroup")) {
                    student.setBloodgroup(DataUtil.emptyString(request.getParameter(fieldName)));
                }

                if (fieldName.equalsIgnoreCase("nationality")) {
                    student.setNationality(DataUtil.emptyString(request.getParameter(fieldName)));
                }

                if (fieldName.equalsIgnoreCase("religion")) {
                    student.setReligion(DataUtil.emptyString(request.getParameter(fieldName)));

                }

                if (fieldName.equalsIgnoreCase("caste")) {
                    student.setCaste(DataUtil.emptyString(request.getParameter(fieldName)));
                }

                if (fieldName.equalsIgnoreCase("motherT")) {
                    student.setMothertongue(DataUtil.emptyString(request.getParameter(fieldName)));
                }

                if (fieldName.equalsIgnoreCase("createddate")) {
                    student.setCreateddate(DateUtil.indiandateParser(request.getParameter(fieldName)));
                }

                if (fieldName.equalsIgnoreCase("remarks")) {
                    student.setRemarks(DataUtil.emptyString(request.getParameter(fieldName)));
                }

                if (fieldName.equalsIgnoreCase("studentpicupdate")) {
                    studentPicUpdate = DataUtil.emptyString(request.getParameter(fieldName));
                }

                if (fieldName.equalsIgnoreCase("studentexternalid")) {
                    student.setStudentexternalid(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("crecord")) {
                    student.setCrecord(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("dateofcr")) {
                    student.setCrecorddate(DateUtil.indiandateParser(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("place")) {
                    student.setPlaceofbirth(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("tcno")) {
                    student.setNooftc(DataUtil.parseInt(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("dateoftc")) {
                    student.setDateoftc(DateUtil.indiandateParser(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("classonleaving")) {
                    student.setClassonleaving(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("progress")) {
                    student.setSubsequentprogress(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("dateofleaving")) {
                    student.setDateleaving(DateUtil.indiandateParser(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("reasonforleaving")) {
                    student.setReasonleaving(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("notcissued")) {
                    student.setNotcissued(DataUtil.parseInt(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("dateoftcissued")) {
                    student.setDatetcissued(DateUtil.indiandateParser(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("guardian")) {
                    student.setGuardiandetails(DataUtil.emptyString(request.getParameter(fieldName)));
                }

                if (fieldName.equalsIgnoreCase("semester")) {
                    student.setSemester(DataUtil.parseInt(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("stream")) {
                    student.setStream(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("mediumofinstruction")) {
                    student.setMediumofinstruction(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("previousschooltype")) {
                    student.setPreviousschooltype(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("previouschooladdress")) {
                    student.setPreviouschooladdress(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("urbanrural")) {
                    student.setUrbanrural(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("studentscastecertno")) {
                    student.setStudentscastecertno(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("studentscaste")) {
                    student.setStudentscaste(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("socialcategory")) {
                    student.setSocialcategory(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("belongtobpl")) {
                    student.setBelongtobpl(DataUtil.parseInt(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("bplcardno")) {
                    student.setBplcardno(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("bhagyalakshmibondnumber")) {
                    student.setBhagyalakshmibondnumber(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("disabilitychild")) {
                    student.setDisabilitychild(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("specialcategory")) {
                    dropdowncateg = DataUtil.emptyString(request.getParameter(fieldName));
                }
                if (fieldName.equalsIgnoreCase("newcategory")) {
                    newcateg = DataUtil.emptyString(request.getParameter(fieldName));
                }
                if (fieldName.equalsIgnoreCase("sts")) {
                    student.setSts(request.getParameter(fieldName));
                }
                if (fieldName.equalsIgnoreCase("rte")) {
                    student.setRte(DataUtil.parseInt(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("passedout")) {
                    student.setPassedout(DataUtil.parseInt(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("leftout")) {
                    student.setLeftout(DataUtil.parseInt(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("droppedout")) {
                    student.setDroppedout(DataUtil.parseInt(request.getParameter(fieldName)));
                }
                // Updating paretns information

                parents.setPid(parentsId);
                parents.setSid(studentId);

                if (fieldName.equalsIgnoreCase("fathersname")) {
                    parents.setFathersname(DataUtil.emptyString(request.getParameter(fieldName)));
                }

                if (fieldName.equalsIgnoreCase("mothersname")) {
                    parents.setMothersname(DataUtil.emptyString(request.getParameter(fieldName)));
                }

                if (fieldName.equalsIgnoreCase("profession")) {
                    parents.setProfession(DataUtil.emptyString(request.getParameter(fieldName)));

                }

                if (fieldName.equalsIgnoreCase("annualincome")) {
                    parents.setParentsannualincome(DataUtil.emptyString(request.getParameter(fieldName)));

                }

                if (fieldName.equalsIgnoreCase("permanentaddress")) {
                    parents.setAddresspermanent(DataUtil.emptyString(request.getParameter(fieldName)));
                }

                if (fieldName.equalsIgnoreCase("temporaryaddress")) {
                    parents.setAddresstemporary(DataUtil.emptyString(request.getParameter(fieldName)));
                }

                if (fieldName.equalsIgnoreCase("noofdependents")) {
                    parents.setNoofdependents(DataUtil.parseInt(request.getParameter(fieldName)));
                }

                if (fieldName.equalsIgnoreCase("remarksadditional")) {
                    parents.setRemarks(DataUtil.emptyString(request.getParameter(fieldName)));
                }

                if (fieldName.equalsIgnoreCase("contactnumber")) {
                    parents.setContactnumber(DataUtil.emptyString(request.getParameter(fieldName)));
                }

                if (fieldName.equalsIgnoreCase("cocontactnumber")) {
                    parents.setCocontactnumber(DataUtil.emptyString(request.getParameter(fieldName)));
                }

                if (fieldName.equalsIgnoreCase("email")) {
                    parents.setEmail(DataUtil.emptyString(request.getParameter(fieldName)));
                }

                if (fieldName.equalsIgnoreCase("pep")) {
                    puDetails.setExampassedappearance(DataUtil.parseInt(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("passedyear")) {
                    puDetails.setExampassedyear(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("regno")) {
                    puDetails.setExampassedregno(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("resultclass")) {
                    puDetails.setExampassedresultwithclass(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("xsecondlanguage")) {
                    puDetails.setSecondlanguage(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("aggmarks")) {
                    puDetails.setAggregatemarkssslc(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("subjectspart1")) {
                    puDetails.setOptionalsubjects(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("subjectspart2")) {
                    puDetails.setCompulsorysubjects(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("xmediuminstruction")) {
                    puDetails.setSslcmediuminstruction(DataUtil.emptyString(request.getParameter(fieldName)));

                }
                if (fieldName.equalsIgnoreCase("PUmediuminstruction")) {
                    puDetails.setPumediuminstruction(DataUtil.emptyString(request.getParameter(fieldName)));

                }
                if (fieldName.equalsIgnoreCase("pudetailsid")) {
                    puDetails.setIdpudetails(DataUtil.parseInt(request.getParameter(fieldName)));

                }
                if (fieldName.equalsIgnoreCase("fathersqualification")) {
                    parents.setFathersqualification(DataUtil.emptyString(request.getParameter(fieldName)));

                }
                if (fieldName.equalsIgnoreCase("mothersqualification")) {
                    parents.setMothersqualification(DataUtil.emptyString(request.getParameter(fieldName)));

                }
                if (fieldName.equalsIgnoreCase("fatherscastecertno")) {
                    parents.setFatherscastecertno(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("motherscastecertno")) {
                    parents.setMotherscastecertno(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("fatherscaste")) {
                    parents.setFatherscaste(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("motherscaste")) {
                    parents.setMotherscaste(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("languagesstudied")) {
                    student.setLanguagesstudied(DataUtil.emptyString(request.getParameter(fieldName)));

                }
                if (fieldName.equalsIgnoreCase("mediumofinstructionlastschool")) {
                    student.setInstructionmediumlastschool(DataUtil.emptyString(request.getParameter(fieldName)));

                }

                // Updating Degree Details
                if (fieldName.equalsIgnoreCase("pepdc")) {
                    degreeDetails.setExampassedappearance(DataUtil.parseInt(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("passedyeardc")) {
                    degreeDetails.setExampassedyear(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("regnodc")) {
                    degreeDetails.setExampassedregno(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("resultclassdc")) {
                    degreeDetails.setExampassedresultwithclass(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("mediumofinstructiondc")) {
                    degreeDetails.setPumediuminstruction(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("qpartone")) {
                    degreeDetails
                        .setSubjectsqualifingexampartone(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("qparttwo")) {
                    degreeDetails
                        .setSubjectsqualifingexamparttwo(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("ppartone")) {
                    degreeDetails.setSubjectsdegreecoursepartone(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("pparttwo")) {
                    degreeDetails.setSubjectsdegreecourseparttwo(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("pumarkscard")) {
                    degreeDetails.setPumarkscard(DataUtil.parseInt(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("medicalreport")) {
                    degreeDetails.setMedicalreport(DataUtil.parseInt(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("incomecertificate")) {
                    degreeDetails.setIncomecertificate(DataUtil.parseInt(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("migrationcertificate")) {
                    degreeDetails.setMigrationcertificate(DataUtil.parseInt(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("originaltc")) {
                    degreeDetails.setTransfercertificate(DataUtil.parseInt(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("castecertificate")) {
                    degreeDetails.setCastecertificate(DataUtil.parseInt(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("games")) {
                    degreeDetails.setProficiencysports(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("extracurricular")) {
                    degreeDetails.setExtracurricular(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("employer")) {
                    degreeDetails.setAreyouemployee(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("karnataka")) {
                    degreeDetails.setKarnataka(DataUtil.parseInt(request.getParameter(fieldName)));
                }
                // End Degree Details

                // Bank Details
                if (fieldName.equalsIgnoreCase("bankname")) {
                    student.setBankname(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("bankifsc")) {
                    student.setBankifsc(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("accno")) {
                    student.setAccno(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                // End Bank Details

            }
            if (listOfFiles != null && listOfFiles.length != 0) {

                for (MultipartFile fileItem : listOfFiles) {
                    String fileName = (DataUtil.emptyString(fileItem.getOriginalFilename()));
                    String fileValue = (DataUtil.emptyString(fileItem.getName()));

                    if (!fileName.equalsIgnoreCase("")) {
                        // Resize the image
                        byte[] bytesEncoded = Base64.encodeBase64(fileItem.getBytes());
                        System.out.println("ecncoded value is " + new String(bytesEncoded));
                        String saveFile = new String(bytesEncoded);

                        student.setStudentpic(saveFile);

                    } else {

                        student.setStudentpic(studentPicUpdate);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if ("".equalsIgnoreCase(newcateg)) {
            student.setSpecialcategory(dropdowncateg);
        } else {
            student.setSpecialcategory(newcateg);
        }
        student.setArchive(0);
        student.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
        student.setUserid(Integer.parseInt(httpSession.getAttribute("userloginid").toString()));

        if (puDetails.getIdpudetails() != null) {
            new studentDetailsDAO().updatePuDetails(puDetails);
            student.setPudetails(puDetails);
        }

        if (degreeDetails.getIddegreedetails() != null) {
            new studentDetailsDAO().updateDegreeDetails(degreeDetails);
            student.setDegreedetails(degreeDetails);
        }
        student = new studentDetailsDAO().update(student);
        if (pid != "") {
            parents.setStudent(student);
            parents.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
            parents.setUserid(Integer.parseInt(httpSession.getAttribute("userloginid").toString()));

            parents = new parentsDetailsDAO().update(parents);
        }
        String stId = student.getSid().toString();
        int branchId = student.getBranchid();
        return stId + "_" + branchId;

    }

    public boolean viewAllStudentsList() {

        boolean result = false;
        if (httpSession.getAttribute(BRANCHID) != null) {

            try {

                List<Student> list = new studentDetailsDAO()
                    .readListOfStudents(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
                request.setAttribute("studentList", list);
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
                result = false;
            }

        }

        return result;
    }

    public void archiveMultiple() {
        String[] studentIds = request.getParameterValues("studentIDs");

        if (studentIds != null) {
            List<Integer> ids = new ArrayList();
            for (String id : studentIds) {
                ids.add(Integer.valueOf(id));

            }
            new studentDetailsDAO().archiveMultiple(ids);
        }
    }

    public boolean viewAllStudentsArchive() {

        boolean result = false;

        try {
            List<Student> list = new studentDetailsDAO().readListOfStudentsArchive();
            request.setAttribute("studentListArchive", list);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void deleteMultiple() {
        String[] studentIds = request.getParameterValues("studentIDs");
        if (studentIds != null) {
            List<Integer> ids = new ArrayList();
            List<Integer> iddetails = new ArrayList();
            for (String id : studentIds) {
                String[] iddetailsarray = id.split(":");
                ids.add(Integer.valueOf(iddetailsarray[0]));
                if (iddetailsarray.length > 1) {
                    iddetails.add(Integer.valueOf(iddetailsarray[1]));
                }
            }
            new studentDetailsDAO().deleteMultiple(ids, iddetails);
        }
    }

    public void restoreMultiple() {
        String[] studentIds = request.getParameterValues("studentIDs");
        if (studentIds != null) {
            List<Integer> ids = new ArrayList();
            for (String id : studentIds) {
                String[] iddetailsarray = id.split(":");
                ids.add(Integer.valueOf(iddetailsarray[0]));
            }
            System.out.println("id length" + studentIds.length);
            new studentDetailsDAO().restoreMultiple(ids);
        }
    }

    public boolean promoteMultiple() {
        String[] studentIds = request.getParameterValues("studentIDs");
        String classStudying = request.getParameter("classstudying");
        String promotedYear = httpSession.getAttribute("currentAcademicYear").toString();
        List<Student> studentList = new ArrayList<Student>();

        boolean result = false;

        for (String id : studentIds) {
            Student student = new Student();
            student.setSid(Integer.valueOf(id));
            student.setClassstudying(request.getParameter("classstudying_" + id));
            studentList.add(student);
        }

        if (new studentDetailsDAO().promoteMultiple(studentList, classStudying, promotedYear, Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()))) {
            result = true;
        }
        return result;
    }

    public boolean viewAllStudentsParents() {

        boolean result = false;
        // String pages = "1";
        if (httpSession.getAttribute(BRANCHID) != null) {
            try {
                int page = 1;
                int recordsPerPage = 100;
                if (!"".equalsIgnoreCase(DataUtil.emptyString(request.getParameter("page")))) {
                    page = Integer.parseInt(request.getParameter("page"));
                }

                List<Parents> list = new studentDetailsDAO().readListOfObjectsPaginationALL((page - 1) * recordsPerPage,
                    recordsPerPage, Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
                request.setAttribute("studentList", list);
                int noOfRecords = new studentDetailsDAO()
                    .getNoOfRecords(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
                int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
                request.setAttribute("studentList", list);
                request.setAttribute("noOfPages", noOfPages);
                request.setAttribute("currentPage", page);
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public boolean viewfeesStructurePerYear() {
        boolean result = false;

        try {

            long id = Long.parseLong(request.getParameter("id"));
            String academicYear = request.getParameter("academicyear");

            List<Receiptinfo> rinfo = new feesCollectionDAO().getReceiptDetailsPerStudent(id, academicYear);
            request.setAttribute("receiptinfo", rinfo);
            List<Studentfeesstructure> feesstructure = new studentDetailsDAO().getStudentFeesStructure(id,
                academicYear);

            long totalSum = 0l;
            for (Receiptinfo receiptInfoSingle : rinfo) {
                totalSum = totalSum + receiptInfoSingle.getTotalamount();
            }

            long totalFeesAmount = 0l;
            for (Studentfeesstructure studentfeesstructureSingle : feesstructure) {
                totalFeesAmount = totalFeesAmount + studentfeesstructureSingle.getFeesamount()
                    - studentfeesstructureSingle.getWaiveoff();
            }
            httpSession.setAttribute("feesstructure", feesstructure);
            httpSession.setAttribute("sumoffees", totalSum);
            httpSession.setAttribute("dueamount", totalFeesAmount - totalSum);
            httpSession.setAttribute("totalfees", totalFeesAmount);
            httpSession.setAttribute("academicPerYear", academicYear);

            result = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean exportDataForStudents() {

        String[] studentIds = request.getParameterValues("studentIDs");
        boolean successResult = false;

        List<Parents> listOfStudentRecords = new ArrayList<Parents>();

        if (studentIds != null) {
            for (String id : studentIds) {
                if (id != null || id != "") {
                    String queryMain = "From Parents as parents where parents.branchid="
                        + Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()) + " AND";
                    String querySub = " parents.Student.id = " + id + " order by parents.Student.admissionnumber ASC";
                    queryMain = queryMain + querySub;

                    List<Parents> searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
                    request.setAttribute("searchStudentList", searchStudentList);

                    Parents searchStudentRecords = new studentDetailsDAO().getStudentRecords(queryMain);
                    listOfStudentRecords.add(searchStudentRecords);
                }

            }
            try {
                if (exportDataToExcel(listOfStudentRecords)) {
                    successResult = true;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return successResult;

    }

    public boolean exportDataToExcel(List<Parents> listOfStudentRecords) throws Exception {

        boolean writeSucees = false;

        try {

            // Creating an excel file
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("ListOfStudents");
            Map<String, Object[]> data = new HashMap<String, Object[]>();
            Map<String, Object[]> headerData = new HashMap<String, Object[]>();
            headerData.put("Header",
                new Object[]{"Student Name", "Gender", "Date Of Birth", "Age", "Studying In Class",
                    "Admitted In Class", "Admission Number", "Admission Date", "Blood Group", "Religion",
                    "Caste", "Fathers Name", "Mothers Name"});
            int i = 1;
            for (Parents studentDetails : listOfStudentRecords) {
                data.put(Integer.toString(i),
                    new Object[]{DataUtil.emptyString(studentDetails.getStudent().getName()),
                        DataUtil.emptyString(studentDetails.getStudent().getGender()),
                        DataUtil.emptyString(
                            DateUtil.getStringDate(studentDetails.getStudent().getDateofbirth())),
                        DataUtil.emptyString(Integer.toString(studentDetails.getStudent().getAge())),
                        DataUtil.emptyString(studentDetails.getStudent().getClassstudying().replace("--", " ")),
                        DataUtil.emptyString(
                            studentDetails.getStudent().getClassadmittedin().replace("--", " ")),
                        DataUtil.emptyString(studentDetails.getStudent().getAdmissionnumber()),
                        DataUtil.emptyString(studentDetails.getStudent().getAdmissiondate().toString()),
                        DataUtil.emptyString(studentDetails.getStudent().getBloodgroup()),
                        DataUtil.emptyString(studentDetails.getStudent().getReligion()),
                        DataUtil.emptyString(studentDetails.getStudent().getCaste()),
                        DataUtil.emptyString(studentDetails.getFathersname()),
                        DataUtil.emptyString(studentDetails.getMothersname())});
                i++;
            }
            Row headerRow = sheet.createRow(0);
            Object[] objArrHeader = headerData.get("Header");
            int cellnum1 = 0;
            for (Object obj : objArrHeader) {
                Cell cell = headerRow.createCell(cellnum1++);
                if (obj instanceof String)
                    cell.setCellValue((String) obj);
            }
            Set<String> keyset = data.keySet();
            int rownum = 1;
            for (String key : keyset) {
                Row row = sheet.createRow(rownum++);
                Object[] objArr = data.get(key);
                int cellnum = 0;
                for (Object obj : objArr) {
                    Cell cell = row.createCell(cellnum++);
                    if (obj instanceof Date)
                        cell.setCellValue((Date) obj);
                    else if (obj instanceof Boolean)
                        cell.setCellValue((Boolean) obj);
                    else if (obj instanceof String)
                        cell.setCellValue((String) obj);
                    else if (obj instanceof Double)
                        cell.setCellValue((Double) obj);
                }
            }

            // ClassLoader classLoader = getClass().getClassLoader();
            // Local
            // FileOutputStream out = new FileOutputStream("D:/schoolfiles/test.xlsx");
            // FileOutputStream out = new FileOutputStream(new
            // File("/usr/local/tomcat/webapps/www.searchmysearch.com/musarpbiabha/studentsdetails.xlsx"));
            FileOutputStream out = new FileOutputStream(
                new File(System.getProperty("java.io.tmpdir") + "/studentsdetails.xlsx"));
            workbook.write(out);
            out.close();
            writeSucees = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return writeSucees;
        // getFile(name, path);
    }

    public String generateBonafide() {

        String[] studentIds = request.getParameterValues("studentIDs");
        String bonafidePage = null;

        if (studentIds != null) {
            String getStudentInfo = "from Parents as parents where parents.Student.sid=" + studentIds[0];
            Parents parents = new studentDetailsDAO().getStudentRecords(getStudentInfo);
            httpSession.setAttribute("studentdetailsbonafide", parents);
            bonafidePage = "bonafidecertificateprint";
        }

        return bonafidePage;
    }

    public boolean downlaodFile() {
        boolean result = false;
        try {

            File downloadFile = new File(System.getProperty("java.io.tmpdir") + "/studentsdetails.xlsx");
            FileInputStream inStream = new FileInputStream(downloadFile);

            // get MIME type of the file
            String mimeType = "application/vnd.ms-excel";

            // set content attributes for the response
            response.setContentType(mimeType);
            // response.setContentLength((int) bis.length());

            // set headers for the response
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", "studentdetails.xlsx");
            response.setHeader(headerKey, headerValue);

            // get output stream of the response
            OutputStream outStream = response.getOutputStream();

            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;

            // write bytes read from the input stream into the output stream
            while ((bytesRead = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }

            inStream.close();
            outStream.close();
            result = true;
        } catch (Exception e) {
            System.out.println("" + e);
        }
        return result;
    }

    public String addNew() {

        if (httpSession.getAttribute(BRANCHID) != null) {
            String branchId = httpSession.getAttribute(BRANCHID).toString();
            return "addStudent";
            /*
             * if("1".equalsIgnoreCase(branchId) || "2".equalsIgnoreCase(branchId) ||
             * "3".equalsIgnoreCase(branchId)) { return "addStudent.jsp"; }else
             * if("4".equalsIgnoreCase(branchId)) { return "addStudentPU.jsp"; }else
             * if("5".equalsIgnoreCase(branchId)) { return "addStudentDC.jsp"; }
             */
        }
        return "sessiontimeout";
    }

    public void viewAllStudentsSuperAdmin() {

        String pages = "1";

        try {
            int page = 1;
            int recordsPerPage = 5000;
            if (pages != null) {
                page = Integer.parseInt(pages);
            }
            List<Parents> list = new studentDetailsDAO().readListStudentsSuperAdmin((page - 1) * recordsPerPage,
                recordsPerPage);
            request.setAttribute("studentList", list);
            int noOfRecords = new studentDetailsDAO().getNoOfRecords();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            request.setAttribute("studentList", list);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}