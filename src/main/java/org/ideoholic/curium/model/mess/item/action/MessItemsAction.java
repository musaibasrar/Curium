/**
 * 
 */
package org.ideoholic.curium.model.mess.item.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ideoholic.curium.model.mess.item.service.MessItemsService;
import org.ideoholic.curium.model.mess.supplier.service.MessSuppliersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Musaib_2
 * 
 */
@Controller
@RequestMapping("/MessItemsProcess")
public class MessItemsAction {

	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;

	@PostMapping("/printStockReceivedReport")
	public String printStockReceivedReport() {
		return "printstockreceivedreport";
	}

	@PostMapping("/generateStockReceivedReport")
	public String generateStockReceivedReport() {
		new MessItemsService(request, response).generateStockReceivedReport();
		return "stockreceivedreport";
	}

	@GetMapping("/receiveStock")
	public String receiveStockReport() {
		new MessItemsService(request, response).receiveStockReport();
		return "stockreceivedreport";
	}

	@PostMapping("/printStockIssuanceReport")
	public String printStockIssuanceReport() {
		return "printstockissuancereport";
	}

	@PostMapping("/generateStockIssuanceReport")
	public String generateStockIssuanceReport() {
		new MessItemsService(request, response).generateStockIssuanceReport();
		return "stockissuancereport";
	}

	@GetMapping("/issuanceStock")
	public String issuanceStock() {
		new MessItemsService(request, response).getIssuanceStock();
		return "stockissuancereport";
	}

	@PostMapping("/printBatchStockAvailability")
	public String printBatchStockAvailability() {
		new MessItemsService(request, response).getBatchStock();
		return "printbatchstock";
	}

	@GetMapping("/batchStock")
	public String batchStock() {
		new MessItemsService(request, response).getBatchStock();
		return "batchstock";
	}

	@PostMapping("/printStockAvailability")
	public String printStockAvailability() {
		new MessItemsService(request, response).getCurrentStock();
		return "printcurrentstock";
	}

	@GetMapping("/currentStock")
	public String currentStock() {
		new MessItemsService(request, response).getCurrentStock();
		return "currentstock";
	}

	@PostMapping("/cancelPurchase")
	public String cancelPurchase() {

		new MessItemsService(request, response).cancelPurchase();
		new MessSuppliersService(request, response).viewSuppliersDetails();
		new MessItemsService(request, response).viewItemDetails();
		new MessItemsService(request, response).getInvoiceDetails();
		return "purchase";
	}

	@PostMapping("/savePurchase")
	public String savePurchase() {
		new MessItemsService(request, response).savePurchase();
		new MessSuppliersService(request, response).viewSuppliersDetails();
		new MessItemsService(request, response).viewItemDetails();
		new MessItemsService(request, response).getInvoiceDetails();
		return "purchase";
	}

	@PostMapping("/deleteItems")
	public String deleteItems() {
		new MessItemsService(request, response).deleteMultipleItems();
		return viewItems();
	}

	@PostMapping("/updateItems")
	public String updateItems() {

		new MessItemsService(request, response).updateItems();
		return viewItems();
	}

	@GetMapping("/viewItems")
	public String viewItems() {
		return new MessItemsService(request, response).viewItemDetails();
	}

	@PostMapping("/addItems")
	public String addItems() {
		new MessItemsService(request, response).addItemDetails();
		return viewItems();
	}

	@GetMapping("/purchaseItems")
	public String purchaseItems() {
		new MessSuppliersService(request, response).viewSuppliersDetails();
		new MessItemsService(request, response).viewItemDetails();
		new MessItemsService(request, response).getInvoiceDetails();
		return "purchase";
	}

	@GetMapping("/addsuppliers")
	public String addSuppliers() {
		return "addsuppliers";
	}

}
