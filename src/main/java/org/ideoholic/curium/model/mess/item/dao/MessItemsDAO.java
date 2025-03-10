package org.ideoholic.curium.model.mess.item.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.ideoholic.curium.model.account.dto.VoucherEntrytransactions;
import org.ideoholic.curium.model.feescategory.dto.Feescategory;
import org.ideoholic.curium.model.mess.item.dto.MessItems;
import org.ideoholic.curium.model.mess.item.dto.PoMaster;
import org.ideoholic.curium.model.mess.item.dto.PurchaseOrder;
import org.ideoholic.curium.model.mess.stockentry.dto.MessInvoiceDetails;
import org.ideoholic.curium.model.mess.stockentry.dto.MessStockAvailability;
import org.ideoholic.curium.model.mess.stockentry.dto.MessStockEntry;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.util.HibernateUtil;
import org.ideoholic.curium.util.Session;
import org.ideoholic.curium.util.Session.Transaction;

public class MessItemsDAO {
	Session session = null;
	/**
	 * * Hibernate Session Variable
	 */
	Transaction transaction = null;
	/**
	 * * Hibernate Transaction Variable
	 */
	Transaction transaction1;
	//SessionFactory sessionFactory;
	
	private static final Logger logger = LogManager.getLogger(MessItemsDAO.class);
	
	public MessItemsDAO() {
		session = HibernateUtil.openCurrentSession();
	}

	

	public List<MessItems> getItemsDetails() {
		
        List<MessItems> results = new ArrayList<MessItems>();
        try {
                transaction = session.beginTransaction();
                results = (List<MessItems>) session.createQuery("From MessItems mi order by mi.id DESC").setCacheable(true).setCacheRegion("commonregion").list();
                transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
                
                hibernateException.printStackTrace();

        } finally {
    			HibernateUtil.closeSession();
        }
        return results;
}



	public MessItems addNewItem(MessStockAvailability messStockAvailability) {
		 
		try {
	            //this.session = sessionFactory.openCurrentSession();
	            transaction = session.beginTransaction();
	            session.save(messStockAvailability);
	            Query query = session.createQuery("update MessItems set externalid= concat(externalid,'_"+messStockAvailability.getMessitems().getId()+"') where id="+messStockAvailability.getMessitems().getId());
				query.executeUpdate();
				/*messStockAvailability.setItemid(messItems.getId());
				session.save(messStockAvailability);*/
	            transaction.commit();
	        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
	            
	            hibernateException.printStackTrace();
	        } finally {
	    			HibernateUtil.closeSession();
	        }
		 return messStockAvailability.getMessitems();
	}



	public boolean deleteItems(List<Integer> ids) {
        
        boolean result = false;
        try {
            transaction = session.beginTransaction();
            
            List<MessStockAvailability> stocklist = new ArrayList<MessStockAvailability>();
            Query queryStock = session.createQuery("from MessStockAvailability msa where msa.availablestock > 0 and msa.messitems.id IN (:ids) ");
            queryStock.setParameterList("ids", ids);
            stocklist = queryStock.getResultList();
            
            if (stocklist.isEmpty()) {
            	
            	Query queryMSA = session.createQuery("delete from MessStockAvailability msa where msa.messitems.id IN (:ids)");
                queryMSA.setParameterList("ids", ids);
                queryMSA.executeUpdate();
                
                Query queryMI = session.createQuery("delete from MessItems where id IN (:ids)");
                queryMI.setParameterList("ids", ids);
                queryMI.executeUpdate();
                
                result = true;
			}
            transaction.commit();
            
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
		}
        return result;
    }



	public boolean updateMultipleItems(List<MessStockAvailability> messStockAvailabilityList) {

        boolean result = false;
        try {
            transaction = session.beginTransaction();
            for (MessStockAvailability items : messStockAvailabilityList) {
                Query query = session.createQuery("update MessItems set name = '"+items.getMessitems().getName()+"', unitofmeasure = '"+items.getMessitems().getUnitofmeasure()+"' where id="+items.getMessitems().getId());
				query.executeUpdate();
				Query queryMessStock = session.createQuery("update MessStockAvailability set minstock = '"+items.getMinstock()+"' where itemid="+items.getMessitems().getId());
				queryMessStock.executeUpdate();
            }
            transaction.commit();
            result = true;
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
		}
        return result;
    
	}



	public boolean addNewStock(List<MessStockEntry> messStockEntryList, VoucherEntrytransactions transactions, String updateDrAccount, String updateCrAccount, VoucherEntrytransactions transactionTC, String updateTransportationDrAccount, String updateTransportationCrAccount) {
		 
				boolean result = false; 
		try {
	            //this.session = sessionFactory.openCurrentSession();
	            transaction = session.beginTransaction();
				session.save(transactions);
				Query query = session.createQuery(updateDrAccount);
				query.executeUpdate();
				Query query1 = session.createQuery(updateCrAccount);
				query1.executeUpdate();
				
				if(transactionTC != null) {
					session.save(transactionTC);
					Query queryDrTc = session.createQuery(updateTransportationDrAccount);
					queryDrTc.executeUpdate();
					Query queryCrTc = session.createQuery(updateTransportationCrAccount);
					queryCrTc.executeUpdate();
				}
				
	            
	            for (MessStockEntry messStockEntry : messStockEntryList) {
	            	session.save(messStockEntry);
					/*
					 * Query queryUpdateMessStock = session.
					 * createQuery("update MessStockEntry set externalid= concat(externalid,'_"
					 * +messStockEntry.getId()+"') where id="+messStockEntry.getId());
					 * queryUpdateMessStock.executeUpdate();
					 */
					Query queryStockAvailability = session.createQuery("update MessStockAvailability set availablestock= availablestock+'"+messStockEntry.getQuantity()+"' where itemid="+messStockEntry.getItemid());
					queryStockAvailability.executeUpdate();
					Query queryInvoice = session.createQuery("update MessInvoiceDetails set voucherid= '"+transactions.getTransactionsid()+"' where id="+messStockEntry.getMessinvoicedetails().getId());
					queryInvoice.executeUpdate();
				}
	            
	            transaction.commit();
	            result = true;
	        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
	            
	            hibernateException.printStackTrace();
	        } finally {
	    			HibernateUtil.closeSession();
	        }
		
		 return result;
	}



	public List<MessInvoiceDetails> getInvoiceDetailsPagination(int offset,
			int noOfRecords, int branchId) {
		
		List<MessInvoiceDetails> results = new ArrayList<MessInvoiceDetails>();

		try {
			
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("From MessInvoiceDetails as invoicedetails where invoicedetails.status != 'CANCELLED' and invoicedetails.branchid = "+branchId+" order by invoicedetails.invoicedate DESC").setCacheable(true).setCacheRegion("commonregion");
			query.setFirstResult(offset);   
			query.setMaxResults(noOfRecords);
			results = query.getResultList();
			
			transaction.commit();
			

		} catch (Exception hibernateException) {  transaction.rollback(); logger.error(hibernateException);
			
			System.out.println("Exception is "+hibernateException);
			hibernateException.printStackTrace();

		} finally {
				HibernateUtil.closeSession();
			return results;
		}
	}



	public int getTotalNoOfRecords(int branchId) {
		
		List<MessInvoiceDetails> results = new ArrayList<MessInvoiceDetails>();
		int noOfRecords = 0;
		try {
			// this.session =
			// HibernateUtil.getSessionFactory().openCurrentSession();
			transaction = session.beginTransaction();

			results = (List<MessInvoiceDetails>) session.createQuery("From MessInvoiceDetails as invoicedetails where invoicedetails.status != 'CANCELLED' and invoicedetails.branchid="+branchId).setCacheable(true).setCacheRegion("commonregion")
					.list();
			noOfRecords = results.size();
			logger.info("The size of MessInvoiceDetails is:::::::::::::::::::::::::::::::::::::::::: "+ noOfRecords);
			transaction.commit();

		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();

		} finally {
				HibernateUtil.closeSession();
			return noOfRecords;
		}
	}



	public List<MessStockAvailability> getItemsStockAvailability() {
		
        List<MessStockAvailability> results = new ArrayList<MessStockAvailability>();
        try {
                transaction = session.beginTransaction();
                results = (List<MessStockAvailability>) session.createQuery("From MessStockAvailability ms order by ms.messitems.name ASC").setCacheable(true).setCacheRegion("commonregion").list();
                transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
                
                hibernateException.printStackTrace();

        } finally {
    			HibernateUtil.closeSession();
        }
        return results;
}



	public MessItems getItem(Integer itemid) {
		
        MessItems results = new MessItems();
        
        try {
                transaction = session.beginTransaction();
                Query query = session.createQuery("From MessItems mi where mi.id="+itemid).setCacheable(true).setCacheRegion("commonregion");
                results = (MessItems) query.uniqueResult();
                transaction.commit();
                
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
                hibernateException.printStackTrace();
        } finally {
    			HibernateUtil.closeSession();
        }
        return results;
}



	public List<MessStockEntry> getItemsStockEntry() {
		
        List<MessStockEntry> results = new ArrayList<MessStockEntry>();
        try {
                transaction = session.beginTransaction();
                results = (List<MessStockEntry>) session.createQuery("From MessStockEntry ms where ms.availablequantity > 0 and ms.status != 'CANCELLED' order by ms.itemid DESC").setCacheable(true).setCacheRegion("commonregion").list();
                transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
                
                hibernateException.printStackTrace();

        } finally {
    			HibernateUtil.closeSession();
        }
        return results;
}



	public List<MessItems> getItemDetailByID(List<Integer> itemIds) {
        
		List<MessItems> result = new ArrayList<MessItems>();
        try {
            transaction = session.beginTransaction();
            
            Query queryItems = session.createQuery("from MessItems mi where mi.id IN (:ids) order by mi.id DESC").setCacheable(true).setCacheRegion("commonregion");
            queryItems.setParameterList("ids", itemIds);
            result = queryItems.getResultList();
            transaction.commit();
            
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
		}
        return result;
    }



	public boolean cancelPurchase(int invoiceId, List<MessStockEntry> messStockEntryList,
			String updateDrAccount, String updateCrAccount, String cancelVoucher) {
		
		boolean result = false;
		
		try {
            
			transaction = session.beginTransaction();
			
			//
            Query queryInvoiceDetails = session.createQuery("update MessInvoiceDetails set status='CANCELLED' where id = '"+invoiceId+"'");
            queryInvoiceDetails.executeUpdate();
            
            Query queryStock = session.createQuery("update MessStockEntry set status='CANCELLED' where invoicedetailsid = '"+invoiceId+"'");
            queryStock.executeUpdate();
            
            for (MessStockEntry messStockEntry : messStockEntryList) {
            	 Query queryStockAvailability = session.createQuery("update MessStockAvailability set availablestock=availablestock-'"+messStockEntry.getQuantity()+"' where itemid='"+messStockEntry.getItemid()+"'");
            	 queryStockAvailability.executeUpdate();
            }
            
            
            //Accounts Transactions
			Query updateDr = session.createQuery(updateDrAccount);
			updateDr.executeUpdate();
			Query updateCr = session.createQuery(updateCrAccount);
			updateCr.executeUpdate();
			Query cancelVoucherQuery = session.createQuery(cancelVoucher);
			cancelVoucherQuery.executeUpdate();
            
            transaction.commit();
            
            result = true;
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
		}
		
		return result;
	}



	public List<MessStockEntry> getMessStockEntry(Integer invIds) {
		
        List<MessStockEntry> results = new ArrayList<MessStockEntry>();
        try {
                transaction = session.beginTransaction();
                Query query = session.createQuery("From MessStockEntry mse where mse.messinvoicedetails.id = '"+invIds+"'");
                results = query.getResultList();
                transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
                
                hibernateException.printStackTrace();

        } finally {
    			HibernateUtil.closeSession();
        }
        return results;
}



	public MessStockEntry getMessStockEntryByID(Integer stockentryid) {
		
        MessStockEntry results = new MessStockEntry();
        try {
                transaction = session.beginTransaction();
                Query query = session.createQuery("From MessStockEntry mse where id = '"+stockentryid+"'");
                results = (MessStockEntry) query.uniqueResult();
                transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
                
                hibernateException.printStackTrace();

        } finally {
    			HibernateUtil.closeSession();
        }
        return results;
}



	public List<MessStockEntry> getMessStockEntryByIdList(List<Integer> messStockMoveIds) {
		
        List<MessStockEntry> results = new ArrayList<MessStockEntry>();
        try {
                transaction = session.beginTransaction();
                Query query = session.createQuery("From MessStockEntry mse where mse.id IN (:ids)");
                query.setParameterList("ids", messStockMoveIds);
                results = query.getResultList();
                transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
                
                hibernateException.printStackTrace();

        } finally {
    			HibernateUtil.closeSession();
        }
        return results;
}



	public List<MessStockEntry> getStockReceivedDetailsReport(String query) {
		
        List<MessStockEntry> results = new ArrayList<MessStockEntry>();
        
        try {
                transaction = session.beginTransaction();
                results = (List<MessStockEntry>) session.createQuery(query).setCacheable(true).setCacheRegion("commonregion").list();
                transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
                
                hibernateException.printStackTrace();

        } finally {
    			HibernateUtil.closeSession();
        }
        return results;
}



	public List<MessStockAvailability> getItemsStock() {
		
        List<MessStockAvailability> results = new ArrayList<MessStockAvailability>();
        try {
                transaction = session.beginTransaction();
                results = (List<MessStockAvailability>) session.createQuery("From MessStockAvailability ms where availablestock > 0 order by ms.messitems.name ASC").setCacheable(true).setCacheRegion("commonregion").list();
                transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
                
                hibernateException.printStackTrace();

        } finally {
    			HibernateUtil.closeSession();
        }
        return results;
}


	@SuppressWarnings("finally")
	public boolean addNewOrderDetail(List<PurchaseOrder> orderList, PoMaster poMaster) {
		boolean result = false;
		PoMaster poMasterExtid = new PoMaster();
		String externalId = null;
		
		try {
            //this.session = sessionFactory.openCurrentSession();
            transaction = session.beginTransaction();
            
            Query query = session.createQuery("from PoMaster as poMaster order by id desc");
            query.setMaxResults(1);
            poMasterExtid = (PoMaster) query.uniqueResult();
            
            if(poMasterExtid!=null) {
        		// order.setExternalId(order.getExternalId()+String.format("%04d", poMaster.getId()+1));
            	externalId = poMaster.getExternalId()+String.format("%05d", poMasterExtid.getId()+1);
             }else {
            	 externalId = poMaster.getExternalId()+String.format("%05d",1);
             }
            poMaster.setExternalId(externalId);
            session.save(poMaster);
            
            for (PurchaseOrder order : orderList) {
            	 order.setExternalId(externalId);
            	session.save(order);
			}
            transaction.commit();
            result = true;
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
    			HibernateUtil.closeSession();
            return result;
        }
	}



	public List<PoMaster> getPurchaseOrderMasterDetails() {
		List<PoMaster> results = new ArrayList<PoMaster>();
        try {
                transaction = session.beginTransaction();
                results = (List<PoMaster>) session.createQuery("From PoMaster").setCacheable(true).setCacheRegion("commonregion").list();
                transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
                
                hibernateException.printStackTrace();

        } finally {
    			HibernateUtil.closeSession();
        }
        return results;
	}



	public List<PurchaseOrder> getParticularInvoice(String externalId) {
		List<PurchaseOrder> results = new ArrayList<PurchaseOrder>();
	        try {
	                transaction = session.beginTransaction();
	                results = (List<PurchaseOrder>)session.createQuery("From PurchaseOrder mse where externalId = '"+externalId+"'");
	                transaction.commit();
	        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
	                
	                hibernateException.printStackTrace();

	        } finally {
	    			HibernateUtil.closeSession();
	        }
	        return results;
	}



	public boolean addPoMasteDetail(PoMaster poMaster) {
		boolean result = false;
		try {
			 //this.session = sessionFactory.openCurrentSession();
            transaction = session.beginTransaction();
            session.save(poMaster);
            transaction.commit();	
            result = true;
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
    			HibernateUtil.closeSession();
            return result;
        }
	}



	public void cancelPurchaseOrder(List<Integer> ids) {
		try {
			transaction = session.beginTransaction();
			
			
			Query query = session
					.createQuery("delete from PoMaster as poMaster where poMaster.id IN (:ids)");
			query.setParameterList("ids", ids);
			
			query.executeUpdate();
			
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}	
		
	}
	
	public List<MessInvoiceDetails> getInvoiceDetailsPaginationOpeningStock(int offset,
			int noOfRecords, int branchId) {
		
		List<MessInvoiceDetails> results = new ArrayList<MessInvoiceDetails>();

		try {
			
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("From MessInvoiceDetails as invoicedetails where invoicedetails.status != 'CANCELLED' and invoicedetails.branchid = "+branchId+" and invoicedetails.suppliersid = 0  order by invoicedetails.invoicedate DESC").setCacheable(true).setCacheRegion("commonregion");
			query.setFirstResult(offset);   
			query.setMaxResults(noOfRecords);
			results = query.getResultList();
			
			transaction.commit();
			

		} catch (Exception hibernateException) {  transaction.rollback(); logger.error(hibernateException);
			
			System.out.println("Exception is "+hibernateException);
			hibernateException.printStackTrace();

		} finally {
				HibernateUtil.closeSession();
			return results;
		}
	}



	public boolean addNewStockFromPO(List<MessStockEntry> messStockEntryList, VoucherEntrytransactions transactions, String updateDrAccount, String updateCrAccount, VoucherEntrytransactions transactionTC, String updateTransportationDrAccount, String updateTransportationCrAccount, String poNumber) {
		 
				boolean result = false; 
		try {
	            //this.session = sessionFactory.openCurrentSession();
	            transaction = session.beginTransaction();
				session.save(transactions);
				int totalQty = 0;
				Query query = session.createQuery(updateDrAccount);
				query.executeUpdate();
				Query query1 = session.createQuery(updateCrAccount);
				query1.executeUpdate();
				
				if(transactionTC != null) {
					session.save(transactionTC);
					Query queryDrTc = session.createQuery(updateTransportationDrAccount);
					queryDrTc.executeUpdate();
					Query queryCrTc = session.createQuery(updateTransportationCrAccount);
					queryCrTc.executeUpdate();
				}
				
	            
	            for (MessStockEntry messStockEntry : messStockEntryList) {
	            	session.save(messStockEntry);
					/*
					 * Query queryUpdateMessStock = session.
					 * createQuery("update MessStockEntry set externalid= concat(externalid,'_"
					 * +messStockEntry.getId()+"') where id="+messStockEntry.getId());
					 * queryUpdateMessStock.executeUpdate();
					 */
					Query queryStockAvailability = session.createQuery("update MessStockAvailability set availablestock= availablestock+'"+messStockEntry.getQuantity()+"' where itemid="+messStockEntry.getItemid());
					queryStockAvailability.executeUpdate();
					Query queryInvoice = session.createQuery("update MessInvoiceDetails set voucherid= '"+transactions.getTransactionsid()+"' where id="+messStockEntry.getMessinvoicedetails().getId());
					queryInvoice.executeUpdate();
					
					Query queryInvoicePO = session.createQuery("update PurchaseOrder set receivedquantity= '"+messStockEntry.getQuantity()+"' where itemid="+messStockEntry.getItemid()+" and externalid='"+poNumber+"'");
					queryInvoicePO.executeUpdate();
					
					totalQty = (int) (totalQty+messStockEntry.getQuantity());
				}
	            
	            Query queryInvoicePO = session.createQuery("update PoMaster set totalquantityreceived= '"+totalQty+"' where externalid='"+poNumber+"' ");
				queryInvoicePO.executeUpdate();
	            
	            transaction.commit();
	            result = true;
	        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
	            
	            hibernateException.printStackTrace();
	        } finally {
	    			HibernateUtil.closeSession();
	        }
		
		 return result;
	}

	
}
