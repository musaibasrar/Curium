package org.ideoholic.curium.model.user.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.exceptions.CustomErrorMessage;
import org.ideoholic.curium.exceptions.CustomResponseException;
import org.ideoholic.curium.model.user.dto.AdvanceSearchDto;
import org.ideoholic.curium.model.user.dto.DashBoardResponseDto;
import org.ideoholic.curium.model.user.dto.SearchByDateDto;
import org.ideoholic.curium.model.user.dto.SearchByDateResponseDto;
import org.ideoholic.curium.model.user.dto.SearchByParentDto;
import org.ideoholic.curium.model.user.dto.UserAuthenticationDto;
import org.ideoholic.curium.model.user.dto.UserAuthenticationResponseDto;
import org.ideoholic.curium.model.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiActionImpl implements UserApiAction {

    @Autowired
    private UserService userService;

    public ResponseEntity<String> sessionTimeOut() {
        return ResponseEntity.ok("sessiontimeout");
    }

    public ResponseEntity<SearchByDateResponseDto> searchByDate(SearchByDateDto dto, String strBranchId, String dayOne, String dateFrom, String dateTo) {
        SearchByDateResponseDto result = userService.searchByDate(dto, strBranchId, dayOne, dateFrom, dateTo);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<ResultResponse> advanceSearchByParents(SearchByParentDto dto, String branchId) {
        ResultResponse result = userService.advanceSearchByParents(dto, branchId);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<ResultResponse> backup(String fileName) {
        ResultResponse result = userService.backupData(fileName);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        } else {
            throw new CustomResponseException(CustomErrorMessage.BACKUPFAILED);
        }
    }

    public ResponseEntity<ResultResponse> advanceSearch(AdvanceSearchDto dto, String branchId) {
        ResultResponse result = userService.advanceSearch(dto, branchId);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<DashBoardResponseDto> dashBoard(SearchByDateDto dto, String branchId, String currentAcademicYear) {
        DashBoardResponseDto result = userService.dashBoard(dto, branchId, currentAcademicYear);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<UserAuthenticationResponseDto> authenticateUser(UserAuthenticationDto dto) {
        UserAuthenticationResponseDto result = userService.authenticateUser(dto);
        if (!result.isSuccess()) {
            throw new CustomResponseException(CustomErrorMessage.INVALID_CREDENTIALS);
        }
        return ResponseEntity.ok(result);

    }

    public ResponseEntity<UserAuthenticationResponseDto> authenticateMultiUser(String strUserName, String strSuperUserAuth, String strBranchId) {
        UserAuthenticationResponseDto result = userService.authenticateMultiUser(strUserName, strSuperUserAuth, strBranchId);
        if (!result.isSuccess()) {
            throw new CustomResponseException(CustomErrorMessage.INVALID_CREDENTIALS);
        }
        return ResponseEntity.ok(result);
    }

    public ResponseEntity logOutUser() {
        userService.logOutUser();
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<ResultResponse> changePassword(UserAuthenticationDto dto) {
        ResultResponse result = userService.ChangePassword(dto);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        } else {
            throw new CustomResponseException(CustomErrorMessage.INVALID_CREDENTIALS);
        }
    }
}