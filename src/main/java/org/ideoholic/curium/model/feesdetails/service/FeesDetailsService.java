package org.ideoholic.curium.model.feesdetails.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
import org.ideoholic.curium.model.account.dao.AccountDAO;
import org.ideoholic.curium.model.account.dto.VoucherEntrytransactions;
import org.ideoholic.curium.model.feescollection.dto.Feescollection;
import org.ideoholic.curium.model.feescollection.dto.Otherreceiptinfo;
import org.ideoholic.curium.model.feescollection.dto.Receiptinfo;
import org.ideoholic.curium.model.feesdetails.dao.feesDetailsDAO;
import org.ideoholic.curium.model.feesdetails.dto.Feesdetails;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.student.dao.studentDetailsDAO;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.model.student.dto.Studentfeesstructure;
import org.ideoholic.curium.model.user.dao.UserDAO;
import org.ideoholic.curium.util.DataUtil;

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
		Receiptinfo receiptInfo = new Receiptinfo();
		Parents student = new Parents();
		Map<Parents,Receiptinfo> feesMap = new HashMap<Parents,Receiptinfo>();

		if (feesIds != null) {
			for (String id : feesIds) {
				if (id != null || id != "") {
					
					receiptInfo = new feesDetailsDAO().readFeesDetails(Long.parseLong(id));
					student = new studentDetailsDAO().readUniqueObjectParents(receiptInfo.getSid());
					
					Set<Feescollection> setFeesCollection = receiptInfo.getFeesCollectionRecords();
					List<String> feeCatList = new ArrayList<String>();

					for (Feescollection feescollectionSingle : setFeesCollection) {
						List<Studentfeesstructure> studentfeesstructure = new studentDetailsDAO().getStudentFeesStructureDetails(feescollectionSingle.getSfsid());
						feeCatList.add(studentfeesstructure.get(0).getFeescategory().getFeescategoryname());
					}
					
					List<Integer> voucherIds = new ArrayList<Integer>();
					voucherIds.add(receiptInfo.getReceiptvoucher());
					voucherIds.add(receiptInfo.getJournalvoucher());
					voucherIds.add(receiptInfo.getMisc().intValue());

					List<VoucherEntrytransactions> voucherList = new AccountDAO().getVoucherEntryTransactions(voucherIds);
					String division = "";
					for (VoucherEntrytransactions voucher : voucherList) {
						
						if(!division.equalsIgnoreCase("")) {
							division = division+"--"+voucher.getDramount().toString();
						}else {
							division = voucher.getDramount().toString();
						}
						
					}
					receiptInfo.setContributiondivision(division);
					receiptInfo.setFeesCategory(feeCatList);
					
					feesMap.put(student, receiptInfo);
				}

			}
			try {
				if (exportDataToExcel(feesMap)) {
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
	
	
	public boolean exportDataToExcel(Map<Parents,Receiptinfo> feeMap)
			throws Exception {

		boolean writeSucees = false;

		try {
			// Start creating an excel file
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("Collection Details");
			Map<String, Object[]> data = new HashMap<String, Object[]>();
			Map<String, Object[]> headerData = new HashMap<String, Object[]>();
			headerData.put("Header",
					new Object[] { "UID","Book Receipt No.","Name","Contact Number", "Date", "Total", "Collection Head", "Distribution"});
			int i = 1;
			
			for (Entry<Parents, Receiptinfo> entry : feeMap.entrySet()) {
				
                String feesCat = "";
                for (String feeCat : entry.getValue().getFeesCategory()) {

					if(!feesCat.equalsIgnoreCase("")) {
						feesCat = feesCat+"--"+feeCat;
					}else {
						feesCat = feeCat;
					}
				}
	            
				data.put(Integer.toString(i),new Object[] {i, 
						entry.getKey().getStudent().getName(),
						entry.getKey().getStudent().getClassstudying(),
						entry.getValue().getBookreceiptno(),
						entry.getValue().getDate().toString(),
						entry.getValue().getTotalamount(),feesCat,
						entry.getValue().getContributiondivision()});
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
					else if (obj instanceof Integer)
						cell.setCellValue((Integer) obj);
				}
			}
				FileOutputStream out = new FileOutputStream(new File(System.getProperty("java.io.tmpdir")+"/contributiondetails.xlsx"));
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



	public boolean exportDataForOtherFees() {
		
		String[] feesIds = request.getParameterValues("feesIDs");
		boolean successResult = false;
		Otherreceiptinfo receiptInfo = new Otherreceiptinfo();
		Parents student = new Parents();
		Map<Parents,Otherreceiptinfo> feesMap = new HashMap<Parents,Otherreceiptinfo>();

		if (feesIds != null) {
			for (String id : feesIds) {
				if (id != null || id != "") {
					
					receiptInfo = new feesDetailsDAO().readOtherFeesDetails(Long.parseLong(id));
					student = new studentDetailsDAO().readUniqueObjectParents(receiptInfo.getSid());
					feesMap.put(student, receiptInfo);
				}

			}
			try {
				if (exportOtherFeesDataToExcel(feesMap)) {
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
	
	
	public boolean exportOtherFeesDataToExcel(Map<Parents,Otherreceiptinfo> feeMap)
			throws Exception {

		boolean writeSucees = false;

		try {
			// Start creating an excel file
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("Fees Details");
			Map<String, Object[]> data = new HashMap<String, Object[]>();
			Map<String, Object[]> headerData = new HashMap<String, Object[]>();
			headerData.put("Header",
					new Object[] { "Admission Number","UID","STS","Receipt No.", "Student Name","Class","Father Name","Contact Number", "Date of Fees", "Total"});
			int i = 1;
			
			for (Entry<Parents, Otherreceiptinfo> entry : feeMap.entrySet()) {
	            
				data.put(Integer.toString(i),new Object[] { 
						entry.getKey().getStudent().getAdmissionnumber(), 
						entry.getKey().getStudent().getStudentexternalid(), 
						entry.getKey().getStudent().getSts(), 
						entry.getValue().getBranchreceiptnumber(),
						entry.getKey().getStudent().getName(),
						entry.getKey().getStudent().getClassstudying(),
						entry.getKey().getFathersname(), 
						entry.getKey().getContactnumber(), 
						entry.getValue().getDate().toString(),
						entry.getValue().getTotalamount() });
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
				FileOutputStream out = new FileOutputStream(new File(System.getProperty("java.io.tmpdir")+"/contributiondetails.xlsx"));
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
