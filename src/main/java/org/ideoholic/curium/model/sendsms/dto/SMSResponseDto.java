package org.ideoholic.curium.model.sendsms.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.ideoholic.curium.util.SMSReportResponse;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class SMSResponseDto {

    private List<SMSReportResponse> smsDeliveryReport;
    private boolean success;
}
