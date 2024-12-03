package org.ideoholic.curium.model.sendsms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendNumberSMSDto {
    private String numbers;
    private String messageBodyNumbers;
    private String department;
    private String messageBodyStaff;
}
