package org.ideoholic.curium.model.mess.supplier.dao;

import java.util.ArrayList;
import java.util.List;

import org.ideoholic.curium.model.account.dto.Accountdetails;
import org.ideoholic.curium.model.account.dto.Accountdetailsbalance;
import org.ideoholic.curium.model.account.dto.VoucherEntrytransactions;
import org.ideoholic.curium.model.mess.stockentry.dto.MessInvoiceDetails;
import org.ideoholic.curium.model.mess.supplier.dto.MessSuppliers;
import org.ideoholic.curium.model.mess.supplier.dto.MessSuppliersPayment;
import org.ideoholic.curium.repositories.AccountDetailsBalanceRepository;
import org.ideoholic.curium.repositories.AccountDetailsRepository;
import org.ideoholic.curium.repositories.MessInvoiceDetailsRepository;
import org.ideoholic.curium.repositories.MessSuppliersPaymentRepository;
import org.ideoholic.curium.repositories.MessSuppliersRepository;
import org.ideoholic.curium.repositories.VoucherEntryTransactionsRepository;
import org.ideoholic.curium.util.QueryUtil;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessSuppliersDAO {

	private final QueryUtil queryUtil;
	private final MessSuppliersRepository messSuppliersRepo;
	private final AccountDetailsRepository accountdetailsRepo;
	private final AccountDetailsBalanceRepository accountdetailsbalanceRepo;
	private final MessInvoiceDetailsRepository messInvoiceDetailsRepository;
	private final MessSuppliersPaymentRepository messSuppliersPaymentRepo;
	private final VoucherEntryTransactionsRepository voucherEntryTransactionsRepo;
	

	@Transactional(readOnly = true)
	public List<MessSuppliers> getSupplierDetails() {
		List<MessSuppliers> results = new ArrayList<>();
		try {
			// This was the original Hibernate query:
			// results = (List<MessSuppliers>) session.createQuery("From MessSuppliers ms order by ms.id DESC")...
			results = messSuppliersRepo.findAllByOrderByIdDesc();
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(ex.getMessage(), ex);
			ex.printStackTrace();
		}
		return results;
	}

	@Transactional
	public MessSuppliers addNewSupplier(Accountdetails accountDetails, Accountdetailsbalance accountDetailsBalance, MessSuppliers messSuppliers) {
		try {
			// Save supplier first to get ID
			messSuppliers = messSuppliersRepo.save(messSuppliers);

			// This was the original Hibernate update (external ID):
			// Query query = session.createQuery("update MessSuppliers set externalid=concat(externalid,'_"+messSuppliers.getId()+"')...");
			// query.executeUpdate();

			String suffix = "_" + messSuppliers.getId();
			messSuppliersRepo.updateExternalid(messSuppliers.getId(), suffix);

			// Save AccountDetails and Balance
			accountDetails = accountdetailsRepo.save(accountDetails);
			accountDetailsBalance = accountdetailsbalanceRepo.save(accountDetailsBalance);

			// This was the original Hibernate update (link ledger id):
			// Query queryLedgerLink = session.createQuery("update MessSuppliers set linkedledgerid= '"+accountDetails.getAccountdetailsid()+"' where id="+messSuppliers.getId());
			// queryLedgerLink.executeUpdate();

			messSuppliersRepo.updateLinkedLedgerId(messSuppliers.getId(), accountDetails.getAccountdetailsid());
			// messSuppliers =  messSuppliersRepository.findById(messSuppliers.getId()).get(); // (optional)
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(ex.getMessage(), ex);
			ex.printStackTrace();
		}
		return messSuppliers;
	}

	@Transactional
	public boolean deleteSuppliers(List<MessSuppliers> messList) {
		boolean result = false;
		List<Integer> accountIdsList = new ArrayList<>();
		List<Integer> idsList = new ArrayList<>();
		try {
			for (MessSuppliers messSuppliers : messList) {
				accountIdsList.add(messSuppliers.getLinkedledgerid());
				idsList.add(messSuppliers.getId());
			}
			// This was the original Hibernate check for invoice details:
			// List<MessInvoiceDetails> messInvoiceList = ... session.createQuery("From MessInvoiceDetails where suppliersid IN (:ids)")
			List<MessInvoiceDetails> messInvoiceList = messInvoiceDetailsRepository.findBySuppliersidIn(idsList);
			if (messInvoiceList.isEmpty()) {
				// Delete linked account details balance
				// Query queryAB = session.createQuery("delete from Accountdetailsbalance where accountdetailsid IN (:ids)")
				accountdetailsbalanceRepo.deleteByAccountdetailsidIn(accountIdsList);

				// Delete linked account details
				// Query queryAD = session.createQuery("delete from Accountdetails where accountdetailsid IN (:ids)")
				accountdetailsRepo.deleteAllById(accountIdsList);

				// Delete supplier
				// Query queryS = session.createQuery("delete from MessSuppliers where id IN (:ids)")
				messSuppliersRepo.deleteAllById(idsList);

				result = true;
			}
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(ex.getMessage(), ex);
			ex.printStackTrace();
		}
		return result;
	}

	@Transactional
	public boolean updateMultipleSuppliers(List<MessSuppliers> messList) {
		boolean result = false;
		try {
			// This was the original Hibernate update:
			// for (MessSuppliers suppliers : messList) {
			// Query query = session.createQuery("update MessSuppliers set name = '"+suppliers.getName()+"', contactnumber = '"+suppliers.getContactnumber()+"', payto='"+suppliers.getPayto()+"', bankaccountno = '"+suppliers.getBankaccountno()+"', ifsccode = '"+suppliers.getIfsccode()+"', address = '"+suppliers.getAddress()+"' where id="+suppliers.getId());
			// query.executeUpdate();
			// }
			for (MessSuppliers supplier : messList) {
				// In Spring Data JPA, usually fetch, set new values, and save.
				messSuppliersRepo.findById(supplier.getId()).ifPresent(dbSupplier -> {
					dbSupplier.setName(supplier.getName());
					dbSupplier.setContactnumber(supplier.getContactnumber());
					dbSupplier.setPayto(supplier.getPayto());
					dbSupplier.setBankaccountno(supplier.getBankaccountno());
					dbSupplier.setAddress(supplier.getAddress());
					dbSupplier.setIfsccode(supplier.getIfsccode());
					messSuppliersRepo.save(dbSupplier);
				});
			}
			result = true;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(ex.getMessage(), ex);
			ex.printStackTrace();
		}
		return result;
	}

	@Transactional(readOnly = true)
	public MessSuppliers getMessSupplierById(Integer supplierid) {
		MessSuppliers result = null;
		try {
			// This was the original Hibernate query:
			// Query query = session.createQuery("From MessSuppliers ms where ms.id = '"+supplierid+"'");
			// result = (MessSuppliers) query.uniqueResult();
			result = messSuppliersRepo.findById(supplierid).orElse(null);
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(ex.getMessage(), ex);
			ex.printStackTrace();
		}
		return result;
	}

	@Transactional(readOnly = true)
	public Accountdetailsbalance getSupplierBalance(String supplieridledgerid) {
		Accountdetailsbalance accountDetailsBalance = null;
		try {
			// This was the original Hibernate query:
			// Query query = session.createQuery("from Accountdetailsbalance where accountdetailsid ="+supplieridledgerid);
			// accountDetailsBalance = (Accountdetailsbalance) query.uniqueResult();
			Integer aid = Integer.parseInt(supplieridledgerid);
			accountDetailsBalance = accountdetailsbalanceRepo.findByAccountDetails_Accountdetailsid(aid);
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(ex.getMessage(), ex);
			ex.printStackTrace();
		}
		return accountDetailsBalance;
	}

	@Transactional
	public boolean saveIssueCheque(MessSuppliersPayment messSuppliersPayment, VoucherEntrytransactions transactions, String updateCrAccount, String updateDrAccount) {
		boolean result = false;
		try {
            // session.save(transactions);
			voucherEntryTransactionsRepo.save(transactions);
            
            messSuppliersPayment.setVoucherid(transactions.getTransactionsid());
            // session.save(messSuppliersPayment);
            messSuppliersPaymentRepo.save(messSuppliersPayment);
            
            // Query queryCrUpdate = session.createQuery(updateCrAccount);
            queryUtil.runUpdateQuery(updateCrAccount);
			
			// Query queryDrUpdate = session.createQuery(updateDrAccount);
            queryUtil.runUpdateQuery(updateDrAccount);
			
			result = true;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(ex.getMessage(), ex);
			ex.printStackTrace();
		}
		return result;
	}

	@Transactional(readOnly = true)
	public List<MessSuppliersPayment> readListOfSuppliersPaymentPagination(int offset, int noOfRecords, int branchid) {
		List<MessSuppliersPayment> results = new ArrayList<>();
		try {
			// This was the original Hibernate query with pagination:
			// Query query = session.createQuery("FROM MessSuppliersPayment msp where msp.branchid='"+branchid+"' order by msp.issuedate DESC");
			// query.setFirstResult(offset); query.setMaxResults(noOfRecords);
			results = messSuppliersPaymentRepo.findByBranchidOrderByIssuedateDesc(branchid, PageRequest.of(offset / noOfRecords, noOfRecords));
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(ex.getMessage(), ex);
			ex.printStackTrace();
		}
		return results;
	}

	@Transactional(readOnly = true)
	public int getNoOfSuppliersPaymentDetails(int branchId) {
		int noOfRecords = 0;
		try {
			// This was the original Hibernate query:
			// results = (List<MessSuppliersPayment>) session.createQuery("FROM MessSuppliersPayment msp where branchid="+branchId)...
			List<MessSuppliersPayment> results = messSuppliersPaymentRepo.findByBranchid(branchId);
			noOfRecords = results.size();
			log.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: {}", noOfRecords);
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(ex.getMessage(), ex);
			ex.printStackTrace();
		}
		return noOfRecords;
	}

	@Transactional
	public boolean updateSupplierPayment(MessSuppliersPayment messSuppliersPayment,
			VoucherEntrytransactions transactions, String updateCrAccount, String updateDrAccount,
			VoucherEntrytransactions transactionsSupplier, String updateCrAccountSupplier,
			String updateDrAccountSupplier) {

		boolean result = false;
		try {
			// session.save(transactions);
			voucherEntryTransactionsRepo.save(transactions);
			// Query query = session.createQuery(updateDrAccount);
			queryUtil.runUpdateQuery(updateDrAccount);
			// Query query1 = session.createQuery(updateCrAccount);
			queryUtil.runUpdateQuery(updateCrAccount);

			// session.save(transactionsSupplier);
			voucherEntryTransactionsRepo.save(transactionsSupplier);
			// Query queryDrSupplier = session.createQuery(updateDrAccountSupplier);
			queryUtil.runUpdateQuery(updateDrAccountSupplier);
			// Query queryCrSupplier = session.createQuery(updateCrAccountSupplier);
			queryUtil.runUpdateQuery(updateCrAccountSupplier);

			// Query querySupplierPayment = session.createQuery("update MessSuppliersPayment set cleareddate = '"+DateUtil.dateParseryyyymmdd(messSuppliersPayment.getDelivereddate())+"', status='CLEARED', voucheridcleared='"+transactionsSupplier.getTransactionsid()+"' where id="+messSuppliersPayment.getId());
			messSuppliersPaymentRepo.findById(messSuppliersPayment.getId()).ifPresent(messSupplier -> {
				messSupplier.setCleareddate(messSuppliersPayment.getDelivereddate());
				messSupplier.setStatus("CLEARED");
				messSupplier.setVoucheridcleared(transactionsSupplier.getTransactionsid());
				messSuppliersPaymentRepo.save(messSupplier);

			});
			result = true;
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();

		}
		return result;
	}

	@Transactional
	public boolean updateSupplierPaymentDelivered(List<MessSuppliersPayment> messSuppliersPaymentList) {
		boolean result = false;
		try {
			// Original code:
			// for (MessSuppliersPayment messSuppliersPayment : messSuppliersPaymentList) {
			// Query query = session.createQuery("update MessSuppliersPayment set delivereddate = '"+DateUtil.dateParseryyyymmdd(messSuppliersPayment.getDelivereddate())+"', status='DELIVERED' id="+messSuppliersPayment.getId());
			// query.executeUpdate();
			// }
			for (MessSuppliersPayment msp : messSuppliersPaymentList) {
				messSuppliersPaymentRepo.findById(msp.getId()).ifPresent(messPayment -> {
					messPayment.setDelivereddate(msp.getDelivereddate());
					messPayment.setStatus("DELIVERED");
					messSuppliersPaymentRepo.save(messPayment);
				});
			}
			result = true;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(ex.getMessage(), ex);
			ex.printStackTrace();
		}
		return result;
	}

	@Transactional
	public boolean updateSupplierPaymentToIssueed(String updateQuery) {
		boolean result = false;
		try {
			// Original: Query query = session.createQuery(updateQuery);
			// query.executeUpdate();
			queryUtil.runUpdateQuery(updateQuery);
			result = true;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(ex.getMessage(), ex);
			ex.printStackTrace();
		}
		return result;
	}

	@Transactional
	public boolean reverseIssueCheque(String updateMessSupplierPayment, VoucherEntrytransactions transactions, String updateCrAccount, String updateDrAccount) {
		boolean result = false;
		try {
			// This was the original code:
			// session.save(transactions);
			voucherEntryTransactionsRepo.save(transactions);
			// Query queryCrUpdate = session.createQuery(updateCrAccount);
			queryUtil.runUpdateQuery(updateCrAccount);
			// Query queryDrUpdate = session.createQuery(updateDrAccount);
			queryUtil.runUpdateQuery(updateDrAccount);
			// Query query = session.createQuery(updateMessSupplierPayment);
			queryUtil.runUpdateQuery(updateMessSupplierPayment);
			result = true;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(ex.getMessage(), ex);
			ex.printStackTrace();
		}
		return result;
	}
}