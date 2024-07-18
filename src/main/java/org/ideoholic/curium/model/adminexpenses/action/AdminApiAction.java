package org.ideoholic.curium.model.adminexpenses.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.exceptions.CustomErrorMessage;
import org.ideoholic.curium.exceptions.CustomResponseException;
import org.ideoholic.curium.model.adminexpenses.dto.*;
import org.ideoholic.curium.model.adminexpenses.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/adminProcess")
public class AdminApiAction {
    @Autowired
    private AdminService adminService;


    @PostMapping("/rejectVoucher")
    public ResponseEntity<ResultResponse> rejectVoucher(@RequestBody ExpensesIdDto dto, @RequestHeader(value = "branchid") String branchId) {
       adminService.rejectVoucher(dto);
        return viewAllExpenses(branchId);
    }

    @PostMapping("/approveVoucher")
    public ResponseEntity<ResultResponse> approveVoucher(@RequestBody ExpensesIdDto dto,@RequestHeader(value = "branchid") String branchId) {
        adminService.approveVoucher(dto);
        return viewAllExpenses(branchId);
    }

    @PostMapping("/printVoucher")
    public ResponseEntity<Adminexpenses> printVoucher(@RequestBody ExpensesIdDto dto, @RequestHeader(value = "branchid") String branchId) {
        Adminexpenses result = adminService.printVoucher(dto,branchId);
        return ResponseEntity.ok(result);
    }



    @PostMapping("/searchExpenses")
    public ResponseEntity<AdminExpenseResponseDto> searchExpensesbydate(@RequestBody AdminExpensesDto dto) {
        AdminExpenseResponseDto result = adminService.searchExpensesbydate(dto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/viewAllExpenses")
    public ResponseEntity<ResultResponse> viewAllExpenses(@RequestHeader(value = "branchid") String branchId) {
        ResultResponse result = adminService.viewAllExpenses(branchId);
        return ResponseEntity.ok(result);

    }


    @PostMapping("/addExpenses")
    public ResponseEntity<ResultResponse> addExpenses(@RequestBody AdminExpensesDto dto, @RequestHeader(value = "branchid") String branchId) {
          ResultResponse result = adminService.addExpenses(dto);
        if ( result.isSuccess()) {
            return viewAllExpenses(branchId);
        } else {
            throw new CustomResponseException(CustomErrorMessage.NOTSAVEDEXPENSES);
        }

    }

    @PostMapping("/deleteMultiple")
    public ResponseEntity<ResultResponse> deleteMultiple(ExpensesIdDto dto, @RequestHeader(value = "branchid") String branchId) {
        adminService.deleteMultiple(dto);
        return viewAllExpenses(branchId);
    }

    @PostMapping("/viewExpensesBetweenDates")
    public ResponseEntity<AdminExpenseResponseDto> viewExpensesBetweenDates(AdminExpensesDateDto dto) {
        AdminExpenseResponseDto result = adminService.viewExpensesBetweenDates(dto);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        } else {
            throw new CustomResponseException(CustomErrorMessage.ERROR);
        }
    }
}
