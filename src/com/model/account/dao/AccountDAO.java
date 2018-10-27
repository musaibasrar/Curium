package com.model.account.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.model.account.dto.Accountdetails;
import com.model.account.dto.Accountdetailsbalance;
import com.model.account.dto.Accountgroupmaster;
import com.model.account.dto.Accountsubgroupmaster;
import com.model.account.dto.Contratransactions;
import com.model.account.dto.Financialaccountingyear;
import com.model.account.dto.Journaltransactions;
import com.model.account.dto.Paymenttransactions;
import com.model.account.dto.Receipttransactions;
import com.util.HibernateUtil;

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
		session = HibernateUtil.openSession();
	}

	@SuppressWarnings("finally")
	public boolean create(Financialaccountingyear financialaccountingyear) {
		boolean result = false;
		Financialaccountingyear financialYear = new Financialaccountingyear();
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Financialaccountingyear where active='yes'");
			financialYear = (Financialaccountingyear) query.uniqueResult();
			if(financialYear.getActive().equalsIgnoreCase(financialaccountingyear.getActive())){
				financialYear.setActive("no");
				session.update(financialYear);
			}
			session.save(financialaccountingyear);
			transaction.commit();
			result = true;
		} catch (HibernateException hibernateException) { transaction.rollback(); logger.error(hibernateException);
			System.out.println(""+hibernateException);
			
		} finally {
			session.close();
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
		}catch (HibernateException hb) {  transaction.rollback(); logger.error(hb);
			System.out.println("error "+hb);
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
		}catch (HibernateException hb) { transaction.rollback(); logger.error(hb);
			hb.printStackTrace();
		}finally{
			session.close();
		}
		
		return accountGroupMaster;
	}

	public List<Accountsubgroupmaster> getListAccountSubGroupMaster(int accountGroupMasterId, int branchId) {
		
		List<Accountsubgroupmaster> accountSubGroupMaster = new ArrayList<Accountsubgroupmaster>();
		
		try{
			transaction = session.beginTransaction();
			accountSubGroupMaster = session.createQuery("from Accountsubgroupmaster where accountgroupid = '"+accountGroupMasterId+"' and branchid ="+branchId).list();
			transaction.commit();
		}catch (HibernateException hb) { transaction.rollback(); logger.error(hb);
			hb.printStackTrace();
		}finally{
			session.close();
		}
		
		return accountSubGroupMaster;
	}

	public Accountsubgroupmaster createSubGroup(Accountsubgroupmaster accountSubGroupMaster) {
		try{
			transaction = session.beginTransaction();
			session.save(accountSubGroupMaster);
			transaction.commit();
		}catch (HibernateException hb) {  transaction.rollback(); logger.error(hb);
			hb.printStackTrace();
		}
		return accountSubGroupMaster;
	}

	public boolean saveNewAccount(Accountdetails accountDetails) {
		boolean result = false;
		try{
			transaction = session.beginTransaction();
			session.save(accountDetails);
			transaction.commit();
			result = true;
		}catch (HibernateException hb) { transaction.rollback(); logger.error(hb);
			hb.printStackTrace();
			System.out.println("error "+hb);
		}finally{
			session.close();
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
		}finally{
			session.close();
		}
		
		return financialYear;
	}

	public boolean saveAccountBalance(Accountdetailsbalance accountDetailsBalance) {
		boolean result = false;
		try{
			transaction = session.beginTransaction();
			session.save(accountDetailsBalance);
			transaction.commit();
			result = true;
		}catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return result;
	}

	public List<Accountdetailsbalance> getAccountdetailsbalanceExBC(int branchId) {

		List<Accountdetailsbalance> accountDetails = new ArrayList<Accountdetailsbalance>();
		
		try{
			transaction = session.beginTransaction();
			accountDetails = session.createQuery("from Accountdetailsbalance as accdetails where accdetails.accountDetails.accountSubGroupMaster.accountsubgroupmasterid NOT IN (1,2) and branchid="+branchId).list();
			transaction.commit();
		}catch (HibernateException hb) { transaction.rollback(); logger.error(hb);
			hb.printStackTrace();
		}finally{
			session.close();
		}
		
		return accountDetails;
	}

	public List<Accountdetailsbalance> getAccountdetailsbalance(int branchId) {

		List<Accountdetailsbalance> accountDetails = new ArrayList<Accountdetailsbalance>();
		
		try{
			transaction = session.beginTransaction();
			accountDetails = session.createQuery("from Accountdetailsbalance where branchid="+branchId).list();
			transaction.commit();
		}catch (HibernateException hb) {  transaction.rollback(); logger.error(hb);
			hb.printStackTrace();
		}finally{
			session.close();
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
		} catch (HibernateException hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}
		
		return false;
	}

	public boolean saveReceipt(Receipttransactions transactions) {
		
		try{
			transaction = session.beginTransaction();
			session.save(transactions);
			transaction.commit();
			return true;
		}catch (HibernateException hb) { transaction.rollback(); logger.error(hb);
			hb.printStackTrace();
		}finally{
			session.close();
		}
		return false;
	}

	public boolean savePayment(Paymenttransactions transactions) {

		try {
			transaction = session.beginTransaction();
			session.save(transactions);
			transaction.commit();
			return true;
		} catch (HibernateException hb) { transaction.rollback(); logger.error(hb);
			hb.printStackTrace();
		} finally {
			session.close();
		}
		return false;
	}

	public boolean saveContra(Contratransactions transactions) {

		try {
			transaction = session.beginTransaction();
			session.save(transactions);
			transaction.commit();
			return true;
		} catch (HibernateException hb) { transaction.rollback(); logger.error(hb);
			hb.printStackTrace();
		} finally {
			session.close();
		}
		return false;
	}
	
	public boolean saveJournal(Journaltransactions transactions) {

		try {
			transaction = session.beginTransaction();
			session.save(transactions);
			transaction.commit();
			return true;
		} catch (HibernateException hb) { transaction.rollback(); logger.error(hb);
			hb.printStackTrace();
		} finally {
			session.close();
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
		} catch (HibernateException hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}
		
		return accountDetailsBalance;
	}

	public void updateAccountCurrentBalance(BigDecimal currentBalance, Integer accountId) {
		
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("update Accountdetailsbalance set currentbalance='"+currentBalance+"' where accountdetailsid="+accountId);
			query.executeUpdate();
			transaction.commit();
		} catch (HibernateException hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}
		
	}

	public List<Accountdetailsbalance> getAccountdetailsbalanceBankCash(int branchId) {
		
		List<Accountdetailsbalance> accountDetails = new ArrayList<Accountdetailsbalance>();
		
		try{
			transaction = session.beginTransaction();
			accountDetails = session.createQuery("from Accountdetailsbalance as accdetails where accdetails.accountDetails.accountSubGroupMaster.accountsubgroupmasterid IN (1,2) and branchid="+branchId).list();
			transaction.commit();																						   											
		}catch (HibernateException hb) { transaction.rollback(); logger.error(hb);
			hb.printStackTrace();
		}finally{
			session.close();
		}
		
		return accountDetails;
	}

	public List<Receipttransactions> getReceiptTransactions(Integer financialYear, int branchId) {
		List<Receipttransactions> receiptTransactions = new ArrayList<Receipttransactions>();
		try {
			transaction = session.beginTransaction();
			receiptTransactions = session.createQuery("from Receipttransactions where financialyear='"+financialYear+"'and cancelvoucher!='yes' and branchid = "+branchId+" order by transactionsid ASC").list();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		return receiptTransactions;
	}

	public String getAccountName(Integer accountid) {
		Accountdetails accountDetails = new Accountdetails();
		try {
			transaction = session.beginTransaction();
			Query query =  session.createQuery("from Accountdetails where accountdetailsid ="+accountid);
			accountDetails = (Accountdetails) query.uniqueResult(); 
			transaction.commit();
			return accountDetails.getAccountname();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return null;
	}

	public List<Paymenttransactions> getPaymentTransactions(Integer financialYear, int branchId) {
		
		List<Paymenttransactions> paymentTransactions = new ArrayList<Paymenttransactions>();
		try {
			transaction = session.beginTransaction();
			paymentTransactions = session.createQuery("from Paymenttransactions where financialyear='"+financialYear+"' and branchid="+branchId+" order by transactionsid ASC ").list();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		return paymentTransactions;
	}

	public List<Contratransactions> getContraTransactions(Integer financialYear, int branchId) {
		
		List<Contratransactions> contraTransactions = new ArrayList<Contratransactions>();
		try {
			transaction = session.beginTransaction();
			contraTransactions = session.createQuery("from Contratransactions where financialyear='"+financialYear+"' and branchid="+branchId+" order by transactionsid ASC ").list();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return contraTransactions;
	}

	public List<Journaltransactions> getJournalTransactions(Integer financialYear, int branchId) {

		List<Journaltransactions> journalTransactions = new ArrayList<Journaltransactions>();
		try {
			transaction = session.beginTransaction();
			journalTransactions = session.createQuery("from Journaltransactions where financialyear='"+financialYear+"' and branchid="+branchId+" order by transactionsid ASC ").list();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return journalTransactions;
		
	}

	public boolean checkInTransactions(Integer accountId) {
		
		Receipttransactions rTransactions = new Receipttransactions();
		Paymenttransactions pTransactions = new Paymenttransactions();
		Contratransactions cTransactions = new Contratransactions();
		Journaltransactions jTransactions = new Journaltransactions();
		
		try {
			transaction = session.beginTransaction();
			// receipt
			Query receipt = session.createQuery("from Receipttransactions where draccountid='"+accountId+"' or craccountid='"+accountId+"'");
			rTransactions = (Receipttransactions) receipt.uniqueResult();
			
			if(rTransactions.getTransactionsid() != null){
				transaction.commit();
				return true;
			}
			
			// payment
			Query payment = session.createQuery("from Paymenttransactions where draccountid='"+accountId+"' or craccountid='"+accountId+"'");
			pTransactions = (Paymenttransactions) payment.uniqueResult();
			if(pTransactions.getTransactionsid() != null){
				transaction.commit();
				return true;
			}
			
			//contra 
			Query contra = session.createQuery("from Contratransactions where draccountid='"+accountId+"' or craccountid='"+accountId+"'");
			cTransactions = (Contratransactions) contra.uniqueResult();
			if(cTransactions.getTransactionsid() != null){
				transaction.commit();
				return true;
			}
			
			//Journal
			Query journal = session.createQuery("from Journaltransactions where draccountid='"+accountId+"' or craccountid='"+accountId+"'");
			jTransactions = (Journaltransactions) journal.uniqueResult();
			if(jTransactions.getTransactionsid() != null){
				transaction.commit();
				return true;
			}
			
			
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
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
		} catch (HibernateException hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}
		
		return false;
	}

	public Receipttransactions getReceiptDetails(String id) {
		
		Receipttransactions receiptTransactions = new Receipttransactions();
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Receipttransactions where transactionsid='"+id+"'");
			receiptTransactions = (Receipttransactions) query.uniqueResult();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		return receiptTransactions;
	}

	public boolean updateAccounts(Receipttransactions receiptTransaction) {
		
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("update Accountdetailsbalance set currentbalance=currentbalance-'"+receiptTransaction.getCramount()+"' where accountdetailsid="+receiptTransaction.getCraccountid());
			query.executeUpdate();
			Query query2 = session.createQuery("update Accountdetailsbalance set currentbalance=currentbalance-'"+receiptTransaction.getDramount()+"' where accountdetailsid="+receiptTransaction.getDraccountid());
			query2.executeUpdate();
			transaction.commit();
			return true;
		} catch (HibernateException hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}finally{
			session.close();
		}
		return false;
	}

	public boolean cancelReceipt(String id) {
		
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("update Receipttransactions set cancelvoucher='yes' where transactionsid="+id);
			query.executeUpdate();
			transaction.commit();
			return true;
		} catch (HibernateException hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}finally{
			session.close();
		}
		return false;
		
	}

	public Paymenttransactions getPaymentDetails(String id) {
		
		Paymenttransactions paymentTransactions = new Paymenttransactions();
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Paymenttransactions where transactionsid='"+id+"'");
			paymentTransactions = (Paymenttransactions) query.uniqueResult();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		return paymentTransactions;
	}

	public boolean updateAccountsPayment(Paymenttransactions paymentTransaction) {
		
		try {
			transaction = session.beginTransaction();
			if(paymentTransaction.getCraccountid() == 1 || paymentTransaction.getCraccountid() == 2){
				Query query = session.createQuery("update Accountdetailsbalance set currentbalance=currentbalance+'"+paymentTransaction.getCramount()+"' where accountdetailsid="+paymentTransaction.getCraccountid());
				query.executeUpdate();
			}else{
				Query query = session.createQuery("update Accountdetailsbalance set currentbalance=currentbalance-'"+paymentTransaction.getCramount()+"' where accountdetailsid="+paymentTransaction.getCraccountid());
				query.executeUpdate();
			}
			
			
			Query query2 = session.createQuery("update Accountdetailsbalance set currentbalance=currentbalance-'"+paymentTransaction.getDramount()+"' where accountdetailsid="+paymentTransaction.getDraccountid());
			query2.executeUpdate();
			transaction.commit();
			return true;
		} catch (HibernateException hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}finally{
			session.close();
		}
		return false;
		
	}

}
