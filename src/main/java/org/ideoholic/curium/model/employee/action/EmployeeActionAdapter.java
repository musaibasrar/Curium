package org.ideoholic.curium.model.employee.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.department.dto.DepartmentResponseDto;
import org.ideoholic.curium.model.employee.dto.*;
import org.ideoholic.curium.model.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
public class EmployeeActionAdapter {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private HttpSession httpSession;
    private String BRANCHID = "branchid";

    public boolean addEmployee(MultipartFile[] listOfFiles) {
        EmployeeService employeeService = new EmployeeService(request,response);

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName(request.getParameter("name"));
        employeeDto.setGender(request.getParameter("gender"));
        employeeDto.setAddress(request.getParameter("address"));
        employeeDto.setContactNumber(request.getParameter("contactnumber"));
        employeeDto.setEmail(request.getParameter("email"));
        employeeDto.setDateOfJoining(request.getParameter("dateofjoining"));
        employeeDto.setTotalExperience(request.getParameter("totalexperience"));
        employeeDto.setQualification(request.getParameter("qualification"));
        employeeDto.setDepartment(request.getParameter("department"));
        employeeDto.setDesignation(request.getParameter("designation"));
        employeeDto.setSalary(request.getParameter("salary"));
        employeeDto.setRemarks(request.getParameter("remarks"));
        employeeDto.setCurrentEmployee(request.getParameter("currentemployee"));
        employeeDto.setJoiningDate(request.getParameter("joiningdate"));
        employeeDto.setBankName(request.getParameter("bankname"));
        employeeDto.setBankIFSC(request.getParameter("bankifsc"));
        employeeDto.setAccNo(request.getParameter("accno"));

        employeeDto.setBranchCode(httpSession.getAttribute("branchcode").toString());
        ResultResponse resultResponse = employeeService.addEmployee(listOfFiles, employeeDto,httpSession.getAttribute(BRANCHID).toString());
        return resultResponse.isSuccess();
    }
    public boolean viewDetailsEmployee() {
        EmployeeService employeeService = new EmployeeService(request,response);
        ViewDetailsEmployeeResponseDto viewDetailsEmployeeResponseDto = employeeService.viewDetailsEmployee();

        request.setAttribute("stafflogin", viewDetailsEmployeeResponseDto.getEmployeeLogin());

        httpSession.setAttribute("employee", viewDetailsEmployeeResponseDto.getEmployee());

        return viewDetailsEmployeeResponseDto.isSuccess();
    }
    public String updateEmployee(MultipartFile[] listOfFiles) {
        EmployeeService employeeService = new EmployeeService(request,response);

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(request.getParameter("id"));
        employeeDto.setName(request.getParameter("name"));
        employeeDto.setGender(request.getParameter("gender"));
        employeeDto.setAddress(request.getParameter("address"));
        employeeDto.setContactNumber(request.getParameter("contactnumber"));
        employeeDto.setEmail(request.getParameter("email"));
        employeeDto.setDateOfJoining(request.getParameter("dateofjoining"));
        employeeDto.setTotalExperience(request.getParameter("totalexperience"));
        employeeDto.setQualification(request.getParameter("qualification"));
        employeeDto.setDepartment(request.getParameter("department"));
        employeeDto.setDesignation(request.getParameter("designation"));
        employeeDto.setSalary(request.getParameter("salary"));
        employeeDto.setRemarks(request.getParameter("remarks"));
        employeeDto.setCurrentEmployee(request.getParameter("currentemployee"));
        employeeDto.setTeacherExternalId(request.getParameter("teacherexternalid"));
        employeeDto.setLeavingdate(request.getParameter("leavingdate"));
        employeeDto.setJoiningDate(request.getParameter("joiningdate"));
        employeeDto.setBankName(request.getParameter("bankname"));
        employeeDto.setBankIFSC(request.getParameter("bankifsc"));
        employeeDto.setAccNo(request.getParameter("accno"));
        employeeDto.setEmployeephotoupdate(request.getParameter("employeephotoupdate"));
        employeeDto.setEmployeedoc1update(request.getParameter("employeedoc1update"));
        employeeDto.setEmployeedoc2update(request.getParameter("employeedoc2update"));
        employeeDto.setEmployeedoc3update(request.getParameter("employeedoc3update"));
        employeeDto.setEmployeedoc4update(request.getParameter("employeedoc4update"));
        employeeDto.setEmployeedoc5update(request.getParameter("employeedoc5update"));
        employeeDto.setEmployeedoc1delete(request.getParameter("employeedoc1delete"));
        employeeDto.setEmployeedoc2delete(request.getParameter("employeedoc2delete"));
        employeeDto.setEmployeedoc3delete(request.getParameter("employeedoc3delete"));
        employeeDto.setEmployeedoc4delete(request.getParameter("employeedoc4delete"));
        employeeDto.setEmployeedoc5delete(request.getParameter("employeedoc5delete"));

        Teacher employee = employeeService.updateEmployee(listOfFiles,employeeDto);

        return employee.getTid().toString();

    }
    public boolean ViewAllEmployee() {
        EmployeeService employeeService = new EmployeeService(request,response);

        ViewAllEmployeeResponseDto viewAllEmployeeResponseDto = employeeService.ViewAllEmployee(httpSession.getAttribute(BRANCHID).toString());
        httpSession.setAttribute("employeeList", viewAllEmployeeResponseDto.getEmployeeList());
        httpSession.setAttribute("employeeListProcessSalary", viewAllEmployeeResponseDto.getEmployeeListProcessSalary());


        return viewAllEmployeeResponseDto.isSuccess();
    }
    public void deleteMultiple() {
        EmployeeService employeeService = new EmployeeService(request,response);
        EmployeeIdsDto employeeIdsDto = new EmployeeIdsDto();
        employeeIdsDto.setEmployeeIds(request.getParameterValues("employeeIDs"));
        employeeService.deleteMultiple(employeeIdsDto);
    }
    public void viewAllRelations() {
        EmployeeService employeeService = new EmployeeService(request,response);
        ViewAllRelationsResponseDto viewAllRelationsResponseDto = employeeService.viewAllRelations(httpSession.getAttribute(BRANCHID).toString());
        httpSession.setAttribute("listDepartment", viewAllRelationsResponseDto.getListDepartment());
        httpSession.setAttribute("listPosition", viewAllRelationsResponseDto.getListPosition());

    }
    public void searchEmployee() {
        EmployeeService employeeService = new EmployeeService(request,response);

        SearchEmployeeDto searchEmployeeDto = new SearchEmployeeDto();
        searchEmployeeDto.setStaffName(request.getParameter("staffName"));
        searchEmployeeDto.setStaffDepartment(request.getParameter("staffDepartment"));
        EmployeeListDto employeeListDto = employeeService.searchEmployee(searchEmployeeDto,httpSession.getAttribute(BRANCHID).toString());

        request.setAttribute("searchedemployeeList", employeeListDto.getEmployeeList());
    }
    public void basicpayEmployees() {
        EmployeeService employeeService = new EmployeeService(request,response);

        BasicPayEmployeesDto basicPayEmployeesDto = employeeService.basicpayEmployees(httpSession.getAttribute(BRANCHID).toString());

        request.setAttribute("vieweditbasicpay", basicPayEmployeesDto.getBasicPay());
    }
    public void viewDepartments() {
        EmployeeService employeeService = new EmployeeService(request,response);

        DepartmentResponseDto departmentResponseDto = employeeService.viewDepartments(httpSession.getAttribute(BRANCHID).toString());

        httpSession.setAttribute("listDepartment", departmentResponseDto.getDepartmentList());
    }

}
