/**
 * 
 */
package org.ideoholic.curium.model.mess.item.action;

import org.ideoholic.curium.model.mess.supplier.action.MessSuppliersActionAdapter;
import org.ideoholic.curium.model.student.action.StudentActionAdapter;
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
	private MessItemActionAdapter messItemActionAdapter;
	@Autowired
	private MessSuppliersActionAdapter messSuppliersActionAdapter;
	@Autowired
	private StudentActionAdapter studentActionAdapter;

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
		messItemActionAdapter.generateStockIssuanceReport();
		studentActionAdapter.viewAllStudentsParents();
		return "stockissuancereport";
	}

	@GetMapping("/issuanceStock")
	public String issuanceStock() {
		messItemActionAdapter.getIssuanceStock();
		studentActionAdapter.viewAllStudentsParents();
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

		messItemActionAdapter.cancelPurchase();
		messSuppliersActionAdapter.viewSuppliersDetails();
		messItemActionAdapter.viewItemDetails();
		messItemActionAdapter.getInvoiceDetails();
		return "purchase";
	}

	@PostMapping("/savePurchase")
	public String savePurchase() {
		messItemActionAdapter.savePurchase();
		messSuppliersActionAdapter.viewSuppliersDetails();
		messItemActionAdapter.viewItemDetails();
		messItemActionAdapter.getInvoiceDetails();
		return "purchase";
	}

	@PostMapping("/deleteItems")
	public String deleteItems() {
		messItemActionAdapter.deleteMultipleItems();
		return viewItems();
	}

	@PostMapping("/updateItems")
	public String updateItems() {

		messItemActionAdapter.updateItems();
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
		messSuppliersActionAdapter.viewSuppliersDetails();
		messItemActionAdapter.viewItemDetails();
		messItemActionAdapter.getInvoiceDetails();
		return "purchase";
	}

	@GetMapping("/addsuppliers")
	public String addSuppliers() {
		return "addsuppliers";
	}

}
