package com.model.mess.supplier.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
import com.model.account.dto.VoucherEntrytransactions;
import com.model.mess.item.dao.MessItemsDAO;
import com.model.mess.supplier.dao.MessSuppliersDAO;
import com.model.mess.supplier.dto.MessSuppliers;
import com.model.mess.supplier.dto.MessSuppliersPayment;
import com.model.student.dao.studentDetailsDAO;
import com.model.student.dto.Student;
import com.util.DataUtil;
import com.util.DateUtil;

public class MessSuppliersService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;
	private String BRANCHID = "branchid";
	private String USERID = "userloginid";
	
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
			 messSuppliers.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
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
		accountDetails.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
		
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
			accountDetailsBalance.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
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


	public void getSupplierBalance() throws IOException {
		
		String sup = request.getParameter("supplierid");
		String[] supplieridledgerid = sup.split(":");
		Locale indiaLocale = new Locale("en", "IN");
		PrintWriter out = response.getWriter(); 
		response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        
		if(!sup.isEmpty()) {
			
			Accountdetailsbalance accountDetailsBalance =  new MessSuppliersDAO().getSupplierBalance(supplieridledgerid[1]);
			NumberFormat currency  = NumberFormat.getCurrencyInstance(indiaLocale);
		        
		        try {
		        		String buffer = "<input name='balance'  type='text' class='textfieldvalues' id='balance' value="+currency.format(accountDetailsBalance.getCurrentbalance())+" style='font-size: 14px;' readonly>";
		        	
			        	response.getWriter().println(buffer);
		        	
		        } catch (Exception e) {
		            out.write("<input name='balance'  type='text' class='textfieldvalues' id='balance'  style='font-size: 14px;' readonly>");
		        } finally {
		            out.flush();
		            out.close();
		        }
		}else {
					        
		        try {
		        		String buffer = "<input name='balance' type='text' class='textfieldvalues' id='balance' style='font-size: 14px;' readonly>";
			        	response.getWriter().println(buffer);
		        	
		        } catch (Exception e) {
		            out.write("<input name='balance'  type='text' class='textfieldvalues' id='balance'  style='font-size: 14px;' readonly>");
		        } finally {
		            out.flush();
		            out.close();
		        }
		}
	}


	public void issueCheque() {
		
		String date = request.getParameter("transactiondate");
		String sup = request.getParameter("supplierid");
		String[] supplieridledgerid = sup.split(":");
		String chequeNo = request.getParameter("chequeno");
		String issueAmount = request.getParameter("chequeamount");
		String amount = issueAmount.replace(",", "");
											  	
		if (httpSession.getAttribute(BRANCHID).toString()!=null) {
			
			MessSuppliersPayment messSuppliersPayment = new MessSuppliersPayment();
			messSuppliersPayment.setAmount(Float.parseFloat(amount));
			messSuppliersPayment.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			messSuppliersPayment.setChequeno(chequeNo);
			messSuppliersPayment.setIssuedate(DateUtil.indiandateParser(date));
			messSuppliersPayment.setExternalid(supplieridledgerid[2]+"_"+supplieridledgerid[1]);
			messSuppliersPayment.setStatus("ISSUED");
			messSuppliersPayment.setEntrydate(DateUtil.todaysDate());
			messSuppliersPayment.setSupplierid(Integer.parseInt(supplieridledgerid[0]));
			messSuppliersPayment.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
			
			//Pass J.V. : Credit the Cheque Awaiting Settlement & debit the Payment Awaiting Settlement 
			int crCasId = getLedgerAccountId("CAS");
			int drPasId = getLedgerAccountId("PAS");
			
			VoucherEntrytransactions transactions = new VoucherEntrytransactions();
			
			transactions.setDraccountid(drPasId);
			transactions.setCraccountid(crCasId);
			transactions.setDramount(new BigDecimal(amount));
			transactions.setCramount(new BigDecimal(amount));
			transactions.setVouchertype(4);
			transactions.setTransactiondate(DateUtil.indiandateParser(date));
			transactions.setEntrydate(DateUtil.todaysDate());
			transactions.setNarration("Towards Payment of supplier '"+supplieridledgerid[2]+"'");
			transactions.setCancelvoucher("no");
			transactions.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())).getFinancialid());
			transactions.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			transactions.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
			
			String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+amount+" where accountdetailsid="+crCasId;

			String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+amount+" where accountdetailsid="+drPasId;
			
			
			
			boolean result = new MessSuppliersDAO().saveIssueCheque(messSuppliersPayment,transactions,updateCrAccount,updateDrAccount);
			
			request.setAttribute("supplierpaymentissued", result);
		}
		
	}
	
	private Integer getLedgerAccountId(String itemAccount) {
		
	 	
	 	Properties properties = new Properties();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("Util.properties");
		
        		try {
					properties.load(inputStream);
				} catch (IOException e) {
					e.printStackTrace();
				}
		    
        		String ItemLedgerId = properties.getProperty(itemAccount);
		    
		    if(ItemLedgerId!=null) {
		    	return Integer.parseInt(ItemLedgerId);
		    }else {
		    	return 0;
		    }
	}


	public boolean viewSuppliersPaymentDetails() {

		boolean result = false;

		String pages = "1";
		try {
			int page = 1;
			int recordsPerPage = 50;
			
			if (!"".equalsIgnoreCase(DataUtil.emptyString(request.getParameter("page")))) {
				page = Integer.parseInt(request.getParameter("page"));
			}

			List<MessSuppliersPayment> supplierPaymentlist = new MessSuppliersDAO().readListOfSuppliersPaymentPagination((page - 1) * recordsPerPage,
					recordsPerPage, Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			int noOfRecords = new MessSuppliersDAO().getNoOfSuppliersPaymentDetails(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
			request.setAttribute("supplierpaymentlist", supplierPaymentlist);
			request.setAttribute("noOfPages", noOfPages);
			request.setAttribute("currentPage", page);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}


	public void deliveredCheque() {
		
		String date = request.getParameter("deliverydate");
		String[] supplierIds = request.getParameterValues("supplierpaymentid");
		
		
		if (httpSession.getAttribute(BRANCHID).toString()!=null) {
			
			List<MessSuppliersPayment> messSuppliersPaymentList = new ArrayList<MessSuppliersPayment>();
			
			for (String supid : supplierIds) {
				
				MessSuppliersPayment messSuppliersPayment = new MessSuppliersPayment();
				messSuppliersPayment.setId(Integer.parseInt(supid));
				messSuppliersPayment.setDelivereddate(DateUtil.indiandateParser(date));
				messSuppliersPayment.setStatus("DELIVERED");
				messSuppliersPaymentList.add(messSuppliersPayment);
				
			}
			
			
			boolean result = new MessSuppliersDAO().updateSupplierPaymentDelivered(messSuppliersPaymentList);
			request.setAttribute("chequedelivered", result);
		}
		
	}


	public void clearedCheque() {
		
		String date = request.getParameter("cleardate");
		String bankName = request.getParameter("bankname");
		String[] supplierIds = request.getParameterValues("supplierpaymentid");
		
		
		if (httpSession.getAttribute(BRANCHID).toString()!=null) {
			
			
			for (String supid : supplierIds) {
				
				String supplierName = request.getParameter("externalid_"+supid);
				String issueAmount = request.getParameter("chequeamount_"+supid);
				String amount = issueAmount.replace(",", "");
				
				MessSuppliersPayment messSuppliersPayment = new MessSuppliersPayment();
				messSuppliersPayment.setId(Integer.parseInt(supid));
				messSuppliersPayment.setDelivereddate(DateUtil.indiandateParser(date));
				messSuppliersPayment.setStatus("CLEARED");
				messSuppliersPayment.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
				
				//Pass J.V. : Credit the Bank & debit the Cheque Awaiting Settlement 
				int crBankId = getLedgerAccountId(bankName);
				int drCasId = getLedgerAccountId("CAS");
				
				VoucherEntrytransactions transactions = new VoucherEntrytransactions();
				
				transactions.setDraccountid(drCasId);
				transactions.setCraccountid(crBankId);
				transactions.setDramount(new BigDecimal(amount));
				transactions.setCramount(new BigDecimal(amount));
				transactions.setVouchertype(4);
				transactions.setTransactiondate(DateUtil.indiandateParser(date));
				transactions.setEntrydate(DateUtil.todaysDate());
				transactions.setNarration("Towards Payment of supplier '"+supplierName+"'");
				transactions.setCancelvoucher("no");
				transactions.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())).getFinancialid());
				transactions.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
				transactions.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
				
				String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+amount+" where accountdetailsid="+crBankId;
				String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+amount+" where accountdetailsid="+drCasId;
				
				
				
				//Pass J.V. : Credit the Payment Awaiting Settlement & Debit the Supplier 
				int crPasId = getLedgerAccountId("PAS");
				int drSupplierLedgerId = Integer.parseInt(request.getParameter("supplierledgerid_"+supid));
				
				VoucherEntrytransactions transactionsSupplier = new VoucherEntrytransactions();
				
				transactionsSupplier.setDraccountid(drSupplierLedgerId);
				transactionsSupplier.setCraccountid(crPasId);
				transactionsSupplier.setDramount(new BigDecimal(amount));
				transactionsSupplier.setCramount(new BigDecimal(amount));
				transactionsSupplier.setVouchertype(4);
				transactionsSupplier.setTransactiondate(DateUtil.indiandateParser(date));
				transactionsSupplier.setEntrydate(DateUtil.todaysDate());
				transactionsSupplier.setNarration("Towards Payment of supplier '"+supplierName+"'");
				transactionsSupplier.setCancelvoucher("no");
				transactionsSupplier.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())).getFinancialid());
				transactionsSupplier.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
				transactionsSupplier.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
				
				String updateDrAccountSupplier = "update Accountdetailsbalance set currentbalance=currentbalance-"+amount+" where accountdetailsid="+crPasId;
				String updateCrAccountSupplier = "update Accountdetailsbalance set currentbalance=currentbalance-"+amount+" where accountdetailsid="+drSupplierLedgerId;
				
				boolean result = new MessSuppliersDAO().updateSupplierPayment(messSuppliersPayment,transactions,updateCrAccount,updateDrAccount,transactionsSupplier,updateCrAccountSupplier,updateDrAccountSupplier);
				request.setAttribute("chequecleared", result);
			}
			
			
			
		}
		
	}


	public void cancelCheque() {
		
		String[] supplierIds = request.getParameterValues("supplierpaymentid");
		
		
		if (httpSession.getAttribute(BRANCHID).toString()!=null) {
			
			for (String supId : supplierIds) {
				String status = request.getParameter("status_"+supId);
				
					if("DELIVERED".equalsIgnoreCase(status)) {
						new MessSuppliersDAO().updateSupplierPaymentToIssueed("update MessSuppliersPayment set status='ISSUED' where id="+supId+"");
					}else if("ISSUED".equalsIgnoreCase(status)) {
						String issueVoucherId = request.getParameter("issuevoucherid_"+supId);
						String chequeAmount = request.getParameter("chequeamount_"+supId);
						String amount = chequeAmount.replace(",", "");
						String supplierName = request.getParameter("externalid_"+supId);
						
						// Reverse entry for issue cheque

						//Pass J.V. : Credit the Cheque Awaiting Settlement & debit the Payment Awaiting Settlement 
						int drCasId = getLedgerAccountId("CAS");
						int CrPasId = getLedgerAccountId("PAS");
						
						VoucherEntrytransactions transactions = new VoucherEntrytransactions();
						
						transactions.setDraccountid(drCasId);
						transactions.setCraccountid(CrPasId);
						transactions.setDramount(new BigDecimal(amount));
						transactions.setCramount(new BigDecimal(amount));
						transactions.setVouchertype(4);
						transactions.setTransactiondate(DateUtil.todaysDate());
						transactions.setEntrydate(DateUtil.todaysDate());
						transactions.setNarration("Towards reversal of payment to supplier '"+supplierName+"' and voucher # '"+issueVoucherId+"'");
						transactions.setCancelvoucher("no");
						transactions.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())).getFinancialid());
						transactions.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
						transactions.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
						
						String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+amount+" where accountdetailsid="+CrPasId;

						String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+amount+" where accountdetailsid="+drCasId;
						
						String updateMessSupplierPayment = "update MessSuppliersPayment set status='CANCELLED' where id="+supId+"";
						
						boolean result = new MessSuppliersDAO().reverseIssueCheque(updateMessSupplierPayment,transactions,updateCrAccount,updateDrAccount);
					
						request.setAttribute("chequecancelled", result);
						
						// End reverse entry
						
					}
				
			}
			
		}
		
	}


	public void viewBalanceSuppliers() {

		
		List<MessSuppliers> messSuppliersList = new ArrayList<MessSuppliers>();
		List<Accountdetailsbalance> accountdetailsbalanceList = new ArrayList<Accountdetailsbalance>();
			
		
		 if(httpSession.getAttribute(BRANCHID)!=null){
			 	messSuppliersList =	new MessSuppliersDAO().getSupplierDetails();
			 	
			 	List<Integer> supplierLedgerId = new ArrayList<Integer>();
			 	
			 	for (MessSuppliers messSuppliers : messSuppliersList) {
			 				supplierLedgerId.add(messSuppliers.getLinkedledgerid());
				}
			 	
			 	accountdetailsbalanceList = new AccountDAO().getAccountBalanceDetails(supplierLedgerId, Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			 	
			 	
		 }
		 
		 request.setAttribute("supplierbalancedetails", accountdetailsbalanceList);
	}
	
}
