package org.ideoholic.curium.model.mess.item.service;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.account.dao.AccountDAO;
import org.ideoholic.curium.model.account.dto.VoucherEntrytransactions;
import org.ideoholic.curium.model.mess.item.dao.MessItemsDAO;
import org.ideoholic.curium.model.mess.item.dto.*;
import org.ideoholic.curium.model.mess.stockentry.dto.MessInvoiceDetails;
import org.ideoholic.curium.model.mess.stockentry.dto.MessStockAvailability;
import org.ideoholic.curium.model.mess.stockentry.dto.MessStockEntry;
import org.ideoholic.curium.model.mess.stockmove.dao.MessStockMoveDAO;
import org.ideoholic.curium.model.mess.stockmove.dto.MessStockMove;
import org.ideoholic.curium.model.mess.supplier.action.MessSuppliersActionAdapter;
import org.ideoholic.curium.model.mess.supplier.dao.MessSuppliersDAO;
import org.ideoholic.curium.model.mess.supplier.dto.MessSuppliers;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.DateUtil;
import org.ideoholic.curium.util.StockIssuance;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class MessItemsService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;
	private String BRANCHID = "branchid";
	private String USERID = "userloginid";

	@Autowired
	private MessSuppliersActionAdapter messSuppliersActionAdapter;
	
	public MessItemsService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}


	public ResultResponse viewItemDetails(String branchId) {

		ResultResponse result = ResultResponse.builder().success(false).build();

		List<MessStockAvailability> messStockAvailabilityList = new ArrayList<MessStockAvailability>();
		
		 if(branchId!=null){
			 	messStockAvailabilityList = new MessItemsDAO().getItemsStockAvailability(); 
		 }
		 result.setResultList(messStockAvailabilityList);
		 result.setSuccess(true);
		return result;
	}


	public ItemDetailsResponseDto addItemDetails(ItemDetailsDto dto, String branchId, String userId) {
		
		MessItems messItems = new MessItems();
		MessStockAvailability messStockAvailability = new MessStockAvailability();

		ItemDetailsResponseDto result = ItemDetailsResponseDto.builder().build();

		 if(branchId!=null){
             
			 messItems.setName(DataUtil.emptyString(dto.getItemName()));
			 messItems.setExternalid(DataUtil.emptyString(dto.getItemName()));
			 messItems.setUnitofmeasure(DataUtil.emptyString(dto.getUnitOfMeasure()));
			 messItems.setBranchid(Integer.parseInt(branchId));
			 messItems.setLinkedledgerid(getLedgerAccountId("itemaccountid"+Integer.parseInt(branchId)));
			 messItems.setLinkedledgeridexpense(getLedgerAccountId("itemaccountidexpense"+Integer.parseInt(branchId)));
			 messItems.setUserid(Integer.parseInt(userId));
			 
			 messStockAvailability.setAvailablestock(0.0f);
			 messStockAvailability.setBranchid(Integer.parseInt(branchId));
			 messStockAvailability.setMinstock(DataUtil.parseInt(dto.getMinStock()));
			 messStockAvailability.setUserid(Integer.parseInt(userId));
			 
			 messStockAvailability.setMessitems(messItems);
			 
			 messItems= new MessItemsDAO().addNewItem(messStockAvailability);
			 
			 if(messItems.getId()!=null) {
				 result.setItemSave(true);
				 result.setItemName(messItems.getName());
				 result.setSuccess(true);
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


	public ResultResponse deleteMultipleItems(MessIdsDto dto) {
		ResultResponse resultResponse = ResultResponse.builder().build();

        String[] messIds = dto.getMessIds();
        if(messIds!=null){

       List<Integer> ids = new ArrayList<Integer>();
       for (String id : messIds) {
           String[] messId = id.split(":");
           ids.add(Integer.valueOf(messId[0]));
       }
       boolean result = new MessItemsDAO().deleteItems(ids);
			resultResponse.setSuccess(result);

        }
		resultResponse.setSuccess(true);
		return resultResponse;
	}


	public ResultResponse updateItems(ItemsDto dto) {
	ResultResponse resultResponse = ResultResponse.builder().build();

		String[] messIds = dto.getMessIds();

        if(messIds!=null){

            List<MessStockAvailability> messStockAvailabilityList = new ArrayList<>();

            for(int i=0; i<messIds.length;i++) {

            	MessStockAvailability messStockAvailability = new MessStockAvailability();
            	MessItems messItems = new MessItems();

                String itemId = messIds[i];
                messItems.setName(dto.getRequestParams().get("updateitemname_"+itemId));
                messItems.setUnitofmeasure(dto.getRequestParams().get("updateunitofmeasure_"+itemId));
                messItems.setId(DataUtil.parseInt(itemId));
                messStockAvailability.setMessitems(messItems);
                messStockAvailability.setMinstock(Integer.parseInt(dto.getRequestParams().get("updateminstock_"+itemId)));
                messStockAvailabilityList.add(messStockAvailability);
            }
            boolean result = new MessItemsDAO().updateMultipleItems(messStockAvailabilityList);
			resultResponse.setSuccess(result);
        }
		resultResponse.setSuccess(true);
		return resultResponse;
	}


	public ResultResponse savePurchase(PurchaseDto dto, String branchId, String userId) {
	ResultResponse resultResponse = ResultResponse.builder().build();
		
		if(branchId!=null){
			
			
				//String itemsTotal = request.getParameter("itemsTotalAmount");
				String itemsTotal = dto.getItemsTotal();
				BigDecimal itemsTotalAmount = new BigDecimal(itemsTotal);
				itemsTotalAmount = itemsTotalAmount.setScale(2, BigDecimal.ROUND_HALF_EVEN);
				
				String[] itemIds = dto.getItemIds();
				String[] itemsName = dto.getItemsName();
				String[] itemsQuantity = dto.getItemsQuantity();
				String[] salesPrice = dto.getSalesPrice();
				String[] batchNo = dto.getBatchNo();
				String[] lineTotal = dto.getLineTotal();
				String sup = dto.getSupplierId();
				String[] supplieridledgerid = sup.split(":");
				String randomString =  DataUtil.generateString(8);
				String[] purchasePrice = dto.getPurchasePrice();
				String[] sgst = dto.getSGst();
				String[] cgst = dto.getCGst();
				
				//Invoice Details
				MessInvoiceDetails messInvoiceDetails = new MessInvoiceDetails();
				messInvoiceDetails.setBranchid(Integer.parseInt(branchId));
				messInvoiceDetails.setExternalid("MRV");
				messInvoiceDetails.setInvoicedate(DateUtil.indiandateParser(dto.getInvoiceDate()));
				messInvoiceDetails.setEntrydate(DateUtil.todaysDate());
				messInvoiceDetails.setInvoicetotal(itemsTotalAmount.floatValue());
				messInvoiceDetails.setSupplierreferenceno(randomString+":"+dto.getSupplierReferenceNo());
				messInvoiceDetails.setSuppliersid(DataUtil.parseInt(supplieridledgerid[0]));
				messInvoiceDetails.setStatus("ACTIVE");
				
				List<MessStockEntry> messStockEntryList = new ArrayList<MessStockEntry>();
				
				if(itemIds!=null) {
					
					for(int i=0; i < itemIds.length ; i++){
						

						MessStockEntry messStockEntry = new MessStockEntry();
						
						messStockEntry.setItemid(Integer.parseInt(itemIds[i]));
						messStockEntry.setExternalid(itemsName[i]+"_"+salesPrice[i]);
						messStockEntry.setBatchno(batchNo[i]);
						messStockEntry.setReceiveddate(DateUtil.indiandateParser(dto.getItemEntryDate()));
						messStockEntry.setItemunitprice(Float.parseFloat(purchasePrice[i]));
						messStockEntry.setBranchid(Integer.parseInt(branchId));
						messStockEntry.setQuantity(Float.parseFloat(itemsQuantity[i]));
						messStockEntry.setAvailablequantity(Float.parseFloat(itemsQuantity[i]));
						messStockEntry.setMessinvoicedetails(messInvoiceDetails);
						messStockEntry.setSgst(Float.parseFloat(sgst[i]));
						messStockEntry.setCgst(Float.parseFloat(cgst[i]));
						messStockEntry.setStatus("ACTIVE");
						messStockEntry.setUserid(Integer.parseInt(userId));
						
						messStockEntryList.add(messStockEntry);
					
				}
					
						//Pass J.V. : credit the supplier debit the stock account
						int supplierLedgerId = DataUtil.parseInt(supplieridledgerid[1]);
						int stockLedgerId = getLedgerAccountId("itemaccountid"+Integer.parseInt(branchId));
						
						VoucherEntrytransactions transactions = new VoucherEntrytransactions();
						
						transactions.setDraccountid(stockLedgerId);
						transactions.setCraccountid(supplierLedgerId);
						transactions.setDramount(itemsTotalAmount);
						transactions.setCramount(itemsTotalAmount);
						transactions.setVouchertype(1);
						transactions.setTransactiondate(DateUtil.indiandateParser(dto.getInvoiceDate()));
						transactions.setEntrydate(DateUtil.todaysDate());
						transactions.setNarration("Towards New Stock Entry");
						transactions.setCancelvoucher("no");
						transactions.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(branchId)).getFinancialid());
						transactions.setBranchid(Integer.parseInt(branchId));
						transactions.setUserid(Integer.parseInt(userId));
						
						String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+itemsTotalAmount+" where accountdetailsid="+stockLedgerId;

						String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+itemsTotalAmount+" where accountdetailsid="+supplierLedgerId;
						
						
						//Pass J.V to book transportation charges
						
						int drTransportationExpense = getLedgerAccountId("transportationexpenses"+Integer.parseInt(branchId));
						int crSupplierLedgerId = DataUtil.parseInt(supplieridledgerid[1]);
						String transportationCharges = dto.getTransportationCharges();
						
						if(new BigDecimal(transportationCharges).compareTo(BigDecimal.ZERO) > 0) {
						
						VoucherEntrytransactions transactionTC = new VoucherEntrytransactions();
						
						transactionTC.setDraccountid(drTransportationExpense);
						transactionTC.setCraccountid(crSupplierLedgerId);
						transactionTC.setDramount(new BigDecimal(transportationCharges));
						transactionTC.setCramount(new BigDecimal(transportationCharges));
						transactionTC.setVouchertype(4);
						transactionTC.setTransactiondate(DateUtil.indiandateParser(dto.getInvoiceDate()));
						transactionTC.setEntrydate(DateUtil.todaysDate());
						transactionTC.setNarration("Towards transportation/labour charges. Ref. No:"+randomString+":"+dto.getSupplierReferenceNo());
						transactionTC.setCancelvoucher("no");
						transactionTC.setBranchid(Integer.parseInt(branchId));
						transactionTC.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(branchId)).getFinancialid());
						transactionTC.setUserid(Integer.parseInt(userId));

						// Dr
						BigDecimal totalAmount = new BigDecimal(transportationCharges);
						String updateTransportationDrAccount = "update Accountdetailsbalance set currentbalance=currentbalance+"+totalAmount+" where accountdetailsid="+drTransportationExpense;
							
						//Cr
						String updateTransportationCrAccount = "update Accountdetailsbalance set currentbalance=currentbalance+"+totalAmount+" where accountdetailsid="+crSupplierLedgerId;
						
						//End J.V
						boolean result = new MessItemsDAO().addNewStock(messStockEntryList,transactions,updateDrAccount,updateCrAccount,transactionTC,updateTransportationDrAccount,updateTransportationCrAccount);
						resultResponse.setSuccess(result);
						}else {
							boolean result = new MessItemsDAO().addNewStock(messStockEntryList,transactions,updateDrAccount,updateCrAccount,null,null,null);
							resultResponse.setSuccess(result);
						}
				}
				
			}
			resultResponse.setSuccess(true);
			return resultResponse;
		}


		public InvoiceDetailsResponseDto getInvoiceDetails(String strPage, String branchId) {
		InvoiceDetailsResponseDto result = InvoiceDetailsResponseDto.builder().success(false).build();

			//String pages = "1";
			if(branchId!=null){
				try {
					int page = 1;
					int recordsPerPage = 50;
						if (!"".equalsIgnoreCase(DataUtil.emptyString(strPage))) {
							page = Integer.parseInt(strPage);
						}

					List<MessInvoiceDetails> invoicelist = new MessItemsDAO().getInvoiceDetailsPagination((page - 1) * recordsPerPage,
							recordsPerPage, Integer.parseInt(branchId));
					
					Map<MessInvoiceDetails,MessSuppliers> invoiceSuppliersMap = new LinkedHashMap<MessInvoiceDetails,MessSuppliers>();
					
					for (MessInvoiceDetails messInvoiceDetails : invoicelist) {
						MessSuppliers messSuppliers = new MessSuppliers();
						messSuppliers = new MessSuppliersDAO().getMessSupplierById(messInvoiceDetails.getSuppliersid());
						invoiceSuppliersMap.put(messInvoiceDetails, messSuppliers);
					}
					result.setInvoiceSuppliersMap(invoiceSuppliersMap);
					
					int noOfRecords = new MessItemsDAO().getTotalNoOfRecords(Integer.parseInt(branchId));
					int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
					result.setNoOfPages(noOfPages);
					result.setCurrentPage(page);
					result.setSuccess(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			return result;
		}


		public ResultResponse cancelPurchase(InvoiceIdsDto dto) {
				
			String[] ids = dto.getInvoiceId();
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
			return ResultResponse
					.builder()
					.success(true)
					.build();
		}


		public ResultResponse getCurrentStock() {
			ResultResponse result = ResultResponse.builder().build();

			List<MessStockAvailability> messStockAvailability = new MessItemsDAO().getItemsStockAvailability();
			result.setResultList(messStockAvailability);
			result.setSuccess(true);

			return result;
		}


		public ResultResponse getBatchStock() {
			ResultResponse result = ResultResponse.builder().build();

			List<MessStockEntry> messStockEntryList = new MessItemsDAO().getItemsStockEntry();
			result.setResultList(messStockEntryList);
			result.setSuccess(true);

			return result;
		}


		public ResultResponse getIssuanceStock() {
			ResultResponse result = ResultResponse.builder().build();
			
			List<MessItems> messItemsList =  new MessItemsDAO().getItemsDetails();
			result.setResultList(messItemsList);
			result.setSuccess(true);

			return result;
		}


		public IssuanceReportResponseDto generateStockIssuanceReport(IssuanceReportDto dto) {
		IssuanceReportResponseDto responseDto = IssuanceReportResponseDto.builder().build();
			
			String fromDate = DateUtil.dateFromatConversionSlash(dto.getFromDate());
			String toDate = DateUtil.dateFromatConversionSlash(dto.getToDate());
			String issueTo = dto.getIssueTo();
			String purpose = dto.getPurpose();
			String item = dto.getItem();
			String queryMain = "from MessStockMove msm where msm.status != 'CANCELLED' and msm.transactiondate between '"+fromDate+"' and '"+toDate+"' ";
			String subQuery = "";
			List<StockIssuance> stockIssuanceList = new ArrayList<StockIssuance>();
					
			if(!issueTo.isEmpty()) {
				subQuery = "and issuedto = '"+issueTo+"'";
				responseDto.setIssuedToSelected("Issued To:&nbsp;"+issueTo);
			}else {
				responseDto.setIssuedToSelected("");
			}
			
			/*
			 * if(!purpose.isEmpty()) { subQuery = subQuery + "and purpose = '"+purpose+"'";
			 * httpSession.setAttribute("purposeselected", "Purpose:&nbsp;"+purpose); }else
			 * { httpSession.setAttribute("purposeselected", ""); }
			 */
			
			if(!item.isEmpty()) {
				subQuery = subQuery + "and itemid = '"+item+"'";
				responseDto.setItemSelected("Items:&nbsp;"+item);
			}else {
				responseDto.setItemSelected("");
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
									int messItemId = messItems.getId();
									int messStockMoveInteger = messStockMove.getItemid();
								if(messItemId==messStockMoveInteger) {
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
			responseDto.setStockIssuanceList(stockIssuanceList);
			responseDto.setTransactionFromDateSelected("From:"+ dto.getFromDate());
			responseDto.setTransactionToDateSelected("To:"+dto.getToDate());
			
			getIssuanceStock();

			responseDto.setSuccess(true);
			return responseDto;
		}


		public ResultResponse receiveStockReport() {
			ResultResponse result = ResultResponse.builder().build();

			messSuppliersActionAdapter.viewSuppliersDetails();
			
			List<MessItems> messItemsList =  new MessItemsDAO().getItemsDetails();
			result.setResultList(messItemsList);
			result.setSuccess(true);

			return result;
		}


		public StockReportResponseDto generateStockReceivedReport(StockReportDto dto) {
			StockReportResponseDto responseDto = StockReportResponseDto.builder().build();

			String fromDate = DateUtil.dateFromatConversionSlash(dto.getFromDate());
			String toDate = DateUtil.dateFromatConversionSlash(dto.getToDate());
			String supplierid = dto.getSupplierId();
			String[] suppNameId = supplierid.split(":");
			String item = dto.getItem();
			String[] itemNameId = item.split(":");
			String queryMain = "From MessStockEntry ms where ms.status != 'CANCELLED' and ms.messinvoicedetails.entrydate between '"+fromDate+"' and '"+toDate+"' ";
			String subQuery = "";
			
					
			if(!"ALL".equalsIgnoreCase(suppNameId[0])) {
				subQuery = "and ms.messinvoicedetails.suppliersid = '"+Integer.parseInt(suppNameId[0])+"'";
				responseDto.setSupplierSelected("Supplier :&nbsp;"+suppNameId[1]);

			}else {
				responseDto.setSupplierSelected("");
			}
			
			if(!itemNameId[0].isEmpty()) {
				subQuery = subQuery + "and ms.itemid = '"+itemNameId[0]+"'";
				responseDto.setItemSelected("Item:&nbsp;"+itemNameId[1]);
			}else {
				responseDto.setItemSelected("");
			}
			
			List<MessStockEntry> messStockEntryList = new MessItemsDAO().getStockReceivedDetailsReport(queryMain+subQuery);

			responseDto.setMessStockEntryList(messStockEntryList);
			responseDto.setTransactionFromDateSelected("From:"+dto.getFromDate());
			responseDto.setTransactionToDateSelected("To:"+dto.getToDate());
			
			receiveStockReport();

			responseDto.setSuccess(true);

			return responseDto;
		}


		public ResultResponse getCurrentStockToIssue() {
			List<MessStockAvailability> messStockAvailability = new MessItemsDAO().getItemsStock();

			return ResultResponse
					.builder()
					.resultList(messStockAvailability)
					.success(true)
					.build();
		}

}
