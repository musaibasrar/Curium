package org.ideoholic.curium.model.hr.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.employee.dto.BasicPayEmployeesDto;
import org.ideoholic.curium.model.employee.dto.EmployeesWithSalaryResponseDto;
import org.ideoholic.curium.model.employee.dto.SearchEmployeeDto;
import org.ideoholic.curium.model.hr.dto.AdvanceSalaryApprovalDto;
import org.ideoholic.curium.model.hr.dto.ApplyLeaveDto;
import org.ideoholic.curium.model.hr.dto.BasicPayDto;
import org.ideoholic.curium.model.hr.dto.DeleteAdvaceSalaryApprovalDto;
import org.ideoholic.curium.model.hr.dto.HrDataResponseDto;
import org.ideoholic.curium.model.hr.dto.LeaveApprovalsResponseDto;
import org.ideoholic.curium.model.hr.dto.LeaveDetailsDto;
import org.ideoholic.curium.model.hr.dto.LeaveIdsDto;
import org.ideoholic.curium.model.hr.dto.LeaveTypeDto;
import org.ideoholic.curium.model.hr.dto.LeaveTypeResponseDto;
import org.ideoholic.curium.model.hr.dto.LeavesDetailsResponseDto;
import org.ideoholic.curium.model.hr.dto.PayHeadDto;
import org.ideoholic.curium.model.hr.dto.PayHeadResponseDto;
import org.ideoholic.curium.model.hr.dto.PayHeadStaffDetailsDto;
import org.ideoholic.curium.model.hr.dto.PfDto;
import org.ideoholic.curium.model.hr.dto.PfSettingsResponseDto;
import org.ideoholic.curium.model.hr.dto.SalaryDto;
import org.ideoholic.curium.model.hr.dto.SalaryResponseDto;
import org.ideoholic.curium.model.hr.dto.SalarySlipResponseDto;
import org.ideoholic.curium.model.hr.dto.SaveAdvanceSalaryDto;
import org.ideoholic.curium.model.hr.dto.StaffDetailsDto;
import org.ideoholic.curium.model.hr.dto.StaffDetailsResponseDto;
import org.ideoholic.curium.util.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/v1/hrProcess")
public interface HrApiAction {


    @PostMapping("/updateBasicPay")
    ResponseEntity<ResultResponse> updateBasicPay( @RequestBody BasicPayDto dto, @RequestHeader(value = Constants.BRANCHID) String branchId);

    @GetMapping("/viewEditbasicPay")
    ResponseEntity<BasicPayEmployeesDto> viewEditbasicPay( @RequestHeader(value = Constants.BRANCHID) String branchId);

    @PostMapping("/cancelStaffSalary")
    ResponseEntity<ResultResponse> cancelStaffSalary(@RequestBody SalaryDto dto, @RequestHeader (value = Constants.CURRENTACADEMICYEAR) String currentAcademicYear,@RequestHeader(value = Constants.BRANCHID) String branchId);

    @PostMapping("/issueProcessedSalary")
    ResponseEntity<ResultResponse> issueProcessedSalary(@RequestBody SalaryDto dto, @RequestHeader (value = Constants.CURRENTACADEMICYEAR) String currentAcademicYear,@RequestHeader(value = Constants.BRANCHID)  String branchId);

    @PostMapping("/deletePayHeadStaff")
    ResponseEntity<StaffDetailsResponseDto> deletePayHeadStaff(@RequestBody SalaryDto dto,@RequestHeader (value = Constants.CURRENTACADEMICYEAR) String currentAcademicYear );

    @PostMapping("/getStaffDetails")
    ResponseEntity<StaffDetailsResponseDto> getStaffDetails(@RequestBody StaffDetailsDto dto,@RequestHeader (value = Constants.CURRENTACADEMICYEAR) String currentAcademicYear);

    @RequestMapping(value= "/deletePayHead", method= { RequestMethod.GET, RequestMethod.POST })
    ResponseEntity<EmployeesWithSalaryResponseDto> deletePayHead(@RequestHeader(value = Constants.BRANCHID) String branchId);

    @GetMapping("/printSalarySlip")
    ResponseEntity<SalarySlipResponseDto> printSalarySlip(@RequestHeader(value = "processSalaryId") String processSalaryId);

    @GetMapping("/issueStaffSalary")
    ResponseEntity<SalaryResponseDto> issueStaffSalary(@RequestHeader (value=Constants.CURRENTACADEMICYEAR)String currentAcademicYear,@RequestHeader(value = Constants.BRANCHID)  String branchId);

    @GetMapping("/getPayHead")
    ResponseEntity<PayHeadResponseDto> getPayHead(@RequestParam(value = "payHeadType") String payHeadType,@RequestHeader (value = Constants.CURRENTACADEMICYEAR) String currentAcademicYear,@RequestHeader(value = Constants.BRANCHID) String branchId);

    @PostMapping("/processStaffSalary")
    ResponseEntity<ResultResponse> processStaffSalary(@RequestBody SalaryDto dto,@RequestHeader (value = Constants.CURRENTACADEMICYEAR) String currentAcademicYear,@RequestHeader(value = Constants.BRANCHID) String branchId,@RequestHeader (value=Constants.USERID)String userId);

    @PostMapping("/searchEmployeesForProcessSalary")
    ResponseEntity<HrDataResponseDto> searchEmployeesForProcessSalary(@RequestBody SearchEmployeeDto searchEmployeeDto,@RequestHeader(value = Constants.BRANCHID)  String branchId);

    @GetMapping("/processSalary")
    ResponseEntity<HrDataResponseDto> processSalary(@RequestHeader(value = Constants.BRANCHID) String branchId);

    @PostMapping("/rejectLeave")
    ResponseEntity<ResultResponse> rejectLeave(@RequestBody LeaveIdsDto dto,@RequestHeader (value = Constants.CURRENTACADEMICYEAR) String currentAcademicYear,@RequestHeader(value = Constants.BRANCHID)  String branchId);

    @PostMapping("/approveLeave")
    ResponseEntity<ResultResponse> approveLeave(@RequestBody LeaveIdsDto dto,@RequestHeader (value = Constants.CURRENTACADEMICYEAR) String currentAcademicYear,@RequestHeader(value = Constants.BRANCHID) String branchId);

    @GetMapping("/leaveApprovals")
    ResponseEntity<LeaveApprovalsResponseDto> leaveApprovals(@RequestHeader (value = Constants.CURRENTACADEMICYEAR) String currentAcademicYear,@RequestHeader(value = Constants.BRANCHID) String branchId);

    @PostMapping("/applyLeave")
    ResponseEntity<ResultResponse> applyLeave(@RequestBody ApplyLeaveDto dto,@RequestHeader (value = Constants.CURRENTACADEMICYEAR) String currentAcademicYear,@RequestHeader (value=Constants.USERAUTH)String userAuth,@RequestHeader (value=Constants.USERNAME)String username,@RequestHeader(value = Constants.BRANCHID) String branchId,@RequestHeader (value="userloginid")String userId);

    @GetMapping("/leaveApplication")
    ResponseEntity<LeaveTypeResponseDto> leaveApplication(@RequestHeader(value = Constants.BRANCHID) String branchId);

    @GetMapping("/salaryIssue")
    ResponseEntity<SalaryResponseDto> salaryIssue(@RequestHeader(value = Constants.BRANCHID) String branchId);

    @PostMapping("/deleteAdvaceSalaryApproval")
    ResponseEntity<ResultResponse> deleteAdvaceSalaryApproval(@RequestBody DeleteAdvaceSalaryApprovalDto dto);

    @PostMapping("/saveAdvaceSalaryApproval")
    ResponseEntity<ResultResponse> saveAdvaceSalaryApproval(@RequestBody AdvanceSalaryApprovalDto dto ,@RequestHeader(value = Constants.BRANCHID) String branchId);

    @GetMapping("/salaryApproval")
    ResponseEntity<SalaryResponseDto> salaryApproval(@RequestHeader(value = Constants.BRANCHID) String branchId);

    @PostMapping("/saveAdvanceSalary")
    ResponseEntity<ResultResponse> saveAdvanceSalary(@RequestBody SaveAdvanceSalaryDto dto,@RequestHeader(value = Constants.BRANCHID) String branchId,@RequestHeader (value=Constants.USERID) String userId);

    @GetMapping("/advanceSalary")
    ResponseEntity<EmployeesWithSalaryResponseDto> advanceSalary(@RequestHeader(value = Constants.BRANCHID) String branchId);

    @PostMapping("/deletePf")
    ResponseEntity<ResultResponse> deletePf(@RequestBody PfDto dto);

    @PostMapping("/addPf")
    ResponseEntity<ResultResponse>  addPf(@RequestBody PfDto dto,@RequestHeader(value = Constants.BRANCHID) String branchId,@RequestHeader(value = Constants.USERID)String userId);

    @GetMapping("/pfSettings")
    ResponseEntity<PfSettingsResponseDto> pfSettings(@RequestHeader(value = Constants.BRANCHID)String branchId);

    @PostMapping("/addBasicPay")
    ResponseEntity<ResultResponse> addBasicPay(@RequestBody BasicPayDto dto,@RequestHeader (value = Constants.CURRENTACADEMICYEAR) String currentAcademicYear,@RequestHeader(value = Constants.BRANCHID) String branchId,@RequestHeader (value=Constants.USERID)String userId);

    @PostMapping("/searchEmployeesForbasicpay")
    ResponseEntity<HrDataResponseDto> searchEmployeesForbasicpay(@RequestBody SearchEmployeeDto searchEmployeeDto,@RequestHeader(value = Constants.BRANCHID) String branchId);

    @GetMapping("/basicPaySettings")
    ResponseEntity<HrDataResponseDto> basicPaySettings(@RequestHeader(value = Constants.BRANCHID) String branchId);

    @PostMapping("/addPayHeadStaffDetails")
    ResponseEntity<ResultResponse> addPayHeadStaffDetails(@RequestBody PayHeadStaffDetailsDto dto,@RequestHeader (value = Constants.CURRENTACADEMICYEAR) String currentAcademicYear,@RequestHeader(value = Constants.BRANCHID) String branchId,@RequestHeader (value=Constants.USERID)String userId);

    @PostMapping("/searchEmployeesForPayHead")
    ResponseEntity<HrDataResponseDto> searchEmployeesForPayHead(@RequestBody SearchEmployeeDto searchEmployeeDto,@RequestHeader(value = Constants.BRANCHID) String branchId);

    @RequestMapping(value="/addPayHead", method= { RequestMethod.GET, RequestMethod.POST })
    ResponseEntity<HrDataResponseDto> addPayHeadStaff(@RequestHeader(value = Constants.BRANCHID) String branchId);

    @PostMapping("/savePayHead")
    ResponseEntity<ResultResponse> savePayHead(@RequestBody PayHeadDto dto,@RequestHeader (value = Constants.CURRENTACADEMICYEAR) String currentAcademicYear,@RequestHeader(value = Constants.BRANCHID) String branchId,@RequestHeader (value=Constants.USERID)String userId);

    @GetMapping("/payHead")
    ResponseEntity<PayHeadResponseDto> payHead(@RequestHeader (value = Constants.CURRENTACADEMICYEAR)String currentAcademicYear,@RequestHeader(value = Constants.BRANCHID) String branchId);

    @PostMapping("/leaveDetailsPerYear")
    ResponseEntity<LeavesDetailsResponseDto> leaveDetailsPerYear(@RequestBody LeaveDetailsDto dto);

    @GetMapping("/viewLeavesDetails")
    ResponseEntity<LeavesDetailsResponseDto> viewLeavesDetails(@RequestParam(value = "id")String id);

    @PostMapping("/addLeaves")
    ResponseEntity<ResultResponse> addLeaves(@RequestBody LeaveTypeDto dto,@RequestHeader (value = Constants.CURRENTACADEMICYEAR)String currentAcademicYear,@RequestHeader(value = Constants.BRANCHID) String branchId,@RequestHeader(value = Constants.USERID)String userId);

    @GetMapping("/searchEmployees")
    ResponseEntity<HrDataResponseDto>searchEmployees(@RequestBody SearchEmployeeDto searchEmployeeDto, @RequestHeader(value = Constants.BRANCHID) String branchId);

    @GetMapping("/assignLeave")
    ResponseEntity<HrDataResponseDto> assignLeave(@RequestHeader(value = Constants.BRANCHID) String branchId);

    @PostMapping("/deleteLeaveType")
    ResponseEntity<ResultResponse> deleteLeaveType(@RequestBody LeaveTypeDto dto);

    @PostMapping("/saveLeaveType")
    ResponseEntity<ResultResponse> saveLeaveType(@RequestBody LeaveTypeDto dto,@RequestHeader(value = Constants.BRANCHID) String branchId,@RequestHeader (value=Constants.USERID)String userId);

    @GetMapping("/leaveType")
    ResponseEntity<LeaveTypeResponseDto> leaveType(@RequestHeader(value = Constants.BRANCHID) String branchId);

}
