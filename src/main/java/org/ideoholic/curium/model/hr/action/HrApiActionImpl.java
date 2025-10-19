package org.ideoholic.curium.model.hr.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.exceptions.CustomErrorMessage;
import org.ideoholic.curium.exceptions.CustomResponseException;
import org.ideoholic.curium.model.employee.dto.*;
import org.ideoholic.curium.model.employee.service.EmployeeService;
import org.ideoholic.curium.model.hr.dto.*;
import org.ideoholic.curium.model.hr.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class HrApiActionImpl implements HrApiAction {

    @Autowired
    private EmployeeService  employeeService;
    @Autowired
    private HrService hrService;

    private String error = "error";

    public ResponseEntity<ResultResponse> updateBasicPay(BasicPayDto dto, String branchId) {
        ResultResponse result = hrService.updateBasicPayEmployees(dto, branchId);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<BasicPayEmployeesDto> viewEditbasicPay(String branchId) {
        BasicPayEmployeesDto result = employeeService.basicpayEmployees(branchId);
        return ResponseEntity.ok(result);
    }


    public ResponseEntity<ResultResponse> cancelStaffSalary( SalaryDto dto,String currentAcademicYear, String branchId) {
        ResultResponse result = hrService.cancelProcessedSalary(dto,currentAcademicYear,branchId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }


    public ResponseEntity<ResultResponse> issueProcessedSalary( SalaryDto dto, String currentAcademicYear,String branchId) {
        ResultResponse result = hrService.issueProcessedSalary(dto,currentAcademicYear,branchId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        throw  new CustomResponseException(CustomErrorMessage.ERROR);
    }


    public ResponseEntity<StaffDetailsResponseDto> deletePayHeadStaff( SalaryDto dto, String currentAcademicYear ) {

        StaffDetailsResponseDto result = hrService.deletePayHeadStaff(dto,currentAcademicYear);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }


    public ResponseEntity<StaffDetailsResponseDto> getStaffDetails( StaffDetailsDto dto,String currentAcademicYear) {
        StaffDetailsResponseDto result = hrService.getStaffDetails(dto, currentAcademicYear);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<EmployeesWithSalaryResponseDto> deletePayHead(String branchId) {
        EmployeesWithSalaryResponseDto result = employeeService.ViewAllEmployee(branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }


    public ResponseEntity<SalarySlipResponseDto> printSalarySlip(String processSalaryId) {

       SalarySlipResponseDto result = hrService.printSalarySlip(processSalaryId);
       if (result.isSuccess()) {
           return ResponseEntity.ok(result);
       }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }


    public ResponseEntity<SalaryResponseDto> issueStaffSalary(String currentAcademicYear,String branchId) {
        SalaryResponseDto result = hrService.issueStaffSalary(currentAcademicYear,branchId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }


    public ResponseEntity<PayHeadResponseDto> getPayHead( String payHeadType, String currentAcademicYear,String branchId) {
        try {
            PayHeadResponseDto result = hrService.getPayHead(payHeadType,currentAcademicYear,branchId);
             if (result.isSuccess()){
                 return ResponseEntity.ok(result);
             }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        throw new CustomResponseException(CustomErrorMessage.ERROR);

    }


    public ResponseEntity<ResultResponse> processStaffSalary( SalaryDto dto,String currentAcademicYear, String branchId, String userId) {
        ResultResponse result = hrService.processStaffSalary(dto,currentAcademicYear,branchId,userId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }


    public ResponseEntity<HrDataResponseDto> searchEmployeesForProcessSalary( SearchEmployeeDto searchEmployeeDto, String branchId) {
       HrDataResponseDto result = new HrDataResponseDto();
        EmployeeListDto employeeListDto = employeeService.searchEmployee(searchEmployeeDto,branchId);
        result.setEmployeeList(employeeListDto.getEmployeeList());

        ViewAllRelationsResponseDto viewAllRelationsResponseDto = employeeService.viewAllRelations(branchId);
        result.setListDepartment(viewAllRelationsResponseDto.getListDepartment());
        result.setListPosition(viewAllRelationsResponseDto.getListPosition());

       return  ResponseEntity.ok(result);
    }


    public ResponseEntity<HrDataResponseDto> processSalary(String branchId) {
        HrDataResponseDto result = new HrDataResponseDto();

        EmployeesWithSalaryResponseDto viewAllEmployeeResponseDto = employeeService.ViewAllEmployee(branchId);
        result.setEmployeeList(viewAllEmployeeResponseDto.getEmployeeList());
        result.setEmployeeListProcessSalary(viewAllEmployeeResponseDto.getEmployeeListProcessSalary());
        result.setSuccess(viewAllEmployeeResponseDto.isSuccess());

        ViewAllRelationsResponseDto viewAllRelationsResponseDto = employeeService.viewAllRelations(branchId);
        result.setListDepartment(viewAllRelationsResponseDto.getListDepartment());
        result.setListPosition(viewAllRelationsResponseDto.getListPosition());

        return ResponseEntity.ok(result);
    }


    public ResponseEntity<ResultResponse> rejectLeave(LeaveIdsDto dto, String currentAcademicYear,String branchId) {
       ResultResponse result = hrService.rejectLeave(dto);
       LeaveApprovalsResponseDto leaveApprovalsResponseDto = hrService.leaveApprovals(currentAcademicYear,branchId);
        if(result.isSuccess()){

            return ResponseEntity.ok(result);
        }
       throw new CustomResponseException(CustomErrorMessage.ERROR);

    }


    public ResponseEntity<ResultResponse> approveLeave( LeaveIdsDto dto, String currentAcademicYear, String branchId) {
        ResultResponse result = hrService.approveLeave(dto);
        LeaveApprovalsResponseDto leaveApprovalsResponseDto = hrService.leaveApprovals(currentAcademicYear,branchId);
        if(result.isSuccess()){

            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException( CustomErrorMessage.ERROR);
    }


    public ResponseEntity<LeaveApprovalsResponseDto> leaveApprovals(String currentAcademicYear,String branchId) {
       LeaveApprovalsResponseDto result = hrService.leaveApprovals(currentAcademicYear,branchId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }


    public ResponseEntity<ResultResponse> applyLeave( ApplyLeaveDto dto,String currentAcademicYear, String userAuth,String username, String branchId,String userId) {
       ResultResponse result = hrService.applyLeave(dto,currentAcademicYear,userAuth,username,branchId,userId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }


    public ResponseEntity<LeaveTypeResponseDto> leaveApplication(String branchId) {
         LeaveTypeResponseDto result = hrService.leaveType(branchId);

        return ResponseEntity.ok(result);

    }


    public ResponseEntity<SalaryResponseDto> salaryIssue(String branchId) {
        SalaryResponseDto result = hrService.salaryIssue(branchId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }


    public ResponseEntity<ResultResponse> deleteAdvaceSalaryApproval( DeleteAdvaceSalaryApprovalDto dto) {
       ResultResponse result = hrService.deleteAdvaceSalaryApproval(dto);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<ResultResponse> saveAdvaceSalaryApproval( AdvanceSalaryApprovalDto dto , String branchId) {
        ResultResponse result = hrService.saveAdvanceSalaryApproval(dto,branchId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<SalaryResponseDto> salaryApproval( String branchId) {
        SalaryResponseDto result = hrService.salaryApprovalDispaly(branchId);

        return ResponseEntity.ok(result);
    }

    public ResponseEntity<ResultResponse> saveAdvanceSalary( SaveAdvanceSalaryDto dto,String branchId, String userId) {
        ResultResponse result = hrService.saveAdvanceSalary(dto,branchId,userId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<EmployeesWithSalaryResponseDto> advanceSalary(String branchId) {
        EmployeesWithSalaryResponseDto result = employeeService.ViewAllEmployee(branchId);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<ResultResponse> deletePf( PfDto dto) {
        ResultResponse result = hrService.deletePf(dto);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<ResultResponse>  addPf( PfDto dto, String branchId, String userId) {
        ResultResponse result = hrService.addPf(dto,branchId,userId);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<PfSettingsResponseDto> pfSettings(String branchId) {
       PfSettingsResponseDto result = hrService.pfSettings(branchId);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<ResultResponse> addBasicPay( BasicPayDto dto, String currentAcademicYear,String branchId,String userId) {
        ResultResponse result = hrService.addBasicPay(dto,currentAcademicYear,branchId,userId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<HrDataResponseDto> searchEmployeesForbasicpay( SearchEmployeeDto searchEmployeeDto,String branchId) {
        HrDataResponseDto result = new HrDataResponseDto();

        EmployeeListDto employeeListDto = employeeService.searchEmployee(searchEmployeeDto,branchId);
        result.setEmployeeList(employeeListDto.getEmployeeList());

        ViewAllRelationsResponseDto viewAllRelationsResponseDto = employeeService.viewAllRelations(branchId);
        result.setListDepartment(viewAllRelationsResponseDto.getListDepartment());
        result.setListPosition(viewAllRelationsResponseDto.getListPosition());

        return  ResponseEntity.ok(result);
    }

    public ResponseEntity<HrDataResponseDto> basicPaySettings(String branchId) {
        HrDataResponseDto result = new HrDataResponseDto();

        EmployeesWithSalaryResponseDto viewAllEmployeeResponseDto = employeeService.ViewAllEmployee(branchId);
        result.setEmployeeList(viewAllEmployeeResponseDto.getEmployeeList());
        result.setEmployeeListProcessSalary(viewAllEmployeeResponseDto.getEmployeeListProcessSalary());
        result.setSuccess(viewAllEmployeeResponseDto.isSuccess());


        ViewAllRelationsResponseDto viewAllRelationsResponseDto = employeeService.viewAllRelations(branchId);
        result.setListDepartment(viewAllRelationsResponseDto.getListDepartment());
        result.setListPosition(viewAllRelationsResponseDto.getListPosition());


        return ResponseEntity.ok(result);
    }

    public ResponseEntity<ResultResponse> addPayHeadStaffDetails( PayHeadStaffDetailsDto dto,String currentAcademicYear,String branchId,String userId) {
        ResultResponse result = hrService.addPayHeadStaffDetails(dto, currentAcademicYear, branchId, userId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);

    }
    public ResponseEntity<HrDataResponseDto> searchEmployeesForPayHead(SearchEmployeeDto searchEmployeeDto,String branchId) {
        HrDataResponseDto result = new HrDataResponseDto();
        EmployeeListDto employeeListDto = employeeService.searchEmployee(searchEmployeeDto,branchId);
        result.setEmployeeList(employeeListDto.getEmployeeList());

        ViewAllRelationsResponseDto viewAllRelationsResponseDto = employeeService.viewAllRelations(branchId);
        result.setListDepartment(viewAllRelationsResponseDto.getListDepartment());
        result.setListPosition(viewAllRelationsResponseDto.getListPosition());


        return  ResponseEntity.ok(result);
    }
    public ResponseEntity<HrDataResponseDto> addPayHeadStaff(String branchId) {
        HrDataResponseDto result = new HrDataResponseDto();
        EmployeesWithSalaryResponseDto viewAllEmployeeResponseDto = employeeService.ViewAllEmployee(branchId);
        result.setEmployeeList(viewAllEmployeeResponseDto.getEmployeeList());
        result.setEmployeeListProcessSalary(viewAllEmployeeResponseDto.getEmployeeListProcessSalary());
        result.setSuccess(viewAllEmployeeResponseDto.isSuccess());

        ViewAllRelationsResponseDto viewAllRelationsResponseDto = employeeService.viewAllRelations(branchId);
        result.setListDepartment(viewAllRelationsResponseDto.getListDepartment());
        result.setListPosition(viewAllRelationsResponseDto.getListPosition());

        return ResponseEntity.ok(result);
    }

    public ResponseEntity<ResultResponse> savePayHead( PayHeadDto dto, String currentAcademicYear, String branchId, String userId) {
       ResultResponse result = hrService.savePayHead(dto,currentAcademicYear,branchId,userId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);

    }

    public ResponseEntity<PayHeadResponseDto> payHead( String currentAcademicYear, String branchId) {
        PayHeadResponseDto result = hrService.payHead(currentAcademicYear,branchId);

        return ResponseEntity.ok(result);
    }

    public ResponseEntity<LeavesDetailsResponseDto> leaveDetailsPerYear( LeaveDetailsDto dto) {
        LeavesDetailsResponseDto result = hrService.leaveDetailsPerYear(dto);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<LeavesDetailsResponseDto> viewLeavesDetails(String id) {
        LeavesDetailsResponseDto result = hrService.viewLeavesDetails(id);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<ResultResponse> addLeaves( LeaveTypeDto dto, String currentAcademicYear, String branchId, String userId) {
        ResultResponse result = hrService.addLeaves(dto,currentAcademicYear,branchId,userId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<HrDataResponseDto>searchEmployees( SearchEmployeeDto searchEmployeeDto, String branchId) {
        HrDataResponseDto result = new HrDataResponseDto();
        EmployeeListDto employeeListDto = employeeService.searchEmployee(searchEmployeeDto,branchId);
        result.setEmployeeList(employeeListDto.getEmployeeList());

        ViewAllRelationsResponseDto viewAllRelationsResponseDto = employeeService.viewAllRelations(branchId);
         result.setListDepartment(viewAllRelationsResponseDto.getListDepartment());
         result.setListPosition(viewAllRelationsResponseDto.getListPosition());

        LeaveTypeResponseDto leaveTypeResponseDto =  hrService.leaveType(branchId);
        result.setLeavetypemaster(leaveTypeResponseDto.getLeavetypemaster());
        result.setSuccess(leaveTypeResponseDto.isSuccess());

        return ResponseEntity.ok(result);
    }

    public ResponseEntity<HrDataResponseDto> assignLeave( String branchId) {
        HrDataResponseDto result = new HrDataResponseDto();
        EmployeesWithSalaryResponseDto viewAllEmployeeResponseDto = employeeService.ViewAllEmployee(branchId);
        result.setEmployeeList(viewAllEmployeeResponseDto.getEmployeeList());
        result.setEmployeeListProcessSalary(viewAllEmployeeResponseDto.getEmployeeListProcessSalary());
        result.setSuccess(viewAllEmployeeResponseDto.isSuccess());

        ViewAllRelationsResponseDto viewAllRelationsResponseDto = employeeService.viewAllRelations(branchId);
        result.setListDepartment(viewAllRelationsResponseDto.getListDepartment());
        result.setListPosition(viewAllRelationsResponseDto.getListPosition());

       LeaveTypeResponseDto leaveTypeResponseDto = hrService.leaveType(branchId);
        result.setLeavetypemaster(leaveTypeResponseDto.getLeavetypemaster());
        result.setSuccess(leaveTypeResponseDto.isSuccess());

       return ResponseEntity.ok(result);
    }

    public ResponseEntity<ResultResponse> deleteLeaveType( LeaveTypeDto dto) {
         ResultResponse result = hrService.deleteLeaveType(dto);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<ResultResponse> saveLeaveType(LeaveTypeDto dto, String branchId, String userId) {
        ResultResponse result = hrService.saveLeaveType(dto,branchId,userId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<LeaveTypeResponseDto> leaveType(String branchId) {
        LeaveTypeResponseDto result = hrService.leaveType(branchId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

}
