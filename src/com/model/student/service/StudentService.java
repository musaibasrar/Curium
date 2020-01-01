package com.model.student.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.global.Global;
import com.model.academicyear.dao.YearDAO;
import com.model.academicyear.dto.Currentacademicyear;
import com.model.branch.dao.BranchDAO;
import com.model.branch.dto.Branch;
import com.model.branch.service.BranchService;
import com.model.examlevels.dto.Subexamlevel;
import com.model.examlevels.service.ExamLevelService;
import com.model.feescollection.dao.feesCollectionDAO;
import com.model.feescollection.dto.Receiptinfo;
import com.model.feesdetails.dao.feesDetailsDAO;
import com.model.feesdetails.dto.Feesdetails;
import com.model.language.service.LanguageService;
import com.model.parents.dao.parentsDetailsDAO;
import com.model.parents.dto.Parents;
import com.model.qualification.dto.Qualification;
import com.model.qualification.service.QualificationService;
import com.model.std.dto.Classsec;
import com.model.student.dao.studentDetailsDAO;
import com.model.student.dto.Student;
import com.model.student.dto.Studentfeesstructure;
import com.model.user.dao.UserDAO;
import com.model.user.dto.Login;
import com.util.DataUtil;
import com.util.DateUtil;

public class StudentService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;
	private String BRANCHID = "branchid";
	
	private static final Logger logger = LogManager.getLogger(StudentService.class);
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
		
		logger.info("*******Student Service: Add Student ****** ");
		
		Student student = new Student();
		Parents parents = new Parents();
		String addClass = null,addSec =null,addClassE=null,addSecE=null,conClassStudying=null,conClassAdmittedIn=null;
		String admissionYear = null;
		boolean result=false;
		
		try {
			logger.info("*******Student Service: TRY Add Student ****** ");
			List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
		
			 for (FileItem item : items) {
		            if (item.isFormField()) {
		                // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
		                String fieldName = item.getFieldName();

		                if (fieldName.equalsIgnoreCase("name")) {
		                    
		                    student.setName(DataUtil.emptyString(item.getString()).toUpperCase());
		                }

		                
		                if (fieldName.equalsIgnoreCase("gender")) {
		                    
		                    student.setGender(DataUtil.emptyString(item.getString()));

		                }

		                if (fieldName.equalsIgnoreCase("dateofbirth")) {

		                	student.setDateofbirth(DateUtil.dateParserUpdateStd(item.getString()));

		                }

		                if (fieldName.equalsIgnoreCase("age")) {
		                    
		                    student.setAge(DataUtil.parseInt(item.getString()));

		                }
		                
		                if (fieldName.equalsIgnoreCase("qualification")) {
		                    student.setQualification(DataUtil.emptyString(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("admnno")) {
		                	student.setAdmissionnumber(DataUtil.emptyString(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("dateofadmission")) {
		                	student.setAdmissiondate(DateUtil.dateParserUpdateStd(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("examlevel")) {
		                    student.setExamlevel(DataUtil.emptyString(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("languageopted")) {
		                	student.setLanguageopted(DataUtil.emptyString(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("districtcode")) {
		                    student.setDistrictcode(DataUtil.emptyString(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("centercode")) {
		                    student.setCentercode(DataUtil.emptyString(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("createddate")) {
		                    student.setCreateddate(DateUtil.dateParserUpdateStd(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("remarks")) {
		                	student.setRemarks(DataUtil.emptyString(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("religion")) {
                                    student.setReligion(DataUtil.emptyString(item.getString()));
                                }
		                if (fieldName.equalsIgnoreCase("educationqualification")) {
                                    student.setEducation(DataUtil.emptyString(item.getString()));
                                }
		                if (fieldName.equalsIgnoreCase("admittedin")) {
                                    student.setAdmittedin(DataUtil.emptyString(item.getString()));
                                }
		                if (fieldName.equalsIgnoreCase("fathersname")) {
		                	parents.setFathersname(DataUtil.emptyString(item.getString()).toUpperCase());
		                }
		                if (fieldName.equalsIgnoreCase("mothersname")) {
		                	parents.setMothersname(DataUtil.emptyString(item.getString()).toUpperCase());
		                }
		                if (fieldName.equalsIgnoreCase("permanentaddress")) {
		                    parents.setAddresspermanent(DataUtil.emptyString(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("notes")) {
		                	parents.setRemarks(DataUtil.emptyString(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("contactnumber")) {
		                	parents.setContactnumber(DataUtil.emptyString(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("email")) {
		                	parents.setEmail(DataUtil.emptyString(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("guardian")) {
		                	student.setGuardiandetails(DataUtil.emptyString(item.getString()).toUpperCase());
		                }
		                if (fieldName.equalsIgnoreCase("remarksadditional")) {
		                	student.setGuardiandetails(DataUtil.emptyString(item.getString()));
		                }
		                if (fieldName.equalsIgnoreCase("admissionyear")) {
                                    admissionYear = DataUtil.emptyString(item.getString());
                            }
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
		
		// set external id
		final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLM0123NOP89QRSTUVWXYZ4567";
		int count =4;
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
		int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
		builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		student.setStudentexternalid(builder.toString());
		// End external id
		
		student.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		
		//if branch is not head office
		if(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())!=1) {
			student.setRemarks("underapproval");
		}else {
			student.setRemarks("admin");
			Date todaysDate = new Date();
			student.setApprovedon(todaysDate);
		}
		
		
		
		//SET STUDENTS DETAILS
		httpSession.setAttribute("genderadd", student.getGender());
		httpSession.setAttribute("examleveladd", student.getExamlevel());
		httpSession.setAttribute("qualificationadd", student.getQualification());
		httpSession.setAttribute("centercodeadd", student.getCentercode());
		httpSession.setAttribute("languageadd", student.getLanguageopted());
		httpSession.setAttribute("ageadd", student.getAge());
		httpSession.setAttribute("admissionyear", admissionYear);
		
		
		parents.setStudent(student);
		parents.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		parents = new parentsDetailsDAO().create(parents);
		logger.info("*******Student Service: Created Student ****** "+student.getName());
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
				totalFeesAmount = totalFeesAmount+studentfeesstructureSingle.getFeesamount();
			}
			
			//String sumOfFees = new feesDetailsDAO().feesSum(id, currentYear.getCurrentacademicyear());
			//String totalFees = new feesDetailsDAO().feesTotal(id, currentYear.getCurrentacademicyear());
			//String dueAmount = new feesDetailsDAO().dueAmount(id, currentYear.getCurrentacademicyear());
			if (student == null) {
				result = false;
			} else {
				httpSession.setAttribute("student", student);
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
			
			    new ExamLevelService(request, response).examLevels();
		            new LanguageService(request, response).viewLanguage();
		            new BranchService(request, response).viewDistricts();
		            new BranchService(request, response).viewBranches();
		            new QualificationService(request, response).viewQualification();
		            
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
		String id = "";
		String pid = "";
		int studentId = 0;
		int parentsId = 0;
		String addClass = null,addSec =null,addClassE=null,addSecE=null,conClassStudying=null,conClassAdmittedIn=null;
		String studentPicUpdate=null;
		
		try{
			
		List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
	
		 for (FileItem item : items) {
	            if (item.isFormField()) {
	                // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
	                String fieldName = item.getFieldName();
	               
	                if (fieldName.equalsIgnoreCase("id")) {	                
	                id = DataUtil.emptyString(item.getString());
	                
	                }
	                
	                if (fieldName.equalsIgnoreCase("idparents")) {	                
	        		pid = DataUtil.emptyString(item.getString());
	                }
	                
	        		if(id!=""){
	        			studentId = Integer.parseInt(id);
	        			student.setSid(studentId);
	        		}
	        		
	        		if (pid != "") {

	        			parentsId = Integer.parseInt(pid);
	        		}
	                
	                if (fieldName.equalsIgnoreCase("name")) {
	                    
	                    student.setName(DataUtil.emptyString(item.getString()).toUpperCase());
	                }

	                
	                if (fieldName.equalsIgnoreCase("gender")) {
	                    
	                    student.setGender(DataUtil.emptyString(item.getString()));

	                }

	                if (fieldName.equalsIgnoreCase("dateofbirth")) {

	                	student.setDateofbirth(DateUtil.dateParserUpdateStd(item.getString()));

	                }

	                if (fieldName.equalsIgnoreCase("age")) {
	                    
	                    student.setAge(DataUtil.parseInt(item.getString()));

	                }
	                
	                if (fieldName.equalsIgnoreCase("qualification")) {
                            student.setQualification(DataUtil.emptyString(item.getString()));
                        }
	                
	                if (fieldName.equalsIgnoreCase("examlevel")) {
                            student.setExamlevel(DataUtil.emptyString(item.getString()));
                        }
                        if (fieldName.equalsIgnoreCase("languageopted")) {
                                student.setLanguageopted(DataUtil.emptyString(item.getString()));
                        }
                        if (fieldName.equalsIgnoreCase("districtcode")) {
                            student.setDistrictcode(DataUtil.emptyString(item.getString()));
                        }
                        if (fieldName.equalsIgnoreCase("centercode")) {
                            student.setCentercode(DataUtil.emptyString(item.getString()));
                        }


	                if (fieldName.equalsIgnoreCase("admnno")) {
	                	student.setAdmissionnumber(DataUtil.emptyString(item.getString()));
	                }

	                if (fieldName.equalsIgnoreCase("dateofadmission")) {
	                	student.setAdmissiondate(DateUtil.dateParserUpdateStd(item.getString()));
	                }

	                if (fieldName.equalsIgnoreCase("createddate")) {
	                    student.setCreateddate(DateUtil.dateParserUpdateStd(item.getString()));
	                }

	                if (fieldName.equalsIgnoreCase("remarks")) {
	                	student.setRemarks(DataUtil.emptyString(item.getString()));
	                }
	                
	                if (fieldName.equalsIgnoreCase("educationqualification")) {
                            student.setEducation(DataUtil.emptyString(item.getString()));
                        }
	                
	                if(fieldName.equalsIgnoreCase("studentpicupdate")){
	                	studentPicUpdate=DataUtil.emptyString(item.getString());
	                }
	                
	                if (fieldName.equalsIgnoreCase("admittedin")) {
                            student.setAdmittedin(DataUtil.emptyString(item.getString()));
                        }
	                
	                if(fieldName.equalsIgnoreCase("studentexternalid")){
	                	student.setStudentexternalid(DataUtil.emptyString(item.getString()));
	                }
	                
	                if (fieldName.equalsIgnoreCase("guardian")) {
	                	student.setGuardiandetails(DataUtil.emptyString(item.getString()).toUpperCase());
	                }
	                if (fieldName.equalsIgnoreCase("remarksadditional")) {
	                	student.setGuardiandetails(DataUtil.emptyString(item.getString()));
	                }
	                if (fieldName.equalsIgnoreCase("religion")) {
                            student.setReligion(DataUtil.emptyString(item.getString()));
                        }
	                if(fieldName.equalsIgnoreCase("studentarchiveupdate")){
	                    student.setArchive(DataUtil.parseInt(item.getString()));
                        }
	                if(fieldName.equalsIgnoreCase("studentdroppedoutupdate")){
	                    student.setDroppedout(DataUtil.parseInt(item.getString()));
                        }
	                if(fieldName.equalsIgnoreCase("studentpassedoutupdate")){
	                    student.setPassedout(DataUtil.parseInt(item.getString()));
                        }
	                // Updating paretns information
	                
	                parents.setPid(parentsId);
	        		parents.setSid(studentId);

	                if (fieldName.equalsIgnoreCase("fathersname")) {
	                	parents.setFathersname(DataUtil.emptyString(item.getString()).toUpperCase());
	                }


	                if (fieldName.equalsIgnoreCase("mothersname")) {
	                	parents.setMothersname(DataUtil.emptyString(item.getString()).toUpperCase());
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
	                
	                if (fieldName.equalsIgnoreCase("notes")) {
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
	                
	            } else {
	                String fieldName = item.getFieldName();

	                if (fieldName.equalsIgnoreCase("fileToUpload")) {


	                    String fileName = (DataUtil.emptyString(item.getName()));
	                    String fileValue = (DataUtil.emptyString(item.getString()));
	                    if (!fileName.equalsIgnoreCase("")) {
	                       
	                    	                    	
	                    	// Resize the image
	                    	byte[]   bytesEncoded = Base64.encodeBase64(item.get());
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

		 student.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		 student = new studentDetailsDAO().update(student);
 		if (pid != "") {
 			parents.setStudent(student);
 			parents.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
 			parents = new parentsDetailsDAO().update(parents);
 		}
		String stId = student.getSid().toString();
        return stId;
		
		
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
	
	public boolean viewAllStudentsListCenter() {


            boolean result = false;
            if(httpSession.getAttribute(BRANCHID)!=null){
                    
                    try {

                            List<Student> list = new studentDetailsDAO().readListOfStudentsCenter(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
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
			List ids = new ArrayList();
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

	public boolean deleteMultiple() {
		String[] studentIds = request.getParameterValues("studentIDs");
		if (studentIds != null) {
			List ids = new ArrayList();
			for (String id : studentIds) {
				ids.add(Integer.valueOf(id));

			}
			if(new studentDetailsDAO().deleteMultiple(ids)) {
			    return true;
			}
		}
		return false;
	}

	public void restoreMultiple() {
		String[] studentIds = request.getParameterValues("studentIDs");
		if (studentIds != null) {
			List ids = new ArrayList();
			for (String id : studentIds) {
				ids.add(Integer.valueOf(id));

			}
			new studentDetailsDAO().restoreMultiple(ids);
		}
	}

	@SuppressWarnings("finally")
	public boolean searchClass() {
	    String searchQuery = "From Student as student where student.archive=0 and student.passedout=0 and student.remarks = 'approved' OR student.remarks='admin' AND student.droppedout=0 ";
	       String subQuery =null;
	       
	            if(!request.getParameter("centercode").equalsIgnoreCase("")) {
	                String[] centerCode = request.getParameter("centercode").split(":");
	                subQuery = "and student.centercode = '"+centerCode[0]+"'";
	            }
	          	            
	            if(!request.getParameter("examlevel").equalsIgnoreCase("")) {
	                if(subQuery!=null) {
	                    subQuery = subQuery+" AND student.examlevel = '"+request.getParameter("examlevel")+"'";
	                }else {
	                    subQuery = "student.examlevel = '"+request.getParameter("examlevel")+"'";
	                }
	            }
	            
	            if(!request.getParameter("languageopted").equalsIgnoreCase("")) {
	                if(subQuery!=null) {
	                    subQuery = subQuery+"AND student.languageopted = '"+request.getParameter("languageopted")+"'";
	                }else {
	                    subQuery = "student.languageopted = '"+request.getParameter("languageopted")+"'";
	                }
	            }
	            
	            searchQuery = searchQuery+subQuery+" Order by student.admissionnumber ASC";
	            List<Student> studentList = new studentDetailsDAO().getListStudents(searchQuery);
	            request.setAttribute("studentList", studentList);
	     
		return true;

	}

	public boolean promoteMultiple() {
		String[] studentIds = request.getParameterValues("studentIDs");
		String classStudying = request.getParameter("classstudying");
		String currentYear = httpSession.getAttribute("currentAcademicYear").toString();
		String[] currentAcademicYear = currentYear.split("/");
		String academicYear = currentAcademicYear[0].substring(currentAcademicYear[0].length() - 2);
		
		boolean result = false;
		List ids = new LinkedList<Integer>();
		List<Student> studentList = new ArrayList<Student>();
		for (String id : studentIds) {
			String[] idAdmNo = id.split("~");
			ids.add(Integer.valueOf(idAdmNo[0]));
			Student std = getStudentDetails(Long.valueOf(idAdmNo[0]));
			String qualification = getQualification(std.getQualification(),std.getAge()+1);
			std.setQualification(qualification);
			studentList.add(std);
		}
		if (new studentDetailsDAO().promoteOrDemoteMultiple(studentList, classStudying, academicYear, "Promote")) {
			result = true;
		}
		return result;
	}

	private String getQualification(String qualification, Integer age) {
	    List<Qualification> qualList = new QualificationService(request, response).viewQualification();
        String qual = qualification;
        
           for(int i=0;i<qualList.size()-1;i++) {
               if(qualList.get(i).getQualification().equalsIgnoreCase(qualification) && age > qualList.get(i).getQualificationmaxage()) {
                   qual = qualList.get(++i).getQualification();
               }
           }
        return qual;
    }

    private Student getStudentDetails(Long studentId) {
        return  new studentDetailsDAO().readUniqueObject(studentId);
    }

    public boolean viewAllStudentsParents() {

		boolean result = false;
		//String pages = "1";
		if(httpSession.getAttribute(BRANCHID)!=null){
			try {
				int page = 1;
				int recordsPerPage = 20;
				if (!"".equalsIgnoreCase(DataUtil.emptyString(request.getParameter("page")))) {
					page = Integer.parseInt(request.getParameter("page"));
				}
				List<Parents> list = new studentDetailsDAO().readListOfObjectsPaginationALL((page - 1) * recordsPerPage,
						recordsPerPage, Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
				request.setAttribute("studentList", list);
				int noOfRecords = new studentDetailsDAO().getNoOfRecords(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
				int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
				request.setAttribute("noOfPages", noOfPages);
				request.setAttribute("currentPage", page);
				if(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())==1) {
				    request.setAttribute("totalstudents", new studentDetailsDAO().getNoOfRecords());
				}else {
				    request.setAttribute("totalstudents", new studentDetailsDAO().getNoOfRecords(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())));
				}
				
				result = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		        new ExamLevelService(request, response).examLevels();
		        new LanguageService(request, response).viewLanguage();
		        new BranchService(request, response).viewDistricts();
		        new BranchService(request, response).viewBranches();
		        new QualificationService(request, response).viewQualification(); 
		        httpSession.setAttribute("noofpapers", "");
		
		return result;
	}

	public boolean viewfeesStructurePerYear() {
		boolean result = false;
		try {
			long id = Long.parseLong(request.getParameter("id"));
			String academicYear = request.getParameter("academicyear");

			List<Feesdetails> feesdetails = new feesDetailsDAO().readList(id, academicYear);
			List<Studentfeesstructure> feesstructure = new studentDetailsDAO().getStudentFeesStructure(id,
					academicYear);
			String sumOfFees = new feesDetailsDAO().feesSum(id, academicYear);
			String totalFees = new feesDetailsDAO().feesTotal(id, academicYear);
			String dueAmount = new feesDetailsDAO().dueAmount(id, academicYear);

			httpSession.setAttribute("feesdetails", feesdetails);
			httpSession.setAttribute("feesstructure", feesstructure);
			httpSession.setAttribute("sumoffees", sumOfFees);
			httpSession.setAttribute("dueamount", dueAmount);
			httpSession.setAttribute("totalfees", totalFees);
			httpSession.setAttribute("academicPerYear", academicYear);

			result = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void studentsDetailsSearch() {

		if(httpSession.getAttribute(BRANCHID)!=null){
			
			String queryMain = "From Parents as parents where parents.branchid="+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())+" AND";
			String studentname = DataUtil.emptyString(request.getParameter("namesearch"));

			String addClass = request.getParameter("classsearch");
			String addSec = request.getParameter("secsearch");
			String conClassStudying = "";

			if (!addClass.equalsIgnoreCase("Class")) {

				conClassStudying = addClass + " " + "%";

			}
			if (!addSec.equalsIgnoreCase("Sec")) {
				conClassStudying = addClass;
				conClassStudying = conClassStudying + " " + addSec;
			}

			String classStudying = DataUtil.emptyString(conClassStudying);

			String querySub = "";

			if (!studentname.equalsIgnoreCase("")) {
				querySub = " parents.Student.name like '%" + studentname + "%'";
			}

			if (!classStudying.equalsIgnoreCase("") && !querySub.equalsIgnoreCase("")) {
				querySub = querySub + " AND parents.Student.classstudying like '" + classStudying + "' ";
			} else if (!classStudying.equalsIgnoreCase("")) {
				querySub = querySub + " parents.Student.classstudying like '" + classStudying + "' ";
			}

			queryMain = queryMain + querySub + " Order By parent.Student.admissionnumber ASC";
			List<Parents> searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
			request.setAttribute("searchStudentList", searchStudentList);
		}
		

	}

	public boolean exportDataStudents() {

		String[] studentIds = request.getParameterValues("studentIDs");
		boolean successResult = false;
		
		List<Parents> listOfStudentRecords = new ArrayList<Parents>();

		if (studentIds != null) {
			for (String id : studentIds) {
				if (id != null || id != "") {
					String queryMain = "From Parents as parents where parents.branchid="+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())+" AND";
					String querySub = " parents.Student.id = " + id;
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
					new Object[] { "Student Name", "Gender", "Date Of Birth", "Age", "Qualification",
							"Exam Level", "Admission Number", "Admission Date", "Language Opted", "District Code",
							"Center Code", "Fathers Name", "Husband Name" });
			int i = 1;
			for (Parents studentDetails : listOfStudentRecords) {
				data.put(Integer.toString(i),
						new Object[] { studentDetails.getStudent().getName(), studentDetails.getStudent().getGender(),
								studentDetails.getStudent().getDateofbirth().toString(),
								studentDetails.getStudent().getAge().toString(),
								studentDetails.getStudent().getQualification(),
								studentDetails.getStudent().getExamlevel(),
								studentDetails.getStudent().getAdmissionnumber(),
								studentDetails.getStudent().getAdmissiondate().toString(),
								studentDetails.getStudent().getLanguageopted(), studentDetails.getStudent().getDistrictcode(),
								studentDetails.getStudent().getCentercode(), studentDetails.getFathersname(),
								studentDetails.getMothersname() });
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
				FileOutputStream out = new FileOutputStream(new File(System.getProperty("java.io.tmpdir")+"studentsdetails.xlsx"));
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
		String std =  request.getParameter("id");
		String returning = null;
		
		if(std!=null){
			returning = "bonafideprint.jsp";
		}
		if(studentIds!=null && studentIds.length>0){
			std=studentIds[0];
			returning = "bonafidecertificateprint.jsp";
		}
		String getStudentInfo  = "from Parents as parents where parents.Student.sid="+std;
		Parents parents = new studentDetailsDAO().getStudentRecords(getStudentInfo);
		request.setAttribute("studentdetails", parents);
		if(parents!=null){
			return returning;
		}
		return returning;
	}

	public boolean downlaodFile() {
		boolean result = false;
		try {

			File downloadFile = new File(System.getProperty("java.io.tmpdir")+"studentsdetails.xlsx");
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
			logger.error("" + e);
		}
		return result;
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

    public String addNew() {
        
        if(httpSession.getAttribute(BRANCHID)!=null){
            new ExamLevelService(request, response).examLevels();
            new LanguageService(request, response).viewLanguage();
            new BranchService(request, response).viewDistricts();
            new BranchService(request, response).viewBranches();
            new QualificationService(request, response).viewQualification();
            return "addStudent.jsp";
        }
        return "sessiontimeout.jsp";
    }

    public void studentsListReport() {

        new ExamLevelService(request, response).examLevels();
        new LanguageService(request, response).viewLanguage();
        new BranchService(request, response).viewDistricts();
        new BranchService(request, response).viewBranches();
        new QualificationService(request, response).viewQualification(); 
        httpSession.setAttribute("mapstudentreports", "");
        httpSession.setAttribute("printcentername", "");
        httpSession.setAttribute("printexamlevel", "");
        httpSession.setAttribute("printlanguage", "");
        httpSession.setAttribute("printqualification", "");
        httpSession.setAttribute("printreligion", "");
    }

    public void searchStudents() {
       String searchQuery = "From Parents as parent where ";
       String subQuery =null;
       
            if(!request.getParameter("centercode").equalsIgnoreCase("")) {
                String[] centerCode = request.getParameter("centercode").split(":");
                subQuery = "parent.Student.centercode = '"+centerCode[0]+"'";
                httpSession.setAttribute("printcentername", "Center Code/Name: "+centerCode[0]+"/"+centerCode[1]);
                httpSession.setAttribute("studentsreportcentersearch", centerCode[0]+":"+centerCode[1]);
            }else {
                httpSession.setAttribute("studentsreportcentersearch", "");
            }
            
            if(!request.getParameter("districtcode").equalsIgnoreCase("")) {
                if(subQuery!=null) {
                    subQuery = subQuery+"AND parent.Student.districtcode = '"+request.getParameter("districtcode")+"'";
                }else {
                    subQuery = "parent.Student.districtcode = '"+request.getParameter("districtcode")+"'";
                }
                httpSession.setAttribute("studentsreportdistrictsearch", request.getParameter("districtcode").toString());
            }else {
                httpSession.setAttribute("studentsreportdistrictsearch", "");
            }
            
            if(!request.getParameter("examlevel").equalsIgnoreCase("")) {
                if(subQuery!=null) {
                    subQuery = subQuery+"AND parent.Student.examlevel = '"+request.getParameter("examlevel")+"'";
                }else {
                    subQuery = "parent.Student.examlevel = '"+request.getParameter("examlevel")+"'";
                }
                httpSession.setAttribute("printexamlevel", "Examination Level: "+request.getParameter("examlevel").toString());
                httpSession.setAttribute("studentsreportexamlevelsearch", request.getParameter("examlevel").toString());
            }else {
                httpSession.setAttribute("studentsreportexamlevelsearch", "");
            }
            
            if(!request.getParameter("languageopted").equalsIgnoreCase("")) {
                if(subQuery!=null) {
                    subQuery = subQuery+"AND parent.Student.languageopted = '"+request.getParameter("languageopted")+"'";
                }else {
                    subQuery = "parent.Student.languageopted = '"+request.getParameter("languageopted")+"'";
                }
                httpSession.setAttribute("printlanguage", "Language: "+request.getParameter("languageopted").toString());
                httpSession.setAttribute("studentsreportlanguagesearch",request.getParameter("languageopted").toString());
            }else {
                httpSession.setAttribute("studentsreportlanguagesearch","");
            }
            
            if(!request.getParameter("qualification").equalsIgnoreCase("")) {
                if(subQuery!=null) {
                    subQuery = subQuery+"AND parent.Student.qualification = '"+request.getParameter("qualification")+"'";
                }else {
                    subQuery = "parent.Student.qualification = '"+request.getParameter("qualification")+"'";
                }
                httpSession.setAttribute("printqualification", "Qualification: "+request.getParameter("qualification").toString());
                httpSession.setAttribute("studentsreportqualificationsearch", request.getParameter("qualification").toString());
            }else {
                httpSession.setAttribute("studentsreportqualificationsearch", "");
            }
            
            if(!DataUtil.emptyString(request.getParameter("religion")).equalsIgnoreCase("")) {
                if(subQuery!=null) {
                    subQuery = subQuery+"AND parent.Student.religion = '"+request.getParameter("religion")+"'";
                }else {
                    subQuery = "parent.Student.religion = '"+request.getParameter("religion")+"'";
                }
                httpSession.setAttribute("printreligion", "Religion: "+request.getParameter("religion").toString());
                httpSession.setAttribute("studentsreportreligionsearch", request.getParameter("religion").toString());
            }else {
                httpSession.setAttribute("studentsreportreligionsearch", "");
            }
            
           if(!DataUtil.emptyString(request.getParameter("examyear")).equalsIgnoreCase("")) {
                if(subQuery!=null) {
                    String subAcademicYear = request.getParameter("examyear").toString().substring(2, 4);
                    
                    subQuery = subQuery+"AND parent.Student.admissionnumber like '"+subAcademicYear+"%'";
                }else {
                    subQuery = "parent.Student.admissionnumber like '\"+subAcademicYear+\"%'";
                }
                httpSession.setAttribute("studentsreportacademicsearch", request.getParameter("examyear").toString());
            }else {
                httpSession.setAttribute("studentsreportacademicsearch", "");
            }
            searchQuery = searchQuery+subQuery+" AND parent.Student.passedout = 0 AND parent.Student.droppedout = 0 AND (parent.Student.remarks = 'approved' OR parent.Student.remarks = 'admin')  AND parent.Student.archive = 0  Order By parent.Student.admissionnumber ASC";
            List<Parents> parentsList = new studentDetailsDAO().getStudentsList(searchQuery);
            Map<Parents,String> mapStudentReports = new LinkedHashMap<Parents,String>();
            
            for (Parents parents : parentsList) {
                Branch centerName = new BranchDAO().getBranch(parents.getStudent().getCentercode());
                mapStudentReports.put(parents, centerName.getCentername());
            }
            httpSession.setAttribute("mapstudentreports", mapStudentReports);
            httpSession.setAttribute("totalstudentsforprint", parentsList.size());
            
            new ExamLevelService(request, response).examLevels();
            new LanguageService(request, response).viewLanguage();
            new BranchService(request, response).viewDistricts();
            new BranchService(request, response).viewBranches();
            new QualificationService(request, response).viewQualification(); 
            
            //get subject name
            if(!DataUtil.emptyString(request.getParameter("subjectnameAjax")).equalsIgnoreCase("")) {
                httpSession.setAttribute("studentsreportsubjctsearch", request.getParameter("subjectnameAjax").toString());
                httpSession.setAttribute("printsubjectname", "Exam Paper: "+DataUtil.emptyString(request.getParameter("subjectnameAjax")));
            }else {
                httpSession.setAttribute("studentsreportsubjctsearch", "");
            }
            
          //Query subexamlevel to get the numbers of papers per exam
            List<Subexamlevel> noOfPapers = new ExamLevelService(request, response).getSubExamLevelSubject(request.getParameter("examlevel"));
            httpSession.setAttribute("noofpapers", noOfPapers);
            
    }


    public void exportStudentsReport() {
        boolean result = false;
        try {
            if (exportDataToExcel(new Global().getStudentsReports())) {
                    result = true;
            } 

    } catch (Exception e) {
            e.printStackTrace();
    }
        
    }

    private boolean exportDataToExcel(Map<Parents, String> studentsReports) {

        for (Map.Entry<Parents, String> entry : studentsReports.entrySet()) {
            logger.info("Map Values "+entry.getValue());
        }
        return false;
    }

    public void searchLanguagesReport() {
        
        List<Parents> parentsList = new ArrayList<Parents>();
        List<Branch> centerList = new LinkedList<Branch>();
        Map<List<String>,List<String>> languageReports = new LinkedHashMap<List<String>,List<String>>();
        String academicYear = null;
        
        String centerQuery =null;
        
             if(!request.getParameter("centercode").equalsIgnoreCase("")) {
                 Branch centerDetails = new Branch();
                 String[] centerCode = request.getParameter("centercode").split(":");
                 centerQuery = "where centercode = '"+centerCode[0]+"'";
                 centerDetails.setCentercode(centerCode[0]);
                 centerDetails.setCentername(centerCode[1]);
                 centerList.add(centerDetails);
                 httpSession.setAttribute("printcentername", "Center Name: "+centerCode[1]);
                 httpSession.setAttribute("studentsreportcentersearch", centerCode[0]+":"+centerCode[1]);
             }else {
                 centerList = new BranchDAO().readListOfObjects();
                 Collections.sort(centerList);
                 httpSession.setAttribute("studentsreportcentersearch", "");
             }
             
             
             if(!DataUtil.emptyString(request.getParameter("examyear")).equalsIgnoreCase("")) {
            	 String currentYear = request.getParameter("examyear");
                 String[] currentAcademicYear = currentYear.split("/");
         		 academicYear = currentAcademicYear[0].substring(currentAcademicYear[0].length() - 2);
                  httpSession.setAttribute("languageacademicsearch", request.getParameter("examyear").toString());
              }else {
            	  academicYear="";
                  httpSession.setAttribute("languageacademicsearch", "");
              }
             
             
             int englishCountTotal =0, urduCountTotal=0, hindiCountTotal=0, kannadaCountTotal= 0;
             
             if (!request.getParameter("examlevel").equalsIgnoreCase("")) {
                     
                 for (Branch eachBranch : centerList) {   
                     
                     List<String> centerNameCode = new LinkedList<String>();
                     List<String> languageCount = new ArrayList<String>();
                     int englishCount =0, urduCount=0, hindiCount=0, kannadaCount = 0;
                     
                     parentsList = new studentDetailsDAO().getStudentsList("From Parents parents where "
                             + "parents.Student.examlevel='"+request.getParameter("examlevel").toString()+"' and parents.Student.centercode='"+eachBranch.getCentercode()+"' and parents.Student.admissionnumber like '"+academicYear+"%' Order By parents.Student.admissionnumber ASC");
                    
                     for (Parents singleParents : parentsList) {
                             if(singleParents.getStudent().getLanguageopted().equalsIgnoreCase("English")) {
                                 englishCount = englishCount+1;
                             }else if(singleParents.getStudent().getLanguageopted().equalsIgnoreCase("Urdu")) {
                                 urduCount = urduCount+1;
                             }else if(singleParents.getStudent().getLanguageopted().equalsIgnoreCase("Hindi")) {
                                 hindiCount = hindiCount+1;
                             }else if(singleParents.getStudent().getLanguageopted().equalsIgnoreCase("Kannada")) {
                                 kannadaCount = kannadaCount+1;
                             }
                    }
                     englishCountTotal = englishCountTotal + englishCount;
                     urduCountTotal = urduCountTotal + urduCount;
                     hindiCountTotal = hindiCountTotal + hindiCount;
                     kannadaCountTotal = kannadaCountTotal + kannadaCount;
                     
                     
                     if(englishCount!=0 || urduCount!=0 ||  hindiCount!=0 ||  kannadaCount!=0) {

                    	 centerNameCode.add(eachBranch.getCentercode());
                    	 centerNameCode.add(eachBranch.getCentername());
                    	 languageCount.add(Integer.toString(englishCount));
	                     languageCount.add(Integer.toString(urduCount));
	                     languageCount.add(Integer.toString(hindiCount));
	                     languageCount.add(Integer.toString(kannadaCount));
	                     languageCount.add(Integer.toString(englishCount+urduCount+hindiCount+kannadaCount));
	                     
	                     languageReports.put(centerNameCode, languageCount);
                     }
                }
                 httpSession.setAttribute("printexamlevel", "Exam Level: "+request.getParameter("examlevel").toString());
                 httpSession.setAttribute("studentsreportexamlevelsearch", request.getParameter("examlevel").toString());
            }else {
                
                for (Branch eachBranch : centerList) {   
                    
                    List<String> centerNameCode = new LinkedList<String>();
                    List<String> languageCount = new ArrayList<String>();
                    int englishCount = 0, urduCount = 0, hindiCount = 0, kannadaCount = 0;
                    
                    parentsList = new studentDetailsDAO().getStudentsList("From Parents parents where "
                            + "parents.Student.centercode='"+eachBranch.getCentercode()+"' order by parents.Student.centercode DESC");
                   
                    for (Parents singleParents : parentsList) {
                            if(singleParents.getStudent().getLanguageopted().equalsIgnoreCase("English")) {
                                englishCount = englishCount+1;
                            }else if(singleParents.getStudent().getLanguageopted().equalsIgnoreCase("Urdu")) {
                                urduCount = urduCount+1;
                            }else if(singleParents.getStudent().getLanguageopted().equalsIgnoreCase("Hindi")) {
                                hindiCount = hindiCount+1;
                            }else if(singleParents.getStudent().getLanguageopted().equalsIgnoreCase("Kannada")) {
                                kannadaCount = kannadaCount+1;
                            }
                   }
                    
                    englishCountTotal = englishCountTotal + englishCount;
                    urduCountTotal = urduCountTotal + urduCount;
                    hindiCountTotal = hindiCountTotal + hindiCount;
                    kannadaCountTotal = kannadaCountTotal + kannadaCount;
                    
                    if(englishCount!=0 || urduCount!=0 ||  hindiCount!=0 ||  kannadaCount!=0) {
                    	
                    	centerNameCode.add(eachBranch.getCentercode());
                        centerNameCode.add(eachBranch.getCentername());
                        languageCount.add(Integer.toString(englishCount));
                        languageCount.add(Integer.toString(urduCount));
                        languageCount.add(Integer.toString(hindiCount));
                        languageCount.add(Integer.toString(kannadaCount));
                        languageCount.add(Integer.toString(englishCount+urduCount+hindiCount+kannadaCount));
                        
                        languageReports.put(centerNameCode, languageCount);
                    }
               }
                httpSession.setAttribute("studentsreportexamlevelsearch", "");
            }
             httpSession.setAttribute("englishcounttotal", englishCountTotal);
             httpSession.setAttribute("urducounttotal", urduCountTotal);
             httpSession.setAttribute("hindicounttotal", hindiCountTotal);
             httpSession.setAttribute("kannadacounttotal", kannadaCountTotal);
             httpSession.setAttribute("totalcount", englishCountTotal+urduCountTotal+hindiCountTotal+kannadaCountTotal);
             httpSession.setAttribute("languagereports", languageReports);
             new ExamLevelService(request, response).examLevels();
             new BranchService(request, response).viewBranches();
     }

    public void languageListReport() {

        new ExamLevelService(request, response).examLevels();
        new BranchService(request, response).viewBranches();
        httpSession.setAttribute("languagereports", "");
        httpSession.setAttribute("printcentername", "");
        httpSession.setAttribute("printexamlevel", "");
        httpSession.setAttribute("englishcounttotal", "");
        httpSession.setAttribute("urducounttotal", "");
        httpSession.setAttribute("hindicounttotal", "");
        httpSession.setAttribute("kannadacounttotal", "");
        httpSession.setAttribute("totalcount", "");
    }

    public boolean graduateMultiple() {
        String[] studentIds = request.getParameterValues("studentIDs");
        String classStudying = request.getParameter("classstudying");
        boolean result = false;
        List ids = new ArrayList();
        for (String id : studentIds) {
                String[] idAdmNo = id.split("~");
                ids.add(Integer.valueOf(idAdmNo[0]));

        }
        if (new studentDetailsDAO().graduateMultiple(ids)) {
                result = true;
        }
        return result;
}

    public boolean droppedMultiple() {
        String[] studentIds = request.getParameterValues("studentIDs");
        String classStudying = request.getParameter("classstudying");
        boolean result = false;
        List ids = new ArrayList();
        for (String id : studentIds) {
                String[] idAdmNo = id.split("~");
                ids.add(Integer.valueOf(idAdmNo[0]));

        }
        if (new studentDetailsDAO().droppedMultiple(ids)) {
                result = true;
        }
        return result;
}

    public boolean viewGraduated() {

        boolean result = false;

        try {
                List<Student> list = new studentDetailsDAO().readListOfStudentsGraduated();
                request.setAttribute("studentListGraduated", list);
                result = true;
        } catch (Exception e) {
                e.printStackTrace();
        }
        return result;
}

    public boolean viewDropped() {

        boolean result = false;

        try {
                List<Student> list = new studentDetailsDAO().readListOfStudentsDropped();
                request.setAttribute("studentListDropped", list);
                result = true;
        } catch (Exception e) {
                e.printStackTrace();
        }
        return result;
}

    public void restoreMultipleGraduate() {
        String[] studentIds = request.getParameterValues("studentIDs");
        if (studentIds != null) {
                List ids = new ArrayList();
                for (String id : studentIds) {
                        ids.add(Integer.valueOf(id));

                }
                new studentDetailsDAO().restoreMultipleGraduate(ids);
        }
}

    public void restoreMultipleDroppedout() {
        String[] studentIds = request.getParameterValues("studentIDs");
        if (studentIds != null) {
                List ids = new ArrayList();
                for (String id : studentIds) {
                        ids.add(Integer.valueOf(id));

                }
                new studentDetailsDAO().restoreMultipleDroppedout(ids);
        }
}

    public boolean viewAllStudentsParentsCenter() {

        boolean result = false;
        String pages = "1";
        if(httpSession.getAttribute(BRANCHID)!=null){
                try {
                        int page = 1;
                        int recordsPerPage = 2000;
                        if (pages != null) {
                                page = Integer.parseInt(pages);
                        }

                        List<Parents> list = new studentDetailsDAO().readListOfObjectsPaginationALLCenter((page - 1) * recordsPerPage,
                                        recordsPerPage, Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
                        request.setAttribute("studentList", list);
                        int noOfRecords = new studentDetailsDAO().getNoOfRecordsCenter(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
                        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
                        request.setAttribute("noOfPages", noOfPages);
                        request.setAttribute("currentPage", page);
                        result = true;
                } catch (Exception e) {
                        e.printStackTrace();
                }
                
                new ExamLevelService(request, response).examLevels();
                new LanguageService(request, response).viewLanguage();
                new BranchService(request, response).viewDistricts();
                new BranchService(request, response).viewBranchesCenter();
                List<Branch> list = (List<Branch>) request.getAttribute("branchList");
                request.setAttribute("studentviewallcenter", list.get(0).getCentercode()+":"+list.get(0).getCentername());
                new QualificationService(request, response).viewQualification(); 
        }
        
        return result;
}

    public void studentsListReportCenter() {

        if(httpSession.getAttribute(BRANCHID)!=null) {
        new ExamLevelService(request, response).examLevels();
        new LanguageService(request, response).viewLanguage();
        //new BranchService(request, response).viewDistricts();
        new BranchService(request, response).viewBranchesCenter(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
        new QualificationService(request, response).viewQualification(); 
        httpSession.setAttribute("mapstudentreports", "");
        httpSession.setAttribute("printcentername", "");
        httpSession.setAttribute("printexamlevel", "");
        httpSession.setAttribute("printlanguage", "");
        httpSession.setAttribute("printqualification", "");
        httpSession.setAttribute("printreligion", "");
        httpSession.setAttribute("noofpapers", "");
        }
    }

    public String addNewStdentCenter() {
        
        if(httpSession.getAttribute(BRANCHID)!=null){
            Login login = new UserDAO().getAddStudentStatus(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()), httpSession.getAttribute("username").toString());
            if(login.getAddstudentflag()==0) {
                return "addstudenthold.jsp";
            }
            new ExamLevelService(request, response).examLevels();
            new LanguageService(request, response).viewLanguage();
            new BranchService(request, response).viewBranchesCenter(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
            List<Branch> branchList = (List<Branch>) request.getAttribute("branchList");
            new BranchService(request, response).viewDistrictsCenter(branchList.get(0).getDistrictcode());
            new QualificationService(request, response).viewQualification();
            return "addStudent.jsp";
        }
        return "sessiontimeout.jsp";
    }

    public void searchStudentsCenter() {
        String searchQuery = "From Parents as parent where ";
        String subQuery =null;
        
        
        if(httpSession.getAttribute(BRANCHID)!=null) {
            
        Branch branch = new BranchDAO().getBranch(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
        
            if(branch!=null) {
                 subQuery = "parent.Student.centercode = '"+branch.getCentercode()+"'";
                 httpSession.setAttribute("printcentername", "Center Code/Name: "+branch.getCentercode()+"/"+branch.getCentername());
             
                 subQuery = subQuery+" AND parent.Student.districtcode = '"+branch.getDistrictcode()+"'";
             
             if(!request.getParameter("examlevel").equalsIgnoreCase("")) {
                     subQuery = subQuery+" AND parent.Student.examlevel = '"+request.getParameter("examlevel")+"'";
                 httpSession.setAttribute("printexamlevel", "Examination Level: "+request.getParameter("examlevel").toString());
             }
             
             if(!request.getParameter("languageopted").equalsIgnoreCase("")) {
                     subQuery = subQuery+" AND parent.Student.languageopted = '"+request.getParameter("languageopted")+"'";
                 httpSession.setAttribute("printlanguage", "Language: "+request.getParameter("languageopted").toString());
             }
             
             if(!request.getParameter("qualification").equalsIgnoreCase("")) {
                     subQuery = subQuery+" AND parent.Student.qualification = '"+request.getParameter("qualification")+"'";
                 httpSession.setAttribute("printqualification", "Qualification: "+request.getParameter("qualification").toString());
             }
             
             if(!DataUtil.emptyString(request.getParameter("religion")).equalsIgnoreCase("")) {
                     subQuery = subQuery+" AND parent.Student.religion = '"+request.getParameter("religion")+"'";
                     httpSession.setAttribute("printreligion", "Religion: "+request.getParameter("religion").toString());
             }
             
             if(!DataUtil.emptyString(request.getParameter("examyear")).equalsIgnoreCase("")) {
                     String subAcademicYear = request.getParameter("examyear").toString().substring(2, 4);
                     subQuery = subQuery+"AND parent.Student.admissionnumber like '"+subAcademicYear+"%'";
                     httpSession.setAttribute("studentsreportacademicsearch", request.getParameter("examyear").toString());
             }
             
             searchQuery = searchQuery+subQuery+" Order By parent.Student.admissionnumber ASC";
             List<Parents> parentsList = new studentDetailsDAO().getStudentsList(searchQuery);
             Map<Parents,String> mapStudentReports = new HashMap<Parents,String>();
             
             for (Parents parents : parentsList) {
                 Branch centerName = new BranchDAO().getBranch(parents.getStudent().getCentercode());
                 mapStudentReports.put(parents, centerName.getCentername());
             }
             httpSession.setAttribute("mapstudentreports", mapStudentReports);
             new ExamLevelService(request, response).examLevels();
             new LanguageService(request, response).viewLanguage();
             new BranchService(request, response).viewDistricts();
             new BranchService(request, response).viewBranches();
             new QualificationService(request, response).viewQualification(); 
            
     }
    }
    }

    public void searchStudentsviewAll() {
        String searchQuery = "From Parents as parent where parent.Student.passedout = 0 AND parent.Student.droppedout = 0 AND (parent.Student.remarks = 'approved' OR parent.Student.remarks = 'admin')  AND parent.Student.archive = 0";
        
             if(!request.getParameter("centercode").equalsIgnoreCase("")) {
                 String[] centerCode = request.getParameter("centercode").split(":");
                 searchQuery = searchQuery+" AND parent.Student.centercode = '"+centerCode[0]+"'";
                 httpSession.setAttribute("printcentername", "Center Code/Name: "+centerCode[0]+"/"+centerCode[1]);
                 httpSession.setAttribute("studentviewallcenter", centerCode[0]+":"+centerCode[1]);
             }else {
            	 httpSession.setAttribute("studentviewallcenter", "");
             }
             
             if(!request.getParameter("districtcode").equalsIgnoreCase("")) {
            	 searchQuery = searchQuery+" AND parent.Student.districtcode = '"+request.getParameter("districtcode")+"'";
                 httpSession.setAttribute("studentviewalldistrict", request.getParameter("districtcode").toString());
             }else {
                 httpSession.setAttribute("studentviewalldistrict", "");
             }
             
             if(!request.getParameter("examlevel").equalsIgnoreCase("")) {
                	 searchQuery = searchQuery+" AND parent.Student.examlevel = '"+request.getParameter("examlevel")+"'";
                 httpSession.setAttribute("printexamlevel", "Examination Level: "+request.getParameter("examlevel").toString());
                 httpSession.setAttribute("studentviewallexamlevel", request.getParameter("examlevel").toString());
             }else {
                 httpSession.setAttribute("studentviewallexamlevel", "");
             }
             
             if(!request.getParameter("languageopted").equalsIgnoreCase("")) {
            	 searchQuery = searchQuery+" AND parent.Student.languageopted = '"+request.getParameter("languageopted")+"'";
                 httpSession.setAttribute("printlanguage", "Language: "+request.getParameter("languageopted").toString());
                 httpSession.setAttribute("studentviewalllanguage", request.getParameter("languageopted").toString());
             }else {
                 httpSession.setAttribute("studentviewalllanguage", "");
             }
             
             if(!request.getParameter("qualification").equalsIgnoreCase("")) {
            	 searchQuery = searchQuery+" AND parent.Student.qualification = '"+request.getParameter("qualification")+"'";
                 httpSession.setAttribute("printqualification", "Qualification: "+request.getParameter("qualification").toString());
                 httpSession.setAttribute("studentviewallqualification", request.getParameter("qualification").toString());
             }else {
                 httpSession.setAttribute("studentviewallqualification", "");
             }
             
             if(!DataUtil.emptyString(request.getParameter("religion")).equalsIgnoreCase("")) {
            	 searchQuery = searchQuery+" AND parent.Student.religion = '"+request.getParameter("religion")+"'";
                 httpSession.setAttribute("printreligion", "Religion: "+request.getParameter("religion").toString());
                 httpSession.setAttribute("studentviewallreligion", request.getParameter("religion").toString());
             }else {
                 httpSession.setAttribute("studentviewallreligion", "");
             }
             
             if(!DataUtil.emptyString(request.getParameter("academicyear")).equalsIgnoreCase("")) {
                 String subAcademicYear = request.getParameter("academicyear").toString().substring(2, 4);
                 searchQuery = searchQuery+" AND parent.Student.admissionnumber like '"+subAcademicYear+"%'";
                 httpSession.setAttribute("studentviewallacademic", request.getParameter("academicyear").toString());
             }else {
                 httpSession.setAttribute("studentviewallacademic", "");
             }
             
             if(!DataUtil.emptyString(request.getParameter("admissionnumber")).equalsIgnoreCase("")) {
            	 searchQuery = searchQuery+" AND parent.Student.admissionnumber like '%"+request.getParameter("admissionnumber")+"%'";
            	 httpSession.setAttribute("studentviewalladmissionno", request.getParameter("admissionnumber").toString());
             }else {
            	 httpSession.setAttribute("studentviewalladmissionno", "");
             }
             
             if(!DataUtil.emptyString(request.getParameter("studentname")).equalsIgnoreCase("")) {
            	 searchQuery = searchQuery+" AND parent.Student.name like '%"+request.getParameter("studentname")+"%'";
            	 httpSession.setAttribute("studentviewallstudentname", request.getParameter("studentname").toString());
             }else {
            	 httpSession.setAttribute("studentviewallstudentname", "");
             }
             
             if(!DataUtil.emptyString(request.getParameter("fhgname")).equalsIgnoreCase("")) {
            	 searchQuery = searchQuery+" AND parent.fathersname like '%"+request.getParameter("fhgname")+"%' or parent.mothersname like '%"+request.getParameter("fhgname")+"%'"
            	 		+ "or parent.Student.guardiandetails like '%"+request.getParameter("fhgname")+"%'";
            	 httpSession.setAttribute("studentviewallfhgname", request.getParameter("fhgname").toString());
             }else {
            	 httpSession.setAttribute("studentviewallfhgname", "");
             }
             
             if(!DataUtil.emptyString(request.getParameter("gender")).equalsIgnoreCase("")) {
            	 searchQuery = searchQuery+" AND parent.Student.gender = '"+request.getParameter("gender")+"'";
            	 httpSession.setAttribute("studentviewallgender", request.getParameter("gender").toString());
             }else {
            	 httpSession.setAttribute("studentviewallgender", "");
             }
             
             if(!DataUtil.emptyString(request.getParameter("fromage")).equalsIgnoreCase("") && !DataUtil.emptyString(request.getParameter("toage")).equalsIgnoreCase("")) {
            	 searchQuery = searchQuery+" AND parent.Student.age BETWEEN '"+request.getParameter("fromage")+"' AND '"+request.getParameter("toage")+"'";
            	 httpSession.setAttribute("studentviewallfromage", request.getParameter("fromage").toString());
            	 httpSession.setAttribute("studentviewalltoage", request.getParameter("toage").toString());
             }else {
            	 httpSession.setAttribute("studentviewallfromage", "");
            	 httpSession.setAttribute("studentviewalltoage", "");
             }
             
             searchQuery = searchQuery+" Order By parent.Student.admissionnumber ASC";
             
             List<Parents> parentsList = new studentDetailsDAO().getStudentsList(searchQuery);
             
             request.setAttribute("studentList", parentsList);
             request.setAttribute("noOfPages", 1);
             request.setAttribute("currentPage", 1);
             request.setAttribute("totalstudents", parentsList.size());

             new ExamLevelService(request, response).examLevels();
             new LanguageService(request, response).viewLanguage();
             new BranchService(request, response).viewDistricts();
             if("admin".equalsIgnoreCase(httpSession.getAttribute("typeOfUser").toString())) {
                 new BranchService(request, response).viewBranches();
             }else {
                 new BranchService(request, response).viewBranchesCenter();
             }
             
             
             new QualificationService(request, response).viewQualification(); 
     }

	public boolean demoteMultiple() {
		String[] studentIds = request.getParameterValues("studentIDs");
		String classStudying = request.getParameter("classstudying");
		String currentYear = httpSession.getAttribute("currentAcademicYear").toString();
		String[] currentAcademicYear = currentYear.split("/");
		String academicYear = currentAcademicYear[0].substring(currentAcademicYear[0].length() - 2);
		
		boolean result = false;
		List ids = new LinkedList<Integer>();
		List<Student> studentList = new ArrayList<Student>();
		for (String id : studentIds) {
			String[] idAdmNo = id.split("~");
			ids.add(Integer.valueOf(idAdmNo[0]));
			Student std = getStudentDetails(Long.valueOf(idAdmNo[0]));
			String qualification = getQualification(std.getQualification(),std.getAge()-1);
			std.setQualification(qualification);
			studentList.add(std);
		}
		if (new studentDetailsDAO().promoteOrDemoteMultiple(studentList, classStudying, academicYear, "Demote")) {
			result = true;
		}
		return result;
	}

	public String pendingApprovals() {
		
        String searchQuery = "From Parents as parent where parent.Student.passedout = 0 AND parent.Student.droppedout = 0 AND parent.Student.archive = 0";
        
        if(!request.getParameter("centercode").equalsIgnoreCase("")) {
            String[] centerCode = request.getParameter("centercode").split(":");
            searchQuery = searchQuery+" AND parent.Student.centercode = '"+centerCode[0]+"'";
            httpSession.setAttribute("printcentername", "Center Code/Name: "+centerCode[0]+"/"+centerCode[1]);
            httpSession.setAttribute("studentviewallcenter", centerCode[0]+":"+centerCode[1]);
        }else {
       	 httpSession.setAttribute("studentviewallcenter", "");
        }
        
        searchQuery = searchQuery+" AND parent.Student.remarks = 'underapproval' Order By parent.Student.admissionnumber ASC";
        
        List<Parents> parentsList = new studentDetailsDAO().getStudentsList(searchQuery);
        
        request.setAttribute("studentListPendingApproval", parentsList);
        request.setAttribute("noOfPages", 1);
        request.setAttribute("currentPage", 1);
        request.setAttribute("totalstudents", parentsList.size());

        if("1".equalsIgnoreCase(httpSession.getAttribute("branchid").toString())) {
        	new BranchService(request, response).viewBranches();
        	return "pendingapprovals.jsp";
        }else {
        	new BranchService(request, response).viewBranchesCenter();
        	return "pendingapprovalscenter.jsp";
        }
        
	}

	public boolean approveRecords() {
		
		String[] studentIds = request.getParameterValues("studentIDs");
		if (studentIds != null) {
			List ids = new ArrayList();
			for (String id : studentIds) {
				ids.add(Integer.valueOf(id));
			}
			if(new studentDetailsDAO().approveRecords(ids)) {
			    return true;
			}
		}
		return false;
	}

	public void rejectRecords() {
		String[] studentIds = request.getParameterValues("studentIDs");

		if (studentIds != null) {
			List ids = new ArrayList();
			for (String id : studentIds) {
				ids.add(Integer.valueOf(id));

			}
			new studentDetailsDAO().rejectRecords(ids);
		}
	}

	public String rejectedApprovals() {
		
        String searchQuery = "From Parents as parent where parent.Student.passedout = 0 AND parent.Student.droppedout = 0 AND parent.Student.archive = 0";
        
        if(!request.getParameter("centercode").equalsIgnoreCase("")) {
            String[] centerCode = request.getParameter("centercode").split(":");
            searchQuery = searchQuery+" AND parent.Student.centercode = '"+centerCode[0]+"'";
            httpSession.setAttribute("printcentername", "Center Code/Name: "+centerCode[0]+"/"+centerCode[1]);
            httpSession.setAttribute("studentviewallcenter", centerCode[0]+":"+centerCode[1]);
        }else {
       	 httpSession.setAttribute("studentviewallcenter", "");
        }
        
        searchQuery = searchQuery+" AND parent.Student.remarks = 'rejected' Order By parent.Student.admissionnumber ASC";
        
        List<Parents> parentsList = new studentDetailsDAO().getStudentsList(searchQuery);
        
        request.setAttribute("studentListRejectedApproval", parentsList);
        request.setAttribute("noOfPages", 1);
        request.setAttribute("currentPage", 1);
        request.setAttribute("totalstudents", parentsList.size());

        if("1".equalsIgnoreCase(httpSession.getAttribute("branchid").toString())) {
        	new BranchService(request, response).viewBranches();
        	return "rejectedapprovals.jsp";
        }else {
        	new BranchService(request, response).viewBranchesCenter();
        	return "rejectedapprovalscenter.jsp";
        }
        
	}

	public void approvalsHistory() {
		
		String fromDate = DateUtil.dateFromatConversion(DataUtil.emptyString(request.getParameter("fromdate")));
		String toDate = DateUtil.dateFromatConversion(DataUtil.emptyString(request.getParameter("todate")));

	    String searchQuery = "From Parents as parents where parents.Student.archive=0 and parents.Student.passedout=0 and parents.Student.droppedout=0 and parents.Student.remarks = 'approved' and parents.Student.approvedon between '"+fromDate+"' and '"+toDate+"' Order by parents.Student.centercode ASC";
	    List<Parents> studentList = new studentDetailsDAO().getStudentsList(searchQuery);
	    request.setAttribute("studentList", studentList);
		
	}
}
