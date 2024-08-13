package org.ideoholic.curium.model.feescollection.dto;

import lombok.Data;
import org.ideoholic.curium.model.std.dto.Classsec;

import java.util.List;

@Data
public class CancelledReceiptsDto {
    private String branchId;
    private String toDate;
    private String fromDate;
    private String oneDay;
    private List<Classsec> classList;
}
