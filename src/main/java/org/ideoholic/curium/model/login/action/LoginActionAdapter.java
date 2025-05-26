package org.ideoholic.curium.model.login.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.login.dto.LoginDto;
import org.ideoholic.curium.model.login.dto.LoginResponseDto;
import org.ideoholic.curium.model.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginActionAdapter {
	
	@Autowired
    private HttpServletRequest request;
    
	@Autowired
	private HttpServletResponse response;

    @Autowired
    private HttpSession httpSession;

    private String BRANCHID = "branchid";
    
    private String USERID = "userid";
	
	@Autowired
	private LoginService loginService;

	public boolean viewLogin() {

		LoginResponseDto result = loginService.viewLoginDetails(httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("logindetail", result.getLoginList());
		return result.isSuccess();

		
	}

	public void deleteRecord() {
		LoginDto loginDto = new LoginDto();
		loginDto.setIdLogin(request.getParameterValues("id"));
        loginService.deleteRecord(loginDto);
		
	}

	public boolean viewLoginDetail() {
		
		LoginResponseDto result = loginService.viewLoginDetail(request.getParameter("id"), httpSession.getAttribute(BRANCHID).toString());
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
		LoginResponseDto result = loginService.addLoginStaffDetail(loginDto);
		return result.isSuccess();
	}

	public void readListOfBranchId() {
		
		LoginResponseDto result = loginService.readListOfBranchId();
		request.setAttribute("branchdetail", result.getBranch());
		
	}

}
