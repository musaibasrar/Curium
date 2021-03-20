package com.model.feesdetails.service;

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
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.model.feescollection.dto.Receiptinfo;
import com.model.feesdetails.dao.feesDetailsDAO;
import com.model.feesdetails.dto.Feesdetails;
import com.model.parents.dto.Parents;
import com.model.student.dao.studentDetailsDAO;
import com.model.student.dto.Student;
import com.model.user.dao.UserDAO;
import com.util.DataUtil;

public class FeesDetailsService {
	
	 	private HttpServletRequest request;
	    private HttpServletResponse response;
	    private HttpSession httpSession;
	    private String BRANCHID = "branchid";
	    private String USERID = "userid";
	
	public FeesDetailsService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
       this.response = response;
       this.httpSession = request.getSession();
	}



	public Feesdetails addFeesDetails() {
		
		Feesdetails feesdetails = new Feesdetails();
		if(httpSession.getAttribute(BRANCHID)!=null){
			// Setting the fees details
			String sid = request.getParameter("studentId");
			feesdetails.setSid(DataUtil.parseInt(sid));
			feesdetails.setDateoffees(DataUtil.emptyString(request
					.getParameter("dateoffees")));
			feesdetails.setAmountpercat(DataUtil.emptyString(request
					.getParameter("feesTotalAmount")));
			feesdetails.setGrandtotal(DataUtil.emptyString(request
					.getParameter("grandTotalAmount")));
			feesdetails.setMiscamount(DataUtil.emptyString(request
					.getParameter("miscellanousamount")));
			feesdetails.setBalamount(DataUtil.emptyString(request
					.getParameter("balanceamount")));
			String currentYear = (String) httpSession.getAttribute("currentYear");
			feesdetails.setAcademicyear(DataUtil.emptyString(currentYear));
			feesdetails.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			feesdetails.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
			 feesdetails = new feesDetailsDAO().create(feesdetails);
		}
		
		 return feesdetails;
	}



	public boolean exportDataForFees() {
		
		String[] feesIds = request.getParameterValues("feesIDs");
		boolean successResult = false;
		List<Receiptinfo> listOfFeesDetails = new ArrayList<Receiptinfo>();
		Receiptinfo receiptInfo = new Receiptinfo();
		List<Student> listOfStudentDetails = new ArrayList<Student>();
		Student student = new Student();

		if (feesIds != null) {
			for (String id : feesIds) {
				if (id != null || id != "") {
					
					receiptInfo = new feesDetailsDAO().readFeesDetails(Long.parseLong(id));
					listOfFeesDetails.add(receiptInfo);
					
					student = new studentDetailsDAO().readUniqueObject(receiptInfo.getSid());
					listOfStudentDetails.add(student);
				}

			}
			try {
				if (exportDataToExcel(listOfFeesDetails, listOfStudentDetails)) {
					successResult = true;
				} else {
					successResult = false;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return successResult;
	}
	
	
	public boolean exportDataToExcel(List<Receiptinfo> listOfFeesDetails, List<Student> listOfStudent)
			throws Exception {

		boolean writeSucees = false;

		try {
			// Start creating an excel file
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("Fees Details");
			Map<String, Object[]> data = new HashMap<String, Object[]>();
			Map<String, Object[]> headerData = new HashMap<String, Object[]>();
			headerData.put("Header",
					new Object[] { "Admission Number", "Date of Fees", "Total"});
			int i = 1;
			for (Receiptinfo feesDetails : listOfFeesDetails) {
				
				for (Student studentDetails : listOfStudent) {
				
					data.put(Integer.toString(i),new Object[] { 
						studentDetails.getAdmissionnumber(), feesDetails.getDate().toString(),
						feesDetails.getTotalamount() });
				}
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
					else if (obj instanceof Long)
						cell.setCellValue((Long) obj);
				}
			}
				FileOutputStream out = new FileOutputStream(new File(System.getProperty("java.io.tmpdir")+"feesdetails.xlsx"));
				workbook.write(out);
				out.close();
				writeSucees = true;
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return writeSucees;
		// getFile(name, path);
	}



}
