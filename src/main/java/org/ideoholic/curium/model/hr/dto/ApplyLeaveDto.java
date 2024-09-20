package org.ideoholic.curium.model.hr.dto;

import lombok.Data;

@Data
public class ApplyLeaveDto {

    private String leaveTypeName;
    private String reason;
    private String fromDate;
    private String toDate;

}
