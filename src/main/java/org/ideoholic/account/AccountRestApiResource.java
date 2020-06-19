package org.ideoholic.account;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ideoholic.account.dto.AccountProfileDto;
import org.ideoholic.account.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("AccountProcess")
@Component
@Scope("singleton")

public class AccountRestApiResource {

	private final static Logger logger = LoggerFactory.getLogger(AccountRestApiResource.class);
	
	@Inject
	private AccountService accountService;
	
	@POST
	@Path("saveFinancialYear")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response saveFinancialYear(final AccountProfileDto accountDto){
		String output = "";
		output =accountService.saveFinancialYear(accountDto.getBranchId(),accountDto.getFromDate(),accountDto.getToDate(),
				accountDto.getActive());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("createAccount")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response createAccount(final AccountProfileDto accountDto){
		String output = "";
		output =accountService.createAccount(accountDto.getBranchId());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("getCurrentFinancialYear")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getCurrentFinancialYear(final AccountProfileDto accountDto){
		String output = "";
		output =accountService.getCurrentFinancialYear(accountDto.getBranchId());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("getSubGroupNames")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getSubGroupNames(final AccountProfileDto accountDto)throws IOException{
		String output = "";
		output =accountService.getSubGroupNames(accountDto.getBranchId(),accountDto.getAccountGroupMasterId());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("saveAccount")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response saveAccount(final AccountProfileDto accountDto){
		String output = "";
		output =accountService.saveAccount(accountDto.getBranchId(),accountDto.getNewSubGroup(),accountDto.getNewSSGroup(),
				accountDto.getSubGroupName(),accountDto.getSsGroupName(),accountDto.getGroupNam(),accountDto.getAccountName(),
				accountDto.getAccountCode());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("deleteAccount")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteAccount(final AccountProfileDto accountDto)throws IOException{
		String output = "";
		output =accountService.deleteAccount(accountDto.getAccountIds());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("createVoucher")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response createVoucher(final AccountProfileDto accountDto){
		String output = "";
		output =accountService.createVoucher(accountDto.getBranchId());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("saveReceipt")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response saveReceipt(final AccountProfileDto accountDto){
		String output = "";
		output =accountService.saveReceipt(accountDto.getBranchId(),accountDto.getDraccountName(),accountDto.getCraccountName(),
				accountDto.getReceiptVoucher(),accountDto.getDrAmount(),accountDto.getCrAmount(),accountDto.getReceiptDate(),
				accountDto.getReceiptNarration());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("savePayment")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response savePayment(final AccountProfileDto accountDto){
		String output = "";
		output =accountService.savePayment(accountDto.getBranchId(),accountDto.getDraccountNamePayment(),accountDto.getCraccountNamePayment(),
				accountDto.getPaymentVoucher(),accountDto.getDrAmountPayment(),accountDto.getCrAmountPayment(),accountDto.getPaymentDate(),
				accountDto.getPaymentNarration());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("saveContra")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response saveContra(final AccountProfileDto accountDto){
		String output = "";
		output =accountService.saveContra(accountDto.getBranchId(),accountDto.getDraccountNameContra(),accountDto.getCraccountNameContra(),
				accountDto.getContraVoucher(),accountDto.getDrAmountContra(),accountDto.getCrAmountContra(),accountDto.getContraDate(),
				accountDto.getContraNarration());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("saveJournal")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response saveJournal(final AccountProfileDto accountDto){
		String output = "";
		output =accountService.saveJournal(accountDto.getBranchId(),accountDto.getDraccountNameJournal(),accountDto.getCraccountNameJournal(),
				accountDto.getContraVoucher(),accountDto.getDrAmountJournal(),accountDto.getCrAmountJournal(),accountDto.getJournalDate(),
				accountDto.getJournalNarration());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("balanceSheet")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response balanceSheet(final AccountProfileDto accountDto){
		String output = "";
		output =accountService.balanceSheet(accountDto.getBranchId());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("trialBalance")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response trialBalance(final AccountProfileDto accountDto){
		String output = "";
		output =accountService.trialBalance(accountDto.getBranchId(),accountDto.getFromDate(),accountDto.getToDate());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("cancelVoucher")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response cancelVoucher(final AccountProfileDto accountDto){
		String output = "";
		output =accountService.cancelVoucher(accountDto.getReceiptIds(),accountDto.getVoucherType());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("viewCancelledVouchers")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response viewCancelledVouchers(final AccountProfileDto accountDto){
		String output = "";
		output =accountService.viewCancelledVouchers(accountDto.getBranchId());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("getSSGroupNames")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getSSGroupNames(final AccountProfileDto accountDto)throws IOException{
		String output = "";
		output =accountService.getSSGroupNames(accountDto.getBranchId(),accountDto.getSubGroupName());
		return Response.status(200).entity(output).build();

	}
}
