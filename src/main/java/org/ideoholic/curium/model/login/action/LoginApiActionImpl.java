package org.ideoholic.curium.model.login.action;

import org.ideoholic.curium.exceptions.CustomErrorMessage;
import org.ideoholic.curium.exceptions.CustomResponseException;
import org.ideoholic.curium.model.login.dto.LoginDto;
import org.ideoholic.curium.model.login.dto.LoginResponseDto;
import org.ideoholic.curium.model.login.service.LoginService;
import org.ideoholic.curium.model.student.dto.StudentIdsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginApiActionImpl implements LoginApiAction {
	
	@Autowired
	private LoginService loginService;
	
	    public ResponseEntity<LoginResponseDto> viewLoginDetail( String branchid) {
		 LoginResponseDto result = loginService.viewLoginDetails(branchid);
	        return ResponseEntity.ok(result);

	    }
	 
	
	    public ResponseEntity<LoginResponseDto> deleteRecord( LoginDto loginDto, String branchid ) {
		 loginService.deleteRecord(loginDto);
		 LoginResponseDto result = loginService.viewLoginDetails(branchid);
	        return ResponseEntity.ok(result);
	    }
	 
	 
	    public ResponseEntity<LoginResponseDto> logindetail( @RequestParam(value = "lid") String lgId, String branchid) {
		 LoginResponseDto result = loginService.viewLoginDetail(lgId,branchid);
	        return ResponseEntity.ok(result);
	    }
	 
	 
	    public ResponseEntity<LoginResponseDto> updateLoginDetails(@RequestParam(value = "lid") String lgId, String branchid) {
		 LoginResponseDto result = loginService.viewLoginDetail(lgId,branchid);
	        return ResponseEntity.ok(result);
	    }

	    public ResponseEntity<LoginResponseDto> updateDetailsOfLogin(@RequestParam(value = "lid") String lgId, LoginDto loginDto, String branchid) {
   		 if(loginService.updateDetailsOfLogin(loginDto).isSuccess())
   		 {
   			LoginResponseDto result = loginService.viewLoginDetail(lgId,branchid);
	        return ResponseEntity.ok(result);
   		 }
   		throw new CustomResponseException(CustomErrorMessage.ERROR);
	    }

	
	    public ResponseEntity<LoginResponseDto> addLoginStaff() {
		 LoginResponseDto result = loginService.readListOfBranchId();
	        return ResponseEntity.ok(result);
	    }
	 
	 
	    public ResponseEntity<LoginResponseDto> addLoginStaffDetail(@RequestBody LoginDto loginDto) {
		 LoginResponseDto result = loginService.addLoginStaffDetail(loginDto);
		 if(result.isSuccess())
		 {
	        return ResponseEntity.ok(result);
		 }
		 throw new CustomResponseException(CustomErrorMessage.ERROR);
	    }

}
