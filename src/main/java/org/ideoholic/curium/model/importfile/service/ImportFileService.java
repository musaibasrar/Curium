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

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.branch.dto.Branch;
import org.ideoholic.curium.model.parents.dao.parentsDetailsDAO;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.model.user.dto.Login;
import org.ideoholic.curium.util.DateUtil;
import org.springframework.web.multipart.MultipartFile;

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

	public ResultResponse readFile(MultipartFile uploadedFiles,String branchId) throws FileNotFoundException, IOException {
		// Student student = new Student();
		DateFormat format = new SimpleDateFormat("MMMM d, yyyy");
		List<Parents> listParents = new ArrayList<Parents>();
		List<Login> listParentLogin = new ArrayList<Login>();
		System.out.println("-------------------------------READING THE SPREADSHEET-------------------------------------");

					XSSFWorkbook workbookRead = new XSSFWorkbook(uploadedFiles.getInputStream());
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
						//student.setAdmissionnumber(getCellValue(row, 0));
						student.setSts(getCellValue(row, 1));
						student.setStudentexternalid(getCellValue(row, 0));
						student.setName(getCellValue(row, 2));
						student.setGender(getCellValue(row, 3));
						
						student.setDateofbirth(DateUtil.simpleDateParser(
						(getCellValue(row, 16)) + "/" +
						(getCellValue(row, 17)) + "/" +
						(getCellValue(row, 18))));
						
						student.setAge(Integer.parseInt(getCellValue(row, 5)));
						//student.setPlaceofbirth(row.getCell(6).getRawValue());
						student.setAdmissiondate(DateUtil.simpleDateParser(
								(getCellValue(row, 19)) + "/" + (getCellValue(row, 20))
										+ "/" + (getCellValue(row, 21))));
						student.setClassstudying(getCellValue(row, 8)+"--"+getCellValue(row, 47));
						
						student.setClassadmittedin(getCellValue(row, 42)+"--");
						//student.setBloodgroup(row.getCell(9).getStringCellValue());
						student.setMothertongue(getCellValue(row, 10));
						student.setReligion(getCellValue(row, 11));
						student.setStudentscaste(getCellValue(row, 12));
						student.setNationality(getCellValue(row, 13));
						//student.setStudentscastecertno(row.getCell(14).getStringCellValue());
						//student.setDisabilitychild(row.getCell(14).getStringCellValue());//Aadhar no
						//student.setSocialcategory(row.getCell(15).getStringCellValue());
						// student.setSecondlanguage(row.getCell(15).getStringCellValue());
						student.setCreateddate(DateUtil.simpleDateParser(
								(getCellValue(row, 22)) + "/" + (getCellValue(row, 23))
										+ "/" + (getCellValue(row, 24))));

						//student.setSchoollastattended(row.getCell(38).getStringCellValue());
						// student.setLastschooladdress(row.getCell(39).getStringCellValue());
						// student.setTotalmarks(Integer.parseInt(row.getCell(40).getStringCellValue()));
						// student.setPercentage(row.getCell(41).getStringCellValue());
						//student.setLastfirstlanguage(row.getCell(44).getStringCellValue());
						student.setUserid(2);
						//student.setBhagyalakshmibondnumber(row.getCell(50).getStringCellValue());
						//student.setSts(row.getCell(49).getStringCellValue());

						student.setBranchid(2);
						student.setArchive(0);
						student.setPassedout(Integer.parseInt(getCellValue(row, 9)));
						student.setDroppedout(0);
						student.setLeftout(0);
						//student.setStudentexternalid(row.getCell(1).getStringCellValue());
						student.setLeftout(0);

						parent.setFathersname(getCellValue(row, 25));
						//parent.setProfession(row.getCell(26).getStringCellValue());
						//parent.setFathersqualification(row.getCell(27).getStringCellValue());
						parent.setContactnumber(getCellValue(row, 28));
						//parent.setParentsannualincome(row.getCell(29).getStringCellValue());
						// parent.setEmergencycontactno(row.getCell(30).getStringCellValue());
						//parent.setAddresspermanent(getCellValue(row, 31));
						//parent.setAddresstemporary(row.getCell(32).getStringCellValue());
						//student.setGuardiandetails(row.getCell(33).getStringCellValue());
						//parent.setRemarks(row.getCell(34).getStringCellValue());
						parent.setMothersname(" ");
						//parent.setMotherscastecertno(row.getCell(36).getStringCellValue());
						// parent.setProfession(row.getCell(36).getStringCellValue());
						//parent.setMothersqualification(row.getCell(52).getStringCellValue());
						//parent.setCocontactnumber(row.getCell(37).getStringCellValue());
						//parent.setFatherscastecertno(row.getCell(47).getStringCellValue());
						//parent.setMotherscastecertno(row.getCell(48).getStringCellValue());
						//parent.setAddresspermanent(row.getCell(54).getStringCellValue()+"-"+row.getCell(55).getStringCellValue()+"-"+row.getCell(56).getStringCellValue()+"-"+row.getCell(57).getStringCellValue()+"-"+row.getCell(58).getStringCellValue());
						
						parent.setStudent(student);
						parent.setBranchid(Integer.parseInt(branchId));

						listParents.add(parent);

						// System.out.println("date of birth***"
						// +((row.getCell(25).getStringCellValue())+"/"+(row.getCell(26).getStringCellValue())+"/"+(row.getCell(27).getStringCellValue())));
					}

					System.out.println("Values Inserted Successfully");
					
					for (Parents parent : listParents) {
						Login login= new Login();
						Branch branch = new Branch();
						login.setUsername(parent.getStudent().getStudentexternalid());
						login.setPassword(parent.getStudent().getStudentexternalid());
						branch.setIdbranch(parent.getBranchid());
						login.setBranch(branch);
						login.setUsertype("parents");
						listParentLogin.add(login);
					}
					

		return ResultResponse.builder().success(new parentsDetailsDAO().createMultiple(listParents,listParentLogin)).build();
	}
	
	
	private String getCellValue(Row row, int cellIndex) {
	    DataFormatter formatter = new DataFormatter();
	    Cell cell = row.getCell(cellIndex);

	    if (cell == null) {
	        return "";
	    }

	    return formatter.formatCellValue(cell).trim();
	}
}