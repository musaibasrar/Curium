package org.ideoholic.curium.model.feescollection.dto;

import lombok.Data;

@Data
public class AddFeesCollectionDto {
    private String studentId;
    private String[] amountPaying;
    private String fineAmount;
    private String miscAmount;
    private String[] studentSfsIds;
    private String paymentMethod;
    private String ackNo;
    private String transferDate;
    private String transferBankName;
    private String chequeNo;
    private String chequeDate;
    private String chequeBankName;
    private String academicYear;
    private String dateOfFeesDetails;
    private String classAndSecDetails;
}
