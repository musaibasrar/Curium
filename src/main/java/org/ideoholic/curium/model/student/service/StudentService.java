package org.ideoholic.curium.model.student.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ideoholic.curium.model.academicyear.dao.YearDAO;
import org.ideoholic.curium.model.academicyear.dto.Currentacademicyear;
import org.ideoholic.curium.model.account.dao.AccountDAO;
import org.ideoholic.curium.model.account.dto.VoucherEntrytransactions;
import org.ideoholic.curium.model.degreedetails.dto.Degreedetails;
import org.ideoholic.curium.model.feescategory.dto.Feescategory;
import org.ideoholic.curium.model.feescollection.dao.feesCollectionDAO;
import org.ideoholic.curium.model.feescollection.dto.Receiptinfo;
import org.ideoholic.curium.model.parents.dao.parentsDetailsDAO;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.pudetails.dto.Pudetails;
import org.ideoholic.curium.model.stampfees.dao.StampFeesDAO;
import org.ideoholic.curium.model.stampfees.dto.Academicfeesstructure;
import org.ideoholic.curium.model.std.dto.Classsec;
import org.ideoholic.curium.model.std.service.StandardService;
import org.ideoholic.curium.model.student.dao.studentDetailsDAO;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.model.student.dto.Studentfeesstructure;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.DateUtil;
import org.springframework.web.multipart.MultipartFile;

public class StudentService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;
	private String BRANCHID = "branchid";
	private String USERID = "userloginid";
	//current academic year
	private String CURRENTACADEMICYEAR = "currentAcademicYear";
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

	public boolean addStudent(MultipartFile[] listOfFiles) {
	    
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
		                //fee category
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
		puDetails.setcompulsorysubjects(compulsory.toString());
		student.setPudetails(puDetails);
		student.setDegreedetails(degreeDetails);
		parents.setStudent(student);
		parents.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		parents.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
				
		parents = new parentsDetailsDAO().create(parents);
		
		
		//

		if(parents!=null){
			result=true;
			
			stampFees(parents.getStudent().getSid());
		}

		return result;

	}
	
private void stampFees(Integer stdIds) {
	
	if(httpSession.getAttribute(CURRENTACADEMICYEAR)!=null){
	String[] studentIds = {stdIds.toString()};
	if(studentIds!=null){
	Academicfeesstructure academicfessstructure = new Academicfeesstructure();
	List<Academicfeesstructure> listOfacademicfessstructure = new ArrayList<Academicfeesstructure>();
	List<Studentfeesstructure> listOfstudentfeesstructure = new ArrayList<Studentfeesstructure>();
	
	String feesTotalAmount = request.getParameter("feesTotalAmount");
	Long grandTotal = 0l;

	String[] feesCategoryIds = request.getParameterValues("feesIDS");
	String[] feesAmount = request.getParameterValues("fessCat");
	String[] concession = request.getParameterValues("feesConcession");
	String[] totalInstallments = request.getParameterValues("feesCount");
	
	List<Integer> ids = new ArrayList();
	listOfacademicfessstructure.clear();
	for (String id : studentIds) {
		System.out.println("id" + id);
		academicfessstructure = new Academicfeesstructure();
		academicfessstructure.setSid(Integer.valueOf(id));
		academicfessstructure.setAcademicyear(httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
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
		
		Studentfeesstructure studentfeesstructure = new Studentfeesstructure();   
		Feescategory feescategory = new Feescategory();
		studentfeesstructure.setSid(Integer.valueOf(id));
		feescategory.setIdfeescategory(Integer.parseInt(feesCategoryIds[i]));
		studentfeesstructure.setFeescategory(feescategory);
		studentfeesstructure.setFeesamount(Long.parseLong(feesAmount[i]));
		studentfeesstructure.setFeespaid((long) 0);
		studentfeesstructure.setWaiveoff((long) 0);
		studentfeesstructure.setTotalinstallment(Integer.parseInt(totalInstallments[i]));
		studentfeesstructure.setAcademicyear(httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
		studentfeesstructure.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		studentfeesstructure.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
		studentfeesstructure.setConcession(Integer.parseInt(concession[i]));
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

//ledger accountid
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

	public boolean viewAllStudents() {

		boolean result = false;
		String pages = "1";
		try {
			int page = 1;
			int recordsPerPage = 2000;
			if (pages != null) {
				page = Integer.parseInt(pages);
			}

			List<Parents> list = new studentDetailsDAO().readListOfObjectsPaginationALL((page - 1) * recordsPerPage,
					recordsPerPage, Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			int noOfRecords = new studentDetailsDAO().getNoOfRecords(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
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
			httpSession.setAttribute("currentyearfromservice",currentYear.getCurrentacademicyear());
			
			//List<Feesdetails> feesdetails = new feesDetailsDAO().readList(id, currentYear.getCurrentacademicyear());
			//httpSession.setAttribute("feesdetailsfromservice",feesdetails);
			List<Receiptinfo> rinfo = new feesCollectionDAO().getReceiptDetailsPerStudent(id,currentYear.getCurrentacademicyear());
			request.setAttribute("receiptinfo",rinfo);
			List<Studentfeesstructure> feesstructure = new studentDetailsDAO().getStudentFeesStructure(id, currentYear.getCurrentacademicyear());
			
			long totalSum = 0l;
			for (Receiptinfo receiptInfoSingle : rinfo) {
				totalSum = totalSum + receiptInfoSingle.getTotalamount();
			}
			
			long totalFeesAmount = 0l;
			long totalFeesConcession = 0l;
			for (Studentfeesstructure studentfeesstructureSingle : feesstructure) {
				totalFeesAmount = totalFeesAmount+studentfeesstructureSingle.getFeesamount()-studentfeesstructureSingle.getWaiveoff()-studentfeesstructureSingle.getConcession();
				totalFeesConcession = totalFeesConcession+studentfeesstructureSingle.getConcession();
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
					if(classParts.length>1) {
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
					if(classAdmittedParts.length>1) {
						request.setAttribute("secadm", classAdmittedParts[1]);
					}
					
				} else {
					request.setAttribute("classadm", classAdmitted);
					request.setAttribute("secadm", "");
				}

				httpSession.setAttribute("parents", parents);
				//httpSession.setAttribute("feesdetails", feesdetails);
				httpSession.setAttribute("feesstructure", feesstructure);
				httpSession.setAttribute("sumoffees", totalSum);
				httpSession.setAttribute("dueamount", totalFeesAmount-totalSum);
				httpSession.setAttribute("totalfees", totalFeesAmount);
				httpSession.setAttribute("academicPerYear", currentYear.getCurrentacademicyear());
				httpSession.setAttribute("currentAcademicYear", currentYear.getCurrentacademicyear());
				httpSession.setAttribute("totalfeesconcession", totalFeesConcession);
				result = true;
				httpSession.setAttribute("resultfromservice",result);
			}
			new StandardService(request, response).viewClasses();
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
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
		String addClass = null,addSec =null,addClassE=null,addSecE=null,conClassStudying=null,conClassAdmittedIn=null;
		String studentPicUpdate=null;
		String studentpicdelete=null;
		String studentDoc1Update=null;
		String studentDoc2Update=null;
		String studentDoc3Update=null;
		String studentDoc4Update=null;
		String studentDoc5Update=null;
		String studentdoc1delete=null;
		String studentdoc2delete=null;
		String studentdoc3delete=null;
		String studentdoc4delete=null;
		String studentdoc5delete=null;
		String dropdowncateg=null;
		String newcateg=null;
		
		try{
			
			Enumeration<String> enumeration = request.getParameterNames();
	
			while (enumeration.hasMoreElements()) {
	                // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
	            	 String fieldName = enumeration.nextElement();
	               
	                System.out.println("field name is "+fieldName);
	                if (fieldName.equalsIgnoreCase("id")) {	                
	                id = DataUtil.emptyString(request.getParameter(fieldName));
	                
	                }
	                
	                if (fieldName.equalsIgnoreCase("idparents")) {	                
	        		pid = DataUtil.emptyString(request.getParameter(fieldName));
	                }
	                
	        		System.out.println("THE ID IS: " + id + "," + pid);
        		
	        		if(id!=""){
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
		        			conClassStudying = addClass+"--";

		        		}
	                }

	                

	                if (fieldName.equalsIgnoreCase("secstudying")) {

	                    addSec = DataUtil.emptyString(request.getParameter(fieldName));
	                    if (!addSec.equalsIgnoreCase("")) {
		        			conClassStudying = conClassStudying+addSec;
		        		}
	                }

	        		student.setClassstudying(DataUtil.emptyString(conClassStudying));
	                
	                
	                if (fieldName.equalsIgnoreCase("admclass")) {
	                    
	                    addClassE = DataUtil.emptyString(request.getParameter(fieldName));

	                    if (!addClassE.equalsIgnoreCase("")) {
		        			conClassAdmittedIn = addClassE+"--";

		        		}

	                }



	                if (fieldName.equalsIgnoreCase("admsec")) {

	                    
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
	                
	                if(fieldName.equalsIgnoreCase("studentpicupdate")){
	                	studentPicUpdate=DataUtil.emptyString(request.getParameter(fieldName));
	                }
	                
	                if(fieldName.equalsIgnoreCase("studentdoc1update")){
	                	studentDoc1Update=DataUtil.emptyString(request.getParameter(fieldName));
	                }

	                if(fieldName.equalsIgnoreCase("studentdoc2update")){
	                	studentDoc2Update=DataUtil.emptyString(request.getParameter(fieldName));
	                }

	                if(fieldName.equalsIgnoreCase("studentdoc3update")){
	                	studentDoc3Update=DataUtil.emptyString(request.getParameter(fieldName));
	                }

	                if(fieldName.equalsIgnoreCase("studentdoc4update")){
	                	studentDoc4Update=DataUtil.emptyString(request.getParameter(fieldName));
	                }

	                if(fieldName.equalsIgnoreCase("studentdoc5update")){
	                	studentDoc5Update=DataUtil.emptyString(request.getParameter(fieldName));
	                }

	                
	                if(fieldName.equalsIgnoreCase("studentexternalid")){
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
	                if(fieldName.equalsIgnoreCase("newcategory")) {
	                	newcateg = DataUtil.emptyString(request.getParameter(fieldName));
	                }
	                if (fieldName.equalsIgnoreCase("sts")) {
	                	student.setSts(DataUtil.emptyString(request.getParameter(fieldName)));
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
	                if (fieldName.equalsIgnoreCase("yearofadmission")) {
	                	student.setYearofadmission(DataUtil.emptyString(request.getParameter(fieldName)));
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
	                
	                if(fieldName.equalsIgnoreCase("email")){
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
                        if (fieldName.equalsIgnoreCase("Xsecondlanguage")) {
                            puDetails.setSecondlanguage(DataUtil.emptyString(request.getParameter(fieldName)));
                        }
                        if (fieldName.equalsIgnoreCase("aggmarks")) {
                            puDetails.setAggregatemarkssslc(DataUtil.emptyString(request.getParameter(fieldName)));
                        }
                        if (fieldName.equalsIgnoreCase("subjectspart1")) {
                            puDetails.setOptionalsubjects(DataUtil.emptyString(request.getParameter(fieldName)));
                        }
                        if (fieldName.equalsIgnoreCase("subjectspart2")) {
                            puDetails.setcompulsorysubjects(DataUtil.emptyString(request.getParameter(fieldName)));
                        }
                        if (fieldName.equalsIgnoreCase("Xmediuminstruction")) {
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
		                if(fieldName.equalsIgnoreCase("fatherscastecertno")){
		                	parents.setFatherscastecertno(DataUtil.emptyString(request.getParameter(fieldName)));
		                }
		                if(fieldName.equalsIgnoreCase("motherscastecertno")){
		                	parents.setMotherscastecertno(DataUtil.emptyString(request.getParameter(fieldName)));
		                }
		                if(fieldName.equalsIgnoreCase("fatherscaste")){
		                	parents.setFatherscaste(DataUtil.emptyString(request.getParameter(fieldName)));
		                }
		                if(fieldName.equalsIgnoreCase("motherscaste")){
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
		                
		                if (fieldName.equalsIgnoreCase("studentpicdelete")) {
		                	studentpicdelete=DataUtil.emptyString(request.getParameter(fieldName));
		                }

		                if (fieldName.equalsIgnoreCase("studentdoc1delete")) {
		                	studentdoc1delete=DataUtil.emptyString(request.getParameter(fieldName));
		                }

		                if (fieldName.equalsIgnoreCase("studentdoc2delete")) {
		                	studentdoc2delete=DataUtil.emptyString(request.getParameter(fieldName));
		                }

		                if (fieldName.equalsIgnoreCase("studentdoc3delete")) {
		                	studentdoc3delete=DataUtil.emptyString(request.getParameter(fieldName));
		                }

		                if (fieldName.equalsIgnoreCase("studentdoc4delete")) {
		                	studentdoc4delete=DataUtil.emptyString(request.getParameter(fieldName));
		                }

		                if (fieldName.equalsIgnoreCase("studentdoc5delete")) {
		                	studentdoc5delete=DataUtil.emptyString(request.getParameter(fieldName));
		                }
		                
	            } 
			
			
			if(listOfFiles != null && listOfFiles.length != 0){

					MultipartFile fileItem = listOfFiles[0];
					String fileName = (DataUtil.emptyString(fileItem.getOriginalFilename()));
					String fileValue = (DataUtil.emptyString(fileItem.getName()));

	                    if (!fileName.equalsIgnoreCase("")) {
	                    	// Resize the image
	                    	byte[]   bytesEncoded = Base64.encodeBase64(fileItem.getBytes());
	                    	System.out.println("ecncoded value is " + new String(bytesEncoded ));
	                    	String saveFile = new String(bytesEncoded);

	                    	student.setStudentpic(saveFile);

	                    } else{
	                    	
	                    	student.setStudentpic(studentPicUpdate);
	                    }

	                    	MultipartFile fileItem1 = listOfFiles[1];
	                    	String studentdoc1 = (DataUtil.emptyString(fileItem1.getOriginalFilename()));
	                    	
		                    if (!studentdoc1.equalsIgnoreCase("")) {
		                    	// Resize the image
		                    	byte[]   bytesEncoded = Base64.encodeBase64(fileItem1.getBytes());
		                    	System.out.println("ecncoded value is " + new String(bytesEncoded ));
		                    	String saveFile = new String(bytesEncoded);

		                    	student.setStudentdoc1(saveFile);

		                    } else if(studentdoc1delete!=null) {
		                    		student.setStudentdoc1(null);
		                    	}else {
		                    	student.setStudentdoc1(studentDoc1Update);
		                    	}


		                    MultipartFile fileItem2 = listOfFiles[2];
		                	String studentdoc2 = (DataUtil.emptyString(fileItem2.getOriginalFilename()));
		                    if (!studentdoc2.equalsIgnoreCase("")) {
		                    	// Resize the image
		                    	byte[]   bytesEncoded = Base64.encodeBase64(fileItem2.getBytes());
		                    	System.out.println("ecncoded value is " + new String(bytesEncoded ));
		                    	String saveFile = new String(bytesEncoded);

		                    	student.setStudentdoc2(saveFile);

		                    } else if(studentdoc2delete!=null) {
		                    		student.setStudentdoc2(null);
		                    	}else {
		                    	student.setStudentdoc2(studentDoc2Update);
		                    	}


		                    MultipartFile fileItem3 = listOfFiles[3];
		                	String studentdoc3 = (DataUtil.emptyString(fileItem3.getOriginalFilename()));
		                    if (!studentdoc3.equalsIgnoreCase("")) {
		                    	// Resize the image
		                    	byte[]   bytesEncoded = Base64.encodeBase64(fileItem3.getBytes());
		                    	System.out.println("ecncoded value is " + new String(bytesEncoded ));
		                    	String saveFile = new String(bytesEncoded);

		                    	student.setStudentdoc3(saveFile);

		                    } else if(studentdoc3delete!=null) {
		                    		student.setStudentdoc3(null);
		                    	}else {
		                    	student.setStudentdoc3(studentDoc3Update);
		                    	}

		                    MultipartFile fileItem4 = listOfFiles[4];
		                	String studentdoc4 = (DataUtil.emptyString(fileItem4.getOriginalFilename()));
		                    if (!studentdoc4.equalsIgnoreCase("")) {
		                    	// Resize the image
		                    	byte[]   bytesEncoded = Base64.encodeBase64(fileItem4.getBytes());
		                    	System.out.println("ecncoded value is " + new String(bytesEncoded ));
		                    	String saveFile = new String(bytesEncoded);

		                    	student.setStudentdoc4(saveFile);

		                    } else if(studentdoc4delete!=null) {
		                    		student.setStudentdoc4(null);
		                    	}else {
		                    	student.setStudentdoc4(studentDoc4Update);
		                    	}

		                    MultipartFile fileItem5 = listOfFiles[5];
		                	String studentdoc5 = (DataUtil.emptyString(fileItem5.getOriginalFilename()));
		                    if (!studentdoc5.equalsIgnoreCase("")) {
		                    	// Resize the image
		                    	byte[]   bytesEncoded = Base64.encodeBase64(fileItem5.getBytes());
		                    	System.out.println("ecncoded value is " + new String(bytesEncoded ));
		                    	String saveFile = new String(bytesEncoded);

		                    	student.setStudentdoc5(saveFile);

		                    } else if(studentdoc5delete!=null) {
		                    		student.setStudentdoc5(null);
		                    	}else {
		                    	student.setStudentdoc5(studentDoc5Update);
		                    	}
	            }
	
	} catch (Exception e) {
		e.printStackTrace();
	}
		if("".equalsIgnoreCase(newcateg)) {
			student.setSpecialcategory(dropdowncateg);
		}else {
			student.setSpecialcategory(newcateg);
		}
		 student.setArchive(0);
		 student.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		 student.setUserid(Integer.parseInt(httpSession.getAttribute("userloginid").toString()));
		 
		 if(puDetails.getIdpudetails()!=null) {
			 new studentDetailsDAO().updatePuDetails(puDetails);
	         student.setPudetails(puDetails);
		 }
	         
		 if(degreeDetails.getIddegreedetails()!=null) {
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
        return stId+"_"+branchId;
		
	}

	public boolean viewAllStudentsList() {

		boolean result = false;
		if(httpSession.getAttribute(BRANCHID)!=null){
			
			try {

				List<Student> list = new studentDetailsDAO().readListOfStudents(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
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
				if(iddetailsarray.length>1) {
					iddetails.add(Integer.valueOf(iddetailsarray[1]));
				}
			}
			new studentDetailsDAO().deleteMultiple(ids,iddetails);
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
			student.setClassstudying(request.getParameter("classstudying_"+id));
			studentList.add(student);
		}
		
		if (new studentDetailsDAO().promoteMultiple(studentList, classStudying, promotedYear, Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()))) {
			result = true;
		}
		return result;
	}

	public boolean viewAllStudentsParents() {

		boolean result = false;
		//String pages = "1";
		if(httpSession.getAttribute(BRANCHID)!=null){
			try {
				int page = 1;
				int recordsPerPage = 100;
					if (!"".equalsIgnoreCase(DataUtil.emptyString(request.getParameter("page")))) {
						page = Integer.parseInt(request.getParameter("page"));
					}

					/*
					 * List<Parents> list = new
					 * studentDetailsDAO().readListOfObjectsPaginationALL((page - 1) *
					 * recordsPerPage, recordsPerPage,
					 * Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
					 */
				
				List<Object[]> list = new studentDetailsDAO().readListOfObjectsPagination((page - 1) * recordsPerPage,
						recordsPerPage, Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
				
				List<Parents> parentDetails = new ArrayList<Parents>();
	            for(Object[] parentdetails: list){
	            	Parents parent = new Parents();
	            	Student student = new Student();
	                student.setSid((Integer)parentdetails[0]);
	                student.setStudentexternalid((String)parentdetails[1]);
	                student.setAdmissionnumber((String)parentdetails[2]);
	                student.setName((String)parentdetails[3]);
	                student.setClassstudying((String)parentdetails[4]);
	                parent.setFathersname((String)parentdetails[5]);
	                parent.setMothersname((String)parentdetails[6]);
	                parent.setStudent(student);
	                parentDetails.add(parent);
	            }
				
				int noOfRecords = new studentDetailsDAO().getNoOfRecords(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
				int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
				request.setAttribute("studentList", parentDetails);
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
                    
                    List<Receiptinfo> rinfo = new feesCollectionDAO().getReceiptDetailsPerStudent(id,academicYear);
                    request.setAttribute("receiptinfo",rinfo);
                    List<Studentfeesstructure> feesstructure = new studentDetailsDAO().getStudentFeesStructure(id, academicYear);
                    
                    long totalSum = 0l;
                    for (Receiptinfo receiptInfoSingle : rinfo) {
                            totalSum = totalSum + receiptInfoSingle.getTotalamount();
                    }
                    
                    long totalFeesAmount = 0l;
                    long totalFeesConcession = 0l;
                    for (Studentfeesstructure studentfeesstructureSingle : feesstructure) {
                            totalFeesAmount = totalFeesAmount+studentfeesstructureSingle.getFeesamount()-studentfeesstructureSingle.getWaiveoff()-studentfeesstructureSingle.getConcession();
                            totalFeesConcession = totalFeesConcession+studentfeesstructureSingle.getConcession();
                    }
                            httpSession.setAttribute("feesstructure", feesstructure);
                            httpSession.setAttribute("sumoffees", totalSum);
                            httpSession.setAttribute("dueamount", totalFeesAmount-totalSum);
                            httpSession.setAttribute("totalfees", totalFeesAmount);
                            httpSession.setAttribute("academicPerYear", academicYear);
                            httpSession.setAttribute("totalfeesconcession", totalFeesConcession);

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
					String queryMain = "From Parents as parents where parents.Student.branchid="+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())+" AND";
					String querySub = " parents.Student.id = "+id+" order by parents.Student.admissionnumber ASC";
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

	public boolean exportDataToExcel(List<Parents> listOfStudentRecords)
			throws Exception {

		boolean writeSucees = false;
		
		try {

			// Creating an excel file
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("ListOfStudents");
			Map<String, Object[]> data = new HashMap<String, Object[]>();
			Map<String, Object[]> headerData = new HashMap<String, Object[]>();
			headerData.put("Header",
					new Object[] { "Admission No.","STS","UID", "Student Name", "Gender", "Date Of Birth", "Age", "Studying In Class",
							"Admitted In Class", "Admission Date","Admission Year", "Promoted Year", "Blood Group", "Religion", "Student Aadhar Card",
							"Caste", "Fathers Name", "Mothers Name","Contact No.", "Archive", "Graduated", "Left Out", "Dropped Out"});
			int i = 1;
			for (Parents studentDetails : listOfStudentRecords) {
				data.put(Integer.toString(i),
						new Object[] { DataUtil.emptyString(studentDetails.getStudent().getAdmissionnumber()),
								DataUtil.emptyString(studentDetails.getStudent().getSts()),
								DataUtil.emptyString(studentDetails.getStudent().getStudentexternalid()),
								 DataUtil.emptyString(studentDetails.getStudent().getName()),  DataUtil.emptyString(studentDetails.getStudent().getGender()),
								 DateUtil.dateParserddMMYYYY(studentDetails.getStudent().getDateofbirth()),
								 DataUtil.emptyString(Integer.toString(studentDetails.getStudent().getAge())),
								 DataUtil.emptyString(studentDetails.getStudent().getClassstudying().replace("--", " ")),
								 DataUtil.emptyString(studentDetails.getStudent().getClassadmittedin().replace("--", " ")),
								 DateUtil.dateParserddMMYYYY(studentDetails.getStudent().getAdmissiondate()),
								 DataUtil.emptyString(studentDetails.getStudent().getYearofadmission()),DataUtil.emptyString(studentDetails.getStudent().getPromotedyear()),
								 DataUtil.emptyString(studentDetails.getStudent().getBloodgroup()),  DataUtil.emptyString(studentDetails.getStudent().getReligion()),
								 DataUtil.emptyString(studentDetails.getStudent().getDisabilitychild()),
								 DataUtil.emptyString(studentDetails.getStudent().getCaste()),  DataUtil.emptyString(studentDetails.getFathersname()),
								 DataUtil.emptyString(studentDetails.getMothersname()),DataUtil.emptyString(studentDetails.getContactnumber()),
								 
								 
								 studentDetails.getStudent().getArchive()==1 ? "Yes" : "No" , 
										 studentDetails.getStudent().getPassedout()==1 ? "Yes" : "No", studentDetails.getStudent().getLeftout()==1 ? "Yes" : "No",
												 studentDetails.getStudent().getDroppedout()==1 ? "Yes" : "No"});
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
				
				
				//ClassLoader classLoader = getClass().getClassLoader();
				//Local 
				//FileOutputStream out = new FileOutputStream("D:/schoolfiles/test.xlsx");
				//FileOutputStream out = new FileOutputStream(new File("/usr/local/tomcat/webapps/www.searchmysearch.com/musarpbiabha/studentsdetails.xlsx"));
				FileOutputStream out = new FileOutputStream(new File(System.getProperty("java.io.tmpdir")+"/studentsdetails.xlsx"));
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
		
		if(studentIds!=null){
			String getStudentInfo  = "from Parents as parents where parents.Student.sid="+studentIds[0];
			Parents parents = new studentDetailsDAO().getStudentRecords(getStudentInfo);
			httpSession.setAttribute("studentdetailsbonafide", parents);
			bonafidePage = "bonafidecertificateprint";
		}
		
		return bonafidePage;
	}

	public boolean downlaodFile() {
		boolean result = false;
		try {

			File downloadFile = new File(System.getProperty("java.io.tmpdir")+"/studentsdetails.xlsx");
	        FileInputStream inStream = new FileInputStream(downloadFile);

	        // get MIME type of the file
			String mimeType = "application/vnd.ms-excel";

			// set content attributes for the response
			response.setContentType(mimeType);
			// response.setContentLength((int) bis.length());

			// set headers for the response
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"",
					"studentdetails.xlsx");
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
        
        if(httpSession.getAttribute(BRANCHID)!=null){
            String branchId = httpSession.getAttribute(BRANCHID).toString();
            return "addStudent";
           /* if("1".equalsIgnoreCase(branchId) || "2".equalsIgnoreCase(branchId) || "3".equalsIgnoreCase(branchId)) {
                return "addStudent.jsp";
            }else if("4".equalsIgnoreCase(branchId)) {
                return "addStudentPU.jsp";
            }else if("5".equalsIgnoreCase(branchId)) {
                return "addStudentDC.jsp";
            }*/
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
