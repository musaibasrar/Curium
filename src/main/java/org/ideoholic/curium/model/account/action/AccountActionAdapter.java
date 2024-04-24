package org.ideoholic.curium.model.account.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ideoholic.curium.model.account.dto.*;
import org.ideoholic.curium.model.account.service.AccountService;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountActionAdapter {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    public boolean saveAccount() {
	AccountService accountService = new AccountService(request, response);

	AccountDto accountDto = new AccountDto();
	accountDto.setNewSubGroup(request.getParameter("newsubgroup"));
	accountDto.setNewSSGroup(request.getParameter("newssgroup"));
	accountDto.setSubGroupName(request.getParameter("subgroupname"));
	accountDto.setSsGroupName(request.getParameter("ssgroupname"));
	accountDto.setGroupName(request.getParameter("groupname"));
	accountDto.setAccountName(request.getParameter("accountname"));
	accountDto.setAccountCode(request.getParameter("accountcode"));

	ResultResponse response = accountService.saveAccount(accountDto);
	if (response == null) {
	    return false;
	}

	request.setAttribute("createaccountalert", response.getMessage());

	return response.isSuccess();
    }

	public boolean deleteAccount(){
		AccountService accountService = new AccountService(request, response);

		AccountDeleteDto accountDeleteDto = new AccountDeleteDto();
		accountDeleteDto.setAccountIds(request.getParameterValues("accountids"));

		return accountService.deleteAccount(accountDeleteDto);
	}

	public boolean saveReceipt(){
		AccountService accountService = new AccountService(request, response);

		AccountReceiptDto accountReceiptDto = new AccountReceiptDto();
		accountReceiptDto.setDraccountName(request.getParameter("accountname"));
		accountReceiptDto.setCraccountName(request.getParameter("accountnamesecond"));
		accountReceiptDto.setReceiptVoucher(request.getParameter("receiptvoucher"));
		accountReceiptDto.setDrAmount(request.getParameter("dramount"));
		accountReceiptDto.setCrAmount(request.getParameter("cramountsecond"));
		accountReceiptDto.setReceiptDate(request.getParameter("dateofreceipt"));
		accountReceiptDto.setReceiptNarration(request.getParameter("receiptnarration"));

		return accountService.saveReceipt(accountReceiptDto);
	}

	public boolean cancelVoucher(){
		AccountService accountService = new AccountService(request, response);

		CancelVoucherDto cancelVoucherDto = new CancelVoucherDto();
		cancelVoucherDto.setReceiptIds(request.getParameterValues("transactionids"));
		cancelVoucherDto.setVoucher(request.getParameter("voucher"));

		return accountService.cancelVoucher(cancelVoucherDto);
	}

	public boolean saveJournal(){
		AccountService accountService = new AccountService(request, response);

		AccountJournalDto accountJournalDto = new AccountJournalDto();
		accountJournalDto.setDraccountNameJournal(request.getParameter("accountnamejournal"));
		accountJournalDto.setCraccountNameJournal(request.getParameter("accountnamejournalsecond"));
		accountJournalDto.setJournalVoucher(request.getParameter("journalvoucher"));
		accountJournalDto.setDrAmountJournal(request.getParameter("dramountjournal"));
		accountJournalDto.setCrAmountJournal(request.getParameter("cramountjournalsecond"));
		accountJournalDto.setJournalDate(request.getParameter("dateofjournal"));
		accountJournalDto.setJournalNarration(request.getParameter("journalnarration"));

		return accountService.saveJournal(accountJournalDto);
	}

	public boolean saveContra(){
		AccountService accountService = new AccountService(request, response);

		AccountContraDto accountContraDto = new AccountContraDto();
		accountContraDto.setDraccountName(request.getParameter("accountnamecontra"));
		accountContraDto.setCraccountName(request.getParameter("accountnamecontrasecond"));
		accountContraDto.setContraVoucher(request.getParameter("contravoucher"));
		accountContraDto.setDrAmountContra(request.getParameter("dramountcontra"));
		accountContraDto.setCrAmountContra(request.getParameter("cramountcontrasecond"));
		accountContraDto.setContraDate(request.getParameter("dateofcontra"));
		accountContraDto.setContraNarration(request.getParameter("contranarration"));

		return accountService.saveContra(accountContraDto);
	}

	public boolean savePayment(){
		AccountService accountService = new AccountService(request, response);

		AccountPaymentDto accountPaymentDto = new AccountPaymentDto();
		accountPaymentDto.setDraccountName(request.getParameter("accountnamepayment"));
		accountPaymentDto.setCraccountName(request.getParameter("accountnamepaymentsecond"));
		accountPaymentDto.setPaymentVoucher(request.getParameter("paymentavoucher"));
		accountPaymentDto.setDrAmountPayment(request.getParameter("dramountpayment"));
		accountPaymentDto.setCrAmountPayment(request.getParameter("cramountpaymentsecond"));
		accountPaymentDto.setPaymentDate(request.getParameter("dateofpayment"));
		accountPaymentDto.setPaymentNarration(request.getParameter("paymentnarration"));

		return accountService.savePayment(accountPaymentDto);

	}
}
