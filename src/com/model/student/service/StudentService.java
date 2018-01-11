package com.model.student.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
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
import com.model.department.dao.departmentDAO;
import com.model.department.dto.Department;
import com.model.feesdetails.dao.feesDetailsDAO;
import com.model.feesdetails.dto.Feesdetails;
import com.model.parents.dao.parentsDetailsDAO;
import com.model.parents.dto.Parents;
import com.model.position.dao.positionDAO;
import com.model.position.dto.Position;
import com.model.std.dto.Classsec;
import com.model.student.dao.studentDetailsDAO;
import com.model.student.dto.Student;
import com.model.student.dto.Studentfeesstructure;
import com.model.user.dao.UserDAO;
import com.util.DataUtil;
import com.util.DateUtil;

public class StudentService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;
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
		System.out.println("In add contact of student service");
		Student student = new Student();
		Parents parents = new Parents();
		String addClass = null,addSec =null,addClassE=null,addSecE=null,conClassStudying=null,conClassAdmittedIn=null;
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

		                	student.setDateofbirth(DateUtil.dateParserUpdateStd(item.getString()));

		                }

		                if (fieldName.equalsIgnoreCase("age")) {
		                    
		                    student.setAge(DataUtil.parseInt(item.getString()));

		                }

		                if (fieldName.equalsIgnoreCase("addclass")) {
		                    
		                    addClass = DataUtil.emptyString(item.getString());
		                    if (!addClass.equalsIgnoreCase("Class")) {

			        			conClassStudying = addClass;

			        		}
		                }

		                

		                if (fieldName.equalsIgnoreCase("addsec")) {

		                    addSec = DataUtil.emptyString(item.getString());
		                    if (!addSec.equalsIgnoreCase("Sec")) {
			        			conClassStudying = conClassStudying + " " + addSec;
			        		}
		                }

		                

		        		
		        		
		        		

		        		student.setClassstudying(DataUtil.emptyString(conClassStudying));
		                
		                
		                if (fieldName.equalsIgnoreCase("admclassE")) {
		                    
		                    addClassE = DataUtil.emptyString(item.getString());

		                    if (!addClassE.equalsIgnoreCase("Class")) {
			        			conClassAdmittedIn = addClassE;

			        		}

		                }



		                if (fieldName.equalsIgnoreCase("admsecE")) {

		                    
		                    addSecE = DataUtil.emptyString(item.getString());
			        		if (!addSecE.equalsIgnoreCase("Sec")) {
			        			conClassAdmittedIn = conClassAdmittedIn + " " + addSecE;
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
		                	student.setAdmissiondate(DateUtil.dateParserUpdateStd(item.getString()));
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
		                    student.setCreateddate(DateUtil.dateParserUpdateStd(item.getString()));
		                }

		                if (fieldName.equalsIgnoreCase("remarks")) {
		                	student.setRemarks(DataUtil.emptyString(item.getString()));
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
		                
		                if (fieldName.equalsIgnoreCase("remarks")) {
		                	parents.setRemarks(DataUtil.emptyString(item.getString()));
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
		                
		            } else {
		                // Process form file field (input type="file").
		                String fieldName = item.getFieldName();

		                //  InputStream filecontent;
		                System.out.println("fieldname:" + fieldName);
		                // System.out.println("filename:" + fileName);
		                if (fieldName.equalsIgnoreCase("fileToUpload")) {


		                    String fileName = (DataUtil.emptyString(item.getName()));
		                    String fileValue = (DataUtil.emptyString(item.getString()));
		                    //System.out.println("File value is "+fileValue);
		                    
		                    System.out.println("file name:" + fileName);
		                    if (!fileName.equalsIgnoreCase("")) {
		                       
		                    	// encode data on your side using BASE64
		                    	//byte[]   bytesEncoded = Base64.encodeBase64(fileValue.getBytes());
		                    	byte[]   bytesEncoded = Base64.encodeBase64(item.get());
		                    	System.out.println("ecncoded value is " + new String(bytesEncoded ));
		                    	String saveFile = new String(bytesEncoded);
		                    	// Decode data on other side, by processing encoded data
		                    	/*byte[] valueDecoded= Base64.decodeBase64(bytesEncoded );
		                    	System.out.println("Decoded value is " + new String(valueDecoded));*/
		                    	
		                    	// Resize the image
                    	

		                    	/*Properties properties = new Properties();
		                        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("Backuplocation.properties");
		                        properties.load(inputStream);
		                        int picWidth = Integer.parseInt(properties.getProperty("widthofpic"));
		                        int picHeight = Integer.parseInt(properties.getProperty("heightofpic"));
		                    	
		                    	BufferedImage originalImage = ImageIO.read(item.getInputStream());
									int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
			                		
			                		//BufferedImage resizeImageJpg = resizeImage(originalImage, type);
			                		BufferedImage resizedImage = new BufferedImage(picWidth, picHeight, type);
			                		Graphics2D g = resizedImage.createGraphics();
			                		g.drawImage(originalImage, 0, 0, picWidth, picHeight, null);
			                		g.dispose();
								
								ByteArrayOutputStream baos = new ByteArrayOutputStream();
								ImageIO.write( resizedImage, "jpg", baos );
								baos.flush();
								byte[] imageInByte = baos.toByteArray();
								baos.close();
								byte[]   bytesEncoded = Base64.encodeBase64(imageInByte);
								String saveFile = new String(bytesEncoded);*/
		                    	
		                    	//End resizing of image
		                    	
		                    	student.setStudentpic(saveFile);

		                    } else {
		                       // personal.setPhoto(fileName);
		                    }
		                }
		            }


		        }


		
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		student.setArchive(0);

		// student.setParents(parents);
		parents.setStudent(student);

		// student = new studentDetailsDAO().create(student);
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
					recordsPerPage);
			int noOfRecords = new studentDetailsDAO().getNoOfRecords();
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

			httpSession.setAttribute("studentfromservice",student);
			httpSession.setAttribute("parentsfromservice",parents);
			httpSession.setAttribute("idofstudentfromservice",id);
			
			Currentacademicyear currentYear = new YearDAO().showYear();
			httpSession.setAttribute("currentyearfromservice",currentYear.getCurrentacademicyear());
			List<Feesdetails> feesdetails = new feesDetailsDAO().readList(id, currentYear.getCurrentacademicyear());
			httpSession.setAttribute("feesdetailsfromservice",feesdetails);
			List<Studentfeesstructure> feesstructure = new studentDetailsDAO().getStudentFeesStructure(id, currentYear.getCurrentacademicyear());
			String sumOfFees = new feesDetailsDAO().feesSum(id, currentYear.getCurrentacademicyear());
			String totalFees = new feesDetailsDAO().feesTotal(id, currentYear.getCurrentacademicyear());
			String dueAmount = new feesDetailsDAO().dueAmount(id, currentYear.getCurrentacademicyear());
			if (student == null) {
				result = false;
			} else {
				httpSession.setAttribute("student", student);
				String classStudying = student.getClassstudying();
				if (!classStudying.equalsIgnoreCase("") && !classStudying.equalsIgnoreCase(" ") && classStudying.contains(" ")) {
					/*String[] parts = classStudying.split("");
					String[] classParts = classStudying.split(" ");
					String regex = "[a-zA-Z]+";
					int j = 0;
					for (int i = 0; i < parts.length; i++) {

						if (parts[i].matches(regex) == true && j == 0) {
							httpSession.setAttribute("classstudying", parts[i]);
							j++;
						} else if (parts[i].matches(regex) == true && j != 0) {
							httpSession.setAttribute("secstudying", parts[i]);
						}

						// httpSession.setAttribute("classstudying", parts[0]);
						// httpSession.setAttribute("secstudying", parts[1]);
					}

				} else {
					request.setAttribute("classstudying", classStudying);
				}*/
					
					String regex = "[a-zA-Z]+";
					String[] classParts = classStudying.split(" ");
					httpSession.setAttribute("classstudying", classParts[0]);
					
					if(classParts.length>=2 && classParts[1].matches(regex) == true ){
					httpSession.setAttribute("secstudying", classParts[1]);
					}else{
						httpSession.setAttribute("secstudying", "");
					}

				} else {
					request.setAttribute("classstudying", classStudying);
					request.setAttribute("secstudying", "");
				}

				String classAdmitted = student.getClassadmittedin();
				
				if (!classAdmitted.equalsIgnoreCase("")  && !classAdmitted.equalsIgnoreCase(" ") && classAdmitted.contains(" ")) {

					String regex = "[a-zA-Z]+";
					String[] classAdmittedParts = classAdmitted.split(" ");
					
					httpSession.setAttribute("classadm", classAdmittedParts[0]);
					if(classAdmittedParts.length>=2 &&  classAdmittedParts[1].matches(regex) == true){
					httpSession.setAttribute("secadm", classAdmittedParts[1]);
					}else{
						httpSession.setAttribute("secadm", "");
					}
				} else {
					request.setAttribute("classadm", classAdmitted);
					request.setAttribute("secadm", "");
				}

				httpSession.setAttribute("parents", parents);
				httpSession.setAttribute("feesdetails", feesdetails);
				httpSession.setAttribute("feesstructure", feesstructure);
				httpSession.setAttribute("sumoffees", sumOfFees);
				httpSession.setAttribute("dueamount", dueAmount);
				httpSession.setAttribute("totalfees", totalFees);
				httpSession.setAttribute("academicPerYear", currentYear.getCurrentacademicyear());
				httpSession.setAttribute("currentAcademicYear", currentYear.getCurrentacademicyear());
				
				
				/*
				 * if(sumOfFees!= null && !dueAmount.equalsIgnoreCase("")){
				 * sumOfFees = sumOfFees.substring(0, sumOfFees.indexOf('.'));
				 * dueAmount = dueAmount.substring(0, dueAmount.indexOf('.'));
				 * httpSession.setAttribute("feespaid",
				 * (Integer.parseInt(sumOfFees) - Integer.parseInt(dueAmount)));
				 * }else{ httpSession.setAttribute("feespaid", 0); }
				 */

				result = true;
				httpSession.setAttribute("resultfromservice",result);
			}
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

	        		
	        		
	        		System.out.println("M in in personal service and ID is :::::::::::::::::::::::::::::: " + studentId);
	                
	                
	                if (fieldName.equalsIgnoreCase("name")) {
	                    
	                    student.setName(DataUtil.emptyString(item.getString()));
	                    System.out.println("name==" + item.getString());
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

	                if (fieldName.equalsIgnoreCase("classsec")) {
	                    
	                    addClass = DataUtil.emptyString(item.getString());
	                    if (!addClass.equalsIgnoreCase("Class")) {

		        			conClassStudying = addClass;

		        		}
	                }

	                

	                if (fieldName.equalsIgnoreCase("secstudying")) {

	                    addSec = DataUtil.emptyString(item.getString());
	                    if (!addSec.equalsIgnoreCase("Sec")) {
		        			conClassStudying = conClassStudying + " " + addSec;
		        		}
	                }

	        		student.setClassstudying(DataUtil.emptyString(conClassStudying));
	                
	                
	                if (fieldName.equalsIgnoreCase("admclass")) {
	                    
	                    addClassE = DataUtil.emptyString(item.getString());

	                    if (!addClassE.equalsIgnoreCase("Class")) {
		        			conClassAdmittedIn = addClassE;

		        		}

	                }



	                if (fieldName.equalsIgnoreCase("admsec")) {

	                    
	                    addSecE = DataUtil.emptyString(item.getString());
		        		if (!addSecE.equalsIgnoreCase("Sec")) {
		        			conClassAdmittedIn = conClassAdmittedIn + " " + addSecE;
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
	                	student.setAdmissiondate(DateUtil.dateParserUpdateStd(item.getString()));
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
	                    student.setCreateddate(DateUtil.dateParserUpdateStd(item.getString()));
	                }

	                if (fieldName.equalsIgnoreCase("remarks")) {
	                	student.setRemarks(DataUtil.emptyString(item.getString()));
	                }
	                
	                if(fieldName.equalsIgnoreCase("studentpicupdate")){
	                	studentPicUpdate=DataUtil.emptyString(item.getString());
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
	                
	                if (fieldName.equalsIgnoreCase("remarks")) {
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
	                // Process form file field (input type="file").
	                String fieldName = item.getFieldName();

	                //  InputStream filecontent;
	                System.out.println("fieldname:" + fieldName);
	                // System.out.println("filename:" + fileName);
	                if (fieldName.equalsIgnoreCase("fileToUpload")) {


	                    String fileName = (DataUtil.emptyString(item.getName()));
	                    String fileValue = (DataUtil.emptyString(item.getString()));
	                    //System.out.println("File value is "+fileValue);
	                    
	                    System.out.println("file name:" + fileName);
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
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		 student.setArchive(0);
		 student = new studentDetailsDAO().update(student);
 		if (pid != "") {
 			parents.setStudent(student);
 			parents = new parentsDetailsDAO().update(parents);
 		}
		String stId = student.getSid().toString();
        return stId;
		
		
	}

	public boolean viewAllStudentsList() {

		boolean result = false;
		String pages = "1";
		try {

			List<Student> list = new studentDetailsDAO().readListOfStudents();
			request.setAttribute("studentList", list);
			// List<PersonalDetails> list = new
			// PersonalDetailsDAO().readListOfObjects();

			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public void archiveMultiple() {
		String[] studentIds = request.getParameterValues("studentIDs");
		String[] studentNames = request.getParameterValues("studentNames");

		if (studentNames != null) {
			List namesOfStudents = new ArrayList();
			for (String nameof : studentNames) {
				System.out.println("name of students" + nameof);
				// ids.add(Integer.valueOf(id));

			}
		}

		if (studentIds != null) {
			List ids = new ArrayList();
			for (String id : studentIds) {
				System.out.println("id" + id);
				ids.add(Integer.valueOf(id));

			}
			System.out.println("id length" + studentIds.length);
			new studentDetailsDAO().archiveMultiple(ids);
		}
	}

	public boolean viewAllStudentsArchive() {

		boolean result = false;

		try {

			List<Student> list = new studentDetailsDAO().readListOfStudentsArchive();
			request.setAttribute("studentListArchive", list);
			// List<PersonalDetails> list = new
			// PersonalDetailsDAO().readListOfObjects();

			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public void deleteMultiple() {
		String[] studentIds = request.getParameterValues("studentIDs");
		if (studentIds != null) {
			List ids = new ArrayList();
			for (String id : studentIds) {
				System.out.println("id" + id);
				ids.add(Integer.valueOf(id));

			}
			System.out.println("id length" + studentIds.length);
			new studentDetailsDAO().deleteMultiple(ids);
		}
	}

	public void restoreMultiple() {
		String[] studentIds = request.getParameterValues("studentIDs");
		if (studentIds != null) {
			List ids = new ArrayList();
			for (String id : studentIds) {
				System.out.println("id" + id);
				ids.add(Integer.valueOf(id));

			}
			System.out.println("id length" + studentIds.length);
			new studentDetailsDAO().restoreMultiple(ids);
		}
	}

	@SuppressWarnings("finally")
	public boolean searchClass() {
		String classofStd = request.getParameter("classofstd");

		boolean result = false;
		try {
			List<Student> studentList = new studentDetailsDAO().getListOfStudents(classofStd);
			request.setAttribute("studentList", studentList);
			result = true;
		} catch (Exception e) {
			result = false;
		} finally {
			return result;
		}

	}

	public boolean promoteMultiple() {
		String[] studentIds = request.getParameterValues("studentIDs");
		String classStudying = request.getParameter("classstudying");
		boolean result = false;
		List ids = new ArrayList();
		for (String id : studentIds) {
			System.out.println("id" + id);
			ids.add(Integer.valueOf(id));

		}
		System.out.println("id length" + studentIds.length);
		if (new studentDetailsDAO().promoteMultiple(ids, classStudying)) {
			result = true;
		}
		return result;
	}

	public boolean viewAllStudentsParents() {

		boolean result = false;
		String pages = "1";
		try {
			int page = 1;
			int recordsPerPage = 2000;
			if (pages != null) {
				page = Integer.parseInt(pages);
			}

			List<Parents> list = new studentDetailsDAO().readListOfObjectsPaginationALL((page - 1) * recordsPerPage,
					recordsPerPage);
			request.setAttribute("studentList", list);
			// List<String> testList = new ArrayList<String>();
			/*
			 * Parents std; ListIterator<Parents> medicineIterable =
			 * (list.listIterator()); while (medicineIterable.hasNext()) { std =
			 * medicineIterable.next();
			 * 
			 * System.out.println("medicine id " + std.getFathersname());
			 * System.out.println("medicine id " + std.getStudent().getName());
			 * 
			 * }
			 */
			int noOfRecords = new studentDetailsDAO().getNoOfRecords();
			int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
			request.setAttribute("studentList", list);
			request.setAttribute("noOfPages", noOfPages);
			request.setAttribute("currentPage", page);
			List<Department> listDepartment = new departmentDAO().readListOfObjects();
			httpSession.setAttribute("listDepartment", listDepartment);
			List<Position> listPosition = new positionDAO().readListOfObjects();
			httpSession.setAttribute("listPosition", listPosition);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
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
			result = false;
		}
		return result;
	}

	public void studentsDetailsSearch() {

		String queryMain = "From Parents as parents where";
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

		queryMain = queryMain + querySub;
		/*
		 * queryMain =
		 * "FROM Parents as parents where  parents.Student.dateofbirth = '2006-04-06'"
		 * ;
		 */
		System.out.println("SEARCH QUERY ***** " + queryMain);
		List<Parents> searchStudentList = new UserDAO().getListOfStudents(queryMain);
		request.setAttribute("searchStudentList", searchStudentList);

	}

	public boolean exportDataForStudents() {

		String[] studentIds = request.getParameterValues("studentIDs");
		boolean successResult = false;
		String pathOfStudentReports = new DataUtil().getPropertiesValue("studentdetailsreportpath");
		String nameOfFile = request.getParameter("fileName");
		nameOfFile = nameOfFile + ".xlsx";
		List<Parents> listOfStudentRecords = new ArrayList<Parents>();

		if (studentIds != null) {
			for (String id : studentIds) {
				if (id != null || id != "") {
					String queryMain = "From Parents as parents where";
					String querySub = " parents.Student.id = " + id;
					queryMain = queryMain + querySub;

					List<Parents> searchStudentList = new UserDAO().getListOfStudents(queryMain);
					request.setAttribute("searchStudentList", searchStudentList);

					Parents searchStudentRecords = new studentDetailsDAO().getStudentRecords(queryMain);
					listOfStudentRecords.add(searchStudentRecords);
				}

			}
			try {
				if (exportDataToExcel(listOfStudentRecords, pathOfStudentReports, nameOfFile)) {
					successResult = true;
				} else {
					successResult = false;
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return successResult;

	}

	public boolean exportDataToExcel(List<Parents> listOfStudentRecords, String pathOfStudentReports, String nameOfFile)
			throws Exception {

		boolean writeSucees = false;
		// String name =
		// "Error_"+detailsearchList.get(0).getFileName().substring(0,
		// detailsearchList.get(0).getFileName().length()-4)+".xls";
		try {

			// Creating an excel file
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("ErrorList");
			Map<String, Object[]> data = new HashMap<String, Object[]>();
			Map<String, Object[]> headerData = new HashMap<String, Object[]>();
			headerData.put("Header",
					new Object[] { "Student Name", "Gender", "Date Of Birth", "Age", "Studying In Class",
							"Admitted In Class", "Admission Number", "Admission Date", "Blood Group", "Religion",
							"Caste", "Fathers Name", "Mothers Name" });
			int i = 1;
			for (Parents studentDetails : listOfStudentRecords) {
				data.put(Integer.toString(i),
						new Object[] { studentDetails.getStudent().getName(), studentDetails.getStudent().getGender(),
								studentDetails.getStudent().getDateofbirth().toString(),
								studentDetails.getStudent().getAge().toString(),
								studentDetails.getStudent().getClassstudying(),
								studentDetails.getStudent().getClassadmittedin(),
								studentDetails.getStudent().getAdmissionnumber(),
								studentDetails.getStudent().getAdmissiondate().toString(),
								studentDetails.getStudent().getBloodgroup(), studentDetails.getStudent().getReligion(),
								studentDetails.getStudent().getCaste(), studentDetails.getFathersname(),
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
				
				
				ClassLoader classLoader = getClass().getClassLoader();
				FileOutputStream out = new FileOutputStream(new File(classLoader.getResource("").getFile()+nameOfFile));
				//FileOutputStream out = new FileOutputStream(new File("/usr/local/tomcat/webapps/www.searchmysearch.com/files/"+nameOfFile));
				httpSession.setAttribute("filePath",new File(classLoader.getResource("").getFile()+nameOfFile));
				workbook.write(out);
				out.close();
				writeSucees = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return writeSucees;
		// getFile(name, path);
	}

	public static void main(String[] args) {

		String[] studentIds = { "2", "3", "4" };

		for (String id : studentIds) {
			if (id != null) {
				String queryMain = "From Parents as parents where";
				String querySub = " parents.Student.sid =" + id;
				queryMain = queryMain + querySub;

				List<Parents> searchStudentList = new ArrayList<Parents>();
				searchStudentList = new UserDAO().getListOfStudents(queryMain);
				String studentName = searchStudentList.get(0).getStudent().getName();
				System.out.println("The student name is " + studentName);
				// request.setAttribute("searchStudentList", searchStudentList);
			}

		}
	}

	public boolean generateBonafide() {
		// TODO Auto-generated method stub
		return false;
	}

	public void downlaodFile() {
		
		try{
		
        ClassLoader classLoader = getClass().getClassLoader();
        String appPath = classLoader.getResource("Zeee.xls").getFile();
        System.out.println("appPath = " + appPath);
 
        // construct the complete absolute path of the file
        URL url = null;
		URLConnection con = null;
        url = new URL("http://searchmysearch.com/files/Mushy.xlsx");
		con = url.openConnection();
             
		////////////////////////////////
        File downloadFile = new File(classLoader.getResource("Zeee.xls").getFile());
        //FileInputStream inputStream = new FileInputStream(downloadFile);
        FileInputStream inputStream = (FileInputStream) con.getInputStream();
        // get MIME type of the file
        String mimeType = "application/vnd.ms-excel";

        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
 
        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);
 
        // get output stream of the response
        OutputStream outStream = response.getOutputStream();
 
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;
 
        // write bytes read from the input stream into the output stream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
 
        inputStream.close();
        outStream.close();
 
		}catch(Exception e){
		System.out.println(""+e);	
		}
		
	}

}
