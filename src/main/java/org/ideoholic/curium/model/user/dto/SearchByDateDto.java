package org.ideoholic.curium.model.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchByDateDto {
    private String branchId;
    private String toDate;
    private String fromDate;
    private String oneDay;
    private String modeOfPayment;
    private String academicYear;
}
