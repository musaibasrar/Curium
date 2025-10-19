package org.ideoholic.curium.model.mess.item.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.ideoholic.curium.model.account.dto.VoucherEntrytransactions;
import org.ideoholic.curium.model.mess.item.dto.MessItems;
import org.ideoholic.curium.model.mess.item.dto.PoMaster;
import org.ideoholic.curium.model.mess.item.dto.PurchaseOrder;
import org.ideoholic.curium.model.mess.stockentry.dto.MessInvoiceDetails;
import org.ideoholic.curium.model.mess.stockentry.dto.MessStockAvailability;
import org.ideoholic.curium.model.mess.stockentry.dto.MessStockEntry;
import org.ideoholic.curium.repositories.MessInvoiceDetailsRepository;
import org.ideoholic.curium.repositories.MessItemsRepository;
import org.ideoholic.curium.repositories.MessStockAvailabilityRepository;
import org.ideoholic.curium.repositories.MessStockEntryRepository;
import org.ideoholic.curium.repositories.PoMasterRepository;
import org.ideoholic.curium.repositories.PurchaseOrderRepository;
import org.ideoholic.curium.repositories.VoucherEntryTransactionsRepository;
import org.ideoholic.curium.util.Constants;
import org.ideoholic.curium.util.QueryUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessItemsDAO {

    private final QueryUtil queryUtil;
    private final PoMasterRepository poMasterRepository;
    private final MessItemsRepository messItemsRepository;
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final MessStockEntryRepository messStockEntryRepository;
    private final MessInvoiceDetailsRepository messInvoiceDetailsRepository;
    private final MessStockAvailabilityRepository messStockAvailabilityRepository;
    private final VoucherEntryTransactionsRepository voucherEntryTransactionsRepository;


    @Transactional
    public List<MessItems> getItemsDetails() {
        List<MessItems> results = new ArrayList<>();
        try {
            // session.createQuery("From MessItems mi order by mi.id DESC").setCacheable(true).setCacheRegion("commonregion").list();
            results = messItemsRepository.findAllByOrderByIdDesc();
        } catch (Exception hibernateException) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
        }
        return results;
    }

    @Transactional
    public MessItems addNewItem(MessStockAvailability messStockAvailability) {
        try {
            // Query query = session.createQuery("update MessItems set externalid= concat(externalid,'_"+messStockAvailability.getMessitems().getId()+"') where id="+messStockAvailability.getMessitems().getId());
            // query.executeUpdate();
            // session.save(messStockAvailability);
        	Integer messItemId = messStockAvailability.fetchMessItemsId();
			if (messItemId != null) {
				messItemsRepository.findById(messItemId).ifPresent(messItem -> {
					String externalid = messItem.getExternalid();
					messItem.setExternalid(externalid + "_" + messItemId);
				});
			}
            messStockAvailabilityRepository.save(messStockAvailability);
        } catch (Exception hibernateException) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
        }
        return messStockAvailability.getMessitems();
    }

    @Transactional
    public boolean deleteItems(List<Integer> ids) {
        boolean result = false;
        try {
            // Query queryStock = session.createQuery("from MessStockAvailability msa where msa.availablestock > 0 and msa.messitems.id IN (:ids) ");
            List<MessStockAvailability> stocklist = messStockAvailabilityRepository.findByAvailablestockGreaterThanAndMessitems_IdIn(0.0f, ids);

            if (stocklist.isEmpty()) {
                // Query queryMSA = session.createQuery("delete from MessStockAvailability msa where msa.messitems.id IN (:ids)");
                // messStockAvailabilityRepository.deleteByMessitems_IdIn(ids);
            	messStockAvailabilityRepository.deleteByMessitems_IdIn(ids);

                // Query queryMI = session.createQuery("delete from MessItems where id IN (:ids)");
                messItemsRepository.deleteAllByIdInBatch(ids);

                result = true;
            }
        } catch (HibernateException hibernateException) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
        }
        return result;
    }

    @Transactional
    public boolean updateMultipleItems(List<MessStockAvailability> messStockAvailabilityList) {
        boolean result = false;
        try {
            for (MessStockAvailability items : messStockAvailabilityList) {
                // Query query = session.createQuery("update MessItems set name = '"+items.getMessitems().getName()+"', unitofmeasure = '"+items.getMessitems().getUnitofmeasure()+"' where id="+items.getMessitems().getId());
                messItemsRepository.updateNameAndUnitofmeasureById(items.getMessitems().getName(), items.getMessitems().getUnitofmeasure(), items.getMessitems().getId());

                // Query queryMessStock = session.createQuery("update MessStockAvailability set minstock = '"+items.getMinstock()+"' where itemid="+items.getMessitems().getId());
                messStockAvailabilityRepository.updateMinstockByItemid(items.getMinstock(), items.getMessitems().getId());
            }
            result = true;
        } catch (HibernateException hibernateException) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
        }
        return result;
    }

    @Transactional
    public boolean addNewStock(List<MessStockEntry> messStockEntryList, VoucherEntrytransactions transactions,
                              String updateDrAccount, String updateCrAccount,
                              VoucherEntrytransactions transactionTC, String updateTransportationDrAccount,
                              String updateTransportationCrAccount) {

        boolean result = false;
        try {
			// session.save(transactions);
        	voucherEntryTransactionsRepository.save(transactions);
			// Query query = session.createQuery(updateDrAccount);
			// query.executeUpdate();
        	queryUtil.runUpdateQuery(updateDrAccount);
			// Query query1 = session.createQuery(updateCrAccount);
			// query1.executeUpdate();
        	queryUtil.runUpdateQuery(updateCrAccount);
        	
        	if(transactionTC != null) {
        		// session.save(transactionTC);
        		voucherEntryTransactionsRepository.save(transactionTC);
        		// Query queryDrTc = session.createQuery(updateTransportationDrAccount);
				// queryDrTc.executeUpdate();
        		queryUtil.runUpdateQuery(updateTransportationDrAccount);
				// Query queryCrTc = session.createQuery(updateTransportationCrAccount);
				// queryCrTc.executeUpdate();
        		queryUtil.runUpdateQuery(updateTransportationCrAccount);
        	}
        	
        	for (MessStockEntry messStockEntry : messStockEntryList) {
        		// session.save(messStockEntry);
        		messStockEntryRepository.save(messStockEntry);
        		// Query queryStockAvailability = session.createQuery("update MessStockAvailability set availablestock= availablestock+'"+messStockEntry.getQuantity()+"' where itemid="+messStockEntry.getItemid());
        		messStockAvailabilityRepository.incrementAvailableStockByItemId(messStockEntry.getQuantity(), messStockEntry.getItemid());
        		// Query queryInvoice = session.createQuery("update MessInvoiceDetails set voucherid= '"+transactions.getTransactionsid()+"' where id="+messStockEntry.getMessinvoicedetails().getId());
        		messInvoiceDetailsRepository.updateVoucherIdByInvoiceId(transactions.getTransactionsid(), messStockEntry.getMessinvoicedetails().getId());
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
    public List<MessInvoiceDetails> getInvoiceDetailsPagination(int offset, int noOfRecords, Integer branchId) {
        List<MessInvoiceDetails> results = new ArrayList<>();
        try {
            // Query query = session.createQuery("From MessInvoiceDetails as invoicedetails where invoicedetails.status != 'CANCELLED' and invoicedetails.branchid = "+branchId+" order by invoicedetails.invoicedate DESC");
        	Pageable pageable = PageRequest.of(offset, noOfRecords);
        	Page<MessInvoiceDetails> resultsPage = messInvoiceDetailsRepository.findActiveInvoicesByBranchId(Constants.CANCELLED, branchId, pageable);
        	results = resultsPage.getContent();
        } catch (Exception hibernateException) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
        }
        return results;
    }

    @Transactional
    public int getTotalNoOfRecords(int branchId) {
        int noOfRecords = 0;
        try {
            // session.createQuery("From MessInvoiceDetails as invoicedetails where invoicedetails.status != 'CANCELLED' and invoicedetails.branchid="+branchId)
            noOfRecords = messInvoiceDetailsRepository.countByStatusNotAndBranchid(Constants.CANCELLED, branchId);
            log.info("The size of MessInvoiceDetails is:::::::::::::::::::::::::::::::::::::::::: " + noOfRecords);
        } catch (Exception hibernateException) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
        }
        return noOfRecords;
    }

    @Transactional
    public List<MessStockAvailability> getItemsStockAvailability() {
        List<MessStockAvailability> results = new ArrayList<>();
        try {
            // session.createQuery("From MessStockAvailability ms order by ms.messitems.name ASC").setCacheable(true).setCacheRegion("commonregion").list();
            results = messStockAvailabilityRepository.findAllOrderByMessitems();
        } catch (Exception hibernateException) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
        }
        return results;
    }

    @Transactional
    public MessItems getItem(Integer itemid) {
        MessItems results = null;
        try {
            // Query query = session.createQuery("From MessItems mi where mi.id="+itemid).setCacheable(true).setCacheRegion("commonregion");
            results = messItemsRepository.findById(itemid).orElse(null);
        } catch (Exception hibernateException) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
        }
        return results;
    }

    @Transactional
    public List<MessStockEntry> getItemsStockEntry() {
        List<MessStockEntry> results = new ArrayList<>();
        try {
            // session.createQuery("From MessStockEntry ms where ms.availablequantity > 0 and ms.status != 'CANCELLED' order by ms.itemid DESC").setCacheable(true).setCacheRegion("commonregion").list();
            results = messStockEntryRepository.findByAvailablequantityGreaterThanAndStatusNotOrderByItemidDesc(0.0f, Constants.CANCELLED);
        } catch (Exception hibernateException) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
        }
        return results;
    }

    @Transactional
    public List<MessItems> getItemDetailByID(List<Integer> itemIds) {
        List<MessItems> result = new ArrayList<>();
        try {
            // Query queryItems = session.createQuery("from MessItems mi where mi.id IN (:ids) order by mi.id DESC").setCacheable(true).setCacheRegion("commonregion");
            result = messItemsRepository.findByIdInOrderByIdDesc(itemIds);
        } catch (HibernateException hibernateException) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            hibernateException.printStackTrace();
        }
        return result;
    }

    @Transactional
    public boolean cancelPurchase(int invoiceId, List<MessStockEntry> messStockEntryList,
                                 String updateDrAccount, String updateCrAccount, String cancelVoucher) {
        boolean result = false;
        try {
            // Query queryInvoiceDetails = session.createQuery("update MessInvoiceDetails set status='CANCELLED' where id = '"+invoiceId+"'");
            messInvoiceDetailsRepository.updateStatusById(Constants.CANCELLED, invoiceId);

            // Query queryStock = session.createQuery("update MessStockEntry set status='CANCELLED' where invoicedetailsid = '"+invoiceId+"'");
            messStockEntryRepository.updateStatusByInvoicedetailsid(Constants.CANCELLED, invoiceId);

            for (MessStockEntry messStockEntry : messStockEntryList) {
                // Query queryStockAvailability = session.createQuery("update MessStockAvailability set availablestock=availablestock-'"+messStockEntry.getQuantity()+"' where itemid='"+messStockEntry.getItemid()+"'");
                messStockAvailabilityRepository.subtractAvailablestockByItemid(messStockEntry.getQuantity(), messStockEntry.getItemid());
            }

            // Query updateDr = session.createQuery(updateDrAccount);
			// updateDr.executeUpdate();
            queryUtil.runUpdateQuery(updateDrAccount);
            // Query updateCr = session.createQuery(updateCrAccount);
            // updateCr.executeUpdate();
            queryUtil.runUpdateQuery(updateCrAccount);
            // Query cancelVoucherQuery = session.createQuery(cancelVoucher);
            // cancelVoucherQuery.executeUpdate();
            queryUtil.runUpdateQuery(cancelVoucher);

            result = true;
        } catch (HibernateException hibernateException) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            hibernateException.printStackTrace();
        }
        return result;
    }

    @Transactional
    public List<MessStockEntry> getMessStockEntry(Integer invIds) {
        List<MessStockEntry> results = new ArrayList<>();
        try {
            // Query query = session.createQuery("From MessStockEntry mse where mse.messinvoicedetails.id = '"+invIds+"'");
            results = messStockEntryRepository.findByMessinvoicedetails_Id(invIds);
        } catch (Exception hibernateException) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
        }
        return results;
    }

    @Transactional
    public MessStockEntry getMessStockEntryByID(Integer stockentryid) {
        MessStockEntry results = null;
        try {
            // Query query = session.createQuery("From MessStockEntry mse where id = '"+stockentryid+"'");
            results = messStockEntryRepository.findById(stockentryid).orElse(null);
        } catch (Exception hibernateException) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
        }
        return results;
    }

    @Transactional
    public List<MessStockEntry> getMessStockEntryByIdList(List<Integer> messStockMoveIds) {
        List<MessStockEntry> results = new ArrayList<>();
        try {
            // Query query = session.createQuery("From MessStockEntry mse where mse.id IN (:ids)");
            results = messStockEntryRepository.findByIdIn(messStockMoveIds);
        } catch (Exception hibernateException) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
        }
        return results;
    }

    @Transactional
    public List<MessStockEntry> getStockReceivedDetailsReport(String query) {
        List<MessStockEntry> results = new ArrayList<>();
        try {
            // session.createQuery(query).setCacheable(true).setCacheRegion("commonregion").list();
        	results = queryUtil.runGivenQuery(query, MessStockEntry.class);
        } catch (Exception hibernateException) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
        }
        return results;
    }

    @Transactional
    public List<MessStockAvailability> getItemsStock() {
        List<MessStockAvailability> results = new ArrayList<>();
        try {
            // session.createQuery("From MessStockAvailability ms where availablestock > 0 order by ms.messitems.name ASC").setCacheable(true).setCacheRegion("commonregion").list();
            results = messStockAvailabilityRepository.findByAvailablestockGreaterThanOrderByMessitems_NameAsc(0.0f);
        } catch (Exception hibernateException) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
        }
        return results;
    }

    @Transactional
    public boolean addNewOrderDetail(List<PurchaseOrder> orderList, PoMaster poMaster) {
        boolean result = false;
        PoMaster poMasterExtid = null;
        String externalId = null;

        try {
            // Query query = session.createQuery("from PoMaster as poMaster order by id desc");
            // query.setMaxResults(1);
            poMasterExtid = poMasterRepository.findTopByOrderByIdDesc();

            if (poMasterExtid != null) {
            	// order.setExternalId(order.getExternalId()+String.format("%04d", poMaster.getId()+1));
                externalId = poMaster.getExternalId() + String.format("%05d", poMasterExtid.getId() + 1);
            } else {
                externalId = poMaster.getExternalId() + String.format("%05d", 1);
            }
            poMaster.setExternalId(externalId);
            poMasterRepository.save(poMaster);

            for (PurchaseOrder order : orderList) {
                order.setExternalId(externalId);
                purchaseOrderRepository.save(order);
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
    public List<PoMaster> getPurchaseOrderMasterDetails() {
        List<PoMaster> results = new ArrayList<>();
        try {
            // session.createQuery("From PoMaster").setCacheable(true).setCacheRegion("commonregion").list();
            results = poMasterRepository.findAll();
        } catch (Exception hibernateException) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
        }
        return results;
    }

    @Transactional
    public List<PurchaseOrder> getParticularInvoice(String externalId) {
        List<PurchaseOrder> results = new ArrayList<>();
        try {
            // session.createQuery("From PurchaseOrder mse where externalId = '"+externalId+"'");
            results = purchaseOrderRepository.findByExternalId(externalId);
        } catch (Exception hibernateException) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
        }
        return results;
    }

    @Transactional
    public boolean addPoMasteDetail(PoMaster poMaster) {
        boolean result = false;
        try {
            poMasterRepository.save(poMaster);
            result = true;
        } catch (Exception hibernateException) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
        }
        return result;
    }

    @Transactional
    public void cancelPurchaseOrder(List<Integer> ids) {
        try {
            // Query query = session.createQuery("delete from PoMaster as poMaster where poMaster.id IN (:ids)");
            poMasterRepository.deleteByIdIn(ids);
        } catch (Exception hibernateException) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
        }
    }

    @Transactional
    public List<MessInvoiceDetails> getInvoiceDetailsPaginationOpeningStock(int offset, int noOfRecords, int branchId) {
        List<MessInvoiceDetails> results = new ArrayList<>();
        try {
            // Query query = session.createQuery("From MessInvoiceDetails as invoicedetails where invoicedetails.status != 'CANCELLED' and invoicedetails.branchid = "+branchId+" and invoicedetails.suppliersid = 0  order by invoicedetails.invoicedate DESC");
        	Pageable pageable = PageRequest.of(offset, noOfRecords);
            results = messInvoiceDetailsRepository.fetchActiveInvoiceDetails(Constants.CANCELLED, branchId, 0, pageable);
        } catch (Exception hibernateException) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
        }
        return results;
    }

    @Transactional
    public boolean addNewStockFromPO(List<MessStockEntry> messStockEntryList, VoucherEntrytransactions transactions,
                                     String updateDrAccount, String updateCrAccount, VoucherEntrytransactions transactionTC,
                                     String updateTransportationDrAccount, String updateTransportationCrAccount,
                                     String poNumber) {

        boolean result = false;
        try {
        	// session.save(transactions);
        	voucherEntryTransactionsRepository.save(transactions);
			int totalQty = 0;
			// Query query = session.createQuery(updateDrAccount);
			queryUtil.runUpdateQuery(updateDrAccount);
			// Query query1 = session.createQuery(updateCrAccount);
			queryUtil.runUpdateQuery(updateCrAccount);
			
			if(transactionTC != null) {
				// session.save(transactionTC);
				voucherEntryTransactionsRepository.save(transactionTC);
				// Query queryDrTc = session.createQuery(updateTransportationDrAccount);
				queryUtil.runUpdateQuery(updateTransportationDrAccount);
				// Query queryCrTc = session.createQuery(updateTransportationCrAccount);
				queryUtil.runUpdateQuery(updateTransportationCrAccount);
			}
			
            
            for (MessStockEntry messStockEntry : messStockEntryList) {
            	// session.save(messStockEntry);
            	messStockEntryRepository.save(messStockEntry);

				// Query queryStockAvailability = session.createQuery("update MessStockAvailability set availablestock= availablestock+'"+messStockEntry.getQuantity()+"' where itemid="+messStockEntry.getItemid());
            	messStockAvailabilityRepository.incrementAvailableStockByItemId(messStockEntry.getQuantity(), messStockEntry.getItemid());
				// Query queryInvoice = session.createQuery("update MessInvoiceDetails set voucherid= '"+transactions.getTransactionsid()+"' where id="+messStockEntry.getMessinvoicedetails().getId());
            	messInvoiceDetailsRepository.updateVoucherIdByInvoiceId(transactions.getTransactionsid(), messStockEntry.getMessinvoicedetails().getId());
				
				// Query queryInvoicePO = session.createQuery("update PurchaseOrder set receivedquantity= '"+messStockEntry.getQuantity()+"' where itemid="+messStockEntry.getItemid()+" and externalid='"+poNumber+"'");
            	purchaseOrderRepository.updateReceivedQuantity(Float.toString(messStockEntry.getQuantity()), messStockEntry.getItemid(), poNumber);
				
				totalQty = (int) (totalQty+messStockEntry.getQuantity());
			}
            
            // Query queryInvoicePO = session.createQuery("update PoMaster set totalquantityreceived= '"+totalQty+"' where externalid='"+poNumber+"' ");
            poMasterRepository.updateTotalQuantityReceived(totalQty, poNumber);
            
            result = true;
        } catch (Exception hibernateException) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
        }
        return result;
    }
}