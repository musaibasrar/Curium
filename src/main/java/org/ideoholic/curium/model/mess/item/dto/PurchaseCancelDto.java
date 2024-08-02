package org.ideoholic.curium.model.mess.item.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.ideoholic.curium.model.mess.stockentry.dto.MessInvoiceDetails;
import org.ideoholic.curium.model.mess.stockentry.dto.MessStockAvailability;
import org.ideoholic.curium.model.mess.supplier.dto.MessSuppliers;

import java.util.List;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class PurchaseCancelDto {
    private List<MessSuppliers> messSuppliersList;
    private List<MessStockAvailability> messStockAvailabilityList;
    private Map<MessInvoiceDetails,MessSuppliers> invoiceSuppliersMap;
    private int currentPage;
    private int noOfPages;
    private boolean result;
}
