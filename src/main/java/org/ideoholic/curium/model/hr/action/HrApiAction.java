package org.ideoholic.curium.model.hr.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.employee.dto.*;
import org.ideoholic.curium.model.hr.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/hrProcess")
public interface HrApiAction {


    @PostMapping("/updateBasicPay")
    ResponseEntity<ResultResponse> updateBasicPay( @RequestBody BasicPayDto dto, @RequestHeader(value = "branchid") String branchId);

    @GetMapping("/viewEditbasicPay")
    ResponseEntity<BasicPayEmployeesDto> viewEditbasicPay( @RequestHeader(value = "branchid") String branchId);

    @PostMapping("/cancelStaffSalary")
    ResponseEntity<ResultResponse> cancelStaffSalary(@RequestBody SalaryDto dto, @RequestHeader (value = "currentAcademicYear") String currentAcademicYear,@RequestHeader(value = "branchid") String branchId);

    @PostMapping("/issueProcessedSalary")
    ResponseEntity<ResultResponse> issueProcessedSalary(@RequestBody SalaryDto dto, @RequestHeader (value = "currentAcademicYear") String currentAcademicYear,@RequestHeader(value = "branchid")  String branchId);

    @PostMapping("/deletePayHeadStaff")
    ResponseEntity<StaffDetailsResponseDto> deletePayHeadStaff(@RequestBody SalaryDto dto,@RequestHeader (value = "currentAcademicYear") String currentAcademicYear );

    @PostMapping("/getStaffDetails")
    ResponseEntity<StaffDetailsResponseDto> getStaffDetails(@RequestBody StaffDetailsDto dto,@RequestHeader (value = "currentAcademicYear") String currentAcademicYear);

    @RequestMapping(value= "/deletePayHead", method= { RequestMethod.GET, RequestMethod.POST })
    ResponseEntity<EmployeesWithSalaryResponseDto> deletePayHead(@RequestHeader(value = "branchid") String branchId);

    @GetMapping("/printSalarySlip")
    ResponseEntity<SalarySlipResponseDto> printSalarySlip(@RequestHeader(value = "processSalaryId") String processSalaryId);

    @GetMapping("/issueStaffSalary")
    ResponseEntity<SalaryResponseDto> issueStaffSalary(@RequestHeader (value="currentAcademicYear")String currentAcademicYear,@RequestHeader(value = "branchid")  String branchId);

    @GetMapping("/getPayHead")
    ResponseEntity<PayHeadResponseDto> getPayHead(@RequestParam(value = "payHeadType") String payHeadType,@RequestHeader (value = "currentAcademicYear") String currentAcademicYear,@RequestHeader(value = "branchid") String branchId);

    @PostMapping("/processStaffSalary")
    ResponseEntity<ResultResponse> processStaffSalary(@RequestBody SalaryDto dto,@RequestHeader (value = "currentAcademicYear") String currentAcademicYear,@RequestHeader(value = "branchid") String branchId,@RequestHeader (value="userId")String userId);

    @PostMapping("/searchEmployeesForProcessSalary")
    ResponseEntity<HrDataResponseDto> searchEmployeesForProcessSalary(@RequestBody SearchEmployeeDto searchEmployeeDto,@RequestHeader(value = "branchid")  String branchId);

    @GetMapping("/processSalary")
    ResponseEntity<HrDataResponseDto> processSalary(@RequestHeader(value = "branchid") String branchId);

    @PostMapping("/rejectLeave")
    ResponseEntity<ResultResponse> rejectLeave(@RequestBody LeaveIdsDto dto,@RequestHeader (value = "currentAcademicYear") String currentAcademicYear,@RequestHeader(value = "branchid")  String branchId);

    @PostMapping("/approveLeave")
    ResponseEntity<ResultResponse> approveLeave(@RequestBody LeaveIdsDto dto,@RequestHeader (value = "currentAcademicYear") String currentAcademicYear,@RequestHeader(value = "branchid") String branchId);

    @GetMapping("/leaveApprovals")
    ResponseEntity<LeaveApprovalsResponseDto> leaveApprovals(@RequestHeader (value = "currentAcademicYear") String currentAcademicYear,@RequestHeader(value = "branchid") String branchId);

    @PostMapping("/applyLeave")
    ResponseEntity<ResultResponse> applyLeave(@RequestBody ApplyLeaveDto dto,@RequestHeader (value = "currentAcademicYear") String currentAcademicYear,@RequestHeader (value="userAuth")String userAuth,@RequestHeader (value="username")String username,@RequestHeader(value = "branchid") String branchId,@RequestHeader (value="userloginid")String userId);

    @GetMapping("/leaveApplication")
    ResponseEntity<LeaveTypeResponseDto> leaveApplication(@RequestHeader(value = "branchid") String branchId);

    @GetMapping("/salaryIssue")
    ResponseEntity<SalaryResponseDto> salaryIssue(@RequestHeader(value = "branchid") String branchId);

    @PostMapping("/deleteAdvaceSalaryApproval")
    ResponseEntity<ResultResponse> deleteAdvaceSalaryApproval(@RequestBody DeleteAdvaceSalaryApprovalDto dto);

    @PostMapping("/saveAdvaceSalaryApproval")
    ResponseEntity<ResultResponse> saveAdvaceSalaryApproval(@RequestBody AdvanceSalaryApprovalDto dto ,@RequestHeader(value = "branchid") String branchId);

    @GetMapping("/salaryApproval")
    ResponseEntity<SalaryResponseDto> salaryApproval(@RequestHeader(value = "branchid") String branchId);

    @PostMapping("/saveAdvanceSalary")
    ResponseEntity<ResultResponse> saveAdvanceSalary(@RequestBody SaveAdvanceSalaryDto dto,@RequestHeader(value = "branchid") String branchId,@RequestHeader (value="userId") String userId);

    @GetMapping("/advanceSalary")
    ResponseEntity<EmployeesWithSalaryResponseDto> advanceSalary(@RequestHeader(value = "branchid") String branchId);

    @PostMapping("/deletePf")
    ResponseEntity<ResultResponse> deletePf(@RequestBody PfDto dto);

    @PostMapping("/addPf")
    ResponseEntity<ResultResponse>  addPf(@RequestBody PfDto dto,@RequestHeader(value = "branchid") String branchId,@RequestHeader(value = "userId")String userId);

    @GetMapping("/pfSettings")
    ResponseEntity<PfSettingsResponseDto> pfSettings(@RequestHeader(value = "branchid")String branchId);

    @PostMapping("/addBasicPay")
    ResponseEntity<ResultResponse> addBasicPay(@RequestBody BasicPayDto dto,@RequestHeader (value = "currentAcademicYear") String currentAcademicYear,@RequestHeader(value = "branchid") String branchId,@RequestHeader (value="userId")String userId);

    @PostMapping("/searchEmployeesForbasicpay")
    ResponseEntity<HrDataResponseDto> searchEmployeesForbasicpay(@RequestBody SearchEmployeeDto searchEmployeeDto,@RequestHeader(value = "branchid") String branchId);

    @GetMapping("/basicPaySettings")
    ResponseEntity<HrDataResponseDto> basicPaySettings(@RequestHeader(value = "branchid") String branchId);

    @PostMapping("/addPayHeadStaffDetails")
    ResponseEntity<ResultResponse> addPayHeadStaffDetails(@RequestBody PayHeadStaffDetailsDto dto,@RequestHeader (value = "currentAcademicYear") String currentAcademicYear,@RequestHeader(value = "branchid") String branchId,@RequestHeader (value="userId")String userId);

    @PostMapping("/searchEmployeesForPayHead")
    ResponseEntity<HrDataResponseDto> searchEmployeesForPayHead(@RequestBody SearchEmployeeDto searchEmployeeDto,@RequestHeader(value = "branchid") String branchId);

    @RequestMapping(value="/addPayHead", method= { RequestMethod.GET, RequestMethod.POST })
    ResponseEntity<HrDataResponseDto> addPayHeadStaff(@RequestHeader(value = "branchid") String branchId);

    @PostMapping("/savePayHead")
    ResponseEntity<ResultResponse> savePayHead(@RequestBody PayHeadDto dto,@RequestHeader (value = "currentAcademicYear") String currentAcademicYear,@RequestHeader(value = "branchid") String branchId,@RequestHeader (value="userId")String userId);

    @GetMapping("/payHead")
    ResponseEntity<PayHeadResponseDto> payHead(@RequestHeader (value = "currentAcademicYear")String currentAcademicYear,@RequestHeader(value = "branchid") String branchId);

    @PostMapping("/leaveDetailsPerYear")
    ResponseEntity<LeavesDetailsResponseDto> leaveDetailsPerYear(@RequestBody LeaveDetailsDto dto);

    @GetMapping("/viewLeavesDetails")
    ResponseEntity<LeavesDetailsResponseDto> viewLeavesDetails(@RequestParam(value = "id")String id);

    @PostMapping("/addLeaves")
    ResponseEntity<ResultResponse> addLeaves(@RequestBody LeaveTypeDto dto,@RequestHeader (value = "currentAcademicYear")String currentAcademicYear,@RequestHeader(value = "branchid") String branchId,@RequestHeader(value = "userId")String userId);

    @GetMapping("/searchEmployees")
    ResponseEntity<HrDataResponseDto>searchEmployees(@RequestBody SearchEmployeeDto searchEmployeeDto, @RequestHeader(value = "branchid") String branchId);

    @GetMapping("/assignLeave")
    ResponseEntity<HrDataResponseDto> assignLeave(@RequestHeader(value = "branchid") String branchId);

    @PostMapping("/deleteLeaveType")
    ResponseEntity<ResultResponse> deleteLeaveType(@RequestBody LeaveTypeDto dto);

    @PostMapping("/saveLeaveType")
    ResponseEntity<ResultResponse> saveLeaveType(@RequestBody LeaveTypeDto dto,@RequestHeader(value = "branchid") String branchId,@RequestHeader (value="userId")String userId);

    @GetMapping("/leaveType")
    ResponseEntity<LeaveTypeResponseDto> leaveType(@RequestHeader(value = "branchid") String branchId);

}
