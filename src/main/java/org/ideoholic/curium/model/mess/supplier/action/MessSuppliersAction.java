/**
 * 
 */
package org.ideoholic.curium.model.mess.supplier.action;

import org.ideoholic.curium.model.account.action.AccountActionAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/**
 * @author Musaib_2
 * 
 */
@Controller
@RequestMapping({ "/MessSuppliersProcess", "/SupplierBalance" })
public class MessSuppliersAction {

	@Autowired
	private MessSuppliersActionAdapter messSuppliersActionAdapter;

	@Autowired
	private AccountActionAdapter accountActionAdapter;

	@PostMapping("/printSearchSupplierPaymentDetails")
	public String printSearchSupplierPaymentDetails() {
		accountActionAdapter.printSearchJournalEntries();
		return "printsupplierpaymentdetails";
	}

	@PostMapping("/searchSupplierPaymentDetails")
	public String searchSupplierPaymentDetails() {
		accountActionAdapter.searchJournalEntries();
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
		messSuppliersActionAdapter.viewBalanceSuppliers();
		return "printsuppliersbalance";
	}

	@GetMapping("/balanceSuppliers")
	public String balanceSuppliers() {
		messSuppliersActionAdapter.viewBalanceSuppliers();
		return "suppliersbalance";
	}

	@PostMapping("/printSupplierPayment")
	public String printSupplierPayment() {
		messSuppliersActionAdapter.viewSuppliersDetails();
		messSuppliersActionAdapter.viewSuppliersPaymentDetails();
		return "printsupplierpayment";
	}

	@PostMapping("/cancelCheque")
	public String cancelCheque() {

		messSuppliersActionAdapter.cancelCheque();
		messSuppliersActionAdapter.viewSuppliersDetails();
		messSuppliersActionAdapter.viewSuppliersPaymentDetails();
		return "supplierpayment";

	}

	@PostMapping("/clearedCheque")
	public String clearedCheque() {

		messSuppliersActionAdapter.clearedCheque();
		messSuppliersActionAdapter.viewSuppliersDetails();
		messSuppliersActionAdapter.viewSuppliersPaymentDetails();
		return "supplierpayment";

	}

	@PostMapping("/deliveredCheque")
	public String deliveredCheque() {
		messSuppliersActionAdapter.deliveredCheque();
		messSuppliersActionAdapter.viewSuppliersDetails();
		messSuppliersActionAdapter.viewSuppliersPaymentDetails();
		return "supplierpayment";
	}

	@PostMapping("/issueCheque")
	public String issueCheque() {
		messSuppliersActionAdapter.issueCheque();
		messSuppliersActionAdapter.viewSuppliersDetails();
		messSuppliersActionAdapter.viewSuppliersPaymentDetails();
		return "supplierpayment";
	}

	//TODO : This need refactoring in order to return JSON Response.
	@GetMapping("/getSupplierBalance")
	public void getSupplierBalance() {
		try {
			messSuppliersActionAdapter.getSupplierBalance();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@GetMapping("/paymentSuppliers")
	public String paymentSuppliers() {
		messSuppliersActionAdapter.viewSuppliersDetails();
		messSuppliersActionAdapter.viewSuppliersPaymentDetails();
		return "supplierpayment";
	}

	@PostMapping("/deleteSuppliers")
	public String deleteSuppliers() {
		messSuppliersActionAdapter.deleteMultipleSuppliers();
		return viewSuppliers();
	}

	@PostMapping("/updateSuppliers")
	public String updateSuppliers() {

		messSuppliersActionAdapter.updateSuppliers();
		return viewSuppliers();
	}

	@GetMapping("/viewSuppliers")
	public String viewSuppliers() {
		if(messSuppliersActionAdapter.viewSuppliersDetails()){
			return "addsuppliers";
		}
		else
			return "error";
	    }

	@PostMapping("/addSuppliers")
	public String addSuppliers() {
		messSuppliersActionAdapter.addSupplierDetails();
		return viewSuppliers();
	}

}
