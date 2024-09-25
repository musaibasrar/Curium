package org.ideoholic.curium.model.employee.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class EmployeeDto {
    private String name;
    private String gender;
    private String address;
    private String contactNumber;
    private String email;
    private String dateOfJoining;
    private String totalExperience;
    private String qualification;
    private String department;
    private String designation;
    private String salary;
    private String remarks;
    private String currentEmployee;
    private String joiningDate;
    private String bankName;
    private String bankIFSC;
    private String accNo;
    private String id;
    private String teacherExternalId;
    private String leavingdate;
    private String employeephotoupdate;
    private String employeedoc1update;
    private String employeedoc2update;
    private String employeedoc3update;
    private String employeedoc4update;
    private String employeedoc5update;
    private String employeedoc1delete;
    private String employeedoc2delete;
    private String employeedoc3delete;
    private String employeedoc4delete;
    private String employeedoc5delete;
    private String branchId;
    private String subjectsteaching;
}
