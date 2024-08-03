package org.ideoholic.curium.model.mess.stockmove.service;

import lombok.extern.slf4j.Slf4j;
import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.account.dao.AccountDAO;
import org.ideoholic.curium.model.account.dto.VoucherEntrytransactions;
import org.ideoholic.curium.model.mess.item.dao.MessItemsDAO;
import org.ideoholic.curium.model.mess.item.dto.MessItems;
import org.ideoholic.curium.model.mess.item.service.MessItemsService;
import org.ideoholic.curium.model.mess.stockentry.dao.MessStockEntryDAO;
import org.ideoholic.curium.model.mess.stockentry.dto.MessStockEntry;
import org.ideoholic.curium.model.mess.stockmove.dao.MessStockMoveDAO;
import org.ideoholic.curium.model.mess.stockmove.dto.*;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.DateUtil;
import org.ideoholic.curium.util.NumberToWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

@Slf4j
@Service
public class MessStockMoveService {

	@Autowired
	private HttpServletResponse response;

	@Autowired
	private MessItemsService messItemsService;

	
	
	
	public MoveStockResponseDto saveStockMove(StockMoveDto dto, String branchId, String userId, String userName) {

	MoveStockResponseDto results = MoveStockResponseDto.builder().build();

	if(branchId!=null){
		
		
			String[] StockEntryIds = dto.getStockEntryIds();
			String[] itemsName = dto.getItemsName();
			String[] itemsIds = dto.getItemsIds();
			String[] issuequantity = dto.getIssueQuantity();
			String[] itemunitprice = dto.getItemUintPrice();
			String[] purchaseprice = dto.getPurchasePrice();
			String[] custDetails = dto.getCustDetails().split("_");
			String[] sgst = dto.getStateGst();
			String[] cgst = dto.getCenterGst();
			String[] uom = dto.getUom();
			String[] batchno = dto.getBatchNo();
			String[] singleItemTotal = dto.getSingleItemTotal();
			BigDecimal totalValue = BigDecimal.ZERO;
			BigDecimal PurchasePricetotalValue = BigDecimal.ZERO;
			
			
			//Get Payment Details
			String paymentmethodbanktransfer = dto.getPaymentMethodBankTransfer();
			String paymentmethodchequetransfer = dto.getPaymentMethodChequeTransfer();
			String paymentmethodcash = dto.getPaymentMethodCash();
			String ackNo = dto.getAckNo();;
			String ackNoVoucherNarration = "";
			String transferDate = dto.getTransferDate();
			String transferBankname = dto.getTransferBankName();
			String chequeNo = dto.getChequeNo();
			String chequeNoVoucherNarration = "";
			String chequeDate = dto.getChequeDate();
			String chequeBankname = dto.getChequeBankName();
			String paymentType = "Cash";
			String totalCashAmount = dto.getTotalCashAmount();
			String totalBankTransferAmount = dto.getTotalBankTransferAmount();
			String totalChequetransferAmount = dto.getTotalChequeTransferAmount();
			
			String itemsGrandTotalAmountWOGST = dto.getItemsGrandTotalAmountWOGST();
			BigDecimal itemsGrandTotalAmountWithoutGST = BigDecimal.ZERO;
			BigDecimal totalCashAmountBD = new BigDecimal(totalCashAmount);
			BigDecimal totalBankTransferAmountBD = new BigDecimal(totalBankTransferAmount);
			BigDecimal totalChequetransferAmountBD = new BigDecimal(totalChequetransferAmount);
			itemsGrandTotalAmountWithoutGST = new BigDecimal(itemsGrandTotalAmountWOGST);
			BigDecimal difference = (totalCashAmountBD.add(totalBankTransferAmountBD).add(totalChequetransferAmountBD)).subtract(itemsGrandTotalAmountWithoutGST);
			

				if(totalCashAmountBD.compareTo(totalBankTransferAmountBD)>0 && totalCashAmountBD.compareTo(totalChequetransferAmountBD)>0) {
						totalCashAmountBD = totalCashAmountBD.subtract(difference);
				}else if(totalBankTransferAmountBD.compareTo(totalChequetransferAmountBD) > 0) {
						totalBankTransferAmountBD = totalBankTransferAmountBD.subtract(difference);
				}else {
						totalChequetransferAmountBD = totalChequetransferAmountBD.subtract(difference);
				}
							 	
			totalValue = totalValue.add(totalCashAmountBD.add(totalBankTransferAmountBD).add(totalChequetransferAmountBD));
								
				
				if("banktransfer".equalsIgnoreCase(paymentmethodbanktransfer)) {
					ackNoVoucherNarration = " acknowledgement number: "+ackNo+" , Amount transfer date: "+transferDate;
					paymentType = "Bank Transfer";
				}else if("chequetransfer".equalsIgnoreCase(paymentmethodchequetransfer)) {
					chequeNoVoucherNarration = " cheque number: "+chequeNo+" , Amount clearance date: "+chequeDate;
					paymentType = "Cheque";
				}
					
			//End Payment Details
				
				//BILL DETAILS
				
				List<Bill> billList = new ArrayList<Bill>();
				BigDecimal sgsttotal = BigDecimal.ZERO;
				BigDecimal cgsttotal = BigDecimal.ZERO;
				BigDecimal totalwithoutgst = BigDecimal.ZERO;
				BigDecimal totalwithgst = BigDecimal.ZERO;
				BigDecimal grandTotal = BigDecimal.ZERO;
				
				
				for(int i=0; i < StockEntryIds.length ; i++){

					Bill bill = new Bill();
					
					Float sgstpercentage = (Float.parseFloat(sgst[i]) / 100) * Float.parseFloat(itemunitprice[i]);
					Float cgstpercentage = (Float.parseFloat(cgst[i]) / 100) * Float.parseFloat(itemunitprice[i]);
					
					bill.setItemname(itemsName[i]);
					bill.setBatchno(batchno[i]);
					bill.setQuantity(Float.parseFloat(issuequantity[i]));
					bill.setUom(uom[i]);
					bill.setSalesprice(Float.parseFloat(itemunitprice[i]));
					bill.setSgst(Float.parseFloat(sgst[i]));
					bill.setCgst(Float.parseFloat(cgst[i]));
					
					sgsttotal = sgsttotal.add(new BigDecimal(sgst[i]));
					cgsttotal = cgsttotal.add(new BigDecimal(cgst[i]));
					totalwithoutgst = totalwithoutgst.add(new BigDecimal(issuequantity[i]).multiply(new BigDecimal(itemunitprice[i])));
					Float totalGST = Float.parseFloat(sgst[i])+Float.parseFloat(cgst[i]);
					
					totalwithgst = totalwithoutgst.add(new BigDecimal(totalGST*100/100));
					bill.setTotalbillinctax(Float.parseFloat(singleItemTotal[i]));
					grandTotal = grandTotal.add(new BigDecimal(Float.parseFloat(singleItemTotal[i])));
					billList.add(bill);
				
			}
				
				//END BILL DETAILS
			
			List<MessStockMove> messStockMovesList = new ArrayList<MessStockMove>();
			
				for(int i=0; i < StockEntryIds.length ; i++){
					

					MessStockMove messStockMove = new MessStockMove();
					
					messStockMove.setStockentryid(Integer.parseInt(StockEntryIds[i]));
					messStockMove.setItemid(Integer.parseInt(itemsIds[i]));
					messStockMove.setExternalid(custDetails[3]);
					messStockMove.setQuantity(Float.parseFloat(issuequantity[i]));
					messStockMove.setPurpose(itemunitprice[i]);
					messStockMove.setTransactiondate(DateUtil.indiandateParser(dto.getTransactionDate()));
					messStockMove.setIssuedto(custDetails[0]+"_"+custDetails[1]+"_"+custDetails[2]);
					messStockMove.setBranchid(Integer.parseInt(branchId));
					messStockMove.setStatus("ACTIVE");
					
					//totalValue = totalValue.add(new BigDecimal(issuequantity[i]).multiply(new BigDecimal(itemunitprice[i])));
					PurchasePricetotalValue = PurchasePricetotalValue.add(new BigDecimal(issuequantity[i]).multiply(new BigDecimal(purchaseprice[i])));
					messStockMovesList.add(messStockMove);
				
			}
				
					//Pass J.V. : credit the assets & debit the Expenses
					int drStockLedgerIdExpense = getLedgerAccountId("itemaccountidexpense"+Integer.parseInt(branchId));
					int crStockLedgerId = getLedgerAccountId("itemaccountid"+Integer.parseInt(branchId));
					
					VoucherEntrytransactions transactions = new VoucherEntrytransactions();
					
					transactions.setDraccountid(drStockLedgerIdExpense);
					transactions.setCraccountid(crStockLedgerId);
					transactions.setDramount(PurchasePricetotalValue);
					transactions.setCramount(PurchasePricetotalValue);
					transactions.setVouchertype(4);
					transactions.setTransactiondate(DateUtil.indiandateParser(dto.getTransactionDate()));
					transactions.setEntrydate(DateUtil.todaysDate());
					transactions.setNarration("Towards Stock Issue");
					transactions.setCancelvoucher("no");
					transactions.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(branchId)).getFinancialid());
					transactions.setBranchid(Integer.parseInt(branchId));
					transactions.setUserid(Integer.parseInt(userId));
					
					BigDecimal drAmountReceipt = totalValue;
					String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+drAmountReceipt+" where accountdetailsid="+drStockLedgerIdExpense;

					BigDecimal crAmountReceipt = totalValue;
					String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+crAmountReceipt+" where accountdetailsid="+crStockLedgerId;
					
					
					//Pass J.V. : credit the Revenue & debit the Cash
					VoucherEntrytransactions transactionsIncomeCash = new VoucherEntrytransactions();
					VoucherEntrytransactions transactionsIncomeCheque = new VoucherEntrytransactions();
					VoucherEntrytransactions transactionsIncomeBankTransfer = new VoucherEntrytransactions();
					String updateDrAccountIncomeCash = null;
					String updateDrAccountIncomeCheque = null;
					String updateDrAccountIncomeBankTransfer = null;
					String updateCrAccountIncomeCash = null;
					String updateCrAccountIncomeCheque = null;
					String updateCrAccountIncomeBankTransfer = null;
					
					if("cashpayment".equalsIgnoreCase(paymentmethodcash)) {
					
						int drStockLedgerIdIncome = getLedgerAccountId(userName+Integer.parseInt(branchId));
						int crStockLedgerIdIncome = getLedgerAccountId("incomeaccount"+Integer.parseInt(branchId));
						
						transactionsIncomeCash.setDraccountid(drStockLedgerIdIncome);
						transactionsIncomeCash.setCraccountid(crStockLedgerIdIncome);
						transactionsIncomeCash.setDramount(totalCashAmountBD);
						transactionsIncomeCash.setCramount(totalCashAmountBD);
						transactionsIncomeCash.setVouchertype(4);
						transactionsIncomeCash.setTransactiondate(DateUtil.indiandateParser(dto.getTransactionDate()));
						transactionsIncomeCash.setEntrydate(DateUtil.todaysDate());
						transactionsIncomeCash.setNarration("Towards Sales of Stock");
						transactionsIncomeCash.setCancelvoucher("no");
						transactionsIncomeCash.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(branchId)).getFinancialid());
						transactionsIncomeCash.setBranchid(Integer.parseInt(branchId));
						transactionsIncomeCash.setUserid(Integer.parseInt(userId));
						
						BigDecimal drAmountReceiptIncome = totalCashAmountBD;
						updateDrAccountIncomeCash ="update Accountdetailsbalance set currentbalance=currentbalance+"+drAmountReceiptIncome+" where accountdetailsid="+drStockLedgerIdIncome;
	
						BigDecimal crAmountReceiptIncome = totalCashAmountBD;
						updateCrAccountIncomeCash ="update Accountdetailsbalance set currentbalance=currentbalance+"+crAmountReceiptIncome+" where accountdetailsid="+crStockLedgerIdIncome;
					
					}
					
					
					if("banktransfer".equalsIgnoreCase(paymentmethodbanktransfer)) {
						
						int drStockLedgerIdIncome = getLedgerAccountId(transferBankname+Integer.parseInt(branchId));
						int crStockLedgerIdIncome = getLedgerAccountId("incomeaccount"+Integer.parseInt(branchId));
						
						transactionsIncomeBankTransfer.setDraccountid(drStockLedgerIdIncome);
						transactionsIncomeBankTransfer.setCraccountid(crStockLedgerIdIncome);
						transactionsIncomeBankTransfer.setDramount(totalBankTransferAmountBD);
						transactionsIncomeBankTransfer.setCramount(totalBankTransferAmountBD);
						transactionsIncomeBankTransfer.setVouchertype(4);
						transactionsIncomeBankTransfer.setTransactiondate(DateUtil.indiandateParser(dto.getTransactionDate()));
						transactionsIncomeBankTransfer.setEntrydate(DateUtil.todaysDate());
						transactionsIncomeBankTransfer.setNarration("Towards Sales of Stock");
						transactionsIncomeBankTransfer.setCancelvoucher("no");
						transactionsIncomeBankTransfer.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(branchId)).getFinancialid());
						transactionsIncomeBankTransfer.setBranchid(Integer.parseInt(branchId));
						transactionsIncomeBankTransfer.setUserid(Integer.parseInt(userId));
						
						BigDecimal drAmountReceiptIncome = totalBankTransferAmountBD;
						updateDrAccountIncomeBankTransfer ="update Accountdetailsbalance set currentbalance=currentbalance+"+drAmountReceiptIncome+" where accountdetailsid="+drStockLedgerIdIncome;
	
						BigDecimal crAmountReceiptIncome = totalBankTransferAmountBD;
						updateCrAccountIncomeBankTransfer ="update Accountdetailsbalance set currentbalance=currentbalance+"+crAmountReceiptIncome+" where accountdetailsid="+crStockLedgerIdIncome;
					
					}
					
					if("chequetransfer".equalsIgnoreCase(paymentmethodchequetransfer)) {
						
						int drStockLedgerIdIncome = getLedgerAccountId(chequeBankname+Integer.parseInt(branchId));
						int crStockLedgerIdIncome = getLedgerAccountId("incomeaccount"+Integer.parseInt(branchId));
						
						transactionsIncomeCheque.setDraccountid(drStockLedgerIdIncome);
						transactionsIncomeCheque.setCraccountid(crStockLedgerIdIncome);
						transactionsIncomeCheque.setDramount(totalChequetransferAmountBD);
						transactionsIncomeCheque.setCramount(totalChequetransferAmountBD);
						transactionsIncomeCheque.setVouchertype(4);
						transactionsIncomeCheque.setTransactiondate(DateUtil.indiandateParser(dto.getTransactionDate()));
						transactionsIncomeCheque.setEntrydate(DateUtil.todaysDate());
						transactionsIncomeCheque.setNarration("Towards Sales of Stock");
						transactionsIncomeCheque.setCancelvoucher("no");
						transactionsIncomeCheque.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(branchId)).getFinancialid());
						transactionsIncomeCheque.setBranchid(Integer.parseInt(branchId));
						transactionsIncomeCheque.setUserid(Integer.parseInt(userId));
						
						BigDecimal drAmountReceiptIncome = totalChequetransferAmountBD;
						updateDrAccountIncomeCheque ="update Accountdetailsbalance set currentbalance=currentbalance+"+drAmountReceiptIncome+" where accountdetailsid="+drStockLedgerIdIncome;
	
						BigDecimal crAmountReceiptIncome = totalChequetransferAmountBD;
						updateCrAccountIncomeCheque ="update Accountdetailsbalance set currentbalance=currentbalance+"+crAmountReceiptIncome+" where accountdetailsid="+crStockLedgerIdIncome;
					
					}
					
					
					
					boolean result = new MessStockMoveDAO().moveStockSave(messStockMovesList,transactions,updateDrAccount,updateCrAccount,transactionsIncomeCash,transactionsIncomeBankTransfer,transactionsIncomeCheque,updateDrAccountIncomeCash,updateCrAccountIncomeCash,updateDrAccountIncomeBankTransfer,updateCrAccountIncomeBankTransfer,updateDrAccountIncomeCheque,updateCrAccountIncomeCheque);
					
						if(result) {
							//request.setAttribute("billdetails", messStockMovesList);;
							results.setBillList(billList);
							results.setBillDetailsTransactionDate(dto.getTransactionDate());
							results.setBillDetailsStudentName(custDetails[0]);
							results.setBillDetailsClassStudying(custDetails[1]);
							results.setBillDetailsFatherName(custDetails[2]);


							NumberToWord toWord = new NumberToWord();
							String grandTotalInWords = "";
							if(totalValue.compareTo(BigDecimal.ZERO) != 0){
								grandTotalInWords = toWord.convert(grandTotal.intValue());
							}

							StringBuffer res = new StringBuffer();
							String[] strArr = grandTotalInWords.split(" ");

							for(String str : strArr){
								char[] stringArray = str.trim().toCharArray();
								stringArray[0] = Character.toUpperCase(stringArray[0]);
								str = new String(stringArray);
								res.append(str).append(" ");
							}
							grandTotalInWords = res.toString().trim();
							results.setBillDetailsTotalTotal(grandTotalInWords+" "+"Only");
							results.setBillGrandTotal(grandTotal);

							//Get Bill No
							MessStockMove msm = new MessStockMoveDAO().getMessStockMoveMaxRow();
							int billNo = 0;
							if(msm!=null) {
								billNo = msm.getId() + 1;
							}else {
								billNo = 1;
							}

							results.setBillNo(billNo);

						}else {
							results.setBillDetails("");
							results.setBillDetailsTransactionDate("");
							results.setBillDetailsCustomerName("");
						}
					results.setItemsIssued(result);
				}

					messItemsService.viewItemDetails(branchId);

					results.setSuccess(true);
					return results;
			}

	public ResultResponse saveStockMoveOld(StockMoveOldDto dto, String branchId, String userId) {

		boolean result = false;
		ResultResponse resultResponse = ResultResponse.builder().build();
		
		if(branchId!=null){
			
			
				String[] StockEntryIds = dto.getStockEntryIds();
				/*String[] itemsName = request.getParameterValues("itemsname");
				String[] itemsIds = request.getParameterValues("itemsids");
				String[] issuequantity = request.getParameterValues("issuequantity");
				String[] itemunitprice = request.getParameterValues("itemunitprice");*/
				BigDecimal totalValue = BigDecimal.ZERO;
				
				List<MessStockMove> messStockMovesList = new ArrayList<>();
				
				if(StockEntryIds!=null) {
					
					for(int i=0; i < StockEntryIds.length ; i++){
						String issueQuantity = dto.getRequestParams().get("issuequantity_"+StockEntryIds[i]);
						float reqQty = Float.parseFloat(issueQuantity);
						//Query stock entry
						
							List<MessStockEntry> messStockEntryList = new MessStockEntryDAO().getItemsStockEntry(Integer.parseInt(StockEntryIds[i])); 
								
								for (MessStockEntry messStockEntry : messStockEntryList) {

									if(reqQty<=messStockEntry.getAvailablequantity()) {

										MessStockMove messStockMove = new MessStockMove();
										
										messStockMove.setStockentryid(messStockEntry.getId());
										messStockMove.setItemid(messStockEntry.getItemid());
										messStockMove.setExternalid(dto.getRequestParams().get("itemname_"+StockEntryIds[i]));
										messStockMove.setQuantity(reqQty);
										messStockMove.setPurpose(dto.getPurpose());
										messStockMove.setTransactiondate(DateUtil.indiandateParser(dto.getTransactionDate()));
										messStockMove.setIssuedto(dto.getIssuedTo());
										messStockMove.setBranchid(Integer.parseInt(branchId));
										messStockMove.setStatus("ACTIVE");
										messStockMove.setUserid(Integer.parseInt(userId));
										
										totalValue = totalValue.add(new BigDecimal(reqQty).multiply(new BigDecimal(messStockEntry.getItemunitprice())));
										messStockMovesList.add(messStockMove);
										break;
									}else if(reqQty>messStockEntry.getAvailablequantity()) {

										MessStockMove messStockMove = new MessStockMove();
										
										messStockMove.setStockentryid(messStockEntry.getId());
										messStockMove.setItemid(messStockEntry.getItemid());
										messStockMove.setExternalid(dto.getRequestParams().get("itemname_"+StockEntryIds[i]));
										messStockMove.setQuantity(messStockEntry.getAvailablequantity());
										messStockMove.setPurpose(dto.getPurpose());
										messStockMove.setTransactiondate(DateUtil.indiandateParser(dto.getTransactionDate()));
										messStockMove.setIssuedto(dto.getIssuedTo());
										messStockMove.setBranchid(Integer.parseInt(branchId));
										messStockMove.setStatus("ACTIVE");
										messStockMove.setUserid(Integer.parseInt(userId));
										
										totalValue = totalValue.add(new BigDecimal(messStockEntry.getAvailablequantity()).multiply(new BigDecimal(messStockEntry.getItemunitprice())));
										messStockMovesList.add(messStockMove);
										
										reqQty -=messStockEntry.getAvailablequantity(); 
									}
									
									
								}
					
						}
					
						//Pass J.V. : credit the assets & debit the Expenses
						int drStockLedgerIdExpense = getLedgerAccountId("itemaccountidexpense"+Integer.parseInt(branchId));
						int crStockLedgerId = getLedgerAccountId("itemaccountid"+Integer.parseInt(branchId));
						
						VoucherEntrytransactions transactions = new VoucherEntrytransactions();
						
						transactions.setDraccountid(drStockLedgerIdExpense);
						transactions.setCraccountid(crStockLedgerId);
						transactions.setDramount(totalValue);
						transactions.setCramount(totalValue);
						transactions.setVouchertype(4);
						transactions.setTransactiondate(DateUtil.indiandateParser(dto.getTransactionDate()));
						transactions.setEntrydate(DateUtil.todaysDate());
						transactions.setNarration("Towards Stock Issue");
						transactions.setCancelvoucher("no");
						transactions.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(branchId)).getFinancialid());
						transactions.setBranchid(Integer.parseInt(branchId));
						transactions.setUserid(Integer.parseInt(userId));
						
						BigDecimal drAmountReceipt = totalValue;
						String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+drAmountReceipt+" where accountdetailsid="+drStockLedgerIdExpense;

						BigDecimal crAmountReceipt = totalValue;
						String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+crAmountReceipt+" where accountdetailsid="+crStockLedgerId;
						
						//important result = new MessStockMoveDAO().moveStockSave(messStockMovesList,transactions,updateDrAccount,updateCrAccount);
					}
		
						messItemsService.viewItemDetails(branchId);
						resultResponse.setSuccess(result);
				}	
						resultResponse.setSuccess(false);
						return resultResponse;
		}


	public ResultResponse viewStockEntryDetails(String branchId) {
		
		List<MessStockEntry> messStockEntryList = new ArrayList<MessStockEntry>();
		List<MessStockItemDetails> messStockItemDetailsList = new ArrayList<MessStockItemDetails>();
		List<Integer> itemIds = new ArrayList<Integer>();
		
		 if(branchId!=null){
			 
			 messStockEntryList = new MessItemsDAO().getItemsStockEntry();
			 
			 for (MessStockEntry messStockEntry : messStockEntryList) {
				 itemIds.add(messStockEntry.getItemid());
			}
			 
			 //get the item details
			 if(!itemIds.isEmpty()) {
				 
			 List<MessItems> messItem = new ArrayList<MessItems>();
			 messItem = new MessItemsDAO().getItemDetailByID(itemIds);
			 
			 for (MessStockEntry messStockEntry : messStockEntryList) {
				 	int stockid = messStockEntry.getItemid();
				 	
				 for (MessItems messItems : messItem) {
					 int itemid = messItems.getId();
					 
					if(stockid == itemid) {
						MessStockItemDetails messStockItemDetails = new MessStockItemDetails();
						messStockItemDetails.setAvailablequantity(messStockEntry.getAvailablequantity());
						messStockItemDetails.setBatchno(messStockEntry.getBatchno());
						messStockItemDetails.setItemid(messItems.getId());
						messStockItemDetails.setItemunitprice(messStockEntry.getItemunitprice());
						messStockItemDetails.setSgst(messStockEntry.getSgst());
						messStockItemDetails.setCgst(messStockEntry.getCgst());
						String[] salesPrice = messStockEntry.getExternalid().split("_");
						messStockItemDetails.setStockentryexternalid(salesPrice[1]);
						messStockItemDetails.setStockentryid(messStockEntry.getId());
						messStockItemDetails.setItemname(messItems.getName());
						messStockItemDetails.setUnitofmeasure(messItems.getUnitofmeasure());
						messStockItemDetailsList.add(messStockItemDetails);
						break;
					}
				}
			}
			 
		 }
		 }
		 return ResultResponse
				 .builder()
				 .resultList(messStockItemDetailsList)
				 .success(true)
				 .build();
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


	public StockMoveResponseDto viewStockMoveDetails(String strPage, String branchId) {
		
		List<Bill> messStockMoveList = new ArrayList<Bill>();
		StockMoveResponseDto result = StockMoveResponseDto.builder().build();
		
		 if(branchId!=null){
			 
					try {
						int page = 1;
						int recordsPerPage = 50;
							if (!"".equalsIgnoreCase(DataUtil.emptyString(strPage))) {
								page = Integer.parseInt(strPage);
							}

						messStockMoveList = new MessStockMoveDAO().getStockMoveDetails((page - 1) * recordsPerPage,
									recordsPerPage, Integer.parseInt(branchId));
						int noOfRecords = new MessStockMoveDAO().getNoOfRecordsStockMove(Integer.parseInt(branchId));
						int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
						result.setNoOfPages(noOfRecords);
						result.setCurrentPage(page);
						
						result.setSuccess(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				
			 
		 }

		 result.setMessStockMoveList(messStockMoveList);
			
		 return result;
	}


	public ResultResponse cancelStockMove(StockMoveIdsDto dto, String branchId, String userId) {
	ResultResponse resultResponse = ResultResponse.builder().build();
		
	try {
		String[] smIds = dto.getStockMoveIds();

		for (String stockmoveids : smIds) {


			MessStockMove messStockMove = new MessStockMoveDAO().getStockMoveDetails(Integer.parseInt(stockmoveids));
			MessStockEntry messStockEntry = new MessItemsDAO().getMessStockEntryByID(messStockMove.getStockentryid());
			float totalValue = messStockMove.getQuantity() * messStockEntry.getItemunitprice();

			//Pass J.V. : Debit the assets & credit the Expenses

			int drStockLedgerId = getLedgerAccountId("itemaccountid"+Integer.parseInt(branchId));
			int crStockLedgerIdExpense = getLedgerAccountId("itemaccountidexpense"+Integer.parseInt(branchId));

			VoucherEntrytransactions transactions = new VoucherEntrytransactions();

			transactions.setDraccountid(drStockLedgerId);
			transactions.setCraccountid(crStockLedgerIdExpense);
			transactions.setDramount(BigDecimal.valueOf(totalValue));
			transactions.setCramount(BigDecimal.valueOf(totalValue));
			transactions.setVouchertype(4);
			transactions.setTransactiondate(DateUtil.indiandateParser(dto.getRequestParams().get("transactiondate_"+stockmoveids)));
			transactions.setEntrydate(DateUtil.todaysDate());
			transactions.setNarration("Towards Revarsal of Stock Issue");
			transactions.setCancelvoucher("no");
			transactions.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(branchId)).getFinancialid());
			transactions.setBranchid(Integer.parseInt(branchId));
			transactions.setUserid(Integer.parseInt(userId));

			BigDecimal drAmountReceipt = BigDecimal.valueOf(totalValue);
			String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+drAmountReceipt+" where accountdetailsid="+drStockLedgerId;

			BigDecimal crAmountReceipt = BigDecimal.valueOf(totalValue);
			String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+crAmountReceipt+" where accountdetailsid="+crStockLedgerIdExpense;

			boolean result = new MessStockMoveDAO().cancelStockMove(messStockMove,transactions,updateDrAccount,updateCrAccount);
			resultResponse.setSuccess(result);

		}
	}catch (Exception e){
		log.error("Error in cancelStockMove: ", e);
		resultResponse.setSuccess(false);
		resultResponse.setMessage("An error occurred while processing the request: " + e.getMessage());
	}
	resultResponse.setSuccess(true);
	return resultResponse;
}
	
	
	
	public void getCustomerLastPrice(String customerName, String strCustDetails, String itemid, String branchId) throws IOException {

		String[] custDetails = strCustDetails.split("_");
		Locale indiaLocale = new Locale("en", "IN");
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");

		if(!customerName.isEmpty() && !itemid.isEmpty() ) {

			List<MessStockMove> messStockMove = new MessStockMoveDAO().getCustomerLastPrices(custDetails[0]+"_"+custDetails[1], itemid, Integer.parseInt(branchId));

			String priceList = "";
			String priceListFirst = "";
			int i=0;


				for (MessStockMove messStockMove2 : messStockMove) {

					if(priceList.isEmpty()) {
						priceListFirst = "Date:"+DateUtil.dateParserddMMYYYY(messStockMove2.getTransactiondate())+" Price:"+messStockMove2.getPurpose();
						priceList = "Date:"+messStockMove2.getTransactiondate()+" Price:"+messStockMove2.getPurpose();
					}else if(i<=5) {
						priceList = priceList+" || Date:"+messStockMove2.getTransactiondate()+" Price:"+messStockMove2.getPurpose();
					}
					i++;
				}
		        try {
		        		String buffer = "<label style='font-size: 14px;font-weight: bold;'>Last Payment: </label> <label style='font-size: 14px;font-weight: bold;'>"+priceListFirst+" </label>";
		        		buffer = buffer+"<br><label>Payment History: </label> <label>"+priceList+"</label>";
			        	response.getWriter().println(buffer);

		        } catch (Exception e) {
		            out.write("");
		        } finally {
		            out.flush();
		            out.close();
		        }
		}else {
			try {
        		String buffer = "<label style='font-size: 14px;font-weight: bold;'>Last Payment: </label> <label style='font-size: 14px;font-weight: bold;'></label>";
        		buffer = buffer+"<br><label>Payment History: </label> <label></label>";
	        	response.getWriter().println(buffer);

        } catch (Exception e) {
            out.write("");
        } finally {
            out.flush();
            out.close();
        }
		}
	}




	public ResultResponse viewStockDueDetails(ClassSearchDto dto, String branchId) {

		String classSearch = dto.getClassSearch();
		String conClassStudying = "";

		if (!classSearch.equalsIgnoreCase("")) {
			conClassStudying = classSearch+"--"+"%";
		}
		ResultResponse result = ResultResponse.builder().build();
		List<Parents> parentDetails = new ArrayList<>();
		
		if(branchId!=null){
			
			try {
								
				List<Object[]> list = new MessStockMoveDAO().readStockDueDetails(conClassStudying,Integer.parseInt(branchId));
				
	            for(Object[] parentdetails: list){
	            	Parents parent = new Parents();
	            	Student student = new Student();
	                student.setSid((Integer)parentdetails[0]);
	                student.setStudentexternalid((String)parentdetails[1]);
	                student.setAdmissionnumber((String)parentdetails[2]);
	                student.setName((String)parentdetails[3]);
	                student.setClassstudying((String)parentdetails[4]);
	                parent.setFathersname((String)parentdetails[5]);
	                parent.setMothersname((String)parentdetails[6]);
	                parent.setStudent(student);
	                parentDetails.add(parent);
	            }
				
				result.setSuccess(true);
			} catch (Exception e) {
				e.printStackTrace();
			}

			result.setResultList(parentDetails);
		}
		
		return result;
	}
	
	
}
