package org.ideoholic.curium.model.mess.stockmove.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.account.dao.AccountDAO;
import org.ideoholic.curium.model.account.dto.VoucherEntrytransactions;
import org.ideoholic.curium.model.mess.item.dao.MessItemsDAO;
import org.ideoholic.curium.model.mess.item.dto.MessItems;
import org.ideoholic.curium.model.mess.item.service.MessItemsService;
import org.ideoholic.curium.model.mess.stockentry.dao.MessStockEntryDAO;
import org.ideoholic.curium.model.mess.stockentry.dto.MessStockEntry;
import org.ideoholic.curium.model.mess.stockmove.dao.MessStockMoveDAO;
import org.ideoholic.curium.model.mess.stockmove.dto.Bill;
import org.ideoholic.curium.model.mess.stockmove.dto.MessStockItemDetails;
import org.ideoholic.curium.model.mess.stockmove.dto.MessStockMove;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.DateUtil;
import org.ideoholic.curium.util.NumberToWord;

public class MessStockMoveService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;
	private String BRANCHID = "branchid";
	private String USERID = "userloginid";
	
	public MessStockMoveService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

	
	
	
	public boolean saveStockMove() {

	boolean result = false;
	
	if(httpSession.getAttribute(BRANCHID)!=null){
		
		
			String[] StockEntryIds = request.getParameterValues("ids");
			String[] itemsName = request.getParameterValues("itemsname");
			String[] itemsIds = request.getParameterValues("itemsids");
			String[] issuequantity = request.getParameterValues("issuequantity");
			String[] itemunitprice = request.getParameterValues("itemunitprice");
			String[] purchaseprice = request.getParameterValues("purchaseprice");
			String[] custDetails = request.getParameter("issuedto").split("_");
			String[] sgst = request.getParameterValues("sgst");
			String[] cgst = request.getParameterValues("cgst");
			String[] uom = request.getParameterValues("itemsunitofmeasure");
			String[] batchno = request.getParameterValues("batchno");
			String[] singleItemTotal = request.getParameterValues("linetotal");
			BigDecimal totalValue = BigDecimal.ZERO;
			BigDecimal PurchasePricetotalValue = BigDecimal.ZERO;
			
			
			//Get Payment Details
			String paymentmethodbanktransfer = request.getParameter("paymentmethodbanktransfer");
			String paymentmethodchequetransfer = request.getParameter("paymentmethodchequetransfer");
			String paymentmethodcash = request.getParameter("paymentmethodcash");
			String ackNo = request.getParameter("ackno");
			String ackNoVoucherNarration = "";
			String transferDate = request.getParameter("transferdate");
			String transferBankname = request.getParameter("transferbankname");
			String chequeNo = request.getParameter("chequeno");
			String chequeNoVoucherNarration = "";
			String chequeDate = request.getParameter("chequedate");
			String chequeBankname = request.getParameter("chequebankname");
			String paymentType = "Cash";
			String totalCashAmount = request.getParameter("totalcashamount");
			String totalBankTransferAmount = request.getParameter("totalbanktransferamount");
			String totalChequetransferAmount = request.getParameter("totalchequetransferamount");
			
			String itemsGrandTotalAmountWOGST = request.getParameter("itemsGrandTotalAmountWithoutGST");
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
					messStockMove.setExternalid(itemsName[i]);
					messStockMove.setQuantity(Float.parseFloat(issuequantity[i]));
					messStockMove.setPurpose(itemunitprice[i]);
					messStockMove.setTransactiondate(DateUtil.indiandateParser(request.getParameter("transactiondate")));
					messStockMove.setIssuedto(custDetails[0]+"_"+custDetails[1]+"_"+custDetails[2]);
					messStockMove.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
					messStockMove.setStatus("ACTIVE");
					
					//totalValue = totalValue.add(new BigDecimal(issuequantity[i]).multiply(new BigDecimal(itemunitprice[i])));
					PurchasePricetotalValue = PurchasePricetotalValue.add(new BigDecimal(issuequantity[i]).multiply(new BigDecimal(purchaseprice[i])));
					messStockMovesList.add(messStockMove);
				
			}
				
					//Pass J.V. : credit the assets & debit the Expenses
					int drStockLedgerIdExpense = getLedgerAccountId("itemaccountidexpense"+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
					int crStockLedgerId = getLedgerAccountId("itemaccountid"+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
					
					VoucherEntrytransactions transactions = new VoucherEntrytransactions();
					
					transactions.setDraccountid(drStockLedgerIdExpense);
					transactions.setCraccountid(crStockLedgerId);
					transactions.setDramount(PurchasePricetotalValue);
					transactions.setCramount(PurchasePricetotalValue);
					transactions.setVouchertype(4);
					transactions.setTransactiondate(DateUtil.indiandateParser(request.getParameter("transactiondate")));
					transactions.setEntrydate(DateUtil.todaysDate());
					transactions.setNarration("Towards Stock Issue");
					transactions.setCancelvoucher("no");
					transactions.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())).getFinancialid());
					transactions.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
					transactions.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
					
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
					
						int drStockLedgerIdIncome = getLedgerAccountId(httpSession.getAttribute("username").toString()+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
						int crStockLedgerIdIncome = getLedgerAccountId("incomeaccount"+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
						
						transactionsIncomeCash.setDraccountid(drStockLedgerIdIncome);
						transactionsIncomeCash.setCraccountid(crStockLedgerIdIncome);
						transactionsIncomeCash.setDramount(totalCashAmountBD);
						transactionsIncomeCash.setCramount(totalCashAmountBD);
						transactionsIncomeCash.setVouchertype(4);
						transactionsIncomeCash.setTransactiondate(DateUtil.indiandateParser(request.getParameter("transactiondate")));
						transactionsIncomeCash.setEntrydate(DateUtil.todaysDate());
						transactionsIncomeCash.setNarration("Towards Sales of Stock");
						transactionsIncomeCash.setCancelvoucher("no");
						transactionsIncomeCash.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())).getFinancialid());
						transactionsIncomeCash.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
						transactionsIncomeCash.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
						
						BigDecimal drAmountReceiptIncome = totalCashAmountBD;
						updateDrAccountIncomeCash ="update Accountdetailsbalance set currentbalance=currentbalance+"+drAmountReceiptIncome+" where accountdetailsid="+drStockLedgerIdIncome;
	
						BigDecimal crAmountReceiptIncome = totalCashAmountBD;
						updateCrAccountIncomeCash ="update Accountdetailsbalance set currentbalance=currentbalance+"+crAmountReceiptIncome+" where accountdetailsid="+crStockLedgerIdIncome;
					
					}
					
					
					if("banktransfer".equalsIgnoreCase(paymentmethodbanktransfer)) {
						
						int drStockLedgerIdIncome = getLedgerAccountId(transferBankname+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
						int crStockLedgerIdIncome = getLedgerAccountId("incomeaccount"+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
						
						transactionsIncomeBankTransfer.setDraccountid(drStockLedgerIdIncome);
						transactionsIncomeBankTransfer.setCraccountid(crStockLedgerIdIncome);
						transactionsIncomeBankTransfer.setDramount(totalBankTransferAmountBD);
						transactionsIncomeBankTransfer.setCramount(totalBankTransferAmountBD);
						transactionsIncomeBankTransfer.setVouchertype(4);
						transactionsIncomeBankTransfer.setTransactiondate(DateUtil.indiandateParser(request.getParameter("transactiondate")));
						transactionsIncomeBankTransfer.setEntrydate(DateUtil.todaysDate());
						transactionsIncomeBankTransfer.setNarration("Towards Sales of Stock");
						transactionsIncomeBankTransfer.setCancelvoucher("no");
						transactionsIncomeBankTransfer.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())).getFinancialid());
						transactionsIncomeBankTransfer.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
						transactionsIncomeBankTransfer.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
						
						BigDecimal drAmountReceiptIncome = totalBankTransferAmountBD;
						updateDrAccountIncomeBankTransfer ="update Accountdetailsbalance set currentbalance=currentbalance+"+drAmountReceiptIncome+" where accountdetailsid="+drStockLedgerIdIncome;
	
						BigDecimal crAmountReceiptIncome = totalBankTransferAmountBD;
						updateCrAccountIncomeBankTransfer ="update Accountdetailsbalance set currentbalance=currentbalance+"+crAmountReceiptIncome+" where accountdetailsid="+crStockLedgerIdIncome;
					
					}
					
					if("chequetransfer".equalsIgnoreCase(paymentmethodchequetransfer)) {
						
						int drStockLedgerIdIncome = getLedgerAccountId(chequeBankname+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
						int crStockLedgerIdIncome = getLedgerAccountId("incomeaccount"+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
						
						transactionsIncomeCheque.setDraccountid(drStockLedgerIdIncome);
						transactionsIncomeCheque.setCraccountid(crStockLedgerIdIncome);
						transactionsIncomeCheque.setDramount(totalChequetransferAmountBD);
						transactionsIncomeCheque.setCramount(totalChequetransferAmountBD);
						transactionsIncomeCheque.setVouchertype(4);
						transactionsIncomeCheque.setTransactiondate(DateUtil.indiandateParser(request.getParameter("transactiondate")));
						transactionsIncomeCheque.setEntrydate(DateUtil.todaysDate());
						transactionsIncomeCheque.setNarration("Towards Sales of Stock");
						transactionsIncomeCheque.setCancelvoucher("no");
						transactionsIncomeCheque.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())).getFinancialid());
						transactionsIncomeCheque.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
						transactionsIncomeCheque.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
						
						BigDecimal drAmountReceiptIncome = totalChequetransferAmountBD;
						updateDrAccountIncomeCheque ="update Accountdetailsbalance set currentbalance=currentbalance+"+drAmountReceiptIncome+" where accountdetailsid="+drStockLedgerIdIncome;
	
						BigDecimal crAmountReceiptIncome = totalChequetransferAmountBD;
						updateCrAccountIncomeCheque ="update Accountdetailsbalance set currentbalance=currentbalance+"+crAmountReceiptIncome+" where accountdetailsid="+crStockLedgerIdIncome;
					
					}
					
					
					
					result = new MessStockMoveDAO().moveStockSave(messStockMovesList,transactions,updateDrAccount,updateCrAccount,transactionsIncomeCash,transactionsIncomeBankTransfer,transactionsIncomeCheque,updateDrAccountIncomeCash,updateCrAccountIncomeCash,updateDrAccountIncomeBankTransfer,updateCrAccountIncomeBankTransfer,updateDrAccountIncomeCheque,updateCrAccountIncomeCheque);
					
						if(result) {
							request.setAttribute("billdetails", billList);
							//request.setAttribute("billdetails", messStockMovesList);
							request.setAttribute("billdetailstransactiondate", request.getParameter("transactiondate"));
							request.setAttribute("billdetailsstudentname", custDetails[0]);
							request.setAttribute("billdetailsclassstudying", custDetails[1]);
							request.setAttribute("billdetailsfathername", custDetails[2]);
							
							
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
							request.setAttribute("billdetailstotaltotal", grandTotalInWords+" "+"Only");
							request.setAttribute("billgrandtotal", grandTotal);
							
							//Get Bill No
							MessStockMove msm = new MessStockMoveDAO().getMessStockMoveMaxRow();
							int billNo = 0;
							if(msm!=null) {
								String[] externalId = msm.getExternalid().split("_");
								billNo = Integer.parseInt(externalId[1]) + 1;
							}else {
								billNo = 1;
							}
							
					     	 request.setAttribute("billno", billNo);
					     	 
						}else {
							request.setAttribute("billdetails", "");
							request.setAttribute("billdetailstransactiondate", "");
							request.setAttribute("billdetailscustomername", "");
						}
					request.setAttribute("itemsissued", result);
				}
	
					new MessItemsService(request, response).viewItemDetails();
					
					
					return result;
			}

	public boolean saveStockMoveOld() {

		boolean result = false;
		
		if(httpSession.getAttribute(BRANCHID)!=null){
			
			
				String[] StockEntryIds = request.getParameterValues("itemids");
				/*String[] itemsName = request.getParameterValues("itemsname");
				String[] itemsIds = request.getParameterValues("itemsids");
				String[] issuequantity = request.getParameterValues("issuequantity");
				String[] itemunitprice = request.getParameterValues("itemunitprice");*/
				BigDecimal totalValue = BigDecimal.ZERO;
				
				List<MessStockMove> messStockMovesList = new ArrayList<MessStockMove>();
				
				if(StockEntryIds!=null) {
					
					for(int i=0; i < StockEntryIds.length ; i++){
						String issueQuantity = request.getParameter("issuequantity_"+StockEntryIds[i]);
						float reqQty = Float.parseFloat(issueQuantity);
						//Query stock entry
						
							List<MessStockEntry> messStockEntryList = new MessStockEntryDAO().getItemsStockEntry(Integer.parseInt(StockEntryIds[i])); 
								
								for (MessStockEntry messStockEntry : messStockEntryList) {

									if(reqQty<=messStockEntry.getAvailablequantity()) {

										MessStockMove messStockMove = new MessStockMove();
										
										messStockMove.setStockentryid(messStockEntry.getId());
										messStockMove.setItemid(messStockEntry.getItemid());
										messStockMove.setExternalid(request.getParameter("itemname_"+StockEntryIds[i]));
										messStockMove.setQuantity(reqQty);
										messStockMove.setPurpose(request.getParameter("purpose"));
										messStockMove.setTransactiondate(DateUtil.indiandateParser(request.getParameter("transactiondate")));
										messStockMove.setIssuedto(request.getParameter("issuedto"));
										messStockMove.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
										messStockMove.setStatus("ACTIVE");
										messStockMove.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
										
										totalValue = totalValue.add(new BigDecimal(reqQty).multiply(new BigDecimal(messStockEntry.getItemunitprice())));
										messStockMovesList.add(messStockMove);
										break;
									}else if(reqQty>messStockEntry.getAvailablequantity()) {

										MessStockMove messStockMove = new MessStockMove();
										
										messStockMove.setStockentryid(messStockEntry.getId());
										messStockMove.setItemid(messStockEntry.getItemid());
										messStockMove.setExternalid(request.getParameter("itemname_"+StockEntryIds[i]));
										messStockMove.setQuantity(messStockEntry.getAvailablequantity());
										messStockMove.setPurpose(request.getParameter("purpose"));
										messStockMove.setTransactiondate(DateUtil.indiandateParser(request.getParameter("transactiondate")));
										messStockMove.setIssuedto(request.getParameter("issuedto"));
										messStockMove.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
										messStockMove.setStatus("ACTIVE");
										messStockMove.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
										
										totalValue = totalValue.add(new BigDecimal(messStockEntry.getAvailablequantity()).multiply(new BigDecimal(messStockEntry.getItemunitprice())));
										messStockMovesList.add(messStockMove);
										
										reqQty -=messStockEntry.getAvailablequantity(); 
									}
									
									
								}
					
						}
					
						//Pass J.V. : credit the assets & debit the Expenses
						int drStockLedgerIdExpense = getLedgerAccountId("itemaccountidexpense"+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
						int crStockLedgerId = getLedgerAccountId("itemaccountid"+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
						
						VoucherEntrytransactions transactions = new VoucherEntrytransactions();
						
						transactions.setDraccountid(drStockLedgerIdExpense);
						transactions.setCraccountid(crStockLedgerId);
						transactions.setDramount(totalValue);
						transactions.setCramount(totalValue);
						transactions.setVouchertype(4);
						transactions.setTransactiondate(DateUtil.indiandateParser(request.getParameter("transactiondate")));
						transactions.setEntrydate(DateUtil.todaysDate());
						transactions.setNarration("Towards Stock Issue");
						transactions.setCancelvoucher("no");
						transactions.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())).getFinancialid());
						transactions.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
						transactions.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
						
						BigDecimal drAmountReceipt = totalValue;
						String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+drAmountReceipt+" where accountdetailsid="+drStockLedgerIdExpense;

						BigDecimal crAmountReceipt = totalValue;
						String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+crAmountReceipt+" where accountdetailsid="+crStockLedgerId;
						
						//important result = new MessStockMoveDAO().moveStockSave(messStockMovesList,transactions,updateDrAccount,updateCrAccount);
					}
		
						new MessItemsService(request, response).viewItemDetails();
						request.setAttribute("itemsissued", result);
				}	
						return result;
		}


	public void viewStockEntryDetails() {
		
		List<MessStockEntry> messStockEntryList = new ArrayList<MessStockEntry>();
		List<MessStockItemDetails> messStockItemDetailsList = new ArrayList<MessStockItemDetails>();
		List<Integer> itemIds = new ArrayList<Integer>();
		
		 if(httpSession.getAttribute(BRANCHID)!=null){
			 
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
		 request.setAttribute("messstockitemdetailslist", messStockItemDetailsList);
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


	public boolean viewStockMoveDetails() {
		
		List<Bill> messStockMoveList = new ArrayList<Bill>();
		boolean result = false;
		
		 if(httpSession.getAttribute(BRANCHID)!=null){
			 
					try {
						int page = 1;
						int recordsPerPage = 50;
							if (!"".equalsIgnoreCase(DataUtil.emptyString(request.getParameter("page")))) {
								page = Integer.parseInt(request.getParameter("page"));
							}

						messStockMoveList = new MessStockMoveDAO().getStockMoveDetails((page - 1) * recordsPerPage,
									recordsPerPage, Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())); 	
						int noOfRecords = new MessStockMoveDAO().getNoOfRecordsStockMove(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
						int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
						request.setAttribute("noOfPages", noOfPages);
						request.setAttribute("currentPage", page);
						
						result = true;
					} catch (Exception e) {
						e.printStackTrace();
					}
				
			 
		 }
		 
		 request.setAttribute("messstockmovelist", messStockMoveList);
			
		 return result;
	}


	public void cancelStockMove() {
		
	String[] smIds = request.getParameterValues("stockmoveid");
	
	for (String stockmoveids : smIds) {
		
		
		MessStockMove messStockMove = new MessStockMoveDAO().getStockMoveDetails(Integer.parseInt(stockmoveids));
		MessStockEntry messStockEntry = new MessItemsDAO().getMessStockEntryByID(messStockMove.getStockentryid());
		float totalValue = messStockMove.getQuantity() * messStockEntry.getItemunitprice();
		
		//Pass J.V. : Debit the assets & credit the Expenses
		
		int drStockLedgerId = getLedgerAccountId("itemaccountid"+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		int crStockLedgerIdExpense = getLedgerAccountId("itemaccountidexpense"+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		
		VoucherEntrytransactions transactions = new VoucherEntrytransactions();
		
		transactions.setDraccountid(drStockLedgerId);
		transactions.setCraccountid(crStockLedgerIdExpense);
		transactions.setDramount(BigDecimal.valueOf(totalValue));
		transactions.setCramount(BigDecimal.valueOf(totalValue));
		transactions.setVouchertype(4);
		transactions.setTransactiondate(DateUtil.indiandateParser(request.getParameter("transactiondate_"+stockmoveids)));
		transactions.setEntrydate(DateUtil.todaysDate());
		transactions.setNarration("Towards Revarsal of Stock Issue");
		transactions.setCancelvoucher("no");
		transactions.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())).getFinancialid());
		transactions.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		transactions.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
		
		BigDecimal drAmountReceipt = BigDecimal.valueOf(totalValue);
		String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+drAmountReceipt+" where accountdetailsid="+drStockLedgerId;

		BigDecimal crAmountReceipt = BigDecimal.valueOf(totalValue);
		String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+crAmountReceipt+" where accountdetailsid="+crStockLedgerIdExpense;
		
		new MessStockMoveDAO().cancelStockMove(messStockMove,transactions,updateDrAccount,updateCrAccount);
		
	}
	
}
	
	
	
	public void getCustomerLastPrice() throws IOException {
		
		String customerName = request.getParameter("customerName");
		String[] custDetails = request.getParameter("customerName").split("_");
		String itemid = request.getParameter("itemid");
		Locale indiaLocale = new Locale("en", "IN");
		PrintWriter out = response.getWriter(); 
		response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        
		if(!customerName.isEmpty() && !itemid.isEmpty() ) {
			
			List<MessStockMove> messStockMove = new MessStockMoveDAO().getCustomerLastPrices(custDetails[0]+"_"+custDetails[1], itemid, Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			
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
	
	
}
