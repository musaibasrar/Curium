package org.ideoholic.curium.model.account.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.account.dto.AccountDto;
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

}
