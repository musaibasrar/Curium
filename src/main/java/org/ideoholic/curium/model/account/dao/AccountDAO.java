package org.ideoholic.curium.model.account.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.ideoholic.curium.model.account.dto.Accountdetails;
import org.ideoholic.curium.model.account.dto.Accountdetailsbalance;
import org.ideoholic.curium.model.account.dto.Accountgroupmaster;
import org.ideoholic.curium.model.account.dto.Accountssgroupmaster;
import org.ideoholic.curium.model.account.dto.Accountsubgroupmaster;
import org.ideoholic.curium.model.account.dto.Financialaccountingyear;
import org.ideoholic.curium.model.account.dto.VoucherEntrytransactions;
import org.ideoholic.curium.repositories.AccountDetailsBalanceRepository;
import org.ideoholic.curium.repositories.AccountDetailsRepository;
import org.ideoholic.curium.repositories.AccountGroupMasterRepository;
import org.ideoholic.curium.repositories.AccountSubGroupMasterRepository;
import org.ideoholic.curium.repositories.AccountssgroupmasterRepository;
import org.ideoholic.curium.repositories.FinancialAccountingYearRepository;
import org.ideoholic.curium.repositories.VoucherEntryTransactionsRepository;
import org.ideoholic.curium.util.DateUtil;
import org.ideoholic.curium.util.QueryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AccountDAO {
	
	@Autowired
	private FinancialAccountingYearRepository finAccountRepo;
	
	@Autowired
	private AccountDetailsRepository accountDetailsRepo;
	
	@Autowired
	private AccountDetailsBalanceRepository accountDetailsBalanceRepo;
	
	@Autowired
	private AccountGroupMasterRepository accountGroupMasterRepo;
	
	@Autowired
	private AccountSubGroupMasterRepository accountSubGroupMasterRepo;
	
	@Autowired
	private VoucherEntryTransactionsRepository voucherEntryTransactionsRepo;
	
	@Autowired
	private AccountssgroupmasterRepository accountssgroupmasterRepository;
	
	@Autowired
    private QueryUtil queryUtil;


	@Transactional
	public boolean create(Financialaccountingyear financialaccountingyear, int branchId) {
		boolean result = false;
		Financialaccountingyear financialYear = finAccountRepo.findByActiveAndBranchid("yes", branchId);
		try {
			if(financialYear!=null && financialYear.getActive().equalsIgnoreCase(financialaccountingyear.getActive())){
				financialYear.setActive("no");
				finAccountRepo.save(financialYear);
			}
			finAccountRepo.save(financialaccountingyear);
			result = true;
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return result;
	}

	@Transactional
	public Financialaccountingyear getCurrentFinancialYear(int branchId) {
		Financialaccountingyear financialYear = new Financialaccountingyear();
		try{
			financialYear = finAccountRepo.findByActiveAndBranchid("yes", branchId);
		}catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
		return financialYear;
	}

	@Transactional
	public List<Accountgroupmaster> getListAccountGroupMaster(int branchId) {
		List<Accountgroupmaster> accountGroupMaster = new ArrayList<>();
		try{
			accountGroupMaster = accountGroupMasterRepo.findAll();
		}catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
		return accountGroupMaster;
	}

	@Transactional
	public List<Accountsubgroupmaster> getListAccountSubGroupMaster(int accountGroupMasterId, int branchId) {
		
		List<Accountsubgroupmaster> accountSubGroupMaster = new ArrayList<>();
		try{
			// accountSubGroupMaster = session.createQuery("from Accountsubgroupmaster where accountgroupid = '"+accountGroupMasterId+"' and branchid ="+branchId).list();
			Accountgroupmaster accountGroupMaster= accountGroupMasterRepo.findById(accountGroupMasterId).orElse(null);
			accountSubGroupMaster = accountSubGroupMasterRepo.findByAccountGroupMasterAndBranchid(accountGroupMaster, branchId);
		}catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
		return accountSubGroupMaster;
	}

	@Transactional
	public Accountsubgroupmaster createSubGroup(Accountsubgroupmaster accountSubGroupMaster) {
		try{
			accountSubGroupMasterRepo.save(accountSubGroupMaster);
		}catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return accountSubGroupMaster;
	}

	@Transactional
	public String saveNewAccount(Accountdetails accountDetails, Accountdetailsbalance accountDetailsBalance) {
		String result = "false";
		try{
			accountDetailsRepo.save(accountDetails);
			accountDetailsBalanceRepo.save(accountDetailsBalance);
			result = "true";
		}catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return result;
		
	}

	@Transactional
	public Financialaccountingyear getFinancialAccountingYear(int branchId) {
		
		Financialaccountingyear financialYear = new Financialaccountingyear();
		try{
			// session.createQuery("from Financialaccountingyear where active='yes' and branchId="+branchId).uniqueResult();
			financialYear = finAccountRepo.findByActiveAndBranchid("yes", branchId);
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
		return financialYear;
	}

	@Transactional
	public String saveAccountBalance(Accountdetailsbalance accountDetailsBalance) {
		String result = "false";
		try{
			accountDetailsBalanceRepo.save(accountDetailsBalance);
			result = "true";
		}catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return result;
	}

	
	public List<Accountdetailsbalance> getAccountdetailsbalanceExBC(List<Integer> accountIds, int branchId) {
		List<Accountdetailsbalance> accountDetails = new ArrayList<>();
		try{
			// session.createQuery("from Accountdetailsbalance as accdetails where accdetails.accountDetails.accountGroupMaster.accountgroupid IN (:accountIds) and branchid="+branchId);
			accountDetails = accountDetailsBalanceRepo.findAllByBranchIdAndAccountIdsIn(branchId, accountIds);
		}catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
		return accountDetails;
	}

	@Transactional
	public List<Accountdetailsbalance> getAccountdetailsbalance(int branchId) {

		List<Accountdetailsbalance> accountDetails = new ArrayList<Accountdetailsbalance>();

		try{
			// session.createQuery("from Accountdetailsbalance where branchid="+branchId).list();
			accountDetails = accountDetailsBalanceRepo.findByBranchid(branchId);
		}catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}

		return accountDetails;
	}
	
	@Transactional
	public boolean deleteMultipleAccounts(List<Integer> balanceIds, List<Integer> accountdetailsIds) {
		boolean result = false;
		try{
			// session.createQuery("delete from Accountdetailsbalance where accountdetailsbalanceid IN (:balanceids)");
			accountDetailsBalanceRepo.deleteAllById(balanceIds);
			// session.createQuery("delete from Accountdetails where accountdetailsid IN (:accountids)");
			accountDetailsRepo.deleteAllById(accountdetailsIds);
			result = true;
		} catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return result;
	}

	@Transactional
	public boolean saveVoucher(VoucherEntrytransactions transactions) {
		boolean result = false;
		try{
			voucherEntryTransactionsRepo.save(transactions);
			result = true;
		}catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return result;
	}
	
	public boolean saveVoucherwithAccUpdate(VoucherEntrytransactions transactions, String drAmount, String crAmount) {
		boolean result = false;
		try{
			// Query query = session.createQuery(drAmount); query.executeUpdate();
			// Query query1 = session.createQuery(crAmount); query.executeUpdate();
			queryUtil.runUpdateQuery(drAmount);
			queryUtil.runUpdateQuery(crAmount);
			voucherEntryTransactionsRepo.save(transactions);
			result = true;
		}catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return result;
	}

	
	public List<Accountdetailsbalance> getAccountBalanceDetails(List<Integer> accountIds, int branchId) {
		
		List<Accountdetailsbalance> accountDetailsBalance = new ArrayList<Accountdetailsbalance>();

		try {
			// .createQuery("from Accountdetailsbalance where accountdetailsid IN (:ids) and branchid="+branchId);
			
			List<Accountdetails> accountdetailslist = new ArrayList<Accountdetails>();
			for(Integer accountId : accountIds) {
				Accountdetails accountdetails= accountDetailsRepo.findById(accountId).orElse(null);
				accountdetailslist.add(accountdetails);
			}
			
			accountDetailsBalance = accountDetailsBalanceRepo.findByAccountDetailsInAndBranchid(accountdetailslist, branchId);

		}catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
		return accountDetailsBalance;
	}

	@Transactional
	public void updateAccountCurrentBalance(BigDecimal currentBalance, Integer accountId) {

		try{
			// session.createQuery("update Accountdetailsbalance set currentbalance='"+currentBalance+"' where accountdetailsid="+accountId);
			Accountdetails accountdetails= accountDetailsRepo.findById(accountId).orElse(null);
			Accountdetailsbalance accountdetailsbalance = accountDetailsBalanceRepo.findByAccountDetails(accountdetails);
			accountdetailsbalance.setCurrentbalance(currentBalance);
			accountDetailsBalanceRepo.save(accountdetailsbalance);
		} catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}

	}

	@Transactional
	public List<Accountdetailsbalance> getAccountdetailsbalanceBankCash(int branchId) {

		List<Accountdetailsbalance> accountDetails = new ArrayList<Accountdetailsbalance>();

		try{
			// session.createQuery("from Accountdetailsbalance as accdetails where accdetails.accountDetails.accountSSGroupMaster.ssgroupmasterid IN (1,2) and branchid="+branchId).list();
			accountDetails = accountDetailsBalanceRepo.findBankCashAccountDetailsByBranch(branchId);
		}catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

		}
		return accountDetails;
	}

	@Transactional
	public List<VoucherEntrytransactions> getVoucherEntryTransactions(String fromDate, String toDate, Integer financialYear, int branchId, int voucherType) {
		
		List<VoucherEntrytransactions> voucherEntrytransactions = new ArrayList<VoucherEntrytransactions>();
		try{
			Date fromdate = DateUtil.indiandateParser(fromDate);
			Date todate = DateUtil.indiandateParser(toDate);
			// session.createQuery("from VoucherEntrytransactions where transactiondate BETWEEN '"+fromDate+"' and '"+toDate+"' and cancelvoucher!='yes' and vouchertype="+voucherType+" and branchid = "+branchId+" order by transactionsid ASC").list();
			voucherEntrytransactions = voucherEntryTransactionsRepo.findVoucherEntries(fromdate, todate, financialYear, branchId, voucherType);
		} catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}	
		return voucherEntrytransactions;
	}
	
	public List<VoucherEntrytransactions> getCancelledVoucherEntryTransactions(Integer financialYear, int branchId) {

		List<VoucherEntrytransactions> voucherEntrytransactions = new ArrayList<VoucherEntrytransactions>();
		try{
			// session.createQuery("from VoucherEntrytransactions where financialyear='"+financialYear+"'and cancelvoucher='yes' and branchid = "+branchId+" order by transactionsid ASC").list();
			voucherEntrytransactions = voucherEntryTransactionsRepo.findCancelledVoucherEntryTransactions(financialYear, branchId);
		} catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}	
		return voucherEntrytransactions;
	}
	
	@Transactional
	public List<VoucherEntrytransactions> getVoucherEntryTransactionsBetweenDates(String fromDate, String toDate, int accNo, int branchId) {

		List<VoucherEntrytransactions> voucherEntrytransactions = new ArrayList<VoucherEntrytransactions>();
		try{
			Date fromdate = DateUtil.datePars(fromDate);
			Date todate = DateUtil.datePars(toDate);
			//voucherEntrytransactions = session.createQuery("from VoucherEntrytransactions where transactiondate BETWEEN '"+fromDate+"' and '"+toDate+"' and (draccountid='"+accNo+"' or craccountid='"+accNo+"') and cancelvoucher!='yes' and branchid = "+branchId+" order by transactionsid ASC").list();
			voucherEntrytransactions = voucherEntryTransactionsRepo.findTransactionsBetweenDates(fromdate, todate, accNo, branchId);
		} catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}	
		return voucherEntrytransactions;
	}

	@Transactional
	public String getAccountName(Integer accountid) {
		Accountdetails accountDetails = new Accountdetails();
		String accountName = null;
		try{
			// session.createQuery("from Accountdetails where accountdetailsid ="+accountid);
			accountDetails = accountDetailsRepo.findById(accountid).orElse(null);
			accountName = accountDetails.getAccountname();
		} catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return accountName;
	}


	@Transactional
	public boolean checkInTransactions(Integer accountId) {
		boolean result = false;
		try{
			// session.createQuery("from VoucherEntrytransactions where draccountid='"+accountId+"' or craccountid='"+accountId+"'");
			result = voucherEntryTransactionsRepo.existsByDraccountidOrCraccountid(accountId);
		} catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return result;
	}

	public boolean deleteMultipleAccounts(Integer balanceId, Integer accountId) {
		
		boolean result = false;
		try{
			// session.createQuery("delete from Accountdetailsbalance where accountdetailsbalanceid ="+balanceId);
			accountDetailsBalanceRepo.deleteById(balanceId);
			// session.createQuery("delete from Accountdetails where accountdetailsid ="+accountId);
			accountDetailsRepo.deleteById(accountId);
			result = true;
		} catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
		return result;
	}

	public VoucherEntrytransactions getVoucherDetails(String id) {
		
		VoucherEntrytransactions voucherTransactions = new VoucherEntrytransactions();
		try{
			// session.createQuery("from VoucherEntrytransactions where transactionsid='"+id+"'");
			int vid = Integer.parseInt(id);
			voucherTransactions = voucherEntryTransactionsRepo.findByTransactionsid(vid);
		} catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return voucherTransactions;
	}

	@Transactional
	public boolean updateAccountsWithVoucherCancel(String updateDrAccount, String updateCrAccount, String cancelVoucher) {
		boolean result = false;
		try {
			// session.createQuery(updateDrAccount); updateDr.executeUpdate();
			queryUtil.runUpdateQuery(updateDrAccount);
			// session.createQuery(updateCrAccount); updateCr.executeUpdate();
			queryUtil.runUpdateQuery(updateCrAccount);
			// session.createQuery(cancelVoucher); cancelVoucherQuery.executeUpdate();
			queryUtil.runUpdateQuery(cancelVoucher);

			result = true;
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return result;
	}

	@Transactional
	public boolean cancelVoucher(String id) {
		boolean result = false;
		try{
			// session.createQuery("update VoucherEntrytransactions set cancelvoucher='yes' where transactionsid="+id);
			int transactionId = Integer.parseInt(id);
			voucherEntryTransactionsRepo.cancelVoucher(transactionId);
			result = true;
		} catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
		return result;
	}

	public Accountdetails getAccountDetails(int accountid) {
		Accountdetails accountDetails = new Accountdetails();
		try{
			// session.createQuery("from Accountdetails where accountdetailsid ="+accountid);
			accountDetails = accountDetailsRepo.findById(accountid).orElse(null);
		} catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return accountDetails;
	}

	@Transactional
	public Accountdetails checkAccountDetails(String accountName, String accountCode, int branchId) {
		Accountdetails accountDetails = new Accountdetails();
		try{
			// session.createQuery("from Accountdetails where (accountname = '"+accountName+"' or accountcode='"+accountCode+"') and branchid="+branchId+"");
			accountDetails = accountDetailsRepo.findAccountDetails(accountName, accountCode, branchId);
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return accountDetails;
	}

	public List<Accountdetails> getAccountdetails(int branchId) {
		List<Accountdetails> accountDetails = new ArrayList<Accountdetails>();
		try{
			// session.createQuery("from Accountdetails where branchid = "+branchId+" order by accountcode ASC").list();
			accountDetails = accountDetailsRepo.findByBranchidOrderByAccountcodeAsc(branchId);
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return accountDetails;
	}

	@Transactional
	public List<Accountssgroupmaster> getListAccountSSGroupMaster(int accountSubGroupMasterId, int branchId) {
		
		List<Accountssgroupmaster> accountSubGroupMaster = new ArrayList<Accountssgroupmaster>();
		
		try{
			// session.createQuery("from Accountssgroupmaster where subgroupmasterid = '"+accountSubGroupMasterId+"' and branchid ="+branchId).list();
			accountSubGroupMaster = accountssgroupmasterRepository.findBySubgroupmasteridAndBranchid(accountSubGroupMasterId, branchId);
		}catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
		return accountSubGroupMaster;
	}

	@Transactional
	public Accountssgroupmaster createSSGroup(Accountssgroupmaster accountSSGroupMaster) {
		try{
			accountssgroupmasterRepository.save(accountSSGroupMaster);
		}catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return accountSSGroupMaster;
	}

	@Transactional
	public List<Accountdetails> getLedgerAccountdetails(int branchId) {
		
		List<Accountdetails> accountDetails = new ArrayList<Accountdetails>();
		
		try{
			// session.createQuery("from Accountdetails as accdetails where accdetails.branchid="+branchId).list();
			accountDetails = accountDetailsRepo.findByBranchid(branchId);
		}catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return accountDetails;
	}

	@Transactional
	public List<Accountdetails> getAccountdetailsIncomeExpense(int branchId) {
		
		List<Accountdetails> accountDetails = new ArrayList<Accountdetails>();
		try{
			// session.createQuery("from Accountdetails as accdetails where accdetails.accountGroupMaster.accountgroupid = 4 or accdetails.accountGroupMaster.accountgroupid = 5 and accdetails.branchid = "+branchId+" order by accountcode ASC").list();
			accountDetails =  accountDetailsRepo.findIncomeAndExpenseAccountsByBranchId(branchId);
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return accountDetails;
	}
	
	@Transactional
	public List<VoucherEntrytransactions> getVoucherDetailsByNarration(String supplierreferenceno) {
		
		List<VoucherEntrytransactions> voucherTransactions = new ArrayList<VoucherEntrytransactions>();
		
		try{
			// session.createQuery("from VoucherEntrytransactions where narration like '%"+supplierreferenceno+"%'");
			voucherTransactions = voucherEntryTransactionsRepo.findByNarrationLike(supplierreferenceno);
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return voucherTransactions;
	}

	@Transactional
	public List<VoucherEntrytransactions> getAllVoucherEntryTransactionsBetweenDates(String fromDate, String toDate, int branchId) {
		List<VoucherEntrytransactions> voucherEntrytransactions = new ArrayList<VoucherEntrytransactions>();

		try {
			// session.createQuery("from VoucherEntrytransactions where transactiondate BETWEEN '"+fromDate+"' and '"+toDate+"' and cancelvoucher!='yes' and branchid = "+branchId+" order by transactionsid ASC").list();
			Date fromdate = DateUtil.datePars(fromDate);
			Date todate = DateUtil.datePars(toDate);
			voucherEntrytransactions = voucherEntryTransactionsRepo.findByAllVoucherEntryTransactionsBetweenDates(fromdate,todate,branchId);
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return voucherEntrytransactions;
	}

}
