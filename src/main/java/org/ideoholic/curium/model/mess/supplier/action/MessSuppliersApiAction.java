package org.ideoholic.curium.model.mess.supplier.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.account.dto.PrintSearchJournalEntriesDto;
import org.ideoholic.curium.model.account.dto.SearchJournalEntriesResponseDto;
import org.ideoholic.curium.model.account.dto.SearchLedgerEntriesDto;
import org.ideoholic.curium.model.mess.item.dto.MessIdsDto;
import org.ideoholic.curium.model.mess.supplier.dto.ChequeDetailsDto;
import org.ideoholic.curium.model.mess.supplier.dto.ChequeDto;
import org.ideoholic.curium.model.mess.supplier.dto.SupplierPaymentDto;
import org.ideoholic.curium.model.mess.supplier.dto.SuppliersDetailsDto;
import org.ideoholic.curium.model.mess.supplier.dto.SuppliersDetailsResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping({ "/api/v1/messSuppliersProcess", "/api/v1/supplierBalance" })
public interface MessSuppliersApiAction {

	@PostMapping("/printSearchSupplierPaymentDetails")
	public ResponseEntity<SearchJournalEntriesResponseDto> printSearchSupplierPaymentDetails(
			@RequestBody PrintSearchJournalEntriesDto dto, @RequestHeader(value = "branchid") String branchId);

	@PostMapping("/searchSupplierPaymentDetails")
	public ResponseEntity<ResultResponse> searchSupplierPaymentDetails(@RequestBody SearchLedgerEntriesDto dto,
			@RequestHeader(value = "branchid") String branchId);

	@GetMapping("/supplierPaymentReport")
	public ResponseEntity<ResultResponse> supplierPaymentReport(@RequestHeader(value = "branchid") String branchId);

	@PostMapping("/printSuppliersBalance")
	public ResponseEntity<ResultResponse> printSuppliersBalance(@RequestHeader(value = "branchid") String branchId);

	@GetMapping("/balanceSuppliers")
	public ResponseEntity<ResultResponse> balanceSuppliers(@RequestHeader(value = "branchid") String branchId);

	@PostMapping("/printSupplierPayment")
	public ResponseEntity<SupplierPaymentDto> printSupplierPayment(@RequestHeader(value = "branchid") String branchId,
			@RequestParam(value = "page") String page);

	@PostMapping("/cancelCheque")
	public ResponseEntity<SupplierPaymentDto> cancelCheque(@RequestBody ChequeDetailsDto dto,
			@RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "userloginid") String userId,
			@RequestParam(value = "page") String page);

	@PostMapping("/clearedCheque")
	public ResponseEntity<SupplierPaymentDto> clearedCheque(@RequestBody ChequeDetailsDto dto,
			@RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "userloginid") String userId,
			@RequestParam(value = "page") String page);

	@PostMapping("/deliveredCheque")
	public ResponseEntity<SupplierPaymentDto> deliveredCheque(@RequestBody ChequeDetailsDto dto,
			@RequestHeader(value = "branchid") String branchId, @RequestParam(value = "page") String page);

	@PostMapping("/issueCheque")
	public ResponseEntity<SupplierPaymentDto> issueCheque(@RequestBody ChequeDto dto,
			@RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "userloginid") String userId,
			@RequestParam(value = "page") String page);

	// TODO : This need refactoring in order to return JSON Response.
	@GetMapping("/getSupplierBalance")
	public ResponseEntity getSupplierBalance(@RequestHeader(value = "supplierid") String supplierId);

	@GetMapping("/paymentSuppliers")
	public ResponseEntity<SupplierPaymentDto> paymentSuppliers(@RequestHeader(value = "branchid") String branchId,
			@RequestParam(value = "page") String page);

	@PostMapping("/deleteSuppliers")
	public ResponseEntity<ResultResponse> deleteSuppliers(@RequestBody MessIdsDto dto,
			@RequestHeader(value = "branchid") String branchId);

	@PostMapping("/updateSuppliers")
	public ResponseEntity<ResultResponse> updateSuppliers(@RequestBody MessIdsDto dto,
			@RequestHeader(value = "branchid") String branchId);

	@GetMapping("/viewSuppliers")
	public ResponseEntity<ResultResponse> viewSuppliers(@RequestHeader(value = "branchid") String branchId);

	@PostMapping("/addSuppliers")
	public ResponseEntity<SuppliersDetailsResponseDto> addSuppliers(@RequestBody SuppliersDetailsDto dto,
			@RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "userloginid") String userId);

}
