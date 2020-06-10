package org.ideoholic.feesdetails.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.model.feescollection.dto.Receiptinfo;
import com.model.feesdetails.dao.feesDetailsDAO;
import com.model.student.dao.studentDetailsDAO;
import com.model.student.dto.Student;

public class FeesDetailsServiceImpl implements FeesDetailsService {
	
public String exportDataForFees(String[] feesIds) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
		
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
				if (exportDataToExcel(listOfFeesDetails, listOfStudentDetails) != null) {
					successResult = true;
				} else {
					successResult = false;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		sb.append("}");
		return sb.toString();
	}

public String exportDataToExcel(List<Receiptinfo> listOfFeesDetails, List<Student> listOfStudent)
		throws Exception {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
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
	sb.append("}");
	return sb.toString();
	// getFile(name, path);
}

}
