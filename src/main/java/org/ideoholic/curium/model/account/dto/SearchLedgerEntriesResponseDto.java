package org.ideoholic.curium.model.account.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.ideoholic.curium.model.mess.supplier.dto.MessSuppliers;

@Data
@Builder
public class SearchLedgerEntriesResponseDto {
    private Map<VoucherEntrytransactions,String> ledgerTransaction;
    private String ledgerName;
    private String accountId;
    private String fromDate;
    private String toDate;
    private String voucherType;
    private boolean success;
    private List<MessSuppliers> messSuppliersList;
}
