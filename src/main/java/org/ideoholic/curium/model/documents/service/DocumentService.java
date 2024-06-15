package org.ideoholic.curium.model.documents.service;

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
import org.ideoholic.curium.model.documents.dao.DocumentDAO;
import org.ideoholic.curium.model.documents.dto.SearchStudentDto;
import org.ideoholic.curium.model.documents.dto.SearchStudentResponseDto;
import org.ideoholic.curium.model.documents.dto.StudentIdDto;
import org.ideoholic.curium.model.documents.dto.StudentListAaResponseDto;
import org.ideoholic.curium.model.documents.dto.StudentNameSearchDto;
import org.ideoholic.curium.model.documents.dto.Transfercertificate;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.std.dao.StandardDetailsDAO;
import org.ideoholic.curium.model.std.dto.Classsec;
import org.ideoholic.curium.model.std.service.StandardService;
import org.ideoholic.curium.model.student.dao.studentDetailsDAO;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.DateUtil;
import org.ideoholic.curium.util.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;

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
		String bookno = DataUtil.emptyString(request.getParameter("bookno"));
		String tcno = DataUtil.emptyString(request.getParameter("tcno"));
		String caste = DataUtil.emptyString(request.getParameter("caste"));
		String classinword = DataUtil.emptyString(request.getParameter("classinword"));
		String lastexam = DataUtil.emptyString(request.getParameter("lastexam"));
		String failpass = DataUtil.emptyString(request.getParameter("failpass"));
		String firstsubject = DataUtil.emptyString(request.getParameter("firstsubject"));
		String secondsubject = DataUtil.emptyString(request.getParameter("secondsubject"));
		String thirdsubject = DataUtil.emptyString(request.getParameter("thirdsubject"));
		String Fourthsubject = DataUtil.emptyString(request.getParameter("Fourthsubject"));
		String Fifthsubject = DataUtil.emptyString(request.getParameter("Fifthsubject"));
		String sixthsubject = DataUtil.emptyString(request.getParameter("sixthsubject"));
		String pinfig = DataUtil.emptyString(request.getParameter("pinfig"));
		String pinword = DataUtil.emptyString(request.getParameter("pinword"));
		String dues = DataUtil.emptyString(request.getParameter("dues"));
		String concession = DataUtil.emptyString(request.getParameter("concession"));
		String workingdays = DataUtil.emptyString(request.getParameter("workingdays"));
		String present = DataUtil.emptyString(request.getParameter("present"));
		String ncc = DataUtil.emptyString(request.getParameter("ncc"));
		String game = DataUtil.emptyString(request.getParameter("game"));
		String conduct = DataUtil.emptyString(request.getParameter("conduct"));
		String datecert = DataUtil.emptyString(request.getParameter("datecert"));
		String Remarks = DataUtil.emptyString(request.getParameter("Remarks"));
		Date dateOfTc = DateUtil.dateParserUpdateStd(request.getParameter("dateoftc"));
		
		student.setReasonleaving(leavingReason);
		student.setSid(studentId);
		 boolean updateStudent = new studentDetailsDAO().updateStudent(student);
		 
		 if(updateStudent){
			 tc.setSid(studentId);
			 tc.setApplicationstatus("applied");
			 tc.setDateofissues(dateOfTc);
			 tc.setNoofissues(1);
			 
			 Transfercertificate transferCertificate = new DocumentDAO().getTransferCertificateDetails(tc.getSid()); 
			 if(transferCertificate != null){
				 String getStudentInfo  = "from Parents as parents where parents.Student.sid="+studentId;
				 parents = new studentDetailsDAO().getStudentRecords(getStudentInfo);
				 String dateinword=generateDate(parents.getStudent().getDateofbirth());
				 request.setAttribute("leavingReason", leavingReason);
					request.setAttribute("dateinword", dateinword);
					request.setAttribute("leavingReason", leavingReason);
					request.setAttribute("bookno", bookno);
					request.setAttribute("tcno", tcno);
					request.setAttribute("caste", caste);
					request.setAttribute("classinword", classinword);
					request.setAttribute("lastexam", lastexam);
					request.setAttribute("failpass", failpass);
					request.setAttribute("firstsubject", firstsubject);
					request.setAttribute("secondsubject", secondsubject);
					request.setAttribute("thirdsubject", thirdsubject);
					request.setAttribute("Fourthsubject", Fourthsubject);
					request.setAttribute("Fifthsubject", Fifthsubject);
					request.setAttribute("sixthsubject", sixthsubject);
					request.setAttribute("pinfig", pinfig);
					request.setAttribute("pinword", pinword);
					request.setAttribute("dues", dues);
					request.setAttribute("concession",concession);
					request.setAttribute("workingdays", workingdays);
					request.setAttribute("present", present);
					request.setAttribute("ncc", ncc);
					request.setAttribute("game", game);
					request.setAttribute("conduct", conduct);
					request.setAttribute("datecert", datecert);
					request.setAttribute("Remarks", Remarks);
					
				 request.setAttribute("dateinword", dateinword);
				 request.setAttribute("studentdetails", parents);
				 request.setAttribute("tcdetails", tc);
				 return "studentexists";
			 }else {
					transferCertificateString = new DocumentDAO().generateTransferCertificate(tc);
			}
		 }
		 
		 if("true".equalsIgnoreCase(transferCertificateString)){
			 String getStudentInfo  = "from Parents as parents where parents.Student.sid="+studentId;
			 parents = new studentDetailsDAO().getStudentRecords(getStudentInfo);
			 String dateinword=generateDate(parents.getStudent().getDateofbirth());
			 request.setAttribute("leavingReason", leavingReason);
				request.setAttribute("dateinword", dateinword);
				request.setAttribute("leavingReason", leavingReason);
				request.setAttribute("bookno", bookno);
				request.setAttribute("tcno", tcno);
				request.setAttribute("caste", caste);
				request.setAttribute("classinword", classinword);
				request.setAttribute("lastexam", lastexam);
				request.setAttribute("failpass", failpass);
				request.setAttribute("firstsubject", firstsubject);
				request.setAttribute("secondsubject", secondsubject);
				request.setAttribute("thirdsubject", thirdsubject);
				request.setAttribute("Fourthsubject", Fourthsubject);
				request.setAttribute("Fifthsubject", Fifthsubject);
				request.setAttribute("sixthsubject", sixthsubject);
				request.setAttribute("pinfig", pinfig);
				request.setAttribute("pinword", pinword);
				request.setAttribute("dues", dues);
				request.setAttribute("concession",concession);
				request.setAttribute("workingdays", workingdays);
				request.setAttribute("present", present);
				request.setAttribute("ncc", ncc);
				request.setAttribute("game", game);
				request.setAttribute("conduct", conduct);
				request.setAttribute("datecert", datecert);
				request.setAttribute("Remarks", Remarks);
				
			 request.setAttribute("dateinword", dateinword);
			 request.setAttribute("studentdetails", parents);
			 request.setAttribute("tcdetails", tc);
			 return "true";
		 }
		 return "false";
	}


		private String generateDate(Date dateofbirth) {
		// TODO Auto-generated method stub

		String dateOfBirth = DateUtil.dateParseryyyymmdd(dateofbirth);
		String[] dob = dateOfBirth.split("-");
		String dayInWords = formatDayInWords(Integer.parseInt(dob[2]));
	        String monthInWords = formatMonthInWords(Integer.parseInt(dob[1]));
	        String yearInWords = formatYearInWords(Integer.parseInt(dob[0]));

	        return dayInWords+" "+monthInWords+" "+yearInWords;

	    }


	private String formatYearInWords(int year) {
        if (year >= 1900 && year <= 2099) {
            String[] units = {
                "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"
            };
            String[] teens = {
                "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"
            };
            String[] tens = {
                "", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
            };

            int thousandsDigit = (year / 1000) % 10;
            int hundredsDigit = (year / 100) % 10;
            int tensDigit = (year / 10) % 10;
            int onesDigit = year % 10;

            String yearInWords = "";

            if (thousandsDigit > 0) {
                yearInWords += units[thousandsDigit] + " Thousand ";
            }

            if (hundredsDigit > 0) {
                yearInWords += units[hundredsDigit] + " Hundred ";
            }

            if (tensDigit > 1) {
                yearInWords += tens[tensDigit];
                if (onesDigit > 0) {
                    yearInWords += "-" + units[onesDigit];
                }
            } else if (tensDigit == 1) {
                yearInWords += teens[onesDigit];
            } else if (onesDigit > 0) {
                yearInWords += units[onesDigit];
            }

            return yearInWords.trim();
        } else {
            return "Invalid year";
        }
}


	private String formatMonthInWords(int month) {
	        String[] monthNames = {
	            "", "January", "February", "March", "April", "May", "June", "July", "August", "September",
	            "October", "November", "December"
	        };

	        if (month >= 1 && month <= 12) {
	            return monthNames[month];
	        } else {
	            return "Invalid month";
	        }
	    }


	private String formatDayInWords(int day) {
	        String[] dayNames = {
	            "", "First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Seventh", "Eighth", "Ninth",
	            "Tenth", "Eleventh", "Twelfth", "Thirteenth", "Fourteenth", "Fifteenth", "Sixteenth",
	            "Seventeenth", "Eighteenth", "Nineteenth", "Twentieth", "Twenty-First", "Twenty-Second",
	            "Twenty-Third", "Twenty-Fourth", "Twenty-Fifth", "Twenty-Sixth", "Twenty-Seventh",
	            "Twenty-Eighth", "Twenty-Ninth", "Thirtieth", "Thirty-First"
	        };

	        if (day >= 1 && day <= 31) {
	            return dayNames[day];
	        } else {
	            return "Invalid day";
	        }
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


	public StudentListAaResponseDto admissionAbstract(String branchid) {
		StudentListAaResponseDto studentListAaResponseDto = StudentListAaResponseDto.builder().build();
		if(branchid!=null){
			try {
				List<Parents> list = new studentDetailsDAO().getStudentsList("from Parents where branchid = "+Integer.parseInt(branchid.toString()));
				studentListAaResponseDto.setList(list);
				//TODO  change after migrating standard service
				new StandardService(request, response).viewClasses();
				studentListAaResponseDto.setSuccess(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		studentListAaResponseDto.setSuccess(false);
		return studentListAaResponseDto;
	
	}
	

	public SearchStudentResponseDto searchForStudents(SearchStudentDto searchStudentDto, String branchid) {
		SearchStudentResponseDto searchStudentResponseDto = SearchStudentResponseDto.builder().build();
		List<Parents> searchStudentList = new ArrayList<Parents>();
		if (branchid != null) {

			String queryMain = "From Parents as parents where";
			String studentname = DataUtil.emptyString(searchStudentDto.getNameSearch());
			String admissionNumber = DataUtil.emptyString(searchStudentDto.getAdmNo());
			String addClass = searchStudentDto.getClassSearch();
			String addSec = searchStudentDto.getSecSearch();
			String conClassStudying = "";

			if (!addClass.equalsIgnoreCase("")) {

				conClassStudying = addClass + "--" + "%";

			}
			if (!addSec.equalsIgnoreCase("")) {
				conClassStudying = addClass;
				conClassStudying = conClassStudying + "--" + addSec + "%";
			}

			String classStudying = DataUtil.emptyString(conClassStudying);
			String querySub = "";

			if (!studentname.equalsIgnoreCase("")) {
				querySub = " parents.Student.name like '%" + studentname + "%'";
			}

			if (!classStudying.equalsIgnoreCase("") && !querySub.equalsIgnoreCase("")) {
				querySub = querySub + " AND parents.Student.classstudying like '" + classStudying + "'";
			} else if (!classStudying.equalsIgnoreCase("")) {
				querySub = querySub + " parents.Student.classstudying like '" + classStudying + "'";
			}

			if (!admissionNumber.equalsIgnoreCase("") && !querySub.equalsIgnoreCase("")) {
				querySub = querySub + " AND parents.Student.admissionnumber = '" + admissionNumber + "'";
			} else if (!admissionNumber.equalsIgnoreCase("")) {
				querySub = querySub + " parents.Student.admissionnumber = '" + admissionNumber + "'";
			}

			if (!"".equalsIgnoreCase(querySub)) {
				queryMain = queryMain + querySub
						+ " AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.branchid="
						+ Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())
						+ " order by parents.Student.admissionnumber ASC";
				System.out.println("QUERY*********** " + queryMain);
				searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
			}
			searchStudentResponseDto.setSuccess(true);
		}
		searchStudentResponseDto.setSearchStudentList(searchStudentList);
		return searchStudentResponseDto;

	}

	
		public ResultResponse exportAdmissionAbstract(StudentIdDto studentIdDto,String branchid) {
		
		
		List<Parents> listOfStudentRecords = new LinkedList<Parents>();

		if (studentIdDto.getStudentIds() != null) {
			for (String id : studentIdDto.getStudentIds()) {
				if (id != null || id != "") {
					String queryMain = "From Parents as parents where parents.branchid="+branchid+" AND parents.Student.id = "+id+" order by parents.Student.admissionnumber ASC";

					Parents searchStudentRecords = new studentDetailsDAO().getStudentRecords(queryMain);
					listOfStudentRecords.add(searchStudentRecords);
				}

			}
			try {
				if (exportDataToExcel(listOfStudentRecords)) {
					return ResultResponse.builder().success(true).build();
				} 

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		 return ResultResponse.builder().success(false).build();

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
								studentDetails.getStudent().getSubsequentprogress(),
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


	public SearchStudentResponseDto multiClassSearchAdmissoinReport(StudentNameSearchDto studentNameSearchDto,String branchid) {

		SearchStudentResponseDto searchStudentResponseDto= new SearchStudentResponseDto();
		List<Parents> searchStudentList = new ArrayList<Parents>();
		
		if(branchid!=null){
		
		String queryMain = "From Parents as parents where parents.Student.yearofadmission = '"+studentNameSearchDto.getYearOfAdmission()+"' AND ";
		String studentname = DataUtil.emptyString(studentNameSearchDto.getNameSearch());
		String[] addClass = studentNameSearchDto.getClassSearch();
		//String addSec = request.getParameter("secsearch");
		StringBuffer conClassStudying = new StringBuffer();

			int i = 0;
			for (String classOne : addClass) {
				
				if(i>0) {
					conClassStudying.append("' OR parents.Student.classstudying LIKE '"+classOne+"--"+"%");
				}else {
					conClassStudying.append(classOne+"--"+"%");
				}
				
				i++;
			}
			
		
		/*if (!addSec.equalsIgnoreCase("")) {
			//conClassStudying = addClass;
			conClassStudying = conClassStudying+"--"+addSec+"%";
		}*/

		String classStudying = DataUtil.emptyString(conClassStudying.toString());
		String querySub = "";

		if (!studentname.equalsIgnoreCase("")) {
			querySub = " parents.Student.name like '%" + studentname + "%' and parents.Student.branchid="+Integer.parseInt(branchid);
		}

		if (!classStudying.equalsIgnoreCase("")
				&& !querySub.equalsIgnoreCase("")) {
			querySub = querySub + " AND (parents.Student.classstudying like '"
					+ classStudying + "') AND parents.Student.branchid="+Integer.parseInt(branchid)+" order by parents.Student.admissionnumber ASC";
		} else if (!classStudying.equalsIgnoreCase("")) {
			querySub = querySub + " (parents.Student.classstudying like '"
					+ classStudying + "') AND parents.Student.branchid="+Integer.parseInt(branchid)+" order by parents.Student.admissionnumber ASC";
		}

		if(!"".equalsIgnoreCase(querySub)) {
			queryMain = queryMain + querySub;
			searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
		}
		
	}
		searchStudentResponseDto.setSearchStudentList(searchStudentList);
        
		return searchStudentResponseDto;
	}


	public SearchStudentResponseDto multiClassSearchPendingAdmissoinReport(StudentNameSearchDto studentNameSearchDto,
			String branchid, String currentAcademicYear) {

		SearchStudentResponseDto searchStudentResponseDto = new SearchStudentResponseDto();
		List<Parents> searchStudentList = new ArrayList<Parents>();

		if (branchid != null) {

			String queryMain = "From Parents as parents where parents.Student.promotedyear != '" + currentAcademicYear
					+ "' AND ";
			String studentname = DataUtil.emptyString(studentNameSearchDto.getNameSearch());
			String[] addClass = studentNameSearchDto.getClassSearch();
			// String addSec = request.getParameter("secsearch");
			StringBuffer conClassStudying = new StringBuffer();

			int i = 0;
			for (String classOne : addClass) {

				if (i > 0) {
					conClassStudying.append("' OR parents.Student.classstudying LIKE '" + classOne + "--" + "%");
				} else {
					conClassStudying.append(classOne + "--" + "%");
				}

				i++;
			}

			/*
			 * if (!addSec.equalsIgnoreCase("")) { //conClassStudying = addClass;
			 * conClassStudying = conClassStudying+"--"+addSec+"%"; }
			 */

			String classStudying = DataUtil.emptyString(conClassStudying.toString());
			String querySub = "";

			if (!studentname.equalsIgnoreCase("")) {
				querySub = " parents.Student.name like '%" + studentname + "%' and parents.Student.branchid="
						+ Integer.parseInt(branchid);
			}

			if (!classStudying.equalsIgnoreCase("") && !querySub.equalsIgnoreCase("")) {
				querySub = querySub + " AND (parents.Student.classstudying like '" + classStudying
						+ "') AND parents.Student.branchid=" + Integer.parseInt(branchid)
						+ " order by parents.Student.admissionnumber ASC";
			} else if (!classStudying.equalsIgnoreCase("")) {
				querySub = querySub + " (parents.Student.classstudying like '" + classStudying
						+ "') AND parents.Student.branchid=" + Integer.parseInt(branchid)
						+ " order by parents.Student.admissionnumber ASC";
			}

			if (!"".equalsIgnoreCase(querySub)) {
				queryMain = queryMain + querySub;
				searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
			}

		}

		searchStudentResponseDto.setSearchStudentList(searchStudentList);
		return searchStudentResponseDto;

	}	
	
	public void printCharacterCertificate() {
		String character= request.getParameter("characterstudent");
		request.setAttribute("character", character);
	}


	public String GenerateCharacterCertificate() {
		String[] studentIds = request.getParameterValues("studentIDs");
		String characterPage = null;
		
		if(studentIds!=null){
			String getStudentInfo  = "from Parents as parents where parents.Student.sid="+studentIds[0];
			Parents parents = new studentDetailsDAO().getStudentRecords(getStudentInfo);
			httpSession.setAttribute("studentdetailsbonafide", parents);
			characterPage = "charactercertificateprint";
		}
		
		return characterPage;
	}
	
	 public String generateStudyCertificate() {
			
			String[] studentIds = request.getParameterValues("studentIDs");
			String bonafidePage = null;
			
			if(studentIds!=null){
				String getStudentInfo  = "from Parents as parents where parents.Student.sid="+studentIds[0];
				Parents parents = new studentDetailsDAO().getStudentRecords(getStudentInfo);
				httpSession.setAttribute("studentdetailsbonafide", parents);
				bonafidePage = "studycertificateprint";
			}
			
			return bonafidePage;
		}
}
