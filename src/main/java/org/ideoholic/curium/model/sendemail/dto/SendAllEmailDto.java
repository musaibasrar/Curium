package org.ideoholic.curium.model.sendemail.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendAllEmailDto {
    private String addClass;
    private String addSec;
    private String subject;
    private String messageBody;
}
