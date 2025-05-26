package org.ideoholic.curium.model.login.action;

import org.ideoholic.curium.model.login.dto.LoginDto;
import org.ideoholic.curium.model.login.dto.LoginResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/v1/loginProcess")
public interface LoginApiAction {
	    
	    @RequestMapping(value = "/viewLoginDetail", method = { RequestMethod.GET, RequestMethod.POST })
	    public ResponseEntity<LoginResponseDto> viewLoginDetail(@RequestHeader(value = "branchid") String branchid);
	  
	    @PostMapping("/deleteRecord")
	    public ResponseEntity<LoginResponseDto> deleteRecord(@RequestBody LoginDto loginDto,@RequestHeader(value = "branchid") String branchid );
	 
	    @GetMapping("/logindetail")
	    public ResponseEntity<LoginResponseDto> logindetail(@RequestParam(value = "lid") String lgId,@RequestHeader(value = "branchid") String branchid);
	 
	    @PostMapping("/updateLoginDetails")
	    public ResponseEntity<LoginResponseDto> updateLoginDetails(@RequestParam(value = "lid") String lgId,@RequestHeader(value = "branchid") String branchid);

	    @PostMapping("/updateDetailsOfLogin")
	    public ResponseEntity<LoginResponseDto> updateDetailsOfLogin(@RequestParam(value = "lid") String lgId,@RequestBody LoginDto loginDto,@RequestHeader(value = "branchid") String branchid);
   		
	    @GetMapping("/addLoginStaff")
	    public ResponseEntity<LoginResponseDto> addLoginStaff();
	 
	    @PostMapping("/addLoginStaffDetail")
	    public ResponseEntity<LoginResponseDto> addLoginStaffDetail(@RequestBody LoginDto loginDto);

}
