package org.ideoholic.curium.model.mess.supplier.dto;

import lombok.Data;

@Data
public class SuppliersDetailsDto {
    private String name;
    private String ExternalId;
    private String contactNumber;
    private String bankAccountNo;
    private String ifscCode;
    private String address;
    private String payTo;
}
