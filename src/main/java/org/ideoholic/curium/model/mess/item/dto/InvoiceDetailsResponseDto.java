package org.ideoholic.curium.model.mess.item.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.ideoholic.curium.model.mess.stockentry.dto.MessInvoiceDetails;
import org.ideoholic.curium.model.mess.supplier.dto.MessSuppliers;

import java.util.Map;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class InvoiceDetailsResponseDto {
    private Map<MessInvoiceDetails, MessSuppliers> invoiceSuppliersMap;
    private int noOfPages;
    private int currentPage;
    private boolean success;
}
