package com.model.account.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.account.dao.AccountDAO;
import com.model.account.dto.Accountdetails;
import com.model.account.dto.Accountdetailsbalance;
import com.model.account.dto.Accountgroupmaster;
import com.model.account.dto.Accountsubgroupmaster;
import com.model.account.dto.Contratransactions;
import com.model.account.dto.Financialaccountingyear;
import com.model.account.dto.Journaltransactions;
import com.model.account.dto.Paymenttransactions;
import com.model.account.dto.Receipttransactions;
import com.util.DataUtil;
import com.util.DateUtil;

public class AccountService {
	
	 private HttpServletRequest request;
	    private HttpServletResponse response;
	    private HttpSession httpSession;
	    
	
	public AccountService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
        this.response = response;
        this.httpSession = request.getSession();
	}


	public boolean saveFinancialYear() {

		Financialaccountingyear financialaccountingyear = new Financialaccountingyear();

		financialaccountingyear.setFinancialstartdate(DateUtil.dateParserUpdateStd(request.getParameter("fromdate")));
		financialaccountingyear.setFinancialenddate(DateUtil.dateParserUpdateStd(request.getParameter("todate")));
		financialaccountingyear.setActive(DataUtil.emptyString(request.getParameter("active")));
		
		return new AccountDAO().create(financialaccountingyear);
		
	}

	public boolean getCurrentFinancialYear() {
		Financialaccountingyear financialYear = new Financialaccountingyear();
		financialYear =  new AccountDAO().getCurrentFinancialYear();
		request.setAttribute("currentfinancialaccountingyearfrom", financialYear.getFinancialstartdate());
		request.setAttribute("currentfinancialaccountingyearto", financialYear.getFinancialenddate());
		if(financialYear!=null){
			return true;
		}else return false;
	}


	public boolean createAccount() {
		
		List<Accountgroupmaster> accountGroupMaster = new ArrayList<Accountgroupmaster>();
		accountGroupMaster = new AccountDAO().getListAccountGroupMaster();
		request.setAttribute("accountgroupmaster", accountGroupMaster);
		
		List<Accountdetailsbalance> accountDetailsBalance = new ArrayList<Accountdetailsbalance>();
		accountDetailsBalance = new AccountDAO().getAccountdetailsbalance();
		request.setAttribute("accountdetailsbalance", accountDetailsBalance);
		
		if(accountGroupMaster!=null){
			return true;
		}else return false;
		
	}


	public void getSubGroupNames() throws IOException {
		
		List<Accountsubgroupmaster> accountSubGroupMaster = new ArrayList<Accountsubgroupmaster>();
		int accountGroupMasterId = Integer.parseInt(request.getParameter("groupname"));
		accountSubGroupMaster = new AccountDAO().getListAccountSubGroupMaster(accountGroupMasterId);
		request.setAttribute("accountsubgroupmaster", accountSubGroupMaster);
		PrintWriter out = response.getWriter(); 
		response.setContentType("text/xml");
	        response.setHeader("Cache-Control", "no-cache");
	        try {
	        	
	        	/*String buffer = "<select name='subgroupname' > <option value='-1'>My option</option>";
	        	buffer = buffer+"</select>";*/
	        	if(!accountSubGroupMaster.isEmpty()){
	        		String buffer = "<select name='subgroupname' style='width: 240px' id='sgname' onchange='dropdowndist()'>";
		        	for(int i =0; i<accountSubGroupMaster.size();i++){
		        		buffer = buffer +  "<option value="+accountSubGroupMaster.get(i).getAccountsubgroupmasterid()+">"+accountSubGroupMaster.get(i).getAccountsubgroupname()+"</option>";
		        	}
		        	buffer = buffer+"<option value='New Sub-Group'>New Sub-Group</option></select>";
		        	response.getWriter().println(buffer);
	        	}else{
	        		String buffer = "<select name='subgroupname' style='width: 240px' id='sgname' onchange='dropdowndist()'>";
	        		buffer = buffer+"<option></option>";
		        	buffer = buffer+"<option value='New Sub-Group'>New Sub-Group</option>";
		        	buffer = buffer+"</select>";
		        	response.getWriter().println(buffer);
	        	}
	        	
	           /*out.write("<subgroup> abc </subgroup>");*/
	        	/*out.write("<subgroup><option value='1'>Product Name 1 For Category 2</option>" +
	                    "<option value='2'>Product Name 2 For Category 2</option>" +
	                    "<option value='3'>Product Name 3 For Category 2</option></subgroup>");*/
	        	
	            System.out.println("The size of the list is "+accountSubGroupMaster.size());
	        } catch (Exception e) {
	            out.write("<subgroup>0</subgroup>");
	        } finally {
	            out.flush();
	            out.close();
	        }
		
	}

	public boolean saveAccount() {

		boolean result = false;
		String newSubGroup = DataUtil.emptyString(request.getParameter("newsubgroup"));
		String subGroupName = DataUtil.emptyString(request.getParameter("subgroupname"));
		String groupName = DataUtil.emptyString(request.getParameter("groupname"));
		String accountName = DataUtil.emptyString(request.getParameter("accountname"));
		String openingBalance = DataUtil.emptyString(request.getParameter("openingbalance"));
		
		if(!"New Sub-Group".equalsIgnoreCase(subGroupName)){
			Accountdetails accountDetails = new Accountdetails();
			accountDetails.setAccountname(accountName);
			
			if(getInt(subGroupName)!=null){
				Accountsubgroupmaster accountSubGroupMaster = new Accountsubgroupmaster();
				accountSubGroupMaster.setAccountsubgroupmasterid(getInt(subGroupName));
				accountDetails.setAccountSubGroupMaster(accountSubGroupMaster);
			}
			
			Accountgroupmaster accountGroupMaster = new Accountgroupmaster();
			accountGroupMaster.setAccountgroupid(getInt(groupName));
			accountDetails.setAccountGroupMaster(accountGroupMaster);
			boolean newAccount = new AccountDAO().saveNewAccount(accountDetails);
			
			if(newAccount){
				// Add account balance
				Financialaccountingyear financialyear = new AccountDAO().getFinancialAccountingYear();
				Accountdetailsbalance accountDetailsBalance = new Accountdetailsbalance();
				accountDetailsBalance.setAccountDetails(accountDetails);
				if(findCrDr(groupName)){
					accountDetailsBalance.setCrdr("Cr");
				}else{
					accountDetailsBalance.setCrdr("Dr");
				}
				accountDetailsBalance.setFinancialid(financialyear.getFinancialid());
				accountDetailsBalance.setOpeningbalance(new BigDecimal(openingBalance));
				accountDetailsBalance.setCurrentbalance(new BigDecimal(openingBalance));
				accountDetailsBalance.setEnteredon(new Date());
				result = new AccountDAO().saveAccountBalance(accountDetailsBalance);
				
			}
		}else if("New Sub-Group".equalsIgnoreCase(subGroupName)){

			Accountsubgroupmaster accountSubGroupMaster = new Accountsubgroupmaster();
			Accountgroupmaster accountGroup = new Accountgroupmaster();
			accountGroup.setAccountgroupid(Integer.parseInt(groupName));
			accountSubGroupMaster.setAccountGroupMaster(accountGroup);
			accountSubGroupMaster.setAccountsubgroupname(newSubGroup);
			accountSubGroupMaster = new AccountDAO().createSubGroup(accountSubGroupMaster);
			
			if(accountSubGroupMaster.getAccountsubgroupmasterid()!=null){
				Accountdetails accountDetails = new Accountdetails();
				Accountgroupmaster accountGroupMaster = new Accountgroupmaster();
				Accountsubgroupmaster accountSubGroup = new Accountsubgroupmaster();
				accountSubGroup.setAccountsubgroupmasterid(accountSubGroupMaster.getAccountsubgroupmasterid());
				accountDetails.setAccountSubGroupMaster(accountSubGroup);
				accountDetails.setAccountname(accountName);
				accountGroupMaster.setAccountgroupid(Integer.parseInt(groupName));
				accountDetails.setAccountGroupMaster(accountGroupMaster);
				accountDetails.setAccountsubgroupmasterid(accountSubGroupMaster.getAccountsubgroupmasterid());
				boolean newAccount = new AccountDAO().saveNewAccount(accountDetails);
				
				if(newAccount){
					Financialaccountingyear financialyear = new AccountDAO().getFinancialAccountingYear();
					Accountdetailsbalance accountDetailsBalance = new Accountdetailsbalance();
					accountDetailsBalance.setAccountDetails(accountDetails);
					if(findCrDr(groupName)){
						accountDetailsBalance.setCrdr("Cr");
					}else{
						accountDetailsBalance.setCrdr("Dr");
					}
					accountDetailsBalance.setFinancialid(financialyear.getFinancialid());
					accountDetailsBalance.setOpeningbalance(new BigDecimal(openingBalance));
					result = new AccountDAO().saveAccountBalance(accountDetailsBalance);
				}
			}
		}
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
		String[] groupOne = {"1","3","5","8","13","11"};
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
		accountDetailsBalance = new AccountDAO().getAccountdetailsbalanceExBC();
		request.setAttribute("accountdetailsbalanceexbc", accountDetailsBalance);
		
		List<Accountdetailsbalance> accountDetailsBalanceBankCash = new ArrayList<Accountdetailsbalance>();
		accountDetailsBalanceBankCash = new AccountDAO().getAccountdetailsbalanceBankCash();
		request.setAttribute("accountdetailsbalancecontra", accountDetailsBalanceBankCash);
		request.setAttribute("accountdetailsbalancereceipt", accountDetailsBalanceBankCash);
		request.setAttribute("accountdetailsbalancepayment", accountDetailsBalanceBankCash);
		request.setAttribute("accountdetailsbalancejournal", accountDetailsBalance);
		return true;
	}


	public boolean saveReceipt() {
		
		String draccountName = DataUtil.emptyString(request.getParameter("accountname"));
		String craccountName = DataUtil.emptyString(request.getParameter("accountnamesecond"));
		String receiptVoucher = DataUtil.emptyString(request.getParameter("receiptvoucher"));
		String drAmount = DataUtil.emptyString(request.getParameter("dramount"));
		String crAmount = DataUtil.emptyString(request.getParameter("cramountsecond"));
		String receiptDate = DataUtil.emptyString(request.getParameter("dateofreceipt"));
		String receiptNarration = DataUtil.emptyString(request.getParameter("receiptnarration"));
		
		Receipttransactions transactions = new Receipttransactions();
		
		transactions.setDraccountid(Integer.parseInt(draccountName));
		transactions.setCraccountid(Integer.parseInt(craccountName));
		transactions.setDramount(new BigDecimal(drAmount));
		transactions.setCramount(new BigDecimal(crAmount));
		transactions.setVouchertype(Integer.parseInt(receiptVoucher));
		transactions.setDate(DateUtil.dateParserUpdateStd(receiptDate));
		transactions.setNarration(receiptNarration);
		transactions.setFinancialyear(new AccountDAO().getCurrentFinancialYear().getFinancialid());
		
		if(new AccountDAO().saveReceipt(transactions)){
			List<Accountdetailsbalance> accountBalance = new ArrayList<Accountdetailsbalance>();
			BigDecimal currentBalance = BigDecimal.ZERO;
			List<Integer> accountIds = new ArrayList<Integer>();
			accountIds.add(Integer.parseInt(draccountName));
			accountIds.add(Integer.parseInt(craccountName));
			accountBalance = new AccountDAO().getAccountBalanceDetails(accountIds);
			List<BigDecimal> amounts = new ArrayList<BigDecimal>();
			amounts.add(new BigDecimal(drAmount));
			amounts.add(new BigDecimal(crAmount));
			int i = 0;
			for (Accountdetailsbalance accBalance : accountBalance) {
				currentBalance = accBalance.getCurrentbalance().add(amounts.get(i));
				new AccountDAO().updateAccountCurrentBalance(currentBalance,accBalance.getAccountDetails().getAccountdetailsid());
				i++;
			}
			return true;
		}
		
		return false;
	}


	public boolean savePayment() {
		
		String draccountNamePayment = DataUtil.emptyString(request.getParameter("accountnamepayment"));
		String craccountNamePayment = DataUtil.emptyString(request.getParameter("accountnamepaymentsecond"));
		String paymentVoucher = DataUtil.emptyString(request.getParameter("paymentvoucher"));
		String drAmountPayment = DataUtil.emptyString(request.getParameter("dramountpayment"));
		String crAmountPayment = DataUtil.emptyString(request.getParameter("cramountpaymentsecond"));
		String paymentDate = DataUtil.emptyString(request.getParameter("dateofpayment"));
		String paymentNarration = DataUtil.emptyString(request.getParameter("paymentnarration"));
		
		Paymenttransactions transactions = new Paymenttransactions();
		
		transactions.setDraccountid(Integer.parseInt(draccountNamePayment));
		transactions.setCraccountid(Integer.parseInt(craccountNamePayment));
		transactions.setDramount(new BigDecimal(drAmountPayment));
		transactions.setCramount(new BigDecimal(crAmountPayment));
		transactions.setVouchertype(Integer.parseInt(paymentVoucher));
		transactions.setDate(DateUtil.dateParserUpdateStd(paymentDate));
		transactions.setNarration(paymentNarration);
		transactions.setFinancialyear(new AccountDAO().getCurrentFinancialYear().getFinancialid());
		
		if(new AccountDAO().savePayment(transactions)){
			List<Accountdetailsbalance> accountBalance = new ArrayList<Accountdetailsbalance>();
			BigDecimal currentBalance = BigDecimal.ZERO;
			List<Integer> accountIds = new ArrayList<Integer>();
			accountIds.add(Integer.parseInt(draccountNamePayment));
			accountIds.add(Integer.parseInt(craccountNamePayment));
			accountBalance = new AccountDAO().getAccountBalanceDetails(accountIds);

				for (Accountdetailsbalance accountBalanceDetails : accountBalance) {
					
					if(accountBalanceDetails.getAccountDetails().getAccountSubGroupMaster().getAccountsubgroupmasterid() == 1 || accountBalanceDetails.getAccountDetails().getAccountSubGroupMaster().getAccountsubgroupmasterid() == 2){
						new AccountDAO().updateAccountCurrentBalance(accountBalanceDetails.getCurrentbalance().subtract(new BigDecimal(drAmountPayment)),accountBalanceDetails.getAccountDetails().getAccountdetailsid());
					}else{
						new AccountDAO().updateAccountCurrentBalance(accountBalanceDetails.getCurrentbalance().add(new BigDecimal(crAmountPayment)),accountBalanceDetails.getAccountDetails().getAccountdetailsid());
					}
				}
			return true;
		}
		
		return false;
	}


	public boolean saveContra() {
		
		String draccountNameContra = DataUtil.emptyString(request.getParameter("accountnamecontra"));
		String craccountNameContra = DataUtil.emptyString(request.getParameter("accountnamecontrasecond"));
		String contraVoucher = DataUtil.emptyString(request.getParameter("contravoucher"));
		String drAmountContra = DataUtil.emptyString(request.getParameter("dramountcontra"));
		String crAmountContra = DataUtil.emptyString(request.getParameter("cramountcontrasecond"));
		String contraDate = DataUtil.emptyString(request.getParameter("dateofcontra"));
		String contraNarration = DataUtil.emptyString(request.getParameter("contranarration"));
		
		Contratransactions transactions = new Contratransactions();
		
		transactions.setDraccountid(Integer.parseInt(draccountNameContra));
		transactions.setCraccountid(Integer.parseInt(craccountNameContra));
		transactions.setDramount(new BigDecimal(drAmountContra));
		transactions.setCramount(new BigDecimal(crAmountContra));
		transactions.setVouchertype(Integer.parseInt(contraVoucher));
		transactions.setDate(DateUtil.dateParserUpdateStd(contraDate));
		transactions.setNarration(contraNarration);
		transactions.setFinancialyear(new AccountDAO().getCurrentFinancialYear().getFinancialid());
		
		if(new AccountDAO().saveContra(transactions)){
			List<Accountdetailsbalance> accountBalance = new ArrayList<Accountdetailsbalance>();
			BigDecimal currentBalance = BigDecimal.ZERO;
			List<Integer> accountIds = new ArrayList<Integer>();
			accountIds.add(Integer.parseInt(draccountNameContra));
			accountIds.add(Integer.parseInt(craccountNameContra));
			accountBalance = new AccountDAO().getAccountBalanceDetails(accountIds);
			List<BigDecimal> amounts = new ArrayList<BigDecimal>();
			amounts.add(new BigDecimal(drAmountContra));
			amounts.add(new BigDecimal(crAmountContra));
			int i = 0;
			for (Accountdetailsbalance accBalance : accountBalance) {
				currentBalance = accBalance.getCurrentbalance().subtract(amounts.get(i));
				new AccountDAO().updateAccountCurrentBalance(currentBalance,accBalance.getAccountDetails().getAccountdetailsid());
				i++;
			}
			return true;
		}
		
		return false;
	}


	public boolean saveJournal() {
		
		String draccountNameJournal = DataUtil.emptyString(request.getParameter("accountnamejournal"));
		String craccountNameJournal = DataUtil.emptyString(request.getParameter("accountnamejournalsecond"));
		String journalVoucher = DataUtil.emptyString(request.getParameter("journalvoucher"));
		String drAmountJournal = DataUtil.emptyString(request.getParameter("dramountjournal"));
		String crAmountJournal = DataUtil.emptyString(request.getParameter("cramountjournalsecond"));
		String journalDate = DataUtil.emptyString(request.getParameter("dateofjournal"));
		String journalNarration = DataUtil.emptyString(request.getParameter("journalnarration"));
		
		Journaltransactions transactions = new Journaltransactions();
		
		transactions.setDraccountid(Integer.parseInt(draccountNameJournal));
		transactions.setCraccountid(Integer.parseInt(craccountNameJournal));
		transactions.setDramount(new BigDecimal(drAmountJournal));
		transactions.setCramount(new BigDecimal(crAmountJournal));
		transactions.setVouchertype(Integer.parseInt(journalVoucher));
		transactions.setDate(DateUtil.dateParserUpdateStd(journalDate));
		transactions.setNarration(journalNarration);
		transactions.setFinancialyear(new AccountDAO().getCurrentFinancialYear().getFinancialid());
		
		if(new AccountDAO().saveJournal(transactions)){
			List<Accountdetailsbalance> accountBalance = new ArrayList<Accountdetailsbalance>();
			BigDecimal currentBalance = BigDecimal.ZERO;
			List<Integer> accountIds = new ArrayList<Integer>();
			accountIds.add(Integer.parseInt(draccountNameJournal));
			accountIds.add(Integer.parseInt(craccountNameJournal));
			accountBalance = new AccountDAO().getAccountBalanceDetails(accountIds);
			List<BigDecimal> amounts = new ArrayList<BigDecimal>();
			amounts.add(new BigDecimal(drAmountJournal));
			amounts.add(new BigDecimal(crAmountJournal));
			int i = 0;
			for (Accountdetailsbalance accBalance : accountBalance) {
				//currentBalance = accBalance.getCurrentbalance().subtract(amounts.get(i));
				currentBalance = accBalance.getCurrentbalance().add(amounts.get(i));
				new AccountDAO().updateAccountCurrentBalance(currentBalance,accBalance.getAccountDetails().getAccountdetailsid());
				i++;
			}
			return true;
		}
		
		return false;
	}


	public boolean balanceSheet() {
		
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
		accountDetailsBalance = new AccountDAO().getAccountdetailsbalance();
		
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
		
		return true;
	}


	public boolean viewVoucherReceipt() {
		
		List<Receipttransactions> receiptTransactions = new ArrayList<Receipttransactions>();
		String twoAccounts = null;
		Map<Receipttransactions,String> receiptMap = new HashMap<Receipttransactions, String>();
		receiptTransactions = new AccountDAO().getReceiptTransactions(new AccountDAO().getCurrentFinancialYear().getFinancialid());
		
		if(receiptTransactions.isEmpty()){
			return false;
		}
		
		for (Receipttransactions receipttransactionsSingle : receiptTransactions) {
			twoAccounts = new AccountDAO().getAccountName(receipttransactionsSingle.getDraccountid())+"--"+new AccountDAO().getAccountName(receipttransactionsSingle.getCraccountid());
			receiptMap.put(receipttransactionsSingle, twoAccounts);
		}
		request.setAttribute("receipttransactions", receiptMap);
		
		
		
		return true;
	}


	public boolean viewVoucherPayment() {
		
		List<Paymenttransactions> paymentTransactions = new ArrayList<Paymenttransactions>();
		String twoAccounts = null;
		Map<Paymenttransactions,String> paymentMap = new HashMap<Paymenttransactions, String>();
		paymentTransactions = new AccountDAO().getPaymentTransactions(new AccountDAO().getCurrentFinancialYear().getFinancialid());
		
		if(paymentTransactions.isEmpty()){
			return false;
		}
		
		for (Paymenttransactions paymenttransactionsSingle : paymentTransactions) {
			twoAccounts = new AccountDAO().getAccountName(paymenttransactionsSingle.getDraccountid())+"--"+new AccountDAO().getAccountName(paymenttransactionsSingle.getCraccountid());
			paymentMap.put(paymenttransactionsSingle, twoAccounts);
		}
		request.setAttribute("paymenttransactions", paymentMap);
		return true;
	}


	public boolean viewVoucherContra() {
		
		List<Contratransactions> contraTransactions = new ArrayList<Contratransactions>();
		String twoAccounts = null;
		Map<Contratransactions,String> paymentMap = new HashMap<Contratransactions, String>();
		contraTransactions = new AccountDAO().getContraTransactions(new AccountDAO().getCurrentFinancialYear().getFinancialid());
		
		if(contraTransactions.isEmpty()){
			return false;
		}
		
		for (Contratransactions paymenttransactionsSingle : contraTransactions) {
			twoAccounts = new AccountDAO().getAccountName(paymenttransactionsSingle.getDraccountid())+"--"+new AccountDAO().getAccountName(paymenttransactionsSingle.getCraccountid());
			paymentMap.put(paymenttransactionsSingle, twoAccounts);
		}
		request.setAttribute("contratransactions", paymentMap);
		return true;
	}


	public boolean viewVoucherJournal() {

		List<Journaltransactions> journalTransactions = new ArrayList<Journaltransactions>();
		String twoAccounts = null;
		Map<Journaltransactions,String> paymentMap = new HashMap<Journaltransactions, String>();
		journalTransactions = new AccountDAO().getJournalTransactions(new AccountDAO().getCurrentFinancialYear().getFinancialid());
		
		if(journalTransactions.isEmpty()){
			return false;
		}
		
		for (Journaltransactions journaltransactionsSingle : journalTransactions) {
			twoAccounts = new AccountDAO().getAccountName(journaltransactionsSingle.getDraccountid())+"--"+new AccountDAO().getAccountName(journaltransactionsSingle.getCraccountid());
			paymentMap.put(journaltransactionsSingle, twoAccounts);
		}
		request.setAttribute("journaltransactions", paymentMap);
		return true;
		
	}


	public boolean trialBalance() {
		
		List<Accountdetailsbalance> accountDetailsBalance = new ArrayList<Accountdetailsbalance>();
		accountDetailsBalance = new AccountDAO().getAccountdetailsbalance();
		
		BigDecimal debitTotal = BigDecimal.ZERO;
		BigDecimal creditTotal = BigDecimal.ZERO;
		BigDecimal differenceAmont = BigDecimal.ZERO;
		
		if(!accountDetailsBalance.isEmpty()){
		
			for (Accountdetailsbalance accountbalance : accountDetailsBalance) {
				if(accountbalance.getCrdr().equalsIgnoreCase("Cr")){
					creditTotal = creditTotal.add(accountbalance.getCurrentbalance());
				}else if(accountbalance.getCrdr().equalsIgnoreCase("Dr")){
					
					if(accountbalance.getCurrentbalance().signum() == -1){
						accountbalance.setCrdr("Cr");
						accountbalance.setCurrentbalance(accountbalance.getCurrentbalance().abs());
						creditTotal = creditTotal.add(accountbalance.getCurrentbalance());
					}else{
						debitTotal = debitTotal.add(accountbalance.getCurrentbalance());
					}
					
				}
			}
			
		}
		
		request.setAttribute("accountdetailsbalance", accountDetailsBalance);
		request.setAttribute("credittotal", creditTotal);
		request.setAttribute("debittotal", debitTotal);
		
		differenceAmont = creditTotal.subtract(debitTotal);
		
		if(differenceAmont.signum() == -1){
			request.setAttribute("differencetotal", "Difference in opening balances");
			request.setAttribute("creditdifference", differenceAmont.abs());
			request.setAttribute("credittotal", creditTotal.add(differenceAmont.abs()));
		}else if(differenceAmont.signum() == 1){
			request.setAttribute("differencetotal", "Difference in opening balances");
			request.setAttribute("debitdifference", differenceAmont.abs());
			request.setAttribute("debittotal", debitTotal.add(differenceAmont.abs()));
		}
		
		
		return true;
	}


	public boolean cancelReceiptVoucher() {
		
		String[] receiptIds = request.getParameterValues("receiptids");
		
		if (receiptIds != null) {
			
			for (String id : receiptIds) {
				Receipttransactions receiptTransaction = new AccountDAO().getReceiptDetails(id);
				boolean updateResult = new AccountDAO().updateAccounts(receiptTransaction);
				if(updateResult){
					return new AccountDAO().cancelReceipt(id);
				}else {
					return false;
				}
				
			}
			
		}
		
		return false;
	}


	public boolean cancelPaymentVoucher() {
		
		String[] paymentIds = request.getParameterValues("paymentids");
		
		if (paymentIds != null) {
			
			for (String id : paymentIds) {
				Paymenttransactions paymentTransaction = new AccountDAO().getPaymentDetails(id);
				boolean updateResult = new AccountDAO().updateAccountsPayment(paymentTransaction);
				if(updateResult){
					return new AccountDAO().cancelReceipt(id);
				}else {
					return false;
				}
				
			}
			
		}
		
		return false;
		
	}
	
}
