/**
 * 
 */
package org.ideoholic.curium.model.mess.supplier.action;

import org.ideoholic.curium.model.account.service.AccountService;
import org.ideoholic.curium.model.mess.supplier.service.MessSuppliersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Musaib_2
 * 
 */
@Controller
@RequestMapping({ "/MessSuppliersProcess", "/SupplierBalance" })
public class MessSuppliersAction {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	@Autowired
	private MessSuppliersActionAdapter messSuppliersActionAdapter;

	@PostMapping("/printSearchSupplierPaymentDetails")
	public String printSearchSupplierPaymentDetails() {
		new AccountService(request, response).printSearchJournalEntries();
		return "printsupplierpaymentdetails";
	}

	@PostMapping("/searchSupplierPaymentDetails")
	public String searchSupplierPaymentDetails() {
		new AccountService(request, response).searchJournalEntries();
		messSuppliersActionAdapter.viewSuppliersDetails();
		return "supplierpaymentdetails";
	}

	@GetMapping("/supplierPaymentReport")
	public String supplierPaymentReport() {
		messSuppliersActionAdapter.viewSuppliersDetails();
		return "supplierpaymentdetails";
	}

	@PostMapping("/printSuppliersBalance")
	public String printSuppliersBalance() {
		new MessSuppliersService(request, response).viewBalanceSuppliers();
		return "printsuppliersbalance";
	}

	@GetMapping("/balanceSuppliers")
	public String balanceSuppliers() {
		new MessSuppliersService(request, response).viewBalanceSuppliers();
		return "suppliersbalance";
	}

	@PostMapping("/printSupplierPayment")
	public String printSupplierPayment() {
		messSuppliersActionAdapter.viewSuppliersDetails();
		new MessSuppliersService(request, response).viewSuppliersPaymentDetails();
		return "printsupplierpayment";
	}

	@PostMapping("/cancelCheque")
	public String cancelCheque() {

		new MessSuppliersService(request, response).cancelCheque();
		messSuppliersActionAdapter.viewSuppliersDetails();
		new MessSuppliersService(request, response).viewSuppliersPaymentDetails();
		return "supplierpayment";

	}

	@PostMapping("/clearedCheque")
	public String clearedCheque() {

		new MessSuppliersService(request, response).clearedCheque();
		messSuppliersActionAdapter.viewSuppliersDetails();
		new MessSuppliersService(request, response).viewSuppliersPaymentDetails();
		return "supplierpayment";

	}

	@PostMapping("/deliveredCheque")
	public String deliveredCheque() {
		new MessSuppliersService(request, response).deliveredCheque();
		messSuppliersActionAdapter.viewSuppliersDetails();
		new MessSuppliersService(request, response).viewSuppliersPaymentDetails();
		return "supplierpayment";
	}

	@PostMapping("/issueCheque")
	public String issueCheque() {
		new MessSuppliersService(request, response).issueCheque();
		messSuppliersActionAdapter.viewSuppliersDetails();
		new MessSuppliersService(request, response).viewSuppliersPaymentDetails();
		return "supplierpayment";
	}

	@GetMapping("/getSupplierBalance")
	public void getSupplierBalance() {
		try {
			new MessSuppliersService(request, response).getSupplierBalance();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@GetMapping("/paymentSuppliers")
	public String paymentSuppliers() {
		messSuppliersActionAdapter.viewSuppliersDetails();
		new MessSuppliersService(request, response).viewSuppliersPaymentDetails();
		return "supplierpayment";
	}

	@PostMapping("/deleteSuppliers")
	public String deleteSuppliers() {
		new MessSuppliersService(request, response).deleteMultipleSuppliers();
		return viewSuppliers();
	}

	@PostMapping("/updateSuppliers")
	public String updateSuppliers() {

		new MessSuppliersService(request, response).updateSuppliers();
		return viewSuppliers();
	}

	@GetMapping("/viewSuppliers")
	public String viewSuppliers() {
		return messSuppliersActionAdapter.viewSuppliersDetails();
	}

	@PostMapping("/addSuppliers")
	public String addSuppliers() {
		new MessSuppliersService(request, response).addSupplierDetails();
		return viewSuppliers();
	}

}
