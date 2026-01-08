package org.ideoholic.curium.model.login.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.login.dto.LoginDto;
import org.ideoholic.curium.model.login.dto.LoginResponseDto;
import org.ideoholic.curium.model.login.service.LoginService;
import org.ideoholic.curium.util.Constants;
import org.ideoholic.curium.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginActionAdapter {
	
	@Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpSession httpSession;
	
	@Autowired
	private LoginService loginService;

	public boolean viewLogin() {

		LoginResponseDto result = loginService.viewLoginDetails(DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.BRANCHID));
        request.setAttribute("logindetail", result.getLoginList());
		return result.isSuccess();

		
	}

	public void deleteRecord() {
		LoginDto loginDto = new LoginDto();
		loginDto.setIdLogin(request.getParameterValues("id"));
        loginService.deleteRecord(loginDto);
		
	}

	public boolean viewLoginDetail() {
		
		LoginResponseDto result = loginService.viewLoginDetail(request.getParameter("id"), DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.BRANCHID));
		request.setAttribute("logindetail", result.getLogin());
		return result.isSuccess();
		
	}

	public boolean updateDetailsOfLogin() {
		
		LoginDto loginDto = new LoginDto();
		loginDto.setLid(request.getParameter("id"));
		loginDto.setUserName(request.getParameter("username"));
		loginDto.setPassWord(request.getParameter("password"));
		loginDto.setUserType(request.getParameter("usertype"));
		LoginResponseDto result = loginService.updateDetailsOfLogin(loginDto);
		return result.isSuccess();
	}

	public boolean addLoginStaffDetail() {
		LoginDto loginDto = new LoginDto();
		loginDto.setBranchid(request.getParameter("branchid"));
		loginDto.setUserName(request.getParameter("username"));
		loginDto.setPassWord(request.getParameter("password"));
		loginDto.setUserType(request.getParameter("usertype"));
		loginDto.setBranchid(DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.BRANCHID));
		LoginResponseDto result = loginService.addLoginStaffDetail(loginDto);
		return result.isSuccess();
	}

	public void readListOfBranchId() {
		
		LoginResponseDto result = loginService.readListOfBranchId();
		request.setAttribute("branchdetail", result.getBranch());
		
	}

}
