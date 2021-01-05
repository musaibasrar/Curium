package com.model.mess.supplier.service;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.account.dao.AccountDAO;
import com.model.account.dto.Accountdetails;
import com.model.account.dto.Accountdetailsbalance;
import com.model.account.dto.Accountgroupmaster;
import com.model.account.dto.Accountssgroupmaster;
import com.model.account.dto.Accountsubgroupmaster;
import com.model.account.dto.Financialaccountingyear;
import com.model.mess.item.dao.MessItemsDAO;
import com.model.mess.supplier.dao.MessSuppliersDAO;
import com.model.mess.supplier.dto.MessSuppliers;
import com.util.DataUtil;

public class MessSuppliersService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;
	private String BRANCHID = "branchid";
	
	public MessSuppliersService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}


	public String viewSuppliersDetails() {
		
		List<MessSuppliers> messSuppliersList = new ArrayList<MessSuppliers>();
		
		 if(httpSession.getAttribute(BRANCHID)!=null){
			 	messSuppliersList =	new MessSuppliersDAO().getSupplierDetails();
		 }
		 
		 request.setAttribute("messsupplierslist", messSuppliersList);
		 
		return "addsuppliers.jsp";
	}


	public String addSupplierDetails() {
		
		MessSuppliers messSuppliers = new MessSuppliers();
		String result = "false";
		
		 if(httpSession.getAttribute(BRANCHID)!=null){
             
			 messSuppliers.setName(DataUtil.emptyString(request.getParameter("suppliername")));
			 messSuppliers.setExternalid(DataUtil.emptyString(request.getParameter("suppliername")));
			 messSuppliers.setContactnumber(DataUtil.emptyString(request.getParameter("contactnumber")));
			 messSuppliers.setBankaccountno(DataUtil.emptyString(request.getParameter("bankaccountno")));
			 messSuppliers.setIfsccode(DataUtil.emptyString(request.getParameter("ifsccode")));
			 messSuppliers.setAddress(DataUtil.emptyString(request.getParameter("address")));
			 messSuppliers.setPayto(DataUtil.emptyString(request.getParameter("payto")));
			 messSuppliers.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			 messSuppliers.setLinkedledgerid(1);
			 messSuppliers = createLedgerForSupplierAndSave(messSuppliers);
			 
			 /*messSuppliers= new MessSuppliersDAO().addNewSuppplier(messSuppliers);*/
			 
			 if(messSuppliers.getId()!=null) {
				 request.setAttribute("suppliersave", true);
				 request.setAttribute("suppliername", messSuppliers.getName());
				 result = "true";
			 }
		 }
		return result;
	}


	public void deleteMultipleSuppliers() {
        
        String[] messIds = request.getParameterValues("messsuppliersids");
        
        if(messIds!=null){
        
       List<MessSuppliers> messList = new ArrayList<MessSuppliers>();
       
       		for (String id : messIds) {
    	   
		    	   MessSuppliers messSuppliers = new MessSuppliers();
		    	   
		           String messId = id;
		           messSuppliers.setId(DataUtil.parseInt(messId));
		           messSuppliers.setLinkedledgerid(Integer.parseInt(request.getParameter("linkedledgerid_"+messId)));
		           messList.add(messSuppliers);
           
       		}
       		
       boolean result = new MessSuppliersDAO().deleteSuppliers(messList);
       request.setAttribute("suppliersdelete", result);
        
        }
	}


	public void updateSuppliers() {
		

		String[] messIds = request.getParameterValues("messsuppliersids");
        
        
        if(messIds!=null){
        	
            List<MessSuppliers> messList = new ArrayList<MessSuppliers>();
            
            for(int i=0; i<messIds.length;i++) {
            	
            	MessSuppliers messSuppliers = new MessSuppliers();
                String supplierId = messIds[i];
                messSuppliers.setName(request.getParameter("updatesuppliersname_"+supplierId));
                messSuppliers.setContactnumber(request.getParameter("updatesupplierscontactnumber_"+supplierId));
                messSuppliers.setBankaccountno(request.getParameter("updatesuppliersbankaccountno_"+supplierId));
                messSuppliers.setIfsccode(request.getParameter("updatesuppliersifsccode_"+supplierId));
                messSuppliers.setAddress(request.getParameter("updatesuppliersaddress_"+supplierId));
                messSuppliers.setId(DataUtil.parseInt(supplierId));
                messSuppliers.setPayto(request.getParameter("updatepayto_"+supplierId));
                messList.add(messSuppliers);
                
            }
            
            boolean result = new MessSuppliersDAO().updateMultipleSuppliers(messList);
            request.setAttribute("suppliersupdate", result);
        }
		
	}
	

	private MessSuppliers createLedgerForSupplierAndSave(MessSuppliers messSuppliers) {
		
		MessSuppliers result = new MessSuppliers();
		
		if (httpSession.getAttribute(BRANCHID).toString()!=null) {
			
		
		Properties properties = new Properties();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("Util.properties");
		        try {
					properties.load(inputStream);
				} catch (IOException e) {
					e.printStackTrace();
				}
		    
		    String groupName = properties.getProperty("groupName");    
	        String subGroupCode = properties.getProperty("subGroupCode");
	        String ssGroupCode = properties.getProperty("ssGroupCode");
	        String accountCode = properties.getProperty("accountcode");
        
        
	        Accountdetails accountDetails = new Accountdetails();
			accountDetails.setAccountname(messSuppliers.getName());
			accountDetails.setAccountcode(accountCode);
		
			Accountsubgroupmaster accountSubGroupMaster = new Accountsubgroupmaster();
			accountSubGroupMaster.setAccountsubgroupmasterid(Integer.parseInt(subGroupCode));
			accountDetails.setAccountSubGroupMaster(accountSubGroupMaster);
		
			if(ssGroupCode!=null) {
				Accountssgroupmaster accountSSGroup = new Accountssgroupmaster();
				accountSSGroup.setSsgroupmasterid(Integer.parseInt(ssGroupCode));
				accountDetails.setAccountSSGroupMaster(accountSSGroup);
			}
		
		Accountgroupmaster accountGroupMaster = new Accountgroupmaster();
		accountGroupMaster.setAccountgroupid(Integer.parseInt(groupName));
		accountDetails.setAccountGroupMaster(accountGroupMaster);
		accountDetails.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		
			// Add account balance
			Financialaccountingyear financialyear = new AccountDAO().getFinancialAccountingYear(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			Accountdetailsbalance accountDetailsBalance = new Accountdetailsbalance();
			accountDetailsBalance.setAccountDetails(accountDetails);
			if(findCrDr(groupName)){
				accountDetailsBalance.setCrdr("Cr");
			}else{
				accountDetailsBalance.setCrdr("Dr");
			}
			accountDetailsBalance.setFinancialid(financialyear.getFinancialid());
			accountDetailsBalance.setOpeningbalance(new BigDecimal(0));
			accountDetailsBalance.setCurrentbalance(new BigDecimal(0));
			accountDetailsBalance.setEnteredon(new Date());
			accountDetailsBalance.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			result = new MessSuppliersDAO().addNewSupplier(accountDetails, accountDetailsBalance, messSuppliers);
		
		}
		
		return result;
		
	}
	
	private boolean findCrDr(String groupName) {
		String[] groupOne = {"2","3","4"};
		for (String group : groupOne) {
			if(group.equalsIgnoreCase(groupName)){
				return true;
			}
		}
		return false;
	}
	
}
