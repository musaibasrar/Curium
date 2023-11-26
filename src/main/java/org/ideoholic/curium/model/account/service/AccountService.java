package org.ideoholic.curium.model.account.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ideoholic.curium.model.account.dao.AccountDAO;
import org.ideoholic.curium.model.account.dto.Accountdetails;
import org.ideoholic.curium.model.account.dto.Accountdetailsbalance;
import org.ideoholic.curium.model.account.dto.Accountgroupmaster;
import org.ideoholic.curium.model.account.dto.Accountssgroupmaster;
import org.ideoholic.curium.model.account.dto.Accountsubgroupmaster;
import org.ideoholic.curium.model.account.dto.Financialaccountingyear;
import org.ideoholic.curium.model.account.dto.VoucherEntrytransactions;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.DateUtil;

public class AccountService {
	
	 	private HttpServletRequest request;
	    private HttpServletResponse response;
	    private HttpSession httpSession;
	    private String BRANCHID = "branchid";
	    
	    private static final int BUFFER_SIZE = 4096;
	
	public AccountService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
        this.response = response;
        this.httpSession = request.getSession();
	}


	public boolean saveFinancialYear() {

		Financialaccountingyear financialaccountingyear = new Financialaccountingyear();
		
		if(httpSession.getAttribute(BRANCHID)!=null){
			financialaccountingyear.setFinancialstartdate(DateUtil.dateParserUpdateStd(request.getParameter("fromdate")));
			financialaccountingyear.setFinancialenddate(DateUtil.dateParserUpdateStd(request.getParameter("todate")));
			financialaccountingyear.setActive(DataUtil.emptyString(request.getParameter("active")));
			financialaccountingyear.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			return new AccountDAO().create(financialaccountingyear, Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		}

		return false;
	}

	public boolean getCurrentFinancialYear() {
		Financialaccountingyear financialYear = new Financialaccountingyear();
		if(httpSession.getAttribute(BRANCHID)!=null){
			financialYear =  new AccountDAO().getCurrentFinancialYear(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		}
		
		if(financialYear!=null){
	                  request.setAttribute("currentfinancialaccountingyearfrom", financialYear.getFinancialstartdate());
	                  request.setAttribute("currentfinancialaccountingyearto", financialYear.getFinancialenddate());
		}
		
		return true;
	}


	public boolean createAccount() {
		
		List<Accountdetailsbalance> accountDetailsBalance = new ArrayList<Accountdetailsbalance>();
		List<Accountgroupmaster> accountGroupMaster = new ArrayList<Accountgroupmaster>();
		
		if(httpSession.getAttribute(BRANCHID)!=null){
			
			accountGroupMaster = new AccountDAO().getListAccountGroupMaster(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			request.setAttribute("accountgroupmaster", accountGroupMaster);
			
			accountDetailsBalance = new AccountDAO().getAccountdetailsbalance(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			request.setAttribute("accountdetailsbalance", accountDetailsBalance);
		}
		
		
		if(!accountGroupMaster.isEmpty()){
			return true;
		} return false;
		
	}


	public void getSubGroupNames() throws IOException {
		
		List<Accountsubgroupmaster> accountSubGroupMaster = new ArrayList<Accountsubgroupmaster>();
		
		if(httpSession.getAttribute(BRANCHID)!=null){
			int accountGroupMasterId = Integer.parseInt(request.getParameter("groupname"));
			accountSubGroupMaster = new AccountDAO().getListAccountSubGroupMaster(accountGroupMasterId,Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			request.setAttribute("accountsubgroupmaster", accountSubGroupMaster);
			PrintWriter out = response.getWriter(); 
			response.setContentType("text/xml");
		        response.setHeader("Cache-Control", "no-cache");
		        try {
		        	
		        	/*String buffer = "<select name='subgroupname' > <option value='-1'>My option</option>";
		        	buffer = buffer+"</select>";*/
		        	if(!accountSubGroupMaster.isEmpty()){
		        		String buffer = "<select name='subgroupname' style='width: 240px' id='sgname' onchange='dropdowndist();getSSGroup();'>";
		        		buffer = buffer +  "<option></option>";
			        	for(int i =0; i<accountSubGroupMaster.size();i++){
			        		buffer = buffer +  "<option value="+accountSubGroupMaster.get(i).getAccountsubgroupmasterid()+">"+accountSubGroupMaster.get(i).getAccountsubgroupname()+"</option>";
			        	}
			        	buffer = buffer+"<option value='New Group'>New Group</option></select>";
			        	response.getWriter().println(buffer);
		        	}else{
		        		String buffer = "<select name='subgroupname' style='width: 240px' id='sgname' onchange='dropdowndist();getSSGroup();'>";
		        		buffer = buffer+"<option></option>";
			        	buffer = buffer+"<option value='New Group'>New Group</option>";
			        	buffer = buffer+"</select>";
			        	response.getWriter().println(buffer);
		        	}
		        	
		        } catch (Exception e) {
		            out.write("<subgroup>0</subgroup>");
		        } finally {
		            out.flush();
		            out.close();
		        }
		}
		
		
	}

	public String saveAccount() {

		String result = "false";
		String newSubGroup = DataUtil.emptyString(request.getParameter("newsubgroup"));
		String newSSGroup = DataUtil.emptyString(request.getParameter("newssgroup"));
		String subGroupName = DataUtil.emptyString(request.getParameter("subgroupname"));
		String ssGroupName = DataUtil.emptyString(request.getParameter("ssgroupname"));
		String groupName = DataUtil.emptyString(request.getParameter("groupname"));
		String accountName = DataUtil.emptyString(request.getParameter("accountname"));
		String accountCode = DataUtil.emptyString(request.getParameter("accountcode"));
		
		
		Accountdetails accountDetailsCheck = new AccountDAO().checkAccountDetails(accountName, accountCode, Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		
		if(accountDetailsCheck==null) {
			
		if(!"New Group".equalsIgnoreCase(subGroupName)){
			
				Accountdetails accountDetails = new Accountdetails();
				accountDetails.setAccountname(accountName);
				accountDetails.setAccountcode(accountCode);
				
				
				if(getInt(subGroupName)!=null){
					Accountsubgroupmaster accountSubGroupMaster = new Accountsubgroupmaster();
					accountSubGroupMaster.setAccountsubgroupmasterid(getInt(subGroupName));
					accountDetails.setAccountSubGroupMaster(accountSubGroupMaster);
				}
				
				if(!"New Sub-Group".equalsIgnoreCase(ssGroupName)) {
					if(getInt(ssGroupName)!=null) {
						Accountssgroupmaster accountSSGroup = new Accountssgroupmaster();
						accountSSGroup.setSsgroupmasterid(getInt(ssGroupName));
						accountDetails.setAccountSSGroupMaster(accountSSGroup);
					}
				}else if("New Sub-Group".equalsIgnoreCase(ssGroupName)){
					
					Accountsubgroupmaster accountSubGroupMaster = new Accountsubgroupmaster();
					Accountssgroupmaster accountSSGroupMaster = new Accountssgroupmaster();
					accountSubGroupMaster.setAccountsubgroupmasterid(Integer.parseInt(subGroupName));
					accountSSGroupMaster.setAccountSubGroupMaster(accountSubGroupMaster);
					accountSSGroupMaster.setSsgroupname(newSSGroup);
					accountSSGroupMaster.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
					accountSSGroupMaster = new AccountDAO().createSSGroup(accountSSGroupMaster);
					accountDetails.setAccountSSGroupMaster(accountSSGroupMaster);
				}
				
				Accountgroupmaster accountGroupMaster = new Accountgroupmaster();
				accountGroupMaster.setAccountgroupid(getInt(groupName));
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
					result = new AccountDAO().saveNewAccount(accountDetails, accountDetailsBalance);
					
		}else if("New Group".equalsIgnoreCase(subGroupName)){

			Accountsubgroupmaster accountSubGroupMaster = new Accountsubgroupmaster();
			Accountssgroupmaster accountSSGroupMaster = new Accountssgroupmaster();
			Accountgroupmaster accountGroup = new Accountgroupmaster();
			
			accountGroup.setAccountgroupid(Integer.parseInt(groupName));
			accountSubGroupMaster.setAccountGroupMaster(accountGroup);
			accountSubGroupMaster.setAccountsubgroupname(newSubGroup);
			accountSubGroupMaster.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			accountSubGroupMaster = new AccountDAO().createSubGroup(accountSubGroupMaster);
			
			 if("New Sub-Group".equalsIgnoreCase(ssGroupName)){
					
					accountSubGroupMaster.setAccountsubgroupmasterid(accountSubGroupMaster.getAccountsubgroupmasterid());
					accountSSGroupMaster.setAccountSubGroupMaster(accountSubGroupMaster);
					accountSSGroupMaster.setSsgroupname(newSSGroup);
					accountSSGroupMaster.setSsgroupname(newSSGroup);
					accountSSGroupMaster.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
					accountSSGroupMaster = new AccountDAO().createSSGroup(accountSSGroupMaster);
					
				}
			
				Accountdetails accountDetails = new Accountdetails();
				Accountgroupmaster accountGroupMaster = new Accountgroupmaster();
				//Group
				Accountsubgroupmaster accountSubGroup = new Accountsubgroupmaster();
				accountSubGroup.setAccountsubgroupmasterid(accountSubGroupMaster.getAccountsubgroupmasterid());
				accountDetails.setAccountSubGroupMaster(accountSubGroup);
				//Sub-Group
				Accountssgroupmaster accountSSGroup = new Accountssgroupmaster();
				accountSSGroup.setSsgroupmasterid(accountSSGroupMaster.getSsgroupmasterid());
				accountDetails.setAccountSSGroupMaster(accountSSGroup);
				//Account Details
				accountDetails.setAccountname(accountName);
				accountDetails.setAccountcode(accountCode);
				accountGroupMaster.setAccountgroupid(Integer.parseInt(groupName));
				accountDetails.setAccountGroupMaster(accountGroupMaster);
				accountDetails.setAccountsubgroupmasterid(accountSubGroupMaster.getAccountsubgroupmasterid());
				accountDetails.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
				
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
					result = new AccountDAO().saveNewAccount(accountDetails, accountDetailsBalance);
		}}else {
				if(accountName.equalsIgnoreCase(accountDetailsCheck.getAccountname())) {
					result = "Error-Account Name already exists";
				}else if(accountCode.equalsIgnoreCase(accountDetailsCheck.getAccountcode())) {
					result = "Error-Account Code already exists";
				}
		}
		request.setAttribute("createaccountalert", result);
		return result;
	}

	private Integer getInt(String subGroupName) {

		try {
			return Integer.parseInt(subGroupName);
		} catch (Exception e) {
			return null;
		}
		
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


	public boolean deleteAccount() {
		String[] accountIds = request.getParameterValues("accountids");
		if (accountIds != null) {
			List<Integer> balanceIds = new ArrayList<Integer>();
			List<Integer> accountdetailsIds = new ArrayList<Integer>();
			for (String id : accountIds) {
				String[] split = id.split("-");
				balanceIds.add(Integer.valueOf(split[0]));
				accountdetailsIds.add(Integer.valueOf(split[1]));
				boolean checkInTransactions = new AccountDAO().checkInTransactions(Integer.valueOf(split[1]));
				if(!checkInTransactions){
					new AccountDAO().deleteMultipleAccounts(Integer.valueOf(split[0]),Integer.valueOf(split[1]));
				}
			}
			return true;
		}
		return false;
	}


	public boolean createVoucher() {
		
		List<Accountdetailsbalance> accountDetailsBalance = new ArrayList<Accountdetailsbalance>();
		if(httpSession.getAttribute(BRANCHID)!=null) {
			List<Integer> accountIds = new ArrayList<Integer>();
			accountIds.add(2);
			accountIds.add(3);
			accountIds.add(4);
		accountDetailsBalance = new AccountDAO().getAccountdetailsbalanceExBC(accountIds, Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		request.setAttribute("accountdetailsbalanceexbc", accountDetailsBalance);
			accountIds.clear();
			accountIds.add(5);
		accountDetailsBalance = new AccountDAO().getAccountdetailsbalanceExBC(accountIds, Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		request.setAttribute("accountdetailsbalanceexpacc", accountDetailsBalance);
		
		List<Accountdetailsbalance> accountDetailsBalanceBankCash = new ArrayList<Accountdetailsbalance>();
		accountDetailsBalanceBankCash = new AccountDAO().getAccountdetailsbalanceBankCash(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		request.setAttribute("accountdetailsbalancecontra", accountDetailsBalanceBankCash);
		request.setAttribute("accountdetailsbalancereceipt", accountDetailsBalanceBankCash);
		request.setAttribute("accountdetailsbalancepayment", accountDetailsBalanceBankCash);
		
		List<Accountdetailsbalance> accountDetailsJournalEntry = new ArrayList<Accountdetailsbalance>();
		accountDetailsJournalEntry = new AccountDAO().getAccountdetailsbalance(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		request.setAttribute("accountdetailsbalancejournal", accountDetailsJournalEntry);
		return true;
		}
		return false;
	}


	public boolean saveReceipt() {
		
		String draccountName = DataUtil.emptyString(request.getParameter("accountname"));
		String craccountName = DataUtil.emptyString(request.getParameter("accountnamesecond"));
		String receiptVoucher = DataUtil.emptyString(request.getParameter("receiptvoucher"));
		String drAmount = DataUtil.emptyString(request.getParameter("dramount"));
		String crAmount = DataUtil.emptyString(request.getParameter("cramountsecond"));
		String receiptDate = DataUtil.emptyString(request.getParameter("dateofreceipt"));
		String receiptVoucherNo = DataUtil.emptyString(request.getParameter("receiptvoucherno"));
		String receiptNarration = DataUtil.emptyString(request.getParameter("receiptnarration"));
		
		VoucherEntrytransactions transactions = new VoucherEntrytransactions();
		
		transactions.setDraccountid(Integer.parseInt(draccountName));
		transactions.setCraccountid(Integer.parseInt(craccountName));
		transactions.setDramount(new BigDecimal(drAmount));
		transactions.setCramount(new BigDecimal(crAmount));
		transactions.setVouchertype(Integer.parseInt(receiptVoucher));
		transactions.setTransactiondate(DateUtil.indiandateParser(receiptDate));
		transactions.setEntrydate(DateUtil.todaysDate());
		transactions.setVoucherno(receiptVoucherNo);
		transactions.setNarration(receiptNarration);
		transactions.setCancelvoucher("no");
		transactions.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())).getFinancialid());
		transactions.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		
		
		BigDecimal drAmountReceipt = new BigDecimal(drAmount);
		String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+drAmountReceipt+" where accountdetailsid="+Integer.parseInt(draccountName);

		BigDecimal crAmountReceipt = new BigDecimal(crAmount);
		String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+crAmountReceipt+" where accountdetailsid="+Integer.parseInt(craccountName);
		
		return new AccountDAO().saveVoucherwithAccUpdate(transactions,updateDrAccount,updateCrAccount);
		
	}


	public boolean savePayment() {
		
		String draccountNamePayment = DataUtil.emptyString(request.getParameter("accountnamepayment"));
		String craccountNamePayment = DataUtil.emptyString(request.getParameter("accountnamepaymentsecond"));
		String paymentVoucher = DataUtil.emptyString(request.getParameter("paymentvoucher"));
		String drAmountPayment = DataUtil.emptyString(request.getParameter("dramountpayment"));
		String crAmountPayment = DataUtil.emptyString(request.getParameter("cramountpaymentsecond"));
		String paymentDate = DataUtil.emptyString(request.getParameter("dateofpayment"));
		String paymentVoucherNo = DataUtil.emptyString(request.getParameter("paymentvoucherno"));
		String paymentNarration = DataUtil.emptyString(request.getParameter("paymentnarration"));
		String paymentNarrationCategory = DataUtil.emptyString(request.getParameter("paymentnarrationcategory"));
		
		VoucherEntrytransactions transactions = new VoucherEntrytransactions();
		
		transactions.setDraccountid(Integer.parseInt(draccountNamePayment));
		transactions.setCraccountid(Integer.parseInt(craccountNamePayment));
		transactions.setDramount(new BigDecimal(drAmountPayment));
		transactions.setCramount(new BigDecimal(crAmountPayment));
		transactions.setVouchertype(Integer.parseInt(paymentVoucher));
		transactions.setTransactiondate(DateUtil.indiandateParser(paymentDate));
		transactions.setVoucherno(paymentVoucherNo);
		transactions.setEntrydate(DateUtil.todaysDate());
		transactions.setNarration(paymentNarrationCategory+":"+paymentNarration);
		transactions.setCancelvoucher("no");
		transactions.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		transactions.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())).getFinancialid());
		
		BigDecimal drAmount = new BigDecimal(drAmountPayment);
		String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+drAmount+" where accountdetailsid="+Integer.parseInt(draccountNamePayment);

		BigDecimal crAmount = new BigDecimal(crAmountPayment);
		String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+crAmount+" where accountdetailsid="+Integer.parseInt(craccountNamePayment);
		
		return new AccountDAO().saveVoucherwithAccUpdate(transactions,updateDrAccount,updateCrAccount);
		
	}


	public boolean saveContra() {
		
		String draccountNameContra = DataUtil.emptyString(request.getParameter("accountnamecontra"));
		String craccountNameContra = DataUtil.emptyString(request.getParameter("accountnamecontrasecond"));
		String contraVoucher = DataUtil.emptyString(request.getParameter("contravoucher"));
		String drAmountContra = DataUtil.emptyString(request.getParameter("dramountcontra"));
		String crAmountContra = DataUtil.emptyString(request.getParameter("cramountcontrasecond"));
		String contraDate = DataUtil.emptyString(request.getParameter("dateofcontra"));
		String contraVoucherNo = DataUtil.emptyString(request.getParameter("contravoucherno"));
		String contraNarration = DataUtil.emptyString(request.getParameter("contranarration"));
		
		VoucherEntrytransactions transactions = new VoucherEntrytransactions();
		
		transactions.setDraccountid(Integer.parseInt(draccountNameContra));
		transactions.setCraccountid(Integer.parseInt(craccountNameContra));
		transactions.setDramount(new BigDecimal(drAmountContra));
		transactions.setCramount(new BigDecimal(crAmountContra));
		transactions.setVouchertype(Integer.parseInt(contraVoucher));
		transactions.setTransactiondate(DateUtil.indiandateParser(contraDate));
		transactions.setEntrydate(DateUtil.todaysDate());
		transactions.setVoucherno(contraVoucherNo);
		transactions.setNarration(contraNarration);
		transactions.setCancelvoucher("no");
		transactions.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		transactions.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())).getFinancialid());
		
		BigDecimal drAmount = new BigDecimal(drAmountContra);
		String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+drAmount+" where accountdetailsid="+Integer.parseInt(draccountNameContra);

		BigDecimal crAmount = new BigDecimal(drAmountContra);
		String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+crAmount+" where accountdetailsid="+Integer.parseInt(craccountNameContra);
		
		return new AccountDAO().saveVoucherwithAccUpdate(transactions,updateDrAccount,updateCrAccount);
		
	}


	public boolean saveJournal() {
		
		String draccountNameJournal = DataUtil.emptyString(request.getParameter("accountnamejournal"));
		String craccountNameJournal = DataUtil.emptyString(request.getParameter("accountnamejournalsecond"));
		String journalVoucher = DataUtil.emptyString(request.getParameter("journalvoucher"));
		String drAmountJournal = DataUtil.emptyString(request.getParameter("dramountjournal"));
		String crAmountJournal = DataUtil.emptyString(request.getParameter("cramountjournalsecond"));
		String journalDate = DataUtil.emptyString(request.getParameter("dateofjournal"));
		String journalVoucherNo = DataUtil.emptyString(request.getParameter("journalvoucherno"));
		String journalNarration = DataUtil.emptyString(request.getParameter("journalnarration"));
		
		VoucherEntrytransactions transactions = new VoucherEntrytransactions();
		
		transactions.setDraccountid(Integer.parseInt(draccountNameJournal));
		transactions.setCraccountid(Integer.parseInt(craccountNameJournal));
		transactions.setDramount(new BigDecimal(drAmountJournal));
		transactions.setCramount(new BigDecimal(crAmountJournal));
		transactions.setVouchertype(Integer.parseInt(journalVoucher));
		transactions.setTransactiondate(DateUtil.indiandateParser(journalDate));
		transactions.setEntrydate(DateUtil.todaysDate());
		transactions.setVoucherno(journalVoucherNo);
		transactions.setNarration(journalNarration);
		transactions.setCancelvoucher("no");
		transactions.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		transactions.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())).getFinancialid());
		

		// Dr
		BigDecimal drAmount = new BigDecimal(drAmountJournal);
		Accountdetails accountDetailsDr = new AccountDAO().getAccountDetails(Integer.parseInt(draccountNameJournal));
		String updateDrAccount= null;
		if(accountDetailsDr.getAccountGroupMaster().getAccountgroupid()==1 || accountDetailsDr.getAccountGroupMaster().getAccountgroupid()==5) {
			updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+drAmount+" where accountdetailsid="+Integer.parseInt(draccountNameJournal);
		}else {
			updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+drAmount+" where accountdetailsid="+Integer.parseInt(draccountNameJournal);
		}
		
		//Cr
		
		BigDecimal crAmount = new BigDecimal(crAmountJournal);
		String updateCrAccount= null;
		Accountdetails accountDetailsCr = new AccountDAO().getAccountDetails(Integer.parseInt(craccountNameJournal));
		
		if(accountDetailsCr.getAccountGroupMaster().getAccountgroupid()==2 || accountDetailsCr.getAccountGroupMaster().getAccountgroupid()==3 || accountDetailsCr.getAccountGroupMaster().getAccountgroupid()==4) {
			updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+crAmount+" where accountdetailsid="+Integer.parseInt(craccountNameJournal);
		}else {
			updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+crAmount+" where accountdetailsid="+Integer.parseInt(craccountNameJournal);
		}
		
		return new AccountDAO().saveVoucherwithAccUpdate(transactions,updateDrAccount,updateCrAccount);
		
	}


	public boolean balanceSheet() {
		
		
		//Group 1
				BigDecimal liabilities = BigDecimal.ZERO;
				Map<String,BigDecimal> liabilitiesLedgerAccount = new HashMap<String, BigDecimal>();
				
				BigDecimal reserves = BigDecimal.ZERO;
				Map<String,BigDecimal> reservesLedgerAccount = new HashMap<String, BigDecimal>();
				
				//Group 2
				BigDecimal assets = BigDecimal.ZERO;
				Map<String,BigDecimal> assetsLedgerAccount = new HashMap<String, BigDecimal>();
				
				
				List<Accountdetailsbalance> accountDetailsBalance = new ArrayList<Accountdetailsbalance>();
				accountDetailsBalance = new AccountDAO().getAccountdetailsbalance(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
				
				
				for (Accountdetailsbalance accountdetails : accountDetailsBalance) {
					int groupId = accountdetails.getAccountDetails().getAccountGroupMaster().getAccountgroupid();

					switch(groupId){
					case 1: 
							assets = assets.add(accountdetails.getCurrentbalance());
							if(accountdetails.getCurrentbalance().compareTo(BigDecimal.ZERO) != 0 ) {
								assetsLedgerAccount.put(accountdetails.getAccountDetails().getAccountname(), accountdetails.getCurrentbalance());
							}
							break;
					case 2: 
							liabilities = liabilities.add(accountdetails.getCurrentbalance());
							if(accountdetails.getCurrentbalance().compareTo(BigDecimal.ZERO) != 0 ) {
								liabilitiesLedgerAccount.put(accountdetails.getAccountDetails().getAccountname(), accountdetails.getCurrentbalance());
							}	
							break;
							
					case 3:
							reserves = reserves.add(accountdetails.getCurrentbalance());
							if(accountdetails.getCurrentbalance().compareTo(BigDecimal.ZERO) != 0 ) {
								reservesLedgerAccount.put(accountdetails.getAccountDetails().getAccountname(), accountdetails.getCurrentbalance());
							}
							break;

					default:
							
					}
				}
				
				//group 1
				request.setAttribute("liabilities", liabilities);
				request.setAttribute("liabilitiesLedgeraccount", liabilitiesLedgerAccount);
				request.setAttribute("reserves", reserves);
				request.setAttribute("reservesLedgeraccount", reservesLedgerAccount);
					
				//group 2
				request.setAttribute("assets", assets);
				request.setAttribute("assetsLedgeraccount", assetsLedgerAccount);

				
				request.setAttribute("grouponetotal", liabilities);
				request.setAttribute("grouponetotalreserves", reserves);
				request.setAttribute("grouptwototal", assets);
				
				return true;
		/*
		//Group 1 
		BigDecimal capital = BigDecimal.ZERO;
		Map<String,BigDecimal> capitalLedgerAccount = new HashMap<String, BigDecimal>();
		
		BigDecimal loansLiabilities = BigDecimal.ZERO;
		Map<String,BigDecimal> loansLiabilitiesLedgerAccount = new HashMap<String, BigDecimal>();
		
		BigDecimal currentLiabilities = BigDecimal.ZERO;
		Map<String,BigDecimal> currentLiabilitiesLedgerAccount = new HashMap<String, BigDecimal>();
		
		BigDecimal reserves = BigDecimal.ZERO;
		Map<String,BigDecimal> reservesLedgerAccount = new HashMap<String, BigDecimal>();
		
		//Group 2
		BigDecimal fixedAssets = BigDecimal.ZERO;
		Map<String,BigDecimal> fixedAssetsLedgerAccount = new HashMap<String, BigDecimal>();
		
		BigDecimal investments = BigDecimal.ZERO;
		Map<String,BigDecimal> investmentsLedgerAccount = new HashMap<String, BigDecimal>();
		
		BigDecimal currentAssets = BigDecimal.ZERO;
		Map<String,BigDecimal> currentAssetsLedgerAccount = new HashMap<String, BigDecimal>();
		
		BigDecimal loansAssets = BigDecimal.ZERO;
		Map<String,BigDecimal> loansAssetsLedgerAccount = new HashMap<String, BigDecimal>();
		
		BigDecimal miscellaneousExpenses = BigDecimal.ZERO;
		Map<String,BigDecimal> miscellaneousExpensesLedgerAccount = new HashMap<String, BigDecimal>();
		
		
		List<Accountdetailsbalance> accountDetailsBalance = new ArrayList<Accountdetailsbalance>();
		accountDetailsBalance = new AccountDAO().getAccountdetailsbalance(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		
		for (Accountdetailsbalance accountdetails : accountDetailsBalance) {
			int groupId = accountdetails.getAccountDetails().getAccountGroupMaster().getAccountgroupid();

			switch(groupId){
			case 1: 
					capital = capital.add(accountdetails.getCurrentbalance());
					capitalLedgerAccount.put(accountdetails.getAccountDetails().getAccountname(), accountdetails.getCurrentbalance());
					break;
			case 2: 
					currentAssets = currentAssets.add(accountdetails.getCurrentbalance());
					currentAssetsLedgerAccount.put(accountdetails.getAccountDetails().getAccountname(), accountdetails.getCurrentbalance());
					break;
					
			case 3:
					currentLiabilities = currentLiabilities.add(accountdetails.getCurrentbalance());
					currentLiabilitiesLedgerAccount.put(accountdetails.getAccountDetails().getAccountname(), accountdetails.getCurrentbalance());
					break;
			
			case 6:
					fixedAssets = fixedAssets.add(accountdetails.getCurrentbalance());
					fixedAssetsLedgerAccount.put(accountdetails.getAccountDetails().getAccountname(), accountdetails.getCurrentbalance());
					break;
					
			case 9:
					investments = investments.add(accountdetails.getCurrentbalance());
					investmentsLedgerAccount.put(accountdetails.getAccountDetails().getAccountname(), accountdetails.getCurrentbalance());
				  	break;
				  	
			case 10:
					loansAssets = loansAssets.add(accountdetails.getCurrentbalance());
					loansAssetsLedgerAccount.put(accountdetails.getAccountDetails().getAccountname(), accountdetails.getCurrentbalance());
					break;
					
			case 11:
					loansLiabilities = loansLiabilities.add(accountdetails.getCurrentbalance());
					loansLiabilitiesLedgerAccount.put(accountdetails.getAccountDetails().getAccountname(), accountdetails.getCurrentbalance());
					break;
					
			case 12:
					miscellaneousExpenses = miscellaneousExpenses.add(accountdetails.getCurrentbalance());
					miscellaneousExpensesLedgerAccount.put(accountdetails.getAccountDetails().getAccountname(), accountdetails.getCurrentbalance());
					break;
					
			case 13:
					reserves = reserves.add(accountdetails.getCurrentbalance());
					reservesLedgerAccount.put(accountdetails.getAccountDetails().getAccountname(), accountdetails.getCurrentbalance());
					break;
					
			default:
					
			}
		}
		
		//group 1
		request.setAttribute("capital", capital);
		request.setAttribute("capitalledgeraccount", capitalLedgerAccount);
		request.setAttribute("loansliabilities", loansLiabilities);
		request.setAttribute("loansliabilitiesledgeraccount", loansLiabilitiesLedgerAccount);
		request.setAttribute("currentliabilities", currentLiabilities);
		request.setAttribute("currentliabilitiesledgeraccount", currentLiabilitiesLedgerAccount);
		request.setAttribute("reserves", reserves);
		request.setAttribute("reservesledgeraccount", reservesLedgerAccount);
		
		//group 2
		request.setAttribute("fixedassets", fixedAssets);
		request.setAttribute("fixedassetsledgeraccount", fixedAssetsLedgerAccount);
		request.setAttribute("investments", investments);
		request.setAttribute("investmentsledgeraccount", investmentsLedgerAccount);
		request.setAttribute("currentassets", currentAssets);
		request.setAttribute("currentassetsledgeraccount", currentAssetsLedgerAccount);
		request.setAttribute("loansassets", loansAssets);
		request.setAttribute("loansassetsledgeraccount", loansAssetsLedgerAccount);
		request.setAttribute("miscellaneousexpenses", miscellaneousExpenses);
		request.setAttribute("miscellaneousexpensesledgeraccount", miscellaneousExpensesLedgerAccount);
		
		BigDecimal groupOneTotal = capital.add(loansLiabilities).add(currentLiabilities).add(reserves);
		request.setAttribute("grouponetotal", groupOneTotal);
				
		BigDecimal groupTwoTotal = fixedAssets.add(investments).add(currentAssets).add(loansAssets).add(miscellaneousExpenses);
		request.setAttribute("grouptwototal", groupTwoTotal);
		
		BigDecimal diff = groupOneTotal.subtract(groupTwoTotal);
		
		if(diff.compareTo(BigDecimal.ZERO) > 0){
			request.setAttribute("grouptwototallabel", "TOTAL");
			request.setAttribute("grouptwosemitotal", groupTwoTotal);
			request.setAttribute("grouptwototal", groupTwoTotal.add(diff));
			request.setAttribute("grouptwodifferencelabel", "DIFFERENCE");
			request.setAttribute("grouptwodifferenceamount", diff);
		}else if(diff.compareTo(BigDecimal.ZERO) < 0){
			request.setAttribute("grouponetotallabel", "TOTAL");
			request.setAttribute("grouponesemitotal", groupOneTotal);
			request.setAttribute("grouponetotal", groupOneTotal.add(diff.abs()));
			request.setAttribute("differencelabel", "DIFFERENCE");
			request.setAttribute("differenceamount", diff.abs());
			
		}
		
		return true; */
	}


	public boolean viewVouchers(int voucherType) {
		
		List<VoucherEntrytransactions> voucherTransactions = new ArrayList<VoucherEntrytransactions>();
		
		if(httpSession.getAttribute(BRANCHID)!=null) {

		String twoAccounts = null;
		
		Map<VoucherEntrytransactions,String> voucherMap = new LinkedHashMap<VoucherEntrytransactions, String>();
		int financialYearId = new AccountDAO().getCurrentFinancialYear(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())).getFinancialid();
		voucherTransactions = new AccountDAO().getVoucherEntryTransactions(financialYearId, Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()), voucherType);
		
		for (VoucherEntrytransactions voucherEntry : voucherTransactions) {
			twoAccounts = new AccountDAO().getAccountName(voucherEntry.getDraccountid())+"--"+new AccountDAO().getAccountName(voucherEntry.getCraccountid());
			voucherMap.put(voucherEntry, twoAccounts);
		}
		
		request.setAttribute("vouchertransactions", voucherMap);
		
		return true;
		
		}
		return false;
	}


	public boolean trialBalance() {
		
		List<Accountdetailsbalance> accountDetailsBalance = new ArrayList<Accountdetailsbalance>();
		
		String fromDate = DataUtil.dateFromatConversionSlash(DataUtil.emptyString(request.getParameter("fromdate")));
		String toDate = DataUtil.dateFromatConversionSlash(DataUtil.emptyString(request.getParameter("todate")));
		
		if(httpSession.getAttribute(BRANCHID)!=null) {
			
			int branchId = Integer.parseInt(httpSession.getAttribute(BRANCHID).toString());

				List<Accountdetails> accountsDetails = new ArrayList<Accountdetails>();
				accountsDetails = new AccountDAO().getAccountdetails(branchId);
				
				Map<Accountdetails,BigDecimal> accountBalanceMap = new LinkedHashMap<Accountdetails,BigDecimal>();
				
				BigDecimal debitAllAcc = BigDecimal.ZERO;
				BigDecimal creditAllAcc = BigDecimal.ZERO;
				BigDecimal totalBalanceAllAccDiff = BigDecimal.ZERO;
				
				for (Accountdetails accountDetails : accountsDetails) {
					
					List<VoucherEntrytransactions> voucherTransactions = new AccountDAO().getVoucherEntryTransactionsBetweenDates(fromDate, toDate, accountDetails.getAccountdetailsid(), Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
					
					BigDecimal totalAmount = getTotalBalance(accountDetails,voucherTransactions);
					
					if(totalAmount.compareTo(BigDecimal.ZERO) != 0 ) {
						
					accountBalanceMap.put(accountDetails, totalAmount);
						
					if(accountDetails.getAccountGroupMaster().getAccountgroupid()==1 || accountDetails.getAccountGroupMaster().getAccountgroupid()==5) {
						
						if(totalAmount.signum() >= 0) {
							debitAllAcc = debitAllAcc.add(totalAmount);
						}else{
							creditAllAcc = creditAllAcc.add(totalAmount.negate());
						}
						
					}else if(accountDetails.getAccountGroupMaster().getAccountgroupid()==2 || accountDetails.getAccountGroupMaster().getAccountgroupid()==3 || accountDetails.getAccountGroupMaster().getAccountgroupid()==4){
						
						if(totalAmount.signum() >= 0) {
							creditAllAcc = creditAllAcc.add(totalAmount);
						}else{
							debitAllAcc = debitAllAcc.add(totalAmount.negate());
						}
					}
				}
					
				}
				httpSession.setAttribute("accountdetailsbalanceMap", accountBalanceMap);
				httpSession.setAttribute("credittotal", creditAllAcc);
				httpSession.setAttribute("debittotal", debitAllAcc);
				httpSession.setAttribute("fromdatetb", fromDate = DataUtil.dateFromatConversionSlash(fromDate));
				httpSession.setAttribute("todatetb", toDate = DataUtil.dateFromatConversionSlash(toDate));
				
				totalBalanceAllAccDiff = creditAllAcc.subtract(debitAllAcc);
				
				/*if(totalBalanceAllAccDiff.signum() == 1){
					request.setAttribute("differencetotal", "Difference in Balances");
					request.setAttribute("debitdifference", totalBalanceAllAccDiff.abs());
					request.setAttribute("debittotal", debitAllAcc.add(totalBalanceAllAccDiff.abs()));
				}else if(totalBalanceAllAccDiff.signum() == -1){
					request.setAttribute("differencetotal", "Difference in Balances");
					request.setAttribute("creditdifference", totalBalanceAllAccDiff.abs());
					request.setAttribute("credittotal", creditAllAcc.add(totalBalanceAllAccDiff.abs()));
				}*/
				return true;
		}
		
		return false;
	}


	private BigDecimal getTotalBalance(Accountdetails accountDetails, List<VoucherEntrytransactions> voucherTransactions) {
		
		BigDecimal totalBalanceAcc = BigDecimal.ZERO;
		BigDecimal debitAcc = BigDecimal.ZERO;
		BigDecimal creditAcc = BigDecimal.ZERO;
		
		for (VoucherEntrytransactions voucherTransaction : voucherTransactions) {
			
			int accountId = accountDetails.getAccountdetailsid();
			
			int drAccountid = voucherTransaction.getDraccountid();
			int crAccountid = voucherTransaction.getCraccountid();
			
			if(drAccountid == accountId) {
				debitAcc = debitAcc.add(voucherTransaction.getDramount());
			}else if(crAccountid == accountId) {
				creditAcc = creditAcc.add(voucherTransaction.getCramount());
			}
		}
	
	int groupId = accountDetails.getAccountGroupMaster().getAccountgroupid();
		
	if(groupId==1 || groupId==5) {
		totalBalanceAcc = debitAcc.subtract(creditAcc);
	}else{
		totalBalanceAcc = creditAcc.subtract(debitAcc);
	}
	
	return totalBalanceAcc;
	
	}


	public boolean cancelVoucher() {
		
		String[] receiptIds = request.getParameterValues("transactionids");
		int voucherType = DataUtil.parseInt(request.getParameter("voucherType"));
		Date now = new Date();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String todaysDate = formatter.format(now);
        boolean result = false;
		
		
		if (receiptIds != null || voucherType!=0) {
			
			for (String id : receiptIds) {
				VoucherEntrytransactions voucherTransaction = new AccountDAO().getVoucherDetails(id);
				
				if(voucherType==1) {
					
					String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+voucherTransaction.getDramount()+" where accountdetailsid="+voucherTransaction.getDraccountid();
					String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+voucherTransaction.getCramount()+" where accountdetailsid="+voucherTransaction.getCraccountid();

					String cancelVoucher = "update VoucherEntrytransactions set cancelvoucher='yes', vouchercancellationdate='"+todaysDate+"' where transactionsid="+id;
					
					result = new AccountDAO().updateAccountsWithVoucherCancel(updateDrAccount, updateCrAccount, cancelVoucher);
				}else if(voucherType==2) {

					String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+voucherTransaction.getDramount()+" where accountdetailsid="+voucherTransaction.getDraccountid();
					String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+voucherTransaction.getCramount()+" where accountdetailsid="+voucherTransaction.getCraccountid();
					
					String cancelVoucher = "update VoucherEntrytransactions set cancelvoucher='yes', vouchercancellationdate='"+todaysDate+"' where transactionsid="+id;
					
					result = new AccountDAO().updateAccountsWithVoucherCancel(updateDrAccount, updateCrAccount, cancelVoucher);
				}else if(voucherType==3) {

					String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+voucherTransaction.getDramount()+" where accountdetailsid="+voucherTransaction.getDraccountid();
					String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+voucherTransaction.getCramount()+" where accountdetailsid="+voucherTransaction.getCraccountid();
					
					String cancelVoucher = "update VoucherEntrytransactions set cancelvoucher='yes', vouchercancellationdate='"+todaysDate+"' where transactionsid="+id;
					
					result = new AccountDAO().updateAccountsWithVoucherCancel(updateDrAccount, updateCrAccount, cancelVoucher);
					
				}else if(voucherType==4) {
					
					// Dr
					Accountdetails accountDetailsDr = new AccountDAO().getAccountDetails(voucherTransaction.getDraccountid());
					String updateDrAccount= null;
					if(accountDetailsDr.getAccountGroupMaster().getAccountgroupid()==1 || accountDetailsDr.getAccountGroupMaster().getAccountgroupid()==5) {
						updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+voucherTransaction.getDramount()+" where accountdetailsid="+voucherTransaction.getDraccountid();
					}else {
						updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+voucherTransaction.getDramount()+" where accountdetailsid="+voucherTransaction.getDraccountid();
					}
					
					//Cr
					
					Accountdetails accountDetailsCr = new AccountDAO().getAccountDetails(voucherTransaction.getCraccountid());
					String updateCrAccount= null;
					
					if(accountDetailsCr.getAccountGroupMaster().getAccountgroupid()==2 || accountDetailsCr.getAccountGroupMaster().getAccountgroupid()==3 || accountDetailsCr.getAccountGroupMaster().getAccountgroupid()==4) {
						updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+voucherTransaction.getCramount()+" where accountdetailsid="+voucherTransaction.getCraccountid();
					}else {
						updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+voucherTransaction.getCramount()+" where accountdetailsid="+voucherTransaction.getCraccountid();
					}
					
					String cancelVoucher = "update VoucherEntrytransactions set cancelvoucher='yes', vouchercancellationdate='"+todaysDate+"' where transactionsid="+id;
					
					result = new AccountDAO().updateAccountsWithVoucherCancel(updateDrAccount, updateCrAccount, cancelVoucher);
					 
				}

				
				
			}
			
		}
		
		return result;
	}


	public boolean viewCancelledVouchers() {
		
		List<VoucherEntrytransactions> cancelledVoucherTransactions = new ArrayList<VoucherEntrytransactions>();
		
		if(httpSession.getAttribute(BRANCHID)!=null) {

		String twoAccounts = null;
		
		Map<VoucherEntrytransactions,String> voucherMap = new LinkedHashMap<VoucherEntrytransactions, String>();
		int financialYearId = new AccountDAO().getCurrentFinancialYear(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())).getFinancialid();
		cancelledVoucherTransactions = new AccountDAO().getCancelledVoucherEntryTransactions(financialYearId, Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		
		for (VoucherEntrytransactions voucherEntry : cancelledVoucherTransactions) {
			twoAccounts = new AccountDAO().getAccountName(voucherEntry.getDraccountid())+"--"+new AccountDAO().getAccountName(voucherEntry.getCraccountid());
			voucherMap.put(voucherEntry, twoAccounts);
		}
		
		request.setAttribute("cancelledvouchertransactions", voucherMap);
		
		return true;
		
		}
		return false;
	}


	public void getSSGroupNames() throws IOException {
		
		List<Accountssgroupmaster> accountSSGroupMaster = new ArrayList<Accountssgroupmaster>();
		
		if(httpSession.getAttribute(BRANCHID)!=null){
			PrintWriter out = response.getWriter(); 
			response.setContentType("text/xml");
		    response.setHeader("Cache-Control", "no-cache");
		        
			if(!"New Group".equalsIgnoreCase(request.getParameter("subgroupname").toString())) {
			int accountSubGroupMasterId = DataUtil.parseInt(request.getParameter("subgroupname"));
			accountSSGroupMaster = new AccountDAO().getListAccountSSGroupMaster(accountSubGroupMasterId,Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			request.setAttribute("accountssgroupmaster", accountSSGroupMaster);
		        try {
		        	
		        	if(!accountSSGroupMaster.isEmpty()){
		        		String buffer = "<select name='ssgroupname' style='width: 240px' id='ssgname' onchange='ssGroupSelect()'>";
		        		buffer = buffer+"<option></option>";
			        	for(int i =0; i<accountSSGroupMaster.size();i++){
			        		buffer = buffer +  "<option value="+accountSSGroupMaster.get(i).getSsgroupmasterid()+">"+accountSSGroupMaster.get(i).getSsgroupname()+"</option>";
			        	}
			        	buffer = buffer+"<option value='New Sub-Group'>New Sub-Group</option></select>";
			        	response.getWriter().println(buffer);
		        	}else{
		        		String buffer = "<select name='ssgroupname' style='width: 240px' id='ssgname' onchange='ssGroupSelect()'>";
		        		buffer = buffer+"<option></option>";
			        	buffer = buffer+"<option value='New Sub-Group'>New Sub-Group</option>";
			        	buffer = buffer+"</select>";
			        	response.getWriter().println(buffer);
		        	}
		        	
		        } catch (Exception e) {
		            out.write("<subgroup>0</subgroup>");
		        } finally {
		            out.flush();
		            out.close();
		        }
		}else {
			try {
	        		String buffer = "<select name='ssgroupname' style='width: 240px' id='ssgname' onchange='ssGroupSelect()'>";
	        		buffer = buffer+"<option></option>";
		        	buffer = buffer+"<option value='New Sub-Group'>New Sub-Group</option>";
		        	buffer = buffer+"</select>";
		        	response.getWriter().println(buffer);
	        	
	        } catch (Exception e) {
	            out.write("<subgroup>0</subgroup>");
	        } finally {
	            out.flush();
	            out.close();
	        }
		}
		}
		
		
	}


	public boolean searchJournalEntries() {
		
		List<VoucherEntrytransactions> voucherTransactions = new ArrayList<VoucherEntrytransactions>();
		String accountDetails = DataUtil.emptyString(request.getParameter("accountid"));
		String[] accountIdName = accountDetails.split(":");
		int accountId = DataUtil.parseInt(DataUtil.emptyString(accountIdName[0]));
		String fromDate = DataUtil.dateFromatConversionSlash(DataUtil.emptyString(request.getParameter("fromdate")));
		String toDate = DataUtil.dateFromatConversionSlash(DataUtil.emptyString(request.getParameter("todate")));
		if(httpSession.getAttribute(BRANCHID)!=null) {

		String twoAccounts = null;
		
		Map<VoucherEntrytransactions,String> voucherMap = new LinkedHashMap<VoucherEntrytransactions, String>();
		int financialYearId = new AccountDAO().getCurrentFinancialYear(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())).getFinancialid();
		voucherTransactions = new AccountDAO().getVoucherEntryTransactionsBetweenDates(fromDate, toDate, accountId, Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		
		for (VoucherEntrytransactions voucherEntry : voucherTransactions) {
			
			if(voucherEntry.getDraccountid() != accountId) {
				twoAccounts = new AccountDAO().getAccountName(voucherEntry.getDraccountid())+":Dr";
			}else if(voucherEntry.getCraccountid() != accountId) {
				twoAccounts = new AccountDAO().getAccountName(voucherEntry.getCraccountid())+":Cr";
			}
			//twoAccounts = new AccountDAO().getAccountName(voucherEntry.getDraccountid())+"--"+new AccountDAO().getAccountName(voucherEntry.getCraccountid());
			voucherMap.put(voucherEntry, twoAccounts);
		}
		
		request.setAttribute("ledgertransactions", voucherMap);
		request.setAttribute("ledgername", accountIdName[1]);
		
		request.setAttribute("accountid", accountDetails);
		request.setAttribute("fromdate", DataUtil.emptyString(request.getParameter("fromdate")));
		request.setAttribute("todate", DataUtil.emptyString(request.getParameter("todate")));
		
		return true;
		
		}
		return false;
	}


	public void getAllLedgers() {
		
	List<Accountdetails> accountDetails = new ArrayList<Accountdetails>();
	accountDetails = new AccountDAO().getLedgerAccountdetails(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
	request.setAttribute("ledgeraccountdetails", accountDetails);
	
	}


	public boolean getIncomeStatement() {
		
		String fromDate = DataUtil.dateFromatConversionDash(DataUtil.emptyString(request.getParameter("fromdate")));
		String toDate = DataUtil.dateFromatConversionDash(DataUtil.emptyString(request.getParameter("todate")));
		
		if(httpSession.getAttribute(BRANCHID)!=null) {
			
					int branchId = Integer.parseInt(httpSession.getAttribute(BRANCHID).toString());

				List<Accountdetails> accountsDetails = new ArrayList<Accountdetails>();
				accountsDetails = new AccountDAO().getAccountdetailsIncomeExpense(branchId);
				
				Map<Accountdetails,BigDecimal> accountBalanceMap = new LinkedHashMap<Accountdetails,BigDecimal>();
				
				//Group 1
				BigDecimal totalIncome = BigDecimal.ZERO;
				Map<Accountdetails,BigDecimal> incomeLedgersAccount = new HashMap<Accountdetails, BigDecimal>();
				
				
				//Group 2
				BigDecimal totalExpense = BigDecimal.ZERO;
				Map<Accountdetails,BigDecimal> expenseLedgersAccount = new HashMap<Accountdetails, BigDecimal>();
				
				for (Accountdetails accountDetails : accountsDetails) {
					
					List<VoucherEntrytransactions> voucherTransactions = new AccountDAO().getVoucherEntryTransactionsBetweenDates(fromDate, toDate, accountDetails.getAccountdetailsid(), Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
					
					if(!voucherTransactions.isEmpty()) {
					
						BigDecimal totalAmount = getTotalBalance(accountDetails,voucherTransactions);
					
						int groupId = accountDetails.getAccountGroupMaster().getAccountgroupid();
	
						switch(groupId){
						
						case 4: 
								totalIncome = totalIncome.add(totalAmount);
								incomeLedgersAccount.put(accountDetails, totalAmount);
								break;
						case 5: 
								totalExpense = totalExpense.add(totalAmount);
								expenseLedgersAccount.put(accountDetails, totalAmount);
								break;
						default:
								
						}
						
						}
					}
		//group 1
		request.setAttribute("income", totalIncome);
		request.setAttribute("incomeledgersaccount", incomeLedgersAccount);
		
		//group 2
		request.setAttribute("expenses", totalExpense);
		request.setAttribute("expensesledgersaccount", expenseLedgersAccount);
		
		request.setAttribute("incometotallabel", "TOTAL");
		request.setAttribute("expensetotallabel", "TOTAL");
		request.setAttribute("incometotal", totalIncome);
		request.setAttribute("expensetotal", totalExpense);
		
		request.setAttribute("fromdate", fromDate);
		request.setAttribute("todate", toDate);
		
		
		BigDecimal profit = totalIncome.subtract(totalExpense);
		
		if(profit.compareTo(BigDecimal.ZERO) > 0){
			request.setAttribute("profitlabel", "Net Profit");
			request.setAttribute("totalprofit", profit);
		}else if(profit.compareTo(BigDecimal.ZERO) < 0){
			request.setAttribute("losslabel", "Net Loss");
			request.setAttribute("totalloss", profit.negate());
		}
		
		
	}
		return true;
	}
	
	
	public boolean printSearchJournalEntries() {

		List<VoucherEntrytransactions> voucherTransactions = new ArrayList<VoucherEntrytransactions>();
		String accountDetails = DataUtil.emptyString(request.getParameter("accountidselected"));
		String[] accountIdName = accountDetails.split(":");
		int accountId = DataUtil.parseInt(DataUtil.emptyString(accountIdName[0]));
		String fromDate = DateUtil.dateFromatConversionSlash(DataUtil.emptyString(request.getParameter("fromdateselected")));
		String toDate = DateUtil.dateFromatConversionSlash(DataUtil.emptyString(request.getParameter("todateselected")));
		
		if (httpSession.getAttribute(BRANCHID) != null) {

			String twoAccounts = null;

			Map<VoucherEntrytransactions, String> voucherMap = new LinkedHashMap<VoucherEntrytransactions, String>();
			int financialYearId = new AccountDAO()
					.getCurrentFinancialYear(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()))
					.getFinancialid();
			voucherTransactions = new AccountDAO().getVoucherEntryTransactionsBetweenDates(fromDate, toDate, accountId,
					Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));

			for (VoucherEntrytransactions voucherEntry : voucherTransactions) {

				if (voucherEntry.getDraccountid() != accountId) {
					twoAccounts = new AccountDAO().getAccountName(voucherEntry.getDraccountid()) + ":Dr";
				} else if (voucherEntry.getCraccountid() != accountId) {
					twoAccounts = new AccountDAO().getAccountName(voucherEntry.getCraccountid()) + ":Cr";
				}
				// twoAccounts = new
				// AccountDAO().getAccountName(voucherEntry.getDraccountid())+"--"+new
				// AccountDAO().getAccountName(voucherEntry.getCraccountid());
				voucherMap.put(voucherEntry, twoAccounts);
			}

			request.setAttribute("ledgertransactions", voucherMap);
			request.setAttribute("ledgername", accountIdName[1]);
			request.setAttribute("fromdateselected", request.getParameter("fromdateselected"));
			request.setAttribute("todateselected", request.getParameter("todateselected"));

			return true;

		}
		return false;
	}
	
	
	public boolean exportTrialBalance() {

		boolean writeSucees = false;
		Map<Accountdetails,BigDecimal> accountBalanceMap = new LinkedHashMap<Accountdetails,BigDecimal>();
		
		DecimalFormat df = new DecimalFormat("###.##");
		
		accountBalanceMap = (Map<Accountdetails, BigDecimal>) httpSession.getAttribute("accountdetailsbalanceMap");
		String creditAllAcc = httpSession.getAttribute("credittotal").toString();
		String debitAllAcc = httpSession.getAttribute("debittotal").toString();
		String fromDate = (String) httpSession.getAttribute("fromdatetb");
		String toDate = (String) httpSession.getAttribute("todatetb");
		
		try {

			// Creating an excel file
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("trialbalance");
			Map<String, Object[]> data = new HashMap<String, Object[]>();
			Map<String, Object[]> headerData = new HashMap<String, Object[]>();
			headerData.put("Header",
					new Object[] { "Trial Balance"});
			Map<String, Object[]> headerData1 = new HashMap<String, Object[]>();
			headerData1.put("Header",
					new Object[] { "From Date: "+fromDate+"  To Date: "+toDate+""});
			Map<String, Object[]> headerData2 = new HashMap<String, Object[]>();
			headerData2.put("Header",
					new Object[] { "Particulars", "Debit","Credit"});
			int i = 1;
			
			for (Entry<Accountdetails, BigDecimal> accBal : accountBalanceMap.entrySet()) {
				
				String dr = "";
				String cr = "";
				
						
				if(accBal.getKey().getAccountGroupMaster().getAccountgroupid() == 1 || accBal.getKey().getAccountGroupMaster().getAccountgroupid() == 5) {
					
					if(accBal.getValue().compareTo(BigDecimal.ONE)==0 || accBal.getValue().compareTo(BigDecimal.ONE)==1) {
						dr = df.format(accBal.getValue());
						
					}else if(accBal.getValue().compareTo(BigDecimal.ONE)<1) {
						cr = df.format(accBal.getValue().negate());
					}
				}else if(accBal.getKey().getAccountGroupMaster().getAccountgroupid() == 2 || accBal.getKey().getAccountGroupMaster().getAccountgroupid() == 3 || accBal.getKey().getAccountGroupMaster().getAccountgroupid() == 4) {
					
					if(accBal.getValue().compareTo(BigDecimal.ONE)==0 || accBal.getValue().compareTo(BigDecimal.ONE)==1) {
						cr = df.format(accBal.getValue());
						
					}else if(accBal.getValue().compareTo(BigDecimal.ONE)<1) {
						dr = df.format(accBal.getValue().negate());
					}
				}
				
				data.put(Integer.toString(i),
						new Object[] { DataUtil.emptyString(accBal.getKey().getAccountname()),  dr ,
								 cr });
				i++;
			}
			
			Row headerRow = sheet.createRow(0);
			Object[] objArrHeader = headerData.get("Header");
			int cellnum1 = 1;
			for (Object obj : objArrHeader) {
				Cell cell = headerRow.createCell(cellnum1++);
				if (obj instanceof String)
					cell.setCellValue((String) obj);
			}
			
			Row headerRow1 = sheet.createRow(1);
			Object[] objArrHeader1 = headerData1.get("Header");
			int cellnum11 = 1;
			for (Object obj : objArrHeader1) {
				Cell cell = headerRow1.createCell(cellnum11++);
				if (obj instanceof String)
					cell.setCellValue((String) obj);
			}
			
			Row headerRow2 = sheet.createRow(2);
			Object[] objArrHeader2 = headerData2.get("Header");
			int cellnum12 = 0;
			for (Object obj : objArrHeader2) {
				Cell cell = headerRow2.createCell(cellnum12++);
				if (obj instanceof String)
					cell.setCellValue((String) obj);
			}
			
			Set<String> keyset = data.keySet();
			int rownum = 3;
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
				}
			}
			
			rownum++;
			
			data.clear();
			data.put(Integer.toString(1),
					new Object[] { "Total",  debitAllAcc ,
							creditAllAcc });
			
			Set<String> keyset2 = data.keySet();
			for (String key : keyset2) {
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
				}
			}
			
				FileOutputStream out = new FileOutputStream(new File(System.getProperty("java.io.tmpdir")+"/trialbalance.xlsx"));
				workbook.write(out);
				out.close();
				workbook.close();
				writeSucees = true;
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return writeSucees;
		// getFile(name, path);
	}


	public boolean downloadTrialBalance() {
		boolean result = false;
		try {

			File downloadFile = new File(System.getProperty("java.io.tmpdir")+"/trialbalance.xlsx");
	        FileInputStream inStream = new FileInputStream(downloadFile);

	        // get MIME type of the file
			String mimeType = "application/vnd.ms-excel";

			// set content attributes for the response
			response.setContentType(mimeType);
			// response.setContentLength((int) bis.length());

			// set headers for the response
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"",
					"trialbalance.xlsx");
			response.setHeader(headerKey, headerValue);

			// get output stream of the response
			OutputStream outStream = response.getOutputStream();

			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;

			// write bytes read from the input stream into the output stream
			while ((bytesRead = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}

			inStream.close();
			outStream.close();
			result = true;
		} catch (Exception e) {
			System.out.println(""+e);
		}
		return result;
	}
	
public boolean searchSingleLedgerEntries() {
		
		List<VoucherEntrytransactions> voucherTransactions = new ArrayList<VoucherEntrytransactions>();
		int accountId = DataUtil.parseInt(request.getParameter("accountid"));
		if(httpSession.getAttribute(BRANCHID)!=null) {

		String twoAccounts = null;
		
		Map<VoucherEntrytransactions,String> voucherMap = new LinkedHashMap<VoucherEntrytransactions, String>();
		Financialaccountingyear finYear = new AccountDAO().getCurrentFinancialYear(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		int financialYearId = finYear.getFinancialid();
		voucherTransactions = new AccountDAO().getVoucherEntryTransactionsBetweenDates(DateUtil.dateParseryyyymmdd(finYear.getFinancialstartdate()), DateUtil.dateParseryyyymmdd(finYear.getFinancialenddate()), accountId, Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		
		for (VoucherEntrytransactions voucherEntry : voucherTransactions) {
			
			if(voucherEntry.getDraccountid() != accountId) {
				twoAccounts = new AccountDAO().getAccountName(voucherEntry.getDraccountid())+":Dr";
			}else if(voucherEntry.getCraccountid() != accountId) {
				twoAccounts = new AccountDAO().getAccountName(voucherEntry.getCraccountid())+":Cr";
			}
			//twoAccounts = new AccountDAO().getAccountName(voucherEntry.getDraccountid())+"--"+new AccountDAO().getAccountName(voucherEntry.getCraccountid());
			voucherMap.put(voucherEntry, twoAccounts);
		}
		
		request.setAttribute("ledgertransactions", voucherMap);
		request.setAttribute("ledgername", DataUtil.emptyString(request.getParameter("ledgername")));
		
		request.setAttribute("accountid", accountId);
		request.setAttribute("fromdate", DateUtil.dateParserddMMYYYY(finYear.getFinancialstartdate()));
		request.setAttribute("todate", DateUtil.dateParserddMMYYYY(finYear.getFinancialenddate()));
		
		return true;
	
		}
		return false;
	}


public boolean getRPStatement() {
	
	String fromDate = DataUtil.dateFromatConversionDash(DataUtil.emptyString(request.getParameter("fromdate")));
	String toDate = DataUtil.dateFromatConversionDash(DataUtil.emptyString(request.getParameter("todate")));
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	Date newdate = new Date();
	String todaysDate = df.format(newdate);
	int cashLedgerid = Integer.parseInt(getLedgerAccountId("cash"+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())));
	int bankLedgerid = Integer.parseInt(getLedgerAccountId("bank"+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())));
	
	if(httpSession.getAttribute(BRANCHID)!=null) {
		
			int branchId = Integer.parseInt(httpSession.getAttribute(BRANCHID).toString());

			String incomeLedgersRP = getLedgerAccountId("incomeledgerrp"+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			String expenseLedgersRP = getLedgerAccountId("expenseledgerrp"+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			
			String[] strArray = incomeLedgersRP.split(":");
			List<Integer> arrayIncomeLedgersRP = new ArrayList<>();
			for (String s : strArray) {
	        	arrayIncomeLedgersRP.add(Integer.parseInt(s));
	        }
	        
			String[] strArrayEx = expenseLedgersRP.split(":");
			List<Integer> arrayExpenseLedgersRP = new ArrayList<>();
			for (String s : strArrayEx) {
	        	arrayExpenseLedgersRP.add(Integer.parseInt(s));
	        }
	        
			List<Accountdetails> accountsDetails = new ArrayList<Accountdetails>();
			accountsDetails = new AccountDAO().getAccountdetailsIncomeExpenseForRP(arrayIncomeLedgersRP,arrayExpenseLedgersRP,branchId);
			
			//Group 1
			BigDecimal totalIncomeCash = BigDecimal.ZERO;
			BigDecimal totalIncomeBank = BigDecimal.ZERO;
			Map<Accountdetails,BigDecimal> incomeLedgersAccount = new HashMap<Accountdetails, BigDecimal>();
			
			
			//Group 2
			BigDecimal totalExpenseCash = BigDecimal.ZERO;
			BigDecimal totalExpenseBank = BigDecimal.ZERO;
			Map<Accountdetails,BigDecimal> expenseLedgersAccount = new HashMap<Accountdetails, BigDecimal>();
			
			
			
			for (Accountdetails accountDetails : accountsDetails) {
				
				List<VoucherEntrytransactions> voucherTransactions = new AccountDAO().getVoucherEntryTransactionsBetweenDates(fromDate, toDate, accountDetails.getAccountdetailsid(), Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
				
				if(!voucherTransactions.isEmpty()) {
				
					//BigDecimal totalAmount = getTotalBalance(accountDetails,voucherTransactions);
					
					int groupId = accountDetails.getAccountGroupMaster().getAccountgroupid();

					switch(groupId){
					
					case 2: 
							//BigDecimal totalAmount = getTotalBalanceCredit(accountDetails,voucherTransactions);
							BigDecimal[] totalAmount = getTotalBalanceCashBankCredit(accountDetails,voucherTransactions,cashLedgerid,bankLedgerid);
							totalAmount[0] = totalAmount[0].abs();
							totalAmount[1] = totalAmount[1].abs();
							totalIncomeCash = totalIncomeCash.add(totalAmount[0]);
							totalIncomeBank = totalIncomeBank.add(totalAmount[1]);
							incomeLedgersAccount.put(accountDetails, totalAmount[0].add(totalAmount[1]));
							break;
					case 4: 
							BigDecimal[] totalAmountIn = getTotalBalanceCashBankCredit(accountDetails,voucherTransactions,cashLedgerid,bankLedgerid);
							totalAmountIn[0] = totalAmountIn[0].abs();
							totalAmountIn[1] = totalAmountIn[1].abs();
							totalIncomeCash = totalIncomeCash.add(totalAmountIn[0]);
							totalIncomeBank = totalIncomeBank.add(totalAmountIn[1]);
							incomeLedgersAccount.put(accountDetails, totalAmountIn[0].add(totalAmountIn[1]));
							break;
					case 5: 
							BigDecimal[] totalAmountEx = getTotalBalanceCashBankDebit(accountDetails,voucherTransactions,cashLedgerid,bankLedgerid);
							totalAmountEx[0] = totalAmountEx[0].abs();
							totalAmountEx[1] = totalAmountEx[1].abs();
							totalExpenseCash = totalExpenseCash.add(totalAmountEx[0]);
							totalExpenseBank = totalExpenseBank.add(totalAmountEx[1]);
							expenseLedgersAccount.put(accountDetails, totalAmountEx[0].add(totalAmountEx[1]));
							break;
					default:
							
					}
					
					}else {
						
						int groupId = accountDetails.getAccountGroupMaster().getAccountgroupid();

						switch(groupId){
						
						case 2: 
							incomeLedgersAccount.put(accountDetails, BigDecimal.ZERO);
							break;
						case 4: 
								incomeLedgersAccount.put(accountDetails, BigDecimal.ZERO);
								break;
						case 5: 
								expenseLedgersAccount.put(accountDetails, BigDecimal.ZERO);
								break;
						default:
								
						}
					}
				}

	
	BigDecimal profit = totalIncomeCash.add(totalIncomeBank).subtract(totalExpenseCash.add(totalExpenseBank));
	
	if(profit.compareTo(BigDecimal.ZERO) > 0){
		request.setAttribute("profitlabel", "Net Profit");
		request.setAttribute("totalprofit", profit);
	}else if(profit.compareTo(BigDecimal.ZERO) < 0){
		request.setAttribute("losslabel", "Net Loss");
		request.setAttribute("totalloss", profit.negate());
	}
	
	//Calculate halqa total income
	
			String halqaSharePaid = getLedgerAccountId("halqasharepaid"+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			BigDecimal totalPayableHalqaDuration = BigDecimal.ZERO;
			String[] halqaSharePaidstrArray = halqaSharePaid.split(":");
			List<Integer> halqaSharePaidList = new ArrayList<>();
			for (String s : halqaSharePaidstrArray) {
				halqaSharePaidList.add(Integer.parseInt(s));
		    }
		    
			List<Accountdetails> accountsDetailsHalqaSharePaidAccount = new ArrayList<Accountdetails>();
			accountsDetailsHalqaSharePaidAccount = new AccountDAO().getAccountdetailsMultiple(halqaSharePaidList,branchId);
			
			for (Accountdetails accountDetails : accountsDetailsHalqaSharePaidAccount) {
				
				List<VoucherEntrytransactions> voucherTransactions = new AccountDAO().getVoucherEntryTransactionsBetweenDates(fromDate, toDate, accountDetails.getAccountdetailsid(), Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
				
				if(!voucherTransactions.isEmpty()) {
				
					BigDecimal totalAmount = getTotalBalanceCredit(accountDetails,voucherTransactions);
					totalAmount = totalAmount.abs();
					totalPayableHalqaDuration = totalPayableHalqaDuration.add(totalAmount);
					}
				}
			 
	//End Calculating total income
	
	//Calculate halqa paid share
	
		Map<Accountdetails,BigDecimal> mapHalqaSharePaidAccount = new HashMap<Accountdetails, BigDecimal>();
		BigDecimal totalHalqaSharePaidCash = BigDecimal.ZERO;
		BigDecimal totalHalqaSharePaidBank = BigDecimal.ZERO;
	    
		for (Accountdetails accountDetails : accountsDetailsHalqaSharePaidAccount) {
			
			List<VoucherEntrytransactions> voucherTransactions = new AccountDAO().getVoucherEntryTransactionsBetweenDates(fromDate, toDate, accountDetails.getAccountdetailsid(), Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			
			if(!voucherTransactions.isEmpty()) {
			
				BigDecimal[] totalAmount = getTotalBalanceCashBankDebit(accountDetails,voucherTransactions,cashLedgerid,bankLedgerid);
				totalAmount[0] = totalAmount[0].abs();
				totalAmount[1] = totalAmount[1].abs();
				totalHalqaSharePaidCash = totalHalqaSharePaidCash.add(totalAmount[0]);
				totalHalqaSharePaidBank = totalHalqaSharePaidBank.add(totalAmount[1]);
				accountDetails.setAccountname(accountDetails.getAccountname().replace("Payable", "Share Paid").trim());
				mapHalqaSharePaidAccount.put(accountDetails, totalAmount[0].add(totalAmount[1]));
				
				}
			}
		 request.setAttribute("maphalqasharepaidaccount", mapHalqaSharePaidAccount);
		 
		//End Calculating halqa paid share
	
	//Caluclate Halqa Previous Dues
	List<Accountdetailsbalance> accountDetailsBalanceListHalqaPreviousShare = new AccountDAO().getAccountBalanceDetails(halqaSharePaidList, branchId);
	BigDecimal halqaShareDue = BigDecimal.ZERO;
	BigDecimal totalHalqaSharePaidOBCash = BigDecimal.ZERO;
	BigDecimal totalHalqaSharePaidOBBank = BigDecimal.ZERO;
	BigDecimal totalPayableHalqaTillToday = BigDecimal.ZERO;
	BigDecimal totalPayableHalqaTillTodayCash = BigDecimal.ZERO;
	BigDecimal totalPayableHalqaTillTodayBank = BigDecimal.ZERO;
    
	for (Accountdetails accountDetails : accountsDetailsHalqaSharePaidAccount) {
		
		List<VoucherEntrytransactions> voucherTransactions = new AccountDAO().getVoucherEntryTransactionsBetweenDates(fromDate, todaysDate, accountDetails.getAccountdetailsid(), Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		
		if(!voucherTransactions.isEmpty()) {
		
			BigDecimal[] totalAmount = getTotalBalanceCashBankDebit(accountDetails,voucherTransactions,cashLedgerid,bankLedgerid);
			totalAmount[0] = totalAmount[0].abs();
			totalAmount[1] = totalAmount[1].abs();
			totalHalqaSharePaidOBCash = totalHalqaSharePaidOBCash.add(totalAmount[0]);
			totalHalqaSharePaidOBBank = totalHalqaSharePaidOBBank.add(totalAmount[1]);
			}
		}
	 
	
	for (Accountdetails accountDetails : accountsDetailsHalqaSharePaidAccount) {
		
		List<VoucherEntrytransactions> voucherTransactions = new AccountDAO().getVoucherEntryTransactionsBetweenDates(fromDate, todaysDate, accountDetails.getAccountdetailsid(), Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		
		if(!voucherTransactions.isEmpty()) {
		
			BigDecimal[] totalAmount = getTotalBalanceCashBankCredit(accountDetails,voucherTransactions,cashLedgerid,bankLedgerid);
			totalAmount[0] = totalAmount[0].abs();
			totalAmount[1] = totalAmount[1].abs();
			totalPayableHalqaTillTodayCash = totalPayableHalqaTillTodayCash.add(totalAmount[0]);
			totalPayableHalqaTillTodayBank = totalPayableHalqaTillTodayBank.add(totalAmount[1]);
			}
		}
	
	for (Accountdetailsbalance accountdetailsbalance : accountDetailsBalanceListHalqaPreviousShare) {
		int accountId = accountdetailsbalance.getAccountDetails().getAccountGroupMaster().getAccountgroupid();
		
		if(accountId==2) {
			halqaShareDue = halqaShareDue.add(accountdetailsbalance.getCurrentbalance());
		}
		
	}
	totalPayableHalqaTillToday = totalPayableHalqaTillTodayCash.add(totalPayableHalqaTillTodayBank);
	BigDecimal halqaSharePreviousDue = halqaShareDue.add(totalHalqaSharePaidOBCash).add(totalHalqaSharePaidOBBank);
	halqaSharePreviousDue = halqaSharePreviousDue.subtract(totalPayableHalqaTillToday);
	request.setAttribute("halqasharepreviousdue", halqaSharePreviousDue);
	request.setAttribute("halqatotaldue", halqaSharePreviousDue.add(totalPayableHalqaDuration));
	//request.setAttribute("halqatotaldue", halqaShareDue);
	//End Calculating Previous Halqa Share
	
	request.setAttribute("totalpayablehalqaduration", totalPayableHalqaDuration);
	request.setAttribute("totalhalqasharepaid", totalHalqaSharePaidCash.add(totalHalqaSharePaidBank));
	request.setAttribute("halqatotalpayable", halqaSharePreviousDue.add(totalPayableHalqaDuration).subtract(totalHalqaSharePaidCash.add(totalHalqaSharePaidBank)));
	
	//****************************************************************************** CITY ****************************************************
	
	
	//Calculate City total income
	
	String citySharePaid = getLedgerAccountId("citysharepaid"+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
	BigDecimal totalPayableCityDuration = BigDecimal.ZERO;
	String[] citySharePaidstrArray = citySharePaid.split(":");
	List<Integer> citySharePaidList = new ArrayList<>();
	for (String s : citySharePaidstrArray) {
		citySharePaidList.add(Integer.parseInt(s));
    }
    
	List<Accountdetails> accountsDetailsCitySharePaidAccount = new ArrayList<Accountdetails>();
	accountsDetailsCitySharePaidAccount = new AccountDAO().getAccountdetailsMultiple(citySharePaidList,branchId);
	
	for (Accountdetails accountDetails : accountsDetailsCitySharePaidAccount) {
		
		List<VoucherEntrytransactions> voucherTransactions = new AccountDAO().getVoucherEntryTransactionsBetweenDates(fromDate, toDate, accountDetails.getAccountdetailsid(), Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		
		if(!voucherTransactions.isEmpty()) {
		
			BigDecimal totalAmount = getTotalBalanceCredit(accountDetails,voucherTransactions);
			totalAmount = totalAmount.abs();
			totalPayableCityDuration = totalPayableCityDuration.add(totalAmount);
			}
		}
	 
		//End Calculating total income
		
		//Calculate City paid share
		
		Map<Accountdetails,BigDecimal> mapCitySharePaidAccount = new HashMap<Accountdetails, BigDecimal>();
		BigDecimal totalCitySharePaidCash = BigDecimal.ZERO;
		BigDecimal totalCitySharePaidBank = BigDecimal.ZERO;
		
		for (Accountdetails accountDetails : accountsDetailsCitySharePaidAccount) {
			
			List<VoucherEntrytransactions> voucherTransactions = new AccountDAO().getVoucherEntryTransactionsBetweenDates(fromDate, toDate, accountDetails.getAccountdetailsid(), Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			
			if(!voucherTransactions.isEmpty()) {
			
				BigDecimal[] totalAmount = getTotalBalanceCashBankDebit(accountDetails,voucherTransactions,cashLedgerid,bankLedgerid);
				totalAmount[0] = totalAmount[0].abs();
				totalAmount[1] = totalAmount[1].abs();
				totalCitySharePaidCash = totalCitySharePaidCash.add(totalAmount[0]);
				totalCitySharePaidBank = totalCitySharePaidBank.add(totalAmount[1]);
				accountDetails.setAccountname(accountDetails.getAccountname().replace("Payable", "Share Paid").trim());
				mapCitySharePaidAccount.put(accountDetails, totalAmount[0].add(totalAmount[1]));
				
				}
			}
		 request.setAttribute("mapcitysharepaidaccount", mapCitySharePaidAccount);
		 
		//End Calculating City paid share
		
		//Caluclate City Previous Dues
		List<Accountdetailsbalance> accountDetailsBalanceListCityPreviousShare = new AccountDAO().getAccountBalanceDetails(citySharePaidList, branchId);
		BigDecimal cityShareDue = BigDecimal.ZERO;
		BigDecimal totalCitySharePaidOBCash = BigDecimal.ZERO;
		BigDecimal totalCitySharePaidOBBank = BigDecimal.ZERO;
		BigDecimal totalPayableCityTillToday = BigDecimal.ZERO;
		BigDecimal totalPayableCityTillTodayCash = BigDecimal.ZERO;
		BigDecimal totalPayableCityTillTodayBank = BigDecimal.ZERO;
		
		for (Accountdetails accountDetails : accountsDetailsCitySharePaidAccount) {
		
		List<VoucherEntrytransactions> voucherTransactions = new AccountDAO().getVoucherEntryTransactionsBetweenDates(fromDate, todaysDate, accountDetails.getAccountdetailsid(), Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		
		if(!voucherTransactions.isEmpty()) {
		
			BigDecimal[] totalAmount = getTotalBalanceCashBankDebit(accountDetails,voucherTransactions,cashLedgerid,bankLedgerid);
			totalAmount[0] = totalAmount[0].abs();
			totalAmount[1] = totalAmount[1].abs();
			totalCitySharePaidOBCash = totalCitySharePaidOBCash.add(totalAmount[0]);
			totalCitySharePaidOBBank = totalCitySharePaidOBBank.add(totalAmount[1]);
			}
		}
		
		
		for (Accountdetails accountDetails : accountsDetailsCitySharePaidAccount) {
		
		List<VoucherEntrytransactions> voucherTransactions = new AccountDAO().getVoucherEntryTransactionsBetweenDates(fromDate, todaysDate, accountDetails.getAccountdetailsid(), Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		
		if(!voucherTransactions.isEmpty()) {
		
			BigDecimal[] totalAmount = getTotalBalanceCashBankCredit(accountDetails,voucherTransactions,cashLedgerid,bankLedgerid);
			totalAmount[0] = totalAmount[0].abs();
			totalAmount[1] = totalAmount[1].abs();
			totalPayableCityTillTodayCash = totalPayableCityTillTodayCash.add(totalAmount[0]);
			totalPayableCityTillTodayBank = totalPayableCityTillTodayBank.add(totalAmount[1]);
			}
		}
		
		for (Accountdetailsbalance accountdetailsbalance : accountDetailsBalanceListCityPreviousShare) {
		int accountId = accountdetailsbalance.getAccountDetails().getAccountGroupMaster().getAccountgroupid();
		
		if(accountId==2) {
			cityShareDue = cityShareDue.add(accountdetailsbalance.getCurrentbalance());
		}
		
		}
		totalPayableCityTillToday = totalPayableCityTillTodayCash.add(totalPayableCityTillTodayBank);
		BigDecimal citySharePreviousDue = cityShareDue.add(totalCitySharePaidOBCash).add(totalCitySharePaidOBBank);
		citySharePreviousDue = citySharePreviousDue.subtract(totalPayableCityTillToday);
		request.setAttribute("citysharepreviousdue", citySharePreviousDue);
		request.setAttribute("citytotaldue", citySharePreviousDue.add(totalPayableCityDuration));
		//request.setAttribute("citytotaldue", cityShareDue);
		//End Calculating Previous City Share

//********************************************************************* CITY **********************************************************************//
	 
	// Calculate Opening Balances
		
		BigDecimal openingBalanceCash = BigDecimal.ZERO;
		BigDecimal openingBalanceBank = BigDecimal.ZERO;
		BigDecimal closingBalanceCash = BigDecimal.ZERO;
		BigDecimal closingBalanceBank = BigDecimal.ZERO;
		BigDecimal totalCrCash = BigDecimal.ZERO;
		BigDecimal totalDrCash = BigDecimal.ZERO;
		
		BigDecimal totalCrBank = BigDecimal.ZERO;
		BigDecimal totalDrBank = BigDecimal.ZERO;
		
		BigDecimal totalCrCashContra = BigDecimal.ZERO;
		BigDecimal totalDrCashContra = BigDecimal.ZERO;
		
		BigDecimal totalCrBankContra = BigDecimal.ZERO;
		BigDecimal totalDrBankContra = BigDecimal.ZERO;
		
		
		
		
		List<VoucherEntrytransactions> voucherTransactionsCash = new AccountDAO().getVoucherEntryTransactionsBetweenDates(fromDate, todaysDate, cashLedgerid, Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		
		for (VoucherEntrytransactions voucherEntrytransactions : voucherTransactionsCash) {
			int drAccount = voucherEntrytransactions.getDraccountid();
			int crAccount = voucherEntrytransactions.getCraccountid();
			
			if(drAccount == cashLedgerid) {
				totalDrCash = totalDrCash.add(voucherEntrytransactions.getDramount());
			}else if(crAccount == cashLedgerid) {
				totalCrCash = totalCrCash.add(voucherEntrytransactions.getCramount());
			}
		}
				
		List<VoucherEntrytransactions> voucherTransactionsBank = new AccountDAO().getVoucherEntryTransactionsBetweenDates(fromDate, todaysDate, bankLedgerid, Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		
		for (VoucherEntrytransactions voucherEntrytransactions : voucherTransactionsBank) {
			int drAccount = voucherEntrytransactions.getDraccountid();
			int crAccount = voucherEntrytransactions.getCraccountid();
			
			if(drAccount == bankLedgerid) {
				totalDrBank = totalDrBank.add(voucherEntrytransactions.getDramount());
			}else if(crAccount == bankLedgerid) {
				totalCrBank = totalCrBank.add(voucherEntrytransactions.getCramount());
			}
		}
		
		
		
		List<VoucherEntrytransactions> voucherTransactionsCashContra = new AccountDAO().getVoucherEntryTransactionsBetweenDates(fromDate, toDate, cashLedgerid, Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		
		for (VoucherEntrytransactions voucherEntrytransactions : voucherTransactionsCashContra) {
			int drAccount = voucherEntrytransactions.getDraccountid();
			int crAccount = voucherEntrytransactions.getCraccountid();
			
			if(drAccount == cashLedgerid && crAccount == bankLedgerid) {
				totalDrCashContra = totalDrCashContra.add(voucherEntrytransactions.getDramount());
				totalCrBankContra = totalCrBankContra.add(voucherEntrytransactions.getDramount());
			}else if(crAccount == cashLedgerid && drAccount == bankLedgerid) {
				totalCrCashContra = totalCrCashContra.add(voucherEntrytransactions.getCramount());
				totalDrBankContra = totalDrBankContra.add(voucherEntrytransactions.getCramount());
			}
		}
		
		List<Integer> accountids = new ArrayList<Integer>();
		accountids.add(cashLedgerid);
		accountids.add(bankLedgerid);
		List<Accountdetailsbalance> accountDetailsBalanceList = new AccountDAO().getAccountBalanceDetails(accountids, branchId);
		BigDecimal cashBalance = BigDecimal.ZERO;
		BigDecimal bankBalance = BigDecimal.ZERO;
		
		for (Accountdetailsbalance accountdetailsbalance : accountDetailsBalanceList) {
			int accountId = accountdetailsbalance.getAccountDetails().getAccountdetailsid();
			
			if(accountId==cashLedgerid) {
				cashBalance = accountdetailsbalance.getCurrentbalance();
			}else if (accountId==bankLedgerid) {
				bankBalance = accountdetailsbalance.getCurrentbalance();
			}
			
		}
		
		cashBalance = cashBalance.add(totalCrCash);
		openingBalanceCash = cashBalance.subtract(totalDrCash);
		
		bankBalance = bankBalance.add(totalCrBank);
		openingBalanceBank = bankBalance.subtract(totalDrBank);

		System.out.println("Total Cr Cash Contra "+totalCrCashContra);
		System.out.println("Total Dr Cash Contra "+totalDrCashContra);
		System.out.println("Total Cr Bank Contra "+totalCrBankContra);
		System.out.println("Total Dr Bank Contra "+totalDrBankContra);
		
		BigDecimal closingDrCrCash = totalIncomeCash.subtract(totalExpenseCash.add(totalHalqaSharePaidCash)).subtract(totalCrCashContra);
		//BigDecimal closingDrCrCash = totalIncomeCash.subtract(totalHalqaSharePaidCash).subtract(totalCrCashContra);
		closingBalanceCash = openingBalanceCash.add(closingDrCrCash).add(totalDrCashContra);
		
		BigDecimal closingDrCrBank = totalIncomeBank.subtract(totalExpenseBank.add(totalHalqaSharePaidBank)).subtract(totalCrBankContra);
		//BigDecimal closingDrCrBank = totalIncomeBank.subtract(totalHalqaSharePaidBank).subtract(totalCrBankContra);
		closingBalanceBank = openingBalanceBank.add(closingDrCrBank).add(totalDrBankContra);
		
		BigDecimal grandReceiptTotal = totalIncomeCash.add(totalIncomeBank).add(openingBalanceCash).add(openingBalanceBank);
		BigDecimal grandPaymentTotal = totalExpenseCash.add(totalExpenseBank).add(totalHalqaSharePaidCash).add(totalHalqaSharePaidBank).add(closingBalanceCash).add(closingBalanceBank);
		
		request.setAttribute("grandreceipttotal", grandReceiptTotal);
		request.setAttribute("grandpaymenttotal", grandPaymentTotal);
		request.setAttribute("openingbalance", openingBalanceCash);
		request.setAttribute("closingbalance", closingBalanceCash);
		request.setAttribute("openingbalancebank", openingBalanceBank);
		request.setAttribute("closingbalancebank", closingBalanceBank);
		// End calculating Opening Balances
		
		
		//Club Expenses
		
		String[] expClub = getLedgerAccountId("clubexpenses"+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())).split(":");
		Map<Accountdetails,BigDecimal> expenseLedgers = new HashMap<Accountdetails, BigDecimal>();
		Map<Accountdetails,BigDecimal> expenseLedgersAccountClub = new HashMap<Accountdetails, BigDecimal>();
		if (!expClub[0].isEmpty()) {
			
				String[] expClubLedger = getLedgerAccountId("clubexpenseledger"+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())).split(":");
				List<Integer> expClubLedgerList = new ArrayList<Integer>();
				
				for (String exCl : expClubLedger) {
					expClubLedgerList.add(Integer.parseInt(exCl));
				}
				
				List<Accountdetails> expLedgerClub = new ArrayList<Accountdetails>();
				expLedgerClub = new AccountDAO().getAccountdetailsMultiple(expClubLedgerList,branchId);
				
				for (Accountdetails accountDetails : expLedgerClub) {
					
					List<VoucherEntrytransactions> voucherTransactions = new AccountDAO().getVoucherEntryTransactionsBetweenDates(fromDate, toDate, accountDetails.getAccountdetailsid(), Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
					
					if(!voucherTransactions.isEmpty()) {
					
								BigDecimal[] totalAmountEx = getTotalBalanceCashBankDebit(accountDetails,voucherTransactions,cashLedgerid,bankLedgerid);
								totalAmountEx[0] = totalAmountEx[0].abs();
								totalAmountEx[1] = totalAmountEx[1].abs();
								totalExpenseCash = totalExpenseCash.add(totalAmountEx[0]);
								totalExpenseBank = totalExpenseBank.add(totalAmountEx[1]);
								expenseLedgers.put(accountDetails, totalAmountEx[0].add(totalAmountEx[1]));
						}else {
							
							    expenseLedgers.put(accountDetails, BigDecimal.ZERO);
									
						}
					}
				
				Map<Accountdetails,BigDecimal> expenseLedgersAllAccountClub = new HashMap<Accountdetails, BigDecimal>();
				List<Integer> expClubList = new ArrayList<Integer>();
				
				String[] expClubAllAcc = getLedgerAccountId("clubexpensesallaccounts"+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())).split(":");
				for (String exCl : expClubAllAcc) {
					expClubList.add(Integer.parseInt(exCl));
				}
				
				List<Accountdetails> accountsDetailsClub = new ArrayList<Accountdetails>();
				accountsDetailsClub = new AccountDAO().getAccountdetailsMultiple(expClubList,branchId);
				
				for (Accountdetails accountDetails : accountsDetailsClub) {
					
					List<VoucherEntrytransactions> voucherTransactions = new AccountDAO().getVoucherEntryTransactionsBetweenDates(fromDate, toDate, accountDetails.getAccountdetailsid(), Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
					
					if(!voucherTransactions.isEmpty()) {
								BigDecimal totalAmountEx = getTotalBalanceDebit(accountDetails,voucherTransactions);
								totalAmountEx = totalAmountEx.abs();
								expenseLedgersAllAccountClub.put(accountDetails, totalAmountEx);
						}else {
							expenseLedgersAllAccountClub.put(accountDetails, BigDecimal.ZERO);
							}
					}
				
				 for (String exCl : expClub) {
					 Accountdetails accountName = new Accountdetails();
					 BigDecimal totalAmountClub = BigDecimal.ZERO;
					 
					 accountName.setAccountname(exCl);
					 String[] expClubAccounts = getLedgerAccountId(exCl+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())).split(":");
					 
					 int[] expClubAccountsIds = new int[expClubAccounts.length];
		
				        // Convert and populate the int array
				        for (int i = 0; i < expClubAccounts.length; i++) {
				        	expClubAccountsIds[i] = Integer.parseInt(expClubAccounts[i]);
				        }
		
					 
					 for (Entry<Accountdetails, BigDecimal> entry : expenseLedgersAllAccountClub.entrySet()) { 
			            
			            for (int accId : expClubAccountsIds) {
			            	int accIds = entry.getKey().getAccountdetailsid();
			                if(accIds == accId) {
			                	totalAmountClub = totalAmountClub.add(entry.getValue());
			                }
			            }
			            
				 	}
					 expenseLedgersAccountClub.put(accountName, totalAmountClub);
				 }
		}
		//End Club Expenses
		
		//group 1
		request.setAttribute("income", totalIncomeCash.add(totalIncomeBank));
		request.setAttribute("incomeledgersaccount", incomeLedgersAccount);
		
		//group 2
		request.setAttribute("expenses", totalExpenseCash.add(totalIncomeBank));
		request.setAttribute("expensesledgersaccount", expenseLedgers);
		//request.setAttribute("expensesledgersaccount", expenseLedgersAccount);
		
		request.setAttribute("incometotallabel", "Total Income");
		request.setAttribute("expensetotallabel", "Total Expense");
		request.setAttribute("incometotal", totalIncomeCash.add(totalIncomeBank));
		request.setAttribute("expensetotal", totalExpenseCash.add(totalExpenseBank).add(totalHalqaSharePaidCash).add(totalHalqaSharePaidBank));
		request.setAttribute("expenseledgersaccountclub", expenseLedgersAccountClub);
		
		
		//group 3
		request.setAttribute("totalpayablehalqaduration", totalPayableHalqaDuration);
		request.setAttribute("totalhalqasharepaid", totalHalqaSharePaidCash.add(totalHalqaSharePaidBank));
		request.setAttribute("halqatotalpayable", halqaSharePreviousDue.add(totalPayableHalqaDuration).subtract(totalHalqaSharePaidCash.add(totalHalqaSharePaidBank)));
		request.setAttribute("fromdate", DataUtil.emptyString(request.getParameter("fromdate")));
		request.setAttribute("todate", DataUtil.emptyString(request.getParameter("todate")));
		
	
}
	return true;
}

	private BigDecimal[] getTotalBalanceCashBankCredit(Accountdetails accountDetails, List<VoucherEntrytransactions> voucherTransactions, int cashLedgerid, int bankLedgerid) {
		BigDecimal[] values = new BigDecimal[2];
		
		BigDecimal totalBalanceAccCash = BigDecimal.ZERO;
		BigDecimal totalBalanceAccBank = BigDecimal.ZERO;
		BigDecimal creditAccCash = BigDecimal.ZERO;
		BigDecimal creditAccBank = BigDecimal.ZERO;

		for (VoucherEntrytransactions voucherTransaction : voucherTransactions) {
				
				int vtCrAccount = voucherTransaction.getCraccountid();
				int vtDrAccount = voucherTransaction.getDraccountid();
				int accid = accountDetails.getAccountdetailsid();
				
			 if (vtCrAccount == accid) {
				
				if(vtDrAccount == cashLedgerid) {
					creditAccCash = creditAccCash.add(voucherTransaction.getCramount());
				}else if(vtDrAccount == bankLedgerid) {
					creditAccBank = creditAccBank.add(voucherTransaction.getCramount());
				}else {
					creditAccCash = creditAccCash.add(voucherTransaction.getCramount());
				}
				
			}
		}
		
		totalBalanceAccCash = creditAccCash;
		totalBalanceAccBank = creditAccBank;

		values[0]=totalBalanceAccCash;
		values[1]=totalBalanceAccBank;
		return values;
	
}
	
	private BigDecimal[] getTotalBalanceCashBankDebit(Accountdetails accountDetails, List<VoucherEntrytransactions> voucherTransactions, int cashLedgerid, int bankLedgerid) {
		BigDecimal[] values = new BigDecimal[2];
		
		BigDecimal totalBalanceAccCash = BigDecimal.ZERO;
		BigDecimal totalBalanceAccBank = BigDecimal.ZERO;
		BigDecimal debitAccCash = BigDecimal.ZERO;
		BigDecimal debitAccBank = BigDecimal.ZERO;

		for (VoucherEntrytransactions voucherTransaction : voucherTransactions) {
				
				int vtDrAccount = voucherTransaction.getDraccountid();
				int vtCrAccount = voucherTransaction.getCraccountid();
				int accid = accountDetails.getAccountdetailsid();
				
			if ( vtDrAccount == accid) {
				
				if(vtCrAccount == cashLedgerid) {
					debitAccCash = debitAccCash.add(voucherTransaction.getDramount());
				}else if(vtCrAccount == bankLedgerid) {
					debitAccBank = debitAccBank.add(voucherTransaction.getDramount());
				}else {
					debitAccCash = debitAccCash.add(voucherTransaction.getDramount());
				}
				
			} 
		}

		totalBalanceAccCash = debitAccCash;
		totalBalanceAccBank = debitAccBank;
		
		values[0]=totalBalanceAccCash;
		values[1]=totalBalanceAccBank;
		return values;
	
}


	private BigDecimal getTotalBalanceDebit(Accountdetails accountDetails,List<VoucherEntrytransactions> voucherTransactions) {
		
			BigDecimal debitAcc = BigDecimal.ZERO;
			
			for (VoucherEntrytransactions voucherTransaction : voucherTransactions) {
				
				int vDr = voucherTransaction.getDraccountid();
				int aId = accountDetails.getAccountdetailsid();
				if(vDr == aId) {
					debitAcc = debitAcc.add(voucherTransaction.getDramount());
				}
			}
		return debitAcc;
	
	}
	
	private BigDecimal getTotalBalanceCredit(Accountdetails accountDetails,List<VoucherEntrytransactions> voucherTransactions) {
		
		BigDecimal creditAcc = BigDecimal.ZERO;
		
		for (VoucherEntrytransactions voucherTransaction : voucherTransactions) {
			
			int vCr = voucherTransaction.getCraccountid();
			int aId = accountDetails.getAccountdetailsid();
			if(vCr == aId) {
				creditAcc = creditAcc.add(voucherTransaction.getCramount());
			}
		}
	return creditAcc;

}


	private String getLedgerAccountId(String itemAccount) {
		String result = "";
	 	
	 	Properties properties = new Properties();
	    InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("Util.properties");
		
	    		try {
					properties.load(inputStream);
				} catch (IOException e) {
					e.printStackTrace();
				}
		    
	    		String ItemLedgerId = properties.getProperty(itemAccount);
	    		System.out.println("Item Ledger Name "+itemAccount);
		    if(ItemLedgerId!=null) {
		    	result = ItemLedgerId;
		    }else {
		    	String ItemLedger = properties.getProperty(itemAccount.toLowerCase());
		    	result = ItemLedger.toLowerCase();
		    }
		    
		    return result;
	}


	public boolean getIncomeStatementPrint() {
		
		String fromDate = DataUtil.dateFromatConversionDash(DataUtil.emptyString(request.getParameter("fromdate")));
		String toDate = DataUtil.dateFromatConversionDash(DataUtil.emptyString(request.getParameter("todate")));
		
		if(httpSession.getAttribute(BRANCHID)!=null) {
			
					int branchId = Integer.parseInt(httpSession.getAttribute(BRANCHID).toString());

				List<Accountdetails> accountsDetails = new ArrayList<Accountdetails>();
				accountsDetails = new AccountDAO().getAccountdetailsIncomeExpense(branchId);
				
				
				//Group 1
				BigDecimal totalIncome = BigDecimal.ZERO;
				Map<Accountdetails,BigDecimal> incomeLedgersAccount = new HashMap<Accountdetails, BigDecimal>();
				
				
				//Group 2
				BigDecimal totalExpense = BigDecimal.ZERO;
				Map<Accountdetails,BigDecimal> expenseLedgersAccount = new HashMap<Accountdetails, BigDecimal>();
				
				for (Accountdetails accountDetails : accountsDetails) {
					
					List<VoucherEntrytransactions> voucherTransactions = new AccountDAO().getVoucherEntryTransactionsBetweenDates(fromDate, toDate, accountDetails.getAccountdetailsid(), Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
					
					if(!voucherTransactions.isEmpty()) {
					
						BigDecimal totalAmount = getTotalBalance(accountDetails,voucherTransactions);
					
						int groupId = accountDetails.getAccountGroupMaster().getAccountgroupid();
	
						switch(groupId){
						
						case 4: 
								totalIncome = totalIncome.add(totalAmount);
								incomeLedgersAccount.put(accountDetails, totalAmount);
								break;
						case 5: 
								totalExpense = totalExpense.add(totalAmount);
								expenseLedgersAccount.put(accountDetails, totalAmount);
								break;
						default:
								
						}
						
						}
					}
		//group 1
		request.setAttribute("income", totalIncome);
		request.setAttribute("incomeledgersaccount", incomeLedgersAccount);
		
		//group 2
		request.setAttribute("expenses", totalExpense);
		request.setAttribute("expensesledgersaccount", expenseLedgersAccount);
		
		request.setAttribute("incometotallabel", "TOTAL");
		request.setAttribute("expensetotallabel", "TOTAL");
		request.setAttribute("incometotal", totalIncome);
		request.setAttribute("expensetotal", totalExpense);
		
		request.setAttribute("fromdate", fromDate);
		request.setAttribute("todate", toDate);
		
		
		BigDecimal profit = totalIncome.subtract(totalExpense);
		
		if(profit.compareTo(BigDecimal.ZERO) > 0){
			request.setAttribute("profitlabel", "Net Profit");
			request.setAttribute("totalprofit", profit);
		}else if(profit.compareTo(BigDecimal.ZERO) < 0){
			request.setAttribute("losslabel", "Net Loss");
			request.setAttribute("totalloss", profit.negate());
		}
		
		
	}
		return true;
	}

}
