package org.ideoholic.curium.model.hr.dto;

import lombok.Data;

@Data
public class BasicPayDto {
    private String[] staffIds;
    private String[] basicPay;
    private String[] paymentType;
    private String[] accountNo;
    private String[] overTime;

}
