package org.ideoholic.curium.model.hr.dto;

import lombok.Data;

@Data
public class PfDto {

    private String paidByManagement;
    private String paidByStaff;
    private String date;
    private String[] pfids;

}
