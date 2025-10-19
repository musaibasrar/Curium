package org.ideoholic.curium.model.user.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.user.dto.AdvanceSearchDto;
import org.ideoholic.curium.model.user.dto.DashBoardResponseDto;
import org.ideoholic.curium.model.user.dto.SearchByDateDto;
import org.ideoholic.curium.model.user.dto.SearchByDateResponseDto;
import org.ideoholic.curium.model.user.dto.SearchByParentDto;
import org.ideoholic.curium.model.user.dto.UserAuthenticationDto;
import org.ideoholic.curium.model.user.dto.UserAuthenticationResponseDto;
import org.ideoholic.curium.util.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/v1/userProcess")
public interface UserApiAction {

	@GetMapping("/sessionTimeOut")
	public ResponseEntity<String> sessionTimeOut();

	@PostMapping("/searchByDate")
	public ResponseEntity<SearchByDateResponseDto> searchByDate(@RequestBody SearchByDateDto dto, @RequestHeader(value = Constants.BRANCHID) String strBranchId, @RequestHeader(value = "dayOne") String dayOne, @RequestHeader(value = "dateFrom") String dateFrom, @RequestHeader(value = "dateTo") String dateTo);

	@PostMapping("/advanceSearchByParents")
	public ResponseEntity<ResultResponse> advanceSearchByParents(@RequestBody SearchByParentDto dto, @RequestHeader(value = Constants.BRANCHID) String branchId);

	@PostMapping("/backup")
	public ResponseEntity<ResultResponse> backup(@RequestParam(value = "filename") String fileName);

	@PostMapping("/advanceSearch")
	public ResponseEntity<ResultResponse> advanceSearch(@RequestBody AdvanceSearchDto dto, @RequestHeader(value = Constants.BRANCHID) String branchId);

	@PostMapping("/dashBoard")
	public ResponseEntity<DashBoardResponseDto> dashBoard(@RequestBody SearchByDateDto dto, @RequestHeader(value = Constants.BRANCHID) String branchId, @RequestHeader(value = Constants.CURRENTACADEMICYEAR) String currentAcademicYear);

	@PostMapping("/authenticateUser")
	public ResponseEntity<UserAuthenticationResponseDto> authenticateUser(@RequestBody UserAuthenticationDto dto);
	
	@GetMapping("/multiUser")
	public ResponseEntity<UserAuthenticationResponseDto> authenticateMultiUser(@RequestHeader(value = Constants.USERNAME) String strUserName, @RequestHeader(value = Constants.SUPER_USER_AUTH) String strSuperUserAuth, @RequestHeader(value = Constants.BRANCHID) String strBranchId);

	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity logOutUser();
	
	@PostMapping("/changePassword")
	public ResponseEntity<ResultResponse> changePassword(@RequestBody UserAuthenticationDto dto);

}