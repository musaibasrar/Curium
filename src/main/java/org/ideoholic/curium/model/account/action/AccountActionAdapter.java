package org.ideoholic.curium.model.account.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ideoholic.curium.model.account.dto.AccountDeleteDto;
import org.ideoholic.curium.model.account.dto.AccountDto;
import org.ideoholic.curium.model.account.dto.AccountReceiptDto;
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
	accountDto.setNewSubGroup(DataUtil.emptyString(request.getParameter("newsubgroup")));
	accountDto.setNewSSGroup(DataUtil.emptyString(request.getParameter("newssgroup")));
	accountDto.setSubGroupName(DataUtil.emptyString(request.getParameter("subgroupname")));
	accountDto.setSsGroupName(DataUtil.emptyString(request.getParameter("ssgroupname")));
	accountDto.setGroupName(DataUtil.emptyString(request.getParameter("groupname")));
	accountDto.setAccountName(DataUtil.emptyString(request.getParameter("accountname")));
	accountDto.setAccountCode(DataUtil.emptyString(request.getParameter("accountcode")));

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
		AccountService accountService  = new AccountService(request, response);

		AccountReceiptDto accountReceiptDto = new AccountReceiptDto();
		accountReceiptDto.setDraccountName(request.getParameter("accountname"));
		accountReceiptDto.setCraccountName(request.getParameter("accountnamesecond"));
		accountReceiptDto.setReceiptVoucher(request.getParameter("receiptvoucher"));
		accountReceiptDto.setDrAmount(request.getParameter("dramount"));
		accountReceiptDto.setCrAmount(request.getParameter("cramountsecond"));
		accountReceiptDto.setCrAmount(request.getParameter("dateofreceipt"));
		accountReceiptDto.setReceiptNarration(request.getParameter("receiptnarration"));

		return accountService.saveReceipt(accountReceiptDto);
	}

}
