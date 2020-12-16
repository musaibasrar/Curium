package com.model.mess.item.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.model.academicyear.dao.YearDAO;
import com.model.academicyear.dto.Currentacademicyear;
import com.model.account.dao.AccountDAO;
import com.model.account.dto.Accountdetails;
import com.model.degreedetails.dto.Degreedetails;
import com.model.feescategory.dao.feesCategoryDAO;
import com.model.feescollection.dao.feesCollectionDAO;
import com.model.feescollection.dto.Receiptinfo;
import com.model.mess.item.dao.MessItemsDAO;
import com.model.mess.item.dto.MessItems;
import com.model.parents.dao.parentsDetailsDAO;
import com.model.parents.dto.Parents;
import com.model.pudetails.dto.Pudetails;
import com.model.std.dto.Classsec;
import com.model.std.service.StandardService;
import com.model.student.dao.studentDetailsDAO;
import com.model.student.dto.Student;
import com.model.student.dto.Studentfeesstructure;
import com.util.DataUtil;
import com.util.DateUtil;

public class MessItemsService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;
	private String BRANCHID = "branchid";
	
	public MessItemsService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}


	public String viewItemDetails() {
		
		List<MessItems> messItemsList = new ArrayList<MessItems>();
		List<Accountdetails> accountDetailsList = new ArrayList<Accountdetails>();
		
		 if(httpSession.getAttribute(BRANCHID)!=null){
			 	messItemsList =	new MessItemsDAO().getItemsDetails();
				accountDetailsList = new AccountDAO().getAccountdetails(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		 }
		 
		 request.setAttribute("messitemslist", messItemsList);
		 request.setAttribute("accountdetailslist", accountDetailsList);
		 
		return "additems.jsp";
	}


	public String addItemDetails() {
		
		MessItems messItems = new MessItems();
		Accountdetails accountDetails = new Accountdetails();
		String result = "false";
		 if(httpSession.getAttribute(BRANCHID)!=null){
             
			 messItems.setName(DataUtil.emptyString(request.getParameter("itemname")));
			 messItems.setExternalid(DataUtil.emptyString(request.getParameter("itemname")));
			 messItems.setUnitofmeasure(DataUtil.emptyString(request.getParameter("unitofmeasure")));
			 messItems.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			 accountDetails.setAccountdetailsid(DataUtil.parseInt(request.getParameter("glaccount")));
			 
			 messItems.setAccountDetails(accountDetails);
			 messItems= new MessItemsDAO().addNewItem(messItems);
			 
			 if(messItems.getId()!=null) {
				 request.setAttribute("itemsave", true);
				 request.setAttribute("itemname", messItems.getName());
				 result = "true";
			 }
		 }
		return result;
	}


	public void deleteMultipleItems() {
        
        String[] messIds = request.getParameterValues("messitemsids");
        if(messIds!=null){
        
       List<Integer> ids = new ArrayList<Integer>();
       for (String id : messIds) {
           String[] messId = id.split(":");
           ids.add(Integer.valueOf(messId[0]));
       		}
       boolean result = new MessItemsDAO().deleteItems(ids);
       request.setAttribute("itemsdelete", result);
        
        }
	}


	public void updateItems() {
		

		String[] messIds = request.getParameterValues("messitemsids");
        String[] updateItemName = request.getParameterValues("updateitemname");
        String[] updateUnitOfMeasure = request.getParameterValues("updateunitofmeasure");
        
        if(messIds!=null){
            
            List<MessItems> messList = new ArrayList<MessItems>();
            
            for(int i=0; i<messIds.length;i++) {
            	MessItems messItems = new MessItems();
                String[] itemId = messIds[i].split(":");
                messItems.setName(updateItemName[Integer.valueOf(itemId[1])]);
                messItems.setUnitofmeasure(updateUnitOfMeasure[Integer.valueOf(itemId[1])]);
                messItems.setId(DataUtil.parseInt(itemId[0]));
                messList.add(messItems);
            }
            boolean result = new MessItemsDAO().updateMultipleItems(messList);
            request.setAttribute("itemsupdate", result);
        }
		
	}
	
}
