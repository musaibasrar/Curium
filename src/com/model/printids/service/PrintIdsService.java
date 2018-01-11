package com.model.printids.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.model.feesdetails.dao.feesDetailsDAO;
import com.model.feesdetails.dto.Feesdetails;
import com.model.parents.dao.parentsDetailsDAO;
import com.model.parents.dto.Parents;
import com.model.printids.dao.PrintIdsDAO;
import com.model.std.dto.Classsec;
import com.model.student.dao.studentDetailsDAO;
import com.model.student.dto.Student;
import com.model.student.dto.Studentfeesstructure;
import com.model.user.dao.UserDAO;
import com.util.DataUtil;
import com.util.DateUtil;

public class PrintIdsService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;

	public PrintIdsService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

	public boolean addStudent() {
		System.out.println("In add contact of student service");
		Student student = new Student();
		Parents parents = new Parents();

		student.setName(DataUtil.emptyString(request.getParameter("name")));
		student.setGender(DataUtil.emptyString(request.getParameter("gender")));
		student.setDateofbirth(DateUtil.simpleDateParser(request.getParameter("dateofbirth")));
		student.setAge(DataUtil.parseInt(request.getParameter("age")));

		String addClass = request.getParameter("addclass");
		String addSec = request.getParameter("addsec");
		String conClassStudying = "";
		if (!addClass.equalsIgnoreCase("Class")) {

			conClassStudying = addClass;

		}
		if (!addSec.equalsIgnoreCase("Sec")) {
			conClassStudying = conClassStudying + " " + addSec;
		}

		student.setClassstudying(DataUtil.emptyString(conClassStudying));

		String addClassE = request.getParameter("admclassE");
		String addSecE = request.getParameter("admsecE");
		String conClassAdmittedIn = "";
		if (!addClassE.equalsIgnoreCase("Class")) {
			conClassAdmittedIn = addClassE;

		}
		if (!addSecE.equalsIgnoreCase("Sec")) {
			conClassAdmittedIn = conClassAdmittedIn + " " + addSecE;
		}

		student.setClassadmittedin(DataUtil.emptyString(conClassAdmittedIn));

		student.setStdlaststudied(DataUtil.emptyString(request.getParameter("lastclass")));
		student.setSchoollastattended(DataUtil.emptyString(request.getParameter("lastschool")));
		student.setAdmissionnumber(DataUtil.emptyString(request.getParameter("admnno")));
		student.setAdmissiondate(DateUtil.simpleDateParser(request.getParameter("dateofadmission")));
		student.setBloodgroup(DataUtil.emptyString(request.getParameter("bloodgroup")));
		student.setNationality(DataUtil.emptyString(request.getParameter("nationality")));
		student.setReligion(DataUtil.emptyString(request.getParameter("religion")));
		student.setCaste(DataUtil.emptyString(request.getParameter("caste")));
		student.setMothertongue(DataUtil.emptyString(request.getParameter("motherT")));
		student.setCreateddate(DateUtil.simpleDateParser(request.getParameter("createddate")));
		student.setRemarks(DataUtil.emptyString(request.getParameter("remarks")));
		student.setArchive(0);

		// parents.setSid(student.getSid());
		parents.setFathersname(DataUtil.emptyString(request.getParameter("fathersname")));
		parents.setMothersname(DataUtil.emptyString(request.getParameter("mothersname")));
		parents.setProfesssion(DataUtil.emptyString(request.getParameter("profession")));
		parents.setParentsannualincome(DataUtil.emptyString(request.getParameter("parentsannualincome")));
		parents.setAddresspermanent(DataUtil.emptyString(request.getParameter("permanentaddress")));
		parents.setAddresstemporary(DataUtil.emptyString(request.getParameter("temporaryaddress")));
		parents.setNoofdependents(DataUtil.parseInt(request.getParameter("noofdependents")));
		parents.setRemarks(DataUtil.emptyString(request.getParameter("remarks")));
		parents.setContactnumber(DataUtil.emptyString(request.getParameter("contactnumber")));
		parents.setCocontactnumber(DataUtil.emptyString(request.getParameter("cocontactnumber")));

		// student.setParents(parents);
		parents.setStudent(student);

		// student = new studentDetailsDAO().create(student);
		parents = new parentsDetailsDAO().create(parents);

		return true;

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

			String currentYear = (String) httpSession.getAttribute("currentAcademicYear");
			List<Feesdetails> feesdetails = new feesDetailsDAO().readList(id, currentYear);
			List<Studentfeesstructure> feesstructure = new studentDetailsDAO().getStudentFeesStructure(id, currentYear);
			String sumOfFees = new feesDetailsDAO().feesSum(id, currentYear);
			String totalFees = new feesDetailsDAO().feesTotal(id, currentYear);
			String dueAmount = new feesDetailsDAO().dueAmount(id, currentYear);
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
				httpSession.setAttribute("academicPerYear", currentYear);
				/*
				 * if(sumOfFees!= null && !dueAmount.equalsIgnoreCase("")){
				 * sumOfFees = sumOfFees.substring(0, sumOfFees.indexOf('.'));
				 * dueAmount = dueAmount.substring(0, dueAmount.indexOf('.'));
				 * httpSession.setAttribute("feespaid",
				 * (Integer.parseInt(sumOfFees) - Integer.parseInt(dueAmount)));
				 * }else{ httpSession.setAttribute("feespaid", 0); }
				 */

				result = true;
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
		id = request.getParameter("id");
		String pid = "";
		pid = request.getParameter("idparents");

		System.out.println("THE ID IS: " + id + "," + pid);

		int studentId = 0;
		int parentsId = 0;
		studentId = Integer.parseInt(id);
		if (pid != "") {

			parentsId = Integer.parseInt(pid);
		}

		System.out.println("M in in personal service and ID is :::::::::::::::::::::::::::::: " + studentId);

		student.setSid(studentId);
		student.setName(DataUtil.emptyString(request.getParameter("name")));
		student.setGender(DataUtil.emptyString(request.getParameter("gender")));
		student.setDateofbirth(DateUtil.datePars(request.getParameter("dateofbirth")));
		student.setAge(DataUtil.parseInt(request.getParameter("age")));

		String addClass = request.getParameter("classsec");
		String addSec = request.getParameter("secstudying");

		String conClassStudying = "";
		if (!addClass.equalsIgnoreCase("") || !addClass.equalsIgnoreCase(" ")) {

			conClassStudying = addClass;

		}
		if (!addSec.equalsIgnoreCase("") || !addSec.equalsIgnoreCase(" ")) {
			conClassStudying = conClassStudying + " " + addSec;
		}

		student.setClassstudying(DataUtil.emptyString(conClassStudying));

		String addClassE = request.getParameter("admclass");
		String addSecE = request.getParameter("admsec");

		String conClassAdmittedIn = "";
		if (!addClassE.equalsIgnoreCase("") || !addClassE.equalsIgnoreCase(" ")) {
			conClassAdmittedIn = addClassE;

		}
		if (!addSecE.equalsIgnoreCase("") || !addSecE.equalsIgnoreCase(" ")) {
			conClassAdmittedIn = conClassAdmittedIn + " " + addSecE;
		}

		student.setClassadmittedin(DataUtil.emptyString(conClassAdmittedIn));

		student.setStdlaststudied(DataUtil.emptyString(request.getParameter("lastclass")));
		student.setSchoollastattended(DataUtil.emptyString(request.getParameter("lastschool")));
		student.setAdmissionnumber(DataUtil.emptyString(request.getParameter("admnno")));
		student.setAdmissiondate(DateUtil.datePars(request.getParameter("dateofadmission")));
		student.setBloodgroup(DataUtil.emptyString(request.getParameter("bloodgroup")));
		student.setNationality(DataUtil.emptyString(request.getParameter("nationality")));
		student.setReligion(DataUtil.emptyString(request.getParameter("religion")));
		student.setCaste(DataUtil.emptyString(request.getParameter("caste")));
		student.setMothertongue(DataUtil.emptyString(request.getParameter("motherT")));
		student.setCreateddate(DateUtil.datePars(request.getParameter("createddate")));
		student.setRemarks(DataUtil.emptyString(request.getParameter("remarks")));
		student.setArchive(0);

		parents.setPid(parentsId);
		parents.setSid(studentId);
		parents.setFathersname(DataUtil.emptyString(request.getParameter("fathersname")));
		parents.setMothersname(DataUtil.emptyString(request.getParameter("mothersname")));
		parents.setProfesssion(DataUtil.emptyString(request.getParameter("profession")));
		parents.setParentsannualincome(DataUtil.emptyString(request.getParameter("annualincome")));
		parents.setAddresspermanent(DataUtil.emptyString(request.getParameter("permanentaddress")));
		parents.setAddresstemporary(DataUtil.emptyString(request.getParameter("temporaryaddress")));
		parents.setNoofdependents(DataUtil.parseInt(request.getParameter("noofdependents")));
		parents.setRemarks(DataUtil.emptyString(request.getParameter("remarks")));
		parents.setContactnumber(DataUtil.emptyString(request.getParameter("contactnumber")));
		parents.setCocontactnumber(DataUtil.emptyString(request.getParameter("cocontactnumber")));

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
		nameOfFile = nameOfFile + ".xls";
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

			// Start creating an excel file
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("ErrorList");
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

			try {

				String xlsFileName = pathOfStudentReports + nameOfFile;
				FileOutputStream out = new FileOutputStream(new File(xlsFileName));
				workbook.write(out);
				out.close();
				writeSucees = true;
				System.out.println("Excel written successfully...");

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO: handle exception
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

	public void searchDetails() {
		String queryMain = "From Parents as parents where";
		String studentname = DataUtil.emptyString(request
				.getParameter("namesearch"));

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
			querySub = " parents.Student.name like '%" + studentname + "%' AND parents.Student.archive=0";
		}

		if (!classStudying.equalsIgnoreCase("")
				&& !querySub.equalsIgnoreCase("")) {
			querySub = querySub + " AND parents.Student.classstudying like '"
					+ classStudying + "' AND parents.Student.archive=0";
		} else if (!classStudying.equalsIgnoreCase("")) {
			querySub = querySub + " parents.Student.classstudying like '"
					+ classStudying + "' AND parents.Student.archive=0";
		}

		queryMain = queryMain + querySub;
		/*
		 * queryMain =
		 * "FROM Parents as parents where  parents.Student.dateofbirth = '2006-04-06'"
		 * ;
		 */
		System.out.println("SEARCH QUERY ***** " + queryMain);
		List<Parents> searchStudentList = new UserDAO()
				.getListOfStudents(queryMain);
		request.setAttribute("searchStudentList", searchStudentList);
		
	}

	public boolean printMultiple() {
		boolean result = false;
        String[] studentIDs = request.getParameterValues("studentIDs");
        List<Long> ids = new ArrayList<Long>();
        Parents parentsDetails = new Parents();
     
        	
          int i = 1;
       
          for (String id : studentIDs) {

              
               System.out.println("Value of i is " + i);
               int sid = Integer.valueOf(id);
               parentsDetails = new PrintIdsDAO().printMultipleIds(id);
               
               //PersonalDetails personal = new PersonalDetailsDAO().printMultiple(pid);

               if (parentsDetails != null) {
                   httpSession.setAttribute("studentname" + i + "", parentsDetails.getStudent().getName());
                   httpSession.setAttribute("fathersname" + i + "", parentsDetails.getFathersname());
                   httpSession.setAttribute("class" + i + "", parentsDetails.getClass());
                   httpSession.setAttribute("Address" + i + "", parentsDetails.getAddresspermanent());
                   httpSession.setAttribute("Contactnumber" + i + "", parentsDetails.getContactnumber());
                   httpSession.setAttribute("studentpic" + i + "",parentsDetails.getStudent().getStudentpic());
                   
                   //result = true;
               } else {

                  
                   //result = false;
               }

               i++;
           }
       
       httpSession.setAttribute("iInitial", i);
       i = (int) (Math.ceil((float) (i) / 3));
       httpSession.setAttribute("endValue", i);
       
       
        if (parentsDetails == null) {
            result = false;
        } else {
            result = true;
        }
        return result;

}
	
}
