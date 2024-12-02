package org.ideoholic.curium.model.sendsms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendStaffSMSDto {
    private String department;
    private String messageBodyStaff;
}
