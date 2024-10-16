package org.ideoholic.curium.model.student.service;

import org.apache.commons.codec.binary.Base64;
import org.ideoholic.curium.model.account.dao.AccountDAO;
import org.ideoholic.curium.model.account.dto.VoucherEntrytransactions;
import org.ideoholic.curium.model.branch.dto.Branch;
import org.ideoholic.curium.model.degreedetails.dto.Degreedetails;
import org.ideoholic.curium.model.feescategory.dto.Feescategory;
import org.ideoholic.curium.model.parents.dao.parentsDetailsDAO;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.pudetails.dto.Pudetails;
import org.ideoholic.curium.model.stampfees.dao.StampFeesDAO;
import org.ideoholic.curium.model.stampfees.dto.Academicfeesstructure;
import org.ideoholic.curium.model.std.action.StandardActionAdapter;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.model.student.dto.Studentfeesstructure;
import org.ideoholic.curium.model.user.dao.UserDAO;
import org.ideoholic.curium.model.user.dto.Login;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.DateUtil;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

public class StudentServiceArchive {
    private StandardActionAdapter standardActionAdapter;

    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession httpSession;
    private String BRANCHID = "branchid";
    private String USERID = "userloginid";
    private String CURRENTACADEMICYEAR = "currentAcademicYear";
    private StringBuilder optional = new StringBuilder();
    private StringBuilder compulsory = new StringBuilder();

    public boolean addStudent(MultipartFile[] listOfFiles){

        Student student = new Student();
        Parents parents = new Parents();
        Pudetails puDetails = new Pudetails();
        Degreedetails degreeDetails = new Degreedetails();
        String addClass = null,addSec =null,addClassE=null,addSecE=null,conClassStudying = null,conClassAdmittedIn=null;
        boolean result=false;

        try {
            Enumeration<String> enumeration = request.getParameterNames();

            while (enumeration.hasMoreElements()) {
                // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
                String fieldName = enumeration.nextElement();

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

                if (fieldName.equalsIgnoreCase("addclass")) {

                    addClass = DataUtil.emptyString(request.getParameter(fieldName));
                    if (!addClass.equalsIgnoreCase("")) {
                        conClassStudying = addClass+"--";

                    }
                }

                if (fieldName.equalsIgnoreCase("addsec")) {

                    addSec = DataUtil.emptyString(request.getParameter(fieldName));
                    if (!addSec.equalsIgnoreCase("")) {
                        conClassStudying = conClassStudying+addSec;
                    }
                }
                student.setClassstudying(DataUtil.emptyString(conClassStudying));

                if (fieldName.equalsIgnoreCase("admclassE")) {

                    addClassE = DataUtil.emptyString(request.getParameter(fieldName));

                    if (!addClassE.equalsIgnoreCase("")) {
                        conClassAdmittedIn = addClassE+"--";
                    }
                }

                if (fieldName.equalsIgnoreCase("admsecE")) {

                    addSecE = DataUtil.emptyString(request.getParameter(fieldName));
                    if (!addSecE.equalsIgnoreCase("")) {
                        conClassAdmittedIn = conClassAdmittedIn+addSecE;
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
                if (fieldName.equalsIgnoreCase("crecord")) {
                    student.setCrecord(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("crecorddate")) {
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
                // @UI 'core subjects studied'
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
                //@UI 'Was in receipt of any scholarship'
                if (fieldName.equalsIgnoreCase("belongtobpl")) {
                    student.setBelongtobpl(DataUtil.parseInt(request.getParameter(fieldName)));
                }
                //@UI 'Adhar card no'
                if (fieldName.equalsIgnoreCase("bplcardno")) {
                    student.setBplcardno(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                //@UI 'Whether Vaccinated'
                if (fieldName.equalsIgnoreCase("bhagyalakshmibondnumber")) {
                    student.setBhagyalakshmibondnumber(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                //@UI 'Marks of Identification on Pupil's body'
                if (fieldName.equalsIgnoreCase("disabilitychild")) {
                    student.setDisabilitychild(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("specialcategory")) {
                    student.setSpecialcategory(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("sts")) {
                    student.setSts(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("rte")) {
                    student.setRte(DataUtil.parseInt(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("yearofadmission")) {
                    student.setYearofadmission(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                // PU Details
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
                if (fieldName.equalsIgnoreCase("Xsecondlanguage")) {
                    puDetails.setSecondlanguage(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("aggmarks")) {
                    puDetails.setAggregatemarkssslc(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("arts1")) {
                    if(!DataUtil.emptyString(request.getParameter(fieldName)).isEmpty()) {
                        optional.append(DataUtil.emptyString(request.getParameter(fieldName))+"-");
                    }
                }
                if (fieldName.equalsIgnoreCase("arts2")) {
                    if(!DataUtil.emptyString(request.getParameter(fieldName)).isEmpty()) {
                        compulsory.append(DataUtil.emptyString(request.getParameter(fieldName))+"-");
                    }
                }
                if (fieldName.equalsIgnoreCase("science1")) {

                    if(!DataUtil.emptyString(request.getParameter(fieldName)).isEmpty()) {
                        optional.append(DataUtil.emptyString(request.getParameter(fieldName))+"-");
                    }

                }
                if (fieldName.equalsIgnoreCase("science2")) {
                    if(!DataUtil.emptyString(request.getParameter(fieldName)).isEmpty()) {
                        compulsory.append(DataUtil.emptyString(request.getParameter(fieldName))+"-");
                    }

                }
                if (fieldName.equalsIgnoreCase("Xmediuminstruction")) {
                    puDetails.setSslcmediuminstruction(DataUtil.emptyString(request.getParameter(fieldName)));

                }
                if (fieldName.equalsIgnoreCase("PUmediuminstruction")) {
                    puDetails.setPumediuminstruction(DataUtil.emptyString(request.getParameter(fieldName)));

                }
                // End PU Details

                if (fieldName.equalsIgnoreCase("languagesstudied")) {
                    student.setLanguagesstudied(DataUtil.emptyString(request.getParameter(fieldName)));

                }
                if (fieldName.equalsIgnoreCase("mediumofinstructionlastschool")) {
                    student.setInstructionmediumlastschool(DataUtil.emptyString(request.getParameter(fieldName)));

                }
                if (fieldName.equalsIgnoreCase("fathersname")) {
                    parents.setFathersname(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("mothersname")) {
                    parents.setMothersname(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("profession")) {
                    parents.setProfession(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("parentsannualincome")) {
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
                if (fieldName.equalsIgnoreCase("contactnumber")) {
                    parents.setContactnumber(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("cocontactnumber")) {
                    parents.setCocontactnumber(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("email")) {
                    parents.setEmail(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("fathersqualification")) {
                    parents.setFathersqualification(DataUtil.emptyString(request.getParameter(fieldName)));

                }
                if (fieldName.equalsIgnoreCase("mothersqualification")) {
                    parents.setMothersqualification(DataUtil.emptyString(request.getParameter(fieldName)));

                }
                //@UI 'Fathers Occupation'
                if(fieldName.equalsIgnoreCase("fatherscastecertno")){
                    parents.setFatherscastecertno(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                //@UI 'Mothers Occupation'
                if(fieldName.equalsIgnoreCase("motherscastecertno")){
                    parents.setMotherscastecertno(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if(fieldName.equalsIgnoreCase("fatherscaste")){
                    parents.setFatherscaste(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if(fieldName.equalsIgnoreCase("motherscaste")){
                    parents.setMotherscaste(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("remarksadditional")) {
                    parents.setRemarks(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                // Adding Degree Details
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
                    degreeDetails.setSubjectsqualifingexampartone(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("qparttwo")) {
                    degreeDetails.setSubjectsqualifingexamparttwo(DataUtil.emptyString(request.getParameter(fieldName)));
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

                //End Degree Details

                //Bank Details
                if (fieldName.equalsIgnoreCase("bankname")) {
                    student.setBankname(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("bankifsc")) {
                    student.setBankifsc(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                if (fieldName.equalsIgnoreCase("accno")) {
                    student.setAccno(DataUtil.emptyString(request.getParameter(fieldName)));
                }
                //End Bank Details
            }
            // Process form file field (input type="file")
            if(listOfFiles != null && listOfFiles.length != 0)
            {

                MultipartFile fileItem1 = listOfFiles[0];
                String fileName1 = (DataUtil.emptyString(fileItem1.getOriginalFilename()));

                if (!fileName1.equalsIgnoreCase("")) {
                    byte[]   bytesEncoded = Base64.encodeBase64(fileItem1.getBytes());
                    String saveFile = new String(bytesEncoded);
                    student.setStudentpic(saveFile);
                }

                //Student Docs
                MultipartFile fileItem2 = listOfFiles[1];
                String fileName2 = (DataUtil.emptyString(fileItem2.getOriginalFilename()));

                if (!fileName2.equalsIgnoreCase("")) {
                    // encode data on your side using BASE64
                    byte[]   bytesEncoded = Base64.encodeBase64(fileItem2.getBytes());
                    String saveFile = new String(bytesEncoded);
                    student.setStudentdoc1(saveFile);

                }

                MultipartFile fileItem3 = listOfFiles[2];
                String fileName3 = (DataUtil.emptyString(fileItem3.getOriginalFilename()));

                if (!fileName3.equalsIgnoreCase("")) {
                    // encode data on your side using BASE64
                    byte[]   bytesEncoded = Base64.encodeBase64(fileItem3.getBytes());
                    String saveFile = new String(bytesEncoded);
                    student.setStudentdoc2(saveFile);

                }


                MultipartFile fileItem4 = listOfFiles[3];
                String fileName4 = (DataUtil.emptyString(fileItem4.getOriginalFilename()));

                if (!fileName4.equalsIgnoreCase("")) {
                    // encode data on your side using BASE64
                    byte[]   bytesEncoded = Base64.encodeBase64(fileItem4.getBytes());
                    String saveFile = new String(bytesEncoded);
                    student.setStudentdoc3(saveFile);

                }

                MultipartFile fileItem5 = listOfFiles[4];
                String fileName5 = (DataUtil.emptyString(fileItem5.getOriginalFilename()));
                if (!fileName5.equalsIgnoreCase("")) {
                    // encode data on your side using BASE64
                    byte[]   bytesEncoded = Base64.encodeBase64(fileItem5.getBytes());
                    String saveFile = new String(bytesEncoded);
                    student.setStudentdoc4(saveFile);

                }

                MultipartFile fileItem6 = listOfFiles[5];

                String fileName6 = (DataUtil.emptyString(fileItem6.getOriginalFilename()));
                if (!fileName6.equalsIgnoreCase("")) {
                    // encode data on your side using BASE64
                    byte[]   bytesEncoded = Base64.encodeBase64(fileItem6.getBytes());
                    String saveFile = new String(bytesEncoded);
                    student.setStudentdoc5(saveFile);
                }

                //End Student Docs
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        student.setArchive(0);
        student.setPassedout(0);
        student.setDroppedout(0);
        student.setLeftout(0);
        //DataUtil.generateString(5)
        student.setStudentexternalid(httpSession.getAttribute("branchcode").toString());
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

        if(parents!=null){
            result=true;
            String[] yearofAdmission = parents.getStudent().getYearofadmission().split("/");
            String[] currentAcademicYear = httpSession.getAttribute("currentAcademicYear").toString().split("/");
            String setYear = null;
            int yoa = Integer.parseInt(yearofAdmission[0]);
            int ca = Integer.parseInt(currentAcademicYear[0]);

            if(yoa == ca || yoa < ca) {
                setYear = httpSession.getAttribute("currentAcademicYear").toString();
            }else if (yoa > ca) {
                setYear = request.getParameter("yearofadmission");
            }

            stampFees(parents.getStudent().getSid(),setYear);
            createParentLogin(parents.getStudent().getStudentexternalid(),parents.getContactnumber(),parents.getBranchid());
        }

        return result;

    }
    private void createParentLogin(String studentexternalid, String contactnumber, int branchid) {
        // TODO Auto-generated method stub
        Login login= new Login();
        Branch branch = new Branch();
        login.setUsername(studentexternalid);
        login.setPassword(contactnumber);
        branch.setIdbranch(branchid);
        login.setBranch(branch);
        login.setUsertype("parents");
        new UserDAO().addUser(login);
    }


    private void stampFees(Integer stdIds, String setYear) {

        if(httpSession.getAttribute(CURRENTACADEMICYEAR)!=null){
            String[] feesCategoryIds = request.getParameterValues("feescategory");
            if(feesCategoryIds!=null) {

                String[] studentIds = {stdIds.toString()};
                if(studentIds!=null){
                    Academicfeesstructure academicfessstructure = new Academicfeesstructure();
                    List<Academicfeesstructure> listOfacademicfessstructure = new ArrayList<Academicfeesstructure>();
                    List<Studentfeesstructure> listOfstudentfeesstructure = new ArrayList<Studentfeesstructure>();

                    String feesTotalAmount = request.getParameter("feesTotalAmount");
                    Long grandTotal = 0l;

                    String[] feesAmount = request.getParameterValues("fessCat");
                    String[] concession = request.getParameterValues("feesConcession");
                    String[] totalInstallments = request.getParameterValues("feesCount");

                    List<Integer> ids = new ArrayList();
                    listOfacademicfessstructure.clear();
                    for (String id : studentIds) {
                        System.out.println("id" + id);
                        academicfessstructure = new Academicfeesstructure();
                        academicfessstructure.setSid(Integer.valueOf(id));
                        academicfessstructure.setAcademicyear(setYear);
                        academicfessstructure.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
                        academicfessstructure.setTotalfees(feesTotalAmount);
                        grandTotal = grandTotal + Long.parseLong(academicfessstructure.getTotalfees());
                        academicfessstructure.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
                        academicfessstructure.setUserid(Integer.parseInt(httpSession.getAttribute("userloginid").toString()));

                        listOfacademicfessstructure.add(academicfessstructure);
                        // ids.add(Integer.valueOf(id));

                    }

                    for (String id : studentIds) {

                        for(int i=0; i < feesCategoryIds.length ; i++){
                            String[] feesCategoryIdsdiv = 	feesCategoryIds[i].split("--");

                            Studentfeesstructure studentfeesstructure = new Studentfeesstructure();
                            Feescategory feescategory = new Feescategory();
                            studentfeesstructure.setSid(Integer.valueOf(id));
                            feescategory.setIdfeescategory(Integer.parseInt(feesCategoryIdsdiv[0]));
                            studentfeesstructure.setFeescategory(feescategory);
                            studentfeesstructure.setFeesamount(Long.parseLong(feesAmount[Integer.parseInt(feesCategoryIdsdiv[1])]));
                            studentfeesstructure.setFeespaid((long) 0);
                            studentfeesstructure.setWaiveoff((long) 0);
                            studentfeesstructure.setTotalinstallment(Integer.parseInt(totalInstallments[Integer.parseInt(feesCategoryIdsdiv[1])]));
                            studentfeesstructure.setAcademicyear(setYear);
                            studentfeesstructure.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
                            studentfeesstructure.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
                            studentfeesstructure.setConcession(Integer.parseInt(concession[Integer.parseInt(feesCategoryIdsdiv[1])]));
                            listOfstudentfeesstructure.add(studentfeesstructure);
                        }



                    }

                    //Accounts
                    //Pass J.V. : credit the Fees as income & debit the cash

                    int crFees = getLedgerAccountId("unearnedstudentfeesincome"+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
                    int drAccount = getLedgerAccountId("studentfeesreceivable"+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));;

                    VoucherEntrytransactions transactions = new VoucherEntrytransactions();

                    transactions.setDraccountid(drAccount);
                    transactions.setCraccountid(crFees);
                    transactions.setDramount(new BigDecimal(grandTotal));
                    transactions.setCramount(new BigDecimal(grandTotal));
                    transactions.setVouchertype(4);
                    transactions.setTransactiondate(DateUtil.todaysDate());
                    transactions.setEntrydate(DateUtil.todaysDate());
                    transactions.setNarration("Towards Fees Stamp");
                    transactions.setCancelvoucher("no");
                    transactions.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())).getFinancialid());
                    transactions.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
                    transactions.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));

                    String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+grandTotal+" where accountdetailsid="+drAccount;

                    String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+grandTotal+" where accountdetailsid="+crFees;

                    // End J.V
                    new StampFeesDAO().addStampFees(listOfacademicfessstructure,httpSession.getAttribute(CURRENTACADEMICYEAR).toString(),listOfstudentfeesstructure,transactions,updateDrAccount,updateCrAccount);
                    //new studentDetailsDAO().addStudentfeesstructure(listOfstudentfeesstructure,httpSession.getAttribute(CURRENTACADEMICYEAR).toString());

                }
            }
        }
    }

    private int getLedgerAccountId(String itemAccount) {

        int result = 0;

        Properties properties = new Properties();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("Util.properties");

        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String ItemLedgerId = properties.getProperty(itemAccount);

        if(ItemLedgerId!=null) {
            result = Integer.parseInt(ItemLedgerId);
        }else {
            String ItemLedger = properties.getProperty(itemAccount.toLowerCase());
            result = Integer.parseInt(ItemLedger.toLowerCase());
        }

        return result;
    }
}
