package org.ideoholic.curium.model.multitenant;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TenantRegistryEntry {
    String tenantId;
    String schemaName;
    String status;
    String displayName;
}