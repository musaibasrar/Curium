package org.ideoholic.curium.model.mess.stockentry.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class MessStockEntryResponseDto {
   private String supplierRefNo;
   private String invoiceTotal;
   private String supplierName;
   private String invoiceDate;
   private boolean success;
}
