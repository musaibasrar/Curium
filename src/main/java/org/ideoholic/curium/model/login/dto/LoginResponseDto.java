package org.ideoholic.curium.model.login.dto;

import java.util.List;

import org.ideoholic.curium.model.branch.dto.Branch;
import org.ideoholic.curium.model.user.dto.Login;

import lombok.Data;

@Data
public class LoginResponseDto {
	
	private List<Login> loginList;
	private boolean success;
	private Login login;
	private List<Branch> branch;

}
