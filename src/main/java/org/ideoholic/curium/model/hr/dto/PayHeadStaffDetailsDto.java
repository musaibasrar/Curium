package org.ideoholic.curium.model.hr.dto;

import lombok.Data;

@Data
public class PayHeadStaffDetailsDto {
    private String[] staffIds;
    private String[] values;
    private String payHeadId;
    private String amountPer;

}
