package com.model.mess.stockmove.service;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.account.dao.AccountDAO;
import com.model.account.dto.VoucherEntrytransactions;
import com.model.mess.item.dao.MessItemsDAO;
import com.model.mess.item.dto.MessItems;
import com.model.mess.item.service.MessItemsService;
import com.model.mess.stockentry.dto.MessStockAvailability;
import com.model.mess.stockentry.dto.MessStockEntry;
import com.model.mess.stockmove.dao.MessStockMoveDAO;
import com.model.mess.stockmove.dto.MessStockItemDetails;
import com.model.mess.stockmove.dto.MessStockMove;
import com.util.DateUtil;

public class MessStockMoveService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;
	private String BRANCHID = "branchid";
	
	public MessStockMoveService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}


	public void saveStockMove() {

		boolean result = false;
		
		if(httpSession.getAttribute(BRANCHID)!=null){
			
			
				String[] StockEntryIds = request.getParameterValues("ids");
				String[] itemsName = request.getParameterValues("itemsname");
				String[] itemsIds = request.getParameterValues("itemsids");
				String[] issuequantity = request.getParameterValues("issuequantity");
				String[] itemunitprice = request.getParameterValues("itemunitprice");
				BigDecimal totalValue = BigDecimal.ZERO;
				
				List<MessStockMove> messStockMovesList = new ArrayList<MessStockMove>();
				
					for(int i=0; i < StockEntryIds.length ; i++){
						

						MessStockMove messStockMove = new MessStockMove();
						
						messStockMove.setStockentryid(Integer.parseInt(StockEntryIds[i]));
						messStockMove.setItemid(Integer.parseInt(itemsIds[i]));
						messStockMove.setExternalid(itemsName[i]);
						messStockMove.setQuantity(Float.parseFloat(issuequantity[i]));
						messStockMove.setPurpose(request.getParameter("purpose"));
						messStockMove.setTransactiondate(DateUtil.indiandateParser(request.getParameter("transactiondate")));
						messStockMove.setIssuedto(request.getParameter("issuedto"));
						messStockMove.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
						messStockMove.setStatus("ACTIVE");
						
						totalValue = totalValue.add(new BigDecimal(issuequantity[i]).multiply(new BigDecimal(itemunitprice[i])));
						messStockMovesList.add(messStockMove);
					
				}
					
						//Pass J.V. : credit the assets & debit the Expenses
						int drStockLedgerIdExpense = getLedgerAccountId("itemaccountidexpense");
						int crStockLedgerId = getLedgerAccountId("itemaccountid");
						
						VoucherEntrytransactions transactions = new VoucherEntrytransactions();
						
						transactions.setDraccountid(drStockLedgerIdExpense);
						transactions.setCraccountid(crStockLedgerId);
						transactions.setDramount(totalValue);
						transactions.setCramount(totalValue);
						transactions.setVouchertype(4);
						transactions.setTransactiondate(DateUtil.indiandateParser(request.getParameter("transactiondate")));
						transactions.setNarration("Towards Stock Issue");
						transactions.setCancelvoucher("no");
						transactions.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())).getFinancialid());
						transactions.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
						
						
						BigDecimal drAmountReceipt = totalValue;
						String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+drAmountReceipt+" where accountdetailsid="+drStockLedgerIdExpense;

						BigDecimal crAmountReceipt = totalValue;
						String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+crAmountReceipt+" where accountdetailsid="+crStockLedgerId;
						
						result = new MessStockMoveDAO().moveStockSave(messStockMovesList,transactions,updateDrAccount,updateCrAccount);
			}
		new MessItemsService(request, response).viewItemDetails();
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
				 	
				 for (MessItems messItems : messItem) {
					if(messStockEntry.getItemid() == messItems.getId()) {
						MessStockItemDetails messStockItemDetails = new MessStockItemDetails();
						messStockItemDetails.setAvailablequantity(messStockEntry.getAvailablequantity());
						messStockItemDetails.setBatchno(messStockEntry.getBatchno());
						messStockItemDetails.setItemid(messItems.getId());
						messStockItemDetails.setItemunitprice(messStockEntry.getItemunitprice());
						messStockItemDetails.setStockentryexternalid(messStockEntry.getExternalid());
						messStockItemDetails.setStockentryid(messStockEntry.getId());
						messStockItemDetails.setItemname(messItems.getName());
						messStockItemDetails.setUnitofmeasure(messItems.getUnitofmeasure());
						messStockItemDetailsList.add(messStockItemDetails);
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


	public void viewStockMoveDetails() {
		
		List<MessStockMove> messStockMoveList = new ArrayList<MessStockMove>();
		
		 if(httpSession.getAttribute(BRANCHID)!=null){
			 messStockMoveList = new MessStockMoveDAO().getStockMoveDetails(); 
		 }
		 
		 request.setAttribute("messstockmovelist", messStockMoveList);
		 
	}


	public void cancelStockMove() {
		
	String[] smIds = request.getParameterValues("stockmoveid");
	
	for (String stockmoveids : smIds) {
		
		
		MessStockMove messStockMove = new MessStockMoveDAO().getStockMoveDetails(Integer.parseInt(stockmoveids));
		float totalValue = messStockMove.getQuantity() * messStockMove.getQuantity();
		
		//Pass J.V. : Debit the assets & credit the Expenses
		
		int drStockLedgerId = getLedgerAccountId("itemaccountid");
		int crStockLedgerIdExpense = getLedgerAccountId("itemaccountidexpense");
		
		VoucherEntrytransactions transactions = new VoucherEntrytransactions();
		
		transactions.setDraccountid(drStockLedgerId);
		transactions.setCraccountid(crStockLedgerIdExpense);
		transactions.setDramount(BigDecimal.valueOf(totalValue));
		transactions.setCramount(BigDecimal.valueOf(totalValue));
		transactions.setVouchertype(4);
		transactions.setTransactiondate(DateUtil.indiandateParser(request.getParameter("transactiondate")));
		transactions.setNarration("Towards Revarsal of Stock Issue");
		transactions.setCancelvoucher("no");
		transactions.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())).getFinancialid());
		transactions.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		
		
		BigDecimal drAmountReceipt = BigDecimal.valueOf(totalValue);
		String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+drAmountReceipt+" where accountdetailsid="+drStockLedgerId;

		BigDecimal crAmountReceipt = BigDecimal.valueOf(totalValue);
		String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+crAmountReceipt+" where accountdetailsid="+crStockLedgerIdExpense;
		
		new MessStockMoveDAO().cancelStockMove(messStockMove,transactions,updateDrAccount,updateCrAccount);
		
	}
	
}
	
}
