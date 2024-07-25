package org.ideoholic.curium.model.mess.supplier.service;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.account.dao.AccountDAO;
import org.ideoholic.curium.model.account.dto.*;
import org.ideoholic.curium.model.mess.supplier.dao.MessSuppliersDAO;
import org.ideoholic.curium.model.mess.supplier.dto.ChequeDetailsDto;
import org.ideoholic.curium.model.mess.supplier.dto.ChequeDto;
import org.ideoholic.curium.model.mess.supplier.dto.MessSuppliers;
import org.ideoholic.curium.model.mess.supplier.dto.MessSuppliersPayment;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.DateUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;

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


	public ResultResponse viewSuppliersDetails(String branchId) {
		
		List<MessSuppliers> messSuppliersList = new ArrayList<MessSuppliers>();
		
		 if(branchId!=null){
			 	messSuppliersList =	new MessSuppliersDAO().getSupplierDetails();
		 }
		 
		 return ResultResponse
				 .builder()
				 .resultList(messSuppliersList)
				 .success(true)
				 .build();
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


	public ResultResponse issueCheque(ChequeDto dto, String branchId, String userId) {
		ResultResponse resultResponse = ResultResponse.builder().build();
		
		String date = dto.getDate();
		String sup = dto.getSupplierId();
		String[] supplieridledgerid = sup.split(":");
		String chequeNo = dto.getChequeNo();
		String issueAmount = dto.getIssueAmount();
		String amount = issueAmount.replace(",", "");
											  	
		if (branchId!=null) {
			
			MessSuppliersPayment messSuppliersPayment = new MessSuppliersPayment();
			messSuppliersPayment.setAmount(Float.parseFloat(amount));
			messSuppliersPayment.setBranchid(Integer.parseInt(branchId));
			messSuppliersPayment.setChequeno(chequeNo);
			messSuppliersPayment.setIssuedate(DateUtil.indiandateParser(date));
			messSuppliersPayment.setExternalid(supplieridledgerid[2]+"_"+supplieridledgerid[1]);
			messSuppliersPayment.setStatus("ISSUED");
			messSuppliersPayment.setEntrydate(DateUtil.todaysDate());
			messSuppliersPayment.setSupplierid(Integer.parseInt(supplieridledgerid[0]));
			messSuppliersPayment.setUserid(Integer.parseInt(userId));
			
			//Pass J.V. : Credit the Cheque Awaiting Settlement & debit the Payment Awaiting Settlement 
			int crCasId = getLedgerAccountId("CAS"+Integer.parseInt(branchId));
			int drPasId = getLedgerAccountId("PAS"+Integer.parseInt(branchId));
			
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
			transactions.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(branchId)).getFinancialid());
			transactions.setBranchid(Integer.parseInt(branchId));
			transactions.setUserid(Integer.parseInt(userId));
			
			String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+amount+" where accountdetailsid="+crCasId;

			String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+amount+" where accountdetailsid="+drPasId;
			
			
			
			boolean result = new MessSuppliersDAO().saveIssueCheque(messSuppliersPayment,transactions,updateCrAccount,updateDrAccount);

			resultResponse.setSuccess(result);
		}
		resultResponse.setSuccess(true);
		return resultResponse;
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


	public ResultResponse clearedCheque(ChequeDetailsDto dto, String branchId, String userId) {
		ResultResponse resultResponse = ResultResponse.builder().build();
		
		String date = dto.getDate();
		String bankName = dto.getBankName();
		String[] supplierIds = dto.getSupplierIds();
		
		
		if (branchId!=null) {
			
			
			for (String supid : supplierIds) {
				
				String supplierName = dto.getRequestParams().get("externalid_"+supid);
				String issueAmount = dto.getRequestParams().get("chequeamount_"+supid);
				String amount = issueAmount.replace(",", "");
				String paymentType = dto.getRequestParams().get("chequeno_"+supid);

				if ("Cash".equalsIgnoreCase(paymentType)) {
						paymentType = "By Cash";

				}else {
					paymentType = "By Cheque #: "+paymentType;
				}
				
				MessSuppliersPayment messSuppliersPayment = new MessSuppliersPayment();
				messSuppliersPayment.setId(Integer.parseInt(supid));
				messSuppliersPayment.setDelivereddate(DateUtil.indiandateParser(date));
				messSuppliersPayment.setStatus("CLEARED");
				messSuppliersPayment.setUserid(Integer.parseInt(userId));
				
				//Pass J.V. : Credit the Bank & debit the Cheque Awaiting Settlement 
				int crBankId = getLedgerAccountId(bankName+Integer.parseInt(branchId));
				int drCasId = getLedgerAccountId("CAS"+Integer.parseInt(branchId));
				
				VoucherEntrytransactions transactions = new VoucherEntrytransactions();
				
				transactions.setDraccountid(drCasId);
				transactions.setCraccountid(crBankId);
				transactions.setDramount(new BigDecimal(amount));
				transactions.setCramount(new BigDecimal(amount));
				transactions.setVouchertype(4);
				transactions.setTransactiondate(DateUtil.indiandateParser(date));
				transactions.setEntrydate(DateUtil.todaysDate());
				transactions.setNarration("Towards Payment of supplier '"+supplierName+"' : "+paymentType);
				transactions.setCancelvoucher("no");
				transactions.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(branchId)).getFinancialid());
				transactions.setBranchid(Integer.parseInt(branchId));
				transactions.setUserid(Integer.parseInt(userId));
				
				String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+amount+" where accountdetailsid="+crBankId;
				String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+amount+" where accountdetailsid="+drCasId;
				
				
				
				//Pass J.V. : Credit the Payment Awaiting Settlement & Debit the Supplier 
				int crPasId = getLedgerAccountId("PAS"+Integer.parseInt(branchId));
				int drSupplierLedgerId = Integer.parseInt(dto.getRequestParams().get("supplierledgerid_"+supid));
				
				VoucherEntrytransactions transactionsSupplier = new VoucherEntrytransactions();
				
				transactionsSupplier.setDraccountid(drSupplierLedgerId);
				transactionsSupplier.setCraccountid(crPasId);
				transactionsSupplier.setDramount(new BigDecimal(amount));
				transactionsSupplier.setCramount(new BigDecimal(amount));
				transactionsSupplier.setVouchertype(4);
				transactionsSupplier.setTransactiondate(DateUtil.indiandateParser(date));
				transactionsSupplier.setEntrydate(DateUtil.todaysDate());
				transactionsSupplier.setNarration("Towards Payment of supplier '"+supplierName+"' : "+paymentType);
				transactionsSupplier.setCancelvoucher("no");
				transactionsSupplier.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(branchId)).getFinancialid());
				transactionsSupplier.setBranchid(Integer.parseInt(branchId));
				transactionsSupplier.setUserid(Integer.parseInt(userId));
				
				String updateDrAccountSupplier = "update Accountdetailsbalance set currentbalance=currentbalance-"+amount+" where accountdetailsid="+crPasId;
				String updateCrAccountSupplier = "update Accountdetailsbalance set currentbalance=currentbalance-"+amount+" where accountdetailsid="+drSupplierLedgerId;
				
				boolean result = new MessSuppliersDAO().updateSupplierPayment(messSuppliersPayment,transactions,updateCrAccount,updateDrAccount,transactionsSupplier,updateCrAccountSupplier,updateDrAccountSupplier);
				resultResponse.setSuccess(result);
			}
			
			
			
		}
		resultResponse.setSuccess(true);
		return resultResponse;
	}


	public ResultResponse cancelCheque(ChequeDetailsDto dto, String branchId, String userId) {
		ResultResponse resultResponse = ResultResponse.builder().build();
		
		String[] supplierIds = dto.getSupplierIds();
		
		
		if (branchId!=null) {
			
			for (String supId : supplierIds) {
				String status = dto.getRequestParams().get("status_"+supId);
				
					if("DELIVERED".equalsIgnoreCase(status)) {
						new MessSuppliersDAO().updateSupplierPaymentToIssueed("update MessSuppliersPayment set status='ISSUED' where id="+supId+"");
					}else if("ISSUED".equalsIgnoreCase(status)) {
						String issueVoucherId = dto.getRequestParams().get("issuevoucherid_"+supId);
						String chequeAmount = dto.getRequestParams().get("chequeamount_"+supId);
						String amount = chequeAmount.replace(",", "");
						String supplierName = dto.getRequestParams().get("externalid_"+supId);
						
						// Reverse entry for issue cheque

						//Pass J.V. : Credit the Cheque Awaiting Settlement & debit the Payment Awaiting Settlement 
						int drCasId = getLedgerAccountId("CAS"+Integer.parseInt(branchId));
						int CrPasId = getLedgerAccountId("PAS"+Integer.parseInt(branchId));
						
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
						transactions.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(branchId)).getFinancialid());
						transactions.setBranchid(Integer.parseInt(branchId));
						transactions.setUserid(Integer.parseInt(userId));
						
						String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+amount+" where accountdetailsid="+CrPasId;

						String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+amount+" where accountdetailsid="+drCasId;
						
						String updateMessSupplierPayment = "update MessSuppliersPayment set status='CANCELLED' where id="+supId+"";
						
						boolean result = new MessSuppliersDAO().reverseIssueCheque(updateMessSupplierPayment,transactions,updateCrAccount,updateDrAccount);

						resultResponse.setSuccess(result);
						
						// End reverse entry
						
					}
				
			}
			
		}
		resultResponse.setSuccess(true);
		return resultResponse;
	}


	public ResultResponse viewBalanceSuppliers(String branchId) {

		
		List<MessSuppliers> messSuppliersList;
		List<Accountdetailsbalance> accountdetailsbalanceList = new ArrayList<>();

		
		 if(branchId!=null){
			 	messSuppliersList =	new MessSuppliersDAO().getSupplierDetails();
			 	
			 	List<Integer> supplierLedgerId = new ArrayList<>();
			 	
			 	for (MessSuppliers messSuppliers : messSuppliersList) {
			 				supplierLedgerId.add(messSuppliers.getLinkedledgerid());
				}
			 	
			 	accountdetailsbalanceList = new AccountDAO().getAccountBalanceDetails(supplierLedgerId, Integer.parseInt(branchId));
			 	
			 	
		 }
		 return ResultResponse
				 .builder()
				 .resultList(accountdetailsbalanceList)
				 .success(true)
				 .build();
	}
	
}
