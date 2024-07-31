package org.ideoholic.curium.model.mess.supplier.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class SupplierPaymentDto {
    private List<MessSuppliers> messSuppliersList;
    private List<MessSuppliersPayment> supplierPaymentlist;
    private int noOfPages;
    private int page;
    private boolean result;
}
