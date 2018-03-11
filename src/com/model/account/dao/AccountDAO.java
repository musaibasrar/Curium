package com.model.account.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
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
		} catch (HibernateException hibernateException) {
			System.out.println(""+hibernateException);
			transaction.rollback();
		} finally {
			session.close();
		}
		return result;
	}

	public Financialaccountingyear getCurrentFinancialYear() {
		Financialaccountingyear financialYear = new Financialaccountingyear();
		try{
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("from Financialaccountingyear where active = 'yes' ");
			financialYear = (Financialaccountingyear) query.uniqueResult();
			transaction.commit();
		}catch(HibernateException hb){
			System.out.println("error "+hb);
		}
		
		return financialYear;
	}

	@SuppressWarnings("unchecked")
	public List<Accountgroupmaster> getListAccountGroupMaster() {
		List<Accountgroupmaster> accountGroupMaster = new ArrayList<Accountgroupmaster>();
		
		try{
			transaction = session.beginTransaction();
			accountGroupMaster = session.createQuery("from Accountgroupmaster").list();
			transaction.commit();
		}catch(HibernateException hb){
			hb.printStackTrace();
		}finally{
			session.close();
		}
		
		return accountGroupMaster;
	}

	public List<Accountsubgroupmaster> getListAccountSubGroupMaster(int accountGroupMasterId) {
		
		List<Accountsubgroupmaster> accountSubGroupMaster = new ArrayList<Accountsubgroupmaster>();
		
		try{
			transaction = session.beginTransaction();
			accountSubGroupMaster = session.createQuery("from Accountsubgroupmaster where accountgroupid = '"+accountGroupMasterId+"'").list();
			transaction.commit();
		}catch(HibernateException hb){
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
		}catch(HibernateException hb){
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
		}catch(HibernateException hb){
			hb.printStackTrace();
			System.out.println("error "+hb);
		}finally{
			session.close();
		}
		return result;
		
	}

	public Financialaccountingyear getFinancialAccountingYear() {
		
		Financialaccountingyear financialYear = new Financialaccountingyear();
		
		try {
			transaction = session.beginTransaction();
			financialYear = (Financialaccountingyear) session.createQuery("from Financialaccountingyear where active='yes'").uniqueResult();
			transaction.commit();
		} catch (Exception e) {
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
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return result;
	}

	public List<Accountdetailsbalance> getAccountdetailsbalanceExBC() {

		List<Accountdetailsbalance> accountDetails = new ArrayList<Accountdetailsbalance>();
		
		try{
			transaction = session.beginTransaction();
			accountDetails = session.createQuery("from Accountdetailsbalance as accdetails where accdetails.accountDetails.accountSubGroupMaster.accountsubgroupmasterid NOT IN (1,2)").list();
			transaction.commit();
		}catch(HibernateException hb){
			hb.printStackTrace();
		}finally{
			session.close();
		}
		
		return accountDetails;
	}

	public List<Accountdetailsbalance> getAccountdetailsbalance() {

		List<Accountdetailsbalance> accountDetails = new ArrayList<Accountdetailsbalance>();
		
		try{
			transaction = session.beginTransaction();
			accountDetails = session.createQuery("from Accountdetailsbalance").list();
			transaction.commit();
		}catch(HibernateException hb){
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
		} catch (HibernateException hibernateException) {
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
		}catch(HibernateException hb){
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
		} catch (HibernateException hb) {
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
		} catch (HibernateException hb) {
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
		} catch (HibernateException hb) {
			hb.printStackTrace();
		} finally {
			session.close();
		}
		return false;
	}
	
	public List<Accountdetailsbalance> getAccountBalanceDetails(List<Integer> accountIds) {
		
		List<Accountdetailsbalance> accountDetailsBalance = new ArrayList<Accountdetailsbalance>();
		try {
			transaction = session.beginTransaction();
			
			
			Query query = session
					.createQuery("from Accountdetailsbalance where accountdetailsid IN (:ids)");
			query.setParameterList("ids", accountIds);
			accountDetailsBalance = query.list();
			transaction.commit();
		} catch (HibernateException hibernateException) {
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
		} catch (HibernateException hibernateException) {
			hibernateException.printStackTrace();
		}
		
		
	}

	public List<Accountdetailsbalance> getAccountdetailsbalanceBankCash() {
		
		List<Accountdetailsbalance> accountDetails = new ArrayList<Accountdetailsbalance>();
		
		try{
			transaction = session.beginTransaction();
			accountDetails = session.createQuery("from Accountdetailsbalance as accdetails where accdetails.accountDetails.accountSubGroupMaster.accountsubgroupmasterid IN (1,2)").list();
			transaction.commit();																						   											
		}catch(HibernateException hb){
			hb.printStackTrace();
		}finally{
			session.close();
		}
		
		return accountDetails;
	}

	public List<Receipttransactions> getReceiptTransactions(Integer financialYear) {
		List<Receipttransactions> receiptTransactions = new ArrayList<Receipttransactions>();
		try {
			transaction = session.beginTransaction();
			receiptTransactions = session.createQuery("from Receipttransactions where financialyear='"+financialYear+"' order by transactionsid ASC").list();
			transaction.commit();
		} catch (Exception e) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return null;
	}

	public List<Paymenttransactions> getPaymentTransactions(Integer financialYear) {
		
		List<Paymenttransactions> paymentTransactions = new ArrayList<Paymenttransactions>();
		try {
			transaction = session.beginTransaction();
			paymentTransactions = session.createQuery("from Paymenttransactions where financialyear='"+financialYear+"' order by transactionsid ASC ").list();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		return paymentTransactions;
	}

	public List<Contratransactions> getContraTransactions(Integer financialYear) {
		
		List<Contratransactions> contraTransactions = new ArrayList<Contratransactions>();
		try {
			transaction = session.beginTransaction();
			contraTransactions = session.createQuery("from Contratransactions where financialyear='"+financialYear+"' order by transactionsid ASC ").list();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return contraTransactions;
	}

	public List<Journaltransactions> getJournalTransactions(Integer financialYear) {

		List<Journaltransactions> journalTransactions = new ArrayList<Journaltransactions>();
		try {
			transaction = session.beginTransaction();
			journalTransactions = session.createQuery("from Journaltransactions where financialyear='"+financialYear+"' order by transactionsid ASC ").list();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return journalTransactions;
		
	}

}
