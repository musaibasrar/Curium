package org.ideoholic.curium.model.user.dto;

import lombok.Data;

@Data
public class SuperDashboardFilterRequestDto {
    private String academicYear;
    private String branchIds;
    private String fromDate;
    private String toDate;
    private String selectedClass;
    private String section;
    private String examination;
    private String feeCategory;
    private String sortBy;
}
