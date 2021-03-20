package com.model.documents.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.model.documents.dao.DocumentDAO;
import com.model.documents.dto.Transfercertificate;
import com.model.parents.dto.Parents;
import com.model.std.service.StandardService;
import com.model.student.dao.studentDetailsDAO;
import com.model.student.dto.Student;
import com.util.DataUtil;
import com.util.DateUtil;

public class DocumentService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;
	private String BRANCHID = "branchid";
	/**
    * Size of a byte buffer to read/write file
    */
   private static final int BUFFER_SIZE = 4096;
	
	public DocumentService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}
	
	
	public boolean transferCertificate(){
		
		if(httpSession.getAttribute(BRANCHID)!=null){
			try {
				List<Parents> list = new studentDetailsDAO().getStudentsList("from Parents where branchid = "+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
				request.setAttribute("studentListtc", list);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return false;
		
	}

	public String generateTransferCertificate() {
		Student student = new Student();
		Parents parents = new Parents();
		Transfercertificate tc = new Transfercertificate();
		String transferCertificateString = null;
		
		int studentId = DataUtil.parseInt(request.getParameter("studentId"));
		String leavingReason = DataUtil.emptyString(request.getParameter("reason"));
		Date dateOfTc = DateUtil.dateParserUpdateStd(request.getParameter("dateoftc"));
		
		student.setReasonleaving(leavingReason);
		student.setSid(studentId);
		 boolean updateStudent = new studentDetailsDAO().updateStudent(student);
		 
		 if(updateStudent){
			 tc.setSid(studentId);
			 tc.setApplicationstatus("applied");
			 tc.setDateofissues(dateOfTc);
			 tc.setNoofissues(1);
			 tc.setUserid(Integer.parseInt(httpSession.getAttribute("userloginid").toString()));
			 Transfercertificate transferCertificate = new DocumentDAO().getTransferCertificateDetails(tc.getSid()); 
			 if(transferCertificate != null){
				 return "studentexists";
			 }else {
					transferCertificateString = new DocumentDAO().generateTransferCertificate(tc);
			}
		 }
		 
		 if("true".equalsIgnoreCase(transferCertificateString)){
			 String getStudentInfo  = "from Parents as parents where parents.Student.sid="+studentId;
			 parents = new studentDetailsDAO().getStudentRecords(getStudentInfo);
			 request.setAttribute("studentdetails", parents);
			 request.setAttribute("tcdetails", tc);
			 return "true";
		 }
		 return "false";
	}


	public boolean printTransferCertificate() {
		
		Parents parents = new Parents();
		Transfercertificate tc = new Transfercertificate();
		
		int studentId = DataUtil.parseInt(request.getParameter("id"));
		 
			tc = new DocumentDAO().getTransferCertificateDetails(studentId);
		 
			 String getStudentInfo  = "from Parents as parents where parents.Student.sid="+studentId;
			 parents = new studentDetailsDAO().getStudentRecords(getStudentInfo);
			 request.setAttribute("studentdetails", parents);
			 request.setAttribute("tcdetails", tc);
			 
			 if(tc.getTcid() != null){
				 return true;
			 }else{
				 return false;
			 }
	}


	public boolean admissionAbstract() {
		
		if(httpSession.getAttribute(BRANCHID)!=null){
			try {
				List<Parents> list = new studentDetailsDAO().getStudentsList("from Parents where branchid = "+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
				request.setAttribute("studentListaa", list);
				new StandardService(request, response).viewClasses();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return false;
		
	}


	public boolean searchForStudents() {
		List<Parents> searchStudentList = new ArrayList<Parents>();
		boolean result = false;
		if(httpSession.getAttribute(BRANCHID)!=null){
		
		String queryMain = "From Parents as parents where";
		String studentname = DataUtil.emptyString(request.getParameter("namesearch"));
		String admissionNumber = DataUtil.emptyString(request.getParameter("admno"));
		String addClass = request.getParameter("classsearch");
		String addSec = request.getParameter("secsearch");
		String conClassStudying = "";
		
		if (!addClass.equalsIgnoreCase("")) {

			conClassStudying = addClass+"--" +"%";

		}
		if (!addSec.equalsIgnoreCase("")) {
			conClassStudying = addClass;
			conClassStudying = conClassStudying+"--"+addSec+"%";
		}

		String classStudying = DataUtil.emptyString(conClassStudying);
		String querySub = "";

		if (!studentname.equalsIgnoreCase("")) {
			querySub = " parents.Student.name like '%" + studentname + "%'";
		}

		if (!classStudying.equalsIgnoreCase("")	&& !querySub.equalsIgnoreCase("")) {
			querySub = querySub + " AND parents.Student.classstudying like '"
					+ classStudying + "'";
		} else if (!classStudying.equalsIgnoreCase("")) {
			querySub = querySub + " parents.Student.classstudying like '"
					+ classStudying + "'";
		}

		if (!admissionNumber.equalsIgnoreCase("") && !querySub.equalsIgnoreCase("")) {
			querySub = querySub + " AND parents.Student.admissionnumber = '"+admissionNumber+"'";
		}else if(!admissionNumber.equalsIgnoreCase("")) {
			querySub = querySub + " parents.Student.admissionnumber = '"+admissionNumber+"'";
		}
		
		if(!"".equalsIgnoreCase(querySub)) {
			queryMain = queryMain + querySub + " AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.branchid="+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())+" order by parents.Student.admissionnumber ASC";
			System.out.println("QUERY*********** "+queryMain);
			searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
		}
		result = true;
	}
		request.setAttribute("searchStudentList", searchStudentList);
		return result;

	}


	public boolean exportAdmissionAbstract() {

		String[] studentIds = request.getParameterValues("studentIDs");
		boolean successResult = false;
		
		List<Parents> listOfStudentRecords = new LinkedList<Parents>();

		if (studentIds != null) {
			for (String id : studentIds) {
				if (id != null || id != "") {
					String queryMain = "From Parents as parents where parents.branchid="+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())+" AND parents.Student.id = "+id+" order by parents.Student.admissionnumber ASC";

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


	private boolean exportDataToExcel(List<Parents> listOfStudentRecords) {

		boolean writeSucees = false;
		
		try {

			// Creating an excel file
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("Page 1");
			XSSFSheet sheetTwo = workbook.createSheet("Page 2");
			Map<String, Object[]> data = new HashMap<String, Object[]>();
			Map<String, Object[]> dataTwo = new HashMap<String, Object[]>();
			Map<String, Object[]> headerDataTop = new HashMap<String, Object[]>();
			Map<String, Object[]> headerDataSN = new HashMap<String, Object[]>();
			Map<String, Object[]> headerData = new HashMap<String, Object[]>();
			Map<String, Object[]> headerDataTwo = new HashMap<String, Object[]>();
			
			headerDataTop.put("HeaderTop",
					new Object[] { "ADMISSION ABSTRACT"});
			
			headerDataSN.put("HeaderSN",
					new Object[] { "Name of school: "});
			
			headerData.put("Header",
					new Object[] { "Admission No.", "Cumulative Record No. with date of opening", "Name in Full", "Boy or Girl", "Date of Birth",
							"Father and mother Name and occupation", "Parents Annual Income", "Number Of Dependence", "Nationality, Religion and caste",
							"Mother Tongue", "Guardian Name and Address"});
			
			headerDataTwo.put("Header",
					new Object[] { "Permanent address of the Pupil", "Last schoool attended", "Standard last studied", "No.& date of transfer certificate",
							"Standard to which admitted with school", "Date of admission", "Subsequent progress", "Class of leaving",
							"Date of leaving school", "Reason for leaving", "No. & date of transfer cerificate issed", "Remarks"});
			
			int i = 1;
			for (Parents studentDetails : listOfStudentRecords) {
				data.put(Integer.toString(i),
						new Object[] { studentDetails.getStudent().getAdmissionnumber(), studentDetails.getStudent().getCrecord()+"/"+studentDetails.getStudent().getCrecorddate(),
								studentDetails.getStudent().getName(),
								studentDetails.getStudent().getGender(),
								getStringDate(studentDetails.getStudent().getDateofbirth()),
								studentDetails.getFathersname()+" / "+studentDetails.getMothersname(),
								studentDetails.getParentsannualincome(),
								studentDetails.getNoofdependents(),
								studentDetails.getStudent().getNationality()+","+studentDetails.getStudent().getReligion()+" and "+studentDetails.getStudent().getCaste(),
								studentDetails.getStudent().getMothertongue(), ""});
				
				dataTwo.put(Integer.toString(i),
						new Object[] { studentDetails.getAddresspermanent(), studentDetails.getStudent().getSchoollastattended(),
								studentDetails.getStudent().getStdlaststudied(),
								studentDetails.getStudent().getNooftc()+"/"+studentDetails.getStudent().getDateoftc(),
								studentDetails.getStudent().getClassadmittedin(),
								getStringDate(studentDetails.getStudent().getAdmissiondate()),
								//studentDetails.getStudent().getSubsequentprogress(),
								studentDetails.getStudent().getClassonleaving(),
								getStringDate(studentDetails.getStudent().getDateleaving()),
								studentDetails.getStudent().getReasonleaving(),
								studentDetails.getStudent().getNotcissued()+"/"+studentDetails.getStudent().getDatetcissued(),
								studentDetails.getStudent().getRemarks()});
				i++;
			}
			
			//Fonts
			 XSSFFont font= workbook.createFont();
			    font.setFontHeightInPoints((short)20);
			    font.setFontName("Arial");
			    font.setColor(IndexedColors.BLACK.getIndex());
			    font.setBold(true);
			    font.setItalic(false);
			    
			 XSSFFont headerFont= workbook.createFont();
			 	headerFont.setFontName("Arial");
			 	headerFont.setColor(IndexedColors.BLACK.getIndex());
			 	headerFont.setBold(true);
			 	headerFont.setItalic(false);    

			
			//styles
			 XSSFCellStyle style = workbook.createCellStyle();
			 style.setFont(font);
			 
			 XSSFCellStyle stylePage2 = workbook.createCellStyle();
			 stylePage2.setFont(headerFont);
			 
			 XSSFCellStyle styleHeader = workbook.createCellStyle();
			 styleHeader.setFont(headerFont);
			 styleHeader.setWrapText(true);
			 styleHeader.setAlignment(HorizontalAlignment.CENTER); 
			 styleHeader.setRotation((short)90);
			 styleHeader.setBorderBottom(BorderStyle.THIN);
			 styleHeader.setBorderTop(BorderStyle.THIN);
			 styleHeader.setBorderRight(BorderStyle.THIN);
			 styleHeader.setBorderLeft(BorderStyle.THIN);
			 
			 XSSFCellStyle styleRows = workbook.createCellStyle();
			 styleRows.setBorderTop(BorderStyle.THIN);
			 styleRows.setBorderRight(BorderStyle.THIN);
			 styleRows.setBorderLeft(BorderStyle.THIN);
			 
			 //End Style
			 
			// Sheet One
			
			Row headerRow = sheet.createRow(0);
			Object[] objArrHeaderTop = headerDataTop.get("HeaderTop");
			int cellnumber = 2;
			for (Object obj : objArrHeaderTop) {
				Cell cell = headerRow.createCell(cellnumber++);
				if (obj instanceof String)
					cell.setCellValue((String) obj);
					cell.setCellStyle(style);
			}
			
			Row headerRowSN = sheet.createRow(2);
			Object[] objArrHeaderSN = headerDataSN.get("HeaderSN");
			int cellnumSN = 2;
			for (Object obj : objArrHeaderSN) {
				Cell cell = headerRowSN.createCell(cellnumSN++);
				if (obj instanceof String)
					cell.setCellValue((String) obj);
					cell.setCellStyle(stylePage2);
			}
			
			Row headerRowT = sheet.createRow(4);
			Object[] objArrHeader = headerData.get("Header");
			int cellnum1 = 0;
			for (Object obj : objArrHeader) {
				Cell cell = headerRowT.createCell(cellnum1++);
				if (obj instanceof String)
					cell.setCellValue((String) obj);
					cell.setCellStyle(styleHeader);
			}
			
			Set<String> keyset = data.keySet();
			int rownum = 5;
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
					
					cell.setCellStyle(styleRows);
				}
			}
			
			// Sheet Two
			Row headerRowTwo = sheetTwo.createRow(0);
			Object[] objArrHeaderTwo = headerDataTwo.get("Header");
			int cellnumTwo = 0;
			for (Object obj : objArrHeaderTwo) {
				Cell cell = headerRowTwo.createCell(cellnumTwo++);
				if (obj instanceof String)
					cell.setCellValue((String) obj);
					cell.setCellStyle(styleHeader);
			}
			Set<String> keysetTwo= dataTwo.keySet();
			int rownumTwo = 1;
			for (String key : keysetTwo) {
				Row row = sheetTwo.createRow(rownumTwo++);
				Object[] objArr = dataTwo.get(key);
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
					cell.setCellStyle(styleRows);
				}
			}
			
			
				FileOutputStream out = new FileOutputStream(new File(System.getProperty("java.io.tmpdir")+"/admissionabstract.xlsx"));
				workbook.write(out);
				out.close();
				writeSucees = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return writeSucees;
	}


	private Object getStringDate(Date date) {
		
		if(date!=null) {
		    SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");  
			return formatter.format(date);
		}
		return null;
	}


	public boolean downlaodFile() {
		boolean result = false;
		try {

			File downloadFile = new File(System.getProperty("java.io.tmpdir")+"/admissionabstract.xlsx");
	        FileInputStream inStream = new FileInputStream(downloadFile);

	        // get MIME type of the file
			String mimeType = "application/vnd.ms-excel";

			// set content attributes for the response
			response.setContentType(mimeType);
			// response.setContentLength((int) bis.length());

			// set headers for the response
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"",
					"admissionabstract.xlsx");
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

	
	
}
