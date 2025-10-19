package org.ideoholic.curium.model.mess.stockentry.dao;

import java.util.ArrayList;
import java.util.List;

import org.ideoholic.curium.model.mess.item.dto.MessItems;
import org.ideoholic.curium.model.mess.item.dto.PurchaseOrder;
import org.ideoholic.curium.model.mess.stockentry.dto.MessStockEntry;
import org.ideoholic.curium.repositories.MessItemsRepository;
import org.ideoholic.curium.repositories.MessStockEntryRepository;
import org.ideoholic.curium.repositories.PurchaseOrderRepository;
import org.ideoholic.curium.util.Constants;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessStockEntryDAO {

	private final MessItemsRepository messItemsRepository;
	private final PurchaseOrderRepository purchaseOrderRepository;
	private final MessStockEntryRepository messStockEntryRepository;


	@Transactional
	public List<MessItems> getItemsDetails() {
		List<MessItems> results = new ArrayList<MessItems>();
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
			MessItems savedMi = messItemsRepository.save(messItems);

			// Query query = session.createQuery("update MessItems set externalid= concat(externalid,'_"+messItems.getId()+"') where id="+messItems.getId());
			String existingExternalId = savedMi.getExternalid() == null ? "" : savedMi.getExternalid();
			savedMi.setExternalid(existingExternalId + "_" + savedMi.getId());
			savedMi = messItemsRepository.save(savedMi);

			return savedMi;
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
			return messItems;
		}
	}

	@Transactional
	public boolean deleteItems(List<Integer> ids) {
		boolean result = false;
		try {
			// Query query = session.createQuery("delete from MessItems where id IN (:ids)");
			// Use repository delete in batch for efficiency
			messItemsRepository.deleteAllByIdInBatch(ids);
			result = true;
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return result;
	}

	@Transactional
	public boolean updateMultipleItems(List<MessItems> messList) {
		boolean result = false;
		try {
			// for (MessItems items : messList) {
			//     Query query = session.createQuery("update MessItems set name = '"+items.getName()+"', unitofmeasure = '"+items.getUnitofmeasure()+"' where id="+items.getId());
			//     query.executeUpdate();
			// }

			for (MessItems items : messList) {
				// Load existing entity (optional), update fields, save
				MessItems existing = messItemsRepository.findById(items.getId()).map(messItem -> {
					messItem.setName(items.getName());
					messItem.setUnitofmeasure(items.getUnitofmeasure());
					return messItemsRepository.save(messItem);
				}).orElse(messItemsRepository.save(items));
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
		List<MessStockEntry> results = new ArrayList<MessStockEntry>();
		try {
			// results = (List<MessStockEntry>) session.createQuery("From MessStockEntry mse where mse.status != 'CANCELLED' and mse.messinvoicedetails.id = '"+invoiceDetailsId+"' and mse.messinvoicedetails.status != 'CANCELLED' order by mse.id DESC").list();
			results = messStockEntryRepository.findMRVDetailsByInvoiceDetailsId(invoiceDetailsId, Constants.CANCELLED);
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return results;
	}

	@Transactional
	public List<MessStockEntry> getItemsStockEntry(int itemId) {
		List<MessStockEntry> results = new ArrayList<MessStockEntry>();
		try {
			// results = (List<MessStockEntry>) session.createQuery("From MessStockEntry mse where mse.status != 'CANCELLED' and mse.itemid = '"+itemId+"' and mse.messinvoicedetails.status !='CANCELLED' order by mse.id ASC").list();
			results = messStockEntryRepository.findItemsStockEntryByItemId(itemId, Constants.CANCELLED);
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return results;
	}

	@Transactional
	public List<PurchaseOrder> getPurchaseOrderById(String invoiceDetailsId) {
		List<PurchaseOrder> results = new ArrayList<PurchaseOrder>();
		try {
			// results = (List<PurchaseOrder>) session.createQuery("From PurchaseOrder po where  po.externalId = '"+invoiceDetailsId+"' and po.receivedQuantity < po.quantity").setCacheable(true).list();
			results = purchaseOrderRepository.findByExternalIdAndReceivedQuantityLessThanQuantity(invoiceDetailsId);
		} catch (Exception hibernateException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error(hibernateException.getMessage(), hibernateException);
			hibernateException.printStackTrace();
		}
		return results;
	}
}