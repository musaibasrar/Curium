package org.ideoholic.curium.model.mess.stockmove.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;
import org.ideoholic.curium.model.account.dto.VoucherEntrytransactions;
import org.ideoholic.curium.model.mess.item.dto.MessItems;
import org.ideoholic.curium.model.mess.item.dto.MessStockMoveInfo;
import org.ideoholic.curium.model.mess.stockentry.dto.MessStockEntry;
import org.ideoholic.curium.model.mess.stockmove.dto.Bill;
import org.ideoholic.curium.model.mess.stockmove.dto.MessStockMove;
import org.ideoholic.curium.model.mess.stockmove.dto.MessTaxInvoice;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.repositories.MessInvoiceDetailsRepository;
import org.ideoholic.curium.repositories.MessItemsRepository;
import org.ideoholic.curium.repositories.MessStockAvailabilityRepository;
import org.ideoholic.curium.repositories.MessStockEntryRepository;
import org.ideoholic.curium.repositories.MessStockMoveInfoRepository;
import org.ideoholic.curium.repositories.MessStockMoveRepository;
import org.ideoholic.curium.repositories.MessTaxInvoiceRepository;
import org.ideoholic.curium.repositories.VoucherEntryTransactionsRepository;
import org.ideoholic.curium.util.QueryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessStockMoveDAO {

	private final QueryUtil queryUtil;
	private final MessItemsRepository messItemsRepository;
	private MessTaxInvoiceRepository messTaxInvoiceRepository;
	private final MessStockMoveRepository messStockMoveRepository;
	private final MessStockEntryRepository messStockEntryRepository;
	private final MessStockMoveInfoRepository messStockMoveInfoRepository;
	private final MessInvoiceDetailsRepository messInvoiceDetailsRepository;
	private final MessStockAvailabilityRepository messStockAvailabilityRepository;
	private VoucherEntryTransactionsRepository voucherEntryTransactionsRepository;

	@Transactional
	public List<MessItems> getItemsDetails() {
		List<MessItems> results = new ArrayList<>();
		try {
			// results = (List<MessItems>) session.createQuery("From MessItems mi order by mi.id ASC").setCacheable(true).setCacheRegion("commonregion").list();
			results = messItemsRepository.findAllByOrderByIdAsc();
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return results;
	}

	@Transactional
	public MessItems addNewItem(MessItems messItems) {
		try {
			// session.save(messItems);
			// Query query = session.createQuery("update MessItems set externalid= concat(externalid,'_"+messItems.getId()+"') where id="+messItems.getId());

			// Persist new item
			messItems = messItemsRepository.save(messItems);
			// update externalid by appending id
			if (messItems != null && messItems.getId() != null) {
				messItems.setExternalid("externalid_" + messItems.getId());
				messItemsRepository.save(messItems);
			}
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return messItems;
	}

	@Transactional
	public boolean deleteItems(List<Integer> ids) {
		boolean result = false;
		try {
			// Query query = session.createQuery("delete from MessItems where id IN (:ids)");
			// query.setParameterList("ids", ids);

			// Use repository to delete in batch
			List<MessItems> toDelete = messItemsRepository.findAllById(ids);
			messItemsRepository.deleteAll(toDelete);
			result = true;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(ex.getMessage(), ex);
			ex.printStackTrace();
		}
		return result;
	}

	@Transactional
	public boolean updateMultipleItems(List<MessItems> messList) {
		boolean result = false;
		try {
			// transaction = session.beginTransaction();
			// for (MessItems items : messList) {
			//     Query query = session.createQuery("update MessItems set name = '"+items.getName()+"', unitofmeasure = '"+items.getUnitofmeasure()+"' where id="+items.getId());
			//     query.executeUpdate();
			// }
			// transaction.commit();

			for (MessItems items : messList) {
				messItemsRepository.findById(items.getId()).ifPresent(existing -> {
					existing.setName(items.getName());
					existing.setUnitofmeasure(items.getUnitofmeasure());
					messItemsRepository.save(existing);	
				});
			}
			result = true;
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return result;

	}

	@Transactional
	public List<MessStockEntry> getMRVDetails(int invoiceDetailsId) {
		List<MessStockEntry> results = new ArrayList<>();
		try {
			// results = (List<MessStockEntry>) session.createQuery("From MessStockEntry mse where mse.status != 'CANCELLED' and mse.messinvoicedetails.status !='CANCELLED' and mse.messinvoicedetails.id = "+invoiceDetailsId+" order by mse.id ASC").list();

			results = messStockEntryRepository.findByMessinvoicedetails_IdAndStatusNotOrderByIdAsc(invoiceDetailsId, "CANCELLED");
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return results;
	}

	@Transactional
	public boolean moveStockSave(List<MessStockMove> messStockMovesList, VoucherEntrytransactions transactions,
			String updateDrAccount, String updateCrAccount, VoucherEntrytransactions transactionsIncomeCash,
			VoucherEntrytransactions transactionsIncomeBank, VoucherEntrytransactions transactionsIncomeCheque,
			String updateDrAccountIncomeCash, String updateCrAccountIncomeCash, String updateDrAccountIncomeBank,
			String updateCrAccountIncomeBank, String updateDrAccountIncomeCheque, String updateCrAccountIncomeCheque,
			MessStockMoveInfo messStockMoveInfo) {

		boolean result = false;
		int billNo = 0;
		try {
			// session.save(transactions);
			voucherEntryTransactionsRepository.save(transactions);
			// Query query = session.createQuery(updateDrAccount);
			queryUtil.runUpdateQuery(updateDrAccount);
			// Query query1 = session.createQuery(updateCrAccount);
			queryUtil.runUpdateQuery(updateCrAccount);

			if (transactionsIncomeCash != null) {
				voucherEntryTransactionsRepository.save(transactionsIncomeCash);
			}

			if (transactionsIncomeBank != null) {
				voucherEntryTransactionsRepository.save(transactionsIncomeBank);
			}

			if (transactionsIncomeCheque != null) {
				voucherEntryTransactionsRepository.save(transactionsIncomeCheque);
			}

			if (updateDrAccountIncomeCash != null) {
				// Query queryIncome = session.createQuery(updateDrAccountIncomeCash);
				queryUtil.runUpdateQuery(updateDrAccountIncomeCash);
				// Query queryIncomeCr = session.createQuery(updateCrAccountIncomeCash);
				queryUtil.runUpdateQuery(updateCrAccountIncomeCash);
			}

			if (updateDrAccountIncomeBank != null) {
				// Query queryIncome = session.createQuery(updateDrAccountIncomeBank);
				queryUtil.runUpdateQuery(updateDrAccountIncomeBank);
				// Query queryIncomeCr = session.createQuery(updateCrAccountIncomeBank);
				queryUtil.runUpdateQuery(updateCrAccountIncomeBank);
			}

			if (updateDrAccountIncomeCheque != null) {
				// Query queryIncome = session.createQuery(updateDrAccountIncomeCheque);
				queryUtil.runUpdateQuery(updateDrAccountIncomeCheque);
				// Query queryIncomeCr = session.createQuery(updateCrAccountIncomeCheque);
				queryUtil.runUpdateQuery(updateCrAccountIncomeCheque);
			}

			// Determine billNo similar to original logic (get last MessStockMove)
			billNo = messStockMoveRepository.findTopByOrderByIdDesc().map(msm -> msm.getId() + 1).orElse(1);

			for (MessStockMove messStockMove : messStockMovesList) {

				messStockMove.setExternalid(String.format("%04d", billNo));
				// session.save(messStockMove);
				messStockMoveRepository.save(messStockMove);
				// Query queryUpdateMessStock = session.createQuery("update MessStockMove set voucherid = '"+transactions.getTransactionsid()+"' where id="+messStockMove.getId());
				messStockMoveRepository.updateVoucherId(transactions.getTransactionsid(), messStockMove.getId());
				// Query queryStockAvailability = session.createQuery("update MessStockAvailability set availablestock= availablestock-'"+messStockMove.getQuantity()+"' where itemid="+messStockMove.getItemid());
				messStockAvailabilityRepository.decrementAvailableStock(messStockMove.getQuantity(), messStockMove.getItemid());
				// Query queryStockEntry = session.createQuery("update MessStockEntry set availablequantity= availablequantity-'"+messStockMove.getQuantity()+"' where id="+messStockMove.getStockentryid());
				messStockEntryRepository.findById(messStockMove.getStockentryid()).ifPresent(msEntry -> {
					Float availablequantity = msEntry.getAvailablequantity();
					availablequantity -= messStockMove.getQuantity();
					messStockEntryRepository.save(msEntry);
				});
				// Query invoiceQuery = session.createQuery("from MessStockEntry where id = '"+messStockMove.getStockentryid()+"'");
				messStockEntryRepository.findById(messStockMove.getStockentryid()).ifPresent(messStockEntry -> {
					// Query queryInvoiceId = session.createQuery("update MessStockEntry set status = 'MOVED' where invoicedetailsid = '"+messStockEntry.getMessinvoicedetails().getId()+"'");
					messStockEntryRepository.updateStatusByInvoicedetailsid("MOVED", messStockEntry.getMessinvoicedetails().getId());
					// Query queryInvoiceDetails = session.createQuery("update MessInvoiceDetails set status = 'MOVED' where id = '"+messStockEntry.getMessinvoicedetails().getId()+"'");
					messInvoiceDetailsRepository.updateStatusById("Moved", messStockEntry.getMessinvoicedetails().getId());	
				});
			}

			// Query queryMessStockMoveInfo = session.createQuery("from MessStockMoveInfo ORDER BY id DESC");
			// queryMessStockMoveInfo.setMaxResults(1);
			MessStockMoveInfo msmi = messStockMoveInfoRepository.findTopByOrderByReceiptnumberDesc();
			int msmiBRN = 0;
			if (msmi != null) {
				msmiBRN = msmi.getReceiptnumber() + 1;
			} else {
				msmiBRN = 1;
			}

			messStockMoveInfo.setBranchreceiptnumber(String.format("%04d", msmiBRN));
			messStockMoveInfoRepository.save(messStockMoveInfo);

			result = true;
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return result;
	}

	@Transactional
	public List<Bill> getStockMoveDetails(int offset, int noOfRecords, int branchId) {

		List<Bill> results = new ArrayList<>();

		try {
			// Query query = session.createQuery("From MessStockMove msm where msm.status != 'CANCELLED' order by msm.id DESC").setCacheable(true).setCacheRegion("commonregion");
			// query.setFirstResult(offset);
			// query.setMaxResults(noOfRecords);
			List<MessStockMove> msmList = messStockMoveRepository.findByStatusAndId("CANCELLED", PageRequest.of(offset, noOfRecords)).toList();
			// TODO: How to cast the above list to List<Bill> ?
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return results;
	}

	@Transactional
	public MessStockMove getStockMoveDetails(int stockid) {
		MessStockMove results = null;
		try {
			// Query query = session.createQuery("From MessStockMove msm where id = '"+stockid+"'");
			// results = (MessStockMove) query.uniqueResult();

			results = messStockMoveRepository.findById(stockid).orElse(new MessStockMove());
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return results;
	}

	@Transactional
	public boolean cancelStockMove(MessStockMove messStockMove, VoucherEntrytransactions transactions, String updateDrAccount,
			String updateCrAccount) {

		boolean result = false;

		try {
			// Query queryInvoiceDetails = session.createQuery("update MessStockMove set status='CANCELLED' where id = '"+messStockMove.getId()+"'");
			messStockMoveRepository.findById(messStockMove.getId()).ifPresent(msm -> {
				msm.setStatus("CANCELLED");
				messStockMoveRepository.save(msm);
			});
			// Query queryStock = session.createQuery("update MessStockEntry set availablequantity = availablequantity+'"+messStockMove.getQuantity()+"' where id = '"+messStockMove.getStockentryid()+"'");
			messStockEntryRepository.findById(messStockMove.getStockentryid()).ifPresent(stockEntry -> {
				Float availableQuantity = stockEntry.getAvailablequantity();
				availableQuantity += messStockMove.getQuantity();
				stockEntry.setAvailablequantity(availableQuantity);
				messStockEntryRepository.save(stockEntry);

			});
			// Query queryStockAvailable = session.createQuery("update MessStockAvailability set availablestock = availablestock+'"+messStockMove.getQuantity()+"' where itemid = '"+messStockMove.getItemid()+"'");
			messStockAvailabilityRepository.incrementAvailableStockByItemId(messStockMove.getQuantity(),
					messStockMove.getItemid());

			// Accounts
			// session.save(transactions);
			voucherEntryTransactionsRepository.save(transactions);
			// Query query = session.createQuery(updateDrAccount);
			queryUtil.runUpdateQuery(updateDrAccount);
			// Query query1 = session.createQuery(updateCrAccount);
			queryUtil.runUpdateQuery(updateCrAccount);

			result = true;
		} catch (HibernateException hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			hibernateException.printStackTrace();
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ex.printStackTrace();
		}
		return result;
	}

	@Transactional
	public List<MessStockMove> getStockMoveDetailsReport(String stockMoveQuery) {
		List<MessStockMove> results = new ArrayList<>();
		try {
			// results = (List<MessStockMove>) session.createQuery(stockMoveQuery).setCacheable(true).setCacheRegion("commonregion").list();
			results = queryUtil.runGivenQuery(stockMoveQuery, MessStockMove.class);
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return results;
	}

	@Transactional
	public int getNoOfRecordsStockMove(int branchId) {
		int noOfRecords = 0;
		try {
			// results = (List<Student>) session.createQuery("From MessStockMove msm where msm.status != 'CANCELLED' AND msm.branchid="+branchId).setCacheable(true).setCacheRegion("commonregion").list();
			noOfRecords = messStockMoveRepository.countByStatusAndBranchid("CANCELLED", branchId);
			log.info("The size of list is:::::::::::::::::::::::::::::::::::::::::: " + noOfRecords);
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return noOfRecords;
	}

	@Transactional
	public List<MessStockMove> getCustomerLastPrices(String customerName, String itemid, int branchId) {
		List<MessStockMove> results = new ArrayList<>();
		try {
			// Query query = session.createQuery("From MessStockMove msm where msm.status != 'CANCELLED' and issuedto='"+customerName+"' and itemid='"+itemid+"' AND msm.branchid="+branchId+" order by id DESC");
			results = messStockMoveRepository.findByStatusNotAndIssuedtoAndItemidAndBranchidOrderByIdDesc("CANCELLED", customerName, Integer.valueOf(itemid), branchId);
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return results;
	}

	@Transactional
	public MessStockMove getMessStockMoveMaxRow() {

		MessStockMove msm = new MessStockMove();

		try {
			// Query queryMaxRow = session.createQuery("from MessStockMove ORDER BY id DESC");
			// queryMaxRow.setMaxResults(1);

			msm = messStockMoveRepository.findTopByOrderByIdDesc().orElse(new MessStockMove());
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();

		}
		return msm;

	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<Object[]> readStockDueDetails(String classStudying, int branchId) {
		List<Object[]> results = new ArrayList<>();

		try {
			// Query query = session.createSQLQuery("select s.sid, s.studentexternalid, s.admissionnumber, s.name, s.classstudying, f.fathersname, f.mothersname from student s JOIN parents f ON s.sid=f.sid where s.archive = 0 and s.classstudying = '"+classStudying+"' AND s.branchid = "+branchId);
			String sql = "SELECT s.sid, s.studentexternalid, s.admissionnumber, s.name, s.classstudying, f.fathersname, f.mothersname from student s JOIN parents f ON s.sid=f.sid "
					+ "WHERE s.archive = 0 and s.classstudying = " + classStudying + " AND s.branchid = " + branchId;
			results = queryUtil.runGivenQuery(sql, Object[].class);
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();

		}
		return results;
	}

	@Transactional
	public List<MessStockMoveInfo> getTotalDue() {
		List<MessStockMoveInfo> results = new ArrayList<>();
		try {
			// Query query = session.createQuery("from MessStockMoveInfo");
			results = messStockMoveInfoRepository.findAll();
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return results;
	}

	@Transactional
	public void updateDue(int id, Long dueAmount) {
		try {
			// Query queryUpdate = session.createQuery("update MessStockMoveInfo set due = '"+dueAmount+"'  where receiptnumber = '"+id+"'");
			messStockMoveInfoRepository.findById(id).ifPresent(messStockMoveInfo -> {
				messStockMoveInfo.setDue(dueAmount);
			});
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
	}

	@Transactional
	public void messTaxInvoiceSave(List<MessTaxInvoice> messTaxInvoiceList) {
		try {
			// for (MessTaxInvoice messTaxInvoice : messTaxInvoiceList) {
			//     session.save(messTaxInvoice);
			// }

			messTaxInvoiceRepository.saveAll(messTaxInvoiceList);
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
	}

	@Transactional
	public List<MessTaxInvoice> getTaxInvoiceDetail() {
		List<MessTaxInvoice> results = new ArrayList<>();
		try {
			// Query query = session.createQuery("from MessTaxInvoice");
			results = messTaxInvoiceRepository.findAll();
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return results;
	}
}