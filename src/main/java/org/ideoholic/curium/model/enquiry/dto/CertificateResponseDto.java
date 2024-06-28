package org.ideoholic.curium.model.enquiry.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CertificateResponseDto {
    private String name;
    private String place;
    private String mobile;
    private String date;
    private boolean success;
}
