package org.ideoholic.account.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.model.account.dao.AccountDAO;
import com.model.account.dto.Accountdetails;
import com.model.account.dto.Accountdetailsbalance;
import com.model.account.dto.Accountgroupmaster;
import com.model.account.dto.Accountssgroupmaster;
import com.model.account.dto.Accountsubgroupmaster;
import com.model.account.dto.Financialaccountingyear;
import com.model.account.dto.VoucherEntrytransactions;
import com.util.DataUtil;
import com.util.DateUtil;

public class AccountServiceImpl implements AccountService {
	
	public String saveFinancialYear(String branchId, String fromDate,String toDate,String active) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");

		Financialaccountingyear financialaccountingyear = new Financialaccountingyear();
		
		if(branchId!=null){
			financialaccountingyear.setFinancialstartdate(DateUtil.dateParserUpdateStd(fromDate));
			financialaccountingyear.setFinancialenddate(DateUtil.dateParserUpdateStd(toDate));
			financialaccountingyear.setActive(DataUtil.emptyString(active));
			financialaccountingyear.setBranchid(Integer.parseInt(branchId.toString()));
			boolean financialAccountYear= new AccountDAO().create(financialaccountingyear);
		}
		sb.append("result:").append("false");
		sb.append("}");
		return sb.toString();
	}

public String createAccount(String branchId) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
		
		List<Accountdetailsbalance> accountDetailsBalance = new ArrayList<Accountdetailsbalance>();
		List<Accountgroupmaster> accountGroupMaster = new ArrayList<Accountgroupmaster>();
		
		if(branchId!=null){
			
			accountGroupMaster = new AccountDAO().getListAccountGroupMaster(Integer.parseInt(branchId.toString()));
			sb.append("accountgroupmaster").append(accountGroupMaster);
			
			accountDetailsBalance = new AccountDAO().getAccountdetailsbalance(Integer.parseInt(branchId.toString()));
			sb.append("accountdetailsbalance").append(accountDetailsBalance);
		}
		
		
		if(!accountGroupMaster.isEmpty()){
		sb.append("result").append("true");
		return sb.toString();
		}
		sb.append("result").append("false");
		sb.append("}");
		return sb.toString();
		
	}

public String getCurrentFinancialYear(String banchId) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	Financialaccountingyear financialYear = new Financialaccountingyear();
	if(banchId!=null){
		financialYear =  new AccountDAO().getCurrentFinancialYear(Integer.parseInt(banchId.toString()));
	}
	
	if(financialYear!=null){
		          sb.append("currentfinancialaccountingyearfrom").append(financialYear.getFinancialstartdate());
                  sb.append("currentfinancialaccountingyearto").append(financialYear.getFinancialenddate());
	}
	sb.append("result").append("true");
	sb.append("}");
	return sb.toString();
}

public String getSubGroupNames(String branchId,int accountGroupMasterId) throws IOException {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	List<Accountsubgroupmaster> accountSubGroupMaster = new ArrayList<Accountsubgroupmaster>();
	
	if(branchId!=null){
		accountSubGroupMaster = new AccountDAO().getListAccountSubGroupMaster(accountGroupMasterId,Integer.parseInt(branchId.toString()));
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
	sb.append("}");
	
}

public String saveAccount(String branchId,String newSubGroup,String newSSGroup,String subGroupName,String ssGroupName,
		String groupName,String accountName,String accountCode) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");

	String result = "false";
	
	Accountdetails accountDetailsCheck = new AccountDAO().checkAccountDetails(accountName, accountCode);
	
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
				accountSSGroupMaster.setBranchid(Integer.parseInt(branchId.toString()));
				accountSSGroupMaster = new AccountDAO().createSSGroup(accountSSGroupMaster);
				accountDetails.setAccountSSGroupMaster(accountSSGroupMaster);
			}
			
			Accountgroupmaster accountGroupMaster = new Accountgroupmaster();
			accountGroupMaster.setAccountgroupid(getInt(groupName));
			accountDetails.setAccountGroupMaster(accountGroupMaster);
			accountDetails.setBranchid(Integer.parseInt(branchId.toString()));
			
				// Add account balance
				Financialaccountingyear financialyear = new AccountDAO().getFinancialAccountingYear(Integer.parseInt(branchId.toString()));
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
				accountDetailsBalance.setBranchid(Integer.parseInt(branchId.toString()));
				result = new AccountDAO().saveNewAccount(accountDetails, accountDetailsBalance);
				
	}else if("New Group".equalsIgnoreCase(subGroupName)){

		Accountsubgroupmaster accountSubGroupMaster = new Accountsubgroupmaster();
		Accountssgroupmaster accountSSGroupMaster = new Accountssgroupmaster();
		Accountgroupmaster accountGroup = new Accountgroupmaster();
		
		accountGroup.setAccountgroupid(Integer.parseInt(groupName));
		accountSubGroupMaster.setAccountGroupMaster(accountGroup);
		accountSubGroupMaster.setAccountsubgroupname(newSubGroup);
		accountSubGroupMaster.setBranchid(Integer.parseInt(branchId.toString()));
		accountSubGroupMaster = new AccountDAO().createSubGroup(accountSubGroupMaster);
		
		 if("New Sub-Group".equalsIgnoreCase(ssGroupName)){
				
				accountSubGroupMaster.setAccountsubgroupmasterid(accountSubGroupMaster.getAccountsubgroupmasterid());
				accountSSGroupMaster.setAccountSubGroupMaster(accountSubGroupMaster);
				accountSSGroupMaster.setSsgroupname(newSSGroup);
				accountSSGroupMaster.setSsgroupname(newSSGroup);
				accountSSGroupMaster.setBranchid(Integer.parseInt(branchId.toString()));
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
			accountDetails.setBranchid(Integer.parseInt(branchId.toString()));
			
				Financialaccountingyear financialyear = new AccountDAO().getFinancialAccountingYear(Integer.parseInt(branchId.toString()));
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
				accountDetailsBalance.setBranchid(Integer.parseInt(branchId.toString()));
				result = new AccountDAO().saveNewAccount(accountDetails, accountDetailsBalance);
	}}else {
			if(accountName.equalsIgnoreCase(accountDetailsCheck.getAccountname())) {
				result = "Error-Account Name already exists";
			}else if(accountCode.equalsIgnoreCase(accountDetailsCheck.getAccountcode())) {
				result = "Error-Account Code already exists";
			}
	}
	sb.append("createaccountalert").append(result);
	sb.append("}");
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

public String deleteAccount(String[] accountIds) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
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
		sb.append("result").append("true");
		return sb.toString();
	}
	sb.append("result").append("false");
	sb.append("}");
	return sb.toString();
}

public String createVoucher(String branchId) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	
	List<Accountdetailsbalance> accountDetailsBalance = new ArrayList<Accountdetailsbalance>();
	if(branchId!=null) {
		List<Integer> accountIds = new ArrayList<Integer>();
		accountIds.add(2);
		accountIds.add(3);
		accountIds.add(4);
	accountDetailsBalance = new AccountDAO().getAccountdetailsbalanceExBC(accountIds, Integer.parseInt(branchId.toString()));
	sb.append("accountdetailsbalanceexbc").append(accountDetailsBalance);
		accountIds.clear();
		accountIds.add(5);
	accountDetailsBalance = new AccountDAO().getAccountdetailsbalanceExBC(accountIds, Integer.parseInt(branchId.toString()));
	sb.append("accountdetailsbalanceexpacc").append(accountDetailsBalance);
	
	List<Accountdetailsbalance> accountDetailsBalanceBankCash = new ArrayList<Accountdetailsbalance>();
	accountDetailsBalanceBankCash = new AccountDAO().getAccountdetailsbalanceBankCash(Integer.parseInt(branchId.toString()));
	sb.append("accountdetailsbalancecontra").append(accountDetailsBalanceBankCash);
	sb.append("accountdetailsbalancereceipt").append(accountDetailsBalanceBankCash);
	sb.append("accountdetailsbalancepayment").append(accountDetailsBalanceBankCash);
	
	List<Accountdetailsbalance> accountDetailsJournalEntry = new ArrayList<Accountdetailsbalance>();
	accountDetailsJournalEntry = new AccountDAO().getAccountdetailsbalance(Integer.parseInt(branchId.toString()));
	sb.append("accountdetailsbalancejournal").append(accountDetailsJournalEntry);
	sb.append("result").append("true");
	return sb.toString();
	}
	sb.append("result").append("false");
	sb.append("}");
	return sb.toString();
}

public String saveReceipt(String branchId,String draccountName,String craccountName,String receiptVoucher,String drAmount,
		String crAmount,String receiptDate,String receiptNarration) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	
	VoucherEntrytransactions transactions = new VoucherEntrytransactions();
	
	transactions.setDraccountid(Integer.parseInt(draccountName));
	transactions.setCraccountid(Integer.parseInt(craccountName));
	transactions.setDramount(new BigDecimal(drAmount));
	transactions.setCramount(new BigDecimal(crAmount));
	transactions.setVouchertype(Integer.parseInt(receiptVoucher));
	transactions.setTransactiondate(DateUtil.dateParserUpdateStd(receiptDate));
	transactions.setNarration(receiptNarration);
	transactions.setCancelvoucher("no");
	transactions.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(branchId.toString())).getFinancialid());
	transactions.setBranchid(Integer.parseInt(branchId.toString()));
	
	
	BigDecimal drAmountReceipt = new BigDecimal(drAmount);
	String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+drAmountReceipt+" where accountdetailsid="+Integer.parseInt(draccountName);

	BigDecimal crAmountReceipt = new BigDecimal(crAmount);
	String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+crAmountReceipt+" where accountdetailsid="+Integer.parseInt(craccountName);
	
	boolean saveVoucherAccUpdate= new AccountDAO().saveVoucherwithAccUpdate(transactions,updateDrAccount,updateCrAccount);

	sb.append("}");
	return sb.toString();
}

public String savePayment(String branchId,String draccountNamePayment,String craccountNamePayment,String paymentVoucher,
		String drAmountPayment,String crAmountPayment,String paymentDate,String paymentNarration) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	
	VoucherEntrytransactions transactions = new VoucherEntrytransactions();
	
	transactions.setDraccountid(Integer.parseInt(draccountNamePayment));
	transactions.setCraccountid(Integer.parseInt(craccountNamePayment));
	transactions.setDramount(new BigDecimal(drAmountPayment));
	transactions.setCramount(new BigDecimal(crAmountPayment));
	transactions.setVouchertype(Integer.parseInt(paymentVoucher));
	transactions.setTransactiondate(DateUtil.dateParserUpdateStd(paymentDate));
	transactions.setNarration(paymentNarration);
	transactions.setCancelvoucher("no");
	transactions.setBranchid(Integer.parseInt(branchId.toString()));
	transactions.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(branchId.toString())).getFinancialid());
	
	BigDecimal drAmount = new BigDecimal(drAmountPayment);
	String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+drAmount+" where accountdetailsid="+Integer.parseInt(draccountNamePayment);

	BigDecimal crAmount = new BigDecimal(crAmountPayment);
	String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+crAmount+" where accountdetailsid="+Integer.parseInt(craccountNamePayment);
	
	boolean saveVoucherAccUpdate= new AccountDAO().saveVoucherwithAccUpdate(transactions,updateDrAccount,updateCrAccount);
	sb.append("}");
	return sb.toString();
}

public String saveContra(String branchId,String draccountNameContra,String craccountNameContra,String contraVoucher,
		String drAmountContra,String crAmountContra,String contraDate,String contraNarration) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	
	VoucherEntrytransactions transactions = new VoucherEntrytransactions();
	
	transactions.setDraccountid(Integer.parseInt(draccountNameContra));
	transactions.setCraccountid(Integer.parseInt(craccountNameContra));
	transactions.setDramount(new BigDecimal(drAmountContra));
	transactions.setCramount(new BigDecimal(crAmountContra));
	transactions.setVouchertype(Integer.parseInt(contraVoucher));
	transactions.setTransactiondate(DateUtil.dateParserUpdateStd(contraDate));
	transactions.setNarration(contraNarration);
	transactions.setCancelvoucher("no");
	transactions.setBranchid(Integer.parseInt(branchId.toString()));
	transactions.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(branchId.toString())).getFinancialid());
	
	BigDecimal drAmount = new BigDecimal(drAmountContra);
	String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+drAmount+" where accountdetailsid="+Integer.parseInt(draccountNameContra);

	BigDecimal crAmount = new BigDecimal(drAmountContra);
	String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+crAmount+" where accountdetailsid="+Integer.parseInt(craccountNameContra);
	
	boolean saveVoucherAccUpdate= new AccountDAO().saveVoucherwithAccUpdate(transactions,updateDrAccount,updateCrAccount);
	sb.append("}");
	return sb.toString();
}

public String saveJournal(String branchId,String draccountNameJournal,String craccountNameJournal,String journalVoucher,
		String drAmountJournal,String crAmountJournal,String journalDate,String journalNarration) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	
	VoucherEntrytransactions transactions = new VoucherEntrytransactions();
	
	transactions.setDraccountid(Integer.parseInt(draccountNameJournal));
	transactions.setCraccountid(Integer.parseInt(craccountNameJournal));
	transactions.setDramount(new BigDecimal(drAmountJournal));
	transactions.setCramount(new BigDecimal(crAmountJournal));
	transactions.setVouchertype(Integer.parseInt(journalVoucher));
	transactions.setTransactiondate(DateUtil.dateParserUpdateStd(journalDate));
	transactions.setNarration(journalNarration);
	transactions.setCancelvoucher("no");
	transactions.setBranchid(Integer.parseInt(branchId.toString()));
	transactions.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(branchId.toString())).getFinancialid());
	

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
	
	boolean saveVoucherAccUpdate= new AccountDAO().saveVoucherwithAccUpdate(transactions,updateDrAccount,updateCrAccount);
	sb.append("}");
	return sb.toString();
}

public String balanceSheet(String branchId) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	
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
	accountDetailsBalance = new AccountDAO().getAccountdetailsbalance(Integer.parseInt(branchId.toString()));
	
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
	sb.append("capital").append(capital);
	sb.append(",capitalledgeraccount").append(capitalLedgerAccount);
	sb.append(",loansliabilities").append(loansLiabilities);
	sb.append(",loansliabilitiesledgeraccount").append(loansLiabilitiesLedgerAccount);
	sb.append(",currentliabilities").append(currentLiabilities);
	sb.append(",currentliabilitiesledgeraccount").append(currentLiabilitiesLedgerAccount);
	sb.append(",reserves").append(reserves);
	sb.append(",reservesledgeraccount").append(reservesLedgerAccount);
	
	//group 2
	sb.append(",fixedassets").append(fixedAssets);
	sb.append(",fixedassetsledgeraccount").append(fixedAssetsLedgerAccount);
	sb.append(",investments").append(investments);
	sb.append(",investmentsledgeraccount").append(investmentsLedgerAccount);
	sb.append(",currentassets").append(currentAssets);
	sb.append(",currentassetsledgeraccount").append(currentAssetsLedgerAccount);
	sb.append(",loansassets").append(loansAssets);
	sb.append(",loansassetsledgeraccount").append(loansAssetsLedgerAccount);
	sb.append(",miscellaneousexpenses").append(miscellaneousExpenses);
	sb.append(",miscellaneousexpensesledgeraccount").append(miscellaneousExpensesLedgerAccount);
	
	BigDecimal groupOneTotal = capital.add(loansLiabilities).add(currentLiabilities).add(reserves);
	sb.append("grouponetotal").append(groupOneTotal);
			
	BigDecimal groupTwoTotal = fixedAssets.add(investments).add(currentAssets).add(loansAssets).add(miscellaneousExpenses);
	sb.append("grouptwototal").append(groupTwoTotal);
	
	BigDecimal diff = groupOneTotal.subtract(groupTwoTotal);
	
	if(diff.compareTo(BigDecimal.ZERO) > 0){
		sb.append("grouptwototallabel").append("TOTAL");
		sb.append(",grouptwosemitotal").append(groupTwoTotal);
		sb.append(",grouptwototal").append(groupTwoTotal.add(diff));
		sb.append(",grouptwodifferencelabel").append("DIFFERENCE");
		sb.append(",grouptwodifferenceamount").append(diff);
	}else if(diff.compareTo(BigDecimal.ZERO) < 0){
		sb.append("grouponetotallabel").append("TOTAL");
		sb.append(",grouponesemitotal").append(groupOneTotal);
		sb.append(",grouponetotal").append(groupOneTotal.add(diff.abs()));
		sb.append(",differencelabel").append("DIFFERENCE");
		sb.append(",differenceamount").append(diff.abs());
		
	}
	sb.append("result:").append("true");
	sb.append("}");
	return sb.toString();
}

public String trialBalance(String branchId,String fromDate,String toDate) {
	
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	List<Accountdetailsbalance> accountDetailsBalance = new ArrayList<Accountdetailsbalance>();
	
	if(branchId!=null) {
		
		Integer.parseInt(branchId.toString());

			List<Accountdetails> accountsDetails = new ArrayList<Accountdetails>();
			accountsDetails = new AccountDAO().getAccountdetails();
			
			Map<Accountdetails,BigDecimal> accountBalanceMap = new LinkedHashMap<Accountdetails,BigDecimal>();
			
			BigDecimal debitAllAcc = BigDecimal.ZERO;
			BigDecimal creditAllAcc = BigDecimal.ZERO;
			BigDecimal totalBalanceAllAccDiff = BigDecimal.ZERO;
			
			for (Accountdetails accountDetails : accountsDetails) {
				
				List<VoucherEntrytransactions> voucherTransactions = new AccountDAO().getVoucherEntryTransactionsBetweenDates(fromDate, toDate, accountDetails.getAccountdetailsid(), Integer.parseInt(branchId.toString()));
				
				BigDecimal totalAmount = getTotalBalance(accountDetails,voucherTransactions);
				
				accountBalanceMap.put(accountDetails, totalAmount);
					
				if(accountDetails.getAccountGroupMaster().getAccountgroupid()==1 || accountDetails.getAccountGroupMaster().getAccountgroupid()==5) {
					debitAllAcc = debitAllAcc.add(totalAmount);
				}else{
					creditAllAcc = creditAllAcc.add(totalAmount);
				}
			}
			sb.append("accountdetailsbalanceMap").append(accountBalanceMap);
			sb.append(",credittotal").append(creditAllAcc);
			sb.append(",debittotal").append(debitAllAcc);
			
			totalBalanceAllAccDiff = creditAllAcc.subtract(debitAllAcc);
			
			if(totalBalanceAllAccDiff.signum() == 1){
				sb.append("differencetotal").append("Difference in Balances");
				sb.append(",debitdifference").append(totalBalanceAllAccDiff.abs());
				sb.append(",debittotal").append(debitAllAcc.add(totalBalanceAllAccDiff.abs()));
			}else if(totalBalanceAllAccDiff.signum() == -1){
				sb.append("differencetotal").append("Difference in Balances");
				sb.append(",creditdifference").append(totalBalanceAllAccDiff.abs());
				sb.append(",credittotal").append(creditAllAcc.add(totalBalanceAllAccDiff.abs()));
			}
			sb.append("result:").append("true");
			return sb.toString();
	}
	sb.append("result:").append("true");
	sb.append("}");
	return sb.toString();
}

private BigDecimal getTotalBalance(Accountdetails accountDetails, List<VoucherEntrytransactions> voucherTransactions) {
	
	BigDecimal totalBalanceAcc = BigDecimal.ZERO;
	BigDecimal debitAcc = BigDecimal.ZERO;
	BigDecimal creditAcc = BigDecimal.ZERO;
	
	for (VoucherEntrytransactions voucherTransaction : voucherTransactions) {
		
		if(voucherTransaction.getDraccountid() == accountDetails.getAccountdetailsid()) {
			debitAcc = debitAcc.add(voucherTransaction.getDramount());
		}else if(voucherTransaction.getCraccountid() == accountDetails.getAccountdetailsid()) {
			creditAcc = creditAcc.add(voucherTransaction.getCramount());
		}
	}

if(accountDetails.getAccountGroupMaster().getAccountgroupid()==1 || accountDetails.getAccountGroupMaster().getAccountgroupid()==5) {
	totalBalanceAcc = debitAcc.subtract(creditAcc);
}else{
	totalBalanceAcc = creditAcc.subtract(debitAcc);
}

return totalBalanceAcc;

}

public String cancelVoucher(String[] receiptIds,int voucherType ) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	
	
	if (receiptIds != null || voucherType!=0) {
		
		for (String id : receiptIds) {
			VoucherEntrytransactions voucherTransaction = new AccountDAO().getVoucherDetails(id);
			
			if(voucherType==1) {
				
				String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+voucherTransaction.getDramount()+" where accountdetailsid="+voucherTransaction.getDraccountid();
				String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+voucherTransaction.getCramount()+" where accountdetailsid="+voucherTransaction.getCraccountid();

				String cancelVoucher = "update VoucherEntrytransactions set cancelvoucher='yes' where transactionsid="+id;
				
				boolean updateAccVoucherCancel= new AccountDAO().updateAccountsWithVoucherCancel(updateDrAccount, updateCrAccount, cancelVoucher);
			}else if(voucherType==2) {

				String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+voucherTransaction.getDramount()+" where accountdetailsid="+voucherTransaction.getDraccountid();
				String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+voucherTransaction.getCramount()+" where accountdetailsid="+voucherTransaction.getCraccountid();
				
				String cancelVoucher = "update VoucherEntrytransactions set cancelvoucher='yes' where transactionsid="+id;
				
				boolean updateAccVoucherCancel= new AccountDAO().updateAccountsWithVoucherCancel(updateDrAccount, updateCrAccount, cancelVoucher);
			}else if(voucherType==3) {

				String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+voucherTransaction.getDramount()+" where accountdetailsid="+voucherTransaction.getDraccountid();
				String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+voucherTransaction.getCramount()+" where accountdetailsid="+voucherTransaction.getCraccountid();
				
				String cancelVoucher = "update VoucherEntrytransactions set cancelvoucher='yes' where transactionsid="+id;
				
				boolean updateAccVoucherCancel= new AccountDAO().updateAccountsWithVoucherCancel(updateDrAccount, updateCrAccount, cancelVoucher);
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
				
				String cancelVoucher = "update VoucherEntrytransactions set cancelvoucher='yes' where transactionsid="+id;
				
				boolean updateAccVoucherCancel= new AccountDAO().updateAccountsWithVoucherCancel(updateDrAccount, updateCrAccount, cancelVoucher);
			}

			
			
		}
		
	}
	sb.append("result:").append("false");
	sb.append("}");
	return sb.toString();
}

public String viewCancelledVouchers(String branchId) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	
	List<VoucherEntrytransactions> cancelledVoucherTransactions = new ArrayList<VoucherEntrytransactions>();
	
	if(branchId!=null) {

	String twoAccounts = null;
	
	Map<VoucherEntrytransactions,String> voucherMap = new LinkedHashMap<VoucherEntrytransactions, String>();
	int financialYearId = new AccountDAO().getCurrentFinancialYear(Integer.parseInt(branchId.toString())).getFinancialid();
	cancelledVoucherTransactions = new AccountDAO().getCancelledVoucherEntryTransactions(financialYearId, Integer.parseInt(branchId.toString()));
	
	for (VoucherEntrytransactions voucherEntry : cancelledVoucherTransactions) {
		twoAccounts = new AccountDAO().getAccountName(voucherEntry.getDraccountid())+"--"+new AccountDAO().getAccountName(voucherEntry.getCraccountid());
		voucherMap.put(voucherEntry, twoAccounts);
	}
	
	sb.append("cancelledvouchertransactions").append(voucherMap);
	sb.append("result:").append("true");
	return sb.toString();
	
	}
	sb.append("result:").append("false");
	sb.append("}");
	return sb.toString();
}

public String getSSGroupNames(String branchId, String subgroupname) throws IOException {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	
	List<Accountssgroupmaster> accountSSGroupMaster = new ArrayList<Accountssgroupmaster>();
	
	if(branchId!=null){
		PrintWriter out = response.getWriter(); 
		response.setContentType("text/xml");
	    response.setHeader("Cache-Control", "no-cache");
	        
		if(!"New Group".equalsIgnoreCase(subgroupname.toString())) {
		int accountSubGroupMasterId = DataUtil.parseInt(subgroupname);
		accountSSGroupMaster = new AccountDAO().getListAccountSSGroupMaster(accountSubGroupMasterId,Integer.parseInt(branchId.toString()));
		sb.append("accountssgroupmaster").append(accountSSGroupMaster);
	        try {
	        	
	        	if(!accountSSGroupMaster.isEmpty()){
	        		String buffer = "<select name='ssgroupname' style='width: 240px' id='ssgname' onchange='ssGroupSelect()'>";
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
	
	sb.append("}");
}
}

