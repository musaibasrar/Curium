package com.model.importfile.service;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hpsf.Vector;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.model.importfile.dao.ImportFileDAO;
import com.model.importfile.dto.ImportFileDTO;
import com.model.parents.dao.parentsDetailsDAO;
import com.model.parents.dto.Parents;
import com.model.student.dao.studentDetailsDAO;
import com.model.student.dto.Student;
import com.mysql.jdbc.Statement;
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
    List<Student> listStudent = new ArrayList<Student>();
    try {
        System.out.println("-------------------------------READING THE SPREADSHEET-------------------------------------");
              
        List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
		
		 for (FileItem item : items) {
	                // Process form file field (input type="file").
	                String fieldName = item.getFieldName();

	                if (fieldName.equalsIgnoreCase("fileToImport")) {
        
        
        XSSFWorkbook workbookRead = new XSSFWorkbook(item.getInputStream());
        XSSFSheet spreadsheetRead = workbookRead.getSheetAt(0);

        Iterator< Row> rowIterator = spreadsheetRead.iterator();
        while (rowIterator.hasNext()) {
        	Student student= new Student();
            row = (XSSFRow) rowIterator.next();
            if(row.getRowNum() ==0)
                continue;
            Iterator< Cell> cellIterator = row.cellIterator();
            

           while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                cell.setCellType(CellType.STRING);
                switch (cell.getColumnIndex()) {
                    case 0:
                        System.out.print(
                                cell.getStringCellValue() + " \t\t");
                        break;
                    case 1:
                        System.out.print(
                                cell.getStringCellValue() + " \t\t");
                        break;
                    case 2:
                        System.out.print(
                                cell.getStringCellValue() + " \t\t");
                        break;
                    case 3:
                        System.out.print(
                                cell.getStringCellValue() + " \t\t");
                        break;
                    case 4:
                        System.out.print(
                                cell.getStringCellValue() + " \t\t");
                        break;
                        
            }
           }
            System.out.println();
            student.setAdmissionnumber(row.getCell(0).getStringCellValue());
            student.setSts(Integer.parseInt(row.getCell(1).getStringCellValue()));
            student.setName(row.getCell(2).getStringCellValue());
            student.setGender(row.getCell(3).getStringCellValue());
            student.setDateofbirth(DateUtil.simpleDateParser((row.getCell(25).getStringCellValue())+"/"+(row.getCell(26).getStringCellValue())+"/"+(row.getCell(27).getStringCellValue())));
            student.setAge(Integer.parseInt(row.getCell(5).getStringCellValue()));
            student.setPlaceofbirth (row.getCell(6).getStringCellValue());
            student.setAdmissiondate(DateUtil.simpleDateParser((row.getCell(28).getStringCellValue())+"/"+(row.getCell(29).getStringCellValue())+"/"+(row.getCell(30).getStringCellValue())));
            student.setClassstudying(row.getCell(8).getStringCellValue());
            student.setClassadmittedin(row.getCell(9).getStringCellValue());
            student.setBloodgroup(row.getCell(10).getStringCellValue());
            student.setNationality(row.getCell(11).getStringCellValue());
            student.setReligion(row.getCell(12).getStringCellValue());
            student.setStudentscastecertno(row.getCell(13).getStringCellValue());
            student.setStudentscaste(row.getCell(14).getStringCellValue());
            student.setSocialcategory(row.getCell(15).getStringCellValue());
           // student.(row.getCell(16).getStringCellValue());
           // student.(row.getCell(17).getStringCellValue());
           // student.(row.getCell(18).getStringCellValue());
          //  student(row.getCell(19).getStringCellValue());
            student.setSpecialcategory(row.getCell(20).getStringCellValue());
            student.setMothertongue(row.getCell(21).getStringCellValue());
            student.setRte(Integer.parseInt(row.getCell(22).getStringCellValue()));
            student.setCreateddate(DateUtil.simpleDateParser((row.getCell(31).getStringCellValue())+"/"+(row.getCell(32).getStringCellValue())+"/"+(row.getCell(33).getStringCellValue())));
            student.setRemarks(row.getCell(24).getStringCellValue());
            
           listStudent.add(student);
           //System.out.println("date of birth***" +((row.getCell(25).getStringCellValue())+"/"+(row.getCell(26).getStringCellValue())+"/"+(row.getCell(27).getStringCellValue())));
        }
        
        
        System.out.println("Values Inserted Successfully");
	        }
		 }
    } catch (FileUploadException e) {
        e.printStackTrace();
    }
     Student student = new Student();
     student.setStudentexternalid(DataUtil.generateString(5));
	 student.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
	 student.setAdmissionnumber(row.getCell(0).getStringCellValue());
     student.setSts(Integer.parseInt(row.getCell(1).getStringCellValue()));
     student.setName(row.getCell(2).getStringCellValue());
     student.setGender(row.getCell(3).getStringCellValue());
     student.setDateofbirth(DateUtil.simpleDateParser((row.getCell(25).getStringCellValue())+"/"+(row.getCell(26).getStringCellValue())+"/"+(row.getCell(27).getStringCellValue())));
     student.setAge(Integer.parseInt(row.getCell(5).getStringCellValue()));
     student.setPlaceofbirth(row.getCell(6).getStringCellValue());
     student.setAdmissiondate(DateUtil.simpleDateParser((row.getCell(28).getStringCellValue())+"/"+(row.getCell(29).getStringCellValue())+"/"+(row.getCell(30).getStringCellValue())));
     student.setClassstudying(row.getCell(8).getStringCellValue());
     student.setClassadmittedin(row.getCell(9).getStringCellValue());
     student.setBloodgroup(row.getCell(10).getStringCellValue());
     student.setNationality(row.getCell(11).getStringCellValue());
     student.setReligion(row.getCell(12).getStringCellValue());
     student.setStudentscastecertno(row.getCell(13).getStringCellValue());
     student.setStudentscaste(row.getCell(14).getStringCellValue());
     student.setSocialcategory(row.getCell(15).getStringCellValue());
     student.setSpecialcategory(row.getCell(20).getStringCellValue());
     student.setMothertongue(row.getCell(21).getStringCellValue());
     student.setRte(Integer.parseInt(row.getCell(22).getStringCellValue()));
     student.setCreateddate(DateUtil.simpleDateParser((row.getCell(31).getStringCellValue())+"/"+(row.getCell(32).getStringCellValue())+"/"+(row.getCell(33).getStringCellValue())));
     student.setRemarks(row.getCell(24).getStringCellValue());
    
	student = new studentDetailsDAO().create(student);
	return false;
}
}
