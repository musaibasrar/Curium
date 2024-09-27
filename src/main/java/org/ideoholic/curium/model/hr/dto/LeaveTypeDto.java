package org.ideoholic.curium.model.hr.dto;

import lombok.Data;

@Data
public class LeaveTypeDto {
    private String leaveTypeName;
    private String idLeave;

    private String[] leaveTypeNames;
    private String[] totalLeaves;
    private String[] staff;
}
