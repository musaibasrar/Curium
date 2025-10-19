	package org.ideoholic.curium.model.mess.stockentry.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.ideoholic.curium.model.mess.item.dao.MessItemsDAO;
import org.ideoholic.curium.model.mess.item.dto.MessItems;
import org.ideoholic.curium.model.mess.item.dto.PurchaseOrder;
import org.ideoholic.curium.model.mess.stockentry.dao.MessStockEntryDAO;
import org.ideoholic.curium.model.mess.stockentry.dto.MessStockEntry;
import org.ideoholic.curium.model.mess.stockentry.dto.MessStockEntryResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessStockEntryService {
	@Autowired
	private HttpServletResponse response;
	
	@Autowired
	private MessItemsDAO messItemsDao;

	@Autowired
	private MessStockEntryDAO messStockEntryDao;


	public MessStockEntryResponseDto getMRVDetails(String strInvoiceDetailsId, String supplierRefNo, String invoiceTotal, String supplierName, String invoiceDate, String branchId)  throws IOException {
		MessStockEntryResponseDto responseDto = MessStockEntryResponseDto.builder().build();

		if(branchId!=null){
			
			int invoiceDetailsId = Integer.parseInt(strInvoiceDetailsId);

			responseDto.setSupplierRefNo(supplierRefNo);
			responseDto.setInvoiceTotal(invoiceTotal);
			responseDto.setSupplierName(supplierName);
			responseDto.setInvoiceDate(invoiceDate);
			
			List<MessStockEntry> messStockEntryList = new ArrayList<MessStockEntry>();
			messStockEntryList = messStockEntryDao.getMRVDetails(invoiceDetailsId);
			//List<VoucherEntrytransactions> vet = new AccountDAO().getVoucherDetailsByNarration(messStockEntryList.get(0).getMessinvoicedetails().getSupplierreferenceno());
			String labourCharge = "100";
			
			/*
			 * if(!vet.isEmpty()) {
			 * labourCharge=vet.get(0).getDramount().toBigInteger().toString(); }
			 */
			
			PrintWriter out = response.getWriter(); 
			response.setContentType("text/xml");
		        response.setHeader("Cache-Control", "no-cache");
		        
		        
		        try {
		        	
		        	StringBuilder tableBuilder = new StringBuilder(
		        				"<table  style='margin-left: auto;margin-right: auto;'>" + 
		        				"						<tr>" + 
		        				"							<td class='alignRight'>Supplier &nbsp;</td>" + 
		        				"							<td class='alignRightInvoice'>" + 
		        				"								"+supplierName+"" + 
		        				"							</td>" + 
		        				"							" + 
		        				"							<td class='alignRight'>Reference/Invoice No.&nbsp;</td>" + 
		        				"							<td class='alignRightInvoice'>"
		        				+ "									"+supplierRefNo+"</td>" +
		        				"							" + 
		        				"						</tr>" + 
		        				"						<tr>" + 
		        				"							<td><br /></td>" + 
		        				"							</tr>" + 
		        				"						<tr>" + 
		        				"						<td class='alignRight'>Invoice Date&nbsp;</td>" + 
		        				"							<td class='alignRightInvoice'> "+invoiceDate+"</td>" + 
		        				"						<td class='alignRight'>Grand Total&nbsp;</td>" + 
		        				"							<td class='alignRightInvoice'> "+invoiceTotal+"</td>" +
		        				"							</tr>" + 
		        				"							<tr>" + 
		        				"							<td><br /></td>" + 
		        				"							</tr>" + 
		        				"							<tr>" + 
		        				"							<td><br /></td>" + 
		        				"							</tr>" + 
		        				"							</tr>" + 
		        				"						<tr>" + 
		        				"							<td class='alignRight'>&nbsp;</td>" + 
		        				"							<td class='alignRightInvoice'></td>" + 
		        				"							<td class='alignRight'>&nbsp;&nbsp;&nbsp;Labour charges&nbsp;</td>" + 
		        				"							<td class='alignRightInvoice'> "+labourCharge+"</td>" + 
		        				"							</tr>" + 
		        				"							<tr>" + 
		        				"							<td><br /></td>" + 
		        				"							</tr>" + 
		        				"					</table>"
		        			
		        			
		        			);
		        	
		        	
		        	StringBuilder rowBuidler = new StringBuilder( "<table border='1' style='margin-left: auto;margin-right: auto;' style='border-color:#4b6a84' id='vd'>" + 
			        													"<thead>" + 
			        													"<tr class='headerText' >" + 
			        													"<th>Item Name</th>" +
			        													"<th>Batch No.</th>" +
			        													"<th>Quantity</th>" + 
			        													"<th>UOM</th>" + 
			        													"<th>Sales Price</th>" +
			        													"<th>Purchase Price</th>" +
			        													"<th>SGST</th>" +
			        													"<th>CGST</th>" +
			        													"<th>Item Total</th>" + 
			        													"</tr>" + 
			        													"</thead>" + 
		        													"<tbody>");
		        	
		        	
		        	for (MessStockEntry messStockEntry : messStockEntryList) {
		        		
		        			   MessItems messItems = messItemsDao.getItem(messStockEntry.getItemid());
		        			   float itemTotal = messStockEntry.getQuantity() * messStockEntry.getItemunitprice();
		        			   String[] purchasePrice = messStockEntry.getExternalid().split("_");
		        		rowBuidler.append(
		        	                 "<tr style='border-color:#000000' border='1' cellpadding='1' cellspacing='1' >" + 
		        			         "<td class='dataText'>"+messItems.getName()+"</td>" + 
		        			         "<td class='dataText'>"+messStockEntry.getBatchno()+"</td>" + 
		        			         "<td class='dataText'>"+messStockEntry.getQuantity()+"</td>" + 
		        			         "<td class='dataText'>"+messItems.getUnitofmeasure()+"</td>" + 
		        			         "<td class='dataText'>"+purchasePrice[1]+"</td>" +
		        			         "<td class='dataText'>"+messStockEntry.getItemunitprice()+"</td>" +
		        			         "<td class='dataText'>"+messStockEntry.getSgst()+"</td>" +
		        			         "<td class='dataText'>"+messStockEntry.getCgst()+"</td>" +
		        			         "<td class='dataText'>"+itemTotal+"</td>" + 
		        			         "</tr>");
		        		
		        		
		        	}
		        	
		        	rowBuidler.append("</tbody>" + 
		        			"		                </table>");
		        	
		        	tableBuilder.append(rowBuidler.toString());
		        	String outputTable = tableBuilder.toString();
		        	
		        	response.getWriter().println(outputTable);
		        	
		        } catch (Exception e) {
		            out.write("<table> <tr><td>Data Not Available</td></tr></table>");
		        } finally {
		            out.flush();
		            out.close();
		        }
		}
		responseDto.setSuccess(true);
		return responseDto;
		
	}


	public MessStockEntryResponseDto getPurchaseOrderById(String strInvoiceDetailsId, 
			String supplierName, String invoiceDate, String branchId) throws IOException {
		MessStockEntryResponseDto responseDto = MessStockEntryResponseDto.builder().build();

		if(branchId!=null){
			
			//int invoiceDetailsId = Integer.parseInt(strInvoiceDetailsId);

			responseDto.setSupplierName(supplierName);
			responseDto.setInvoiceDate(invoiceDate);
			
			List<PurchaseOrder> purchaseOrderList = new ArrayList<PurchaseOrder>();
			purchaseOrderList = messStockEntryDao.getPurchaseOrderById(strInvoiceDetailsId);
			//List<VoucherEntrytransactions> vet = new AccountDAO().getVoucherDetailsByNarration(messStockEntryList.get(0).getMessinvoicedetails().getSupplierreferenceno());
			
			/*
			 * if(!vet.isEmpty()) {
			 * labourCharge=vet.get(0).getDramount().toBigInteger().toString(); }
			 */
			
			PrintWriter out = response.getWriter(); 
			response.setContentType("text/xml");
		        response.setHeader("Cache-Control", "no-cache");
		        
		        
		        try {
		        	
		        	StringBuilder tableBuilder = new StringBuilder(
		        				"<table  style='margin-left: auto;margin-right: auto;'>" + 
		        				"						<tr>" + 
		        				"							<td class='alignRight'>Supplier &nbsp;</td>" + 
		        				"							<td class='alignRightInvoice'>" + 
		        				"								"+supplierName+"" + 
		        				"							</td>" + 
		        				"							" + 
									/*
									 * "							<td class='alignRight'>Reference/Invoice No.&nbsp;</td>"
									 * + "							<td class='alignRightInvoice'>" +
									 * "									"+supplierRefNo+"</td>" +
									 */
		        				"							" + 
		        				"						</tr>" + 
		        				"						<tr>" + 
		        				"							<td><br /></td>" + 
		        				"							</tr>" + 
		        				"						<tr>" + 
		        				"						<td class='alignRight'>P.O.Date&nbsp;</td>" + 
		        				"							<td class='alignRightInvoice'> "+invoiceDate+"</td>" + 
									/*
									 * "						<td class='alignRight'>Grand Total&nbsp;</td>" +
									 * "							<td class='alignRightInvoice'> "+invoiceTotal+
									 * "</td>" +
									 */
		        				"							</tr>" + 
		        				"							<tr>" + 
		        				"							<td><br /></td>" + 
		        				"							</tr>" + 
		        				"							<tr>" + 
		        				"							<td><br /></td>" + 
		        				"							</tr>" + 
		        				"							</tr>" + 
		        				"						<tr>" + 
		        				"							<td class='alignRight'>&nbsp;</td>" + 
		        				"							<td class='alignRightInvoice'></td>" + 
		        				"							</tr>" + 
		        				"							<tr>" + 
		        				"							<td><br /></td>" + 
		        				"							</tr>" + 
		        				"					</table>"
		        			
		        			
		        			);
		        	
		        	
		        	StringBuilder rowBuidler = new StringBuilder( "<table border='1' style='margin-left: auto;margin-right: auto;' style='border-color:#4b6a84' id='vd'>" + 
			        													"<thead>" + 
			        													"<tr class='headerText' >" + 
			        													"<th>Item Name</th>" +
			        													//"<th>Batch No.</th>" +
			        													"<th>Quantity</th>" + 
			        													//"<th>UOM</th>" + 
							/*
							 * "<th>Sales Price</th>" + "<th>Purchase Price</th>" + "<th>SGST</th>" +
							 * "<th>CGST</th>" + "<th>Item Total</th>" +
							 */
			        													"</tr>" + 
			        													"</thead>" + 
		        													"<tbody>");
		        	
		        	
		        	for (PurchaseOrder purchaseOrder : purchaseOrderList) {
		        		
		        		rowBuidler.append(
		        	                 "<tr style='border-color:#000000' border='1' cellpadding='1' cellspacing='1' >" + 
		        	                 "<td class='dataText'>"+purchaseOrder.getSupplierName()+"</td>" + 
		        			         "<td class='dataText'>"+purchaseOrder.getQuantity()+"</td>" + 
		        			         "</tr>");
		        		
		        		
		        	}
		        	
		        	rowBuidler.append("</tbody>" + 
		        			"		                </table>");
		        	
		        	tableBuilder.append(rowBuidler.toString());
		        	String outputTable = tableBuilder.toString();
		        	
		        	response.getWriter().println(outputTable);
		        	
		        } catch (Exception e) {
		            out.write("<table> <tr><td>Data Not Available</td></tr></table>");
		        } finally {
		            out.flush();
		            out.close();
		        }
		}
		responseDto.setSuccess(true);
		return responseDto;
		
	}
	
	public MessStockEntryResponseDto getDueMRVDetails(String studentName, 
			String branchreceiptnumber, String due, String date, String branchId, String currentAcademicYear) throws IOException {
		MessStockEntryResponseDto responseDto = MessStockEntryResponseDto.builder().build();

		if(branchId!=null){
			

			responseDto.setStudentName(studentName);
			responseDto.setBranchReceiptNumber(branchreceiptnumber);
			
			//List<PurchaseOrder> purchaseOrderList = new ArrayList<PurchaseOrder>();
			//purchaseOrderList = new MessStockEntryDAO().getPurchaseMRVDetails(strInvoiceDetailsId);
			
			
			PrintWriter out = response.getWriter(); 
			response.setContentType("text/xml");
		        response.setHeader("Cache-Control", "no-cache");
		        
		        
		        try {
		        	
		        	StringBuilder tableBuilder = new StringBuilder(
		        				"<table  style='margin-left: auto;margin-right: auto;'>" + 
		        				"						<tr>" + 
		        				"							<td class='alignRight'>Student Name &nbsp;</td>" + 
		        				"							<td class='alignRightInvoice'>" + 
		        				"								"+studentName+"" + 
		        				"							</td>" + 
		        				"							" + 
									
		        				"							" + 
		        				"						</tr>" + 
		        				"						<tr>" + 
		        				"							<td><br /></td>" + 
		        				"							</tr>" + 
		        				"						<tr>" + 
		        				"						<td class='alignRight'>Current Due Amount&nbsp;</td>" + 
		        				"							<td class='alignRightInvoice'> "+due+"</td>" + 
									
		        				"							</tr>" + 
		        				"							<tr>" + 
		        				"							<td><br /></td>" + 
		        				"							</tr>" + 
		        				"							<tr>" + 
		        				"							<td><br /></td>" + 
		        				"							</tr>" + 
		        				"							</tr>" + 
		        				"						<tr>" + 
		        				"							<td class='alignRight'>&nbsp;</td>" + 
		        				"							<td class='alignRightInvoice'></td>" + 
		        				"							</tr>" + 
		        				"							<tr>" + 
		        				"							<td><br /></td>" + 
		        				"							</tr>" + 
		        				"					</table>"
		        			
		        			
		        			);
		        	
		        	
		        	StringBuilder rowBuidler = new StringBuilder( "<table border='1' style='margin-left: auto;margin-right: auto;' style='border-color:#4b6a84' id='vd'>" + 
			        													"<thead>" + 
			        													"<tr class='headerText' >" + 
			        													"<th>Date</th>" +
			        													"<th>Student Name</th>" +
			        													"<th>Branch Receipt Number</th>" + 
			        													"<th>Academic Year</th>" +
			        													"<th>Due Amount</th>" +
			        													"</tr>" + 
			        													"</thead>" + 
		        													"<tbody>");
		        	
		        	
		        	//for (PurchaseOrder purchaseOrder : purchaseOrderList) {
		        		
		        		rowBuidler.append(
		        	                 "<tr style='border-color:#000000' border='1' cellpadding='1' cellspacing='1' >" + 
		        	                 "<td class='dataText'>"+date+"</td>" + 		 
		        	                 "<td class='dataText'>"+studentName+"</td>" + 
		        			         "<td class='dataText'>"+branchreceiptnumber+"</td>" + 
		        			         "<td class='dataText'>"+currentAcademicYear+"</td>" + 
		        			         "<td class='dataText'>"+due+"</td>" + 
		        			         "</tr>");
		        		
		        		
		        	//}
		        	
		        	rowBuidler.append("</tbody>" + 
		        			"		                </table>");
		        	
		        	tableBuilder.append(rowBuidler.toString());
		        	String outputTable = tableBuilder.toString();
		        	
		        	response.getWriter().println(outputTable);
		        	
		        } catch (Exception e) {
		            out.write("<table> <tr><td>Data Not Available</td></tr></table>");
		        } finally {
		            out.flush();
		            out.close();
		        }
		}
		responseDto.setSuccess(true);
		return responseDto;
		
	}
	
	
}
