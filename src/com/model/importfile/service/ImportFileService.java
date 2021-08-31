package com.model.importfile.service;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
ImportFileDTO importdto = new ImportFileDTO();

public boolean readFile(String fileName) throws FileNotFoundException, IOException {
    FileInputStream fis;
    
    List<Student> listStudent = new ArrayList<Student>();
    try {
        System.out.println("-------------------------------READING THE SPREADSHEET-------------------------------------");
        fis = new FileInputStream("C:/Users/Adeeba/importfile_curium.xlsx");
        XSSFWorkbook workbookRead = new XSSFWorkbook(fis);
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
            //student.setDateofbirth(DateUtil.indiandateParser(row.getCell(4).getStringCellValue()));
            student.setAge(Integer.parseInt(row.getCell(5).getStringCellValue()));
            student.setPlaceofbirth(row.getCell(6).getStringCellValue());
           // student.setAdmissiondate(DateUtil.indiandateParser(row.getCell(7).getStringCellValue()));
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
            //student.setCreateddate(DateUtil.indiandateParser(row.getCell(23).getStringCellValue()));
            student.setRemarks(row.getCell(24).getStringCellValue());
            
           listStudent.add(student);
          // System.out.println("date of birth***" +row.getCell(4).getCellType());
        }
        
        
        System.out.println("Values Inserted Successfully");
        fis.close();
        
    } catch (IOException e) {
        e.printStackTrace();
    }
     Student student = new Student();
     student.setStudentexternalid(DataUtil.generateString(1));
	 student.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
	 student.setAdmissionnumber(row.getCell(0).getStringCellValue());
     student.setSts(Integer.parseInt(row.getCell(1).getStringCellValue()));
     student.setName(row.getCell(2).getStringCellValue());
     student.setGender(row.getCell(3).getStringCellValue());
     student.setAge(Integer.parseInt(row.getCell(5).getStringCellValue()));
     student.setPlaceofbirth(row.getCell(6).getStringCellValue());
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
     student.setRemarks(row.getCell(24).getStringCellValue());
    
	student = new studentDetailsDAO().create(student);
	return false;
}
}
