package org.ideoholic.curium.model.mess.supplier.action;

import java.io.IOException;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.exceptions.CustomErrorMessage;
import org.ideoholic.curium.exceptions.CustomResponseException;
import org.ideoholic.curium.model.account.dto.PrintSearchJournalEntriesDto;
import org.ideoholic.curium.model.account.dto.SearchJournalEntriesResponseDto;
import org.ideoholic.curium.model.account.dto.SearchLedgerEntriesDto;
import org.ideoholic.curium.model.account.dto.SearchLedgerEntriesResponseDto;
import org.ideoholic.curium.model.account.service.AccountService;
import org.ideoholic.curium.model.mess.item.dto.MessIdsDto;
import org.ideoholic.curium.model.mess.supplier.dto.ChequeDetailsDto;
import org.ideoholic.curium.model.mess.supplier.dto.ChequeDto;
import org.ideoholic.curium.model.mess.supplier.dto.PaymentDetailsResponseDto;
import org.ideoholic.curium.model.mess.supplier.dto.SupplierPaymentDto;
import org.ideoholic.curium.model.mess.supplier.dto.SuppliersDetailsDto;
import org.ideoholic.curium.model.mess.supplier.dto.SuppliersDetailsResponseDto;
import org.ideoholic.curium.model.mess.supplier.service.MessSuppliersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessSuppliersApiActionImpl implements MessSuppliersApiAction {

	@Autowired
	private MessSuppliersService messSuppliersService;

	@Autowired
	private AccountService accountService;

	public ResponseEntity<SearchJournalEntriesResponseDto> printSearchSupplierPaymentDetails(
			PrintSearchJournalEntriesDto dto, String branchId) {
		SearchJournalEntriesResponseDto result = accountService.printSearchJournalEntries(dto, branchId);
		return ResponseEntity.ok(result);

	}

	public ResponseEntity<ResultResponse> searchSupplierPaymentDetails(SearchLedgerEntriesDto dto, String branchId) {
		SearchLedgerEntriesResponseDto searchLedgerEntriesResponseDto = accountService.searchJournalEntries(dto,
				branchId);
		ResultResponse result = messSuppliersService.viewSuppliersDetails(branchId);
		searchLedgerEntriesResponseDto.setMessSuppliersList(result.getResultList());
		return ResponseEntity.ok(result);
	}

	public ResponseEntity<ResultResponse> supplierPaymentReport(String branchId) {
		ResultResponse result = messSuppliersService.viewSuppliersDetails(branchId);
		return ResponseEntity.ok(result);
	}

	public ResponseEntity<ResultResponse> printSuppliersBalance(String branchId) {
		ResultResponse result = messSuppliersService.viewBalanceSuppliers(branchId);
		return ResponseEntity.ok(result);
	}

	public ResponseEntity<ResultResponse> balanceSuppliers(String branchId) {
		ResultResponse result = messSuppliersService.viewBalanceSuppliers(branchId);
		return ResponseEntity.ok(result);
	}

	public ResponseEntity<SupplierPaymentDto> printSupplierPayment(String branchId, String page) {
		SupplierPaymentDto paymentDto = new SupplierPaymentDto();

		ResultResponse suppliersDetailsResult = messSuppliersService.viewSuppliersDetails(branchId);
		paymentDto.setMessSuppliersList(suppliersDetailsResult.getResultList());

		PaymentDetailsResponseDto suppliersPaymentResult = messSuppliersService.viewSuppliersPaymentDetails(page,
				branchId);
		paymentDto.setSupplierPaymentlist(suppliersPaymentResult.getSupplierPaymentList());
		paymentDto.setNoOfPages(suppliersPaymentResult.getNoOfPages());
		paymentDto.setPage(suppliersPaymentResult.getPage());

		return ResponseEntity.ok(paymentDto);
	}

	@PostMapping("/cancelCheque")
	public ResponseEntity<SupplierPaymentDto> cancelCheque(ChequeDetailsDto dto, String branchId, String userId,
			String page) {
		SupplierPaymentDto paymentDto = new SupplierPaymentDto();

		ResultResponse cancelChequeResult = messSuppliersService.cancelCheque(dto, branchId, userId);
		paymentDto.setResult(cancelChequeResult.isSuccess());

		ResultResponse suppliersDetailsResult = messSuppliersService.viewSuppliersDetails(branchId);
		paymentDto.setMessSuppliersList(suppliersDetailsResult.getResultList());

		PaymentDetailsResponseDto suppliersPaymentResult = messSuppliersService.viewSuppliersPaymentDetails(page,
				branchId);
		paymentDto.setSupplierPaymentlist(suppliersPaymentResult.getSupplierPaymentList());
		paymentDto.setNoOfPages(suppliersPaymentResult.getNoOfPages());
		paymentDto.setPage(suppliersPaymentResult.getPage());

		return ResponseEntity.ok(paymentDto);

	}

	public ResponseEntity<SupplierPaymentDto> clearedCheque(ChequeDetailsDto dto, String branchId, String userId,
			String page) {
		SupplierPaymentDto paymentDto = new SupplierPaymentDto();

		ResultResponse cancelChequeResult = messSuppliersService.clearedCheque(dto, branchId, userId);
		paymentDto.setResult(cancelChequeResult.isSuccess());

		ResultResponse suppliersDetailsResult = messSuppliersService.viewSuppliersDetails(branchId);
		paymentDto.setMessSuppliersList(suppliersDetailsResult.getResultList());

		PaymentDetailsResponseDto suppliersPaymentResult = messSuppliersService.viewSuppliersPaymentDetails(page,
				branchId);
		paymentDto.setSupplierPaymentlist(suppliersPaymentResult.getSupplierPaymentList());
		paymentDto.setNoOfPages(suppliersPaymentResult.getNoOfPages());
		paymentDto.setPage(suppliersPaymentResult.getPage());

		return ResponseEntity.ok(paymentDto);

	}

	public ResponseEntity<SupplierPaymentDto> deliveredCheque(ChequeDetailsDto dto, String branchId, String page) {
		SupplierPaymentDto paymentDto = new SupplierPaymentDto();

		ResultResponse cancelChequeResult = messSuppliersService.deliveredCheque(dto, branchId);
		paymentDto.setResult(cancelChequeResult.isSuccess());

		ResultResponse suppliersDetailsResult = messSuppliersService.viewSuppliersDetails(branchId);
		paymentDto.setMessSuppliersList(suppliersDetailsResult.getResultList());

		PaymentDetailsResponseDto suppliersPaymentResult = messSuppliersService.viewSuppliersPaymentDetails(page,
				branchId);
		paymentDto.setSupplierPaymentlist(suppliersPaymentResult.getSupplierPaymentList());
		paymentDto.setNoOfPages(suppliersPaymentResult.getNoOfPages());
		paymentDto.setPage(suppliersPaymentResult.getPage());

		return ResponseEntity.ok(paymentDto);
	}

	public ResponseEntity<SupplierPaymentDto> issueCheque(ChequeDto dto, String branchId, String userId, String page) {
		SupplierPaymentDto paymentDto = new SupplierPaymentDto();

		ResultResponse cancelChequeResult = messSuppliersService.issueCheque(dto, branchId, userId);
		paymentDto.setResult(cancelChequeResult.isSuccess());

		ResultResponse suppliersDetailsResult = messSuppliersService.viewSuppliersDetails(branchId);
		paymentDto.setMessSuppliersList(suppliersDetailsResult.getResultList());

		PaymentDetailsResponseDto suppliersPaymentResult = messSuppliersService.viewSuppliersPaymentDetails(page,
				branchId);
		paymentDto.setSupplierPaymentlist(suppliersPaymentResult.getSupplierPaymentList());
		paymentDto.setNoOfPages(suppliersPaymentResult.getNoOfPages());
		paymentDto.setPage(suppliersPaymentResult.getPage());

		return ResponseEntity.ok(paymentDto);
	}

	public ResponseEntity getSupplierBalance(String supplierId) {
		try {
			messSuppliersService.getSupplierBalance(supplierId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok().build();
	}

	public ResponseEntity<SupplierPaymentDto> paymentSuppliers(String branchId, String page) {
		SupplierPaymentDto paymentDto = new SupplierPaymentDto();

		ResultResponse suppliersDetailsResult = messSuppliersService.viewSuppliersDetails(branchId);
		paymentDto.setMessSuppliersList(suppliersDetailsResult.getResultList());

		PaymentDetailsResponseDto suppliersPaymentResult = messSuppliersService.viewSuppliersPaymentDetails(page,
				branchId);
		paymentDto.setSupplierPaymentlist(suppliersPaymentResult.getSupplierPaymentList());
		paymentDto.setNoOfPages(suppliersPaymentResult.getNoOfPages());
		paymentDto.setPage(suppliersPaymentResult.getPage());

		return ResponseEntity.ok(paymentDto);
	}

	public ResponseEntity<ResultResponse> deleteSuppliers(MessIdsDto dto, String branchId) {
		messSuppliersService.deleteMultipleSuppliers(dto);
		return viewSuppliers(branchId);
	}

	public ResponseEntity<ResultResponse> updateSuppliers(MessIdsDto dto, String branchId) {
		messSuppliersService.updateSuppliers(dto);
		return viewSuppliers(branchId);
	}

	public ResponseEntity<ResultResponse> viewSuppliers(String branchId) {
		ResultResponse result = messSuppliersService.viewSuppliersDetails(branchId);
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		} else {
			throw new CustomResponseException(CustomErrorMessage.ERROR);
		}
	}

	public ResponseEntity<SuppliersDetailsResponseDto> addSuppliers(SuppliersDetailsDto dto, String branchId,
			String userId) {

		SuppliersDetailsResponseDto responseDto = messSuppliersService.addSupplierDetails(dto, branchId, userId);
		ResultResponse result = viewSuppliers(branchId).getBody();
		responseDto.setMessSuppliersList(result.getResultList());

		return ResponseEntity.ok(responseDto);
	}

}
