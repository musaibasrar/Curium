package org.ideoholic.curium.model.mess.supplier.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class SuppliersDetailsResponseDto {
    private boolean supplierSave;
    private String supplierName;
    private List<MessSuppliers> messSuppliersList;
}