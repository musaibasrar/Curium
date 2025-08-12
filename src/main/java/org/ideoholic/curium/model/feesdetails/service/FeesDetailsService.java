package org.ideoholic.curium.model.feesdetails.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.feescollection.dto.Otherreceiptinfo;
import org.ideoholic.curium.model.feescollection.dto.Receiptinfo;
import org.ideoholic.curium.model.feesdetails.dao.feesDetailsDAO;
import org.ideoholic.curium.model.feesdetails.dto.DataForFeesResponseDto;
import org.ideoholic.curium.model.feesdetails.dto.FeesIdDetailsDto;
import org.ideoholic.curium.model.feesdetails.dto.Feesdetails;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.student.dao.StudentDetailsDAO;
import org.ideoholic.curium.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeesDetailsService {
	
	@Autowired
	private feesDetailsDAO feesDetailsDao;

	public Feesdetails addFeesDetails(FeesIdDetailsDto feesIdDetailsDto,String branchid,String userId, String currentyear) {
		
		Feesdetails feesdetails = new Feesdetails();
		if(branchid!=null){
			// Setting the fees details
			String sid = feesIdDetailsDto.getStudentId();
			feesdetails.setStudent(new StudentDetailsDAO().readUniqueObject(DataUtil.parseInt(sid)));
			feesdetails.setDateoffees(DataUtil.emptyString(feesIdDetailsDto.getDateoffees()));
			feesdetails.setAmountpercat(DataUtil.emptyString(feesIdDetailsDto.getFeesTotalAmount()));
			feesdetails.setGrandtotal(DataUtil.emptyString(feesIdDetailsDto.getGrandTotalAmount()));
			feesdetails.setMiscamount(DataUtil.emptyString(feesIdDetailsDto.getMiscellanousamount()));
			feesdetails.setBalamount(DataUtil.emptyString(feesIdDetailsDto.getBalanceamount()));
			String currentYear = (String) currentyear;
			feesdetails.setAcademicyear(DataUtil.emptyString(currentYear));
			feesdetails.setBranchid(Integer.parseInt(branchid));
			feesdetails.setUserid(Integer.parseInt(userId));
			feesdetails = feesDetailsDao.create(feesdetails);
		}
		
		 return feesdetails;
	}



	public ResultResponse exportDataForFees(FeesIdDetailsDto feesIdDetailsDto) {
		
		ResultResponse result = ResultResponse.builder().success(false).build();
		String[] feesIds =  feesIdDetailsDto.getFeesIds();
		Receiptinfo receiptInfo = new Receiptinfo();
		Parents student = new Parents();
		Map<Parents,Receiptinfo> feesMap = new HashMap<Parents,Receiptinfo>();

		if (feesIds != null) {
			for (String id : feesIds) {
				if (id != null || id != "") {
					
					receiptInfo = feesDetailsDao.readFeesDetails(Long.parseLong(id));
					if (receiptInfo != null) {
						student = new StudentDetailsDAO().readUniqueObjectParents(receiptInfo.fetchSid());
						feesMap.put(student, receiptInfo);
					}
				}

			}
			try {
				if (exportDataToExcel(feesMap)) {
					result = ResultResponse.builder().success(true).build();
				} else {
					result = ResultResponse.builder().success(false).build();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	
	
	public boolean exportDataToExcel(Map<Parents,Receiptinfo> feeMap)
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
			
			for (Entry<Parents, Receiptinfo> entry : feeMap.entrySet()) {
	            
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
				FileOutputStream out = new FileOutputStream(new File(System.getProperty("java.io.tmpdir")+"/feesdetails.xlsx"));
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



	public ResultResponse exportDataForOtherFees(FeesIdDetailsDto feesIdDetailsDto) {
		
		ResultResponse result = ResultResponse.builder().success(false).build();
		String[] feesIds = feesIdDetailsDto.getFeesIds();
		Otherreceiptinfo receiptInfo = new Otherreceiptinfo();
		Parents student = new Parents();
		Map<Parents,Otherreceiptinfo> feesMap = new HashMap<Parents,Otherreceiptinfo>();

		if (feesIds != null) {
			for (String id : feesIds) {
				if (id != null || id != "") {
					
					receiptInfo = feesDetailsDao.readOtherFeesDetails(Long.parseLong(id));
					student = new StudentDetailsDAO().readUniqueObjectParents(receiptInfo.fetchSid());
					feesMap.put(student, receiptInfo);
				}

			}
			try {
				if (exportOtherFeesDataToExcel(feesMap)) {
					result = ResultResponse.builder().success(true).build();
				} else {
					result = ResultResponse.builder().success(false).build();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
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
						entry.getKey().fetchStudent().getAdmissionnumber(), 
						entry.getKey().fetchStudent().getStudentexternalid(), 
						entry.getKey().fetchStudent().getSts(), 
						entry.getValue().getBranchreceiptnumber(),
						entry.getKey().fetchStudent().getName(),
						entry.getKey().fetchStudent().getClassstudying(),
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
				FileOutputStream out = new FileOutputStream(new File(System.getProperty("java.io.tmpdir")+"/feesdetails.xlsx"));
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



	public DataForFeesResponseDto printDataForFees(FeesIdDetailsDto feesIdDetailsDto) {
		
		DataForFeesResponseDto dataForFeesResponseDto = new DataForFeesResponseDto();
		String[] feesIds = feesIdDetailsDto.getFeesIds();
		String toDate= DataUtil.dateFromatConversionDashToSlash(feesIdDetailsDto.getToDate());
		String fromDate = DataUtil.dateFromatConversionDashToSlash(feesIdDetailsDto.getFromDate());
		String oneDay = DataUtil.dateFromatConversionDashToSlash(feesIdDetailsDto.getOneDay());
		
		Receiptinfo receiptInfo = new Receiptinfo();
		Parents student = new Parents();
		Map<Receiptinfo,Parents> feesMap = new HashMap<Receiptinfo,Parents>();
		long sumOfFees = 0l;
		long fine = 0l;
		long misc = 0l;

		if (feesIds != null) {
			for (String id : feesIds) {
				if (id != null || id != "") {
					
					receiptInfo = feesDetailsDao.readFeesDetails(Long.parseLong(id));
					student = new StudentDetailsDAO().readUniqueObjectParents(receiptInfo.fetchSid());
					feesMap.put(receiptInfo, student);
					
					sumOfFees = sumOfFees + receiptInfo.getTotalamount();
					fine = fine + receiptInfo.getFine();
					misc = misc + receiptInfo.getMisc();
				}

			}
		}
		
		dataForFeesResponseDto.setSumOfDetailsFees(sumOfFees);
		dataForFeesResponseDto.setSumOfOnlyFee(sumOfFees-fine-misc);
		dataForFeesResponseDto.setSumOfFine(fine);
		dataForFeesResponseDto.setSumOfMisc(misc);
		if(oneDay.equalsIgnoreCase("")) {
			dataForFeesResponseDto.setDateRangeFeesCollection("From Date: "+fromDate+"             To Date: "+toDate+"");
		}else {
			dataForFeesResponseDto.setDateRangeFeesCollection("Date: "+oneDay+"");
		}
		

		// Step 1: Convert map entries to a list
		List<Map.Entry<Receiptinfo, Parents>> entryList = new ArrayList<>(feesMap.entrySet());

		// Step 2: Sort the list by receiptnumber
		entryList.sort(Comparator.comparing(e -> e.getKey().getReceiptnumber()));

		// Step 3: Create a LinkedHashMap to maintain the sorted order
		Map<Receiptinfo, Parents> sortedMap = new LinkedHashMap<>();
		for (Map.Entry<Receiptinfo, Parents> entry : entryList) {
		    sortedMap.put(entry.getKey(), entry.getValue());
		}
		
		dataForFeesResponseDto.setFeesMap(sortedMap);
		dataForFeesResponseDto.setSuccess(true);
		return dataForFeesResponseDto;
	}



}
