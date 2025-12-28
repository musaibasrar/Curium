package org.ideoholic.curium.model.account.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.account.dao.AccountDAO;
import org.ideoholic.curium.model.account.dto.*;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

@Slf4j
@Service
public class AccountService {

		@Autowired
	    private HttpServletResponse response;
		
		@Autowired
	    private AccountDAO accountDao;
	    
	    private static final int BUFFER_SIZE = 4096;


	public ResultResponse saveFinancialYear(AccountFinancialYearDto accountFinancialYearDto, String branchId) {
		ResultResponse result = ResultResponse.builder().build();

		Financialaccountingyear financialaccountingyear = new Financialaccountingyear();
		
		if(branchId!=null){
			financialaccountingyear.setFinancialstartdate(DateUtil.dateParserUpdateStd(accountFinancialYearDto.getFromDate()));
			financialaccountingyear.setFinancialenddate(DateUtil.dateParserUpdateStd(accountFinancialYearDto.getToDate()));
			financialaccountingyear.setActive(DataUtil.emptyString(accountFinancialYearDto.getActive()));
			financialaccountingyear.setBranchid(Integer.parseInt(branchId));

			result.setSuccess(accountDao.create(financialaccountingyear, Integer.parseInt(branchId)));

			return result;
		}
		result.setSuccess(false);
		return result;
	}

	public CurrentFinancialYearResponseDto getCurrentFinancialYear(String branchId) {
		CurrentFinancialYearResponseDto currentFinancialYearResponseDto = null;
		Financialaccountingyear financialYear = new Financialaccountingyear();
		if(branchId!=null){
			financialYear =  accountDao.getCurrentFinancialYear(Integer.parseInt(branchId));
		}

		if (financialYear != null) {
			currentFinancialYearResponseDto = CurrentFinancialYearResponseDto
					.builder()
					.financialStartDate(financialYear.getFinancialstartdate())
					.financialEndDate(financialYear.getFinancialenddate())
					.success(true)
					.build();
		}
		else {
			currentFinancialYearResponseDto = CurrentFinancialYearResponseDto
					.builder()
					.success(false)
					.build();
		}
		
		return currentFinancialYearResponseDto;
	}


	public CreateAccountResponseDto createAccount(String branchId) {

		List<Accountdetailsbalance> accountDetailsBalance = new ArrayList<Accountdetailsbalance>();
		List<Accountgroupmaster> accountGroupMaster = new ArrayList<Accountgroupmaster>();
		
		if(branchId!=null){

			accountGroupMaster = accountDao.getListAccountGroupMaster(Integer.parseInt(branchId));
			accountDetailsBalance = accountDao.getAccountdetailsbalance(Integer.parseInt(branchId));
		}
		
		
		if(!accountGroupMaster.isEmpty()){
			return CreateAccountResponseDto
					.builder()
					.accountDetailsBalance(accountDetailsBalance)
					.accountGroupMaster(accountGroupMaster)
					.success(true)
					.build();
		}
		return CreateAccountResponseDto
				.builder()
				.success(false)
				.build();
		
	}


	public ResultResponse getSubGroupNames(String branchId, String strAccountGroupMasterId) throws IOException {
		ResultResponse resultResponse = null;
		List<Accountsubgroupmaster> accountSubGroupMaster = new ArrayList<Accountsubgroupmaster>();
		
		if(branchId!=null){
			int accountGroupMasterId = Integer.parseInt(strAccountGroupMasterId);
			accountSubGroupMaster = accountDao.getListAccountSubGroupMaster(accountGroupMasterId,Integer.parseInt(branchId));
			resultResponse = ResultResponse
					.builder()
					.resultList(accountSubGroupMaster)
					.build();
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
		
		return resultResponse;
	}

	public CreateAccountResponseDto saveAccount(AccountDto accountDto, String branchId) {

		CreateAccountResponseDto result = null;
		String newSubGroup =  DataUtil.emptyString(accountDto.getNewSubGroup());
		String newSSGroup =  DataUtil.emptyString(accountDto.getNewSSGroup());
		String subGroupName =  DataUtil.emptyString(accountDto.getSubGroupName());
		String ssGroupName =  DataUtil.emptyString(accountDto.getSsGroupName());
		String groupName =  DataUtil.emptyString(accountDto.getGroupName());
		String accountName =  DataUtil.emptyString(accountDto.getAccountName());
		String accountCode =  DataUtil.emptyString(accountDto.getAccountCode());
		
		
		Accountdetails accountDetailsCheck = accountDao.checkAccountDetails(accountName, accountCode, Integer.parseInt(branchId));
		
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
					accountSSGroupMaster.setBranchid(Integer.parseInt(branchId));
					accountSSGroupMaster = accountDao.createSSGroup(accountSSGroupMaster);
					accountDetails.setAccountSSGroupMaster(accountSSGroupMaster);
				}
				
				Accountgroupmaster accountGroupMaster = new Accountgroupmaster();
				accountGroupMaster.setAccountgroupid(getInt(groupName));
				accountDetails.setAccountGroupMaster(accountGroupMaster);
				accountDetails.setBranchid(Integer.parseInt(branchId));
				
					// Add account balance
					Financialaccountingyear financialyear = accountDao.getFinancialAccountingYear(Integer.parseInt(branchId));
					Accountdetailsbalance accountDetailsBalance = new Accountdetailsbalance();
					accountDetailsBalance.setAccountDetails(accountDetails);
					if(findCrDr(groupName)){
						accountDetailsBalance.setCrdr("Cr");
					}else{
						accountDetailsBalance.setCrdr("Dr");
					}
					accountDetailsBalance.setFinancialAccountingYear(financialyear);
					accountDetailsBalance.setOpeningbalance(new BigDecimal(0));
					accountDetailsBalance.setCurrentbalance(new BigDecimal(0));
					accountDetailsBalance.setEnteredon(new Date());
					accountDetailsBalance.setBranchid(Integer.parseInt(branchId));
					result = CreateAccountResponseDto.builder()
						.message(accountDao.saveNewAccount(accountDetails, accountDetailsBalance))
						.success(true).build();
					
		}else if("New Group".equalsIgnoreCase(subGroupName)){

			Accountsubgroupmaster accountSubGroupMaster = new Accountsubgroupmaster();
			Accountssgroupmaster accountSSGroupMaster = new Accountssgroupmaster();
			Accountgroupmaster accountGroup = new Accountgroupmaster();
			
			accountGroup.setAccountgroupid(Integer.parseInt(groupName));
			accountSubGroupMaster.setAccountGroupMaster(accountGroup);
			accountSubGroupMaster.setAccountsubgroupname(newSubGroup);
			accountSubGroupMaster.setBranchid(Integer.parseInt(branchId));
			accountSubGroupMaster = accountDao.createSubGroup(accountSubGroupMaster);
			
			 if("New Sub-Group".equalsIgnoreCase(ssGroupName)){
					
					accountSubGroupMaster.setAccountsubgroupmasterid(accountSubGroupMaster.getAccountsubgroupmasterid());
					accountSSGroupMaster.setAccountSubGroupMaster(accountSubGroupMaster);
					accountSSGroupMaster.setSsgroupname(newSSGroup);
					accountSSGroupMaster.setSsgroupname(newSSGroup);
					accountSSGroupMaster.setBranchid(Integer.parseInt(branchId));
					accountSSGroupMaster = accountDao.createSSGroup(accountSSGroupMaster);
					
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
				accountDetails.setAccountSubGroupMaster(accountSubGroupMaster);
				accountDetails.setBranchid(Integer.parseInt(branchId));
				
					Financialaccountingyear financialyear = accountDao.getFinancialAccountingYear(Integer.parseInt(branchId));
					Accountdetailsbalance accountDetailsBalance = new Accountdetailsbalance();
					accountDetailsBalance.setAccountDetails(accountDetails);
					if(findCrDr(groupName)){ 
						accountDetailsBalance.setCrdr("Cr");
					}else{
						accountDetailsBalance.setCrdr("Dr");
					}
					accountDetailsBalance.setFinancialAccountingYear(financialyear);
					accountDetailsBalance.setOpeningbalance(new BigDecimal(0));
					accountDetailsBalance.setCurrentbalance(new BigDecimal(0));
					accountDetailsBalance.setEnteredon(new Date());
					accountDetailsBalance.setBranchid(Integer.parseInt(branchId));
					result = CreateAccountResponseDto.builder()
						.message(accountDao.saveNewAccount(accountDetails, accountDetailsBalance))
						.success(true).build();
		}}else {
				if(accountName.equalsIgnoreCase(accountDetailsCheck.getAccountname())) {
					result = CreateAccountResponseDto.builder()
						.message("Error-Account Name already exists")
						.success(false).build();
				}else if(accountCode.equalsIgnoreCase(accountDetailsCheck.getAccountcode())) {
					result = CreateAccountResponseDto.builder()
						.message("Error-Account Code already exists")
						.success(false).build();
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
		String[] groupOne = {"2","3","4"};
		for (String group : groupOne) {
			if(group.equalsIgnoreCase(groupName)){
				return true;
			}
		}
		return false;
	}


	public CreateAccountResponseDto deleteAccount(AccountDeleteDto accountDeleteDto) {
		CreateAccountResponseDto result = CreateAccountResponseDto.builder().build();

		String[] accountIds = accountDeleteDto.getAccountIds();
		if (accountIds != null) {
			List<Integer> balanceIds = new ArrayList<Integer>();
			List<Integer> accountdetailsIds = new ArrayList<Integer>();
			for (String id : accountIds) {
				String[] split = id.split("-");
				balanceIds.add(Integer.valueOf(split[0]));
				accountdetailsIds.add(Integer.valueOf(split[1]));
				boolean checkInTransactions = accountDao.checkInTransactions(Integer.valueOf(split[1]));
				if(!checkInTransactions){
					accountDao.deleteMultipleAccounts(Integer.valueOf(split[0]),Integer.valueOf(split[1]));
				}
			}
			result.setSuccess(true);
			return result;
		}
		result.setSuccess(false);
		return result;
	}


	public CreateVoucherResponseDto createVoucher(String branchId) {

		List<Accountdetailsbalance> accountDetailsBalance = new ArrayList<Accountdetailsbalance>();
		if(branchId!=null) {
			List<Integer> accountIds = new ArrayList<Integer>();
			accountIds.add(2);
			accountIds.add(3);
			accountIds.add(4);
			accountDetailsBalance = accountDao.getAccountdetailsbalanceExBC(accountIds, Integer.parseInt(branchId));
			accountIds.clear();
			
			List<Accountdetailsbalance> accountDetailsBalanceExpenses = new ArrayList<Accountdetailsbalance>();
			accountIds.add(5);
			accountDetailsBalanceExpenses = accountDao.getAccountdetailsbalanceExBC(accountIds, Integer.parseInt(branchId));

			List<Accountdetailsbalance> accountDetailsBalanceBankCash = new ArrayList<Accountdetailsbalance>();
			accountDetailsBalanceBankCash = accountDao.getAccountdetailsbalanceBankCash(Integer.parseInt(branchId));

			List<Accountdetailsbalance> accountDetailsJournalEntry = new ArrayList<Accountdetailsbalance>();
			accountDetailsJournalEntry = accountDao.getAccountdetailsbalance(Integer.parseInt(branchId));

			return CreateVoucherResponseDto
					.builder()
					.accountDetailsBalance(accountDetailsBalance)
					.accountDetailsBalanceExpenses(accountDetailsBalanceExpenses)
					.accountDetailsBalanceBankCash(accountDetailsBalanceBankCash)
					.accountDetailsJournalEntry(accountDetailsJournalEntry)
					.success(true)
					.build();
		}
		return CreateVoucherResponseDto
				.builder()
				.success(false)
				.build();
	}


	public CreateVoucherResponseDto saveReceipt(AccountReceiptDto accountReceiptDto, String branchId) {

		String draccountName = DataUtil.emptyString(accountReceiptDto.getDraccountName());
		String craccountName = DataUtil.emptyString(accountReceiptDto.getCraccountName());
		String receiptVoucher = DataUtil.emptyString(accountReceiptDto.getReceiptVoucher());
		String drAmount = DataUtil.emptyString(accountReceiptDto.getDrAmount());
		String crAmount = DataUtil.emptyString(accountReceiptDto.getCrAmount());
		String receiptDate = DataUtil.emptyString(accountReceiptDto.getReceiptDate());
		String receiptNarration = DataUtil.emptyString(accountReceiptDto.getReceiptNarration());
		
		VoucherEntrytransactions transactions = new VoucherEntrytransactions();
		
		transactions.setDraccountid(Integer.parseInt(draccountName));
		transactions.setCraccountid(Integer.parseInt(craccountName));
		transactions.setDramount(new BigDecimal(drAmount));
		transactions.setCramount(new BigDecimal(crAmount));
		transactions.setVouchertype(Integer.parseInt(receiptVoucher));
		transactions.setTransactiondate(DateUtil.dateParserUpdateStd(receiptDate));
		transactions.setEntrydate(DateUtil.todaysDate());
		transactions.setNarration(receiptNarration);
		transactions.setCancelvoucher("no");
		transactions.setFinancialyear(accountDao.getCurrentFinancialYear(Integer.parseInt(branchId)).getFinancialid());
		transactions.setBranchid(Integer.parseInt(branchId));
		
		
		BigDecimal drAmountReceipt = new BigDecimal(drAmount);
		String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+drAmountReceipt+" where accountdetailsid="+Integer.parseInt(draccountName);

		BigDecimal crAmountReceipt = new BigDecimal(crAmount);
		String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+crAmountReceipt+" where accountdetailsid="+Integer.parseInt(craccountName);
		
		return CreateVoucherResponseDto
				.builder()
				.success(accountDao.saveVoucherwithAccUpdate(transactions,updateDrAccount,updateCrAccount))
				.build();
	}


	public CreateVoucherResponseDto savePayment(AccountPaymentDto accountPaymentDto, String branchId) {
		
		String draccountNamePayment = DataUtil.emptyString(accountPaymentDto.getDraccountName());
		String craccountNamePayment = DataUtil.emptyString(accountPaymentDto.getCraccountName());
		String paymentVoucher = DataUtil.emptyString(accountPaymentDto.getPaymentVoucher());
		String drAmountPayment = DataUtil.emptyString(accountPaymentDto.getDrAmountPayment());
		String crAmountPayment = DataUtil.emptyString(accountPaymentDto.getCrAmountPayment());
		String paymentDate = DataUtil.emptyString(accountPaymentDto.getPaymentDate());
		String paymentNarration = DataUtil.emptyString(accountPaymentDto.getPaymentNarration());
		
		VoucherEntrytransactions transactions = new VoucherEntrytransactions();
		
		transactions.setDraccountid(Integer.parseInt(draccountNamePayment));
		transactions.setCraccountid(Integer.parseInt(craccountNamePayment));
		transactions.setDramount(new BigDecimal(drAmountPayment));
		transactions.setCramount(new BigDecimal(crAmountPayment));
		transactions.setVouchertype(Integer.parseInt(paymentVoucher));
		transactions.setTransactiondate(DateUtil.dateParserUpdateStd(paymentDate));
		transactions.setEntrydate(DateUtil.todaysDate());
		transactions.setNarration(paymentNarration);
		transactions.setCancelvoucher("no");
		transactions.setBranchid(Integer.parseInt(branchId));
		transactions.setFinancialyear(accountDao.getCurrentFinancialYear(Integer.parseInt(branchId)).getFinancialid());
		
		BigDecimal drAmount = new BigDecimal(drAmountPayment);
		String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+drAmount+" where accountdetailsid="+Integer.parseInt(draccountNamePayment);

		BigDecimal crAmount = new BigDecimal(crAmountPayment);
		String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+crAmount+" where accountdetailsid="+Integer.parseInt(craccountNamePayment);
		
		return CreateVoucherResponseDto
				.builder()
				.success(accountDao.saveVoucherwithAccUpdate(transactions,updateDrAccount,updateCrAccount))
				.build();
		
	}


	public CreateVoucherResponseDto saveContra(AccountContraDto accountContraDto, String branchId) {
		
		String draccountNameContra = DataUtil.emptyString(accountContraDto.getDraccountName());
		String craccountNameContra = DataUtil.emptyString(accountContraDto.getCraccountName());
		String contraVoucher = DataUtil.emptyString(accountContraDto.getContraVoucher());
		String drAmountContra = DataUtil.emptyString(accountContraDto.getDrAmountContra());
		String crAmountContra = DataUtil.emptyString(accountContraDto.getCrAmountContra());
		String contraDate = DataUtil.emptyString(accountContraDto.getContraDate());
		String contraNarration = DataUtil.emptyString(accountContraDto.getContraNarration());
		
		VoucherEntrytransactions transactions = new VoucherEntrytransactions();
		
		transactions.setDraccountid(Integer.parseInt(draccountNameContra));
		transactions.setCraccountid(Integer.parseInt(craccountNameContra));
		transactions.setDramount(new BigDecimal(drAmountContra));
		transactions.setCramount(new BigDecimal(crAmountContra));
		transactions.setVouchertype(Integer.parseInt(contraVoucher));
		transactions.setTransactiondate(DateUtil.dateParserUpdateStd(contraDate));
		transactions.setEntrydate(DateUtil.todaysDate());
		transactions.setNarration(contraNarration);
		transactions.setCancelvoucher("no");
		transactions.setBranchid(Integer.parseInt(branchId));
		transactions.setFinancialyear(accountDao.getCurrentFinancialYear(Integer.parseInt(branchId)).getFinancialid());
		
		BigDecimal drAmount = new BigDecimal(drAmountContra);
		String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+drAmount+" where accountdetailsid="+Integer.parseInt(draccountNameContra);

		BigDecimal crAmount = new BigDecimal(drAmountContra);
		String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+crAmount+" where accountdetailsid="+Integer.parseInt(craccountNameContra);
		
		return CreateVoucherResponseDto
				.builder()
				.success(accountDao.saveVoucherwithAccUpdate(transactions,updateDrAccount,updateCrAccount))
				.build();
	}


	public CreateVoucherResponseDto saveJournal(AccountJournalDto accountJournalDto, String branchId) {
		
		String draccountNameJournal = DataUtil.emptyString(accountJournalDto.getDraccountNameJournal());
		String craccountNameJournal = DataUtil.emptyString(accountJournalDto.getCraccountNameJournal());
		String journalVoucher = DataUtil.emptyString(accountJournalDto.getJournalVoucher());
		String drAmountJournal = DataUtil.emptyString(accountJournalDto.getDrAmountJournal());
		String crAmountJournal = DataUtil.emptyString(accountJournalDto.getCrAmountJournal());
		String journalDate = DataUtil.emptyString(accountJournalDto.getJournalDate());
		String journalNarration = DataUtil.emptyString(accountJournalDto.getJournalNarration());
		
		VoucherEntrytransactions transactions = new VoucherEntrytransactions();
		
		transactions.setDraccountid(Integer.parseInt(draccountNameJournal));
		transactions.setCraccountid(Integer.parseInt(craccountNameJournal));
		transactions.setDramount(new BigDecimal(drAmountJournal));
		transactions.setCramount(new BigDecimal(crAmountJournal));
		transactions.setVouchertype(Integer.parseInt(journalVoucher));
		transactions.setTransactiondate(DateUtil.dateParserUpdateStd(journalDate));
		transactions.setEntrydate(DateUtil.todaysDate());
		transactions.setNarration(journalNarration);
		transactions.setCancelvoucher("no");
		transactions.setBranchid(Integer.parseInt(branchId));
		transactions.setFinancialyear(accountDao.getCurrentFinancialYear(Integer.parseInt(branchId)).getFinancialid());
		

		// Dr
		BigDecimal drAmount = new BigDecimal(drAmountJournal);
		Accountdetails accountDetailsDr = accountDao.getAccountDetails(Integer.parseInt(draccountNameJournal));
		String updateDrAccount= null;
		if(accountDetailsDr.getAccountGroupMaster().getAccountgroupid()==1 || accountDetailsDr.getAccountGroupMaster().getAccountgroupid()==5) {
			updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+drAmount+" where accountdetailsid="+Integer.parseInt(draccountNameJournal);
		}else {
			updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+drAmount+" where accountdetailsid="+Integer.parseInt(draccountNameJournal);
		}
		
		//Cr
		
		BigDecimal crAmount = new BigDecimal(crAmountJournal);
		String updateCrAccount= null;
		Accountdetails accountDetailsCr = accountDao.getAccountDetails(Integer.parseInt(craccountNameJournal));
		
		if(accountDetailsCr.getAccountGroupMaster().getAccountgroupid()==2 || accountDetailsCr.getAccountGroupMaster().getAccountgroupid()==3 || accountDetailsCr.getAccountGroupMaster().getAccountgroupid()==4) {
			updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+crAmount+" where accountdetailsid="+Integer.parseInt(craccountNameJournal);
		}else {
			updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+crAmount+" where accountdetailsid="+Integer.parseInt(craccountNameJournal);
		}
		
		return CreateVoucherResponseDto
				.builder()
				.success(accountDao.saveVoucherwithAccUpdate(transactions,updateDrAccount,updateCrAccount))
				.build();
		
	}


	public BalanceSheetResponseDto balanceSheet(String branchId) {
		
		
		//Group 1
				BigDecimal liabilities = BigDecimal.ZERO;
				Map<String,BigDecimal> liabilitiesLedgerAccount = new HashMap<String, BigDecimal>();
				
				BigDecimal reserves = BigDecimal.ZERO;
				Map<String,BigDecimal> reservesLedgerAccount = new HashMap<String, BigDecimal>();
				
				//Group 2
				BigDecimal assets = BigDecimal.ZERO;
				Map<String,BigDecimal> assetsLedgerAccount = new HashMap<String, BigDecimal>();
				
				
				List<Accountdetailsbalance> accountDetailsBalance = new ArrayList<Accountdetailsbalance>();
				accountDetailsBalance = accountDao.getAccountdetailsbalance(Integer.parseInt(branchId));
				
				
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
			return BalanceSheetResponseDto
					.builder()
					.liabilities(liabilities)
					.liabilitiesLedgerAccount(liabilitiesLedgerAccount)
					.reserves(reserves)
					.reservesLedgerAccount(reservesLedgerAccount)
					.assets(assets)
					.assetsLedgerAccount(assetsLedgerAccount)
					.liabilities(liabilities)
					.reserves(reserves)
					.assets(assets)
					.success(true)
					.build();
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
		accountDetailsBalance = accountDao.getAccountdetailsbalance(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		
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

	public ViewNextVoucherResponseDto viewVouchers(int voucherType, String branchId, String fromDate, String toDate, String nextVoucher) {
		ViewNextVoucherDto viewNextVoucherDto = ViewNextVoucherDto.builder()
				.fromDate(fromDate)
				.toDate(toDate)
				.branchId(Integer.valueOf(branchId))
				.nextVoucher(nextVoucher)
				.build();
		return viewVouchers(viewNextVoucherDto, voucherType);
	}

	public ViewNextVoucherResponseDto viewVouchers(ViewNextVoucherDto viewNextVoucherDto){
		String nextVoucher = DataUtil.emptyString(viewNextVoucherDto.getNextVoucher());

		if(nextVoucher.equalsIgnoreCase("Receipt")){
			ViewNextVoucherResponseDto viewNextVoucherResponseDto = viewVouchers(viewNextVoucherDto, 1);
			if(viewNextVoucherResponseDto.isSuccess()){
				viewNextVoucherResponseDto.setVoucherType(nextVoucher);
				return viewNextVoucherResponseDto;
				//receiptdetails
			}

		}else if(nextVoucher.equalsIgnoreCase("Payment")){
			ViewNextVoucherResponseDto viewNextVoucherResponseDto = viewVouchers(viewNextVoucherDto, 2);
			if(viewNextVoucherResponseDto.isSuccess()){
				viewNextVoucherResponseDto.setVoucherType(nextVoucher);
				return viewNextVoucherResponseDto;
			}

		}else if(nextVoucher.equalsIgnoreCase("Contra")){
			ViewNextVoucherResponseDto viewNextVoucherResponseDto = viewVouchers(viewNextVoucherDto, 3);
			if(viewNextVoucherResponseDto.isSuccess()){
				viewNextVoucherResponseDto.setVoucherType(nextVoucher);
				return viewNextVoucherResponseDto;
			}

		}else if(nextVoucher.equalsIgnoreCase("Journal")){
			ViewNextVoucherResponseDto viewNextVoucherResponseDto = viewVouchers(viewNextVoucherDto, 4);
			if(viewNextVoucherResponseDto.isSuccess()){
				viewNextVoucherResponseDto.setVoucherType(nextVoucher);
				return viewNextVoucherResponseDto;
			}
		}
		return ViewNextVoucherResponseDto.builder().success(false).build();
	}

	public ViewNextVoucherResponseDto viewVouchers(ViewNextVoucherDto viewNextVoucherDto, int voucherType) {

		List<VoucherEntrytransactions> voucherTransactions = new ArrayList<VoucherEntrytransactions>();
		String fromDate = DataUtil.dateFromatConversionSlash(DataUtil.emptyString(viewNextVoucherDto.getFromDate()));
		String toDate = DataUtil.dateFromatConversionSlash(DataUtil.emptyString(viewNextVoucherDto.getToDate()));

		if(viewNextVoucherDto.getBranchId()!=null) {

			String twoAccounts = null;

			Map<VoucherEntrytransactions,String> voucherMap = new LinkedHashMap<VoucherEntrytransactions, String>();
			int financialYearId = accountDao.getCurrentFinancialYear(viewNextVoucherDto.getBranchId()).getFinancialid();
			voucherTransactions = accountDao.getVoucherEntryTransactions(fromDate, toDate, financialYearId, viewNextVoucherDto.getBranchId(), voucherType);

			for (VoucherEntrytransactions voucherEntry : voucherTransactions) {
				twoAccounts = accountDao.getAccountName(voucherEntry.getDraccountid())+"--"+accountDao.getAccountName(voucherEntry.getCraccountid());
				voucherMap.put(voucherEntry, twoAccounts);
			}

			return ViewNextVoucherResponseDto
					.builder()
					.voucherTransactions(voucherMap)
					.fromDateSelected(viewNextVoucherDto.getFromDate())
					.toDateSelected(viewNextVoucherDto.getToDate())
					.success(true)
					.build();

		}
		return ViewNextVoucherResponseDto
				.builder()
				.success(false)
				.build();
	}


	public TrialBalanceResponseDto trialBalance(DayBookDto dto, String strBranchId) {
		
		List<Accountdetailsbalance> accountDetailsBalance = new ArrayList<Accountdetailsbalance>();
		
		String fromDate = DataUtil.dateFromatConversionSlash(DataUtil.emptyString(dto.getFromDate()));
		String toDate = DataUtil.dateFromatConversionSlash(DataUtil.emptyString(dto.getToDate()));
		
		if(strBranchId!=null) {
			
			int branchId = Integer.parseInt(strBranchId);

				List<Accountdetails> accountsDetails = accountDao.getAccountdetails(branchId);
				
				Map<Accountdetails,BigDecimal> accountBalanceMap = new LinkedHashMap<>();
				
				BigDecimal debitAllAcc = BigDecimal.ZERO;
				BigDecimal creditAllAcc = BigDecimal.ZERO;
				BigDecimal totalBalanceAllAccDiff = BigDecimal.ZERO;
				
				for (Accountdetails accountDetails : accountsDetails) {
					
					List<VoucherEntrytransactions> voucherTransactions = accountDao.getVoucherEntryTransactionsBetweenDates(fromDate, toDate, accountDetails.getAccountdetailsid(), Integer.parseInt(strBranchId));
					
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

				totalBalanceAllAccDiff = creditAllAcc.subtract(debitAllAcc);

				return TrialBalanceResponseDto
						.builder()
						.accountDetailsBalanceMap(accountBalanceMap)
						.creditTotal(creditAllAcc)
						.debitTotal(debitAllAcc)
						.fromDate(DataUtil.dateFromatConversionSlash(fromDate))
						.toDate(DataUtil.dateFromatConversionSlash(toDate))
						.success(true)
						.build();
				

				
				/*if(totalBalanceAllAccDiff.signum() == 1){
					request.setAttribute("differencetotal", "Difference in Balances");
					request.setAttribute("debitdifference", totalBalanceAllAccDiff.abs());
					request.setAttribute("debittotal", debitAllAcc.add(totalBalanceAllAccDiff.abs()));
				}else if(totalBalanceAllAccDiff.signum() == -1){
					request.setAttribute("differencetotal", "Difference in Balances");
					request.setAttribute("creditdifference", totalBalanceAllAccDiff.abs());
					request.setAttribute("credittotal", creditAllAcc.add(totalBalanceAllAccDiff.abs()));
				}*/
		}
		
		return TrialBalanceResponseDto
				.builder()
				.success(false)
				.build();
	}


	private BigDecimal getTotalBalance(Accountdetails accountDetails, List<VoucherEntrytransactions> voucherTransactions) {
		
		BigDecimal totalBalanceAcc = BigDecimal.ZERO;
		BigDecimal debitAcc = BigDecimal.ZERO;
		BigDecimal creditAcc = BigDecimal.ZERO;
		
		for (VoucherEntrytransactions voucherTransaction : voucherTransactions) {
			int drAccountid = voucherTransaction.getDraccountid();
			int crAccountid = voucherTransaction.getCraccountid();
			int acccountId = accountDetails.getAccountdetailsid(); 
			if(drAccountid == acccountId) {
				BigDecimal augend = voucherTransaction.getDramount();
				if(augend != null) {
					debitAcc = debitAcc.add(augend);
				}
			}else if(crAccountid == acccountId) {
				BigDecimal augend = voucherTransaction.getCramount();
				if(augend != null) {
					creditAcc = creditAcc.add(augend);
				}
			}
		}
	
	if(accountDetails.getAccountGroupMaster().getAccountgroupid()==1 || accountDetails.getAccountGroupMaster().getAccountgroupid()==5) {
		totalBalanceAcc = debitAcc.subtract(creditAcc);
	}else{
		totalBalanceAcc = creditAcc.subtract(debitAcc);
	}
	
	return totalBalanceAcc;
	
	}


	public ResultResponse cancelVoucher(CancelVoucherDto cancelVoucherDto) {
		ResultResponse result = ResultResponse.builder().build();

		String[] receiptIds = cancelVoucherDto.getReceiptIds();
		String voucher = cancelVoucherDto.getVoucher();
		int voucherType = 0;
		Date now = new Date();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String todaysDate = formatter.format(now);
		
        
        switch (voucher) {
		case "Receipt":
			voucherType = 1;
			break;
		case "Payment":
			voucherType = 2;
			break;
		case "Contra":
			voucherType = 3;
			break;
		case "Journal":
			voucherType = 4;
			break;
		default:
			break;
		}
		
		if (receiptIds != null || voucherType!=0) {
			
			for (String id : receiptIds) {
				VoucherEntrytransactions voucherTransaction = accountDao.getVoucherDetails(id);
				
				if(voucherType==1) {
					
					String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+voucherTransaction.getDramount()+" where accountdetailsid="+voucherTransaction.getDraccountid();
					String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+voucherTransaction.getCramount()+" where accountdetailsid="+voucherTransaction.getCraccountid();

					String cancelVoucher = "update VoucherEntrytransactions set cancelvoucher='yes', vouchercancellationdate='"+todaysDate+"' where transactionsid="+id;

					result.setSuccess(accountDao.updateAccountsWithVoucherCancel(updateDrAccount, updateCrAccount, cancelVoucher));
					return result;
				}else if(voucherType==2) {

					String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+voucherTransaction.getDramount()+" where accountdetailsid="+voucherTransaction.getDraccountid();
					String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+voucherTransaction.getCramount()+" where accountdetailsid="+voucherTransaction.getCraccountid();
					
					String cancelVoucher = "update VoucherEntrytransactions set cancelvoucher='yes', vouchercancellationdate='"+todaysDate+"' where transactionsid="+id;

					result.setSuccess(accountDao.updateAccountsWithVoucherCancel(updateDrAccount, updateCrAccount, cancelVoucher));
					return result;
				}else if(voucherType==3) {

					String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+voucherTransaction.getDramount()+" where accountdetailsid="+voucherTransaction.getDraccountid();
					String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+voucherTransaction.getCramount()+" where accountdetailsid="+voucherTransaction.getCraccountid();
					
					String cancelVoucher = "update VoucherEntrytransactions set cancelvoucher='yes', vouchercancellationdate='"+todaysDate+"' where transactionsid="+id;

					result.setSuccess(accountDao.updateAccountsWithVoucherCancel(updateDrAccount, updateCrAccount, cancelVoucher));
					return result;
				}else if(voucherType==4) {
					
					// Dr
					Accountdetails accountDetailsDr = accountDao.getAccountDetails(voucherTransaction.getDraccountid());
					String updateDrAccount= null;
					if(accountDetailsDr.getAccountGroupMaster().getAccountgroupid()==1 || accountDetailsDr.getAccountGroupMaster().getAccountgroupid()==5) {
						updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+voucherTransaction.getDramount()+" where accountdetailsid="+voucherTransaction.getDraccountid();
					}else {
						updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+voucherTransaction.getDramount()+" where accountdetailsid="+voucherTransaction.getDraccountid();
					}
					
					//Cr
					
					Accountdetails accountDetailsCr = accountDao.getAccountDetails(voucherTransaction.getCraccountid());
					String updateCrAccount= null;
					
					if(accountDetailsCr.getAccountGroupMaster().getAccountgroupid()==2 || accountDetailsCr.getAccountGroupMaster().getAccountgroupid()==3 || accountDetailsCr.getAccountGroupMaster().getAccountgroupid()==4) {
						updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+voucherTransaction.getCramount()+" where accountdetailsid="+voucherTransaction.getCraccountid();
					}else {
						updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+voucherTransaction.getCramount()+" where accountdetailsid="+voucherTransaction.getCraccountid();
					}
					
					String cancelVoucher = "update VoucherEntrytransactions set cancelvoucher='yes', vouchercancellationdate='"+todaysDate+"' where transactionsid="+id;
					
					result.setSuccess(accountDao.updateAccountsWithVoucherCancel(updateDrAccount, updateCrAccount, cancelVoucher));
					return  result;
				}

				
				
			}
			
		}
		result.setSuccess(true);
		return result;
	}


	public ResultResponse viewCancelledVouchers(String branchId) {
		
		List<VoucherEntrytransactions> cancelledVoucherTransactions = new ArrayList<VoucherEntrytransactions>();
		
		if(branchId!=null) {

		String twoAccounts = null;
		
		Map<VoucherEntrytransactions,String> voucherMap = new LinkedHashMap<VoucherEntrytransactions, String>();
		int financialYearId = accountDao.getCurrentFinancialYear(Integer.parseInt(branchId)).getFinancialid();
		cancelledVoucherTransactions = accountDao.getCancelledVoucherEntryTransactions(financialYearId, Integer.parseInt(branchId));
		
		for (VoucherEntrytransactions voucherEntry : cancelledVoucherTransactions) {
			twoAccounts = accountDao.getAccountName(voucherEntry.getDraccountid())+"--"+accountDao.getAccountName(voucherEntry.getCraccountid());
			voucherMap.put(voucherEntry, twoAccounts);
		}

		return ResultResponse
				.builder()
				.resultMap(voucherMap)
				.success(true)
				.build();
		
		}
		return ResultResponse
				.builder()
				.success(false)
				.build();
	}


	public ResultResponse getSSGroupNames(String branchId, String strAccountSubGroupMasterId) throws IOException {
		ResultResponse resultResponse = null;
		List<Accountssgroupmaster> accountSSGroupMaster = new ArrayList<Accountssgroupmaster>();

		if(branchId!=null){
			PrintWriter out = response.getWriter();
			response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");

			if(!"New Group".equalsIgnoreCase(strAccountSubGroupMasterId)) {
				int accountSubGroupMasterId = DataUtil.parseInt(strAccountSubGroupMasterId);
				accountSSGroupMaster = accountDao.getListAccountSSGroupMaster(accountSubGroupMasterId,Integer.parseInt(branchId));
				resultResponse = ResultResponse
						.builder()
						.resultList(accountSSGroupMaster)
						.build();
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


		return resultResponse;
	}

	public SearchLedgerEntriesResponseDto searchJournalEntries(SearchLedgerEntriesDto searchLedgerEntriesDto, String branchId) {
		SearchLedgerEntriesResponseDto result = SearchLedgerEntriesResponseDto.builder().build();

		try {
			List<VoucherEntrytransactions> voucherTransactions = new ArrayList<VoucherEntrytransactions>();
			String accountDetails = DataUtil.emptyString(searchLedgerEntriesDto.getAccountDetails());
			String[] accountIdName = accountDetails.split(":");
			int accountId = DataUtil.parseInt(DataUtil.emptyString(accountIdName[0]));
			String fromDate = DataUtil.dateFromatConversionSlash(searchLedgerEntriesDto.getFromDate());
			String toDate = DataUtil.dateFromatConversionSlash(searchLedgerEntriesDto.getToDate());
			if(branchId!=null) {

				String twoAccounts = null;

				Map<VoucherEntrytransactions,String> voucherMap = new LinkedHashMap<>();
				int financialYearId = accountDao.getCurrentFinancialYear(Integer.parseInt(branchId)).getFinancialid();
				voucherTransactions = accountDao.getVoucherEntryTransactionsBetweenDates(fromDate, toDate, accountId, Integer.parseInt(branchId));

				for (VoucherEntrytransactions voucherEntry : voucherTransactions) {

					if(voucherEntry.getDraccountid() != accountId) {
						twoAccounts = accountDao.getAccountName(voucherEntry.getDraccountid())+":Dr";
					}else if(voucherEntry.getCraccountid() != accountId) {
						twoAccounts = accountDao.getAccountName(voucherEntry.getCraccountid())+":Cr";
					}
					//twoAccounts = accountDao.getAccountName(voucherEntry.getDraccountid())+"--"+accountDao.getAccountName(voucherEntry.getCraccountid());
					voucherMap.put(voucherEntry, twoAccounts);
				}

				result.setLedgerTransaction(voucherMap);
				result.setAccountId(accountDetails);
				result.setLedgerName(accountIdName[1]);
				result.setFromDate(searchLedgerEntriesDto.getFromDate());
				result.setToDate(searchLedgerEntriesDto.getToDate());
				result.setSuccess(true);
				return result;
			}
			result.setSuccess(false);
			return result;
		}catch (Exception e) {
			log.error("Error in printSearchJournalEntries: ", e);
			result.setSuccess(false);
			result.setVoucherType("An error occurred while processing the request: " + e.getMessage());
		}
		return result;
	}


	public ResultResponse getAllLedgers(String branchId) {

		List<Accountdetails> accountDetails = accountDao.getLedgerAccountdetails(Integer.parseInt(branchId));

		return ResultResponse.builder()
				.success(true)
				.resultList(accountDetails)
				.build();
	}


	public IncomeStatementResponseDto getIncomeStatement(IncomeStatementDto incomeStatementDto, String strBranchId) {
		IncomeStatementResponseDto result = IncomeStatementResponseDto.builder().build();

		String fromDate = DataUtil.dateFromatConversionDash(DataUtil.emptyString(incomeStatementDto.getFromDate()));
		String toDate = DataUtil.dateFromatConversionDash(DataUtil.emptyString(incomeStatementDto.getToDate()));

		if(strBranchId!=null) {

					int branchId = Integer.parseInt(strBranchId);

				List<Accountdetails> accountsDetails = accountDao.getAccountdetailsIncomeExpense(branchId);

				//Group 1
				BigDecimal totalIncome = BigDecimal.ZERO;
				List<LedgerAccBalanceDto> incomeLedgersAccount = new ArrayList<>();


				//Group 2
				BigDecimal totalExpense = BigDecimal.ZERO;
				List<LedgerAccBalanceDto> expenseLedgersAccount = new ArrayList<>();

				for (Accountdetails accountDetails : accountsDetails) {

					List<VoucherEntrytransactions> voucherTransactions = accountDao.getVoucherEntryTransactionsBetweenDates(fromDate, toDate, accountDetails.getAccountdetailsid(), Integer.parseInt(strBranchId));

					if(!voucherTransactions.isEmpty()) {

						BigDecimal totalAmount = getTotalBalance(accountDetails,voucherTransactions);

						int groupId = accountDetails.getAccountGroupMaster().getAccountgroupid();

						switch(groupId){

						case 4:
								totalIncome = totalIncome.add(totalAmount);
								incomeLedgersAccount.add(LedgerAccBalanceDto.builder()
										.accountdetails(accountDetails)
										.balance(totalAmount)
									.build());
								break;
						case 5:
								totalExpense = totalExpense.add(totalAmount);
								expenseLedgersAccount.add(LedgerAccBalanceDto.builder()
										.accountdetails(accountDetails)
										.balance(totalAmount)
									.build());
								break;
						default:

						}

						}
					}
		//group 1
		result.setIncome(totalIncome);
		result.setIncomeLedgersAccount(incomeLedgersAccount);

		//group 2
		result.setExpenses(totalExpense);
		result.setExpenseLedgersAccount(expenseLedgersAccount);

		result.setIncomeTotalLabel("TOTAL");
		result.setExpenseTotalLabel("TOTAL");
		result.setIncomeTotal(totalIncome);
		result.setExpenseTotal(totalExpense);

		result.setFromDate(fromDate);
		result.setToDate(toDate);


		BigDecimal profit = totalIncome.subtract(totalExpense);

		if(profit.compareTo(BigDecimal.ZERO) > 0){
			result.setProfitLabel("Net Profit");
			result.setTotalProfit(profit);
		}else if(profit.compareTo(BigDecimal.ZERO) < 0){
			result.setLossLabel("Net Loss");
			result.setTotalLoss(profit.negate());
		}


	}
		result.setSuccess(true);
		return result;
	}
	
	public SearchJournalEntriesResponseDto printSearchJournalEntries(PrintSearchJournalEntriesDto printSearchJournalEntriesDto, String branchId) {
		SearchJournalEntriesResponseDto result = SearchJournalEntriesResponseDto.builder().build();

		try {
			List<VoucherEntrytransactions> voucherTransactions = new ArrayList<VoucherEntrytransactions>();
			String accountDetails = DataUtil.emptyString(printSearchJournalEntriesDto.getAccountDetails());
			String[] accountIdName = accountDetails.split(":");
			int accountId = DataUtil.parseInt(DataUtil.emptyString(accountIdName[0]));
			String fromDate = DateUtil.dateFromatConversionSlash(DataUtil.emptyString(printSearchJournalEntriesDto.getFromDate()));
			String toDate = DateUtil.dateFromatConversionSlash(DataUtil.emptyString(printSearchJournalEntriesDto.getToDate()));

			if (branchId != null) {

				String twoAccounts = null;

				Map<VoucherEntrytransactions, String> voucherMap = new LinkedHashMap<VoucherEntrytransactions, String>();
				int financialYearId = accountDao
						.getCurrentFinancialYear(Integer.parseInt((branchId)))
						.getFinancialid();
				voucherTransactions = accountDao.getVoucherEntryTransactionsBetweenDates(fromDate, toDate, accountId,
                        Integer.parseInt(branchId));

				for (VoucherEntrytransactions voucherEntry : voucherTransactions) {

					if (voucherEntry.getDraccountid() != accountId) {
						twoAccounts = accountDao.getAccountName(voucherEntry.getDraccountid()) + ":Dr";
					} else if (voucherEntry.getCraccountid() != accountId) {
						twoAccounts = accountDao.getAccountName(voucherEntry.getCraccountid()) + ":Cr";
					}
					// twoAccounts = new
					// AccountDAO().getAccountName(voucherEntry.getDraccountid())+"--"+new
					// AccountDAO().getAccountName(voucherEntry.getCraccountid());
					voucherMap.put(voucherEntry, twoAccounts);
				}

				result.setLedgerTransaction(voucherMap);
				result.setLedgerName(accountIdName[1]);
				result.setSuccess(true);
				return result;
			}
			result.setSuccess(false);
			return result;
		}catch (Exception e) {
			log.error("Error in cancelCheque: ", e);
			result.setSuccess(false);
			result.setMessage("An error occurred while processing the request: " + e.getMessage());
		}
		return  result;
	}
	
	
	public ResultResponse exportTrialBalance(ExportTrialBalanceDto exportTrialBalanceDto) {
		ResultResponse result = ResultResponse.builder().build();
		
		DecimalFormat df = new DecimalFormat("###.##");
		Map<String, TrailBalanceDto> trailBalanceDto = exportTrialBalanceDto.getTrailBalanceDto();

		String creditAllAcc = exportTrialBalanceDto.getCreditAllAcc();
		String debitAllAcc = exportTrialBalanceDto.getDebitAllAcc();
		String fromDate = exportTrialBalanceDto.getFromDate();
		String toDate = exportTrialBalanceDto.getToDate();
		
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
			
			for (Entry<String, TrailBalanceDto> accBal : trailBalanceDto.entrySet()) {

				String dr = "";
				String cr = "";
				Accountdetails key = accBal.getValue().getAccountDetails();
				BigDecimal value = accBal.getValue().getAmount();
				
						
				if(key.getAccountGroupMaster().getAccountgroupid() == 1 || key.getAccountGroupMaster().getAccountgroupid() == 5) {
					
					if(value.compareTo(BigDecimal.ONE)==0 || value.compareTo(BigDecimal.ONE)==1) {
						dr = df.format(value);
						
					}else if(value.compareTo(BigDecimal.ONE)<1) {
						cr = df.format(value.negate());
					}
				}else if(key.getAccountGroupMaster().getAccountgroupid() == 2 || key.getAccountGroupMaster().getAccountgroupid() == 3 || key.getAccountGroupMaster().getAccountgroupid() == 4) {
					
					if(value.compareTo(BigDecimal.ONE)==0 || value.compareTo(BigDecimal.ONE)==1) {
						cr = df.format(value);
						
					}else if(value.compareTo(BigDecimal.ONE)<1) {
						dr = df.format(value.negate());
					}
				}
				
				data.put(Integer.toString(i),
						new Object[] { DataUtil.emptyString(key.getAccountname()),  dr ,
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
				result.setSuccess(true);
				
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
		// getFile(name, path);
	}


	public ResultResponse downloadTrialBalance() {
		ResultResponse result = ResultResponse.builder().build();
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
			result.setSuccess(true);
		} catch (Exception e) {
			System.out.println(""+e);
		}
		return result;
	}
	
public SearchSingleLedgerEntriesResponseDto searchSingleLedgerEntries(String accountIds, String branchId, String ledgerName) {
		
		List<VoucherEntrytransactions> voucherTransactions = new ArrayList<VoucherEntrytransactions>();
		int accountId = DataUtil.parseInt(accountIds);
		if(branchId!=null) {

		String twoAccounts = null;
		
		Map<VoucherEntrytransactions,String> voucherMap = new LinkedHashMap<VoucherEntrytransactions, String>();
		Financialaccountingyear finYear = accountDao.getCurrentFinancialYear(Integer.parseInt(branchId));
		int financialYearId = finYear.getFinancialid();
		voucherTransactions = accountDao.getVoucherEntryTransactionsBetweenDates(DateUtil.dateParseryyyymmdd(finYear.getFinancialstartdate()), DateUtil.dateParseryyyymmdd(finYear.getFinancialenddate()), accountId, Integer.parseInt(branchId));
		
		for (VoucherEntrytransactions voucherEntry : voucherTransactions) {
			
			if(voucherEntry.getDraccountid() != accountId) {
				twoAccounts = accountDao.getAccountName(voucherEntry.getDraccountid())+":Dr";
			}else if(voucherEntry.getCraccountid() != accountId) {
				twoAccounts = accountDao.getAccountName(voucherEntry.getCraccountid())+":Cr";
			}
			//twoAccounts = accountDao.getAccountName(voucherEntry.getDraccountid())+"--"+accountDao.getAccountName(voucherEntry.getCraccountid());
			voucherMap.put(voucherEntry, twoAccounts);
		}

		return SearchSingleLedgerEntriesResponseDto
				.builder()
				.ledgerTransaction(voucherMap)
				.accountId(accountIds)
				.ledgerName(ledgerName)
				.fromDate(DateUtil.dateParserddMMYYYY(finYear.getFinancialstartdate()))
				.toDate(DateUtil.dateParserddMMYYYY(finYear.getFinancialstartdate()))
				.success(true)
				.build();
	
		}
		return SearchSingleLedgerEntriesResponseDto
				.builder()
				.success(false)
				.build();
	}

	public VoucherPrintResponseDto viewVouchersPrint(VoucherPrintDto voucherPrintDto, String branchId){

		String nextVoucher = DataUtil.emptyString(voucherPrintDto.getNextVoucher());

		if(nextVoucher.equalsIgnoreCase("Receipt")){
			VoucherPrintResponseDto voucherPrintResponseDto = viewVouchersPrint(voucherPrintDto, 1, branchId);
			if(voucherPrintResponseDto.isSuccess()){
				voucherPrintResponseDto.setVoucherType(nextVoucher);
				return voucherPrintResponseDto;
				//receiptdetails
			}

		}else if(nextVoucher.equalsIgnoreCase("Payment")){
			VoucherPrintResponseDto voucherPrintResponseDto = viewVouchersPrint(voucherPrintDto, 2, branchId);
			if(voucherPrintResponseDto.isSuccess()){
				voucherPrintResponseDto.setVoucherType(nextVoucher);
				return voucherPrintResponseDto;
			}

		}else if(nextVoucher.equalsIgnoreCase("Contra")){
			VoucherPrintResponseDto voucherPrintResponseDto = viewVouchersPrint(voucherPrintDto, 3, branchId);
			if(voucherPrintResponseDto.isSuccess()){
				voucherPrintResponseDto.setVoucherType(nextVoucher);
				return voucherPrintResponseDto;
			}

		}else if(nextVoucher.equalsIgnoreCase("Journal")){
			VoucherPrintResponseDto voucherPrintResponseDto = viewVouchersPrint(voucherPrintDto, 3, branchId);
			if(voucherPrintResponseDto.isSuccess()){
				voucherPrintResponseDto.setVoucherType(nextVoucher);
				return voucherPrintResponseDto;
			}
		}
		return VoucherPrintResponseDto.builder().build();
	}

	public VoucherPrintResponseDto viewVouchersPrint(VoucherPrintDto voucherPrintDto, int voucherType, String branchId) {

		List<VoucherEntrytransactions> voucherTransactions = new ArrayList<VoucherEntrytransactions>();
		String fromDate = DataUtil.dateFromatConversionSlash(DataUtil.emptyString(voucherPrintDto.getFromDate()));
		String toDate = DataUtil.dateFromatConversionSlash(DataUtil.emptyString(voucherPrintDto.getToDate()));

		if(branchId!=null) {

			String twoAccounts = null;

			Map<VoucherEntrytransactions,String> voucherMap = new LinkedHashMap<VoucherEntrytransactions, String>();
			int financialYearId = accountDao.getCurrentFinancialYear(Integer.parseInt(branchId)).getFinancialid();
			voucherTransactions = accountDao.getVoucherEntryTransactions(fromDate, toDate, financialYearId, Integer.parseInt(branchId), voucherType);

			for (VoucherEntrytransactions voucherEntry : voucherTransactions) {
				twoAccounts = accountDao.getAccountName(voucherEntry.getDraccountid())+"--"+accountDao.getAccountName(voucherEntry.getCraccountid());
				voucherMap.put(voucherEntry, twoAccounts);
			}

			return VoucherPrintResponseDto
					.builder()
					.voucherTransactions(voucherMap)
					.fromDateSelected(voucherPrintDto.getFromDate())
					.toDateSelected(voucherPrintDto.getToDate())
					.success(true)
					.build();

		}

		return VoucherPrintResponseDto
				.builder()
				.success(false)
				.build();
	}




	public ResultResponse exportVoucher(ExportVoucherDto exportVoucherDto, String branchId){
		String nextVoucher = DataUtil.emptyString(exportVoucherDto.getNextVoucher());

		if(nextVoucher.equalsIgnoreCase("Receipt")){
			ResultResponse resultResponse = exportVoucher(exportVoucherDto, 1, branchId);
			if(resultResponse.isSuccess()){
				resultResponse.setMessage(nextVoucher);
				return resultResponse;
			}

		}else if(nextVoucher.equalsIgnoreCase("Payment")){

			ResultResponse resultResponse = exportVoucher(exportVoucherDto, 2, branchId);
			if(resultResponse.isSuccess()){
				resultResponse.setMessage(nextVoucher);
				return resultResponse;
			}

		}else if(nextVoucher.equalsIgnoreCase("Contra")){

			ResultResponse resultResponse = exportVoucher(exportVoucherDto, 3, branchId);
			if(resultResponse.isSuccess()){
				resultResponse.setMessage(nextVoucher);
				return resultResponse;
			}

		}else if(nextVoucher.equalsIgnoreCase("Journal")){

			ResultResponse resultResponse = exportVoucher(exportVoucherDto, 4, branchId);
			if(resultResponse.isSuccess()){
				resultResponse.setMessage(nextVoucher);
				return resultResponse;
			}
		}
		return ResultResponse
				.builder()
				.success(false)
				.build();
	}

	public ResultResponse exportVoucher(ExportVoucherDto exportVoucherDto, int voucherType, String branchId) {

		boolean writeSucees = false;
		DecimalFormat df = new DecimalFormat("###.##");

		List<VoucherEntrytransactions> voucherTransactions = new ArrayList<VoucherEntrytransactions>();
		String fromDate = DataUtil.dateFromatConversionSlash(DataUtil.emptyString(exportVoucherDto.getFromDate()));
		String toDate = DataUtil.dateFromatConversionSlash(DataUtil.emptyString(exportVoucherDto.getToDate()));

		if(branchId!=null) {

			BigDecimal total = BigDecimal.ZERO;
			String twoAccounts = null;

			Map<VoucherEntrytransactions,String> voucherMap = new LinkedHashMap<VoucherEntrytransactions, String>();
			int financialYearId = accountDao.getCurrentFinancialYear(Integer.parseInt(branchId)).getFinancialid();
			voucherTransactions = accountDao.getVoucherEntryTransactions(fromDate, toDate, financialYearId, Integer.parseInt(branchId), voucherType);

			for (VoucherEntrytransactions voucherEntry : voucherTransactions) {
				twoAccounts = accountDao.getAccountName(voucherEntry.getDraccountid())+"--"+accountDao.getAccountName(voucherEntry.getCraccountid());
				voucherMap.put(voucherEntry, twoAccounts);
			}

			try {

				// Creating an excel file
				XSSFWorkbook workbook = new XSSFWorkbook();
				XSSFSheet sheet = workbook.createSheet("vouchertransactions");
				Map<String, Object[]> data = new LinkedHashMap<String, Object[]>();
				Map<String, Object[]> headerData = new HashMap<String, Object[]>();
				headerData.put("Header",
						new Object[] { "Voucher Transactions"});
				Map<String, Object[]> headerData1 = new HashMap<String, Object[]>();
				headerData1.put("Header",
						new Object[] { "From Date: "+fromDate+"  To Date: "+toDate+""});
				Map<String, Object[]> headerData2 = new HashMap<String, Object[]>();
				headerData2.put("Header",
						new Object[] { "Sl No.","Voucher No","Dr Account -- Cr Account","Narration","Amount"});
				//"Sl.No", "Voucher No",
				int i = 1;

				for (Entry<VoucherEntrytransactions, String> accBal : voucherMap.entrySet()) {

					//Integer.toString(i),Integer.toString(accBal.getKey().getTransactionsid()),
					data.put(Integer.toString(i),
							new Object[] { Integer.toString(i),Integer.toString(accBal.getKey().getTransactionsid()),accBal.getValue().toString(),
									accBal.getKey().getNarration(), df.format(accBal.getKey().getDramount())});
					i++;
					total = total.add(accBal.getKey().getDramount());
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
						new Object[] { "","","","Total",  df.format(total)});

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

				FileOutputStream out = new FileOutputStream(new File(System.getProperty("java.io.tmpdir")+"/vouchertransactions.xlsx"));
				workbook.write(out);
				out.close();
				workbook.close();
				ResultResponse
						.builder()
						.success(true)
						.build();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ResultResponse
				.builder()
				.success(false)
				.build();
		// getFile(name, path);
	}


	public ResultResponse downloadVoucherTransactions() {
		ResultResponse result = ResultResponse.builder().build();
		try {

			File downloadFile = new File(System.getProperty("java.io.tmpdir")+"/vouchertransactions.xlsx");
	        FileInputStream inStream = new FileInputStream(downloadFile);

	        // get MIME type of the file
			String mimeType = "application/vnd.ms-excel";

			// set content attributes for the response
			response.setContentType(mimeType);
			// response.setContentLength((int) bis.length());

			// set headers for the response
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"",
					"vouchertransactions.xlsx");
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
			result.setSuccess(true);
		} catch (Exception e) {
			System.out.println(""+e);
		}
		return result;
	}


	public DayBookResponseDto getDayBook(DayBookDto dto, String strBranchId) {

		
		String toDate = null;
		Map<VoucherEntrytransactions,String> voucherEntryTransactionsMap = new HashMap<VoucherEntrytransactions,String>();
		
		if(dto.getToDate() == null) {
			toDate = dto.getFromDate();
		}else {
			toDate = dto.getToDate();
		}
		
		
		if(strBranchId!=null) {
			
					int branchId = Integer.parseInt(strBranchId);

				List<Accountdetails> accountsDetails = new ArrayList<Accountdetails>();
				accountsDetails = accountDao.getAccountdetailsIncomeExpense(branchId);
				
				Map<Accountdetails,BigDecimal> accountBalanceMap = new LinkedHashMap<Accountdetails,BigDecimal>();
				
				//Group 1
				BigDecimal totalIncome = BigDecimal.ZERO;
				Map<Accountdetails,BigDecimal> incomeLedgersAccount = new HashMap<Accountdetails, BigDecimal>();
				
				
				//Group 2
				BigDecimal totalExpense = BigDecimal.ZERO;
				Map<Accountdetails,BigDecimal> expenseLedgersAccount = new HashMap<Accountdetails, BigDecimal>();
				
				
				
				
				List<VoucherEntrytransactions> allVoucherTransactions = accountDao.getAllVoucherEntryTransactionsBetweenDates(dto.getFromDate(), toDate, branchId);
				
				for (VoucherEntrytransactions voucherEntrytransactions : allVoucherTransactions) {
					String drAccountName = accountDao.getAccountName(voucherEntrytransactions.getDraccountid());
					String crAccountName = accountDao.getAccountName(voucherEntrytransactions.getCraccountid());
					
					voucherEntryTransactionsMap.put(voucherEntrytransactions, drAccountName+":"+crAccountName);
				}
				
	}
			return DayBookResponseDto
					.builder()
					.voucherEntryTransactions(voucherEntryTransactionsMap)
					.success(true)
					.build();
	}

	public ReceiptPaymentResponseDto getRPStatement(DayBookDto dto, String branchId) {
		ReceiptPaymentResponseDto result = ReceiptPaymentResponseDto.builder().build();
		
		
		
		if(branchId!=null) {
			
			String cashLedgerid = getLedgerAccountId("cashledgers"+Integer.parseInt(branchId));
			String bankLedgerid = getLedgerAccountId("bankledgers"+Integer.parseInt(branchId));
			String excludeIncomeLedger = getLedgerAccountId("excludeincomeledger"+Integer.parseInt(branchId));
			
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date newdate = new Date();
			String todaysDate = df.format(newdate);
			
			String[] strArray = cashLedgerid.split(":");
			List<Integer> cashLedgeridRP = new ArrayList<>();
			for (String s : strArray) {
				cashLedgeridRP.add(Integer.parseInt(s));
	        }
	        
			String[] strArrayEx = bankLedgerid.split(":");
			List<Integer> bankLedgeridRP = new ArrayList<>();
			for (String s : strArrayEx) {
				bankLedgeridRP.add(Integer.parseInt(s));
	        }
			
			String[] strArrayExcludeIncome = excludeIncomeLedger.split(":");
			List<Integer> excludeIncomeLedgerRP = new ArrayList<>();
			for (String s : strArrayExcludeIncome) {
				excludeIncomeLedgerRP.add(Integer.parseInt(s));
	        }
			
				List<Accountdetails> accountsDetails = new ArrayList<Accountdetails>();
				accountsDetails = new AccountDAO().getLedgerAccountdetails(Integer.parseInt(branchId));
				
				//Group 1
				BigDecimal totalIncomeCash = BigDecimal.ZERO;
				BigDecimal totalIncomeBank = BigDecimal.ZERO;
				Map<Accountdetails,BigDecimal> incomeLedgersAccount = new HashMap<Accountdetails, BigDecimal>();
				
				
				//Group 2
				BigDecimal totalExpenseCash = BigDecimal.ZERO;
				BigDecimal totalExpenseBank = BigDecimal.ZERO;
				Map<Accountdetails,BigDecimal> expenseLedgersAccount = new HashMap<Accountdetails, BigDecimal>();
				
				
				for (Accountdetails accountDetails : accountsDetails) {
					int accountId = accountDetails.getAccountdetailsid();
					if(!excludeIncomeLedgerRP.contains(accountId)) {
					List<VoucherEntrytransactions> voucherTransactions = new AccountDAO().getVoucherEntryTransactionsBetweenDates(dto.getFromDate(), dto.getToDate(), accountDetails.getAccountdetailsid(), Integer.parseInt(branchId));
					
					if(!voucherTransactions.isEmpty()) {
					
						int groupId = accountDetails.getAccountGroupMaster().getAccountgroupid();

						switch(groupId){
						
						case 1: 
							if(cashLedgeridRP.contains(accountId) || bankLedgeridRP.contains(accountId)) {
								BigDecimal[] totalAmountIncome = getTotalBalanceCashBankIncome(accountDetails,voucherTransactions,cashLedgeridRP,bankLedgeridRP);
								totalAmountIncome[0] = totalAmountIncome[0].abs();
								totalAmountIncome[1] = totalAmountIncome[1].abs();
								totalIncomeCash = totalIncomeCash.add(totalAmountIncome[0]);
								totalIncomeBank = totalIncomeBank.add(totalAmountIncome[1]);
								break;
							}
													
						case 4: 
							    BigDecimal incomeCashBank = BigDecimal.ZERO;
								for (VoucherEntrytransactions voucherTransaction : voucherTransactions) {
									incomeCashBank = incomeCashBank.add(voucherTransaction.getCramount());
								}
								incomeLedgersAccount.put(accountDetails, incomeCashBank);
								break;
						case 5: 
								BigDecimal[] totalAmountEx = getTotalBalanceCashBankDebit(accountDetails,voucherTransactions,cashLedgeridRP,bankLedgeridRP);
								totalAmountEx[0] = totalAmountEx[0].abs();
								totalAmountEx[1] = totalAmountEx[1].abs();
								totalExpenseCash = totalExpenseCash.add(totalAmountEx[0]);
								totalExpenseBank = totalExpenseBank.add(totalAmountEx[1]);
								expenseLedgersAccount.put(accountDetails, totalAmountEx[0].add(totalAmountEx[1]));
								break;
						default:
								
						}
						
						}/*else {
							
							int groupId = accountDetails.getAccountGroupMaster().getAccountgroupid();

							switch(groupId){
							
							case 4: 
									incomeLedgersAccount.put(accountDetails, BigDecimal.ZERO);
									break;
							case 5: 
									expenseLedgersAccount.put(accountDetails, BigDecimal.ZERO);
									break;
							default:
									
							}
						}*/
					}
				}
		
		BigDecimal profit = totalIncomeCash.add(totalIncomeBank).subtract(totalExpenseCash.add(totalExpenseBank));
		
		if(profit.compareTo(BigDecimal.ZERO) > 0){
			result.setProfitLabel("Net Profit");
			result.setTotalProfit(profit);
		}else if(profit.compareTo(BigDecimal.ZERO) < 0){
			result.setProfitLabel("Net Loss");
			result.setTotalProfit(profit.negate());
		}
		
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
			
			
			
			
			List<VoucherEntrytransactions> voucherTransactionsCash = new AccountDAO().getVoucherEntryTransactionsBetweenDatesByIds(dto.getFromDate(), todaysDate, cashLedgeridRP, Integer.parseInt(branchId));
			
			for (VoucherEntrytransactions voucherEntrytransactions : voucherTransactionsCash) {
				int drAccount = voucherEntrytransactions.getDraccountid();
				int crAccount = voucherEntrytransactions.getCraccountid();
				
				if(cashLedgeridRP.contains(drAccount)) {
					totalDrCash = totalDrCash.add(voucherEntrytransactions.getDramount());
				}else if(cashLedgeridRP.contains(crAccount)) {
					totalCrCash = totalCrCash.add(voucherEntrytransactions.getCramount());
				}
			}
					
			List<VoucherEntrytransactions> voucherTransactionsBank = new AccountDAO().getVoucherEntryTransactionsBetweenDatesByIds(dto.getFromDate(), todaysDate, bankLedgeridRP, Integer.parseInt(branchId));
			
			for (VoucherEntrytransactions voucherEntrytransactions : voucherTransactionsBank) {
				int drAccount = voucherEntrytransactions.getDraccountid();
				int crAccount = voucherEntrytransactions.getCraccountid();
				
				if(bankLedgeridRP.contains(drAccount)) {
					totalDrBank = totalDrBank.add(voucherEntrytransactions.getDramount());
				}else if(bankLedgeridRP.contains(crAccount)) {
					totalCrBank = totalCrBank.add(voucherEntrytransactions.getCramount());
				}
			}
			
			
			
			List<VoucherEntrytransactions> voucherTransactionsCashContra = new AccountDAO().getVoucherEntryTransactionsBetweenDatesByIds(dto.getFromDate(), dto.getToDate(), cashLedgeridRP, Integer.parseInt(branchId));
			
			for (VoucherEntrytransactions voucherEntrytransactions : voucherTransactionsCashContra) {
				int drAccount = voucherEntrytransactions.getDraccountid();
				int crAccount = voucherEntrytransactions.getCraccountid();
				
				if(cashLedgeridRP.contains(drAccount) && bankLedgeridRP.contains(crAccount)) {
					totalDrCashContra = totalDrCashContra.add(voucherEntrytransactions.getDramount());
				}else if(cashLedgeridRP.contains(crAccount) && bankLedgeridRP.contains(drAccount)) {
					totalCrCashContra = totalCrCashContra.add(voucherEntrytransactions.getCramount());
					totalDrBankContra = totalDrBankContra.add(voucherEntrytransactions.getCramount());
				}
			}
			
			List<VoucherEntrytransactions> voucherTransactionsBankCreditEntries = new AccountDAO().getVoucherEntryTransactionsBetweenDatesByIds(dto.getFromDate(), dto.getToDate(), bankLedgeridRP, Integer.parseInt(branchId));
			
			for (VoucherEntrytransactions voucherEntrytransactions : voucherTransactionsBankCreditEntries) {
				int crAccount = voucherEntrytransactions.getCraccountid();
				
				if(bankLedgeridRP.contains(crAccount)) {
					totalCrBankContra = totalCrBankContra.add(voucherEntrytransactions.getDramount());
				}
			}
			
			List<Integer> accountids = new ArrayList<Integer>();
			accountids.addAll(cashLedgeridRP);
			accountids.addAll(bankLedgeridRP);
			List<Accountdetailsbalance> accountDetailsBalanceList = new AccountDAO().getAccountBalanceDetails(accountids, Integer.parseInt(branchId));
			BigDecimal cashBalance = BigDecimal.ZERO;
			BigDecimal bankBalance = BigDecimal.ZERO;
			
			for (Accountdetailsbalance accountdetailsbalance : accountDetailsBalanceList) {
				int accountId = accountdetailsbalance.getAccountDetails().getAccountdetailsid();
				
				if(cashLedgeridRP.contains(accountId)) {
					cashBalance = accountdetailsbalance.getCurrentbalance();
				}else if (bankLedgeridRP.contains(accountId)) {
					bankBalance = accountdetailsbalance.getCurrentbalance();
				}
				
			}
			
			cashBalance = cashBalance.add(totalCrCash);
			openingBalanceCash = cashBalance.subtract(totalDrCash);
			
			bankBalance = bankBalance.add(totalCrBank);
			openingBalanceBank = bankBalance.subtract(totalDrBank);

			
			BigDecimal closingDrCrCash = totalIncomeCash.subtract(totalExpenseCash).subtract(totalCrCashContra);
			closingBalanceCash = openingBalanceCash.add(closingDrCrCash).add(totalDrCashContra);
			
			BigDecimal closingDrCrBank = totalIncomeBank.subtract(totalCrBankContra);
			closingBalanceBank = openingBalanceBank.add(closingDrCrBank).add(totalDrBankContra);
			
			BigDecimal grandReceiptTotal = totalIncomeCash.add(totalIncomeBank).add(openingBalanceCash).add(openingBalanceBank);
			BigDecimal grandPaymentTotal = totalExpenseCash.add(totalExpenseBank).add(closingBalanceCash).add(closingBalanceBank);
			
			
			result.setGrandReceiptTotal(grandReceiptTotal.toString());
			result.setGrandPaymentTotal(grandPaymentTotal.toString());
			result.setOpeningBalanceCash(openingBalanceCash.toString());
			result.setClosingBalanceCash(closingBalanceCash.toString());
			result.setOpeningBalanceBank(openingBalanceBank.toString());
			result.setClosingBalanceBank(closingBalanceBank.toString()); 
			// End calculating Opening Balances
			
			//group 1
			result.setIncome(totalIncomeCash.add(totalIncomeBank));
			result.setIncomeLedgersAccount(incomeLedgersAccount);
			
			//group 2
			result.setExpenses(totalExpenseCash.add(totalIncomeBank));
			result.setExpenseLedgersAccount(expenseLedgersAccount);
			
			result.setIncomeTotalLabel("Total Income");
			result.setExpenseTotalLabel("Total Expense");
			result.setIncomeTotal(totalIncomeCash.add(totalIncomeBank));
			result.setExpenseTotal(totalExpenseCash.add(totalExpenseBank));
			
			//group 3
			result.setFromDate(dto.getFromDate());
			result.setToDate(dto.getToDate());
			result.setSuccess(true);
	}
		return result;
}
	
	private BigDecimal[] getTotalBalanceCashBankCredit(Accountdetails accountDetails, List<VoucherEntrytransactions> voucherTransactions, List<Integer> cashLedgeridRP, List<Integer> bankLedgeridRP) {
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
				
				if(cashLedgeridRP.contains(vtDrAccount)) {
					creditAccCash = creditAccCash.add(voucherTransaction.getCramount());
				}else if(bankLedgeridRP.contains(vtDrAccount)) {
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
	
	private BigDecimal[] getTotalBalanceCashBankDebit(Accountdetails accountDetails, List<VoucherEntrytransactions> voucherTransactions, List<Integer> cashLedgeridRP, List<Integer> bankLedgeridRP) {
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
				
				if(cashLedgeridRP.contains(vtCrAccount)) {
					debitAccCash = debitAccCash.add(voucherTransaction.getDramount());
				}else if(bankLedgeridRP.contains(vtCrAccount)) {
					debitAccBank = debitAccBank.add(voucherTransaction.getDramount());
				}
				
			} 
		}

		totalBalanceAccCash = debitAccCash;
		totalBalanceAccBank = debitAccBank;
		
		values[0]=totalBalanceAccCash;
		values[1]=totalBalanceAccBank;
		return values;
	
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
	
	private BigDecimal[] getTotalBalanceCashBankIncome(Accountdetails accountDetails, List<VoucherEntrytransactions> voucherTransactions, List<Integer> cashLedgeridRP, List<Integer> bankLedgeridRP) {
		BigDecimal[] values = new BigDecimal[2];
		
		BigDecimal totalBalanceAccCash = BigDecimal.ZERO;
		BigDecimal totalBalanceAccBank = BigDecimal.ZERO;
		BigDecimal debitAccCash = BigDecimal.ZERO;
		BigDecimal debitAccBank = BigDecimal.ZERO;

		for (VoucherEntrytransactions voucherTransaction : voucherTransactions) {
				
				int vtDrAccount = voucherTransaction.getDraccountid();
				int accid = accountDetails.getAccountdetailsid();
				
			if ( vtDrAccount == accid) {
				
				if(cashLedgeridRP.contains(vtDrAccount)) {
					debitAccCash = debitAccCash.add(voucherTransaction.getDramount());
				}else if(bankLedgeridRP.contains(vtDrAccount)) {
					debitAccBank = debitAccBank.add(voucherTransaction.getDramount());
				}
				
			} 
		}

		totalBalanceAccCash = debitAccCash;
		totalBalanceAccBank = debitAccBank;
		
		values[0]=totalBalanceAccCash;
		values[1]=totalBalanceAccBank;
		return values;
	
}
}
