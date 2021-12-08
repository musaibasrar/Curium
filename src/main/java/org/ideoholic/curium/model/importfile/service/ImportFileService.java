package org.ideoholic.curium.model.importfile.service;

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

import org.ideoholic.curium.model.parents.dao.parentsDetailsDAO;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.DateUtil;

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
					System.out.println("last row is " + rowTotal);
					while (rowIterator.hasNext()) {
						Student student = new Student();
						Parents parent = new Parents();
						row = (XSSFRow) rowIterator.next();
						if (row.getRowNum() == 0)
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
						student.setAdmissionnumber(row.getCell(0).getStringCellValue());
						// student.setRegistrationnumber(row.getCell(1).getStringCellValue());
						student.setName(row.getCell(2).getStringCellValue());
						student.setGender(row.getCell(3).getStringCellValue());
						student.setDateofbirth(DateUtil.simpleDateParser(
								(row.getCell(16).getStringCellValue()) + "/" + (row.getCell(17).getStringCellValue())
										+ "/" + (row.getCell(18).getStringCellValue())));
						student.setAge(Integer.parseInt(row.getCell(5).getStringCellValue()));
						student.setPlaceofbirth(row.getCell(6).getRawValue());
						student.setAdmissiondate(DateUtil.simpleDateParser(
								(row.getCell(19).getStringCellValue()) + "/" + (row.getCell(20).getStringCellValue())
										+ "/" + (row.getCell(21).getStringCellValue())));
						student.setClassstudying(row.getCell(8).getStringCellValue());
						// student.setClassadmittedin(row.getCell(9).getStringCellValue());
						student.setBloodgroup(row.getCell(9).getStringCellValue());
						student.setMothertongue(row.getCell(10).getStringCellValue());
						student.setReligion(row.getCell(11).getStringCellValue());
						student.setStudentscaste(row.getCell(12).getStringCellValue());
						student.setNationality(row.getCell(13).getStringCellValue());
						student.setStudentscastecertno(row.getCell(14).getStringCellValue());
						// student.setSecondlanguage(row.getCell(15).getStringCellValue());
						student.setCreateddate(DateUtil.simpleDateParser(
								(row.getCell(22).getStringCellValue()) + "/" + (row.getCell(23).getStringCellValue())
										+ "/" + (row.getCell(24).getStringCellValue())));

						student.setSchoollastattended(row.getCell(38).getStringCellValue());
						// student.setLastschooladdress(row.getCell(39).getStringCellValue());
						// student.setTotalmarks(Integer.parseInt(row.getCell(40).getStringCellValue()));
						// student.setPercentage(row.getCell(41).getStringCellValue());
						// student.setLastfirstlanguage(row.getCell(44).getStringCellValue());
						student.setUserid(Integer.parseInt(row.getCell(46).getStringCellValue()));

						student.setBranchid(3);
						student.setArchive(0);
						student.setPassedout(0);
						student.setDroppedout(0);
						student.setLeftout(0);
						student.setStudentexternalid(DataUtil.generateString(5));
						student.setLeftout(0);

						parent.setFathersname(row.getCell(25).getStringCellValue());
						// parent.setFatheroccupation(row.getCell(26).getStringCellValue());
						parent.setFathersqualification(row.getCell(27).getStringCellValue());
						parent.setContactnumber(row.getCell(28).getStringCellValue());
						parent.setParentsannualincome(row.getCell(29).getStringCellValue());
						// parent.setEmergencycontactno(row.getCell(30).getStringCellValue());
						parent.setAddresspermanent(row.getCell(31).getStringCellValue());
						parent.setAddresstemporary(row.getCell(32).getStringCellValue());
						student.setGuardiandetails(row.getCell(33).getStringCellValue());
						parent.setRemarks(row.getCell(34).getStringCellValue());
						parent.setMothersname(row.getCell(35).getStringCellValue());
						// parent.setProfession(row.getCell(36).getStringCellValue());
						// parent.setMothersqualification(row.getCell(37).getStringCellValue());
						parent.setCocontactnumber(row.getCell(37).getStringCellValue());

						parent.setStudent(student);
						parent.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));

						listParents.add(parent);

						// System.out.println("date of birth***"
						// +((row.getCell(25).getStringCellValue())+"/"+(row.getCell(26).getStringCellValue())+"/"+(row.getCell(27).getStringCellValue())));
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
