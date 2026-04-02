package org.ideoholic.curium.model.account.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.ideoholic.curium.model.mess.supplier.dto.MessSuppliers;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchLedgerEntriesResponseDto {
    private Map<VoucherEntrytransactions,String> ledgerTransaction;
    private String ledgerName;
    private String accountId;
    private String fromDate;
    private String toDate;
    private String voucherType;
    private BigDecimal openingBalance;
    private BigDecimal closingBalance;
    private int accountgroupmasterid;
    private boolean success;
    private List<MessSuppliers> messSuppliersList;
}
