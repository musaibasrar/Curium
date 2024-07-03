/**
 * 
 */
package org.ideoholic.curium.model.mess.item.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ideoholic.curium.model.mess.item.service.MessItemsService;
import org.ideoholic.curium.model.mess.supplier.service.MessSuppliersService;
import org.ideoholic.curium.model.student.service.StudentService;
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
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private MessItemActionAdapter messItemActionAdapter;

	@PostMapping("/printStockReceivedReport")
	public String printStockReceivedReport() {
		return "printstockreceivedreport";
	}

	@PostMapping("/generateStockReceivedReport")
	public String generateStockReceivedReport() {
		messItemActionAdapter.generateStockReceivedReport();
		return "stockreceivedreport";
	}

	@GetMapping("/receiveStock")
	public String receiveStockReport() {
		messItemActionAdapter.receiveStockReport();
		return "stockreceivedreport";
	}

	@PostMapping("/printStockIssuanceReport")
	public String printStockIssuanceReport() {
		return "printstockissuancereport";
	}

	@PostMapping("/generateStockIssuanceReport")
	public String generateStockIssuanceReport() {
		new MessItemsService(request, response).generateStockIssuanceReport();
		new StudentService(request, response).viewAllStudentsParents();
		return "stockissuancereport";
	}

	@GetMapping("/issuanceStock")
	public String issuanceStock() {
		messItemActionAdapter.getIssuanceStock();
		new StudentService(request, response).viewAllStudentsParents();
		return "stockissuancereport";
	}

	@PostMapping("/printBatchStockAvailability")
	public String printBatchStockAvailability() {
		messItemActionAdapter.getBatchStock();
		return "printbatchstock";
	}

	@GetMapping("/batchStock")
	public String batchStock() {
		messItemActionAdapter.getBatchStock();
		return "batchstock";
	}

	@PostMapping("/printStockAvailability")
	public String printStockAvailability() {
		messItemActionAdapter.getCurrentStock();
		return "printcurrentstock";
	}

	@GetMapping("/currentStock")
	public String currentStock() {
		messItemActionAdapter.getCurrentStock();
		return "currentstock";
	}

	@PostMapping("/cancelPurchase")
	public String cancelPurchase() {

		new MessItemsService(request, response).cancelPurchase();
		new MessSuppliersService(request, response).viewSuppliersDetails();
		messItemActionAdapter.viewItemDetails();
		new MessItemsService(request, response).getInvoiceDetails();
		return "purchase";
	}

	@PostMapping("/savePurchase")
	public String savePurchase() {
		new MessItemsService(request, response).savePurchase();
		new MessSuppliersService(request, response).viewSuppliersDetails();
		messItemActionAdapter.viewItemDetails();
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
		return messItemActionAdapter.viewItemDetails();
	}

	@PostMapping("/addItems")
	public String addItems() {
		messItemActionAdapter.addItemDetails();
		return viewItems();
	}

	@GetMapping("/purchaseItems")
	public String purchaseItems() {
		new MessSuppliersService(request, response).viewSuppliersDetails();
		messItemActionAdapter.viewItemDetails();
		new MessItemsService(request, response).getInvoiceDetails();
		return "purchase";
	}

	@GetMapping("/addsuppliers")
	public String addSuppliers() {
		return "addsuppliers";
	}

}
