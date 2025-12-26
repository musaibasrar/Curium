package org.ideoholic.curium.model.login.service;

import java.util.ArrayList;
import java.util.List;

import org.ideoholic.curium.model.branch.dto.Branch;
import org.ideoholic.curium.model.login.dao.LoginDao;
import org.ideoholic.curium.model.login.dto.LoginDto;
import org.ideoholic.curium.model.login.dto.LoginResponseDto;
import org.ideoholic.curium.model.user.dto.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
	
	@Autowired
	private LoginDao loginDao;

	public LoginResponseDto viewLoginDetails(String branchId) {
		LoginResponseDto loginResponseDto = new LoginResponseDto();

		if (branchId != null) {
			try {
				List<Login> list = loginDao.readListOfLoginDetail(branchId);
				loginResponseDto.setLoginList(list);
				loginResponseDto.setSuccess(true);
			} catch (Exception e) {
				e.printStackTrace();
				loginResponseDto.setSuccess(false);
			}
		}
		return loginResponseDto;
	}

	public void deleteRecord(LoginDto loginDto) {
		String[] idsLogin = loginDto.getIdLogin();
		if (idsLogin != null) {
			List<Integer> ids = new ArrayList<>();
			for (String id : idsLogin) {
				ids.add(Integer.valueOf(id));
			}
			loginDao.deleteRecord(ids);
		}
		
	}

	

	public LoginResponseDto viewLoginDetail(String lgId, String branchId) {
		LoginResponseDto loginResponseDto = new LoginResponseDto();
		int lid = Integer.parseInt(lgId);
		if (branchId != null) {
			try {
				Login login = loginDao.readDetailsOfLogin(lid);
				loginResponseDto.setLogin(login);
				loginResponseDto.setSuccess(true);
			} catch (Exception e) {
				e.printStackTrace();
				loginResponseDto.setSuccess(false);
			}
		}
		return loginResponseDto;
	}

	public LoginResponseDto updateDetailsOfLogin(LoginDto loginDto) {
		LoginResponseDto loginResponseDto = new LoginResponseDto();
		Login login = new Login();
		login.setLid(Integer.parseInt(loginDto.getLid()));
		login.setUsername(loginDto.getUserName());
		login.setPassword(loginDto.getPassWord());
		login.setUsertype(loginDto.getUserType());
		boolean result = loginDao.updateDetailsOfLogin(login);
		loginResponseDto.setSuccess(result);
		return loginResponseDto;
	}

	public LoginResponseDto readListOfBranchId() {
		LoginResponseDto loginResponseDto = new LoginResponseDto();
		List<Branch> branch =loginDao.readListOfBranchId();
		loginResponseDto.setBranch(branch);
		return loginResponseDto;
	}

	public LoginResponseDto addLoginStaffDetail(LoginDto loginDto) {
		LoginResponseDto loginResponseDto = new LoginResponseDto();
		Branch branch = new Branch();
		Login login = new Login();
		branch.setIdbranch(Integer.parseInt(loginDto.getBranchid()));
		login.setBranch(branch);
		login.setUsername(loginDto.getUserName());
		login.setPassword(loginDto.getPassWord());
		login.setUsertype(loginDto.getUserType());
		boolean result = loginDao.saveLoginDetail(login);
		loginResponseDto.setSuccess(result);
		return loginResponseDto;
	}

}
