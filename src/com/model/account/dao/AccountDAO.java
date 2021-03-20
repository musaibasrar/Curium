package com.model.account.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;

import com.model.account.dto.Accountdetails;
import com.model.account.dto.Accountdetailsbalance;
import com.model.account.dto.Accountgroupmaster;
import com.model.account.dto.Accountssgroupmaster;
import com.model.account.dto.Accountsubgroupmaster;
import com.model.account.dto.Financialaccountingyear;
import com.model.account.dto.VoucherEntrytransactions;
import com.util.HibernateUtil;
import com.util.Session;
import com.util.Session.Transaction;

public class AccountDAO {
	Session session = null;
	/**
	 * * Hibernate Session Variable
	 */
	Transaction transaction = null;
	/**
	 * * Hibernate Transaction Variable
	 */
	Transaction transaction1;
	private static final Logger logger = LogManager.getLogger(AccountDAO.class);

	public AccountDAO() {
		session = HibernateUtil.openCurrentSession();
	}

	@SuppressWarnings("finally")
	public boolean create(Financialaccountingyear financialaccountingyear, int branchId) {
		boolean result = false;
		Financialaccountingyear financialYear = new Financialaccountingyear();
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Financialaccountingyear where active='yes' and branchid="+branchId);
			financialYear = (Financialaccountingyear) query.uniqueResult();
			if(financialYear!=null && financialYear.getActive().equalsIgnoreCase(financialaccountingyear.getActive())){
				financialYear.setActive("no");
				session.update(financialYear);
			}
			session.save(financialaccountingyear);
			transaction.commit();
			result = true;
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			System.out.println(""+hibernateException);
			
		} finally {
			HibernateUtil.closeSession();
		}
		return result;
	}

	public Financialaccountingyear getCurrentFinancialYear(int branchId) {
		Financialaccountingyear financialYear = new Financialaccountingyear();
		try{
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("from Financialaccountingyear where active = 'yes' and branchid="+branchId);
			financialYear = (Financialaccountingyear) query.uniqueResult();
			transaction.commit();
		}catch (Exception hb) {  transaction.rollback(); logger.error(hb);
			System.out.println("error "+hb);
		}finally {
			HibernateUtil.closeSession();
		}
		
		return financialYear;
	}

	@SuppressWarnings("unchecked")
	public List<Accountgroupmaster> getListAccountGroupMaster(int branchId) {
		List<Accountgroupmaster> accountGroupMaster = new ArrayList<Accountgroupmaster>();
		
		try{
			transaction = session.beginTransaction();
			accountGroupMaster = session.createQuery("from Accountgroupmaster").list();
			transaction.commit();
		}catch (Exception hb) { transaction.rollback(); logger.error(hb);
			hb.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		
		return accountGroupMaster;
	}

	public List<Accountsubgroupmaster> getListAccountSubGroupMaster(int accountGroupMasterId, int branchId) {
		
		List<Accountsubgroupmaster> accountSubGroupMaster = new ArrayList<Accountsubgroupmaster>();
		
		try{
			transaction = session.beginTransaction();
			accountSubGroupMaster = session.createQuery("from Accountsubgroupmaster where accountgroupid = '"+accountGroupMasterId+"' and branchid ="+branchId).list();
			transaction.commit();
		}catch (Exception hb) { transaction.rollback(); logger.error(hb);
			hb.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		
		return accountSubGroupMaster;
	}

	public Accountsubgroupmaster createSubGroup(Accountsubgroupmaster accountSubGroupMaster) {
		try{
			transaction = session.beginTransaction();
			session.save(accountSubGroupMaster);
			transaction.commit();
		}catch (Exception hb) {  transaction.rollback(); logger.error(hb);
			hb.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return accountSubGroupMaster;
	}

	public String saveNewAccount(Accountdetails accountDetails, Accountdetailsbalance accountDetailsBalance) {
		String result = "false";
		try{
			transaction = session.beginTransaction();
			session.save(accountDetails);
			session.save(accountDetailsBalance);
			transaction.commit();
			result = "true";
		}catch (Exception hb) { transaction.rollback(); logger.error(hb);
			hb.printStackTrace();
			System.out.println("error "+hb);
		}finally {
			HibernateUtil.closeSession();
		}
		return result;
		
	}

	public Financialaccountingyear getFinancialAccountingYear(int branchId) {
		
		Financialaccountingyear financialYear = new Financialaccountingyear();
		
		try {
			transaction = session.beginTransaction();
			financialYear = (Financialaccountingyear) session.createQuery("from Financialaccountingyear where active='yes' and branchId="+branchId).uniqueResult();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		
		return financialYear;
	}

	public String saveAccountBalance(Accountdetailsbalance accountDetailsBalance) {
		String result = "false";
		try{
			transaction = session.beginTransaction();
			session.save(accountDetailsBalance);
			transaction.commit();
			result = "true";
		}catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return result;
	}

	public List<Accountdetailsbalance> getAccountdetailsbalanceExBC(List<Integer> accountIds, int branchId) {

		List<Accountdetailsbalance> accountDetails = new ArrayList<Accountdetailsbalance>();
		
		try{
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Accountdetailsbalance as accdetails where accdetails.accountDetails.accountGroupMaster.accountgroupid IN (:accountIds) and branchid="+branchId);
			query.setParameterList("accountIds", accountIds);
			accountDetails = query.getResultList();
			transaction.commit();
		}catch (Exception hb) { transaction.rollback(); logger.error(hb);
			hb.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		
		return accountDetails;
	}

	public List<Accountdetailsbalance> getAccountdetailsbalance(int branchId) {

		List<Accountdetailsbalance> accountDetails = new ArrayList<Accountdetailsbalance>();
		
		try{
			transaction = session.beginTransaction();
			accountDetails = session.createQuery("from Accountdetailsbalance where branchid="+branchId).list();
			transaction.commit();
		}catch (Exception hb) {  transaction.rollback(); logger.error(hb);
			hb.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		
		return accountDetails;
	}
	
	public boolean deleteMultipleAccounts(List<Integer> balanceIds, List<Integer> accountdetailsIds) {
		
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("delete from Accountdetailsbalance where accountdetailsbalanceid IN (:balanceids)");
			query.setParameterList("balanceids", balanceIds);
			Query query2 = session.createQuery("delete from Accountdetails where accountdetailsid IN (:accountids)");
			query2.setParameterList("accountids", accountdetailsIds);
			query.executeUpdate();
			query2.executeUpdate();
			transaction.commit();
			return true;
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		
		return false;
	}

	public boolean saveVoucher(VoucherEntrytransactions transactions) {
		
		try{
			transaction = session.beginTransaction();
			session.save(transactions);
			transaction.commit();
			return true;
		}catch (Exception hb) { transaction.rollback(); logger.error(hb);
			hb.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return false;
	}
	
	public boolean saveVoucherwithAccUpdate(VoucherEntrytransactions transactions, String drAmount, String crAmount) {
		
		try{
			transaction = session.beginTransaction();
			session.save(transactions);
			Query query = session.createQuery(drAmount);
			query.executeUpdate();
			Query query1 = session.createQuery(crAmount);
			query1.executeUpdate();
			transaction.commit();
			return true;
		}catch (Exception hb) { transaction.rollback(); logger.error(hb);
			hb.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return false;
	}

	
	public List<Accountdetailsbalance> getAccountBalanceDetails(List<Integer> accountIds, int branchId) {
		
		List<Accountdetailsbalance> accountDetailsBalance = new ArrayList<Accountdetailsbalance>();
		try {
			transaction = session.beginTransaction();
			
			
			Query query = session
					.createQuery("from Accountdetailsbalance where accountdetailsid IN (:ids) and branchid="+branchId);
			query.setParameterList("ids", accountIds);
			accountDetailsBalance = query.list();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		
		return accountDetailsBalance;
	}

	public void updateAccountCurrentBalance(BigDecimal currentBalance, Integer accountId) {
		
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("update Accountdetailsbalance set currentbalance='"+currentBalance+"' where accountdetailsid="+accountId);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		
	}

	public List<Accountdetailsbalance> getAccountdetailsbalanceBankCash(int branchId) {
		
		List<Accountdetailsbalance> accountDetails = new ArrayList<Accountdetailsbalance>();
		
		try{
			transaction = session.beginTransaction();
			accountDetails = session.createQuery("from Accountdetailsbalance as accdetails where accdetails.accountDetails.accountGroupMaster.accountgroupid IN (1) and branchid="+branchId).list();
			transaction.commit();																						   											
		}catch (Exception hb) { transaction.rollback(); logger.error(hb);
			hb.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return accountDetails;
	}

	public List<VoucherEntrytransactions> getVoucherEntryTransactions(Integer financialYear, int branchId, int voucherType) {
		
		List<VoucherEntrytransactions> voucherEntrytransactions = new ArrayList<VoucherEntrytransactions>();
		try {
			transaction = session.beginTransaction();
			voucherEntrytransactions = session.createQuery("from VoucherEntrytransactions where financialyear='"+financialYear+"'and cancelvoucher!='yes' and vouchertype="+voucherType+" and branchid = "+branchId+" order by transactionsid ASC").list();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}	finally {
			HibernateUtil.closeSession();
		}	
		return voucherEntrytransactions;
	}
	
	public List<VoucherEntrytransactions> getCancelledVoucherEntryTransactions(Integer financialYear, int branchId) {
		
		List<VoucherEntrytransactions> voucherEntrytransactions = new ArrayList<VoucherEntrytransactions>();
		try {
			transaction = session.beginTransaction();
			voucherEntrytransactions = session.createQuery("from VoucherEntrytransactions where financialyear='"+financialYear+"'and cancelvoucher='yes' and branchid = "+branchId+" order by transactionsid ASC").list();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}	finally {
			HibernateUtil.closeSession();
		}	
		return voucherEntrytransactions;
	}
	
	public List<VoucherEntrytransactions> getVoucherEntryTransactionsBetweenDates(String fromDate, String toDate, int accNo, int branchId) {
		List<VoucherEntrytransactions> voucherEntrytransactions = new ArrayList<VoucherEntrytransactions>();
		try {
			transaction = session.beginTransaction();
			voucherEntrytransactions = session.createQuery("from VoucherEntrytransactions where transactiondate BETWEEN '"+fromDate+"' and '"+toDate+"' and (draccountid='"+accNo+"' or craccountid='"+accNo+"') and cancelvoucher!='yes' and branchid = "+branchId+" order by transactionsid ASC").list();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}		
		return voucherEntrytransactions;
	}

	public String getAccountName(Integer accountid) {
		
		Accountdetails accountDetails = new Accountdetails();
		String accountName = null;
		try {
			transaction = session.beginTransaction();
			Query query =  session.createQuery("from Accountdetails where accountdetailsid ="+accountid);
			accountDetails = (Accountdetails) query.uniqueResult(); 
			accountName = accountDetails.getAccountname();
			transaction.commit();
			
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return accountName;
	}


	public boolean checkInTransactions(Integer accountId) {
		
		VoucherEntrytransactions rTransactions = new VoucherEntrytransactions();
		
		
		try {
			transaction = session.beginTransaction();

			Query receipt = session.createQuery("from VoucherEntrytransactions where draccountid='"+accountId+"' or craccountid='"+accountId+"'");
			rTransactions = (VoucherEntrytransactions) receipt.uniqueResult();
			transaction.commit();

			if(rTransactions != null){
				return true;
			}
			
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return false;
	}

	public boolean deleteMultipleAccounts(Integer balanceId, Integer accountId) {
		
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("delete from Accountdetailsbalance where accountdetailsbalanceid ="+balanceId);
			Query query2 = session.createQuery("delete from Accountdetails where accountdetailsid ="+accountId);
			query.executeUpdate();
			query2.executeUpdate();
			transaction.commit();
			return true;
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		
		return false;
	}

	public VoucherEntrytransactions getVoucherDetails(String id) {
		
		VoucherEntrytransactions voucherTransactions = new VoucherEntrytransactions();
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from VoucherEntrytransactions where transactionsid='"+id+"'");
			voucherTransactions = (VoucherEntrytransactions) query.uniqueResult();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return voucherTransactions;
	}

	public boolean updateAccountsWithVoucherCancel(String updateDrAccount, String updateCrAccount, String cancelVoucher) {
		
		try {
			transaction = session.beginTransaction();
			Query updateDr = session.createQuery(updateDrAccount);
			updateDr.executeUpdate();
			Query updateCr = session.createQuery(updateCrAccount);
			updateCr.executeUpdate();
			Query cancelVoucherQuery = session.createQuery(cancelVoucher);
			cancelVoucherQuery.executeUpdate();
			transaction.commit();
			return true;
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return false;
	}

	public boolean cancelVoucher(String id) {
		
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("update VoucherEntrytransactions set cancelvoucher='yes' where transactionsid="+id);
			query.executeUpdate();
			transaction.commit();
			return true;
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return false;
		
	}

	public Accountdetails getAccountDetails(int accountid) {
		Accountdetails accountDetails = new Accountdetails();
		try {
			transaction = session.beginTransaction();
			Query query =  session.createQuery("from Accountdetails where accountdetailsid ="+accountid);
			accountDetails = (Accountdetails) query.uniqueResult(); 
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return accountDetails;
	}

	public Accountdetails checkAccountDetails(String accountName, String accountCode) {
		Accountdetails accountDetails = new Accountdetails();
		try {
			transaction = session.beginTransaction();
			Query query =  session.createQuery("from Accountdetails where accountname = '"+accountName+"' or accountcode='"+accountCode+"'");
			accountDetails = (Accountdetails) query.uniqueResult(); 
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return accountDetails;
	}

	public List<Accountdetails> getAccountdetails(int branchId) {
		List<Accountdetails> accountDetails = new ArrayList<Accountdetails>();
		try {
			transaction = session.beginTransaction();
			accountDetails =  session.createQuery("from Accountdetails where branchid = "+branchId+" order by accountcode ASC").list();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return accountDetails;
	}

	public List<Accountssgroupmaster> getListAccountSSGroupMaster(int accountSubGroupMasterId, int branchId) {
		
		List<Accountssgroupmaster> accountSubGroupMaster = new ArrayList<Accountssgroupmaster>();
		
		try{
			transaction = session.beginTransaction();
			accountSubGroupMaster = session.createQuery("from Accountssgroupmaster where subgroupmasterid = '"+accountSubGroupMasterId+"' and branchid ="+branchId).list();
			transaction.commit();
		}catch (Exception hb) { transaction.rollback(); logger.error(hb);
			hb.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		
		return accountSubGroupMaster;
	}

	public Accountssgroupmaster createSSGroup(Accountssgroupmaster accountSSGroupMaster) {
		try{
			transaction = session.beginTransaction();
			session.save(accountSSGroupMaster);
			transaction.commit();
		}catch (Exception hb) {  transaction.rollback(); logger.error(hb);
			hb.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return accountSSGroupMaster;
	}

	public List<Accountdetails> getLedgerAccountdetails(int branchId) {
		
		List<Accountdetails> accountDetails = new ArrayList<Accountdetails>();
		
		try{
			transaction = session.beginTransaction();
			accountDetails = session.createQuery("from Accountdetails as accdetails where accdetails.branchid="+branchId).list();
			transaction.commit();																						   											
		}catch (Exception hb) { transaction.rollback(); logger.error(hb);
			hb.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return accountDetails;
	}

	public List<Accountdetails> getAccountdetailsIncomeExpense(int branchId) {
		
		List<Accountdetails> accountDetails = new ArrayList<Accountdetails>();
		try {
			transaction = session.beginTransaction();												  	
			accountDetails =  session.createQuery("from Accountdetails as accdetails where accdetails.accountGroupMaster.accountgroupid = 4 or accdetails.accountGroupMaster.accountgroupid = 5 and accdetails.branchid = "+branchId+" order by accountcode ASC").list();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return accountDetails;
	}

}
