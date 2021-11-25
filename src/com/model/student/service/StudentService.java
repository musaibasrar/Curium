package com.model.student.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import com.model.academicyear.dao.YearDAO;
import com.model.academicyear.dto.Currentacademicyear;
import com.model.degreedetails.dto.Degreedetails;
import com.model.feescollection.dao.feesCollectionDAO;
import com.model.feescollection.dto.Receiptinfo;
import com.model.parents.dao.parentsDetailsDAO;
import com.model.parents.dto.Parents;
import com.model.pudetails.dto.Pudetails;
import com.model.std.dto.Classsec;
import com.model.std.service.StandardService;
import com.model.student.dao.studentDetailsDAO;
import com.model.student.dto.Student;
import com.model.student.dto.Studentfeesstructure;
import com.util.DataUtil;
import com.util.DateUtil;

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

	public boolean addStudent() {
	    
		Student student = new Student();
		Parents parents = new Parents();
		Pudetails puDetails = new Pudetails();
		Degreedetails degreeDetails = new Degreedetails();
		String addClass = null,addSec =null,addClassE=null,addSecE=null,conClassStudying = null,conClassAdmittedIn=null;
		boolean result=false;
		
		try {
			List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
		
			 for (FileItem item : items) {
		            if (item.isFormField()) {
		                // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
		                String fieldName = item.getFieldName();

		                if (fieldName.equalsIgnoreCase("name")) {
		                    
		                    student.setName(DataUtil.emptyString(item.getString()));
		                    System.out.println("name==" + item.getString());
		                }

		                
		                if (fieldName.equalsIgnoreCase("gender")) {
		                    
		                    student.setGender(DataUtil.emptyString(item.getString()));

		                }

		                if (fieldName.equalsIgnoreCase("dateofbirth")) {

		                	student.setDateofbirth(DateUtil.indiandateParser(item.getString()));

		                }

		                if (fieldName.equalsIgnoreCase("age")) {
		                    
		                    student.setAge(DataUtil.parseInt(item.getString()));

		                }

		                if (fieldName.equalsIgnoreCase("addclass")) {
		                    
		                    addClass = DataUtil.emptyString(item.getString());
		                    if (!addClass.equalsIgnoreCase("")) {
		                            conClassStudying = addClass+"--";

		                    }
		                }

		                if (fieldName.equalsIgnoreCase("addsec")) {

		                    addSec = DataUtil.emptyString(item.getString());
		                    if (!addSec.equalsIgnoreCase("")) {
			        			conClassStudying = conClassStudying+addSec;
			        		}
		                }
		        		student.setClassstudying(DataUtil.emptyString(conClassStudying));
		                
		                if (fieldName.equalsIgnoreCase("admclassE")) {
		                    
		                    addClassE = DataUtil.emptyString(item.getString());

		                    if (!addClassE.equalsIgnoreCase("")) {
			        			conClassAdmittedIn = addClassE+"--";
			        		}
		                }

		                if (fieldName.equalsIgnoreCase("admsecE")) {
		                    
		                    addSecE = DataUtil.emptyString(item.getString());
			        		if (!addSecE.equalsIgnoreCase("")) {
			        			conClassAdmittedIn = conClassAdmittedIn+addSecE;
			        		}
		                }
		              
		                student.setClassadmittedin(DataUtil.emptyString(conClassAdmittedIn));

		                if (fieldName.equalsIgnoreCase("lastclass")) {
		                    student.setStdlaststudied(DataUtil.emptyString(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("lastschool")) {
		                	student.setSchoollastattended(DataUtil.emptyString(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("admnno")) {
		                	student.setAdmissionnumber(DataUtil.emptyString(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("dateofadmission")) {
		                	student.setAdmissiondate(DateUtil.indiandateParser(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("bloodgroup")) {
		                    student.setBloodgroup(DataUtil.emptyString(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("nationality")) {
		                	student.setNationality(DataUtil.emptyString(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("religion")) {
		                    student.setReligion(DataUtil.emptyString(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("caste")) {
		                    student.setCaste(DataUtil.emptyString(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("motherT")) {
		                    student.setMothertongue(DataUtil.emptyString(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("createddate")) {
		                    student.setCreateddate(DateUtil.indiandateParser(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("remarks")) {
		                	student.setRemarks(DataUtil.emptyString(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("crecord")) {
		                	student.setCrecord(DataUtil.emptyString(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("dateofcr")) {
		                	student.setCrecorddate(DateUtil.indiandateParser(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("place")) {
		                	student.setPlaceofbirth(DataUtil.emptyString(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("tcno")) {
		                	student.setNooftc(DataUtil.parseInt(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("dateoftc")) {
		                	student.setDateoftc(DateUtil.indiandateParser(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("classonleaving")) {
		                	student.setClassonleaving(DataUtil.emptyString(item.getString()));
		                }
		                // @UI 'core subjects studied'
		                if (fieldName.equalsIgnoreCase("progress")) {
		                	student.setSubsequentprogress(DataUtil.emptyString(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("dateofleaving")) {
		                	student.setDateleaving(DateUtil.indiandateParser(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("reasonforleaving")) {
		                	student.setReasonleaving(DataUtil.emptyString(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("notcissued")) {
		                	student.setNotcissued(DataUtil.parseInt(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("dateoftcissued")) {
		                	student.setDatetcissued(DateUtil.indiandateParser(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("guardian")) {
		                	student.setGuardiandetails(DataUtil.emptyString(item.getString()));
		                }
		                
		                if (fieldName.equalsIgnoreCase("semester")) {
		                	student.setSemester(DataUtil.parseInt(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("stream")) {
		                	student.setStream(DataUtil.emptyString(item.getString()));
		                }		                
		                if (fieldName.equalsIgnoreCase("mediumofinstruction")) {
		                	student.setMediumofinstruction(DataUtil.emptyString(item.getString()));
		                }	
		                if (fieldName.equalsIgnoreCase("previousschooltype")) {
		                	student.setPreviousschooltype(DataUtil.emptyString(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("previouschooladdress")) {
		                	student.setPreviouschooladdress(DataUtil.emptyString(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("urbanrural")) {
		                	student.setUrbanrural(DataUtil.emptyString(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("studentscastecertno")) {
		                	student.setStudentscastecertno(DataUtil.emptyString(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("studentscaste")) {
		                	student.setStudentscaste(DataUtil.emptyString(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("socialcategory")) {
		                	student.setSocialcategory(DataUtil.emptyString(item.getString()));
		                }
		                //@UI 'Was in receipt of any scholarship'
		                if (fieldName.equalsIgnoreCase("belongtobpl")) {
		                	student.setBelongtobpl(DataUtil.parseInt(item.getString()));
		                }
		                //@UI 'Adhar card no'
		                if (fieldName.equalsIgnoreCase("bplcardno")) {
		                	student.setBplcardno(DataUtil.emptyString(item.getString()));
		                }
		                //@UI 'Whether Vaccinated'
		                if (fieldName.equalsIgnoreCase("bhagyalakshmibondnumber")) {
		                	student.setBhagyalakshmibondnumber(DataUtil.emptyString(item.getString()));
		                }
		                //@UI 'Marks of Identification on Pupil's body'
		                if (fieldName.equalsIgnoreCase("disabilitychild")) {
		                	student.setDisabilitychild(DataUtil.emptyString(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("specialcategory")) {
		                	student.setSpecialcategory(DataUtil.emptyString(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("sts")) {
		                	student.setSts(DataUtil.parseInt(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("rte")) {
		                	student.setRte(DataUtil.parseInt(item.getString()));
		                }
		                // PU Details
		                if (fieldName.equalsIgnoreCase("pep")) {
                                    puDetails.setExampassedappearance(DataUtil.parseInt(item.getString()));
                                }
		                if (fieldName.equalsIgnoreCase("passedyear")) {
                                    puDetails.setExampassedyear(DataUtil.emptyString(item.getString()));
                                }
		                if (fieldName.equalsIgnoreCase("regno")) {
                                    puDetails.setExampassedregno(DataUtil.emptyString(item.getString()));
                                }
		                if (fieldName.equalsIgnoreCase("resultclass")) {
                                    puDetails.setExampassedresultwithclass(DataUtil.emptyString(item.getString()));
                                }
		                if (fieldName.equalsIgnoreCase("Xsecondlanguage")) {
                                    puDetails.setSecondlanguage(DataUtil.emptyString(item.getString()));
                                }
		                if (fieldName.equalsIgnoreCase("aggmarks")) {
                                    puDetails.setAggregatemarkssslc(DataUtil.emptyString(item.getString()));
                                }
		                if (fieldName.equalsIgnoreCase("arts1")) {
		                    if(!DataUtil.emptyString(item.getString()).isEmpty()) {
		                        optional.append(DataUtil.emptyString(item.getString())+"-");
		                    }
                                }
		                if (fieldName.equalsIgnoreCase("arts2")) {
                                    if(!DataUtil.emptyString(item.getString()).isEmpty()) {
                                        compulsory.append(DataUtil.emptyString(item.getString())+"-");
                                    }
                                }
		                if (fieldName.equalsIgnoreCase("science1")) {
		                    
                                    if(!DataUtil.emptyString(item.getString()).isEmpty()) {
                                        optional.append(DataUtil.emptyString(item.getString())+"-");
                                    }
                                    
                                }
		                if (fieldName.equalsIgnoreCase("science2")) {
                                    if(!DataUtil.emptyString(item.getString()).isEmpty()) {
                                        compulsory.append(DataUtil.emptyString(item.getString())+"-");
                                    }
                                   
                                }
		                if (fieldName.equalsIgnoreCase("Xmediuminstruction")) {
		                    puDetails.setSslcmediuminstruction(DataUtil.emptyString(item.getString()));
                                   
                                }
		                if (fieldName.equalsIgnoreCase("PUmediuminstruction")) {
		                    puDetails.setPumediuminstruction(DataUtil.emptyString(item.getString()));
                                   
                                }
		                // End PU Details
		                
		                if (fieldName.equalsIgnoreCase("languagesstudied")) {
                                    student.setLanguagesstudied(DataUtil.emptyString(item.getString()));
                                   
                                }
		                if (fieldName.equalsIgnoreCase("mediumofinstructionlastschool")) {
		                    student.setInstructionmediumlastschool(DataUtil.emptyString(item.getString()));
                                   
                                }
		                if (fieldName.equalsIgnoreCase("fathersname")) {
                                    parents.setFathersname(DataUtil.emptyString(item.getString()));
                                }
                                if (fieldName.equalsIgnoreCase("mothersname")) {
                                        parents.setMothersname(DataUtil.emptyString(item.getString()));
                                }
                                if (fieldName.equalsIgnoreCase("profession")) {
                                    parents.setProfesssion(DataUtil.emptyString(item.getString()));
                                }
                                if (fieldName.equalsIgnoreCase("parentsannualincome")) {
                                        parents.setParentsannualincome(DataUtil.emptyString(item.getString()));
                                }
                                if (fieldName.equalsIgnoreCase("permanentaddress")) {
                                    parents.setAddresspermanent(DataUtil.emptyString(item.getString()));
                                }
                                if (fieldName.equalsIgnoreCase("temporaryaddress")) {
                                    parents.setAddresstemporary(DataUtil.emptyString(item.getString()));
                                }
                                if (fieldName.equalsIgnoreCase("noofdependents")) {
                                        parents.setNoofdependents(DataUtil.parseInt(item.getString()));
                                }
                                if (fieldName.equalsIgnoreCase("contactnumber")) {
                                        parents.setContactnumber(DataUtil.emptyString(item.getString()));
                                }
                                if (fieldName.equalsIgnoreCase("cocontactnumber")) {
                                        parents.setCocontactnumber(DataUtil.emptyString(item.getString()));
                                }
                                if (fieldName.equalsIgnoreCase("email")) {
                                        parents.setEmail(DataUtil.emptyString(item.getString()));
                                }
		                if (fieldName.equalsIgnoreCase("fathersqualification")) {
		                    parents.setFathersqualification(DataUtil.emptyString(item.getString()));
                                   
                                }
		                if (fieldName.equalsIgnoreCase("mothersqualification")) {
		                    parents.setMothersqualification(DataUtil.emptyString(item.getString()));
                                   
                                }
		                //@UI 'Fathers Occupation'
		                if(fieldName.equalsIgnoreCase("fatherscastecertno")){
		                	parents.setFatherscastecertno(DataUtil.emptyString(item.getString()));
		                }
		              //@UI 'Mothers Occupation'
		                if(fieldName.equalsIgnoreCase("motherscastecertno")){
		                	parents.setMotherscastecertno(DataUtil.emptyString(item.getString()));
		                }
		                if(fieldName.equalsIgnoreCase("fatherscaste")){
		                	parents.setFatherscaste(DataUtil.emptyString(item.getString()));
		                }
		                if(fieldName.equalsIgnoreCase("motherscaste")){
		                	parents.setMotherscaste(DataUtil.emptyString(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("remarksadditional")) {
		                	parents.setRemarks(DataUtil.emptyString(item.getString()));
		                }
		                // Adding Degree Details
		                if (fieldName.equalsIgnoreCase("pepdc")) {
                                    degreeDetails.setExampassedappearance(DataUtil.parseInt(item.getString()));
                                }
		                if (fieldName.equalsIgnoreCase("passedyeardc")) {
                                    degreeDetails.setExampassedyear(DataUtil.emptyString(item.getString()));
                                }
		                if (fieldName.equalsIgnoreCase("regnodc")) {
                                    degreeDetails.setExampassedregno(DataUtil.emptyString(item.getString()));
                                }
		                if (fieldName.equalsIgnoreCase("resultclassdc")) {
                                    degreeDetails.setExampassedresultwithclass(DataUtil.emptyString(item.getString()));
                                }
		                if (fieldName.equalsIgnoreCase("mediumofinstructiondc")) {
                                    degreeDetails.setPumediuminstruction(DataUtil.emptyString(item.getString()));
                                }
		                if (fieldName.equalsIgnoreCase("qpartone")) {
                                    degreeDetails.setSubjectsqualifingexampartone(DataUtil.emptyString(item.getString()));
                                }
		                if (fieldName.equalsIgnoreCase("qparttwo")) {
                                    degreeDetails.setSubjectsqualifingexamparttwo(DataUtil.emptyString(item.getString()));
                                }
		                if (fieldName.equalsIgnoreCase("ppartone")) {
                                    degreeDetails.setSubjectsdegreecoursepartone(DataUtil.emptyString(item.getString()));
                                }
		                if (fieldName.equalsIgnoreCase("pparttwo")) {
                                    degreeDetails.setSubjectsdegreecourseparttwo(DataUtil.emptyString(item.getString()));
                                }
		                if (fieldName.equalsIgnoreCase("pumarkscard")) {
                                    degreeDetails.setPumarkscard(DataUtil.parseInt(item.getString()));
                                }
		                if (fieldName.equalsIgnoreCase("medicalreport")) {
                                    degreeDetails.setMedicalreport(DataUtil.parseInt(item.getString()));
                                }
		                if (fieldName.equalsIgnoreCase("incomecertificate")) {
                                    degreeDetails.setIncomecertificate(DataUtil.parseInt(item.getString()));
                                }
		                if (fieldName.equalsIgnoreCase("migrationcertificate")) {
                                    degreeDetails.setMigrationcertificate(DataUtil.parseInt(item.getString()));
                                }
		                if (fieldName.equalsIgnoreCase("originaltc")) {
                                    degreeDetails.setTransfercertificate(DataUtil.parseInt(item.getString()));
                                }
		                if (fieldName.equalsIgnoreCase("castecertificate")) {
                                    degreeDetails.setCastecertificate(DataUtil.parseInt(item.getString()));
                                }
		                if (fieldName.equalsIgnoreCase("games")) {
                                    degreeDetails.setProficiencysports(DataUtil.emptyString(item.getString()));
                                }
		                if (fieldName.equalsIgnoreCase("extracurricular")) {
                                    degreeDetails.setExtracurricular(DataUtil.emptyString(item.getString()));
                                }
		                if (fieldName.equalsIgnoreCase("employer")) {
                                    degreeDetails.setAreyouemployee(DataUtil.emptyString(item.getString()));
                                }
		                if (fieldName.equalsIgnoreCase("karnataka")) {
                                    degreeDetails.setKarnataka(DataUtil.parseInt(item.getString()));
                                }
		                
		                //End Degree Details
		                
		                //Bank Details
		                if (fieldName.equalsIgnoreCase("bankname")) {
		                	student.setBankname(DataUtil.emptyString(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("bankifsc")) {
		                	student.setBankifsc(DataUtil.emptyString(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("accno")) {
		                	student.setAccno(DataUtil.emptyString(item.getString()));
		                }
		                //End Bank Details
		                
		            } else {
		                // Process form file field (input type="file").
		                String fieldName = item.getFieldName();

		                if (fieldName.equalsIgnoreCase("fileToUpload")) {

		                    String fileName = (DataUtil.emptyString(item.getName()));
		                    String fileValue = (DataUtil.emptyString(item.getString()));
		                    if (!fileName.equalsIgnoreCase("")) {
		                    	// encode data on your side using BASE64
		                    	byte[]   bytesEncoded = Base64.encodeBase64(item.get());
		                    	String saveFile = new String(bytesEncoded);
		                    	student.setStudentpic(saveFile);

		                    } 
		                }
		            }
		        }
		} catch (FileUploadException e) {
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

		if(parents!=null){
			result=true;
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

			List<Student> list = new studentDetailsDAO().readListOfObjectsPagination((page - 1) * recordsPerPage,
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
		boolean result = false;
		try {
			long id = Long.parseLong(request.getParameter("id"));
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
			for (Studentfeesstructure studentfeesstructureSingle : feesstructure) {
				totalFeesAmount = totalFeesAmount+studentfeesstructureSingle.getFeesamount()-studentfeesstructureSingle.getWaiveoff();
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
					request.setAttribute("classstudying", classParts[0]);
					request.setAttribute("secstudying", "");
					if(classParts.length>1) {
						request.setAttribute("secstudying", classParts[1]);
					}
					
				} else {
					request.setAttribute("classstudying", classStudying);
					request.setAttribute("secstudying", "");
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

	public String updateStudent() {
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
		String dropdowncateg=null;
		String newcateg=null;
		
		try{
			
		List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
	
		 for (FileItem item : items) {
	            if (item.isFormField()) {
	                // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
	                String fieldName = item.getFieldName();
	               
	                System.out.println("field name is "+fieldName);
	                if (fieldName.equalsIgnoreCase("id")) {	                
	                id = DataUtil.emptyString(item.getString());
	                
	                }
	                
	                if (fieldName.equalsIgnoreCase("idparents")) {	                
	        		pid = DataUtil.emptyString(item.getString());
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
	                    
	                    student.setName(DataUtil.emptyString(item.getString()));
	                    System.out.println("name==" + item.getString());
	                }

	                
	                if (fieldName.equalsIgnoreCase("gender")) {
	                    
	                    student.setGender(DataUtil.emptyString(item.getString()));

	                }

	                if (fieldName.equalsIgnoreCase("dateofbirth")) {

	                	student.setDateofbirth(DateUtil.indiandateParser(item.getString()));

	                }

	                if (fieldName.equalsIgnoreCase("age")) {
	                    
	                    student.setAge(DataUtil.parseInt(item.getString()));

	                }

	                if (fieldName.equalsIgnoreCase("classsec")) {
	                    
	                    addClass = DataUtil.emptyString(item.getString());
	                    if (!addClass.equalsIgnoreCase("")) {
		        			conClassStudying = addClass+"--";

		        		}
	                }

	                

	                if (fieldName.equalsIgnoreCase("secstudying")) {

	                    addSec = DataUtil.emptyString(item.getString());
	                    if (!addSec.equalsIgnoreCase("")) {
		        			conClassStudying = conClassStudying+addSec;
		        		}
	                }

	        		student.setClassstudying(DataUtil.emptyString(conClassStudying));
	                
	                
	                if (fieldName.equalsIgnoreCase("admclass")) {
	                    
	                    addClassE = DataUtil.emptyString(item.getString());

	                    if (!addClassE.equalsIgnoreCase("")) {
		        			conClassAdmittedIn = addClassE+"--";

		        		}

	                }



	                if (fieldName.equalsIgnoreCase("admsec")) {

	                    
	                    addSecE = DataUtil.emptyString(item.getString());
		        		if (!addSecE.equalsIgnoreCase("")) {
		        			conClassAdmittedIn = conClassAdmittedIn+addSecE;
		        		}


	                }
	              
	                student.setClassadmittedin(DataUtil.emptyString(conClassAdmittedIn));



	                if (fieldName.equalsIgnoreCase("lastclass")) {
	                    student.setStdlaststudied(DataUtil.emptyString(item.getString()));
	                }


	                if (fieldName.equalsIgnoreCase("lastschool")) {
	                	student.setSchoollastattended(DataUtil.emptyString(item.getString()));
	                }


	                if (fieldName.equalsIgnoreCase("admnno")) {
	                	student.setAdmissionnumber(DataUtil.emptyString(item.getString()));
	                }

	                if (fieldName.equalsIgnoreCase("dateofadmission")) {
	                	student.setAdmissiondate(DateUtil.indiandateParser(item.getString()));
	                }


	                if (fieldName.equalsIgnoreCase("bloodgroup")) {
	                    student.setBloodgroup(DataUtil.emptyString(item.getString()));
	                }



	                if (fieldName.equalsIgnoreCase("nationality")) {
	                	student.setNationality(DataUtil.emptyString(item.getString()));
	                }


	                if (fieldName.equalsIgnoreCase("religion")) {
	                    student.setReligion(DataUtil.emptyString(item.getString()));

	                }

	                if (fieldName.equalsIgnoreCase("caste")) {
	                    student.setCaste(DataUtil.emptyString(item.getString()));
	                }

	                if (fieldName.equalsIgnoreCase("motherT")) {
	                    student.setMothertongue(DataUtil.emptyString(item.getString()));
	                }

	                if (fieldName.equalsIgnoreCase("createddate")) {
	                    student.setCreateddate(DateUtil.indiandateParser(item.getString()));
	                }

	                if (fieldName.equalsIgnoreCase("remarks")) {
	                	student.setRemarks(DataUtil.emptyString(item.getString()));
	                }
	                
	                if(fieldName.equalsIgnoreCase("studentpicupdate")){
	                	studentPicUpdate=DataUtil.emptyString(item.getString());
	                }
	                
	                if(fieldName.equalsIgnoreCase("studentexternalid")){
	                	student.setStudentexternalid(DataUtil.emptyString(item.getString()));
	                }
	                if (fieldName.equalsIgnoreCase("crecord")) {
	                	student.setCrecord(DataUtil.emptyString(item.getString()));
	                }
	                if (fieldName.equalsIgnoreCase("dateofcr")) {
	                	student.setCrecorddate(DateUtil.indiandateParser(item.getString()));
	                }
	                if (fieldName.equalsIgnoreCase("place")) {
	                	student.setPlaceofbirth(DataUtil.emptyString(item.getString()));
	                }
	                if (fieldName.equalsIgnoreCase("tcno")) {
	                	student.setNooftc(DataUtil.parseInt(item.getString()));
	                }
	                if (fieldName.equalsIgnoreCase("dateoftc")) {
	                	student.setDateoftc(DateUtil.indiandateParser(item.getString()));
	                }
	                if (fieldName.equalsIgnoreCase("classonleaving")) {
	                	student.setClassonleaving(DataUtil.emptyString(item.getString()));
	                }
	                if (fieldName.equalsIgnoreCase("progress")) {
	                	student.setSubsequentprogress(DataUtil.emptyString(item.getString()));
	                }
	                if (fieldName.equalsIgnoreCase("dateofleaving")) {
	                	student.setDateleaving(DateUtil.indiandateParser(item.getString()));
	                }
	                if (fieldName.equalsIgnoreCase("reasonforleaving")) {
	                	student.setReasonleaving(DataUtil.emptyString(item.getString()));
	                }
	                if (fieldName.equalsIgnoreCase("notcissued")) {
	                	student.setNotcissued(DataUtil.parseInt(item.getString()));
	                }
	                if (fieldName.equalsIgnoreCase("dateoftcissued")) {
	                	student.setDatetcissued(DateUtil.indiandateParser(item.getString()));
	                }
	                if (fieldName.equalsIgnoreCase("guardian")) {
	                	student.setGuardiandetails(DataUtil.emptyString(item.getString()));
	                }
	                
	                if (fieldName.equalsIgnoreCase("semester")) {
	                	student.setSemester(DataUtil.parseInt(item.getString()));
	                }
	                if (fieldName.equalsIgnoreCase("stream")) {
	                	student.setStream(DataUtil.emptyString(item.getString()));
	                }		                
	                if (fieldName.equalsIgnoreCase("mediumofinstruction")) {
	                	student.setMediumofinstruction(DataUtil.emptyString(item.getString()));
	                }	
	                if (fieldName.equalsIgnoreCase("previousschooltype")) {
	                	student.setPreviousschooltype(DataUtil.emptyString(item.getString()));
	                }
	                if (fieldName.equalsIgnoreCase("previouschooladdress")) {
	                	student.setPreviouschooladdress(DataUtil.emptyString(item.getString()));
	                }
	                if (fieldName.equalsIgnoreCase("urbanrural")) {
	                	student.setUrbanrural(DataUtil.emptyString(item.getString()));
	                }
	                if (fieldName.equalsIgnoreCase("studentscastecertno")) {
	                	student.setStudentscastecertno(DataUtil.emptyString(item.getString()));
	                }
	                if (fieldName.equalsIgnoreCase("studentscaste")) {
	                	student.setStudentscaste(DataUtil.emptyString(item.getString()));
	                }
	                if (fieldName.equalsIgnoreCase("socialcategory")) {
	                	student.setSocialcategory(DataUtil.emptyString(item.getString()));
	                }
	                if (fieldName.equalsIgnoreCase("belongtobpl")) {
	                	student.setBelongtobpl(DataUtil.parseInt(item.getString()));
	                }
	                if (fieldName.equalsIgnoreCase("bplcardno")) {
	                	student.setBplcardno(DataUtil.emptyString(item.getString()));
	                }
	                if (fieldName.equalsIgnoreCase("bhagyalakshmibondnumber")) {
	                	student.setBhagyalakshmibondnumber(DataUtil.emptyString(item.getString()));
	                }
	                if (fieldName.equalsIgnoreCase("disabilitychild")) {
	                	student.setDisabilitychild(DataUtil.emptyString(item.getString()));
	                }
	                if (fieldName.equalsIgnoreCase("specialcategory")) {
	                	dropdowncateg = DataUtil.emptyString(item.getString());
	                }
	                if(fieldName.equalsIgnoreCase("newcategory")) {
	                	newcateg = DataUtil.emptyString(item.getString());
	                }
	                if (fieldName.equalsIgnoreCase("sts")) {
	                	student.setSts(DataUtil.parseInt(item.getString()));
	                }
	                if (fieldName.equalsIgnoreCase("rte")) {
	                	student.setRte(DataUtil.parseInt(item.getString()));
	                }
	                if (fieldName.equalsIgnoreCase("passedout")) {
	                	student.setPassedout(DataUtil.parseInt(item.getString()));
	                }
	                if (fieldName.equalsIgnoreCase("leftout")) {
	                	student.setLeftout(DataUtil.parseInt(item.getString()));
	                }
	                if (fieldName.equalsIgnoreCase("droppedout")) {
	                	student.setDroppedout(DataUtil.parseInt(item.getString()));
	                }
	                // Updating paretns information
	                
	                parents.setPid(parentsId);
	        		parents.setSid(studentId);

	                if (fieldName.equalsIgnoreCase("fathersname")) {
	                	parents.setFathersname(DataUtil.emptyString(item.getString()));
	                }


	                if (fieldName.equalsIgnoreCase("mothersname")) {
	                	parents.setMothersname(DataUtil.emptyString(item.getString()));
	                }

	                if (fieldName.equalsIgnoreCase("profession")) {
	                    parents.setProfesssion(DataUtil.emptyString(item.getString()));

	                }

	                if (fieldName.equalsIgnoreCase("annualincome")) {
	                	parents.setParentsannualincome(DataUtil.emptyString(item.getString()));

	                }

	                if (fieldName.equalsIgnoreCase("permanentaddress")) {
	                    parents.setAddresspermanent(DataUtil.emptyString(item.getString()));
	                }

	                if (fieldName.equalsIgnoreCase("temporaryaddress")) {
	                    parents.setAddresstemporary(DataUtil.emptyString(item.getString()));
	                }


	                if (fieldName.equalsIgnoreCase("noofdependents")) {
	                	parents.setNoofdependents(DataUtil.parseInt(item.getString()));
	                }
	                
	                if (fieldName.equalsIgnoreCase("remarksadditional")) {
	                	parents.setRemarks(DataUtil.emptyString(item.getString()));
	                }
	                
	                
	                if (fieldName.equalsIgnoreCase("contactnumber")) {
	                	parents.setContactnumber(DataUtil.emptyString(item.getString()));
	                }
	                
	                if (fieldName.equalsIgnoreCase("cocontactnumber")) {
	                	parents.setCocontactnumber(DataUtil.emptyString(item.getString()));
	                }
	                
	                if(fieldName.equalsIgnoreCase("email")){
	                	parents.setEmail(DataUtil.emptyString(item.getString()));
	                }
	                
	                if (fieldName.equalsIgnoreCase("pep")) {
                            puDetails.setExampassedappearance(DataUtil.parseInt(item.getString()));
                        }
                        if (fieldName.equalsIgnoreCase("passedyear")) {
                            puDetails.setExampassedyear(DataUtil.emptyString(item.getString()));
                        }
                        if (fieldName.equalsIgnoreCase("regno")) {
                            puDetails.setExampassedregno(DataUtil.emptyString(item.getString()));
                        }
                        if (fieldName.equalsIgnoreCase("resultclass")) {
                            puDetails.setExampassedresultwithclass(DataUtil.emptyString(item.getString()));
                        }
                        if (fieldName.equalsIgnoreCase("Xsecondlanguage")) {
                            puDetails.setSecondlanguage(DataUtil.emptyString(item.getString()));
                        }
                        if (fieldName.equalsIgnoreCase("aggmarks")) {
                            puDetails.setAggregatemarkssslc(DataUtil.emptyString(item.getString()));
                        }
                        if (fieldName.equalsIgnoreCase("subjectspart1")) {
                            puDetails.setOptionalsubjects(DataUtil.emptyString(item.getString()));
                        }
                        if (fieldName.equalsIgnoreCase("subjectspart2")) {
                            puDetails.setCompulsorysubjects(DataUtil.emptyString(item.getString()));
                        }
                        if (fieldName.equalsIgnoreCase("Xmediuminstruction")) {
                            puDetails.setSslcmediuminstruction(DataUtil.emptyString(item.getString()));
                           
                        }
                        if (fieldName.equalsIgnoreCase("PUmediuminstruction")) {
                            puDetails.setPumediuminstruction(DataUtil.emptyString(item.getString()));
                           
                        }
                        if (fieldName.equalsIgnoreCase("pudetailsid")) {
                            puDetails.setIdpudetails(DataUtil.parseInt(item.getString()));
                           
                        }
                        if (fieldName.equalsIgnoreCase("fathersqualification")) {
                            parents.setFathersqualification(DataUtil.emptyString(item.getString()));
                           
                        }
                        if (fieldName.equalsIgnoreCase("mothersqualification")) {
                            parents.setMothersqualification(DataUtil.emptyString(item.getString()));
                           
                        }
		                if(fieldName.equalsIgnoreCase("fatherscastecertno")){
		                	parents.setFatherscastecertno(DataUtil.emptyString(item.getString()));
		                }
		                if(fieldName.equalsIgnoreCase("motherscastecertno")){
		                	parents.setMotherscastecertno(DataUtil.emptyString(item.getString()));
		                }
		                if(fieldName.equalsIgnoreCase("fatherscaste")){
		                	parents.setFatherscaste(DataUtil.emptyString(item.getString()));
		                }
		                if(fieldName.equalsIgnoreCase("motherscaste")){
		                	parents.setMotherscaste(DataUtil.emptyString(item.getString()));
		                }
                        if (fieldName.equalsIgnoreCase("languagesstudied")) {
                            student.setLanguagesstudied(DataUtil.emptyString(item.getString()));
                           
                        }
                        if (fieldName.equalsIgnoreCase("mediumofinstructionlastschool")) {
                            student.setInstructionmediumlastschool(DataUtil.emptyString(item.getString()));
                           
                        }
                        
                     // Updating Degree Details
                        if (fieldName.equalsIgnoreCase("pepdc")) {
                            degreeDetails.setExampassedappearance(DataUtil.parseInt(item.getString()));
                        }
                        if (fieldName.equalsIgnoreCase("passedyeardc")) {
                            degreeDetails.setExampassedyear(DataUtil.emptyString(item.getString()));
                        }
                        if (fieldName.equalsIgnoreCase("regnodc")) {
                            degreeDetails.setExampassedregno(DataUtil.emptyString(item.getString()));
                        }
                        if (fieldName.equalsIgnoreCase("resultclassdc")) {
                            degreeDetails.setExampassedresultwithclass(DataUtil.emptyString(item.getString()));
                        }
                        if (fieldName.equalsIgnoreCase("mediumofinstructiondc")) {
                            degreeDetails.setPumediuminstruction(DataUtil.emptyString(item.getString()));
                        }
                        if (fieldName.equalsIgnoreCase("qpartone")) {
                            degreeDetails.setSubjectsqualifingexampartone(DataUtil.emptyString(item.getString()));
                        }
                        if (fieldName.equalsIgnoreCase("qparttwo")) {
                            degreeDetails.setSubjectsqualifingexamparttwo(DataUtil.emptyString(item.getString()));
                        }
                        if (fieldName.equalsIgnoreCase("ppartone")) {
                            degreeDetails.setSubjectsdegreecoursepartone(DataUtil.emptyString(item.getString()));
                        }
                        if (fieldName.equalsIgnoreCase("pparttwo")) {
                            degreeDetails.setSubjectsdegreecourseparttwo(DataUtil.emptyString(item.getString()));
                        }
                        if (fieldName.equalsIgnoreCase("pumarkscard")) {
                            degreeDetails.setPumarkscard(DataUtil.parseInt(item.getString()));
                        }
                        if (fieldName.equalsIgnoreCase("medicalreport")) {
                            degreeDetails.setMedicalreport(DataUtil.parseInt(item.getString()));
                        }
                        if (fieldName.equalsIgnoreCase("incomecertificate")) {
                            degreeDetails.setIncomecertificate(DataUtil.parseInt(item.getString()));
                        }
                        if (fieldName.equalsIgnoreCase("migrationcertificate")) {
                            degreeDetails.setMigrationcertificate(DataUtil.parseInt(item.getString()));
                        }
                        if (fieldName.equalsIgnoreCase("originaltc")) {
                            degreeDetails.setTransfercertificate(DataUtil.parseInt(item.getString()));
                        }
                        if (fieldName.equalsIgnoreCase("castecertificate")) {
                            degreeDetails.setCastecertificate(DataUtil.parseInt(item.getString()));
                        }
                        if (fieldName.equalsIgnoreCase("games")) {
                            degreeDetails.setProficiencysports(DataUtil.emptyString(item.getString()));
                        }
                        if (fieldName.equalsIgnoreCase("extracurricular")) {
                            degreeDetails.setExtracurricular(DataUtil.emptyString(item.getString()));
                        }
                        if (fieldName.equalsIgnoreCase("employer")) {
                            degreeDetails.setAreyouemployee(DataUtil.emptyString(item.getString()));
                        }
                        if (fieldName.equalsIgnoreCase("karnataka")) {
                            degreeDetails.setKarnataka(DataUtil.parseInt(item.getString()));
                        }
                        //End Degree Details
                        
                      //Bank Details
		                if (fieldName.equalsIgnoreCase("bankname")) {
		                	student.setBankname(DataUtil.emptyString(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("bankifsc")) {
		                	student.setBankifsc(DataUtil.emptyString(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("accno")) {
		                	student.setAccno(DataUtil.emptyString(item.getString()));
		                }
		              //End Bank Details
		                
	            } else {
	                String fieldName = item.getFieldName();

	                if (fieldName.equalsIgnoreCase("fileToUpload")) {


	                    String fileName = (DataUtil.emptyString(item.getName()));
	                    String fileValue = (DataUtil.emptyString(item.getString()));
	                    if (!fileName.equalsIgnoreCase("")) {
	                       
	                    	                    	
	                    	// Resize the image
	                    	byte[]   bytesEncoded = Base64.encodeBase64(item.get());
	                    	System.out.println("ecncoded value is " + new String(bytesEncoded ));
	                    	String saveFile = new String(bytesEncoded);

	                    	student.setStudentpic(saveFile);

	                    } else{
	                    	
	                    	student.setStudentpic(studentPicUpdate);
	                    }
	                }
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
		boolean result = false;
		List<Integer> ids = new ArrayList();
		for (String id : studentIds) {
			System.out.println("id" + id);
			ids.add(Integer.valueOf(id));

		}
		if (new studentDetailsDAO().promoteMultiple(ids, classStudying)) {
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

				List<Parents> list = new studentDetailsDAO().readListOfObjectsPaginationALL((page - 1) * recordsPerPage,
						recordsPerPage, Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
				request.setAttribute("studentList", list);
				int noOfRecords = new studentDetailsDAO().getNoOfRecords(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
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
                    
                    List<Receiptinfo> rinfo = new feesCollectionDAO().getReceiptDetailsPerStudent(id,academicYear);
                    request.setAttribute("receiptinfo",rinfo);
                    List<Studentfeesstructure> feesstructure = new studentDetailsDAO().getStudentFeesStructure(id, academicYear);
                    
                    long totalSum = 0l;
                    for (Receiptinfo receiptInfoSingle : rinfo) {
                            totalSum = totalSum + receiptInfoSingle.getTotalamount();
                    }
                    
                    long totalFeesAmount = 0l;
                    for (Studentfeesstructure studentfeesstructureSingle : feesstructure) {
                            totalFeesAmount = totalFeesAmount+studentfeesstructureSingle.getFeesamount()-studentfeesstructureSingle.getWaiveoff();
                    }
                            httpSession.setAttribute("feesstructure", feesstructure);
                            httpSession.setAttribute("sumoffees", totalSum);
                            httpSession.setAttribute("dueamount", totalFeesAmount-totalSum);
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
					String queryMain = "From Parents as parents where parents.branchid="+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())+" AND";
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
					new Object[] { "Student Name", "Gender", "Date Of Birth", "Age", "Studying In Class",
							"Admitted In Class", "Admission Number", "Admission Date", "Blood Group", "Religion",
							"Caste", "Fathers Name", "Mothers Name" });
			int i = 1;
			for (Parents studentDetails : listOfStudentRecords) {
				data.put(Integer.toString(i),
						new Object[] { DataUtil.emptyString(studentDetails.getStudent().getName()),  DataUtil.emptyString(studentDetails.getStudent().getGender()),
								 DataUtil.emptyString(DateUtil.getStringDate(studentDetails.getStudent().getDateofbirth())),
								 DataUtil.emptyString(Integer.toString(studentDetails.getStudent().getAge())),
								 DataUtil.emptyString(studentDetails.getStudent().getClassstudying().replace("--", " ")),
								 DataUtil.emptyString(studentDetails.getStudent().getClassadmittedin().replace("--", " ")),
								 DataUtil.emptyString(studentDetails.getStudent().getAdmissionnumber()),
								 DataUtil.emptyString(studentDetails.getStudent().getAdmissiondate().toString()),
								 DataUtil.emptyString(studentDetails.getStudent().getBloodgroup()),  DataUtil.emptyString(studentDetails.getStudent().getReligion()),
								 DataUtil.emptyString(studentDetails.getStudent().getCaste()),  DataUtil.emptyString(studentDetails.getFathersname()),
								 DataUtil.emptyString(studentDetails.getMothersname()) });
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
			bonafidePage = "bonafidecertificateprint.jsp";
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
            return "addStudent.jsp";
           /* if("1".equalsIgnoreCase(branchId) || "2".equalsIgnoreCase(branchId) || "3".equalsIgnoreCase(branchId)) {
                return "addStudent.jsp";
            }else if("4".equalsIgnoreCase(branchId)) {
                return "addStudentPU.jsp";
            }else if("5".equalsIgnoreCase(branchId)) {
                return "addStudentDC.jsp";
            }*/
        }
        return "sessiontimeout.jsp";
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
