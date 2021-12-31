package com.model.importfile.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.model.parents.dao.parentsDetailsDAO;
import com.model.parents.dto.Parents;
import com.model.student.dto.Student;
import com.util.DataUtil;
import com.util.DateUtil;

public class ImportFileService {

	private static HttpServletRequest request;
	private static HttpServletResponse response;
	private HttpSession httpSession;
	private String BRANCHID = "branchid";

	public ImportFileService(HttpServletRequest request, HttpServletResponse response) {

		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

	XSSFRow row;

	public boolean readFile() throws FileNotFoundException, IOException {
		// Student student = new Student();
		DateFormat format = new SimpleDateFormat("MMMM d, yyyy");
		List<Parents> listParents = new ArrayList<Parents>();
		try {
			System.out.println(
					"-------------------------------READING THE SPREADSHEET-------------------------------------");

			List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

			for (FileItem item : items) {
				// Process form file field (input type="file").
				String fieldName = item.getFieldName();

				if (fieldName.equalsIgnoreCase("fileToImport")) {

					XSSFWorkbook workbookRead = new XSSFWorkbook(item.getInputStream());
					XSSFSheet spreadsheetRead = workbookRead.getSheetAt(0);

					Iterator<Row> rowIterator = spreadsheetRead.iterator();
					int rowTotal = spreadsheetRead.getLastRowNum();
					
					while (rowIterator.hasNext()) {
						Student student = new Student();
						Parents parent = new Parents();
						row = (XSSFRow) rowIterator.next();
						if (row.getRowNum() == 0)
							continue;
						
						if(row.getCell(0) == null)
							continue;
												
						Iterator<Cell> cellIterator = row.cellIterator();

						while (cellIterator.hasNext()) {
							Cell cell = cellIterator.next();
							cell.setCellType(CellType.STRING);
							switch (cell.getColumnIndex()) {
							case 0:
								System.out.print(cell.getStringCellValue() + " \t\t");
								break;
							case 1:
								System.out.print(cell.getStringCellValue() + " \t\t");
								break;
							case 2:
								System.out.print(cell.getStringCellValue() + " \t\t");
								break;
							case 3:
								System.out.print(cell.getStringCellValue() + " \t\t");
								break;
							case 4:
								System.out.print(cell.getStringCellValue() + " \t\t");
								break;

							}
						}
						
						Cell cell = row.getCell(0);
						if(cell!=null) {
							student.setAdmissionnumber(row.getCell(0).getStringCellValue());
						}else {
							student.setAdmissionnumber("");
						}
						
						Cell cell1 = row.getCell(1);
						if(cell1!=null) {
							student.setName(row.getCell(1).getStringCellValue());
						}else {
							student.setName("");
						}
						
						String classStudying = null;
						Cell cell2 = row.getCell(2);
						if(cell2!=null) {
							classStudying = row.getCell(2).getStringCellValue();
						}else {
							classStudying = "";
						}
						
						Cell cell3 = row.getCell(3);
						String sec = null;
						if(cell3!=null) {
							sec = row.getCell(3).getStringCellValue();
						}
						
						
						if(sec!=null) {
							classStudying=classStudying+"--"+sec;
						}
						
						student.setClassstudying(classStudying);
						student.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
						student.setArchive(0);
						student.setPassedout(0);
						student.setDroppedout(0);
						student.setLeftout(0);
						student.setStudentexternalid(DataUtil.generateString(5));
						student.setLeftout(0);

						Cell cell4 = row.getCell(4);
						if(cell4!=null) {
							parent.setFathersname(row.getCell(4).getStringCellValue());
						}else {
							parent.setFathersname("");
						}
						
						Cell cell5 = row.getCell(5);
						if(cell5!=null) {
							parent.setMothersname(row.getCell(5).getStringCellValue());
						}else {
							parent.setMothersname("");
						}
						
						
						Cell cell6 = row.getCell(6);
						if(cell6!=null) {
							parent.setContactnumber(row.getCell(6).getStringCellValue());
						}else {
							parent.setContactnumber("");
						}
						
						
						Cell cell7 = row.getCell(7);
						if(cell7!=null) {
							parent.setCocontactnumber(row.getCell(7).getStringCellValue());
						}else {
							parent.setCocontactnumber("");
						}
						
						parent.setStudent(student);
						parent.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));

						listParents.add(parent);

					}

					System.out.println("Values Inserted Successfully");
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		return new parentsDetailsDAO().createMultiple(listParents);
	}
}
