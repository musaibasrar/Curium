package org.ideoholic.curium.model.mess.item.service;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.account.dao.AccountDAO;
import org.ideoholic.curium.model.account.dto.VoucherEntrytransactions;
import org.ideoholic.curium.model.mess.item.dao.MessItemsDAO;
import org.ideoholic.curium.model.mess.item.dto.MessItems;
import org.ideoholic.curium.model.mess.stockentry.dto.MessInvoiceDetails;
import org.ideoholic.curium.model.mess.stockentry.dto.MessStockAvailability;
import org.ideoholic.curium.model.mess.stockentry.dto.MessStockEntry;
import org.ideoholic.curium.model.mess.stockmove.dao.MessStockMoveDAO;
import org.ideoholic.curium.model.mess.stockmove.dto.MessStockMove;
import org.ideoholic.curium.model.mess.supplier.dao.MessSuppliersDAO;
import org.ideoholic.curium.model.mess.supplier.dto.MessSuppliers;
import org.ideoholic.curium.model.mess.supplier.service.MessSuppliersService;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.DateUtil;
import org.ideoholic.curium.util.StockIssuance;

public class MessItemsService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;
	private String BRANCHID = "branchid";
	private String USERID = "userloginid";
	
	public MessItemsService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}


	public String viewItemDetails() {
		
		List<MessStockAvailability> messStockAvailabilityList = new ArrayList<MessStockAvailability>();
		
		 if(httpSession.getAttribute(BRANCHID)!=null){
			 	messStockAvailabilityList = new MessItemsDAO().getItemsStockAvailability(); 
		 }
		 
		 request.setAttribute("messstockavailabilitylist", messStockAvailabilityList);
		 
		return "additems";
	}


	public String addItemDetails() {
		
		MessItems messItems = new MessItems();
		MessStockAvailability messStockAvailability = new MessStockAvailability();
		String result = "false";
		 if(httpSession.getAttribute(BRANCHID)!=null){
             
			 messItems.setName(DataUtil.emptyString(request.getParameter("itemname")));
			 messItems.setExternalid(DataUtil.emptyString(request.getParameter("itemname")));
			 messItems.setUnitofmeasure(DataUtil.emptyString(request.getParameter("unitofmeasure")));
			 messItems.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			 messItems.setLinkedledgerid(getLedgerAccountId("itemaccountid"+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())));
			 messItems.setLinkedledgeridexpense(getLedgerAccountId("itemaccountidexpense"+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())));
			 messItems.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
			 
			 messStockAvailability.setAvailablestock(0.0f);
			 messStockAvailability.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			 messStockAvailability.setMinstock(DataUtil.parseInt(request.getParameter("minstock")));
			 messStockAvailability.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
			 
			 messStockAvailability.setMessitems(messItems);
			 
			 messItems= new MessItemsDAO().addNewItem(messStockAvailability);
			 
			 if(messItems.getId()!=null) {
				 request.setAttribute("itemsave", true);
				 request.setAttribute("itemname", messItems.getName());
				 result = "true";
			 }
		 }
		return result;
	}


	private Integer getLedgerAccountId(String itemAccount) {
		
	 	
	 	Properties properties = new Properties();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("Util.properties");
		
        		try {
					properties.load(inputStream);
				} catch (IOException e) {
					e.printStackTrace();
				}
		    
        		String ItemLedgerId = properties.getProperty(itemAccount);
		    
		    if(ItemLedgerId!=null) {
		    	return Integer.parseInt(ItemLedgerId);
		    }else {
		    	return 0;
		    }
	}


	public void deleteMultipleItems() {
        
        String[] messIds = request.getParameterValues("messitemsids");
        if(messIds!=null){
        
       List<Integer> ids = new ArrayList<Integer>();
       for (String id : messIds) {
           String[] messId = id.split(":");
           ids.add(Integer.valueOf(messId[0]));
       		}
       boolean result = new MessItemsDAO().deleteItems(ids);
       request.setAttribute("itemsdelete", result);
        
        }
	}


	public void updateItems() {
		

		String[] messIds = request.getParameterValues("messitemsids");
        
        if(messIds!=null){
            
            List<MessStockAvailability> messStockAvailabilityList = new ArrayList<MessStockAvailability>();
            
            for(int i=0; i<messIds.length;i++) {
            	
            	MessStockAvailability messStockAvailability = new MessStockAvailability();
            	MessItems messItems = new MessItems();
            	
                String itemId = messIds[i];
                messItems.setName(request.getParameter("updateitemname_"+itemId));
                messItems.setUnitofmeasure(request.getParameter("updateunitofmeasure_"+itemId));
                messItems.setId(DataUtil.parseInt(itemId));
                messStockAvailability.setMessitems(messItems);
                messStockAvailability.setMinstock(Integer.parseInt(request.getParameter("updateminstock_"+itemId)));
                messStockAvailabilityList.add(messStockAvailability);
            }
            boolean result = new MessItemsDAO().updateMultipleItems(messStockAvailabilityList);
            request.setAttribute("itemsupdate", result);
        }
		
	}


	public void savePurchase() {

		
		if(httpSession.getAttribute(BRANCHID)!=null){
			
			
				//String itemsTotal = request.getParameter("itemsTotalAmount");
				String itemsTotal = request.getParameter("itemsGrandTotalAmountWithoutGST");
				BigDecimal itemsTotalAmount = new BigDecimal(itemsTotal);
				itemsTotalAmount = itemsTotalAmount.setScale(2, BigDecimal.ROUND_HALF_EVEN);
				
				String[] itemIds = request.getParameterValues("itemids");
				String[] itemsName = request.getParameterValues("itemsname");
				String[] itemsQuantity = request.getParameterValues("itemsquantity");
				String[] salesPrice = request.getParameterValues("price");
				String[] batchNo = request.getParameterValues("batchno");
				String[] lineTotal = request.getParameterValues("linetotal");
				String sup = request.getParameter("supplierid");
				String[] supplieridledgerid = sup.split(":");
				String randomString =  DataUtil.generateString(8);
				String[] purchasePrice = request.getParameterValues("purchaseprice");
				String[] sgst = request.getParameterValues("sgst");
				String[] cgst = request.getParameterValues("cgst");
				
				//Invoice Details
				MessInvoiceDetails messInvoiceDetails = new MessInvoiceDetails();
				messInvoiceDetails.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
				messInvoiceDetails.setExternalid("MRV");
				messInvoiceDetails.setInvoicedate(DateUtil.indiandateParser(request.getParameter("invoicedate")));
				messInvoiceDetails.setEntrydate(DateUtil.todaysDate());
				messInvoiceDetails.setInvoicetotal(itemsTotalAmount.floatValue());
				messInvoiceDetails.setSupplierreferenceno(randomString+":"+request.getParameter("supplierreferenceno"));
				messInvoiceDetails.setSuppliersid(DataUtil.parseInt(supplieridledgerid[0]));
				messInvoiceDetails.setStatus("ACTIVE");
				
				List<MessStockEntry> messStockEntryList = new ArrayList<MessStockEntry>();
				
				if(itemIds!=null) {
					
					for(int i=0; i < itemIds.length ; i++){
						

						MessStockEntry messStockEntry = new MessStockEntry();
						
						messStockEntry.setItemid(Integer.parseInt(itemIds[i]));
						messStockEntry.setExternalid(itemsName[i]+"_"+salesPrice[i]);
						messStockEntry.setBatchno(batchNo[i]);
						messStockEntry.setReceiveddate(DateUtil.indiandateParser(request.getParameter("itementrydate")));
						messStockEntry.setItemunitprice(Float.parseFloat(purchasePrice[i]));
						messStockEntry.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
						messStockEntry.setQuantity(Float.parseFloat(itemsQuantity[i]));
						messStockEntry.setAvailablequantity(Float.parseFloat(itemsQuantity[i]));
						messStockEntry.setMessinvoicedetails(messInvoiceDetails);
						messStockEntry.setSgst(Float.parseFloat(sgst[i]));
						messStockEntry.setCgst(Float.parseFloat(cgst[i]));
						messStockEntry.setStatus("ACTIVE");
						messStockEntry.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
						
						messStockEntryList.add(messStockEntry);
					
				}
					
						//Pass J.V. : credit the supplier debit the stock account
						int supplierLedgerId = DataUtil.parseInt(supplieridledgerid[1]);
						int stockLedgerId = getLedgerAccountId("itemaccountid"+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
						
						VoucherEntrytransactions transactions = new VoucherEntrytransactions();
						
						transactions.setDraccountid(stockLedgerId);
						transactions.setCraccountid(supplierLedgerId);
						transactions.setDramount(itemsTotalAmount);
						transactions.setCramount(itemsTotalAmount);
						transactions.setVouchertype(1);
						transactions.setTransactiondate(DateUtil.indiandateParser(request.getParameter("invoicedate")));
						transactions.setEntrydate(DateUtil.todaysDate());
						transactions.setNarration("Towards New Stock Entry");
						transactions.setCancelvoucher("no");
						transactions.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())).getFinancialid());
						transactions.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
						transactions.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
						
						String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+itemsTotalAmount+" where accountdetailsid="+stockLedgerId;

						String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+itemsTotalAmount+" where accountdetailsid="+supplierLedgerId;
						
						
						//Pass J.V to book transportation charges
						
						int drTransportationExpense = getLedgerAccountId("transportationexpenses"+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
						int crSupplierLedgerId = DataUtil.parseInt(supplieridledgerid[1]);
						String transportationCharges = request.getParameter("transportationcharges");
						
						if(new BigDecimal(transportationCharges).compareTo(BigDecimal.ZERO) > 0) {
						
						VoucherEntrytransactions transactionTC = new VoucherEntrytransactions();
						
						transactionTC.setDraccountid(drTransportationExpense);
						transactionTC.setCraccountid(crSupplierLedgerId);
						transactionTC.setDramount(new BigDecimal(transportationCharges));
						transactionTC.setCramount(new BigDecimal(transportationCharges));
						transactionTC.setVouchertype(4);
						transactionTC.setTransactiondate(DateUtil.indiandateParser(request.getParameter("invoicedate")));
						transactionTC.setEntrydate(DateUtil.todaysDate());
						transactionTC.setNarration("Towards transportation/labour charges. Ref. No:"+randomString+":"+request.getParameter("supplierreferenceno"));
						transactionTC.setCancelvoucher("no");
						transactionTC.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
						transactionTC.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())).getFinancialid());
						transactionTC.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));

						// Dr
						BigDecimal totalAmount = new BigDecimal(transportationCharges);
						String updateTransportationDrAccount = "update Accountdetailsbalance set currentbalance=currentbalance+"+totalAmount+" where accountdetailsid="+drTransportationExpense;
							
						//Cr
						String updateTransportationCrAccount = "update Accountdetailsbalance set currentbalance=currentbalance+"+totalAmount+" where accountdetailsid="+crSupplierLedgerId;
						
						//End J.V
						boolean result = new MessItemsDAO().addNewStock(messStockEntryList,transactions,updateDrAccount,updateCrAccount,transactionTC,updateTransportationDrAccount,updateTransportationCrAccount);
						request.setAttribute("itemsreceived",result);
						}else {
							boolean result = new MessItemsDAO().addNewStock(messStockEntryList,transactions,updateDrAccount,updateCrAccount,null,null,null);
							request.setAttribute("itemsreceived",result);
						}
				}
				
			}
		}


		public boolean getInvoiceDetails() {

			boolean result = false;
			//String pages = "1";
			if(httpSession.getAttribute(BRANCHID)!=null){
				try {
					int page = 1;
					int recordsPerPage = 50;
						if (!"".equalsIgnoreCase(DataUtil.emptyString(request.getParameter("page")))) {
							page = Integer.parseInt(request.getParameter("page"));
						}

					List<MessInvoiceDetails> invoicelist = new MessItemsDAO().getInvoiceDetailsPagination((page - 1) * recordsPerPage,
							recordsPerPage, Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
					
					Map<MessInvoiceDetails,MessSuppliers> invoiceSuppliersMap = new LinkedHashMap<MessInvoiceDetails,MessSuppliers>();
					
					for (MessInvoiceDetails messInvoiceDetails : invoicelist) {
						MessSuppliers messSuppliers = new MessSuppliers();
						messSuppliers = new MessSuppliersDAO().getMessSupplierById(messInvoiceDetails.getSuppliersid());
						invoiceSuppliersMap.put(messInvoiceDetails, messSuppliers);
					}
					
					request.setAttribute("invoicelist", invoiceSuppliersMap);
					
					int noOfRecords = new MessItemsDAO().getTotalNoOfRecords(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
					int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
					request.setAttribute("noOfPages", noOfPages);
					request.setAttribute("currentPage", page);
					result = true;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			return result;
		}


		public void cancelPurchase() {
				
			String[] ids = request.getParameterValues("invoiceid");
			Date now = new Date();
	        String pattern = "yyyy-MM-dd";
	        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
	        String todaysDate = formatter.format(now);
			for (String invIds : ids) {
				
				String[] ivids = invIds.split(":");
				int invoiceId = Integer.parseInt(ivids[0]);
				int voucherId = Integer.parseInt(ivids[1]);
				String stockEntrystatus = ivids[2];
				
				if(!"MOVED".equalsIgnoreCase(stockEntrystatus)) {
				
				List<MessStockEntry> messStockEntryList = new MessItemsDAO().getMessStockEntry(invoiceId);
				
				VoucherEntrytransactions voucherTransaction = new AccountDAO().getVoucherDetails(ivids[1]);
				
				String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+voucherTransaction.getDramount()+" where accountdetailsid="+voucherTransaction.getDraccountid();
				String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+voucherTransaction.getCramount()+" where accountdetailsid="+voucherTransaction.getCraccountid();
				String cancelVoucher = "update VoucherEntrytransactions set cancelvoucher='yes', vouchercancellationdate='"+todaysDate+"' where transactionsid="+voucherId;
				
				new MessItemsDAO().cancelPurchase(invoiceId,messStockEntryList,updateDrAccount, updateCrAccount, cancelVoucher);
				
				}else {
					System.out.println("Can't cancel receive voucher");
				}
			}
			
		}


		public void getCurrentStock() {
			List<MessStockAvailability> messStockAvailability = new MessItemsDAO().getItemsStockAvailability();
			
			request.setAttribute("currentstocklist", messStockAvailability);
		}


		public void getBatchStock() {
			List<MessStockEntry> messStockEntryList = new MessItemsDAO().getItemsStockEntry();
			request.setAttribute("messstockentrylist", messStockEntryList);
			
		}


		public void getIssuanceStock() {
			
			List<MessItems> messItemsList =  new MessItemsDAO().getItemsDetails();
			request.setAttribute("itemslist", messItemsList);
			
		}


		public void generateStockIssuanceReport() {
			
			String fromDate = DateUtil.dateFromatConversionSlash(request.getParameter("transactiondatefrom"));
			String toDate = DateUtil.dateFromatConversionSlash(request.getParameter("transactiondateto"));
			String issueTo = request.getParameter("issuedto");
			String purpose = request.getParameter("purpose");
			String item = request.getParameter("itemname");
			String queryMain = "from MessStockMove msm where msm.status != 'CANCELLED' and msm.transactiondate between '"+fromDate+"' and '"+toDate+"' ";
			String subQuery = "";
			List<StockIssuance> stockIssuanceList = new ArrayList<StockIssuance>();
					
			if(!issueTo.isEmpty()) {
				subQuery = "and issuedto = '"+issueTo+"'";
				httpSession.setAttribute("issuedtoselected", "Issued To:&nbsp;"+issueTo);
			}else {
				httpSession.setAttribute("issuedtoselected", "");
			}
			
			/*
			 * if(!purpose.isEmpty()) { subQuery = subQuery + "and purpose = '"+purpose+"'";
			 * httpSession.setAttribute("purposeselected", "Purpose:&nbsp;"+purpose); }else
			 * { httpSession.setAttribute("purposeselected", ""); }
			 */
			
			if(!item.isEmpty()) {
				subQuery = subQuery + "and itemid = '"+item+"'";
				httpSession.setAttribute("itemselected", "Items:&nbsp;"+item);
			}else {
				httpSession.setAttribute("itemselected", "");
			}
			
			List<MessStockMove> messStockMoveList = new MessStockMoveDAO().getStockMoveDetailsReport(queryMain+subQuery);
			
			if(!messStockMoveList.isEmpty()) {
				
			List<Integer> messStockMoveIds = new ArrayList<Integer>();
			List<Integer> messStockItemIds = new ArrayList<Integer>();
				
			for (MessStockMove messStockMove : messStockMoveList) {
				messStockMoveIds.add(messStockMove.getStockentryid());
				messStockItemIds.add(messStockMove.getItemid());
			}
			
			List<MessStockEntry> messStockEntryList = new MessItemsDAO().getMessStockEntryByIdList(messStockMoveIds);
			List<MessItems> messItemsList = new MessItemsDAO().getItemDetailByID(messStockItemIds);
			
			for (MessStockMove messStockMove : messStockMoveList) {
				
					StockIssuance stockIssuance = new StockIssuance();
				for (MessStockEntry stockEntry : messStockEntryList) {
					
					if(messStockMove.getStockentryid().equals(stockEntry.getId())) {
						
							for (MessItems messItems : messItemsList) {
								
								if(messItems.getId().equals(messStockMove.getItemid())) {
									
									stockIssuance.setItemname(messItems.getName());
									stockIssuance.setQuantity(messStockMove.getQuantity());
									stockIssuance.setUnitofmeasure(messItems.getUnitofmeasure());
									stockIssuance.setItemunitprice(stockEntry.getItemunitprice());
									stockIssuance.setIssuedto(messStockMove.getIssuedto());
									stockIssuance.setPurpose(messStockMove.getPurpose());
									stockIssuance.setTransactiondate(messStockMove.getTransactiondate());
									stockIssuanceList.add(stockIssuance);	
									break;
									
								}
								
							}
							break;
					}
				}
				
			}
			
			/*for (MessStockMove messStockMove : messStockMoveList) {
				StockIssuance stockIssuance = new StockIssuance();
				
				for (MessStockEntry stockEntry : messStockEntryList) {
					
						for (MessItems stockItems : messItemsList) {
							
							
							if(messStockMove.getItemid().equals(stockEntry.getItemid())) {
								
								if(stockItems.getId().equals(stockEntry.getItemid())) {
									stockIssuance.setItemname(stockItems.getName());
									stockIssuance.setQuantity(messStockMove.getQuantity());
									stockIssuance.setUnitofmeasure(stockItems.getUnitofmeasure());
									stockIssuance.setItemunitprice(stockEntry.getItemunitprice());
									stockIssuance.setIssuedto(messStockMove.getIssuedto());
									stockIssuance.setPurpose(messStockMove.getPurpose());
									stockIssuance.setTransactiondate(messStockMove.getTransactiondate());
									System.out.println(" Item Name is "+stockItems.getName());
									stockIssuanceList.add(stockIssuance);
									break;
								}
								
								break;
							}
							
							if(messStockMove.getStockentryid() == stockEntry.getId()) {
								
							if(stockEntry.getItemid() == stockItems.getId()) {
								stockIssuance.setItemname(stockItems.getName());
								stockIssuance.setQuantity(messStockMove.getQuantity());
								stockIssuance.setUnitofmeasure(stockItems.getUnitofmeasure());
								stockIssuance.setItemunitprice(stockEntry.getItemunitprice());
								stockIssuance.setIssuedto(messStockMove.getIssuedto());
								stockIssuance.setPurpose(messStockMove.getPurpose());
								stockIssuance.setTransactiondate(messStockMove.getTransactiondate());
								stockIssuanceList.add(stockIssuance);
							}
							
						}
						
					}
				}
			}*/
			
			}
			httpSession.setAttribute("stockissuancelist", stockIssuanceList);
			httpSession.setAttribute("transactionfromdateselected", "From:"+request.getParameter("transactiondatefrom"));
			httpSession.setAttribute("transactiontodateselected", "To:"+request.getParameter("transactiondateto"));
			
			getIssuanceStock();
		}


		public void receiveStockReport() {
			
			new MessSuppliersService(request, response).viewSuppliersDetails();
			
			List<MessItems> messItemsList =  new MessItemsDAO().getItemsDetails();
			request.setAttribute("itemslist", messItemsList);
		
		
		
		}


		public void generateStockReceivedReport() {
			
			String fromDate = DateUtil.dateFromatConversionSlash(request.getParameter("transactiondatefrom"));
			String toDate = DateUtil.dateFromatConversionSlash(request.getParameter("transactiondateto"));
			String supplierid = request.getParameter("supplier");
			String[] suppNameId = supplierid.split(":");
			String item = request.getParameter("itemname");
			String[] itemNameId = item.split(":");
			String queryMain = "From MessStockEntry ms where ms.status != 'CANCELLED' and ms.messinvoicedetails.entrydate between '"+fromDate+"' and '"+toDate+"' ";
			String subQuery = "";
			
					
			if(!"ALL".equalsIgnoreCase(suppNameId[0])) {
				subQuery = "and ms.messinvoicedetails.suppliersid = '"+Integer.parseInt(suppNameId[0])+"'";
				httpSession.setAttribute("supplierselected", "Supplier :&nbsp;"+suppNameId[1]);
			}else {
				httpSession.setAttribute("supplierselected", "");
			}
			
			if(!itemNameId[0].isEmpty()) {
				subQuery = subQuery + "and ms.itemid = '"+itemNameId[0]+"'";
				httpSession.setAttribute("itemselected", "Item:&nbsp;"+itemNameId[1]);
			}else {
				httpSession.setAttribute("itemselected", "");
			}
			
			List<MessStockEntry> messStockEntryList = new MessItemsDAO().getStockReceivedDetailsReport(queryMain+subQuery);
			
			httpSession.setAttribute("messstockentrylist", messStockEntryList);
			httpSession.setAttribute("transactionfromdateselected", "From:"+request.getParameter("transactiondatefrom"));
			httpSession.setAttribute("transactiontodateselected", "To:"+request.getParameter("transactiondateto"));
			
			receiveStockReport();
			
		}


		public void getCurrentStockToIssue() {
			List<MessStockAvailability> messStockAvailability = new MessItemsDAO().getItemsStock();
			
			request.setAttribute("stocklist", messStockAvailability);
		}

}
