package org.ideoholic.curium.model.login.service;

import java.util.ArrayList;
import java.util.List;

import org.ideoholic.curium.model.diary.dao.diaryDAO;
import org.ideoholic.curium.model.library.dao.LibraryDAO;
import org.ideoholic.curium.model.library.dto.Book;
import org.ideoholic.curium.model.library.dto.BooksResponseDto;
import org.ideoholic.curium.model.login.dao.LoginDao;
import org.ideoholic.curium.model.login.dto.LoginDto;
import org.ideoholic.curium.model.login.dto.LoginResponseDto;
import org.springframework.stereotype.Service;
import org.ideoholic.curium.model.user.dto.Login;
import org.ideoholic.curium.model.branch.dto.Branch;

@Service
public class LoginService {

	public LoginResponseDto viewLoginDetails(String branchId) {
		LoginResponseDto loginResponseDto = new LoginResponseDto();

		if (branchId != null) {
			try {
				List<Login> list = new LoginDao().readListOfLoginDetail(branchId);
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
			List<Integer> ids = new ArrayList();
			for (String id : idsLogin) {
				ids.add(Integer.valueOf(id));
			}
			new LoginDao().deleteRecord(ids);
		}
		
	}

	

	public LoginResponseDto viewLoginDetail(String lgId, String branchId) {
		LoginResponseDto loginResponseDto = new LoginResponseDto();
		int lid = Integer.parseInt(lgId);
		if (branchId != null) {
			try {
				Login login = new LoginDao().readDetailsOfLogin(lid);
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
		boolean result = new LoginDao().updateDetailsOfLogin(login);
		loginResponseDto.setSuccess(result);
		return loginResponseDto;
	}

	public LoginResponseDto readListOfBranchId() {
		LoginResponseDto loginResponseDto = new LoginResponseDto();
		List<Branch> branch = new LoginDao().readListOfBranchId();
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
		boolean result = new LoginDao().saveLoginDetail(login);
		loginResponseDto.setSuccess(result);
		return loginResponseDto;
	}

}
