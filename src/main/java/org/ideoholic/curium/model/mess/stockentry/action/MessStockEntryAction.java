/**
 * 
 */
package org.ideoholic.curium.model.mess.stockentry.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.mess.item.service.MessItemsService;
import org.ideoholic.curium.model.mess.stockentry.service.MessStockEntryService;
import org.ideoholic.curium.model.mess.supplier.service.MessSuppliersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

	@GetMapping("/mrvDetails")
	public void mrvDetails() {
		try {
			new MessStockEntryService(request, response).getMRVDetails();
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
		return "purchase";
	}

	@RequestMapping(value = "/addSuppliers", method = { RequestMethod.GET, RequestMethod.POST })
	public String addSuppliers() {
		return "addsuppliers";
	}

}
