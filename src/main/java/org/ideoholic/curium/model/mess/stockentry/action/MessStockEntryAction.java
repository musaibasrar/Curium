/**
 * 
 */
package org.ideoholic.curium.model.mess.stockentry.action;

import org.ideoholic.curium.model.mess.item.action.MessItemActionAdapter;
import org.ideoholic.curium.model.mess.supplier.action.MessSuppliersActionAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Musaib_2
 * 
 */

@Controller
@RequestMapping("/stockentry")
public class MessStockEntryAction {

	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	@Autowired
	HttpSession httpSession;
	@Autowired
	private MessItemActionAdapter messItemActionAdapter;
	@Autowired
	private MessStockEntryActionAdapter messStockEntryActionAdapter;
	@Autowired
	private MessSuppliersActionAdapter messSuppliersActionAdapter;

	@GetMapping("/mrvDetails")
	public void mrvDetails() {
		try {
			messStockEntryActionAdapter.getMRVDetails();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@PostMapping("/savePurchase")
	public String savePurchase() {

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
		return "purchase";
	}

	@RequestMapping(value = "/addSuppliers", method = { RequestMethod.GET, RequestMethod.POST })
	public String addSuppliers() {
		return "addsuppliers";
	}

}
