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
import org.ideoholic.curium.util.HibernateUtil;
import org.ideoholic.curium.util.QueryUtil;
import org.ideoholic.curium.util.Session;
import org.ideoholic.curium.util.Session.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
			hibernateException.printStackTrace();;
			throw hibernateException;
		}
		return result;
	}

	@Transactional
	public Financialaccountingyear getCurrentFinancialYear(int branchId) {
		Financialaccountingyear financialYear;
		try{
			financialYear = finAccountRepo.findByActiveAndBranchid("yes", branchId);
		}catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
		}
		
		return financialYear;
	}

	@Transactional
	public List<Accountgroupmaster> getListAccountGroupMaster(int branchId) {
		List<Accountgroupmaster> accountGroupMaster;
		try{
			accountGroupMaster = accountGroupMasterRepo.findAll();
		}catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
		}
		
		return accountGroupMaster;
	}

	@Transactional
	public List<Accountsubgroupmaster> getListAccountSubGroupMaster(int accountGroupMasterId, int branchId) {
		
		List<Accountsubgroupmaster> accountSubGroupMaster;
		try{
			Accountgroupmaster accountGroupMaster= accountGroupMasterRepo.findById(accountGroupMasterId).orElse(null);
			accountSubGroupMaster = accountSubGroupMasterRepo.findByAccountGroupMasterAndBranchid(accountGroupMaster, branchId);
		}catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
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
            throw hibernateException;
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
			hibernateException.printStackTrace();;
			throw hibernateException;
		}
		return result;
		
	}

	@Transactional
	public Financialaccountingyear getFinancialAccountingYear(int branchId) {
		
		Financialaccountingyear financialYear = new Financialaccountingyear();
		try{
			financialYear = finAccountRepo.findByActiveAndBranchid("yes", branchId);
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();;
			throw hibernateException;
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
			hibernateException.printStackTrace();;
			throw hibernateException;
		}
		return result;
	}

	
	public List<Accountdetailsbalance> getAccountdetailsbalanceExBC(List<Integer> accountIds, int branchId) {
		List<Accountdetailsbalance> accountDetails;
		try{
			accountDetails = accountDetailsBalanceRepo.findAllByBranchIdAndAccountIdsIn(branchId, accountIds);
		}catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			throw hibernateException;
		}
		
		return accountDetails;
	}

	@Transactional
	public List<Accountdetailsbalance> getAccountdetailsbalance(int branchId) {

		List<Accountdetailsbalance> accountDetails = new ArrayList<Accountdetailsbalance>();

		try{
			accountDetails = accountDetailsBalanceRepo.findByBranchid(branchId);
		}catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();;
			throw hibernateException;
		}

		return accountDetails;
	}
	
	@Transactional
	public boolean deleteMultipleAccounts(List<Integer> balanceIds, List<Integer> accountdetailsIds) {
		boolean result;
		try{
			accountDetailsBalanceRepo.deleteAllById(balanceIds);
			accountDetailsRepo.deleteAllById(accountdetailsIds);
			result = true;
		} catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
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
            throw hibernateException;
		}
		return result;
	}
	
	public boolean saveVoucherwithAccUpdate(VoucherEntrytransactions transactions, String drAmount, String crAmount) {
		boolean result = false;
		try{
			voucherEntryTransactionsRepo.save(transactions);
			result = true;
		}catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
		}
		return result;
	}

	
	public List<Accountdetailsbalance> getAccountBalanceDetails(List<Integer> accountIds, int branchId) {
		
		List<Accountdetailsbalance> accountDetailsBalance = new ArrayList<Accountdetailsbalance>();

		try {
			List<Accountdetails> accountdetailslist = new ArrayList<Accountdetails>();
			for(Integer accountId : accountIds) {
				Accountdetails accountdetails= accountDetailsRepo.findById(accountId).orElse(null);
				accountdetailslist.add(accountdetails);
			}
			
			accountDetailsBalance = accountDetailsBalanceRepo.findByAccountDetailsInAndBranchid(accountdetailslist, branchId);

		}catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
		}
		
		return accountDetailsBalance;
	}

	@Transactional
	public void updateAccountCurrentBalance(BigDecimal currentBalance, Integer accountId) {
		
		try{
			Accountdetails accountdetails= accountDetailsRepo.findById(accountId).orElse(null);
			Accountdetailsbalance accountdetailsbalance = accountDetailsBalanceRepo.findByAccountDetails(accountdetails);
			accountdetailsbalance.setCurrentbalance(currentBalance);
			accountDetailsBalanceRepo.save(accountdetailsbalance);
		} catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
		}
		
	}

	@Transactional
	public List<Accountdetailsbalance> getAccountdetailsbalanceBankCash(int branchId) {
		
		List<Accountdetailsbalance> accountDetails = new ArrayList<Accountdetailsbalance>();
		
		try{
			accountDetails = accountDetailsBalanceRepo.findBankCashAccountDetailsByBranch(branchId);
		}catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
		
		}
		return accountDetails;
	}

	@Transactional
	public List<VoucherEntrytransactions> getVoucherEntryTransactions(String fromDate, String toDate, Integer financialYear, int branchId, int voucherType) {
		
		List<VoucherEntrytransactions> voucherEntrytransactions = new ArrayList<VoucherEntrytransactions>();
		try{
			Date fromdate = DateUtil.indiandateParser(fromDate);
			Date todate = DateUtil.indiandateParser(toDate);
			voucherEntrytransactions = voucherEntryTransactionsRepo.findVoucherEntries(fromdate, todate, financialYear, branchId, voucherType);
		} catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
		}	
		return voucherEntrytransactions;
	}
	
	public List<VoucherEntrytransactions> getCancelledVoucherEntryTransactions(Integer financialYear, int branchId) {
		
		List<VoucherEntrytransactions> voucherEntrytransactions = new ArrayList<VoucherEntrytransactions>();
		try{
			voucherEntrytransactions = voucherEntryTransactionsRepo.findCancelledVoucherEntryTransactions(financialYear, branchId);
		} catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
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
            throw hibernateException;
		}	
		return voucherEntrytransactions;
	}

	@Transactional
	public String getAccountName(Integer accountid) {
		Accountdetails accountDetails = new Accountdetails();
		String accountName = null;
		try{
			accountDetails = accountDetailsRepo.findById(accountid).orElse(null);
			accountName = accountDetails.getAccountname();
		} catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
		}
		return accountName;
	}


	@Transactional
	public boolean checkInTransactions(Integer accountId) {
		boolean result = false;
		VoucherEntrytransactions rTransactions = new VoucherEntrytransactions();
		try{
			result = voucherEntryTransactionsRepo.existsByDraccountidOrCraccountid(accountId);
		} catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
		}
		return result;
	}

	public boolean deleteMultipleAccounts(Integer balanceId, Integer accountId) {
		
		boolean result = false;
		try{
			accountDetailsBalanceRepo.deleteById(balanceId);
			accountDetailsRepo.deleteById(accountId);
			result = true;
		} catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
		}
		
		return result;
	}

	public VoucherEntrytransactions getVoucherDetails(String id) {
		
		VoucherEntrytransactions voucherTransactions = new VoucherEntrytransactions();
		try{
			int vid = Integer.parseInt(id);
			voucherTransactions = voucherEntryTransactionsRepo.findByTransactionsid(vid);
		} catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
		}
		return voucherTransactions;
	}

	@Transactional
	public boolean updateAccountsWithVoucherCancel(String updateDrAccount, String updateCrAccount, String cancelVoucher) {
		boolean result = false;
		try{
			
			    queryUtil.runUpdateQuery(updateDrAccount);

			    queryUtil.runUpdateQuery(updateCrAccount);

			    queryUtil.runUpdateQuery(cancelVoucher);

	            result= true;
				
		} catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
		}
		return result;
	}

	@Transactional
	public boolean cancelVoucher(String id) {
		boolean result = false;
		try{
			 int transactionId = Integer.parseInt(id);
			voucherEntryTransactionsRepo.cancelVoucher(transactionId);
			result = true;
		} catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
		}
		
		return result;
	}

	public Accountdetails getAccountDetails(int accountid) {
		Accountdetails accountDetails = new Accountdetails();
		try{
			accountDetails = accountDetailsRepo.findById(accountid).orElse(null);
		} catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
		}
		return accountDetails;
	}

	@Transactional
	public Accountdetails checkAccountDetails(String accountName, String accountCode, int branchId) {
		Accountdetails accountDetails = new Accountdetails();
		try{
			accountDetails = accountDetailsRepo.findAccountDetails(accountName, accountCode, branchId);
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();;
			throw hibernateException;
		}
		return accountDetails;
	}

	public List<Accountdetails> getAccountdetails(int branchId) {
		List<Accountdetails> accountDetails = new ArrayList<Accountdetails>();
		try{
			accountDetails = accountDetailsRepo.findByBranchidOrderByAccountcodeAsc(branchId);
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();;
			throw hibernateException;
		}
		return accountDetails;
	}

	@Transactional
	public List<Accountssgroupmaster> getListAccountSSGroupMaster(int accountSubGroupMasterId, int branchId) {
		
		List<Accountssgroupmaster> accountSubGroupMaster = new ArrayList<Accountssgroupmaster>();
		
		try{
			accountSubGroupMaster = accountssgroupmasterRepository.findBySubgroupmasteridAndBranchid(accountSubGroupMasterId, branchId);
		}catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();;
			throw hibernateException;
		}
		
		return accountSubGroupMaster;
	}

	@Transactional
	public Accountssgroupmaster createSSGroup(Accountssgroupmaster accountSSGroupMaster) {
		try{
			accountssgroupmasterRepository.save(accountSSGroupMaster);
		}catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();;
			throw hibernateException;
		}
		return accountSSGroupMaster;
	}

	@Transactional
	public List<Accountdetails> getLedgerAccountdetails(int branchId) {
		
		List<Accountdetails> accountDetails = new ArrayList<Accountdetails>();
		
		try{
			accountDetails = accountDetailsRepo.findByBranchid(branchId);
		}catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();;
			throw hibernateException;
		}
		return accountDetails;
	}

	@Transactional
	public List<Accountdetails> getAccountdetailsIncomeExpense(int branchId) {
		
		List<Accountdetails> accountDetails = new ArrayList<Accountdetails>();
		try{
			accountDetails =  accountDetailsRepo.findIncomeAndExpenseAccountsByBranchId(branchId);
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();;
			throw hibernateException;
		}
		return accountDetails;
	}
	
	@Transactional
	public List<VoucherEntrytransactions> getVoucherDetailsByNarration(String supplierreferenceno) {
		
		List<VoucherEntrytransactions> voucherTransactions = new ArrayList<VoucherEntrytransactions>();
		
		try{
			voucherTransactions = voucherEntryTransactionsRepo.findByNarrationLike(supplierreferenceno);
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();;
			throw hibernateException;		
			}
		return voucherTransactions;
	}

	@Transactional
	public List<VoucherEntrytransactions> getAllVoucherEntryTransactionsBetweenDates(String fromDate, String toDate, int branchId) {
		List<VoucherEntrytransactions> voucherEntrytransactions = new ArrayList<VoucherEntrytransactions>();

		try {

			Date fromdate = DateUtil.datePars(fromDate);
			Date todate = DateUtil.datePars(toDate);
			voucherEntrytransactions = voucherEntryTransactionsRepo.findByAllVoucherEntryTransactionsBetweenDates(fromdate,todate,branchId);
		} catch (Exception hibernateException) {
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();;
			throw hibernateException;

		}
		return voucherEntrytransactions;
	}

}
